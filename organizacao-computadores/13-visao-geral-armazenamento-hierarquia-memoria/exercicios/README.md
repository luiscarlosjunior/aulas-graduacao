# ✏️ Exercícios - Aula 13

## Hierarquia de Memória e Sistema de Armazenamento

---

### 📌 Instruções

- Responda cada exercício de forma completa e justificada
- Mostre todos os cálculos quando solicitado
- Use os conceitos e fórmulas apresentados na aula
- Exercícios marcados com ⭐ são de maior dificuldade

---

### Exercício 1 - Conceitos Fundamentais

Explique com suas próprias palavras:

a) Por que é impossível construir uma memória que seja simultaneamente muito rápida, muito grande e muito barata?

b) Qual é a solução que os projetistas de computadores encontraram para esse problema?

c) Dê uma analogia do seu dia a dia para a hierarquia de memória.

---

### Exercício 2 - Identificação dos Níveis

Complete a tabela abaixo com as informações corretas:

| Nível | Tecnologia | Tempo de Acesso | Capacidade Típica | Volátil? |
|-------|------------|-----------------|--------------------|---------:|
| 1 | ? | < 1 ns | ? bytes | ? |
| 2 | Cache L1 | ? | ? KB | ? |
| 3 | ? | 3-10 ns | 256 KB - 1 MB | ? |
| 4 | Cache L3 | ? | ? | Sim |
| 5 | ? | 50-100 ns | 4-64 GB | ? |
| 6 | ? | 25-250 μs | ? | Não |
| 7 | HDD | ? | ? | ? |

---

### Exercício 3 - Cálculo de AMAT (Nível Básico)

Um sistema possui as seguintes características:

- Cache Hit Time: 3 ns
- Cache Hit Rate: 92%
- Tempo de acesso à memória principal (Miss Penalty): 80 ns

a) Calcule o AMAT (Average Memory Access Time).

b) Qual seria o AMAT se o hit rate fosse melhorado para 98%?

c) Qual é o speedup obtido com a melhoria do hit rate de 92% para 98%?

d) Compare o AMAT nos dois cenários com o tempo de acesso direto à memória principal (sem cache).

---

### Exercício 4 - Cálculo de AMAT (Dois Níveis de Cache) ⭐

Um processador possui o seguinte sistema de memória:

- Cache L1: Hit Time = 1 ns, Hit Rate = 94%
- Cache L2: Hit Time = 5 ns, Hit Rate local = 85%
- Memória Principal: Tempo de Acesso = 120 ns

a) Calcule o AMAT considerando os dois níveis de cache.

b) Calcule o AMAT se houvesse apenas a Cache L1 (sem L2).

c) Qual o benefício percentual de ter o segundo nível de cache?

d) Se o custo de adicionar a Cache L2 aumenta o preço do processador em 15%, vale a pena? Justifique.

---

### Exercício 5 - Princípios de Localidade

Para cada trecho de código abaixo, identifique e justifique qual(is) tipo(s) de localidade está(ão) presente(s):

**Código A:**
```c
int soma = 0;
for (int i = 0; i < 100; i++) {
    soma += array[i];
}
```

**Código B:**
```c
for (int rep = 0; rep < 1000; rep++) {
    x = x * 2 + 1;
}
```

**Código C:**
```c
for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
        resultado += matriz[j][i]; // Note: j varia primeiro
    }
}
```

**Código D:**
```c
int fibonacci[100];
fibonacci[0] = 0;
fibonacci[1] = 1;
for (int i = 2; i < 100; i++) {
    fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
}
```

---

### Exercício 6 - Padrões de Acesso

Classifique cada situação abaixo quanto ao padrão de acesso à memória (sequencial, aleatório, strided ou loop) e avalie se o desempenho da cache será bom ou ruim:

a) Reproduzir um arquivo de vídeo do início ao fim

b) Consultar registros aleatórios em um banco de dados por chave primária

c) Percorrer uma lista encadeada alocada dinamicamente na memória

d) Acessar cada 5º elemento de um vetor (passo = 5)

e) Executar repetidamente um laço `while` com 20 instruções

f) Leitura de uma planilha Excel linha por linha

---

### Exercício 7 - Comparação de Tecnologias

Compare as seguintes tecnologias de armazenamento preenchendo a tabela:

| Critério | SRAM | DRAM | SSD (NAND Flash) | HDD |
|----------|------|------|-------------------|-----|
| Velocidade (classificação 1-4) | ? | ? | ? | ? |
| Capacidade (classificação 1-4) | ? | ? | ? | ? |
| Custo por GB (classificação 1-4) | ? | ? | ? | ? |
| Volatilidade | ? | ? | ? | ? |
| Partes mecânicas | ? | ? | ? | ? |
| Consumo de energia (relativo) | ? | ? | ? | ? |

*(1 = melhor/maior, 4 = pior/menor para velocidade e capacidade)*
*(1 = mais barato, 4 = mais caro para custo)*

---

### Exercício 8 - Problema do Memory Wall ⭐

Considere que em 1990:
- Velocidade do processador: 100 MHz
- Tempo de acesso à DRAM: 100 ns

E que a cada ano:
- Velocidade do processador aumenta 50%
- Velocidade da DRAM melhora 7%

a) Calcule a velocidade do processador e o tempo de acesso à DRAM em 2000 (10 anos depois).

b) Quantos ciclos de clock o processador fica "parado" esperando a memória em 1990 e em 2000?

c) Explique por que esse fenômeno é chamado de "Memory Wall" (Muro da Memória).

d) Como a hierarquia de memória (especificamente a cache) ajuda a mitigar esse problema?

---

### Exercício 9 - Cenário Real: Upgrade de Computador

Um estudante tem um notebook com as seguintes especificações:

- CPU: Intel i5 (Cache L1: 64 KB, L2: 256 KB, L3: 6 MB)
- RAM: 4 GB DDR4
- Armazenamento: HDD 1 TB (5400 RPM)

Ele reclama que o computador está lento para:
1. Abrir programas (demora 30-60 segundos)
2. Alternar entre muitos aplicativos (o sistema "trava")
3. Compilar projetos de programação (muito demorado)

Para cada problema:

a) Identifique qual nível da hierarquia de memória é o gargalo.

b) Sugira uma melhoria de hardware que resolveria o problema, justificando com base nos conceitos da hierarquia.

c) Estime o ganho de desempenho esperado com cada melhoria.

---

### Exercício 10 - Simulação de Cache

Considere uma cache com **4 blocos**, onde cada bloco armazena **2 elementos**. Os seguintes endereços são acessados em sequência:

```
Sequência de acessos: 0, 1, 2, 3, 4, 5, 0, 1, 6, 7, 0, 1
```

Para mapeamento direto onde: `bloco_cache = (endereço / 2) mod 4`

a) Simule todos os acessos, indicando Hit ou Miss para cada um.

b) Calcule o Hit Rate final.

c) Identifique se houve algum conflito de mapeamento (dois endereços competindo pelo mesmo bloco).

d) Qual seria o Hit Rate se a cache tivesse 8 blocos em vez de 4?

---

### Exercício 11 - CPI e Stalls de Memória ⭐

Um processador com clock de 2,5 GHz executa um programa com as seguintes características:

- CPI ideal (sem stalls de memória): 1,2 ciclos
- 40% das instruções são de acesso à memória (load/store)
- Cache L1 hit rate: 93%
- L1 miss, L2 hit rate: 80% (miss penalty: 10 ciclos)
- L2 miss, acesso à RAM (miss penalty: 100 ciclos)

a) Calcule o número médio de stall cycles por instrução de memória.

b) Calcule o CPI real do processador.

c) Qual é o tempo médio por instrução?

d) Se o programa tem 1 bilhão de instruções, quanto tempo leva para executar?

e) Qual seria o tempo de execução se o hit rate da L1 fosse melhorado para 98%?

---

### Exercício 12 - Questão Dissertativa ⭐

Considere a seguinte afirmação:

> *"A hierarquia de memória é uma das invenções mais importantes da computação, pois cria a ilusão de uma memória simultaneamente grande e rápida, algo que seria fisicamente impossível com uma única tecnologia."*

Escreva um texto de 15 a 20 linhas que:

a) Explique por que é impossível ter uma memória única que seja grande e rápida ao mesmo tempo.

b) Descreva como a hierarquia de memória resolve esse problema.

c) Explique o papel dos princípios de localidade no funcionamento da hierarquia.

d) Dê um exemplo prático de como a hierarquia de memória afeta a experiência do usuário no dia a dia.

---

## 📊 Gabarito Resumido

<details>
<summary>Clique para ver as respostas resumidas</summary>

**Exercício 3:**
- a) AMAT = 3 + 0,08 × 80 = 3 + 6,4 = **9,4 ns**
- b) AMAT = 3 + 0,02 × 80 = 3 + 1,6 = **4,6 ns**
- c) Speedup = 9,4 / 4,6 = **2,04x**
- d) Sem cache: 80 ns. Com cache (92%): 9,4 ns → 8,5x. Com cache (98%): 4,6 ns → 17,4x

**Exercício 4:**
- a) AMAT = 1 + 0,06 × (5 + 0,15 × 120) = 1 + 0,06 × 23 = **2,38 ns**
- b) AMAT = 1 + 0,06 × 120 = **8,2 ns**
- c) Benefício: (8,2 - 2,38) / 8,2 = **71% de melhoria**

**Exercício 8:**
- a) CPU 2000: 100 × 1,5¹⁰ ≈ 5.767 MHz ≈ **5,77 GHz**; DRAM 2000: 100 / 1,07¹⁰ ≈ **50,8 ns**
- b) 1990: 100 MHz → 10 ns/ciclo → 100/10 = **10 ciclos**; 2000: 5.770 MHz → 0,173 ns/ciclo → 50,8/0,173 ≈ **294 ciclos**

**Exercício 10:**
- a) Sequência: M, H, M, H, M, H, M, H, M, H, H, H (depende do mapeamento exato)
- b) Hit Rate depende do mapeamento e conflitos

**Exercício 11:**
- a) Stalls = 0,07 × 10 + 0,07 × 0,20 × 100 = 0,7 + 1,4 = **2,1 ciclos por instrução de memória**
- b) CPI real = 1,2 + 0,40 × 2,1 = 1,2 + 0,84 = **2,04 ciclos**
- c) T = 2,04 / 2,5×10⁹ = **0,816 ns** por instrução
- d) Tempo total = 10⁹ × 0,816 ns = **0,816 segundos**

</details>

---

> 💡 **Dica:** Para os exercícios de cálculo, sempre organize os dados antes de aplicar a fórmula. Identifique claramente Hit Time, Hit Rate, Miss Rate e Miss Penalty.
