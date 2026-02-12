# 📊 Exemplos — Aula 11: Conceitos Básicos de Arquitetura e Organização

> Exemplos de comparação entre arquiteturas, cálculos de desempenho e aplicação da Lei de Amdahl

---

## 🔍 Parte 1 — Arquitetura vs Organização

### Exemplo 1: Mesma Arquitetura, Organizações Diferentes

**Cenário:** Dois processadores executam o mesmo programa (mesma ISA x86):

| Característica | Processador A | Processador B |
|---------------|---------------|---------------|
| **Arquitetura (ISA)** | x86-64 | x86-64 |
| **Clock** | 3.0 GHz | 4.5 GHz |
| **CPI médio** | 1.5 | 1.0 |
| **Cache L1** | 32 KB | 64 KB |
| **Cores** | 4 | 8 |

**Pergunta:** Qual é mais rápido para um programa sequencial de 100 milhões de instruções?

**Resolução:**

```
    Processador A:
    T = N × CPI / f = 100×10⁶ × 1.5 / (3.0×10⁹)
    T = 150×10⁶ / 3.0×10⁹ = 50×10⁻³ s = 50 ms

    Processador B:
    T = N × CPI / f = 100×10⁶ × 1.0 / (4.5×10⁹)
    T = 100×10⁶ / 4.5×10⁹ = 22.2×10⁻³ s = 22.2 ms
```

**Resultado:** O Processador B é **2.25× mais rápido** para este programa.

> 💡 O Processador B é mais rápido não só pelo clock maior, mas também pelo CPI menor (melhor organização interna).

---

### Exemplo 2: CISC vs RISC

**Cenário:** A mesma tarefa implementada em duas arquiteturas:

| Aspecto | CISC (x86) | RISC (ARM) |
|---------|-----------|-----------|
| **Nº de instruções** | 5 milhões | 8 milhões |
| **CPI médio** | 3.0 | 1.2 |
| **Clock** | 3.0 GHz | 2.0 GHz |

**Resolução:**

```
    CISC: T = 5×10⁶ × 3.0 / (3.0×10⁹) = 5.0 ms
    RISC: T = 8×10⁶ × 1.2 / (2.0×10⁹) = 4.8 ms
```

**Resultado:** O RISC é ligeiramente mais rápido, apesar de ter mais instruções e clock menor!

> 💡 RISC compensa o maior número de instruções com CPI muito menor. Cada instrução é mais simples e rápida.

---

## 🔍 Parte 2 — Cálculos de Desempenho

### Exemplo 3: CPI Médio com Mistura de Instruções

**Cenário:** Um processador executa um programa com a seguinte distribuição:

| Tipo de instrução | Frequência | CPI |
|-------------------|-----------|-----|
| Aritmética (ADD, SUB) | 40% | 1 |
| Transferência (LOAD, STORE) | 30% | 3 |
| Desvio (BRANCH, JUMP) | 20% | 2 |
| Ponto flutuante (FMUL) | 10% | 5 |

**Resolução:**

```
    CPI médio = Σ(CPIᵢ × Fᵢ)
             = 1×0.40 + 3×0.30 + 2×0.20 + 5×0.10
             = 0.40 + 0.90 + 0.40 + 0.50
             = 2.20 ciclos/instrução
```

**Se o clock é 2 GHz e o programa tem 50 milhões de instruções:**

```
    T = 50×10⁶ × 2.20 / (2×10⁹) = 55 ms

    MIPS = f / (CPI × 10⁶) = 2×10⁹ / (2.20 × 10⁶) = 909 MIPS
```

---

### Exemplo 4: Impacto do Clock no Desempenho

**Cenário:** Qual melhoria se o clock passar de 2 GHz para 3 GHz (mesmo CPI)?

```
    T₁ = N × CPI / f₁ = N × CPI / (2×10⁹)
    T₂ = N × CPI / f₂ = N × CPI / (3×10⁹)

    Speedup = T₁/T₂ = f₂/f₁ = 3/2 = 1.5×
```

**Resultado:** Aumento de 50% no clock → programa 50% mais rápido (neste caso ideal).

> ⚠️ Na prática, aumentar o clock pode aumentar o CPI (devido a problemas de pipeline), reduzindo o ganho real.

---

### Exemplo 5: Comparando MIPS de Diferentes Processadores

**Cenário:**

| Processador | Clock | CPI | MIPS |
|-------------|-------|-----|------|
| **P1** | 1 GHz | 2.0 | ? |
| **P2** | 2 GHz | 4.0 | ? |
| **P3** | 4 GHz | 1.0 | ? |

**Resolução:**

```
    P1: MIPS = 1×10⁹ / (2.0 × 10⁶) = 500 MIPS
    P2: MIPS = 2×10⁹ / (4.0 × 10⁶) = 500 MIPS
    P3: MIPS = 4×10⁹ / (1.0 × 10⁶) = 4000 MIPS
```

**Resultado:** P1 e P2 têm o mesmo MIPS, apesar de P2 ter o dobro do clock! O CPI importa tanto quanto a frequência.

---

## 🔍 Parte 3 — Lei de Amdahl

### Exemplo 6: Melhorando a Unidade de Ponto Flutuante

**Cenário:** Um programa gasta:
- 60% em operações inteiras
- 40% em operações de ponto flutuante

Você pode comprar uma nova FPU que é **5× mais rápida**. Qual o speedup?

```
    F = 0.40 (fração melhorável)
    S = 5 (fator de melhoria)

    Speedup = 1 / ((1 - 0.40) + 0.40/5)
            = 1 / (0.60 + 0.08)
            = 1 / 0.68
            = 1.47
```

**Resultado:** Speedup de **1.47×** (47% mais rápido). O investimento em uma FPU 5× mais rápida deu um ganho de apenas 47%!

**Speedup máximo (FPU infinitamente rápida):**

```
    Speedup_max = 1 / (1 - 0.40) = 1 / 0.60 = 1.67×
```

> 💡 Mesmo com uma FPU perfeita, o máximo seria 67% de melhoria. Os 60% de inteiros limitam o ganho.

---

### Exemplo 7: Paralelização com Múltiplos Cores

**Cenário:** Um programa tem 80% paralelizável. Calcule o speedup para 2, 4, 8 e 16 cores:

```
    F = 0.80

    2 cores:  Speedup = 1 / (0.20 + 0.80/2)  = 1 / 0.60 = 1.67×
    4 cores:  Speedup = 1 / (0.20 + 0.80/4)  = 1 / 0.40 = 2.50×
    8 cores:  Speedup = 1 / (0.20 + 0.80/8)  = 1 / 0.30 = 3.33×
    16 cores: Speedup = 1 / (0.20 + 0.80/16) = 1 / 0.25 = 4.00×
    ∞ cores:  Speedup = 1 / 0.20             =          5.00×
```

**Tabela resumo:**

| Cores | Speedup | Eficiência (Speedup/Cores) |
|-------|---------|---------------------------|
| 1 | 1.00× | 100% |
| 2 | 1.67× | 83% |
| 4 | 2.50× | 63% |
| 8 | 3.33× | 42% |
| 16 | 4.00× | 25% |
| ∞ | 5.00× | ~0% |

> 💡 Observe como a **eficiência cai** à medida que adicionamos mais cores. Os 20% sequenciais se tornam o gargalo dominante!

---

### Exemplo 8: Ciclo de Instrução — Execução Passo a Passo

**Cenário:** Executar as instruções abaixo na memória:

```
    Endereço 100: LOAD R1, [200]    (R1 ← Memória[200])
    Endereço 101: LOAD R2, [201]    (R2 ← Memória[201])
    Endereço 102: ADD R3, R1, R2    (R3 ← R1 + R2)
    Endereço 103: STORE [202], R3   (Memória[202] ← R3)
    
    Memória[200] = 15
    Memória[201] = 27
```

**Execução passo a passo:**

| Passo | PC | Fase | Ação | Resultado |
|-------|-----|------|------|-----------|
| 1 | 100 | Busca | IR ← "LOAD R1, [200]" | PC = 101 |
| 2 | 100 | Decodif. | UC identifica LOAD | — |
| 3 | 100 | Execução | R1 ← Memória[200] | R1 = 15 |
| 4 | 101 | Busca | IR ← "LOAD R2, [201]" | PC = 102 |
| 5 | 101 | Decodif. | UC identifica LOAD | — |
| 6 | 101 | Execução | R2 ← Memória[201] | R2 = 27 |
| 7 | 102 | Busca | IR ← "ADD R3, R1, R2" | PC = 103 |
| 8 | 102 | Decodif. | UC identifica ADD | — |
| 9 | 102 | Execução | R3 ← 15 + 27 | R3 = 42 |
| 10 | 103 | Busca | IR ← "STORE [202], R3" | PC = 104 |
| 11 | 103 | Decodif. | UC identifica STORE | — |
| 12 | 103 | Execução | Memória[202] ← 42 | Mem[202] = 42 |

**Estado final:** Memória[202] = 42 (15 + 27 = 42 ✓)

---

> 💡 **Dica geral:** Para cálculos de desempenho, sempre identifique claramente as variáveis (N, CPI, f) antes de aplicar as fórmulas. Erros comuns envolvem unidades (GHz vs MHz) e potências de 10.

---

> ⬅️ [Exercícios](../exercicios/README.md) | [Voltar para a Aula](../README.md)
