# 📊 Exemplos — Aula 08: Expressões Booleanas de Circuitos e Circuitos de Expressões

> Exemplos resolvidos de conversão entre circuitos e expressões, e simplificação com Karnaugh

---

## 🔍 Parte 1 — Circuito para Expressão

### Exemplo 1: Circuito com AND, OR e NOT

```
    A ───────────┐
                 │AND├──┐
    B ──[NOT]────┘      │
                        │OR├──── S
    C ───────────┐      │
                 │AND├──┘
    D ───────────┘
```

**Resolução:**

| Passo | Porta | Entradas | Expressão |
|-------|-------|----------|-----------|
| 1 | NOT | B | P₁ = B̄ |
| 2 | AND₁ | A, P₁ | P₂ = A · B̄ |
| 3 | AND₂ | C, D | P₃ = C · D |
| 4 | OR | P₂, P₃ | S = A·B̄ + C·D |

**Resultado:** `S = A · B̄ + C · D`

---

### Exemplo 2: Circuito com NAND

```
    A ───┐
         │NAND├──┐
    B ───┘       │
                 │AND├──── S
    C ───┐       │
         │NAND├──┘
    D ───┘
```

**Resolução:**

| Passo | Porta | Entradas | Expressão |
|-------|-------|----------|-----------|
| 1 | NAND₁ | A, B | P₁ = NOT(A·B) = (A·B)̄ |
| 2 | NAND₂ | C, D | P₂ = NOT(C·D) = (C·D)̄ |
| 3 | AND | P₁, P₂ | S = (A·B)̄ · (C·D)̄ |

**Simplificação usando De Morgan:**

```
    S = (A·B)̄ · (C·D)̄
    
    Aplicando De Morgan em cada termo:
    (A·B)̄ = Ā + B̄
    (C·D)̄ = C̄ + D̄
    
    S = (Ā + B̄) · (C̄ + D̄)
```

---

### Exemplo 3: Circuito Complexo com 3 Níveis

```
    A ───┐
         │OR├───┐
    B ───┘      │
                │AND├──┐
    C ──────────┘      │
                       │OR├──── S
    A ──[NOT]──┐       │
               │AND├───┘
    B ──[NOT]──┘
```

**Resolução:**

| Passo | Porta | Entradas | Expressão |
|-------|-------|----------|-----------|
| 1 | OR | A, B | P₁ = A + B |
| 2 | AND₁ | P₁, C | P₂ = (A + B) · C |
| 3 | NOT₁ | A | P₃ = Ā |
| 4 | NOT₂ | B | P₄ = B̄ |
| 5 | AND₂ | P₃, P₄ | P₅ = Ā · B̄ |
| 6 | OR | P₂, P₅ | S = (A+B)·C + Ā·B̄ |

**Resultado:** `S = (A + B) · C + Ā · B̄`

**Expandindo:** `S = A·C + B·C + Ā·B̄`

**Tabela verdade para verificação:**

| A | B | C | A·C | B·C | Ā·B̄ | S |
|---|---|---|-----|-----|------|---|
| 0 | 0 | 0 | 0 | 0 | 1 | **1** |
| 0 | 0 | 1 | 0 | 0 | 1 | **1** |
| 0 | 1 | 0 | 0 | 0 | 0 | **0** |
| 0 | 1 | 1 | 0 | 1 | 0 | **1** |
| 1 | 0 | 0 | 0 | 0 | 0 | **0** |
| 1 | 0 | 1 | 1 | 0 | 0 | **1** |
| 1 | 1 | 0 | 0 | 0 | 0 | **0** |
| 1 | 1 | 1 | 1 | 1 | 0 | **1** |

---

## 🏗️ Parte 2 — Expressão para Circuito

### Exemplo 4: S = A · (B + C̄)

**Análise:**
- Operação principal: AND (entre A e o parênteses)
- Operando 1: A (entrada direta)
- Operando 2: B + C̄ (OR entre B e NOT C)

```
    Circuito:
    B ──────────┐
                │OR├───┐
    C ──[NOT]───┘      │
                       │AND├──── S
    A ─────────────────┘

    Portas necessárias: 1 NOT + 1 OR + 1 AND = 3 portas
```

---

### Exemplo 5: S = (A ⊕ B) · C + D̄

**Análise:**
- Operação principal: OR (entre (A⊕B)·C e D̄)
- Lado esquerdo: AND entre (A⊕B) e C
- Lado direito: NOT D

```
    Circuito:
    A ───┐
         │XOR├──┐
    B ───┘      │
                │AND├──┐
    C ──────────┘      │
                       │OR├──── S
    D ──[NOT]──────────┘

    Portas: 1 XOR + 1 AND + 1 NOT + 1 OR = 4 portas
```

---

### Exemplo 6: S = Ā·B̄·C + Ā·B·C + A·B·C

**Análise:** Forma SOP (Soma de Produtos) com 3 termos.

```
    Circuito (antes de simplificar):
    
    A ──[NOT]──┐
    B ──[NOT]──│AND3├──┐
    C ─────────┘       │
                       │
    A ──[NOT]──┐       │OR3├──── S
    B ─────────│AND3├──┘   │
    C ─────────┘       │   │
                       │   │
    A ─────────┐       │   │
    B ─────────│AND3├──┘
    C ─────────┘

    Portas: 2 NOT + 3 AND(3 entradas) + 1 OR(3 entradas) = 6 portas
```

**Simplificando:**

```
    S = Ā·B̄·C + Ā·B·C + A·B·C
    S = Ā·C·(B̄ + B) + A·B·C      (fatorando Ā·C)
    S = Ā·C·1 + A·B·C             (B̄ + B = 1)
    S = Ā·C + A·B·C
    S = C·(Ā + A·B)               (fatorando C)
    S = C·(Ā + B)                 (pois Ā + A·B = Ā + B)
```

```
    Circuito simplificado:
    
    A ──[NOT]──┐
               │OR├───┐
    B ─────────┘      │
                      │AND├──── S
    C ────────────────┘

    Portas: 1 NOT + 1 OR + 1 AND = 3 portas (50% de redução!)
```

---

## 🗺️ Parte 3 — Simplificação com Mapa de Karnaugh

### Exemplo 7: Karnaugh 2 Variáveis — S = Ā·B + A·B + A·B̄

**Passo 1: Tabela verdade**

| A | B | S |
|---|---|---|
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 1 |

**Passo 2: Preencher o mapa**

```
            B
          0   1
       ┌─────┬─────┐
    A 0│  0  │  1  │
       ├─────┼─────┤
      1│  1  │  1  │
       └─────┴─────┘
```

**Passo 3: Agrupar**

```
    Grupo 1 (vertical): A=0,B=1 e A=1,B=1 → B=1 nos dois → S₁ = B
    Grupo 2 (horizontal): A=1,B=0 e A=1,B=1 → A=1 nos dois → S₂ = A
```

**Resultado:** `S = A + B`

**Verificação:** Ā·B + A·B + A·B̄ = B·(Ā+A) + A·B̄ = B + A·B̄ = B + A ✓

---

### Exemplo 8: Karnaugh 3 Variáveis — S(A,B,C) = Σm(0, 1, 2, 3, 7)

**Passo 1: Tabela verdade**

| A | B | C | Mintermo | S |
|---|---|---|----------|---|
| 0 | 0 | 0 | m₀ | 1 |
| 0 | 0 | 1 | m₁ | 1 |
| 0 | 1 | 0 | m₂ | 1 |
| 0 | 1 | 1 | m₃ | 1 |
| 1 | 0 | 0 | m₄ | 0 |
| 1 | 0 | 1 | m₅ | 0 |
| 1 | 1 | 0 | m₆ | 0 |
| 1 | 1 | 1 | m₇ | 1 |

**Passo 2: Preencher o mapa**

```
              BC
           00  01  11  10
        ┌────┬────┬────┬────┐
    A  0│  1 │  1 │  1 │  1 │
        ├────┼────┼────┼────┤
       1│  0 │  0 │  1 │  0 │
        └────┴────┴────┴────┘
```

**Passo 3: Identificar grupos**

```
    Grupo 1 (4 células): m₀, m₁, m₃, m₂ → toda a linha A=0 → Ā
    
              BC
           00  01  11  10
        ╔════╤════╤════╤════╗
    A  0║  1 │  1 │  1 │  1 ║  ← Grupo 1: Ā
        ╠════╪════╪════╪════╣
       1│  0 │  0 │  1 │  0 │
        └────┴────┴────┴────┘
    
    Grupo 2 (2 células): m₃ e m₇ → BC=11 → B·C
    
              BC
           00  01  11  10
        ┌────┬────╤════╤────┐
    A  0│  1 │  1 ║  1 ║  1 │
        ├────┼────╫════╫────┤  ← Grupo 2: B·C
       1│  0 │  0 ║  1 ║  0 │
        └────┴────╨════╨────┘
```

**Passo 4: Escrever a expressão**

```
    S = Ā + B·C
```

**Verificação rápida:**
- m₀: Ā = 1 ✓
- m₇: B·C = 1 ✓
- m₄: Ā = 0, B·C = 0 → S = 0 ✓

---

### Exemplo 9: Karnaugh com Agrupamento nas Bordas

**Função:** S(A,B,C) = Σm(0, 2, 4, 6)

**Mapa:**

```
              BC
           00  01  11  10
        ┌────┬────┬────┬────┐
    A  0│  1 │  0 │  0 │  1 │
        ├────┼────┼────┼────┤
       1│  1 │  0 │  0 │  1 │
        └────┴────┴────┴────┘
```

**Agrupamento:** As colunas 00 e 10 formam um grupo de 4 (lembre-se: as bordas "se tocam"!)

```
              BC
           00  01  11  10
        ╔════╤────┬────╤════╗
    A  0║  1 │  0 │  0 │  1 ║
        ╠════╪────┼────╪════╣  ← Grupo de 4 envolvendo as bordas
       1║  1 │  0 │  0 │  1 ║
        ╚════╧────┴────╧════╝
```

O que é constante neste grupo? C = 0 em todas as células!

**Resultado:** `S = C̄`

**Verificação:** Mintermos 0, 2, 4, 6 → são exatamente os números onde C = 0 ✓

---

### Exemplo 10: Projeto Completo — Da Especificação ao Circuito

**Problema:** Projetar um circuito que acende um LED quando pelo menos 2 de 3 interruptores (A, B, C) estão ligados.

**Passo 1: Tabela verdade**

| A | B | C | LED |
|---|---|---|-----|
| 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 0 |
| 0 | 1 | 0 | 0 |
| 0 | 1 | 1 | 1 |
| 1 | 0 | 0 | 0 |
| 1 | 0 | 1 | 1 |
| 1 | 1 | 0 | 1 |
| 1 | 1 | 1 | 1 |

**Passo 2: Mapa de Karnaugh**

```
              BC
           00  01  11  10
        ┌────┬────┬────┬────┐
    A  0│  0 │  0 │  1 │  0 │
        ├────┼────┼────┼────┤
       1│  0 │  1 │  1 │  1 │
        └────┴────┴────┴────┘
```

**Passo 3: Agrupar**

```
    Grupo 1: m₃, m₇ (BC=11) → B·C
    Grupo 2: m₅, m₇ (A=1, coluna 01 e 11... não adjacentes!)
    
    Vamos re-agrupar:
    Grupo 1: m₃, m₇ → B·C        (coluna 11)
    Grupo 2: m₅, m₇ → A·C        (A=1, C=1)
    Grupo 3: m₆, m₇ → A·B        (A=1, B=1)
```

**Passo 4: Expressão**

```
    LED = A·B + A·C + B·C
```

**Passo 5: Circuito**

```
    A ───┐
         │AND├──┐
    B ───┘      │
                │
    A ───┐      │OR3├──── LED
         │AND├──┘   │
    C ───┘      │   │
                │   │
    B ───┐      │   │
         │AND├──┘
    C ───┘

    Portas: 3 AND + 1 OR(3 entradas) = 4 portas
```

---

> 💡 **Dica de estudo:** O Mapa de Karnaugh é uma ferramenta poderosa. Pratique preenchendo mapas e identificando grupos — com o tempo, você "enxergará" as simplificações instantaneamente!

---

> ⬅️ [Voltar para a Aula](../README.md) | [Exercícios →](../exercicios/README.md)
