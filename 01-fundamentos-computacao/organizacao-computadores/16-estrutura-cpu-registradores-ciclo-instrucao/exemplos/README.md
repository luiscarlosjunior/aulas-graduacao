# 🔬 Exemplos Práticos - Aula 16

## Estrutura da CPU, Registradores e Ciclo de Instrução

---

## Exemplo 1: Trace Completo — Instrução ADD 🧮

### Cenário

```
Memória:
  Endereço 0x100: instrução ADD R1, R2, R3 (codificada como 0x00430820)
  R2 = 15, R3 = 27

Registradores antes:
  PC  = 0x100
  R1  = 0
  R2  = 15
  R3  = 27
  PSW = 0x00000000

═══ TRACE COMPLETO ═══

╔════════════╦═════════════════════════════════════════════════════╗
║   Ciclo    ║  Micro-operação e Estado                           ║
╠════════════╬═════════════════════════════════════════════════════╣
║            ║  FASE: FETCH                                       ║
║   t1       ║  MAR ← PC                                         ║
║            ║  MAR = 0x100                                       ║
╠════════════╬═════════════════════════════════════════════════════╣
║   t2       ║  MBR ← Memória[0x100] = 0x00430820                ║
║            ║  PC ← PC + 4 = 0x104                               ║
╠════════════╬═════════════════════════════════════════════════════╣
║   t3       ║  IR ← MBR = 0x00430820                            ║
╠════════════╬═════════════════════════════════════════════════════╣
║            ║  FASE: DECODE                                      ║
║   t4       ║  UC decodifica IR:                                 ║
║            ║    Opcode = 000000 (tipo R)                        ║
║            ║    rs = R2, rt = R3, rd = R1                       ║
║            ║    funct = 100000 (ADD)                            ║
║            ║  A ← R2 = 15                                      ║
║            ║  B ← R3 = 27                                      ║
╠════════════╬═════════════════════════════════════════════════════╣
║            ║  FASE: EXECUTE                                     ║
║   t5       ║  ALU_resultado ← A + B = 15 + 27 = 42            ║
║            ║  PSW: Z=0 (não é zero), N=0 (positivo),           ║
║            ║        C=0 (sem carry), V=0 (sem overflow)        ║
╠════════════╬═════════════════════════════════════════════════════╣
║            ║  FASE: STORE                                       ║
║   t6       ║  R1 ← ALU_resultado = 42                          ║
╠════════════╬═════════════════════════════════════════════════════╣
║  RESULTADO ║  R1 = 42, PC = 0x104, PSW: Z=0, N=0, C=0, V=0   ║
╚════════════╩═════════════════════════════════════════════════════╝
```

---

## Exemplo 2: Trace Completo — Instrução LOAD 📥

### Cenário

```
Memória:
  Endereço 0x200: instrução LOAD R5, [0x1000]
  Endereço 0x1000: valor 0xDEADBEEF

Registradores antes:
  PC = 0x200
  R5 = 0x00000000

═══ TRACE COMPLETO ═══

Ciclo t1 (Fetch):
  MAR ← PC = 0x200

Ciclo t2 (Fetch):
  MBR ← Mem[0x200] = código da instrução LOAD R5, [0x1000]
  PC ← 0x204

Ciclo t3 (Fetch):
  IR ← MBR

Ciclo t4 (Decode):
  UC: operação = LOAD, destino = R5, endereço = 0x1000
  MAR ← 0x1000  (endereço do dado a ser lido)

Ciclo t5 (Execute - Acesso à memória):
  MBR ← Mem[0x1000] = 0xDEADBEEF

Ciclo t6 (Store):
  R5 ← MBR = 0xDEADBEEF

RESULTADO:
  R5 = 0xDEADBEEF ✅
  PC = 0x204
```

---

## Exemplo 3: Trace Completo — Instrução STORE 📤

### Cenário

```
Memória:
  Endereço 0x300: instrução STORE R7, [0x2000]
  R7 = 0x12345678

═══ TRACE COMPLETO ═══

Ciclo t1 (Fetch):
  MAR ← PC = 0x300

Ciclo t2 (Fetch):
  MBR ← Mem[0x300] = instrução STORE R7, [0x2000]
  PC ← 0x304

Ciclo t3 (Fetch):
  IR ← MBR

Ciclo t4 (Decode):
  UC: operação = STORE, fonte = R7, endereço = 0x2000
  MAR ← 0x2000        (endereço destino)
  MBR ← R7 = 0x12345678  (dado a ser escrito)

Ciclo t5 (Execute - Escrita na memória):
  Mem[0x2000] ← MBR = 0x12345678

RESULTADO:
  Memória[0x2000] = 0x12345678 ✅
  PC = 0x304
```

---

## Exemplo 4: Trace Completo — Desvio Condicional (BEQ) 🔀

### Cenário

```
Memória:
  Endereço 0x400: BEQ R1, R2, 0x500  (salta para 0x500 se R1 == R2)
  Endereço 0x404: SUB R3, R3, R1     (próxima instrução sequencial)
  Endereço 0x500: ADD R4, R4, R5     (destino do salto)

═══ CASO A: R1 = 10, R2 = 10 (condição VERDADEIRA) ═══

Fetch (t1-t3): Busca instrução BEQ de 0x400
  IR = BEQ R1, R2, 0x500
  PC = 0x404

Decode (t4):
  UC: operação = BEQ, compara R1 com R2
  A ← R1 = 10
  B ← R2 = 10

Execute (t5):
  ALU: A - B = 10 - 10 = 0
  Flag Z = 1 (resultado é zero, ou seja, R1 == R2)

  Como Z = 1 (iguais): PC ← 0x500  ← SALTO!

RESULTADO: PC = 0x500 → Próxima instrução será ADD R4, R4, R5 ✅

═══ CASO B: R1 = 10, R2 = 20 (condição FALSA) ═══

Fetch (t1-t3): Igual
  PC = 0x404

Decode (t4):
  A ← R1 = 10
  B ← R2 = 20

Execute (t5):
  ALU: A - B = 10 - 20 = -10
  Flag Z = 0 (resultado NÃO é zero, ou seja, R1 ≠ R2)

  Como Z = 0: PC permanece 0x404 (NÃO SALTA)

RESULTADO: PC = 0x404 → Próxima instrução será SUB R3, R3, R1 ✅
```

---

## Exemplo 5: Execução de um Programa Completo 🖥️

### Programa: Somar os valores 3 + 5 e armazenar na memória

```
Programa em Assembly (simplificado):

Endereço │ Instrução           │ Significado
─────────┼─────────────────────┼────────────────────────
0x000    │ LOAD R1, [0x100]    │ R1 ← Mem[0x100] (valor 3)
0x004    │ LOAD R2, [0x104]    │ R2 ← Mem[0x104] (valor 5)
0x008    │ ADD  R3, R1, R2     │ R3 ← R1 + R2
0x00C    │ STORE R3, [0x108]   │ Mem[0x108] ← R3 (resultado)
0x010    │ HALT                │ Parar execução

Dados na memória:
0x100: 3
0x104: 5
0x108: ? (será preenchido)

═══ EXECUÇÃO PASSO A PASSO ═══

>>> Instrução 1: LOAD R1, [0x100] <<<
  PC=0x000 → Fetch → Decode → MAR=0x100 → MBR=3 → R1=3
  PC=0x004 após fetch

>>> Instrução 2: LOAD R2, [0x104] <<<
  PC=0x004 → Fetch → Decode → MAR=0x104 → MBR=5 → R2=5
  PC=0x008 após fetch

>>> Instrução 3: ADD R3, R1, R2 <<<
  PC=0x008 → Fetch → Decode → A=R1=3, B=R2=5
  Execute: 3 + 5 = 8 → R3=8
  PC=0x00C após fetch

>>> Instrução 4: STORE R3, [0x108] <<<
  PC=0x00C → Fetch → Decode → MAR=0x108, MBR=R3=8
  Execute: Mem[0x108] ← 8
  PC=0x010 após fetch

>>> Instrução 5: HALT <<<
  PC=0x010 → Fetch → Decode → CPU para.

ESTADO FINAL:
┌────────────────────────────────────────────────────┐
│ R1=3, R2=5, R3=8                                   │
│ Mem[0x100]=3, Mem[0x104]=5, Mem[0x108]=8 ✅        │
│ PC=0x014 (ou parado no HALT)                        │
│ Total de instruções executadas: 5                   │
│ Total de acessos à memória: 5 (fetch) + 3 (dados)  │
└────────────────────────────────────────────────────┘
```

---

## Exemplo 6: Programa com Loop e Desvio 🔄

### Programa: Somar os números de 1 a 5

```
Registradores iniciais: R1=0 (soma), R2=1 (contador), R3=5 (limite)

Endereço │ Instrução            │ Significado
─────────┼──────────────────────┼─────────────────────
0x000    │ MOV R1, #0           │ R1 ← 0 (soma = 0)
0x004    │ MOV R2, #1           │ R2 ← 1 (i = 1)
0x008    │ MOV R3, #5           │ R3 ← 5 (limite)
0x00C    │ ADD R1, R1, R2       │ R1 ← R1 + R2 (soma += i)  ← LOOP
0x010    │ ADD R2, R2, #1       │ R2 ← R2 + 1 (i++)
0x014    │ CMP R2, R3           │ Compara R2 com R3
0x018    │ BLE 0x00C            │ Se R2 ≤ R3, volta para LOOP
0x01C    │ STORE R1, [0x200]    │ Mem[0x200] ← R1 (salva resultado)
0x020    │ HALT                 │ Parar

═══ TRACE DO LOOP ═══

Iteração 1: R1=0, R2=1, R3=5
  ADD R1,R1,R2 → R1 = 0+1 = 1
  ADD R2,R2,#1 → R2 = 2
  CMP R2,R3    → 2 ≤ 5? SIM → BLE salta para 0x00C

Iteração 2: R1=1, R2=2
  ADD R1,R1,R2 → R1 = 1+2 = 3
  ADD R2,R2,#1 → R2 = 3
  CMP R2,R3    → 3 ≤ 5? SIM → BLE salta para 0x00C

Iteração 3: R1=3, R2=3
  ADD R1,R1,R2 → R1 = 3+3 = 6
  ADD R2,R2,#1 → R2 = 4
  CMP R2,R3    → 4 ≤ 5? SIM → BLE salta

Iteração 4: R1=6, R2=4
  ADD R1,R1,R2 → R1 = 6+4 = 10
  ADD R2,R2,#1 → R2 = 5
  CMP R2,R3    → 5 ≤ 5? SIM → BLE salta

Iteração 5: R1=10, R2=5
  ADD R1,R1,R2 → R1 = 10+5 = 15
  ADD R2,R2,#1 → R2 = 6
  CMP R2,R3    → 6 ≤ 5? NÃO → BLE NÃO salta

STORE R1, [0x200] → Mem[0x200] = 15
HALT

Resultado: 1+2+3+4+5 = 15 ✅

Estatísticas:
  Instruções de inicialização: 3
  Iterações do loop: 5 × 4 instruções = 20
  Instruções de finalização: 2
  Total: 25 instruções executadas
```

---

## Exemplo 7: Flags da PSW em Ação 🚩

### Demonstração das Flags

```
═══ Operação: 127 + 1 (8 bits com sinal) ═══

  R1 = 127 = 0111 1111
  R2 = 1   = 0000 0001

  ADD R3, R1, R2

  0111 1111  (127)
+ 0000 0001  (1)
──────────
  1000 0000  (-128 em complemento de 2!)

  Flags:
    Z = 0 (resultado ≠ 0)
    N = 1 (bit mais significativo = 1, negativo)
    C = 0 (não houve carry para fora de 8 bits)
    V = 1 (OVERFLOW! De positivo + positivo deu negativo!)

═══ Operação: 5 - 5 ═══

  R1 = 5  = 0000 0101
  R2 = 5  = 0000 0101

  SUB R3, R1, R2

  0000 0101  (5)
- 0000 0101  (5)
──────────
  0000 0000  (0)

  Flags:
    Z = 1 ← (resultado é ZERO)
    N = 0 (resultado não é negativo)
    C = 0 (sem borrow)
    V = 0 (sem overflow)

═══ Operação: 3 - 7 ═══

  R1 = 3  = 0000 0011
  R2 = 7  = 0000 0111

  SUB R3, R1, R2

  0000 0011  (3)
- 0000 0111  (7)
──────────
  1111 1100  (-4 em complemento de 2)

  Flags:
    Z = 0 (resultado ≠ 0)
    N = 1 ← (resultado é NEGATIVO)
    C = 1 (houve borrow)
    V = 0 (sem overflow: negativo é resultado correto)

═══ Uso das Flags em desvios condicionais ═══

CMP R1, R2         ; Internamente faz R1 - R2 e atualiza flags
BEQ label          ; Salta se Z=1 (R1 == R2)
BNE label          ; Salta se Z=0 (R1 != R2)
BGT label          ; Salta se Z=0 e N=0 (R1 > R2)
BLT label          ; Salta se N=1 (R1 < R2)
BGE label          ; Salta se N=0 (R1 >= R2)
BLE label          ; Salta se Z=1 ou N=1 (R1 <= R2)
```

---

## Exemplo 8: Chamada de Sub-rotina e a Pilha 📚

```
Programa principal que chama uma sub-rotina:

Endereço │ Instrução            │ Significado
─────────┼──────────────────────┼─────────────────────────────
0x100    │ MOV R1, #10          │ R1 = 10
0x104    │ MOV R2, #20          │ R2 = 20
0x108    │ CALL 0x200           │ Chama sub-rotina em 0x200
0x10C    │ STORE R3, [0x300]    │ Usa resultado (após retorno)
0x110    │ HALT                 │
         │                      │
0x200    │ ADD R3, R1, R2       │ Sub-rotina: R3 = R1 + R2
0x204    │ RET                  │ Retorna ao chamador

═══ EXECUÇÃO DETALHADA ═══

Estado inicial: PC=0x100, SP=0xFFFC (topo da pilha)

1. MOV R1, #10  → R1=10, PC=0x104
2. MOV R2, #20  → R2=20, PC=0x108

3. CALL 0x200 (Chamada de sub-rotina):
   • PUSH PC (salva endereço de retorno na pilha):
     SP ← SP - 4 = 0xFFF8
     Mem[0xFFF8] ← 0x10C (endereço de retorno)
   • PC ← 0x200 (salta para sub-rotina)

   Pilha:
   ┌──────────┐
   │  0x10C   │ ← SP = 0xFFF8 (endereço de retorno)
   ├──────────┤
   │   ...    │
   └──────────┘

4. ADD R3, R1, R2 → R3 = 10 + 20 = 30, PC=0x204

5. RET (Retorno):
   • POP PC (restaura endereço de retorno):
     PC ← Mem[0xFFF8] = 0x10C
     SP ← SP + 4 = 0xFFFC

6. STORE R3, [0x300] → Mem[0x300] = 30, PC=0x110

7. HALT → Programa termina

RESULTADO: Mem[0x300] = 30 ✅
A pilha voltou ao estado original (SP = 0xFFFC)
```

---

## Exemplo 9: Formato MIPS Detalhado 📐

```
═══ Codificação da instrução ADD $t0, $t1, $t2 ═══

Formato R:
┌────────┬───────┬───────┬───────┬───────┬────────┐
│ opcode │  rs   │  rt   │  rd   │ shamt │ funct  │
│ 6 bits │5 bits │5 bits │5 bits │5 bits │ 6 bits │
└────────┴───────┴───────┴───────┴───────┴────────┘

$t0 = registrador 8  = 01000
$t1 = registrador 9  = 01001
$t2 = registrador 10 = 01010

ADD: opcode = 000000, funct = 100000

┌────────┬───────┬───────┬───────┬───────┬────────┐
│ 000000 │ 01001 │ 01010 │ 01000 │ 00000 │ 100000 │
└────────┴───────┴───────┴───────┴───────┴────────┘
   op      $t1     $t2     $t0    shamt    ADD

Binário: 0000 0001 0010 1010 0100 0000 0010 0000
Hexadecimal: 0x012A4020

═══ Codificação da instrução LW $t0, 100($t1) ═══

Formato I:
┌────────┬───────┬───────┬──────────────────────┐
│ opcode │  rs   │  rt   │     imediato          │
│ 6 bits │5 bits │5 bits │     16 bits           │
└────────┴───────┴───────┴──────────────────────┘

LW: opcode = 100011
$t0 = 01000 (destino)
$t1 = 01001 (base)
100 = 0000000001100100

┌────────┬───────┬───────┬──────────────────────┐
│ 100011 │ 01001 │ 01000 │ 0000000001100100      │
└────────┴───────┴───────┴──────────────────────┘
  LW       $t1     $t0          100

Binário: 1000 1101 0010 1000 0000 0000 0110 0100
Hexadecimal: 0x8D280064
```

---

## Exemplo 10: Cálculo de Desempenho 📈

```
═══ Problema 1: Tempo de execução ═══

Processador: 2,5 GHz
Programa: 2 × 10⁹ instruções
Mix de instruções:
  - 40% aritméticas (CPI = 1)
  - 20% loads (CPI = 3, inclui acesso à memória)
  - 10% stores (CPI = 3)
  - 20% desvios (CPI = 2, inclui penalidade de branch)
  - 10% outras (CPI = 1)

CPI médio = 0,40×1 + 0,20×3 + 0,10×3 + 0,20×2 + 0,10×1
CPI médio = 0,40 + 0,60 + 0,30 + 0,40 + 0,10
CPI médio = 1,80 ciclos

Tempo de execução:
T = N × CPI × T_ciclo
T = 2×10⁹ × 1,80 × (1 / 2,5×10⁹)
T = 2×10⁹ × 1,80 × 0,4×10⁻⁹
T = 1,44 segundos

═══ Problema 2: Comparação entre processadores ═══

Processador A: 3 GHz, CPI = 2,0
Processador B: 2 GHz, CPI = 1,2

Para o mesmo programa de 10⁹ instruções:

Tempo A = 10⁹ × 2,0 / (3×10⁹) = 0,667 s
Tempo B = 10⁹ × 1,2 / (2×10⁹) = 0,600 s

Processador B é mais rápido! (apesar de ter clock menor)
Speedup = 0,667 / 0,600 = 1,11x

📌 Conclusão: Clock mais alto NÃO significa necessariamente
   processador mais rápido. O CPI é igualmente importante!

═══ Problema 3: Impacto do pipeline ═══

Sem pipeline: CPI = 5 (5 ciclos por instrução)
Com pipeline de 5 estágios (ideal): CPI = 1

Mesmo clock, mesmo programa:
  Speedup = CPI_sem / CPI_com = 5 / 1 = 5x mais rápido!

Na prática (hazards, stalls):
  CPI com pipeline ≈ 1,2-1,5
  Speedup real ≈ 5 / 1,35 ≈ 3,7x
```

---

## 📝 Resumo dos Exemplos

| Exemplo | Conceito Demonstrado |
|---------|---------------------|
| 1 | Trace completo de ADD — todas as micro-operações |
| 2 | Trace completo de LOAD — acesso à memória |
| 3 | Trace completo de STORE — escrita na memória |
| 4 | Desvio condicional BEQ — condição verdadeira e falsa |
| 5 | Programa completo (soma 3+5) — todas as instruções |
| 6 | Loop com desvio — soma de 1 a 5 |
| 7 | Flags da PSW — overflow, zero, negativo, carry |
| 8 | Chamada de sub-rotina — CALL, pilha e RET |
| 9 | Formato de instrução MIPS — codificação binária |
| 10 | Cálculos de desempenho — CPI, tempo, pipeline |
