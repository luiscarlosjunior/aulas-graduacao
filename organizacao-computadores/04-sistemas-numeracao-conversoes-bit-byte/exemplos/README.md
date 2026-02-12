# 📊 Exemplos — Aula 04: Conversões, Bit e Byte

> Tabelas de conversão, cálculos de armazenamento e codificação de texto

---

## 🔄 Parte 1 — Conversões entre Bases Diversas

### Exemplo 1: Base 3 → Decimal (2201₃)

```
    2 × 3³ = 2 × 27 = 54
    2 × 3² = 2 × 9  = 18
    0 × 3¹ = 0 × 3  =  0
    1 × 3⁰ = 1 × 1  =  1
                       ────
    Total:             73

    Resultado: 2201₃ = 73₁₀
```

---

### Exemplo 2: Decimal → Base 5 (234₁₀)

```
    234 ÷ 5 = 46  resto  4  ↑
     46 ÷ 5 =  9  resto  1  │
      9 ÷ 5 =  1  resto  4  │  Leia de baixo para cima
      1 ÷ 5 =  0  resto  1  │

    Resultado: 234₁₀ = 1414₅
```

**Verificação:** 1×125 + 4×25 + 1×5 + 4×1 = 125 + 100 + 5 + 4 = **234** ✓

---

### Exemplo 3: Base 7 → Base 4 (via decimal) (325₇)

```
    PASSO 1: Base 7 → Decimal
    3 × 49 + 2 × 7 + 5 × 1 = 147 + 14 + 5 = 166₁₀

    PASSO 2: Decimal → Base 4
    166 ÷ 4 = 41  resto  2  ↑
     41 ÷ 4 = 10  resto  1  │
     10 ÷ 4 =  2  resto  2  │
      2 ÷ 4 =  0  resto  2  │

    Resultado: 325₇ = 2212₄
```

---

## 📦 Parte 2 — Cálculos com Unidades de Armazenamento

### Exemplo 4: Conversão entre Unidades (Binário/IEC)

```
    Converter 5 GiB para bytes:

    5 GiB = 5 × 1024 MiB
          = 5.120 MiB
          = 5.120 × 1024 KiB
          = 5.242.880 KiB
          = 5.242.880 × 1024 bytes
          = 5.368.709.120 bytes

    Atalho: 5 × 1024³ = 5 × 1.073.741.824 = 5.368.709.120 bytes
```

---

### Exemplo 5: HD de 1 TB — Quanto o Windows Mostra?

```
    Fabricante anuncia: 1 TB (SI)
    = 1 × 10¹² bytes
    = 1.000.000.000.000 bytes

    Windows calcula em GiB:
    1.000.000.000.000 ÷ 1.073.741.824 = 931,32 GiB

    → O Windows mostra aproximadamente 931 GB
    → "Faltam" ~69 GB? Não! São os mesmos bytes, medidos diferente.
```

---

### Exemplo 6: Quanto Espaço Ocupa uma Foto?

```
    Câmera de 24 megapixels:
    - Resolução: 6000 × 4000 = 24.000.000 pixels
    - Cada pixel: 3 bytes (RGB, 8 bits por canal)

    SEM compressão (BMP/RAW):
    24.000.000 × 3 = 72.000.000 bytes ≈ 68,66 MiB ≈ 72 MB

    COM compressão JPEG (qualidade alta):
    ≈ 8-12 MB (compressão de ~6-9x)

    COM compressão JPEG (qualidade média):
    ≈ 3-5 MB (compressão de ~15-24x)
```

---

### Exemplo 7: Velocidade de Internet vs. Download

```
    Plano contratado: 200 Mbps (megabits por segundo)

    Conversão para bytes:
    200 Mbps ÷ 8 = 25 MB/s (megabytes por segundo)

    Tempo para baixar um jogo de 80 GB:
    80.000 MB ÷ 25 MB/s = 3.200 segundos
    3.200 ÷ 60 = 53,3 minutos

    ⚠️ Na prática, nunca se atinge a velocidade máxima (overhead de protocolos,
    congestionamento, etc.). Espere ~70-80% da velocidade teórica.
    Tempo realista: ~67-76 minutos
```

---

## 🔤 Parte 3 — Codificação ASCII

### Exemplo 8: Codificando "Hello" em ASCII

```
    Caractere  Decimal   Hexadecimal   Binário (8 bits)
    ─────────────────────────────────────────────────────
    H          72        48            01001000
    e          101       65            01100101
    l          108       6C            01101100
    l          108       6C            01101100
    o          111       6F            01101111

    Em hex: 48 65 6C 6C 6F
    Em binário: 01001000 01100101 01101100 01101100 01101111
    Total: 5 bytes = 40 bits
```

---

### Exemplo 9: Codificando "Olá" em UTF-8

```
    Caractere  Unicode    UTF-8 (hex)     Bytes
    ─────────────────────────────────────────────
    O          U+004F     4F              1
    l          U+006C     6C              1
    á          U+00E1     C3 A1           2

    Em hex: 4F 6C C3 A1
    Total: 4 bytes (não 3! O 'á' ocupa 2 bytes em UTF-8)
```

> 💡 Textos em português ocupam **mais espaço** em UTF-8 do que em inglês, porque acentos usam 2 bytes cada.

---

### Exemplo 10: Diferença entre Maiúscula e Minúscula

```
    'A' = 65₁₀ = 01000001₂
    'a' = 97₁₀ = 01100001₂
                  ──────────
    Diferença:    00100000₂ = 32₁₀

    Observe: a ÚNICA diferença é o bit 5!
    Maiúscula: bit 5 = 0
    Minúscula: bit 5 = 1

    Isso vale para TODAS as letras do alfabeto:
    'B'=66  'b'=98   (diferença: 32)
    'Z'=90  'z'=122  (diferença: 32)
```

---

## 📐 Parte 4 — Tabelas de Referência

### Tabela: Potências de 2 (de 2⁰ a 2²⁰)

| Potência | Valor | Aproximação |
|----------|-------|-------------|
| 2⁰ | 1 | |
| 2¹ | 2 | |
| 2² | 4 | |
| 2³ | 8 | |
| 2⁴ | 16 | |
| 2⁵ | 32 | |
| 2⁶ | 64 | |
| 2⁷ | 128 | |
| 2⁸ | 256 | |
| 2⁹ | 512 | |
| 2¹⁰ | 1.024 | ~1 mil (1 KiB) |
| 2¹¹ | 2.048 | |
| 2¹² | 4.096 | |
| 2¹³ | 8.192 | |
| 2¹⁴ | 16.384 | |
| 2¹⁵ | 32.768 | |
| 2¹⁶ | 65.536 | ~65 mil |
| 2²⁰ | 1.048.576 | ~1 milhão (1 MiB) |
| 2³⁰ | 1.073.741.824 | ~1 bilhão (1 GiB) |
| 2³² | 4.294.967.296 | ~4 bilhões (endereço IPv4) |
| 2⁴⁰ | 1.099.511.627.776 | ~1 trilhão (1 TiB) |

---

### Tabela: Comparação SI vs. IEC

| Unidade SI | Valor Exato | Unidade IEC | Valor Exato | Diferença |
|-----------|-------------|-------------|-------------|-----------|
| 1 KB | 1.000 B | 1 KiB | 1.024 B | 2,4% |
| 1 MB | 1.000.000 B | 1 MiB | 1.048.576 B | 4,9% |
| 1 GB | 10⁹ B | 1 GiB | 1.073.741.824 B | 7,4% |
| 1 TB | 10¹² B | 1 TiB | 1.099.511.627.776 B | 10,0% |
| 1 PB | 10¹⁵ B | 1 PiB | 1.125.899.906.842.624 B | 12,6% |

---

<div align="center">

**📊 Use estas tabelas como referência para resolver os exercícios!**

*Voltar para a [Aula 04](../README.md)*

</div>
