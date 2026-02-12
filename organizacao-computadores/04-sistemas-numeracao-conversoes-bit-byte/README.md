# 🔢 Aula 04 — Sistemas de Numeração: Conversões, Bit e Byte

> **Disciplina:** Organização de Computadores  
> **Duração:** 2 horas-aula  
> **Nível:** Iniciante a Intermediário  
> **Pré-requisitos:** Aulas 02 e 03 — Sistemas de Numeração

---

## 🎯 Objetivos de Aprendizado

Ao final desta aula, o estudante será capaz de:

- ✅ Realizar conversões entre quaisquer bases numéricas
- ✅ Definir **bit** como a menor unidade de informação
- ✅ Definir **byte** e explicar por que agrupa 8 bits
- ✅ Usar corretamente as unidades de medida (KB, MB, GB, TB, PB)
- ✅ Distinguir unidades IEC (KiB, MiB) de unidades SI (KB, MB)
- ✅ Explicar como texto é representado em binário (ASCII e Unicode)
- ✅ Aplicar esses conceitos em situações reais (armazenamento, velocidade de rede)

---

## 📋 Sumário

1. [Conversão Geral entre Bases](#1--conversão-geral-entre-bases)
2. [O Bit — A Menor Unidade](#2--o-bit--a-menor-unidade)
3. [O Byte — Por que 8 Bits?](#3--o-byte--por-que-8-bits)
4. [Unidades de Medida de Dados](#4--unidades-de-medida-de-dados)
5. [IEC vs. SI — A Confusão das Unidades](#5--iec-vs-si--a-confusão-das-unidades)
6. [Representação de Texto: ASCII e Unicode](#6--representação-de-texto-ascii-e-unicode)
7. [Aplicações no Mundo Real](#7--aplicações-no-mundo-real)
8. [Por que Isso Importa para um Cientista da Computação?](#8--por-que-isso-importa-para-um-cientista-da-computação)
9. [Resumo](#9--resumo)
10. [Leitura Complementar](#10--leitura-complementar)

---

## 1. 🔄 Conversão Geral entre Bases

Nas aulas anteriores, aprendemos a converter entre decimal, binário, octal e hexadecimal. Agora vamos generalizar o processo para **qualquer base**.

### 1.1 Método Universal: Base X → Decimal

Para converter de **qualquer base** para decimal, use o método dos **pesos posicionais**:

```
    N = dₙ × bⁿ + dₙ₋₁ × bⁿ⁻¹ + ... + d₁ × b¹ + d₀ × b⁰
```

**Exemplo: Converter 2102₃ (base 3) para decimal**

```
    2 × 3³ = 2 × 27 = 54
    1 × 3² = 1 × 9  =  9
    0 × 3¹ = 0 × 3  =  0
    2 × 3⁰ = 2 × 1  =  2
                       ────
    Total:             65

    Resultado: 2102₃ = 65₁₀
```

**Exemplo: Converter 432₅ (base 5) para decimal**

```
    4 × 5² = 4 × 25 = 100
    3 × 5¹ = 3 × 5  =  15
    2 × 5⁰ = 2 × 1  =   2
                       ─────
    Total:             117

    Resultado: 432₅ = 117₁₀
```

### 1.2 Método Universal: Decimal → Base X

Para converter de decimal para **qualquer base**, use **divisões sucessivas pela base de destino**:

**Exemplo: Converter 100₁₀ para base 3**

```
    100 ÷ 3 = 33  resto  1  ↑
     33 ÷ 3 = 11  resto  0  │
     11 ÷ 3 =  3  resto  2  │  Leia de baixo para cima
      3 ÷ 3 =  1  resto  0  │
      1 ÷ 3 =  0  resto  1  │

    Resultado: 100₁₀ = 10201₃
```

**Verificação:** 1×81 + 0×27 + 2×9 + 0×3 + 1×1 = 81 + 0 + 18 + 0 + 1 = **100** ✓

### 1.3 Conversão Base X → Base Y (via Decimal)

Para converter entre duas bases **quaisquer**, use o **decimal como ponte**:

```
    Base X  ──→  DECIMAL  ──→  Base Y
           (pesos)      (divisões)
```

**Exemplo: Converter 321₅ para base 7**

```
    PASSO 1: Base 5 → Decimal
    3 × 25 + 2 × 5 + 1 × 1 = 75 + 10 + 1 = 86₁₀

    PASSO 2: Decimal → Base 7
    86 ÷ 7 = 12  resto  2  ↑
    12 ÷ 7 =  1  resto  5  │
     1 ÷ 7 =  0  resto  1  │

    Resultado: 321₅ = 152₇
```

> 💡 **Exceção:** Para binário ↔ octal e binário ↔ hex, use o método de **agrupamento** (muito mais rápido do que passar pelo decimal).

---

## 2. 💡 O Bit — A Menor Unidade

### 2.1 Definição

O **bit** (Binary Digit — dígito binário) é a **menor unidade de informação** em computação.

```
    ┌───────────────────────────────────┐
    │           UM BIT                  │
    │                                   │
    │    Pode ser:  0  ou  1            │
    │                                   │
    │    Representa UMA decisão binária │
    │    (sim/não, ligado/desligado,    │
    │     verdadeiro/falso)             │
    └───────────────────────────────────┘
```

### 2.2 Capacidade de Representação

A quantidade de informação que podemos representar cresce **exponencialmente** com o número de bits:

| Bits | Combinações (2ⁿ) | Faixa de Valores | Exemplo de Uso |
|------|------------------|-------------------|----------------|
| 1 | 2 | 0-1 | Ligado/desligado |
| 2 | 4 | 0-3 | Direções (N, S, L, O) |
| 3 | 8 | 0-7 | Dígito octal |
| 4 | 16 | 0-15 | Dígito hexadecimal |
| 7 | 128 | 0-127 | Caractere ASCII |
| 8 | 256 | 0-255 | 1 byte / componente de cor RGB |
| 10 | 1.024 | 0-1023 | ~1 mil valores |
| 16 | 65.536 | 0-65535 | Porta TCP/UDP |
| 20 | 1.048.576 | 0-1048575 | ~1 milhão de valores |
| 32 | 4.294.967.296 | 0-~4 bilhões | Endereço IPv4 |
| 64 | 1,8 × 10¹⁹ | — | Endereço de memória moderno |
| 128 | 3,4 × 10³⁸ | — | Endereço IPv6 |
| 256 | 1,2 × 10⁷⁷ | — | Chave criptográfica AES-256 |

> 💡 **Perspectiva:** Com 256 bits, temos mais combinações do que **átomos no universo observável** (estimado em ~10⁸⁰). É por isso que criptografia de 256 bits é considerada virtualmente inquebrável por força bruta!

### 2.3 Informação e Entropia

O conceito de bit foi formalizado por **Claude Shannon** em 1948, no artigo *"A Mathematical Theory of Communication"* — o marco fundador da **Teoria da Informação**.

> 💡 **Definição formal:** 1 bit é a quantidade de informação necessária para resolver uma incerteza entre **duas alternativas igualmente prováveis**.

**Exemplo:** Se alguém joga uma moeda e você pergunta "deu cara?", a resposta ("sim" ou "não") contém **exatamente 1 bit** de informação.

---

## 3. 📦 O Byte — Por que 8 Bits?

### 3.1 Definição

Um **byte** é um grupo de **8 bits**. É a unidade fundamental de **armazenamento e endereçamento** na maioria dos computadores.

```
    UM BYTE = 8 BITS

    ┌───┬───┬───┬───┬───┬───┬───┬───┐
    │ 0 │ 1 │ 1 │ 0 │ 0 │ 1 │ 0 │ 1 │  = 01100101₂ = 101₁₀ = 65₁₆
    └───┴───┴───┴───┴───┴───┴───┴───┘
    bit7 bit6 bit5 bit4 bit3 bit2 bit1 bit0
    MSB                              LSB

    MSB = Most Significant Bit (bit mais significativo)
    LSB = Least Significant Bit (bit menos significativo)
```

### 3.2 Por que Exatamente 8 Bits?

A escolha de 8 bits não é arbitrária. Há razões históricas e práticas:

| Razão | Explicação |
|-------|-----------|
| **Potência de 2** | 8 = 2³ → simplifica o hardware |
| **ASCII** | 7 bits para caracteres + 1 bit de paridade = 8 |
| **Suficiente para texto** | 256 valores cobrem o alfabeto latino, números e símbolos |
| **Dividivisível** | 8 se divide em 2 nibbles de 4 bits (1 dígito hex cada) |
| **IBM System/360** | A IBM padronizou o byte de 8 bits em 1964 |

> 💡 **Curiosidade histórica:** Nem sempre foram 8 bits! O termo "byte" foi cunhado por Werner Buchholz em 1956 na IBM. Antes da padronização, existiam bytes de 6, 7 e até 9 bits em diferentes máquinas.

### 3.3 Nibble — Meio Byte

Um **nibble** (ou nybble) é um grupo de **4 bits** — exatamente **meio byte**:

```
    1 BYTE = 2 NIBBLES

    ┌───────────┬───────────┐
    │  NIBBLE   │  NIBBLE   │
    │   alto    │   baixo   │
    ├───┬───┬───┼───┬───┬───┤
    │ 1 │ 0 │ 1 │ 0 │ 1 │ 1 │ ...espere, isso são 6 bits!
    └───┴───┴───┴───┴───┴───┘

    Correto:
    ┌───┬───┬───┬───┬───┬───┬───┬───┐
    │ 1 │ 0 │ 1 │ 0 │ 1 │ 0 │ 1 │ 1 │ = AB₁₆
    └───┴───┴───┴───┴───┴───┴───┴───┘
    ├── nibble alto ──┤├── nibble baixo ─┤
         1010 = A₁₆       1011 = B₁₆
```

> 💡 **Cada nibble corresponde a exatamente 1 dígito hexadecimal!** É por isso que 1 byte = 2 dígitos hex (00 a FF).

### 3.4 Word (Palavra)

A **palavra** (word) é a unidade natural de processamento da CPU:

| Arquitetura | Tamanho da Palavra | Bytes |
|------------|-------------------|-------|
| 8 bits (antigos) | 8 bits | 1 byte |
| 16 bits (8086) | 16 bits | 2 bytes |
| 32 bits (x86) | 32 bits | 4 bytes |
| 64 bits (x86-64) | 64 bits | 8 bytes |

> 💡 Quando dizemos que um processador é "64 bits", significa que ele processa **palavras de 64 bits** (8 bytes) de cada vez.

---

## 4. 📐 Unidades de Medida de Dados

### 4.1 Unidades Tradicionais (Base 2 — Potências de 1024)

Na computação, as unidades foram historicamente baseadas em **potências de 2**:

| Unidade | Abreviação | Valor em Bytes | Potência de 2 |
|---------|-----------|---------------|----------------|
| Byte | B | 1 | 2⁰ |
| Kilobyte | KB | 1.024 | 2¹⁰ |
| Megabyte | MB | 1.048.576 | 2²⁰ |
| Gigabyte | GB | 1.073.741.824 | 2³⁰ |
| Terabyte | TB | 1.099.511.627.776 | 2⁴⁰ |
| Petabyte | PB | 1.125.899.906.842.624 | 2⁵⁰ |
| Exabyte | EB | 1.152.921.504.606.846.976 | 2⁶⁰ |

```
    ESCALA DE UNIDADES (Base 2)

    1 EB = 1024 PB
    1 PB = 1024 TB
    1 TB = 1024 GB
    1 GB = 1024 MB      ← Cada nível é 1024× o anterior
    1 MB = 1024 KB
    1 KB = 1024 Bytes
    1 Byte = 8 bits
```

### 4.2 Visualização das Grandezas

```
    1 Byte         ≈ 1 caractere de texto
    1 KB (1.024 B) ≈ 1 parágrafo de texto
    1 MB           ≈ 1 livro pequeno / 1 foto em baixa resolução
    1 GB           ≈ ~250 músicas MP3 / 1 filme em baixa qualidade
    1 TB           ≈ ~500 horas de vídeo HD / biblioteca inteira
    1 PB           ≈ ~20 milhões de arquivos de escritório
    1 EB           ≈ Todo o tráfego da internet em ~1 dia
```

---

## 5. ⚖️ IEC vs. SI — A Confusão das Unidades

### 5.1 O Problema

Os prefixos "kilo", "mega", "giga" vêm do **Sistema Internacional (SI)** e significam:
- Kilo = 1.000 (10³)
- Mega = 1.000.000 (10⁶)
- Giga = 1.000.000.000 (10⁹)

Mas na computação, usamos:
- "Kilo" = 1.024 (2¹⁰)
- "Mega" = 1.048.576 (2²⁰)
- "Giga" = 1.073.741.824 (2³⁰)

> ⚠️ **Isso causa confusão!** Quando um fabricante vende um HD de "1 TB", ele quer dizer 1.000.000.000.000 bytes (SI). Mas o sistema operacional conta 1 TB como 1.099.511.627.776 bytes (binário). Resultado: o HD parece ter "menos" do que o anunciado!

### 5.2 A Solução IEC (1998)

Em 1998, a **IEC** (International Electrotechnical Commission) criou novos prefixos para potências de 2:

| Unidade SI (base 10) | Valor | Unidade IEC (base 2) | Valor |
|----------------------|-------|---------------------|-------|
| 1 KB (kilobyte) | 1.000 B | 1 KiB (kibibyte) | 1.024 B |
| 1 MB (megabyte) | 1.000.000 B | 1 MiB (mebibyte) | 1.048.576 B |
| 1 GB (gigabyte) | 1.000.000.000 B | 1 GiB (gibibyte) | 1.073.741.824 B |
| 1 TB (terabyte) | 1.000.000.000.000 B | 1 TiB (tebibyte) | 1.099.511.627.776 B |

```
    COMPARAÇÃO VISUAL

    SI (base 10):    1 KB = 1.000 bytes
    IEC (base 2):    1 KiB = 1.024 bytes
    Diferença:       2,4%

    SI (base 10):    1 TB = 1.000.000.000.000 bytes
    IEC (base 2):    1 TiB = 1.099.511.627.776 bytes
    Diferença:       ~10% ← A diferença CRESCE com a unidade!
```

### 5.3 Exemplo Prático: O "HD que Encolhe"

```
    Você compra um SSD anunciado como "512 GB":

    Fabricante calcula: 512 × 1.000.000.000 = 512.000.000.000 bytes

    Seu Windows mostra:  512.000.000.000 ÷ 1.073.741.824 ≈ 476,84 GB

    → O SSD "perdeu" ~35 GB? Não! São as mesmas quantidades de bytes,
      apenas medidos com regras diferentes.
```

### 5.4 Quem Usa Cada Unidade?

| Usa Base 10 (SI) | Usa Base 2 (IEC) |
|------------------|------------------|
| Fabricantes de HD/SSD | Sistemas operacionais (Windows) |
| Provedores de internet | Linux (geralmente mostra GiB) |
| Fabricantes de RAM (marketing) | Programadores |
| Telecomunicações | Especificações técnicas |

---

## 6. 🔤 Representação de Texto: ASCII e Unicode

### 6.1 ASCII — American Standard Code for Information Interchange

O **ASCII** (1963) é a tabela de codificação de caracteres mais fundamental da computação.

```
    ASCII usa 7 bits → 128 caracteres possíveis (0 a 127)

    ┌─────────────────────────────────────────────────┐
    │  0-31:    Caracteres de controle (não visíveis) │
    │  32:      Espaço                                │
    │  48-57:   Dígitos (0-9)                         │
    │  65-90:   Letras maiúsculas (A-Z)               │
    │  97-122:  Letras minúsculas (a-z)               │
    │  33-47, 58-64, 91-96, 123-126: Símbolos         │
    │  127:     DEL (delete)                          │
    └─────────────────────────────────────────────────┘
```

**Tabela ASCII — Caracteres Imprimíveis (seleção):**

| Decimal | Hex | Binário | Caractere | | Decimal | Hex | Binário | Caractere |
|---------|-----|---------|-----------|---|---------|-----|---------|-----------|
| 32 | 20 | 0100000 | (espaço) | | 65 | 41 | 1000001 | A |
| 48 | 30 | 0110000 | 0 | | 66 | 42 | 1000010 | B |
| 49 | 31 | 0110001 | 1 | | 67 | 43 | 1000011 | C |
| 50 | 32 | 0110010 | 2 | | 90 | 5A | 1011010 | Z |
| 57 | 39 | 0111001 | 9 | | 97 | 61 | 1100001 | a |
| 33 | 21 | 0100001 | ! | | 98 | 62 | 1100010 | b |
| 63 | 3F | 0111111 | ? | | 122 | 7A | 1111010 | z |

> 💡 **Dicas úteis:**
> - A diferença entre maiúscula e minúscula é sempre **32** (1 bit): 'A'=65, 'a'=97 (97−65=32)
> - O caractere '0' (dígito) tem código 48, não 0!
> - Para converter dígito ASCII em valor numérico: código − 48

### 6.2 Exemplo: Codificando "OLA" em ASCII/Binário

```
    Texto: "OLA"

    O → 79₁₀ → 4F₁₆ → 01001111₂
    L → 76₁₀ → 4C₁₆ → 01001100₂
    A → 65₁₀ → 41₁₆ → 01000001₂

    Em binário: 01001111 01001100 01000001
    Em hex:     4F 4C 41
    Tamanho:    3 bytes (24 bits)
```

### 6.3 Limitações do ASCII

O ASCII só tem 128 caracteres — insuficiente para outros idiomas:

- ❌ Sem acentos (á, é, ã, ç) — problema para o português!
- ❌ Sem caracteres de outros alfabetos (cirílico, árabe, japonês)
- ❌ Sem emojis

### 6.4 Unicode — A Solução Universal

O **Unicode** (1991) é o padrão moderno que busca representar **todos os caracteres de todos os idiomas do mundo**:

| Aspecto | ASCII | Unicode |
|---------|-------|---------|
| Bits | 7 | Variável (8, 16, 32) |
| Caracteres | 128 | ~150.000+ |
| Idiomas | Inglês (basicamente) | Todos |
| Emojis | Não | Sim |
| Codificações | ASCII | UTF-8, UTF-16, UTF-32 |

### 6.5 UTF-8 — A Codificação Mais Usada

**UTF-8** é a codificação Unicode mais popular na web (>98% das páginas):

```
    UTF-8 usa tamanho VARIÁVEL:

    Caracteres ASCII (0-127):     1 byte   (compatível com ASCII!)
    Acentos latinos (128-2047):   2 bytes
    Outros alfabetos (2048-65535):3 bytes
    Emojis e símbolos raros:      4 bytes
```

| Caractere | Unicode | UTF-8 (hex) | Bytes |
|-----------|---------|-------------|-------|
| A | U+0041 | 41 | 1 |
| ã | U+00E3 | C3 A3 | 2 |
| ç | U+00E7 | C3 A7 | 2 |
| € | U+20AC | E2 82 AC | 3 |
| 😀 | U+1F600 | F0 9F 98 80 | 4 |

> 💡 **Por que UTF-8 é tão popular?** Porque texto em inglês ocupa **exatamente o mesmo espaço** que ASCII (1 byte por caractere), e só usa mais bytes quando necessário. Máxima **compatibilidade** com mínimo **desperdício**!

---

## 7. 🌍 Aplicações no Mundo Real

### 7.1 Tamanhos de Arquivo

```
    TIPO DE ARQUIVO          TAMANHO TÍPICO
    ───────────────────────────────────────────
    Documento Word simples   50-500 KB
    Foto JPEG (12 MP)        3-5 MB
    Música MP3 (4 min)       4-8 MB
    Música FLAC (4 min)      25-40 MB
    Vídeo 1080p (1 min)      100-200 MB
    Vídeo 4K (1 min)         300-600 MB
    Filme completo (1080p)   1-4 GB
    Jogo AAA moderno         50-200 GB
    Banco de dados grande    Vários TB
```

### 7.2 Velocidade de Internet

> ⚠️ **Atenção com unidades!** Velocidade de internet é medida em **bits** por segundo (bps), não bytes!

```
    CONVERSÃO: bits ↔ bytes

    Velocidade contratada: 100 Mbps (megaBITS por segundo)
    Velocidade em bytes:   100 ÷ 8 = 12,5 MB/s (megaBYTES por segundo)

    ┌─────────────────────────────────────────────────────┐
    │  100 Mbps ÷ 8 = 12,5 MB/s                          │
    │                                                     │
    │  Tempo para baixar um filme de 4 GB:                │
    │  4.000 MB ÷ 12,5 MB/s = 320 segundos ≈ 5,3 min    │
    └─────────────────────────────────────────────────────┘
```

**Tabela de velocidades comuns:**

| Velocidade (bits) | Velocidade (bytes) | Tempo para 1 GB |
|-------------------|--------------------|-----------------|
| 10 Mbps | 1,25 MB/s | ~14 minutos |
| 50 Mbps | 6,25 MB/s | ~2,7 minutos |
| 100 Mbps | 12,5 MB/s | ~1,3 minutos |
| 300 Mbps | 37,5 MB/s | ~27 segundos |
| 1 Gbps | 125 MB/s | ~8 segundos |

### 7.3 Capacidade de Armazenamento

| Dispositivo | Capacidade Típica | Equivalente Aproximado |
|------------|-------------------|----------------------|
| Disquete 3.5" | 1,44 MB | 1 foto em baixa resolução |
| CD-ROM | 700 MB | ~200 músicas MP3 |
| DVD | 4,7 GB | 1-2 filmes |
| Blu-ray | 25-100 GB | ~5-20 filmes HD |
| Pen drive | 8-256 GB | Milhares de documentos |
| SSD | 256 GB - 4 TB | Sistema + programas + mídia |
| HDD | 1-20 TB | Acervo de mídia completo |
| Fita magnética (LTO-9) | 18 TB | Backup empresarial |

### 7.4 Cálculos Práticos

**Quanto espaço ocupa 1 hora de vídeo 4K?**

```
    Resolução 4K: 3840 × 2160 pixels
    Cada pixel:   3 bytes (RGB, 8 bits por canal)
    Frames por segundo: 30 fps

    1 frame = 3840 × 2160 × 3 = 24.883.200 bytes ≈ 23,7 MB
    1 segundo = 23,7 MB × 30 = 711 MB
    1 hora = 711 MB × 3600 = 2.559.600 MB ≈ 2,44 TB (sem compressão!)

    Com compressão H.265 (HEVC): ~10-30 GB por hora (compressão de ~100x)
```

**Quantas músicas cabem em 16 GB?**

```
    Música média MP3 (128 kbps, 4 min): ~4 MB
    Espaço disponível: 16 GB = 16.384 MB (em MiB) ou 16.000 MB (em MB)

    16.000 ÷ 4 = 4.000 músicas (aproximadamente)
```

---

## 8. 🎓 Por que Isso Importa para um Cientista da Computação?

### 8.1 Escolha de Tipos de Dados

Ao programar, você escolhe tipos de dados que determinam **quantos bits** são usados:

```
    TIPO DE DADO      BITS    FAIXA (sem sinal)         FAIXA (com sinal)
    ──────────────────────────────────────────────────────────────────────
    byte / uint8       8      0 a 255                   -128 a 127
    short / uint16    16      0 a 65.535                -32.768 a 32.767
    int / uint32      32      0 a 4.294.967.295         -2³¹ a 2³¹-1
    long / uint64     64      0 a ~1,8 × 10¹⁹          -2⁶³ a 2⁶³-1
    float             32      ~7 dígitos de precisão
    double            64      ~15 dígitos de precisão
```

> 💡 Escolher o tipo certo significa **usar memória eficientemente**. Se uma variável nunca passa de 255, use `byte` (8 bits) em vez de `int` (32 bits) — economiza 4× a memória!

### 8.2 Buffers e Overflow

```
    Se uma variável de 8 bits (byte) vale 255 (11111111₂)
    e você soma 1:

    11111111
  +        1
    ─────────
   100000000  ← 9 bits! Mas só cabem 8...

    Resultado no programa: 0 (os 8 bits ficam: 00000000)
    Isso é um OVERFLOW! 💥

    Consequências reais:
    • Ariane 5 (1996): overflow causou a explosão do foguete
    • Boeing 787: timer de 32 bits resetava após 248 dias
```

### 8.3 Performance e Cache

O tamanho dos dados afeta diretamente a **performance**:

```
    Array de 1 milhão de elementos:

    byte[]  → 1 MB   → Cabe na cache L2 (tipicamente 1-4 MB)  ⚡ RÁPIDO
    int[]   → 4 MB   → Pode não caber na cache L2              🐌 Mais lento
    long[]  → 8 MB   → Definitivamente não cabe na cache       🐌🐌 Lento

    Menos bytes = mais dados na cache = programa mais rápido!
```

---

## 9. 📝 Resumo

### Unidades Fundamentais

```
    1 bit  = menor unidade (0 ou 1)
    1 nibble = 4 bits (1 dígito hexadecimal)
    1 byte = 8 bits (1 caractere ASCII)
    1 word = depende da arquitetura (32 ou 64 bits)
```

### Unidades de Armazenamento

```
    BINÁRIO (IEC):                 DECIMAL (SI):
    1 KiB = 1.024 bytes           1 KB = 1.000 bytes
    1 MiB = 1.024 KiB             1 MB = 1.000 KB
    1 GiB = 1.024 MiB             1 GB = 1.000 MB
    1 TiB = 1.024 GiB             1 TB = 1.000 GB
```

### Codificação de Texto

```
    ASCII:    7 bits, 128 caracteres (só inglês)
    UTF-8:    1-4 bytes, compatível com ASCII, >150.000 caracteres
    UTF-16:   2-4 bytes, base 16 bits
    UTF-32:   4 bytes fixos por caractere
```

### Conversão entre Bases

```
    MÉTODO UNIVERSAL:
    Base X → Decimal:   Multiplicar cada dígito pelo peso (baseⁿ) e somar
    Decimal → Base Y:   Dividir sucessivamente pela base e ler restos
    Base X → Base Y:    Usar decimal como intermediário
    
    ATALHOS:
    Binário ↔ Octal:    Agrupar/expandir de 3 em 3 bits
    Binário ↔ Hex:      Agrupar/expandir de 4 em 4 bits
```

### Fórmula Essencial

```
    n bits → 2ⁿ combinações → valores de 0 a 2ⁿ − 1
```

---

## 10. 📚 Leitura Complementar

### Livros:
- **Stallings, W.** *Computer Organization and Architecture*. Cap. 9.
- **Monteiro, M. A.** *Introdução à Organização de Computadores*. Cap. 2-3.
- **Tanenbaum, A. S.** *Structured Computer Organization*. Cap. 1-2.

### Referências online:
- [Tabela ASCII completa](https://www.asciitable.com/)
- [Unicode Character Table](https://unicode-table.com/)
- [IEC 80000-13 (padrão de unidades binárias)](https://en.wikipedia.org/wiki/ISO/IEC_80000)

### Experimente em Python:
```python
# Tamanho de strings em bytes
texto = "Olá Mundo!"
print(f"Texto: {texto}")
print(f"Tamanho em bytes (UTF-8): {len(texto.encode('utf-8'))}")
print(f"Tamanho em bytes (ASCII): N/A (tem acentos!)")

# Códigos ASCII
for char in "HELLO":
    print(f"'{char}' → decimal: {ord(char)}, hex: {hex(ord(char))}, bin: {bin(ord(char))}")

# Conversão de unidades
gb_si = 500  # 500 GB (fabricante)
gb_binario = (gb_si * 1_000_000_000) / (1024**3)
print(f"\n{gb_si} GB (SI) = {gb_binario:.2f} GiB (IEC)")
```

---

## 📂 Materiais Complementares

- 📁 **[Exemplos](exemplos/)** — Tabelas de conversão, cálculos de armazenamento
- 📁 **[Exercícios](exercicios/)** — Exercícios práticos com aplicações do dia a dia

---

<div align="center">

**📦 "Um byte não parece muito, mas com bilhões deles, construímos todo o mundo digital."**

</div>
