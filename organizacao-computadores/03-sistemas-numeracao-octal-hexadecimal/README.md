# 🔢 Aula 03 — Sistemas de Numeração: Octal e Hexadecimal

> **Disciplina:** Organização de Computadores  
> **Duração:** 2 horas-aula  
> **Nível:** Iniciante a Intermediário  
> **Pré-requisitos:** Aula 02 — Sistemas de Numeração: Decimal e Binário

---

## 🎯 Objetivos de Aprendizado

Ao final desta aula, o estudante será capaz de:

- ✅ Descrever o sistema octal (base 8) e seus dígitos
- ✅ Descrever o sistema hexadecimal (base 16) e seus dígitos
- ✅ Converter entre octal, decimal e binário
- ✅ Converter entre hexadecimal, decimal e binário
- ✅ Explicar a relação entre binário-octal (grupos de 3) e binário-hex (grupos de 4)
- ✅ Identificar aplicações práticas do hexadecimal na computação

---

## 📋 Sumário

1. [Por que Precisamos de Outras Bases?](#1--por-que-precisamos-de-outras-bases)
2. [Sistema Octal (Base 8)](#2--sistema-octal-base-8)
3. [Conversões com Octal](#3--conversões-com-octal)
4. [Sistema Hexadecimal (Base 16)](#4--sistema-hexadecimal-base-16)
5. [Conversões com Hexadecimal](#5--conversões-com-hexadecimal)
6. [Relação Binário ↔ Octal ↔ Hexadecimal](#6--relação-binário--octal--hexadecimal)
7. [Aplicações Práticas do Hexadecimal](#7--aplicações-práticas-do-hexadecimal)
8. [Tabela de Referência Completa](#8--tabela-de-referência-completa)
9. [Resumo](#9--resumo)
10. [Leitura Complementar](#10--leitura-complementar)

---

## 1. 🤔 Por que Precisamos de Outras Bases?

Na aula anterior, vimos que computadores usam **binário**. Mas números binários são muito **longos** e difíceis de ler para humanos:

```
    Endereço de memória em binário:
    1100 0000 1010 1000 0000 0001 0000 0001

    Mesmo número em hexadecimal:
    C0A80101

    Muito mais fácil de ler e memorizar! ✓
```

Os sistemas **octal** e **hexadecimal** existem como **atalhos de leitura** para números binários:

| Base | Relação com Binário | Uso Principal |
|------|--------------------|----|
| Octal (8) | 1 dígito octal = 3 bits | Permissões Unix/Linux |
| Hexadecimal (16) | 1 dígito hex = 4 bits | Memória, cores, endereços |

> 💡 **Conceito-chave:** Octal e hexadecimal **não são usados pelo computador internamente**. São apenas formas compactas de **representar binário** para facilitar a leitura humana.

---

## 2. 🎱 Sistema Octal (Base 8)

### 2.1 Características

| Propriedade | Valor |
|------------|-------|
| Base | 8 |
| Dígitos | 0, 1, 2, 3, 4, 5, 6, 7 |
| Pesos | ..., 8³, 8², 8¹, 8⁰ |
| Relação com binário | **1 dígito octal = 3 bits** |

> 💡 **Por que 3 bits?** Porque 2³ = 8. Com 3 bits, podemos representar 8 valores (0 a 7) — exatamente os dígitos do octal!

### 2.2 Notação Posicional Octal

**Exemplo: 357₈ — quanto vale em decimal?**

```
    Número: 357₈

    Posição:    2      1      0
    Dígito:     3      5      7
    Peso:      8²     8¹     8⁰
    Valor:     64      8      1

    Cálculo:
    3 × 64  =  192
    5 × 8   =   40
    7 × 1   =    7
               ─────
    Total:     239

    Resultado: 357₈ = 239₁₀
```

### 2.3 Tabela de Potências de 8

| Potência | Valor |
|----------|-------|
| 8⁰ | 1 |
| 8¹ | 8 |
| 8² | 64 |
| 8³ | 512 |
| 8⁴ | 4.096 |
| 8⁵ | 32.768 |

---

## 3. 🔄 Conversões com Octal

### 3.1 Octal → Decimal (Pesos Posicionais)

Multiplique cada dígito pelo peso da posição e some:

**Exemplo: Converter 745₈ para decimal**

```
    7 × 8² = 7 × 64 = 448
    4 × 8¹ = 4 × 8  =  32
    5 × 8⁰ = 5 × 1  =   5
                       ─────
    Total:             485

    Resultado: 745₈ = 485₁₀
```

### 3.2 Decimal → Octal (Divisões Sucessivas por 8)

Mesmo método das divisões por 2, mas agora dividimos por **8**:

**Exemplo: Converter 350₁₀ para octal**

```
    350 ÷ 8 = 43  resto  6  ↑
     43 ÷ 8 =  5  resto  3  │  Leia de baixo para cima
      5 ÷ 8 =  0  resto  5  │

    Resultado: 350₁₀ = 536₈
```

**Verificação:** 5×64 + 3×8 + 6×1 = 320 + 24 + 6 = **350** ✓

### 3.3 Binário → Octal (Agrupamento de 3 bits) ⭐

Este é o método **mais rápido e mais importante**:

**Algoritmo:**
1. Agrupe os bits de **3 em 3** da direita para a esquerda
2. Complete com zeros à esquerda se necessário
3. Converta cada grupo de 3 bits para o dígito octal correspondente

**Tabela de conversão (memorize!):**

| Binário (3 bits) | Octal |
|-------------------|-------|
| 000 | 0 |
| 001 | 1 |
| 010 | 2 |
| 011 | 3 |
| 100 | 4 |
| 101 | 5 |
| 110 | 6 |
| 111 | 7 |

**Exemplo: Converter 110101011₂ para octal**

```
    Passo 1: Agrupar de 3 em 3 (da direita para esquerda)

    110  101  011
     │    │    │
     ▼    ▼    ▼
     6    5    3

    Resultado: 110101011₂ = 653₈
```

**Exemplo: Converter 10110₂ para octal**

```
    Passo 1: Agrupar de 3 em 3 (completando com zero)

    010  110     ← adicionamos um zero à esquerda
     │    │
     ▼    ▼
     2    6

    Resultado: 10110₂ = 26₈
```

### 3.4 Octal → Binário (Expansão para 3 bits)

Processo inverso — substitua cada dígito octal por seus 3 bits:

**Exemplo: Converter 472₈ para binário**

```
     4    7    2
     │    │    │
     ▼    ▼    ▼
    100  111  010

    Resultado: 472₈ = 100111010₂
```

---

## 4. 🔷 Sistema Hexadecimal (Base 16)

### 4.1 Características

| Propriedade | Valor |
|------------|-------|
| Base | 16 |
| Dígitos | 0-9 e A, B, C, D, E, F |
| Pesos | ..., 16³, 16², 16¹, 16⁰ |
| Relação com binário | **1 dígito hex = 4 bits** |
| Notação comum | Prefixo `0x` (ex: 0xFF) |

> 💡 **Por que 4 bits?** Porque 2⁴ = 16. Com 4 bits, podemos representar 16 valores (0 a 15) — exatamente os dígitos do hexadecimal!

### 4.2 Os Dígitos Hexadecimais

Como a base 16 precisa de 16 símbolos diferentes e só temos 10 algarismos (0-9), usamos **letras** para os valores de 10 a 15:

| Decimal | Hexadecimal | Binário (4 bits) |
|---------|-------------|------------------|
| 0 | 0 | 0000 |
| 1 | 1 | 0001 |
| 2 | 2 | 0010 |
| 3 | 3 | 0011 |
| 4 | 4 | 0100 |
| 5 | 5 | 0101 |
| 6 | 6 | 0110 |
| 7 | 7 | 0111 |
| 8 | 8 | 1000 |
| 9 | 9 | 1001 |
| 10 | **A** | 1010 |
| 11 | **B** | 1011 |
| 12 | **C** | 1100 |
| 13 | **D** | 1101 |
| 14 | **E** | 1110 |
| 15 | **F** | 1111 |

> 💡 **Dica de memorização:** A=10, B=11, C=12, D=13, E=14, F=15. Basta pensar na ordem do **alfabeto**!

### 4.3 Notação Posicional Hexadecimal

**Exemplo: 2AF₁₆ — quanto vale em decimal?**

```
    Número: 2AF₁₆

    Posição:    2      1      0
    Dígito:     2      A      F
    Valor:      2     10     15
    Peso:     16²    16¹    16⁰
    Valor:    256     16      1

    Cálculo:
     2 × 256  =  512
    10 × 16   =  160
    15 × 1    =   15
                ─────
    Total:      687

    Resultado: 2AF₁₆ = 687₁₀
```

### 4.4 Tabela de Potências de 16

| Potência | Valor | Uso Comum |
|----------|-------|-----------|
| 16⁰ | 1 | |
| 16¹ | 16 | |
| 16² | 256 | 1 byte |
| 16³ | 4.096 | |
| 16⁴ | 65.536 | 2 bytes |
| 16⁵ | 1.048.576 | |
| 16⁶ | 16.777.216 | 3 bytes (cores RGB) |
| 16⁷ | 268.435.456 | |
| 16⁸ | 4.294.967.296 | 4 bytes (endereço IPv4) |

---

## 5. 🔄 Conversões com Hexadecimal

### 5.1 Hexadecimal → Decimal (Pesos Posicionais)

**Exemplo: Converter 1F4₁₆ para decimal**

```
     1  ×  16²  =   1 × 256  =  256
    15  ×  16¹  =  15 × 16   =  240
     4  ×  16⁰  =   4 × 1    =    4
                               ─────
                    Total:      500

    Resultado: 1F4₁₆ = 500₁₀
```

### 5.2 Decimal → Hexadecimal (Divisões Sucessivas por 16)

**Exemplo: Converter 750₁₀ para hexadecimal**

```
    750 ÷ 16 = 46  resto  14 (= E)  ↑
     46 ÷ 16 =  2  resto  14 (= E)  │  Leia de baixo para cima
      2 ÷ 16 =  0  resto   2        │

    Resultado: 750₁₀ = 2EE₁₆
```

**Verificação:** 2×256 + 14×16 + 14×1 = 512 + 224 + 14 = **750** ✓

### 5.3 Binário → Hexadecimal (Agrupamento de 4 bits) ⭐

**Algoritmo:**
1. Agrupe os bits de **4 em 4** da direita para a esquerda
2. Complete com zeros à esquerda se necessário
3. Converta cada grupo de 4 bits para o dígito hexadecimal

**Exemplo: Converter 11000000101010000000000100000001₂ para hexadecimal**

```
    Passo 1: Agrupar de 4 em 4

    1100  0000  1010  1000  0000  0001  0000  0001
      │     │     │     │     │     │     │     │
      ▼     ▼     ▼     ▼     ▼     ▼     ▼     ▼
      C     0     A     8     0     1     0     1

    Resultado: C0A80101₁₆
```

> 💡 **Perceba:** Este é o endereço IP **192.168.1.1** em hexadecimal! (C0=192, A8=168, 01=1, 01=1)

**Exemplo: Converter 1010111011₂ para hexadecimal**

```
    Passo 1: Agrupar de 4 em 4 (completando com zeros)

    0010  1011  1011     ← adicionamos dois zeros à esquerda
      │     │     │
      ▼     ▼     ▼
      2     B     B

    Resultado: 1010111011₂ = 2BB₁₆
```

### 5.4 Hexadecimal → Binário (Expansão para 4 bits)

Substitua cada dígito hex por seus 4 bits:

**Exemplo: Converter FF00₁₆ para binário**

```
     F     F     0     0
     │     │     │     │
     ▼     ▼     ▼     ▼
    1111  1111  0000  0000

    Resultado: FF00₁₆ = 1111111100000000₂
```

---

## 6. 🔗 Relação Binário ↔ Octal ↔ Hexadecimal

### 6.1 O "Truque" dos Agrupamentos

A conversão direta entre binário e octal/hexadecimal funciona porque:

```
    BINÁRIO ←→ OCTAL              BINÁRIO ←→ HEXADECIMAL
    
    2³ = 8  (base octal)          2⁴ = 16 (base hexadecimal)
    
    Portanto:                      Portanto:
    1 dígito octal = 3 bits       1 dígito hex = 4 bits
```

### 6.2 Conversão Octal ↔ Hexadecimal (via Binário)

Para converter entre octal e hexadecimal, use o **binário como intermediário**:

**Exemplo: Converter 753₈ para hexadecimal**

```
    PASSO 1: Octal → Binário (expandir cada dígito para 3 bits)

     7     5     3
     │     │     │
     ▼     ▼     ▼
    111   101   011

    Binário: 111101011

    PASSO 2: Binário → Hexadecimal (reagrupar em 4 bits)

    0001  1110  1011     ← completar com zeros à esquerda
      │     │     │
      ▼     ▼     ▼
      1     E     B

    Resultado: 753₈ = 1EB₁₆
```

### 6.3 Diagrama de Conversões

```
                    DECIMAL
                   ╱       ╲
           ÷8,×8 ╱         ╲ ÷16,×16
                ╱             ╲
             OCTAL           HEXADECIMAL
               ╲              ╱
         3 bits ╲            ╱ 4 bits
                 ╲          ╱
                  BINÁRIO
            (base fundamental)
```

> 💡 **Regra de ouro:** Para converter entre **qualquer** combinação (octal ↔ hex, octal ↔ decimal, hex ↔ decimal), pode-se usar o **binário como ponte**. Mas para octal/hex ↔ decimal, divisões/multiplicações diretas são mais rápidas.

---

## 7. 🌍 Aplicações Práticas do Hexadecimal

### 7.1 Cores em HTML/CSS 🎨

Cores na web são representadas como **#RRGGBB** (Red, Green, Blue), onde cada componente vai de 00 a FF (0 a 255):

```
    COR               HEX        R      G      B
    ───────────────────────────────────────────────
    Vermelho puro     #FF0000    255     0      0
    Verde puro        #00FF00      0   255      0
    Azul puro         #0000FF      0     0    255
    Branco            #FFFFFF    255   255    255
    Preto             #000000      0     0      0
    Amarelo           #FFFF00    255   255      0
    Ciano             #00FFFF      0   255    255
    Magenta           #FF00FF    255     0    255
    Cinza médio       #808080    128   128    128
    Laranja           #FFA500    255   165      0
```

> 💡 **Exemplo prático:** A cor `#1E90FF` (Dodger Blue) tem:
> - R = 1E₁₆ = 30₁₀
> - G = 90₁₆ = 144₁₀
> - B = FF₁₆ = 255₁₀

### 7.2 Endereços de Memória 💾

Programadores frequentemente veem endereços de memória em hexadecimal:

```
    Endereço de memória típico (64 bits):
    0x00007FFDE83A4B20

    Em binário seria:
    0000 0000 0000 0000 0111 1111 1111 1101
    1110 1000 0011 1010 0100 1011 0010 0000

    Muito mais fácil ler em hex! ✓
```

### 7.3 Endereços MAC (Rede) 🌐

Cada placa de rede tem um endereço MAC único, expresso em hexadecimal:

```
    Formato:  XX:XX:XX:XX:XX:XX
    Exemplo:  A4:83:E7:2F:C1:0B

    Cada par hexadecimal = 1 byte = 8 bits
    Total: 6 bytes = 48 bits = 281 trilhões de combinações
```

### 7.4 Códigos de Erro e Debug 🔧

Mensagens de erro frequentemente mostram códigos em hexadecimal:

```
    Tela azul do Windows (BSOD):
    STOP: 0x0000007E

    Erros HTTP (internamente):
    0x194 = 404 (Not Found)
    0x1F4 = 500 (Internal Server Error)
```

### 7.5 Permissões Unix/Linux (Octal) 🐧

No Linux, permissões de arquivo são expressas em **octal**:

```
    chmod 755 arquivo.sh

    7 = 111₂ = rwx (leitura + escrita + execução)  → dono
    5 = 101₂ = r-x (leitura + execução)            → grupo
    5 = 101₂ = r-x (leitura + execução)            → outros

    Cada dígito octal codifica 3 permissões (3 bits)!
```

| Octal | Binário | Permissão |
|-------|---------|-----------|
| 0 | 000 | --- (nenhuma) |
| 1 | 001 | --x (execução) |
| 2 | 010 | -w- (escrita) |
| 3 | 011 | -wx (escrita + execução) |
| 4 | 100 | r-- (leitura) |
| 5 | 101 | r-x (leitura + execução) |
| 6 | 110 | rw- (leitura + escrita) |
| 7 | 111 | rwx (todas) |

### 7.6 Unicode e UTF-8

Caracteres Unicode são identificados por código hexadecimal:

```
    Caracter    Unicode     Descrição
    ────────────────────────────────────
    A           U+0041      Letra A maiúscula
    ñ           U+00F1      Letra n com til
    €           U+20AC      Símbolo do Euro
    😀          U+1F600     Emoji sorridente
    漢          U+6F22      Caractere chinês "Han"
```

### 7.7 IPv6

Endereços IPv6 são escritos em hexadecimal:

```
    IPv4: 192.168.1.1           (4 bytes, decimal)
    IPv6: 2001:0db8:85a3:0000:0000:8a2e:0370:7334  (16 bytes, hex)

    IPv6 permite 2¹²⁸ ≈ 3,4 × 10³⁸ endereços
    (o suficiente para cada grão de areia na Terra ter bilhões de endereços!)
```

---

## 8. 📋 Tabela de Referência Completa

### Números de 0 a 31 nas Quatro Bases

| Decimal | Binário | Octal | Hexadecimal |
|---------|---------|-------|-------------|
| 0 | 0000 0 | 0 | 0 |
| 1 | 0000 1 | 1 | 1 |
| 2 | 0001 0 | 2 | 2 |
| 3 | 0001 1 | 3 | 3 |
| 4 | 0010 0 | 4 | 4 |
| 5 | 0010 1 | 5 | 5 |
| 6 | 0011 0 | 6 | 6 |
| 7 | 0011 1 | 7 | 7 |
| 8 | 0100 0 | 10 | 8 |
| 9 | 0100 1 | 11 | 9 |
| 10 | 0101 0 | 12 | A |
| 11 | 0101 1 | 13 | B |
| 12 | 0110 0 | 14 | C |
| 13 | 0110 1 | 15 | D |
| 14 | 0111 0 | 16 | E |
| 15 | 0111 1 | 17 | F |
| 16 | 1000 0 | 20 | 10 |
| 20 | 10100 | 24 | 14 |
| 25 | 11001 | 31 | 19 |
| 31 | 11111 | 37 | 1F |
| 32 | 100000 | 40 | 20 |

---

## 9. 📝 Resumo

### Quadro Comparativo das Quatro Bases

| Aspecto | Decimal | Binário | Octal | Hexadecimal |
|---------|---------|---------|-------|-------------|
| **Base** | 10 | 2 | 8 | 16 |
| **Dígitos** | 0-9 | 0-1 | 0-7 | 0-9, A-F |
| **Bits por dígito** | ~3,32 | 1 | 3 | 4 |
| **Uso principal** | Cotidiano | Hardware | Permissões Linux | Memória, cores |
| **Prefixo** | (nenhum) | 0b | 0o | 0x |

### Métodos de Conversão Rápida

```
    BINÁRIO → OCTAL:  Agrupe de 3 em 3 bits (da direita para esquerda)
    OCTAL → BINÁRIO:  Expanda cada dígito octal em 3 bits

    BINÁRIO → HEX:    Agrupe de 4 em 4 bits (da direita para esquerda)
    HEX → BINÁRIO:    Expanda cada dígito hex em 4 bits

    QUALQUER → DECIMAL:  Pesos posicionais (dígito × baseⁿ)
    DECIMAL → QUALQUER:  Divisões sucessivas pela base
```

### Verificação em Python

```python
# Conversões em Python
numero = 255

print(f"Decimal:      {numero}")          # 255
print(f"Binário:      {bin(numero)}")      # 0b11111111
print(f"Octal:        {oct(numero)}")      # 0o377
print(f"Hexadecimal:  {hex(numero)}")      # 0xff

# Conversão de string para inteiro
print(int('377', 8))    # 255 (octal → decimal)
print(int('FF', 16))    # 255 (hex → decimal)
print(int('11111111', 2))  # 255 (binário → decimal)
```

---

## 10. 📚 Leitura Complementar

### Livros:
- **Stallings, W.** *Computer Organization and Architecture*. Cap. 9.
- **Tocci, R. & Widmer, N.** *Sistemas Digitais*. Cap. 2.
- **Monteiro, M. A.** *Introdução à Organização de Computadores*. Cap. 2.

### Experimente:
- Inspecione o código-fonte de uma página web e observe as cores em hexadecimal
- No terminal Linux, digite `ls -la` e observe as permissões (converta para octal)
- Use `python3 -c "print(hex(192), hex(168), hex(1), hex(1))"` para ver um IP em hex

---

## 📂 Materiais Complementares

- 📁 **[Exemplos](exemplos/)** — Conversões detalhadas passo a passo
- 📁 **[Exercícios](exercicios/)** — Exercícios práticos de conversão

---

<div align="center">

**🔷 "O hexadecimal é o melhor amigo do programador — transforma longas sequências de bits em algo legível!"**

</div>
