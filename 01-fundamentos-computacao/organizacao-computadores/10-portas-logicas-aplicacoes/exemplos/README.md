# 📊 Exemplos — Aula 10: Aplicações de Circuitos Lógicos

> Exemplos práticos de multiplexadores, somadores, decodificadores, flip-flops e aplicações

---

## 🔍 Parte 1 — Multiplexadores e Demultiplexadores

### Exemplo 1: MUX 2:1

**Circuito:**

```
    D₀ ───┐
          │MUX├──── Y
    D₁ ───┘
           │
    S ─────┘ (seleção)
```

**Expressão:** `Y = S̄·D₀ + S·D₁`

**Tabela:**

| S | Y |
|---|---|
| 0 | D₀ |
| 1 | D₁ |

**Implementação com portas:**

```
    S ──[NOT]──┐
               │AND├──┐
    D₀ ────────┘      │
                      │OR├──── Y
    S ─────────┐      │
               │AND├──┘
    D₁ ────────┘
```

**Total:** 1 NOT + 2 AND + 1 OR = **4 portas**

---

### Exemplo 2: Implementando uma Função com MUX

**Problema:** Implementar `S = Σm(1, 2, 3)` de 2 variáveis usando um MUX 4:1.

**Método:** Cada mintermo corresponde a uma entrada do MUX. Conecte 1 nas entradas correspondentes aos mintermos e 0 nas demais.

| S₁(A) | S₀(B) | Mintermo | S | Dᵢ |
|-------|-------|----------|---|-----|
| 0 | 0 | m₀ | 0 | D₀ = 0 |
| 0 | 1 | m₁ | 1 | D₁ = 1 |
| 1 | 0 | m₂ | 1 | D₂ = 1 |
| 1 | 1 | m₃ | 1 | D₃ = 1 |

**Circuito:**

```
    0 (GND) ─── D₀ ─┐
    1 (VCC) ─── D₁ ─┤ MUX ├──── S
    1 (VCC) ─── D₂ ─┤ 4:1 │
    1 (VCC) ─── D₃ ─┘     │
                     │
    A ─── S₁    B ── S₀
```

> 💡 Qualquer função booleana pode ser implementada com um MUX de tamanho adequado!

---

### Exemplo 3: Decodificador 3:8

**Problema:** Projetar um decodificador de 3 entradas e 8 saídas.

**Tabela parcial:**

| A₂ | A₁ | A₀ | Y₀ | Y₁ | Y₂ | Y₃ | Y₄ | Y₅ | Y₆ | Y₇ |
|----|----|-----|----|----|----|----|----|----|----|----|
| 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0 |
| 0 | 1 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 | 0 |
| 0 | 1 | 1 | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 |
| 1 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 | 0 |
| 1 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 1 | 0 | 0 |
| 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 0 |
| 1 | 1 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 |

**Expressões:**

```
    Y₀ = Ā₂ · Ā₁ · Ā₀     Y₄ = A₂ · Ā₁ · Ā₀
    Y₁ = Ā₂ · Ā₁ · A₀      Y₅ = A₂ · Ā₁ · A₀
    Y₂ = Ā₂ · A₁ · Ā₀      Y₆ = A₂ · A₁ · Ā₀
    Y₃ = Ā₂ · A₁ · A₀       Y₇ = A₂ · A₁ · A₀
```

> 💡 Cada saída corresponde a um **mintermo**! Decodificadores são geradores de mintermos.

---

## 🔍 Parte 2 — Somadores

### Exemplo 4: Soma com Meio-Somador

**Problema:** Somar A=1 e B=1 usando meio-somador.

```
    S = A ⊕ B = 1 ⊕ 1 = 0
    C = A · B = 1 · 1 = 1
```

**Resultado:** Soma = 0, Carry = 1 → "10" em binário = 2 em decimal ✓ (1+1=2)

---

### Exemplo 5: Soma com Somador Completo

**Problema:** Somar A=1, B=1 com Cᵢₙ=1.

```
    S    = A ⊕ B ⊕ Cᵢₙ = 1 ⊕ 1 ⊕ 1 = 1
    Cₒᵤₜ = A·B + Cᵢₙ·(A ⊕ B) = 1·1 + 1·(1⊕1) = 1 + 1·0 = 1
```

**Resultado:** Soma = 1, Cₒᵤₜ = 1 → "11" em binário = 3 em decimal ✓ (1+1+1=3)

---

### Exemplo 6: Somador Ripple Carry de 4 Bits

**Problema:** Somar 1011 (11) + 0110 (6)

| Posição | Aᵢ | Bᵢ | Cᵢₙ | Sᵢ = Aᵢ⊕Bᵢ⊕Cᵢₙ | Cₒᵤₜ = Aᵢ·Bᵢ+Cᵢₙ·(Aᵢ⊕Bᵢ) |
|---------|----|----|------|-------------------|------------------------------|
| Bit 0 | 1 | 0 | 0 | 1 | 0 |
| Bit 1 | 1 | 1 | 0 | 0 | 1 |
| Bit 2 | 0 | 1 | 1 | 0 | 1 |
| Bit 3 | 1 | 0 | 1 | 0 | 1 |

**Resultado:** C₄S₃S₂S₁S₀ = **10001** = 17 em decimal ✓ (11+6=17)

```
      1 0 1 1    (11)
    + 0 1 1 0    ( 6)
    ─────────
    1 0 0 0 1    (17)  ✓
```

---

## 🔍 Parte 3 — Circuitos Sequenciais

### Exemplo 7: Flip-Flop D — Armazenando uma Sequência

**Problema:** O sinal D varia ao longo do tempo. Qual é a saída Q?

```
    CLK:  ─┐ ┌─┐ ┌─┐ ┌─┐ ┌─┐ ┌─
           └─┘ └─┘ └─┘ └─┘ └─┘
    
    D:    ──1──0──1──1──0──
    
    Q:    ──?──?──?──?──?──
```

**Resolução:** O flip-flop D copia D para Q na **borda de subida** do clock:

| Borda CLK | D | Q(t+1) |
|-----------|---|--------|
| 1ª ↑ | 1 | 1 |
| 2ª ↑ | 0 | 0 |
| 3ª ↑ | 1 | 1 |
| 4ª ↑ | 1 | 1 |
| 5ª ↑ | 0 | 0 |

```
    CLK:  ─┐ ┌─┐ ┌─┐ ┌─┐ ┌─┐ ┌─
           └─┘ └─┘ └─┘ └─┘ └─┘
    
    D:    ──1──0──1──1──0──
    
    Q:    ──1──0──1──1──0──  (cópia atrasada de 1 clock)
```

> 💡 O flip-flop D funciona como um **atraso** de 1 ciclo de clock!

---

### Exemplo 8: Contador de 3 Bits com Flip-Flops T

**Problema:** Projetar um contador que conte de 0 a 7.

**Projeto:** Conecte 3 flip-flops T em cascata, todos com T=1 (sempre alternam).

```
    CLK ──► T FF₀ (Q₀) ──► T FF₁ (Q₁) ──► T FF₂ (Q₂)
```

**Diagrama temporal:**

```
    CLK: ─┐┌┐┌┐┌┐┌┐┌┐┌┐┌┐┌─
          └┘└┘└┘└┘└┘└┘└┘└┘

    Q₀:  ─┐ ┌─┐ ┌─┐ ┌─┐ ┌─  (alterna a cada clock)
          └─┘ └─┘ └─┘ └─┘

    Q₁:  ──┐   ┌───┐   ┌──   (alterna a cada 2 clocks)
           └───┘   └───┘

    Q₂:  ────┐       ┌────   (alterna a cada 4 clocks)
             └───────┘
    
    Cont: 0 1 2 3 4 5 6 7 0
```

---

## 🔍 Parte 4 — Aplicações Práticas

### Exemplo 9: Semáforo Simples (2 estados)

**Especificação:** Semáforo com Verde (2s) e Vermelho (2s), usando contador e decodificador.

**Projeto:**

```
    Oscilador ──► Contador ──► Decodificador ──► LEDs
    (1 Hz)        (mod 4)      (2:4)
```

| Contagem | Q₁ | Q₀ | Verde | Vermelho |
|----------|----|----|-------|----------|
| 0 | 0 | 0 | 1 | 0 |
| 1 | 0 | 1 | 1 | 0 |
| 2 | 1 | 0 | 0 | 1 |
| 3 | 1 | 1 | 0 | 1 |

**Expressões:**

```
    Verde    = Q̄₁   (Q₁ = 0)
    Vermelho = Q₁    (Q₁ = 1)
```

> 💡 Apenas 1 flip-flop (Q₁) é necessário para controlar 2 estados de cor!

---

### Exemplo 10: Fechadura Digital de 2 Dígitos

**Especificação:** A porta abre quando a sequência "01" é digitada (primeiro 0, depois 1).

**Projeto com flip-flop D:**

```
    Estado 0: Esperando primeiro dígito
    Estado 1: Recebeu "0", esperando "1"
    Estado 2: Recebeu "01" → ABRE!
```

| Estado Atual | Entrada | Próximo Estado | Saída (Abre) |
|-------------|---------|----------------|-------------|
| S0 | 0 | S1 | 0 |
| S0 | 1 | S0 | 0 |
| S1 | 0 | S1 | 0 |
| S1 | 1 | S2 | 1 |
| S2 | X | S0 | 0 (volta ao início) |

> 💡 Este é um exemplo de **máquina de estados finitos**, conceito fundamental em Teoria da Computação!

---

> 💡 **Dica geral:** Ao projetar circuitos práticos, sempre comece pela tabela verdade ou pelo diagrama de estados, depois derive as expressões e por fim construa o circuito.

---

> ⬅️ [Exercícios](../exercicios/README.md) | [Voltar para a Aula](../README.md)
