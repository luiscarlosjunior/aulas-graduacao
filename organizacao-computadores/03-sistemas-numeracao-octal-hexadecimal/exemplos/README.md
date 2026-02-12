# 📊 Exemplos — Aula 03: Sistemas de Numeração — Octal e Hexadecimal

> Conversões detalhadas passo a passo entre bases

---

## 🔄 Parte 1 — Conversões com Octal

### Exemplo 1: Decimal → Octal (156₁₀)

```
    156 ÷ 8 = 19  resto  4  ↑
     19 ÷ 8 =  2  resto  3  │  Leia de baixo para cima
      2 ÷ 8 =  0  resto  2  │

    Resultado: 156₁₀ = 234₈
```

**Verificação:** 2×64 + 3×8 + 4×1 = 128 + 24 + 4 = **156** ✓

---

### Exemplo 2: Octal → Decimal (1472₈)

```
    1 × 8³ = 1 × 512 = 512
    4 × 8² = 4 × 64  = 256
    7 × 8¹ = 7 × 8   =  56
    2 × 8⁰ = 2 × 1   =   2
                        ─────
    Total:              826

    Resultado: 1472₈ = 826₁₀
```

---

### Exemplo 3: Binário → Octal (110110101₂)

```
    Agrupar de 3 em 3 (da direita para esquerda):

    110  110  101
     │    │    │
     ▼    ▼    ▼
     6    6    5

    Resultado: 110110101₂ = 665₈
```

---

### Exemplo 4: Octal → Binário (527₈)

```
    Expandir cada dígito para 3 bits:

     5    2    7
     │    │    │
     ▼    ▼    ▼
    101  010  111

    Resultado: 527₈ = 101010111₂
```

---

### Exemplo 5: Permissões Linux (chmod)

```
    chmod 644 arquivo.txt

    6 = 110₂ = rw-  (leitura + escrita)         → dono
    4 = 100₂ = r--  (somente leitura)            → grupo
    4 = 100₂ = r--  (somente leitura)            → outros

    Significado: O dono pode ler e escrever; grupo e outros só podem ler.
```

```
    chmod 777 script.sh

    7 = 111₂ = rwx  (todas as permissões)        → dono
    7 = 111₂ = rwx  (todas as permissões)        → grupo
    7 = 111₂ = rwx  (todas as permissões)        → outros

    Significado: Todos podem tudo (geralmente não recomendado!).
```

---

## 🔄 Parte 2 — Conversões com Hexadecimal

### Exemplo 6: Decimal → Hexadecimal (1000₁₀)

```
    1000 ÷ 16 = 62  resto   8        ↑
      62 ÷ 16 =  3  resto  14 (= E)  │  Leia de baixo para cima
       3 ÷ 16 =  0  resto   3        │

    Resultado: 1000₁₀ = 3E8₁₆
```

**Verificação:** 3×256 + 14×16 + 8×1 = 768 + 224 + 8 = **1000** ✓

---

### Exemplo 7: Hexadecimal → Decimal (BEEF₁₆)

```
    B × 16³ = 11 × 4096 = 45056
    E × 16² = 14 × 256  =  3584
    E × 16¹ = 14 × 16   =   224
    F × 16⁰ = 15 × 1    =    15
                           ──────
    Total:                 48879

    Resultado: BEEF₁₆ = 48879₁₀
```

---

### Exemplo 8: Binário → Hexadecimal (10110111₂)

```
    Agrupar de 4 em 4 (da direita para esquerda):

    1011  0111
      │     │
      ▼     ▼
      B     7

    Resultado: 10110111₂ = B7₁₆
```

---

### Exemplo 9: Hexadecimal → Binário (A3F₁₆)

```
    Expandir cada dígito para 4 bits:

     A     3     F
     │     │     │
     ▼     ▼     ▼
    1010  0011  1111

    Resultado: A3F₁₆ = 101000111111₂
```

---

### Exemplo 10: Cor HTML — Decompondo #1E90FF

```
    Cor: #1E90FF (Dodger Blue)

    Componente R: 1E₁₆
      1 × 16 + 14 × 1 = 16 + 14 = 30₁₀

    Componente G: 90₁₆
      9 × 16 + 0 × 1 = 144 + 0 = 144₁₀

    Componente B: FF₁₆
      15 × 16 + 15 × 1 = 240 + 15 = 255₁₀

    Resultado: RGB(30, 144, 255) — Azul vibrante com um pouco de verde
```

---

## 🔄 Parte 3 — Conversão entre Octal e Hexadecimal (via Binário)

### Exemplo 11: Octal → Hexadecimal (3567₈)

```
    PASSO 1: Octal → Binário
     3    5    6    7
     │    │    │    │
     ▼    ▼    ▼    ▼
    011  101  110  111

    Binário: 011101110111

    PASSO 2: Binário → Hexadecimal (reagrupar em 4)
    0111  0111  0111
      │     │     │
      ▼     ▼     ▼
      7     7     7

    Resultado: 3567₈ = 777₁₆
```

**Verificação (ambos em decimal):**
- 3567₈ = 3×512 + 5×64 + 6×8 + 7×1 = 1536 + 320 + 48 + 7 = **1911**
- 777₁₆ = 7×256 + 7×16 + 7×1 = 1792 + 112 + 7 = **1911** ✓

---

### Exemplo 12: Hexadecimal → Octal (4DC₁₆)

```
    PASSO 1: Hex → Binário
     4     D     C
     │     │     │
     ▼     ▼     ▼
    0100  1101  1100

    Binário: 010011011100

    PASSO 2: Binário → Octal (reagrupar em 3)
    010  011  011  100
     │    │    │    │
     ▼    ▼    ▼    ▼
     2    3    3    4

    Resultado: 4DC₁₆ = 2334₈
```

---

## 📋 Parte 4 — Exemplo Integrado: Endereço IP

### Exemplo 13: Representar 192.168.1.100 nas diferentes bases

```
    Componente   Decimal    Binário       Octal    Hexadecimal
    ─────────────────────────────────────────────────────────
    192          192        1100 0000     300      C0
    168          168        1010 1000     250      A8
    1              1        0000 0001     001      01
    100          100        0110 0100     144      64

    IP completo:
    Decimal:       192.168.1.100
    Binário:       11000000.10101000.00000001.01100100
    Hexadecimal:   C0.A8.01.64  (ou C0A80164)
```

---

<div align="center">

**📊 Use estes exemplos como referência para resolver os exercícios!**

*Voltar para a [Aula 03](../README.md)*

</div>
