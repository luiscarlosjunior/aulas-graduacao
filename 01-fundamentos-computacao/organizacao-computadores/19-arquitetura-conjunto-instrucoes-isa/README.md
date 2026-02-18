# 🏗️ Aula 19 - Arquitetura do Conjunto de Instruções (ISA)

## 📋 Objetivos de Aprendizagem

Ao final desta aula, você será capaz de:

- ✅ Definir o que é ISA (Instruction Set Architecture) e sua importância
- ✅ Compreender a ISA como interface entre hardware e software
- ✅ Classificar instruções pelo número de endereços (0, 1, 2, 3)
- ✅ Analisar o formato de instruções (opcode + operandos)
- ✅ Identificar os tipos de operandos e tamanhos de dados
- ✅ Categorizar instruções (transferência, aritmética, lógica, controle, E/S)
- ✅ Explicar endianness (big-endian vs little-endian)
- ✅ Comparar arquiteturas baseadas em pilha e baseadas em registradores
- ✅ Reconhecer conceitos de ISAs reais (x86, ARM, MIPS)

---

## 📚 Conteúdo

### 1. O que é ISA (Instruction Set Architecture)?

#### 1.1 Definição

A **ISA (Instruction Set Architecture)** — ou **Arquitetura do Conjunto de Instruções** — é a **interface abstrata** entre o hardware e o software de um computador. Ela define:

- Quais **instruções** o processador pode executar
- Quais **registradores** estão disponíveis
- Quais **tipos de dados** são suportados
- Como a **memória** é endereçada
- Como as **instruções são codificadas** em binário

> 💡 **A ISA é o "contrato" entre quem projeta o hardware e quem escreve o software.** O programador (ou compilador) precisa conhecer a ISA para gerar código que o processador entenda.

#### 1.2 ISA como Interface Hardware/Software

```
┌──────────────────────────────────────────────────────────┐
│                    SOFTWARE                               │
│                                                          │
│  ┌──────────────────────────────────────────────────┐    │
│  │            Aplicações (Java, Python, C)           │    │
│  └──────────────────────┬───────────────────────────┘    │
│                         │                                │
│  ┌──────────────────────▼───────────────────────────┐    │
│  │           Sistema Operacional                     │    │
│  └──────────────────────┬───────────────────────────┘    │
│                         │                                │
│  ┌──────────────────────▼───────────────────────────┐    │
│  │        Compilador / Montador (Assembler)          │    │
│  └──────────────────────┬───────────────────────────┘    │
│                         │                                │
├═════════════════════════▼════════════════════════════════╡
│                                                          │
│   ████████████████████████████████████████████████████    │
│   █                    ISA                           █    │
│   █  (Arquitetura do Conjunto de Instruções)         █    │
│   █                                                  █    │
│   █  • Instruções      • Registradores              █    │
│   █  • Modos de endereç. • Tipos de dados           █    │
│   █  • Formato binário  • Interrupções              █    │
│   ████████████████████████████████████████████████████    │
│                                                          │
├═════════════════════════▼════════════════════════════════╡
│                    HARDWARE                               │
│                                                          │
│  ┌──────────────────────▼───────────────────────────┐    │
│  │           Microarquitetura                        │    │
│  │  (Pipeline, caches, unidades funcionais)          │    │
│  └──────────────────────┬───────────────────────────┘    │
│                         │                                │
│  ┌──────────────────────▼───────────────────────────┐    │
│  │              Circuitos Digitais                    │    │
│  │  (Portas lógicas, transistores)                   │    │
│  └──────────────────────────────────────────────────┘    │
└──────────────────────────────────────────────────────────┘
```

#### 1.3 Por que a ISA é Importante?

| Aspecto | Por que importa |
|---------|----------------|
| **Compatibilidade** | Programas compilados para uma ISA funcionam em qualquer processador que implemente essa ISA |
| **Portabilidade** | Software x86 roda em Intel e AMD (mesma ISA) |
| **Evolução** | Hardware pode mudar internamente sem quebrar compatibilidade com software |
| **Projeto** | Define o que o compilador pode usar e o que o hardware deve implementar |
| **Desempenho** | Uma ISA bem projetada facilita implementações eficientes |

**Exemplo de compatibilidade:**
```
Programa compilado para ISA x86-64:
  → Roda no Intel Core i7
  → Roda no Intel Core i9
  → Roda no AMD Ryzen 5
  → Roda no AMD Ryzen 9
  → NÃO roda em ARM (ISA diferente!)
```

---

### 2. Características das Instruções: Número de Endereços

As instruções podem ser classificadas pelo **número de endereços** (operandos) que especificam:

#### 2.1 Instrução de 3 Endereços

Especifica **dois operandos fonte** e **um destino**.

```
Formato:    OPERAÇÃO    destino, fonte1, fonte2

Exemplo:    ADD  R1, R2, R3     ; R1 ← R2 + R3

Significado: "Some R2 com R3 e coloque o resultado em R1"
```

**Vantagens:** Preserva os operandos fonte; mais flexível
**Desvantagens:** Instruções maiores (3 campos de endereço)
**Exemplos:** ARM, MIPS, RISC-V

#### 2.2 Instrução de 2 Endereços

Especifica **dois operandos**, onde um deles é **fonte e destino**.

```
Formato:    OPERAÇÃO    destino/fonte1, fonte2

Exemplo:    ADD  R1, R2         ; R1 ← R1 + R2

Significado: "Some R1 com R2 e coloque o resultado em R1"
             (R1 original é sobrescrito!)
```

**Vantagens:** Instruções menores
**Desvantagens:** Destrói um dos operandos fonte
**Exemplos:** x86 (maioria das instruções)

#### 2.3 Instrução de 1 Endereço

Usa um **acumulador** implícito como um dos operandos.

```
Formato:    OPERAÇÃO    operando

Exemplo:    ADD  X       ; ACC ← ACC + mem[X]

Significado: "Some o conteúdo da posição de memória X
              com o acumulador e armazene no acumulador"
```

**Vantagens:** Instruções muito curtas
**Desvantagens:** Acumulador é gargalo; muitas instruções necessárias
**Exemplos:** Processadores antigos (Intel 8080, 6502)

#### 2.4 Instrução de 0 Endereços

Todos os operandos estão na **pilha**. Operações usam o topo da pilha.

```
Formato:    OPERAÇÃO    (sem operandos explícitos)

Exemplo:    ADD         ; TOS ← TOS + TOS-1
                        ; (soma os dois valores do topo da pilha)

Sequência para calcular A + B:
  PUSH A    ; Empilha A
  PUSH B    ; Empilha B
  ADD       ; Desempilha B e A, soma, empilha resultado
```

**Vantagens:** Instruções muito curtas; avaliação de expressões natural
**Desvantagens:** Muitas instruções; overhead de manipulação de pilha
**Exemplos:** JVM (Java Virtual Machine), calculadoras HP (RPN)

#### 2.5 Comparação: Calculando X = (A + B) × (C - D)

| Tipo | Código | Nº Instruções |
|------|--------|:-------------:|
| **3 endereços** | `ADD R1, A, B` | |
| | `SUB R2, C, D` | |
| | `MUL X, R1, R2` | **3** |
| **2 endereços** | `MOV R1, A` | |
| | `ADD R1, B` | |
| | `MOV R2, C` | |
| | `SUB R2, D` | |
| | `MUL R1, R2` | |
| | `MOV X, R1` | **6** |
| **1 endereço** | `LOAD A` | |
| | `ADD B` | |
| | `STORE T1` | |
| | `LOAD C` | |
| | `SUB D` | |
| | `MUL T1` | |
| | `STORE X` | **7** |
| **0 endereços** | `PUSH A` | |
| | `PUSH B` | |
| | `ADD` | |
| | `PUSH C` | |
| | `PUSH D` | |
| | `SUB` | |
| | `MUL` | |
| | `POP X` | **8** |

> 📝 **Observe:** Menos endereços por instrução = instruções menores, mas mais instruções necessárias!

---

### 3. Formato de Instruções

#### 3.1 Estrutura Geral

Toda instrução em linguagem de máquina é composta por campos binários:

```
┌──────────────┬──────────────┬──────────────┬──────────────┐
│    OPCODE    │  Operando 1  │  Operando 2  │  Operando 3  │
│ (código da   │  (fonte/     │  (fonte)     │  (destino)   │
│  operação)   │   destino)   │              │              │
└──────────────┴──────────────┴──────────────┴──────────────┘
```

**Opcode (Operation Code):** Identifica a operação a ser realizada (ADD, SUB, LOAD, etc.)

**Operandos:** Indicam os dados ou endereços envolvidos na operação

#### 3.2 Exemplo: Formato MIPS (32 bits, 3 tipos)

**Tipo R (Registrador):**
```
┌────────┬───────┬───────┬───────┬───────┬────────┐
│ opcode │  rs   │  rt   │  rd   │ shamt │ funct  │
│ 6 bits │5 bits │5 bits │5 bits │5 bits │ 6 bits │
└────────┴───────┴───────┴───────┴───────┴────────┘
= 32 bits

Exemplo: ADD $t0, $s1, $s2
opcode = 000000 (tipo R)
rs = 10001 ($s1 = reg 17)
rt = 10010 ($s2 = reg 18)
rd = 01000 ($t0 = reg 8)
shamt = 00000
funct = 100000 (ADD)

Binário: 000000 10001 10010 01000 00000 100000
Hexadec: 0x02324020
```

**Tipo I (Imediato):**
```
┌────────┬───────┬───────┬──────────────────────┐
│ opcode │  rs   │  rt   │     imediato         │
│ 6 bits │5 bits │5 bits │     16 bits          │
└────────┴───────┴───────┴──────────────────────┘
= 32 bits

Exemplo: ADDI $t0, $s1, 100
opcode = 001000 (ADDI)
rs = 10001 ($s1)
rt = 01000 ($t0)
imediato = 0000000001100100 (100)
```

**Tipo J (Jump):**
```
┌────────┬──────────────────────────────────────┐
│ opcode │              endereço                 │
│ 6 bits │              26 bits                  │
└────────┴──────────────────────────────────────┘
= 32 bits

Exemplo: J 1000
opcode = 000010 (J)
endereço = 00000000000000001111101000 (1000)
```

#### 3.3 Compromissos no Projeto do Formato

```
┌─────────────────────────────────────────────────────────┐
│         COMPROMISSOS NO DESIGN DA ISA                    │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  Mais bits no opcode     ↔  Menos bits nos operandos    │
│  (mais instruções)           (menos registradores/      │
│                               menor alcance de endereço) │
│                                                         │
│  Instruções maiores      ↔  Instruções menores          │
│  (mais flexibilidade)        (menos memória usada,      │
│                               mais cache hits)           │
│                                                         │
│  Tamanho fixo            ↔  Tamanho variável            │
│  (fácil decodificar,        (mais compacto, porém       │
│   bom para pipeline)         difícil decodificar)        │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

---

### 4. Tipos de Operandos

Os operandos das instruções podem representar diferentes tipos de dados:

#### 4.1 Tipos Numéricos

| Tipo | Descrição | Tamanho Típico | Exemplos de Valores |
|------|-----------|:--------------:|---------------------|
| **Inteiro sem sinal** | Números naturais (≥ 0) | 8, 16, 32, 64 bits | 0, 1, 255, 65535 |
| **Inteiro com sinal** | Complemento de 2 | 8, 16, 32, 64 bits | -128 a +127 (8 bits) |
| **Ponto flutuante simples** | IEEE 754 single | 32 bits | 3,14; -0,001; 1,23×10³⁸ |
| **Ponto flutuante duplo** | IEEE 754 double | 64 bits | Precisão de ~15 dígitos |
| **BCD** | Binary-Coded Decimal | 4 bits/dígito | Cálculos financeiros |

#### 4.2 Tipos Não Numéricos

| Tipo | Descrição | Codificação | Exemplo |
|------|-----------|-------------|---------|
| **Caractere** | Letra, dígito, símbolo | ASCII (7 bits), UTF-8 | 'A' = 0x41 |
| **Lógico (booleano)** | Verdadeiro/Falso | 1 bit (ou byte) | 1 = true, 0 = false |
| **Endereço** | Ponteiro para memória | 32 ou 64 bits | 0x7FFF0000 |
| **String** | Sequência de caracteres | Vetor de chars | "Hello" |

---

### 5. Tamanhos de Dados

#### 5.1 Nomenclatura Padrão

| Nome | Tamanho | Bits | Faixa (sem sinal) | Faixa (com sinal) |
|------|:-------:|:----:|:------------------:|:------------------:|
| **Bit** | 1 bit | 1 | 0-1 | — |
| **Nibble** | ½ byte | 4 | 0-15 | -8 a +7 |
| **Byte** | 1 byte | 8 | 0-255 | -128 a +127 |
| **Halfword** | 2 bytes | 16 | 0-65.535 | -32.768 a +32.767 |
| **Word** | 4 bytes | 32 | 0 ~ 4,3 × 10⁹ | ~ ±2,1 × 10⁹ |
| **Doubleword** | 8 bytes | 64 | 0 ~ 1,8 × 10¹⁹ | ~ ±9,2 × 10¹⁸ |
| **Quadword** | 16 bytes | 128 | 0 ~ 3,4 × 10³⁸ | ~ ±1,7 × 10³⁸ |

> ⚠️ **Atenção:** O tamanho de "word" varia entre arquiteturas! Em x86-64 uma "word" é 16 bits, enquanto em MIPS é 32 bits.

#### 5.2 Tamanhos em Diferentes Arquiteturas

| Arquitetura | Byte | Halfword | Word | Doubleword |
|------------|:----:|:--------:|:----:|:----------:|
| **MIPS** | 8 bits | 16 bits | 32 bits | 64 bits |
| **ARM (32)** | 8 bits | 16 bits | 32 bits | 64 bits |
| **ARM (64)** | 8 bits | 16 bits | 32 bits | 64 bits |
| **x86** | 8 bits | — | 16 bits | 32 bits |
| **x86-64** | 8 bits | — | 16 bits (word) | 32 bits (dword) |

---

### 6. Categorias de Instruções

As instruções de um processador podem ser agrupadas em categorias funcionais:

#### 6.1 Visão Geral das Categorias

```
┌─────────────────────────────────────────────────────────┐
│           CATEGORIAS DE INSTRUÇÕES                       │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  📦 TRANSFERÊNCIA DE DADOS                              │
│     MOV, LOAD, STORE, PUSH, POP, IN, OUT               │
│                                                         │
│  ➕ ARITMÉTICAS                                          │
│     ADD, SUB, MUL, DIV, INC, DEC, NEG                  │
│                                                         │
│  🔣 LÓGICAS e BIT                                       │
│     AND, OR, NOT, XOR, SHL, SHR, ROL, ROR              │
│                                                         │
│  🔀 CONTROLE DE FLUXO                                   │
│     JMP, JZ, JNZ, JE, CALL, RET, LOOP                  │
│                                                         │
│  📥 ENTRADA/SAÍDA                                       │
│     IN, OUT (portas de I/O)                             │
│                                                         │
│  ⚙️ CONTROLE DO SISTEMA                                 │
│     NOP, HLT, INT, IRET, CLI, STI                      │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

#### 6.2 Transferência de Dados

| Instrução | Operação | Exemplo |
|-----------|----------|---------|
| **MOV** | Copia dado de fonte para destino | `MOV R1, R2` (R1 ← R2) |
| **LOAD** | Carrega dado da memória para registrador | `LOAD R1, [addr]` |
| **STORE** | Armazena dado do registrador na memória | `STORE [addr], R1` |
| **PUSH** | Empilha valor no topo da pilha | `PUSH R1` |
| **POP** | Desempilha valor do topo da pilha | `POP R1` |
| **XCHG** | Troca valores entre dois registradores | `XCHG R1, R2` |
| **LEA** | Carrega endereço efetivo | `LEA R1, [addr]` |

#### 6.3 Aritméticas

| Instrução | Operação | Exemplo |
|-----------|----------|---------|
| **ADD** | Soma | `ADD R1, R2, R3` (R1 = R2 + R3) |
| **SUB** | Subtração | `SUB R1, R2, R3` (R1 = R2 - R3) |
| **MUL** | Multiplicação | `MUL R1, R2, R3` (R1 = R2 × R3) |
| **DIV** | Divisão | `DIV R1, R2, R3` (R1 = R2 / R3) |
| **INC** | Incremento | `INC R1` (R1 = R1 + 1) |
| **DEC** | Decremento | `DEC R1` (R1 = R1 - 1) |
| **NEG** | Negação (complemento de 2) | `NEG R1` (R1 = -R1) |
| **CMP** | Compara (subtração sem armazenar) | `CMP R1, R2` (flags ← R1 - R2) |

#### 6.4 Lógicas e Manipulação de Bits

| Instrução | Operação | Exemplo |
|-----------|----------|---------|
| **AND** | E lógico bit a bit | `AND R1, R2, R3` |
| **OR** | OU lógico bit a bit | `OR R1, R2, R3` |
| **NOT** | Inversão de bits | `NOT R1` |
| **XOR** | OU exclusivo | `XOR R1, R2, R3` |
| **SHL/SLL** | Shift para esquerda (×2) | `SHL R1, 2` |
| **SHR/SRL** | Shift para direita (÷2) | `SHR R1, 1` |
| **ROL** | Rotação para esquerda | `ROL R1, 3` |
| **ROR** | Rotação para direita | `ROR R1, 1` |
| **TEST** | AND sem armazenar (só flags) | `TEST R1, R2` |

#### 6.5 Controle de Fluxo

| Instrução | Operação | Exemplo |
|-----------|----------|---------|
| **JMP** | Desvio incondicional | `JMP label` |
| **JZ/JE** | Desvia se zero/igual | `JZ label` |
| **JNZ/JNE** | Desvia se não zero/não igual | `JNZ label` |
| **JG/JGT** | Desvia se maior | `JG label` |
| **JL/JLT** | Desvia se menor | `JL label` |
| **CALL** | Chamada de sub-rotina | `CALL func` |
| **RET** | Retorno de sub-rotina | `RET` |
| **LOOP** | Decrementa CX e desvia se ≠ 0 | `LOOP label` |

#### 6.6 Entrada/Saída e Sistema

| Instrução | Operação | Exemplo |
|-----------|----------|---------|
| **IN** | Lê dado de porta de I/O | `IN AL, 60h` |
| **OUT** | Escreve dado em porta de I/O | `OUT 60h, AL` |
| **NOP** | Nenhuma operação | `NOP` |
| **HLT** | Para o processador | `HLT` |
| **INT** | Interrupção de software | `INT 21h` |
| **IRET** | Retorno de interrupção | `IRET` |

---

### 7. Endianness (Ordenação de Bytes)

#### 7.1 O que é Endianness?

**Endianness** define a **ordem em que os bytes** de um dado multi-byte são armazenados na memória.

Considere o valor hexadecimal **0x12345678** (4 bytes) armazenado a partir do endereço 100:

#### 7.2 Big-Endian vs Little-Endian

**Big-Endian:** O byte mais significativo (MSB) é armazenado no endereço **menor**.

```
Endereço:  100    101    102    103
Conteúdo: [0x12] [0x34] [0x56] [0x78]
           MSB                   LSB
           ←── Mais significativo primeiro
```

**Little-Endian:** O byte menos significativo (LSB) é armazenado no endereço **menor**.

```
Endereço:  100    101    102    103
Conteúdo: [0x78] [0x56] [0x34] [0x12]
           LSB                   MSB
           ←── Menos significativo primeiro
```

#### 7.3 Comparação Visual

```
Valor: 0x12345678

Big-Endian (como lemos):
┌──────┬──────┬──────┬──────┐
│ 0x12 │ 0x34 │ 0x56 │ 0x78 │    "Começa pelo grande"
└──────┴──────┴──────┴──────┘
 End 100 End 101 End 102 End 103

Little-Endian (invertido):
┌──────┬──────┬──────┬──────┐
│ 0x78 │ 0x56 │ 0x34 │ 0x12 │    "Começa pelo pequeno"
└──────┴──────┴──────┴──────┘
 End 100 End 101 End 102 End 103
```

#### 7.4 Quem Usa o Quê?

| Endianness | Arquiteturas | Observação |
|-----------|-------------|------------|
| **Big-Endian** | SPARC, MIPS (padrão), Motorola 68000, IBM z/Series | Ordem natural de leitura |
| **Little-Endian** | x86, x86-64, ARM (padrão), RISC-V | Facilita operações com dados menores |
| **Bi-Endian** | ARM, MIPS, PowerPC, RISC-V | Pode ser configurado para ambos |

> 💡 **Protocolos de rede** (TCP/IP) usam **big-endian** (chamado de "network byte order"). Por isso, computadores little-endian precisam converter ao enviar/receber dados pela rede.

---

### 8. Arquiteturas Baseadas em Pilha vs Registradores

#### 8.1 Arquitetura Baseada em Pilha (Stack-Based)

Os operandos estão implicitamente no **topo da pilha**.

```
Exemplo: Calcular (3 + 5) × 2

PUSH 3      Pilha: [3]
PUSH 5      Pilha: [3, 5]
ADD         Pilha: [8]         (desempilha 3 e 5, empilha 3+5=8)
PUSH 2      Pilha: [8, 2]
MUL         Pilha: [16]        (desempilha 8 e 2, empilha 8×2=16)
POP  R      R = 16             (resultado em R)
```

```
Estado da pilha a cada passo:

PUSH 3:    PUSH 5:    ADD:       PUSH 2:    MUL:
┌───┐      ┌───┐      ┌───┐     ┌───┐      ┌────┐
│   │      │ 5 │←TOS  │   │     │ 2 │←TOS  │    │
├───┤      ├───┤      ├───┤     ├───┤      ├────┤
│ 3 │←TOS  │ 3 │      │ 8 │←TOS│ 8 │      │ 16 │←TOS
└───┘      └───┘      └───┘     └───┘      └────┘
```

**Exemplos de uso:** JVM (Java Virtual Machine), .NET CLR, calculadoras HP

#### 8.2 Arquitetura Baseada em Registradores (Register-Based)

Os operandos são especificados como **registradores** (ou memória).

```
Exemplo: Calcular (3 + 5) × 2

MOVI R1, 3       ; R1 = 3
MOVI R2, 5       ; R2 = 5
ADD  R3, R1, R2  ; R3 = R1 + R2 = 8
MOVI R4, 2       ; R4 = 2
MUL  R5, R3, R4  ; R5 = R3 × R4 = 16
; Resultado em R5 = 16
```

**Exemplos de uso:** x86, ARM, MIPS, RISC-V (a maioria dos processadores modernos)

#### 8.3 Comparação

| Aspecto | Baseada em Pilha | Baseada em Registradores |
|---------|:----------------:|:------------------------:|
| Operandos | Implícitos (pilha) | Explícitos (registradores) |
| Tamanho da instrução | Menor (sem operandos) | Maior (com operandos) |
| Número de instruções | Mais | Menos |
| Acesso a dados | Topo da pilha apenas | Qualquer registrador |
| Hardware | Mais simples | Mais complexo |
| Desempenho | Menor (muitos acessos à pilha) | Maior (registradores são rápidos) |
| Pipeline | Mais difícil | Mais fácil |
| Uso moderno | Máquinas virtuais (JVM) | Processadores físicos |

---

### 9. Exemplos de ISAs Reais

#### 9.1 ISA x86 / x86-64

```
┌──────────────────────────────────────────────────────┐
│                 ISA x86 / x86-64                      │
├──────────────────────────────────────────────────────┤
│ Tipo: CISC                                           │
│ Registradores (x86-64): 16 de 64 bits               │
│   RAX, RBX, RCX, RDX, RSI, RDI, RBP, RSP           │
│   R8-R15                                             │
│ Tamanho da instrução: 1 a 15 bytes (variável)       │
│ Endianness: Little-endian                            │
│ Endereçamento: Muitos modos                          │
│ Operandos: Principalmente 2 endereços                │
│                                                      │
│ Exemplo de código:                                   │
│   mov  eax, [ebx+4]    ; Carrega da memória         │
│   add  eax, ecx        ; Soma registradores          │
│   push eax              ; Empilha                    │
│   call funcao           ; Chama sub-rotina           │
└──────────────────────────────────────────────────────┘
```

#### 9.2 ISA ARM (AArch64)

```
┌──────────────────────────────────────────────────────┐
│                 ISA ARM (AArch64)                     │
├──────────────────────────────────────────────────────┤
│ Tipo: RISC                                           │
│ Registradores: 31 de 64 bits (X0-X30) + SP + PC     │
│ Tamanho da instrução: 32 bits (fixo)                 │
│ Endianness: Bi-endian (geralmente little-endian)     │
│ Endereçamento: Load/Store                            │
│ Operandos: 3 endereços                               │
│                                                      │
│ Exemplo de código:                                   │
│   LDR  X1, [X2, #8]    ; Carrega da memória         │
│   ADD  X3, X1, X4      ; Soma registradores          │
│   STR  X3, [X5]        ; Armazena na memória         │
│   BL   funcao          ; Chama sub-rotina            │
└──────────────────────────────────────────────────────┘
```

#### 9.3 ISA MIPS

```
┌──────────────────────────────────────────────────────┐
│                    ISA MIPS                           │
├──────────────────────────────────────────────────────┤
│ Tipo: RISC                                           │
│ Registradores: 32 de 32 bits ($0-$31)                │
│   $zero=0, $at, $v0-$v1, $a0-$a3, $t0-$t9,         │
│   $s0-$s7, $k0-$k1, $gp, $sp, $fp, $ra             │
│ Tamanho da instrução: 32 bits (fixo)                 │
│ Endianness: Bi-endian                                │
│ Endereçamento: Load/Store                            │
│ Operandos: 3 endereços                               │
│ Formatos: R, I, J                                    │
│                                                      │
│ Exemplo de código:                                   │
│   lw   $t0, 0($s1)     ; Carrega da memória         │
│   add  $t2, $t0, $t1   ; Soma registradores          │
│   sw   $t2, 0($s2)     ; Armazena na memória         │
│   jal  funcao           ; Chama sub-rotina           │
└──────────────────────────────────────────────────────┘
```

#### 9.4 Tabela Comparativa das ISAs

| Aspecto | x86-64 | ARM (AArch64) | MIPS |
|---------|:------:|:-------------:|:----:|
| Filosofia | CISC | RISC | RISC |
| Registradores GP | 16 | 31 | 32 |
| Tamanho registrador | 64 bits | 64 bits | 32/64 bits |
| Tamanho instrução | 1-15 bytes | 4 bytes | 4 bytes |
| Endianness | Little | Bi (padrão Little) | Bi |
| Nº endereços | 2 | 3 | 3 |
| Acesso à memória | Muitos modos | Load/Store | Load/Store |
| Instruções | ~1500+ | ~400 | ~200 |
| Mercado principal | PCs, servidores | Smartphones, embarcados | Acadêmico, roteadores |

---

### 10. Por que o Projeto da ISA Importa?

#### 10.1 Impacto na Evolução Tecnológica

```
┌─────────────────────────────────────────────────────────┐
│       IMPACTO DO DESIGN DA ISA                           │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  📦 Compatibilidade retroativa                          │
│     x86 mantém compatibilidade desde 1978!             │
│     Programas de 16 bits ainda rodam em CPUs modernas  │
│                                                         │
│  ⚡ Eficiência de implementação                         │
│     ISA RISC (ARM) = hardware simples, baixa energia   │
│     ISA CISC (x86) = hardware complexo, alta energia   │
│                                                         │
│  🔧 Facilidade de compilação                            │
│     Mais registradores = menos acessos à memória       │
│     Instruções ortogonais = compilador mais simples    │
│                                                         │
│  📏 Densidade de código                                 │
│     Instruções variáveis (x86) = programas menores     │
│     Instruções fixas (ARM) = programas maiores         │
│     (Thumb-2 do ARM mitiga isso)                       │
│                                                         │
│  🚀 Extensibilidade                                     │
│     RISC-V: ISA modular, adicione extensões conforme   │
│     necessário (M, A, F, D, V, ...)                    │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

---

### 11. Resumo da Aula

```
┌─────────────────────────────────────────────────────────┐
│                    RESUMO - AULA 19                      │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  🔹 ISA é a interface entre hardware e software         │
│                                                         │
│  🔹 Instruções variam por nº de endereços:              │
│     0 (pilha), 1 (acumulador), 2 e 3 endereços        │
│                                                         │
│  🔹 Formato: opcode + operandos                         │
│     Fixo (RISC) vs variável (CISC)                     │
│                                                         │
│  🔹 Tipos de dados: inteiros, ponto flutuante,          │
│     caracteres, lógicos, endereços                     │
│                                                         │
│  🔹 Categorias de instruções: transferência,            │
│     aritmética, lógica, controle, E/S                  │
│                                                         │
│  🔹 Endianness: big-endian (MSB primeiro) vs            │
│     little-endian (LSB primeiro)                       │
│                                                         │
│  🔹 Pilha vs registradores: processadores modernos      │
│     usam registradores; VMs usam pilha                 │
│                                                         │
│  🔹 ISAs reais: x86 (CISC, PCs), ARM (RISC, móveis),   │
│     MIPS (RISC, acadêmico)                             │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

---

## 📖 Referências

1. STALLINGS, W. **Arquitetura e Organização de Computadores**. 10ª ed. Pearson, 2017.
2. PATTERSON, D. A.; HENNESSY, J. L. **Organização e Projeto de Computadores: A Interface Hardware/Software**. 5ª ed. Elsevier, 2017.
3. TANENBAUM, A. S. **Organização Estruturada de Computadores**. 6ª ed. Pearson, 2013.
4. Intel Corporation. **Intel® 64 and IA-32 Architectures Software Developer's Manual**.
5. ARM Ltd. **ARM Architecture Reference Manual (ARMv8)**.
6. MIPS Technologies. **MIPS32 Architecture for Programmers**.

---

> 💡 **Próxima aula:** Tipos de Operações, Linguagem Assembly e Modos de Endereçamento — vamos mergulhar no Assembly e entender como o processador acessa os dados!
