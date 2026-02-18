# ✏️ Exercícios - Aula 15

## Sistema de Entrada e Saída (E/S)

---

### 📌 Instruções

- Responda cada exercício de forma completa e justificada
- Mostre cálculos quando solicitado
- Compare técnicas usando os critérios discutidos em aula
- Exercícios marcados com ⭐ são de maior dificuldade

---

### Exercício 1 - Conceitos Fundamentais

a) Explique por que os dispositivos de E/S não podem se conectar diretamente ao barramento do sistema. Cite pelo menos 3 razões.

b) Quais são as 5 funções principais de um módulo de E/S?

c) Por que o sistema de E/S é frequentemente o "gargalo" de desempenho em um computador?

d) Descreva os 3 registradores internos de um módulo de E/S e a função de cada um.

---

### Exercício 2 - Classificação de Dispositivos

Classifique cada dispositivo abaixo nas categorias **legível por humano**, **legível por máquina** ou **comunicação**. Além disso, indique se é dispositivo de **entrada**, **saída** ou **entrada/saída**:

| Dispositivo | Categoria | Direção |
|------------|-----------|---------|
| Teclado | ? | ? |
| SSD NVMe | ? | ? |
| Monitor HDMI | ? | ? |
| Placa de rede Ethernet | ? | ? |
| Scanner | ? | ? |
| Sensor de temperatura | ? | ? |
| Pen drive USB | ? | ? |
| Webcam | ? | ? |
| Impressora 3D | ? | ? |
| Adaptador Bluetooth | ? | ? |

---

### Exercício 3 - E/S Programada (Polling)

a) Desenhe o fluxograma (usando texto) da E/S programada para ler um byte de um dispositivo.

b) Uma CPU de 4 GHz executa polling a um sensor que gera dados a cada 10 ms. Se cada verificação de status consome 5 ciclos, calcule:
   - Quantas verificações ocorrem entre cada dado?
   - Quantos ciclos são desperdiçados em polling entre cada dado?
   - Qual a porcentagem do tempo da CPU desperdiçada?

c) Em qual situação a E/S programada pode ser uma boa escolha? Dê um exemplo prático.

---

### Exercício 4 - E/S por Interrupção

a) Descreva o passo a passo completo do que acontece quando um dispositivo de E/S gera uma interrupção, desde o sinal de IRQ até o retorno ao programa original.

b) O que é uma ISR (Interrupt Service Routine)? Qual a diferença entre ela e uma função normal de programa?

c) Explique o conceito de **interrupção aninhada** (nested interrupt). Quando ela é útil?

d) Se uma ISR leva 200 ciclos para executar e a CPU é de 2 GHz, e o teclado gera 10 interrupções por segundo:
   - Qual o overhead total de interrupções do teclado por segundo?
   - Qual a porcentagem da CPU usada para atender o teclado?

---

### Exercício 5 - DMA (Acesso Direto à Memória)

a) Quais são os 4 registradores de um controlador DMA e qual a função de cada um?

b) Descreva o fluxo completo de uma operação DMA para ler 1 MB de um SSD para a memória RAM.

c) Explique a diferença entre os modos de transferência DMA:
   - Burst Mode
   - Cycle Stealing
   - Transparent Mode

d) Por que o DMA ainda precisa gerar uma interrupção ao final da transferência?

---

### Exercício 6 - Comparação de Técnicas de E/S ⭐

Um sistema precisa transferir 256 KB de dados de um disco para a memória. O disco tem taxa de transferência de 100 MB/s. A CPU opera a 3 GHz.

Para cada técnica, calcule:

a) **E/S Programada:** Se cada byte requer 10 ciclos para o loop de polling e transferência, quantos ciclos a CPU gasta no total? Quanto tempo isso leva?

b) **E/S por Interrupção:** Se cada interrupção transfere 4 bytes (1 palavra) e custa 500 ciclos de overhead, quantas interrupções são necessárias? Quantos ciclos são gastos?

c) **DMA:** Se a configuração do DMA custa 1.000 ciclos e a interrupção final custa 500 ciclos, quantos ciclos de CPU são usados?

d) Monte uma tabela comparativa com os resultados e calcule a porcentagem de CPU livre em cada caso.

---

### Exercício 7 - Endereçamento de E/S

a) Explique a diferença entre E/S mapeada em memória e E/S isolada (port-mapped I/O).

b) Preencha a tabela comparativa:

| Característica | E/S Mapeada em Memória | E/S Isolada |
|---------------|----------------------|-------------|
| Instruções usadas | ? | ? |
| Espaço de endereçamento | ? | ? |
| Proteção de acesso | ? | ? |
| Exemplo de arquitetura | ? | ? |

c) Cite uma vantagem e uma desvantagem de cada abordagem.

d) Em um sistema com E/S mapeada em memória e espaço de endereçamento de 32 bits, se 256 MB são reservados para dispositivos de E/S, quanta memória RAM pode ser endereçada? Isso é um problema?

---

### Exercício 8 - Barramentos de E/S

a) Complete a tabela com as velocidades máximas:

| Barramento | Velocidade Máxima | Tipo (Serial/Paralelo) |
|------------|-------------------|----------------------|
| USB 2.0 | ? | ? |
| USB 3.0 | ? | ? |
| USB4 | ? | ? |
| SATA III | ? | ? |
| PCIe 4.0 x4 | ? | ? |
| PCIe 5.0 x16 | ? | ? |

b) Por que os barramentos modernos são seriais e não paralelos, como eram antigamente?

c) Um SSD NVMe conectado via PCIe 4.0 x4 consegue atingir uma taxa de leitura de 7.000 MB/s. O barramento PCIe 4.0 x4 suporta essa velocidade? Justifique.

d) Quanto tempo leva para transferir um arquivo de 10 GB por USB 2.0 e por USB 3.0? Qual a diferença de tempo?

---

### Exercício 9 - Cenários Reais ⭐

Para cada cenário abaixo, indique qual técnica de E/S (programada, interrupção ou DMA) é mais adequada e justifique:

a) Um microcontrolador Arduino lendo a temperatura de um sensor a cada 100 ms.

b) Um servidor web processando pacotes de rede que chegam a milhares por segundo.

c) Um sistema de backup copiando 500 GB de dados de um HDD para outro.

d) Um sistema embarcado de airbag que precisa detectar uma colisão em menos de 1 ms.

e) Uma placa de vídeo (GPU) recebendo texturas de 100 MB do disco para renderizar uma cena 3D.

f) Um sistema de monitoramento cardíaco que registra batimentos e emite alarme se houver anomalia.

---

### Exercício 10 - Controlador de Interrupções

a) O que é um PIC (Programmable Interrupt Controller) e qual sua função?

b) Em um sistema com 8 linhas de IRQ, como o controlador decide qual interrupção atender quando duas chegam simultaneamente?

c) O que é o EOI (End of Interrupt) e por que é necessário enviá-lo ao final de uma ISR?

d) Em um sistema multinúcleo com APIC, como as interrupções podem ser distribuídas entre os núcleos? Qual a vantagem disso?

---

### Exercício 11 - DMA Avançado ⭐

Um controlador DMA opera em modo Cycle Stealing com as seguintes características:

- CPU a 2 GHz
- Cada cycle steal transfere 8 bytes e toma 1 ciclo do barramento
- Transferência total: 64 KB
- Tempo de configuração DMA: 800 ciclos
- Tempo de ISR final: 400 ciclos

a) Quantos cycle steals são necessários para transferir 64 KB?

b) Qual o tempo total da transferência?

c) Se sem DMA a CPU precisaria de 20 ciclos por byte para fazer a transferência, quantos ciclos seriam gastos?

d) Qual é o speedup obtido pelo uso de DMA com cycle stealing em termos de ciclos de CPU gastos?

---

### Exercício 12 - Questão Dissertativa ⭐

Considere a evolução das técnicas de E/S desde os primeiros computadores até os dias atuais:

a) Descreva como os computadores das décadas de 1950-1960 realizavam E/S e quais eram as limitações.

b) Explique por que a E/S por interrupção foi uma evolução revolucionária em relação à E/S programada.

c) Descreva como o DMA mudou a forma como grandes volumes de dados são transferidos.

d) Discuta as tendências modernas de E/S, como:
   - Interrupt Coalescing (agrupamento de interrupções)
   - RDMA (Remote Direct Memory Access)
   - NVMe (como combina PCIe com DMA)

Escreva um texto de 15 a 25 linhas abrangendo esses pontos.

---

## 📊 Gabarito Resumido

<details>
<summary>Clique para ver as respostas resumidas</summary>

**Exercício 3b:**
- Verificações entre cada dado: 10 ms × 4 GHz / 5 ciclos = **8.000.000 verificações**
- Ciclos desperdiçados: 8.000.000 × 5 = **40.000.000 ciclos** por dado
- Porcentagem: 40.000.000 / (10 ms × 4 GHz) = 40.000.000 / 40.000.000 = **100%** (a CPU faz SOMENTE polling!)

**Exercício 4d:**
- Overhead por segundo: 10 × 200 = **2.000 ciclos/s**
- Porcentagem: 2.000 / 2.000.000.000 = **0,0001%** (praticamente zero)

**Exercício 6:**
- a) Programada: 256 KB × 1024 = 262.144 bytes × 10 ciclos = **2.621.440 ciclos** (0,87 ms)
- b) Interrupção: 262.144/4 = 65.536 interrupções × 500 = **32.768.000 ciclos** (10,9 ms)
- c) DMA: 1.000 + 500 = **1.500 ciclos** (0,0005 ms)
- d) CPU livre: Programada: 0%, Interrupção: ~99%, DMA: ~100%

**Exercício 8d:**
- USB 2.0: 10 GB / 60 MB/s ≈ **171 segundos** (2,8 min)
- USB 3.0: 10 GB / 625 MB/s ≈ **16,4 segundos**
- Diferença: ~155 segundos (10,4x mais rápido)

**Exercício 11:**
- a) 64 KB / 8 bytes = **8.192 cycle steals**
- b) Configuração + steals + ISR = 800 + 8.192 + 400 = **9.392 ciclos** de CPU (4,696 μs)
- c) Sem DMA: 65.536 × 20 = **1.310.720 ciclos**
- d) Speedup: 1.310.720 / 9.392 ≈ **139,6x**

</details>

---

> 💡 **Dica:** Ao comparar técnicas de E/S, sempre calcule quantos ciclos de CPU são gastos e qual a porcentagem de CPU livre. Isso torna a comparação muito mais concreta e fácil de entender!
