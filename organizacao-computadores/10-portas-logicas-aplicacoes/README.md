# ⚙️ Aula 10 — Funções e Portas Lógicas: Aplicações de Circuitos Lógicos

> **Disciplina:** Organização de Computadores  
> **Duração:** 2 horas-aula  
> **Nível:** Intermediário  
> **Pré-requisitos:** Aulas 07, 08 e 09 — Portas Lógicas, Expressões Booleanas e Conversões

---

## 🎯 Objetivos de Aprendizado

Ao final desta aula, o estudante será capaz de:

- ✅ Diferenciar **circuitos combinacionais** de **circuitos sequenciais**
- ✅ Projetar e analisar **multiplexadores (MUX)** e **demultiplexadores (DEMUX)**
- ✅ Compreender o funcionamento de **codificadores** e **decodificadores**
- ✅ Implementar **meio-somador**, **somador completo** e **somador ripple carry**
- ✅ Projetar **comparadores** de magnitude
- ✅ Compreender os conceitos de **flip-flops** (SR, JK, D, T) e **latches**
- ✅ Distinguir entre **latches** e **flip-flops**
- ✅ Descrever o funcionamento básico de **registradores** e **contadores**
- ✅ Relacionar esses blocos construtivos com a **arquitetura de computadores**

---

## 📋 Sumário

1. [Circuitos Combinacionais: Visão Geral](#1--circuitos-combinacionais-visão-geral)
2. [Multiplexadores (MUX) e Demultiplexadores (DEMUX)](#2--multiplexadores-mux-e-demultiplexadores-demux)
3. [Codificadores e Decodificadores](#3--codificadores-e-decodificadores)
4. [Meio-Somador e Somador Completo](#4--meio-somador-e-somador-completo)
5. [Somador Ripple Carry](#5--somador-ripple-carry)
6. [Comparadores](#6--comparadores)
7. [Introdução a Circuitos Sequenciais](#7--introdução-a-circuitos-sequenciais)
8. [Latches vs Flip-Flops](#8--latches-vs-flip-flops)
9. [Flip-Flops: SR, JK, D, T](#9--flip-flops-sr-jk-d-t)
10. [Registradores e Contadores](#10--registradores-e-contadores)
11. [Aplicações no Mundo Real](#11--aplicações-no-mundo-real)
12. [Por que Circuitos Importam para Cientistas da Computação](#12--por-que-circuitos-importam-para-cientistas-da-computação)
13. [Resumo](#13--resumo)
14. [Leitura Complementar](#14--leitura-complementar)

---

## 1. 🔌 Circuitos Combinacionais: Visão Geral

### 1.1 Definição

Um **circuito combinacional** é aquele cuja saída depende **apenas** das entradas atuais, sem memória de estados anteriores.

```
    ┌──────────────────────────────────────────────┐
    │         CIRCUITOS COMBINACIONAIS              │
    ├──────────────────────────────────────────────┤
    │  • Saída = f(entradas atuais)                │
    │  • Sem memória / sem realimentação           │
    │  • Mesma entrada → sempre mesma saída        │
    │                                              │
    │  Exemplos:                                   │
    │  ┌──────────┐  ┌────────────┐  ┌─────────┐  │
    │  │ Somadores│  │Decodificad.│  │   MUX   │  │
    │  └──────────┘  └────────────┘  └─────────┘  │
    └──────────────────────────────────────────────┘
```

### 1.2 Tipos Principais

| Bloco | Função | Exemplo de uso |
|-------|--------|---------------|
| **MUX** | Seleciona uma entre várias entradas | Seleção de dados |
| **DEMUX** | Distribui uma entrada para várias saídas | Roteamento de dados |
| **Codificador** | Converte 2ⁿ entradas em n bits | Teclado → código binário |
| **Decodificador** | Converte n bits em 2ⁿ saídas | Seleção de endereços |
| **Somador** | Soma binária | Unidade aritmética |
| **Comparador** | Compara dois números | Decisões condicionais |

---

## 2. 🔀 Multiplexadores (MUX) e Demultiplexadores (DEMUX)

### 2.1 Multiplexador (MUX)

O MUX é um **seletor de dados**: várias entradas, uma saída. As linhas de seleção determinam qual entrada é copiada para a saída.

```
    MUX 4:1 (4 entradas, 1 saída, 2 bits de seleção)

    D₀ ──┐
    D₁ ──┤      ┌──────┐
    D₂ ──┤──────│ MUX  │──── Y (saída)
    D₃ ──┤      │ 4:1  │
          └──────┘
    S₁ S₀ ──────┘ (seleção)
```

**Tabela de funcionamento:**

| S₁ | S₀ | Saída Y |
|----|----|---------|
| 0 | 0 | D₀ |
| 0 | 1 | D₁ |
| 1 | 0 | D₂ |
| 1 | 1 | D₃ |

**Expressão booleana do MUX 4:1:**

```
    Y = S̄₁·S̄₀·D₀ + S̄₁·S₀·D₁ + S₁·S̄₀·D₂ + S₁·S₀·D₃
```

**Implementação com portas lógicas:**

```
    S₁ ──[NOT]──┐
    S₀ ──[NOT]──┤
                │
    D₀ ─┐      │
    S̄₁ ─┤AND├──┐
    S̄₀ ─┘      │
                │
    D₁ ─┐      │
    S̄₁ ─┤AND├──┤OR├──── Y
    S₀ ─┘      │
                │
    D₂ ─┐      │
    S₁ ─┤AND├──┤
    S̄₀ ─┘      │
                │
    D₃ ─┐      │
    S₁ ─┤AND├──┘
    S₀ ─┘
```

### 2.2 Demultiplexador (DEMUX)

O DEMUX faz o inverso do MUX: uma entrada é **roteada** para uma das várias saídas.

```
    DEMUX 1:4 (1 entrada, 4 saídas, 2 bits de seleção)

                ┌────────┐──── Y₀
    D (entrada)─│ DEMUX  │──── Y₁
                │  1:4   │──── Y₂
    S₁ S₀ ─────│        │──── Y₃
                └────────┘
```

**Tabela de funcionamento:**

| S₁ | S₀ | Y₀ | Y₁ | Y₂ | Y₃ |
|----|----|----|----|----|----|
| 0 | 0 | D | 0 | 0 | 0 |
| 0 | 1 | 0 | D | 0 | 0 |
| 1 | 0 | 0 | 0 | D | 0 |
| 1 | 1 | 0 | 0 | 0 | D |

**Expressões:**

```
    Y₀ = D · S̄₁ · S̄₀
    Y₁ = D · S̄₁ · S₀
    Y₂ = D · S₁ · S̄₀
    Y₃ = D · S₁ · S₀
```

### 2.3 Analogia

```
    MUX = Controle remoto de TV
    ├─ Vários canais (entradas)
    ├─ Você seleciona um (seleção)
    └─ Assiste apenas um por vez (saída)

    DEMUX = Central de distribuição
    ├─ Um pacote chega (entrada)
    ├─ O endereço determina o destino (seleção)
    └─ O pacote vai para uma das saídas
```

---

## 3. 🔢 Codificadores e Decodificadores

### 3.1 Codificador (Encoder)

Converte **2ⁿ linhas de entrada** em **n linhas de saída** (código binário).

**Codificador 4:2 (4 entradas → 2 saídas):**

| D₃ | D₂ | D₁ | D₀ | A₁ | A₀ |
|----|----|----|----|----|----|
| 0 | 0 | 0 | 1 | 0 | 0 |
| 0 | 0 | 1 | 0 | 0 | 1 |
| 0 | 1 | 0 | 0 | 1 | 0 |
| 1 | 0 | 0 | 0 | 1 | 1 |

**Expressões:**

```
    A₁ = D₂ + D₃
    A₀ = D₁ + D₃
```

> ⚠️ Codificadores simples assumem que **apenas uma entrada está ativa** por vez. Para múltiplas entradas ativas, usa-se o **codificador de prioridade**.

### 3.2 Codificador de Prioridade

Quando várias entradas estão ativas, a de **maior prioridade** (geralmente o número mais alto) define a saída.

**Codificador de prioridade 4:2:**

| D₃ | D₂ | D₁ | D₀ | A₁ | A₀ | V (válido) |
|----|----|----|----|----|----|----|
| 0 | 0 | 0 | 0 | X | X | 0 |
| 0 | 0 | 0 | 1 | 0 | 0 | 1 |
| 0 | 0 | 1 | X | 0 | 1 | 1 |
| 0 | 1 | X | X | 1 | 0 | 1 |
| 1 | X | X | X | 1 | 1 | 1 |

### 3.3 Decodificador (Decoder)

Faz o inverso: converte **n linhas de entrada** em **2ⁿ linhas de saída**, ativando apenas uma.

**Decodificador 2:4 (2 entradas → 4 saídas):**

| A₁ | A₀ | Y₀ | Y₁ | Y₂ | Y₃ |
|----|----|----|----|----|----|
| 0 | 0 | 1 | 0 | 0 | 0 |
| 0 | 1 | 0 | 1 | 0 | 0 |
| 1 | 0 | 0 | 0 | 1 | 0 |
| 1 | 1 | 0 | 0 | 0 | 1 |

**Expressões:**

```
    Y₀ = Ā₁ · Ā₀
    Y₁ = Ā₁ · A₀
    Y₂ = A₁ · Ā₀
    Y₃ = A₁ · A₀
```

**Circuito:**

```
    A₁ ──[NOT]──┐
                │AND├──── Y₀ (Ā₁ · Ā₀)
    A₀ ──[NOT]──┘

    A₁ ──[NOT]──┐
                │AND├──── Y₁ (Ā₁ · A₀)
    A₀ ─────────┘

    A₁ ─────────┐
                │AND├──── Y₂ (A₁ · Ā₀)
    A₀ ──[NOT]──┘

    A₁ ─────────┐
                │AND├──── Y₃ (A₁ · A₀)
    A₀ ─────────┘
```

> 💡 **Aplicação:** Decodificadores são usados para **selecionar endereços de memória** nos computadores. Cada endereço ativa uma célula específica!

---

## 4. ➕ Meio-Somador e Somador Completo

### 4.1 Meio-Somador (Half Adder)

Soma **dois bits** sem considerar "vem-um" de uma soma anterior.

| A | B | Soma (S) | Carry (C) |
|---|---|----------|-----------|
| 0 | 0 | 0 | 0 |
| 0 | 1 | 1 | 0 |
| 1 | 0 | 1 | 0 |
| 1 | 1 | 0 | 1 |

**Expressões:**

```
    S = A ⊕ B     (XOR)
    C = A · B     (AND)
```

**Circuito:**

```
    A ───┬───┐
         │   │XOR├──── S (Soma)
    B ───┼───┘
         │
         ├───┐
         │   │AND├──── C (Carry)
         └───┘
```

### 4.2 Somador Completo (Full Adder)

Soma **dois bits mais o carry** de entrada (Cᵢₙ).

| A | B | Cᵢₙ | Soma (S) | Cₒᵤₜ |
|---|---|------|----------|-------|
| 0 | 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 1 | 0 |
| 0 | 1 | 0 | 1 | 0 |
| 0 | 1 | 1 | 0 | 1 |
| 1 | 0 | 0 | 1 | 0 |
| 1 | 0 | 1 | 0 | 1 |
| 1 | 1 | 0 | 0 | 1 |
| 1 | 1 | 1 | 1 | 1 |

**Expressões:**

```
    S    = A ⊕ B ⊕ Cᵢₙ
    Cₒᵤₜ = A·B + A·Cᵢₙ + B·Cᵢₙ
         = A·B + Cᵢₙ·(A ⊕ B)
```

**Circuito:**

```
    A ───┐
         │XOR├───┐
    B ───┘       │XOR├──── S (Soma)
                 │
    Cᵢₙ ────────┘

    A ───┐
         │AND├───┐
    B ───┘       │
                 │OR├──── Cₒᵤₜ (Carry out)
    A ───┐       │
         │XOR├───┤
    B ───┘       │
         ┌───────┘
         │AND├
    Cᵢₙ ─┘
```

> 💡 Um somador completo pode ser construído com **2 meio-somadores** e 1 porta OR!

---

## 5. 🔗 Somador Ripple Carry

### 5.1 Conceito

O **somador ripple carry** (somador com propagação de carry) conecta vários somadores completos em cascata para somar números de múltiplos bits.

### 5.2 Somador de 4 Bits

```
                    A₃ B₃       A₂ B₂       A₁ B₁       A₀ B₀
                     │  │        │  │        │  │        │  │
                     ▼  ▼        ▼  ▼        ▼  ▼        ▼  ▼
    Cₒᵤₜ ◄── ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐
              │   FA₃    │ │   FA₂    │ │   FA₁    │ │   FA₀    │◄── Cᵢₙ = 0
              │          │ │          │ │          │ │          │
    C₄  ◄────│Cₒ     Cᵢ│◄│Cₒ     Cᵢ│◄│Cₒ     Cᵢ│◄│Cₒ     Cᵢ│
              └────┬─────┘ └────┬─────┘ └────┬─────┘ └────┬─────┘
                   │            │            │            │
                   ▼            ▼            ▼            ▼
                   S₃           S₂           S₁           S₀
```

**Exemplo:** Somar 0101 (5) + 0011 (3):

| Posição | A | B | Cᵢₙ | S | Cₒᵤₜ |
|---------|---|---|------|---|-------|
| Bit 0 | 1 | 1 | 0 | 0 | 1 |
| Bit 1 | 0 | 1 | 1 | 0 | 1 |
| Bit 2 | 1 | 0 | 1 | 0 | 1 |
| Bit 3 | 0 | 0 | 1 | 1 | 0 |

**Resultado:** 1000 (8) ✓ (5 + 3 = 8)

### 5.3 Limitação: Atraso de Propagação

```
    ⚠️ O carry precisa "percorrer" todos os somadores!
    
    Tempo total = n × (tempo de 1 somador completo)
    
    Para 32 bits: 32 × atraso = LENTO!
    
    Solução: Carry Lookahead Adder (somador com antecipação de carry)
    → Calcula todos os carries em paralelo
    → Mais portas, mas muito mais rápido!
```

---

## 6. ⚖️ Comparadores

### 6.1 Comparador de 1 Bit

Compara dois bits A e B e indica se A > B, A < B ou A = B.

| A | B | A>B | A=B | A<B |
|---|---|-----|-----|-----|
| 0 | 0 | 0 | 1 | 0 |
| 0 | 1 | 0 | 0 | 1 |
| 1 | 0 | 1 | 0 | 0 |
| 1 | 1 | 0 | 1 | 0 |

**Expressões:**

```
    A > B  →  A · B̄
    A = B  →  A ⊙ B = A·B + Ā·B̄    (XNOR)
    A < B  →  Ā · B
```

### 6.2 Comparador de n Bits

Para comparar números de n bits, a lógica é aplicada bit a bit:

```
    IGUALDADE: Todos os bits devem ser iguais
    A = B  →  (A₃⊙B₃) · (A₂⊙B₂) · (A₁⊙B₁) · (A₀⊙B₀)

    MAIOR QUE: Verifica do bit mais significativo para o menos significativo
    A > B  →  Se A₃>B₃, ou (A₃=B₃ e A₂>B₂), ou ...
```

> 💡 Comparadores são fundamentais para instruções de **desvio condicional** (if, while) nos processadores!

---

## 7. 🔄 Introdução a Circuitos Sequenciais

### 7.1 Combinacional vs Sequencial

| Aspecto | Combinacional | Sequencial |
|---------|---------------|------------|
| **Memória** | Não tem | Tem memória (estado) |
| **Saída depende de** | Entradas atuais | Entradas atuais + estado anterior |
| **Realimentação** | Não | Sim |
| **Exemplos** | Somador, MUX | Flip-flop, registrador, contador |

```
    COMBINACIONAL:                  SEQUENCIAL:
    
    Entradas ──► [Lógica] ──► Saídas     Entradas ──► [Lógica] ──► Saídas
                                                        ▲    │
                                                        │    ▼
                                                     [Memória]
                                                     (estado)
```

### 7.2 Conceito de Estado

Um circuito sequencial "lembra" o que aconteceu antes. Essa memória é chamada de **estado**.

**Exemplo cotidiano:** Um interruptor de luz:
- Pressionar uma vez → liga (estado = ligado)
- Pressionar novamente → desliga (estado = desligado)
- A saída depende do estado atual, não apenas da entrada

---

## 8. 🔒 Latches vs Flip-Flops

### 8.1 Latch SR (Set-Reset)

O latch é o elemento de memória mais simples. Mantém um bit de informação.

**Latch SR com portas NOR:**

```
    S ───┐
         │NOR├──┬──── Q
    ┌────┘      │
    │    ┌──────┘
    │    │
    └────┤
         │NOR├──┬──── Q̄
    R ───┘      │
         ┌──────┘
         └──────── (realimentação)
```

**Tabela de funcionamento:**

| S | R | Q (próximo) | Q̄ (próximo) | Ação |
|---|---|-------------|-------------|------|
| 0 | 0 | Q (mantém) | Q̄ (mantém) | Sem mudança |
| 0 | 1 | 0 | 1 | Reset |
| 1 | 0 | 1 | 0 | Set |
| 1 | 1 | ? | ? | ⚠️ Proibido! |

> ⚠️ A condição S=1 e R=1 é **proibida** porque gera um estado indefinido.

### 8.2 Diferença: Latch vs Flip-Flop

| Aspecto | Latch | Flip-Flop |
|---------|-------|-----------|
| **Sensibilidade** | Sensível ao **nível** do sinal | Sensível à **borda** do clock |
| **Quando muda** | Enquanto o sinal de habilitação estiver ativo | Apenas no instante da borda (subida ou descida) |
| **Transparência** | Transparente quando habilitado | Nunca transparente |
| **Uso** | Circuitos simples | Registradores, contadores, CPUs |

```
    LATCH (sensível a nível):        FLIP-FLOP (sensível a borda):
    
    CLK ──────┐     ┌──────         CLK ──────┐     ┌──────
              │     │                          │     │
              └─────┘                          └─────┘
    ◄────ativo────►                        ▲
    Muda durante todo                  Muda apenas
    o período ativo                    neste instante
```

---

## 9. 📦 Flip-Flops: SR, JK, D, T

### 9.1 Flip-Flop SR

Igual ao latch SR, mas controlado por clock (borda).

```
         ┌────────┐
    S ───│  SR    │──── Q
         │  FF    │
    R ───│        │──── Q̄
         │   ▲    │
    CLK ─│───┘    │
         └────────┘
```

| S | R | Q(t+1) | Ação |
|---|---|--------|------|
| 0 | 0 | Q(t) | Mantém |
| 0 | 1 | 0 | Reset |
| 1 | 0 | 1 | Set |
| 1 | 1 | ? | Proibido |

### 9.2 Flip-Flop JK

Resolve o problema do estado proibido do SR. Quando J=K=1, o flip-flop **alterna** (toggle).

```
         ┌────────┐
    J ───│  JK    │──── Q
         │  FF    │
    K ───│        │──── Q̄
         │   ▲    │
    CLK ─│───┘    │
         └────────┘
```

| J | K | Q(t+1) | Ação |
|---|---|--------|------|
| 0 | 0 | Q(t) | Mantém |
| 0 | 1 | 0 | Reset |
| 1 | 0 | 1 | Set |
| 1 | 1 | Q̄(t) | **Toggle** (alterna) |

> 💡 O flip-flop JK é o mais **versátil** — pode funcionar como SR, D ou T!

### 9.3 Flip-Flop D (Data)

O mais simples e mais usado. A saída simplesmente **copia** a entrada D na borda do clock.

```
         ┌────────┐
    D ───│  D     │──── Q
         │  FF    │
         │        │──── Q̄
         │   ▲    │
    CLK ─│───┘    │
         └────────┘
```

| D | Q(t+1) | Ação |
|---|--------|------|
| 0 | 0 | Armazena 0 |
| 1 | 1 | Armazena 1 |

**Equação:** `Q(t+1) = D`

> 💡 O flip-flop D é a base dos **registradores** — cada flip-flop D armazena 1 bit!

### 9.4 Flip-Flop T (Toggle)

Quando T=1, o flip-flop **alterna** o estado. Quando T=0, **mantém**.

```
         ┌────────┐
    T ───│  T     │──── Q
         │  FF    │
         │        │──── Q̄
         │   ▲    │
    CLK ─│───┘    │
         └────────┘
```

| T | Q(t+1) | Ação |
|---|--------|------|
| 0 | Q(t) | Mantém |
| 1 | Q̄(t) | Alterna |

> 💡 O flip-flop T é perfeito para construir **contadores**!

### 9.5 Resumo Comparativo

| Flip-Flop | Entradas | Estado Proibido? | Uso Principal |
|-----------|----------|------------------|---------------|
| **SR** | S, R | Sim (S=R=1) | Básico |
| **JK** | J, K | Não | Versátil |
| **D** | D | Não | Registradores |
| **T** | T | Não | Contadores |

---

## 10. 📊 Registradores e Contadores

### 10.1 Registradores

Um **registrador** armazena múltiplos bits usando flip-flops D em paralelo.

**Registrador de 4 bits:**

```
    D₃ ──┐  D₂ ──┐  D₁ ──┐  D₀ ──┐
         │       │       │       │
    ┌────┴──┐┌───┴───┐┌──┴────┐┌─┴─────┐
    │  D FF ││  D FF ││  D FF ││  D FF │
    │  ▲    ││  ▲    ││  ▲    ││  ▲    │
    └──┼──┬─┘└──┼──┬─┘└──┼──┬─┘└──┼──┬─┘
       │  │     │  │     │  │     │  │
    CLK┘  Q₃ CLK┘  Q₂ CLK┘  Q₁ CLK┘  Q₀
    
    (Todos os clocks são conectados ao mesmo sinal)
```

**Tipos de registradores:**

| Tipo | Funcionamento | Uso |
|------|---------------|-----|
| **Paralelo** | Carrega todos os bits de uma vez | Registradores da CPU |
| **Deslocamento (shift)** | Desloca bits para esquerda/direita | Multiplicação/divisão por 2 |
| **Circular** | Desloca com o último bit voltando ao primeiro | Sequenciadores |

### 10.2 Contadores

Um **contador** é um circuito sequencial que percorre uma sequência de estados (geralmente uma contagem binária).

**Contador binário de 3 bits (0 a 7):**

```
    CLK ──► T FF₀ ──► T FF₁ ──► T FF₂
            Q₀         Q₁         Q₂

    Sequência: 000 → 001 → 010 → 011 → 100 → 101 → 110 → 111 → 000 ...
```

| Clock | Q₂ | Q₁ | Q₀ | Decimal |
|-------|----|----|-----|---------|
| 0 | 0 | 0 | 0 | 0 |
| 1 | 0 | 0 | 1 | 1 |
| 2 | 0 | 1 | 0 | 2 |
| 3 | 0 | 1 | 1 | 3 |
| 4 | 1 | 0 | 0 | 4 |
| 5 | 1 | 0 | 1 | 5 |
| 6 | 1 | 1 | 0 | 6 |
| 7 | 1 | 1 | 1 | 7 |
| 8 | 0 | 0 | 0 | 0 (recomeça) |

> 💡 Contadores são usados no **Program Counter (PC)** do processador, que mantém o endereço da próxima instrução!

---

## 11. 🌍 Aplicações no Mundo Real

### 11.1 Controlador de Semáforo

Um semáforo simples pode ser implementado com um **contador** e um **decodificador**:

```
    ┌──────────┐     ┌──────────────┐     ┌──────────┐
    │ Oscilador│────►│  Contador    │────►│Decodific. │
    │ (clock)  │     │  (estados)   │     │(luzes)    │
    └──────────┘     └──────────────┘     └──────┬────┘
                                                 │
                                          ┌──────┴──────┐
                                          │  🔴 🟡 🟢  │
                                          └─────────────┘

    Estado 0-2: Verde   (3 ciclos)
    Estado 3:   Amarelo (1 ciclo)
    Estado 4-6: Vermelho (3 ciclos)
    Estado 7:   Recomeça
```

### 11.2 Sistema de Alarme

```
    Sensores ──► Codificador ──► Lógica ──► Decodificador ──► Alarmes
    (porta,       de prioridade   combinac.   de saída         (sirene,
     janela,                                                    luz,
     movimento)                                                 SMS)
```

### 11.3 Fechadura Digital

```
    Teclado ──► Registrador ──► Comparador ──► Trava
    (dígitos)   (armazena      (compara com   (abre/fecha)
                 sequência)     código correto)
```

### 11.4 Dentro do seu Computador

| Componente | Circuitos Utilizados |
|-----------|---------------------|
| **CPU** | Somadores, MUX, registradores, flip-flops |
| **Memória RAM** | Milhões de flip-flops D (1 por bit) |
| **Barramento** | MUX/DEMUX para selecionar dispositivos |
| **Controlador de disco** | Contadores, codificadores |
| **GPU** | Milhares de somadores em paralelo |

---

## 12. 💻 Por que Circuitos Importam para Cientistas da Computação

```
    ┌─────────────────────────────────────────────────────────┐
    │     Nível 5: Aplicação (software do usuário)            │
    ├─────────────────────────────────────────────────────────┤
    │     Nível 4: Sistema Operacional                        │
    ├─────────────────────────────────────────────────────────┤
    │     Nível 3: Linguagem de Alto Nível (Python, Java)     │
    ├─────────────────────────────────────────────────────────┤
    │     Nível 2: Linguagem de Máquina / Assembly            │
    ├─────────────────────────────────────────────────────────┤
    │  ► Nível 1: Microarquitetura (circuitos combinacionais  │
    │             e sequenciais, portas lógicas)               │
    ├─────────────────────────────────────────────────────────┤
    │     Nível 0: Dispositivos (transistores, silício)       │
    └─────────────────────────────────────────────────────────┘
```

**Entender circuitos ajuda a:**

1. **Otimizar software** — sabendo como o hardware funciona, você escreve código mais eficiente
2. **Depurar problemas** — entender por que certas operações são mais rápidas que outras
3. **Projetar sistemas** — arquitetura de computadores, sistemas embarcados, IoT
4. **Inovar** — criar novos processadores, aceleradores, FPGAs

---

## 13. 📌 Resumo

| Conceito | Resumo |
|----------|--------|
| **MUX** | Seleciona 1 de N entradas com linhas de seleção |
| **DEMUX** | Roteia 1 entrada para 1 de N saídas |
| **Codificador** | 2ⁿ entradas → n bits de saída |
| **Decodificador** | n bits de entrada → 2ⁿ saídas |
| **Meio-somador** | Soma 2 bits (sem carry in) |
| **Somador completo** | Soma 2 bits + carry in |
| **Ripple carry** | Cascata de somadores completos |
| **Comparador** | Compara dois números bit a bit |
| **Latch** | Elemento de memória sensível a nível |
| **Flip-flop** | Elemento de memória sensível a borda |
| **Registrador** | Conjunto de flip-flops D em paralelo |
| **Contador** | Sequência de estados (contagem) |

> 🧠 **Mensagem principal:** Todas as operações de um computador — soma, comparação, armazenamento, contagem — são realizadas por combinações de portas lógicas simples. Dominar esses blocos construtivos é entender como o computador realmente funciona!

---

## 14. 📚 Leitura Complementar

- 📖 TOCCI, R. J.; WIDMER, N. S. **Sistemas Digitais: Princípios e Aplicações**. Cap. 5–7 — Circuitos Combinacionais e Sequenciais.
- 📖 FLOYD, T. L. **Sistemas Digitais: Fundamentos e Aplicações**. Cap. 6 — Funções de Circuitos Combinacionais.
- 📖 TANENBAUM, A. S. **Organização Estruturada de Computadores**. Cap. 3 — Nível Lógico Digital.
- 📖 STALLINGS, W. **Arquitetura e Organização de Computadores**. Cap. 11 — Lógica Digital.
- 🌐 [CircuitVerse — Simulador de Circuitos Online](https://circuitverse.org/)
- 🌐 [Logicly — Simulador Visual de Circuitos](https://logic.ly/)

---

> ⬅️ [Aula 09 — Circuitos, Tabelas e Expressões](../09-portas-logicas-circuitos-tabelas-expressoes/README.md) | [Exemplos](./exemplos/README.md) | [Exercícios](./exercicios/README.md) ➡️
