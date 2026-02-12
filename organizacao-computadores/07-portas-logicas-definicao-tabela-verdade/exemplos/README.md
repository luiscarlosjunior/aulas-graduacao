# 📊 Exemplos — Aula 07: Portas Lógicas — Tabelas Verdade e Expressões Booleanas

> Exemplos resolvidos de tabelas verdade, expressões booleanas e aplicação das leis da álgebra booleana

---

## 📋 Parte 1 — Tabelas Verdade

### Exemplo 1: Construir Tabela Verdade de S = A · B + C

**Passo 1:** Identificar as variáveis: A, B, C (3 variáveis → 2³ = 8 linhas)

**Passo 2:** Calcular expressões intermediárias e resultado final:

| A | B | C | A · B | S = A·B + C |
|---|---|---|-------|-------------|
| 0 | 0 | 0 | 0 | 0 + 0 = **0** |
| 0 | 0 | 1 | 0 | 0 + 1 = **1** |
| 0 | 1 | 0 | 0 | 0 + 0 = **0** |
| 0 | 1 | 1 | 0 | 0 + 1 = **1** |
| 1 | 0 | 0 | 0 | 0 + 0 = **0** |
| 1 | 0 | 1 | 0 | 0 + 1 = **1** |
| 1 | 1 | 0 | 1 | 1 + 0 = **1** |
| 1 | 1 | 1 | 1 | 1 + 1 = **1** |

> 💡 **Dica:** Calcule as operações internas primeiro (A·B), depois combine com o restante.

---

### Exemplo 2: Construir Tabela Verdade de S = (A + B) · C̄

| A | B | C | A + B | C̄ | S = (A+B) · C̄ |
|---|---|---|-------|---|---------------|
| 0 | 0 | 0 | 0 | 1 | 0 · 1 = **0** |
| 0 | 0 | 1 | 0 | 0 | 0 · 0 = **0** |
| 0 | 1 | 0 | 1 | 1 | 1 · 1 = **1** |
| 0 | 1 | 1 | 1 | 0 | 1 · 0 = **0** |
| 1 | 0 | 0 | 1 | 1 | 1 · 1 = **1** |
| 1 | 0 | 1 | 1 | 0 | 1 · 0 = **0** |
| 1 | 1 | 0 | 1 | 1 | 1 · 1 = **1** |
| 1 | 1 | 1 | 1 | 0 | 1 · 0 = **0** |

> Observe: A saída é 1 quando (A ou B é 1) **e** C é 0.

---

### Exemplo 3: Construir Tabela Verdade de S = A ⊕ B ⊕ C

| A | B | C | A ⊕ B | S = (A⊕B) ⊕ C |
|---|---|---|-------|---------------|
| 0 | 0 | 0 | 0 | 0 ⊕ 0 = **0** |
| 0 | 0 | 1 | 0 | 0 ⊕ 1 = **1** |
| 0 | 1 | 0 | 1 | 1 ⊕ 0 = **1** |
| 0 | 1 | 1 | 1 | 1 ⊕ 1 = **0** |
| 1 | 0 | 0 | 1 | 1 ⊕ 0 = **1** |
| 1 | 0 | 1 | 1 | 1 ⊕ 1 = **0** |
| 1 | 1 | 0 | 0 | 0 ⊕ 0 = **0** |
| 1 | 1 | 1 | 0 | 0 ⊕ 1 = **1** |

> 🎓 **Reconheceu?** Esta é a expressão da **soma** no somador completo! S = A ⊕ B ⊕ Cₑ. A saída é 1 quando há um número **ímpar** de 1s nas entradas.

---

## 🔢 Parte 2 — Avaliação de Expressões Booleanas

### Exemplo 4: Calcular S = Ā·B + A·B̄ para todos os valores de A e B

```
    Essa expressão é equivalente a A ⊕ B (XOR)! Vamos verificar:
```

| A | B | Ā | B̄ | Ā·B | A·B̄ | S = Ā·B + A·B̄ | A ⊕ B |
|---|---|---|---|------|------|---------------|-------|
| 0 | 0 | 1 | 1 | 1·0=0 | 0·1=0 | 0+0=**0** | **0** ✓ |
| 0 | 1 | 1 | 0 | 1·1=1 | 0·0=0 | 1+0=**1** | **1** ✓ |
| 1 | 0 | 0 | 1 | 0·0=0 | 1·1=1 | 0+1=**1** | **1** ✓ |
| 1 | 1 | 0 | 0 | 0·1=0 | 1·0=0 | 0+0=**0** | **0** ✓ |

> ✅ Confirmado: `Ā·B + A·B̄ = A ⊕ B`

---

### Exemplo 5: Calcular S = A·B̄·C + A·B·C̄ + A·B·C quando A=1, B=1, C=0

```
    Substituindo A=1, B=1, C=0:

    Termo 1: A·B̄·C = 1 · 0̄ · 0 = 1 · 1 · 0 = 0   (B̄ = NOT 1 = 0... ops!)
    
    Corrijo: B=1, então B̄=0
    
    Termo 1: A·B̄·C = 1 · 0 · 0 = 0
    Termo 2: A·B·C̄ = 1 · 1 · 1 = 1     (C̄ = NOT 0 = 1)
    Termo 3: A·B·C  = 1 · 1 · 0 = 0

    S = 0 + 1 + 0 = 1
```

---

## ⚖️ Parte 3 — Aplicação das Leis da Álgebra Booleana

### Exemplo 6: Verificar o 2º Teorema de De Morgan por Tabela Verdade

**Teorema:** `NOT(A + B) = Ā · B̄`

| A | B | A+B | NOT(A+B) | Ā | B̄ | Ā · B̄ | Iguais? |
|---|---|-----|----------|---|---|--------|---------|
| 0 | 0 | 0 | **1** | 1 | 1 | **1** | ✓ |
| 0 | 1 | 1 | **0** | 1 | 0 | **0** | ✓ |
| 1 | 0 | 1 | **0** | 0 | 1 | **0** | ✓ |
| 1 | 1 | 1 | **0** | 0 | 0 | **0** | ✓ |

> ✅ Teorema verificado! "O NOT do OR é o AND dos NOTs"

---

### Exemplo 7: Simplificar S = A·B + A·B̄ usando álgebra booleana

```
    S = A·B + A·B̄

    PASSO 1: Fatorar A (propriedade distributiva)
    S = A · (B + B̄)

    PASSO 2: Aplicar lei do complemento: B + B̄ = 1
    S = A · 1

    PASSO 3: Aplicar lei da identidade: A · 1 = A
    S = A
```

**Verificação por tabela verdade:**

| A | B | A·B | A·B̄ | A·B + A·B̄ | A |
|---|---|-----|------|-----------|---|
| 0 | 0 | 0 | 0 | **0** | **0** ✓ |
| 0 | 1 | 0 | 0 | **0** | **0** ✓ |
| 1 | 0 | 0 | 1 | **1** | **1** ✓ |
| 1 | 1 | 1 | 0 | **1** | **1** ✓ |

> ✅ Confirmado: a expressão simplifica para apenas **A**!

---

### Exemplo 8: Simplificar S = (A + B) · (A + C) usando álgebra booleana

```
    S = (A + B) · (A + C)

    PASSO 1: Aplicar distributiva do OR sobre AND
    (esta é a distributiva "reversa" da álgebra booleana)
    S = A + B · C

    Verificação via expansão:
    (A + B) · (A + C) = A·A + A·C + B·A + B·C
                      = A + A·C + A·B + B·C    (A·A = A)
                      = A·(1 + C + B) + B·C     (fatorar A)
                      = A·1 + B·C               (1 + qualquer coisa = 1)
                      = A + B·C ✓
```

---

### Exemplo 9: Aplicar De Morgan em NOT(A·B + C)

```
    S = NOT(A·B + C)

    Seja X = A·B e Y = C:
    S = NOT(X + Y)

    PASSO 1: Aplicar De Morgan: NOT(X + Y) = X̄ · Ȳ
    S = NOT(A·B) · NOT(C)

    PASSO 2: Aplicar De Morgan novamente em NOT(A·B): NOT(A·B) = Ā + B̄
    S = (Ā + B̄) · C̄

    PASSO 3 (opcional): Distribuir
    S = Ā·C̄ + B̄·C̄
```

**Verificação parcial (A=1, B=0, C=0):**
```
    Original: NOT(1·0 + 0) = NOT(0 + 0) = NOT(0) = 1
    Simplificada: (0 + 1) · 1 = 1 · 1 = 1 ✓
```

---

### Exemplo 10: NAND como Porta Universal — Construir NOT, AND e OR com NAND

**NOT usando NAND:**
```
    Conectar a mesma entrada nas duas entradas do NAND:
    NOT(A) = NAND(A, A) = NOT(A · A) = NOT(A)  ✓

    A ───┬──┐
         │  │D─o── Ā
         └──┘
```

**AND usando NAND:**
```
    Usar NAND seguido de NOT (feito com outro NAND):
    A · B = NOT(NAND(A, B)) = NOT(NOT(A · B)) = A · B  ✓

    A ──┐                    ┌──┐
        │D─o── (sinal) ──┬──│D─o── A · B
    B ──┘                 └──┘
       NAND₁                NAND₂ (como NOT)
```

**OR usando NAND:**
```
    Aplicar De Morgan: A + B = NOT(NOT(A) · NOT(B))
    = NAND(NOT(A), NOT(B))
    = NAND(NAND(A,A), NAND(B,B))

    A ──┬──┐                     ┌──┐
        │  │D─o── Ā ──────────┬──│D─o── A + B
        └──┘                  │  └──┘
    B ──┬──┐                  │  NAND₃
        │  │D─o── B̄ ─────────┘
        └──┘
       Usa 3 portas NAND no total!
```

---

> 💡 **Dica de estudo:** Para cada exemplo, tente resolver sozinho antes de olhar a solução. Cobrir a coluna de resultado e tentar preencher é excelente para fixar os conceitos!

---

> ⬅️ [Voltar para a Aula](../README.md) | [Exercícios →](../exercicios/README.md)
