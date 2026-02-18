# 📝 Exercícios — Aula 08: Expressões Booleanas de Circuitos e Circuitos de Expressões

> Exercícios práticos de conversão entre circuitos e expressões, simplificação e Mapa de Karnaugh

**Instruções:** Para exercícios de circuito, desenhe o circuito em texto (como nos exemplos). Para simplificações, indique cada lei usada.

---

## 🟢 Nível Básico

### Exercício 1 — Circuito para Expressão

Encontre a expressão booleana de cada circuito:

**a)**
```
    A ───┐
         │OR├──┐
    B ───┘     │
               │AND├──── S
    C ─────────┘
```

**b)**
```
    A ──[NOT]──┐
               │OR├──── S
    B ─────────┘
```

**c)**
```
    A ───┐
         │AND├──┐
    B ───┘      │
                │OR├──── S
    C ───┐      │
         │AND├──┘
    D ───┘
```

---

### Exercício 2 — Expressão para Circuito

Desenhe o circuito lógico para cada expressão:

a) `S = A · B + C`

b) `S = (A + B) · C̄`

c) `S = Ā · B + A · B̄`

d) `S = A · B · C + D`

Para cada circuito, indique quantas portas foram utilizadas.

---

### Exercício 3 — Expressões de Circuitos com NAND e NOR

Encontre a expressão de cada circuito e simplifique:

**a)**
```
    A ───┐
         │NAND├──── S
    B ───┘
```

**b)**
```
    A ───┐
         │NOR├──── S
    B ───┘
```

**c)**
```
    A ──┬──┐
        │  │NAND├──── S
        └──┘
```

> 💡 **Dica para (c):** Quando ambas as entradas de um NAND são iguais, o que acontece?

---

## 🟡 Nível Intermediário

### Exercício 4 — Circuitos com Múltiplos Níveis

Encontre a expressão e construa a tabela verdade:

**a)**
```
    A ───┐
         │AND├──┐
    B ───┘      │
                │OR├──┐
    C ──────────┘     │
                      │AND├──── S
    D ──[NOT]─────────┘
```

**b)**
```
    A ──[NOT]──┐
               │AND├──┐
    B ─────────┘      │
                      │OR├──── S
    A ─────────┐      │
               │AND├──┘
    C ──[NOT]──┘
```

---

### Exercício 5 — Simplificação Algébrica

Simplifique cada expressão usando as leis da álgebra booleana. Mostre os passos:

a) `S = A·B·C + A·B·C̄ + A·B̄·C`

b) `S = (A + B) · (A + C) · (B + C)`

c) `S = Ā·B̄·C̄ + Ā·B̄·C + A·B̄·C̄ + A·B̄·C`

d) `S = A·B + A·B̄ + Ā·B`

---

### Exercício 6 — Mapa de Karnaugh (2 Variáveis)

Use o Mapa de Karnaugh para simplificar:

**a)** S(A,B) = Σm(0, 1, 3)

```
            B
          0   1
       ┌─────┬─────┐
    A 0│     │     │
       ├─────┼─────┤
      1│     │     │
       └─────┴─────┘
```

**b)** S(A,B) = Σm(1, 2, 3)

**c)** S(A,B) = Σm(0, 1, 2, 3) ← Qual é o resultado?

---

### Exercício 7 — Mapa de Karnaugh (3 Variáveis)

Use o Mapa de Karnaugh para simplificar:

**a)** S(A,B,C) = Σm(0, 1, 4, 5)

```
              BC
           00  01  11  10
        ┌────┬────┬────┬────┐
    A  0│    │    │    │    │
        ├────┼────┼────┼────┤
       1│    │    │    │    │
        └────┴────┴────┴────┘
```

**b)** S(A,B,C) = Σm(3, 4, 5, 7)

**c)** S(A,B,C) = Σm(0, 2, 4, 6)

**d)** S(A,B,C) = Σm(1, 3, 5, 6, 7)

> ⚠️ Lembre-se: a ordem das colunas é **00, 01, 11, 10** (código Gray)!

---

## 🔴 Nível Avançado

### Exercício 8 — Equivalência de Circuitos

Prove que os dois circuitos abaixo são equivalentes:

**Circuito 1:**
```
    A ──[NOT]──┐
               │AND├──┐
    B ──[NOT]──┘      │
                      │OR├──── S₁
    A ─────────┐      │
               │AND├──┘
    B ─────────┘
```

**Circuito 2:**
```
    A ───┐
         │XNOR├──── S₂
    B ───┘
```

Método: Extraia as expressões, construa as tabelas verdade e compare.

---

### Exercício 9 — Projeto Completo (Especificação → Circuito Simplificado)

Um sistema de alarme tem 3 sensores (A, B, C). O alarme dispara (S=1) quando:
- O sensor A detecta movimento **e** pelo menos um dos outros dois detecta
- **OU** quando os sensores B e C detectam simultaneamente

a) Construa a tabela verdade.

b) Escreva a expressão na forma de soma de produtos.

c) Simplifique usando Mapa de Karnaugh.

d) Desenhe o circuito simplificado.

e) Quantas portas o circuito original e o simplificado precisam?

---

### Exercício 10 — Verdadeiro ou Falso

| | Afirmação | V/F |
|---|-----------|-----|
| a) | Para ler um circuito, começamos pela saída e vamos para as entradas | |
| b) | A forma SOP (Soma de Produtos) resulta em um circuito de 2 níveis | |
| c) | O Mapa de Karnaugh para 3 variáveis tem 8 células | |
| d) | No Mapa de Karnaugh, as colunas seguem a ordem binária natural (00, 01, 10, 11) | |
| e) | Grupos no Karnaugh devem ter tamanho potência de 2 | |
| f) | Dois circuitos com expressões diferentes nunca podem ser equivalentes | |
| g) | Simplificar um circuito pode torná-lo mais lento | |
| h) | As bordas do Mapa de Karnaugh são adjacentes (se "tocam") | |

---

### Exercício 11 — Comparação de Circuitos

Para a função S(A,B,C) = Σm(1, 2, 5, 6):

a) Escreva a expressão na forma canônica (soma de mintermos).

b) Simplifique usando Mapa de Karnaugh.

c) Desenhe o circuito **antes** da simplificação. Conte as portas.

d) Desenhe o circuito **depois** da simplificação. Conte as portas.

e) Calcule a porcentagem de redução de portas.

---

### Exercício 12 — Desafio: Display de 7 Segmentos

Um display de 7 segmentos mostra dígitos de 0 a 3 usando 2 bits de entrada (A, B):

```
     ─a─
    |   |
    f   b
    |   |
     ─g─
    |   |
    e   c
    |   |
     ─d─
```

| A | B | Dígito | a | b | c | d | e | f | g |
|---|---|--------|---|---|---|---|---|---|---|
| 0 | 0 | 0 | 1 | 1 | 1 | 1 | 1 | 1 | 0 |
| 0 | 1 | 1 | 0 | 1 | 1 | 0 | 0 | 0 | 0 |
| 1 | 0 | 2 | 1 | 1 | 0 | 1 | 1 | 0 | 1 |
| 1 | 1 | 3 | 1 | 1 | 1 | 1 | 0 | 0 | 1 |

a) Encontre a expressão booleana para o segmento **a** (use Karnaugh se desejar).

b) Encontre a expressão booleana para o segmento **g**.

c) Desenhe o circuito para o segmento **a**.

> 💡 Este exercício mostra como os displays dos relógios digitais, calculadoras e painéis eletrônicos funcionam internamente!

---

> 💡 **Dica geral:** Sempre valide suas simplificações comparando as tabelas verdade da expressão original e da simplificada. Se todas as saídas forem iguais, a simplificação está correta!

---

> ⬅️ [Exemplos](../exemplos/README.md) | [Voltar para a Aula](../README.md)
