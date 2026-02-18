# ✏️ Exercícios - Aula 14

## Memória Principal, Cache, Magnéticas e Ópticas

---

### 📌 Instruções

- Responda cada exercício de forma completa e justificada
- Mostre todos os cálculos passo a passo
- Desenhe diagramas quando solicitado
- Exercícios marcados com ⭐ são de maior dificuldade

---

### Exercício 1 - SRAM vs. DRAM

a) Desenhe (em forma de diagrama de texto) a estrutura de uma célula SRAM e de uma célula DRAM. Identifique os componentes de cada uma.

b) Explique por que a DRAM precisa de refresh e a SRAM não.

c) Por que a DRAM é usada como memória principal e não a SRAM? Considere custo, densidade e consumo de energia.

d) Se um chip DRAM tem 8 Gbit de capacidade e precisa fazer refresh de todas as linhas a cada 64 ms, e cada linha leva 50 ns para refresh, calcule o overhead de refresh em porcentagem do tempo total. Considere que o chip tem 131.072 linhas.

---

### Exercício 2 - Tipos de ROM

Complete a tabela comparativa:

| Tipo | Gravação | Apagamento | Nº de Regravações | Exemplo de Uso |
|------|----------|------------|-------------------|----------------|
| ROM | ? | ? | ? | ? |
| PROM | ? | ? | ? | ? |
| EPROM | ? | ? | ? | ? |
| EEPROM | ? | ? | ? | ? |
| Flash NAND | ? | ? | ? | ? |

---

### Exercício 3 - Endereçamento de Memória

a) Um processador tem barramento de endereços de 24 bits e cada endereço aponta para 1 byte. Qual a capacidade máxima de memória endereçável?

b) Se o mesmo processador tivesse palavras de 16 bits (2 bytes) em vez de 8 bits, qual seria a capacidade endereçável?

c) Quantos bits de endereço são necessários para endereçar 512 MB de memória byte a byte?

d) Um chip de memória tem a especificação "16M × 8". Quantos pinos de endereço e quantos pinos de dados ele precisa?

---

### Exercício 4 - Mapeamento Direto de Cache

Uma cache com mapeamento direto possui as seguintes características:

- Tamanho da cache: 32 KB
- Tamanho do bloco (linha): 64 bytes
- Endereço de memória: 32 bits

a) Quantas linhas a cache possui?

b) Quantos bits são usados para Offset, Índice e Tag?

c) Decomponha o endereço `0xABCD1234` em Tag, Índice e Offset.

d) Dois endereços `0x00001000` e `0x00009000` podem estar na cache ao mesmo tempo? Justifique.

---

### Exercício 5 - Mapeamento Associativo por Conjunto ⭐

Uma cache 4-way set-associative possui:

- Tamanho total: 128 KB
- Tamanho do bloco: 32 bytes
- Endereço de memória: 32 bits

a) Quantas linhas a cache possui no total?

b) Quantos conjuntos (sets) existem?

c) Determine os bits de Tag, Índice e Offset.

d) Simule os acessos à sequência de endereços de bloco: **0, 8, 0, 16, 24, 8, 0** considerando que todos mapeiam para o mesmo conjunto. Use política LRU. Para cada acesso, indique Hit ou Miss e o estado da cache.

---

### Exercício 6 - Políticas de Substituição

Dada uma cache totalmente associativa com **3 linhas** e a seguinte sequência de acessos a blocos:

```
A, B, C, A, D, B, E, C, A
```

Simule o funcionamento da cache usando **cada** política abaixo, indicando Hit/Miss a cada passo:

a) LRU (Least Recently Used)

b) FIFO (First In, First Out)

c) Compare o hit rate obtido em cada política.

---

### Exercício 7 - Políticas de Escrita

a) Explique a diferença entre Write-Through e Write-Back.

b) Um programa executa 500.000 instruções de escrita. O cache hit rate para escritas é 96%. Compare o número de acessos à memória principal para:
   - Write-Through
   - Write-Back (assuma que 30% das linhas substituídas são "dirty")

c) Em qual cenário Write-Through é preferível? E Write-Back?

d) O que é um Write Buffer e como ele melhora o desempenho do Write-Through?

---

### Exercício 8 - Disco Rígido (HDD)

Um disco rígido tem as seguintes especificações:

- Velocidade de rotação: 10.000 RPM
- Seek time médio: 6 ms
- Setores por trilha: 500
- Bytes por setor: 512 bytes
- Taxa de transferência: 200 MB/s

a) Calcule a latência rotacional média.

b) Calcule o tempo de transferência de 1 setor.

c) Calcule o tempo total de acesso a um setor aleatório.

d) Quantos acessos aleatórios a setores individuais o disco pode fazer por segundo (IOPS)?

e) Compare com um SSD NVMe que tem latência de acesso de 20 μs. Quantas vezes mais IOPS o SSD consegue?

---

### Exercício 9 - SSD e NAND Flash ⭐

a) Explique a diferença entre SLC, MLC, TLC e QLC em termos de bits por célula, velocidade, durabilidade e custo.

b) Um SSD TLC de 1 TB tem especificação de 600 TBW (TeraBytes Written). Se o usuário escreve em média 50 GB por dia, em quantos anos o SSD atingirá o limite de escrita?

c) O que é "wear leveling" e por que é importante em SSDs?

d) Por que SSDs são muito mais rápidos que HDDs para acessos aleatórios, mas a diferença é menor para acessos sequenciais?

---

### Exercício 10 - Armazenamento Óptico

a) Explique como um disco óptico (CD/DVD/Blu-ray) armazena dados usando os conceitos de "pit" e "land".

b) Por que o Blu-ray consegue armazenar mais dados que o DVD no mesmo tamanho físico de disco?

c) Complete a tabela:

| Mídia | Capacidade | Comprimento de Onda do Laser | Velocidade 1x |
|-------|------------|------------------------------|---------------|
| CD | ? | ? | ? |
| DVD | ? | ? | ? |
| Blu-ray | ? | ? | ? |

d) Cite duas vantagens e duas desvantagens dos discos ópticos em comparação com pen drives USB.

---

### Exercício 11 - Comparação Geral de Tecnologias ⭐

Você precisa projetar o sistema de armazenamento para três cenários diferentes. Para cada cenário, indique qual(is) tecnologia(s) de memória usar e justifique:

a) **Servidor de banco de dados** que precisa de acesso rápido a milhões de registros aleatórios e não pode perder dados.

b) **Câmera de segurança** que grava vídeo 24/7 por 30 dias e depois sobrescreve os dados antigos.

c) **Sistema embarcado em um satélite** que precisa armazenar firmware de forma confiável em condições extremas de radiação e temperatura.

d) **Computador de um estudante** com orçamento limitado que precisa de bom desempenho para programação e armazenamento de arquivos.

---

### Exercício 12 - Questão Integradora ⭐

Considere um sistema computacional com a seguinte configuração:

- CPU: 3 GHz, CPI ideal = 1,0
- Cache L1: 64 KB, hit rate = 96%, hit time = 1 ciclo
- Cache L2: 512 KB, hit rate local = 90%, hit time = 10 ciclos
- RAM: 16 GB DDR4, tempo de acesso = 200 ciclos
- SSD NVMe: 1 TB, tempo de acesso = 50.000 ciclos
- 35% das instruções são de acesso à memória

a) Calcule o AMAT considerando apenas L1, L2 e RAM.

b) Calcule o CPI real do processador.

c) Se o programa tem 10 bilhões de instruções, quanto tempo leva para executar?

d) Se o hit rate da L1 fosse melhorado de 96% para 99%, qual seria o novo tempo de execução?

e) Discuta: Vale mais a pena melhorar o hit rate da L1 de 96% para 99%, ou melhorar o hit rate da L2 de 90% para 95%? Justifique com cálculos.

---

## 📊 Gabarito Resumido

<details>
<summary>Clique para ver as respostas resumidas</summary>

**Exercício 3:**
- a) 2²⁴ = 16.777.216 bytes = **16 MB**
- b) 2²⁴ × 2 = **32 MB**
- c) 512 MB = 2²⁹ → **29 bits**
- d) 16M = 2²⁴ → **24 pinos de endereço**; 8 bits → **8 pinos de dados**

**Exercício 4:**
- a) 32 KB / 64 bytes = **512 linhas**
- b) Offset: 6 bits; Índice: 9 bits; Tag: 17 bits
- c) 0xABCD1234 = Tag: 0x55E6, Índice: 0x048, Offset: 0x34
- d) 0x00001000 → Índice = 0x040; 0x00009000 → Índice = 0x240. **Sim**, podem coexistir (índices diferentes).

**Exercício 5:**
- a) 128 KB / 32 bytes = **4096 linhas**
- b) 4096 / 4 = **1024 conjuntos**
- c) Offset: 5 bits; Índice: 10 bits; Tag: 17 bits

**Exercício 8:**
- a) 10.000 RPM → 6.000 ms/60 = 6 ms/rotação → latência média = **3 ms**
- b) 512 / (200 × 10⁶) = **2,56 μs**
- c) T = 6 + 3 + 0,00256 = **~9 ms**
- d) 1000/9 ≈ **111 IOPS**
- e) SSD: 1000/0,02 = **50.000 IOPS** → ~450x mais IOPS

**Exercício 9b:**
- 600 TB / 50 GB por dia = 12.000 dias = **~32,9 anos**

**Exercício 12:**
- a) AMAT = 1 + 0,04 × (10 + 0,10 × 200) = 1 + 0,04 × 30 = **2,2 ciclos**
- b) CPI = 1,0 + 0,35 × (2,2 - 1) = 1,0 + 0,42 = **1,42 ciclos**
- c) T = 10¹⁰ × 1,42 / (3 × 10⁹) = **~4,73 segundos**
- d) Com L1 99%: AMAT = 1 + 0,01 × 30 = 1,3; CPI = 1,0 + 0,35 × 0,3 = 1,105; T = **~3,68 s**

</details>

---

> 💡 **Dica:** Para exercícios de cache, sempre comece determinando os bits de Offset, depois Índice e por último Tag. Lembre-se: Offset é determinado pelo tamanho do bloco, Índice pelo número de linhas (ou conjuntos), e Tag é o restante.
