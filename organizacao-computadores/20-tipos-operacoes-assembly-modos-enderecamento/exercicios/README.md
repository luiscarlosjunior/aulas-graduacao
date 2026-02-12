# 📝 Exercícios - Aula 20: Tipos de Operações, Assembly e Modos de Endereçamento

## Exercício 1 — Operações de Transferência de Dados

Dado o estado inicial dos registradores e memória:

```
EAX = 10, EBX = 20, ECX = 30, EDX = 40
mem[100] = 50, mem[200] = 60, mem[300] = 70
Pilha vazia, ESP = 1000
```

Determine o estado final de todos os registradores e posições de memória após a execução de cada sequência:

a)
```assembly
MOV  EAX, EBX
MOV  ECX, [100]
MOV  [200], EAX
```

b)
```assembly
PUSH EAX
PUSH EBX
POP  ECX
POP  EDX
```

c)
```assembly
XCHG EAX, EBX
MOV  ECX, [200]
MOV  [100], ECX
XCHG [100], EAX
```

---

## Exercício 2 — Operações Aritméticas e Flags

Para cada operação abaixo, calcule o resultado e indique o estado dos flags CF, ZF, SF e OF. Considere operações com inteiros de 8 bits com sinal.

a) `ADD AL, BL` onde AL = 100, BL = 50
b) `ADD AL, BL` onde AL = 200, BL = 100 (operação **sem sinal** — análise CF)
c) `SUB AL, BL` onde AL = 30, BL = 30
d) `SUB AL, BL` onde AL = 10, BL = 20
e) `ADD AL, BL` onde AL = 127, BL = 1 (overflow com sinal!)
f) `INC AL` onde AL = 255 (0xFF)

---

## Exercício 3 — Operações Lógicas

Dado AL = 10110011b (0xB3), execute cada operação e mostre o resultado em binário e hexadecimal:

a) `AND AL, 11110000b`
b) `OR AL, 00001100b`
c) `XOR AL, 11111111b`
d) `NOT AL`
e) `SHL AL, 2`
f) `SHR AL, 3`
g) `ROL AL, 1`

---

## Exercício 4 — Controle de Fluxo

Rastreie a execução do seguinte código e determine o valor final de EAX:

```assembly
    MOV   EAX, 0
    MOV   ECX, 5

loop_inicio:
    ADD   EAX, ECX
    DEC   ECX
    CMP   ECX, 0
    JG    loop_inicio

    ; Qual o valor de EAX?
```

a) Preencha a tabela de rastreamento com os valores de EAX e ECX a cada iteração.
b) Qual operação matemática este código realiza?
c) Reescreva o código usando a instrução `LOOP` em vez de `CMP` + `JG`.
d) Modifique o código para calcular 1 + 2 + 3 + ... + 100.

---

## Exercício 5 — Programa Assembly — IF/ELSE

Escreva o código assembly (x86) equivalente a cada trecho em C:

a)
```c
if (x > 0)
    y = x;
else
    y = -x;
```

b)
```c
if (a == b)
    c = a + b;
else if (a > b)
    c = a - b;
else
    c = b - a;
```

c)
```c
switch (x) {
    case 1: y = 10; break;
    case 2: y = 20; break;
    case 3: y = 30; break;
    default: y = 0;
}
```

---

## Exercício 6 — Modos de Endereçamento — Identificação

Identifique o modo de endereçamento usado em cada instrução:

| Instrução | Modo de Endereçamento |
|-----------|:---------------------:|
| a) `MOV EAX, 42` | |
| b) `MOV EAX, EBX` | |
| c) `MOV EAX, [1000h]` | |
| d) `MOV EAX, [EBX]` | |
| e) `MOV EAX, [EBX + 8]` | |
| f) `MOV EAX, [EBX + ESI*4]` | |
| g) `MOV EAX, [EBX + ESI*4 + 12]` | |
| h) `PUSH EAX` | |
| i) `JMP +100` | |
| j) `ADD $t0, $t1, $t2` (MIPS) | |
| k) `LW $t0, 16($s1)` (MIPS) | |
| l) `ADDI $t0, $zero, 25` (MIPS) | |

---

## Exercício 7 — Cálculo de Endereço Efetivo

Dados os seguintes valores de registradores:

```
EBX = 0x1000, ESI = 5, EDI = 0x2000, EBP = 0x3000, ESP = 0xFF00
PC = 0x4000
```

Calcule o endereço efetivo (EA) para cada instrução:

a) `MOV EAX, [EBX]` → EA = ?
b) `MOV EAX, [EBX + 16]` → EA = ?
c) `MOV EAX, [EBX + ESI*4]` → EA = ?
d) `MOV EAX, [EBP - 8]` → EA = ?
e) `MOV EAX, [EDI + ESI*2 + 100]` → EA = ?
f) `JMP +200` (relativo ao PC, PC aponta para próxima instrução = PC + tamanho da instrução, assuma 5 bytes) → EA = ?

---

## Exercício 8 — Acesso a Arrays

Considere um array de inteiros (32 bits cada) armazenado na memória:

```
Endereço base: 0x5000
Conteúdo: arr[0]=10, arr[1]=20, arr[2]=30, arr[3]=40, arr[4]=50
```

a) Qual o endereço de `arr[3]`?
b) Escreva a instrução para carregar `arr[3]` em EAX usando modo **base + offset**.
c) Escreva a instrução para carregar `arr[ESI]` em EAX usando modo **indexado com escala** (ESI contém o índice).
d) Escreva um loop que soma todos os 5 elementos do array.
e) Se o array fosse de bytes (8 bits) em vez de inteiros (32 bits), como mudaria o fator de escala?

---

## Exercício 9 — Stack Frame e Chamada de Função

Considere a seguinte função em C:

```c
int multiplica(int a, int b) {
    int resultado = a * b;
    return resultado;
}

// Chamada:
int x = multiplica(6, 7);
```

a) Escreva o código assembly (x86) para a chamada da função e a função em si, usando a convenção cdecl (parâmetros na pilha, resultado em EAX).
b) Desenhe o estado da pilha dentro da função, indicando todos os endereços relativos a EBP.
c) Qual o valor de EAX após o retorno da função?
d) Por que é importante que a função salve e restaure EBP?

---

## Exercício 10 — Comparação de Modos de Endereçamento

Para cada cenário abaixo, indique qual modo de endereçamento é mais adequado e justifique:

a) Inicializar um registrador com o valor constante 0.
b) Acessar o quinto elemento de um array de inteiros.
c) Acessar um campo de uma struct apontada por um ponteiro.
d) Implementar um desvio condicional para um rótulo próximo.
e) Acessar uma variável local de uma função (no stack frame).
f) Percorrer um array com um ponteiro que avança a cada iteração.
g) Implementar uma tabela de ponteiros de função.

---

## Exercício 11 — Tradução C → Assembly

Traduza os seguintes trechos de código C para assembly x86. Use comentários para explicar cada linha.

a) Atribuição simples:
```c
int a = 5;
int b = a + 3;
int c = b * 2;
```

b) Loop com array:
```c
int arr[5] = {2, 4, 6, 8, 10};
int soma = 0;
for (int i = 0; i < 5; i++) {
    soma += arr[i];
}
```

c) Função com retorno:
```c
int quadrado(int x) {
    return x * x;
}

int resultado = quadrado(7);
```

---

## Exercício 12 — Análise e Projeto

a) Explique por que `XOR EAX, EAX` é preferido em vez de `MOV EAX, 0` para zerar um registrador em processadores modernos. (Dica: pense em tamanho da instrução e dependências.)

b) Por que o modo de endereçamento **indireto** (ponteiro de ponteiro) é raramente usado, embora exista no hardware?

c) Compare os modos de endereçamento disponíveis no x86 versus MIPS. Qual é mais restritivo? Por quê?

d) Explique como o **modo base + offset** é essencial para implementar o acesso a variáveis locais e parâmetros de funções no stack frame.

e) Um processador RISC tem poucos modos de endereçamento (basicamente: imediato, registrador e base + offset). Por que essa simplificação é vantajosa para o pipeline?

---

## 🎯 Gabarito Resumido

<details>
<summary>Clique para ver as respostas</summary>

### Exercício 1
a) EAX=20, EBX=20, ECX=50, EDX=40; mem[200]=20
b) EAX=10, EBX=20, ECX=20, EDX=10; ESP=1000 (voltou ao original)
c) EAX=60, EBX=10, ECX=60; mem[100]=20 (após primeiro XCHG: EAX=20, EBX=10; após MOV [100]=60; após XCHG [100]: [100]=20, EAX=60)

### Exercício 2
a) 150 (0x96), CF=0, ZF=0, SF=1 (negativo em comp.2 de 8 bits), OF=1
b) 300 → 44 (mod 256), CF=1 (carry), ZF=0, SF=0, OF=0
c) 0, CF=0, ZF=1, SF=0, OF=0
d) -10 (0xF6), CF=1 (borrow), ZF=0, SF=1, OF=0
e) 128 (0x80), CF=0, ZF=0, SF=1, OF=1 (overflow: positivo+positivo=negativo)
f) 0 (0x00), CF=0 (INC não afeta CF), ZF=1, SF=0, OF=0

### Exercício 3
a) AL = 10110000b = 0xB0
b) AL = 10111111b = 0xBF
c) AL = 01001100b = 0x4C
d) AL = 01001100b = 0x4C
e) AL = 11001100b = 0xCC (shift left 2: preenche com 0s à direita)
f) AL = 00010110b = 0x16 (shift right 3)
g) AL = 01100111b = 0x67 (bit 7 vai para bit 0)

### Exercício 4
a) Iterações: ECX=5→EAX=5; ECX=4→EAX=9; ECX=3→EAX=12; ECX=2→EAX=14; ECX=1→EAX=15
b) Soma de 1 a 5 = 15
c) `MOV ECX,5 / XOR EAX,EAX / loop_inicio: ADD EAX,ECX / LOOP loop_inicio`

### Exercício 6
a) Imediato, b) Registrador, c) Direto, d) Registrador indireto, e) Base + offset
f) Base + indexado com escala, g) Base + indexado + deslocamento, h) Pilha (implícito)
i) Relativo ao PC, j) Registrador, k) Base + offset, l) Imediato

### Exercício 7
a) EA = 0x1000
b) EA = 0x1000 + 16 = 0x1010
c) EA = 0x1000 + 5×4 = 0x1000 + 20 = 0x1014
d) EA = 0x3000 - 8 = 0x2FF8
e) EA = 0x2000 + 5×2 + 100 = 0x2000 + 10 + 100 = 0x206E
f) EA = 0x4000 + 5 + 200 = 0x40CD

### Exercício 8
a) 0x5000 + 3×4 = 0x500C
b) `MOV EAX, [0x500C]` ou `MOV EBX, 0x5000` / `MOV EAX, [EBX + 12]`
c) `MOV EAX, [EBX + ESI*4]` (com EBX = 0x5000)
e) Escala = 1 em vez de 4

### Exercício 9
c) EAX = 42 (6 × 7)

</details>
