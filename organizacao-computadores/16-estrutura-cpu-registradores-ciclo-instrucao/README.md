# 🧠 Aula 16 - Estrutura da CPU, Organização de Registradores e Ciclo de Instrução

## 📋 Informações da Aula

| Item | Descrição |
|------|-----------|
| **Curso** | Organização de Computadores |
| **Aula** | 16 |
| **Tema** | Estrutura da CPU, Registradores e Ciclo de Instrução |
| **Duração** | 2 horas (120 minutos) |
| **Pré-requisitos** | Aulas 13-15 - Memória e E/S |

---

## 🎯 Objetivos de Aprendizagem

Ao final desta aula, o estudante será capaz de:

1. ✅ Descrever a estrutura interna da CPU e seus componentes
2. ✅ Classificar e explicar os diferentes tipos de registradores
3. ✅ Compreender a organização dos registradores e o fluxo de dados
4. ✅ Detalhar cada fase do ciclo de instrução
5. ✅ Interpretar o formato de instruções (opcode e operandos)
6. ✅ Descrever as micro-operações de cada fase do ciclo
7. ✅ Entender o caminho de dados e os sinais de controle
8. ✅ Relacionar fatores que afetam o desempenho da CPU

---

## 📚 Conteúdo

### 1. Estrutura Interna da CPU 🏗️

#### 1.1 Visão Geral

A CPU (Central Processing Unit) é o "cérebro" do computador. Internamente, ela é composta por quatro componentes principais:

```
┌────────────────────────────────────────────────────────────┐
│                         CPU                                 │
│                                                             │
│  ┌──────────────────────┐    ┌──────────────────────────┐  │
│  │   UNIDADE DE CONTROLE│    │  UNIDADE LÓGICA E        │  │
│  │        (UC)          │    │  ARITMÉTICA (ULA/ALU)     │  │
│  │                      │    │                          │  │
│  │  - Decodifica instr. │    │  - Operações aritméticas │  │
│  │  - Gera sinais de    │    │    (soma, subtração,     │  │
│  │    controle          │    │     multiplicação...)    │  │
│  │  - Coordena todos    │    │  - Operações lógicas     │  │
│  │    os componentes    │    │    (AND, OR, NOT, XOR)   │  │
│  │                      │    │  - Comparações           │  │
│  │  É o "maestro" da    │    │  - Deslocamentos (shift) │  │
│  │  orquestra!          │    │                          │  │
│  └──────────┬───────────┘    └────────────┬─────────────┘  │
│             │                              │                │
│             └──────────────┬───────────────┘                │
│                            │                                │
│  ┌─────────────────────────┴──────────────────────────────┐│
│  │              BARRAMENTO INTERNO DA CPU                  ││
│  └─────────────────────────┬──────────────────────────────┘│
│                            │                                │
│  ┌─────────────────────────┴──────────────────────────────┐│
│  │                   REGISTRADORES                         ││
│  │                                                         ││
│  │  ┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐ ┌────┐   ││
│  │  │ PC │ │ IR │ │ SP │ │ MAR│ │ MBR│ │ PSW│ │R0-Rn│   ││
│  │  └────┘ └────┘ └────┘ └────┘ └────┘ └────┘ └────┘   ││
│  └─────────────────────────────────────────────────────────┘│
│                            │                                │
│                    ┌───────┴───────┐                        │
│                    │  Interface    │                        │
│                    │  de Barramento│◄──► Barramento Externo │
│                    └───────────────┘    (Memória e E/S)     │
└────────────────────────────────────────────────────────────┘
```

#### 1.2 Componentes em Detalhe

| Componente | Função | Analogia |
|-----------|--------|----------|
| **ALU** (Unidade Lógica e Aritmética) | Realiza todas as operações matemáticas e lógicas | A "calculadora" da CPU |
| **UC** (Unidade de Controle) | Decodifica instruções e gera sinais de controle | O "maestro" que coordena tudo |
| **Registradores** | Armazenamento ultrarrápido dentro da CPU | A "memória de trabalho imediata" |
| **Barramento Interno** | Conecta os componentes internos da CPU | As "estradas" internas |

#### 1.3 A ALU (Unidade Lógica e Aritmética)

```
                    Operandos
                   ┌───┐ ┌───┐
                   │ A │ │ B │
                   └─┬─┘ └─┬─┘
                     │     │
                     ▼     ▼
              ┌──────────────────┐
              │                  │
  Operação ──►│       ALU        │──► Resultado
  (da UC)     │                  │
              │  ┌─────────────┐ │──► Flags (PSW)
              │  │ Soma        │ │    ├── Zero (Z)
              │  │ Subtração   │ │    ├── Carry (C)
              │  │ AND/OR/XOR  │ │    ├── Overflow (V)
              │  │ NOT         │ │    ├── Negativo (N)
              │  │ Shift/Rotate│ │    └── ...
              │  │ Comparação  │ │
              │  └─────────────┘ │
              └──────────────────┘

A ALU recebe:
  - Dois operandos (A e B)
  - Um código de operação (da Unidade de Controle)

A ALU produz:
  - O resultado da operação
  - Flags indicando propriedades do resultado
```

#### 1.4 A Unidade de Controle (UC)

```
┌──────────────────────────────────────────────────────────┐
│                  UNIDADE DE CONTROLE                      │
│                                                          │
│  Entradas:                     Saídas (Sinais de Controle):
│  ┌───────────┐                 │
│  │ Instrução │                 ├──► Para ALU: operação
│  │   (IR)    │─────────►       ├──► Para Registradores: ler/escrever
│  └───────────┘       UC        ├──► Para Barramento: endereço/dados
│  ┌───────────┐  (Decodifica    ├──► Para Memória: leitura/escrita
│  │   Flags   │   e gera        ├──► Para E/S: comandos
│  │   (PSW)   │─────────►sinais)├──► Para PC: incrementar/carregar
│  └───────────┘                 ├──► Para MUX: selecionar caminhos
│  ┌───────────┐                 │
│  │   Clock   │─────────►       │
│  └───────────┘                 │
│                                                          │
│  Implementações:                                         │
│  • Hardwired (circuito dedicado) → Mais rápida          │
│  • Microprogramada (microcódigo) → Mais flexível        │
└──────────────────────────────────────────────────────────┘
```

---

### 2. Tipos de Registradores 📋

Os registradores são classificados em duas grandes categorias:

#### 2.1 Registradores de Propósito Geral (GPR)

São registradores que o programador pode usar livremente para armazenar dados e endereços durante a execução do programa.

```
Registradores de Propósito Geral (exemplo x86-64):
┌──────────┬──────────────────────────────────────────────────┐
│ Registr. │ Uso típico (convenção, não obrigatório)           │
├──────────┼──────────────────────────────────────────────────┤
│   RAX    │ Acumulador (resultado de operações, retorno)     │
│   RBX    │ Base (endereçamento de memória)                   │
│   RCX    │ Contador (loops, contagens)                       │
│   RDX    │ Dados (operações de E/S, multiplicação)           │
│   RSI    │ Source Index (índice de origem em cópias)         │
│   RDI    │ Destination Index (índice de destino)             │
│   RBP    │ Base Pointer (base do stack frame)                │
│   RSP    │ Stack Pointer (topo da pilha)                     │
│   R8-R15 │ Registradores adicionais (x86-64)                │
└──────────┴──────────────────────────────────────────────────┘

Cada registrador: 64 bits (8 bytes) em x86-64
Total: 16 registradores × 64 bits = 128 bytes

Subregistradores (compatibilidade):
RAX (64 bits)
├── EAX (32 bits inferiores)
│   ├── AX (16 bits inferiores)
│   │   ├── AH (8 bits superiores de AX)
│   │   └── AL (8 bits inferiores de AX)
```

#### 2.2 Registradores de Propósito Especial

São registradores com funções específicas e essenciais para o funcionamento da CPU.

##### 2.2.1 PC (Program Counter) / IP (Instruction Pointer)

```
┌─────────────────────────────────────────────────────────┐
│  PC - PROGRAM COUNTER (Contador de Programa)             │
│                                                          │
│  Função: Armazena o endereço da PRÓXIMA instrução        │
│          a ser buscada na memória                        │
│                                                          │
│  Comportamento:                                          │
│  ┌────┐                                                  │
│  │ PC │= 0x1000  → Busca instrução no endereço 0x1000   │
│  └────┘                                                  │
│  Após busca:                                             │
│  ┌────┐                                                  │
│  │ PC │= 0x1004  → Incrementado automaticamente          │
│  └────┘             (próxima instrução)                   │
│                                                          │
│  Em caso de DESVIO (jump/branch):                        │
│  ┌────┐                                                  │
│  │ PC │= 0x2000  → Carregado com endereço do destino     │
│  └────┘                                                  │
│                                                          │
│  💡 Sem o PC, a CPU não saberia QUAL instrução executar! │
└─────────────────────────────────────────────────────────┘
```

##### 2.2.2 IR (Instruction Register)

```
┌─────────────────────────────────────────────────────────┐
│  IR - INSTRUCTION REGISTER (Registrador de Instrução)    │
│                                                          │
│  Função: Armazena a instrução ATUALMENTE sendo executada │
│                                                          │
│  Conteúdo típico:                                        │
│  ┌────────────────────────────────────┐                  │
│  │  Opcode  │  Operando1  │ Operando2 │                  │
│  │  (6 bits)│  (5 bits)   │ (5 bits)  │  ...             │
│  └────────────────────────────────────┘                  │
│                                                          │
│  A Unidade de Controle DECODIFICA o conteúdo do IR       │
│  para determinar qual operação executar.                 │
└─────────────────────────────────────────────────────────┘
```

##### 2.2.3 SP (Stack Pointer)

```
┌─────────────────────────────────────────────────────────┐
│  SP - STACK POINTER (Ponteiro de Pilha)                  │
│                                                          │
│  Função: Aponta para o TOPO da pilha na memória          │
│                                                          │
│  A pilha é usada para:                                   │
│  • Salvar endereço de retorno em chamadas de sub-rotina  │
│  • Salvar registradores durante interrupções             │
│  • Variáveis locais de funções                           │
│  • Passagem de parâmetros                                │
│                                                          │
│  Operações:                                              │
│                                                          │
│  PUSH (empilhar):        POP (desempilhar):              │
│  SP ← SP - tamanho      Dado ← Memória[SP]              │
│  Memória[SP] ← Dado     SP ← SP + tamanho               │
│                                                          │
│  Memória:                                                │
│  Endereço alto                                           │
│  ┌──────────┐                                            │
│  │  ......  │                                            │
│  ├──────────┤                                            │
│  │ Dado 1   │ ← Primeiro dado empilhado                  │
│  ├──────────┤                                            │
│  │ Dado 2   │                                            │
│  ├──────────┤                                            │
│  │ Dado 3   │ ← SP aponta aqui (topo da pilha)          │
│  ├──────────┤                                            │
│  │ (livre)  │                                            │
│  └──────────┘                                            │
│  Endereço baixo                                          │
│                                                          │
│  A pilha geralmente CRESCE para endereços MENORES!       │
└─────────────────────────────────────────────────────────┘
```

##### 2.2.4 PSW / FLAGS (Program Status Word)

```
┌──────────────────────────────────────────────────────────┐
│  PSW / FLAGS - PALAVRA DE STATUS DO PROGRAMA              │
│                                                           │
│  Contém bits individuais (flags) que indicam:             │
│  • Resultado da última operação da ALU                    │
│  • Estado do processador                                  │
│                                                           │
│  FLAGS Register (x86 simplificado):                       │
│  ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐              │
│  │...│ O │ D │ I │ T │ S │ Z │...│ C │...│              │
│  └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘              │
│                                                           │
│  Flag │ Nome            │ Significado                     │
│  ─────┼─────────────────┼──────────────────────────────── │
│   C   │ Carry           │ Houve "vai-um" (carry/borrow)  │
│   Z   │ Zero            │ Resultado é zero               │
│   S   │ Sign            │ Resultado é negativo           │
│   O   │ Overflow        │ Overflow em aritmética sinaliz.│
│   I   │ Interrupt Enable│ Interrupções habilitadas       │
│   D   │ Direction       │ Direção em operações de string │
│   T   │ Trap            │ Modo de depuração passo a passo│
│                                                           │
│  Exemplo:                                                 │
│  ADD R1, R2      ; R1 = R1 + R2                          │
│  Se resultado = 0: Flag Z = 1                            │
│  Se houve carry:   Flag C = 1                            │
│  Se resultado < 0: Flag S = 1                            │
│                                                           │
│  JZ label        ; Salta se Z = 1 (resultado foi zero)   │
│  JC label        ; Salta se C = 1 (houve carry)          │
└──────────────────────────────────────────────────────────┘
```

##### 2.2.5 MAR e MBR (Registradores de Interface com Memória)

```
┌─────────────────────────────────────────────────────────┐
│  MAR - Memory Address Register                           │
│  MBR - Memory Buffer Register (ou MDR - Data Register)   │
│                                                          │
│       CPU                          Memória               │
│  ┌─────────────┐              ┌────────────────┐        │
│  │    ┌─────┐  │  Barramento  │                │        │
│  │    │ MAR │──┼──de Endereço─►│   Endereço     │        │
│  │    └─────┘  │              │      │         │        │
│  │    ┌─────┐  │  Barramento  │      ▼         │        │
│  │    │ MBR │◄─┼──de Dados────┤   Dados        │        │
│  │    └─────┘  │              │                │        │
│  └─────────────┘              └────────────────┘        │
│                                                          │
│  LEITURA da memória:                                     │
│  1. MAR ← endereço desejado                             │
│  2. Sinal de leitura é enviado                          │
│  3. Memória coloca dado no barramento                   │
│  4. MBR ← dado lido                                     │
│                                                          │
│  ESCRITA na memória:                                     │
│  1. MAR ← endereço desejado                             │
│  2. MBR ← dado a escrever                               │
│  3. Sinal de escrita é enviado                          │
│  4. Memória armazena o conteúdo de MBR                  │
└─────────────────────────────────────────────────────────┘
```

#### 2.3 Tabela Resumo dos Registradores

| Registrador | Nome | Bits (x86-64) | Função |
|------------|------|--------------|--------|
| **PC/IP** | Program Counter | 64 | Endereço da próxima instrução |
| **IR** | Instruction Register | 64+ | Instrução atual sendo executada |
| **SP/RSP** | Stack Pointer | 64 | Topo da pilha |
| **PSW/FLAGS** | Program Status Word | 64 | Flags de estado/condição |
| **MAR** | Memory Address Register | 64 | Endereço para acessar memória |
| **MBR/MDR** | Memory Buffer Register | 64 | Dado lido/a escrever na memória |
| **RAX-R15** | General Purpose | 64 × 16 | Dados e endereços do programador |

---

### 3. Organização dos Registradores e Fluxo de Dados 🔀

#### 3.1 Caminho de Dados (Datapath)

```
┌──────────────────────────────────────────────────────────┐
│                   DATAPATH DA CPU                         │
│                                                           │
│  ┌──────┐                              ┌──────────────┐  │
│  │  PC  │────────────────┐             │              │  │
│  └──────┘                │             │  Unidade de  │  │
│                          │             │  Controle    │  │
│  ┌──────┐                │   Sinais    │  (UC)        │  │
│  │  IR  │◄───────────────┤◄────────────│              │  │
│  └──────┘                │             │              │  │
│                          │             └──────────────┘  │
│  ┌──────┐            ┌───┴───┐                          │
│  │  MAR │◄───────────│       │                          │
│  └──┬───┘            │  MUX  │    ┌─────────────────┐   │
│     │                │       │    │  Banco de       │   │
│     │    ┌──────┐    └───┬───┘    │  Registradores  │   │
│     │    │  MBR │◄──►    │    ┌──►│  ┌───┐ ┌───┐   │   │
│     │    └──────┘        │    │   │  │R0 │ │R4 │   │   │
│     │                    │    │   │  │R1 │ │R5 │   │   │
│     ▼                    │    │   │  │R2 │ │R6 │   │   │
│  ┌──────┐               ▼    │   │  │R3 │ │R7 │   │   │
│  │      │         ┌──────────┐│   │  └───┘ └───┘   │   │
│  │Memória│◄──────►│   ALU    │├───│                 │   │
│  │      │         │          ││   └─────────────────┘   │
│  └──────┘         │ A OP B   ││                          │
│                   └────┬─────┘│                          │
│                        │      │                          │
│                        ▼      │                          │
│                   ┌────────┐  │                          │
│                   │  PSW   │  │                          │
│                   │ (Flags)│  │                          │
│                   └────────┘  │                          │
│                               │                          │
│  ◄──────── Barramento Interno ─────────►                 │
└──────────────────────────────────────────────────────────┘
```

#### 3.2 Fluxo de Dados por Tipo de Instrução

```
INSTRUÇÃO ARITMÉTICA: ADD R1, R2, R3  (R1 = R2 + R3)
─────────────────────────────────────────────────────
1. Buscar instrução: PC → MAR → Memória → MBR → IR
2. Decodificar: UC lê IR, identifica ADD
3. Executar: R2 → ALU entrada A
             R3 → ALU entrada B
             ALU: A + B = Resultado
4. Armazenar: Resultado → R1
              ALU → PSW (flags)
              PC → PC + 4

INSTRUÇÃO DE MEMÓRIA: LOAD R1, [endereço]
──────────────────────────────────────────
1. Buscar instrução: PC → MAR → Memória → MBR → IR
2. Decodificar: UC lê IR, identifica LOAD
3. Calcular endereço: Endereço do operando
4. Acessar memória: Endereço → MAR → Memória → MBR
5. Armazenar: MBR → R1

INSTRUÇÃO DE DESVIO: JZ label  (salta se zero)
───────────────────────────────────────────────
1. Buscar instrução: PC → MAR → Memória → MBR → IR
2. Decodificar: UC lê IR, identifica JZ
3. Verificar condição: UC lê flag Z do PSW
4. Se Z = 1: PC ← endereço do label
   Se Z = 0: PC continua normal (próxima instrução)
```

---

### 4. Ciclo de Instrução em Detalhe ♻️

#### 4.1 O Ciclo Básico

Toda instrução passa por um ciclo de fases:

```
┌─────────┐    ┌──────────┐    ┌──────────┐    ┌──────────┐
│  FETCH   │──►│  DECODE   │──►│ EXECUTE   │──►│  STORE   │
│ (Buscar) │   │(Decodif.) │   │(Executar) │   │(Armazenar)│
└─────────┘    └──────────┘    └──────────┘    └──────────┘
     ▲                                               │
     └───────────────────────────────────────────────┘
                    (próxima instrução)
```

#### 4.2 Fases Detalhadas

```
═══════════════════════════════════════════════════════════
                 CICLO DE INSTRUÇÃO DETALHADO
═══════════════════════════════════════════════════════════

┌──────────────────────────────────────────────────────────┐
│ FASE 1: FETCH (Busca da Instrução)                       │
│                                                          │
│ Micro-operações:                                         │
│   t1: MAR ← PC              (endereço da instrução)     │
│   t2: MBR ← Memória[MAR]    (busca instrução)           │
│       PC ← PC + n           (incrementa PC)             │
│   t3: IR ← MBR              (carrega no registrador)    │
│                                                          │
│ O que acontece:                                          │
│   • PC diz ONDE está a próxima instrução                │
│   • A memória entrega a instrução                       │
│   • IR recebe a instrução para decodificação            │
│   • PC já aponta para a instrução seguinte              │
└──────────────────────────────────────────────────────────┘
                        │
                        ▼
┌──────────────────────────────────────────────────────────┐
│ FASE 2: DECODE (Decodificação)                           │
│                                                          │
│ Micro-operações:                                         │
│   t4: UC decodifica campo de opcode do IR                │
│       UC identifica tipo de instrução                    │
│       UC determina operandos necessários                 │
│                                                          │
│ O que acontece:                                          │
│   • UC analisa o conteúdo do IR                         │
│   • Identifica: qual operação? quais operandos?         │
│   • Gera sinais de controle para a fase de execução     │
│   • Se necessário, busca operandos da memória           │
└──────────────────────────────────────────────────────────┘
                        │
                        ▼
┌──────────────────────────────────────────────────────────┐
│ FASE 3: EXECUTE (Execução)                               │
│                                                          │
│ Depende do tipo de instrução:                            │
│                                                          │
│ Aritmética/Lógica (ex: ADD R1, R2):                     │
│   t5: A ← R2                (operando 1 para ALU)       │
│   t6: Resultado ← A + R1    (ALU executa operação)      │
│       PSW ← flags da ALU    (atualiza flags)            │
│                                                          │
│ Acesso à memória (ex: LOAD R1, [addr]):                 │
│   t5: MAR ← campo de endereço do IR                     │
│   t6: MBR ← Memória[MAR]    (lê dado da memória)       │
│                                                          │
│ Desvio (ex: JMP addr):                                   │
│   t5: PC ← campo de endereço do IR                      │
│       (modifica o fluxo de execução)                     │
└──────────────────────────────────────────────────────────┘
                        │
                        ▼
┌──────────────────────────────────────────────────────────┐
│ FASE 4: STORE (Armazenamento do Resultado)               │
│                                                          │
│ Micro-operações:                                         │
│                                                          │
│ Para registrador:                                        │
│   t7: R1 ← Resultado         (salva no registrador)     │
│                                                          │
│ Para memória (ex: STORE R1, [addr]):                     │
│   t7: MAR ← endereço                                    │
│       MBR ← R1                                           │
│   t8: Memória[MAR] ← MBR     (escreve na memória)       │
│                                                          │
│ Após o armazenamento:                                    │
│   → Volta para FASE 1 (Fetch da próxima instrução)      │
│   → Verifica se há interrupção pendente                  │
└──────────────────────────────────────────────────────────┘
```

#### 4.3 Ciclo de Instrução com Interrupção

```
┌───────┐   ┌────────┐   ┌─────────┐   ┌───────┐   ┌──────────────┐
│ FETCH │──►│ DECODE │──►│ EXECUTE │──►│ STORE │──►│ Interrupção? │
└───────┘   └────────┘   └─────────┘   └───────┘   └──────┬───────┘
   ▲                                                       │
   │                                            ┌──────────┴──────┐
   │                                            │                 │
   │                                           NÃO               SIM
   │                                            │                 │
   │                                            │    ┌────────────┴──┐
   └────────────────────────────────────────────┘    │ Salva contexto│
                                                      │ Desvia para   │
                                                      │ ISR            │
                                                      └───────────────┘
```

---

### 5. Formato de Instruções 📐

#### 5.1 Estrutura Geral

```
Uma instrução de máquina é composta por:

┌────────────────────┬────────────────────────────────────┐
│      OPCODE        │           OPERANDOS                │
│  (código da        │  (dados ou endereços de dados      │
│   operação)        │   sobre os quais operar)           │
└────────────────────┴────────────────────────────────────┘

O OPCODE diz O QUE fazer
Os OPERANDOS dizem COM O QUE fazer
```

#### 5.2 Tipos de Formato por Número de Operandos

```
═══ 3 OPERANDOS ═══ (RISC: ARM, MIPS)
┌────────┬─────────┬─────────┬─────────┐
│ Opcode │ Destino │ Fonte 1 │ Fonte 2 │
└────────┴─────────┴─────────┴─────────┘
Exemplo: ADD R1, R2, R3    ; R1 = R2 + R3

═══ 2 OPERANDOS ═══ (x86)
┌────────┬─────────┬─────────┐
│ Opcode │Dest/Font│ Fonte 2 │
└────────┴─────────┴─────────┘
Exemplo: ADD R1, R2         ; R1 = R1 + R2
(Destino é também um dos fontes)

═══ 1 OPERANDO ═══ (Acumulador implícito)
┌────────┬─────────┐
│ Opcode │Operando │
└────────┴─────────┘
Exemplo: ADD R2              ; ACC = ACC + R2
(Acumulador é fonte e destino implícito)

═══ 0 OPERANDOS ═══ (Máquina de pilha)
┌────────┐
│ Opcode │
└────────┘
Exemplo: ADD                 ; Topo_pilha = Topo + Subtopo
(Operandos retirados da pilha automaticamente)
```

#### 5.3 Exemplo MIPS (32 bits)

```
Formato R (Registrador):
┌────────┬───────┬───────┬───────┬───────┬────────┐
│ opcode │  rs   │  rt   │  rd   │ shamt │ funct  │
│ 6 bits │5 bits │5 bits │5 bits │5 bits │ 6 bits │
└────────┴───────┴───────┴───────┴───────┴────────┘

Exemplo: ADD $t0, $t1, $t2  → R[8] = R[9] + R[10]
┌────────┬───────┬───────┬───────┬───────┬────────┐
│ 000000 │ 01001 │ 01010 │ 01000 │ 00000 │ 100000 │
│ (R-type)│ ($t1) │ ($t2) │ ($t0) │(não usa)│ (ADD) │
└────────┴───────┴───────┴───────┴───────┴────────┘

Formato I (Imediato):
┌────────┬───────┬───────┬──────────────────────┐
│ opcode │  rs   │  rt   │     imediato          │
│ 6 bits │5 bits │5 bits │     16 bits           │
└────────┴───────┴───────┴──────────────────────┘

Exemplo: ADDI $t0, $t1, 100  → R[8] = R[9] + 100
┌────────┬───────┬───────┬──────────────────────┐
│ 001000 │ 01001 │ 01000 │ 0000000001100100      │
│ (ADDI) │ ($t1) │ ($t0) │       (100)           │
└────────┴───────┴───────┴──────────────────────┘

Formato J (Jump):
┌────────┬──────────────────────────────────────┐
│ opcode │            endereço                    │
│ 6 bits │            26 bits                     │
└────────┴──────────────────────────────────────┘
```

---

### 6. Micro-operações para Cada Fase ⚙️

#### 6.1 Micro-operações do Fetch

```
═══ FASE DE FETCH (igual para TODAS as instruções) ═══

Ciclo de clock t1:
  MAR ← PC
  [PC é copiado para MAR para enviar endereço à memória]

Ciclo de clock t2:
  MBR ← Memória[MAR]       [Instrução é lida da memória]
  PC ← PC + 4              [PC incrementado (instrução de 4 bytes)]
  [Estas duas operações podem ser feitas em PARALELO]

Ciclo de clock t3:
  IR ← MBR                 [Instrução copiada para IR]

Total do Fetch: 3 ciclos de clock
```

#### 6.2 Micro-operações do Decode e Execute

```
═══ INSTRUÇÃO: ADD R1, R2, R3 ═══

Fetch (comum):
  t1: MAR ← PC
  t2: MBR ← Mem[MAR]; PC ← PC + 4
  t3: IR ← MBR

Decode:
  t4: UC decodifica IR → operação = ADD
      A ← R2                [operando 1 para registro temp.]
      B ← R3                [operando 2 para registro temp.]

Execute:
  t5: ALU_resultado ← A + B
      PSW ← flags(ALU)      [Zero, Carry, Overflow, etc.]

Store:
  t6: R1 ← ALU_resultado

Total: 6 ciclos

═══ INSTRUÇÃO: LOAD R1, [0x1000] ═══

Fetch:
  t1: MAR ← PC
  t2: MBR ← Mem[MAR]; PC ← PC + 4
  t3: IR ← MBR

Decode:
  t4: UC decodifica IR → operação = LOAD
      MAR ← campo_endereço(IR)   [0x1000]

Execute (acesso à memória):
  t5: MBR ← Mem[MAR]             [Lê dado do endereço 0x1000]

Store:
  t6: R1 ← MBR                   [Dado vai para R1]

Total: 6 ciclos

═══ INSTRUÇÃO: STORE R1, [0x2000] ═══

Fetch:
  t1: MAR ← PC
  t2: MBR ← Mem[MAR]; PC ← PC + 4
  t3: IR ← MBR

Decode:
  t4: UC decodifica IR → operação = STORE
      MAR ← campo_endereço(IR)   [0x2000]
      MBR ← R1                   [Dado a escrever]

Execute (acesso à memória):
  t5: Mem[MAR] ← MBR             [Escreve na memória]

Total: 5 ciclos

═══ INSTRUÇÃO: JZ label (Jump if Zero) ═══

Fetch:
  t1: MAR ← PC
  t2: MBR ← Mem[MAR]; PC ← PC + 4
  t3: IR ← MBR

Decode:
  t4: UC decodifica IR → operação = JZ
      UC verifica flag Z do PSW

Execute:
  t5: Se Z = 1: PC ← campo_endereço(IR)
      Se Z = 0: (nada, PC já aponta para a próxima)

Total: 5 ciclos
```

---

### 7. Caminho de Dados e Sinais de Controle 🔌

#### 7.1 Sinais de Controle

```
A Unidade de Controle gera sinais para coordenar tudo:

┌──────────────────────────────────────────────────────┐
│            SINAIS DE CONTROLE (exemplos)               │
├──────────────────────────────────────────────────────┤
│                                                       │
│  Para a Memória:                                      │
│  ├── MEM_READ:  Ativar leitura da memória            │
│  └── MEM_WRITE: Ativar escrita na memória            │
│                                                       │
│  Para os Registradores:                               │
│  ├── REG_READ:  Ler registrador especificado         │
│  ├── REG_WRITE: Escrever no registrador especificado │
│  └── REG_SELECT: Selecionar qual registrador (campo) │
│                                                       │
│  Para a ALU:                                          │
│  ├── ALU_OP: Código da operação (ADD, SUB, AND, etc.)│
│  └── ALU_SRC: Selecionar fonte dos operandos         │
│                                                       │
│  Para o PC:                                           │
│  ├── PC_INC: Incrementar PC                          │
│  └── PC_LOAD: Carregar novo valor no PC (desvio)     │
│                                                       │
│  Para o IR:                                           │
│  └── IR_LOAD: Carregar instrução da memória no IR    │
│                                                       │
│  Para os MUXes:                                       │
│  └── MUX_SEL: Selecionar entrada do multiplexador    │
│                                                       │
└──────────────────────────────────────────────────────┘
```

#### 7.2 Exemplo: Sinais para ADD R1, R2, R3

```
Fase    │ Ciclo │ Sinais de Controle Ativos
────────┼───────┼──────────────────────────────────
Fetch   │  t1   │ PC_OUT, MAR_IN
        │  t2   │ MEM_READ, MBR_IN, PC_INC
        │  t3   │ MBR_OUT, IR_IN
────────┼───────┼──────────────────────────────────
Decode  │  t4   │ IR_OUT(rs), REG_READ, A_IN
        │       │ IR_OUT(rt), REG_READ, B_IN
────────┼───────┼──────────────────────────────────
Execute │  t5   │ A_OUT, B_OUT, ALU_OP=ADD, TEMP_IN
────────┼───────┼──────────────────────────────────
Store   │  t6   │ TEMP_OUT, IR_OUT(rd), REG_WRITE

Cada sinal é um fio de controle que ativa ou desativa
um componente específico em cada ciclo de clock.
```

---

### 8. Fatores de Desempenho da CPU 📈

#### 8.1 A Equação Fundamental de Desempenho

```
Tempo de Execução = Nº de Instruções × CPI × Tempo de Ciclo

Onde:
  - Nº de Instruções: depende do programa e do compilador
  - CPI: Ciclos por Instrução (depende da arquitetura)
  - Tempo de Ciclo: 1/Frequência do clock (depende do hardware)

Exemplo:
  Programa com 10⁹ instruções
  CPI médio = 1,5
  Clock = 3 GHz (ciclo = 0,333 ns)

  Tempo = 10⁹ × 1,5 × 0,333 × 10⁻⁹ = 0,5 segundos
```

#### 8.2 Fatores que Afetam o Desempenho

| Fator | Impacto | Como Melhorar |
|-------|---------|---------------|
| **Frequência do clock** | Mais ciclos por segundo | Tecnologia de fabricação |
| **CPI** | Menos ciclos por instrução | Pipeline, superscalar |
| **Nº de instruções** | Menos trabalho total | Melhor compilador, ISA eficiente |
| **Cache hit rate** | Menos stalls de memória | Maior/melhor cache |
| **Pipeline** | Sobreposição de fases | Mais estágios de pipeline |
| **Superscalar** | Múltiplas instruções por ciclo | Múltiplas ALUs |
| **Predição de desvio** | Menos desperdício por branch | Algoritmos de predição |

#### 8.3 CPUs Modernas: Técnicas Avançadas

```
CPU moderna vs. CPU básica que estudamos:

CPU Básica (esta aula):
  ┌───────┐ ┌────────┐ ┌─────────┐ ┌───────┐
  │ Fetch │→│ Decode │→│ Execute │→│ Store │→ Próxima instrução
  └───────┘ └────────┘ └─────────┘ └───────┘
  1 instrução de cada vez

CPU com Pipeline (5 estágios):
  Instr 1: │ IF │ ID │ EX │ MEM│ WB │
  Instr 2:      │ IF │ ID │ EX │ MEM│ WB │
  Instr 3:           │ IF │ ID │ EX │ MEM│ WB │
  Instr 4:                │ IF │ ID │ EX │ MEM│ WB │
  Instr 5:                     │ IF │ ID │ EX │ MEM│ WB │

  Até 5 instruções em execução simultânea!
  CPI ideal: 1 (uma instrução completa por ciclo)

CPU Superscalar (2-wide):
  Tempo:    │ t1    │ t2    │ t3    │ t4    │
  Instr A:  │IF  ID │EX MEM│ WB    │       │
  Instr B:  │IF  ID │EX MEM│ WB    │       │
  Instr C:  │       │IF  ID│EX MEM │ WB    │
  Instr D:  │       │IF  ID│EX MEM │ WB    │

  2 instruções completam por ciclo!
  CPI ideal: 0,5 (IPC = 2)
```

---

### 9. Mundo Real: Como CPUs Modernas São Organizadas 🌍

#### 9.1 Diagrama Simplificado de um Núcleo Moderno

```
┌──────────────────────────────────────────────────────┐
│              NÚCLEO DE CPU MODERNO                     │
│                                                       │
│  ┌─────────────────────────────────────────────────┐ │
│  │                  Front-End                       │ │
│  │  ┌──────┐  ┌────────┐  ┌──────────┐  ┌───────┐│ │
│  │  │ I-TLB│→│ I-Cache │→│ Decodific.│→│ Fila  ││ │
│  │  │      │  │  (L1I)  │  │ (4 inst/ │  │de μOps││ │
│  │  └──────┘  └────────┘  │  ciclo)   │  └───────┘│ │
│  │         ┌──────────┐   └──────────┘            │ │
│  │         │ Preditor │                            │ │
│  │         │ de Desvio│ ← 97%+ de acerto!         │ │
│  │         └──────────┘                            │ │
│  └─────────────────────────────────────────────────┘ │
│                         │                             │
│  ┌─────────────────────────────────────────────────┐ │
│  │               Back-End (Execução)                │ │
│  │                                                   │ │
│  │  ┌──────┐ ┌──────┐ ┌──────┐ ┌──────┐ ┌──────┐ │ │
│  │  │ ALU  │ │ ALU  │ │ ALU  │ │ FPU  │ │ Load │ │ │
│  │  │  1   │ │  2   │ │  3   │ │      │ │/Store│ │ │
│  │  └──────┘ └──────┘ └──────┘ └──────┘ └──────┘ │ │
│  │                                                   │ │
│  │  Execução Fora de Ordem (Out-of-Order)           │ │
│  │  Até 4-6 instruções executando POR CICLO!        │ │
│  └─────────────────────────────────────────────────┘ │
│                         │                             │
│  ┌─────────────────────────────────────────────────┐ │
│  │  ┌──────┐  ┌──────┐  ┌──────────┐             │ │
│  │  │D-TLB │  │D-Cache│  │ Reorder  │             │ │
│  │  │      │  │ (L1D) │  │ Buffer   │             │ │
│  │  └──────┘  └──────┘  │(in-order  │             │ │
│  │                       │ commit)   │             │ │
│  │                       └──────────┘             │ │
│  └─────────────────────────────────────────────────┘ │
└──────────────────────────────────────────────────────┘
```

#### 9.2 Dados de CPUs Reais (2024)

| Processador | Núcleos | Freq. Max | Pipeline | IPC | Transistores |
|------------|---------|-----------|----------|-----|-------------|
| Intel i9-14900K | 24 (8P+16E) | 6,0 GHz | 20+ estágios | ~4 | ~25 bilhões |
| AMD Ryzen 9 7950X | 16 | 5,7 GHz | ~19 estágios | ~4 | ~13 bilhões |
| Apple M3 Max | 16 (12P+4E) | 4,1 GHz | ~16 estágios | ~5 | ~92 bilhões |
| ARM Cortex-A78 | Variável | 3,0 GHz | ~13 estágios | ~3 | — |

---

### 10. Resumo da Aula 📝

```
┌──────────────────────────────────────────────────────────┐
│     RESUMO - ESTRUTURA DA CPU E CICLO DE INSTRUÇÃO       │
├──────────────────────────────────────────────────────────┤
│                                                          │
│  Componentes da CPU:                                     │
│  • ALU: operações aritméticas e lógicas                  │
│  • UC: decodificação e sinais de controle                │
│  • Registradores: armazenamento ultrarrápido             │
│  • Barramento interno: interconexão                      │
│                                                          │
│  Registradores Especiais:                                │
│  • PC: endereço da próxima instrução                    │
│  • IR: instrução atual                                   │
│  • SP: topo da pilha                                     │
│  • PSW/FLAGS: resultado da ALU e estado do processador   │
│  • MAR/MBR: interface com a memória                      │
│                                                          │
│  Ciclo de Instrução:                                     │
│  FETCH → DECODE → EXECUTE → STORE                        │
│  (buscar)  (decodif) (executar)  (armazenar)            │
│                                                          │
│  Formato: OPCODE + OPERANDOS                             │
│  (0, 1, 2 ou 3 operandos)                               │
│                                                          │
│  T = N_instr × CPI × T_ciclo                            │
│                                                          │
│  Evolução: Pipeline → Superscalar → Out-of-Order        │
└──────────────────────────────────────────────────────────┘
```

---

## 🔗 Referências

1. STALLINGS, W. **Arquitetura e Organização de Computadores**. 10ª ed. Pearson, 2017. Capítulos 12 e 14.
2. TANENBAUM, A. S. **Organização Estruturada de Computadores**. 6ª ed. Pearson, 2013. Capítulo 4.
3. PATTERSON, D.; HENNESSY, J. **Organização e Projeto de Computadores**. 5ª ed. Elsevier, 2017. Capítulo 4.
4. HENNESSY, J.; PATTERSON, D. **Arquitetura de Computadores: Uma Abordagem Quantitativa**. 6ª ed. Elsevier, 2019.

---

> 💡 **Dica de estudo:** Para dominar o ciclo de instrução, trace manualmente as micro-operações de diferentes instruções (ADD, LOAD, STORE, JUMP). Para cada ciclo de clock, identifique quais registradores são lidos/escritos e quais sinais de controle estão ativos.
