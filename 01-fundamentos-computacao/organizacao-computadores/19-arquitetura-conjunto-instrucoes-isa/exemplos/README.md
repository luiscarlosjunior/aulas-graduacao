# 🧪 Exemplos - Aula 19: Arquitetura do Conjunto de Instruções (ISA)

## Exemplo 1: Avaliação de Expressão com Diferentes Números de Endereços

### Expressão: Y = (A - B) / (C + D × E)

#### 3 Endereços (estilo MIPS/ARM):

```assembly
MUL  R1, D, E       ; R1 = D × E
ADD  R2, C, R1      ; R2 = C + R1 = C + D×E
SUB  R3, A, B       ; R3 = A - B
DIV  Y,  R3, R2     ; Y  = R3 / R2 = (A-B) / (C+D×E)

Total: 4 instruções
```

#### 2 Endereços (estilo x86):

```assembly
MOV  R1, D          ; R1 = D
MUL  R1, E          ; R1 = R1 × E = D×E
MOV  R2, C          ; R2 = C
ADD  R2, R1         ; R2 = R2 + R1 = C + D×E
MOV  R1, A          ; R1 = A
SUB  R1, B          ; R1 = R1 - B = A - B
DIV  R1, R2         ; R1 = R1 / R2 = (A-B) / (C+D×E)
MOV  Y,  R1         ; Y = R1

Total: 8 instruções
```

#### 1 Endereço (estilo acumulador):

```assembly
LOAD  D             ; ACC = D
MUL   E             ; ACC = ACC × E = D×E
ADD   C             ; ACC = ACC + C = C + D×E
STORE T1            ; T1 = ACC (salva temporário)
LOAD  A             ; ACC = A
SUB   B             ; ACC = ACC - B = A - B
DIV   T1            ; ACC = ACC / T1 = (A-B) / (C+D×E)
STORE Y             ; Y = ACC

Total: 8 instruções
```

#### 0 Endereços (pilha):

```assembly
PUSH  A             ; Pilha: [A]
PUSH  B             ; Pilha: [A, B]
SUB                 ; Pilha: [A-B]
PUSH  C             ; Pilha: [A-B, C]
PUSH  D             ; Pilha: [A-B, C, D]
PUSH  E             ; Pilha: [A-B, C, D, E]
MUL                 ; Pilha: [A-B, C, D×E]
ADD                 ; Pilha: [A-B, C+D×E]
DIV                 ; Pilha: [(A-B)/(C+D×E)]
POP   Y             ; Y = (A-B)/(C+D×E)

Total: 10 instruções
```

### Resumo Comparativo

| Tipo | Instruções | Acessos à Memória | Tamanho Total Est. |
|------|:----------:|:-----------------:|:------------------:|
| 3 endereços | 4 | 4 (operandos A-E, Y) | 4 × 4 bytes = 16 bytes |
| 2 endereços | 8 | 6 | 8 × 3 bytes = 24 bytes |
| 1 endereço | 8 | 8 (+ 2 para T1) | 8 × 2 bytes = 16 bytes |
| 0 endereços | 10 | 6 (push/pop) | 10 × 1 byte = 10 bytes |

> 💡 Instruções de 0 endereços são menores individualmente, mas precisamos de mais delas!

---

## Exemplo 2: Codificação de Instrução MIPS

### Codificar: `ADD $t0, $s1, $s2`

**Informações:**
- ADD é instrução Tipo R
- $t0 = registrador 8 (destino, rd)
- $s1 = registrador 17 (fonte, rs)
- $s2 = registrador 18 (fonte, rt)

**Formato Tipo R:**
```
┌────────┬───────┬───────┬───────┬───────┬────────┐
│ opcode │  rs   │  rt   │  rd   │ shamt │ funct  │
│ 6 bits │5 bits │5 bits │5 bits │5 bits │ 6 bits │
└────────┴───────┴───────┴───────┴───────┴────────┘
```

**Preenchimento:**
```
opcode = 000000  (Tipo R)
rs     = 10001   ($s1 = 17 em binário)
rt     = 10010   ($s2 = 18 em binário)
rd     = 01000   ($t0 = 8 em binário)
shamt  = 00000   (não é shift)
funct  = 100000  (ADD)
```

**Resultado:**
```
Binário: 000000 10001 10010 01000 00000 100000
         ────── ───── ───── ───── ───── ──────

Agrupando em 4 bits: 0000 0010 0011 0010 0100 0000 0010 0000

Hexadecimal: 0x02324020
```

---

## Exemplo 3: Codificação de Instrução MIPS — Tipo I

### Codificar: `LW $t0, 32($s3)`

**Informações:**
- LW (Load Word) é instrução Tipo I
- opcode de LW = 100011
- $t0 = registrador 8 (rt, destino)
- $s3 = registrador 19 (rs, base)
- Offset = 32

**Formato Tipo I:**
```
┌────────┬───────┬───────┬──────────────────────┐
│ opcode │  rs   │  rt   │     imediato         │
│ 6 bits │5 bits │5 bits │     16 bits          │
└────────┴───────┴───────┴──────────────────────┘
```

**Preenchimento:**
```
opcode   = 100011  (LW)
rs       = 10011   ($s3 = 19)
rt       = 01000   ($t0 = 8)
imediato = 0000000000100000  (32 em binário, 16 bits)
```

**Resultado:**
```
Binário: 100011 10011 01000 0000000000100000

Hexadecimal: 0x8E680020
```

---

## Exemplo 4: Endianness na Prática

### Armazenando o valor 0x0A0B0C0D a partir do endereço 200

**Big-Endian:**
```
Endereço: 200   201   202   203
Conteúdo: 0x0A  0x0B  0x0C  0x0D

Leitura do byte no endereço 200: 0x0A (byte mais significativo)
```

**Little-Endian:**
```
Endereço: 200   201   202   203
Conteúdo: 0x0D  0x0C  0x0B  0x0A

Leitura do byte no endereço 200: 0x0D (byte menos significativo)
```

### Impacto prático — Leitura parcial:

**Se lermos apenas 2 bytes (halfword) a partir do endereço 200:**

```
Big-Endian:    0x0A0B (bytes mais significativos)
Little-Endian: 0x0C0D (bytes menos significativos)

⚠️ Valores completamente diferentes!
```

### Outro exemplo — Armazenando o inteiro 1 (0x00000001):

```
Big-Endian:
End: 100  101  102  103
     00   00   00   01    ← "1" está no final

Little-Endian:
End: 100  101  102  103
     01   00   00   00    ← "1" está no início

Vantagem do Little-Endian: Para converter um valor de 32 bits
para 8 bits, basta ler o byte no mesmo endereço (100).
O byte no endereço 100 é sempre o LSB.
```

---

## Exemplo 5: Arquitetura de Pilha — Avaliação de Expressão Infixa

### Converter e avaliar: `3 + 4 × 2 - 1`

**Passo 1: Notação pós-fixa (RPN):**
```
Infixa:   3 + 4 × 2 - 1
Pós-fixa: 3 4 2 × + 1 -
```

**Passo 2: Execução na máquina de pilha:**

```
Instrução    Pilha (base → topo)     Explicação
─────────    ──────────────────      ──────────────
PUSH 3       [3]                     Empilha 3
PUSH 4       [3, 4]                  Empilha 4
PUSH 2       [3, 4, 2]              Empilha 2
MUL          [3, 8]                  4 × 2 = 8
ADD          [11]                    3 + 8 = 11
PUSH 1       [11, 1]                Empilha 1
SUB          [10]                    11 - 1 = 10

Resultado: 10 ✅
```

**Verificação:** 3 + 4 × 2 - 1 = 3 + 8 - 1 = 10 ✅

---

## Exemplo 6: Registradores de ISAs Reais

### Registradores x86-64

```
Registradores de propósito geral (64 bits):
┌──────────────────────────────────────────────────────────┐
│  RAX  │  Acumulador, retorno de funções                  │
│  RBX  │  Base, propósito geral                           │
│  RCX  │  Contador (loops)                                │
│  RDX  │  Dados, extensão de RAX                          │
│  RSI  │  Índice fonte (source index)                     │
│  RDI  │  Índice destino (destination index)              │
│  RBP  │  Ponteiro base do stack frame                    │
│  RSP  │  Ponteiro do topo da pilha (stack pointer)       │
│  R8   │  Propósito geral (novo em x86-64)                │
│  R9   │  Propósito geral                                 │
│  R10  │  Propósito geral                                 │
│  R11  │  Propósito geral                                 │
│  R12  │  Propósito geral                                 │
│  R13  │  Propósito geral                                 │
│  R14  │  Propósito geral                                 │
│  R15  │  Propósito geral                                 │
├──────────────────────────────────────────────────────────┤
│  RIP  │  Instruction Pointer (Program Counter)           │
│ RFLAGS│  Registrador de flags (ZF, CF, OF, SF, ...)      │
└──────────────────────────────────────────────────────────┘

Sub-registradores (compatibilidade):
64 bits: RAX
32 bits: EAX  (metade inferior de RAX)
16 bits: AX   (metade inferior de EAX)
 8 bits: AH (alto), AL (baixo) de AX

┌────────────────────────────────────────────────────┐
│                        RAX (64 bits)               │
│                ┌───────────────────────────────────┐│
│                │           EAX (32 bits)          ││
│                │          ┌────────────────────────┐│
│                │          │       AX (16 bits)    ││
│                │          │   ┌────────┬──────────┐│
│                │          │   │AH(8bit)│ AL(8bit) ││
│                │          │   └────────┴──────────┘│
└────────────────────────────────────────────────────┘
```

### Registradores ARM (AArch64)

```
31 registradores de propósito geral:
┌──────────────────────────────────────────────────────┐
│  X0-X7    │  Argumentos de função / retorno          │
│  X8       │  Resultado indireto                      │
│  X9-X15   │  Temporários (caller-saved)              │
│  X16-X17  │  Intra-procedure-call (IP0, IP1)         │
│  X18      │  Plataforma específico                   │
│  X19-X28  │  Callee-saved                            │
│  X29 (FP) │  Frame Pointer                           │
│  X30 (LR) │  Link Register (endereço de retorno)     │
├──────────────────────────────────────────────────────┤
│  SP       │  Stack Pointer                           │
│  PC       │  Program Counter                         │
│  XZR      │  Zero Register (sempre vale 0)           │
└──────────────────────────────────────────────────────┘

Versão 32 bits dos registradores:
X0 (64 bits) ↔ W0 (32 bits, metade inferior)
```

---

## Exemplo 7: Categorias de Instruções — Programa Completo

### Programa em estilo assembly que soma os elementos de um vetor:

```assembly
; Programa: Somar 5 elementos de um vetor
; Vetor em memória: V = [10, 20, 30, 40, 50] a partir do endereço 1000
; Resultado armazenado em R5

; ═══ TRANSFERÊNCIA DE DADOS ═══
        MOV   R1, #1000      ; R1 = endereço base do vetor
        MOV   R2, #5         ; R2 = contador (5 elementos)
        MOV   R5, #0         ; R5 = soma = 0

; ═══ TRANSFERÊNCIA (memória → registrador) ═══
loop:   LOAD  R3, [R1]       ; R3 = mem[R1] (carrega elemento)

; ═══ ARITMÉTICA ═══
        ADD   R5, R5, R3     ; R5 = R5 + R3 (acumula soma)
        ADD   R1, R1, #4     ; R1 = R1 + 4 (próximo elemento, 4 bytes)
        SUB   R2, R2, #1     ; R2 = R2 - 1 (decrementa contador)

; ═══ LÓGICA (comparação) ═══
        CMP   R2, #0         ; Compara R2 com 0

; ═══ CONTROLE DE FLUXO ═══
        BNE   loop           ; Se R2 ≠ 0, volta ao loop

; ═══ TRANSFERÊNCIA (registrador → memória) ═══
        STORE [2000], R5     ; Armazena resultado no endereço 2000

; ═══ SISTEMA ═══
        HLT                  ; Para o processador

; Resultado: R5 = 10 + 20 + 30 + 40 + 50 = 150
```

### Classificação de cada instrução usada:

| Instrução | Categoria | Ocorrências |
|-----------|-----------|:-----------:|
| MOV | Transferência de dados | 3 |
| LOAD | Transferência de dados | 5 (no loop) |
| STORE | Transferência de dados | 1 |
| ADD | Aritmética | 10 (no loop) |
| SUB | Aritmética | 5 (no loop) |
| CMP | Lógica/Comparação | 5 (no loop) |
| BNE | Controle de fluxo | 5 (no loop) |
| HLT | Sistema | 1 |

---

## Exemplo 8: Tamanhos de Dados e Representação

### O valor decimal 1000 em diferentes tamanhos:

```
1000 em decimal = 0x3E8 em hexadecimal = 1111101000 em binário

Como byte (8 bits):
  ⚠️ NÃO CABE! 1000 > 255 (máximo de 8 bits sem sinal)
  Overflow!

Como halfword (16 bits):
  0000 0011 1110 1000
  = 0x03E8
  ✅ Cabe (0-65535)

Como word (32 bits):
  0000 0000 0000 0000 0000 0011 1110 1000
  = 0x000003E8
  ✅ Cabe

Como doubleword (64 bits):
  0000 0000 0000 0000 0000 0000 0000 0000
  0000 0000 0000 0000 0000 0011 1110 1000
  = 0x00000000000003E8
  ✅ Cabe (muito espaço desperdiçado)
```

### O valor -50 em complemento de 2:

```
|-50| = 50 = 0011 0010

Complemento de 1: 1100 1101
Complemento de 2: 1100 1110 = 0xCE

Verificação: 1100 1110 = -128 + 64 + 0 + 0 + 8 + 4 + 2 + 0
           = -128 + 78 = -50 ✅

Em 8 bits:   CE
Em 16 bits:  FFCE
Em 32 bits:  FFFFFFCE
(extensão de sinal: preenche com 1s à esquerda pois é negativo)
```
