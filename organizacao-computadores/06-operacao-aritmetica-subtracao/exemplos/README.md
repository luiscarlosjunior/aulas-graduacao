# 📊 Exemplos — Aula 06: Operação Aritmética — Subtração Binária

> Exemplos resolvidos passo a passo de subtração binária direta e por complemento a 2

---

## ➖ Parte 1 — Subtração Direta com Empréstimo

### Exemplo 1: Subtração Simples (1101 - 0100)

```
              1 1 0 1    (13 em decimal)
          -   0 1 0 0    ( 4 em decimal)
          ───────────
              1 0 0 1    ( 9 em decimal) ✓
```

**Passo a passo:**

| Coluna | Operação | Resultado | Empréstimo |
|--------|----------|-----------|------------|
| Pos 0 | 1 - 0 = 1 | **1** | 0 |
| Pos 1 | 0 - 0 = 0 | **0** | 0 |
| Pos 2 | 1 - 1 = 0 | **0** | 0 |
| Pos 3 | 1 - 0 = 1 | **1** | 0 |

**Verificação:** 13 - 4 = 9 → 1001₂ = 8 + 1 = 9 ✓

---

### Exemplo 2: Subtração com Empréstimo (10010 - 01011)

```
      Empréstimos:  0  1  1  0  1
                    1  0  0  1  0    (18 em decimal)
                -   0  1  0  1  1    (11 em decimal)
                ─────────────────
                    0  0  1  1  1    ( 7 em decimal) ✓
```

**Passo a passo detalhado:**

| Coluna | Operação | Detalhe | Resultado | Empréstimo |
|--------|----------|---------|-----------|------------|
| Pos 0 | 0 - 1 | Precisa empréstimo! 10₂ - 1 = 1 | **1** | 1 |
| Pos 1 | 1 - 1 - 1(emp) | 1 - 1 = 0, 0 - 1: empréstimo! 10₂ - 1 = 1 | **1** | 1 |
| Pos 2 | 0 - 0 - 1(emp) | 0 - 0 = 0, 0 - 1: empréstimo! 10₂ - 1 = 1 | **1** | 1 |
| Pos 3 | 0 - 1 - 1(emp) | 0 - 1: empréstimo! 10₂ - 1 = 1, 1 - 1 = 0 | **0** | 1 |
| Pos 4 | 1 - 0 - 1(emp) | 1 - 0 = 1, 1 - 1 = 0 | **0** | 0 |

**Verificação:** 18 - 11 = 7 → 00111₂ = 4 + 2 + 1 = 7 ✓

---

### Exemplo 3: Subtração de 8 Bits (11000000 - 00111010)

```
      Empréstimos:  0  1  1  1  1  1  1  0
                    1  1  0  0  0  0  0  0    (192₁₀)
                -   0  0  1  1  1  0  1  0    ( 58₁₀)
                ─────────────────────────
                    1  0  0  0  0  1  1  0    (134₁₀) ✓
```

**Verificação:** 192 - 58 = 134 → 10000110₂ = 128 + 4 + 2 = 134 ✓

---

## 🔄 Parte 2 — Subtração pelo Complemento a 2

### Exemplo 4: A > B → Resultado Positivo (25 - 10, 8 bits)

```
    PASSO 1: Converter para binário
    A  = +25 = 00011001₂
    B  = +10 = 00001010₂

    PASSO 2: Obter complemento a 2 de B
    NOT(B)     = 11110101
    NOT(B) + 1 = 11110110 → -10 em C2

    PASSO 3: Somar A + (-B)
      Carry:  1 1 1 1 0 0 0 0
              0 0 0 1 1 0 0 1    (+25)
          +   1 1 1 1 0 1 1 0    (-10)
          ─────────────────────
          [1] 0 0 0 0 1 1 1 1    (+15)
           ↑
           Carry descartado

    Resultado: 00001111₂ = +15 ✓ (25 - 10 = 15)
```

---

### Exemplo 5: A < B → Resultado Negativo (30 - 75, 8 bits)

```
    PASSO 1: Converter para binário
    A = +30 = 00011110₂
    B = +75 = 01001011₂

    PASSO 2: Obter complemento a 2 de B
    NOT(B)     = 10110100
    NOT(B) + 1 = 10110101 → -75 em C2

    PASSO 3: Somar A + (-B)
      Carry:  0 0 1 1 0 1 0 0
              0 0 0 1 1 1 1 0    (+30)
          +   1 0 1 1 0 1 0 1    (-75)
          ─────────────────────
              1 1 0 1 0 0 1 1    (-45)

    PASSO 4: Verificar (MSB = 1, logo negativo)
    11010011₂ → inverte = 00101100, +1 = 00101101 = 45
    Logo: -45 ✓ (30 - 75 = -45)
```

---

### Exemplo 6: Subtração de Número Negativo (15 - (-20), 8 bits)

```
    Isso é 15 + 20 = 35!

    PASSO 1: Representar em C2
    A  = +15 = 00001111₂
    B  = -20 → C2: +20 = 00010100, inverte = 11101011, +1 = 11101100
    -B = -(-20) = +20 → C2: 00010100₂

    PASSO 2: Somar A + (-B) = 15 + 20
      Carry:  0 0 0 1 1 1 0 0
              0 0 0 0 1 1 1 1    (+15)
          +   0 0 0 1 0 1 0 0    (+20)
          ─────────────────────
              0 0 1 0 0 0 1 1    (+35) ✓

    Resultado: 00100011₂ = +35 ✓
```

---

### Exemplo 7: Overflow na Subtração (-100 - 50, 8 bits)

```
    PASSO 1: Representar em C2 (8 bits, faixa: -128 a +127)
    A  = -100 → C2: 10011100₂
    B  = +50  = 00110010₂
    -B = -50 → C2: +50 = 00110010, inverte = 11001101, +1 = 11001110

    PASSO 2: Somar A + (-B) = (-100) + (-50)
      Carry:  1 0 0 1 1 1 0 0
              1 0 0 1 1 1 0 0    (-100)
          +   1 1 0 0 1 1 1 0    (-50)
          ─────────────────────
          [1] 0 1 1 0 1 0 1 0    (+106?!) ⚠️ OVERFLOW!

    Resultado esperado: -150, mas -150 < -128 (mínimo em C2 8 bits)
    O resultado 01101010₂ = +106 está ERRADO!

    Detecção: Somamos dois negativos (MSB=1) e obtivemos positivo (MSB=0) → OVERFLOW!
```

---

## 🖥️ Parte 3 — Circuito Somador/Subtrator

### Exemplo 8: Usando o Circuito para 0110 - 0010 (6 - 2)

```
    SUB = 1 (modo subtração)

    PASSO 1: Inverter B via XOR com SUB=1
    B    = 0 0 1 0
    SUB  = 1 1 1 1  (todos os XOR recebem 1)
    B⊕1  = 1 1 0 1  → NOT(B)

    PASSO 2: Carry In = SUB = 1

    PASSO 3: Somar usando os Full Adders
    
    FA₀: A₀=0, B'₀=1, Cᵢₙ=1 → 0+1+1 = 10 → S₀=0, C₁=1
    FA₁: A₁=1, B'₁=0, C₁ =1 → 1+0+1 = 10 → S₁=0, C₂=1
    FA₂: A₂=1, B'₂=1, C₂ =1 → 1+1+1 = 11 → S₂=1, C₃=1
    FA₃: A₃=0, B'₃=1, C₃ =1 → 0+1+1 = 10 → S₃=0, C₄=1

    Resultado: S = 0100₂ = 4₁₀, Carry out = 1 (descartado em C2)
    Verificação: 6 - 2 = 4 ✓
```

| Somador | Aᵢ | B'ᵢ (invertido) | Carry In | Sᵢ | Carry Out |
|---------|-----|-----------------|----------|-----|-----------|
| FA₀ | 0 | 1 | 1 | 0 | 1 |
| FA₁ | 1 | 0 | 1 | 0 | 1 |
| FA₂ | 1 | 1 | 1 | 1 | 1 |
| FA₃ | 0 | 1 | 1 | 0 | 1 |

---

### Exemplo 9: Usando o Mesmo Circuito para Soma (0110 + 0010)

```
    SUB = 0 (modo soma)

    PASSO 1: B passa direto pelos XOR
    B    = 0 0 1 0
    SUB  = 0 0 0 0
    B⊕0  = 0 0 1 0  → B inalterado

    PASSO 2: Carry In = SUB = 0

    PASSO 3: Somar usando os Full Adders

    FA₀: A₀=0, B₀=0, Cᵢₙ=0 → 0+0+0 = 0  → S₀=0, C₁=0
    FA₁: A₁=1, B₁=1, C₁ =0 → 1+1+0 = 10 → S₁=0, C₂=1
    FA₂: A₂=1, B₂=0, C₂ =1 → 1+0+1 = 10 → S₂=0, C₃=1
    FA₃: A₃=0, B₃=0, C₃ =1 → 0+0+1 = 1  → S₃=1, C₄=0

    Resultado: S = 1000₂ = 8₁₀
    Verificação: 6 + 2 = 8 ✓
```

> 💡 **Observe:** O **mesmo circuito** fez soma e subtração — mudando apenas o sinal SUB!

---

> ⬅️ [Voltar para a Aula](../README.md) | [Exercícios →](../exercicios/README.md)
