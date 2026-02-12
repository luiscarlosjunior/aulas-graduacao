# 🔄 Aula 08 — Funções e Portas Lógicas: Expressões Booleanas de Circuitos e Circuitos de Expressões

> **Disciplina:** Organização de Computadores  
> **Duração:** 2 horas-aula  
> **Nível:** Intermediário  
> **Pré-requisitos:** Aula 07 — Portas Lógicas, Tabelas Verdade e Expressões Booleanas

---

## 🎯 Objetivos de Aprendizado

Ao final desta aula, o estudante será capaz de:

- ✅ Extrair a **expressão booleana** de um circuito lógico (circuito → expressão)
- ✅ Construir um **circuito lógico** a partir de uma expressão booleana (expressão → circuito)
- ✅ Simplificar expressões usando **álgebra booleana**
- ✅ Simplificar expressões usando **Mapa de Karnaugh** (2 e 3 variáveis)
- ✅ Verificar a **equivalência** entre dois circuitos
- ✅ Explicar por que **simplificação importa** (custo, velocidade, consumo de energia)

---

## 📋 Sumário

1. [De Circuito para Expressão Booleana](#1--de-circuito-para-expressão-booleana)
2. [De Expressão Booleana para Circuito](#2--de-expressão-booleana-para-circuito)
3. [Simplificação por Álgebra Booleana](#3--simplificação-por-álgebra-booleana)
4. [Simplificação por Mapa de Karnaugh](#4--simplificação-por-mapa-de-karnaugh)
5. [Equivalência entre Circuitos](#5--equivalência-entre-circuitos)
6. [Circuitos com Múltiplos Níveis de Portas](#6--circuitos-com-múltiplos-níveis-de-portas)
7. [Por que Simplificar Importa?](#7--por-que-simplificar-importa)
8. [Aplicações Práticas](#8--aplicações-práticas)
9. [Resumo](#9--resumo)
10. [Leitura Complementar](#10--leitura-complementar)

---

## 1. 🔍 De Circuito para Expressão Booleana

### 1.1 Método Passo a Passo

Para encontrar a expressão booleana de um circuito:

```
    ALGORITMO: Circuito → Expressão
    ─────────────────────────────────
    1. Identifique as ENTRADAS (variáveis: A, B, C...)
    2. Comece pelas portas mais PRÓXIMAS das entradas
    3. Escreva a expressão de CADA porta intermediária
    4. Substitua progressivamente até chegar à SAÍDA
    5. A expressão da última porta = expressão do circuito
```

### 1.2 Exemplo 1: Circuito Simples

```
    A ───┐
         │AND├──┐
    B ───┘      │
                │OR├──── S
    C ──────────┘
```

**Passo a passo:**

| Passo | Porta | Entradas | Expressão |
|-------|-------|----------|-----------|
| 1 | AND | A, B | P₁ = A · B |
| 2 | OR | P₁, C | S = P₁ + C = **A · B + C** |

**Resultado:** `S = A · B + C`

### 1.3 Exemplo 2: Circuito com NOT

```
    A ──[NOT]──┐
               │AND├──── S
    B ─────────┘
```

| Passo | Porta | Entradas | Expressão |
|-------|-------|----------|-----------|
| 1 | NOT | A | P₁ = Ā |
| 2 | AND | P₁, B | S = Ā · B |

**Resultado:** `S = Ā · B`

### 1.4 Exemplo 3: Circuito com Múltiplas Portas

```
    A ───┐
         │AND├──┐
    B ───┘      │
                │OR├──── S
    A ───┐      │
         │AND├──┘
    C ───┘
```

| Passo | Porta | Entradas | Expressão |
|-------|-------|----------|-----------|
| 1 | AND₁ | A, B | P₁ = A · B |
| 2 | AND₂ | A, C | P₂ = A · C |
| 3 | OR | P₁, P₂ | S = A·B + A·C |

**Resultado:** `S = A · B + A · C` → Simplificando: `S = A · (B + C)`

### 1.5 Exemplo 4: Circuito com 3 Níveis

```
    A ──[NOT]──┐
               │OR├──┐
    B ─────────┘     │
                     │AND├──── S
    C ──[NOT]──┐     │
               │OR├──┘
    D ─────────┘
```

| Passo | Porta | Entradas | Expressão |
|-------|-------|----------|-----------|
| 1 | NOT₁ | A | P₁ = Ā |
| 2 | NOT₂ | C | P₂ = C̄ |
| 3 | OR₁ | P₁, B | P₃ = Ā + B |
| 4 | OR₂ | P₂, D | P₄ = C̄ + D |
| 5 | AND | P₃, P₄ | S = (Ā + B) · (C̄ + D) |

**Resultado:** `S = (Ā + B) · (C̄ + D)`

---

## 2. 🏗️ De Expressão Booleana para Circuito

### 2.1 Método Passo a Passo

```
    ALGORITMO: Expressão → Circuito
    ─────────────────────────────────
    1. Identifique a OPERAÇÃO PRINCIPAL (último nível de precedência)
    2. Divida a expressão nos OPERANDOS dessa operação
    3. Para cada operando, repita recursivamente:
       - Se é uma variável simples → é uma entrada
       - Se é uma variável negada → adicione porta NOT
       - Se é uma sub-expressão → crie a porta correspondente
    4. Conecte tudo da esquerda (entradas) para a direita (saída)
```

### 2.2 Exemplo 1: S = A · B + C

```
    Operação principal: OR (entre A·B e C)
    Operando 1: A · B (porta AND)
    Operando 2: C (entrada direta)

    Circuito:
    A ───┐
         │AND├──┐
    B ───┘      │
                │OR├──── S
    C ──────────┘
```

### 2.3 Exemplo 2: S = (A + B) · C̄

```
    Operação principal: AND (entre (A+B) e C̄)
    Operando 1: A + B (porta OR)
    Operando 2: C̄ (porta NOT em C)

    Circuito:
    A ───┐
         │OR├───┐
    B ───┘      │
                │AND├──── S
    C ──[NOT]───┘
```

### 2.4 Exemplo 3: S = Ā · B + A · B̄ (XOR)

```
    Operação principal: OR
    Operando 1: Ā · B (NOT em A, depois AND com B)
    Operando 2: A · B̄ (A com NOT em B, depois AND)

    Circuito:
    A ──[NOT]──┐
               │AND├──┐
    B ─────────┘      │
                      │OR├──── S
    A ─────────┐      │
               │AND├──┘
    B ──[NOT]──┘
    
    Portas: 2 NOT + 2 AND + 1 OR = 5 portas
```

### 2.5 Exemplo 4: S = A · B · C + Ā · B̄ · C̄

```
    Operação principal: OR
    Operando 1: A · B · C (AND de 3 entradas)
    Operando 2: Ā · B̄ · C̄ (NOT em cada, depois AND de 3)

    Circuito:
    A ────────────┐
    B ────────────│AND3├──┐
    C ────────────┘       │
                          │OR├──── S
    A ──[NOT]──┐          │
    B ──[NOT]──│AND3├─────┘
    C ──[NOT]──┘
    
    Portas: 3 NOT + 2 AND(3 entradas) + 1 OR = 6 portas
```

---

## 3. 📐 Simplificação por Álgebra Booleana

### 3.1 Por que Simplificar?

A mesma função lógica pode ser implementada por expressões diferentes. A mais **simples** usa **menos portas** → circuito menor, mais barato, mais rápido!

### 3.2 Exemplo 1: S = A·B + A·C + A·B̄·C

```
    PASSO 1: Fatorar A dos dois primeiros termos
    S = A·(B + C) + A·B̄·C

    PASSO 2: Fatorar A
    S = A·(B + C + B̄·C)

    PASSO 3: Dentro do parênteses, usar absorção (B + B̄·C = B + C)
    Nota: X + X̄·Y = X + Y
    B + C + B̄·C = B + C + C = B + C   (pois C + C = C pela idempotência)
    
    Mas vamos verificar de outra forma:
    S = A·B + A·C + A·B̄·C
    S = A·B + A·C·(1 + B̄)    (fatorando A·C... na verdade, não)

    Vamos usar outro caminho:
    S = A·B + A·C + A·B̄·C
    S = A·B + A·C(1) + A·B̄·C   (já temos A·C, e A·B̄·C está contido em A·C? Não...)
    
    Verificando: A·C absorve A·B̄·C (pois A·C inclui tanto B=0 quanto B=1)
    Pelo teorema de absorção: X + X·Y = X, onde X = A·C e Y = B̄
    A·C + A·B̄·C = A·C

    S = A·B + A·C

    PASSO final: Fatorar A
    S = A·(B + C)
```

**Resultado:** `S = A · B + A · C + A · B̄ · C = A · (B + C)`

| Original | Simplificada |
|----------|-------------|
| 3 AND + 1 OR + 1 NOT | 1 AND + 1 OR |
| 5 portas | 2 portas |

### 3.3 Exemplo 2: S = Ā·B̄ + Ā·B + A·B̄ + A·B

```
    PASSO 1: Agrupar
    S = Ā·(B̄ + B) + A·(B̄ + B)

    PASSO 2: Complemento: B̄ + B = 1
    S = Ā·1 + A·1

    PASSO 3: Identidade: X·1 = X
    S = Ā + A

    PASSO 4: Complemento
    S = 1
```

A expressão original, que parece complexa, é na verdade **sempre verdadeira**!

### 3.4 Exemplo 3: S = A·B + Ā·C + B·C (Consenso)

```
    Pelo Teorema do Consenso: A·B + Ā·C + B·C = A·B + Ā·C
    
    O terceiro termo (B·C) é REDUNDANTE!
    
    Verificação: Quando B·C = 1 (B=1 e C=1):
    - Se A=1: A·B = 1·1 = 1 (primeiro termo já cobre)
    - Se A=0: Ā·C = 1·1 = 1 (segundo termo já cobre)
    
    S = A·B + Ā·C
```

---

## 4. 🗺️ Simplificação por Mapa de Karnaugh

### 4.1 O Que É o Mapa de Karnaugh?

O **Mapa de Karnaugh** (ou Mapa K) é um método **visual e sistemático** para simplificar expressões booleanas. Em vez de aplicar leis algébricas (que exigem criatividade), basta seguir um procedimento gráfico!

> 💡 **Analogia:** Imagine um jogo de "encontre os pares" — você agrupa 1s vizinhos no mapa e cada grupo elimina uma variável da expressão!

### 4.2 Mapa de Karnaugh para 2 Variáveis

**Estrutura do mapa:**

```
            B
          0   1
       ┌─────┬─────┐
    A 0│  m₀ │  m₁ │     m₀ = Ā·B̄    m₁ = Ā·B
       ├─────┼─────┤
      1│  m₂ │  m₃ │     m₂ = A·B̄    m₃ = A·B
       └─────┴─────┘
```

**Exemplo: S = Ā·B + A·B**

```
            B
          0   1
       ┌─────┬─────┐
    A 0│  0  │  1  │
       ├─────┼─────┤
      1│  0  │  1  │
       └─────┴─────┘
```

Agrupando os dois 1s na coluna B=1: **S = B** ✓

### 4.3 Mapa de Karnaugh para 3 Variáveis

**Estrutura do mapa (atenção: BC usa código Gray!):**

```
              BC
           00  01  11  10
        ┌────┬────┬────┬────┐
    A  0│ m₀ │ m₁ │ m₃ │ m₂ │
        ├────┼────┼────┼────┤
       1│ m₄ │ m₅ │ m₇ │ m₆ │
        └────┴────┴────┴────┘
```

> ⚠️ **IMPORTANTE:** A ordem das colunas é **00, 01, 11, 10** (código Gray), não 00, 01, 10, 11! Isso garante que células adjacentes diferem em **apenas 1 bit**.

### 4.4 Regras para Agrupar

```
    REGRAS DO MAPA DE KARNAUGH
    ─────────────────────────────
    1. Agrupe apenas 1s
    2. Grupos devem ter tamanho potência de 2 (1, 2, 4, 8...)
    3. Grupos devem ser RETANGULARES
    4. Grupos podem "envolver" as bordas (topo↔fundo, esquerda↔direita)
    5. Faça os MAIORES grupos possíveis
    6. Todo 1 deve pertencer a pelo menos um grupo
    7. Grupos podem se sobrepor
    8. Quanto MAIOR o grupo, MAIS variáveis são eliminadas
```

| Tamanho do Grupo | Variáveis Eliminadas |
|-------------------|---------------------|
| 1 célula | 0 (mantém todas) |
| 2 células | 1 variável eliminada |
| 4 células | 2 variáveis eliminadas |
| 8 células | 3 variáveis eliminadas |

### 4.5 Exemplo Completo com 3 Variáveis

**Função:** S(A,B,C) = Σm(1, 3, 5, 7) (1 nos mintermos 1, 3, 5, 7)

**Passo 1: Preencher o mapa**

```
              BC
           00  01  11  10
        ┌────┬────┬────┬────┐
    A  0│  0 │  1 │  1 │  0 │
        ├────┼────┼────┼────┤
       1│  0 │  1 │  1 │  0 │
        └────┴────┴────┴────┘
```

**Passo 2: Identificar grupos**

```
              BC
           00  01  11  10
        ┌────┬════╤════┬────┐
    A  0│  0 ║  1 │  1 ║  0 │   ← Grupo de 4:
        ├────╫────┼────╫────┤      colunas 01 e 11
       1│  0 ║  1 │  1 ║  0 │
        └────╨════╧════╨────┘
```

**Passo 3: Ler a expressão**

O grupo cobre todas as linhas de A (A=0 e A=1) → A é eliminado.
O grupo cobre BC=01 e BC=11 → B varia, C é sempre 1 → B é eliminado.
Variável restante: **C = 1** em todo o grupo.

**Resultado:** `S = C`

**Verificação:** Nos mintermos 1, 3, 5, 7, C é sempre 1 ✓

### 4.6 Outro Exemplo com 3 Variáveis

**Função:** S(A,B,C) = Σm(0, 2, 4, 5, 6)

**Preencher o mapa:**

```
              BC
           00  01  11  10
        ┌────┬────┬────┬────┐
    A  0│  1 │  0 │  0 │  1 │
        ├────┼────┼────┼────┤
       1│  1 │  1 │  0 │  1 │
        └────┴────┴────┴────┘
```

**Identificar grupos:**

```
    Grupo 1 (4 células): m₀, m₂, m₄, m₆ (colunas 00 e 10)
              BC
           00  01  11  10
        ╔════╤────┬────╤════╗
    A  0║  1 │  0 │  0 │  1 ║  → C̄ (C=0 em todas)
        ╠════╪────┼────╪════╣
       1║  1 │  1 │  0 │  1 ║
        ╚════╧────┴────╧════╝
    
    Grupo 2 (2 células): m₄, m₅
              BC
           00  01  11  10
        ┌────┬────┬────┬────┐
    A  0│  1 │  0 │  0 │  1 │
        ╠════╪════╡────┼────┤
       1║  1 │  1 ║  0 │  1 │  → A·B̄ (A=1, B=0)
        ╚════╧════╛────┴────┘
```

**Resultado:** `S = C̄ + A · B̄`

---

## 5. ⚖️ Equivalência entre Circuitos

### 5.1 Como Verificar

Dois circuitos são **equivalentes** quando produzem a **mesma saída** para todas as combinações de entrada. Para verificar:

```
    Método 1: Extraia a expressão de cada circuito e simplifique
    Método 2: Monte a tabela verdade de cada um e compare
    Método 3: Simplifique ambos e veja se chegam na mesma forma
```

### 5.2 Exemplo

**Circuito 1:**
```
    A ──[NOT]──┐
               │AND├──┐
    B ─────────┘      │
                      │OR├──── S₁
    A ─────────┐      │
               │AND├──┘
    B ──[NOT]──┘
```
Expressão: `S₁ = Ā·B + A·B̄`

**Circuito 2:**
```
    A ───┐
         │XOR├──── S₂
    B ───┘
```
Expressão: `S₂ = A ⊕ B`

**São equivalentes?** Sim! Como vimos na Aula 07: `A ⊕ B = Ā·B + A·B̄`

| A | B | S₁ = Ā·B + A·B̄ | S₂ = A ⊕ B |
|---|---|-----------------|-----------|
| 0 | 0 | 0 | 0 ✓ |
| 0 | 1 | 1 | 1 ✓ |
| 1 | 0 | 1 | 1 ✓ |
| 1 | 1 | 0 | 0 ✓ |

O Circuito 2 usa **1 porta** em vez de **5**! A simplificação economiza 4 portas.

---

## 6. 🏢 Circuitos com Múltiplos Níveis de Portas

### 6.1 Dois Níveis (SOP e POS)

Os circuitos mais comuns em eletrônica digital têm dois níveis:

**SOP (Sum of Products / Soma de Produtos):**

```
    Nível 1: AND          Nível 2: OR
    
    A ──┐                         
        │AND├──┐                  
    B ──┘      │                  
               │OR├──── S         
    C ──┐      │                  
        │AND├──┘                  
    D ──┘                         
    
    S = A·B + C·D
```

**POS (Product of Sums / Produto de Somas):**

```
    Nível 1: OR           Nível 2: AND
    
    A ──┐
        │OR├───┐
    B ──┘      │
               │AND├──── S
    C ──┐      │
        │OR├───┘
    D ──┘
    
    S = (A+B) · (C+D)
```

### 6.2 Três ou Mais Níveis

Circuitos com mais de 2 níveis podem surgir antes da simplificação:

```
    A ───┐
         │AND├──┐
    B ───┘      │
                │OR├──┐
    C ──────────┘     │
                      │AND├──── S
    D ──[NOT]──┐      │
               │OR├───┘
    E ─────────┘

    Expressão: S = (A·B + C) · (D̄ + E)
```

Leitura: Nível 1 (AND e NOT) → Nível 2 (OR) → Nível 3 (AND final).

> 💡 Cada nível de portas adiciona **atraso de propagação**. Menos níveis = circuito mais rápido!

---

## 7. 💰 Por que Simplificar Importa?

### 7.1 Comparação Prática

| Aspecto | Circuito Original | Circuito Simplificado |
|---------|-------------------|----------------------|
| **Portas lógicas** | Mais portas | Menos portas |
| **Transistores** | Mais transistores | Menos transistores |
| **Custo** | Mais caro | Mais barato |
| **Área no chip** | Maior | Menor |
| **Consumo de energia** | Maior | Menor |
| **Velocidade** | Mais lento (mais níveis) | Mais rápido |
| **Confiabilidade** | Menor (mais componentes) | Maior |

### 7.2 Exemplo Numérico

Considere a expressão `S = A·B·C + A·B·C̄ + A·B̄·C`:

**Sem simplificar:**
- 3 portas AND de 3 entradas
- 1 porta OR de 3 entradas
- 2 portas NOT
- **Total: 6 portas**

**Após simplificar:** `S = A·B + A·C = A·(B + C)`
- 1 porta OR de 2 entradas
- 1 porta AND de 2 entradas
- **Total: 2 portas** (redução de 67%!)

### 7.3 Escala Industrial

```
    Se cada porta economizada = 4 transistores:
    
    Circuito original:  6 portas × 4 = 24 transistores
    Circuito simples:   2 portas × 4 =  8 transistores
    
    Economia: 16 transistores POR circuito
    
    Em um chip com 1 milhão de circuitos:
    → 16 MILHÕES de transistores economizados!
    → Menos calor, menos energia, mais rápido
```

---

## 8. 🔧 Aplicações Práticas

### 8.1 Projeto de um Comparador de 1 Bit

**Objetivo:** Circuito que indica se A = B.

**Tabela verdade:**

| A | B | IGUAL |
|---|---|-------|
| 0 | 0 | 1 |
| 0 | 1 | 0 |
| 1 | 0 | 0 |
| 1 | 1 | 1 |

**Expressão (soma de produtos):** `IGUAL = Ā·B̄ + A·B`

**Reconhece?** É a porta **XNOR**! `IGUAL = A ⊙ B`

```
    Circuito original (4 portas):     Circuito simplificado (1 porta):
    
    A ──[NOT]──┐                      A ───┐
               │AND├──┐                    │XNOR├── IGUAL
    B ──[NOT]──┘      │               B ───┘
                      │OR├── IGUAL
    A ─────────┐      │
               │AND├──┘
    B ─────────┘
```

### 8.2 Projeto de Detector de Paridade (3 bits)

**Objetivo:** Saída = 1 quando há um número **ímpar** de 1s na entrada.

| A | B | C | P (paridade ímpar) |
|---|---|---|---------------------|
| 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 1 |
| 0 | 1 | 0 | 1 |
| 0 | 1 | 1 | 0 |
| 1 | 0 | 0 | 1 |
| 1 | 0 | 1 | 0 |
| 1 | 1 | 0 | 0 |
| 1 | 1 | 1 | 1 |

**Expressão:** `P = A ⊕ B ⊕ C` (XOR em cascata!)

> 💡 Detectores de paridade são usados em **memória RAM**, **comunicação serial** e **redes** para detectar erros de transmissão!

---

## 9. 📌 Resumo

| Conceito | Resumo |
|----------|--------|
| Circuito → Expressão | Leia porta a porta, das entradas para a saída |
| Expressão → Circuito | Decomponha pela operação principal, construa de dentro para fora |
| Simplificação algébrica | Aplique leis booleanas (distributiva, absorção, De Morgan) |
| Mapa de Karnaugh | Método visual: agrupe 1s adjacentes em potências de 2 |
| Equivalência | Mesma tabela verdade = mesmo circuito |
| SOP | Soma de Produtos: AND → OR (2 níveis) |
| POS | Produto de Somas: OR → AND (2 níveis) |
| Por que simplificar | Menos portas → mais barato, rápido, eficiente |

> 🧠 **Mensagem principal:** Projetar circuitos é traduzir entre expressões e portas. Simplificar é uma arte com regras claras — domine o Mapa de Karnaugh e a álgebra booleana, e você terá as ferramentas para criar circuitos eficientes!

---

## 10. 📚 Leitura Complementar

- 📖 TOCCI, R. J.; WIDMER, N. S. **Sistemas Digitais: Princípios e Aplicações**. Cap. 4 — Circuitos Lógicos Combinacionais.
- 📖 FLOYD, T. L. **Sistemas Digitais: Fundamentos e Aplicações**. Cap. 4 — Álgebra Booleana e Simplificação Lógica.
- 📖 TANENBAUM, A. S. **Organização Estruturada de Computadores**. Cap. 3 — Seção sobre Circuitos Combinacionais.
- 📖 MANO, M. M. **Digital Logic and Computer Design**. Cap. 3 — Simplificação de Funções Booleanas.
- 🌐 [Karnaugh Map Solver Online](https://www.charlie-coleman.com/experiments/kmap/)
- 🌐 [CircuitVerse — Monte seus circuitos](https://circuitverse.org/)

---

> ⬅️ [Aula 07 — Portas Lógicas](../07-portas-logicas-definicao-tabela-verdade/README.md) | [Exemplos](./exemplos/README.md) | [Exercícios](./exercicios/README.md) ➡️
