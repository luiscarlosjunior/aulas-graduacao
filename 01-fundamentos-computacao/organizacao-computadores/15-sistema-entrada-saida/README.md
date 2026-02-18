# 🔌 Aula 15 - Sistema de Entrada e Saída (E/S)

## 📋 Informações da Aula

| Item | Descrição |
|------|-----------|
| **Curso** | Organização de Computadores |
| **Aula** | 15 |
| **Tema** | Sistema de Entrada e Saída (E/S) |
| **Duração** | 2 horas (120 minutos) |
| **Pré-requisitos** | Aulas 13-14 - Memória e Hierarquia |

---

## 🎯 Objetivos de Aprendizagem

Ao final desta aula, o estudante será capaz de:

1. ✅ Compreender a importância do sistema de E/S na arquitetura de computadores
2. ✅ Descrever a função e estrutura dos módulos de E/S
3. ✅ Classificar dispositivos de E/S por categoria
4. ✅ Explicar e comparar E/S programada, por interrupção e DMA
5. ✅ Analisar o funcionamento de controladores e interfaces de E/S
6. ✅ Identificar padrões de barramento (USB, SATA, PCIe)
7. ✅ Relacionar conceitos teóricos com dispositivos reais do cotidiano

---

## 📚 Conteúdo

### 1. Visão Geral do Sistema de E/S 🖥️

#### 1.1 O que é o Sistema de Entrada e Saída?

O sistema de E/S (Input/Output — I/O) é responsável pela **comunicação entre o processador/memória e o mundo externo**. Sem ele, o computador seria um "cérebro" isolado, incapaz de receber dados ou apresentar resultados.

```
┌─────────────────────────────────────────────────────────┐
│                SISTEMA COMPUTACIONAL                     │
│                                                          │
│  ┌──────┐    ┌─────────┐    ┌──────────────┐           │
│  │      │◄──►│         │◄──►│              │           │
│  │ CPU  │    │ Memória │    │  Módulo E/S  │◄──► Dispositivos
│  │      │◄──►│         │◄──►│              │    Externos
│  └──────┘    └─────────┘    └──────────────┘           │
│       ▲           ▲              ▲                      │
│       └───────────┴──────────────┘                      │
│              Barramento do Sistema                       │
└─────────────────────────────────────────────────────────┘
```

#### 1.2 Importância do Sistema de E/S

| Aspecto | Descrição |
|---------|-----------|
| **Comunicação** | Permite interação com usuários e outros sistemas |
| **Versatilidade** | Suporta enorme variedade de dispositivos |
| **Gargalo** | Frequentemente é o limitador de desempenho do sistema |
| **Complexidade** | Precisa lidar com diferenças de velocidade enormes |

#### 1.3 O Problema da Velocidade

```
Velocidade relativa dos componentes:

CPU:        ██████████████████████████████████████ ~GHz (bilhões op/s)
RAM:        ███████████████████████               ~100 ns
SSD:        ████████████                          ~μs
Teclado:    █                                     ~ms (dezenas de teclas/s)
Impressora: ▌                                     ~s (páginas/minuto)

O sistema de E/S precisa conciliar essas diferenças ENORMES!
Diferença CPU vs. Teclado: ~10.000.000x
```

---

### 2. Módulos de E/S: Função e Estrutura 🏗️

#### 2.1 Por que usar Módulos de E/S?

Os dispositivos de E/S **não podem se conectar diretamente ao barramento do sistema** porque:

1. Grande variedade de dispositivos com padrões diferentes
2. Velocidades de transferência muito diferentes
3. Formatos de dados diferentes
4. O processador não deveria gerenciar cada dispositivo diretamente

> 💡 O módulo de E/S atua como um **intermediário (interface)** entre o processador e os dispositivos.

#### 2.2 Funções do Módulo de E/S

| Função | Descrição |
|--------|-----------|
| **Controle e temporização** | Coordena o fluxo de dados entre componentes internos e dispositivos |
| **Comunicação com o processador** | Recebe comandos, envia status, transfere dados |
| **Comunicação com dispositivos** | Envia sinais de controle, recebe status, transfere dados |
| **Buffering** | Armazena temporariamente dados para compensar diferenças de velocidade |
| **Detecção de erros** | Verifica integridade dos dados transferidos |

#### 2.3 Estrutura Interna de um Módulo de E/S

```
┌──────────────────────────────────────────────────────┐
│                  MÓDULO DE E/S                         │
│                                                        │
│  ┌──────────────────────────────────────────────────┐ │
│  │              Interface com o Barramento            │ │
│  │    Linhas de Dados  │  Linhas de Endereço         │ │
│  │    Linhas de Controle                              │ │
│  └──────────────┬───────────────────────────────────┘ │
│                 │                                      │
│  ┌──────────────┴───────────────────────────────────┐ │
│  │              Lógica de E/S                        │ │
│  │                                                    │ │
│  │  ┌────────────┐  ┌────────────┐  ┌─────────────┐ │ │
│  │  │ Registrador│  │ Registrador│  │ Registrador  │ │ │
│  │  │ de Status  │  │ de Dados   │  │ de Controle  │ │ │
│  │  │ (Estado)   │  │ (Buffer)   │  │ (Comandos)   │ │ │
│  │  └────────────┘  └────────────┘  └─────────────┘ │ │
│  │                                                    │ │
│  └──────────────┬───────────────────────────────────┘ │
│                 │                                      │
│  ┌──────────────┴───────────────────────────────────┐ │
│  │          Interface com Dispositivos                │ │
│  │                                                    │ │
│  │   ┌──────────┐  ┌──────────┐  ┌──────────┐      │ │
│  │   │Dispositivo│  │Dispositivo│  │Dispositivo│      │ │
│  │   │    1      │  │    2      │  │    3      │      │ │
│  │   └──────────┘  └──────────┘  └──────────┘      │ │
│  └──────────────────────────────────────────────────┘ │
└──────────────────────────────────────────────────────┘
```

#### 2.4 Registradores do Módulo de E/S

| Registrador | Função | Exemplo |
|-------------|--------|---------|
| **Status** | Indica o estado atual do dispositivo | Pronto, ocupado, erro |
| **Dados** | Buffer para dados sendo transferidos | Byte recebido do teclado |
| **Controle** | Armazena comandos do processador | Ler, escrever, reiniciar |

---

### 3. Categorias de Dispositivos de E/S 📱

#### 3.1 Classificação por Tipo de Interação

```
┌──────────────────────────────────────────────────────────┐
│            CATEGORIAS DE DISPOSITIVOS DE E/S              │
├──────────────────────────────────────────────────────────┤
│                                                          │
│  👤 LEGÍVEIS POR HUMANOS (Human-Readable)               │
│  ├── Teclado (entrada)                                   │
│  ├── Mouse (entrada)                                     │
│  ├── Monitor/Display (saída)                             │
│  ├── Impressora (saída)                                  │
│  ├── Tela touchscreen (entrada/saída)                    │
│  └── Alto-falante (saída)                                │
│                                                          │
│  🖥️ LEGÍVEIS POR MÁQUINA (Machine-Readable)             │
│  ├── Disco rígido (HDD)                                  │
│  ├── SSD                                                 │
│  ├── Sensor de temperatura                               │
│  ├── Atuadores (motores, relés)                          │
│  └── Controladores industriais                           │
│                                                          │
│  🌐 COMUNICAÇÃO                                          │
│  ├── Placa de rede (Ethernet)                            │
│  ├── Adaptador Wi-Fi                                     │
│  ├── Bluetooth                                           │
│  ├── Modem                                               │
│  └── Porta serial/USB                                    │
│                                                          │
└──────────────────────────────────────────────────────────┘
```

#### 3.2 Classificação por Velocidade

| Categoria | Velocidade | Exemplos |
|-----------|-----------|----------|
| **Muito lenta** | < 1 KB/s | Teclado, mouse |
| **Lenta** | 1 KB/s - 1 MB/s | Impressora, scanner |
| **Média** | 1-100 MB/s | Ethernet, USB 2.0 |
| **Rápida** | 100 MB/s - 1 GB/s | SSD SATA, USB 3.0 |
| **Muito rápida** | > 1 GB/s | SSD NVMe, GPU, Rede 10G |

#### 3.3 Classificação por Direção

| Tipo | Direção | Exemplos |
|------|---------|----------|
| **Entrada** | Dispositivo → CPU | Teclado, mouse, microfone, câmera |
| **Saída** | CPU → Dispositivo | Monitor, impressora, alto-falante |
| **Entrada/Saída** | Bidirecional | HDD, SSD, placa de rede, touchscreen |

---

### 4. Tipos de Operações de E/S 🔄

#### 4.1 E/S Programada (Polling) 📋

Na E/S programada, o **processador é totalmente responsável** pela transferência de dados. Ele verifica repetidamente o status do dispositivo (polling/consulta ativa).

```
┌────────────────────────────────────────────────────────┐
│              E/S PROGRAMADA (POLLING)                    │
├────────────────────────────────────────────────────────┤
│                                                        │
│   CPU                         Módulo E/S               │
│   ┌─────┐                    ┌─────────┐              │
│   │     │──① Enviar comando──►│         │              │
│   │     │                    │         │              │
│   │     │──② Ler status──────►│         │              │
│   │     │◄──── Status ────────│  Pronto?│              │
│   │     │                    │   NÃO   │              │
│   │     │──② Ler status──────►│         │              │
│   │     │◄──── Status ────────│  Pronto?│              │
│   │     │                    │   NÃO   │              │
│   │     │──② Ler status──────►│         │              │
│   │     │◄──── Status ────────│  Pronto?│              │
│   │     │                    │   SIM!  │              │
│   │     │──③ Ler dado────────►│         │              │
│   │     │◄──── Dado ──────────│         │              │
│   │     │                    │         │              │
│   │     │──④ Escrever na ──►│         │              │
│   │     │    memória         │         │              │
│   └─────┘                    └─────────┘              │
│                                                        │
│   ⚠️ CPU fica OCUPADA esperando (busy waiting)        │
│   ❌ Desperdício de tempo do processador               │
└────────────────────────────────────────────────────────┘
```

**Algoritmo da E/S Programada:**

```
procedimento E/S_Programada(comando, dispositivo):
    1. CPU envia COMANDO para o módulo de E/S
    2. repita
           CPU lê REGISTRO DE STATUS do módulo
       até que status == PRONTO        ← Busy waiting!
    3. CPU lê/escreve DADO do/no módulo de E/S
    4. CPU transfere dado para/da memória
    5. Se houver mais dados, volta ao passo 1
```

**Características:**

| Aspecto | Avaliação |
|---------|-----------|
| Simplicidade | ✅ Muito simples de implementar |
| Hardware adicional | ✅ Mínimo |
| Uso da CPU | ❌ CPU fica 100% ocupada esperando |
| Desempenho | ❌ Muito ineficiente para dispositivos lentos |
| Quando usar | Sistemas embarcados simples |

#### 4.2 E/S por Interrupção ⚡

Na E/S por interrupção, o processador **envia o comando e continua executando outras tarefas**. O dispositivo avisa quando está pronto através de uma **interrupção**.

```
┌────────────────────────────────────────────────────────┐
│              E/S POR INTERRUPÇÃO                        │
├────────────────────────────────────────────────────────┤
│                                                        │
│  Tempo ──────────────────────────────────────────►     │
│                                                        │
│  CPU:  │ Programa │ Envia │ Programa │ ISR  │ Programa │
│        │ principal│comando│ principal│(trata│ principal│
│        │ ........│.......│ ........│interr)│ ........│
│        └─────────┴───┬───┴─────────┴───┬──┴─────────┘│
│                      │                  │              │
│  E/S:               │ Processando... │              │
│        ─────────────┴──────────────┬──┘              │
│                                     │                  │
│                            INTERRUPÇÃO!                │
│                            "Estou pronto!"             │
│                                                        │
│  ✅ CPU LIVRE para outras tarefas enquanto espera!     │
└────────────────────────────────────────────────────────┘
```

**Algoritmo da E/S por Interrupção:**

```
procedimento E/S_por_Interrupção(comando, dispositivo):

  --- Fase 1: Iniciar operação ---
    1. CPU envia COMANDO para o módulo de E/S
    2. CPU CONTINUA executando outro programa  ← Não espera!

  --- Fase 2: Quando o dispositivo termina ---
    3. Módulo de E/S gera INTERRUPÇÃO
    4. CPU salva contexto do programa atual
    5. CPU executa a ISR (Interrupt Service Routine):
       a. Lê/escreve dado do/no módulo de E/S
       b. Transfere dado para/da memória
    6. CPU restaura contexto do programa anterior
    7. CPU retorna ao programa que estava executando
```

**Ciclo de interrupção detalhado:**

```
┌─────────────────────────────────────────────────────┐
│             CICLO DE INTERRUPÇÃO                     │
│                                                      │
│   ① CPU termina instrução atual                      │
│      │                                               │
│   ② CPU verifica se há interrupção pendente          │
│      │                                               │
│      ├── NÃO → Busca próxima instrução normalmente   │
│      │                                               │
│      └── SIM → ③ Salva contexto (PC, registradores)  │
│                │                                     │
│                ④ Identifica fonte da interrupção      │
│                │                                     │
│                ⑤ Carrega endereço da ISR              │
│                │  (da tabela de vetores de interrupção)│
│                │                                     │
│                ⑥ Executa ISR (transfere dados)         │
│                │                                     │
│                ⑦ Restaura contexto                    │
│                │                                     │
│                ⑧ Retorna ao programa interrompido     │
└─────────────────────────────────────────────────────┘
```

**Características:**

| Aspecto | Avaliação |
|---------|-----------|
| Complexidade | 🔶 Moderada |
| Hardware adicional | 🔶 Controlador de interrupções necessário |
| Uso da CPU | ✅ Livre durante a espera |
| Overhead | 🔶 Custo de salvar/restaurar contexto |
| Transferência | ❌ CPU ainda participa da transferência |
| Quando usar | Maioria dos dispositivos de E/S |

#### 4.3 Acesso Direto à Memória (DMA) 🚀

No DMA, um **controlador dedicado** transfere dados diretamente entre o dispositivo de E/S e a memória, **sem envolvimento do processador**.

```
┌────────────────────────────────────────────────────────┐
│            ACESSO DIRETO À MEMÓRIA (DMA)                │
├────────────────────────────────────────────────────────┤
│                                                        │
│        ┌──────┐                                        │
│        │ CPU  │                                        │
│        └──┬───┘                                        │
│           │  ① Configura DMA                           │
│           │  (endereço, tamanho,                       │
│           │   direção, dispositivo)                    │
│           │                                            │
│  ═════╤══╧══════════════════╤═══════════════           │
│       │   BARRAMENTO        │                          │
│  ═════╤═════════════════════╤═══════════════           │
│       │                     │                          │
│  ┌────┴────┐          ┌─────┴─────┐                   │
│  │ Memória │◄── ② ───►│  Controlad.│◄──►┌──────────┐ │
│  │         │ Transfere │    DMA    │    │Dispositivo│ │
│  │         │ direto!   │          │    │   de E/S   │ │
│  └─────────┘          └─────┬─────┘    └──────────┘ │
│                              │                        │
│                      ③ Ao terminar,                   │
│                        DMA gera                       │
│                        INTERRUPÇÃO                    │
│                        para a CPU                     │
│                                                        │
│  ✅ CPU COMPLETAMENTE LIVRE durante a transferência!   │
│  ✅ Dados vão DIRETO do dispositivo para a memória!    │
└────────────────────────────────────────────────────────┘
```

**Algoritmo do DMA:**

```
procedimento DMA(dispositivo, endereço_memória, tamanho):

  --- Fase 1: CPU configura o DMA ---
    1. CPU escreve no controlador DMA:
       - Endereço de memória de origem/destino
       - Número de bytes a transferir
       - Direção (leitura ou escrita)
       - Dispositivo de E/S envolvido
    2. CPU CONTINUA executando outros programas ← Totalmente livre!

  --- Fase 2: Controlador DMA trabalha sozinho ---
    3. Controlador DMA solicita o barramento
    4. Transfere dados diretamente: Dispositivo ↔ Memória
    5. Decrementa contador de bytes
    6. Se contador > 0, volta ao passo 3

  --- Fase 3: Conclusão ---
    7. Controlador DMA gera INTERRUPÇÃO para a CPU
    8. CPU executa ISR curta (apenas verifica sucesso)
```

**Registradores do Controlador DMA:**

```
┌──────────────────────────────────────────┐
│        CONTROLADOR DMA                    │
│                                          │
│  ┌────────────────────────────────────┐  │
│  │  Registrador de Endereço de Memória│  │ ← Onde armazenar/buscar
│  │  (MAR - Memory Address Register)   │  │
│  └────────────────────────────────────┘  │
│  ┌────────────────────────────────────┐  │
│  │  Registrador Contador de Bytes     │  │ ← Quantos bytes faltam
│  │  (Count Register)                  │  │
│  └────────────────────────────────────┘  │
│  ┌────────────────────────────────────┐  │
│  │  Registrador de Controle           │  │ ← Tipo de operação
│  │  (Leitura/Escrita, dispositivo)    │  │
│  └────────────────────────────────────┘  │
│  ┌────────────────────────────────────┐  │
│  │  Registrador de Status             │  │ ← Pronto, ocupado, erro
│  │  (Status Register)                 │  │
│  └────────────────────────────────────┘  │
└──────────────────────────────────────────┘
```

**Modos de Transferência DMA:**

| Modo | Descrição | Uso |
|------|-----------|-----|
| **Burst** | DMA toma o barramento e transfere todo o bloco de uma vez | Transferência de grandes blocos (disco) |
| **Cycle Stealing** | DMA "rouba" um ciclo de barramento por vez entre instruções da CPU | Quando CPU não pode parar |
| **Transparente** | DMA usa o barramento apenas quando a CPU não precisa dele | Mais eficiente, mais complexo |

**Características:**

| Aspecto | Avaliação |
|---------|-----------|
| Complexidade | 🔴 Alta (hardware dedicado) |
| Hardware adicional | 🔴 Controlador DMA necessário |
| Uso da CPU | ✅✅ Completamente livre |
| Overhead | ✅ Mínimo (apenas configuração e interrupção final) |
| Transferência | ✅ Direta, sem envolvimento da CPU |
| Quando usar | Transferências de grandes volumes (disco, rede, GPU) |

---

### 5. Comparação das Técnicas de E/S 📊

#### 5.1 Tabela Comparativa

| Característica | E/S Programada | E/S por Interrupção | DMA |
|---------------|---------------|---------------------|-----|
| CPU durante transferência | Ocupada (polling) | Livre (mas participa da transferência) | Totalmente livre |
| Quem transfere dados | CPU | CPU (na ISR) | Controlador DMA |
| Hardware necessário | Mínimo | Controlador de interrupções | Controlador DMA |
| Complexidade de software | Simples | Moderada | Moderada |
| Complexidade de hardware | Simples | Moderada | Alta |
| Desempenho | Baixo | Bom | Excelente |
| Adequado para | Dispositivos rápidos, embarcados simples | Dispositivos de velocidade média | Transferências volumosas |

#### 5.2 Diagrama Comparativo de Uso da CPU

```
E/S Programada:
CPU: [████ ESPERA ████][Trans][████ ESPERA ████][Trans]
     100% ocupada com E/S!

E/S por Interrupção:
CPU: [Programa][cmd][Programa...][ISR/Trans][Programa...]
     Livre entre os comandos, mas transfere na ISR

DMA:
CPU: [cfg][Programa...continua...executando...][ISR curta]
     Praticamente toda a CPU livre!

Legenda:
████ = CPU desperdiçada
cmd  = Enviar comando
cfg  = Configurar DMA
ISR  = Interrupt Service Routine
Trans= Transferência de dados pela CPU
```

#### 5.3 Evolução das Técnicas

```
  Simples ────────────────────────────────► Complexo
  Lento ──────────────────────────────────► Rápido

  E/S Programada ──► E/S por Interrupção ──► DMA
       │                      │                  │
  CPU faz tudo         CPU faz transf.     CPU não faz nada
  CPU desperdiçada     CPU mais livre      CPU totalmente livre
  Hardware simples     Hardware médio      Hardware complexo
```

---

### 6. Interfaces e Controladores de E/S 🔧

#### 6.1 Controlador de E/S

O controlador de E/S (também chamado de **adaptador** ou **interface**) é o hardware que gerencia a comunicação com um dispositivo específico.

```
┌─────────────────────────────────────────────────────┐
│            CONTROLADOR DE E/S (Exemplo: SATA)        │
│                                                      │
│  Barramento      ┌──────────────────────┐            │
│  do Sistema ◄──►│  Lógica do           │            │
│  (PCIe)         │  Controlador         │            │
│                  │  ┌────────────────┐  │  Cabo      │
│                  │  │Registradores   │  │  SATA      │
│                  │  │de Comando/     │  │◄──────►HDD │
│                  │  │Status/Dados    │  │        /SSD │
│                  │  └────────────────┐  │            │
│                  │  ┌────────────────┐  │            │
│                  │  │Buffer (FIFO)   │  │            │
│                  │  └────────────────┘  │            │
│                  │  ┌────────────────┐  │            │
│                  │  │Controle DMA    │  │            │
│                  │  └────────────────┘  │            │
│                  └──────────────────────┘            │
└─────────────────────────────────────────────────────┘
```

#### 6.2 Endereçamento de E/S

Existem duas abordagens para o processador acessar os registradores dos módulos de E/S:

| Abordagem | Descrição | Instruções | Exemplo |
|-----------|-----------|------------|---------|
| **E/S mapeada em memória** | Registradores de E/S ocupam endereços no espaço de memória | Mesmas instruções de memória (MOV, LOAD, STORE) | ARM, MIPS |
| **E/S isolada (port-mapped)** | Registradores de E/S têm espaço de endereço separado | Instruções especiais (IN, OUT) | x86 |

```
E/S Mapeada em Memória:                E/S Isolada (Port-Mapped):
┌────────────────────┐                 ┌──────────────┐  ┌──────────────┐
│  0x0000 - 0xEFFF   │ Memória RAM     │ Espaço de    │  │ Espaço de    │
│                    │                 │ Memória      │  │ Portas E/S   │
├────────────────────┤                 │              │  │              │
│  0xF000 - 0xF0FF   │ Teclado         │ 0x0000       │  │ Port 0x60    │
│  0xF100 - 0xF1FF   │ Display         │   ...        │  │ Port 0x64    │
│  0xF200 - 0xF2FF   │ Disco           │ 0xFFFF       │  │   ...        │
│  0xF300 - 0xFFFF   │ Outros E/S      │              │  │ Port 0x3F8   │
└────────────────────┘                 └──────────────┘  └──────────────┘

// E/S mapeada em memória:             // E/S isolada:
MOV R1, [0xF000]  // lê do teclado    IN R1, 0x60   // lê do teclado
MOV [0xF100], R2  // escreve display  OUT 0x3F8, R2 // escreve serial
```

---

### 7. Padrões de Barramento de E/S 🔌

#### 7.1 USB (Universal Serial Bus)

```
Evolução do USB:
┌──────────┬──────────┬────────────────┬──────────────────┐
│ Versão   │   Ano    │ Velocidade Máx │   Nome Popular   │
├──────────┼──────────┼────────────────┼──────────────────┤
│ USB 1.0  │  1996    │    1,5 Mbps    │   Low Speed      │
│ USB 1.1  │  1998    │   12 Mbps      │   Full Speed     │
│ USB 2.0  │  2000    │  480 Mbps      │   Hi-Speed       │
│ USB 3.0  │  2008    │  5 Gbps        │   SuperSpeed     │
│ USB 3.1  │  2013    │ 10 Gbps        │   SuperSpeed+    │
│ USB 3.2  │  2017    │ 20 Gbps        │   SuperSpeed 20G │
│ USB4     │  2019    │ 40 Gbps        │   USB4           │
│ USB4 v2  │  2022    │ 80 Gbps        │   USB4 v2        │
└──────────┴──────────┴────────────────┴──────────────────┘
```

```
Conectores USB:
┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐
│ Type-A   │  │ Type-B   │  │ Micro-B  │  │ Type-C   │
│ ┌──────┐ │  │ ┌────┐   │  │  ┌────┐  │  │ ┌──────┐ │
│ │ ████ │ │  │ │ ██ │   │  │  │ ██ │  │  │ │ ████ │ │
│ │      │ │  │ │    │   │  │  └────┘  │  │ └──────┘ │
│ └──────┘ │  │ └────┘   │  │          │  │ Reversível│
│ Clássico │  │Impressora│  │ Celular  │  │ Moderno  │
└──────────┘  └──────────┘  └──────────┘  └──────────┘
```

#### 7.2 SATA (Serial ATA)

| Versão | Velocidade | Uso |
|--------|-----------|-----|
| SATA I | 1,5 Gbps (150 MB/s) | HDDs antigos |
| SATA II | 3 Gbps (300 MB/s) | HDDs modernos |
| SATA III | 6 Gbps (600 MB/s) | SSDs SATA |

#### 7.3 PCIe (Peripheral Component Interconnect Express)

```
PCIe usa LANES (vias) ponto a ponto:

┌──────────┬────────────────────┬─────────────────────────┐
│  Versão  │  Velocidade/Lane   │  x16 (placa de vídeo)   │
├──────────┼────────────────────┼─────────────────────────┤
│ PCIe 3.0 │  ~1 GB/s           │  ~16 GB/s               │
│ PCIe 4.0 │  ~2 GB/s           │  ~32 GB/s               │
│ PCIe 5.0 │  ~4 GB/s           │  ~64 GB/s               │
│ PCIe 6.0 │  ~8 GB/s           │  ~128 GB/s              │
└──────────┴────────────────────┴─────────────────────────┘

Configurações de lanes:
  x1  ─ Placa de rede, placa de som
  x4  ─ SSD NVMe
  x8  ─ SSD RAID, placas especializadas
  x16 ─ Placa de vídeo (GPU)
```

#### 7.4 Comparação de Barramentos

```
Velocidade máxima teórica:

USB 2.0:     ██ (480 Mbps)
USB 3.0:     ██████████ (5 Gbps)
SATA III:    ████████████ (6 Gbps)
USB 3.2:     ████████████████████ (20 Gbps)
USB4:        ████████████████████████████████████████ (40 Gbps)
PCIe 4.0 x4: ████████████████████████████████████████████████████████████████ (64 Gbps)
PCIe 5.0 x16:████████████████████████████████████████████████████████████████████ (512 Gbps)
```

---

### 8. Exemplos do Mundo Real 🌍

#### 8.1 Como o Teclado Funciona (E/S por Interrupção)

```
Passo a passo quando você pressiona a tecla 'A':

1. 👆 Você pressiona 'A' no teclado
   │
2. ⌨️ Circuito do teclado detecta a tecla pressionada
   │  Gera o código da tecla (scan code: 0x1E para 'A')
   │
3. 🔌 Controlador do teclado (USB/PS2) recebe o scan code
   │  Armazena no buffer de dados
   │
4. ⚡ Controlador gera INTERRUPÇÃO (IRQ 1 / USB interrupt)
   │
5. 🖥️ CPU salva contexto do programa atual
   │
6. 📋 CPU executa a ISR do teclado:
   │  - Lê o scan code do controlador
   │  - Converte para código ASCII (0x41 = 'A')
   │  - Coloca no buffer de teclado do sistema operacional
   │
7. ↩️ CPU restaura contexto e volta ao programa anterior
   │
8. 📱 O programa que está em foco lê do buffer do SO
   │  e exibe 'A' na tela

Tempo total: ~1-5 ms (imperceptível para o humano)
```

#### 8.2 Como o Disco Lê Dados (DMA)

```
Quando um programa abre um arquivo de 1 MB:

1. 📂 Programa solicita ao SO: "Abrir arquivo.txt"
   │
2. 🖥️ SO determina os setores do arquivo no disco
   │
3. ⚙️ CPU configura o controlador DMA:
   │  - Endereço de memória destino
   │  - Número de bytes: 1.048.576 (1 MB)
   │  - Direção: Disco → Memória
   │
4. 🔄 CPU envia comando ao controlador SATA/NVMe:
   │  "Ler setores X a Y"
   │
5. 💨 CPU volta a executar outros programas!
   │
6. 💽 Disco/SSD lê os dados
   │  Controlador DMA transfere diretamente para a RAM
   │  (CPU NÃO participa!)
   │
7. ✅ DMA termina → Gera INTERRUPÇÃO
   │
8. 🖥️ CPU executa ISR curta:
   │  "Transferência completa, sem erros"
   │
9. 📂 SO avisa o programa: "Arquivo pronto na memória!"
```

#### 8.3 Como a Impressora Funciona

```
Impressora: Exemplo clássico de dispositivo LENTO

Velocidade da CPU:     ~10 bilhões de operações/s
Velocidade da impressora: ~20 páginas/minuto

Se usasse E/S Programada:
  CPU ficaria 99,999...% do tempo em busy waiting!
  Totalmente inaceitável.

Solução: Spooling + DMA
  1. Programa envia documento para o "spool" (fila no disco)
  2. Serviço de impressão (spooler) gerencia a fila
  3. DMA transfere dados do disco para a impressora
  4. CPU praticamente não é envolvida
  5. Usuário pode continuar trabalhando normalmente
```

#### 8.4 Placa de Rede e E/S

```
Recebimento de um pacote de rede (Ethernet):

  Internet → Cabo → ┌──────────────┐ → RAM
                     │Placa de Rede │
                     │  (NIC)       │
                     │              │
                     │ Buffer RX    │ ← Pacote chega aqui
                     │ Controlador  │
                     │ DMA          │ ← Transfere para RAM
                     └──────────────┘
                            │
                       Interrupção
                            │
                         ┌──┴──┐
                         │ CPU │ ← Processa o pacote
                         └─────┘   (cabeçalho TCP/IP, etc.)

Placas de rede modernas usam:
- DMA para transferir dados
- Interrupt Coalescing: agrupa várias interrupções
  em uma só (reduz overhead em redes rápidas)
- RSS (Receive Side Scaling): distribui pacotes
  entre múltiplos núcleos da CPU
```

---

### 9. Resumo da Aula 📝

```
┌──────────────────────────────────────────────────────────┐
│         RESUMO - SISTEMA DE ENTRADA E SAÍDA              │
├──────────────────────────────────────────────────────────┤
│                                                          │
│  Módulo de E/S: Interface entre CPU/memória e            │
│  dispositivos externos (controle, buffer, status)        │
│                                                          │
│  Categorias de dispositivos:                             │
│  • Legíveis por humanos (teclado, monitor)               │
│  • Legíveis por máquina (disco, sensores)                │
│  • Comunicação (rede, USB)                               │
│                                                          │
│  Técnicas de E/S:                                        │
│  ┌──────────┬─────────────┬────────┐                    │
│  │Programada│ Interrupção │  DMA   │                    │
│  │CPU espera│ CPU livre   │CPU livre│                    │
│  │CPU transf│ CPU transf  │DMA tran.│                    │
│  │Simples   │ Moderada    │Complexa │                    │
│  └──────────┴─────────────┴────────┘                    │
│                                                          │
│  Barramentos: USB (até 80 Gbps), SATA (6 Gbps),        │
│  PCIe (até 128 GB/s por x16)                            │
│                                                          │
│  E/S mapeada em memória vs. E/S isolada (portas)        │
└──────────────────────────────────────────────────────────┘
```

---

## 🔗 Referências

1. STALLINGS, W. **Arquitetura e Organização de Computadores**. 10ª ed. Pearson, 2017. Capítulo 7.
2. TANENBAUM, A. S. **Organização Estruturada de Computadores**. 6ª ed. Pearson, 2013. Capítulo 5.
3. PATTERSON, D.; HENNESSY, J. **Organização e Projeto de Computadores**. 5ª ed. Elsevier, 2017. Capítulo 5.

---

## ➡️ Próxima Aula

**Aula 16 - Estrutura da CPU, Organização de Registradores e Ciclo de Instrução:** Estrutura interna da CPU, tipos de registradores, ciclo de instrução detalhado e micro-operações.

---

> 💡 **Dica de estudo:** Para entender bem as diferenças entre as técnicas de E/S, pense na analogia de esperar uma pizza: Polling = ficar olhando pela janela o tempo todo; Interrupção = colocar o celular para tocar quando o entregador chegar; DMA = alguém recebe a pizza para você e coloca na mesa!
