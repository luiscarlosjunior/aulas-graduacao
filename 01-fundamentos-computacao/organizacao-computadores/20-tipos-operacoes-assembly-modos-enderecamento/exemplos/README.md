# 🧪 Exemplos - Aula 20: Tipos de Operações, Assembly e Modos de Endereçamento

## Exemplo 1: Operações de Transferência de Dados

### Cenário: Trocar valores entre duas variáveis na memória

**Usando registradores (3 MOVs):**

```assembly
; Trocar valores de mem[100] e mem[200]
; Supondo: mem[100] = 10, mem[200] = 20

MOV  EAX, [100]      ; EAX = 10   (carrega primeiro valor)
MOV  EBX, [200]      ; EBX = 20   (carrega segundo valor)
MOV  [100], EBX      ; mem[100] = 20  (armazena segundo no primeiro)
MOV  [200], EAX      ; mem[200] = 10  (armazena primeiro no segundo)

; Resultado: mem[100] = 20, mem[200] = 10 ✅
```

**Usando XCHG (mais eficiente):**

```assembly
MOV  EAX, [100]      ; EAX = 10
XCHG EAX, [200]      ; EAX = 20, mem[200] = 10
MOV  [100], EAX      ; mem[100] = 20

; Resultado: mem[100] = 20, mem[200] = 10 ✅
```

**Usando PUSH/POP (via pilha):**

```assembly
PUSH [100]            ; Pilha: [10]
PUSH [200]            ; Pilha: [10, 20]
POP  [100]            ; mem[100] = 20, Pilha: [10]
POP  [200]            ; mem[200] = 10, Pilha: []

; Resultado: mem[100] = 20, mem[200] = 10 ✅
```

---

## Exemplo 2: Operações Aritméticas — Média de 3 Números

### Calcular: média = (A + B + C) / 3

**Valores:** A = 15, B = 27, C = 33

```assembly
; Dados na memória
; mem[A] = 15, mem[B] = 27, mem[C] = 33

MOV   EAX, [A]       ; EAX = 15
ADD   EAX, [B]       ; EAX = 15 + 27 = 42
ADD   EAX, [C]       ; EAX = 42 + 33 = 75

; Preparar divisão: EDX:EAX / divisor
; DIV divide EDX:EAX por operando
; Resultado: EAX = quociente, EDX = resto

CDQ                   ; Extensão de sinal EAX → EDX:EAX
                      ; EDX:EAX = 75 (se EAX ≥ 0, EDX = 0)
MOV   ECX, 3         ; ECX = 3 (divisor)
IDIV  ECX            ; EAX = 75 / 3 = 25, EDX = 75 % 3 = 0

MOV   [media], EAX   ; mem[media] = 25

; Resultado: média = 25 ✅
```

**Rastreamento passo a passo:**

| Instrução | EAX | EBX | ECX | EDX | Flags |
|-----------|:---:|:---:|:---:|:---:|:-----:|
| MOV EAX, [A] | 15 | — | — | — | — |
| ADD EAX, [B] | 42 | — | — | — | ZF=0, SF=0 |
| ADD EAX, [C] | 75 | — | — | — | ZF=0, SF=0 |
| CDQ | 75 | — | — | 0 | — |
| MOV ECX, 3 | 75 | — | 3 | 0 | — |
| IDIV ECX | **25** | — | 3 | **0** | — |

---

## Exemplo 3: Operações Lógicas — Manipulação de Bits

### Cenário: Registrador de controle de LED (8 LEDs)

```
Bit:    7    6    5    4    3    2    1    0
LED:   LED7 LED6 LED5 LED4 LED3 LED2 LED1 LED0

Estado inicial: AL = 0000 0000 (todos desligados)
```

```assembly
; Ligar LED3 (bit 3)
OR   AL, 00001000b    ; AL = 0000 0000 OR 0000 1000 = 0000 1000
                      ; LED3 ligado ✅

; Ligar LED0 e LED7 também
OR   AL, 10000001b    ; AL = 0000 1000 OR 1000 0001 = 1000 1001
                      ; LED7, LED3, LED0 ligados ✅

; Desligar LED3 (sem afetar outros)
AND  AL, 11110111b    ; AL = 1000 1001 AND 1111 0111 = 1000 0001
                      ; LED3 desligado, outros mantidos ✅

; Inverter LED0 (toggle)
XOR  AL, 00000001b    ; AL = 1000 0001 XOR 0000 0001 = 1000 0000
                      ; LED0 desligado (era ligado) ✅

; Verificar se LED7 está ligado
TEST AL, 10000000b    ; Faz AND sem alterar AL, atualiza flags
JNZ  led7_ligado      ; Se ZF=0 (resultado ≠ 0), LED7 está ligado
```

**Resumo das operações de bits:**

| Operação | Instrução | Máscara | Efeito |
|----------|-----------|---------|--------|
| Ligar bit N | `OR reg, (1 << N)` | `00001000b` (bit 3) | Ativa o bit |
| Desligar bit N | `AND reg, NOT(1 << N)` | `11110111b` (bit 3) | Desativa o bit |
| Inverter bit N | `XOR reg, (1 << N)` | `00001000b` (bit 3) | Toggle |
| Testar bit N | `TEST reg, (1 << N)` | `00001000b` (bit 3) | Verifica sem alterar |

---

## Exemplo 4: Controle de Fluxo — Encontrar o Maior de 3 Números

### Código em C equivalente:

```c
int max = A;
if (B > max) max = B;
if (C > max) max = C;
```

### Versão Assembly (x86):

```assembly
; Dados: A=15, B=42, C=27

    MOV   EAX, [A]       ; EAX = 15 (assume que A é o maior)

    CMP   EAX, [B]       ; Compara EAX com B (15 - 42 = -27)
    JGE   pula_B          ; Se EAX >= B, pula (não pula, pois 15 < 42)
    MOV   EAX, [B]       ; EAX = 42 (B é maior)
pula_B:

    CMP   EAX, [C]       ; Compara EAX com C (42 - 27 = 15)
    JGE   pula_C          ; Se EAX >= C, pula (pula, pois 42 > 27)
    MOV   EAX, [C]       ; (não executado)
pula_C:

    MOV   [max], EAX     ; max = 42 ✅
```

**Rastreamento:**

| Passo | Instrução | EAX | Flags | Desvia? |
|:-----:|-----------|:---:|:-----:|:-------:|
| 1 | MOV EAX, [A] | 15 | — | — |
| 2 | CMP EAX, [B] | 15 | SF=1, ZF=0 | — |
| 3 | JGE pula_B | 15 | — | Não (15 < 42) |
| 4 | MOV EAX, [B] | **42** | — | — |
| 5 | CMP EAX, [C] | 42 | SF=0, ZF=0 | — |
| 6 | JGE pula_C | 42 | — | Sim (42 > 27) |
| 7 | MOV [max], EAX | 42 | — | — |

---

## Exemplo 5: Loop — Soma de Elementos de um Array

### Somar os 5 elementos do array: [10, 20, 30, 40, 50]

```assembly
section .data
    array  dd  10, 20, 30, 40, 50    ; 5 inteiros de 32 bits
    N      equ 5                      ; Número de elementos

section .text
    ; Registradores:
    ; EAX = soma acumulada
    ; EBX = endereço base do array
    ; ECX = contador
    ; ESI = índice (offset)

    XOR   EAX, EAX          ; EAX = 0 (soma = 0)
    LEA   EBX, [array]      ; EBX = endereço base do array
    MOV   ECX, N             ; ECX = 5 (contador)
    XOR   ESI, ESI           ; ESI = 0 (índice = 0)

soma_loop:
    ADD   EAX, [EBX + ESI*4] ; EAX += array[ESI]
                              ; Base + índice × escala (4 bytes por int)
    INC   ESI                 ; ESI++ (próximo índice)
    DEC   ECX                 ; ECX-- (decrementa contador)
    JNZ   soma_loop           ; Se ECX ≠ 0, volta ao loop

    ; EAX = 10 + 20 + 30 + 40 + 50 = 150
```

**Rastreamento completo:**

| Iteração | ESI | ECX | [EBX+ESI×4] | EAX (soma) | JNZ? |
|:--------:|:---:|:---:|:-----------:|:----------:|:----:|
| 1 | 0 | 4 | array[0] = 10 | 10 | Sim |
| 2 | 1 | 3 | array[1] = 20 | 30 | Sim |
| 3 | 2 | 2 | array[2] = 30 | 60 | Sim |
| 4 | 3 | 1 | array[3] = 40 | 100 | Sim |
| 5 | 4 | 0 | array[4] = 50 | **150** | Não (sai) |

---

## Exemplo 6: Modos de Endereçamento — Todos em Ação

### Cenário: Acessar o terceiro campo de uma struct em um array

```c
// Equivalente em C:
struct Aluno {
    int id;       // offset 0
    int nota;     // offset 4
    int idade;    // offset 8
};  // tamanho total: 12 bytes

struct Aluno turma[100];  // Array de 100 alunos

// Acessar turma[5].idade
int x = turma[5].idade;
```

```assembly
; Endereços e offsets:
; Base do array "turma" = 0x2000
; Tamanho de cada struct = 12 bytes
; Offset do campo "idade" = 8
; Índice do aluno = 5

; ═══ MODO IMEDIATO ═══
MOV   ECX, 5              ; ECX = 5 (índice do aluno)
; O valor "5" está na própria instrução

; ═══ MODO POR REGISTRADOR ═══
MOV   EAX, ECX            ; EAX = ECX = 5
; Operando é um registrador

; ═══ MODO DIRETO ═══
MOV   EBX, [0x2000]       ; EBX = conteúdo do endereço 0x2000 (turma[0].id)
; Endereço está diretamente na instrução

; ═══ MODO REGISTRADOR INDIRETO ═══
LEA   EBX, [turma]        ; EBX = endereço base de turma
MOV   EAX, [EBX]          ; EAX = mem[EBX] = turma[0].id
; Registrador contém o endereço

; ═══ MODO BASE + OFFSET ═══
MOV   EAX, [EBX + 8]      ; EAX = mem[EBX + 8] = turma[0].idade
; Base (EBX) + deslocamento fixo (8)

; ═══ MODO INDEXADO COM ESCALA ═══
; Para acessar turma[5].idade:
; EA = base + índice × tamanho_struct + offset_campo
; EA = EBX + ECX × 12 + 8

; x86 não suporta escala de 12 diretamente, então:
IMUL  ESI, ECX, 12        ; ESI = 5 × 12 = 60 (offset do aluno 5)
MOV   EAX, [EBX + ESI + 8] ; EAX = mem[0x2000 + 60 + 8]
                            ;     = mem[0x2044]
                            ;     = turma[5].idade ✅
; Modo: Base + Índice + Deslocamento

; ═══ MODO RELATIVO AO PC ═══
JMP   proxima_secao        ; Desvia para rótulo (PC + offset)
; O assembler calcula o offset relativo automaticamente
```

**Mapa de memória da struct:**

```
Endereço    Conteúdo          Significado
─────────   ─────────         ──────────────────
0x2000      turma[0].id       ← EBX aponta aqui
0x2004      turma[0].nota
0x2008      turma[0].idade
0x200C      turma[1].id
0x2010      turma[1].nota
0x2014      turma[1].idade
...
0x203C      turma[5].id       ← EBX + 60
0x2040      turma[5].nota     ← EBX + 64
0x2044      turma[5].idade    ← EBX + 68 = EBX + ESI + 8 ✅
```

---

## Exemplo 7: Programa Assembly Completo — Fatorial

### Calcular 5! = 120

```assembly
; ══════════════════════════════════════════
; Programa: Calcula o fatorial de N
; N = 5 → Resultado = 120
; ══════════════════════════════════════════

section .data
    N      dd  5               ; Número para calcular fatorial
    result dd  0               ; Resultado

section .text
    global _start

_start:
    MOV   ECX, [N]            ; ECX = 5 (contador / N)
    MOV   EAX, 1              ; EAX = 1 (acumulador do resultado)

fatorial_loop:
    CMP   ECX, 1              ; ECX <= 1?
    JLE   fim_loop            ; Se sim, termina
    IMUL  EAX, ECX            ; EAX = EAX × ECX
    DEC   ECX                 ; ECX = ECX - 1
    JMP   fatorial_loop       ; Volta ao início do loop

fim_loop:
    MOV   [result], EAX       ; Armazena resultado

    ; Encerrar programa
    MOV   EAX, 1              ; syscall exit
    XOR   EBX, EBX            ; código de retorno 0
    INT   0x80
```

**Rastreamento:**

| Iteração | ECX | EAX | Operação | Resultado |
|:--------:|:---:|:---:|----------|:---------:|
| — | 5 | 1 | Inicialização | — |
| 1 | 5 | 1 × 5 = 5 | IMUL EAX, ECX | 5 |
| 2 | 4 | 5 × 4 = 20 | IMUL EAX, ECX | 20 |
| 3 | 3 | 20 × 3 = 60 | IMUL EAX, ECX | 60 |
| 4 | 2 | 60 × 2 = 120 | IMUL EAX, ECX | 120 |
| 5 | 1 | — | JLE fim_loop (ECX=1, sai) | **120** ✅ |

---

## Exemplo 8: Chamada de Função em Assembly

### Função: somar(a, b) que retorna a + b

```assembly
; ══════════════════════════════════════════
; Demonstração de CALL e RET
; Convenção de chamada: parâmetros na pilha
; ══════════════════════════════════════════

section .text
    global _start

_start:
    ; Chamar somar(10, 20)
    PUSH  20              ; Empilha segundo argumento (direita → esquerda)
    PUSH  10              ; Empilha primeiro argumento
    CALL  somar           ; Chama a função
    ADD   ESP, 8          ; Limpa os argumentos da pilha (2 × 4 bytes)
    ; EAX = 30 (resultado da função)

    ; Encerrar programa
    MOV   EBX, EAX        ; Código de retorno = resultado
    MOV   EAX, 1          ; syscall exit
    INT   0x80

; ─── Função somar ───────────────────────
somar:
    PUSH  EBP             ; Salva frame pointer antigo
    MOV   EBP, ESP        ; Novo frame pointer

    ; Layout da pilha:
    ; [EBP + 12] = 20  (segundo argumento)
    ; [EBP + 8]  = 10  (primeiro argumento)
    ; [EBP + 4]  = endereço de retorno
    ; [EBP]      = EBP antigo ← ESP/EBP aponta aqui

    MOV   EAX, [EBP + 8]  ; EAX = primeiro arg = 10
    ADD   EAX, [EBP + 12] ; EAX = EAX + segundo arg = 10 + 20 = 30

    MOV   ESP, EBP        ; Restaura stack pointer
    POP   EBP             ; Restaura frame pointer
    RET                    ; Retorna (EAX = 30)
```

**Estado da pilha durante a chamada:**

```
Antes do CALL:          Dentro de somar:          Após RET:

ESP →  ┌──────────┐    ESP/EBP → ┌──────────┐    ESP → ┌──────────┐
       │    10    │              │ EBP antigo│          │    10    │
       ├──────────┤    EBP+4  → ├──────────┤          ├──────────┤
       │    20    │              │ end.retorno│         │    20    │
       ├──────────┤    EBP+8  → ├──────────┤          ├──────────┤
       │   ...    │              │    10    │          │   ...    │
       └──────────┘    EBP+12 → ├──────────┤          └──────────┘
                                │    20    │          EAX = 30
                                ├──────────┤
                                │   ...    │
                                └──────────┘
```

---

## Exemplo 9: Modos de Endereçamento MIPS

### Instruções MIPS com diferentes modos

```assembly
# ═══ IMEDIATO ═══
addi  $t0, $zero, 100    # $t0 = 0 + 100 = 100
# O valor 100 está codificado na instrução

# ═══ REGISTRADOR ═══
add   $t2, $t0, $t1      # $t2 = $t0 + $t1
# Operandos são registradores

# ═══ BASE + OFFSET (Registrador Indireto com Deslocamento) ═══
lw    $t0, 0($s1)        # $t0 = mem[$s1 + 0]
# Base = $s1, Offset = 0

lw    $t1, 4($s1)        # $t1 = mem[$s1 + 4]
# Base = $s1, Offset = 4 (próximo word)

sw    $t2, 8($s1)        # mem[$s1 + 8] = $t2
# Base = $s1, Offset = 8

# ═══ RELATIVO AO PC ═══
beq   $t0, $t1, label    # Se $t0 == $t1, PC = PC + 4 + offset
# O offset é calculado relativamente ao PC

# ═══ PSEUDO-DIRETO (instruções J) ═══
j     0x00400020          # PC = endereço de destino
# Os 26 bits do endereço são combinados com bits do PC

# ═══ REGISTRADOR INDIRETO ═══
jr    $ra                 # PC = $ra
# Salta para o endereço contido no registrador $ra
```

**Mapa visual dos modos MIPS:**

```
┌─────────────────────────────────────────────────────┐
│           MODOS DE ENDEREÇAMENTO MIPS                │
├──────────────────┬──────────────────────────────────┤
│ Imediato         │ addi $t0, $zero, 42              │
│                  │ ori  $t1, $zero, 0xFF            │
├──────────────────┼──────────────────────────────────┤
│ Registrador      │ add  $t0, $s1, $s2              │
│                  │ sub  $t1, $t2, $t3              │
├──────────────────┼──────────────────────────────────┤
│ Base + Offset    │ lw   $t0, 8($s1)  → EA=$s1+8   │
│                  │ sw   $t0, -4($sp) → EA=$sp-4    │
├──────────────────┼──────────────────────────────────┤
│ Relativo ao PC   │ beq  $t0, $t1, +12              │
│                  │ bne  $s0, $zero, loop            │
├──────────────────┼──────────────────────────────────┤
│ Pseudo-direto    │ j    endereço                    │
│                  │ jal  funcao                      │
├──────────────────┼──────────────────────────────────┤
│ Reg. Indireto    │ jr   $ra                         │
└──────────────────┴──────────────────────────────────┘
```

---

## Exemplo 10: Comparação de Modos de Endereçamento para a Mesma Tarefa

### Tarefa: Carregar o valor 42 no registrador EAX

| Modo | Instrução x86 | Endereço Efetivo | Acessos à Memória |
|------|--------------|:----------------:|:-----------------:|
| Imediato | `MOV EAX, 42` | N/A | 0 |
| Direto | `MOV EAX, [addr_42]` | addr_42 | 1 |
| Registrador | `MOV EAX, EBX` (EBX=42) | N/A | 0 |
| Reg. Indireto | `MOV EAX, [EBX]` (EBX→42) | [EBX] | 1 |
| Base+Offset | `MOV EAX, [EBX+4]` | [EBX+4] | 1 |
| Indexado | `MOV EAX, [arr+ESI*4]` | [arr+ESI*4] | 1 |
| Indireto | `MOV EAX, [[ptr]]` | [[ptr]] | 2 |

> 📝 **Quanto mais indireto o modo, mais acessos à memória e mais lento o acesso. Modos com registrador são os mais rápidos.**
