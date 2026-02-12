# 📝 Exercícios — Aula 05: Operação Aritmética — Soma Binária

> Exercícios práticos de soma binária, complemento a 2 e circuitos somadores

**Instruções:** Mostre todos os cálculos passo a passo, incluindo os carries. Verifique seus resultados convertendo para decimal.

---

## 🟢 Nível Básico

### Exercício 1 — Regras Fundamentais

Complete a tabela com os resultados da soma binária:

| Operação | Resultado | Carry |
|----------|-----------|-------|
| 0 + 0 | | |
| 0 + 1 | | |
| 1 + 0 | | |
| 1 + 1 | | |
| 1 + 1 + 1 | | |
| 0 + 1 + 1 | | |

---

### Exercício 2 — Soma Binária Simples (4 bits)

Realize as somas binárias abaixo. Mostre os carries e verifique em decimal.

| | Operação | Resultado | Decimal |
|---|----------|-----------|---------|
| a) | 0010 + 0001 | | |
| b) | 0100 + 0011 | | |
| c) | 1010 + 0100 | | |
| d) | 0110 + 0110 | | |
| e) | 1001 + 0110 | | |

---

### Exercício 3 — Soma com Carry de Propagação

Resolva detalhadamente, mostrando a propagação do carry coluna a coluna:

a) `1011 + 0111`

b) `1110 + 0011`

c) `1111 + 1111`

> 💡 Para o item (c): qual é o resultado em decimal? Quantos bits são necessários para armazená-lo?

---

## 🟡 Nível Intermediário

### Exercício 4 — Soma de 8 Bits

Realize as somas e indique se ocorre overflow para 8 bits sem sinal (máx 255):

| | Operação | Resultado | Overflow? |
|---|----------|-----------|-----------|
| a) | 01100100 + 00110010 | | |
| b) | 10110011 + 01001101 | | |
| c) | 11111111 + 00000001 | | |
| d) | 10000000 + 10000000 | | |

---

### Exercício 5 — Conversão para Complemento a 2

Represente os seguintes números em **complemento a 2** com **8 bits**:

| | Número | Em C2 (8 bits) |
|---|--------|----------------|
| a) | +45 | |
| b) | -45 | |
| c) | +127 | |
| d) | -128 | |
| e) | -1 | |
| f) | -64 | |

> 💡 **Dica:** Para números negativos: escreva o positivo em binário, inverta todos os bits, some 1.

---

### Exercício 6 — Soma em Complemento a 2

Realize as somas em complemento a 2 (8 bits). Mostre todos os passos e indique se ocorre overflow.

a) (+30) + (+15)

b) (+50) + (-20)

c) (-10) + (-15)

d) (-1) + (+1)

> ⚠️ **Lembre-se:** A faixa de C2 com 8 bits é -128 a +127. Descarte o carry de saída.

---

### Exercício 7 — Detecção de Overflow em C2

Para cada soma em complemento a 2 (8 bits), determine:
1. O resultado
2. Se há overflow
3. Justifique sua resposta

| | Operação | Resultado | Overflow? | Justificativa |
|---|----------|-----------|-----------|---------------|
| a) | (+100) + (+50) | | | |
| b) | (-100) + (-50) | | | |
| c) | (+100) + (-50) | | | |
| d) | (-100) + (+50) | | | |

> 💡 **Regra:** Overflow em C2 só pode ocorrer ao somar dois números de **mesmo sinal**.

---

## 🔴 Nível Avançado

### Exercício 8 — Meio-Somador e Somador Completo

a) Preencha a tabela verdade do **meio-somador**:

| A | B | S (Soma) | C (Carry) |
|---|---|----------|-----------|
| 0 | 0 | | |
| 0 | 1 | | |
| 1 | 0 | | |
| 1 | 1 | | |

b) Escreva as expressões booleanas para S e C.

c) Preencha a tabela verdade do **somador completo**:

| A | B | Cₑ | S (Soma) | Cₛ (Carry Out) |
|---|---|-----|----------|-----------------|
| 0 | 0 | 0 | | |
| 0 | 0 | 1 | | |
| 0 | 1 | 0 | | |
| 0 | 1 | 1 | | |
| 1 | 0 | 0 | | |
| 1 | 0 | 1 | | |
| 1 | 1 | 0 | | |
| 1 | 1 | 1 | | |

d) Escreva as expressões booleanas para S e Cₛ.

---

### Exercício 9 — Somador de 4 Bits Passo a Passo

Usando um somador ripple-carry de 4 bits (4 somadores completos em cascata), calcule a soma de `A = 1011` e `B = 1101`. Preencha a tabela:

| Somador | Aᵢ | Bᵢ | Carry In | Sᵢ | Carry Out |
|---------|-----|-----|----------|-----|-----------|
| FA₀ | | | 0 | | |
| FA₁ | | | | | |
| FA₂ | | | | | |
| FA₃ | | | | | |

Resultado final: S = _______ , Carry Out = _______

Verifique em decimal: _______ + _______ = _______

---

### Exercício 10 — Análise Crítica

Considere um processador de 4 bits que usa complemento a 2:

a) Qual é a faixa de valores representáveis?

b) Calcule (+7) + (+1). O que acontece? Explique o fenômeno.

c) Calcule (-8) + (-1). O que acontece?

d) Por que o processador precisa de uma **flag de overflow** separada da flag de carry?

---

### Exercício 11 — Verdadeiro ou Falso

Marque V (verdadeiro) ou F (falso) e justifique as falsas:

| | Afirmação | V/F |
|---|-----------|-----|
| a) | Em binário, 1 + 1 = 2 | |
| b) | O carry na soma binária funciona como o "vai-um" na soma decimal | |
| c) | Com 8 bits sem sinal, a soma de 200 + 100 não causa overflow | |
| d) | Em complemento a 2, o bit mais significativo indica o sinal | |
| e) | No complemento a 2, para negar um número basta inverter os bits | |
| f) | O somador completo tem 3 entradas e 2 saídas | |
| g) | Overflow em C2 sempre ocorre quando há carry de saída | |
| h) | O ripple-carry adder é lento porque o carry precisa propagar | |

---

### Exercício 12 — Desafio

Um sistema embarcado usa registradores de **4 bits** em complemento a 2.

a) Liste todos os valores possíveis (binário e decimal) nesta representação.

b) O programador precisa calcular `(-5) + (-4)`. Mostre o cálculo completo e explique o resultado.

c) Proponha uma solução para que o cálculo do item (b) funcione corretamente.

d) Um somador ripple-carry de 4 bits precisa de quantos meio-somadores e quantos somadores completos se for implementado da forma mais simples? Justifique.

---

> 💡 **Dica geral:** Sempre converta para decimal para verificar suas respostas! Se a resposta em decimal não bate com a esperada, revise seu trabalho binário.

---

> ⬅️ [Exemplos](../exemplos/README.md) | [Voltar para a Aula](../README.md) | [Aula 06 →](../../06-operacao-aritmetica-subtracao/README.md)
