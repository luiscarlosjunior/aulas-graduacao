# 📝 Exercícios - Aula 17: Pipeline, RISC e CISC

## Exercício 1 — Conceito de Pipeline

Explique o conceito de pipeline usando uma analogia diferente da lavanderia (por exemplo: linha de montagem de carros, restaurante fast-food, ou outra). Sua analogia deve:

a) Identificar pelo menos 4 estágios.
b) Mostrar como funciona sem e com pipeline.
c) Calcular o ganho de desempenho.

---

## Exercício 2 — Cálculo de Speedup

Um processador possui um pipeline de **7 estágios**, cada estágio com tempo de execução de **1,5 ns**.

a) Qual o tempo para executar **1 instrução** com e sem pipeline?
b) Qual o tempo para executar **50 instruções** com e sem pipeline?
c) Qual o speedup para 50 instruções?
d) Qual o speedup máximo teórico?
e) Quantas instruções seriam necessárias para atingir um speedup de pelo menos 6,5×?

---

## Exercício 3 — Diagrama de Pipeline

Desenhe o diagrama temporal do pipeline de 5 estágios (IF, ID, EX, MEM, WB) para a execução das seguintes 8 instruções consecutivas **sem hazards**:

```
I1, I2, I3, I4, I5, I6, I7, I8
```

a) Quantos ciclos são necessários?
b) Qual o throughput (instruções por ciclo) quando o pipeline está cheio?
c) Em que ciclo o pipeline atinge estado estável (cheio)?

---

## Exercício 4 — Hazard de Dados

Considere o seguinte trecho de código assembly:

```assembly
ADD  R1, R2, R3      ; I1: R1 = R2 + R3
SUB  R4, R1, R5      ; I2: R4 = R1 - R5
AND  R6, R4, R7      ; I3: R6 = R4 AND R7
OR   R8, R1, R9      ; I4: R8 = R1 OR R9
XOR  R10, R11, R12   ; I5: R10 = R11 XOR R12
```

a) Identifique **todas** as dependências de dados (RAW) existentes.
b) Desenhe o diagrama do pipeline **sem forwarding**, indicando os stalls necessários.
c) Desenhe o diagrama do pipeline **com forwarding**, mostrando os caminhos de bypass.
d) Quantos ciclos são economizados com o forwarding?

---

## Exercício 5 — Hazard de Controle

Dado o seguinte código com desvio condicional:

```assembly
LOOP:  ADD   R1, R1, R2      ; I1
       SUB   R3, R3, #1      ; I2
       BNE   R3, LOOP        ; I3: Desvia se R3 ≠ 0
       MUL   R4, R5, R6      ; I4
       DIV   R7, R8, R9      ; I5
```

Considere que o loop executa 10 vezes (R3 começa com valor 10) e o desvio é resolvido no estágio EX.

a) Qual a penalidade em ciclos por erro de predição?
b) Com predição **"sempre não tomado"**: quantas vezes a predição acerta e quantas erra?
c) Com predição **"sempre tomado"**: quantas vezes acerta e quantas erra?
d) Qual estratégia de predição é melhor para este loop? Justifique.
e) Calcule o número total de ciclos de penalidade para cada estratégia.

---

## Exercício 6 — Pipeline Desbalanceado

Um processador possui um pipeline de 4 estágios com os seguintes tempos:

| Estágio | Tempo |
|---------|-------|
| Busca (IF) | 250 ps |
| Decodificação (ID) | 350 ps |
| Execução (EX) | 150 ps |
| Escrita (WB) | 200 ps |

a) Qual deve ser o período do clock para este pipeline?
b) Qual é a latência de uma instrução individual no pipeline?
c) Qual é o throughput máximo em instruções por segundo?
d) Calcule o speedup real comparado à execução sem pipeline.
e) Sugira como melhorar o balanceamento deste pipeline.

---

## Exercício 7 — RISC vs CISC — Análise Teórica

Compare as filosofias RISC e CISC respondendo:

a) Explique por que a filosofia RISC favorece o pipeline.
b) Qual a vantagem do CISC em termos de **densidade de código** (tamanho do programa)?
c) Por que processadores CISC modernos (como o Intel Core) usam tradução para micro-operações?
d) Em que situações a filosofia RISC é mais vantajosa que a CISC?
e) O que significa dizer que RISC e CISC estão "convergindo"?

---

## Exercício 8 — Comparação de Código RISC vs CISC

Considere a operação: **somar o conteúdo de duas posições de memória e armazenar o resultado em uma terceira**.

```
resultado = mem[A] + mem[B]
```

a) Escreva o código em estilo **CISC** (usando uma instrução que acesse memória diretamente na operação).
b) Escreva o código em estilo **RISC** (usando apenas instruções load/store para acessar memória).
c) Compare: número de instruções, acessos à memória, e ciclos estimados.
d) Qual abordagem resulta em programa menor (em bytes)?
e) Qual abordagem é mais fácil de otimizar no pipeline? Por quê?

---

## Exercício 9 — Predição de Desvio

Um processador usa predição de desvio de **2 bits** (contador saturante). O estado inicial é **10 (Fraco Tomado)**.

A seguinte sequência de resultados de desvio é observada:

```
T, T, NT, NT, T, NT, T, T, T, NT
```

Onde T = Tomado e NT = Não Tomado.

a) Preencha a tabela mostrando o estado antes, a predição, se acertou e o estado depois para cada desvio.
b) Qual a taxa de acerto total?
c) Compare com a taxa de acerto se usássemos predição estática "sempre tomado".
d) Compare com a taxa de acerto se usássemos predição de 1 bit.

---

## Exercício 10 — Impacto de Hazards no Desempenho

Um processador com pipeline de 5 estágios executa um programa com as seguintes características:

- Total de instruções: 2.000.000
- 25% são instruções de load (30% delas causam stall de 1 ciclo)
- 20% são instruções de branch (predição com 90% de acerto, penalidade de 3 ciclos por erro)
- Clock: 2 GHz

a) Calcule o CPI real do processador.
b) Qual o tempo de execução do programa?
c) Qual seria o tempo com pipeline ideal (CPI = 1)?
d) Qual o percentual de perda de desempenho devido aos hazards?
e) Se a taxa de acerto da predição de desvio melhorasse para 97%, qual seria o novo CPI?

---

## Exercício 11 — Classificação de Processadores

Classifique cada processador abaixo como RISC ou CISC e justifique brevemente:

| Processador | RISC ou CISC? | Justificativa |
|-------------|:------------:|---------------|
| Intel Core i7 | | |
| ARM Cortex-A78 | | |
| MIPS R4000 | | |
| AMD Ryzen 9 | | |
| Apple M2 | | |
| RISC-V | | |
| Motorola 68000 | | |
| IBM POWER10 | | |

---

## Exercício 12 — Projeto de Pipeline

Você está projetando um novo processador e precisa decidir entre um pipeline de **5 estágios** e um de **10 estágios** (superpipeline).

Dados:
- Tempo total para executar uma instrução sem pipeline: 10 ns
- Pipeline de 5 estágios: estágio de 2 ns + overhead de 0,2 ns por estágio
- Pipeline de 10 estágios: estágio de 1 ns + overhead de 0,2 ns por estágio
- Programa de 1.000.000 de instruções
- Taxa de branch: 20%, penalidade = (k/2) ciclos por erro, taxa de acerto: 90%

a) Qual o ciclo de clock de cada pipeline (considerando o overhead)?
b) Qual o CPI ideal de cada pipeline?
c) Qual o CPI real de cada pipeline (considerando penalidades de branch)?
d) Qual pipeline oferece melhor tempo de execução?
e) Discuta as vantagens e desvantagens de pipelines mais profundos.

---

## 🎯 Gabarito Resumido

<details>
<summary>Clique para ver as respostas</summary>

### Exercício 2
a) Sem pipeline: 7 × 1,5 = 10,5 ns; Com pipeline: 10,5 ns (mesma latência para 1 instrução)
b) Sem pipeline: 50 × 10,5 = 525 ns; Com pipeline: (7 + 49) × 1,5 = 84 ns
c) Speedup = 525/84 = 6,25×
d) Speedup máximo = k = 7×
e) Resolvendo: 7n/(7+n-1) ≥ 6,5 → 7n ≥ 6,5(n+6) → 0,5n ≥ 39 → n ≥ 78 instruções

### Exercício 3
a) 5 + (8-1) = 12 ciclos
b) 1 instrução por ciclo (quando cheio)
c) No ciclo 5, o pipeline está cheio

### Exercício 5
a) Penalidade = 2 ciclos (EX é o 3° estágio, já buscou 2 instruções)
b) "Sempre NT": acerta 1 vez (última iteração), erra 9 vezes
c) "Sempre T": acerta 9 vezes, erra 1 vez (última iteração)
d) "Sempre tomado" é melhor — loops geralmente repetem muitas vezes
e) "Sempre NT": 9 × 2 = 18 ciclos; "Sempre T": 1 × 2 = 2 ciclos

### Exercício 6
a) Ciclo = max(250, 350, 150, 200) = 350 ps
b) Latência = 4 × 350 = 1400 ps
c) Throughput = 1/350 ps ≈ 2,86 × 10⁹ instr/s ≈ 2,86 GIPS
d) Sem pipeline: 250+350+150+200 = 950 ps → Speedup = 950/350 = 2,71×

### Exercício 10
a) CPI = 1 + (0,25 × 0,30 × 1) + (0,20 × 0,10 × 3) = 1 + 0,075 + 0,06 = 1,135
b) Tempo = 2.000.000 × 1,135 / (2 × 10⁹) = 1,135 ms
c) Tempo ideal = 2.000.000 × 1 / (2 × 10⁹) = 1,0 ms
d) Perda = (1,135 - 1) / 1 = 13,5%
e) CPI = 1 + 0,075 + (0,20 × 0,03 × 3) = 1 + 0,075 + 0,018 = 1,093

### Exercício 12
a) Pipeline 5: 2 + 0,2 = 2,2 ns; Pipeline 10: 1 + 0,2 = 1,2 ns
b) CPI ideal = 1 para ambos
c) Pipeline 5: CPI = 1 + 0,20 × 0,10 × 2,5 = 1,05; Pipeline 10: CPI = 1 + 0,20 × 0,10 × 5 = 1,10
d) Pipeline 5: T = 10⁶ × 1,05 × 2,2 = 2,31 ms; Pipeline 10: T = 10⁶ × 1,10 × 1,2 = 1,32 ms → Pipeline 10 é mais rápido
e) Pipelines mais profundos: maior throughput, mas maior penalidade de hazards e maior consumo de energia

</details>
