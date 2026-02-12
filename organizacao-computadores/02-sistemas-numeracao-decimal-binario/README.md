# 🔢 Aula 02 — Sistemas de Numeração: Decimal e Binário

> **Disciplina:** Organização de Computadores  
> **Duração:** 2 horas-aula  
> **Nível:** Iniciante  
> **Pré-requisitos:** Aula 01 — Evolução Histórica dos Computadores

---

## 🎯 Objetivos de Aprendizado

Ao final desta aula, o estudante será capaz de:

- ✅ Explicar o que é um sistema de numeração posicional
- ✅ Descrever o sistema decimal (base 10) e seus fundamentos
- ✅ Descrever o sistema binário (base 2) e por que computadores o utilizam
- ✅ Converter números do sistema decimal para binário (método das divisões sucessivas)
- ✅ Converter números do sistema binário para decimal (método dos pesos posicionais)
- ✅ Aplicar conversões em exemplos práticos do cotidiano

---

## 📋 Sumário

1. [O que é um Sistema de Numeração?](#1--o-que-é-um-sistema-de-numeração)
2. [Sistema Decimal (Base 10)](#2--sistema-decimal-base-10)
3. [Sistema Binário (Base 2)](#3--sistema-binário-base-2)
4. [Por que Computadores Usam Binário?](#4--por-que-computadores-usam-binário)
5. [Conversão: Decimal → Binário](#5--conversão-decimal--binário)
6. [Conversão: Binário → Decimal](#6--conversão-binário--decimal)
7. [Números Fracionários](#7--números-fracionários)
8. [Analogias do Mundo Real](#8--analogias-do-mundo-real)
9. [Resumo](#9--resumo)
10. [Leitura Complementar](#10--leitura-complementar)

---

## 1. 🌐 O que é um Sistema de Numeração?

Um **sistema de numeração** é um conjunto de regras e símbolos usados para representar quantidades. Diferentes civilizações criaram diferentes sistemas ao longo da história.

### 1.1 Sistemas Não Posicionais vs. Posicionais

#### Sistema Não Posicional (exemplo: numeração romana)

No sistema romano, o valor de um símbolo é **sempre o mesmo**, independente de sua posição:

```
    III  =  1 + 1 + 1  =  3
    XXX  =  10 + 10 + 10  =  30

    O "I" sempre vale 1, esteja onde estiver.
```

**Problemas:** Representar números grandes é difícil e operações aritméticas são complicadas.

> 🤔 Tente multiplicar MCMXCIV × XLVII usando algarismos romanos... Praticamente impossível!

#### Sistema Posicional (exemplo: numeração decimal)

No sistema posicional, o valor de um dígito depende da sua **posição** no número:

```
    No número 555:

    5  5  5
    │  │  └── 5 × 10⁰ = 5 × 1   = 5     (unidades)
    │  └───── 5 × 10¹ = 5 × 10  = 50    (dezenas)
    └──────── 5 × 10² = 5 × 100 = 500   (centenas)
                                   ─────
                            Total: 555
```

> 💡 **Perceba:** O mesmo dígito "5" tem **três valores diferentes** dependendo da posição!

### 1.2 Elementos de um Sistema de Numeração Posicional

Todo sistema posicional é definido por:

| Elemento | Descrição | Exemplo (Decimal) |
|---------|-----------|-------------------|
| **Base (b)** | Quantidade de símbolos distintos | 10 |
| **Dígitos** | Símbolos utilizados (0 até b−1) | 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 |
| **Peso** | Potência da base conforme a posição | 10⁰, 10¹, 10², ... |

**Fórmula geral** para obter o valor de um número em qualquer base:

```
N = dₙ × bⁿ + dₙ₋₁ × bⁿ⁻¹ + ... + d₁ × b¹ + d₀ × b⁰

Onde:
  N  = valor do número
  dᵢ = dígito na posição i
  b  = base do sistema
  n  = posição mais significativa
```

---

## 2. 🔟 Sistema Decimal (Base 10)

O sistema **decimal** é o sistema que usamos no dia a dia. Sua origem está provavelmente ligada aos **10 dedos das mãos** — daí o nome "decimal" (do latim *decem* = dez).

### 2.1 Características

| Propriedade | Valor |
|------------|-------|
| Base | 10 |
| Dígitos | 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 |
| Pesos | ..., 10³, 10², 10¹, 10⁰ |
| Origem | ~3000 a.C. (Índia) |
| Difusão | Árabes levaram para a Europa (~séc. X) |

> 💡 Por isso chamamos de **algarismos indo-arábicos**!

### 2.2 Notação Posicional — Exemplo Detalhado

Vamos decompor o número **4.827** em suas componentes posicionais:

```
    Número: 4827₁₀

    Posição:    3      2      1      0
    Dígito:     4      8      2      7
    Peso:      10³    10²    10¹    10⁰
    Valor:    1000    100     10      1

    Cálculo:
    4 × 10³  =  4 × 1000  =  4000
    8 × 10²  =  8 × 100   =   800
    2 × 10¹  =  2 × 10    =    20
    7 × 10⁰  =  7 × 1     =     7
                             ──────
                 Total:      4.827
```

### 2.3 Tabela de Potências de 10

| Potência | Valor | Nome |
|----------|-------|------|
| 10⁰ | 1 | Unidade |
| 10¹ | 10 | Dezena |
| 10² | 100 | Centena |
| 10³ | 1.000 | Milhar |
| 10⁴ | 10.000 | Dezena de milhar |
| 10⁵ | 100.000 | Centena de milhar |
| 10⁶ | 1.000.000 | Milhão |

---

## 3. 💻 Sistema Binário (Base 2)

O sistema **binário** é o sistema fundamental da computação digital. Utiliza apenas **dois dígitos**: 0 e 1.

### 3.1 Características

| Propriedade | Valor |
|------------|-------|
| Base | 2 |
| Dígitos | 0 e 1 |
| Pesos | ..., 2⁴, 2³, 2², 2¹, 2⁰ |
| Cada dígito é chamado | **Bit** (Binary Digit) |
| Grupo de 8 bits | **Byte** |

### 3.2 Tabela de Potências de 2

Esta tabela é **essencial** — memorize-a!

| Potência | Valor | Binário |
|----------|-------|---------|
| 2⁰ | 1 | 1 |
| 2¹ | 2 | 10 |
| 2² | 4 | 100 |
| 2³ | 8 | 1000 |
| 2⁴ | 16 | 10000 |
| 2⁵ | 32 | 100000 |
| 2⁶ | 64 | 1000000 |
| 2⁷ | 128 | 10000000 |
| 2⁸ | 256 | 100000000 |
| 2⁹ | 512 | 1000000000 |
| 2¹⁰ | 1024 | 10000000000 |

> 💡 **Dica de memorização:** 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 — cada número é o **dobro** do anterior!

### 3.3 Contando em Binário

Assim como no decimal "contamos até acabar os dígitos e depois adicionamos uma nova posição", no binário é o mesmo — mas os dígitos acabam mais rápido (só temos 0 e 1):

```
    Decimal    Binário     Explicação
    ───────    ───────     ──────────────────────────────
       0         0         Zero em ambos os sistemas
       1         1         Um em ambos os sistemas
       2        10         Acabaram os dígitos! Vai 1 para a próxima posição
       3        11         
       4       100         Mais uma posição!
       5       101         
       6       110         
       7       111         
       8      1000         Mais uma posição!
       9      1001         
      10      1010         
      11      1011         
      12      1100         
      13      1101         
      14      1110         
      15      1111         
      16     10000         Mais uma posição!
```

> 💡 **Observe o padrão:** Com **n bits**, podemos representar números de **0 até 2ⁿ − 1**:
> - 1 bit: 0 a 1 (2 valores)
> - 2 bits: 0 a 3 (4 valores)
> - 3 bits: 0 a 7 (8 valores)
> - 4 bits: 0 a 15 (16 valores)
> - 8 bits: 0 a 255 (256 valores)

---

## 4. ⚡ Por que Computadores Usam Binário?

Esta é uma das perguntas mais importantes da disciplina. A resposta está na **eletrônica**:

### 4.1 Razões Físicas

```
    CIRCUITO ELETRÔNICO — DOIS ESTADOS CLAROS

    Estado 0 (DESLIGADO):          Estado 1 (LIGADO):
    ┌─────────────────┐            ┌─────────────────┐
    │                 │            │                 │
    │   ○ (apagado)   │            │   ● (aceso)     │
    │                 │            │                 │
    │   0V ~ 0.8V    │            │   2V ~ 5V       │
    │   (tensão baixa)│            │   (tensão alta) │
    └─────────────────┘            └─────────────────┘
```

| Estado Físico | Representação Binária | Exemplo |
|--------------|----------------------|---------|
| Sem tensão / tensão baixa | 0 | Lâmpada apagada |
| Com tensão / tensão alta | 1 | Lâmpada acesa |
| Interruptor aberto | 0 | Circuito desconectado |
| Interruptor fechado | 1 | Circuito conectado |
| Superfície lisa (CD) | 0 | Sem reflexão |
| Superfície com pit (CD) | 1 | Com reflexão |
| Sem magnetização (HD) | 0 | Norte |
| Com magnetização (HD) | 1 | Sul |

### 4.2 Vantagens do Binário

1. **Simplicidade:** Apenas dois estados → circuitos mais simples e baratos
2. **Confiabilidade:** Fácil distinguir entre "ligado" e "desligado" (mesmo com ruído)
3. **Matemática:** A álgebra booleana (base da lógica digital) opera com 2 valores
4. **Velocidade:** Transistores funcionam como interruptores binários extremamente rápidos

### 4.3 Analogia: Interruptores de Luz

```
    ANALOGIA — REPRESENTANDO NÚMEROS COM INTERRUPTORES

    Um interruptor = 1 bit:
    [OFF]  = 0
    [ON]   = 1

    Três interruptores = 3 bits (8 combinações):

    [OFF][OFF][OFF]  = 000₂ = 0₁₀
    [OFF][OFF][ON ]  = 001₂ = 1₁₀
    [OFF][ON ][OFF]  = 010₂ = 2₁₀
    [OFF][ON ][ON ]  = 011₂ = 3₁₀
    [ON ][OFF][OFF]  = 100₂ = 4₁₀
    [ON ][OFF][ON ]  = 101₂ = 5₁₀
    [ON ][ON ][OFF]  = 110₂ = 6₁₀
    [ON ][ON ][ON ]  = 111₂ = 7₁₀
```

> 💡 **É assim que o computador funciona!** Bilhões de transistores (interruptores microscópicos) ligando e desligando trilhões de vezes por segundo.

---

## 5. 🔄 Conversão: Decimal → Binário

### 5.1 Método das Divisões Sucessivas

**Algoritmo:**
1. Divida o número decimal por 2
2. Anote o **resto** (será 0 ou 1)
3. Use o **quociente** como novo dividendo
4. Repita até o quociente ser 0
5. Leia os restos de **baixo para cima** ← o resultado!

### 5.2 Exemplo Completo: Converter 25₁₀ para binário

```
    25 ÷ 2 = 12  resto  1  ↑
    12 ÷ 2 =  6  resto  0  │
     6 ÷ 2 =  3  resto  0  │  Leia de baixo
     3 ÷ 2 =  1  resto  1  │  para cima!
     1 ÷ 2 =  0  resto  1  │
                             
    Resultado: 25₁₀ = 11001₂
```

**Verificação (convertendo de volta):**
```
    1 1 0 0 1₂

    1 × 2⁴ = 1 × 16 = 16
    1 × 2³ = 1 × 8  =  8
    0 × 2² = 0 × 4  =  0
    0 × 2¹ = 0 × 2  =  0
    1 × 2⁰ = 1 × 1  =  1
                       ────
                Total: 25 ✓
```

### 5.3 Exemplo: Converter 100₁₀ para binário

```
    100 ÷ 2 = 50  resto  0  ↑
     50 ÷ 2 = 25  resto  0  │
     25 ÷ 2 = 12  resto  1  │
     12 ÷ 2 =  6  resto  0  │  Leia de baixo
      6 ÷ 2 =  3  resto  0  │  para cima!
      3 ÷ 2 =  1  resto  1  │
      1 ÷ 2 =  0  resto  1  │
                              
    Resultado: 100₁₀ = 1100100₂
```

### 5.4 Exemplo: Converter 255₁₀ para binário

```
    255 ÷ 2 = 127  resto  1  ↑
    127 ÷ 2 =  63  resto  1  │
     63 ÷ 2 =  31  resto  1  │
     31 ÷ 2 =  15  resto  1  │
     15 ÷ 2 =   7  resto  1  │  Leia de baixo
      7 ÷ 2 =   3  resto  1  │  para cima!
      3 ÷ 2 =   1  resto  1  │
      1 ÷ 2 =   0  resto  1  │
                               
    Resultado: 255₁₀ = 11111111₂ (8 bits — todos 1!)
```

> 💡 **255 = 11111111₂** → Este é o maior valor que cabe em **1 byte** (8 bits). Por isso, muitos valores em computação vão de 0 a 255 (ex: componentes RGB de cores)!

### 5.5 Método Alternativo: Subtração de Potências de 2

Outra forma de converter é subtrair as maiores potências de 2 que "cabem" no número:

**Exemplo: Converter 45₁₀ para binário**

```
    Potências de 2:  128  64  32  16  8  4  2  1

    45 ≥ 32?  SIM → 45 - 32 = 13  → bit = 1
    13 ≥ 16?  NÃO                  → bit = 0
    13 ≥  8?  SIM → 13 - 8  = 5   → bit = 1
     5 ≥  4?  SIM → 5  - 4  = 1   → bit = 1
     1 ≥  2?  NÃO                  → bit = 0
     1 ≥  1?  SIM → 1  - 1  = 0   → bit = 1

    Resultado: 45₁₀ = 101101₂
```

---

## 6. 🔄 Conversão: Binário → Decimal

### 6.1 Método dos Pesos Posicionais

**Algoritmo:**
1. Numere as posições da **direita para a esquerda**, começando em 0
2. Multiplique cada dígito binário pela potência de 2 correspondente
3. Some todos os resultados

### 6.2 Exemplo Completo: Converter 110101₂ para decimal

```
    Posição:    5      4      3      2      1      0
    Dígito:     1      1      0      1      0      1
    Peso:      2⁵     2⁴     2³     2²     2¹     2⁰
    Valor:     32     16      8      4      2      1

    Cálculo:
    1 × 2⁵  =  1 × 32  =  32
    1 × 2⁴  =  1 × 16  =  16
    0 × 2³  =  0 × 8   =   0
    1 × 2²  =  1 × 4   =   4
    0 × 2¹  =  0 × 2   =   0
    1 × 2⁰  =  1 × 1   =   1
                          ─────
               Total:     53

    Resultado: 110101₂ = 53₁₀
```

### 6.3 Exemplo: Converter 10110011₂ para decimal

```
    Posição:    7      6      5      4      3      2      1      0
    Dígito:     1      0      1      1      0      0      1      1
    Peso:     128     64     32     16      8      4      2      1

    Cálculo:
    1 × 128  =  128
    0 × 64   =    0
    1 × 32   =   32
    1 × 16   =   16
    0 × 8    =    0
    0 × 4    =    0
    1 × 2    =    2
    1 × 1    =    1
                ─────
    Total:     179

    Resultado: 10110011₂ = 179₁₀
```

### 6.4 Dica Prática: Some Apenas os "1"s

Como 0 multiplicado por qualquer coisa é 0, você só precisa somar as potências de 2 onde o bit é **1**:

```
    10110011₂ = 128 + 32 + 16 + 2 + 1 = 179₁₀
                 ↑      ↑    ↑    ↑   ↑
                bit 7  bit 5 bit 4 bit 1 bit 0
```

> 💡 **Atalho:** Ignore os zeros e some apenas as potências correspondentes aos bits "1".

---

## 7. 🔢 Números Fracionários

Até agora, trabalhamos com números **inteiros**. Mas e os números com parte fracionária?

### 7.1 No Sistema Decimal

Posições **à direita** da vírgula têm potências **negativas** da base:

```
    Número: 23,75₁₀

    Posição:    1      0     -1     -2
    Dígito:     2      3   ,  7      5
    Peso:      10¹    10⁰   10⁻¹   10⁻²
    Valor:     10      1    0,1    0,01

    Cálculo:
    2 × 10   =  20
    3 × 1    =   3
    7 × 0,1  =   0,7
    5 × 0,01 =   0,05
               ───────
    Total:     23,75
```

### 7.2 No Sistema Binário

Funciona da mesma forma — potências negativas de 2:

| Potência | Valor Decimal |
|----------|--------------|
| 2⁻¹ | 0,5 |
| 2⁻² | 0,25 |
| 2⁻³ | 0,125 |
| 2⁻⁴ | 0,0625 |

**Exemplo: Converter 101,11₂ para decimal**

```
    Posição:    2      1      0     -1     -2
    Dígito:     1      0      1   ,  1      1
    Peso:      2²     2¹     2⁰   2⁻¹    2⁻²
    Valor:      4      2      1    0,5    0,25

    Cálculo:
    1 × 4    =  4
    0 × 2    =  0
    1 × 1    =  1
    1 × 0,5  =  0,5
    1 × 0,25 =  0,25
               ──────
    Total:     5,75

    Resultado: 101,11₂ = 5,75₁₀
```

### 7.3 Conversão da Parte Fracionária: Decimal → Binário

**Algoritmo para a parte fracionária:**
1. Multiplique a parte fracionária por 2
2. Anote a **parte inteira** do resultado (será 0 ou 1)
3. Use a **parte fracionária** restante como novo multiplicando
4. Repita até a parte fracionária ser 0 (ou atingir a precisão desejada)
5. Leia os dígitos de **cima para baixo** ← o resultado!

**Exemplo: Converter 0,625₁₀ para binário**

```
    0,625 × 2 = 1,250  → parte inteira: 1  ↓
    0,250 × 2 = 0,500  → parte inteira: 0  │  Leia de cima
    0,500 × 2 = 1,000  → parte inteira: 1  │  para baixo!
    0,000 ← PAROU (parte fracionária = 0)   ↓

    Resultado: 0,625₁₀ = 0,101₂
```

**Verificação:**
```
    0,101₂ = 1 × 2⁻¹ + 0 × 2⁻² + 1 × 2⁻³
            = 0,5 + 0 + 0,125
            = 0,625 ✓
```

> ⚠️ **Atenção:** Nem todo número decimal fracionário tem representação **exata** em binário! Por exemplo, 0,1₁₀ em binário gera uma **dízima periódica**: 0,0001100110011... — é por isso que computadores às vezes têm **erros de arredondamento** em cálculos com ponto flutuante!

---

## 8. 🌍 Analogias do Mundo Real

### 8.1 Interruptores de Luz

```
    Imagine uma fileira de 8 interruptores de luz.
    Cada interruptor pode estar DESLIGADO (0) ou LIGADO (1).

    Interruptor:  [ON][OFF][ON][ON][OFF][OFF][ON][ON]
    Binário:        1    0   1   1    0    0   1   1
    Pesos:        128   64  32  16    8    4   2   1

    Número representado: 128 + 32 + 16 + 2 + 1 = 179₁₀

    → Com 8 interruptores, você pode representar 256 números (0 a 255).
    → Um computador faz isso com BILHÕES de "interruptores" (transistores)!
```

### 8.2 Código Morse

O Código Morse já usava uma ideia binária — sinais **curtos** e **longos**:

```
    Código Morse:    •  –
    Analogia binária: 0  1

    Assim como o Morse codifica letras com dois sinais,
    o binário codifica TUDO com dois dígitos!
```

### 8.3 DNA

O DNA usa 4 bases (A, T, C, G) — é um sistema "quaternário" (base 4). O binário usa 2 "bases" (0 e 1). Ambos codificam **informação complexa** a partir de unidades simples!

### 8.4 Perguntas de "Sim ou Não"

```
    O jogo "20 perguntas" é essencialmente uma busca binária!

    Com 1 pergunta (1 bit): distingue 2 opções
    Com 2 perguntas (2 bits): distingue 4 opções
    Com 10 perguntas (10 bits): distingue 1.024 opções
    Com 20 perguntas (20 bits): distingue 1.048.576 opções!
```

---

## 9. 📝 Resumo

### Tabela Comparativa Decimal × Binário

| Aspecto | Decimal (Base 10) | Binário (Base 2) |
|---------|-------------------|------------------|
| **Base** | 10 | 2 |
| **Dígitos** | 0-9 | 0-1 |
| **Uso** | Cotidiano humano | Computadores |
| **Pesos** | ..., 100, 10, 1 | ..., 8, 4, 2, 1 |
| **Exemplo** | 42₁₀ | 101010₂ |
| **Vantagem** | Intuitivo p/ humanos | Simples p/ circuitos |

### Métodos de Conversão

```
    DECIMAL → BINÁRIO                    BINÁRIO → DECIMAL
    ══════════════════                   ══════════════════
    Método: Divisões Sucessivas          Método: Pesos Posicionais

    Divida por 2 repetidamente           Multiplique cada bit pelo
    Leia os RESTOS de baixo              seu peso (2ⁿ) e SOME tudo
    para cima

    Exemplo: 25₁₀                       Exemplo: 11001₂
    25÷2=12 r:1 ↑                       1×16 + 1×8 + 0×4 + 0×2 + 1×1
    12÷2=6  r:0 │                       = 16 + 8 + 0 + 0 + 1
     6÷2=3  r:0 │                       = 25₁₀
     3÷2=1  r:1 │
     1÷2=0  r:1 │
    = 11001₂     │
```

### Fórmulas Importantes

| Conceito | Fórmula |
|---------|---------|
| Valor de um número na base b | N = Σ dᵢ × bⁱ |
| Quantidade de valores com n bits | 2ⁿ |
| Maior valor com n bits | 2ⁿ − 1 |

---

## 10. 📚 Leitura Complementar

### Livros:
- **Stallings, W.** *Computer Organization and Architecture*. Cap. 9 (Number Systems).
- **Monteiro, M. A.** *Introdução à Organização de Computadores*. Cap. 2.
- **Tocci, R. & Widmer, N.** *Sistemas Digitais*. Cap. 2.

### Prática com Python:
```python
# Converter decimal para binário
print(bin(25))        # Saída: 0b11001

# Converter binário para decimal
print(int('11001', 2))  # Saída: 25

# Ver a representação binária formatada
print(f'{25:08b}')    # Saída: 00011001 (com zeros à esquerda)
```

---

## 📂 Materiais Complementares

- 📁 **[Exemplos](exemplos/)** — Conversões passo a passo com múltiplos exemplos
- 📁 **[Exercícios](exercicios/)** — Exercícios de conversão de dificuldade crescente

---

<div align="center">

**🔢 "Existem apenas 10 tipos de pessoas no mundo: as que entendem binário e as que não entendem."**

*— Piada clássica de programadores* 😄

</div>
