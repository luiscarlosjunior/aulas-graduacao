# 🔬 Exemplos Práticos - Aula 13

## Hierarquia de Memória e Sistema de Armazenamento

---

## Exemplo 1: Diagrama Detalhado da Hierarquia de Memória 📊

### Hierarquia Completa de um Computador Moderno (2024)

```
╔═══════════════════════════════════════════════════════════════╗
║                    HIERARQUIA DE MEMÓRIA                      ║
║               (Computador Desktop Típico - 2024)              ║
╠═══════════════════════════════════════════════════════════════╣
║                                                               ║
║  NÍVEL 0 - REGISTRADORES                                     ║
║  ┌──────────────────────────────────┐                         ║
║  │  16 registradores de 64 bits     │ ⏱️ < 0,5 ns            ║
║  │  = 128 bytes                     │ 💰 Embutido na CPU     ║
║  │  Acesso: 1 ciclo de clock        │                         ║
║  └──────────────────────────────────┘                         ║
║                    ↕ (transferência interna)                   ║
║  NÍVEL 1 - CACHE L1                                          ║
║  ┌──────────────────────────────────┐                         ║
║  │  32 KB Instruções + 32 KB Dados  │ ⏱️ ~1 ns (4 ciclos)   ║
║  │  = 64 KB por núcleo              │ 💰 Embutido na CPU     ║
║  │  SRAM, por núcleo                │                         ║
║  └──────────────────────────────────┘                         ║
║                    ↕ (~4 ciclos)                              ║
║  NÍVEL 2 - CACHE L2                                          ║
║  ┌──────────────────────────────────┐                         ║
║  │  256 KB - 1 MB                   │ ⏱️ ~3-10 ns           ║
║  │  SRAM, por núcleo                │ 💰 Embutido na CPU     ║
║  └──────────────────────────────────┘                         ║
║                    ↕ (~12 ciclos)                             ║
║  NÍVEL 3 - CACHE L3                                          ║
║  ┌──────────────────────────────────┐                         ║
║  │  8 MB - 64 MB                    │ ⏱️ ~10-20 ns          ║
║  │  SRAM, compartilhada             │ 💰 Embutido na CPU     ║
║  └──────────────────────────────────┘                         ║
║                    ↕ Barramento de memória (~100 ns)          ║
║  NÍVEL 4 - MEMÓRIA PRINCIPAL (RAM)                            ║
║  ┌──────────────────────────────────┐                         ║
║  │  8 GB - 64 GB                    │ ⏱️ ~50-100 ns         ║
║  │  DDR4 / DDR5 DRAM                │ 💰 ~R$ 20/GB          ║
║  └──────────────────────────────────┘                         ║
║                    ↕ Barramento PCIe/SATA (~μs)               ║
║  NÍVEL 5 - ARMAZENAMENTO SECUNDÁRIO                          ║
║  ┌──────────────────────────────────┐                         ║
║  │  SSD: 256 GB - 4 TB              │ ⏱️ ~25-100 μs         ║
║  │  HDD: 1 TB - 20 TB               │ ⏱️ ~3-10 ms           ║
║  │  NVMe / SATA                      │ 💰 ~R$ 0,30/GB (SSD) ║
║  └──────────────────────────────────┘                         ║
║                    ↕ Rede / USB                               ║
║  NÍVEL 6 - ARMAZENAMENTO TERCIÁRIO                           ║
║  ┌──────────────────────────────────┐                         ║
║  │  Fita Magnética: PB              │ ⏱️ segundos a minutos ║
║  │  Nuvem: ilimitado                │ 💰 ~R$ 0,01/GB        ║
║  │  Óptico: TB                       │                        ║
║  └──────────────────────────────────┘                         ║
╚═══════════════════════════════════════════════════════════════╝
```

---

## Exemplo 2: Cálculo de AMAT (Tempo Médio de Acesso à Memória) 🧮

### Caso 1: Sistema com um nível de cache

```
Dados do sistema:
  Cache Hit Time (tc)  = 2 ns
  Cache Hit Rate (h)   = 90%
  RAM Access Time (tm) = 100 ns

Fórmula:
  AMAT = tc + (1 - h) × tm
  AMAT = 2 + (1 - 0,90) × 100
  AMAT = 2 + 0,10 × 100
  AMAT = 2 + 10
  AMAT = 12 ns

Comparação:
  Sem cache:  100 ns
  Com cache:  12 ns
  Speedup:    100 / 12 = 8,33x mais rápido! ✅
```

### Caso 2: Sistema com dois níveis de cache

```
Dados do sistema:
  Cache L1 Hit Time         = 1 ns
  Cache L1 Hit Rate         = 95%
  Cache L2 Hit Time         = 5 ns
  Cache L2 Hit Rate (local) = 80%    (dado encontrado em L2 quando não está em L1)
  RAM Access Time            = 100 ns

Fórmula (hierárquica):
  AMAT = Hit_L1 + Miss_L1 × (Hit_L2 + Miss_L2 × Tempo_RAM)
  AMAT = 1 + 0,05 × (5 + 0,20 × 100)
  AMAT = 1 + 0,05 × (5 + 20)
  AMAT = 1 + 0,05 × 25
  AMAT = 1 + 1,25
  AMAT = 2,25 ns

Comparação:
  Sem cache:             100 ns
  Com 1 nível (L1):     ~6 ns
  Com 2 níveis (L1+L2):  2,25 ns  ← Ainda melhor! ✅
```

### Caso 3: Impacto da variação do Hit Rate

```
┌────────────┬────────────┬──────────┬───────────────┐
│  Hit Rate  │ Miss Rate  │ AMAT(ns) │    Speedup    │
│   Cache    │   Cache    │          │ (vs. sem cache)│
├────────────┼────────────┼──────────┼───────────────┤
│    80%     │    20%     │  22,0    │     4,5x      │
│    85%     │    15%     │  17,0    │     5,9x      │
│    90%     │    10%     │  12,0    │     8,3x      │
│    93%     │     7%     │   9,0    │    11,1x      │
│    95%     │     5%     │   7,0    │    14,3x      │
│    97%     │     3%     │   5,0    │    20,0x      │
│    99%     │     1%     │   3,0    │    33,3x      │
│   99,5%    │    0,5%    │   2,5    │    40,0x      │
└────────────┴────────────┴──────────┴───────────────┘

Parâmetros: Hit Time = 2 ns, Miss Penalty = 100 ns

📌 Observe: Ir de 95% para 99% (apenas 4 pontos percentuais)
   reduz o AMAT de 7 ns para 3 ns — mais que o DOBRO do speedup!
```

---

## Exemplo 3: Localidade Temporal e Espacial em Código 💻

### Exemplo 3.1: Soma de Vetor (Localidade Espacial + Temporal)

```c
#include <stdio.h>

#define N 10000

int main() {
    int vetor[N];
    int soma = 0;           // ← Localidade TEMPORAL (acessada N vezes)

    // Inicialização com localidade ESPACIAL
    for (int i = 0; i < N; i++) {
        vetor[i] = i + 1;   // ← Acesso SEQUENCIAL (espacial)
    }

    // Soma com AMBAS as localidades
    for (int i = 0; i < N; i++) {
        soma += vetor[i];   // vetor[i] → espacial, soma → temporal
    }

    printf("Soma = %d\n", soma);
    return 0;
}
```

**Análise de acessos à memória:**

```
Variável 'soma':
  Acessos: soma é lida e escrita 10.000 vezes
  → FORTE localidade TEMPORAL
  → Ficará no registrador ou Cache L1

Vetor 'vetor[]':
  Acessos: vetor[0], vetor[1], vetor[2], ..., vetor[9999]
  → FORTE localidade ESPACIAL (endereços consecutivos)
  → Blocos de cache trazem múltiplos elementos de uma vez

Variável 'i':
  Acessos: incrementada e comparada 10.000 vezes
  → FORTE localidade TEMPORAL
  → Ficará no registrador
```

### Exemplo 3.2: Multiplicação de Matrizes — Bom vs. Mau Acesso

```c
// VERSÃO 1: Acesso amigável à cache (ijk) ✅
void multiplicar_bom(int A[][N], int B[][N], int C[][N]) {
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++) {
            int soma = 0;
            for (int k = 0; k < N; k++)
                soma += A[i][k] * B[k][j]; // A: por linha ✅
            C[i][j] = soma;
        }
}

// VERSÃO 2: Acesso ruim à cache (kij reorganizado) ❌
void multiplicar_ruim(int A[][N], int B[][N], int C[][N]) {
    for (int k = 0; k < N; k++)
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                C[i][j] += A[i][k] * B[k][j]; // C: muitas escritas
}
```

**Comparação de desempenho para N = 1000:**

```
┌──────────────┬───────────────┬─────────────┬───────────────────┐
│   Versão     │ Cache Misses  │ Tempo (aprox)│ Razão             │
├──────────────┼───────────────┼─────────────┼───────────────────┤
│ Versão 1 ✅ │    Baixo       │    2,1 s     │ Acesso sequencial │
│ Versão 2 ❌ │    Alto        │    8,5 s     │ Acesso com saltos │
└──────────────┴───────────────┴─────────────┴───────────────────┘

A diferença é de ~4x apenas pela ORDEM de acesso à memória!
Mesmas operações, mesmo resultado, mas desempenho muito diferente.
```

---

## Exemplo 4: Cálculo de Tamanho e Endereçamento de Memória 📐

### Exemplo 4.1: Capacidade de memória por nível

```
Pergunta: Um processador tem:
  - 16 registradores de 64 bits
  - Cache L1: 32 KB dados + 32 KB instruções
  - Cache L2: 256 KB
  - Cache L3: 8 MB
  - RAM: 16 GB
  - SSD: 512 GB

Qual a capacidade total e a proporção entre níveis?

Resolução:
  Registradores: 16 × 8 bytes = 128 bytes = 0,000128 KB
  Cache L1:      64 KB
  Cache L2:      256 KB
  Cache L3:      8.192 KB (8 MB)
  RAM:           16.777.216 KB (16 GB)
  SSD:           536.870.912 KB (512 GB)

Proporções (relativo aos registradores):
  Registradores: 1x            (128 B)
  Cache L1:      512x          (64 KB)
  Cache L2:      2.048x        (256 KB)
  Cache L3:      65.536x       (8 MB)
  RAM:           134.217.728x  (16 GB)
  SSD:           4.294.967.296x (512 GB) ← 4 bilhões de vezes maior!
```

### Exemplo 4.2: Tempo total para transferir dados entre níveis

```
Pergunta: Quanto tempo leva para copiar 64 bytes (uma linha de cache)
de cada nível para o processador?

                    Tempo de      Tempo Total    Fator de
Nível               Acesso        (64 bytes)     Lentidão
─────────────────────────────────────────────────────────
Registrador         0,3 ns        0,3 ns         1x
Cache L1            1 ns          1 ns           3x
Cache L2            5 ns          5 ns           17x
Cache L3            15 ns         15 ns          50x
RAM                 80 ns         80 ns          267x
SSD NVMe            50 μs         50.000 ns      166.667x
HDD                 5 ms          5.000.000 ns   16.666.667x

📌 Do HDD ao registrador: ~17 MILHÕES de vezes mais lento!
```

---

## Exemplo 5: Simulação Visual de Acessos à Cache 🎮

### Cenário: Cache com 4 blocos, acesso sequencial a vetor

```
Cache: 4 blocos, cada bloco armazena 4 elementos do vetor
Vetor: v[0], v[1], v[2], ..., v[15]

═══ Passo 1: Acesso a v[0] ═══
Cache MISS! → Traz bloco com v[0], v[1], v[2], v[3]
┌─────────────────┬─────────────────┬──────────┬──────────┐
│ v[0] v[1] v[2]  │                 │          │          │
│ v[3]            │     (vazio)     │ (vazio)  │ (vazio)  │
│ Bloco 0 ★      │     Bloco 1     │ Bloco 2  │ Bloco 3  │
└─────────────────┴─────────────────┴──────────┴──────────┘

═══ Passo 2: Acesso a v[1] ═══
Cache HIT! ✅ (v[1] já está no Bloco 0)

═══ Passo 3: Acesso a v[2] ═══
Cache HIT! ✅ (v[2] já está no Bloco 0)

═══ Passo 4: Acesso a v[3] ═══
Cache HIT! ✅ (v[3] já está no Bloco 0)

═══ Passo 5: Acesso a v[4] ═══
Cache MISS! → Traz bloco com v[4], v[5], v[6], v[7]
┌─────────────────┬─────────────────┬──────────┬──────────┐
│ v[0] v[1] v[2]  │ v[4] v[5] v[6]  │          │          │
│ v[3]            │ v[7]            │ (vazio)  │ (vazio)  │
│ Bloco 0        │ Bloco 1 ★      │ Bloco 2  │ Bloco 3  │
└─────────────────┴─────────────────┴──────────┴──────────┘

═══ Passos 6-8: Acessos a v[5], v[6], v[7] ═══
Cache HIT! HIT! HIT! ✅✅✅

═══ Resultado para v[0]..v[15]: ═══
Acessos totais: 16
Cache Misses:   4 (v[0], v[4], v[8], v[12])
Cache Hits:     12 (todos os outros)
Hit Rate:       12/16 = 75%

🔑 Cada miss traz 4 elementos → 3 hits gratuitos por localidade espacial!
```

---

## Exemplo 6: Analogia Completa — Escritório de Trabalho 🏢

```
╔═══════════════════════════════════════════════════════════════╗
║           ANALOGIA: SEU AMBIENTE DE TRABALHO                  ║
╠═══════════════════════════════════════════════════════════════╣
║                                                               ║
║  🧠 Sua mente (o que você lembra agora)                      ║
║     = REGISTRADORES                                           ║
║     Capacidade: poucos dados, acesso instantâneo              ║
║     Exemplo: O número de telefone que você acabou de ler      ║
║                                                               ║
║  📎 Post-its colados no monitor                               ║
║     = CACHE L1                                                ║
║     Capacidade: poucas notas, acesso muito rápido             ║
║     Exemplo: Senha do Wi-Fi, ramal do chefe                   ║
║                                                               ║
║  📓 Caderno aberto na mesa                                    ║
║     = CACHE L2                                                ║
║     Capacidade: várias páginas, acesso rápido                 ║
║     Exemplo: Anotações da reunião de hoje                     ║
║                                                               ║
║  📁 Gaveta da mesa                                            ║
║     = CACHE L3                                                ║
║     Capacidade: várias pastas, alguns segundos para buscar    ║
║     Exemplo: Documentos da semana                             ║
║                                                               ║
║  🗄️ Armário do escritório                                    ║
║     = MEMÓRIA RAM                                             ║
║     Capacidade: muitas pastas, minutos para buscar            ║
║     Exemplo: Projetos do mês                                  ║
║                                                               ║
║  📦 Arquivo morto no depósito                                 ║
║     = SSD / HDD                                               ║
║     Capacidade: caixas enormes, horas para localizar          ║
║     Exemplo: Projetos de anos anteriores                      ║
║                                                               ║
║  🏪 Depósito externo / Cartório                               ║
║     = Armazenamento Terciário / Nuvem                         ║
║     Capacidade: enorme, dias para obter                       ║
║     Exemplo: Documentos históricos da empresa                 ║
╚═══════════════════════════════════════════════════════════════╝
```

---

## Exemplo 7: Comparação de Tecnologias Reais (2024) 💻

### Processador Intel Core i7-13700K

```
┌──────────────────────────────────────────────────────────┐
│           Intel Core i7-13700K - Hierarquia               │
├──────────────────────────────────────────────────────────┤
│                                                          │
│  Registradores: 16 registradores × 64 bits cada          │
│                 + 16 registradores AVX-512 × 512 bits    │
│                                                          │
│  Cache L1 (por P-core):                                  │
│    - 48 KB Instruções (6-way associative)                │
│    - 32 KB Dados (8-way associative)                     │
│    - Latência: ~4 ciclos                                 │
│                                                          │
│  Cache L2 (por P-core):                                  │
│    - 1,25 MB (10-way associative)                        │
│    - Latência: ~12 ciclos                                │
│                                                          │
│  Cache L3 (compartilhada):                               │
│    - 30 MB (12-way associative)                          │
│    - Latência: ~42 ciclos                                │
│                                                          │
│  Suporta DDR4-3200 / DDR5-5600                           │
│    - 16 GB - 128 GB                                      │
│    - Latência: ~80-100 ns                                │
└──────────────────────────────────────────────────────────┘
```

### Comparação entre processadores

```
┌─────────────────┬──────────┬──────────┬──────────────┐
│  Processador     │ Cache L1 │ Cache L2 │   Cache L3   │
│                  │(por core)│(por core)│(compartilhada)│
├─────────────────┼──────────┼──────────┼──────────────┤
│ Intel i5-13600K │ 80 KB    │ 1,25 MB  │    20 MB     │
│ Intel i7-13700K │ 80 KB    │ 1,25 MB  │    30 MB     │
│ Intel i9-13900K │ 80 KB    │ 2 MB     │    36 MB     │
│ AMD Ryzen 5 7600│ 64 KB    │ 1 MB     │    32 MB     │
│ AMD Ryzen 7 7700│ 64 KB    │ 1 MB     │    32 MB     │
│ AMD Ryzen 9 7950│ 64 KB    │ 1 MB     │    64 MB     │
│ Apple M2        │ 192 KB   │ 16 MB    │  (integrada) │
│ Apple M3 Pro    │ 192 KB   │ 16 MB    │    36 MB     │
└─────────────────┴──────────┴──────────┴──────────────┘
```

---

## Exemplo 8: Cálculo de CPI com Stalls de Memória ⏱️

```
Problema: Um processador com CPI ideal de 1,0 ciclo tem:
  - 30% das instruções fazem acesso à memória
  - Cache hit rate: 95%
  - Miss penalty: 50 ciclos
  - Clock: 3 GHz

Calcule o CPI real e o impacto no desempenho.

Resolução:

1) Memory stall cycles por instrução:
   Stalls = Freq_acesso_mem × Miss_rate × Miss_penalty
   Stalls = 0,30 × 0,05 × 50
   Stalls = 0,75 ciclos por instrução

2) CPI real:
   CPI_real = CPI_ideal + Stalls
   CPI_real = 1,0 + 0,75
   CPI_real = 1,75 ciclos

3) Degradação de desempenho:
   Speedup_ideal / real = CPI_real / CPI_ideal = 1,75 / 1,0 = 1,75
   O processador roda 1,75x mais lento do que o ideal

4) Tempo por instrução:
   T_instrução = CPI_real / Clock = 1,75 / 3×10⁹ = 0,583 ns

5) Se o hit rate fosse 99%:
   Stalls = 0,30 × 0,01 × 50 = 0,15 ciclos
   CPI_real = 1,0 + 0,15 = 1,15 ciclos
   Melhoria: 1,75/1,15 = 1,52x mais rápido
```

---

## Exemplo 9: Memória Virtual e SWAP 🔄

```
Cenário: Computador com 8 GB de RAM e 10 programas abertos

Programa        RAM usada     Status
──────────────────────────────────────
Chrome (10 abas)  2,5 GB      Ativo (em uso)
VS Code           1,2 GB      Ativo
Spotify           0,4 GB      Em segundo plano
Slack             0,8 GB      Em segundo plano
Zoom              0,6 GB      Minimizado
Excel             0,5 GB      Minimizado
Word              0,3 GB      Minimizado
Steam             0,8 GB      Em segundo plano
Antivírus         0,3 GB      Serviço
Sistema (OS)      1,5 GB      Sempre ativo
──────────────────────────────────────
TOTAL NECESSÁRIO: 8,9 GB
RAM DISPONÍVEL:   8,0 GB
DÉFICIT:          0,9 GB  ← Precisa de SWAP!

O Sistema Operacional move para o SWAP (SSD/HDD):
┌─────────────────────────────────────────────┐
│                    RAM (8 GB)                │
│  Chrome | VS Code | Spotify | Slack | SO    │
│  2,5 GB   1,2 GB   0,4 GB   0,8 GB  1,5 GB │
│  + partes ativas dos outros programas       │
│  (Total: ~8 GB)                              │
└─────────────────────────────────────────────┘
         ↕ SWAP (quando necessário)
┌─────────────────────────────────────────────┐
│               SWAP no SSD (0,9 GB+)         │
│  Zoom (dados) | Excel (dados) | Word | Steam│
│  Dados pouco usados dos programas minimizados│
└─────────────────────────────────────────────┘

⚠️ Quando o usuário clica no Zoom:
  1. OS precisa trazer dados do SWAP para a RAM
  2. Algo da RAM precisa ir para o SWAP
  3. Transferência SSD→RAM: ~100 μs por bloco
  4. vs. Acesso normal à RAM: ~100 ns
  5. = ~1000x mais lento!
  6. RESULTADO: "Travada" perceptível ao alternar janelas
```

---

## Exemplo 10: Comparação de Latências em Escala Humana ⏰

```
Se 1 ciclo de CPU (0,3 ns a 3 GHz) fosse 1 SEGUNDO:

Operação                  Tempo Real    Escala Humana
──────────────────────────────────────────────────────
1 ciclo de CPU            0,3 ns        1 segundo
Acesso ao registrador     0,3 ns        1 segundo
Acesso à Cache L1         1,3 ns        4 segundos
Acesso à Cache L2         4 ns          13 segundos
Acesso à Cache L3         12 ns         40 segundos
Acesso à RAM              100 ns        6 minutos
Acesso ao SSD (NVMe)      25 μs         1 dia
Acesso ao SSD (SATA)      100 μs        4 dias
Acesso ao HDD             10 ms         1 ano
Acesso à rede (internet)  50 ms         5 anos
Reiniciar o computador    60 s          ~6.000 anos!

📌 Se acessar um registrador levasse 1 segundo,
   acessar o HDD levaria o equivalente a 1 ANO!
   Isso mostra por que a cache é TÃO importante.
```

---

## 📝 Resumo dos Exemplos

| Exemplo | Conceito Demonstrado |
|---------|---------------------|
| 1 | Diagrama completo da hierarquia com valores reais |
| 2 | Cálculos de AMAT com 1 e 2 níveis de cache |
| 3 | Localidade temporal e espacial em código C |
| 4 | Cálculos de capacidade e tempo de transferência |
| 5 | Simulação visual de cache com vetor |
| 6 | Analogia completa do escritório |
| 7 | Dados reais de processadores modernos |
| 8 | Cálculo de CPI com stalls de memória |
| 9 | Memória virtual e SWAP na prática |
| 10 | Latências em escala humana |
