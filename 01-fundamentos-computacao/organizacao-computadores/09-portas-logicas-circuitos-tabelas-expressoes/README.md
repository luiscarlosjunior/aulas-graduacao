# 🔄 Aula 09 — Funções e Portas Lógicas: Circuitos Lógicos, Tabelas da Verdade e Expressões Booleanas

> **Disciplina:** Organização de Computadores  
> **Duração:** 2 horas-aula  
> **Nível:** Intermediário  
> **Pré-requisitos:** Aula 07 — Portas Lógicas e Tabelas Verdade; Aula 08 — Expressões Booleanas e Circuitos

---

## 🎯 Objetivos de Aprendizado

Ao final desta aula, o estudante será capaz de:

- ✅ Compreender as **três representações** da lógica digital: circuitos, tabelas verdade e expressões booleanas
- ✅ **Converter sistematicamente** entre quaisquer duas representações
- ✅ Derivar expressões booleanas a partir de tabelas verdade nas formas **SOP** e **POS**
- ✅ Identificar e utilizar **mintermos** e **maxtermos**
- ✅ Escrever expressões na **forma canônica** (SOP e POS)
- ✅ Simplificar funções booleanas usando **Mapas de Karnaugh** (3 e 4 variáveis)
- ✅ Trabalhar com **condições don't-care** em Mapas de Karnaugh
- ✅ Aplicar uma **metodologia de projeto** completa (especificação → circuito otimizado)

---

## 📋 Sumário

1. [Revisão: Portas, Tabelas e Expressões](#1--revisão-portas-tabelas-e-expressões)
2. [As Três Representações da Lógica Digital](#2--as-três-representações-da-lógica-digital)
3. [Conversão: Tabela Verdade → Expressão Booleana](#3--conversão-tabela-verdade--expressão-booleana)
4. [Mintermos e Maxtermos](#4--mintermos-e-maxtermos)
5. [Formas Canônicas: SOP e POS](#5--formas-canônicas-sop-e-pos)
6. [Conversão: Tabela Verdade → Circuito](#6--conversão-tabela-verdade--circuito)
7. [Conversão: Circuito → Tabela Verdade](#7--conversão-circuito--tabela-verdade)
8. [Mapa de Karnaugh para 3 Variáveis](#8--mapa-de-karnaugh-para-3-variáveis)
9. [Mapa de Karnaugh para 4 Variáveis](#9--mapa-de-karnaugh-para-4-variáveis)
10. [Condições Don't-Care](#10--condições-dont-care)
11. [Metodologia de Projeto](#11--metodologia-de-projeto)
12. [Resumo](#12--resumo)
13. [Leitura Complementar](#13--leitura-complementar)

---

## 1. 🔍 Revisão: Portas, Tabelas e Expressões

Nas aulas anteriores, estudamos os três pilares da lógica digital:

| Conceito | O que é | Exemplo |
|----------|---------|---------|
| **Porta Lógica** | Componente físico que realiza uma operação lógica | AND, OR, NOT, NAND, NOR, XOR |
| **Tabela Verdade** | Tabela com todas as combinações de entrada e suas saídas | 2ⁿ linhas para n variáveis |
| **Expressão Booleana** | Fórmula algébrica que descreve a função lógica | S = A · B + C̄ |

### 1.1 Portas Lógicas — Referência Rápida

```
    AND         OR          NOT         NAND        NOR         XOR
  A ──┐      A ──┐       A ──[NOT]   A ──┐       A ──┐      A ──┐
      │AND     │OR├─S      ──── S       │NAND├S      │NOR├S      │XOR├─S
  B ──┘      B ──┘                  B ──┘       B ──┘      B ──┘
  A·B        A+B          Ā         (A·B)̄       (A+B)̄      A⊕B
```

### 1.2 Tabelas Verdade Fundamentais

| A | B | AND | OR | NAND | NOR | XOR | XNOR |
|---|---|-----|----|------|-----|-----|------|
| 0 | 0 | 0 | 0 | 1 | 1 | 0 | 1 |
| 0 | 1 | 0 | 1 | 1 | 0 | 1 | 0 |
| 1 | 0 | 0 | 1 | 1 | 0 | 1 | 0 |
| 1 | 1 | 1 | 1 | 0 | 0 | 0 | 1 |

---

## 2. 🔺 As Três Representações da Lógica Digital

Toda função lógica pode ser descrita de **três formas equivalentes**, e podemos converter entre elas:

```
                    ┌─────────────────┐
                    │  TABELA VERDADE │
                    └────────┬────────┘
                       ↗           ↘
           ┌──────────┐             ┌──────────────┐
           │ CIRCUITO │ ◄─────────► │  EXPRESSÃO   │
           │  LÓGICO  │             │  BOOLEANA    │
           └──────────┘             └──────────────┘
```

### 2.1 Mapa de Conversões

| De → Para | Método |
|-----------|--------|
| **Tabela → Expressão** | Soma de Produtos (SOP) ou Produto de Somas (POS) |
| **Tabela → Circuito** | Primeiro derive a expressão, depois construa o circuito |
| **Expressão → Tabela** | Avalie a expressão para todas as combinações de entrada |
| **Expressão → Circuito** | Decomponha a expressão em portas (de dentro para fora) |
| **Circuito → Expressão** | Leia porta a porta, das entradas para a saída |
| **Circuito → Tabela** | Primeiro extraia a expressão, depois construa a tabela |

> 💡 **Ponto-chave:** A tabela verdade é a representação mais "fundamental" — ela define completamente a função. A expressão e o circuito são formas de implementá-la.

---

## 3. 📐 Conversão: Tabela Verdade → Expressão Booleana

Esta é uma das conversões mais importantes no projeto digital. Existem dois métodos:

### 3.1 Método SOP — Soma de Produtos (Sum of Products)

**Algoritmo:**

```
    1. Identifique todas as linhas da tabela onde a SAÍDA = 1
    2. Para cada linha com saída 1, escreva um MINTERMO:
       - Variável = 1 → escreva a variável (ex: A)
       - Variável = 0 → escreva a variável complementada (ex: Ā)
    3. Conecte todos os mintermos com OR (+)
```

**Exemplo:** Encontrar a expressão SOP para a tabela:

| A | B | C | S |
|---|---|---|---|
| 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 1 |
| 0 | 1 | 0 | 0 |
| 0 | 1 | 1 | 1 |
| 1 | 0 | 0 | 1 |
| 1 | 0 | 1 | 0 |
| 1 | 1 | 0 | 1 |
| 1 | 1 | 1 | 0 |

**Linhas com S = 1:** linhas 1, 3, 4, 6 (contando de 0)

| Linha | A | B | C | Mintermo |
|-------|---|---|---|----------|
| 1 | 0 | 0 | 1 | Ā · B̄ · C |
| 3 | 0 | 1 | 1 | Ā · B · C |
| 4 | 1 | 0 | 0 | A · B̄ · C̄ |
| 6 | 1 | 1 | 0 | A · B · C̄ |

**Resultado SOP:** `S = Ā·B̄·C + Ā·B·C + A·B̄·C̄ + A·B·C̄`

### 3.2 Método POS — Produto de Somas (Product of Sums)

**Algoritmo:**

```
    1. Identifique todas as linhas da tabela onde a SAÍDA = 0
    2. Para cada linha com saída 0, escreva um MAXTERMO:
       - Variável = 0 → escreva a variável (ex: A)
       - Variável = 1 → escreva a variável complementada (ex: Ā)
    3. Conecte todos os maxtermos com AND (·)
```

**Usando a mesma tabela:**

**Linhas com S = 0:** linhas 0, 2, 5, 7

| Linha | A | B | C | Maxtermo |
|-------|---|---|---|----------|
| 0 | 0 | 0 | 0 | A + B + C |
| 2 | 0 | 1 | 0 | A + B̄ + C |
| 5 | 1 | 0 | 1 | Ā + B + C̄ |
| 7 | 1 | 1 | 1 | Ā + B̄ + C̄ |

**Resultado POS:** `S = (A+B+C) · (A+B̄+C) · (Ā+B+C̄) · (Ā+B̄+C̄)`

### 3.3 SOP vs POS — Quando Usar Qual?

| Critério | SOP | POS |
|----------|-----|-----|
| **Baseado em** | Saídas = 1 | Saídas = 0 |
| **Usa** | Mintermos | Maxtermos |
| **Operação nível 1** | AND | OR |
| **Operação nível 2** | OR | AND |
| **Melhor quando** | Poucas saídas = 1 | Poucas saídas = 0 |

> 💡 Se a tabela tem mais 0s que 1s, o SOP gerará uma expressão mais curta. Se tem mais 1s que 0s, o POS será mais curto.

---

## 4. 📊 Mintermos e Maxtermos

### 4.1 Definições

**Mintermo (mᵢ):** Um termo-produto que contém **todas** as variáveis (complementadas ou não) e vale 1 para exatamente **uma** combinação de entrada.

**Maxtermo (Mᵢ):** Um termo-soma que contém **todas** as variáveis (complementadas ou não) e vale 0 para exatamente **uma** combinação de entrada.

### 4.2 Tabela Completa para 3 Variáveis

| Linha | A | B | C | Mintermo (mᵢ) | Maxtermo (Mᵢ) |
|-------|---|---|---|----------------|----------------|
| 0 | 0 | 0 | 0 | m₀ = Ā·B̄·C̄ | M₀ = A+B+C |
| 1 | 0 | 0 | 1 | m₁ = Ā·B̄·C | M₁ = A+B+C̄ |
| 2 | 0 | 1 | 0 | m₂ = Ā·B·C̄ | M₂ = A+B̄+C |
| 3 | 0 | 1 | 1 | m₃ = Ā·B·C | M₃ = A+B̄+C̄ |
| 4 | 1 | 0 | 0 | m₄ = A·B̄·C̄ | M₄ = Ā+B+C |
| 5 | 1 | 0 | 1 | m₅ = A·B̄·C | M₅ = Ā+B+C̄ |
| 6 | 1 | 1 | 0 | m₆ = A·B·C̄ | M₆ = Ā+B̄+C |
| 7 | 1 | 1 | 1 | m₇ = A·B·C | M₇ = Ā+B̄+C̄ |

### 4.3 Relação entre Mintermo e Maxtermo

Observe que o mintermo e o maxtermo de uma mesma linha são **complementares**:

```
    mᵢ = M̄ᵢ       e       Mᵢ = m̄ᵢ

    Exemplo:
    m₃ = Ā·B·C         →  m̄₃ = NOT(Ā·B·C) = A + B̄ + C̄ = M₃  ✓
```

> 💡 Isso é consequência direta do **Teorema de De Morgan**!

---

## 5. 📜 Formas Canônicas: SOP e POS

### 5.1 Forma Canônica SOP (Soma Canônica de Mintermos)

A expressão é escrita como a **soma (OR) de todos os mintermos** onde a saída é 1:

```
    S(A,B,C) = Σm(1, 3, 4, 6)
             = m₁ + m₃ + m₄ + m₆
             = Ā·B̄·C + Ā·B·C + A·B̄·C̄ + A·B·C̄
```

A notação **Σm(...)** lista os **índices** dos mintermos.

### 5.2 Forma Canônica POS (Produto Canônico de Maxtermos)

A expressão é escrita como o **produto (AND) de todos os maxtermos** onde a saída é 0:

```
    S(A,B,C) = ΠM(0, 2, 5, 7)
             = M₀ · M₂ · M₅ · M₇
             = (A+B+C) · (A+B̄+C) · (Ā+B+C̄) · (Ā+B̄+C̄)
```

A notação **ΠM(...)** lista os **índices** dos maxtermos.

### 5.3 Relação entre SOP e POS Canônicos

Para uma mesma função, os índices dos mintermos e maxtermos são **complementares**:

```
    Se S = Σm(1, 3, 4, 6)      → índices com saída 1
    Então S = ΠM(0, 2, 5, 7)   → índices com saída 0

    Juntos: {1,3,4,6} ∪ {0,2,5,7} = {0,1,2,3,4,5,6,7} = todas as linhas ✓
```

### 5.4 Exemplo Completo

Dada a tabela verdade:

| A | B | S |
|---|---|---|
| 0 | 0 | 1 |
| 0 | 1 | 0 |
| 1 | 0 | 1 |
| 1 | 1 | 1 |

**SOP canônica:** `S = Σm(0, 2, 3) = Ā·B̄ + A·B̄ + A·B`

**POS canônica:** `S = ΠM(1) = (A + B̄)`

Neste caso, a forma POS é muito mais simples! Apenas 1 maxtermo vs 3 mintermos.

---

## 6. 🔌 Conversão: Tabela Verdade → Circuito

O caminho da tabela verdade ao circuito passa pela expressão:

```
    ┌─────────────┐          ┌────────────┐          ┌──────────┐
    │   TABELA    │  ──────► │  EXPRESSÃO │  ──────► │ CIRCUITO │
    │   VERDADE   │  SOP/POS │  BOOLEANA  │  portas  │  LÓGICO  │
    └─────────────┘          └────────────┘          └──────────┘
```

### 6.1 Exemplo: Da Tabela ao Circuito

**Tabela verdade:**

| A | B | S |
|---|---|---|
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 0 |

**Passo 1 — Expressão SOP:** `S = Ā·B + A·B̄`

**Passo 2 — Circuito:**

```
    A ──[NOT]──┐
               │AND├──┐
    B ─────────┘      │
                      │OR├──── S
    A ─────────┐      │
               │AND├──┘
    B ──[NOT]──┘
```

**Ou reconhecendo que é XOR:**

```
    A ───┐
         │XOR├──── S
    B ───┘
```

> 💡 Sempre verifique se a expressão pode ser simplificada antes de construir o circuito!

---

## 7. 🔍 Conversão: Circuito → Tabela Verdade

### 7.1 Método Direto

```
    ALGORITMO: Circuito → Tabela Verdade
    ──────────────────────────────────────
    1. Identifique as variáveis de entrada e a saída
    2. Extraia a expressão booleana do circuito (porta a porta)
    3. Construa a tabela com 2ⁿ linhas (n = número de variáveis)
    4. Avalie a expressão para cada combinação de entrada
```

### 7.2 Exemplo

**Circuito dado:**

```
    A ───┐
         │NAND├──┐
    B ───┘       │
                 │AND├──── S
    C ───────────┘
```

**Passo 1 — Expressão:** O NAND de A e B gera `(A·B)̄`, que é ligado ao AND com C:

```
    S = (A·B)̄ · C
```

**Passo 2 — Tabela Verdade:**

| A | B | C | A·B | (A·B)̄ | S = (A·B)̄ · C |
|---|---|---|-----|--------|----------------|
| 0 | 0 | 0 | 0 | 1 | 0 |
| 0 | 0 | 1 | 0 | 1 | 1 |
| 0 | 1 | 0 | 0 | 1 | 0 |
| 0 | 1 | 1 | 0 | 1 | 1 |
| 1 | 0 | 0 | 0 | 1 | 0 |
| 1 | 0 | 1 | 0 | 1 | 1 |
| 1 | 1 | 0 | 1 | 0 | 0 |
| 1 | 1 | 1 | 1 | 0 | 0 |

**Forma canônica:** `S = Σm(1, 3, 5)` — saída 1 quando C=1 e pelo menos um de A ou B é 0.

### 7.3 Método Alternativo: Avaliação Direta

Quando o circuito é complexo, pode ser mais fácil avaliar o circuito **diretamente** para cada combinação de entrada, propagando os sinais porta a porta, sem extrair a expressão completa:

```
    Para cada combinação de A, B, C:
        1. Atribua os valores nas entradas
        2. Calcule a saída de cada porta (da esquerda para a direita)
        3. O valor da última porta é a saída S
```

---

## 8. 📊 Mapa de Karnaugh para 3 Variáveis

### 8.1 Estrutura do Mapa de 3 Variáveis

O Mapa de Karnaugh (K-Map) é uma forma visual de simplificar expressões:

```
                  BC
               00   01   11   10
            ┌─────┬─────┬─────┬─────┐
     A   0  │ m₀  │ m₁  │ m₃  │ m₂  │
            ├─────┼─────┼─────┼─────┤
         1  │ m₄  │ m₅  │ m₇  │ m₆  │
            └─────┴─────┴─────┴─────┘
```

> ⚠️ A ordem das colunas é **00, 01, 11, 10** (código Gray) — apenas 1 bit muda entre colunas adjacentes!

### 8.2 Regras de Agrupamento

```
    REGRAS DO MAPA DE KARNAUGH
    ──────────────────────────
    1. Agrupe apenas células com valor 1
    2. Grupos devem ter tamanho potência de 2 (1, 2, 4, 8)
    3. Grupos devem ser retangulares (horizontal ou vertical)
    4. Quanto MAIOR o grupo, MAIS SIMPLES o termo resultante
    5. As BORDAS são adjacentes (o mapa "enrola" como um cilindro)
    6. Cada célula com 1 deve pertencer a pelo menos um grupo
    7. Grupos podem se sobrepor
    8. Use o MENOR número de grupos possível
```

### 8.3 Como Ler os Grupos

Quando você identifica um grupo, observe quais variáveis **permanecem constantes** dentro do grupo:

| Tamanho do grupo | Variáveis eliminadas | Variáveis no termo |
|-------------------|----------------------|---------------------|
| 1 célula | 0 | 3 variáveis (mintermo completo) |
| 2 células | 1 | 2 variáveis |
| 4 células | 2 | 1 variável |
| 8 células | 3 | 0 variáveis → S = 1 |

### 8.4 Exemplo Detalhado: S(A,B,C) = Σm(0, 2, 4, 5, 6)

**Passo 1 — Preencher o mapa:**

```
                  BC
               00   01   11   10
            ┌─────┬─────┬─────┬─────┐
     A   0  │  1  │  0  │  0  │  1  │
            ├─────┼─────┼─────┼─────┤
         1  │  1  │  1  │  0  │  1  │
            └─────┴─────┴─────┴─────┘
```

**Passo 2 — Identificar grupos:**

```
                  BC
               00   01   11   10
            ╔═════╗─────┬─────╔═════╗
     A   0  ║  1  ║  0  │  0  ║  1  ║  ← Grupo 1 (bordas!)
            ╠═════╬─────┼─────╠═════╣
         1  ║  1  ║  1  │  0  ║  1  ║  ← Grupo 2
            ╚═════╩─────┴─────╚═════╝
```

- **Grupo 1** (4 células: m₀, m₂, m₄, m₆): colunas BC=00 e BC=10 → B̄ é constante → **C̄**

  Espera! Vamos verificar: m₀(000), m₂(010), m₄(100), m₆(110). C é sempre 0. B varia. A varia. → Termo: **C̄**

- **Grupo 2** (2 células: m₄, m₅): A=1, B=0 → **A · B̄**

**Resultado:** `S = C̄ + A·B̄`

**Verificação:**

| A | B | C | C̄ | A·B̄ | S = C̄ + A·B̄ | Σm esperado |
|---|---|---|----|----|-------------|-------------|
| 0 | 0 | 0 | 1 | 0 | 1 | 1 ✓ |
| 0 | 0 | 1 | 0 | 0 | 0 | 0 ✓ |
| 0 | 1 | 0 | 1 | 0 | 1 | — |
| 0 | 1 | 1 | 0 | 0 | 0 | 0 ✓ |
| 1 | 0 | 0 | 1 | 1 | 1 | 1 ✓ |
| 1 | 0 | 1 | 0 | 1 | 1 | 1 ✓ |
| 1 | 1 | 0 | 1 | 0 | 1 | 1 ✓ |
| 1 | 1 | 1 | 0 | 0 | 0 | 0 ✓ |

Espere, a linha 2 (A=0,B=1,C=0) dá S=1, que é m₂ e está em Σm(0,2,4,5,6). Correto! ✓

---

## 9. 📊 Mapa de Karnaugh para 4 Variáveis

### 9.1 Estrutura do Mapa de 4 Variáveis

```
                     CD
                 00    01    11    10
              ┌──────┬──────┬──────┬──────┐
    AB  00    │  m₀  │  m₁  │  m₃  │  m₂  │
              ├──────┼──────┼──────┼──────┤
        01    │  m₄  │  m₅  │  m₇  │  m₆  │
              ├──────┼──────┼──────┼──────┤
        11    │ m₁₂  │ m₁₃  │ m₁₅  │ m₁₄  │
              ├──────┼──────┼──────┼──────┤
        10    │  m₈  │  m₉  │ m₁₁  │ m₁₀  │
              └──────┴──────┴──────┴──────┘
```

> ⚠️ Tanto linhas quanto colunas usam **código Gray**: 00, 01, 11, 10. E as bordas superior/inferior e esquerda/direita são adjacentes!

### 9.2 Adjacências no Mapa 4x4

```
    As seguintes células SÃO adjacentes (mapa "enrola" nos dois eixos):
    
    Horizontal: m₀↔m₂, m₁↔m₃, m₄↔m₆, etc.
    Vertical:   m₀↔m₈, m₁↔m₉, m₂↔m₁₀, etc.
    
    Os 4 cantos (m₀, m₂, m₈, m₁₀) formam um grupo válido!
```

### 9.3 Tamanhos de Grupo para 4 Variáveis

| Tamanho | Variáveis eliminadas | Variáveis restantes | Exemplo |
|---------|----------------------|---------------------|---------|
| 1 | 0 | 4 | A·B·C·D (mintermo) |
| 2 | 1 | 3 | A·B·C |
| 4 | 2 | 2 | A·B |
| 8 | 3 | 1 | A |
| 16 | 4 | 0 | S = 1 |

### 9.4 Exemplo Detalhado: S(A,B,C,D) = Σm(0, 1, 2, 5, 8, 9, 10)

**Passo 1 — Preencher o mapa:**

```
                     CD
                 00    01    11    10
              ┌──────┬──────┬──────┬──────┐
    AB  00    │  1   │  1   │  0   │  1   │
              ├──────┼──────┼──────┼──────┤
        01    │  0   │  1   │  0   │  0   │
              ├──────┼──────┼──────┼──────┤
        11    │  0   │  0   │  0   │  0   │
              ├──────┼──────┼──────┼──────┤
        10    │  1   │  1   │  0   │  1   │
              └──────┴──────┴──────┴──────┘
```

**Passo 2 — Identificar grupos:**

- **Grupo 1** (4 células: m₀, m₂, m₈, m₁₀): cantos esquerdo → B̄·D̄
- **Grupo 2** (2 células: m₀, m₁): AB=00, CD=00 e CD=01 → Ā·B̄·D̄... Espere, vamos repensar.

Vamos reorganizar:

- **Grupo 1** (4 células: m₀, m₂, m₈, m₁₀): linhas AB=00 e AB=10, colunas CD=00 e CD=10. B=0 constante, D=0 constante → **B̄·D̄**
- **Grupo 2** (2 células: m₀, m₁): AB=00, CD=00 e CD=01 → A=0, B=0, C=0 → **Ā·B̄·C̄**
- **Grupo 3** (2 células: m₁, m₅): CD=01, AB=00 e AB=01 → A=0, C=0, D=1 → **Ā·C̄·D**
- **Grupo 4** (2 células: m₈, m₉): AB=10, CD=00 e CD=01 → A=1, B=0, C=0 → **A·B̄·C̄**

Mas o Grupo 2 e Grupo 4 podem ser combinados com o Grupo 1. Vamos otimizar:

- **Grupo 1** (4 células: m₀, m₂, m₈, m₁₀): **B̄·D̄**
- **Grupo 2** (2 células: m₀, m₁ e m₈, m₉) — na verdade, m₀, m₁, m₈, m₉ formam um grupo de 4! AB=00 e AB=10, CD=00 e CD=01. B=0 constante, C=0 constante → **B̄·C̄**
- **Grupo 3** (2 células: m₁, m₅): **Ā·C̄·D**

Verificando cobertura: m₀ (✓ G1, G2), m₁ (✓ G2, G3), m₂ (✓ G1), m₅ (✓ G3), m₈ (✓ G1, G2), m₉ (✓ G2), m₁₀ (✓ G1) ✓

**Resultado:** `S = B̄·D̄ + B̄·C̄ + Ā·C̄·D`

Podemos simplificar mais: `S = B̄·D̄ + C̄·(B̄ + Ā·D)` — mas a forma SOP já é adequada para implementação em 2 níveis.

---

## 10. ❓ Condições Don't-Care

### 10.1 O que São?

Em muitos projetos reais, certas combinações de entrada **nunca ocorrem** ou **não importa** qual seja a saída. Essas são chamadas de **condições don't-care** e são representadas por **X** ou **d** na tabela verdade.

### 10.2 Por que São Úteis?

O projetista pode escolher se a saída é 0 ou 1 para essas condições, aproveitando para **aumentar os agrupamentos** no Mapa de Karnaugh e obter expressões mais simples!

### 10.3 Exemplo: Display BCD

No sistema BCD (Binary Coded Decimal), 4 bits representam dígitos de 0 a 9. As combinações de 10 a 15 **nunca ocorrem**:

| Decimal | A | B | C | D | S | Observação |
|---------|---|---|---|---|---|------------|
| 0 | 0 | 0 | 0 | 0 | 1 | |
| 1 | 0 | 0 | 0 | 1 | 0 | |
| 2 | 0 | 0 | 1 | 0 | 0 | |
| 3 | 0 | 0 | 1 | 1 | 0 | |
| 4 | 0 | 1 | 0 | 0 | 0 | |
| 5 | 0 | 1 | 0 | 1 | 1 | |
| 6 | 0 | 1 | 1 | 0 | 1 | |
| 7 | 0 | 1 | 1 | 1 | 1 | |
| 8 | 1 | 0 | 0 | 0 | 1 | |
| 9 | 1 | 0 | 0 | 1 | 1 | |
| 10 | 1 | 0 | 1 | 0 | X | Don't-care |
| 11 | 1 | 0 | 1 | 1 | X | Don't-care |
| 12 | 1 | 1 | 0 | 0 | X | Don't-care |
| 13 | 1 | 1 | 0 | 1 | X | Don't-care |
| 14 | 1 | 1 | 1 | 0 | X | Don't-care |
| 15 | 1 | 1 | 1 | 1 | X | Don't-care |

**Mapa de Karnaugh com don't-cares:**

```
                     CD
                 00    01    11    10
              ┌──────┬──────┬──────┬──────┐
    AB  00    │  1   │  0   │  0   │  0   │
              ├──────┼──────┼──────┼──────┤
        01    │  0   │  1   │  1   │  1   │
              ├──────┼──────┼──────┼──────┤
        11    │  X   │  X   │  X   │  X   │
              ├──────┼──────┼──────┼──────┤
        10    │  1   │  1   │  X   │  X   │
              └──────┴──────┴──────┴──────┘
```

**Estratégia:** Trate os X como 1 quando isso ajudar a formar grupos maiores, e como 0 caso contrário.

- **Grupo 1** (8 células: m₅,m₇,m₆,m₁₃,m₁₅,m₁₄,m₉,m₁₁): toda a metade B=1 e A=1 → **B + A** — vamos verificar: inclui don't-cares. Considerando como 1: m₄(B=1,CD=00)=0... Reformulando:

Agrupamentos possíveis tratando X como 1:
- **Grupo 1** (4 células: m₅, m₇, m₁₃, m₁₅): B·D → **B·D**
- **Grupo 2** (4 células: m₆, m₇, m₁₄, m₁₅): B·C → **B·C**
- **Grupo 3** (4 células: m₈, m₉, m₁₀, m₁₁): A·B̄ → **A**  (incluindo d₁₀ e d₁₁ como 1, e m₈=1, m₉=1)
- **Grupo 4** (1 célula: m₀): Ā·B̄·C̄·D̄

Mas m₀ pode agrupar com m₈ (=1) e d₁₂, d₄... m₄=0. Então m₀ e m₈ formam par se d₁₂=X. Grupo: m₀, m₈, d₁₂, m₄? m₄=0, não pode. m₀ e m₈: AB=00 e 10, CD=00 → B̄·C̄·D̄.

**Resultado:** `S = B·D + B·C + A + B̄·C̄·D̄`

Simplificando: `S = A + B·C + B·D + B̄·C̄·D̄`

> 💡 **Sem don't-cares**, a expressão seria significativamente mais complexa. As condições don't-care são um presente para o projetista!

---

## 11. 🛠️ Metodologia de Projeto

### 11.1 Fluxo Completo de Projeto Digital

```
    ┌─────────────────────────────────────────────────────────────┐
    │                METODOLOGIA DE PROJETO                       │
    ├─────────────────────────────────────────────────────────────┤
    │                                                             │
    │  1. ESPECIFICAÇÃO                                           │
    │     └─ Descreva o problema em linguagem natural             │
    │                                                             │
    │  2. TABELA VERDADE                                          │
    │     └─ Liste todas as combinações de entrada e saída        │
    │                                                             │
    │  3. EXPRESSÃO CANÔNICA                                      │
    │     └─ Derive SOP ou POS a partir da tabela                 │
    │                                                             │
    │  4. SIMPLIFICAÇÃO                                           │
    │     └─ Use Karnaugh (ou álgebra) para minimizar             │
    │                                                             │
    │  5. CIRCUITO                                                │
    │     └─ Construa o circuito a partir da expressão simples    │
    │                                                             │
    │  6. VERIFICAÇÃO                                             │
    │     └─ Confira a tabela verdade do circuito final           │
    │                                                             │
    └─────────────────────────────────────────────────────────────┘
```

### 11.2 Exemplo Completo: Sistema de Votação

**Especificação:** Em um comitê de 3 pessoas (A, B, C), uma decisão é aprovada por **maioria simples** (2 ou mais votos a favor).

**Passo 1 — Tabela Verdade:**

| A | B | C | S (aprovado) |
|---|---|---|-------------|
| 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 0 |
| 0 | 1 | 0 | 0 |
| 0 | 1 | 1 | 1 |
| 1 | 0 | 0 | 0 |
| 1 | 0 | 1 | 1 |
| 1 | 1 | 0 | 1 |
| 1 | 1 | 1 | 1 |

**Passo 2 — Expressão Canônica SOP:**

`S = Σm(3, 5, 6, 7) = Ā·B·C + A·B̄·C + A·B·C̄ + A·B·C`

**Passo 3 — Mapa de Karnaugh:**

```
                  BC
               00   01   11   10
            ┌─────┬─────┬─────┬─────┐
     A   0  │  0  │  0  │  1  │  0  │
            ├─────┼─────┼─────┼─────┤
         1  │  0  │  1  │  1  │  1  │
            └─────┴─────┴─────┴─────┘

    Grupo 1: m₃, m₇ → B·C
    Grupo 2: m₅, m₇ → A·C
    Grupo 3: m₆, m₇ → A·B
```

**Passo 4 — Expressão Simplificada:**

`S = A·B + A·C + B·C`

**Passo 5 — Circuito:**

```
    A ───┐
         │AND├──┐
    B ───┘      │
                │
    A ───┐      │OR├──── S
         │AND├──┤ (3 entradas)
    C ───┘      │
                │
    B ───┐      │
         │AND├──┘
    C ───┘
```

**Passo 6 — Verificação:** Compare a tabela verdade do circuito com a original ✓

> 💡 Este circuito é chamado de **circuito de maioria** e é um bloco fundamental em muitos sistemas digitais!

---

## 12. 📌 Resumo

| Conceito | Resumo |
|----------|--------|
| **Três representações** | Circuito, tabela verdade e expressão são equivalentes |
| **SOP** | Soma de mintermos (linhas com saída = 1) |
| **POS** | Produto de maxtermos (linhas com saída = 0) |
| **Mintermo (mᵢ)** | Termo-produto com todas as variáveis, vale 1 em uma linha |
| **Maxtermo (Mᵢ)** | Termo-soma com todas as variáveis, vale 0 em uma linha |
| **Forma canônica** | Expressão com todos os mintermos ou maxtermos |
| **K-Map 3 variáveis** | 2×4 células, código Gray nas colunas |
| **K-Map 4 variáveis** | 4×4 células, código Gray em ambos os eixos |
| **Don't-care** | Condições irrelevantes que ajudam a simplificar |
| **Metodologia** | Especificação → Tabela → Expressão → Karnaugh → Circuito → Verificação |

> 🧠 **Mensagem principal:** Dominar a conversão entre as três representações é a habilidade mais importante no projeto de circuitos digitais. O Mapa de Karnaugh é sua ferramenta mais poderosa para simplificação — pratique até que se torne automático!

---

## 13. 📚 Leitura Complementar

- 📖 TOCCI, R. J.; WIDMER, N. S. **Sistemas Digitais: Princípios e Aplicações**. Cap. 4 — Circuitos Lógicos Combinacionais.
- 📖 FLOYD, T. L. **Sistemas Digitais: Fundamentos e Aplicações**. Cap. 4 e 5 — Álgebra Booleana e Mapas de Karnaugh.
- 📖 MANO, M. M. **Digital Logic and Computer Design**. Cap. 3 — Simplificação de Funções Booleanas.
- 📖 TANENBAUM, A. S. **Organização Estruturada de Computadores**. Cap. 3 — Nível Lógico Digital.
- 🌐 [Karnaugh Map Solver Online](https://www.charlie-coleman.com/experiments/kmap/)
- 🌐 [CircuitVerse — Monte seus circuitos](https://circuitverse.org/)

---

> ⬅️ [Aula 08 — Expressões e Circuitos](../08-portas-logicas-expressoes-booleanas-circuitos/README.md) | [Exemplos](./exemplos/README.md) | [Exercícios](./exercicios/README.md) ➡️
