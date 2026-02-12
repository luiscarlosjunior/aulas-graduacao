# 🧪 Exemplos - Aula 17: Pipeline, RISC e CISC

## Exemplo 1: Diagrama de Pipeline Básico

### Execução de 6 instruções em pipeline de 5 estágios

```
Ciclo de Clock:  1    2    3    4    5    6    7    8    9   10
               ┌────┬────┬────┬────┬────┐
Instrução 1:   │ IF │ ID │ EX │MEM │ WB │
               └────┴────┴────┴────┴────┘
                    ┌────┬────┬────┬────┬────┐
Instrução 2:        │ IF │ ID │ EX │MEM │ WB │
                    └────┴────┴────┴────┴────┘
                         ┌────┬────┬────┬────┬────┐
Instrução 3:             │ IF │ ID │ EX │MEM │ WB │
                         └────┴────┴────┴────┴────┘
                              ┌────┬────┬────┬────┬────┐
Instrução 4:                  │ IF │ ID │ EX │MEM │ WB │
                              └────┴────┴────┴────┴────┘
                                   ┌────┬────┬────┬────┬────┐
Instrução 5:                       │ IF │ ID │ EX │MEM │ WB │
                                   └────┴────┴────┴────┴────┘
                                        ┌────┬────┬────┬────┬────┐
Instrução 6:                            │ IF │ ID │ EX │MEM │ WB │
                                        └────┴────┴────┴────┴────┘
```

**Análise:**
- Sem pipeline: 6 × 5 = **30 ciclos**
- Com pipeline: 5 + (6-1) = **10 ciclos**
- Speedup = 30/10 = **3,0×**

---

## Exemplo 2: Cálculo de Speedup do Pipeline

### Cenário: Pipeline de 5 estágios, executando 20 instruções

**Dados:**
- k = 5 estágios
- n = 20 instruções
- Tempo de cada estágio: t = 2 ns

**Cálculo sem pipeline:**
```
Tempo_seq = n × k × t
Tempo_seq = 20 × 5 × 2
Tempo_seq = 200 ns
```

**Cálculo com pipeline:**
```
Tempo_pipe = (k + n - 1) × t
Tempo_pipe = (5 + 20 - 1) × 2
Tempo_pipe = 24 × 2
Tempo_pipe = 48 ns
```

**Speedup:**
```
Speedup = Tempo_seq / Tempo_pipe
Speedup = 200 / 48
Speedup = 4,17×
```

**Throughput:**
```
Sem pipeline:  1 / (5 × 2ns) = 0,1 instrução/ns = 100 MIPS
Com pipeline:  20 / 48ns     = 0,417 instrução/ns ≈ 417 MIPS
```

> 📝 **Observe:** O speedup (4,17×) está próximo do máximo teórico (5×), pois temos 20 instruções (relativamente muitas).

---

## Exemplo 3: Speedup para Diferentes Quantidades de Instruções

| Instruções (n) | Ciclos sem Pipeline | Ciclos com Pipeline | Speedup | % do Máximo (5×) |
|:--------------:|:-------------------:|:-------------------:|:-------:|:-----------------:|
| 1 | 5 | 5 | 1,00× | 20% |
| 2 | 10 | 6 | 1,67× | 33% |
| 5 | 25 | 9 | 2,78× | 56% |
| 10 | 50 | 14 | 3,57× | 71% |
| 20 | 100 | 24 | 4,17× | 83% |
| 50 | 250 | 54 | 4,63× | 93% |
| 100 | 500 | 104 | 4,81× | 96% |
| 1000 | 5000 | 1004 | 4,98× | 99,6% |
| ∞ | — | — | 5,00× | 100% |

> 🎯 **Conclusão:** Quanto mais instruções, mais o speedup se aproxima do valor ideal k = 5.

---

## Exemplo 4: Pipeline com Hazard de Dados e Forwarding

### Código:

```assembly
ADD  R1, R2, R3     ; R1 = R2 + R3
SUB  R4, R1, R5     ; R4 = R1 - R5  (depende de R1)
AND  R6, R1, R7     ; R6 = R1 AND R7 (depende de R1)
OR   R8, R9, R10    ; R8 = R9 OR R10 (independente)
```

### Sem forwarding (com stalls):

```
Ciclo:     1    2    3    4    5    6    7    8    9   10   11
ADD R1:   IF   ID   EX  MEM   WB
SUB R4:        IF   ── stall ──   ID   EX  MEM   WB
AND R6:             ── stall ──        IF   ID   EX  MEM  WB
OR  R8:                                     IF   ID   EX  MEM  WB
```
Tempo: **11 ciclos** (2 stalls inseridos)

### Com forwarding (EX→EX):

```
Ciclo:     1    2    3    4    5    6    7    8
ADD R1:   IF   ID   EX  MEM   WB
                     │
              ┌──────┘ forwarding
              ▼
SUB R4:        IF   ID   EX  MEM   WB
AND R6:             IF   ID   EX  MEM   WB
OR  R8:                  IF   ID   EX  MEM   WB
```
Tempo: **8 ciclos** (sem stalls, forwarding resolve a dependência)

> 💡 **Forwarding economizou 3 ciclos neste exemplo!**

---

## Exemplo 5: Pipeline com Hazard de Controle

### Código com desvio:

```assembly
BEQ  R1, R2, alvo    ; Se R1 == R2, desvia para "alvo"
ADD  R3, R4, R5      ; Instrução seguinte
SUB  R6, R7, R8      ; Instrução seguinte
alvo:
MUL  R9, R10, R11    ; Destino do desvio
```

### Sem predição (stall até resolver):

```
Ciclo:      1    2    3    4    5    6    7    8    9
BEQ:       IF   ID   EX  MEM   WB
                      ↑
                 Desvio resolvido no EX
ADD/MUL:                  IF   ID   EX  MEM   WB
próxima:                       IF   ID   EX  MEM   WB

Penalidade: 2 ciclos de stall (bolhas nos ciclos 2 e 3 para IF)
```

### Com predição "não tomado" (acerta — desvio não é tomado):

```
Ciclo:      1    2    3    4    5    6    7    8
BEQ:       IF   ID   EX  MEM   WB
ADD:            IF   ID   EX  MEM   WB     ✅ Predição correta!
SUB:                 IF   ID   EX  MEM   WB

Penalidade: 0 ciclos (predição acertou)
```

### Com predição "não tomado" (erra — desvio é tomado):

```
Ciclo:      1    2    3    4    5    6    7    8    9
BEQ:       IF   ID   EX  MEM   WB
ADD:            IF   ID   ✗✗  ← Descartada (flush)
SUB:                 IF   ✗✗  ← Descartada (flush)
MUL(alvo):                IF   ID   EX  MEM   WB

Penalidade: 2 ciclos (instruções descartadas + busca do alvo)
```

---

## Exemplo 6: Comparação RISC vs CISC — Cópia de Bloco de Memória

### Tarefa: Copiar 100 bytes de um endereço para outro

**CISC (x86) — com instrução REP MOVSB:**

```assembly
; Configuração
MOV  ECX, 100       ; Contador = 100 bytes
MOV  ESI, origem    ; Ponteiro fonte
MOV  EDI, destino   ; Ponteiro destino

; Cópia - UMA instrução faz tudo!
REP MOVSB           ; Repete: copia byte de [ESI] para [EDI]
                    ; incrementa ESI e EDI, decrementa ECX
                    ; até ECX = 0

; Total: 4 instruções no programa
```

**RISC (estilo ARM/MIPS):**

```assembly
; Configuração
MOV   R1, #origem     ; Ponteiro fonte
MOV   R2, #destino    ; Ponteiro destino
MOV   R3, #100        ; Contador

; Loop de cópia
loop:
  LDRB  R4, [R1]      ; Carrega 1 byte da origem
  STRB  R4, [R2]      ; Armazena 1 byte no destino
  ADD   R1, R1, #1    ; Incrementa ponteiro fonte
  ADD   R2, R2, #1    ; Incrementa ponteiro destino
  SUB   R3, R3, #1    ; Decrementa contador
  BNE   loop           ; Se contador ≠ 0, volta ao loop

; Total: 9 instruções (3 setup + 6 no loop × 100 iterações = 603 exec.)
```

**Comparação:**

| Métrica | CISC (x86) | RISC (ARM) |
|---------|-----------|-----------|
| Instruções no código | 4 | 9 |
| Execuções de instrução | 4 | 603 |
| Tamanho do programa | Menor | Maior |
| Complexidade do hardware | Maior | Menor |
| Ciclos por instrução | Variável | ~1 (exceto branch) |

---

## Exemplo 7: Formatos de Instrução — RISC vs CISC

### RISC (ARM — tamanho fixo de 32 bits):

```
Tipo R (Registrador):
┌──────┬──────┬──────┬──────┬──────┬──────┐
│ Cond │  Op  │  Rn  │  Rd  │Shift │  Rm  │
│4 bits│8 bits│4 bits│4 bits│8 bits│4 bits │
└──────┴──────┴──────┴──────┴──────┴──────┘
                    = 32 bits (sempre)

Tipo I (Imediato):
┌──────┬──────┬──────┬──────┬──────────────┐
│ Cond │  Op  │  Rn  │  Rd  │  Imediato    │
│4 bits│8 bits│4 bits│4 bits│   12 bits    │
└──────┴──────┴──────┴──────┴──────────────┘
                    = 32 bits (sempre)
```

### CISC (x86 — tamanho variável):

```
Instrução curta (1 byte):
┌──────────┐
│   NOP    │
│  1 byte  │
└──────────┘

Instrução média (3 bytes):
┌──────────┬──────────┬──────────┐
│  Opcode  │  ModR/M  │   Disp   │
│  1 byte  │  1 byte  │  1 byte  │
└──────────┴──────────┴──────────┘

Instrução longa (até 15 bytes):
┌────────┬────────┬────────┬────────┬────────┬────────┬────────────────┐
│Prefixos│ REX    │Opcode  │ModR/M  │  SIB   │  Disp  │   Imediato    │
│0-4 byte│0-1 byte│1-3byte │0-1byte │0-1byte │0-4byte │  0-4 bytes    │
└────────┴────────┴────────┴────────┴────────┴────────┴────────────────┘
= 1 a 15 bytes (variável!)
```

> 📝 **Observe:** A regularidade do RISC facilita enormemente a decodificação e o pipeline!

---

## Exemplo 8: Análise de Desempenho — Pipeline Desbalanceado

### Pipeline com estágios de tempos diferentes:

| Estágio | Tempo |
|---------|-------|
| IF | 200 ps |
| ID | 150 ps |
| EX | 250 ps |
| MEM | 300 ps |
| WB | 100 ps |

**Sem pipeline:**
```
Latência = 200 + 150 + 250 + 300 + 100 = 1000 ps por instrução
```

**Com pipeline:**
```
O ciclo de clock deve ser igual ao estágio MAIS LENTO:
Ciclo = max(200, 150, 250, 300, 100) = 300 ps

Latência de 1 instrução = 5 × 300 = 1500 ps (pior!)
Throughput = 1 / 300 ps ≈ 3,33 GHz (melhor!)
```

**Sem pipeline — Throughput:**
```
Throughput = 1 / 1000 ps = 1 GHz
```

**Speedup real (para muitas instruções):**
```
Speedup = 1000 / 300 = 3,33× (não 5×, pois o pipeline está desbalanceado)
```

> ⚠️ **Importante:** Um pipeline desbalanceado (estágios com tempos diferentes) reduz a eficiência! O estágio mais lento limita todo o pipeline.

---

## Exemplo 9: Predição de Desvio com 2 Bits

### Sequência de desvios: T, T, NT, T, T, T, NT, T

**Estado inicial:** 00 (Forte Não Tomado)

| # | Resultado Real | Estado Antes | Predição | Acertou? | Estado Depois |
|---|---------------|-------------|----------|----------|--------------|
| 1 | T (Tomado) | 00 | NT | ❌ | 01 |
| 2 | T | 01 | NT | ❌ | 10 |
| 3 | NT (Não Tomado) | 10 | T | ❌ | 01 |
| 4 | T | 01 | NT | ❌ | 10 |
| 5 | T | 10 | T | ✅ | 11 |
| 6 | T | 11 | T | ✅ | 11 |
| 7 | NT | 11 | T | ❌ | 10 |
| 8 | T | 10 | T | ✅ | 11 |

**Taxa de acerto:** 3/8 = **37,5%** (baixa, pois o padrão é irregular)

> 💡 Para padrões regulares (como loops), a predição de 2 bits funciona muito melhor — tipicamente 85-95% de acerto.

---

## Exemplo 10: Impacto de Pipeline na Performance Real

### Cenário: Processador executando 1.000.000 de instruções

**Dados:**
- Clock: 1 GHz (ciclo = 1 ns)
- Pipeline: 5 estágios
- 20% das instruções são loads com dependência (1 ciclo de stall)
- 15% das instruções são branches com 10% de erro na predição (2 ciclos de penalidade cada)

**Cálculo do CPI (Ciclos por Instrução):**

```
CPI_ideal = 1 (pipeline ideal)

Penalidade de dados = 0,20 × 1 ciclo = 0,20
Penalidade de controle = 0,15 × 0,10 × 2 ciclos = 0,03

CPI_real = 1 + 0,20 + 0,03 = 1,23
```

**Tempo de execução:**

```
Tempo = n_instruções × CPI × T_ciclo
Tempo = 1.000.000 × 1,23 × 1 ns
Tempo = 1.230.000 ns = 1,23 ms
```

**Speedup real vs ideal:**

```
Speedup_real = CPI_ideal / CPI_real = 1 / 1,23 = 0,813

Ou seja, perdemos ~19% do desempenho ideal por causa dos hazards.
```

**Se não houvesse pipeline:**
```
Tempo = 1.000.000 × 5 × 1 ns = 5.000.000 ns = 5 ms

Speedup_pipeline = 5 ms / 1,23 ms = 4,07×
```

> 🎯 Mesmo com hazards, o pipeline ainda oferece um speedup significativo de **4,07×** comparado à execução sem pipeline!
