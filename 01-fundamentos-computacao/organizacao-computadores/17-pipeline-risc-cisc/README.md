# 🚀 Aula 17 - Pipeline, RISC e CISC

## 📋 Objetivos de Aprendizagem

Ao final desta aula, você será capaz de:

- ✅ Compreender o conceito de pipeline e sua analogia com processos do cotidiano
- ✅ Identificar os cinco estágios clássicos do pipeline (IF, ID, EX, MEM, WB)
- ✅ Calcular o speedup e a vazão (throughput) de um pipeline
- ✅ Reconhecer os três tipos de hazards (conflitos) e suas soluções
- ✅ Diferenciar as filosofias RISC e CISC
- ✅ Comparar arquiteturas ARM (RISC) e x86 (CISC)
- ✅ Entender a convergência moderna entre RISC e CISC

---

## 📚 Conteúdo

### 1. Introdução ao Pipeline

#### 1.1 O que é Pipeline?

**Pipeline** é uma técnica de implementação de processadores onde múltiplas instruções são sobrepostas durante a execução. Assim como uma linha de montagem industrial, o pipeline divide o processamento de uma instrução em **estágios independentes**, permitindo que várias instruções estejam em diferentes fases de execução simultaneamente.

> 💡 **Ideia central:** Não esperamos uma instrução terminar completamente para iniciar a próxima!

#### 1.2 Analogia da Lavanderia 🧺

Imagine que você precisa lavar 4 cargas de roupa. Cada carga passa por:

1. **Lavar** (30 min)
2. **Secar** (30 min)
3. **Passar** (30 min)
4. **Guardar** (30 min)

**Sem pipeline (sequencial):**

```
Carga 1: [Lavar][Secar][Passar][Guardar]
Carga 2:                                 [Lavar][Secar][Passar][Guardar]
Carga 3:                                                                 [Lavar][Secar][Passar][Guardar]
Carga 4:                                                                                                 [Lavar][Secar][Passar][Guardar]

Tempo total: 4 cargas × 120 min = 480 min = 8 horas
```

**Com pipeline (sobreposto):**

```
Tempo:    30   60   90  120  150  180  210
Carga 1: [Lav][Sec][Pas][Gua]
Carga 2:      [Lav][Sec][Pas][Gua]
Carga 3:           [Lav][Sec][Pas][Gua]
Carga 4:                [Lav][Sec][Pas][Gua]

Tempo total: 120 + (3 × 30) = 210 min = 3.5 horas
```

> 🎯 **Resultado:** Com pipeline, reduzimos o tempo de 8 horas para 3.5 horas! O pipeline **não** acelera uma única carga, mas **aumenta a vazão** (throughput) — o número de cargas completadas por unidade de tempo.

---

### 2. Estágios do Pipeline de Instruções

O pipeline clássico de 5 estágios (utilizado no processador MIPS) divide a execução de cada instrução em:

| Estágio | Sigla | Nome em Português | Descrição |
|---------|-------|--------------------|-----------|
| 1 | **IF** | Busca da Instrução (Instruction Fetch) | Busca a instrução na memória usando o PC (Program Counter) |
| 2 | **ID** | Decodificação (Instruction Decode) | Decodifica a instrução e lê os registradores |
| 3 | **EX** | Execução (Execute) | Realiza a operação na ULA ou calcula endereço |
| 4 | **MEM** | Acesso à Memória (Memory Access) | Acessa a memória de dados (load/store) |
| 5 | **WB** | Escrita (Write Back) | Escreve o resultado no registrador de destino |

#### Diagrama do Pipeline de 5 Estágios

```
┌──────────────────────────────────────────────────────────────┐
│                    PIPELINE DE 5 ESTÁGIOS                    │
├──────────┬──────────┬──────────┬──────────┬──────────────────┤
│          │          │          │          │                  │
│   IF     │   ID     │   EX     │   MEM    │      WB         │
│          │          │          │          │                  │
│ Busca    │ Decodi-  │ Execução │ Acesso   │ Escrita no      │
│ instrução│ ficação  │ na ULA   │ memória  │ registrador     │
│ na       │ + leitura│          │ dados    │                  │
│ memória  │ registr. │          │          │                  │
│          │          │          │          │                  │
└──────────┴──────────┴──────────┴──────────┴──────────────────┘
     │           │          │          │            │
     ▼           ▼          ▼          ▼            ▼
  ┌──────┐  ┌──────┐  ┌──────┐  ┌──────┐    ┌──────────┐
  │Memória│  │Banco │  │ ULA  │  │Memória│    │  Banco   │
  │Instr. │  │Regist│  │      │  │Dados  │    │ Registr. │
  └──────┘  └──────┘  └──────┘  └──────┘    └──────────┘
```

#### Execução de Múltiplas Instruções no Pipeline

```
Ciclo de clock:   1    2    3    4    5    6    7    8    9
                ┌────┬────┬────┬────┬────┐
Instrução 1:    │ IF │ ID │ EX │MEM │ WB │
                └────┴────┴────┴────┴────┘
                     ┌────┬────┬────┬────┬────┐
Instrução 2:         │ IF │ ID │ EX │MEM │ WB │
                     └────┴────┴────┴────┴────┘
                          ┌────┬────┬────┬────┬────┐
Instrução 3:              │ IF │ ID │ EX │MEM │ WB │
                          └────┴────┴────┴────┴────┘
                               ┌────┬────┬────┬────┬────┐
Instrução 4:                   │ IF │ ID │ EX │MEM │ WB │
                               └────┴────┴────┴────┴────┘
                                    ┌────┬────┬────┬────┬────┐
Instrução 5:                        │ IF │ ID │ EX │MEM │ WB │
                                    └────┴────┴────┴────┴────┘
```

> 📝 **Observe:** A partir do ciclo 5, temos **5 instruções sendo executadas simultaneamente**, cada uma em um estágio diferente!

---

### 3. Speedup e Throughput do Pipeline

#### 3.1 Speedup Teórico

O **speedup** mede quanto o pipeline é mais rápido que a execução sequencial:

```
                    Tempo sem pipeline
Speedup = ─────────────────────────────────
                    Tempo com pipeline
```

Para **n instruções** e **k estágios** de pipeline:

```
Tempo sem pipeline  = n × k × t
Tempo com pipeline  = k × t + (n - 1) × t = (k + n - 1) × t

Onde: t = tempo de cada estágio (ciclo de clock)
```

Portanto:

```
              n × k
Speedup = ─────────────
            k + n - 1
```

**Quando n → ∞ (muitas instruções):**

```
Speedup_máximo = k (número de estágios)
```

> 🎯 **Conclusão:** Um pipeline de 5 estágios pode, idealmente, ser **até 5 vezes mais rápido** que a execução sequencial!

#### 3.2 Throughput (Vazão)

O **throughput** é o número de instruções completadas por unidade de tempo:

```
Sem pipeline:  Throughput = 1 / (k × t)    instruções por ciclo
Com pipeline:  Throughput = 1 / t           instruções por ciclo (ideal)
```

> 💡 **Pipeline não reduz a latência** (tempo de uma instrução individual), mas **aumenta a vazão** (instruções por segundo).

#### 3.3 Exemplo Numérico

Considere um processador com pipeline de 5 estágios, cada estágio leva 1 ns:

| Métrica | Sem Pipeline | Com Pipeline (ideal) |
|---------|-------------|---------------------|
| Latência de 1 instrução | 5 ns | 5 ns |
| Tempo para 100 instruções | 500 ns | 104 ns |
| Throughput | 0,2 instr/ns | ~1 instr/ns |
| Speedup (100 instruções) | 1× | ~4,81× |

---

### 4. Hazards (Conflitos) no Pipeline

Na prática, o pipeline nem sempre funciona perfeitamente. Existem situações chamadas **hazards** (conflitos) que impedem que a próxima instrução execute no ciclo esperado.

#### 4.1 Hazard Estrutural (Structural Hazard)

Ocorre quando **dois estágios precisam do mesmo recurso de hardware ao mesmo tempo**.

**Exemplo:** Uma instrução está buscando dados na memória (MEM) enquanto outra quer buscar uma instrução (IF), e existe apenas uma memória.

```
Ciclo:        1    2    3    4    5    6
Instr 1:     IF   ID   EX  MEM  WB
Instr 2:          IF   ID   EX  MEM  WB
Instr 3:               IF   ID   EX  MEM  WB
Instr 4:                    IF ← CONFLITO! (IF e MEM usam a mesma memória)
                             ↑
                    Ambos querem acessar a memória!
```

**Solução:**
- Separar a memória em **memória de instruções** e **memória de dados** (arquitetura Harvard)
- Usar **caches separadas** (cache L1 de instruções e cache L1 de dados)

#### 4.2 Hazard de Dados (Data Hazard)

Ocorre quando uma instrução **depende do resultado de uma instrução anterior** que ainda não completou.

**Exemplo:**

```assembly
ADD R1, R2, R3    ; R1 = R2 + R3
SUB R4, R1, R5    ; R4 = R1 - R5  ← precisa do valor de R1!
```

```
Ciclo:        1    2    3    4    5    6    7
ADD R1,R2,R3: IF   ID   EX  MEM  [WB] ← R1 é escrito aqui
SUB R4,R1,R5:      IF  [ID]  EX  MEM   WB
                         ↑
                  Precisa ler R1, mas R1 ainda não foi escrito!
```

**Tipos de dependência de dados:**

| Tipo | Sigla | Descrição | Exemplo |
|------|-------|-----------|---------|
| Leitura após Escrita | RAW (Read After Write) | Instrução lê antes da anterior escrever | `ADD R1,...` → `SUB ...,R1,...` |
| Escrita após Leitura | WAR (Write After Read) | Instrução escreve antes da anterior ler | `SUB ...,R1,...` → `ADD R1,...` |
| Escrita após Escrita | WAW (Write After Write) | Duas instruções escrevem no mesmo registrador | `ADD R1,...` → `MUL R1,...` |

**Soluções:**

**a) Forwarding (Adiantamento / Bypass):**

O resultado é encaminhado diretamente da saída da ULA para a entrada, sem esperar o WB:

```
                1    2    3    4    5    6
ADD R1,R2,R3:  IF   ID   EX  MEM   WB
                          │
                    ┌─────┘ (forwarding: resultado da ULA
                    │        é enviado diretamente)
                    ▼
SUB R4,R1,R5:       IF   ID   EX  MEM   WB
```

**b) Stalling (Bolha / Pipeline Stall):**

Inserir ciclos de espera (bolhas) até que o dado esteja disponível:

```
                1    2    3    4    5    6    7    8
ADD R1,R2,R3:  IF   ID   EX  MEM   WB
SUB R4,R1,R5:       IF   ── bolha ──   ID   EX  MEM  WB
```

**c) Reordenação de instruções** pelo compilador para preencher os slots vazios com instruções independentes.

#### 4.3 Hazard de Controle (Control Hazard)

Ocorre com **instruções de desvio** (branch), pois não sabemos qual será a próxima instrução até que o desvio seja resolvido.

**Exemplo:**

```assembly
BEQ R1, R2, label    ; Se R1 == R2, desvia para label
ADD R3, R4, R5       ; Essa instrução deve ser executada?
SUB R6, R7, R8       ; E essa?
```

```
Ciclo:          1    2    3    4    5    6
BEQ R1,R2,lab: IF   ID   EX  MEM   WB
                          ↑
                  Só aqui sabe se desvia!
ADD R3,R4,R5:       IF   ID   ??   ← Será descartada se desviar!
SUB R6,R7,R8:            IF   ??
```

**Soluções:**

| Técnica | Descrição | Eficácia |
|---------|-----------|----------|
| **Stalling** | Congela o pipeline até resolver o desvio | Simples, mas lento |
| **Branch Prediction (Predição de Desvio)** | Adivinha se o desvio será tomado ou não | 85-97% de acerto |
| **Delayed Branch** | Executa instrução após o branch independente do resultado | Usada no MIPS |
| **Branch Target Buffer (BTB)** | Cache com endereços-alvo de desvios recentes | Muito eficiente |

**Tipos de Predição de Desvio:**

```
┌─────────────────────────────────────────────────────┐
│              PREDIÇÃO DE DESVIO                      │
├─────────────────────┬───────────────────────────────┤
│    ESTÁTICA          │         DINÂMICA              │
│                     │                               │
│ • Sempre não toma   │ • Contador de 1 bit           │
│ • Sempre toma       │ • Contador de 2 bits          │
│ • Baseada no opcode │ • Predição correlacionada     │
│ • BTFN (backward    │ • Predição por torneio        │
│   taken, forward    │ • Perceptron                  │
│   not taken)        │                               │
└─────────────────────┴───────────────────────────────┘
```

**Predição de 2 bits (Saturating Counter):**

```
         Tomado              Tomado
   ┌───────────────┐   ┌───────────────┐
   │               ▼   │               ▼
┌──────┐        ┌──────────┐        ┌──────────┐        ┌──────┐
│  00  │◄───────│    01    │        │    10    │───────►│  11  │
│Forte │Não tom.│  Fraco   │        │  Fraco   │Tomado  │Forte │
│Não T.│        │  Não T.  │        │  Tomado  │        │Tomado│
└──────┘        └──────────┘        └──────────┘        └──────┘
                   │      ▲            │      ▲
                   │      │            │      │
                   └──────┘            └──────┘
                   Não tom.            Não tom.

Prediz "Não Tomado"        Prediz "Tomado"
    (00, 01)                  (10, 11)
```

---

### 5. Filosofia RISC (Reduced Instruction Set Computer)

#### 5.1 Conceito

A filosofia **RISC** propõe que o processador deve ter um **conjunto reduzido de instruções simples**, executadas rapidamente (idealmente em 1 ciclo de clock).

> 💡 **Princípio:** "Faça o simples rapidamente" — instruções complexas são construídas combinando instruções simples.

#### 5.2 Características Principais do RISC

| Característica | Descrição |
|---------------|-----------|
| **Instruções simples** | Cada instrução realiza uma operação básica |
| **Tamanho fixo** | Todas as instruções têm o mesmo tamanho (ex: 32 bits) |
| **Poucos formatos** | Número reduzido de formatos de instrução |
| **Load/Store** | Apenas LOAD e STORE acessam a memória; operações são entre registradores |
| **Muitos registradores** | Grande número de registradores de propósito geral (32, 64 ou mais) |
| **Pipeline eficiente** | Instruções regulares facilitam o pipeline |
| **Execução em 1 ciclo** | Maioria das instruções completa em 1 ciclo de clock |
| **Compilador complexo** | O compilador é responsável por otimizações |

#### 5.3 Arquitetura Load/Store

```
┌─────────────────────────────────────────────────────┐
│                ARQUITETURA RISC                      │
│                                                     │
│   Memória ◄──── LOAD ────► Registradores            │
│   Memória ◄──── STORE ───► Registradores            │
│                                                     │
│   Registradores ◄── Operações ──► Registradores     │
│                     (ADD, SUB,                       │
│                      MUL, etc.)                      │
│                                                     │
│   ⚠️ ULA NÃO acessa memória diretamente!            │
└─────────────────────────────────────────────────────┘
```

#### 5.4 Exemplos de Processadores RISC

- **ARM** (Advanced RISC Machines) — smartphones, tablets, Raspberry Pi, Apple M1/M2
- **MIPS** — roteadores, consoles de videogame antigos, ensino acadêmico
- **RISC-V** — arquitetura aberta (open-source), crescente adoção
- **SPARC** — servidores Sun/Oracle
- **PowerPC** — consoles antigos (GameCube, Wii), alguns servidores IBM

---

### 6. Filosofia CISC (Complex Instruction Set Computer)

#### 6.1 Conceito

A filosofia **CISC** propõe que o processador deve ter um **conjunto rico e variado de instruções**, incluindo instruções complexas que realizam múltiplas operações em um único comando.

> 💡 **Princípio:** "Uma instrução faz muito" — reduz o número de instruções necessárias no programa.

#### 6.2 Características Principais do CISC

| Característica | Descrição |
|---------------|-----------|
| **Instruções complexas** | Uma instrução pode fazer várias operações |
| **Tamanho variável** | Instruções de 1 a 15+ bytes |
| **Muitos formatos** | Grande variedade de formatos de instrução |
| **Acesso direto à memória** | Operações podem acessar memória diretamente |
| **Poucos registradores** | Menos registradores de propósito geral |
| **Microcódigo** | Instruções complexas são implementadas via microcódigo |
| **Múltiplos ciclos** | Instruções complexas levam vários ciclos |
| **Programa menor** | Menos instruções no programa compilado |

#### 6.3 Exemplo de Instrução Complexa (x86)

```
; x86 CISC - Uma única instrução faz tudo:
MOVSB    ; Move byte de [SI] para [DI], incrementa SI e DI

; Equivalente em RISC (múltiplas instruções simples):
LOAD  R1, [R2]      ; Carrega byte do endereço em R2
STORE R1, [R3]      ; Armazena byte no endereço em R3
ADD   R2, R2, #1    ; Incrementa ponteiro fonte
ADD   R3, R3, #1    ; Incrementa ponteiro destino
```

#### 6.4 Exemplos de Processadores CISC

- **x86 / x86-64 (AMD64)** — Intel Core, AMD Ryzen (PCs e servidores)
- **Motorola 68000** — Macintosh original, Amiga, Mega Drive
- **VAX** — minicomputadores DEC
- **IBM System/360** — mainframes IBM

---

### 7. RISC vs CISC — Comparação Detalhada

| Aspecto | RISC | CISC |
|---------|------|------|
| 📏 **Conjunto de instruções** | Pequeno (50-150) | Grande (200-500+) |
| 📐 **Tamanho da instrução** | Fixo (32 bits) | Variável (1-15+ bytes) |
| ⚡ **Ciclos por instrução** | 1 ciclo (maioria) | Múltiplos ciclos |
| 🧮 **Acesso à memória** | Apenas LOAD/STORE | Qualquer instrução |
| 📦 **Registradores** | Muitos (32-64+) | Poucos (8-16) |
| 🔧 **Complexidade do hardware** | Simples | Complexo |
| 💻 **Complexidade do compilador** | Complexo | Simples |
| 🏗️ **Pipeline** | Muito eficiente | Difícil de implementar |
| 📄 **Tamanho do programa** | Maior (mais instruções) | Menor (menos instruções) |
| 🔌 **Consumo de energia** | Baixo | Alto |
| 💰 **Custo do chip** | Menor | Maior |
| 📱 **Uso principal** | Smartphones, embarcados | PCs, servidores |
| 🏭 **Implementação** | Hardware direto (hardwired) | Microcódigo |
| 📊 **Modos de endereçamento** | Poucos e simples | Muitos e complexos |

#### Exemplo Comparativo: Somar dois valores da memória

**CISC (x86):**
```assembly
; Uma instrução acessa memória e soma
ADD [mem2], [mem1]    ; mem2 = mem2 + mem1
; Total: 1 instrução
```

**RISC (ARM/MIPS):**
```assembly
LOAD  R1, [mem1]     ; Carrega valor de mem1 em R1
LOAD  R2, [mem2]     ; Carrega valor de mem2 em R2
ADD   R3, R1, R2     ; R3 = R1 + R2
STORE R3, [mem2]     ; Armazena resultado em mem2
; Total: 4 instruções
```

> 📝 **Nota:** Apesar de ter mais instruções, o RISC pode ser mais rápido porque cada instrução executa em 1 ciclo e o pipeline é mais eficiente!

---

### 8. Exemplos Reais: ARM vs x86

#### 8.1 ARM (RISC) 📱

```
┌─────────────────────────────────────────┐
│              ARM (RISC)                  │
├─────────────────────────────────────────┤
│ • Instruções de 32 bits (ARM) ou        │
│   16/32 bits (Thumb-2)                  │
│ • 16 registradores de propósito geral   │
│ • Arquitetura Load/Store                │
│ • Execução condicional em todas instr.  │
│ • Baixo consumo de energia              │
│                                         │
│ 📱 Onde encontramos:                     │
│   - iPhones (Apple A-series, M-series)  │
│   - Android (Snapdragon, Exynos)        │
│   - Raspberry Pi                        │
│   - Nintendo Switch                     │
│   - MacBooks com Apple Silicon          │
│   - Servidores AWS Graviton             │
└─────────────────────────────────────────┘
```

#### 8.2 x86 (CISC) 💻

```
┌─────────────────────────────────────────┐
│              x86 (CISC)                  │
├─────────────────────────────────────────┤
│ • Instruções de 1 a 15 bytes            │
│ • 16 registradores (x86-64)             │
│ • Acesso direto memória-registrador     │
│ • Microcódigo interno                   │
│ • Alto desempenho por núcleo            │
│                                         │
│ 💻 Onde encontramos:                     │
│   - PCs desktop e notebooks             │
│   - Servidores de data centers          │
│   - Consoles de videogame (PS5, Xbox)   │
│   - Supercomputadores                   │
│   - Estações de trabalho                │
└─────────────────────────────────────────┘
```

---

### 9. Convergência Moderna: RISC e CISC se Aproximam

Na prática moderna, a distinção entre RISC e CISC tornou-se menos clara:

#### 9.1 x86 Moderno = CISC por Fora, RISC por Dentro

```
┌─────────────────────────────────────────────────────────┐
│                  PROCESSADOR x86 MODERNO                 │
│                                                         │
│   Instruções x86 ──► ┌─────────────────┐               │
│   (CISC)              │  DECODIFICADOR  │               │
│                       │  (traduz para   │               │
│                       │   micro-ops)    │               │
│                       └────────┬────────┘               │
│                                │                        │
│                                ▼                        │
│                       ┌─────────────────┐               │
│                       │  NÚCLEO RISC    │               │
│                       │  (executa       │               │
│                       │   micro-ops     │               │
│                       │   simples)      │               │
│                       └─────────────────┘               │
│                                                         │
│   💡 O processador Intel/AMD traduz instruções CISC     │
│      em micro-operações RISC internamente!              │
└─────────────────────────────────────────────────────────┘
```

#### 9.2 ARM Moderno = Instruções Cada Vez Mais Complexas

Os processadores ARM modernos também incorporaram:
- Instruções SIMD (NEON)
- Instruções de criptografia
- Operações de ponto flutuante complexas
- Extensões de virtualização

#### 9.3 Quadro da Convergência

```
    CISC (x86)                         RISC (ARM)
    ─────────                          ─────────
    Muitas instruções                  Poucas instruções
    complexas                          simples
         │                                  │
         │    ┌─────────────────────┐       │
         └───►│    CONVERGÊNCIA     │◄──────┘
              │                     │
              │ • Tradução interna  │
              │ • Pipeline profundo │
              │ • Superescalar      │
              │ • Exec. fora de     │
              │   ordem             │
              │ • Predição avançada │
              └─────────────────────┘
```

---

### 10. Por que Isso Importa Hoje? 🌍

#### 10.1 O Mundo é ARM + x86

```
┌────────────────────────────────────────────────────┐
│           DISTRIBUIÇÃO DE ARQUITETURAS              │
├────────────────────┬───────────────────────────────┤
│    ARM (RISC)      │          x86 (CISC)           │
│                    │                               │
│ 📱 ~99% dos        │ 💻 ~90% dos PCs              │
│    smartphones     │                               │
│                    │ 🖥️ ~95% dos servidores        │
│ 🔌 IoT e          │    tradicionais               │
│    embarcados      │                               │
│                    │ 🎮 Consoles de videogame      │
│ 🖥️ Apple Silicon   │    (PS5, Xbox)               │
│    (MacBooks)      │                               │
│                    │ 🏢 Estações de trabalho       │
│ ☁️ AWS Graviton    │                               │
│    (servidores)    │                               │
└────────────────────┴───────────────────────────────┘
```

#### 10.2 Tendências Atuais

1. **ARM nos servidores:** Amazon (Graviton), Ampere, Microsoft (Azure Cobalt)
2. **Apple Silicon:** MacBooks e iMacs usando ARM (M1, M2, M3, M4)
3. **RISC-V:** Arquitetura aberta ganhando espaço em IoT e educação
4. **Eficiência energética:** ARM domina onde energia é crítica
5. **Computação em nuvem:** Mix de ARM e x86 dependendo da carga de trabalho

---

### 11. Resumo da Aula

```
┌─────────────────────────────────────────────────────────┐
│                    RESUMO - AULA 17                      │
├─────────────────────────────────────────────────────────┤
│                                                         │
│  🔹 Pipeline: técnica que sobrepõe execução de          │
│     instruções em estágios (IF, ID, EX, MEM, WB)       │
│                                                         │
│  🔹 Hazards: conflitos que impedem o pipeline ideal     │
│     • Estrutural: recurso compartilhado                 │
│     • Dados: dependência entre instruções               │
│     • Controle: instruções de desvio                    │
│                                                         │
│  🔹 RISC: instruções simples, tamanho fixo,             │
│     load/store, muitos registradores                    │
│                                                         │
│  🔹 CISC: instruções complexas, tamanho variável,       │
│     acesso direto à memória, microcódigo                │
│                                                         │
│  🔹 Convergência: x86 moderno é CISC por fora e        │
│     RISC por dentro; ARM incorpora complexidade         │
│                                                         │
│  🔹 Mundo real: ARM domina móveis, x86 domina PCs      │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

---

## 📖 Referências

1. STALLINGS, W. **Arquitetura e Organização de Computadores**. 10ª ed. Pearson, 2017.
2. PATTERSON, D. A.; HENNESSY, J. L. **Organização e Projeto de Computadores: A Interface Hardware/Software**. 5ª ed. Elsevier, 2017.
3. TANENBAUM, A. S. **Organização Estruturada de Computadores**. 6ª ed. Pearson, 2013.
4. HENNESSY, J. L.; PATTERSON, D. A. **Arquitetura de Computadores: Uma Abordagem Quantitativa**. 6ª ed. Elsevier, 2019.
5. ARM Ltd. **ARM Architecture Reference Manual**. Disponível em: developer.arm.com

---

> 💡 **Próxima aula:** Introdução às Arquiteturas Paralelas e Taxonomia de Flynn — vamos explorar como múltiplos processadores trabalham juntos!
