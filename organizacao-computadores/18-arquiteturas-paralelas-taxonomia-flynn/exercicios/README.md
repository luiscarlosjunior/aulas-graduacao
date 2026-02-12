# 📝 Exercícios - Aula 18: Arquiteturas Paralelas e Taxonomia de Flynn

## Exercício 1 — Motivação para o Paralelismo

Responda as seguintes questões:

a) O que são os "três muros" (three walls) que limitaram o aumento da frequência dos processadores?
b) Por que simplesmente aumentar a frequência do clock não é mais viável?
c) Qual foi a solução adotada pela indústria a partir de ~2005?
d) Explique a relação entre consumo de energia e frequência de clock (P ∝ f × V²).

---

## Exercício 2 — Níveis de Paralelismo

Classifique cada cenário abaixo no nível de paralelismo correspondente (ILP, DLP, TLP ou Task-Level):

| Cenário | Nível de Paralelismo |
|---------|:-------------------:|
| a) Pipeline superescalar executando 4 instruções por ciclo | |
| b) GPU aplicando o mesmo filtro em milhões de pixels | |
| c) Navegador web em um núcleo e compilador em outro | |
| d) Instrução AVX somando 8 floats simultaneamente | |
| e) Cluster de 100 computadores processando dados de genoma | |
| f) Processador com execução fora de ordem | |
| g) 4 threads de um servidor web atendendo clientes | |

---

## Exercício 3 — Taxonomia de Flynn — Classificação

Classifique cada um dos seguintes sistemas/processadores segundo a Taxonomia de Flynn (SISD, SIMD, MISD, MIMD). Justifique cada resposta em uma frase.

a) Microcontrolador PIC16F877 (8 bits, sem pipeline)
b) Processador Intel Xeon com 32 núcleos
c) GPU NVIDIA com 5.000 CUDA cores
d) Sistema de controle de voo triplo-redundante (3 computadores processam os mesmos dados)
e) Processador MIPS R2000 (pipeline de 5 estágios, 1 núcleo)
f) Cluster Beowulf com 256 nós
g) Processador ARM Cortex-A78 executando instrução NEON (vetorial)
h) Computador quântico (considere a perspectiva de Flynn)

---

## Exercício 4 — Lei de Amdahl — Cálculos Básicos

Um programa tem tempo de execução de **500 segundos** em um processador sequencial. Medições mostram que **60%** do programa pode ser paralelizado.

a) Qual é o speedup máximo teórico (p → ∞)?
b) Calcule o speedup para p = 2, 4, 8, 16 e 32 processadores.
c) Calcule o tempo de execução para cada valor de p.
d) Calcule a eficiência para cada valor de p.
e) A partir de quantos processadores a eficiência cai abaixo de 50%?

---

## Exercício 5 — Lei de Amdahl — Análise Avançada

Considere um programa que leva 1000 segundos para executar. O programa possui 3 partes:

| Parte | Tempo | Paralelizável? |
|-------|:-----:|:--------------:|
| Inicialização | 50 s | Não |
| Processamento principal | 900 s | Sim |
| Finalização | 50 s | Não |

a) Qual a fração paralelizável (f)?
b) Qual o speedup com 10 processadores?
c) Qual o speedup máximo teórico?
d) Se você conseguir reduzir a inicialização e finalização para 10 s cada (mas mantendo os 900 s de processamento), qual seria o novo speedup máximo?
e) O que é mais efetivo: investir em mais processadores ou reduzir a parte sequencial?

---

## Exercício 6 — Memória Compartilhada vs Distribuída

Compare as duas arquiteturas respondendo:

a) Desenhe um diagrama simples de um sistema com 4 processadores usando memória compartilhada.
b) Desenhe um diagrama simples de um sistema com 4 processadores usando memória distribuída.
c) Liste 3 vantagens e 3 desvantagens de cada abordagem.
d) Para cada aplicação abaixo, indique qual arquitetura de memória é mais adequada e por quê:
   - Servidor web com 10.000 requisições simultâneas
   - Simulação climática global distribuída entre 1000 computadores
   - Banco de dados relacional com muitas consultas concorrentes
   - Treinamento de rede neural com modelo de 175 bilhões de parâmetros

---

## Exercício 7 — Multi-Core e SMT

a) Explique a diferença entre um processador com 4 núcleos físicos e um processador com 2 núcleos + Hyper-Threading (4 threads lógicas).
b) Um Intel Core i7 tem 8 núcleos com Hyper-Threading. Quantas threads lógicas ele possui?
c) Por que o ganho de desempenho do Hyper-Threading não é de 2× (duas threads por núcleo)?
d) Explique o conceito de "P-cores" e "E-cores" na arquitetura híbrida da Intel (12ª geração em diante).
e) Qual a vantagem de ter núcleos de eficiência (E-cores) além dos núcleos de performance (P-cores)?

---

## Exercício 8 — GPU Computing

a) Por que a GPU é mais adequada que a CPU para operações com vetores e matrizes grandes?
b) Cite 3 diferenças fundamentais entre a arquitetura de uma CPU e de uma GPU.
c) Explique por que o treinamento de redes neurais (Deep Learning) se beneficia enormemente de GPUs.
d) O que é GPGPU (General-Purpose Computing on Graphics Processing Units)?
e) Um programador precisa decidir entre processar dados em CPU ou GPU. Descreva um cenário onde a CPU seria melhor e um onde a GPU seria melhor.

---

## Exercício 9 — Lei de Amdahl com Dados Reais

Um cientista está otimizando um programa de simulação molecular. O perfil de execução é:

| Componente | % do Tempo | Paralelizável? |
|-----------|:----------:|:--------------:|
| Leitura de dados | 2% | Não |
| Cálculo de forças | 75% | Sim |
| Atualização de posições | 15% | Sim |
| Comunicação entre partículas | 5% | Parcialmente (50%) |
| Escrita de resultados | 3% | Não |

a) Calcule a fração total paralelizável (f).
b) Calcule o speedup com 16 processadores.
c) Calcule o speedup com 256 processadores.
d) Quantos processadores seriam necessários para um speedup de 10×?
e) Qual componente oferece o maior potencial de melhoria se fosse otimizado?

---

## Exercício 10 — Supercomputadores e Escalabilidade

a) Pesquise (ou use os dados da aula): qual é o supercomputador mais rápido do mundo atualmente? Quantos processadores ele possui?
b) Se um supercomputador tem 1.000.000 de núcleos e um programa tem 0,1% de parte sequencial, qual o speedup máximo pela Lei de Amdahl?
c) Calcule o speedup real com 1.000.000 de núcleos e f = 0,999.
d) Explique por que na prática os supercomputadores usam memória distribuída em vez de compartilhada.
e) O que é a lista TOP500 e como ela classifica os supercomputadores?

---

## Exercício 11 — Aplicações do Mundo Real

Para cada aplicação abaixo, identifique:
- A classificação Flynn mais apropriada
- Se usaria memória compartilhada ou distribuída
- O nível de paralelismo principal (ILP, DLP, TLP, Task)

| Aplicação | Flynn | Memória | Paralelismo |
|-----------|:-----:|:-------:|:-----------:|
| a) Edição de vídeo 4K em tempo real | | | |
| b) Servidor de banco de dados MySQL | | | |
| c) Treinamento do ChatGPT | | | |
| d) Jogo 3D com ray tracing | | | |
| e) Análise de DNA em cluster | | | |
| f) Arduino controlando sensor | | | |
| g) Mineração de criptomoedas | | | |
| h) Previsão do tempo global | | | |

---

## Exercício 12 — Projeto de Sistema Paralelo

Sua empresa precisa processar 10 TB de dados de log diariamente. Cada registro de log precisa ser:
1. Lido do disco (não paralelizável, 10% do tempo)
2. Parseado/Processado (paralelizável, 70% do tempo)
3. Indexado (paralelizável, 15% do tempo)
4. Gravado no banco (parcialmente paralelizável — 50%, 5% do tempo)

a) Calcule a fração total paralelizável do programa.
b) Usando a Lei de Amdahl, calcule o speedup com 8, 16, 32 e 64 processadores.
c) Quantos processadores você recomendaria? Justifique considerando o custo-benefício.
d) Que tipo de arquitetura (memória compartilhada ou distribuída) você escolheria?
e) Proponha uma melhoria na parte sequencial (leitura de disco) que aumentaria o speedup máximo.

---

## 🎯 Gabarito Resumido

<details>
<summary>Clique para ver as respostas</summary>

### Exercício 2
a) ILP, b) DLP, c) TLP, d) DLP, e) Task-Level, f) ILP, g) TLP

### Exercício 3
a) SISD, b) MIMD, c) SIMD, d) MISD, e) SISD (pipeline é ILP, não muda classificação Flynn), f) MIMD, g) SIMD (instrução NEON), h) Não se encaixa perfeitamente (discussão aberta)

### Exercício 4
a) Speedup_max = 1/(1-0,60) = 1/0,40 = 2,5×
b) p=2: 1,43×; p=4: 1,82×; p=8: 2,11×; p=16: 2,29×; p=32: 2,39×
c) p=2: 350s; p=4: 275s; p=8: 237,5s; p=16: 218,75s; p=32: 209,38s
d) p=2: 71,4%; p=4: 45,5%; p=8: 26,3%; p=16: 14,3%; p=32: 7,5%
e) Com p=4, a eficiência já cai abaixo de 50% (45,5%)

### Exercício 5
a) f = 900/1000 = 0,90
b) S = 1/(0,10 + 0,90/10) = 1/0,19 = 5,26×
c) S_max = 1/0,10 = 10×
d) f_novo = 900/920 ≈ 0,978 → S_max = 1/0,022 ≈ 46×
e) Reduzir a parte sequencial é **muito** mais efetivo

### Exercício 9
a) f = 0,75 + 0,15 + 0,05×0,50 = 0,925
b) S = 1/(0,075 + 0,925/16) = 1/(0,075 + 0,0578) = 1/0,1328 = 7,53×
c) S = 1/(0,075 + 0,925/256) = 1/(0,075 + 0,00361) = 1/0,07861 = 12,72×
d) Resolvendo 10 = 1/(0,075 + 0,925/p) → p ≈ 37 processadores
e) O cálculo de forças (75%) — paralelizá-lo melhor traz o maior ganho

### Exercício 10
b) S_max = 1/0,001 = 1000×
c) S = 1/(0,001 + 0,999/1000000) = 1/0,001000999 ≈ 999×

### Exercício 12
a) f = 0,70 + 0,15 + 0,05×0,50 = 0,875
b) p=8: 4,57×; p=16: 6,40×; p=32: 7,41×; p=64: 7,69×
c) ~16-32 processadores (ponto de retorno decrescente)

</details>
