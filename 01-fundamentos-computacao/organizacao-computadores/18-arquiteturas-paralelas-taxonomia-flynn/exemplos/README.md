# 🧪 Exemplos - Aula 18: Arquiteturas Paralelas e Taxonomia de Flynn

## Exemplo 1: Classificação pela Taxonomia de Flynn

### Classifique cada sistema na taxonomia de Flynn:

| Sistema | Classificação | Justificativa |
|---------|:------------:|---------------|
| Arduino (ATmega328) | **SISD** | Um processador, uma instrução por vez, um dado por vez |
| Intel Core i7-13700K | **MIMD** | 16 núcleos independentes, instruções e dados diferentes |
| NVIDIA RTX 4090 | **SIMD** | Milhares de cores executando a mesma operação em dados diferentes |
| Cluster Beowulf (100 PCs) | **MIMD** | Múltiplos computadores independentes conectados em rede |
| Processador vetorial Cray | **SIMD** | Uma instrução opera sobre vetores de dados |
| Sistema de voo redundante | **MISD** | Múltiplos computadores processam os mesmos dados de sensores |
| Smartphone Snapdragon 8 Gen 3 | **MIMD** | CPU multi-core (8 núcleos) com instruções independentes |
| Instrução AVX-512 no Intel | **SIMD** | Uma instrução soma 16 floats simultaneamente |

---

## Exemplo 2: Lei de Amdahl — Cálculo Passo a Passo

### Problema

Um programa leva **200 segundos** para executar em um processador. Análise de perfil mostra que **70%** do tempo é gasto em uma parte paralelizável. Calcule o speedup usando 2, 4, 8, 16 e 64 processadores.

### Dados

```
Tempo total = 200 s
f = 0,70 (fração paralelizável = 70%)
(1 - f) = 0,30 (fração sequencial = 30%)

Tempo sequencial = 0,30 × 200 = 60 s
Tempo paralelo = 0,70 × 200 = 140 s
```

### Cálculos

**Para p = 2:**
```
Speedup = 1 / [(1 - 0,70) + 0,70/2]
        = 1 / [0,30 + 0,35]
        = 1 / 0,65
        = 1,54×

Tempo = 200 / 1,54 = 130 s
Verificação: 60 + 140/2 = 60 + 70 = 130 s ✅
```

**Para p = 4:**
```
Speedup = 1 / [0,30 + 0,70/4]
        = 1 / [0,30 + 0,175]
        = 1 / 0,475
        = 2,11×

Tempo = 200 / 2,11 = 95 s
```

**Para p = 8:**
```
Speedup = 1 / [0,30 + 0,70/8]
        = 1 / [0,30 + 0,0875]
        = 1 / 0,3875
        = 2,58×

Tempo = 200 / 2,58 = 77,5 s
```

**Para p = 16:**
```
Speedup = 1 / [0,30 + 0,70/16]
        = 1 / [0,30 + 0,04375]
        = 1 / 0,34375
        = 2,91×

Tempo = 200 / 2,91 = 68,7 s
```

**Para p = 64:**
```
Speedup = 1 / [0,30 + 0,70/64]
        = 1 / [0,30 + 0,01094]
        = 1 / 0,31094
        = 3,22×

Tempo = 200 / 3,22 = 62,2 s
```

**Para p → ∞:**
```
Speedup_máx = 1 / (1 - f) = 1 / 0,30 = 3,33×
Tempo_mínimo = 60 s (somente a parte sequencial!)
```

### Tabela Resumo

| p | Tempo Paralelo | Tempo Total | Speedup | Eficiência (S/p) |
|:-:|:--------------:|:-----------:|:-------:|:-----------------:|
| 1 | 140 s | 200 s | 1,00× | 100% |
| 2 | 70 s | 130 s | 1,54× | 77% |
| 4 | 35 s | 95 s | 2,11× | 53% |
| 8 | 17,5 s | 77,5 s | 2,58× | 32% |
| 16 | 8,75 s | 68,75 s | 2,91× | 18% |
| 64 | 2,19 s | 62,19 s | 3,22× | 5% |
| ∞ | 0 s | 60 s | 3,33× | ~0% |

> 💡 **Observe:** A eficiência cai drasticamente com mais processadores! Com 64 processadores, a eficiência é apenas 5% — a maioria dos processadores fica ociosa esperando a parte sequencial.

---

## Exemplo 3: Lei de Amdahl — Quanto Paralelizar?

### Cenário

Sua equipe pode investir recursos para paralelizar diferentes porções de um programa:

| Opção | Fração Paralelizável | Custo de Implementação |
|-------|:-------------------:|:---------------------:|
| A | 50% | Baixo |
| B | 80% | Médio |
| C | 95% | Alto |
| D | 99% | Muito Alto |

### Speedup Máximo para Cada Opção (p → ∞)

```
Opção A: Speedup_max = 1/(1-0,50) = 1/0,50 = 2,00×
Opção B: Speedup_max = 1/(1-0,80) = 1/0,20 = 5,00×
Opção C: Speedup_max = 1/(1-0,95) = 1/0,05 = 20,00×
Opção D: Speedup_max = 1/(1-0,99) = 1/0,01 = 100,00×
```

### Speedup Realista com 8 Processadores

```
Opção A: S = 1/(0,50 + 0,50/8) = 1/0,5625 = 1,78×
Opção B: S = 1/(0,20 + 0,80/8) = 1/0,30   = 3,33×
Opção C: S = 1/(0,05 + 0,95/8) = 1/0,1688 = 5,93×
Opção D: S = 1/(0,01 + 0,99/8) = 1/0,1338 = 7,48×
```

> 🎯 **Conclusão:** Paralelizar mais do programa traz retornos cada vez maiores, mas o custo também aumenta. A decisão depende do custo-benefício.

---

## Exemplo 4: Comparação SISD vs SIMD

### Tarefa: Somar dois vetores de 1000 elementos

**SISD (processador sequencial):**

```
Para i de 0 até 999:
    C[i] = A[i] + B[i]

Total de operações de soma: 1000
Total de ciclos (1 soma/ciclo): ~1000 ciclos
```

**SIMD com largura 4 (ex: SSE):**

```
Para i de 0 até 999, passo 4:
    C[i..i+3] = A[i..i+3] + B[i..i+3]  (4 somas em paralelo)

Total de operações SIMD: 1000 / 4 = 250
Total de ciclos (~1 operação SIMD/ciclo): ~250 ciclos
Speedup: 1000 / 250 = 4×
```

**SIMD com largura 8 (ex: AVX-256):**

```
Para i de 0 até 999, passo 8:
    C[i..i+7] = A[i..i+7] + B[i..i+7]  (8 somas em paralelo)

Total de operações SIMD: 1000 / 8 = 125
Total de ciclos: ~125 ciclos
Speedup: 1000 / 125 = 8×
```

**SIMD com largura 16 (ex: AVX-512):**

```
Para i de 0 até 999, passo 16:
    C[i..i+15] = A[i..i+15] + B[i..i+15]  (16 somas em paralelo)

Total de operações SIMD: 1000 / 16 ≈ 63
Total de ciclos: ~63 ciclos
Speedup: 1000 / 63 ≈ 16×
```

**GPU (milhares de cores SIMD):**

```
Lançar 1000 threads, cada uma faz:
    C[thread_id] = A[thread_id] + B[thread_id]

Se a GPU tem 1000+ cores: todas executam em ~1 ciclo
Speedup teórico: ~1000× (limitado por largura de banda de memória)
```

---

## Exemplo 5: Memória Compartilhada vs Distribuída

### Problema: Somar 1.000.000 de números usando 4 processadores

**Memória Compartilhada (OpenMP):**

```
// Pseudocódigo com memória compartilhada
int soma_total = 0;           // Variável compartilhada
int vetor[1000000];           // Dados compartilhados

// Cada processador soma sua parte
#parallel for reduction(+:soma_total)
for (int i = 0; i < 1000000; i++)
    soma_total += vetor[i];

// Resultado já está em soma_total

Comunicação: via memória compartilhada (muito rápido)
Programação: simples (uma diretiva)
```

**Memória Distribuída (MPI):**

```
// Pseudocódigo com troca de mensagens
int meu_rank = obter_rank();           // ID do processador (0-3)
int pedaco = 1000000 / 4;             // 250.000 elementos por processador
int meus_dados[pedaco];               // Memória LOCAL

// Passo 1: Distribuir dados (processador 0 envia)
if (meu_rank == 0)
    para cada processador p:
        ENVIAR(vetor[p*pedaco .. (p+1)*pedaco-1], destino=p)
else
    RECEBER(meus_dados, origem=0)

// Passo 2: Cada processador soma sua parte LOCAL
int soma_local = 0;
for (int i = 0; i < pedaco; i++)
    soma_local += meus_dados[i];

// Passo 3: Coletar resultados (todos enviam para o processador 0)
if (meu_rank == 0)
    soma_total = soma_local;
    para cada processador p (1 a 3):
        soma_total += RECEBER(origem=p)
else
    ENVIAR(soma_local, destino=0)

Comunicação: via rede (mais lento)
Programação: complexa (enviar/receber explícito)
```

**Comparação para este problema:**

| Aspecto | Memória Compartilhada | Memória Distribuída |
|---------|:--------------------:|:-------------------:|
| Comunicação | ~10 ns (via cache) | ~1 µs (via rede) |
| Linhas de código | ~5 | ~20 |
| Overhead | Baixo | Alto (distribuir + coletar) |
| Escalabilidade | 4 processadores (bom) | 4 processadores (overkill) |

---

## Exemplo 6: Eficiência e Escalabilidade

### Métricas de Desempenho Paralelo

**Definições:**

```
Speedup(p) = T(1) / T(p)

Eficiência(p) = Speedup(p) / p

Onde:
  T(1) = tempo de execução com 1 processador
  T(p) = tempo de execução com p processadores
```

### Cenário: Programa com T(1) = 1000 s

| p | T(p) medido | Speedup | Eficiência | Classificação |
|:-:|:-----------:|:-------:|:----------:|:-------------:|
| 1 | 1000 s | 1,00× | 100% | Referência |
| 2 | 520 s | 1,92× | 96% | Excelente |
| 4 | 280 s | 3,57× | 89% | Muito bom |
| 8 | 160 s | 6,25× | 78% | Bom |
| 16 | 100 s | 10,0× | 63% | Razoável |
| 32 | 75 s | 13,3× | 42% | Moderado |
| 64 | 65 s | 15,4× | 24% | Baixo |
| 128 | 62 s | 16,1× | 13% | Muito baixo |

> 📝 **Observação:** A eficiência cai com mais processadores. Existe um ponto de "retorno decrescente" onde adicionar mais processadores não compensa.

### Estimativa da fração sequencial (pela Lei de Amdahl):

```
Se com 128 processadores o speedup é 16,1:

16,1 = 1 / [(1-f) + f/128]
(1-f) + f/128 = 1/16,1 = 0,0621
(1-f) + 0,0078f = 0,0621
1 - f + 0,0078f = 0,0621
1 - 0,9922f = 0,0621
0,9922f = 0,9379
f = 0,945

Ou seja, ~94,5% do programa é paralelizável e ~5,5% é sequencial.
Speedup máximo teórico = 1/0,055 ≈ 18,2×
```

---

## Exemplo 7: Arquiteturas de Processadores Reais

### Intel Core i9-13900K (Classificação Flynn)

```
┌──────────────────────────────────────────────────────┐
│         Intel Core i9-13900K                          │
│                                                      │
│  Nível de instrução (ILP):     SISD por núcleo       │
│  ├── Pipeline superescalar                           │
│  ├── Execução fora de ordem                          │
│  └── Predição de desvio                              │
│                                                      │
│  Nível de dados (SIMD):                              │
│  ├── AVX-512 (16 floats em paralelo)   → SIMD       │
│  └── SSE4.2 (4 floats em paralelo)     → SIMD       │
│                                                      │
│  Nível de thread (TLP):                              │
│  ├── 8 P-cores + 16 E-cores = 24 núcleos            │
│  ├── Hyper-threading nos P-cores                     │
│  └── 32 threads simultâneas              → MIMD      │
│                                                      │
│  Classificação geral: MIMD                           │
│  (com capacidades SIMD em cada núcleo)               │
└──────────────────────────────────────────────────────┘
```

### NVIDIA RTX 4090 (Classificação Flynn)

```
┌──────────────────────────────────────────────────────┐
│         NVIDIA RTX 4090                               │
│                                                      │
│  Nível macro:                                        │
│  ├── 128 Streaming Multiprocessors (SMs)             │
│  ├── Cada SM tem 128 CUDA cores                      │
│  └── Total: 16.384 CUDA cores                        │
│                                                      │
│  Dentro de cada SM:  SIMD                            │
│  ├── 32 threads executam a mesma instrução (warp)    │
│  └── Mesma instrução, dados diferentes               │
│                                                      │
│  Entre SMs:  MIMD                                    │
│  ├── SMs podem executar kernels diferentes           │
│  └── Warps diferentes podem ter instruções diferentes│
│                                                      │
│  Classificação: SIMD (primariamente)                 │
│  Modelo de programação: SPMD                         │
│  (Single Program, Multiple Data)                     │
└──────────────────────────────────────────────────────┘
```

---

## Exemplo 8: Speedup Real vs Ideal

### Cenário: Renderização de imagem em 4K

```
Etapas do programa:
1. Carregar texturas           (5% do tempo)  → Sequencial
2. Configurar cena             (3% do tempo)  → Sequencial
3. Renderizar pixels           (85% do tempo) → Paralelo
4. Aplicar pós-processamento   (5% do tempo)  → Paralelo
5. Salvar imagem               (2% do tempo)  → Sequencial

f = 0,85 + 0,05 = 0,90 (90% paralelizável)
(1-f) = 0,10 (10% sequencial)
```

**Speedup para diferentes GPUs:**

| GPU | Cores CUDA | p efetivo* | Speedup (Amdahl) |
|-----|:----------:|:----------:|:-----------------:|
| GTX 1060 | 1.280 | 160 | 9,38× |
| RTX 3070 | 5.888 | 736 | 9,87× |
| RTX 4090 | 16.384 | 2.048 | 9,95× |

*p efetivo considerando eficiência real (overhead, ocupação, etc.)

> 📝 **Mesmo triplicando os cores de 5.888 para 16.384, o speedup quase não muda (9,87→ 9,95).** A fração sequencial de 10% limita o ganho a no máximo 10×.
