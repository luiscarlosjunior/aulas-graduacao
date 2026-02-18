# 🔄 Aula 18 - Introdução às Arquiteturas Paralelas e Taxonomia de Flynn

## 📋 Objetivos de Aprendizagem

Ao final desta aula, você será capaz de:

- ✅ Compreender os limites da computação sequencial e por que o paralelismo é necessário
- ✅ Identificar os diferentes níveis de paralelismo (instrução, dados, thread, tarefa)
- ✅ Classificar arquiteturas segundo a Taxonomia de Flynn (SISD, SIMD, MISD, MIMD)
- ✅ Diferenciar memória compartilhada e memória distribuída
- ✅ Entender os conceitos de processadores multi-core e GPU computing
- ✅ Aplicar a Lei de Amdahl para calcular o speedup de sistemas paralelos
- ✅ Relacionar os conceitos com aplicações reais: supercomputadores, nuvem e GPUs

---

## 📚 Conteúdo

### 1. Por que Paralelismo?

#### 1.1 Os Limites do Processador Sequencial

Durante décadas, o desempenho dos processadores cresceu seguindo a **Lei de Moore** — o número de transistores dobrava a cada ~18 meses. Contudo, por volta de 2005, a indústria atingiu barreiras físicas fundamentais:

```
┌─────────────────────────────────────────────────────────┐
│              OS TRÊS MUROS (Three Walls)                 │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  🧱 MURO DE POTÊNCIA (Power Wall)                       │
│     • Aumentar a frequência → aumento exponencial       │
│       no consumo de energia e dissipação de calor       │
│     • P ∝ f × V² (potência cresce com frequência       │
│       e quadrado da tensão)                             │
│     • CPUs atingiram ~4-5 GHz e estagnaram              │
│                                                         │
│  🧱 MURO DE MEMÓRIA (Memory Wall)                       │
│     • Velocidade do processador cresceu muito mais      │
│       rápido que a velocidade da memória                │
│     • Gap processador-memória cada vez maior            │
│     • Caches ajudam mas não resolvem totalmente         │
│                                                         │
│  🧱 MURO DE ILP (Instruction-Level Parallelism Wall)    │
│     • Limite prático de paralelismo de instruções       │
│     • Dependências entre instruções limitam ILP         │
│     • Retornos decrescentes em pipelines profundos      │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

#### 1.2 A Solução: Mais Núcleos, Não Mais Frequência

```
        Evolução da Performance dos Processadores

  Performance
     ▲
     │                              ┌─ Multi-core
     │                        ●●●●●●●●●●●●●
     │                   ●●●●●
     │              ●●●●●     ← Frequência estagnou (~2005)
     │         ●●●●
     │     ●●●●
     │  ●●●
     │●●
     ●
     └──────────────────────────────────────────► Ano
     1990    1995    2000    2005    2010    2015    2020

     Até 2005: mais frequência = mais performance
     Após 2005: mais núcleos = mais performance
```

> 💡 **Mudança de paradigma:** Em vez de processar instruções mais rápido, passamos a processar **mais instruções simultaneamente** em múltiplos núcleos.

---

### 2. Tipos de Paralelismo

O paralelismo pode ser explorado em diferentes **níveis de granularidade**:

#### 2.1 Tabela dos Níveis de Paralelismo

| Nível | Nome | Descrição | Exemplo |
|-------|------|-----------|---------|
| 🔬 | **ILP** (Instruction-Level Parallelism) | Executa múltiplas instruções simultaneamente dentro de um núcleo | Pipeline, superescalar, VLIW |
| 📊 | **DLP** (Data-Level Parallelism) | Aplica a mesma operação a múltiplos dados simultaneamente | SIMD, vetorização, GPU |
| 🧵 | **TLP** (Thread-Level Parallelism) | Executa múltiplas threads em paralelo | Multi-core, SMT/Hyper-threading |
| 📋 | **Task-Level Parallelism** | Executa tarefas diferentes simultaneamente | Cluster, computação distribuída |

#### 2.2 Paralelismo no Nível de Instrução (ILP)

```
Pipeline Superescalar: executa 2+ instruções por ciclo

Ciclo:    1       2       3       4       5
        ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐
Via 1:  │IF I1│ │ID I1│ │EX I1│ │ME I1│ │WB I1│
        │IF I2│ │ID I2│ │EX I2│ │ME I2│ │WB I2│
        └─────┘ └─────┘ └─────┘ └─────┘ └─────┘
                ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐
Via 2:          │IF I3│ │ID I3│ │EX I3│ │ME I3│ │...
                │IF I4│ │ID I4│ │EX I4│ │ME I4│ │...
                └─────┘ └─────┘ └─────┘ └─────┘

→ 2 instruções completadas por ciclo!
```

#### 2.3 Paralelismo no Nível de Dados (DLP)

```
Operação escalar:           Operação SIMD (vetorial):

A[0] = B[0] + C[0]         A[0..3] = B[0..3] + C[0..3]
A[1] = B[1] + C[1]         (4 somas em paralelo!)
A[2] = B[2] + C[2]
A[3] = B[3] + C[3]

4 operações sequenciais     1 operação vetorial
4 ciclos                    1 ciclo
```

#### 2.4 Paralelismo no Nível de Thread (TLP)

```
┌──────────────────────────────────────────────┐
│         PROCESSADOR MULTI-CORE               │
│                                              │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │  Núcleo 0│  │  Núcleo 1│  │  Núcleo 2│   │
│  │          │  │          │  │          │   │
│  │ Thread A │  │ Thread B │  │ Thread C │   │
│  │(navegador│  │ (editor  │  │ (compila-│   │
│  │  web)    │  │ de texto)│  │   ção)   │   │
│  └──────────┘  └──────────┘  └──────────┘   │
│                                              │
│            Cache L3 Compartilhada            │
└──────────────────────────────────────────────┘
```

#### 2.5 Paralelismo no Nível de Tarefa

```
┌──────┐    ┌──────┐    ┌──────┐    ┌──────┐
│ Nó 1 │    │ Nó 2 │    │ Nó 3 │    │ Nó 4 │
│Tarefa│    │Tarefa│    │Tarefa│    │Tarefa│
│  A   │    │  B   │    │  C   │    │  D   │
└──┬───┘    └──┬───┘    └──┬───┘    └──┬───┘
   │           │           │           │
   └───────────┴─────┬─────┴───────────┘
                     │
              ┌──────┴──────┐
              │    REDE     │
              │(Interconnect)│
              └─────────────┘

Exemplo: Cluster computacional processando
diferentes partes de um problema
```

---

### 3. Taxonomia de Flynn

Em 1966, **Michael J. Flynn** propôs uma classificação de arquiteturas de computadores baseada em dois critérios:
- Número de **fluxos de instruções** (Instruction streams)
- Número de **fluxos de dados** (Data streams)

#### 3.1 Visão Geral

```
                    ┌─────────────────────────────────────────┐
                    │         TAXONOMIA DE FLYNN              │
                    ├──────────────────┬──────────────────────┤
                    │  Dados Únicos    │  Dados Múltiplos     │
                    │  (Single Data)   │  (Multiple Data)     │
┌───────────────────┼──────────────────┼──────────────────────┤
│ Instrução Única   │                  │                      │
│ (Single           │     SISD         │      SIMD            │
│  Instruction)     │                  │                      │
├───────────────────┼──────────────────┼──────────────────────┤
│ Instruções        │                  │                      │
│ Múltiplas         │     MISD         │      MIMD            │
│ (Multiple         │                  │                      │
│  Instruction)     │                  │                      │
└───────────────────┴──────────────────┴──────────────────────┘
```

---

#### 3.2 SISD — Single Instruction, Single Data

**Uma instrução opera sobre um dado por vez.**

Esta é a arquitetura tradicional **Von Neumann** — um único processador executa uma sequência de instruções sobre um fluxo de dados.

```
┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│  Unidade de  │────►│  Processador │────►│   Memória    │
│  Controle    │     │   (1 ULA)    │     │   (Dados)    │
│ (1 instrução)│     │              │     │  (1 fluxo)   │
└──────────────┘     └──────────────┘     └──────────────┘

   1 Instrução          1 Operação          1 Dado
```

**Características:**
- Um fluxo de instruções
- Um fluxo de dados
- Execução sequencial (pode ter pipeline)
- Modelo clássico de computação

**Exemplos:**
- Processadores antigos (Intel 8086, Motorola 68000)
- Microcontroladores simples (Arduino com ATmega328)

**Execução:**
```
Tempo:    t1      t2      t3      t4
Instr:   ADD     SUB     MUL     DIV
Dados:   A+B     C-D     E×F     G/H

→ Uma operação por vez, sobre um par de dados por vez
```

---

#### 3.3 SIMD — Single Instruction, Multiple Data

**Uma instrução opera sobre múltiplos dados simultaneamente.**

```
                 ┌───────────────┐
                 │  Unidade de   │
                 │   Controle    │
                 │(1 instrução:  │
                 │    ADD)       │
                 └──────┬────────┘
                        │ (mesma instrução para todos)
            ┌───────────┼───────────┐
            ▼           ▼           ▼
      ┌──────────┐┌──────────┐┌──────────┐
      │  ULA 0   ││  ULA 1   ││  ULA 2   │
      │ A[0]+B[0]││ A[1]+B[1]││ A[2]+B[2]│
      └──────────┘└──────────┘└──────────┘
            │           │           │
            ▼           ▼           ▼
      ┌──────────┐┌──────────┐┌──────────┐
      │ Dado 0   ││ Dado 1   ││ Dado 2   │
      └──────────┘└──────────┘└──────────┘
```

**Características:**
- Um fluxo de instruções
- Múltiplos fluxos de dados
- Todos os processadores executam a **mesma** instrução
- Ideal para operações regulares em vetores e matrizes

**Exemplos reais:**

| Tecnologia | Descrição | Largura |
|-----------|-----------|---------|
| **SSE** (Intel) | Streaming SIMD Extensions | 128 bits (4 floats) |
| **AVX** (Intel) | Advanced Vector Extensions | 256 bits (8 floats) |
| **AVX-512** (Intel) | AVX com 512 bits | 512 bits (16 floats) |
| **NEON** (ARM) | ARM SIMD | 128 bits (4 floats) |
| **GPU** (NVIDIA/AMD) | Milhares de cores SIMD | Milhares de threads |

**Execução SIMD:**
```
Instrução:  ADD (soma vetorial)

         ULA0       ULA1       ULA2       ULA3
         A[0]+B[0]  A[1]+B[1]  A[2]+B[2]  A[3]+B[3]
          = C[0]     = C[1]     = C[2]     = C[3]

→ 4 somas em um único ciclo!
```

---

#### 3.4 MISD — Multiple Instruction, Single Data

**Múltiplas instruções operam sobre o mesmo dado.**

```
      ┌──────────────┐
      │    Dado      │
      │    (1 fluxo) │
      └──────┬───────┘
             │ (mesmo dado para todos)
    ┌────────┼────────┐
    ▼        ▼        ▼
┌────────┐┌────────┐┌────────┐
│Instr. A││Instr. B││Instr. C│
│ (ADD)  ││ (MUL)  ││ (CMP)  │
└────────┘└────────┘└────────┘
```

**Características:**
- Múltiplos fluxos de instruções
- Um fluxo de dados
- Categoria **rara** na prática
- Mais teórica que prática

**Aplicações (raras):**
- **Tolerância a falhas:** Múltiplos processadores executam o mesmo cálculo com algoritmos diferentes e comparam resultados (exemplo: sistemas de controle de voo)
- **Processamento sistólico:** Arrays sistólicos em aplicações específicas
- **Pipeline de funções:** Cada processador aplica uma transformação diferente ao mesmo fluxo de dados

> ⚠️ **MISD é a categoria menos comum da taxonomia de Flynn.** A maioria dos autores considera que existem pouquíssimos exemplos práticos.

---

#### 3.5 MIMD — Multiple Instruction, Multiple Data

**Múltiplas instruções operam sobre múltiplos dados independentemente.**

```
┌──────────────┐   ┌──────────────┐   ┌──────────────┐
│ Controlador 1│   │ Controlador 2│   │ Controlador 3│
│ (Instrução A)│   │ (Instrução B)│   │ (Instrução C)│
└──────┬───────┘   └──────┬───────┘   └──────┬───────┘
       │                  │                  │
       ▼                  ▼                  ▼
┌──────────────┐   ┌──────────────┐   ┌──────────────┐
│ Processador 1│   │ Processador 2│   │ Processador 3│
│  Dados X     │   │  Dados Y     │   │  Dados Z     │
│  ADD X1, X2  │   │  MUL Y1, Y2  │   │  SUB Z1, Z2  │
└──────────────┘   └──────────────┘   └──────────────┘
```

**Características:**
- Múltiplos fluxos de instruções
- Múltiplos fluxos de dados
- Cada processador é **independente**
- Arquitetura paralela mais **flexível e poderosa**
- Categoria mais **comum** em computação moderna

**Subdivisões do MIMD:**

```
                    MIMD
                     │
          ┌──────────┴──────────┐
          │                     │
    ┌─────┴──────┐      ┌──────┴──────┐
    │  MEMÓRIA   │      │  MEMÓRIA    │
    │COMPARTILHADA│     │ DISTRIBUÍDA │
    │(Shared Mem)│      │(Distributed)│
    └─────┬──────┘      └──────┬──────┘
          │                    │
    ┌─────┴──────┐      ┌─────┴──────┐
    │ UMA / NUMA │      │  Clusters  │
    │Multi-core  │      │  Grids     │
    │  SMP       │      │  MPP       │
    └────────────┘      └────────────┘
```

**Exemplos:**
- Processadores multi-core (Intel Core i7, AMD Ryzen)
- Servidores multiprocessadores
- Clusters de computadores
- Supercomputadores
- Computação em nuvem

---

#### 3.6 Resumo da Taxonomia de Flynn

| Categoria | Instrução | Dados | Exemplo Real | Aplicação |
|-----------|:---------:|:-----:|-------------|-----------|
| **SISD** | 1 | 1 | Microcontrolador | Controle simples |
| **SIMD** | 1 | N | GPU, SSE/AVX | Gráficos, IA, vetores |
| **MISD** | N | 1 | (raro) Sist. tolerante a falhas | Aviação, segurança |
| **MIMD** | N | N | Multi-core, cluster | Servidores, HPC |

---

### 4. Memória Compartilhada vs Memória Distribuída

#### 4.1 Memória Compartilhada (Shared Memory)

Todos os processadores acessam o **mesmo espaço de memória**.

```
┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐
│Processador│  │Processador│  │Processador│  │Processador│
│    P0    │  │    P1    │  │    P2    │  │    P3    │
└─────┬────┘  └─────┬────┘  └─────┬────┘  └─────┬────┘
      │             │             │             │
      └─────────────┴──────┬──────┴─────────────┘
                           │
                    ┌──────┴──────┐
                    │  BARRAMENTO │
                    │  / SWITCH   │
                    └──────┬──────┘
                           │
                    ┌──────┴──────┐
                    │   MEMÓRIA   │
                    │COMPARTILHADA│
                    └─────────────┘
```

**Tipos de memória compartilhada:**

| Tipo | Nome | Descrição | Tempo de Acesso |
|------|------|-----------|----------------|
| **UMA** | Uniform Memory Access | Todos os processadores acessam a memória com o mesmo tempo | Uniforme |
| **NUMA** | Non-Uniform Memory Access | Cada processador tem memória "local" mais rápida e pode acessar memória "remota" mais lentamente | Variável |

**Exemplo NUMA:**

```
┌──────────────────┐     ┌──────────────────┐
│    Nó 0          │     │    Nó 1          │
│ ┌────┐  ┌─────┐  │     │ ┌────┐  ┌─────┐  │
│ │ P0 │  │Mem 0│  │◄───►│ │ P1 │  │Mem 1│  │
│ └────┘  └─────┘  │     │ └────┘  └─────┘  │
│                  │     │                  │
│ Acesso local:    │     │ Acesso local:    │
│ rápido (50ns)    │     │ rápido (50ns)    │
│ Acesso remoto:   │     │ Acesso remoto:   │
│ lento (100ns)    │     │ lento (100ns)    │
└──────────────────┘     └──────────────────┘
```

**Vantagens:**
- ✅ Comunicação rápida entre processadores (via memória)
- ✅ Programação mais simples (variáveis compartilhadas)
- ✅ Ideal para threads cooperativas

**Desvantagens:**
- ❌ Escalabilidade limitada (contenção no barramento)
- ❌ Problema de coerência de cache
- ❌ Necessidade de sincronização (locks, semáforos)

---

#### 4.2 Memória Distribuída (Distributed Memory)

Cada processador possui sua **própria memória local**. Comunicação via **troca de mensagens** pela rede.

```
┌─────────────┐  ┌─────────────┐  ┌─────────────┐
│ Processador │  │ Processador │  │ Processador │
│     P0      │  │     P1      │  │     P2      │
│  ┌───────┐  │  │  ┌───────┐  │  │  ┌───────┐  │
│  │Mem. 0 │  │  │  │Mem. 1 │  │  │  │Mem. 2 │  │
│  └───────┘  │  │  └───────┘  │  │  └───────┘  │
└──────┬──────┘  └──────┬──────┘  └──────┬──────┘
       │                │                │
       └────────────────┼────────────────┘
                        │
                 ┌──────┴──────┐
                 │    REDE     │
                 │(troca de    │
                 │ mensagens)  │
                 └─────────────┘
```

**Vantagens:**
- ✅ Alta escalabilidade (milhares de nós)
- ✅ Sem problemas de coerência de cache
- ✅ Cada nó é independente

**Desvantagens:**
- ❌ Comunicação mais lenta (pela rede)
- ❌ Programação mais complexa (MPI, troca de mensagens)
- ❌ Balanceamento de carga mais difícil

#### 4.3 Comparação Direta

| Aspecto | Memória Compartilhada | Memória Distribuída |
|---------|:--------------------:|:-------------------:|
| Comunicação | Via memória | Via rede (mensagens) |
| Escalabilidade | Limitada (~64-128 cores) | Alta (milhares de nós) |
| Programação | Mais fácil (OpenMP, threads) | Mais complexa (MPI) |
| Custo | Menor (um computador) | Maior (vários computadores) |
| Latência | Baixa (nanosegundos) | Alta (microssegundos) |
| Coerência de cache | Necessária | Não se aplica |
| Exemplos | Multi-core, SMP | Clusters, supercomputadores |

---

### 5. Processadores Multi-Core

#### 5.1 Estrutura de um Processador Multi-Core

```
┌─────────────────────────────────────────────────────────────────┐
│                    PROCESSADOR MULTI-CORE                       │
│                                                                 │
│  ┌───────────────┐  ┌───────────────┐  ┌───────────────┐       │
│  │   Núcleo 0    │  │   Núcleo 1    │  │   Núcleo 2    │  ...  │
│  │ ┌───────────┐ │  │ ┌───────────┐ │  │ ┌───────────┐ │       │
│  │ │  Pipeline  │ │  │ │  Pipeline  │ │  │ │  Pipeline  │ │       │
│  │ │  (ULA,FPU) │ │  │ │  (ULA,FPU) │ │  │ │  (ULA,FPU) │ │       │
│  │ └───────────┘ │  │ └───────────┘ │  │ └───────────┘ │       │
│  │ ┌────┐ ┌────┐ │  │ ┌────┐ ┌────┐ │  │ ┌────┐ ┌────┐ │       │
│  │ │L1-I│ │L1-D│ │  │ │L1-I│ │L1-D│ │  │ │L1-I│ │L1-D│ │       │
│  │ └────┘ └────┘ │  │ └────┘ └────┘ │  │ └────┘ └────┘ │       │
│  │   ┌────────┐  │  │   ┌────────┐  │  │   ┌────────┐  │       │
│  │   │Cache L2│  │  │   │Cache L2│  │  │   │Cache L2│  │       │
│  │   └────────┘  │  │   └────────┘  │  │   └────────┘  │       │
│  └───────────────┘  └───────────────┘  └───────────────┘       │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │                    Cache L3 (Compartilhada)             │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │              Controlador de Memória                      │    │
│  └─────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────┘
```

#### 5.2 Evolução dos Processadores Multi-Core

| Ano | Processador | Núcleos | Observação |
|-----|------------|:-------:|-----------|
| 2005 | Intel Pentium D | 2 | Primeiro dual-core Intel para desktop |
| 2006 | Intel Core 2 Quad | 4 | Primeiro quad-core |
| 2010 | Intel Core i7-980X | 6 | Hexa-core para entusiastas |
| 2017 | AMD Ryzen Threadripper | 16 | Revolução multi-core AMD |
| 2019 | AMD Ryzen 9 3950X | 16 | 16 cores para desktop mainstream |
| 2022 | Intel Core i9-13900K | 24 | Arquitetura híbrida (P-cores + E-cores) |
| 2023 | AMD EPYC 9654 | 96 | Servidor com 96 cores |
| 2024 | Apple M4 Max | 16 | ARM multi-core para notebooks |

#### 5.3 Hyper-Threading / SMT

**SMT (Simultaneous Multithreading):** Cada núcleo físico pode executar **2 ou mais threads** simultaneamente, compartilhando recursos do núcleo.

```
┌──────────────────────────────┐
│   Núcleo Físico com SMT      │
│                              │
│  ┌───────────┐ ┌───────────┐ │
│  │ Thread 0  │ │ Thread 1  │ │  ← 2 threads lógicas
│  │(registr.) │ │(registr.) │ │
│  └─────┬─────┘ └─────┬─────┘ │
│        │              │      │
│        └──────┬───────┘      │
│               ▼              │
│        ┌──────────────┐      │
│        │  Recursos    │      │   ← Compartilhados
│        │ compartilhados│     │
│        │ (ULA, Cache,  │     │
│        │  Pipeline)   │      │
│        └──────────────┘      │
└──────────────────────────────┘

Exemplo: Intel i7 com 8 núcleos + HT = 16 threads lógicas
```

---

### 6. GPU Computing

#### 6.1 GPU: Milhares de Núcleos Simples

A **GPU (Graphics Processing Unit)** é um processador massivamente paralelo, projetado originalmente para gráficos, mas hoje amplamente usado para computação de propósito geral (**GPGPU**).

```
┌──────────────────────────────────────────────────┐
│     CPU vs GPU: Diferença de Filosofia           │
│                                                  │
│  CPU (poucos núcleos complexos):                 │
│  ┌──────────┐ ┌──────────┐                       │
│  │ Núcleo 0 │ │ Núcleo 1 │  → 4-16 núcleos      │
│  │ (potente) │ │ (potente) │  → Alta performance  │
│  │ L1 + L2  │ │ L1 + L2  │     por núcleo       │
│  └──────────┘ └──────────┘  → Bom para tarefas   │
│                                sequenciais       │
│                                                  │
│  GPU (muitos núcleos simples):                   │
│  ┌──┐┌──┐┌──┐┌──┐┌──┐┌──┐┌──┐┌──┐               │
│  │c0││c1││c2││c3││c4││c5││c6││c7│               │
│  └──┘└──┘└──┘└──┘└──┘└──┘└──┘└──┘               │
│  ┌──┐┌──┐┌──┐┌──┐┌──┐┌──┐┌──┐┌──┐  → Milhares   │
│  │c8││c9││..││..││..││..││..││..│  → Simples     │
│  └──┘└──┘└──┘└──┘└──┘└──┘└──┘└──┘  → Operações   │
│  ... (milhares de cores)      paralelas em dados │
└──────────────────────────────────────────────────┘
```

#### 6.2 CPU vs GPU — Comparação

| Aspecto | CPU | GPU |
|---------|-----|-----|
| Núcleos | 4-128 (complexos) | 1.000-16.000+ (simples) |
| Clock | 3-5 GHz | 1-2 GHz |
| Cache | Grande (MB) | Pequena (KB por SM) |
| Controle | Sofisticado | Simples |
| Melhor para | Tarefas sequenciais, lógica complexa | Tarefas paralelas massivas, dados regulares |
| Programação | C, Java, Python | CUDA, OpenCL, Metal |
| Memória | DDR5 (~50 GB/s) | GDDR6/HBM (~1-3 TB/s) |
| Potência | 65-250W | 150-450W |

#### 6.3 Aplicações de GPU Computing

```
┌────────────────────────────────────────────────────┐
│           APLICAÇÕES DE GPU COMPUTING               │
├────────────────────────────────────────────────────┤
│                                                    │
│  🎮 Jogos e Gráficos 3D                            │
│     Renderização em tempo real                     │
│                                                    │
│  🤖 Inteligência Artificial / Deep Learning        │
│     Treinamento de redes neurais                   │
│                                                    │
│  🔬 Computação Científica                          │
│     Simulações, dinâmica molecular                 │
│                                                    │
│  ₿ Criptografia / Blockchain                      │
│     Mineração de criptomoedas                      │
│                                                    │
│  🖼️ Processamento de Imagens/Vídeo                 │
│     Filtros, codificação, edição                   │
│                                                    │
│  📊 Big Data / Analytics                           │
│     Processamento massivo de dados                 │
│                                                    │
└────────────────────────────────────────────────────┘
```

---

### 7. Lei de Amdahl

#### 7.1 Definição

A **Lei de Amdahl** estabelece o **speedup máximo** que pode ser obtido ao paralelizar uma parte de um programa, considerando que sempre existe uma fração **sequencial** que não pode ser paralelizada.

```
                    1
Speedup = ──────────────────────
            (1 - f) + f/p

Onde:
  f = fração do programa que pode ser paralelizada (0 ≤ f ≤ 1)
  p = número de processadores
  (1 - f) = fração sequencial (não paralelizável)
```

#### 7.2 Interpretação Gráfica

```
Programa original (sequencial):

[=====SEQUENCIAL=====|===============PARALELO================]
     (1 - f)                        f

Com p processadores:

[=====SEQUENCIAL=====|====P1====]
                     |====P2====|
                     |====P3====|
                     |====P4====|

A parte sequencial NÃO muda!
A parte paralela é dividida entre p processadores.
```

#### 7.3 Exemplo de Cálculo

**Problema:** Um programa leva 100 segundos para executar. 80% do código pode ser paralelizado. Calcule o speedup para 1, 2, 4, 8, 16 e infinitos processadores.

| Processadores (p) | Parte Seq (20s) | Parte Par (80s/p) | Tempo Total | Speedup |
|:-----------------:|:---------------:|:-----------------:|:-----------:|:-------:|
| 1 | 20 s | 80 s | 100 s | 1,00× |
| 2 | 20 s | 40 s | 60 s | 1,67× |
| 4 | 20 s | 20 s | 40 s | 2,50× |
| 8 | 20 s | 10 s | 30 s | 3,33× |
| 16 | 20 s | 5 s | 25 s | 4,00× |
| 64 | 20 s | 1,25 s | 21,25 s | 4,71× |
| ∞ | 20 s | 0 s | 20 s | **5,00×** |

> 🎯 **Speedup máximo (p → ∞) = 1/(1-f) = 1/0,2 = 5×**

> ⚠️ **Conclusão fundamental:** Mesmo com **infinitos processadores**, o speedup máximo é limitado pela fração sequencial! Se 20% do programa é sequencial, **nunca** será mais que 5× mais rápido.

#### 7.4 Impacto da Fração Sequencial

| Fração Paralela (f) | Fração Sequencial (1-f) | Speedup Máximo (p → ∞) |
|:-------------------:|:----------------------:|:---------------------:|
| 50% | 50% | 2× |
| 75% | 25% | 4× |
| 90% | 10% | 10× |
| 95% | 5% | 20× |
| 99% | 1% | 100× |
| 99,9% | 0,1% | 1000× |

```
Speedup
   ▲
   │                                    f = 99%
   │                              ●─────────────────
   │                         ●
100│                    ●
   │               ●                    f = 95%
   │          ●──────────────────────────────────
 20│     ●────                          f = 90%
   │  ●────────────────────────────────────────
 10│●──                                 f = 75%
  4│●───────────────────────────────────────────
   │                                    f = 50%
  2│●───────────────────────────────────────────
   │
   └──────────────────────────────────────────► p
   1    4    8    16   32   64   128  256  512
                Número de processadores
```

---

### 8. Aplicações no Mundo Real

#### 8.1 Supercomputadores

Os supercomputadores modernos são sistemas MIMD massivos com milhares de processadores:

| Supercomputador | Localização | Núcleos | Performance (PFLOPS) | Tipo |
|----------------|-------------|:-------:|:-------------------:|------|
| Frontier | EUA (ORNL) | 8.730.112 | 1.194 | AMD CPU + GPU |
| Aurora | EUA (Argonne) | 4.742.808 | 1.012 | Intel CPU + GPU |
| Eagle | EUA (Microsoft) | — | 561 | NVIDIA GPU Cloud |
| Fugaku | Japão (RIKEN) | 7.630.848 | 442 | ARM (A64FX) |
| LUMI | Finlândia | 2.220.288 | 379 | AMD CPU + GPU |

> 💡 **1 PFLOPS = 10¹⁵ operações de ponto flutuante por segundo** (um quadrilhão!)

#### 8.2 Computação em Nuvem (Cloud Computing)

```
┌────────────────────────────────────────────────┐
│            CLOUD COMPUTING                      │
│                                                │
│   Usuário                                      │
│     │                                          │
│     ▼                                          │
│   ┌──────────────────┐                         │
│   │  Load Balancer   │ ← Distribui requisições │
│   └────────┬─────────┘                         │
│            │                                   │
│     ┌──────┼──────┐                            │
│     ▼      ▼      ▼                            │
│   ┌────┐ ┌────┐ ┌────┐                         │
│   │VM 1│ │VM 2│ │VM 3│  ← Máquinas virtuais   │
│   └────┘ └────┘ └────┘                         │
│                                                │
│   Cada VM pode ter múltiplos vCPUs (MIMD)      │
│   GPU disponível para ML/AI (SIMD)             │
│   Escala automaticamente (auto-scaling)        │
└────────────────────────────────────────────────┘
```

#### 8.3 GPUs em Jogos e IA

```
┌────────────────────────────────────────────────┐
│     GPU NVIDIA RTX 4090 (Ada Lovelace)         │
├────────────────────────────────────────────────┤
│                                                │
│  • 16.384 CUDA cores (SIMD)                    │
│  • 512 Tensor cores (IA)                       │
│  • 128 RT cores (Ray Tracing)                  │
│  • 24 GB GDDR6X                                │
│  • 1 TB/s largura de banda de memória          │
│  • ~83 TFLOPS (FP32)                           │
│                                                │
│  Aplicações:                                   │
│  🎮 Jogos 4K a 120+ FPS                        │
│  🤖 Treinamento de IA (ChatGPT, etc.)          │
│  🎬 Renderização 3D profissional               │
│  🔬 Simulações científicas                     │
│                                                │
└────────────────────────────────────────────────┘
```

---

### 9. Resumo da Aula

```
┌─────────────────────────────────────────────────────────┐
│                    RESUMO - AULA 18                      │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  🔹 Paralelismo é necessário porque a frequência dos    │
│     processadores estagnou (~2005)                      │
│                                                         │
│  🔹 Taxonomia de Flynn classifica arquiteturas:         │
│     • SISD: sequencial clássico                        │
│     • SIMD: mesma instrução, múltiplos dados (GPU)     │
│     • MISD: raro, tolerância a falhas                  │
│     • MIMD: múltiplos processadores independentes      │
│                                                         │
│  🔹 Memória compartilhada: fácil de programar,         │
│     escalabilidade limitada                            │
│                                                         │
│  🔹 Memória distribuída: alta escalabilidade,           │
│     programação mais complexa                          │
│                                                         │
│  🔹 Multi-core: a solução atual para desempenho        │
│                                                         │
│  🔹 GPU: milhares de núcleos SIMD para computação      │
│     massivamente paralela (jogos, IA, ciência)         │
│                                                         │
│  🔹 Lei de Amdahl: speedup limitado pela fração        │
│     sequencial do programa                             │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

---

## 📖 Referências

1. STALLINGS, W. **Arquitetura e Organização de Computadores**. 10ª ed. Pearson, 2017.
2. PATTERSON, D. A.; HENNESSY, J. L. **Organização e Projeto de Computadores: A Interface Hardware/Software**. 5ª ed. Elsevier, 2017.
3. TANENBAUM, A. S. **Organização Estruturada de Computadores**. 6ª ed. Pearson, 2013.
4. HENNESSY, J. L.; PATTERSON, D. A. **Arquitetura de Computadores: Uma Abordagem Quantitativa**. 6ª ed. Elsevier, 2019.
5. FLYNN, M. J. **Some Computer Organizations and Their Effectiveness**. IEEE Transactions on Computers, 1972.
6. TOP500. **Lista dos Supercomputadores mais Rápidos do Mundo**. Disponível em: top500.org

---

> 💡 **Próxima aula:** Arquitetura do Conjunto de Instruções (ISA) — vamos estudar a interface entre hardware e software!
