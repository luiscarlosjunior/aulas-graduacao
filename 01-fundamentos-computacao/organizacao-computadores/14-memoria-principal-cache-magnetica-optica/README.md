# 💾 Aula 14 - Memória Principal, Cache, Magnéticas e Ópticas

## 📋 Informações da Aula

| Item | Descrição |
|------|-----------|
| **Curso** | Organização de Computadores |
| **Aula** | 14 |
| **Tema** | Memória Principal, Cache, Magnéticas e Ópticas |
| **Duração** | 2 horas (120 minutos) |
| **Pré-requisitos** | Aula 13 - Hierarquia de Memória |

---

## 🎯 Objetivos de Aprendizagem

Ao final desta aula, o estudante será capaz de:

1. ✅ Diferenciar SRAM e DRAM e explicar seus funcionamentos
2. ✅ Identificar os tipos de ROM e suas aplicações
3. ✅ Compreender organização e endereçamento de memória
4. ✅ Explicar os níveis de cache (L1, L2, L3) e técnicas de mapeamento
5. ✅ Analisar políticas de substituição e escrita em caches
6. ✅ Descrever o funcionamento de discos rígidos e armazenamento óptico
7. ✅ Compreender a tecnologia SSD e Flash NAND
8. ✅ Comparar todas as tecnologias de memória e suas aplicações

---

## 📚 Conteúdo

### 1. Memória Principal (RAM) 🧩

#### 1.1 SRAM (Static Random Access Memory)

A SRAM armazena cada bit usando um **circuito flip-flop** composto por 6 transistores (tipicamente).

```
Célula SRAM (6 transistores):
┌─────────────────────────────────┐
│         Bit Line    Bit Line'   │
│            │            │       │
│     ┌──────┤            ├──────┐│
│     │   ┌──┴──┐    ┌──┴──┐   ││
│     │   │ INV  │◄──►│ INV │   ││
│     │   │  1   │    │  2  │   ││
│     │   └──┬──┘    └──┬──┘   ││
│     │      │            │      ││
│     └──────┤            ├──────┘│
│            │            │       │
│       ─────┴────────────┴───── │
│            Word Line            │
└─────────────────────────────────┘

- 2 inversores cruzados mantêm o estado
- Enquanto houver energia, o dado persiste
- NÃO precisa de refresh
```

**Características da SRAM:**

| Propriedade | Valor |
|-------------|-------|
| Transistores por bit | 6 |
| Necessita refresh? | **Não** |
| Velocidade | Muito rápida (1-10 ns) |
| Custo | Alto |
| Densidade | Baixa (célula grande) |
| Consumo | Menor (estática) |
| Uso principal | **Cache do processador** |

#### 1.2 DRAM (Dynamic Random Access Memory)

A DRAM armazena cada bit usando apenas **1 transistor + 1 capacitor**.

```
Célula DRAM (1 transistor + 1 capacitor):
┌─────────────────────────────────┐
│         Bit Line                │
│            │                    │
│         ┌──┴──┐                 │
│  Word ──┤ FET │                 │
│  Line   └──┬──┘                 │
│            │                    │
│         ┌──┴──┐                 │
│         │     │                 │
│         │  C  │ ← Capacitor     │
│         │     │   (armazena     │
│         └──┬──┘    a carga)     │
│            │                    │
│          ──┴── GND              │
└─────────────────────────────────┘

Bit 1: Capacitor CARREGADO
Bit 0: Capacitor DESCARREGADO

⚠️ Problema: O capacitor perde carga com o tempo!
   → Precisa de REFRESH periódico (a cada ~64 ms)
```

**Características da DRAM:**

| Propriedade | Valor |
|-------------|-------|
| Transistores por bit | 1 (+ 1 capacitor) |
| Necessita refresh? | **Sim** (a cada ~64 ms) |
| Velocidade | Moderada (50-100 ns) |
| Custo | Moderado |
| Densidade | Alta (célula pequena) |
| Consumo | Maior (refresh constante) |
| Uso principal | **Memória principal** |

#### 1.3 Comparação SRAM vs. DRAM

| Característica | SRAM | DRAM |
|---------------|------|------|
| Elementos por bit | 6 transistores | 1 transistor + 1 capacitor |
| Velocidade | Mais rápida (1-10 ns) | Mais lenta (50-100 ns) |
| Custo | Mais cara | Mais barata |
| Densidade | Menor | Maior |
| Refresh necessário | Não | Sim |
| Complexidade do circuito | Maior | Menor |
| Consumo de energia | Menor (sem refresh) | Maior (com refresh) |
| Aplicação típica | Cache (L1, L2, L3) | Memória principal (RAM) |

#### 1.4 Evolução da DRAM

```
Evolução das tecnologias DRAM:

FPM DRAM (1990s)         → Acesso sequencial por página
  │
  ▼
EDO DRAM (1995)          → Pipelining de acessos
  │
  ▼
SDRAM (1997)             → Sincronizada com o clock do barramento
  │
  ▼
DDR (2000)               → Double Data Rate (2 transferências por ciclo)
  │
  ▼
DDR2 (2003)              → 2x a velocidade do DDR
  │
  ▼
DDR3 (2007)              → 2x a velocidade do DDR2
  │
  ▼
DDR4 (2014)              → 2x a velocidade do DDR3, menor consumo
  │
  ▼
DDR5 (2020)              → 2x a velocidade do DDR4, maior densidade
```

| Geração | Clock Efetivo | Largura de Banda | Tensão |
|---------|---------------|------------------|--------|
| DDR | 200-400 MT/s | 1,6-3,2 GB/s | 2,5 V |
| DDR2 | 400-1066 MT/s | 3,2-8,5 GB/s | 1,8 V |
| DDR3 | 800-2133 MT/s | 6,4-17 GB/s | 1,5 V |
| DDR4 | 1600-3200 MT/s | 12,8-25,6 GB/s | 1,2 V |
| DDR5 | 3200-8400 MT/s | 25,6-67,2 GB/s | 1,1 V |

> 📌 **MT/s** = Megatransferências por segundo. DDR transfere dados nas bordas de subida E descida do clock.

---

### 2. Memória ROM (Read-Only Memory) 📀

Memórias ROM armazenam dados **permanentemente** (ou semi-permanentemente) e são não-voláteis.

#### 2.1 Tipos de ROM

```
Evolução das memórias ROM:

ROM ──► PROM ──► EPROM ──► EEPROM ──► Flash
(fixa)  (1 vez)  (UV)     (elétrica)  (blocos)
```

| Tipo | Gravação | Apagamento | Regravável? | Uso |
|------|----------|------------|-------------|-----|
| **ROM** | Fábrica | Impossível | Não | Firmware fixo |
| **PROM** | Usuário (1 vez) | Impossível | Não | Protótipos |
| **EPROM** | Elétrica | Luz UV (20-30 min) | Sim (limitado) | Desenvolvimento |
| **EEPROM** | Elétrica | Elétrica (byte a byte) | Sim (~100K vezes) | Configurações |
| **Flash** | Elétrica | Elétrica (por bloco) | Sim (~10K-100K vezes) | SSD, Pen drive |

#### 2.2 Detalhes de cada tipo

**ROM (Mask ROM):**
```
- Dados gravados durante a FABRICAÇÃO do chip
- Impossível alterar depois
- Muito barato para produção em massa
- Exemplo: Cartuchos de videogame antigos
```

**PROM (Programmable ROM):**
```
- Vem "em branco" da fábrica
- Usuário grava UMA única vez usando um programador
- Fusíveis internos são "queimados" para gravar
- Exemplo: Protótipos de firmware
```

**EPROM (Erasable PROM):**
```
┌──────────────────────────┐
│  ┌────────────────────┐  │
│  │    Janela de UV     │  │ ← Janela de quartzo
│  │   ┌──────────┐     │  │    para apagar com
│  │   │ Chip     │     │  │    luz ultravioleta
│  │   │ EPROM    │     │  │
│  │   └──────────┘     │  │
│  └────────────────────┘  │
│       Pinos DIP          │
└──────────────────────────┘
- Apagamento: expor à luz UV por 20-30 minutos
- Apaga TUDO de uma vez
- Pode ser regravada centenas de vezes
```

**EEPROM (Electrically Erasable PROM):**
```
- Apagamento ELÉTRICO (sem luz UV)
- Pode apagar byte a byte individualmente
- Mais lenta para escrita que Flash
- Até ~100.000 ciclos de escrita
- Exemplo: BIOS do computador (antigamente)
```

**Flash Memory:**
```
- Evolução da EEPROM
- Apaga por BLOCOS (não byte a byte)
- Muito mais rápida que EEPROM para escrita
- Dois tipos: NOR Flash e NAND Flash
  - NOR: Acesso aleatório rápido → Firmware
  - NAND: Alta densidade → SSD, Pen drive, Cartão SD
- Até ~10.000-100.000 ciclos de escrita por célula
```

---

### 3. Organização e Endereçamento de Memória 🏗️

#### 3.1 Organização Básica

```
┌─────────────────────────────────────────────────────────┐
│               ORGANIZAÇÃO DA MEMÓRIA                     │
├─────────────────────────────────────────────────────────┤
│                                                          │
│  Endereço     Conteúdo (1 byte por endereço)            │
│  ────────     ──────────────────────────────             │
│  0x0000       │ 0100 1010 │                              │
│  0x0001       │ 1100 0011 │                              │
│  0x0002       │ 0000 1111 │                              │
│  0x0003       │ 1010 1010 │                              │
│     ...       │    ...    │                              │
│  0xFFFF       │ 0110 0001 │                              │
│                                                          │
│  Neste exemplo:                                          │
│  - 16 bits de endereço → 2¹⁶ = 65.536 endereços        │
│  - Cada endereço contém 1 byte (8 bits)                 │
│  - Capacidade total: 64 KB                              │
└─────────────────────────────────────────────────────────┘
```

#### 3.2 Cálculos de Endereçamento

```
Fórmulas fundamentais:

Número de endereços = 2^n   (onde n = número de bits de endereço)
Capacidade = Número de endereços × Tamanho da palavra

Exemplos:
┌─────────────────┬─────────────────┬─────────────────────┐
│ Bits de endereço│ Nº de endereços │ Capacidade (byte)    │
├─────────────────┼─────────────────┼─────────────────────┤
│      16 bits    │     65.536      │      64 KB           │
│      20 bits    │   1.048.576     │       1 MB           │
│      24 bits    │  16.777.216     │      16 MB           │
│      32 bits    │ 4.294.967.296   │       4 GB           │
│      36 bits    │ ~68 bilhões     │      64 GB           │
│      48 bits    │ ~281 trilhões   │     256 TB           │
│      64 bits    │ ~18 quintilhões │      16 EB           │
└─────────────────┴─────────────────┴─────────────────────┘
```

#### 3.3 Organização em Chips de Memória

```
Chip de memória: 4M × 8 (4 Megaendereços × 8 bits cada)

                  ┌──────────────┐
                  │              │
  Endereço ─────►│   4M × 8     │──────► Dados (8 bits)
  (22 bits)      │   Chip de    │
                  │   Memória    │
  CS (Chip ─────►│              │
  Select)        │              │
                  │              │
  R/W ──────────►│              │
  (Read/Write)   │              │
                  └──────────────┘

  - 22 bits de endereço: 2²² = 4.194.304 endereços = 4M
  - 8 bits de dados por endereço
  - Capacidade total: 4M × 1 byte = 4 MB
```

#### 3.4 Expansão de Memória

```
Expansão de CAPACIDADE (mais endereços):
Usar 2 chips de 4M × 8 para ter 8M × 8 (8 MB)

          ┌──────────────┐
  A[0:21]─┤   Chip 0     ├─ D[0:7]
          │   4M × 8     │
  A[22]═0─┤CS            │
          └──────────────┘
                                    ══► 8M × 8
          ┌──────────────┐              (8 MB)
  A[0:21]─┤   Chip 1     ├─ D[0:7]
          │   4M × 8     │
  A[22]═1─┤CS            │
          └──────────────┘

  Bit A[22] seleciona qual chip está ativo

Expansão de LARGURA (mais bits por palavra):
Usar 2 chips de 4M × 8 para ter 4M × 16 (8 MB)

          ┌──────────────┐
  A[0:21]─┤   Chip 0     ├─ D[0:7]   (byte inferior)
          │   4M × 8     │
   CS ────┤              │
          └──────────────┘
                                    ══► 4M × 16
          ┌──────────────┐              (8 MB)
  A[0:21]─┤   Chip 1     ├─ D[8:15]  (byte superior)
          │   4M × 8     │
   CS ────┤              │
          └──────────────┘

  Ambos os chips são acessados SIMULTANEAMENTE
```

---

### 4. Memória Cache 🚀

#### 4.1 Conceito e Funcionamento

A cache é uma memória pequena e muito rápida que armazena cópias dos dados mais usados da memória principal.

```
┌──────────┐     ┌────────────┐     ┌──────────────────┐
│          │────►│            │────►│                   │
│   CPU    │ hit │   CACHE    │miss │  MEMÓRIA PRINCIPAL│
│          │◄────│            │◄────│                   │
└──────────┘     └────────────┘     └──────────────────┘
   ~0,3 ns          ~1-20 ns            ~50-100 ns

Fluxo:
1. CPU solicita dado no endereço X
2. Cache verifica se tem cópia de X
3. Se SIM (hit): retorna o dado rapidamente
4. Se NÃO (miss): busca na memória principal,
   armazena na cache e retorna para a CPU
```

#### 4.2 Níveis de Cache

```
┌─────────────────────────────────────────────────────────┐
│                    CPU MODERNA                            │
│                                                          │
│  ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐  │
│  │ Núcleo 0│  │ Núcleo 1│  │ Núcleo 2│  │ Núcleo 3│  │
│  │┌──┐┌──┐│  │┌──┐┌──┐│  │┌──┐┌──┐│  │┌──┐┌──┐│  │
│  ││L1││L1││  ││L1││L1││  ││L1││L1││  ││L1││L1││  │
│  ││ I││ D││  ││ I││ D││  ││ I││ D││  ││ I││ D││  │
│  │└──┘└──┘│  │└──┘└──┘│  │└──┘└──┘│  │└──┘└──┘│  │
│  │ ┌────┐ │  │ ┌────┐ │  │ ┌────┐ │  │ ┌────┐ │  │
│  │ │ L2 │ │  │ │ L2 │ │  │ │ L2 │ │  │ │ L2 │ │  │
│  │ └────┘ │  │ └────┘ │  │ └────┘ │  │ └────┘ │  │
│  └────┬────┘  └────┬────┘  └────┬────┘  └────┬────┘  │
│       └────────────┴────────────┴────────────┘        │
│                         │                              │
│              ┌──────────┴──────────┐                   │
│              │     Cache L3        │                   │
│              │  (Compartilhada)    │                   │
│              │     8 - 64 MB       │                   │
│              └─────────────────────┘                   │
└─────────────────────────────────────────────────────────┘
```

| Nível | Tamanho Típico | Latência | Escopo | Divisão |
|-------|---------------|----------|--------|---------|
| **L1 I-Cache** | 32-64 KB | 1-4 ciclos | Por núcleo | Apenas instruções |
| **L1 D-Cache** | 32-64 KB | 1-4 ciclos | Por núcleo | Apenas dados |
| **L2** | 256 KB - 1 MB | 4-12 ciclos | Por núcleo | Unificada |
| **L3** | 4-64 MB | 12-40 ciclos | Compartilhada | Unificada |

#### 4.3 Estrutura de uma Linha de Cache

```
Uma linha (bloco) de cache contém:

┌───────┬────────────────────┬────────────────────────────┐
│ Valid │      Tag           │         Dados              │
│ (1 bit)│ (identifica o     │   (bloco de dados da       │
│       │  endereço na RAM)  │    memória principal)      │
│       │                    │   Tipicamente 64 bytes     │
└───────┴────────────────────┴────────────────────────────┘

Valid = 1: Linha contém dados válidos
Valid = 0: Linha está vazia/inválida
Tag: Identifica qual bloco da memória está armazenado
Dados: Os bytes copiados da memória principal
```

#### 4.4 Mapeamento Direto (Direct Mapping)

Cada bloco da memória principal pode ir para **apenas UMA** posição na cache.

```
Fórmula: Linha da Cache = (Endereço do Bloco) mod (Número de Linhas)

Exemplo: Cache com 4 linhas, blocos de 4 bytes

Endereço (12 bits):
┌─────────┬──────────┬──────────┐
│   Tag   │  Índice  │  Offset  │
│ (8 bits)│ (2 bits) │ (2 bits) │
└─────────┴──────────┴──────────┘

Índice: Seleciona a linha da cache (2 bits → 4 linhas)
Offset: Seleciona o byte dentro do bloco (2 bits → 4 bytes)
Tag: Identifica qual bloco está na linha

Memória Principal:           Cache (4 linhas):
┌──────────────┐            ┌───┬─────┬──────────┐
│ Bloco 0      │──────────►│ V │ Tag │ Dados    │ Linha 0
│ Bloco 1      │──────────►│ V │ Tag │ Dados    │ Linha 1
│ Bloco 2      │──────────►│ V │ Tag │ Dados    │ Linha 2
│ Bloco 3      │──────────►│ V │ Tag │ Dados    │ Linha 3
│ Bloco 4      │──────────►│   │     │          │ Linha 0 (!)
│ Bloco 5      │──────────►│   │     │          │ Linha 1 (!)
│ Bloco 6      │──────────►│   │     │          │ Linha 2 (!)
│ Bloco 7      │──────────►│   │     │          │ Linha 3 (!)
│    ...        │            └───┴─────┴──────────┘
└──────────────┘
⚠️ Blocos 0 e 4 competem pela mesma linha!
```

#### 4.5 Mapeamento Totalmente Associativo (Fully Associative)

Cada bloco da memória pode ir para **QUALQUER** linha da cache.

```
Endereço:
┌──────────────────┬──────────┐
│       Tag        │  Offset  │
│   (10 bits)      │ (2 bits) │
└──────────────────┴──────────┘

Não tem campo "índice" — qualquer linha é candidata!

Cache (4 linhas):
┌───┬──────────┬──────────┐
│ V │   Tag    │  Dados   │ Linha 0 ← Qualquer bloco pode vir aqui
├───┼──────────┼──────────┤
│ V │   Tag    │  Dados   │ Linha 1 ← Qualquer bloco pode vir aqui
├───┼──────────┼──────────┤
│ V │   Tag    │  Dados   │ Linha 2 ← Qualquer bloco pode vir aqui
├───┼──────────┼──────────┤
│ V │   Tag    │  Dados   │ Linha 3 ← Qualquer bloco pode vir aqui
└───┴──────────┴──────────┘

Vantagem: Nenhum conflito de mapeamento!
Desvantagem: Precisa comparar a tag com TODAS as linhas
             (hardware comparador muito caro)
```

#### 4.6 Mapeamento Associativo por Conjunto (Set-Associative)

Combina os dois anteriores: a cache é dividida em **conjuntos**, e dentro de cada conjunto o mapeamento é associativo.

```
Cache 2-way set-associative com 4 conjuntos:

Endereço:
┌─────────┬──────────┬──────────┐
│   Tag   │  Índice  │  Offset  │
│ (8 bits)│ (2 bits) │ (2 bits) │
└─────────┴──────────┴──────────┘

Índice: Seleciona o CONJUNTO
Dentro do conjunto: Pode ir em qualquer uma das 2 vias (ways)

┌─────────┬───┬─────┬────────┬───┬─────┬────────┐
│ Conjunto│ V │ Tag │ Dados  │ V │ Tag │ Dados  │
│         │   │     │ (Via 0)│   │     │(Via 1) │
├─────────┼───┼─────┼────────┼───┼─────┼────────┤
│    0    │ 1 │ 0x3A│ ...    │ 1 │ 0x7E│ ...    │
│    1    │ 1 │ 0x12│ ...    │ 0 │  -  │  -     │
│    2    │ 1 │ 0x5C│ ...    │ 1 │ 0x9F│ ...    │
│    3    │ 1 │ 0x01│ ...    │ 1 │ 0x45│ ...    │
└─────────┴───┴─────┴────────┴───┴─────┴────────┘

Total de linhas: 4 conjuntos × 2 vias = 8 linhas

Comparação precisa verificar apenas 2 tags (vs. todas no full associative)
```

#### 4.7 Comparação dos Mapeamentos

| Característica | Direto | Assoc. por Conjunto | Totalmente Assoc. |
|---------------|--------|--------------------|--------------------|
| Flexibilidade | Baixa | Média | Alta |
| Conflitos | Muitos | Poucos | Nenhum |
| Hardware comparador | 1 comparação | N comparações (N-way) | Todas as linhas |
| Complexidade | Simples | Moderada | Alta |
| Custo | Baixo | Médio | Alto |
| Uso prático | Raro atualmente | **Mais comum** | Caches muito pequenas |

---

### 5. Políticas de Substituição 🔄

Quando a cache está cheia e ocorre um miss, **qual linha deve ser removida** para dar lugar ao novo bloco?

#### 5.1 LRU (Least Recently Used)

```
Remove o bloco que foi acessado há MAIS TEMPO.

Exemplo (cache com 4 blocos, acesso: A, B, C, D, E, A):

Passo 1: A → [A, -, -, -]        (Miss, cache vazia)
Passo 2: B → [A, B, -, -]        (Miss, cache vazia)
Passo 3: C → [A, B, C, -]        (Miss, cache vazia)
Passo 4: D → [A, B, C, D]        (Miss, cache cheia)
Passo 5: E → [E, B, C, D]        (Miss, remove A - LRU)
Passo 6: A → [E, A, C, D]        (Miss, remove B - LRU)

✅ Vantagem: Bom aproveitamento da localidade temporal
❌ Desvantagem: Precisa rastrear a ordem de uso (hardware complexo)
```

#### 5.2 FIFO (First In, First Out)

```
Remove o bloco que está na cache há MAIS TEMPO (independente do uso).

Exemplo (cache com 4 blocos, acesso: A, B, C, D, E, A):

Passo 1: A → [A, -, -, -]        (Miss)
Passo 2: B → [A, B, -, -]        (Miss)
Passo 3: C → [A, B, C, -]        (Miss)
Passo 4: D → [A, B, C, D]        (Miss, cache cheia)
Passo 5: E → [E, B, C, D]        (Miss, remove A - primeiro a entrar)
Passo 6: A → [E, A, C, D]        (Miss, remove B - primeiro a entrar)

✅ Vantagem: Implementação simples (fila circular)
❌ Desvantagem: Pode remover blocos ainda muito usados
```

#### 5.3 Random (Aleatório)

```
Remove um bloco ALEATORIAMENTE.

✅ Vantagem: Implementação muito simples
❌ Desvantagem: Desempenho imprevisível
📌 Na prática: desempenho surpreendentemente próximo do LRU
   para caches grandes!
```

#### 5.4 Comparação de Políticas

| Política | Complexidade | Desempenho | Hardware |
|----------|-------------|------------|----------|
| **LRU** | Alta | Melhor | Contadores/bits por linha |
| **FIFO** | Média | Bom | Ponteiro circular |
| **Random** | Baixa | Razoável | Gerador pseudoaleatório |
| **LFU** | Muito alta | Variável | Contadores de frequência |

---

### 6. Políticas de Escrita ✍️

#### 6.1 Write-Through (Escrita Direta)

```
Toda escrita atualiza SIMULTANEAMENTE a cache E a memória principal.

CPU ──escrita──► Cache ──escrita──► Memória Principal
                  │                       │
               Atualiza               Atualiza
              imediatamente          imediatamente

✅ Vantagens:
  - Memória principal sempre atualizada
  - Recuperação simples em caso de falha
  - Mais simples de implementar

❌ Desvantagens:
  - Mais lento (toda escrita vai até a RAM)
  - Maior tráfego no barramento de memória

🔧 Otimização: Write Buffer
  CPU → Cache → Write Buffer → Memória (em background)
```

#### 6.2 Write-Back (Escrita Retroativa)

```
Escrita atualiza APENAS a cache. A memória é atualizada
somente quando a linha é REMOVIDA da cache.

CPU ──escrita──► Cache    (Memória NÃO é atualizada agora)
                  │
               Marca como
               "dirty" (D=1)
                  │
            ... mais tarde, quando a linha é substituída ...
                  │
               Cache ──escrita──► Memória Principal
               (Dirty writeback)

✅ Vantagens:
  - Mais rápido para escritas (não espera a RAM)
  - Menos tráfego no barramento
  - Múltiplas escritas ao mesmo bloco = 1 escrita na RAM

❌ Desvantagens:
  - Memória principal pode estar desatualizada
  - Mais complexo (precisa do bit "dirty")
  - Recuperação mais complexa em caso de falha

Linha de Cache com bit dirty:
┌───────┬───────┬────────────────┬────────────────────────┐
│ Valid │ Dirty │      Tag       │         Dados          │
│  (V)  │  (D)  │                │                        │
└───────┴───────┴────────────────┴────────────────────────┘
```

#### 6.3 Tratamento de Write Miss

| Estratégia | Ação no Miss de Escrita |
|------------|------------------------|
| **Write Allocate** | Traz o bloco para a cache, depois escreve nele (usado com Write-Back) |
| **No Write Allocate** | Escreve diretamente na memória, sem trazer para cache (usado com Write-Through) |

---

### 7. Armazenamento Magnético: Disco Rígido (HDD) 🧲

#### 7.1 Estrutura Física do HDD

```
          Vista Superior (Prato)
        ┌─────────────────────────┐
       ╱                           ╲
      │    ╭─────────────────╮      │
      │   ╱  Trilha externa   ╲     │ ← Trilha (Track)
      │  │  ╭───────────────╮  │    │
      │  │ ╱   Trilha média  ╲ │    │
      │  ││  ╭─────────────╮ ││    │
      │  ││ │   Trilha     │ ││    │
      │  ││ │  interna ●   │ ││    │ ● = Eixo (Spindle)
      │  ││ │  (centro)    │ ││    │
      │  ││  ╰─────────────╯ ││    │
      │  │ ╲                 ╱ │    │
      │  │  ╰───────────────╯  │    │
      │   ╲                   ╱     │
      │    ╰─────────────────╯      │
      │                             │
      │   ══════════════╗           │ ← Braço atuador
      │                  ║ Cabeça   │
       ╲                 ║ de       ╱
        └────────────────║────────┘
                         ║ Leitura/
                         ║ Escrita
```

```
          Vista Lateral (Múltiplos Pratos)
        ┌───────────────────────────┐
        │  ──── Cabeça de leitura   │
        │  ═══  Prato (superfície)  │
        │  ──── Cabeça de leitura   │
        │                           │
        │  ──── Cabeça              │
        │  ═══  Prato               │
        │  ──── Cabeça              │
        │                           │
        │  ──── Cabeça              │
        │  ═══  Prato               │
        │  ──── Cabeça              │
        │           │               │
        │           │ Eixo          │
        │        ┌──┴──┐            │
        │        │Motor│            │
        └────────┴─────┴────────────┘
```

#### 7.2 Terminologia do HDD

| Termo | Definição |
|-------|-----------|
| **Prato (Platter)** | Disco circular revestido de material magnético |
| **Superfície (Surface)** | Cada face de um prato (2 superfícies por prato) |
| **Trilha (Track)** | Círculo concêntrico na superfície do prato |
| **Setor (Sector)** | Menor unidade endereçável (tipicamente 512 bytes ou 4 KB) |
| **Cilindro (Cylinder)** | Conjunto de trilhas na mesma posição em todos os pratos |
| **Cabeça (Head)** | Dispositivo que lê/escreve dados magnéticos |
| **Braço Atuador** | Mecanismo que posiciona a cabeça sobre a trilha desejada |
| **RPM** | Rotações por minuto do prato (5400, 7200, 10000, 15000) |

#### 7.3 Tempos de Acesso do HDD

```
Tempo total de acesso = Seek Time + Rotational Latency + Transfer Time

┌──────────────────┐
│   Seek Time      │ Tempo para mover a cabeça até a trilha correta
│   (3-15 ms)      │ Depende da distância entre trilhas
├──────────────────┤
│   Rotational     │ Tempo para o setor desejado girar até a cabeça
│   Latency        │ Em média: metade de uma rotação
│   (2-6 ms)       │ 7200 RPM → 1 rotação = 8,33 ms → latência = 4,17 ms
├──────────────────┤
│   Transfer Time  │ Tempo para ler/escrever os dados do setor
│   (0,01-0,1 ms)  │ Depende da taxa de transferência e tamanho do setor
└──────────────────┘

Exemplo para HDD 7200 RPM:
  Seek Time médio:      8 ms
  Rotational Latency:   4,17 ms (metade de 8,33 ms)
  Transfer Time:        0,01 ms (1 setor de 512 bytes)
  ────────────────────────────
  TOTAL:                ~12,18 ms

Comparação: RAM acessa em ~100 ns = 0,0001 ms
           HDD: ~12 ms → ~120.000x mais lento que a RAM!
```

---

### 8. Armazenamento Óptico 💿

#### 8.1 Tecnologias Ópticas

```
CD (Compact Disc):
  ┌──────────────────────┐
  │  Laser infravermelho  │  λ = 780 nm
  │  Capacidade: 700 MB   │
  │  1x = 150 KB/s        │
  │  Trilha: espiral única │
  └──────────────────────┘

DVD (Digital Versatile Disc):
  ┌──────────────────────┐
  │  Laser vermelho       │  λ = 650 nm
  │  Capacidade: 4,7 GB   │  (single-layer)
  │           ou 8,5 GB   │  (dual-layer)
  │  1x = 1,35 MB/s       │
  └──────────────────────┘

Blu-ray:
  ┌──────────────────────┐
  │  Laser azul-violeta   │  λ = 405 nm
  │  Capacidade: 25 GB    │  (single-layer)
  │           ou 50 GB    │  (dual-layer)
  │  1x = 4,5 MB/s        │
  └──────────────────────┘
```

#### 8.2 Como Funciona a Leitura Óptica

```
Superfície do disco óptico:

    ╔══╗    ╔══╗         ╔════╗    ╔═╗
────╝  ╚────╝  ╚─────────╝    ╚────╝ ╚────
    pit  land  pit  land    pit    land pit

Pit (cavidade): Área rebaixada no disco
Land (planície): Área plana no disco

O laser reflete de forma diferente nas transições:
- Transição pit→land ou land→pit = bit 1
- Sem transição = bit 0

O detector mede a intensidade da luz refletida
para decodificar os dados.
```

#### 8.3 Comparação de Mídias Ópticas

| Característica | CD | DVD | Blu-ray |
|---------------|-----|-----|---------|
| Comprimento de onda | 780 nm | 650 nm | 405 nm |
| Capacidade (1 camada) | 700 MB | 4,7 GB | 25 GB |
| Capacidade (2 camadas) | — | 8,5 GB | 50 GB |
| Trilha mínima (pitch) | 1,6 μm | 0,74 μm | 0,32 μm |
| Velocidade 1x | 150 KB/s | 1,35 MB/s | 4,5 MB/s |
| Lançamento | 1982 | 1995 | 2006 |

---

### 9. SSD (Solid State Drive) e Tecnologia NAND Flash ⚡

#### 9.1 Estrutura do SSD

```
┌────────────────────────────────────────────────────┐
│                    SSD                              │
│  ┌──────────────┐  ┌──────┐  ┌──────────────────┐ │
│  │ Controlador  │  │ DRAM │  │                   │ │
│  │   (FTL,      │  │Buffer│  │   Chips NAND      │ │
│  │  wear level, │  │      │  │   Flash           │ │
│  │  ECC, etc.)  │  │      │  │   ┌───┐ ┌───┐    │ │
│  └──────────────┘  └──────┘  │   │ N │ │ N │    │ │
│                               │   │ A │ │ A │    │ │
│  Interface: SATA ou NVMe      │   │ N │ │ N │    │ │
│  (PCIe)                       │   │ D │ │ D │    │ │
│                               │   └───┘ └───┘    │ │
│                               │   ┌───┐ ┌───┐    │ │
│                               │   │ N │ │ N │    │ │
│                               │   │ A │ │ A │    │ │
│                               │   │ N │ │ N │    │ │
│                               │   │ D │ │ D │    │ │
│                               │   └───┘ └───┘    │ │
│                               └──────────────────┘ │
└────────────────────────────────────────────────────┘
```

#### 9.2 Tipos de NAND Flash

```
Tipos de células NAND:

SLC (Single-Level Cell):    1 bit por célula
  Tensão: [0V]─────[Vth]─────[Vmax]
          │    0    │    1    │
  - Mais rápida e durável
  - Mais cara e menor capacidade
  - ~100.000 ciclos de escrita

MLC (Multi-Level Cell):     2 bits por célula
  Tensão: [0V]──[V1]──[V2]──[V3]──[Vmax]
          │  11  │ 10  │ 00  │  01  │
  - Equilíbrio entre desempenho e capacidade
  - ~10.000 ciclos de escrita

TLC (Triple-Level Cell):    3 bits por célula
  Tensão: [0V]─[V1]─[V2]─[V3]─[V4]─[V5]─[V6]─[V7]─[Vmax]
          │ 111│110│100│101│001│000│010│011│
  - Maior capacidade, mais barata
  - ~3.000 ciclos de escrita

QLC (Quad-Level Cell):      4 bits por célula
  - 16 níveis de tensão
  - Máxima capacidade, menor custo
  - ~1.000 ciclos de escrita
```

| Tipo | Bits/Célula | Velocidade | Durabilidade | Custo | Uso |
|------|-------------|------------|--------------|-------|-----|
| SLC | 1 | Mais rápida | ~100K ciclos | $$$$$ | Empresarial |
| MLC | 2 | Rápida | ~10K ciclos | $$$ | Workstations |
| TLC | 3 | Moderada | ~3K ciclos | $$ | Consumidor |
| QLC | 4 | Mais lenta | ~1K ciclos | $ | Armazenamento em massa |

#### 9.3 SSD vs HDD

| Aspecto | HDD | SSD SATA | SSD NVMe |
|---------|-----|----------|----------|
| Leitura sequencial | 80-200 MB/s | 500-550 MB/s | 3.000-7.000 MB/s |
| Escrita sequencial | 80-180 MB/s | 450-520 MB/s | 2.000-5.000 MB/s |
| Leitura aleatória (IOPS) | 50-200 | 50.000-100.000 | 500.000-1.000.000 |
| Latência | 3-15 ms | 25-100 μs | 10-20 μs |
| Partes móveis | Sim | Não | Não |
| Resistência a choque | Baixa | Alta | Alta |
| Consumo de energia | 6-15 W | 2-5 W | 3-8 W |
| Ruído | Sim | Não | Não |
| Vida útil (escritas) | Ilimitada* | Limitada (TBW) | Limitada (TBW) |
| Custo por GB | R$ 0,08-0,15 | R$ 0,30-0,60 | R$ 0,40-0,80 |

---

### 10. Tabela Comparativa Geral 📊

| Tecnologia | Velocidade | Capacidade | Custo/GB | Volátil | Uso Principal |
|-----------|-----------|-----------|---------|---------|--------------|
| **SRAM** | < 10 ns | KB-MB | $$$$$ | Sim | Cache |
| **DRAM (DDR5)** | 50-100 ns | GB | $$$ | Sim | RAM |
| **Flash NOR** | 70 ns (leitura) | MB | $$$$ | Não | Firmware |
| **Flash NAND SLC** | 25 μs | GB | $$$$ | Não | SSD empresarial |
| **Flash NAND TLC** | 50 μs | TB | $$ | Não | SSD consumidor |
| **HDD** | 3-10 ms | TB | $ | Não | Armazenamento massa |
| **CD/DVD/Blu-ray** | ms | GB | ¢ | Não | Mídia, distribuição |
| **Fita Magnética** | s-min | PB | ¢¢ | Não | Backup corporativo |

---

### 11. Resumo da Aula 📝

```
┌──────────────────────────────────────────────────────────┐
│          RESUMO - MEMÓRIAS E ARMAZENAMENTO               │
├──────────────────────────────────────────────────────────┤
│                                                          │
│  RAM:                                                    │
│  • SRAM (6 transistores, sem refresh) → Cache            │
│  • DRAM (1T+1C, com refresh) → Memória principal         │
│                                                          │
│  ROM: ROM → PROM → EPROM → EEPROM → Flash                │
│                                                          │
│  Cache:                                                  │
│  • Mapeamento: Direto, Associativo, Assoc. por Conjunto  │
│  • Substituição: LRU, FIFO, Random                       │
│  • Escrita: Write-Through, Write-Back                    │
│                                                          │
│  HDD: Pratos magnéticos, trilhas, setores, cilindros     │
│  Óptico: CD (700 MB), DVD (4,7 GB), Blu-ray (25 GB)     │
│  SSD: NAND Flash (SLC, MLC, TLC, QLC)                   │
│                                                          │
│  Tendência: SSDs substituindo HDDs para uso geral        │
│  HDDs permanecem para armazenamento em massa             │
└──────────────────────────────────────────────────────────┘
```

---

## 🔗 Referências

1. STALLINGS, W. **Arquitetura e Organização de Computadores**. 10ª ed. Pearson, 2017. Capítulos 4 e 5.
2. TANENBAUM, A. S. **Organização Estruturada de Computadores**. 6ª ed. Pearson, 2013. Capítulos 2 e 4.
3. PATTERSON, D.; HENNESSY, J. **Organização e Projeto de Computadores**. 5ª ed. Elsevier, 2017. Capítulo 5.
4. HENNESSY, J.; PATTERSON, D. **Arquitetura de Computadores: Uma Abordagem Quantitativa**. 6ª ed. Elsevier, 2019.

---

## ➡️ Próxima Aula

**Aula 15 - Sistema de Entrada e Saída (E/S):** Módulos de E/S, técnicas de transferência (polling, interrupção, DMA), barramentos e padrões de interface.

---

> 💡 **Dica de estudo:** Pratique a decomposição de endereços em Tag, Índice e Offset para os três tipos de mapeamento. Esse é o conceito mais cobrado em provas!
