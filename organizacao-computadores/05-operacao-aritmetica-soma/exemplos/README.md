# 📊 Exemplos — Aula 05: Operação Aritmética — Soma Binária

> Exemplos resolvidos passo a passo de soma binária, complemento a 2 e circuitos somadores

---

## ➕ Parte 1 — Soma Binária Básica

### Exemplo 1: Soma Simples de 4 Bits (1010 + 0011)

```
      Carry:  0 1 1 0
              1 0 1 0    (10 em decimal)
          +   0 0 1 1    ( 3 em decimal)
          ───────────
              1 1 0 1    (13 em decimal) ✓
```

**Passo a passo:**

| Coluna | Operação | Resultado | Carry |
|--------|----------|-----------|-------|
| Pos 0 | 0 + 1 = 1 | **1** | 0 |
| Pos 1 | 1 + 1 = 10 | **0** | 1 |
| Pos 2 | 0 + 0 + 1 = 1 | **1** | 0 |
| Pos 3 | 1 + 0 = 1 | **1** | 0 |

**Verificação:** 10 + 3 = 13 → 1101₂ = 8 + 4 + 1 = 13 ✓

---

### Exemplo 2: Soma com Múltiplos Carries (1111 + 0001)

```
      Carry:  1 1 1 1
              1 1 1 1    (15 em decimal)
          +   0 0 0 1    ( 1 em decimal)
          ───────────
          [1] 0 0 0 0    (16 em decimal)
```

**Passo a passo:**

| Coluna | Operação | Resultado | Carry |
|--------|----------|-----------|-------|
| Pos 0 | 1 + 1 = 10 | **0** | 1 |
| Pos 1 | 1 + 0 + 1 = 10 | **0** | 1 |
| Pos 2 | 1 + 0 + 1 = 10 | **0** | 1 |
| Pos 3 | 1 + 0 + 1 = 10 | **0** | 1 |
| Carry final | | **1** | — |

**Resultado:** 10000₂ = 16₁₀. Se trabalhamos com 4 bits sem sinal, ocorre **overflow** (15 + 1 não cabe em 4 bits). Se permitimos 5 bits, o resultado está correto.

---

### Exemplo 3: Soma de 8 Bits (10110101 + 01101010)

```
      Carry:  1 1 1 1 1 1 0 0
              1 0 1 1 0 1 0 1    (181 em decimal)
          +   0 1 1 0 1 0 1 0    (106 em decimal)
          ─────────────────────
          [1] 0 0 0 1 1 1 1 1    (287 em decimal)
```

**Passo a passo detalhado:**

| Coluna | Bits | + Carry | Total | Escreve | Novo Carry |
|--------|------|---------|-------|---------|------------|
| 0 | 1+0 | +0 | 1 | **1** | 0 |
| 1 | 0+1 | +0 | 1 | **1** | 0 |
| 2 | 1+0 | +0 | 1 | **1** | 0 |
| 3 | 0+1 | +0 | 1 | **1** | 0 |
| 4 | 1+0 | +1 | 2 | **0** | 1 |
| 5 | 1+1 | +1 | 3 | **1** | 1 |
| 6 | 0+1 | +1 | 2 | **0** | 1 |
| 7 | 1+0 | +1 | 2 | **0** | 1 |

**Resultado:** 100011111₂ = 287₁₀ ✓ (com 9 bits)

**Nota:** Em 8 bits sem sinal (máx 255), ocorre overflow! O processador armazenaria `00011111₂ = 31` e ativaria a flag de carry.

---

## 🔄 Parte 2 — Complemento a 2

### Exemplo 4: Converter -13 para Complemento a 2 (8 bits)

```
    PASSO 1: Escrever +13 em binário (8 bits)
    +13 = 00001101₂

    PASSO 2: Inverter todos os bits (complemento a 1)
    00001101 → 11110010

    PASSO 3: Somar 1
      1 1 1 1 0 0 1 0
    +               1
    ─────────────────
      1 1 1 1 0 0 1 1

    Resultado: -13 em C2 = 11110011₂
```

**Verificação rápida:** 11110011₂ → inverte = 00001100, +1 = 00001101 = 13. Logo é -13 ✓

---

### Exemplo 5: Soma em C2 — Positivo + Negativo (+20 + (-7))

```
    PASSO 1: Converter para C2 (8 bits)
    +20 = 00010100₂
    -7 → +7 = 00000111, inverte = 11111000, +1 = 11111001

    PASSO 2: Somar normalmente
      Carry:  1 1 1 1 1 0 0 0
              0 0 0 1 0 1 0 0    (+20)
          +   1 1 1 1 1 0 0 1    (-7)
          ─────────────────────
          [1] 0 0 0 0 1 1 0 1    (+13)
           ↑
           Carry descartado

    Resultado: 00001101₂ = +13 ✓ (20 + (-7) = 13)
```

---

### Exemplo 6: Soma em C2 — Negativo + Negativo (-10 + (-25))

```
    PASSO 1: Converter para C2 (8 bits)
    -10 → +10 = 00001010, inverte = 11110101, +1 = 11110110
    -25 → +25 = 00011001, inverte = 11100110, +1 = 11100111

    PASSO 2: Somar normalmente
      Carry:  1 1 1 0 0 1 1 0
              1 1 1 1 0 1 1 0    (-10)
          +   1 1 1 0 0 1 1 1    (-25)
          ─────────────────────
          [1] 1 1 0 1 1 1 0 1    (-35)
           ↑
           Carry descartado

    PASSO 3: Verificar o resultado
    11011101₂ → inverte = 00100010, +1 = 00100011 = 35
    Logo: -35 ✓ (-10 + (-25) = -35)

    MSB = 1 (negativo), somamos dois negativos e obtivemos negativo.
    Sem overflow! ✓
```

---

### Exemplo 7: Detecção de Overflow em C2 (+100 + +50, 8 bits)

A faixa de 8 bits com sinal (C2) é **-128 a +127**.

```
    +100 = 01100100₂
    +50  = 00110010₂

    Carry:  0 1 1 0 0 1 0 0
            0 1 1 0 0 1 0 0    (+100)
        +   0 0 1 1 0 0 1 0    (+50)
        ─────────────────────
            1 0 0 1 0 1 1 0    (-106?!)
```

**Análise:**

| Verificação | Valor |
|-------------|-------|
| Resultado esperado | +150 |
| Faixa máxima (C2, 8 bits) | +127 |
| Resultado obtido | 10010110₂ = -106 em C2 |
| **Overflow?** | **SIM!** ⚠️ |

**Motivo:** Somamos dois positivos (MSB=0) e obtivemos negativo (MSB=1). O carry entrando no MSB (1) é diferente do carry saindo do MSB (0).

---

## ⚡ Parte 3 — Circuitos Somadores

### Exemplo 8: Meio-Somador — Todas as Combinações

Dado o circuito meio-somador com entradas A e B:

```
    S = A ⊕ B    (XOR)
    C = A · B    (AND)
```

Calculando cada combinação:

| A | B | A ⊕ B → S | A · B → C | Significado |
|---|---|-----------|-----------|-------------|
| 0 | 0 | 0 ⊕ 0 = **0** | 0 · 0 = **0** | 0 + 0 = 0 |
| 0 | 1 | 0 ⊕ 1 = **1** | 0 · 1 = **0** | 0 + 1 = 1 |
| 1 | 0 | 1 ⊕ 0 = **1** | 1 · 0 = **0** | 1 + 0 = 1 |
| 1 | 1 | 1 ⊕ 1 = **0** | 1 · 1 = **1** | 1 + 1 = 10 (0 com carry 1) |

---

### Exemplo 9: Somador Completo — Caso A=1, B=1, Cₑ=1

```
    Entradas: A=1, B=1, Cₑ=1
    
    PASSO 1: Calcular A ⊕ B
    1 ⊕ 1 = 0

    PASSO 2: Calcular S = (A ⊕ B) ⊕ Cₑ
    0 ⊕ 1 = 1    → S = 1

    PASSO 3: Calcular A · B
    1 · 1 = 1

    PASSO 4: Calcular (A ⊕ B) · Cₑ
    0 · 1 = 0

    PASSO 5: Calcular Cₛ = (A · B) + ((A ⊕ B) · Cₑ)
    1 + 0 = 1    → Cₛ = 1

    Resultado: 1 + 1 + 1 = 11₂ (S=1, Carry=1) ✓
    Em decimal: 1 + 1 + 1 = 3 = 11₂ ✓
```

---

### Exemplo 10: Somador de 4 Bits — Somar 0101 + 0011

Usando 4 somadores completos (FA) em cascata:

```
    A = 0101 (5₁₀)
    B = 0011 (3₁₀)

    FA₀: A₀=1, B₀=1, C₀=0 → S₀=0, C₁=1
    FA₁: A₁=0, B₁=1, C₁=1 → S₁=0, C₂=1
    FA₂: A₂=1, B₂=0, C₂=1 → S₂=0, C₃=1
    FA₃: A₃=0, B₃=0, C₃=1 → S₃=1, C₄=0

    Resultado: S = 1000₂ = 8₁₀
    Carry out: C₄ = 0 (sem overflow)
    
    Verificação: 5 + 3 = 8 ✓
```

**Propagação do carry passo a passo:**

| Somador | A | B | Cₑ | S | Cₛ |
|---------|---|---|----|---|-----|
| FA₀ | 1 | 1 | 0 | 0 | 1 |
| FA₁ | 0 | 1 | 1 | 0 | 1 |
| FA₂ | 1 | 0 | 1 | 0 | 1 |
| FA₃ | 0 | 0 | 1 | 1 | 0 |

---

> 💡 **Dica de estudo:** Para cada exemplo, refaça os cálculos sem olhar a solução. Depois confira! A prática repetida é a melhor forma de dominar a soma binária.

---

> ⬅️ [Voltar para a Aula](../README.md) | [Exercícios →](../exercicios/README.md)
