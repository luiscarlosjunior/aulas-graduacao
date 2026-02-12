# 📝 Exercícios — Aula 07: Portas Lógicas — Tabelas Verdade e Expressões Booleanas

> Exercícios práticos sobre portas lógicas, tabelas verdade e álgebra booleana

**Instruções:** Para exercícios de tabela verdade, mostre colunas intermediárias. Para simplificações, indique a lei usada em cada passo.

---

## 🟢 Nível Básico

### Exercício 1 — Identificação de Portas

Para cada descrição, identifique a porta lógica correspondente:

| | Descrição | Porta |
|---|-----------|-------|
| a) | Saída = 1 somente quando todas as entradas são 1 | |
| b) | Saída = 1 quando pelo menos uma entrada é 1 | |
| c) | Inverte a entrada | |
| d) | Saída = 1 quando as entradas são diferentes | |
| e) | Saída = 0 somente quando todas as entradas são 1 | |
| f) | Saída = 1 somente quando todas as entradas são 0 | |
| g) | Saída = 1 quando as entradas são iguais | |

---

### Exercício 2 — Tabelas Verdade Básicas

Complete as tabelas verdade:

**a) S = A · B̄**

| A | B | B̄ | S = A · B̄ |
|---|---|---|----------|
| 0 | 0 | | |
| 0 | 1 | | |
| 1 | 0 | | |
| 1 | 1 | | |

**b) S = Ā + B**

| A | B | Ā | S = Ā + B |
|---|---|---|---------|
| 0 | 0 | | |
| 0 | 1 | | |
| 1 | 0 | | |
| 1 | 1 | | |

---

### Exercício 3 — Avaliação de Expressões

Calcule o valor de cada expressão para os valores dados:

| | Expressão | A | B | C | Resultado |
|---|-----------|---|---|---|-----------|
| a) | A · B + C | 1 | 0 | 1 | |
| b) | (A + B) · C | 0 | 1 | 1 | |
| c) | Ā · B̄ | 1 | 0 | — | |
| d) | A ⊕ B | 1 | 1 | — | |
| e) | NOT(A · B) + C | 1 | 1 | 0 | |
| f) | A · (B + C̄) | 1 | 0 | 0 | |

---

## 🟡 Nível Intermediário

### Exercício 4 — Tabelas Verdade de 3 Variáveis

Construa a tabela verdade completa (8 linhas) para cada expressão:

a) `S = A · B + B · C`

b) `S = (A + B) · (Ā + C)`

c) `S = A ⊕ B · C`

> 💡 **Atenção à precedência:** AND antes de OR, NOT antes de tudo!

---

### Exercício 5 — Expressões a Partir de Tabelas Verdade

Dada a tabela verdade, escreva a expressão booleana na forma de **soma de produtos** (mintermos onde S=1):

**a)**

| A | B | S |
|---|---|---|
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 0 |

**b)**

| A | B | C | S |
|---|---|---|---|
| 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 1 |
| 0 | 1 | 0 | 0 |
| 0 | 1 | 1 | 1 |
| 1 | 0 | 0 | 0 |
| 1 | 0 | 1 | 0 |
| 1 | 1 | 0 | 1 |
| 1 | 1 | 1 | 1 |

> 💡 **Dica:** Para cada linha onde S=1, escreva o produto (AND) das variáveis (com NOT onde for 0). Depois junte tudo com OR.

---

### Exercício 6 — Leis da Álgebra Booleana

Identifique qual lei foi aplicada em cada simplificação:

| | De | Para | Lei |
|---|---|------|-----|
| a) | A + 0 | A | |
| b) | A · A | A | |
| c) | A + Ā | 1 | |
| d) | A · B + A · C | A · (B + C) | |
| e) | NOT(NOT(A)) | A | |
| f) | A + 1 | 1 | |
| g) | A · Ā | 0 | |

---

### Exercício 7 — Teoremas de De Morgan

Aplique os Teoremas de De Morgan para simplificar:

a) `NOT(A · B)` = ?

b) `NOT(A + B + C)` = ?

c) `NOT(Ā · B)` = ?

d) `NOT((A + B) · C)` = ?

> 💡 **Lembre-se:** "Quebre a barra, troque a operação!" Para mais de 2 variáveis, aplique De Morgan generalizando.

---

## 🔴 Nível Avançado

### Exercício 8 — Simplificação Algébrica

Simplifique cada expressão usando as leis da álgebra booleana. Mostre cada passo e a lei usada:

a) `S = A·B + A·B̄`

b) `S = A·B·C + A·B·C̄`

c) `S = (A + B) · (A + B̄)`

d) `S = A·B + Ā·B + A·B̄ + Ā·B̄`

---

### Exercício 9 — Equivalência de Expressões

Prove que as expressões são equivalentes usando tabela verdade ou álgebra booleana:

a) `A ⊕ B = Ā·B + A·B̄`

b) `NAND(A,B) = Ā + B̄` (De Morgan)

c) `A + A·B = A` (Absorção)

---

### Exercício 10 — Portas Universais

a) Usando **apenas portas NAND**, desenhe o circuito equivalente a uma porta **OR** de 2 entradas.

b) Usando **apenas portas NOR**, desenhe o circuito equivalente a uma porta **AND** de 2 entradas.

c) Quantas portas NAND são necessárias para implementar a expressão `S = A + B · C`?

---

### Exercício 11 — Verdadeiro ou Falso

| | Afirmação | V/F |
|---|-----------|-----|
| a) | Na álgebra booleana, 1 + 1 = 2 | |
| b) | A porta NAND é chamada "universal" porque pode implementar qualquer função lógica | |
| c) | A XOR tem saída 1 quando o número de entradas em 1 é ímpar | |
| d) | NOT(NOT(A)) = A é chamada de lei da involução | |
| e) | A precedência correta é: OR > AND > NOT | |
| f) | De Morgan: NOT(A · B) = NOT(A) + NOT(B) | |
| g) | A porta OR é equivalente a dois interruptores em série | |
| h) | A tabela verdade de n variáveis tem 2ⁿ linhas | |

---

### Exercício 12 — Desafio: Projeto Lógico

Um sistema de votação tem 3 juízes (A, B, C). A proposta é aprovada quando pelo **menos 2 juízes** votam a favor (1 = sim).

a) Construa a tabela verdade completa.

b) Escreva a expressão booleana na forma de soma de produtos.

c) Simplifique a expressão usando álgebra booleana.

d) Quais portas lógicas seriam necessárias para implementar o circuito simplificado?

> 💡 Este é o famoso **circuito de maioria** (majority gate) — um dos blocos fundamentais de eletrônica digital!

---

> 💡 **Dica geral:** Sempre verifique suas simplificações construindo a tabela verdade da expressão original e da simplificada. Se as colunas de saída forem idênticas, a simplificação está correta!

---

> ⬅️ [Exemplos](../exemplos/README.md) | [Voltar para a Aula](../README.md) | [Aula 08 →](../../08-portas-logicas-expressoes-booleanas-circuitos/README.md)
