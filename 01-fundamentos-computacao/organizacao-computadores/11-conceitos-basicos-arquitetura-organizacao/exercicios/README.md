# 📝 Exercícios — Aula 11: Conceitos Básicos de Arquitetura e Organização

> Exercícios sobre arquitetura vs organização, Von Neumann, Harvard, desempenho e Lei de Amdahl

**Instruções:** Para cálculos, mostre todos os passos. Indique as unidades nas respostas. Use as fórmulas fornecidas na aula.

---

## 🟢 Nível Básico

### Exercício 1 — Arquitetura vs Organização

Classifique cada item como pertencente à **Arquitetura (A)** ou **Organização (O)**:

| Item | A ou O |
|------|--------|
| a) Conjunto de instruções (ISA) do processador | |
| b) Tamanho da cache L2 | |
| c) Tipos de dados suportados (inteiro, float) | |
| d) Frequência do clock (3.5 GHz) | |
| e) Modos de endereçamento | |
| f) Número de estágios do pipeline | |
| g) Formato das instruções (32 bits) | |
| h) Tecnologia de fabricação (7nm) | |

---

### Exercício 2 — Von Neumann vs Harvard

**a)** Complete a tabela:

| Característica | Von Neumann | Harvard |
|---------------|-------------|---------|
| Memórias | | |
| Barramentos | | |
| Acesso simultâneo a instrução e dado | | |
| Complexidade de hardware | | |
| Exemplo de uso | | |

**b)** Explique o "Gargalo de Von Neumann" em suas próprias palavras.

**c)** O que é a "Arquitetura Harvard Modificada" e por que é usada na prática?

---

### Exercício 3 — Componentes do Computador

Associe cada componente à sua função:

| Componente | Função |
|-----------|--------|
| a) ULA | ( ) Coordena a execução de instruções |
| b) UC | ( ) Armazena o endereço da próxima instrução |
| c) PC | ( ) Realiza operações aritméticas e lógicas |
| d) IR | ( ) Armazena a instrução sendo executada |
| e) MAR | ( ) Conecta CPU, memória e E/S |
| f) Barramento | ( ) Armazena o endereço de memória a ser acessado |

---

### Exercício 4 — Ciclo de Instrução

Ordene corretamente as fases do ciclo de instrução:

( ) Execução  
( ) Decodificação  
( ) Busca  

Para a instrução `SUB R1, R2, R3` (R1 ← R2 - R3), descreva o que acontece em cada fase.

---

## 🟡 Nível Intermediário

### Exercício 5 — Cálculo de Tempo de Execução

**a)** Um programa tem 200 milhões de instruções, CPI médio = 2.5 e clock de 3 GHz. Calcule o tempo de execução.

**b)** Outro programa no mesmo processador tem 150 milhões de instruções e CPI médio = 3.0. Qual programa é mais rápido?

**c)** Calcule o MIPS do processador em cada caso.

---

### Exercício 6 — CPI Médio

Um processador executa um programa com a seguinte distribuição de instruções:

| Tipo | Frequência | CPI |
|------|-----------|-----|
| Aritmética | 50% | 1 |
| Memória (LOAD/STORE) | 25% | 4 |
| Desvio (BRANCH) | 15% | 2 |
| Ponto flutuante | 10% | 6 |

**a)** Calcule o CPI médio.

**b)** Se o clock é 2.5 GHz e o programa tem 80 milhões de instruções, qual é o tempo de execução?

**c)** Se você pudesse reduzir o CPI de memória de 4 para 2 (com cache melhor), qual seria o novo CPI médio e o novo tempo de execução?

**d)** Qual é a melhoria percentual?

---

### Exercício 7 — Comparação de Processadores

Dois processadores executam o MESMO programa:

| | Processador X | Processador Y |
|---|---------------|---------------|
| Clock | 4.0 GHz | 2.5 GHz |
| CPI | 2.0 | 1.2 |

**a)** Calcule o MIPS de cada processador.

**b)** Para um programa de 500 milhões de instruções, calcule o tempo de execução em cada um.

**c)** Qual é mais rápido? Calcule o speedup.

**d)** Um processador com clock mais alto é **sempre** mais rápido? Justifique.

---

### Exercício 8 — Lei de Amdahl

**a)** Um programa gasta 70% do tempo em cálculos e 30% em acesso à memória. Se o acesso à memória for acelerado por um fator de 3, qual é o speedup total?

**b)** Qual seria o speedup se o acesso à memória fosse infinitamente rápido?

**c)** Se, em vez disso, os cálculos fossem acelerados por um fator de 2, qual seria o speedup?

**d)** Qual melhoria (memória 3× ou cálculo 2×) dá maior benefício?

---

## 🔴 Nível Avançado

### Exercício 9 — Lei de Amdahl: Paralelização

Um programa é composto de:
- 10% de código sequencial (não paralelizável)
- 90% de código paralelizável

**a)** Calcule o speedup para 2, 4, 8, 16, 64 e 1024 processadores.

**b)** Qual é o speedup máximo teórico (infinitos processadores)?

**c)** Preencha a tabela e observe a tendência:

| Processadores | Speedup | Eficiência (%) |
|--------------|---------|----------------|
| 1 | | |
| 2 | | |
| 4 | | |
| 8 | | |
| 16 | | |
| 64 | | |
| 1024 | | |
| ∞ | | |

**d)** A partir de quantos processadores o ganho adicional se torna desprezível (< 5% de melhoria ao dobrar)?

---

### Exercício 10 — Projeto de Desempenho

Uma empresa tem duas opções para melhorar o desempenho de um servidor:

**Opção A:** Novo processador com clock 50% maior (mesmo CPI)  
**Opção B:** Novo sistema de memória que reduz o CPI de instruções de memória de 5 para 2

Distribuição de instruções do programa:

| Tipo | Frequência | CPI |
|------|-----------|-----|
| Aritmética | 45% | 1 |
| Memória | 35% | 5 |
| Desvio | 20% | 2 |

**a)** Calcule o CPI médio atual.

**b)** Calcule o tempo de execução atual (programa de 1 bilhão de instruções, clock 3 GHz).

**c)** Calcule o tempo de execução com a Opção A.

**d)** Calcule o CPI médio e tempo de execução com a Opção B.

**e)** Qual opção oferece melhor speedup?

**f)** Quanto custaria implementar a Opção A + B juntas?

---

### Exercício 11 — Verdadeiro ou Falso

| | Afirmação | V/F |
|---|-----------|-----|
| a) | Dois processadores com a mesma ISA sempre têm o mesmo desempenho | |
| b) | No modelo de Von Neumann, instruções e dados ficam na mesma memória | |
| c) | A arquitetura Harvard elimina completamente o gargalo de memória | |
| d) | MIPS é sempre uma boa métrica para comparar processadores diferentes | |
| e) | A Lei de Amdahl mostra que o speedup é limitado pela parte não melhorável | |
| f) | Cache é uma solução para o gargalo de Von Neumann | |
| g) | RISC sempre executa programas mais rápido que CISC | |
| h) | O ciclo de instrução tem 3 fases: busca, decodificação e execução | |
| i) | Aumentar o clock sempre melhora o desempenho proporcionalmente | |
| j) | A arquitetura Harvard modificada combina caches separados com memória unificada | |

---

### Exercício 12 — Análise Crítica

**a)** Um vendedor afirma que seu processador de 5 GHz é "duas vezes mais rápido" que um de 2.5 GHz. Essa afirmação é necessariamente verdadeira? Explique usando o conceito de CPI.

**b)** Por que os smartphones modernos usam processadores ARM (RISC) em vez de x86 (CISC)? Considere aspectos de consumo de energia, desempenho e organização.

**c)** Explique por que a Lei de Amdahl é importante para engenheiros de software, não apenas para engenheiros de hardware.

**d)** Pesquise: qual é a arquitetura (ISA) do processador do computador/smartphone que você está usando agora? É CISC ou RISC?

---

> 💡 **Dica geral:** Nos cálculos de desempenho, preste atenção às unidades! GHz = 10⁹ Hz, MHz = 10⁶ Hz. Um erro comum é misturar as ordens de grandeza.

---

> ⬅️ [Exemplos](../exemplos/README.md) | [Voltar para a Aula](../README.md)
