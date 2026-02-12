# 📝 Exercícios — Aula 02: Sistemas de Numeração — Decimal e Binário

> Exercícios de conversão de dificuldade crescente

**Instruções:** Mostre todos os passos das conversões. Não use calculadora — o objetivo é dominar o método manual.

---

## 🟢 Nível Básico

### Exercício 1 — Decimal para Binário (números pequenos)

Converta os seguintes números decimais para binário usando o **método das divisões sucessivas**. Mostre todos os passos.

| | Decimal | Binário |
|---|---------|---------|
| a) | 7 | |
| b) | 10 | |
| c) | 15 | |
| d) | 16 | |
| e) | 20 | |

---

### Exercício 2 — Binário para Decimal (números pequenos)

Converta os seguintes números binários para decimal usando o **método dos pesos posicionais**. Mostre todos os cálculos.

| | Binário | Decimal |
|---|---------|---------|
| a) | 101 | |
| b) | 1100 | |
| c) | 10001 | |
| d) | 11111 | |
| e) | 10101 | |

---

### Exercício 3 — Verdadeiro ou Falso

Classifique como **Verdadeiro (V)** ou **Falso (F)**. Corrija as falsas.

a) ( ) O número binário 1111₂ equivale a 16₁₀.

b) ( ) Com 4 bits é possível representar 16 valores diferentes (de 0 a 15).

c) ( ) O sistema binário utiliza os dígitos 0, 1 e 2.

d) ( ) Em um sistema posicional, o valor de um dígito depende da sua posição.

e) ( ) O número 10₂ (binário) equivale a 10₁₀ (decimal).

f) ( ) O maior número que pode ser representado com 8 bits é 256.

---

## 🟡 Nível Intermediário

### Exercício 4 — Decimal para Binário (números maiores)

Converta para binário. Mostre todos os passos:

| | Decimal | Binário |
|---|---------|---------|
| a) | 50 | |
| b) | 99 | |
| c) | 128 | |
| d) | 175 | |
| e) | 255 | |
| f) | 1000 | |

---

### Exercício 5 — Binário para Decimal (8 bits)

Converta para decimal:

| | Binário | Decimal |
|---|---------|---------|
| a) | 01100100 | |
| b) | 10101010 | |
| c) | 11001100 | |
| d) | 00110011 | |
| e) | 10000001 | |
| f) | 01111110 | |

---

### Exercício 6 — Quantos Bits são Necessários?

Determine o número **mínimo** de bits necessários para representar cada valor decimal:

| | Valor Decimal | Bits Necessários | Justificativa |
|---|--------------|-----------------|---------------|
| a) | 7 | | |
| b) | 8 | | |
| c) | 100 | | |
| d) | 255 | | |
| e) | 256 | | |
| f) | 1023 | | |
| g) | 1024 | | |

> 💡 **Dica:** Você precisa de n bits se 2ⁿ⁻¹ ≤ valor ≤ 2ⁿ − 1.

---

### Exercício 7 — Conversão de Frações

Converta os seguintes números fracionários:

**Decimal → Binário:**

| | Decimal | Binário |
|---|---------|---------|
| a) | 0,5 | |
| b) | 0,25 | |
| c) | 0,875 | |
| d) | 3,75 | |

**Binário → Decimal:**

| | Binário | Decimal |
|---|---------|---------|
| e) | 0,1 | |
| f) | 1,01 | |
| g) | 110,101 | |
| h) | 0,001 | |

---

## 🔴 Nível Avançado

### Exercício 8 — Padrões Binários

Analise os padrões e responda:

a) Qual é o resultado de somar 1 ao número binário 1111₂? O que acontece?

b) Observe a sequência: 1, 10, 100, 1000, 10000 em binário. Qual é a regra? Qual o equivalente decimal?

c) Observe: 1, 11, 111, 1111, 11111. Qual o equivalente decimal de cada? Que fórmula pode descrever esse padrão?

d) Se um computador usa endereços de **32 bits**, quantos endereços de memória diferentes ele pode acessar?

---

### Exercício 9 — Problemas Práticos

a) **Cores RGB:** Uma cor em computador é representada por 3 componentes (Vermelho, Verde, Azul), cada uma com valores de 0 a 255. Quantos bits são necessários para representar **uma cor completa**? Quantas cores diferentes são possíveis?

b) **Endereço IPv4:** Um endereço IP (v4) é composto por 4 números de 0 a 255 (ex: 192.168.1.1). Quantos bits compõem um endereço IPv4? Quantos endereços IP diferentes são possíveis?

c) **Caracteres ASCII:** A tabela ASCII usa 7 bits para representar caracteres. Quantos caracteres diferentes podem ser representados? Se usarmos 8 bits (ASCII estendido), quantos caracteres temos?

---

### Exercício 10 — O Problema do 0,1

a) Tente converter **0,1₁₀** para binário usando o método de multiplicação por 2 (faça pelo menos 10 iterações). O que acontece?

b) Em Python, digite `0.1 + 0.2` e veja o resultado. Por que o resultado **não é exatamente 0.3**?

c) Se 0,1 não pode ser representado exatamente em binário, como programadores lidam com isso em sistemas financeiros (onde centavos importam)?

---

### Exercício 11 — Desafio de Conversão Rápida

Converta **mentalmente** (sem usar divisões escritas) os seguintes números, usando a técnica de subtração de potências de 2:

> **Potências de 2 para referência:** 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024

| | Decimal | Binário (resposta direta) |
|---|---------|--------------------------|
| a) | 64 | |
| b) | 33 | |
| c) | 127 | |
| d) | 200 | |
| e) | 500 | |

---

### Exercício 12 — Reflexão Conceitual

Responda com suas próprias palavras:

a) Por que os computadores usam o sistema binário em vez do decimal? Liste pelo menos **3 razões**.

b) Se um extraterrestre tivesse 6 dedos em cada mão (12 no total), qual sistema de numeração ele provavelmente usaria? Quais seriam os dígitos?

c) Explique, usando uma analogia do cotidiano, o conceito de **notação posicional**.

d) Qual a relação entre o número de bits e a quantidade de informação que pode ser representada? Expresse matematicamente.

---

## 📌 Gabarito — Nível Básico (para auto-verificação)

<details>
<summary>Clique para ver as respostas do Exercício 1</summary>

| | Decimal | Binário |
|---|---------|---------|
| a) | 7 | 111 |
| b) | 10 | 1010 |
| c) | 15 | 1111 |
| d) | 16 | 10000 |
| e) | 20 | 10100 |

</details>

<details>
<summary>Clique para ver as respostas do Exercício 2</summary>

| | Binário | Decimal |
|---|---------|---------|
| a) | 101 | 5 |
| b) | 1100 | 12 |
| c) | 10001 | 17 |
| d) | 11111 | 31 |
| e) | 10101 | 21 |

</details>

<details>
<summary>Clique para ver as respostas do Exercício 3</summary>

a) **FALSO** — 1111₂ = 8 + 4 + 2 + 1 = 15₁₀ (não 16)

b) **VERDADEIRO** — 2⁴ = 16 valores (0 a 15)

c) **FALSO** — O sistema binário utiliza apenas os dígitos 0 e 1

d) **VERDADEIRO** — Essa é a definição de sistema posicional

e) **FALSO** — 10₂ = 1×2 + 0×1 = 2₁₀

f) **FALSO** — O maior número com 8 bits é 2⁸ − 1 = 255 (não 256)

</details>

---

## 📌 Critérios de Avaliação

| Critério | Peso |
|---------|------|
| Método correto de conversão | 30% |
| Passos intermediários mostrados | 25% |
| Resposta final correta | 30% |
| Verificação / conferência | 15% |

---

<div align="center">

**📝 Pratique até se sentir confiante com as conversões!**

*Voltar para a [Aula 02](../README.md)*

</div>
