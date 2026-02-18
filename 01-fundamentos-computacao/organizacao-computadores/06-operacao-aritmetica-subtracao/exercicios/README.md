# 📝 Exercícios — Aula 06: Operação Aritmética — Subtração Binária

> Exercícios práticos de subtração binária, complemento a 2 e circuito somador/subtrator

**Instruções:** Mostre todos os cálculos passo a passo. Verifique seus resultados convertendo para decimal.

---

## 🟢 Nível Básico

### Exercício 1 — Regras Fundamentais

Complete a tabela com os resultados da subtração binária:

| Operação | Resultado | Empréstimo? |
|----------|-----------|-------------|
| 0 - 0 | | |
| 1 - 0 | | |
| 1 - 1 | | |
| 0 - 1 | | |
| 10 - 1 | | |
| 11 - 1 | | |

---

### Exercício 2 — Subtração Direta (4 bits)

Realize as subtrações binárias abaixo usando o método direto com empréstimo. Mostre os empréstimos e verifique em decimal.

| | Operação | Resultado | Decimal |
|---|----------|-----------|---------|
| a) | 1010 - 0001 | | |
| b) | 1100 - 0011 | | |
| c) | 1111 - 0101 | | |
| d) | 1000 - 0001 | | |
| e) | 1001 - 0110 | | |

---

### Exercício 3 — Subtração com Múltiplos Empréstimos

Resolva detalhadamente, mostrando cada empréstimo:

a) `10000 - 00111`

b) `11000000 - 00010101`

c) `10000000 - 00000001`

> 💡 **Dica:** O item (c) é um caso especial — o empréstimo propaga por todas as colunas!

---

## 🟡 Nível Intermediário

### Exercício 4 — Complemento a 2 para Subtração

Para cada par de números, calcule A - B usando o método do **complemento a 2** em **8 bits**:

| | A | B | A - B |
|---|---|---|-------|
| a) | +40 | +15 | |
| b) | +60 | +80 | |
| c) | +100 | +100 | |
| d) | +10 | +127 | |

**Para cada item, mostre:**
1. A e B em binário (8 bits)
2. O complemento a 2 de B
3. A soma A + C2(B)
4. O resultado final (com sinal)

---

### Exercício 5 — Subtração com Números Negativos

Realize as operações em complemento a 2 (8 bits):

a) (+50) - (-30) = ?

b) (-20) - (+15) = ?

c) (-40) - (-60) = ?

d) (-1) - (-1) = ?

> 💡 **Lembre-se:** A - B = A + (-B). Se B já é negativo, -B se torna positivo!

---

### Exercício 6 — Detecção de Overflow

Para cada operação em C2 (8 bits, faixa -128 a +127), calcule o resultado e determine se há overflow:

| | Operação | Resultado | Overflow? | Por quê? |
|---|----------|-----------|-----------|----------|
| a) | (+120) - (-20) | | | |
| b) | (-100) - (+40) | | | |
| c) | (+80) - (+90) | | | |
| d) | (-50) - (-60) | | | |

---

### Exercício 7 — Comparação de Métodos

Calcule `11010 - 01110` (26 - 14) de **duas formas**:

a) Subtração direta com empréstimo

b) Usando complemento a 2 (5 bits)

c) Os resultados são iguais? Compare a dificuldade de cada método.

---

## 🔴 Nível Avançado

### Exercício 8 — Circuito Somador/Subtrator

Considere o circuito somador/subtrator de 4 bits com sinal SUB.

a) Para A = 1010 e B = 0011, com SUB = 1 (subtração), preencha a tabela:

| Somador | Aᵢ | Bᵢ | Bᵢ ⊕ SUB | Carry In | Sᵢ | Carry Out |
|---------|-----|-----|----------|----------|-----|-----------|
| FA₀ | | | | 1 (SUB) | | |
| FA₁ | | | | | | |
| FA₂ | | | | | | |
| FA₃ | | | | | | |

Resultado: S = _______ , Carry = _______

Verifique em decimal: _______ - _______ = _______

b) Refaça com SUB = 0 (soma) para os mesmos valores de A e B.

---

### Exercício 9 — Flags da ULA

Para cada operação abaixo (8 bits, C2), determine o valor de cada flag:

| Operação | Z (Zero) | N (Negativo) | C (Carry) | V (Overflow) |
|----------|----------|-------------|-----------|-------------|
| 50 - 50 | | | | |
| 50 - 30 | | | | |
| 30 - 50 | | | | |
| 120 - (-20) | | | | |

> 💡 **Dicas:**
> - Z = 1 quando o resultado é zero
> - N = 1 quando o MSB do resultado é 1
> - C = carry de saída da operação
> - V = overflow em complemento a 2

---

### Exercício 10 — Verdadeiro ou Falso

Marque V (verdadeiro) ou F (falso) e justifique as falsas:

| | Afirmação | V/F |
|---|-----------|-----|
| a) | Em binário, 0 - 1 = 1 com empréstimo da próxima posição | |
| b) | O computador precisa de um circuito subtrator separado do somador | |
| c) | A - B em complemento a 2 é calculado como A + NOT(B) + 1 | |
| d) | Overflow na subtração pode ocorrer quando os operandos têm o mesmo sinal | |
| e) | A instrução CMP (compare) realiza uma subtração sem armazenar o resultado | |
| f) | Com 8 bits em C2, (-128) - 1 não causa overflow | |
| g) | O sinal SUB do circuito somador/subtrator controla as portas XOR e o Carry In | |
| h) | Subtrair é sempre mais lento que somar no processador | |

---

### Exercício 11 — Análise de Faixa

Um microcontrolador usa registradores de 8 bits em complemento a 2.

a) Qual é o maior resultado possível de uma subtração?

b) Qual é o menor resultado possível de uma subtração?

c) Dê um exemplo de subtração que produz o maior resultado.

d) Dê um exemplo de subtração que produz o menor resultado.

e) Dê dois exemplos de subtração que causam overflow.

---

### Exercício 12 — Desafio: Subtração em Cascata

Calcule a expressão `A - B - C` em complemento a 2 (8 bits), onde A = +80, B = +30, C = +25.

a) Faça em duas etapas: primeiro (A - B), depois (resultado - C).

b) Verifique se houve overflow em alguma etapa.

c) Refaça como A + (-B) + (-C) em uma única soma de três parcelas. O resultado é o mesmo?

---

> 💡 **Dica geral:** A subtração por complemento a 2 transforma tudo em soma. Quando estiver em dúvida, siga o algoritmo: inverta os bits de B, some 1, e depois some com A!

---

> ⬅️ [Exemplos](../exemplos/README.md) | [Voltar para a Aula](../README.md) | [Aula 07 →](../../07-portas-logicas-definicao-tabela-verdade/README.md)
