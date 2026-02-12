# 🔌 Aula 07 — Funções e Portas Lógicas: Definição, Representação, Tabela Verdade e Expressões Booleanas

> **Disciplina:** Organização de Computadores  
> **Duração:** 2 horas-aula  
> **Nível:** Iniciante a Intermediário  
> **Pré-requisitos:** Aulas 05 e 06 — Operações Aritméticas Binárias

---

## 🎯 Objetivos de Aprendizado

Ao final desta aula, o estudante será capaz de:

- ✅ Explicar quem foi **George Boole** e a importância da álgebra booleana
- ✅ Identificar e descrever as **7 portas lógicas** fundamentais (AND, OR, NOT, NAND, NOR, XOR, XNOR)
- ✅ Desenhar os **símbolos** de cada porta lógica
- ✅ Construir a **tabela verdade** de qualquer porta lógica
- ✅ Escrever **expressões booleanas** usando notação padrão
- ✅ Aplicar as **leis e propriedades** da álgebra booleana (incluindo De Morgan)
- ✅ Relacionar portas lógicas com situações do **dia a dia**

---

## 📋 Sumário

1. [George Boole e a Álgebra Booleana](#1--george-boole-e-a-álgebra-booleana)
2. [Valores Lógicos: Verdadeiro e Falso](#2--valores-lógicos-verdadeiro-e-falso)
3. [Portas Lógicas Básicas: AND, OR, NOT](#3--portas-lógicas-básicas-and-or-not)
4. [Portas Universais: NAND e NOR](#4--portas-universais-nand-e-nor)
5. [Portas Especiais: XOR e XNOR](#5--portas-especiais-xor-e-xnor)
6. [Resumo de Todas as Portas](#6--resumo-de-todas-as-portas)
7. [Notação de Expressões Booleanas](#7--notação-de-expressões-booleanas)
8. [Leis e Propriedades da Álgebra Booleana](#8--leis-e-propriedades-da-álgebra-booleana)
9. [Analogias com o Mundo Real](#9--analogias-com-o-mundo-real)
10. [Por que Isso Importa?](#10--por-que-isso-importa)
11. [Resumo](#11--resumo)
12. [Leitura Complementar](#12--leitura-complementar)

---

## 1. 📜 George Boole e a Álgebra Booleana

### 1.1 Quem Foi George Boole?

**George Boole** (1815–1864) foi um matemático e filósofo inglês que criou a **álgebra booleana** em 1854, no livro *"An Investigation of the Laws of Thought"* (Uma Investigação sobre as Leis do Pensamento).

> 💡 Boole demonstrou que a **lógica** podia ser tratada como um sistema **algébrico**, usando apenas dois valores: **VERDADEIRO** e **FALSO**. Na época, ninguém imaginou que isso teria aplicação prática. Quase 100 anos depois, **Claude Shannon** (1938) provou que essa álgebra era perfeita para projetar **circuitos elétricos**!

### 1.2 A Conexão: Lógica → Eletrônica → Computadores

```
    George Boole (1854)          Claude Shannon (1938)         Computadores Modernos
    ┌────────────────┐           ┌────────────────┐           ┌──────────────────┐
    │ Álgebra com    │  ──────→  │ Circuitos com  │  ──────→  │ Bilhões de       │
    │ VERDADEIRO e   │           │ interruptores  │           │ transistores      │
    │ FALSO          │           │ ON e OFF       │           │ fazendo 0 e 1    │
    └────────────────┘           └────────────────┘           └──────────────────┘
```

---

## 2. 💡 Valores Lógicos: Verdadeiro e Falso

Na álgebra booleana, existem apenas **dois valores possíveis**:

| Valor Lógico | Representação | Nível Elétrico | Exemplo |
|-------------|---------------|----------------|---------|
| **VERDADEIRO** | 1 (TRUE, HIGH, H) | +5V ou +3,3V | Interruptor ligado |
| **FALSO** | 0 (FALSE, LOW, L) | 0V (terra) | Interruptor desligado |

> 🎓 **Por que só 0 e 1?** Porque é muito mais fácil construir circuitos que distinguem entre dois estados (ligado/desligado, alta/baixa tensão) do que entre dez estados (0 a 9). Por isso o computador usa base 2!

---

## 3. ⚡ Portas Lógicas Básicas: AND, OR, NOT

### 3.1 Porta AND (E)

A saída é 1 **somente** quando **todas** as entradas são 1.

> 💡 **Analogia:** Dois interruptores em **série**. A lâmpada só acende se o primeiro **E** o segundo estiverem ligados.

```
    Interruptor A     Interruptor B     Lâmpada
    ───[  /  ]────────[  /  ]────────── 💡
    
    Ambos ligados → lâmpada acende!
    Qualquer um desligado → lâmpada apagada.
```

**Símbolo:**

```
    A ───┐
         │D──── S         S = A · B
    B ───┘
       AND
```

**Tabela Verdade:**

| A | B | S = A · B |
|---|---|-----------|
| 0 | 0 | **0** |
| 0 | 1 | **0** |
| 1 | 0 | **0** |
| 1 | 1 | **1** |

**Expressão:** `S = A · B` (lê-se "A **e** B")

---

### 3.2 Porta OR (OU)

A saída é 1 quando **pelo menos uma** entrada é 1.

> 💡 **Analogia:** Dois interruptores em **paralelo**. A lâmpada acende se o primeiro **OU** o segundo estiverem ligados.

```
         ┌───[  /  ]───┐
    ─────┤  Interr. A   ├─────── 💡
         └───[  /  ]───┘
              Interr. B
    
    Qualquer um ligado → lâmpada acende!
    Ambos desligados → lâmpada apagada.
```

**Símbolo:**

```
    A ───┐
         )──── S         S = A + B
    B ───┘
       OR
```

**Tabela Verdade:**

| A | B | S = A + B |
|---|---|-----------|
| 0 | 0 | **0** |
| 0 | 1 | **1** |
| 1 | 0 | **1** |
| 1 | 1 | **1** |

**Expressão:** `S = A + B` (lê-se "A **ou** B")

> ⚠️ **Cuidado:** O símbolo `+` aqui significa **OU lógico**, não adição aritmética! 1 + 1 = 1 (em lógica), não 2!

---

### 3.3 Porta NOT (NÃO / Inversor)

A saída é o **inverso** da entrada. É a única porta com **uma** entrada.

> 💡 **Analogia:** Um interruptor que funciona ao contrário — quando você liga, a lâmpada apaga; quando desliga, a lâmpada acende!

**Símbolo:**

```
    A ───[>o]──── S         S = Ā  (A barrado / NOT A)
         NOT
```

**Tabela Verdade:**

| A | S = Ā |
|---|-------|
| 0 | **1** |
| 1 | **0** |

**Expressão:** `S = Ā` ou `S = A'` ou `S = NOT(A)` ou `S = ¬A`

---

## 4. 🔧 Portas Universais: NAND e NOR

### 4.1 Por Que "Universais"?

As portas NAND e NOR são chamadas **universais** porque, usando **apenas NAND** (ou apenas NOR), é possível construir **qualquer outra porta lógica** — AND, OR, NOT, XOR, etc.

> 🎓 **Na prática:** Chips reais são frequentemente construídos usando apenas portas NAND (tecnologia CMOS), pois elas são mais eficientes em termos de transistores.

### 4.2 Porta NAND (NÃO-E)

É uma porta **AND seguida de NOT**. A saída é 0 somente quando **todas** as entradas são 1.

**Símbolo:**

```
    A ───┐
         │D─o── S         S = (A · B)̄  = NOT(A · B)
    B ───┘
       NAND
    (o bolinha indica inversão)
```

**Tabela Verdade:**

| A | B | A · B | S = NOT(A · B) |
|---|---|-------|----------------|
| 0 | 0 | 0 | **1** |
| 0 | 1 | 0 | **1** |
| 1 | 0 | 0 | **1** |
| 1 | 1 | 1 | **0** |

> Observe: é exatamente o **inverso** da AND!

### 4.3 Porta NOR (NÃO-OU)

É uma porta **OR seguida de NOT**. A saída é 1 somente quando **todas** as entradas são 0.

**Símbolo:**

```
    A ───┐
         )─o── S         S = (A + B)̄  = NOT(A + B)
    B ───┘
       NOR
    (o bolinha indica inversão)
```

**Tabela Verdade:**

| A | B | A + B | S = NOT(A + B) |
|---|---|-------|----------------|
| 0 | 0 | 0 | **1** |
| 0 | 1 | 1 | **0** |
| 1 | 0 | 1 | **0** |
| 1 | 1 | 1 | **0** |

> Observe: é exatamente o **inverso** da OR!

---

## 5. ✨ Portas Especiais: XOR e XNOR

### 5.1 Porta XOR (OU Exclusivo)

A saída é 1 quando as entradas são **diferentes**.

> 💡 **Analogia:** Uma escada com dois interruptores — um em cima, outro em baixo. A lâmpada muda de estado quando **qualquer um** dos dois é acionado, mas se ambos estiverem na mesma posição, a lâmpada fica no estado inicial.

**Símbolo:**

```
    A ───┐
         )⊕── S         S = A ⊕ B
    B ───┘
       XOR
```

**Tabela Verdade:**

| A | B | S = A ⊕ B |
|---|---|-----------|
| 0 | 0 | **0** |
| 0 | 1 | **1** |
| 1 | 0 | **1** |
| 1 | 1 | **0** |

**Expressão equivalente:** `S = A ⊕ B = Ā·B + A·B̄` (A diferente de B)

> 🎓 **Onde vimos o XOR?** Na **soma binária**! A saída do meio-somador: S = A ⊕ B. O XOR é o "somador natural" de 1 bit!

### 5.2 Porta XNOR (OU Exclusivo Negado / Coincidência)

A saída é 1 quando as entradas são **iguais**.

**Símbolo:**

```
    A ───┐
         )⊕o── S         S = A ⊙ B = NOT(A ⊕ B)
    B ───┘
       XNOR
    (o bolinha indica inversão)
```

**Tabela Verdade:**

| A | B | A ⊕ B | S = NOT(A ⊕ B) |
|---|---|-------|----------------|
| 0 | 0 | 0 | **1** |
| 0 | 1 | 1 | **0** |
| 1 | 0 | 1 | **0** |
| 1 | 1 | 0 | **1** |

**Expressão equivalente:** `S = A ⊙ B = A·B + Ā·B̄` (A igual a B)

> 💡 A XNOR é usada em **comparadores** — compara se dois bits são iguais!

---

## 6. 📋 Resumo de Todas as Portas

### 6.1 Tabela Comparativa Completa

| A | B | AND | OR | NOT A | NAND | NOR | XOR | XNOR |
|---|---|-----|-----|-------|------|-----|-----|------|
| 0 | 0 | 0 | 0 | 1 | 1 | 1 | 0 | 1 |
| 0 | 1 | 0 | 1 | 1 | 1 | 0 | 1 | 0 |
| 1 | 0 | 0 | 1 | 0 | 1 | 0 | 1 | 0 |
| 1 | 1 | 1 | 1 | 0 | 0 | 0 | 0 | 1 |

### 6.2 Tabela de Símbolos e Expressões

| Porta | Expressão | Leitura | Tipo |
|-------|-----------|---------|------|
| AND | S = A · B | "A e B" | Básica |
| OR | S = A + B | "A ou B" | Básica |
| NOT | S = Ā | "não A" / "A negado" | Básica |
| NAND | S = (A · B)̄ | "não (A e B)" | Universal |
| NOR | S = (A + B)̄ | "não (A ou B)" | Universal |
| XOR | S = A ⊕ B | "A ou-exclusivo B" | Especial |
| XNOR | S = A ⊙ B | "A coincidência B" | Especial |

### 6.3 Resumo Visual dos Símbolos

```
    AND:    ──┐          OR:    ──┐          NOT:   ──[>o]──
            │D──               )──
            ──┘                ──┘

    NAND:   ──┐          NOR:   ──┐          XOR:    ──┐
            │D─o──               )─o──               )⊕──
            ──┘                  ──┘                  ──┘

    XNOR:   ──┐
             )⊕o──
             ──┘
```

---

## 7. 📝 Notação de Expressões Booleanas

### 7.1 Símbolos Comuns

| Operação | Símbolos Comuns | Exemplo |
|----------|----------------|---------|
| AND | `·`, `∧`, `×`, `&`, sem símbolo (justaposição) | A·B, A∧B, AB |
| OR | `+`, `∨`, `|` | A+B, A∨B |
| NOT | barra (Ā), apóstrofo (A'), `¬`, `~`, `!` | Ā, A', ¬A |
| XOR | `⊕`, `^` | A⊕B, A^B |
| XNOR | `⊙`, `≡` | A⊙B |

### 7.2 Precedência de Operadores

Assim como na matemática (multiplicação antes da adição), a álgebra booleana tem precedência:

```
    1º  NOT     (maior precedência)
    2º  AND
    3º  OR      (menor precedência)
```

**Exemplo:** `A + B · C̄` é interpretado como `A + (B · (NOT C))`

```
    PASSO 1: NOT C  →  C̄
    PASSO 2: B AND C̄  →  B · C̄
    PASSO 3: A OR (B · C̄)  →  A + B · C̄
```

### 7.3 Exemplos de Expressões

| Expressão | Leitura | Resultado quando A=1, B=0, C=1 |
|-----------|---------|-------------------------------|
| A · B | A e B | 1 · 0 = **0** |
| A + B | A ou B | 1 + 0 = **1** |
| Ā | Não A | NOT 1 = **0** |
| A · B + C | (A e B) ou C | (1·0) + 1 = 0 + 1 = **1** |
| (A + B) · C | (A ou B) e C | (1+0) · 1 = 1 · 1 = **1** |
| A ⊕ B | A xor B | 1 ⊕ 0 = **1** |

---

## 8. 📐 Leis e Propriedades da Álgebra Booleana

### 8.1 Leis Fundamentais

| Lei | AND (·) | OR (+) |
|-----|---------|--------|
| **Identidade** | A · 1 = A | A + 0 = A |
| **Elemento nulo** | A · 0 = 0 | A + 1 = 1 |
| **Idempotência** | A · A = A | A + A = A |
| **Complemento** | A · Ā = 0 | A + Ā = 1 |
| **Involução** | NOT(NOT A) = A | |

### 8.2 Propriedades

| Propriedade | AND (·) | OR (+) |
|-------------|---------|--------|
| **Comutativa** | A · B = B · A | A + B = B + A |
| **Associativa** | (A · B) · C = A · (B · C) | (A + B) + C = A + (B + C) |
| **Distributiva** | A · (B + C) = A·B + A·C | A + (B · C) = (A+B) · (A+C) |

### 8.3 Teoremas de De Morgan ⭐

Os **Teoremas de De Morgan** são talvez as leis mais importantes da álgebra booleana:

```
    ┌─────────────────────────────────────────────────────┐
    │                                                     │
    │  1º Teorema:  NOT(A · B) = Ā + B̄                   │
    │               "O NOT do AND é o OR dos NOTs"        │
    │                                                     │
    │  2º Teorema:  NOT(A + B) = Ā · B̄                   │
    │               "O NOT do OR é o AND dos NOTs"        │
    │                                                     │
    └─────────────────────────────────────────────────────┘
```

**Verificação do 1º Teorema por tabela verdade:**

| A | B | A·B | NOT(A·B) | Ā | B̄ | Ā+B̄ | Iguais? |
|---|---|-----|----------|---|---|------|---------|
| 0 | 0 | 0 | 1 | 1 | 1 | 1 | ✓ |
| 0 | 1 | 0 | 1 | 1 | 0 | 1 | ✓ |
| 1 | 0 | 0 | 1 | 0 | 1 | 1 | ✓ |
| 1 | 1 | 1 | 0 | 0 | 0 | 0 | ✓ |

> ✅ Todas as linhas são iguais → Teorema verificado!

> 💡 **Dica para memorizar De Morgan:** "Quebre a barra, troque a operação!"
> - Barra sobre AND → separa em OR: NOT(A·B) = NOT(A) + NOT(B)
> - Barra sobre OR → separa em AND: NOT(A+B) = NOT(A) · NOT(B)

### 8.4 Absorção e Outras Identidades Úteis

| Nome | Identidade |
|------|-----------|
| Absorção 1 | A + A·B = A |
| Absorção 2 | A · (A+B) = A |
| Consenso | A·B + Ā·C + B·C = A·B + Ā·C |
| Simplificação | A·B + A·B̄ = A |

---

## 9. 🌍 Analogias com o Mundo Real

### 9.1 Portas Lógicas no Dia a Dia

| Porta | Analogia do Dia a Dia |
|-------|----------------------|
| **AND** | Cofre com duas chaves: precisa da chave A **e** da chave B para abrir |
| **OR** | Sala com duas portas: pode entrar pela porta A **ou** pela porta B |
| **NOT** | Interruptor invertido: quando está "ligado" no interruptor, a lâmpada apaga |
| **NAND** | Alarme de segurança: só desliga quando **ambos** os sensores estão OK |
| **NOR** | Trava de emergência: qualquer sensor ativado trava o sistema |
| **XOR** | Interruptor de escada: mudar qualquer interruptor muda o estado da luz |
| **XNOR** | Verificador de igualdade: acende LED verde quando dois sinais são iguais |

### 9.2 Exemplos Práticos

**🚗 Cinto de segurança do carro (AND):**
```
    Motor liga = chave_ignição · cinto_afivelado
    (precisa das DUAS condições para ligar)
```

**🔔 Campainha da escola (OR):**
```
    Campainha toca = botão_diretoria + timer_automático
    (qualquer um dos dois aciona a campainha)
```

**🔒 Sistema de segurança (NAND):**
```
    Alarme = NOT(sensor1 · sensor2)
    (se AMBOS os sensores detectarem "OK", alarme desliga)
    (se QUALQUER um falhar, alarme dispara)
```

---

## 10. 🎯 Por que Isso Importa?

### 10.1 Tudo no computador é feito com portas lógicas!

```
    Portas Lógicas → Circuitos → Somadores → ULA → Processador → Computador
```

| Componente | Portas Lógicas Usadas |
|-----------|----------------------|
| Meio-somador | 1 XOR + 1 AND |
| Somador completo | 2 XOR + 2 AND + 1 OR |
| Somador de 32 bits | ~160 portas |
| ULA completa | ~milhares de portas |
| Processador moderno | ~bilhões de transistores (portas) |

### 10.2 Portas Lógicas na Programação

Toda condição que você escreve em código usa lógica booleana:

```python
    # Python — operadores lógicos são portas lógicas!
    if idade >= 18 and habilitacao == True:    # AND
        print("Pode dirigir")
    
    if senha_correta or biometria_ok:          # OR
        print("Acesso liberado")
    
    if not bloqueado:                          # NOT
        print("Conta ativa")
```

---

## 11. 📌 Resumo

| Conceito | Resumo |
|----------|--------|
| Álgebra Booleana | Sistema lógico com dois valores (0 e 1), criado por George Boole |
| AND (·) | Saída = 1 somente quando **todas** as entradas = 1 |
| OR (+) | Saída = 1 quando **pelo menos uma** entrada = 1 |
| NOT (barra) | Inverte a entrada |
| NAND | NOT + AND → universal |
| NOR | NOT + OR → universal |
| XOR (⊕) | Saída = 1 quando entradas são **diferentes** |
| XNOR (⊙) | Saída = 1 quando entradas são **iguais** |
| De Morgan | NOT(A·B) = Ā+B̄ e NOT(A+B) = Ā·B̄ |
| Precedência | NOT > AND > OR |

---

## 12. 📚 Leitura Complementar

- 📖 TOCCI, R. J.; WIDMER, N. S. **Sistemas Digitais: Princípios e Aplicações**. Cap. 3 e 4 — Portas Lógicas e Álgebra Booleana.
- 📖 FLOYD, T. L. **Sistemas Digitais: Fundamentos e Aplicações**. Cap. 3 — Portas Lógicas.
- 📖 TANENBAUM, A. S. **Organização Estruturada de Computadores**. Cap. 3 — O Nível Lógico Digital.
- 📖 STALLINGS, W. **Arquitetura e Organização de Computadores**. Apêndice B — Lógica Digital.
- 🌐 [Logic.ly — Simulador de Portas Lógicas](https://logic.ly/)
- 🌐 [CircuitVerse — Simulador Online](https://circuitverse.org/)

---

> ⬅️ [Aula 06 — Subtração Binária](../06-operacao-aritmetica-subtracao/README.md) | [Exemplos](./exemplos/README.md) | [Exercícios](./exercicios/README.md) | [Aula 08 — Expressões e Circuitos](../08-portas-logicas-expressoes-booleanas-circuitos/README.md) ➡️
