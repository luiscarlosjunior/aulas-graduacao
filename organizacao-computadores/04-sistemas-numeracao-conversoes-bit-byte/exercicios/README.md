# 📝 Exercícios — Aula 04: Conversões, Bit e Byte

> Exercícios práticos com aplicações do dia a dia

**Instruções:** Mostre todos os cálculos. Para exercícios de conversão, apresente os passos do método utilizado.

---

## 🟢 Nível Básico

### Exercício 1 — Conversão entre Bases Diversas

Converta para **decimal** usando o método dos pesos posicionais:

| | Número | Base | Decimal |
|---|--------|------|---------|
| a) | 1021₃ | 3 | |
| b) | 432₅ | 5 | |
| c) | 156₇ | 7 | |
| d) | 1A₁₂ | 12 | |
| e) | 100₂ | 2 | |
| f) | 100₈ | 8 | |
| g) | 100₁₆ | 16 | |

> 💡 **Observe os itens e, f e g:** O mesmo número "100" tem valores completamente diferentes dependendo da base!

---

### Exercício 2 — Decimal para Outras Bases

Converta o número **200₁₀** para cada base usando divisões sucessivas:

| | Base de Destino | Resultado |
|---|----------------|-----------|
| a) | Base 2 (binário) | |
| b) | Base 3 | |
| c) | Base 5 | |
| d) | Base 8 (octal) | |
| e) | Base 16 (hexadecimal) | |

---

### Exercício 3 — Bits e Combinações

Preencha a tabela:

| Bits | Combinações (2ⁿ) | Faixa (sem sinal) | Exemplo de Uso |
|------|------------------|--------------------|----------------|
| 1 | | 0 a ___ | |
| 4 | | 0 a ___ | |
| 8 | | 0 a ___ | |
| 16 | | 0 a ___ | |
| 32 | | 0 a ___ | |

---

### Exercício 4 — Verdadeiro ou Falso

a) ( ) 1 byte = 8 bits.

b) ( ) 1 KB sempre equivale a 1.000 bytes.

c) ( ) O caractere 'A' em ASCII tem o código 65, e o caractere 'a' tem o código 97.

d) ( ) ASCII pode representar caracteres acentuados como 'ç' e 'ã'.

e) ( ) UTF-8 é compatível com ASCII para caracteres de 0 a 127.

f) ( ) Um nibble = 4 bits = 1 dígito hexadecimal.

g) ( ) Um HD anunciado como 500 GB mostra 500 GB no Windows.

h) ( ) A velocidade de internet é medida em bytes por segundo.

---

## 🟡 Nível Intermediário

### Exercício 5 — Conversão de Unidades de Armazenamento

Resolva as conversões:

| | Conversão | Resultado |
|---|-----------|-----------|
| a) | 3 GiB → bytes | |
| b) | 2.048 KiB → MiB | |
| c) | 5.000.000 bytes → MiB (IEC) | |
| d) | 5.000.000 bytes → MB (SI) | |
| e) | 1 TiB → GiB | |
| f) | 750 GB (SI) → GiB (IEC) | |

---

### Exercício 6 — Codificação ASCII

a) Codifique a palavra **"CPU"** em ASCII (decimal, hexadecimal e binário).

b) Decodifique a seguinte sequência hexadecimal em texto ASCII:
   `42 49 54 53`

c) Decodifique esta sequência binária em texto ASCII:
   `01000010 01011001 01010100 01000101`

d) Qual é a diferença numérica (em decimal) entre qualquer letra maiúscula e sua correspondente minúscula em ASCII? Por que isso é útil para programadores?

---

### Exercício 7 — Cálculos de Armazenamento

a) Uma foto de 16 megapixels (4608 × 3456) sem compressão (3 bytes por pixel). Quantos **megabytes** ela ocupa?

b) Um arquivo de áudio tem 44.100 amostras por segundo (qualidade CD), com 16 bits por amostra e 2 canais (estéreo). Quantos **megabytes** ocupa 1 minuto de áudio sem compressão?

c) Quantas fotos de 5 MB cabem em um pen drive de 32 GB (SI)?

d) Um banco de dados tem 10 milhões de registros, cada um com 500 bytes. Quanto espaço total em **gigabytes** (SI)?

---

### Exercício 8 — Velocidade de Internet

a) Sua internet é de **300 Mbps**. Qual é a velocidade máxima de download em **MB/s**?

b) Quanto tempo leva para baixar um arquivo de **2 GB** com uma conexão de **100 Mbps**? (velocidade teórica)

c) Uma empresa precisa fazer backup de **5 TB** de dados pela internet. Se a velocidade de upload é **50 Mbps**, quanto tempo levará? Expresse em horas.

d) Por que provedores de internet anunciam velocidades em **Mbps** em vez de **MB/s**? (Pense no marketing)

---

## 🔴 Nível Avançado

### Exercício 9 — UTF-8 e Tamanho de Strings

a) Calcule o tamanho em bytes da string **"Organização"** em UTF-8:
   - Identifique quais caracteres usam 1 byte e quais usam 2 bytes
   - Some o total

b) Compare o tamanho em bytes de **"Hello World"** e **"Olá Mundo!"** em UTF-8. Explique a diferença.

c) Um sistema armazena nomes de pessoas com no máximo 50 **caracteres**. Se os nomes estão em UTF-8 e podem conter acentos, qual é o tamanho máximo em **bytes** que o campo deve ter? Justifique.

---

### Exercício 10 — O Problema das Unidades

Um cliente comprou um SSD de **1 TB** (anunciado pelo fabricante).

a) Quantos bytes o SSD realmente tem?

b) Quantos GiB o sistema operacional mostra?

c) O cliente reclama que o SSD tem "apenas 931 GB". Escreva uma explicação clara (como se fosse para um cliente leigo) sobre por que isso acontece.

d) Calcule a diferença percentual entre TB (SI) e TiB (IEC).

---

### Exercício 11 — Desafio: Projeto de Armazenamento

Você foi contratado para projetar o armazenamento de um sistema de câmeras de segurança:

- **16 câmeras** gravando simultaneamente
- Resolução: **1920 × 1080** (Full HD)
- 30 frames por segundo
- 3 bytes por pixel (RGB)
- Compressão H.264: fator de compressão de **50x**
- Gravação **24 horas por dia, 7 dias por semana**
- Dados devem ser mantidos por **30 dias**

Calcule:

a) Tamanho de 1 frame sem compressão (em bytes e MB).

b) Tamanho de 1 segundo de vídeo sem compressão (em MB).

c) Tamanho de 1 segundo de vídeo **com** compressão H.264 (em KB).

d) Tamanho de 1 hora de vídeo de **1 câmera** com compressão (em GB).

e) Armazenamento total necessário para **16 câmeras, 30 dias** (em TB).

f) Quantos HDs de 8 TB você precisa comprar? (considere 10% de margem)

---

### Exercício 12 — Reflexão e Pesquisa

a) Explique com suas próprias palavras a diferença entre **bit** e **byte**, e dê um exemplo prático de quando cada unidade é usada.

b) Por que o padrão IEC (KiB, MiB, GiB) não foi amplamente adotado, mesmo sendo mais preciso? Pesquise e dê sua opinião.

c) O código ASCII do caractere **'0'** (zero) é 48, mas o valor numérico de zero é 0. Explique essa diferença e como um programador converte entre o caractere '0' e o número 0.

d) Se um novo padrão de caracteres precisasse representar os caracteres de **todos os 7.000+ idiomas humanos**, incluindo emojis, símbolos matemáticos e caracteres históricos (como hieróglifos egípcios), quantos bits por caractere seriam necessários? O Unicode atual é suficiente? Pesquise sobre o Unicode 15.0.

---

## 📌 Gabarito — Nível Básico

<details>
<summary>Clique para ver as respostas do Exercício 1</summary>

| | Número | Base | Decimal |
|---|--------|------|---------|
| a) | 1021₃ | 3 | 1×27 + 0×9 + 2×3 + 1×1 = **34** |
| b) | 432₅ | 5 | 4×25 + 3×5 + 2×1 = **117** |
| c) | 156₇ | 7 | 1×49 + 5×7 + 6×1 = **90** |
| d) | 1A₁₂ | 12 | 1×12 + 10×1 = **22** |
| e) | 100₂ | 2 | **4** |
| f) | 100₈ | 8 | **64** |
| g) | 100₁₆ | 16 | **256** |

O número "100" vale 4, 64 ou 256 dependendo da base!

</details>

<details>
<summary>Clique para ver as respostas do Exercício 3</summary>

| Bits | Combinações (2ⁿ) | Faixa (sem sinal) |
|------|------------------|--------------------|
| 1 | 2 | 0 a 1 |
| 4 | 16 | 0 a 15 |
| 8 | 256 | 0 a 255 |
| 16 | 65.536 | 0 a 65.535 |
| 32 | 4.294.967.296 | 0 a 4.294.967.295 |

</details>

<details>
<summary>Clique para ver as respostas do Exercício 4</summary>

a) **VERDADEIRO**

b) **FALSO** — 1 KB = 1.000 bytes (SI) ou 1 KiB = 1.024 bytes (IEC). Depende do padrão.

c) **VERDADEIRO**

d) **FALSO** — ASCII padrão (7 bits) não suporta acentos.

e) **VERDADEIRO**

f) **VERDADEIRO**

g) **FALSO** — Mostra cerca de 465 GiB (~465 GB), pois fabricantes usam base 10 e Windows usa base 2.

h) **FALSO** — A velocidade de internet é medida em **bits** por segundo (bps/Mbps/Gbps).

</details>

---

## 📌 Critérios de Avaliação

| Critério | Peso |
|---------|------|
| Cálculos corretos | 35% |
| Unidades corretas (não confundir bit/byte, SI/IEC) | 25% |
| Passos intermediários | 20% |
| Clareza na argumentação (questões discursivas) | 20% |

---

<div align="center">

**📝 Dominar bits e bytes é essencial para todo profissional de TI!**

*Voltar para a [Aula 04](../README.md)*

</div>
