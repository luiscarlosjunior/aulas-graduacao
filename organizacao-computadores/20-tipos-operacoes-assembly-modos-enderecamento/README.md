# ⚙️ Aula 20 - Tipos de Operações, Linguagem Assembly e Modos de Endereçamento

## 📋 Objetivos de Aprendizagem

Ao final desta aula, você será capaz de:

- ✅ Detalhar os tipos de operações: transferência, aritmética, lógica e controle de fluxo
- ✅ Compreender o que é linguagem Assembly e por que aprendê-la
- ✅ Identificar os componentes de um programa Assembly (labels, mnemonics, operandos, comentários)
- ✅ Entender o papel do assembler, linker e loader
- ✅ Dominar os modos de endereçamento e suas aplicações
- ✅ Comparar modos de endereçamento em tabela com exemplos práticos
- ✅ Relacionar Assembly com aplicações do mundo real

---

## 📚 Conteúdo

### 1. Tipos de Operações em Detalhe

#### 1.1 Operações de Transferência de Dados

As operações de transferência movem dados entre **registradores**, **memória** e **pilha**.

| Instrução | Operação | Fonte → Destino | Exemplo |
|-----------|----------|:---------------:|---------|
| **MOV** | Move (copia) | Reg→Reg, Mem→Reg, Reg→Mem, Imm→Reg | `MOV EAX, EBX` |
| **LOAD / LW** | Carrega da memória | Mem → Reg | `LW $t0, 0($s1)` |
| **STORE / SW** | Armazena na memória | Reg → Mem | `SW $t0, 0($s1)` |
| **PUSH** | Empilha | Reg/Mem/Imm → Pilha | `PUSH EAX` |
| **POP** | Desempilha | Pilha → Reg/Mem | `POP EBX` |
| **XCHG** | Troca | Reg ↔ Reg | `XCHG EAX, EBX` |
| **LEA** | Carrega endereço | Endereço → Reg | `LEA EAX, [EBX+4]` |
| **MOVZX** | Move com extensão zero | Reg/Mem → Reg (maior) | `MOVZX EAX, AL` |
| **MOVSX** | Move com extensão de sinal | Reg/Mem → Reg (maior) | `MOVSX EAX, AL` |

**Diagrama de fluxo de dados:**

```
┌──────────────┐          ┌──────────────┐
│  Registrador │◄── MOV ──►│  Registrador │
│     (fonte)  │          │  (destino)   │
└──────────────┘          └──────────────┘
       ▲                         │
       │ LOAD                    │ STORE
       │                         ▼
┌──────────────────────────────────────────┐
│              MEMÓRIA                      │
└──────────────────────────────────────────┘
       ▲                         │
       │ POP                     │ PUSH
       │                         ▼
┌──────────────────────────────────────────┐
│               PILHA                       │
└──────────────────────────────────────────┘
```

**Exemplo prático — PUSH e POP:**

```
Antes:               PUSH EAX:            POP EBX:
                     (EAX = 42)

ESP → ┌────────┐    ESP → ┌────────┐     ESP → ┌────────┐
      │  ????  │          │   42   │           │   42   │
      ├────────┤    ESP+4→├────────┤     ESP+4→├────────┤
      │  ...   │          │  ...   │           │  ...   │
      └────────┘          └────────┘           └────────┘
                                               EBX = 42
```

---

#### 1.2 Operações Aritméticas

| Instrução | Operação | Descrição | Flags Afetadas |
|-----------|----------|-----------|:--------------:|
| **ADD** | Soma | dest = dest + fonte | CF, ZF, OF, SF |
| **ADC** | Soma com carry | dest = dest + fonte + CF | CF, ZF, OF, SF |
| **SUB** | Subtração | dest = dest - fonte | CF, ZF, OF, SF |
| **SBB** | Subtração com borrow | dest = dest - fonte - CF | CF, ZF, OF, SF |
| **MUL** | Multiplicação sem sinal | EDX:EAX = EAX × fonte | CF, OF |
| **IMUL** | Multiplicação com sinal | EDX:EAX = EAX × fonte | CF, OF |
| **DIV** | Divisão sem sinal | EAX = EDX:EAX / fonte; EDX = resto | — |
| **IDIV** | Divisão com sinal | EAX = EDX:EAX / fonte; EDX = resto | — |
| **INC** | Incremento | dest = dest + 1 | ZF, OF, SF |
| **DEC** | Decremento | dest = dest - 1 | ZF, OF, SF |
| **NEG** | Negação | dest = -dest (compl. 2) | CF, ZF, OF, SF |
| **CMP** | Compara | Calcula dest - fonte (sem armazenar) | CF, ZF, OF, SF |

**Registrador de FLAGS (bits importantes):**

```
┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
│ OF │ DF │ IF │ TF │ SF │ ZF │    │ AF │    │ PF │ CF │
│ 11 │ 10 │  9 │  8 │  7 │  6 │  5 │  4 │  3 │  2 │  0 │
└────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘

CF (Carry Flag):     1 se houve vai-um/empresta-um (sem sinal)
ZF (Zero Flag):      1 se resultado é zero
SF (Sign Flag):      1 se resultado é negativo (bit MSB = 1)
OF (Overflow Flag):  1 se houve overflow (com sinal)
PF (Parity Flag):    1 se nº de bits 1 no resultado é par
```

**Exemplo — CMP e desvio condicional:**

```assembly
; Comparar se EAX == 5
CMP  EAX, 5         ; Calcula EAX - 5, atualiza flags
                     ; Se EAX = 5: resultado = 0, ZF = 1
                     ; Se EAX ≠ 5: resultado ≠ 0, ZF = 0
JE   igual           ; Desvia se ZF = 1 (Equal/Zero)
; código se EAX ≠ 5
JMP  fim
igual:
; código se EAX == 5
fim:
```

---

#### 1.3 Operações Lógicas e de Bit

| Instrução | Operação | Exemplo (bit a bit) | Uso Comum |
|-----------|----------|:-------------------:|-----------|
| **AND** | E lógico | 1100 AND 1010 = 1000 | Mascarar bits, limpar bits |
| **OR** | OU lógico | 1100 OR 1010 = 1110 | Ativar bits |
| **NOT** | Inversão | NOT 1100 = 0011 | Complemento |
| **XOR** | OU exclusivo | 1100 XOR 1010 = 0110 | Inverter bits, zerar registrador |
| **SHL/SAL** | Shift esquerda | 00110 SHL 1 = 01100 | Multiplicar por 2 |
| **SHR** | Shift direita lógico | 01100 SHR 1 = 00110 | Dividir por 2 (sem sinal) |
| **SAR** | Shift direita aritmético | 11100 SAR 1 = 11110 | Dividir por 2 (com sinal) |
| **ROL** | Rotação esquerda | 1001 ROL 1 = 0011 (com wrap) | Criptografia, hash |
| **ROR** | Rotação direita | 1001 ROR 1 = 1100 (com wrap) | Criptografia, hash |
| **TEST** | AND sem armazenar | Atualiza flags | Testar bit específico |

**Aplicações práticas de operações lógicas:**

```assembly
; 1. Zerar um registrador (XOR consigo mesmo — mais rápido que MOV)
XOR  EAX, EAX          ; EAX = 0

; 2. Testar se um bit está ativo (bit 3 do EAX)
TEST EAX, 00001000b    ; Faz AND, atualiza ZF
JNZ  bit3_ativo         ; Se ZF=0, o bit 3 está ativo

; 3. Ativar o bit 5 do EAX
OR   EAX, 00100000b    ; EAX = EAX | 0x20

; 4. Desativar o bit 5 do EAX
AND  EAX, 11011111b    ; EAX = EAX & ~0x20

; 5. Inverter o bit 5 do EAX
XOR  EAX, 00100000b    ; EAX = EAX ^ 0x20

; 6. Multiplicar por 8 (shift left 3 posições)
SHL  EAX, 3            ; EAX = EAX × 2³ = EAX × 8

; 7. Dividir por 4 (shift right 2 posições)
SHR  EAX, 2            ; EAX = EAX / 2² = EAX / 4
```

**Diagramas de Shift e Rotação:**

```
SHL (Shift Left) — Shift Lógico/Aritmético à Esquerda:
┌────┐    ┌───┬───┬───┬───┬───┬───┬───┬───┐
│ CF │◄───│ b7│ b6│ b5│ b4│ b3│ b2│ b1│ b0│◄── 0
└────┘    └───┴───┴───┴───┴───┴───┴───┴───┘
                    ◄─── deslocam ◄───

SHR (Shift Right) — Shift Lógico à Direita:
          ┌───┬───┬───┬───┬───┬───┬───┬───┐    ┌────┐
  0 ────►│ b7│ b6│ b5│ b4│ b3│ b2│ b1│ b0│───►│ CF │
          └───┴───┴───┴───┴───┴───┴───┴───┘    └────┘
                    ───► deslocam ───►

SAR (Shift Right) — Shift Aritmético à Direita:
          ┌───┬───┬───┬───┬───┬───┬───┬───┐    ┌────┐
  b7 ──┬►│ b7│ b6│ b5│ b4│ b3│ b2│ b1│ b0│───►│ CF │
       │  └───┴───┴───┴───┴───┴───┴───┴───┘    └────┘
       └── Mantém o bit de sinal!

ROL (Rotate Left) — Rotação à Esquerda:
  ┌──────────────────────────────────────────┐
  │  ┌───┬───┬───┬───┬───┬───┬───┬───┐      │
  └─►│ b7│ b6│ b5│ b4│ b3│ b2│ b1│ b0│◄─────┘
     └───┴───┴───┴───┴───┴───┴───┴───┘
  O bit que sai pela esquerda entra pela direita
```

---

#### 1.4 Operações de Controle de Fluxo

| Instrução | Tipo | Condição | Descrição |
|-----------|------|----------|-----------|
| **JMP** | Incondicional | Sempre | Desvia para endereço |
| **JE / JZ** | Condicional | ZF = 1 | Desvia se igual/zero |
| **JNE / JNZ** | Condicional | ZF = 0 | Desvia se não igual/não zero |
| **JG / JNLE** | Condicional | ZF=0 e SF=OF | Desvia se maior (com sinal) |
| **JGE / JNL** | Condicional | SF = OF | Desvia se maior ou igual |
| **JL / JNGE** | Condicional | SF ≠ OF | Desvia se menor (com sinal) |
| **JLE / JNG** | Condicional | ZF=1 ou SF≠OF | Desvia se menor ou igual |
| **JA / JNBE** | Condicional | CF=0 e ZF=0 | Desvia se acima (sem sinal) |
| **JB / JNAE** | Condicional | CF = 1 | Desvia se abaixo (sem sinal) |
| **CALL** | Sub-rotina | — | Empilha IP, desvia para função |
| **RET** | Retorno | — | Desempilha IP, retorna |
| **LOOP** | Loop | ECX ≠ 0 | Decrementa ECX, desvia se ≠ 0 |

**Diagrama de CALL e RET:**

```
Antes do CALL:        Após CALL func:       Após RET:
                                            (dentro de func)
IP → instrução A      IP → func             IP → instrução B
     instrução B           ...               
     CALL func             ...
     instrução C           RET
                      
Pilha:                Pilha:                Pilha:
┌──────────┐         ┌──────────┐          ┌──────────┐
│   ...    │         │ end. de C│← ESP     │   ...    │
└──────────┘         ├──────────┤          └──────────┘
                     │   ...    │
                     └──────────┘

CALL: empilha endereço de retorno (instrução C) e desvia
RET: desempilha endereço de retorno e desvia para lá
```

**Estruturas de controle comuns:**

```assembly
; ═══ IF-ELSE ═══
; if (EAX > 10) { ... } else { ... }
    CMP   EAX, 10
    JLE   else_bloco      ; Se EAX <= 10, vai para else
    ; código do IF
    JMP   fim_if
else_bloco:
    ; código do ELSE
fim_if:

; ═══ WHILE ═══
; while (ECX > 0) { ECX--; soma += vetor[i]; }
while_inicio:
    CMP   ECX, 0
    JLE   while_fim       ; Se ECX <= 0, sai do loop
    ; corpo do loop
    DEC   ECX
    JMP   while_inicio
while_fim:

; ═══ FOR ═══
; for (ECX = 10; ECX > 0; ECX--) { ... }
    MOV   ECX, 10
for_inicio:
    CMP   ECX, 0
    JLE   for_fim
    ; corpo do loop
    DEC   ECX
    JMP   for_inicio
for_fim:

; ═══ FOR (com LOOP) ═══
    MOV   ECX, 10         ; Contador
loop_inicio:
    ; corpo do loop
    LOOP  loop_inicio     ; Decrementa ECX, desvia se ECX ≠ 0
```

---

### 2. Introdução à Linguagem Assembly

#### 2.1 O que é Assembly?

**Assembly** (ou linguagem de montagem) é uma **linguagem de programação de baixo nível** que representa as instruções de máquina usando **mnemônicos** legíveis por humanos, em vez de código binário.

```
                Nível de abstração
                
     Alto │  Python:    x = a + b
          │  C:         x = a + b;
          │  Assembly:  MOV EAX, [a]
          │             ADD EAX, [b]
          │             MOV [x], EAX
     Baixo│  Máquina:   10001011 00000101 ...
                        00000011 00000101 ...
                        10001001 00000101 ...
```

#### 2.2 Por que Aprender Assembly?

```
┌─────────────────────────────────────────────────────────┐
│           POR QUE APRENDER ASSEMBLY?                     │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  🔍 Entender como o computador realmente funciona       │
│     → Base para otimização de software                  │
│                                                         │
│  🐛 Depuração (debugging) avançada                      │
│     → Analisar crashes e bugs difíceis                  │
│                                                         │
│  🔒 Segurança e engenharia reversa                      │
│     → Análise de malware, exploits, CTF                 │
│                                                         │
│  ⚡ Programação de alto desempenho                      │
│     → Trechos críticos, drivers, kernel do SO           │
│                                                         │
│  🔧 Sistemas embarcados                                 │
│     → Microcontroladores com recursos limitados         │
│                                                         │
│  📚 Fundamento acadêmico                                │
│     → Entender compiladores e arquitetura               │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

#### 2.3 O Processo: Código Fonte → Programa Executável

```
┌─────────────────┐
│  Código-fonte    │    programa.asm
│  Assembly        │    (texto legível)
└────────┬────────┘
         │
         ▼  ASSEMBLER (Montador)
┌─────────────────┐
│ Código Objeto    │    programa.o / programa.obj
│ (binário parcial)│    (código de máquina, mas referências
└────────┬────────┘     não resolvidas)
         │
         │◄─── Outros .o + Bibliotecas (.lib / .a)
         ▼  LINKER (Ligador/Vinculador)
┌─────────────────┐
│  Executável      │    programa.exe / programa
│  (binário final) │    (código completo, endereços resolvidos)
└────────┬────────┘
         │
         ▼  LOADER (Carregador)
┌─────────────────┐
│  Programa na     │    Na memória RAM
│  Memória         │    (pronto para execução)
└─────────────────┘
```

| Componente | Função | Entrada | Saída |
|-----------|--------|---------|-------|
| **Assembler** | Traduz mnemônicos em código de máquina | .asm | .o / .obj |
| **Linker** | Combina módulos e resolve referências | .o + .lib | executável |
| **Loader** | Carrega o executável na memória | executável | programa em execução |

#### 2.4 Sintaxe do Assembly

Um programa Assembly é composto por **linhas de instrução** com até 4 campos:

```
[rótulo:]    mnemônico    [operandos]    [; comentário]

Exemplos:
inicio:      MOV          EAX, 0         ; Zera o acumulador
             ADD          EAX, EBX       ; Soma EBX ao acumulador
loop:        DEC          ECX            ; Decrementa contador
             JNZ          loop           ; Volta se não zero
fim:         RET                         ; Retorna
```

| Campo | Descrição | Obrigatório? |
|-------|-----------|:------------:|
| **Rótulo (label)** | Nome simbólico para o endereço desta linha | Não |
| **Mnemônico** | Nome da instrução (MOV, ADD, JMP...) | Sim |
| **Operandos** | Dados ou endereços da instrução | Depende da instrução |
| **Comentário** | Texto explicativo (após `;`) | Não |

#### 2.5 Diretivas do Assembler

Diretivas são **comandos para o assembler**, não geram código de máquina:

| Diretiva | Função | Exemplo |
|----------|--------|---------|
| **DB** (Define Byte) | Define dados de 1 byte | `msg DB 'Hello', 0` |
| **DW** (Define Word) | Define dados de 2 bytes | `valor DW 1234h` |
| **DD** (Define Doubleword) | Define dados de 4 bytes | `num DD 100` |
| **DQ** (Define Quadword) | Define dados de 8 bytes | `big DQ 1000000` |
| **RESB/RESW/RESD** | Reserva espaço (NASM) | `buffer RESB 256` |
| **SECTION / SEGMENT** | Define seção do programa | `SECTION .data` |
| **EQU** | Define constante simbólica | `MAX EQU 100` |
| **GLOBAL / EXTERN** | Exporta/importa símbolo | `GLOBAL _start` |

#### 2.6 Estrutura de um Programa Assembly (NASM/Linux)

```assembly
; ═══════════════════════════════════════════
; Programa: Olá Mundo em Assembly (Linux x86)
; Assembler: NASM
; ═══════════════════════════════════════════

section .data                    ; Seção de dados inicializados
    mensagem db 'Ola, Mundo!', 10  ; String + newline (10 = '\n')
    tamanho  equ $ - mensagem      ; Calcula tamanho da string

section .bss                     ; Seção de dados não inicializados
    buffer resb 256              ; Reserva 256 bytes

section .text                    ; Seção de código
    global _start                ; Ponto de entrada

_start:                          ; Rótulo de início
    ; ─── Escrever na tela (syscall write) ───
    mov eax, 4                   ; Syscall número 4 = write
    mov ebx, 1                   ; File descriptor 1 = stdout
    mov ecx, mensagem            ; Ponteiro para a string
    mov edx, tamanho             ; Tamanho da string
    int 0x80                     ; Chama o kernel

    ; ─── Encerrar programa (syscall exit) ───
    mov eax, 1                   ; Syscall número 1 = exit
    mov ebx, 0                   ; Código de retorno 0
    int 0x80                     ; Chama o kernel
```

**Dois estilos de sintaxe:**

| Aspecto | Sintaxe Intel (NASM/MASM) | Sintaxe AT&T (GAS) |
|---------|:------------------------:|:-------------------:|
| Ordem dos operandos | `MOV destino, fonte` | `mov fonte, destino` |
| Prefixo de registrador | Nenhum (`EAX`) | `%` (`%eax`) |
| Prefixo de imediato | Nenhum (`5`) | `$` (`$5`) |
| Tamanho de memória | `DWORD PTR [EBX]` | `(%ebx)` com sufixo `l` |
| Exemplo | `MOV EAX, [EBX+4]` | `movl 4(%ebx), %eax` |

---

### 3. Modos de Endereçamento

Os **modos de endereçamento** definem **como a CPU calcula o endereço efetivo** do operando de uma instrução. São fundamentais para acessar dados na memória e registradores.

#### 3.1 Modo Imediato

O **operando está na própria instrução** — é uma constante.

```
┌────────┬────────────────┐
│ Opcode │   Operando     │   O dado está AQUI, dentro da instrução
│        │   (valor)      │
└────────┴────────────────┘

Exemplo: MOV EAX, 42       ; EAX = 42
         ADD R1, R2, #100   ; R1 = R2 + 100

Endereço efetivo: Não há — o dado é imediato
Uso: Constantes, inicializações
```

#### 3.2 Modo Direto (Absoluto)

A instrução contém o **endereço de memória** onde está o dado.

```
┌────────┬────────────────┐     ┌──────────────┐
│ Opcode │   Endereço     │────►│   MEMÓRIA    │
│        │   (1000)       │     │  [1000] = 42 │
└────────┴────────────────┘     └──────────────┘

Exemplo: MOV EAX, [1000h]   ; EAX = conteúdo da posição 1000h
         LOAD R1, [500]      ; R1 = mem[500]

Endereço efetivo: EA = endereço na instrução
Uso: Variáveis globais
```

#### 3.3 Modo Indireto

A instrução contém o **endereço de uma posição de memória** que, por sua vez, contém o **endereço real** do dado.

```
┌────────┬────────────────┐     ┌──────────────┐     ┌──────────────┐
│ Opcode │   Endereço     │────►│   MEMÓRIA    │────►│   MEMÓRIA    │
│        │   (500)        │     │ [500] = 1000 │     │ [1000] = 42  │
└────────┴────────────────┘     └──────────────┘     └──────────────┘
                                                     O dado está aqui!

Exemplo: MOV EAX, [[500]]   ; EAX = mem[mem[500]]

Endereço efetivo: EA = conteúdo de mem[endereço]
Uso: Ponteiros, acesso indireto
```

#### 3.4 Modo por Registrador

O operando está em um **registrador**.

```
┌────────┬──────┐     ┌──────────────┐
│ Opcode │ Reg  │────►│ Registrador  │
│        │ (R1) │     │   R1 = 42    │
└────────┴──────┘     └──────────────┘

Exemplo: MOV EAX, EBX    ; EAX = EBX
         ADD R1, R2, R3   ; R1 = R2 + R3

Endereço efetivo: Não há endereço de memória
Uso: Operações mais rápidas (registrador é mais rápido que memória)
```

#### 3.5 Modo Registrador Indireto

O **registrador contém o endereço** de memória onde está o dado.

```
┌────────┬──────┐     ┌──────────────┐     ┌──────────────┐
│ Opcode │ Reg  │────►│ Registrador  │────►│   MEMÓRIA    │
│        │ (R1) │     │ R1 = 1000    │     │ [1000] = 42  │
└────────┴──────┘     └──────────────┘     └──────────────┘
                       R1 aponta para          O dado está aqui!
                       o endereço 1000

Exemplo: MOV EAX, [EBX]   ; EAX = mem[EBX]
         LW  $t0, 0($s1)  ; $t0 = mem[$s1]

Endereço efetivo: EA = conteúdo do registrador
Uso: Ponteiros, percorrer arrays
```

#### 3.6 Modos com Deslocamento (Displacement)

Combinam um **registrador** com um **deslocamento (offset)** constante.

##### a) Deslocamento (Base + Offset)

```
┌────────┬──────┬────────┐     ┌──────────────┐     ┌──────────────┐
│ Opcode │ Reg  │ Offset │────►│ Registrador  │     │   MEMÓRIA    │
│        │ (R1) │ (+8)   │     │ R1 = 1000    │     │ [1008] = 42  │
└────────┴──────┴────────┘     └──────┬───────┘     └──────────────┘
                                      │                    ▲
                                      └── 1000 + 8 = 1008─┘

EA = Reg + Offset

Exemplo: MOV EAX, [EBX+8]    ; EAX = mem[EBX + 8]
         LW  $t0, 8($s1)     ; $t0 = mem[$s1 + 8]
```

**Variações:**

| Modo | Fórmula | Uso Típico | Exemplo |
|------|---------|-----------|---------|
| **Base-Register** | EA = Reg_base + offset | Acesso a campos de struct | `MOV EAX, [EBP+8]` (parâmetro de função) |
| **Indexado** | EA = endereço + Reg_índice | Acesso a arrays | `MOV EAX, [array + ESI]` |
| **Base-Indexado** | EA = Reg_base + Reg_índice | Array dentro de struct | `MOV EAX, [EBX + ESI]` |
| **Base-Indexado-Deslocamento** | EA = Reg_base + Reg_índice × escala + offset | Array de structs | `MOV EAX, [EBX + ESI*4 + 8]` |
| **Relativo ao PC** | EA = PC + offset | Desvios, dados locais | `BEQ $t0, $t1, +16` |

##### b) Exemplo visual — Acesso a Array:

```
Array de inteiros (4 bytes cada) a partir do endereço 2000:

Endereço:  2000   2004   2008   2012   2016
Índice:    [0]    [1]    [2]    [3]    [4]
Valor:      10     20     30     40     50

Para acessar array[3]:
  MOV EBX, 2000        ; EBX = endereço base do array
  MOV ESI, 3           ; ESI = índice
  MOV EAX, [EBX + ESI*4]  ; EAX = mem[2000 + 3×4] = mem[2012] = 40

EA = 2000 + 3 × 4 = 2012
```

##### c) Endereçamento Relativo ao PC:

```
Instrução em 0x1000: BEQ $t0, $t1, +12

Se condição verdadeira:
  PC novo = PC_atual + 4 + 12 = 0x1000 + 4 + 12 = 0x1010

┌────────────────┐
│ 0x1000: BEQ    │──── Se verdadeiro ────►┌──────────────┐
│ 0x1004: ADD    │                         │ 0x1010: SUB  │
│ 0x1008: SUB    │                         │              │
│ 0x100C: MUL    │                         └──────────────┘
│ 0x1010: SUB    │◄── Desvia para cá
└────────────────┘
```

#### 3.7 Modo Pilha (Stack)

O operando está implicitamente no **topo da pilha**, acessado via Stack Pointer (SP).

```
PUSH EAX:                      POP EBX:
1. SP = SP - 4                 1. EBX = mem[SP]
2. mem[SP] = EAX               2. SP = SP + 4

Pilha (cresce para baixo):
     Endereços altos
     ┌──────────────┐
     │     ...      │
     ├──────────────┤
SP→  │  Topo (TOS)  │ ← PUSH coloca aqui / POP pega daqui
     ├──────────────┤
     │  TOS - 1     │
     ├──────────────┤
     │  TOS - 2     │
     └──────────────┘
     Endereços baixos
```

---

### 4. Tabela Comparativa dos Modos de Endereçamento

| Modo | EA (Endereço Efetivo) | Exemplo (x86) | Exemplo (MIPS/ARM) | Vantagem | Desvantagem |
|------|:--------------------:|:-------------:|:-------------------:|----------|-------------|
| **Imediato** | Não há (dado na instrução) | `MOV EAX, 5` | `ADDI $t0, $zero, 5` | Rápido, sem acesso à memória | Valor limitado ao tamanho do campo |
| **Direto** | EA = endereço | `MOV EAX, [1000h]` | — | Simples | Endereço fixo, pouco flexível |
| **Indireto** | EA = mem[endereço] | `MOV EAX, [[1000h]]` | — | Ponteiros de ponteiros | 2 acessos à memória (lento) |
| **Registrador** | Não há (dado no reg.) | `MOV EAX, EBX` | `ADD $t0, $s1, $s2` | Muito rápido | Poucos registradores |
| **Reg. Indireto** | EA = Reg | `MOV EAX, [EBX]` | `LW $t0, 0($s1)` | Flexível, ponteiros | 1 acesso à memória |
| **Base + Offset** | EA = Reg + offset | `MOV EAX, [EBP+8]` | `LW $t0, 8($s1)` | Structs, pilha | Offset limitado |
| **Indexado** | EA = base + Reg × escala | `MOV EAX, [arr+ESI*4]` | — | Arrays | Mais complexo |
| **Relativo PC** | EA = PC + offset | `JMP +100` | `BEQ $t0, $t1, label` | Código relocável | Alcance limitado |
| **Pilha** | EA = SP (implícito) | `PUSH EAX` / `POP EBX` | — | Simples para sub-rotinas | Acesso limitado ao topo |

---

### 5. Aplicações no Mundo Real

#### 5.1 Debugging e Engenharia Reversa

```
┌─────────────────────────────────────────────────────────┐
│    APLICAÇÕES DE ASSEMBLY NO MUNDO REAL                  │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  🐛 Debugging                                           │
│  → Analisar core dumps e crashes em programas C/C++     │
│  → Ferramentas: GDB, WinDbg, IDA Pro                   │
│                                                         │
│  🔒 Segurança / Engenharia Reversa                      │
│  → Análise de malware                                   │
│  → Desenvolvimento de exploits (buffer overflow)        │
│  → Competições CTF (Capture The Flag)                   │
│                                                         │
│  ⚡ Performance Crítica                                  │
│  → Kernels de SO (boot, interrupt handlers)             │
│  → Drivers de hardware                                  │
│  → Codecs de áudio/vídeo (trechos otimizados)          │
│  → Instruções SIMD (SSE, AVX) em jogos                 │
│                                                         │
│  🔧 Sistemas Embarcados                                 │
│  → Microcontroladores (Arduino, PIC, ARM Cortex-M)     │
│  → Firmware, bootloaders                                │
│  → Dispositivos IoT com memória limitada                │
│                                                         │
│  🖥️ Compiladores                                        │
│  → Back-end de compiladores gera assembly/código máquina│
│  → Otimização de código gerado                          │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

#### 5.2 Exemplo: Buffer Overflow (Conceitual)

```assembly
; Função vulnerável em assembly (conceito educacional)
funcao_vulneravel:
    push  ebp              ; Salva frame pointer
    mov   ebp, esp         ; Novo frame
    sub   esp, 64          ; Reserva 64 bytes para buffer local

    ; Buffer de 64 bytes no stack:
    ; [EBP-64] até [EBP-1] = buffer
    ; [EBP]    = EBP salvo
    ; [EBP+4]  = Endereço de retorno ← ALVO do ataque!

    ; Se dados do usuário excedem 64 bytes, sobrescrevem
    ; o endereço de retorno, permitindo desviar a execução

    mov   esp, ebp         ; Restaura stack
    pop   ebp              ; Restaura frame pointer
    ret                    ; Retorna (para endereço possivelmente corrompido!)

; É por isso que existem proteções como:
; - Stack canaries (valores de proteção)
; - ASLR (randomização de endereços)
; - DEP/NX (pilha não executável)
```

---

### 6. Resumo da Aula

```
┌─────────────────────────────────────────────────────────┐
│                    RESUMO - AULA 20                      │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  🔹 Tipos de operações:                                 │
│     • Transferência: MOV, LOAD, STORE, PUSH, POP       │
│     • Aritmética: ADD, SUB, MUL, DIV, INC, DEC         │
│     • Lógica: AND, OR, NOT, XOR, SHL, SHR, ROL        │
│     • Controle: JMP, JZ, JNZ, CALL, RET, LOOP         │
│                                                         │
│  🔹 Assembly: linguagem de baixo nível com mnemônicos   │
│     → Assembler traduz para código de máquina           │
│     → Linker combina módulos                            │
│     → Loader carrega na memória                         │
│                                                         │
│  🔹 Sintaxe: [label:] mnemônico [operandos] [;coment]  │
│                                                         │
│  🔹 Modos de endereçamento:                             │
│     • Imediato: dado na instrução                       │
│     • Direto: endereço na instrução                     │
│     • Indireto: ponteiro para ponteiro                  │
│     • Registrador: dado no registrador                  │
│     • Registrador indireto: reg aponta para memória     │
│     • Base + offset: struct, stack frame, arrays        │
│     • Indexado: arrays com escala                        │
│     • Relativo ao PC: desvios, código relocável         │
│     • Pilha: operandos implícitos no topo               │
│                                                         │
│  🔹 Aplicações: debugging, segurança, embarcados,       │
│     compiladores, performance crítica                   │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

---

## 📖 Referências

1. STALLINGS, W. **Arquitetura e Organização de Computadores**. 10ª ed. Pearson, 2017.
2. PATTERSON, D. A.; HENNESSY, J. L. **Organização e Projeto de Computadores: A Interface Hardware/Software**. 5ª ed. Elsevier, 2017.
3. TANENBAUM, A. S. **Organização Estruturada de Computadores**. 6ª ed. Pearson, 2013.
4. HYDE, R. **The Art of Assembly Language**. 2ª ed. No Starch Press, 2010.
5. DUNTEMANN, J. **Assembly Language Step-by-Step**. 3ª ed. Wiley, 2009.
6. Intel Corporation. **Intel® 64 and IA-32 Architectures Software Developer's Manual**.

---

> 💡 **Parabéns!** Você completou o módulo sobre Organização de Computadores. Com o conhecimento de pipeline, arquiteturas paralelas, ISA, assembly e modos de endereçamento, você tem uma base sólida para entender como os computadores modernos funcionam em seu nível mais fundamental.
