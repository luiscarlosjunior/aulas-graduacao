# ➖ Aula 06 — Operação Aritmética: Subtração Binária

> **Disciplina:** Organização de Computadores  
> **Duração:** 2 horas-aula  
> **Nível:** Iniciante a Intermediário  
> **Pré-requisitos:** Aula 05 — Soma Binária e Complemento a 2

---

## 🎯 Objetivos de Aprendizado

Ao final desta aula, o estudante será capaz de:

- ✅ Aplicar as regras fundamentais da subtração binária
- ✅ Realizar subtração com **empréstimo** (borrow)
- ✅ Subtrair usando o **método do complemento** (complemento a 1 e complemento a 2)
- ✅ Explicar por que computadores usam soma com complemento em vez de subtração direta
- ✅ Detectar **overflow** em operações de subtração
- ✅ Compreender como a ULA implementa subtração usando somadores
- ✅ Conectar os conceitos de subtração ao projeto da ULA

---

## 📋 Sumário

1. [Revisão: Soma Binária e Complemento a 2](#1--revisão-soma-binária-e-complemento-a-2)
2. [Regras Fundamentais da Subtração Binária](#2--regras-fundamentais-da-subtração-binária)
3. [Subtração com Empréstimo (Borrow)](#3--subtração-com-empréstimo-borrow)
4. [Subtração pelo Método do Complemento](#4--subtração-pelo-método-do-complemento)
5. [Subtração em Complemento a 2](#5--subtração-em-complemento-a-2)
6. [Overflow na Subtração](#6--overflow-na-subtração)
7. [Comparação: Subtração Decimal × Binária](#7--comparação-subtração-decimal--binária)
8. [Como a CPU Implementa a Subtração](#8--como-a-cpu-implementa-a-subtração)
9. [Conexão com o Projeto da ULA](#9--conexão-com-o-projeto-da-ula)
10. [Resumo](#10--resumo)
11. [Leitura Complementar](#11--leitura-complementar)

---

## 1. 🔄 Revisão: Soma Binária e Complemento a 2

Antes de subtrair, vamos relembrar o que aprendemos na Aula 05:

| Conceito | Resumo |
|----------|--------|
| Soma binária | 0+0=0, 0+1=1, 1+0=1, 1+1=10 |
| Carry | "Vai-um" quando resultado ≥ 2 |
| Complemento a 2 | Inverte bits e soma 1 → representa números negativos |
| Faixa (8 bits, C2) | -128 a +127 |
| Somador completo | S = A⊕B⊕C, Cₛ = (A·B) + (C·(A⊕B)) |

> 🔑 **Fato crucial:** Subtrair é o mesmo que **somar o negativo**!
> ```
>     A - B = A + (-B)
> ```
> Esta identidade simples é a base de tudo que veremos nesta aula!

---

## 2. ➖ Regras Fundamentais da Subtração Binária

A subtração binária tem **4 regras** básicas:

| Operação | Resultado | Empréstimo (Borrow) | Analogia Decimal |
|----------|-----------|---------------------|------------------|
| `0 - 0` | **0** | 0 | 0 - 0 = 0 |
| `1 - 0` | **1** | 0 | 1 - 0 = 1 |
| `1 - 1` | **0** | 0 | 1 - 1 = 0 |
| `0 - 1` | **1** (com empréstimo) | 1 | Como 10 - 1 = 9 em decimal |

### 🔑 A Regra Crucial: `0 - 1 = 1` com empréstimo

Assim como no sistema decimal, quando subtraímos um dígito maior de um menor, precisamos **pedir emprestado** à posição vizinha:

```
    Em decimal:  0 - 1 → empresta da próxima casa
                 10 - 1 = 9   (tomamos emprestado 10 da casa seguinte)
    
    Em binário:  0 - 1 → empresta da próxima casa
                 10 - 1 = 1   (tomamos emprestado 10₂ = 2₁₀ da casa seguinte)
```

> 💡 **Analogia:** Imagine que você tem R$ 0 na carteira e precisa pagar R$ 1. Você pede emprestado R$ 10 (uma "nota da próxima casa") — em binário, esse empréstimo vale 2 (= 10₂). Com 2, paga 1 e sobra 1!

---

## 3. 🔢 Subtração com Empréstimo (Borrow)

### 3.1 Comparação com Decimal

```
    Decimal:           Binário:
    
        ⁰ 10               ⁰ 10
        5̶  2               1̶  0  1  0    (10₁₀)
    -   1  7           -   0  1  1  1    ( 7₁₀)
    ───────            ─────────────
        3  5               0  0  1  1    ( 3₁₀)
```

### 3.2 Exemplo Detalhado: 1010₂ - 0111₂

```
    Empréstimos: 1  1  1
                 1  0  1  0    (10 em decimal)
             -   0  1  1  1    ( 7 em decimal)
             ─────────────
                 0  0  1  1    ( 3 em decimal) ✓
```

**Passo a passo (da direita para a esquerda):**

| Passo | Coluna | Operação | Resultado | Empréstimo |
|-------|--------|----------|-----------|------------|
| 1 | Pos 0 | 0 - 1: precisa empréstimo! → 10 - 1 = 1 | **1** | 1 (da pos 1) |
| 2 | Pos 1 | 1 - 1 - 1(empréstimo) = -1: precisa empréstimo! → 10 - 1 - 1 = 0 | **0** | 1 (da pos 2) |
| 3 | Pos 2 | 0 - 1 - 1(empréstimo) = -2: precisa empréstimo! → 10 - 1 - 1 = 0 | **0** | 1 (da pos 3) |
| 4 | Pos 3 | 1 - 0 - 1(empréstimo) = 0 | **0** | 0 |

Resultado: **0011₂** = 3₁₀ (10 - 7 = 3) ✓

### 3.3 Quando o Resultado É Negativo (Subtração Direta)

O que acontece quando subtraímos um número **maior** de um **menor**?

Exemplo: 0100₂ - 0111₂ (4 - 7):

Neste caso, o empréstimo "sai" do último bit — isso indica que o resultado é **negativo**. A subtração direta com empréstimo não funciona bem para resultados negativos. Por isso, os computadores usam um método diferente!

---

## 4. 🔄 Subtração pelo Método do Complemento

### 4.1 A Grande Ideia

Em vez de subtrair diretamente, podemos **transformar a subtração em soma**:

```
    A - B = A + (-B) = A + (complemento a 2 de B)
```

> 💡 **Por que isso é genial?** Porque o computador **já sabe somar**! Ele tem circuitos somadores prontos. Se conseguir transformar B em -B, pode usar o **mesmo circuito** para somar e subtrair!

### 4.2 Método com Complemento a 1 (Histórico)

```
    Para calcular A - B:
    1. Calcule o complemento a 1 de B (inverta todos os bits)
    2. Some A + complemento_a_1(B)
    3. Se houver carry de saída: some 1 ao resultado (end-around carry)
    4. Se NÃO houver carry: o resultado é negativo (está em C1)
```

**Exemplo: 7 - 3 em 4 bits**

```
    A = 0111 (7)
    B = 0011 (3) → C1 de B = 1100

    Soma: 0111 + 1100 = [1]0011
                         ↑ carry de saída → soma 1

    0011 + 0001 = 0100 = 4 ✓ (7 - 3 = 4)
```

> ⚠️ O passo extra de "end-around carry" é inconveniente. Por isso, preferimos o complemento a 2!

### 4.3 Método com Complemento a 2 ⭐ (O Método Real!)

```
    Para calcular A - B:
    1. Calcule o complemento a 2 de B (inverta os bits e some 1)
    2. Some A + complemento_a_2(B)
    3. Descarte qualquer carry de saída
    4. O resultado está pronto! ✓
```

**Exemplo: 7 - 3 em 4 bits**

```
    A = 0111 (7)
    B = 0011 (3) → inverte = 1100, +1 = 1101 → C2 de B = 1101

    Soma: 0111 + 1101 = [1]0100
                         ↑ carry descartado

    Resultado: 0100 = +4 ✓ (7 - 3 = 4)
```

Sem passos extras! Simples e direto!

---

## 5. 🧮 Subtração em Complemento a 2

### 5.1 Caso 1: A > B (Resultado Positivo)

**Calcular: 12 - 5 em 8 bits**

```
    PASSO 1: Representar em C2
    A = +12 = 00001100₂
    B = +5  = 00000101₂
    -B → inverte = 11111010, +1 = 11111011

    PASSO 2: Somar A + (-B)
      Carry:  1 1 1 1 1 0 0 0
              0 0 0 0 1 1 0 0    (+12)
          +   1 1 1 1 1 0 1 1    (-5)
          ─────────────────────
          [1] 0 0 0 0 0 1 1 1    (+7)
           ↑
           Carry descartado

    Resultado: 00000111₂ = +7 ✓ (12 - 5 = 7)
```

### 5.2 Caso 2: A < B (Resultado Negativo)

**Calcular: 5 - 12 em 8 bits**

```
    PASSO 1: Representar em C2
    A  = +5  = 00000101₂
    B  = +12 = 00001100₂
    -B → inverte = 11110011, +1 = 11110100

    PASSO 2: Somar A + (-B)
      Carry:  0 0 0 0 0 1 0 0
              0 0 0 0 0 1 0 1    (+5)
          +   1 1 1 1 0 1 0 0    (-12)
          ─────────────────────
              1 1 1 1 1 0 0 1    (-7)

    PASSO 3: Verificar
    11111001₂ → inverte = 00000110, +1 = 00000111 = 7
    MSB = 1, logo é negativo → -7 ✓ (5 - 12 = -7)
```

### 5.3 Caso 3: Números Iguais

**Calcular: 9 - 9 em 8 bits**

```
    A  = +9 = 00001001₂
    -B → +9 = 00001001, inverte = 11110110, +1 = 11110111

    Soma:
      Carry:  1 1 1 1 1 1 1 0
              0 0 0 0 1 0 0 1    (+9)
          +   1 1 1 1 0 1 1 1    (-9)
          ─────────────────────
          [1] 0 0 0 0 0 0 0 0    (0)
           ↑
           Carry descartado

    Resultado: 00000000₂ = 0 ✓ (9 - 9 = 0)
```

### 5.4 Caso 4: Subtração de Número Negativo

**Calcular: 10 - (-3) em 8 bits**

```
    Isso é o mesmo que 10 + 3 = 13!

    A  = +10 = 00001010₂
    B  = -3 em C2: +3 = 00000011, inverte = 11111100, +1 = 11111101
    -B = -(-3) = +3 = 00000011₂

    Soma:
              0 0 0 0 1 0 1 0    (+10)
          +   0 0 0 0 0 0 1 1    (+3)
          ─────────────────────
              0 0 0 0 1 1 0 1    (+13) ✓
```

---

## 6. 💥 Overflow na Subtração

### 6.1 Quando Ocorre?

Em complemento a 2, overflow na subtração ocorre quando:

- Subtraímos um **negativo** de um **positivo** e o resultado é **negativo**, OU
- Subtraímos um **positivo** de um **negativo** e o resultado é **positivo**

> 🔑 **Regra equivalente:** Como A - B = A + (-B), basta aplicar a regra de overflow da **soma**: overflow ocorre quando o carry entrando no MSB ≠ carry saindo do MSB.

### 6.2 Exemplo de Overflow

**Calcular: 100 - (-50) em 8 bits (faixa: -128 a +127)**

```
    A   = +100 = 01100100₂
    B   = -50 → C2: +50 = 00110010, inverte = 11001101, +1 = 11001110
    -B  = +50 = 00110010₂

    Soma:  A + (-B) = 100 + 50 = 150 (deveria ser!)
    
      Carry:  0 1 1 0 0 1 0 0
              0 1 1 0 0 1 0 0    (+100)
          +   0 0 1 1 0 0 1 0    (+50)
          ─────────────────────
              1 0 0 1 0 1 1 0    (-106?!) ⚠️ OVERFLOW!
```

Resultado esperado: +150, mas +150 > +127 (máximo em 8 bits C2). Overflow!

### 6.3 Tabela de Detecção de Overflow na Subtração

| Operação | Sinal de A | Sinal de B | Sinal do Resultado | Overflow? |
|----------|-----------|-----------|-------------------|-----------|
| A - B (positivo grande - negativo grande) | + | - | - | **Sim** ⚠️ |
| A - B (negativo grande - positivo grande) | - | + | + | **Sim** ⚠️ |
| A - B (mesmo sinal) | + | + | qualquer | **Não** |
| A - B (mesmo sinal) | - | - | qualquer | **Não** |

---

## 7. ⚖️ Comparação: Subtração Decimal × Binária

| Aspecto | Decimal | Binária |
|---------|---------|---------|
| Dígitos | 0 a 9 | 0 a 1 |
| Empréstimo | Empresta 10 | Empresta 2 (= 10₂) |
| Regras | Mais combinações | Apenas 4 regras |
| Negativos | Sinal "-" na frente | Complemento a 2 |
| Implementação | Calculadora/manual | Circuito somador + complemento |

**Exemplo comparativo:**

```
    Decimal: 52 - 37            Binário: 110100 - 100101
    
        ⁴ 12                      ⁰ ¹ ⁰ 10
        5̶  2                      1  1  0̶  1  0  0
    -   3  7                  -   1  0  0  1  0  1
    ───────                   ─────────────────────
        1  5                      0  0  1  1  1  1
    
    52 - 37 = 15₁₀              110100₂ - 100101₂ = 001111₂ = 15₁₀ ✓
```

---

## 8. 🖥️ Como a CPU Implementa a Subtração

### 8.1 O Truque Elegante

A CPU **não tem um circuito subtrator**! Em vez disso, ela reutiliza o **somador** com uma modificação simples:

```
    SUBTRAÇÃO: A - B

    1. Inverte todos os bits de B (usando portas NOT)
    2. Soma A + NOT(B) + 1 (o +1 entra como Carry In = 1)
    3. Pronto! A - B = A + NOT(B) + 1 = A + C2(B)
```

### 8.2 Circuito Somador/Subtrator

```
    Sinal SUB (0 = soma, 1 = subtração)
    │
    │     B₃    B₂    B₁    B₀
    │     │     │     │     │
    │  ┌──▼──┐┌──▼──┐┌──▼──┐┌──▼──┐
    ├──►│ XOR ││ XOR ││ XOR ││ XOR │  ← Se SUB=1, inverte B
    │  └──┬──┘└──┬──┘└──┬──┘└──┬──┘    Se SUB=0, B passa direto
    │     │     │     │     │
    │  ┌──▼──┐┌──▼──┐┌──▼──┐┌──▼──┐
    │  │ FA₃ ││ FA₂ ││ FA₁ ││ FA₀ │←── SUB (como Carry In)
    │  └──┬──┘└──┬──┘└──┬──┘└──┬──┘    SUB=1 → soma 1 → completa o C2!
    │     │     │     │     │
    │     S₃    S₂    S₁    S₀
    │
    ▼ C₄ (carry/borrow out)
```

**Como funciona:**

| Operação | SUB | Efeito nos XOR | Carry In | Resultado |
|----------|-----|----------------|----------|-----------|
| Soma (A+B) | 0 | B passa direto (B⊕0=B) | 0 | A + B |
| Subtração (A-B) | 1 | B é invertido (B⊕1=NOT B) | 1 | A + NOT(B) + 1 = A - B |

> 🎓 **Brilhante!** Com **um único sinal de controle** (SUB), o mesmo circuito faz soma e subtração. Isso economiza transistores, espaço e energia!

### 8.3 Exemplo Prático no Circuito

**Calcular 0101 - 0011 (5 - 3) usando o somador/subtrator:**

```
    SUB = 1 (subtração)
    
    B = 0011 → XOR com 1111 → NOT(B) = 1100
    Carry In = 1
    
    Soma: A + NOT(B) + 1
          0101 + 1100 + 1
    
      Carry: 1 1 0 0 1
             0 1 0 1
         +   1 1 0 0
         + carry  1
         ──────────
         [1]0 0 1 0  = +2 ✓ (5 - 3 = 2)
```

---

## 9. 🔧 Conexão com o Projeto da ULA

### 9.1 Operações da ULA

A ULA combina o somador/subtrator com portas lógicas:

```
    ┌─────────────────────────────────────────┐
    │                  ULA                     │
    │                                          │
    │  Entradas: A (n bits), B (n bits)        │
    │  Controle: OpCode (seleciona operação)   │
    │                                          │
    │  ┌──────────┐ ┌──────────┐ ┌──────────┐ │
    │  │ Somador/  │ │  Lógica  │ │  Shift   │ │
    │  │ Subtrator │ │ AND/OR/  │ │ Left/    │ │
    │  │          │ │ NOT/XOR  │ │ Right    │ │
    │  └────┬─────┘ └────┬─────┘ └────┬─────┘ │
    │       └──────┬──────┘────────────┘       │
    │              │                           │
    │         ┌────▼────┐                      │
    │         │   MUX   │ ← OpCode seleciona   │
    │         └────┬────┘                      │
    │              │                           │
    │  Saída: Resultado + Flags (Z,C,V,N)      │
    └─────────────────────────────────────────┘
```

### 9.2 Flags Relevantes para Subtração

| Flag | Nome | Significado na Subtração |
|------|------|--------------------------|
| **Z** | Zero | Resultado = 0 (A == B) |
| **C** | Carry/Borrow | Se não houver borrow (A ≥ B para sem sinal) |
| **V** | Overflow | Resultado fora da faixa (C2) |
| **N** | Negativo | MSB = 1 (resultado negativo em C2) |

### 9.3 Comparação com Flags

Quando o processador executa uma instrução de **comparação** (CMP), ele faz internamente `A - B` e **descarta o resultado**, mas **mantém as flags**!

```
    CMP A, B    →    Calcula A - B internamente
                     Z=1 se A == B
                     N=1 se A < B (com sinal)
                     C=0 se A < B (sem sinal)
```

> 💡 Toda comparação que você faz em programação (`if (a > b)`, `while (x != 0)`) usa subtração internamente!

---

## 10. 📌 Resumo

| Conceito | Resumo |
|----------|--------|
| Subtração direta | 0-0=0, 1-0=1, 1-1=0, 0-1=1 com empréstimo |
| Empréstimo (borrow) | Semelhante ao "empresta" da subtração decimal |
| Método do complemento | A - B = A + (-B) — transforma subtração em soma |
| Complemento a 2 | Inverte bits + soma 1 → método preferido |
| Somador/Subtrator | Mesmo circuito faz ambas operações (sinal SUB + XOR) |
| Overflow na subtração | Quando sinais opostos produzem resultado inesperado |
| CMP (comparação) | Usa subtração internamente para definir flags |

> 🧠 **Mensagem principal:** O computador é "preguiçoso" de forma inteligente — ele transforma **toda subtração em uma soma**, reaproveitando o mesmo hardware. Simplicidade é elegância!

---

## 11. 📚 Leitura Complementar

- 📖 STALLINGS, W. **Arquitetura e Organização de Computadores**. Cap. 10 — Aritmética do Computador.
- 📖 TANENBAUM, A. S. **Organização Estruturada de Computadores**. Cap. 3 — O Nível Lógico Digital.
- 📖 TOCCI, R. J.; WIDMER, N. S. **Sistemas Digitais: Princípios e Aplicações**. Cap. 6 — Aritmética Digital.
- 📖 PATTERSON, D. A.; HENNESSY, J. L. **Organização e Projeto de Computadores**. Cap. 3 — Aritmética para Computadores.
- 🌐 [Simulador de ULA — CircuitVerse](https://circuitverse.org/)

---

> ⬅️ [Aula 05 — Soma Binária](../05-operacao-aritmetica-soma/README.md) | [Exemplos](./exemplos/README.md) | [Exercícios](./exercicios/README.md) | [Aula 07 — Portas Lógicas](../07-portas-logicas-definicao-tabela-verdade/README.md) ➡️
