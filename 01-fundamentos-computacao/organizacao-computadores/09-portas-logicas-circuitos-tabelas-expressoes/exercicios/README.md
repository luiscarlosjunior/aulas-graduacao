# 📝 Exercícios — Aula 09: Circuitos Lógicos, Tabelas da Verdade e Expressões Booleanas

> Exercícios práticos de conversão entre representações, mintermos/maxtermos e Mapas de Karnaugh

**Instruções:** Para exercícios com circuitos, desenhe em formato texto. Para Karnaugh, desenhe o mapa e mostre os agrupamentos. Sempre verifique seus resultados.

---

## 🟢 Nível Básico

### Exercício 1 — Tabela Verdade → Expressão SOP

Escreva a expressão na forma **Soma de Produtos (SOP)** para cada tabela:

**a)**

| A | B | S |
|---|---|---|
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 0 |

**b)**

| A | B | C | S |
|---|---|---|---|
| 0 | 0 | 0 | 1 |
| 0 | 0 | 1 | 0 |
| 0 | 1 | 0 | 1 |
| 0 | 1 | 1 | 1 |
| 1 | 0 | 0 | 0 |
| 1 | 0 | 1 | 0 |
| 1 | 1 | 0 | 1 |
| 1 | 1 | 1 | 0 |

---

### Exercício 2 — Tabela Verdade → Expressão POS

Para as **mesmas tabelas** do Exercício 1, escreva a expressão na forma **Produto de Somas (POS)**.

Compare: qual forma ficou mais simples em cada caso?

---

### Exercício 3 — Mintermos e Maxtermos

**a)** Para 3 variáveis (A, B, C), escreva o mintermo e o maxtermo para cada linha:

| Linha | A | B | C | Mintermo (mᵢ) | Maxtermo (Mᵢ) |
|-------|---|---|---|----------------|----------------|
| 0 | 0 | 0 | 0 | | |
| 1 | 0 | 0 | 1 | | |
| 2 | 0 | 1 | 0 | | |
| 3 | 0 | 1 | 1 | | |
| 4 | 1 | 0 | 0 | | |
| 5 | 1 | 0 | 1 | | |
| 6 | 1 | 1 | 0 | | |
| 7 | 1 | 1 | 1 | | |

**b)** Se S = Σm(0, 3, 5, 6), qual é a expressão na notação ΠM?

**c)** Se S = ΠM(1, 2, 4, 7), qual é a expressão na notação Σm?

---

### Exercício 4 — Circuito → Tabela Verdade

Construa a tabela verdade para cada circuito:

**a)**

```
    A ───┐
         │AND├──┐
    B ───┘      │
                │NOR├──── S
    C ──────────┘
```

**b)**

```
    A ──[NOT]──┐
               │OR├──┐
    B ─────────┘     │
                     │AND├──── S
    C ───────────────┘
```

---

## 🟡 Nível Intermediário

### Exercício 5 — Mapa de Karnaugh (3 Variáveis)

Simplifique usando Mapa de Karnaugh:

**a)** S(A,B,C) = Σm(0, 1, 2, 3)

```
                  BC
               00   01   11   10
            ┌─────┬─────┬─────┬─────┐
     A   0  │     │     │     │     │
            ├─────┼─────┼─────┼─────┤
         1  │     │     │     │     │
            └─────┴─────┴─────┴─────┘
```

**b)** S(A,B,C) = Σm(0, 2, 4, 6)

**c)** S(A,B,C) = Σm(1, 2, 5, 6)

**d)** S(A,B,C) = Σm(0, 1, 4, 5, 6, 7)

> ⚠️ Lembre-se: a ordem das colunas é **00, 01, 11, 10** (código Gray)!

---

### Exercício 6 — Mapa de Karnaugh (4 Variáveis)

Simplifique usando Mapa de Karnaugh:

**a)** S(A,B,C,D) = Σm(0, 1, 4, 5, 8, 9, 12, 13)

```
                     CD
                 00    01    11    10
              ┌──────┬──────┬──────┬──────┐
    AB  00    │      │      │      │      │
              ├──────┼──────┼──────┼──────┤
        01    │      │      │      │      │
              ├──────┼──────┼──────┼──────┤
        11    │      │      │      │      │
              ├──────┼──────┼──────┼──────┤
        10    │      │      │      │      │
              └──────┴──────┴──────┴──────┘
```

**b)** S(A,B,C,D) = Σm(0, 2, 8, 10)

**c)** S(A,B,C,D) = Σm(0, 1, 2, 3, 4, 5, 6, 7)

---

### Exercício 7 — Don't-Care

Simplifique usando Mapa de Karnaugh com condições don't-care:

**a)** S(A,B,C,D) = Σm(2, 6, 10, 14) + Σd(0, 4, 8, 12)

**b)** S(A,B,C,D) = Σm(1, 5, 7, 15) + Σd(3, 11, 13)

> 💡 **Dica:** Os don't-cares podem ser tratados como 1 ou 0 — escolha o que gerar grupos maiores!

---

### Exercício 8 — Conversão Completa (Tabela → Expressão → Circuito)

Dada a tabela verdade abaixo:

| A | B | C | S |
|---|---|---|---|
| 0 | 0 | 0 | 0 |
| 0 | 0 | 1 | 1 |
| 0 | 1 | 0 | 1 |
| 0 | 1 | 1 | 1 |
| 1 | 0 | 0 | 0 |
| 1 | 0 | 1 | 0 |
| 1 | 1 | 0 | 1 |
| 1 | 1 | 1 | 1 |

a) Escreva a expressão SOP canônica.

b) Escreva a expressão POS canônica.

c) Simplifique com Mapa de Karnaugh.

d) Desenhe o circuito da expressão simplificada.

e) Conte o número de portas do circuito original (sem simplificação) e do simplificado.

---

## 🔴 Nível Avançado

### Exercício 9 — Projeto: Detector de Números Divisíveis por 3

Um circuito recebe um número de 3 bits (A, B, C) representando valores de 0 a 7. A saída S = 1 quando o número é **divisível por 3** (considere 0 como divisível por 3).

a) Construa a tabela verdade.

b) Escreva a forma canônica SOP (Σm).

c) Simplifique usando Mapa de Karnaugh.

d) Desenhe o circuito simplificado.

e) Qual seria o circuito na forma POS?

---

### Exercício 10 — Projeto: Sistema de Controle de Acesso

Uma porta de segurança tem 4 sensores (A, B, C, D). A porta abre (S=1) quando:

- Pelo menos 3 sensores estão ativados
- **OU** quando os sensores A e D estão ativados simultaneamente

a) Construa a tabela verdade completa (16 linhas).

b) Derive a expressão SOP.

c) Simplifique com Mapa de Karnaugh 4 variáveis.

d) Desenhe o circuito simplificado.

e) Quantas portas foram economizadas com a simplificação?

---

### Exercício 11 — Verdadeiro ou Falso

| | Afirmação | V/F |
|---|-----------|-----|
| a) | O mintermo m₅ para 3 variáveis (A,B,C) é A·B̄·C | |
| b) | O maxtermo M₃ para 3 variáveis (A,B,C) é A+B̄+C̄ | |
| c) | Se S = Σm(1,3,5,7), então S = ΠM(0,2,4,6) | |
| d) | No K-Map de 4 variáveis, as 4 células dos cantos formam um grupo válido | |
| e) | Um grupo de 4 células no K-Map elimina 2 variáveis | |
| f) | Condições don't-care DEVEM ser tratadas como 1 no K-Map | |
| g) | A forma SOP usa portas OR no primeiro nível e AND no segundo | |
| h) | O K-Map de 3 variáveis tem 6 células | |
| i) | Dois circuitos com expressões diferentes podem ser equivalentes | |
| j) | A forma POS é derivada das linhas onde a saída é 1 | |

---

### Exercício 12 — Desafio: Display BCD para 7 Segmentos

Um display de 7 segmentos recebe um número BCD (4 bits: A, B, C, D) representando dígitos de 0 a 9. As combinações 10–15 são don't-care.

```
     ─a─
    |   |
    f   b
    |   |
     ─g─
    |   |
    e   c
    |   |
     ─d─
```

| Dígito | A | B | C | D | a | b | c | d | e | f | g |
|--------|---|---|---|---|---|---|---|---|---|---|---|
| 0 | 0 | 0 | 0 | 0 | 1 | 1 | 1 | 1 | 1 | 1 | 0 |
| 1 | 0 | 0 | 0 | 1 | 0 | 1 | 1 | 0 | 0 | 0 | 0 |
| 2 | 0 | 0 | 1 | 0 | 1 | 1 | 0 | 1 | 1 | 0 | 1 |
| 3 | 0 | 0 | 1 | 1 | 1 | 1 | 1 | 1 | 0 | 0 | 1 |
| 4 | 0 | 1 | 0 | 0 | 0 | 1 | 1 | 0 | 0 | 1 | 1 |
| 5 | 0 | 1 | 0 | 1 | 1 | 0 | 1 | 1 | 0 | 1 | 1 |
| 6 | 0 | 1 | 1 | 0 | 1 | 0 | 1 | 1 | 1 | 1 | 1 |
| 7 | 0 | 1 | 1 | 1 | 1 | 1 | 1 | 0 | 0 | 0 | 0 |
| 8 | 1 | 0 | 0 | 0 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |
| 9 | 1 | 0 | 0 | 1 | 1 | 1 | 1 | 1 | 0 | 1 | 1 |

a) Encontre a expressão simplificada para o segmento **a** usando Karnaugh (4 variáveis com don't-care).

b) Encontre a expressão simplificada para o segmento **d**.

c) Desenhe o circuito para o segmento **a**.

> 💡 Este é um projeto clássico de eletrônica digital! Decodificadores BCD-para-7-segmentos são usados em relógios, calculadoras e painéis eletrônicos.

---

> 💡 **Dica geral:** Ao resolver exercícios de Karnaugh, sempre verifique se há adjacências nas bordas do mapa. É o erro mais comum entre estudantes!

---

> ⬅️ [Exemplos](../exemplos/README.md) | [Voltar para a Aula](../README.md)
