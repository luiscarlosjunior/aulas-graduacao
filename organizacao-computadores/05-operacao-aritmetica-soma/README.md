# ➕ Aula 05 — Operação Aritmética: Soma Binária

> **Disciplina:** Organização de Computadores  
> **Duração:** 2 horas-aula  
> **Nível:** Iniciante a Intermediário  
> **Pré-requisitos:** Aulas 02, 03 e 04 — Sistemas de Numeração e Conversões

---

## 🎯 Objetivos de Aprendizado

Ao final desta aula, o estudante será capaz de:

- ✅ Aplicar as quatro regras fundamentais da soma binária
- ✅ Realizar adição de números binários de múltiplos bits com **vai-um** (carry)
- ✅ Compreender e detectar **overflow** (transbordamento) em somas
- ✅ Representar números com sinal usando **sinal-magnitude**, **complemento a 1** e **complemento a 2**
- ✅ Realizar somas com números em **complemento a 2**
- ✅ Descrever os circuitos **meio-somador** e **somador completo** (truth tables e expressões)
- ✅ Explicar como a **ULA** do processador realiza a adição

---

## 📋 Sumário

1. [Revisão: Sistema Binário](#1--revisão-sistema-binário)
2. [Regras Fundamentais da Soma Binária](#2--regras-fundamentais-da-soma-binária)
3. [Soma com Vai-Um (Carry)](#3--soma-com-vai-um-carry)
4. [Adição de Números com Múltiplos Bits](#4--adição-de-números-com-múltiplos-bits)
5. [Overflow — Transbordamento](#5--overflow--transbordamento)
6. [Representação de Números com Sinal](#6--representação-de-números-com-sinal)
7. [Soma em Complemento a 2](#7--soma-em-complemento-a-2)
8. [Circuitos Somadores](#8--circuitos-somadores)
9. [Aplicações no Mundo Real](#9--aplicações-no-mundo-real)
10. [Resumo](#10--resumo)
11. [Leitura Complementar](#11--leitura-complementar)

---

## 1. 🔄 Revisão: Sistema Binário

Antes de somar, vamos relembrar os fundamentos:

| Conceito | Descrição |
|----------|-----------|
| Base | 2 (binária) |
| Dígitos | 0 e 1 |
| Cada posição | Representa uma potência de 2 |
| Bit | **B**inary Dig**it** — menor unidade de informação |

```
    Posição:    7    6    5    4    3    2    1    0
    Peso:      128   64   32   16    8    4    2    1
    Exemplo:    1    0    1    0    0    1    1    0  = 166₁₀
```

> 💡 **Analogia do dia a dia:** Pense em caixas de tamanhos diferentes. A caixa da posição 0 cabe **1 item**, a da posição 1 cabe **2 itens**, a da posição 2 cabe **4 itens**, e assim por diante. Somar em binário é como reorganizar itens nessas caixas — quando uma caixa "enche" (passa de 1), o excesso vai para a próxima caixa maior.

---

## 2. ➕ Regras Fundamentais da Soma Binária

A adição binária tem apenas **4 regras**. Compare com a adição decimal onde temos 100 combinações possíveis (0+0 até 9+9). Aqui, a simplicidade é a grande vantagem!

| Operação | Resultado | Carry (Vai-um) | Analogia Decimal |
|----------|-----------|-----------------|------------------|
| `0 + 0` | **0** | 0 | 0 + 0 = 0 |
| `0 + 1` | **1** | 0 | 0 + 1 = 1 |
| `1 + 0` | **1** | 0 | 1 + 0 = 1 |
| `1 + 1` | **10** (zero, vai um) | 1 | Como 5 + 5 = 10 em decimal |

### 🔑 A Regra Crucial: `1 + 1 = 10₂`

No sistema decimal, quando somamos 5 + 5, obtemos 10: escrevemos **0** e "vai **1**" para a próxima coluna. O mesmo acontece em binário com `1 + 1`:

```
      Em decimal: 5 + 5 = 10  →  escreve 0, vai 1
      Em binário: 1 + 1 = 10  →  escreve 0, vai 1
```

E quando temos `1 + 1 + 1`? (ou seja, dois bits mais um carry):

```
      1 + 1 + 1 = 11₂  (três em decimal)
      → escreve 1, vai 1
```

| Operação Completa | Resultado | Carry |
|--------------------|-----------|-------|
| `0 + 0 + 0` | **0** | 0 |
| `0 + 0 + 1` | **1** | 0 |
| `0 + 1 + 0` | **1** | 0 |
| `0 + 1 + 1` | **10** | 1 |
| `1 + 0 + 0` | **1** | 0 |
| `1 + 0 + 1` | **10** | 1 |
| `1 + 1 + 0` | **10** | 1 |
| `1 + 1 + 1` | **11** | 1 |

---

## 3. 🔢 Soma com Vai-Um (Carry)

O **carry** (vai-um) funciona exatamente como na soma decimal que aprendemos na escola:

### 3.1 Exemplo em Decimal (revisão)

```
      ¹              ← carry (vai-um)
        4 7
      + 3 5
      ─────
        8 2
```

Passo a passo:
1. Coluna das unidades: 7 + 5 = 12 → escreve **2**, vai **1**
2. Coluna das dezenas: 1 + 4 + 3 = 8

### 3.2 Exemplo em Binário

Vamos somar `1011₂ + 0110₂`:

```
      Carry:  1 1 0
              1 0 1 1    (11 em decimal)
          +   0 1 1 0    ( 6 em decimal)
          ───────────
            1 0 0 0 1    (17 em decimal) ✓
```

**Passo a passo (da direita para a esquerda):**

| Passo | Coluna | Operação | Resultado | Carry |
|-------|--------|----------|-----------|-------|
| 1 | Posição 0 | 1 + 0 = 1 | **1** | 0 |
| 2 | Posição 1 | 1 + 1 = 10 | **0** | 1 |
| 3 | Posição 2 | 1 + 0 + 1 = 10 | **0** | 1 |
| 4 | Posição 3 | 1 + 1 + 0 = 10 | **0** | 1 |
| 5 | Carry final | — | **1** | — |

Resultado: **10001₂** = 17₁₀ (11 + 6 = 17) ✓

> 🎓 **Dica:** Sempre confira sua resposta convertendo para decimal!

---

## 4. 📐 Adição de Números com Múltiplos Bits

### 4.1 Soma de 8 Bits

Vamos somar `10110011₂ + 01001110₂`:

```
      Carry:  1 1 1 1 0 1 0
              1 0 1 1 0 0 1 1    (179 em decimal)
          +   0 1 0 0 1 1 1 0    ( 78 em decimal)
          ─────────────────────
            1 0 0 0 0 0 0 0 1    (257 em decimal) ✓
```

> ⚠️ **Atenção:** O resultado tem **9 bits**, mas só tínhamos 8 bits! Isso é um **overflow** — veremos isso na próxima seção.

### 4.2 Método Prático para Soma Binária

```
    ALGORITMO: Soma Binária Coluna a Coluna
    ─────────────────────────────────────────
    1. Alinhe os números à direita
    2. Comece pela coluna mais à direita (LSB)
    3. Some: bit_A + bit_B + carry_anterior
    4. Se resultado ≤ 1: escreva o resultado, carry = 0
    5. Se resultado = 2: escreva 0, carry = 1
    6. Se resultado = 3: escreva 1, carry = 1
    7. Repita para cada coluna, movendo à esquerda
    8. Se sobrar carry, escreva-o como bit mais significativo
```

---

## 5. 💥 Overflow — Transbordamento

### 5.1 O Que É Overflow?

**Overflow** ocorre quando o resultado de uma operação **não cabe** no número de bits disponível.

> 💡 **Analogia:** Imagine um copo d'água. Se o copo tem capacidade para 255 ml (8 bits sem sinal = 0 a 255) e você tenta colocar 300 ml, os 45 ml extras **transbordam**. O computador não tem como "aumentar o copo" — ele simplesmente perde os bits excedentes!

### 5.2 Overflow em Números Sem Sinal

Com **n bits**, podemos representar valores de **0** a **2ⁿ - 1**:

| Bits | Valor Máximo | Faixa |
|------|-------------|-------|
| 4 | 15 | 0 a 15 |
| 8 | 255 | 0 a 255 |
| 16 | 65.535 | 0 a 65.535 |
| 32 | 4.294.967.295 | 0 a ~4,3 bilhões |

**Exemplo de overflow (4 bits):**

```
      Carry:  1 1 1
              1 1 1 1    (15 em decimal)
          +   0 0 0 1    ( 1 em decimal)
          ───────────
          [1] 0 0 0 0    (0?! deveria ser 16!)
           ↑
           Este bit é "perdido" — overflow!
```

Com 4 bits, 15 + 1 deveria dar 16, mas 16 não cabe em 4 bits. O resultado é **0000₂** = 0. O carry saiu para fora!

### 5.3 Detecção de Overflow

**Para números sem sinal:** Overflow ocorre quando há **carry-out** (vai-um saindo do bit mais significativo).

**Para números com sinal (complemento a 2):** Overflow ocorre quando:
- Somamos dois números **positivos** e o resultado é **negativo**, OU
- Somamos dois números **negativos** e o resultado é **positivo**

> 🔑 **Regra prática:** Em complemento a 2, overflow ocorre quando o carry **entrando** no último bit é diferente do carry **saindo** do último bit.

---

## 6. 📊 Representação de Números com Sinal

No mundo real, precisamos representar números **negativos**. Mas o computador só tem 0 e 1! Como resolver?

### 6.1 Sinal-Magnitude

O bit mais significativo (MSB) indica o sinal:
- **0** = positivo
- **1** = negativo

```
    Com 4 bits:
    0 101 = +5
    1 101 = -5
    ↑
    bit de sinal
```

| Binário | Decimal |
|---------|---------|
| 0111 | +7 |
| 0110 | +6 |
| 0101 | +5 |
| 0100 | +4 |
| 0011 | +3 |
| 0010 | +2 |
| 0001 | +1 |
| 0000 | +0 |
| 1000 | **-0** ← problema! |
| 1001 | -1 |
| 1010 | -2 |
| 1011 | -3 |
| 1100 | -4 |
| 1101 | -5 |
| 1110 | -6 |
| 1111 | -7 |

> ⚠️ **Problemas:** Dois zeros (+0 e -0) e a soma direta não funciona!

### 6.2 Complemento a 1

Para negar um número, **inverta todos os bits**:

```
    +5 em 4 bits = 0101
    -5 em 4 bits = 1010  (inverteu cada bit)
```

| Binário | Decimal |
|---------|---------|
| 0111 | +7 |
| 0110 | +6 |
| 0101 | +5 |
| 0100 | +4 |
| 0011 | +3 |
| 0010 | +2 |
| 0001 | +1 |
| 0000 | +0 |
| 1111 | **-0** ← ainda temos dois zeros |
| 1110 | -1 |
| 1101 | -2 |
| 1100 | -3 |
| 1011 | -4 |
| 1010 | -5 |
| 1001 | -6 |
| 1000 | -7 |

> ⚠️ **Problema:** Ainda tem dois zeros! Mas pelo menos a soma "quase" funciona.

### 6.3 Complemento a 2 ⭐ (O Método Usado pelos Computadores!)

Para negar um número: **inverta todos os bits e some 1**:

```
    +5 em 4 bits = 0101
    Inverte:       1010
    Soma 1:      + 0001
                 ──────
    -5 em C2:      1011
```

| Binário | Decimal |
|---------|---------|
| 0111 | +7 |
| 0110 | +6 |
| 0101 | +5 |
| 0100 | +4 |
| 0011 | +3 |
| 0010 | +2 |
| 0001 | +1 |
| 0000 | 0 |
| 1111 | -1 |
| 1110 | -2 |
| 1101 | -3 |
| 1100 | -4 |
| 1011 | -5 |
| 1010 | -6 |
| 1001 | -7 |
| 1000 | -8 |

> ✅ **Vantagens:** Apenas **um zero**, faixa de -8 a +7 (com 4 bits), e a **soma funciona diretamente**!

### 6.4 Comparação das Representações (4 bits)

| Método | Faixa | Zeros | Soma Direta? |
|--------|-------|-------|-------------|
| Sem sinal | 0 a 15 | 1 | Sim |
| Sinal-Magnitude | -7 a +7 | 2 | Não |
| Complemento a 1 | -7 a +7 | 2 | Quase* |
| **Complemento a 2** | **-8 a +7** | **1** | **Sim** ✅ |

_*Complemento a 1 exige "end-around carry" — um ajuste extra._

---

## 7. 🧮 Soma em Complemento a 2

Esta é a forma como os computadores **realmente** somam! Funciona para positivos e negativos usando o mesmo circuito.

### 7.1 Regras

1. Represente ambos os números em complemento a 2
2. Some normalmente (ignorando que representam números com sinal)
3. Descarte qualquer carry que saia do bit mais significativo
4. Verifique se houve overflow

### 7.2 Caso 1: Positivo + Positivo

Somar +3 e +4 em 4 bits (complemento a 2):

```
       0 0 1 1    (+3)
   +   0 1 0 0    (+4)
   ───────────
       0 1 1 1    (+7) ✓
```

### 7.3 Caso 2: Positivo + Negativo

Somar +5 e -3 em 4 bits:

```
    +5 = 0101
    -3 → +3 = 0011, inverte = 1100, +1 = 1101

       Carry:  1 1 0 1
               0 1 0 1    (+5)
           +   1 1 0 1    (-3)
           ───────────
           [1] 0 0 1 0    (+2) ✓
            ↑
            Carry descartado!
```

Resultado: **0010₂ = +2** (5 + (-3) = 2) ✓

### 7.4 Caso 3: Negativo + Negativo

Somar -2 e -3 em 4 bits:

```
    -2 → +2 = 0010, inverte = 1101, +1 = 1110
    -3 → +3 = 0011, inverte = 1100, +1 = 1101

       Carry:  1 1 1 0
               1 1 1 0    (-2)
           +   1 1 0 1    (-3)
           ───────────
           [1] 1 0 1 1    (-5) ✓
            ↑
            Carry descartado!
```

Verificação: 1011₂ em C2 → inverte = 0100, +1 = 0101 = 5, logo -5 ✓

### 7.5 Caso 4: Overflow em Complemento a 2

Somar +5 e +4 em 4 bits (faixa: -8 a +7):

```
       Carry:  0 1 0 0
               0 1 0 1    (+5)
           +   0 1 0 0    (+4)
           ───────────
               1 0 0 1    (-7?!) ✗ OVERFLOW!
```

Resultado deveria ser +9, mas +9 não cabe em 4 bits com sinal! O resultado 1001₂ é interpretado como -7 em complemento a 2 — **errado**!

> 🚨 **Overflow detectado:** Somamos dois positivos (MSB=0) e obtivemos negativo (MSB=1)!

---

## 8. ⚡ Circuitos Somadores

Como o computador implementa essa soma fisicamente? Com **portas lógicas** organizadas em circuitos somadores!

### 8.1 Meio-Somador (Half Adder)

Soma **dois bits** sem carry de entrada.

**Tabela Verdade:**

| A | B | Soma (S) | Carry (C) |
|---|---|----------|-----------|
| 0 | 0 | 0 | 0 |
| 0 | 1 | 1 | 0 |
| 1 | 0 | 1 | 0 |
| 1 | 1 | 0 | 1 |

**Expressões lógicas:**

```
    S = A ⊕ B    (XOR — OU Exclusivo)
    C = A · B    (AND — E)
```

**Circuito (diagrama em texto):**

```
    A ──┬──→ [XOR] ──→ S (Soma)
        │       ↑
    B ──┼───────┘
        │
        └──→ [AND] ──→ C (Carry)
              ↑
    B ────────┘
```

> 💡 O meio-somador é como uma calculadora que só sabe somar **dois algarismos de um dígito**, sem considerar o "vai-um" de uma coluna anterior.

### 8.2 Somador Completo (Full Adder)

Soma **dois bits** mais um **carry de entrada** (Cₑ). Este é o bloco fundamental usado na prática!

**Tabela Verdade:**

| A | B | Cₑ (Carry In) | Soma (S) | Cₛ (Carry Out) |
|---|---|----------------|----------|-----------------|
| 0 | 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 1 | 0 |
| 0 | 1 | 0 | 1 | 0 |
| 0 | 1 | 1 | 0 | 1 |
| 1 | 0 | 0 | 1 | 0 |
| 1 | 0 | 1 | 0 | 1 |
| 1 | 1 | 0 | 0 | 1 |
| 1 | 1 | 1 | 1 | 1 |

**Expressões lógicas:**

```
    S  = A ⊕ B ⊕ Cₑ
    Cₛ = (A · B) + (Cₑ · (A ⊕ B))
```

**Circuito (diagrama em texto):**

```
    A ──┬──→ [XOR] ──┬──→ [XOR] ──→ S (Soma)
        │      ↑      │      ↑
    B ──┼──────┘      │   Cₑ─┘
        │             │
        ├──→ [AND] ──→│──→ [OR] ──→ Cₛ (Carry Out)
        │      ↑      │      ↑
    B ──┘      │      └→ [AND]
               │           ↑
              A        Cₑ─┘
```

### 8.3 Somador de N Bits (Ripple-Carry Adder)

Para somar números de 4 bits, conectamos 4 somadores completos em **cascata**:

```
                  C₄    C₃    C₂    C₁    C₀=0
                  ↑     ↑     ↑     ↑     │
              ┌──────┐┌──────┐┌──────┐┌──────┐
    A₃,B₃ →→→│  FA  ││  FA  ││  FA  ││  FA  │←← A₀,B₀
              └──┬───┘└──┬───┘└──┬───┘└──┬───┘
                 ↓       ↓       ↓       ↓
                 S₃      S₂      S₁      S₀

    FA = Full Adder (Somador Completo)
    C₀ = 0 (sem carry inicial)
    C₄ = Carry de saída (pode indicar overflow)
```

> 🎓 **Por que "Ripple"?** O carry "propaga" (ripple = ondulação) do somador menos significativo para o mais significativo, como uma onda. O somador da posição 3 precisa **esperar** o carry chegar das posições 0, 1 e 2 — isso é o principal gargalo de velocidade!

---

## 9. 🌍 Aplicações no Mundo Real

### 9.1 A ULA (Unidade Lógica Aritmética)

A **ULA** é o "coração matemático" do processador. Ela contém os circuitos somadores e realiza:

```
    ┌─────────────────────────┐
    │          ULA            │
    │                         │
    │  • Soma (ADD)           │
    │  • Subtração (SUB)      │
    │  • AND, OR, NOT, XOR    │
    │  • Comparação           │
    │  • Shift (deslocamento) │
    │                         │
    │  Flags de saída:        │
    │  • Zero (Z)             │
    │  • Carry (C)            │
    │  • Overflow (V)         │
    │  • Sinal/Negativo (N)   │
    └─────────────────────────┘
```

### 9.2 Como o Processador Soma

Quando você escreve `a + b` em qualquer linguagem de programação:

1. O valor de `a` é carregado em um **registrador** (ex: R1)
2. O valor de `b` é carregado em outro **registrador** (ex: R2)
3. A instrução `ADD R3, R1, R2` é executada
4. A ULA realiza a soma bit a bit usando somadores completos
5. O resultado vai para R3
6. As **flags** são atualizadas (carry, overflow, zero, sinal)

### 9.3 Velocidade da Soma

| Tipo de Somador | Velocidade | Custo |
|----------------|-----------|-------|
| Ripple-Carry (cascata) | Lento (propaga carry) | Barato |
| Carry-Lookahead | Rápido (calcula carry em paralelo) | Caro |
| Carry-Select | Intermediário | Intermediário |

> 💡 **Processadores modernos** usam variações sofisticadas do carry-lookahead para somar números de **64 bits** em frações de nanossegundo!

### 9.4 Analogia do Dia a Dia

Imagine que você tem um grupo de **4 amigos**, cada um responsável por somar uma coluna de números:

- **Ripple-Carry:** O amigo da coluna 2 precisa esperar o amigo da coluna 1 terminar para saber se tem "vai-um". O da coluna 3 espera o da coluna 2, e assim por diante. É como uma fila!
- **Carry-Lookahead:** Os amigos são mais espertos — eles conseguem **prever** se vai ter "vai-um" antes mesmo de a conta estar pronta, trabalhando todos ao mesmo tempo!

---

## 10. 📌 Resumo

| Conceito | Resumo |
|----------|--------|
| Regras da soma | 0+0=0, 0+1=1, 1+0=1, 1+1=10 |
| Carry | "Vai-um" — quando o resultado de uma coluna ≥ 2 |
| Overflow | Resultado não cabe nos bits disponíveis |
| Complemento a 2 | Inverte bits e soma 1 — método padrão para números com sinal |
| Meio-somador | Soma 2 bits (S = A⊕B, C = A·B) |
| Somador completo | Soma 2 bits + carry (S = A⊕B⊕Cₑ) |
| Ripple-carry | N somadores completos em cascata |

> 🧠 **Para lembrar:** A soma binária é a operação mais fundamental do computador. **Todas** as outras operações aritméticas podem ser construídas a partir dela!

---

## 11. 📚 Leitura Complementar

- 📖 STALLINGS, W. **Arquitetura e Organização de Computadores**. Cap. 10 — Aritmética do Computador.
- 📖 TANENBAUM, A. S. **Organização Estruturada de Computadores**. Cap. 3 — O Nível Lógico Digital.
- 📖 TOCCI, R. J.; WIDMER, N. S. **Sistemas Digitais: Princípios e Aplicações**. Cap. 6 — Aritmética Digital.
- 🌐 [Simulador de Somadores — CircuitVerse](https://circuitverse.org/)

---

> ⬅️ [Aula 04 — Conversões, Bit e Byte](../04-sistemas-numeracao-conversoes-bit-byte/README.md) | [Exemplos](./exemplos/README.md) | [Exercícios](./exercicios/README.md) | [Aula 06 — Subtração Binária](../06-operacao-aritmetica-subtracao/README.md) ➡️
