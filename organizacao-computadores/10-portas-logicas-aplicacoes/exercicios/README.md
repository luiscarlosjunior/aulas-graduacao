# 📝 Exercícios — Aula 10: Aplicações de Circuitos Lógicos

> Exercícios práticos sobre MUX, DEMUX, codificadores, decodificadores, somadores, flip-flops e aplicações

**Instruções:** Desenhe circuitos em formato texto quando necessário. Mostre tabelas verdade e expressões booleanas. Para flip-flops, desenhe o diagrama temporal.

---

## 🟢 Nível Básico

### Exercício 1 — Multiplexador (MUX)

**a)** Complete a tabela de um MUX 2:1 com a expressão `Y = S̄·D₀ + S·D₁`:

| S | D₀ | D₁ | Y |
|---|----|----|---|
| 0 | 0 | 1 | |
| 0 | 1 | 0 | |
| 1 | 0 | 1 | |
| 1 | 1 | 0 | |

**b)** Quantas linhas de seleção são necessárias para um MUX 8:1?

**c)** Desenhe o circuito de um MUX 2:1 usando portas AND, OR e NOT.

---

### Exercício 2 — Decodificador

**a)** Complete a tabela de um decodificador 2:4:

| A₁ | A₀ | Y₀ | Y₁ | Y₂ | Y₃ |
|----|----|----|----|----|----|
| 0 | 0 | | | | |
| 0 | 1 | | | | |
| 1 | 0 | | | | |
| 1 | 1 | | | | |

**b)** Escreva as expressões booleanas para Y₀, Y₁, Y₂ e Y₃.

**c)** Quantas saídas teria um decodificador 4:16?

---

### Exercício 3 — Meio-Somador e Somador Completo

**a)** Calcule a soma e o carry para o **meio-somador** com as entradas:

| A | B | Soma (S) | Carry (C) |
|---|---|----------|-----------|
| 0 | 0 | | |
| 0 | 1 | | |
| 1 | 0 | | |
| 1 | 1 | | |

**b)** Calcule a soma e o carry-out para o **somador completo** com:
- A = 1, B = 0, Cᵢₙ = 1
- A = 1, B = 1, Cᵢₙ = 1

**c)** Quantos meio-somadores e portas OR são necessários para construir um somador completo?

---

### Exercício 4 — Flip-Flops: Tabela de Transição

Complete as tabelas de próximo estado para cada flip-flop:

**a) Flip-Flop SR:**

| S | R | Q(t) | Q(t+1) |
|---|---|------|--------|
| 0 | 0 | 0 | |
| 0 | 0 | 1 | |
| 0 | 1 | 0 | |
| 0 | 1 | 1 | |
| 1 | 0 | 0 | |
| 1 | 0 | 1 | |

**b) Flip-Flop JK:**

| J | K | Q(t) | Q(t+1) |
|---|---|------|--------|
| 0 | 0 | 0 | |
| 0 | 0 | 1 | |
| 1 | 1 | 0 | |
| 1 | 1 | 1 | |

**c) Flip-Flop T:**

| T | Q(t) | Q(t+1) |
|---|------|--------|
| 0 | 0 | |
| 0 | 1 | |
| 1 | 0 | |
| 1 | 1 | |

---

## 🟡 Nível Intermediário

### Exercício 5 — Somador Ripple Carry

**a)** Use um somador ripple carry de 4 bits para calcular:

```
    0111 (7) + 0101 (5) = ?
```

Preencha a tabela:

| Posição | Aᵢ | Bᵢ | Cᵢₙ | Sᵢ | Cₒᵤₜ |
|---------|----|----|------|----|-------|
| Bit 0 | | | 0 | | |
| Bit 1 | | | | | |
| Bit 2 | | | | | |
| Bit 3 | | | | | |

**b)** Calcule: 1010 (10) + 0111 (7). O resultado cabe em 4 bits?

**c)** Se o somador ripple carry de 4 bits tem atraso de 10ns por somador completo, qual é o atraso total? Se usássemos 32 bits, quanto tempo levaria?

---

### Exercício 6 — Implementando Funções com MUX

**a)** Implemente a função `S(A,B) = Σm(0, 2)` usando um MUX 4:1. Quais entradas (D₀ a D₃) devem receber 0 e quais devem receber 1?

**b)** Implemente `S(A,B,C) = Σm(1, 3, 5, 7)` usando um MUX 8:1.

**c)** É possível implementar QUALQUER função booleana de n variáveis com um MUX de 2ⁿ:1? Justifique.

---

### Exercício 7 — Codificador de Prioridade

Projete um codificador de prioridade 8:3 (8 entradas, 3 saídas de código, 1 saída de válido).

**a)** Complete a tabela para as combinações onde D₇=1, D₃=1 (sem outras ativas), e nenhuma ativa.

**b)** Qual é a prioridade: D₀ (menor) ou D₇ (maior)?

**c)** Por que o sinal V (válido) é necessário?

---

### Exercício 8 — Comparador de 2 Bits

Projete um comparador que compara dois números de 2 bits: A = A₁A₀ e B = B₁B₀.

**a)** Construa a tabela verdade para a saída "A > B" (considere apenas os 4 valores possíveis de A e B em forma resumida).

**b)** Derive a expressão booleana para "A = B".

**c)** Quantas portas XNOR são necessárias para a saída "A = B"?

---

## 🔴 Nível Avançado

### Exercício 9 — Diagrama Temporal de Flip-Flops

Dado o sinal de clock e a entrada D abaixo, desenhe a saída Q para um flip-flop D sensível à **borda de subida**:

```
    CLK:  ─┐ ┌─┐ ┌─┐ ┌─┐ ┌─┐ ┌─┐ ┌─
           └─┘ └─┘ └─┘ └─┘ └─┘ └─┘

    D:    0─1──1──0──0──1──0──

    Q:    ?─?──?──?──?──?──?──
```

Repita para um flip-flop D sensível à **borda de descida**.

---

### Exercício 10 — Projeto: Somador/Subtrator de 4 Bits

Um circuito somador/subtrator usa um sinal de controle M:
- M = 0 → operação de **soma** (A + B)
- M = 1 → operação de **subtração** (A − B usando complemento de 2)

**a)** Explique como portas XOR podem ser usadas para inverter condicionalmente os bits de B.

**b)** Desenhe o diagrama de blocos do somador/subtrator de 4 bits.

**c)** Calcule: 0110 (6) − 0011 (3) usando este circuito.

> 💡 **Dica:** No complemento de 2, subtrair B equivale a somar o complemento de B mais 1. O sinal M pode ser usado como carry-in!

---

### Exercício 11 — Verdadeiro ou Falso

| | Afirmação | V/F |
|---|-----------|-----|
| a) | Um MUX 8:1 tem 3 linhas de seleção | |
| b) | Um decodificador 3:8 pode gerar todos os mintermos de 3 variáveis | |
| c) | O meio-somador considera o carry de entrada | |
| d) | O flip-flop D tem estado proibido | |
| e) | O flip-flop JK resolve o problema do estado proibido do SR | |
| f) | Um latch é sensível à borda do clock | |
| g) | Um registrador de 8 bits usa 8 flip-flops D | |
| h) | O somador ripple carry é mais rápido que o carry lookahead | |
| i) | Contadores podem ser construídos com flip-flops T | |
| j) | A memória RAM usa latches/flip-flops para armazenar dados | |

---

### Exercício 12 — Projeto: Controlador de Semáforo

Projete um controlador de semáforo simples com as seguintes especificações:

- 3 estados: Verde (2 ciclos), Amarelo (1 ciclo), Vermelho (2 ciclos)
- Total: 5 estados → use um contador módulo 5

**a)** Desenhe o diagrama de estados.

**b)** Construa a tabela de transição de estados.

**c)** Quantos flip-flops são necessários? (Dica: ⌈log₂(5)⌉)

**d)** Derive as expressões para as saídas Verde, Amarelo e Vermelho em função dos flip-flops.

**e)** Desenhe o diagrama de blocos do circuito completo.

> 💡 Este é um projeto clássico de eletrônica digital que integra contadores, decodificadores e lógica combinacional!

---

> 💡 **Dica geral:** Para circuitos sequenciais, sempre desenhe o diagrama temporal (cronograma) para visualizar o comportamento ao longo do tempo.

---

> ⬅️ [Exemplos](../exemplos/README.md) | [Voltar para a Aula](../README.md)
