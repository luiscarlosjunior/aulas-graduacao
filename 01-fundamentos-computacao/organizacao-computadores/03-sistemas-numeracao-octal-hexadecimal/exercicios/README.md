# 📝 Exercícios — Aula 03: Sistemas de Numeração — Octal e Hexadecimal

> Exercícios práticos de conversão entre bases

**Instruções:** Mostre todos os passos das conversões. Use os métodos ensinados na aula.

---

## 🟢 Nível Básico

### Exercício 1 — Octal para Decimal

Converta os seguintes números octais para decimal:

| | Octal | Decimal |
|---|-------|---------|
| a) | 17₈ | |
| b) | 52₈ | |
| c) | 100₈ | |
| d) | 377₈ | |
| e) | 644₈ | |

---

### Exercício 2 — Hexadecimal para Decimal

Converta os seguintes números hexadecimais para decimal:

| | Hexadecimal | Decimal |
|---|-------------|---------|
| a) | A₁₆ | |
| b) | 1F₁₆ | |
| c) | FF₁₆ | |
| d) | 100₁₆ | |
| e) | 2A3₁₆ | |

---

### Exercício 3 — Binário ↔ Octal (Agrupamento de 3)

**Binário → Octal:**

| | Binário | Octal |
|---|---------|-------|
| a) | 110111₂ | |
| b) | 101010101₂ | |
| c) | 1111111₂ | |

**Octal → Binário:**

| | Octal | Binário |
|---|-------|---------|
| d) | 75₈ | |
| e) | 123₈ | |
| f) | 600₈ | |

---

### Exercício 4 — Binário ↔ Hexadecimal (Agrupamento de 4)

**Binário → Hexadecimal:**

| | Binário | Hexadecimal |
|---|---------|-------------|
| a) | 11010110₂ | |
| b) | 10101011110011₂ | |
| c) | 11111111₂ | |

**Hexadecimal → Binário:**

| | Hexadecimal | Binário |
|---|-------------|---------|
| d) | 9A₁₆ | |
| e) | FACE₁₆ | |
| f) | 100₁₆ | |

---

## 🟡 Nível Intermediário

### Exercício 5 — Decimal para Octal e Hexadecimal

Converta cada número decimal para **octal** e **hexadecimal**:

| | Decimal | Octal | Hexadecimal |
|---|---------|-------|-------------|
| a) | 100 | | |
| b) | 255 | | |
| c) | 500 | | |
| d) | 1024 | | |
| e) | 4096 | | |

---

### Exercício 6 — Conversão Octal ↔ Hexadecimal (via binário)

Converta usando binário como intermediário. Mostre os dois passos:

| | Origem | Destino |
|---|--------|---------|
| a) | 752₈ → | ___₁₆ |
| b) | 1234₈ → | ___₁₆ |
| c) | ABC₁₆ → | ___₈ |
| d) | FF0₁₆ → | ___₈ |

---

### Exercício 7 — Cores HTML

Determine os valores **decimais** de R, G e B para cada cor hexadecimal:

| | Cor Hex | R (decimal) | G (decimal) | B (decimal) | Cor Aproximada |
|---|---------|-------------|-------------|-------------|----------------|
| a) | #FF0000 | | | | |
| b) | #00FF00 | | | | |
| c) | #808080 | | | | |
| d) | #FFA500 | | | | |
| e) | #4B0082 | | | | |

**Agora o inverso — converta RGB decimal para hexadecimal:**

| | R | G | B | Cor Hex |
|---|---|---|---|---------|
| f) | 255 | 255 | 0 | # |
| g) | 0 | 128 | 255 | # |
| h) | 100 | 200 | 50 | # |

---

### Exercício 8 — Permissões Linux

Determine a representação **binária** e o significado de cada permissão:

| | Octal | Binário | Dono | Grupo | Outros |
|---|-------|---------|------|-------|--------|
| a) | 755 | | | | |
| b) | 644 | | | | |
| c) | 700 | | | | |
| d) | 600 | | | | |
| e) | 777 | | | | |

**Agora o inverso — determine o valor octal:**

| | Dono | Grupo | Outros | Octal |
|---|------|-------|--------|-------|
| f) | rwx | r-- | r-- | |
| g) | rw- | rw- | --- | |
| h) | r-x | r-x | r-x | |

---

## 🔴 Nível Avançado

### Exercício 9 — Endereços IP

a) Converta o endereço IP **172.16.254.1** para:
   - Representação binária completa (32 bits)
   - Representação hexadecimal

b) O endereço hexadecimal **0xC0A8FE01** representa qual IP em notação decimal?

c) Quantos bits tem um endereço IPv4? E um IPv6? Quantos dígitos hexadecimais são necessários para representar cada um?

---

### Exercício 10 — Endereço MAC

Um endereço MAC é **AA:BB:CC:11:22:33**.

a) Converta cada componente para binário.

b) Quantos bits compõem um endereço MAC no total?

c) Quantos endereços MAC diferentes são possíveis? Expresse em potência de 2 e depois calcule o valor aproximado.

---

### Exercício 11 — Desafio: Todas as Bases

Complete a tabela convertendo cada número para todas as outras bases:

| Decimal | Binário | Octal | Hexadecimal |
|---------|---------|-------|-------------|
| 42 | | | |
| | 11001010 | | |
| | | 777 | |
| | | | DEAD |
| 1000 | | | |
| | 10000000000 | | |

---

### Exercício 12 — Reflexão

a) Por que o hexadecimal é **mais usado** que o octal na computação moderna? Relacione com o tamanho do byte (8 bits).

b) Um programador encontra o seguinte valor em um dump de memória: `0xCAFEBABE`. Este valor é famoso na computação — pesquise: o que ele representa?

c) Por que endereços de memória são mostrados em hexadecimal e não em decimal?

d) Se os computadores usassem uma base 3 (ternário) em vez de binário, quais seriam as vantagens e desvantagens? Pesquise sobre "computadores ternários" (ex: Setun, da União Soviética).

---

## 📌 Gabarito — Nível Básico

<details>
<summary>Clique para ver as respostas do Exercício 1</summary>

| | Octal | Decimal |
|---|-------|---------|
| a) | 17₈ | 15 (1×8 + 7×1) |
| b) | 52₈ | 42 (5×8 + 2×1) |
| c) | 100₈ | 64 (1×64) |
| d) | 377₈ | 255 (3×64 + 7×8 + 7×1) |
| e) | 644₈ | 420 (6×64 + 4×8 + 4×1) |

</details>

<details>
<summary>Clique para ver as respostas do Exercício 2</summary>

| | Hexadecimal | Decimal |
|---|-------------|---------|
| a) | A₁₆ | 10 |
| b) | 1F₁₆ | 31 (1×16 + 15×1) |
| c) | FF₁₆ | 255 (15×16 + 15×1) |
| d) | 100₁₆ | 256 (1×256) |
| e) | 2A3₁₆ | 675 (2×256 + 10×16 + 3×1) |

</details>

<details>
<summary>Clique para ver as respostas do Exercício 4 (Binário ↔ Hex)</summary>

| | Binário | Hexadecimal |
|---|---------|-------------|
| a) | 11010110₂ | D6 |
| b) | 10101011110011₂ | 2AF3 |
| c) | 11111111₂ | FF |
| d) | 9A₁₆ | 10011010₂ |
| e) | FACE₁₆ | 1111101011001110₂ |
| f) | 100₁₆ | 100000000₂ |

</details>

---

## 📌 Critérios de Avaliação

| Critério | Peso |
|---------|------|
| Método correto de conversão | 30% |
| Passos intermediários | 25% |
| Resposta final correta | 30% |
| Aplicações práticas | 15% |

---

<div align="center">

**📝 Pratique as conversões até se tornarem automáticas!**

*Voltar para a [Aula 03](../README.md)*

</div>
