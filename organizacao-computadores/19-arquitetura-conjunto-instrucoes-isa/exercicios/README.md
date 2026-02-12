# 📝 Exercícios - Aula 19: Arquitetura do Conjunto de Instruções (ISA)

## Exercício 1 — Conceito de ISA

a) Defina ISA (Instruction Set Architecture) em suas próprias palavras.
b) Explique por que a ISA é considerada a "interface" entre hardware e software.
c) Dê um exemplo prático de como a ISA garante compatibilidade de software.
d) Qual a diferença entre ISA e microarquitetura?
e) Por que é possível que processadores diferentes (ex: Intel e AMD) executem os mesmos programas?

---

## Exercício 2 — Número de Endereços

Converta a expressão abaixo para código assembly usando cada tipo de instrução (0, 1, 2 e 3 endereços):

```
Z = (A + B) × (C - D)
```

a) **3 endereços** (estilo MIPS): use R1, R2, R3...
b) **2 endereços** (estilo x86): use R1, R2...
c) **1 endereço** (acumulador): use LOAD, STORE, ADD, SUB, MUL
d) **0 endereços** (pilha): use PUSH, POP, ADD, SUB, MUL
e) Compare: qual tipo usa menos instruções? Qual produz instruções menores?

---

## Exercício 3 — Expressões Complexas

Escreva o código para avaliar a expressão abaixo usando **3 endereços** e **0 endereços** (pilha):

```
W = (A × B + C) / (D - E × F)
```

a) Versão com 3 endereços.
b) Versão com 0 endereços (pilha). Mostre o estado da pilha a cada passo.
c) Quantas instruções cada versão utiliza?
d) Se cada instrução de 3 endereços ocupa 4 bytes e cada instrução de pilha ocupa 1 byte (sem operando) ou 2 bytes (com operando), qual versão ocupa menos espaço em memória?

---

## Exercício 4 — Formato de Instrução MIPS

Codifique as seguintes instruções MIPS em binário e hexadecimal:

**Tabela de referência:**

| Registrador | Número | | Operação | Tipo | Opcode | Funct |
|------------|:------:|-|----------|:----:|:------:|:-----:|
| $zero | 0 | | ADD | R | 000000 | 100000 |
| $t0 | 8 | | SUB | R | 000000 | 100010 |
| $t1 | 9 | | AND | R | 000000 | 100100 |
| $t2 | 10 | | OR | R | 000000 | 100101 |
| $s0 | 16 | | SLT | R | 000000 | 101010 |
| $s1 | 17 | | ADDI | I | 001000 | — |
| $s2 | 18 | | LW | I | 100011 | — |
| $s3 | 19 | | SW | I | 101011 | — |

a) `ADD $t2, $s0, $s1`
b) `SUB $t0, $t1, $t2`
c) `ADDI $s1, $s0, 50`
d) `LW $t0, 100($s2)`
e) `SW $t1, -4($s3)`

---

## Exercício 5 — Decodificação de Instruções

Decodifique as seguintes instruções MIPS (dadas em hexadecimal) usando as tabelas do exercício anterior:

a) `0x02328020` — Identifique: tipo, opcode, registradores, operação
b) `0x8E280010` — Identifique: tipo, opcode, registradores, offset
c) `0x22300064` — Identifique: tipo, opcode, registradores, valor imediato
d) `0xAE690008` — Identifique todos os campos

---

## Exercício 6 — Endianness

O valor hexadecimal **0xDEADBEEF** deve ser armazenado na memória a partir do endereço **0x400**.

a) Mostre como os bytes ficam dispostos na memória em **big-endian**.
b) Mostre como os bytes ficam dispostos na memória em **little-endian**.
c) Se um programa lê apenas o byte no endereço 0x400, qual valor obtém em cada formato?
d) Se um programa lê uma halfword (2 bytes) a partir do endereço 0x400, qual valor obtém em cada formato?
e) Explique por que protocolos de rede usam big-endian (network byte order).
f) Um arquivo criado em um computador big-endian é transferido para um computador little-endian. O que acontece se o programa simplesmente lê os dados sem converter? Dê um exemplo numérico.

---

## Exercício 7 — Tipos de Operandos e Tamanhos

a) Qual o maior valor inteiro **sem sinal** representável em:
   - 8 bits?
   - 16 bits?
   - 32 bits?

b) Qual a faixa de valores inteiros **com sinal** (complemento de 2) para:
   - 8 bits?
   - 16 bits?
   - 32 bits?

c) Represente o valor decimal **-100** em complemento de 2 usando:
   - 8 bits
   - 16 bits
   - 32 bits

d) Explique a diferença entre word, doubleword e quadword. Dê o tamanho em bits de cada um.

e) Por que diferentes arquiteturas definem "word" com tamanhos diferentes?

---

## Exercício 8 — Categorias de Instruções

Classifique cada instrução abaixo em sua categoria (Transferência, Aritmética, Lógica, Controle de Fluxo, E/S ou Sistema):

| Instrução | Categoria |
|-----------|:---------:|
| a) `MOV R1, R2` | |
| b) `ADD R3, R4, R5` | |
| c) `JMP label` | |
| d) `AND R1, R2, R3` | |
| e) `PUSH R5` | |
| f) `BEQ R1, R2, label` | |
| g) `SHL R1, #2` | |
| h) `CALL funcao` | |
| i) `NOP` | |
| j) `DIV R6, R7, R8` | |
| k) `IN AL, 0x60` | |
| l) `RET` | |
| m) `CMP R1, R2` | |
| n) `STORE [addr], R1` | |
| o) `INT 0x21` | |

---

## Exercício 9 — Pilha vs Registradores

Considere a expressão: `R = (X + Y) × (X - Y)`

a) Escreva o código para uma **máquina de pilha** (0 endereços). Mostre o estado da pilha após cada instrução.
b) Escreva o código para uma **máquina de registradores** (3 endereços).
c) Compare o número total de instruções.
d) Em qual abordagem é mais fácil implementar o pipeline? Por quê?
e) Qual abordagem é mais comum em processadores modernos? Por quê?

---

## Exercício 10 — Comparação de ISAs

Complete a tabela comparativa:

| Característica | x86-64 | ARM (AArch64) | MIPS |
|---------------|:------:|:-------------:|:----:|
| a) Tipo (RISC/CISC) | | | |
| b) Nº registradores GP | | | |
| c) Tamanho do registrador | | | |
| d) Tamanho da instrução | | | |
| e) Nº de endereços | | | |
| f) Endianness | | | |
| g) Acesso à memória | | | |
| h) Mercado principal | | | |

---

## Exercício 11 — Projeto de ISA

Você foi contratado para projetar a ISA de um processador simples com as seguintes restrições:

- Instrução de 16 bits (tamanho fixo)
- 8 registradores de propósito geral
- 16 instruções diferentes

a) Quantos bits são necessários para o campo opcode?
b) Quantos bits são necessários para identificar cada registrador?
c) Projete o formato de instrução Tipo R (3 registradores). Quantos bits sobram?
d) Projete o formato de instrução Tipo I (1 registrador + imediato). Qual o maior valor imediato?
e) Desenhe o layout de bits de cada formato.
f) Quais são as limitações desta ISA comparada ao MIPS?

---

## Exercício 12 — Análise Crítica

Responda de forma dissertativa (3-5 frases cada):

a) Por que a ISA x86 mantém compatibilidade com programas escritos para o 8086 (1978)? Quais são as vantagens e desvantagens dessa decisão?

b) O RISC-V é uma ISA aberta (open-source). Quais são as implicações disso para a indústria de processadores e para a educação?

c) Se você fosse projetar uma ISA do zero hoje para um smartphone, escolheria tamanho de instrução fixo ou variável? Justifique.

d) Explique por que a JVM (Java Virtual Machine) usa uma arquitetura baseada em pilha, enquanto processadores físicos usam registradores.

---

## 🎯 Gabarito Resumido

<details>
<summary>Clique para ver as respostas</summary>

### Exercício 2
a) 3 endereços: ADD R1,A,B / SUB R2,C,D / MUL Z,R1,R2 → **3 instruções**
b) 2 endereços: MOV R1,A / ADD R1,B / MOV R2,C / SUB R2,D / MUL R1,R2 / MOV Z,R1 → **6 instruções**
c) 1 endereço: LOAD A / ADD B / STORE T1 / LOAD C / SUB D / MUL T1 / STORE Z → **7 instruções**
d) 0 endereços: PUSH A / PUSH B / ADD / PUSH C / PUSH D / SUB / MUL / POP Z → **8 instruções**

### Exercício 4
a) ADD $t2, $s0, $s1: 000000 10000 10001 01010 00000 100000 = **0x02114020**
b) SUB $t0, $t1, $t2: 000000 01001 01010 01000 00000 100010 = **0x012A4022**
c) ADDI $s1, $s0, 50: 001000 10000 10001 0000000000110010 = **0x22110032**
d) LW $t0, 100($s2): 100011 10010 01000 0000000001100100 = **0x8E480064**
e) SW $t1, -4($s3): 101011 10011 01001 1111111111111100 = **0xAE69FFFC**

### Exercício 6
a) Big-endian: 0x400=DE, 0x401=AD, 0x402=BE, 0x403=EF
b) Little-endian: 0x400=EF, 0x401=BE, 0x402=AD, 0x403=DE
c) Big: 0xDE; Little: 0xEF
d) Big: 0xDEAD; Little: 0xBEEF

### Exercício 7
a) 8 bits: 255; 16 bits: 65.535; 32 bits: 4.294.967.295
b) 8 bits: -128 a +127; 16 bits: -32.768 a +32.767; 32 bits: -2.147.483.648 a +2.147.483.647
c) -100: 8 bits: 0x9C; 16 bits: 0xFF9C; 32 bits: 0xFFFFFF9C

### Exercício 8
a) Transferência, b) Aritmética, c) Controle, d) Lógica, e) Transferência, f) Controle, g) Lógica, h) Controle, i) Sistema, j) Aritmética, k) E/S, l) Controle, m) Aritmética/Lógica, n) Transferência, o) Sistema

### Exercício 11
a) 4 bits (2⁴ = 16 instruções)
b) 3 bits (2³ = 8 registradores)
c) Tipo R: 4(op) + 3(rd) + 3(rs) + 3(rt) = 13 bits → sobram 3 bits
d) Tipo I: 4(op) + 3(reg) + 9(imm) = 16 bits → imediato máx = 511 (sem sinal) ou -256 a +255 (com sinal)

</details>
