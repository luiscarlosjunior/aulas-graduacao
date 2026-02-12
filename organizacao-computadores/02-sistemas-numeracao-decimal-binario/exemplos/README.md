# 📊 Exemplos — Aula 02: Sistemas de Numeração — Decimal e Binário

> Conversões passo a passo com múltiplos exemplos resolvidos

---

## 🔄 Parte 1 — Conversão Decimal → Binário (Divisões Sucessivas)

### Exemplo 1: Converter 13₁₀ para binário

```
    13 ÷ 2 =  6  resto  1  ↑
     6 ÷ 2 =  3  resto  0  │
     3 ÷ 2 =  1  resto  1  │  Leia de baixo para cima
     1 ÷ 2 =  0  resto  1  │

    Resultado: 13₁₀ = 1101₂
```

**Verificação:** 1×8 + 1×4 + 0×2 + 1×1 = 8 + 4 + 0 + 1 = **13** ✓

---

### Exemplo 2: Converter 42₁₀ para binário

```
    42 ÷ 2 = 21  resto  0  ↑
    21 ÷ 2 = 10  resto  1  │
    10 ÷ 2 =  5  resto  0  │
     5 ÷ 2 =  2  resto  1  │  Leia de baixo para cima
     2 ÷ 2 =  1  resto  0  │
     1 ÷ 2 =  0  resto  1  │

    Resultado: 42₁₀ = 101010₂
```

**Verificação:** 32 + 0 + 8 + 0 + 2 + 0 = **42** ✓

---

### Exemplo 3: Converter 73₁₀ para binário

```
    73 ÷ 2 = 36  resto  1  ↑
    36 ÷ 2 = 18  resto  0  │
    18 ÷ 2 =  9  resto  0  │
     9 ÷ 2 =  4  resto  1  │  Leia de baixo para cima
     4 ÷ 2 =  2  resto  0  │
     2 ÷ 2 =  1  resto  0  │
     1 ÷ 2 =  0  resto  1  │

    Resultado: 73₁₀ = 1001001₂
```

**Verificação:** 64 + 0 + 0 + 8 + 0 + 0 + 1 = **73** ✓

---

### Exemplo 4: Converter 200₁₀ para binário

```
    200 ÷ 2 = 100  resto  0  ↑
    100 ÷ 2 =  50  resto  0  │
     50 ÷ 2 =  25  resto  0  │
     25 ÷ 2 =  12  resto  1  │
     12 ÷ 2 =   6  resto  0  │  Leia de baixo para cima
      6 ÷ 2 =   3  resto  0  │
      3 ÷ 2 =   1  resto  1  │
      1 ÷ 2 =   0  resto  1  │

    Resultado: 200₁₀ = 11001000₂
```

**Verificação:** 128 + 64 + 0 + 0 + 8 + 0 + 0 + 0 = **200** ✓

---

### Exemplo 5: Converter 0₁₀ e 1₁₀ para binário (casos triviais)

```
    0₁₀ = 0₂      (zero é zero em qualquer base!)
    1₁₀ = 1₂      (um é um em qualquer base!)
```

---

## 🔄 Parte 2 — Conversão Binário → Decimal (Pesos Posicionais)

### Exemplo 6: Converter 1010₂ para decimal

```
    Posição:    3      2      1      0
    Dígito:     1      0      1      0
    Peso:       8      4      2      1

    Cálculo: 1×8 + 0×4 + 1×2 + 0×1 = 8 + 0 + 2 + 0 = 10

    Resultado: 1010₂ = 10₁₀
```

---

### Exemplo 7: Converter 11110000₂ para decimal

```
    Posição:    7      6      5      4      3      2      1      0
    Dígito:     1      1      1      1      0      0      0      0
    Peso:     128     64     32     16      8      4      2      1

    Cálculo: 128 + 64 + 32 + 16 + 0 + 0 + 0 + 0 = 240

    Resultado: 11110000₂ = 240₁₀
```

---

### Exemplo 8: Converter 10000000₂ para decimal

```
    Apenas o bit 7 está ligado:

    1 × 2⁷ = 1 × 128 = 128

    Resultado: 10000000₂ = 128₁₀
```

> 💡 **Regra rápida:** Um número binário que é "1 seguido de zeros" é sempre uma potência de 2!

---

### Exemplo 9: Converter 11111111₂ para decimal

```
    Todos os bits estão ligados:

    128 + 64 + 32 + 16 + 8 + 4 + 2 + 1 = 255

    Resultado: 11111111₂ = 255₁₀
```

> 💡 **Regra rápida:** Um número binário com todos os bits "1" é igual a 2ⁿ − 1, onde n é o número de bits. Aqui: 2⁸ − 1 = 256 − 1 = 255.

---

## 🔄 Parte 3 — Conversão de Frações Decimais → Binário

### Exemplo 10: Converter 0,75₁₀ para binário

```
    0,75 × 2 = 1,50  → parte inteira: 1  ↓
    0,50 × 2 = 1,00  → parte inteira: 1  ↓  Leia de cima para baixo
    0,00 ← PAROU

    Resultado: 0,75₁₀ = 0,11₂
```

**Verificação:** 1 × 0,5 + 1 × 0,25 = 0,5 + 0,25 = **0,75** ✓

---

### Exemplo 11: Converter 5,375₁₀ para binário

**Parte inteira (5):**
```
    5 ÷ 2 = 2  resto  1  ↑
    2 ÷ 2 = 1  resto  0  │
    1 ÷ 2 = 0  resto  1  │

    Parte inteira: 101₂
```

**Parte fracionária (0,375):**
```
    0,375 × 2 = 0,750  → 0  ↓
    0,750 × 2 = 1,500  → 1  ↓
    0,500 × 2 = 1,000  → 1  ↓
    0,000 ← PAROU

    Parte fracionária: 0,011₂
```

**Resultado: 5,375₁₀ = 101,011₂**

**Verificação:** 4 + 0 + 1 + 0 + 0,25 + 0,125 = **5,375** ✓

---

### Exemplo 12: Converter 0,1₁₀ para binário (dízima!)

```
    0,1 × 2 = 0,2  → 0
    0,2 × 2 = 0,4  → 0
    0,4 × 2 = 0,8  → 0
    0,8 × 2 = 1,6  → 1
    0,6 × 2 = 1,2  → 1
    0,2 × 2 = 0,4  → 0  ← O padrão começa a se repetir!
    0,4 × 2 = 0,8  → 0
    0,8 × 2 = 1,6  → 1
    ...

    Resultado: 0,1₁₀ = 0,00011001100110011...₂ (dízima periódica!)
```

> ⚠️ **Importante:** O número 0,1 decimal **não pode ser representado exatamente** em binário! Isso explica por que em linguagens de programação: `0.1 + 0.2 ≠ 0.3` (resultado: 0.30000000000000004).

---

## 📋 Parte 4 — Tabela de Referência Rápida

### Números de 0 a 31 em Decimal e Binário

| Decimal | Binário | | Decimal | Binário |
|---------|---------|---|---------|---------|
| 0 | 00000 | | 16 | 10000 |
| 1 | 00001 | | 17 | 10001 |
| 2 | 00010 | | 18 | 10010 |
| 3 | 00011 | | 19 | 10011 |
| 4 | 00100 | | 20 | 10100 |
| 5 | 00101 | | 21 | 10101 |
| 6 | 00110 | | 22 | 10110 |
| 7 | 00111 | | 23 | 10111 |
| 8 | 01000 | | 24 | 11000 |
| 9 | 01001 | | 25 | 11001 |
| 10 | 01010 | | 26 | 11010 |
| 11 | 01011 | | 27 | 11011 |
| 12 | 01100 | | 28 | 11100 |
| 13 | 01101 | | 29 | 11101 |
| 14 | 01110 | | 30 | 11110 |
| 15 | 01111 | | 31 | 11111 |

---

## 📋 Parte 5 — Conversão pelo Método de Subtração de Potências

### Exemplo 13: Converter 156₁₀ para binário

```
    Potências disponíveis: 128  64  32  16  8  4  2  1

    156 ≥ 128?  SIM → 156 - 128 = 28   → bit 7 = 1
     28 ≥  64?  NÃO                    → bit 6 = 0
     28 ≥  32?  NÃO                    → bit 5 = 0
     28 ≥  16?  SIM →  28 - 16  = 12   → bit 4 = 1
     12 ≥   8?  SIM →  12 -  8  =  4   → bit 3 = 1
      4 ≥   4?  SIM →   4 -  4  =  0   → bit 2 = 1
      0 ≥   2?  NÃO                    → bit 1 = 0
      0 ≥   1?  NÃO                    → bit 0 = 0

    Resultado: 156₁₀ = 10011100₂
```

**Verificação:** 128 + 0 + 0 + 16 + 8 + 4 + 0 + 0 = **156** ✓

---

<div align="center">

**📊 Pratique com os exercícios para fixar os métodos de conversão!**

*Voltar para a [Aula 02](../README.md)*

</div>
