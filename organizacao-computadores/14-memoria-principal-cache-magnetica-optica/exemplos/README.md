# 🔬 Exemplos Práticos - Aula 14

## Memória Principal, Cache, Magnéticas e Ópticas

---

## Exemplo 1: Mapeamento Direto de Cache 🗺️

### Cenário

```
Cache com mapeamento direto:
  - Tamanho da cache: 16 bytes
  - Tamanho do bloco: 4 bytes
  - Número de linhas: 16 / 4 = 4 linhas
  - Endereço da memória: 8 bits (256 bytes de memória)

Decomposição do endereço (8 bits):
┌──────────┬──────────┬──────────┐
│   Tag    │  Índice  │  Offset  │
│ (4 bits) │ (2 bits) │ (2 bits) │
└──────────┴──────────┴──────────┘

  Offset: 2 bits → 4 bytes por bloco (2² = 4)
  Índice: 2 bits → 4 linhas na cache (2² = 4)
  Tag:    4 bits → 16 blocos mapeados por linha (2⁴ = 16)
```

### Simulação de Acessos

Sequência de endereços: **0, 4, 8, 0, 12, 4**

```
Endereço 0 = 0000|00|00 → Tag=0000, Índice=00, Offset=00
Endereço 4 = 0000|01|00 → Tag=0000, Índice=01, Offset=00
Endereço 8 = 0000|10|00 → Tag=0000, Índice=10, Offset=00
Endereço 12 = 0000|11|00 → Tag=0000, Índice=11, Offset=00

═══ Passo 1: Acesso ao endereço 0 ═══
Tag=0000, Índice=00 → Linha 0
Cache: Linha 0 está vazia → MISS ❌
Ação: Traz bloco [0,1,2,3] para Linha 0

Cache:
┌───────┬───┬──────┬────────────────┐
│ Linha │ V │ Tag  │   Dados        │
├───────┼───┼──────┼────────────────┤
│   0   │ 1 │ 0000 │ [0] [1] [2] [3]│ ← NOVO
│   1   │ 0 │  -   │      -         │
│   2   │ 0 │  -   │      -         │
│   3   │ 0 │  -   │      -         │
└───────┴───┴──────┴────────────────┘

═══ Passo 2: Acesso ao endereço 4 ═══
Tag=0000, Índice=01 → Linha 1
Cache: Linha 1 está vazia → MISS ❌
Ação: Traz bloco [4,5,6,7] para Linha 1

Cache:
┌───────┬───┬──────┬────────────────┐
│   0   │ 1 │ 0000 │ [0] [1] [2] [3]│
│   1   │ 1 │ 0000 │ [4] [5] [6] [7]│ ← NOVO
│   2   │ 0 │  -   │      -         │
│   3   │ 0 │  -   │      -         │
└───────┴───┴──────┴────────────────┘

═══ Passo 3: Acesso ao endereço 8 ═══
Tag=0000, Índice=10 → Linha 2 → MISS ❌
Traz bloco [8,9,10,11]

═══ Passo 4: Acesso ao endereço 0 ═══
Tag=0000, Índice=00 → Linha 0
Cache: Linha 0 tem Tag=0000 → HIT ✅ (dado já está na cache!)

═══ Passo 5: Acesso ao endereço 12 ═══
Tag=0000, Índice=11 → Linha 3 → MISS ❌
Traz bloco [12,13,14,15]

═══ Passo 6: Acesso ao endereço 4 ═══
Tag=0000, Índice=01 → Linha 1
Cache: Linha 1 tem Tag=0000 → HIT ✅

RESULTADO FINAL:
  Total de acessos: 6
  Misses: 4
  Hits: 2
  Hit Rate: 2/6 = 33,3%
```

---

## Exemplo 2: Mapeamento Associativo por Conjunto 🗂️

### Cenário

```
Cache 2-way set-associative:
  - Tamanho da cache: 32 bytes
  - Tamanho do bloco: 4 bytes
  - 32 / 4 = 8 linhas → 8 / 2 = 4 conjuntos (2-way)
  - Endereço: 8 bits

Decomposição do endereço (8 bits):
┌──────────┬──────────┬──────────┐
│   Tag    │  Índice  │  Offset  │
│ (4 bits) │ (2 bits) │ (2 bits) │
└──────────┴──────────┴──────────┘

Índice: 2 bits → 4 conjuntos
Cada conjunto: 2 vias (ways)
```

### Simulação com Política LRU

Sequência: **0, 16, 0, 32, 16**

```
Endereço 0  = 0000|00|00 → Tag=0000, Conjunto=00
Endereço 16 = 0001|00|00 → Tag=0001, Conjunto=00  (mesmo conjunto!)
Endereço 32 = 0010|00|00 → Tag=0010, Conjunto=00  (mesmo conjunto!)

═══ Passo 1: Acesso ao endereço 0 ═══
Conjunto 0, Tag=0000 → Ambas vias vazias → MISS ❌
Coloca na Via 0

Conjunto 0:
┌──────┬───┬──────┬──────────┬───┬──────┬──────────┐
│ Conj │ V │ Tag  │ Dados(0) │ V │ Tag  │ Dados(1) │
├──────┼───┼──────┼──────────┼───┼──────┼──────────┤
│  0   │ 1 │ 0000 │ [0-3]  ★│ 0 │  -   │    -     │
└──────┴───┴──────┴──────────┴───┴──────┴──────────┘
LRU: Via 1 é a menos recente (mais antiga)

═══ Passo 2: Acesso ao endereço 16 ═══
Conjunto 0, Tag=0001 → Não encontrado → MISS ❌
Via 0 ocupada, Via 1 vazia → Coloca na Via 1

Conjunto 0:
┌──────┬───┬──────┬──────────┬───┬──────┬──────────┐
│  0   │ 1 │ 0000 │ [0-3]   │ 1 │ 0001 │ [16-19]★ │
└──────┴───┴──────┴──────────┴───┴──────┴──────────┘
LRU: Via 0 (0000) é a menos recente

═══ Passo 3: Acesso ao endereço 0 ═══
Conjunto 0, Tag=0000 → Encontrado na Via 0 → HIT ✅

LRU atualiza: Via 1 (0001) agora é a menos recente

═══ Passo 4: Acesso ao endereço 32 ═══
Conjunto 0, Tag=0010 → Não encontrado → MISS ❌
Ambas vias ocupadas! LRU remove Via 1 (Tag=0001, menos recente)

Conjunto 0:
┌──────┬───┬──────┬──────────┬───┬──────┬──────────┐
│  0   │ 1 │ 0000 │ [0-3]   │ 1 │ 0010 │ [32-35]★ │
└──────┴───┴──────┴──────────┴───┴──────┴──────────┘

═══ Passo 5: Acesso ao endereço 16 ═══
Conjunto 0, Tag=0001 → Não encontrado (foi substituído!) → MISS ❌
LRU remove Via 0 (Tag=0000, menos recente)

Resultado: 1 Hit, 4 Misses → Hit Rate = 20%

⚠️ Este é um caso ruim: 3 endereços competindo por 2 vias
   no mesmo conjunto = thrashing!
```

---

## Exemplo 3: Decomposição de Endereço para Cache 📏

### Exercício Resolvido

```
Dada uma cache com as seguintes características:
  - Tamanho da cache: 64 KB
  - Tamanho do bloco: 64 bytes
  - Mapeamento direto
  - Endereço de memória: 32 bits

Determine: Tag, Índice e Offset.

Resolução:

1) Número de blocos na cache:
   64 KB / 64 bytes = 1024 blocos = 2¹⁰

2) Bits de Offset (dentro do bloco):
   64 bytes = 2⁶ → 6 bits de offset

3) Bits de Índice (seleciona o bloco):
   1024 blocos = 2¹⁰ → 10 bits de índice

4) Bits de Tag (identifica o bloco):
   32 - 10 - 6 = 16 bits de tag

Endereço de 32 bits:
┌────────────────┬──────────────┬────────────┐
│      Tag       │    Índice    │   Offset   │
│   (16 bits)    │   (10 bits)  │  (6 bits)  │
│ bits [31:16]   │ bits [15:6]  │ bits [5:0] │
└────────────────┴──────────────┴────────────┘

Exemplo: Endereço 0x12345678
  Binário: 0001 0010 0011 0100 0101 0110 0111 1000
  Tag:     0001 0010 0011 01     = 0x1234 (porção alta)
  Índice:  00 0101 0110         = 0x056 (porção média)
  Offset:  01 1000              = 0x18 (porção baixa)

  Este endereço vai para a LINHA 86 (0x056) da cache.
```

---

## Exemplo 4: Cache Associativa por Conjunto — Cálculo Completo 🧮

```
Cache 4-way set-associative:
  - Tamanho total: 256 KB
  - Tamanho do bloco: 32 bytes
  - Endereço: 32 bits

Resolução:

1) Número total de linhas:
   256 KB / 32 bytes = 8192 linhas

2) Número de conjuntos:
   8192 linhas / 4 vias = 2048 conjuntos = 2¹¹

3) Bits de Offset:
   32 bytes = 2⁵ → 5 bits

4) Bits de Índice:
   2048 conjuntos = 2¹¹ → 11 bits

5) Bits de Tag:
   32 - 11 - 5 = 16 bits

Endereço:
┌────────────────┬───────────────┬───────────┐
│      Tag       │    Índice     │  Offset   │
│   (16 bits)    │   (11 bits)   │ (5 bits)  │
└────────────────┴───────────────┴───────────┘

Para verificar um acesso:
  - Usar os bits de Índice para selecionar o conjunto
  - Comparar a Tag com as 4 vias do conjunto
  - Se alguma via tiver a Tag correspondente → HIT
  - Se nenhuma via tiver a Tag → MISS
```

---

## Exemplo 5: Cálculo de Tempo de Acesso ao HDD 💽

### Cenário

```
Disco rígido com as seguintes especificações:
  - RPM: 7200
  - Seek Time médio: 9 ms
  - Tamanho do setor: 512 bytes
  - Taxa de transferência: 150 MB/s

Calcule o tempo para ler um setor aleatório.

Resolução:

1) Rotational Latency (latência rotacional):
   Rotações por segundo = 7200 / 60 = 120 rotações/s
   Tempo por rotação = 1/120 = 8,33 ms
   Latência média = metade de uma rotação = 8,33 / 2 = 4,17 ms

2) Transfer Time (tempo de transferência):
   Tempo = Tamanho / Taxa = 512 bytes / (150 × 10⁶ bytes/s)
   Tempo = 512 / 150.000.000 = 0,0034 ms ≈ 3,4 μs

3) Tempo total de acesso:
   T = Seek + Rotational Latency + Transfer
   T = 9 + 4,17 + 0,0034
   T = 13,17 ms

Comparação com SSD NVMe:
  SSD: ~0,02 ms (20 μs)
  HDD: ~13,17 ms
  HDD é ~658x mais lento para acesso aleatório!
```

### Tabela para diferentes RPMs

```
┌──────┬───────────────┬──────────────────┬──────────────┐
│ RPM  │ Tempo/rotação │ Latência rotac.  │ T total (ms) │
├──────┼───────────────┼──────────────────┼──────────────┤
│ 5400 │  11,11 ms     │    5,56 ms       │   14,56      │
│ 7200 │   8,33 ms     │    4,17 ms       │   13,17      │
│10000 │   6,00 ms     │    3,00 ms       │   12,00      │
│15000 │   4,00 ms     │    2,00 ms       │   11,00      │
└──────┴───────────────┴──────────────────┴──────────────┘
(Seek time = 9 ms em todos os casos)
```

---

## Exemplo 6: Write-Through vs. Write-Back 📝

### Cenário

```
Um programa escreve 1000 vezes no MESMO endereço.
Cache hit rate para escrita: 98%

═══ Write-Through ═══

Cada escrita com hit vai para cache E para RAM:
  - 980 hits: 980 escritas na cache + 980 escritas na RAM
  - 20 misses: 20 leituras da RAM + 20 escritas na cache + 20 escritas na RAM

Total de acessos à RAM: 980 + 20 + 20 = 1020 acessos
Tempo (RAM a 100 ns): 1020 × 100 = 102.000 ns = 102 μs

═══ Write-Back ═══

Escritas com hit vão APENAS para a cache:
  - 980 hits: 980 escritas na cache (RAM NÃO é acessada)
  - 20 misses: 20 leituras da RAM + 20 dirty writebacks
  - No final: 1 dirty writeback para atualizar a RAM

Total de acessos à RAM: 20 + 20 + 1 = 41 acessos
Tempo (RAM a 100 ns): 41 × 100 = 4.100 ns = 4,1 μs

═══ Comparação ═══

Write-Through: 102 μs
Write-Back:    4,1 μs
Write-Back é ~25x mais eficiente neste cenário!

📌 Para escritas repetidas no mesmo endereço,
   Write-Back é MUITO superior.
```

---

## Exemplo 7: Cálculo de Capacidade de Endereçamento 📐

```
Problema: Um computador possui barramento de endereços de 32 bits
e cada endereço aponta para 1 byte. Quantos módulos de memória
DDR4 de 8 GB são necessários para usar toda a capacidade?

Resolução:

1) Espaço de endereçamento:
   2³² = 4.294.967.296 bytes = 4 GB

2) Número de módulos:
   4 GB / 8 GB = 0,5

   ⚠️ Um único módulo de 8 GB já excede o endereçamento!
   Com 32 bits, o máximo endereçável é 4 GB.

3) Para endereçar 16 GB de RAM:
   Precisamos de: log₂(16 × 10⁹) ≈ 34 bits de endereço
   (por isso sistemas modernos usam 48 ou 64 bits)

Tabela: Bits de endereço vs. RAM máxima:
┌─────────┬──────────────────┐
│  Bits   │ RAM Máxima       │
├─────────┼──────────────────┤
│   32    │ 4 GB             │
│   36    │ 64 GB            │
│   40    │ 1 TB             │
│   48    │ 256 TB           │
│   64    │ 16 EB            │
└─────────┴──────────────────┘
```

---

## Exemplo 8: Comparação Visual de Tecnologias de Memória 📊

```
Tempo de acesso (escala logarítmica):

      1 ns        10 ns       100 ns      1 μs       10 μs     100 μs      1 ms       10 ms
       │           │           │           │          │          │          │          │
SRAM   ████████
DRAM               ██████████████████████
Flash NOR                      █████████████████████
Flash NAND SLC                                       ████████████████████
Flash NAND TLC                                                  ██████████████████
HDD                                                                                 ██████████

Capacidade típica:

SRAM        ██ (KB - MB)
DRAM        ██████████ (GB)
Flash NOR   ████ (MB)
Flash NAND  ██████████████████████████ (TB)
HDD         ████████████████████████████████████ (TB - dezenas de TB)

Custo por GB:

SRAM        ██████████████████████████████████████ ($$$$$)
DRAM        ████████████ ($$$)
Flash NOR   ████████████████████ ($$$$)
Flash NAND  ████ ($$)
HDD         █ ($)
```

---

## Exemplo 9: Política de Substituição LRU — Passo a Passo 🔄

```
Cache com 3 linhas, política LRU.
Sequência de acessos: A, B, C, D, A, B, E, A

Passo 1: A → [A, -, -]     MISS   (cache vazia)
         MRU→LRU: A

Passo 2: B → [A, B, -]     MISS   (cache vazia)
         MRU→LRU: B, A

Passo 3: C → [A, B, C]     MISS   (cache cheia)
         MRU→LRU: C, B, A

Passo 4: D → [D, B, C]     MISS   (substitui A, o LRU)
         MRU→LRU: D, C, B

Passo 5: A → [D, A, C]     MISS   (substitui B, o LRU)
         MRU→LRU: A, D, C

Passo 6: B → [D, A, B]     MISS   (substitui C, o LRU)
         MRU→LRU: B, A, D

Passo 7: E → [E, A, B]     MISS   (substitui D, o LRU)
         MRU→LRU: E, B, A

Passo 8: A → [E, A, B]     HIT ✅  (A está na cache!)
         MRU→LRU: A, E, B

Resultado: 1 hit, 7 misses → Hit Rate = 1/8 = 12,5%

📌 Hit rate baixo porque o working set (5 blocos: A,B,C,D,E)
   é maior que a cache (3 linhas) = thrashing
```

---

## Exemplo 10: DRAM Refresh — Como Funciona 🔋

```
DRAM precisa de refresh porque o capacitor perde carga:

Tempo 0 ms          Tempo 32 ms          Tempo 64 ms
┌──────┐            ┌──────┐             ┌──────┐
│ ████ │ Carga      │ ██   │ Carga       │      │ Carga
│ ████ │ = 100%     │ ██   │ ≈ 50%       │      │ ≈ 0%
│ ████ │ (bit "1")  │ ██   │ (ainda "1") │      │ (dado PERDIDO!)
└──────┘            └──────┘             └──────┘

Solução: REFRESH a cada ~64 ms (antes da carga cair demais)

Processo de Refresh:
1. Controlador de memória lê cada linha da DRAM
2. O circuito de sense amplifier detecta a carga
3. O dado é reescrito com carga completa
4. Cada linha leva ~50 ns para refresh
5. Uma DRAM de 8 GB tem ~65.536 linhas
6. Total: 65.536 × 50 ns = 3,28 ms a cada 64 ms
7. Overhead de refresh: 3,28/64 ≈ 5% do tempo!

Tipos de refresh:
┌─────────────────┬───────────────────────────────────────┐
│  Burst Refresh   │ Para tudo e faz refresh de todas as   │
│                  │ linhas de uma vez (3,28 ms de pausa)  │
├─────────────────┼───────────────────────────────────────┤
│ Distributed     │ Faz refresh de 1 linha a cada ~1 μs   │
│  Refresh         │ (interrupção mínima, mais comum)      │
└─────────────────┴───────────────────────────────────────┘
```

---

## 📝 Resumo dos Exemplos

| Exemplo | Conceito Demonstrado |
|---------|---------------------|
| 1 | Mapeamento direto — simulação completa |
| 2 | Mapeamento associativo por conjunto com LRU |
| 3 | Decomposição de endereço — cálculo detalhado |
| 4 | Cache 4-way set-associative — cálculo completo |
| 5 | Tempo de acesso ao HDD |
| 6 | Comparação Write-Through vs. Write-Back |
| 7 | Cálculo de capacidade de endereçamento |
| 8 | Comparação visual de tecnologias |
| 9 | Política LRU passo a passo |
| 10 | Funcionamento do DRAM Refresh |
