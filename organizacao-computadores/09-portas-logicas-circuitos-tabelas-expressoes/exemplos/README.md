# 📊 Exemplos — Aula 09: Circuitos Lógicos, Tabelas da Verdade e Expressões Booleanas

> Exemplos resolvidos de conversão entre as três representações e simplificação com Karnaugh

---

## 🔍 Parte 1 — Tabela Verdade → Expressão (SOP e POS)

### Exemplo 1: SOP de uma Função de 2 Variáveis

**Tabela verdade:**

| A | B | S |
|---|---|---|
| 0 | 0 | 1 |
| 0 | 1 | 0 |
| 1 | 0 | 1 |
| 1 | 1 | 1 |

**Resolução (SOP):**

| Linha | A | B | Saída | Mintermo |
|-------|---|---|-------|----------|
| 0 | 0 | 0 | 1 | Ā · B̄ |
| 2 | 1 | 0 | 1 | A · B̄ |
| 3 | 1 | 1 | 1 | A · B |

**Resultado SOP:** `S = Ā·B̄ + A·B̄ + A·B`

**Simplificação:** `S = B̄(Ā + A) + A·B = B̄ + A·B = B̄ + A`

> 💡 Usando a lei da absorção: `B̄ + A·B = B̄ + A`

---

### Exemplo 2: POS de uma Função de 2 Variáveis

Usando a **mesma tabela** do Exemplo 1:

**Resolução (POS):**

| Linha | A | B | Saída | Maxtermo |
|-------|---|---|-------|----------|
| 1 | 0 | 1 | 0 | A + B̄ |

**Resultado POS:** `S = (A + B̄)`

> 💡 Como havia apenas 1 linha com saída 0, a POS é muito mais simples! Apenas 1 maxtermo.

**Verificação:** São equivalentes? `A + B̄ = Ā·B̄ + A·B̄ + A·B = B̄ + A` ✓

---

### Exemplo 3: SOP para 3 Variáveis

**Tabela verdade:**

| A | B | C | S |
|---|---|---|---|
| 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 1 |
| 0 | 1 | 0 | 1 |
| 0 | 1 | 1 | 0 |
| 1 | 0 | 0 | 1 |
| 1 | 0 | 1 | 0 |
| 1 | 1 | 0 | 0 |
| 1 | 1 | 1 | 1 |

**Resolução:**

Linhas com S = 1: 1, 2, 4, 7

| Linha | Mintermo | Notação |
|-------|----------|---------|
| 1 | Ā · B̄ · C | m₁ |
| 2 | Ā · B · C̄ | m₂ |
| 4 | A · B̄ · C̄ | m₄ |
| 7 | A · B · C | m₇ |

**Resultado:** `S = Σm(1, 2, 4, 7) = Ā·B̄·C + Ā·B·C̄ + A·B̄·C̄ + A·B·C`

---

### Exemplo 4: POS para 3 Variáveis

Usando a **mesma tabela** do Exemplo 3:

**Resolução:**

Linhas com S = 0: 0, 3, 5, 6

| Linha | Maxtermo | Notação |
|-------|----------|---------|
| 0 | A + B + C | M₀ |
| 3 | A + B̄ + C̄ | M₃ |
| 5 | Ā + B + C̄ | M₅ |
| 6 | Ā + B̄ + C | M₆ |

**Resultado:** `S = ΠM(0, 3, 5, 6) = (A+B+C)·(A+B̄+C̄)·(Ā+B+C̄)·(Ā+B̄+C)`

---

## 🔍 Parte 2 — Tabela Verdade → Circuito

### Exemplo 5: Da Tabela ao Circuito Completo

**Tabela verdade:**

| A | B | S |
|---|---|---|
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 0 |
| 1 | 1 | 1 |

**Passo 1 — Expressão SOP:** S = 1 nas linhas 1 e 3

`S = Ā·B + A·B = B·(Ā + A) = B`

**Passo 2 — Circuito:**

A expressão simplificada é simplesmente `S = B`:

```
    A ─── (não conectado)

    B ──────────── S
```

> 💡 A saída depende apenas de B! A variável A é irrelevante. Isso mostra o poder da simplificação.

---

### Exemplo 6: Tabela de 3 Variáveis → Circuito

**Tabela verdade (detector de paridade par):**

| A | B | C | S |
|---|---|---|---|
| 0 | 0 | 0 | 1 |
| 0 | 0 | 1 | 0 |
| 0 | 1 | 0 | 0 |
| 0 | 1 | 1 | 1 |
| 1 | 0 | 0 | 0 |
| 1 | 0 | 1 | 1 |
| 1 | 1 | 0 | 1 |
| 1 | 1 | 1 | 0 |

**Passo 1 — Expressão SOP:** `S = Σm(0, 3, 5, 6)`

`S = Ā·B̄·C̄ + Ā·B·C + A·B̄·C + A·B·C̄`

**Passo 2 — Reconhecimento:** Esta é a XNOR em cascata! `S = A ⊙ B ⊙ C`

Ou seja: `S = NOT(A ⊕ B ⊕ C)`

**Passo 3 — Circuito:**

```
    A ───┐
         │XOR├───┐
    B ───┘       │XOR├──[NOT]── S
                 │
    C ───────────┘
```

Ou equivalentemente:

```
    A ───┐
         │XNOR├──┐
    B ───┘        │XNOR├──── S
                  │
    C ────────────┘
```

---

## 🔍 Parte 3 — Circuito → Tabela Verdade

### Exemplo 7: Circuito com NAND e OR

**Circuito:**

```
    A ───┐
         │NAND├──┐
    B ───┘       │
                 │OR├──── S
    A ──[NOT]────┘
```

**Passo 1 — Expressão:**

| Passo | Porta | Entradas | Expressão |
|-------|-------|----------|-----------|
| 1 | NAND | A, B | P₁ = (A·B)̄ |
| 2 | NOT | A | P₂ = Ā |
| 3 | OR | P₁, P₂ | S = (A·B)̄ + Ā |

**Simplificação com De Morgan:**

```
    S = (A·B)̄ + Ā
      = (Ā + B̄) + Ā       (De Morgan)
      = Ā + B̄ + Ā
      = Ā + B̄              (Idempotência)
```

**Passo 2 — Tabela Verdade:**

| A | B | (A·B)̄ | Ā | S = Ā + B̄ |
|---|---|--------|---|-----------|
| 0 | 0 | 1 | 1 | 1 |
| 0 | 1 | 1 | 1 | 1 |
| 1 | 0 | 1 | 0 | 1 |
| 1 | 1 | 0 | 0 | 0 |

**Resultado:** `S = Σm(0, 1, 2)` — equivalente a uma porta NAND!

---

### Exemplo 8: Circuito Complexo com 3 Níveis

**Circuito:**

```
    A ───┐
         │AND├──┐
    B ───┘      │
                │OR├──┐
    C ──────────┘     │
                      │AND├──── S
    B ──[NOT]──┐      │
               │OR├───┘
    C ──[NOT]──┘
```

**Passo 1 — Expressão:**

| Passo | Porta | Entradas | Expressão |
|-------|-------|----------|-----------|
| 1 | AND | A, B | P₁ = A·B |
| 2 | OR₁ | P₁, C | P₂ = A·B + C |
| 3 | NOT₁ | B | P₃ = B̄ |
| 4 | NOT₂ | C | P₄ = C̄ |
| 5 | OR₂ | P₃, P₄ | P₅ = B̄ + C̄ |
| 6 | AND | P₂, P₅ | S = (A·B + C)·(B̄ + C̄) |

**Passo 2 — Expandindo:**

```
    S = (A·B + C)·(B̄ + C̄)
      = A·B·B̄ + A·B·C̄ + C·B̄ + C·C̄
      = 0 + A·B·C̄ + B̄·C + 0
      = A·B·C̄ + B̄·C
```

**Passo 3 — Tabela Verdade:**

| A | B | C | A·B·C̄ | B̄·C | S |
|---|---|---|--------|------|---|
| 0 | 0 | 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 0 | 1 | 1 |
| 0 | 1 | 0 | 0 | 0 | 0 |
| 0 | 1 | 1 | 0 | 0 | 0 |
| 1 | 0 | 0 | 0 | 0 | 0 |
| 1 | 0 | 1 | 0 | 1 | 1 |
| 1 | 1 | 0 | 1 | 0 | 1 |
| 1 | 1 | 1 | 0 | 0 | 0 |

**Resultado:** `S = Σm(1, 5, 6) = A·B·C̄ + B̄·C`

---

## 🔍 Parte 4 — Simplificação com Karnaugh

### Exemplo 9: K-Map 3 Variáveis — Σm(1, 3, 5, 7)

**Preencher o mapa:**

```
                  BC
               00   01   11   10
            ┌─────┬─────┬─────┬─────┐
     A   0  │  0  │  1  │  1  │  0  │
            ├─────┼─────┼─────┼─────┤
         1  │  0  │  1  │  1  │  0  │
            └─────┴─────┴─────┴─────┘
```

**Agrupamento:** 4 células nas colunas BC=01 e BC=11 (m₁, m₃, m₅, m₇) formam um grupo de 4.

O que é constante? C = 1 em todas as células.

**Resultado:** `S = C`

> 💡 A saída depende apenas de C! As variáveis A e B são irrelevantes.

---

### Exemplo 10: K-Map 4 Variáveis — Σm(0, 1, 2, 4, 5, 6, 8, 9, 10, 12, 13, 14)

**Preencher o mapa:**

```
                     CD
                 00    01    11    10
              ┌──────┬──────┬──────┬──────┐
    AB  00    │  1   │  1   │  0   │  1   │
              ├──────┼──────┼──────┼──────┤
        01    │  1   │  1   │  0   │  1   │
              ├──────┼──────┼──────┼──────┤
        11    │  1   │  1   │  0   │  1   │
              ├──────┼──────┼──────┼──────┤
        10    │  1   │  1   │  0   │  1   │
              └──────┴──────┴──────┴──────┘
```

**Agrupamento:**

- **Grupo 1** (8 células: colunas CD=00 e CD=10, todas as linhas): D=0 constante → **D̄**
- **Grupo 2** (8 células: colunas CD=00 e CD=01, todas as linhas): C=0 constante → **C̄**

Cobertura: CD=00 (✓ ambos), CD=01 (✓ G2), CD=10 (✓ G1), CD=11 (✗ — correto, é 0!)

**Resultado:** `S = C̄ + D̄`

**Verificação com De Morgan:** `S = C̄ + D̄ = (C · D)̄` → equivale a uma porta NAND de C e D!

---

### Exemplo 11: K-Map com Don't-Care

**Função:** S(A,B,C,D) = Σm(1, 3, 7, 11, 15) + Σd(0, 2, 5)

Os termos d são don't-care.

**Preencher o mapa:**

```
                     CD
                 00    01    11    10
              ┌──────┬──────┬──────┬──────┐
    AB  00    │  X   │  1   │  1   │  X   │
              ├──────┼──────┼──────┼──────┤
        01    │  0   │  X   │  1   │  0   │
              ├──────┼──────┼──────┼──────┤
        11    │  0   │  0   │  1   │  0   │
              ├──────┼──────┼──────┼──────┤
        10    │  0   │  0   │  1   │  0   │
              └──────┴──────┴──────┴──────┘
```

**Agrupamento tratando X como 1 quando vantajoso:**

- **Grupo 1** (4 células: m₃, m₇, m₁₅, m₁₁): toda a coluna CD=11 → **C·D**
- **Grupo 2** (4 células: d₀, m₁, m₃, d₂): linha AB=00 completa → **Ā·B̄**

Cobertura: m₁(✓G2), m₃(✓G1,G2), m₇(✓G1), m₁₁(✓G1), m₁₅(✓G1) ✓

**Resultado:** `S = C·D + Ā·B̄`

---

### Exemplo 12: Projeto Completo — Detector de Números Primos (0–7)

**Especificação:** Dado um número de 3 bits (A, B, C), a saída é 1 se o número for primo.

Primos de 0 a 7: **2, 3, 5, 7**

**Tabela verdade:**

| Decimal | A | B | C | S (primo?) |
|---------|---|---|---|-----------|
| 0 | 0 | 0 | 0 | 0 |
| 1 | 0 | 0 | 1 | 0 |
| 2 | 0 | 1 | 0 | 1 |
| 3 | 0 | 1 | 1 | 1 |
| 4 | 1 | 0 | 0 | 0 |
| 5 | 1 | 0 | 1 | 1 |
| 6 | 1 | 1 | 0 | 0 |
| 7 | 1 | 1 | 1 | 1 |

**Expressão SOP:** `S = Σm(2, 3, 5, 7)`

**Mapa de Karnaugh:**

```
                  BC
               00   01   11   10
            ┌─────┬─────┬─────┬─────┐
     A   0  │  0  │  0  │  1  │  1  │
            ├─────┼─────┼─────┼─────┤
         1  │  0  │  1  │  1  │  0  │
            └─────┴─────┴─────┴─────┘

    Grupo 1: m₂, m₃ → Ā·B
    Grupo 2: m₃, m₇ → B·C
    Grupo 3: m₅, m₇ → A·C
```

**Resultado simplificado:** `S = Ā·B + B·C + A·C`

**Circuito:**

```
    A ──[NOT]──┐
               │AND├──┐
    B ─────────┘      │
                      │
    B ───┐            │OR├──── S
         │AND├────────┤ (3 entradas)
    C ───┘            │
                      │
    A ───┐            │
         │AND├────────┘
    C ───┘
```

**Total:** 1 NOT + 3 AND + 1 OR (3 entradas) = **5 portas**

Sem simplificação seriam 4 AND(3 ent.) + 1 OR(4 ent.) + 2 NOT = **7 portas** → Economia de 29%!

---

> 💡 **Dica geral:** Ao resolver problemas de conversão, sempre verifique seu resultado comparando as tabelas verdade. Se todas as saídas coincidirem, sua conversão está correta!

---

> ⬅️ [Exercícios](../exercicios/README.md) | [Voltar para a Aula](../README.md)
