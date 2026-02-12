# 🔧 Aula 12 — Estrutura Básica do Computador: Busca e Execução de Instruções, Interrupções, Barramentos

> **Disciplina:** Organização de Computadores  
> **Duração:** 2 horas-aula  
> **Nível:** Intermediário  
> **Pré-requisitos:** Aula 11 — Conceitos Básicos de Arquitetura e Organização

---

## 🎯 Objetivos de Aprendizado

Ao final desta aula, o estudante será capaz de:

- ✅ Descrever a **estrutura detalhada** da máquina de Von Neumann
- ✅ Identificar e explicar os **registradores fundamentais**: PC, IR, MAR, MBR
- ✅ Detalhar o **ciclo busca-decodificação-execução** com seus estados
- ✅ Classificar os **tipos de instruções** (transferência, aritmética, lógica, controle)
- ✅ Explicar o conceito de **interrupções** e seus tipos
- ✅ Descrever o **ciclo de tratamento de interrupções**
- ✅ Compreender **prioridade** e **mascaramento** de interrupções
- ✅ Descrever a **arquitetura de barramentos** (dados, endereços, controle)
- ✅ Explicar como a **largura do barramento** impacta o desempenho
- ✅ Relacionar os padrões de barramento modernos (PCI, PCIe, USB)

---

## 📋 Sumário

1. [Estrutura Detalhada da Máquina de Von Neumann](#1--estrutura-detalhada-da-máquina-de-von-neumann)
2. [Registradores Fundamentais](#2--registradores-fundamentais)
3. [Ciclo Busca-Decodificação-Execução em Detalhe](#3--ciclo-busca-decodificação-execução-em-detalhe)
4. [Estados do Ciclo de Instrução](#4--estados-do-ciclo-de-instrução)
5. [Tipos de Instruções](#5--tipos-de-instruções)
6. [Interrupções: Conceito e Tipos](#6--interrupções-conceito-e-tipos)
7. [Ciclo de Tratamento de Interrupções](#7--ciclo-de-tratamento-de-interrupções)
8. [Prioridade e Mascaramento de Interrupções](#8--prioridade-e-mascaramento-de-interrupções)
9. [Múltiplas Interrupções](#9--múltiplas-interrupções)
10. [Arquitetura de Barramentos](#10--arquitetura-de-barramentos)
11. [Largura do Barramento e Desempenho](#11--largura-do-barramento-e-desempenho)
12. [Arbitração de Barramento](#12--arbitração-de-barramento)
13. [Padrões de Barramento](#13--padrões-de-barramento)
14. [Como Tudo Funciona Junto](#14--como-tudo-funciona-junto)
15. [Resumo](#15--resumo)
16. [Leitura Complementar](#16--leitura-complementar)

---

## 1. 🏗️ Estrutura Detalhada da Máquina de Von Neumann

### 1.1 Visão Geral dos Componentes

```
    ┌──────────────────────────────────────────────────────────────────┐
    │                          CPU                                    │
    │  ┌──────────────────────────────────────────────────────────┐   │
    │  │              UNIDADE DE CONTROLE (UC)                    │   │
    │  │  ┌────┐  ┌────┐  ┌──────────┐  ┌──────────────────┐    │   │
    │  │  │ PC │  │ IR │  │ Decodif. │  │ Gerador de       │    │   │
    │  │  │    │  │    │  │ de Instr.│  │ Sinais de Controle│    │   │
    │  │  └────┘  └────┘  └──────────┘  └──────────────────┘    │   │
    │  └──────────────────────────────────────────────────────────┘   │
    │  ┌──────────────────────────────────────────────────────────┐   │
    │  │              UNIDADE LÓGICA E ARITMÉTICA (ULA)           │   │
    │  │  ┌──────────┐  ┌────────────────┐  ┌──────────┐        │   │
    │  │  │Registrad.│  │  Circuitos de  │  │  Flags   │        │   │
    │  │  │ de uso   │  │  operação      │  │ (status) │        │   │
    │  │  │ geral    │  │  (+,-,AND,OR)  │  │          │        │   │
    │  │  └──────────┘  └────────────────┘  └──────────┘        │   │
    │  └──────────────────────────────────────────────────────────┘   │
    │  ┌──────────────────────────────────────────────────────────┐   │
    │  │              INTERFACE COM BARRAMENTO                    │   │
    │  │  ┌─────┐  ┌─────┐                                      │   │
    │  │  │ MAR │  │ MBR │                                      │   │
    │  │  └─────┘  └─────┘                                      │   │
    │  └──────────────────────────────────────────────────────────┘   │
    └──────────────────────────┬──────────────────────────────────────┘
                               │
    ═══════════════════════════╪══════════════════════════════════════
    Barramento de endereços ◄──┤
    Barramento de dados     ◄──┤
    Barramento de controle  ◄──┘
    ═══════════════════════════╪══════════════════════════════════════
                               │
    ┌──────────────────────────┼───────────────────────────────┐
    │        MEMÓRIA PRINCIPAL │                               │
    │  ┌─────────────────────────────────────────────────┐     │
    │  │ Endereço 0:   [instrução ou dado]               │     │
    │  │ Endereço 1:   [instrução ou dado]               │     │
    │  │ Endereço 2:   [instrução ou dado]               │     │
    │  │ ...                                             │     │
    │  │ Endereço N:   [instrução ou dado]               │     │
    │  └─────────────────────────────────────────────────┘     │
    └──────────────────────────────────────────────────────────┘
```

---

## 2. 📋 Registradores Fundamentais

### 2.1 Registradores de Uso Especial

| Registrador | Nome Completo | Função |
|-------------|---------------|--------|
| **PC** | Program Counter (Contador de Programa) | Armazena o endereço da **próxima** instrução a ser buscada |
| **IR** | Instruction Register (Registrador de Instrução) | Armazena a instrução **atualmente** sendo executada |
| **MAR** | Memory Address Register (Reg. de Endereço de Memória) | Armazena o endereço de memória a ser **acessado** |
| **MBR** | Memory Buffer Register (Reg. de Buffer de Memória) | Armazena o dado sendo **lido ou escrito** na memória |

> 💡 Também chamado de **MDR** (Memory Data Register) em algumas referências.

### 2.2 Fluxo de Dados entre Registradores

```
    BUSCA DE INSTRUÇÃO:

    PC ──────► MAR ──────► Memória
                           │
                           ▼
              IR  ◄─────── MBR

    1. PC envia endereço para MAR
    2. MAR coloca endereço no barramento de endereços
    3. Memória retorna o conteúdo para MBR
    4. MBR copia conteúdo para IR
    5. PC é incrementado (PC ← PC + 1)
```

```
    BUSCA DE DADO:

    IR (campo endereço) ──► MAR ──► Memória
                                    │
                                    ▼
    Registrador ◄──────────── MBR

    1. Endereço do operando (de IR) vai para MAR
    2. MAR acessa a memória
    3. Dado retorna pelo MBR
    4. Dado vai para o registrador de destino
```

### 2.3 Outros Registradores Importantes

| Registrador | Função |
|-------------|--------|
| **SP** (Stack Pointer) | Aponta para o topo da pilha |
| **PSW** (Program Status Word) | Flags de status (zero, carry, overflow, sinal) |
| **R0–Rn** (Registradores gerais) | Armazenamento temporário para operandos |

### 2.4 Flags de Status (PSW)

```
    ┌───┬───┬───┬───┬───┬───┬───┬───┐
    │ Z │ N │ C │ V │ I │ . │ . │ . │
    └───┴───┴───┴───┴───┴───┴───┴───┘
      │   │   │   │   │
      │   │   │   │   └── I: Interrupt enable (habilitação de interrupção)
      │   │   │   └────── V: Overflow (transbordamento)
      │   │   └────────── C: Carry (vai-um)
      │   └────────────── N: Negative (resultado negativo)
      └────────────────── Z: Zero (resultado zero)
```

---

## 3. 🔄 Ciclo Busca-Decodificação-Execução em Detalhe

### 3.1 Fase de Busca (Fetch)

```
    PASSOS DA FASE DE BUSCA:
    ─────────────────────────
    1. MAR ← PC              (endereço da instrução vai para MAR)
    2. Leitura da memória     (sinal de controle READ)
    3. MBR ← Memória[MAR]    (instrução vem da memória para MBR)
    4. IR ← MBR              (instrução vai para o registrador de instrução)
    5. PC ← PC + 1           (incrementa o contador de programa)
```

**Em notação de transferência de registradores:**

```
    t₁: MAR ← PC
    t₂: MBR ← Memória[MAR]; PC ← PC + 1
    t₃: IR ← MBR
```

> 💡 Note que no passo t₂, duas operações ocorrem **simultaneamente** (incremento do PC e leitura da memória). Isso é possível porque usam caminhos de dados diferentes.

### 3.2 Fase de Decodificação (Decode)

```
    PASSOS DA FASE DE DECODIFICAÇÃO:
    ─────────────────────────────────
    1. A UC extrai o OPCODE de IR
    2. O decodificador identifica a operação
    3. A UC extrai os campos de operandos
    4. A UC gera os sinais de controle apropriados
```

**Formato típico de instrução:**

```
    ┌──────────┬──────────┬──────────┬──────────┐
    │  OPCODE  │   Reg.   │   Reg.   │ Reg/Imed │
    │ (código  │  Destino │  Fonte 1 │ Fonte 2  │
    │  da op.) │          │          │          │
    └──────────┴──────────┴──────────┴──────────┘
    Bits: 6        5          5          16
    
    Exemplo: ADD R1, R2, R3
    ┌────────┬────┬────┬────┐
    │ 000010 │ 001│ 010│ 011│  → ADD R1 ← R2 + R3
    └────────┴────┴────┴────┘
```

### 3.3 Fase de Execução (Execute)

A execução varia conforme o tipo de instrução:

**Para ADD R1, R2, R3:**

```
    t₄: A ← R2              (operando 1 para entrada A da ULA)
    t₅: B ← R3              (operando 2 para entrada B da ULA)
    t₆: R1 ← A + B          (ULA soma, resultado vai para R1)
        PSW atualizado       (flags Z, N, C, V atualizados)
```

**Para LOAD R1, [endereço]:**

```
    t₄: MAR ← IR(endereço)  (campo de endereço de IR vai para MAR)
    t₅: MBR ← Memória[MAR]  (dado lido da memória)
    t₆: R1 ← MBR            (dado vai para o registrador)
```

**Para STORE [endereço], R1:**

```
    t₄: MAR ← IR(endereço)  (endereço de destino)
    t₅: MBR ← R1            (dado do registrador para MBR)
    t₆: Memória[MAR] ← MBR  (escrita na memória, sinal WRITE)
```

---

## 4. 📊 Estados do Ciclo de Instrução

### 4.1 Diagrama de Estados Completo

```
    ┌──────────────────────────────────────────────────────────────┐
    │                                                              │
    │   ┌─────────────┐                                           │
    │   │   BUSCA DE  │   Fetch instruction                       │
    │   │  INSTRUÇÃO  │                                           │
    │   └──────┬──────┘                                           │
    │          │                                                  │
    │          ▼                                                  │
    │   ┌─────────────┐                                           │
    │   │DECODIFICAÇÃO│   Determine operation                     │
    │   │ DE INSTRUÇÃO│                                           │
    │   └──────┬──────┘                                           │
    │          │                                                  │
    │          ▼                                                  │
    │   ┌─────────────┐                                           │
    │   │  CÁLCULO DE │   Calculate operand address               │
    │   │  ENDEREÇO   │   (se o operando está na memória)         │
    │   └──────┬──────┘                                           │
    │          │                                                  │
    │          ▼                                                  │
    │   ┌─────────────┐                                           │
    │   │  BUSCA DE   │   Fetch operand from memory               │
    │   │  OPERANDO   │                                           │
    │   └──────┬──────┘                                           │
    │          │                                                  │
    │          ▼                                                  │
    │   ┌─────────────┐                                           │
    │   │  EXECUÇÃO   │   Execute operation                       │
    │   │  DA OPERAÇÃO│                                           │
    │   └──────┬──────┘                                           │
    │          │                                                  │
    │          ▼                                                  │
    │   ┌─────────────┐                                           │
    │   │ARMAZENAMENTO│   Store result                            │
    │   │ DO RESULTADO│   (se necessário)                         │
    │   └──────┬──────┘                                           │
    │          │                                                  │
    │          ▼                                                  │
    │   ┌─────────────┐                                           │
    │   │  VERIFICAR  │   Check for interrupts                    │
    │   │ INTERRUPÇÃO │──── Se houver → tratar interrupção        │
    │   └──────┬──────┘                                           │
    │          │ (se não houver)                                   │
    │          └──────────────────────────────────────────────┘    │
    └─────────────────────────────────────────────────────────────┘
```

> 💡 Nem todas as instruções passam por todos os estados. Uma instrução como `NOP` (No Operation) só passa por busca e decodificação.

---

## 5. 📝 Tipos de Instruções

### 5.1 Classificação

| Tipo | Descrição | Exemplos |
|------|-----------|----------|
| **Transferência de dados** | Move dados entre registradores e memória | LOAD, STORE, MOV, PUSH, POP |
| **Aritmética** | Operações matemáticas | ADD, SUB, MUL, DIV, INC, DEC |
| **Lógica** | Operações bit a bit | AND, OR, XOR, NOT, SHIFT |
| **Controle de fluxo** | Altera a sequência de execução | JMP, JZ, JNZ, CALL, RET |
| **E/S** | Comunicação com dispositivos | IN, OUT |
| **Controle do sistema** | Operações privilegiadas | HALT, INT, CLI, STI |

### 5.2 Formato de Instruções

```
    INSTRUÇÃO DE 3 OPERANDOS:
    ADD R1, R2, R3          → R1 ← R2 + R3

    INSTRUÇÃO DE 2 OPERANDOS:
    ADD R1, R2              → R1 ← R1 + R2

    INSTRUÇÃO DE 1 OPERANDO:
    INC R1                  → R1 ← R1 + 1

    INSTRUÇÃO SEM OPERANDO:
    NOP                     → (nenhuma operação)
    HALT                    → (para o processador)
```

### 5.3 Exemplos de Execução

| Instrução | Significado | Efeito |
|-----------|-------------|--------|
| `LOAD R1, [500]` | Carrega dado do endereço 500 para R1 | R1 ← Memória[500] |
| `STORE [600], R2` | Armazena R2 no endereço 600 | Memória[600] ← R2 |
| `ADD R3, R1, R2` | Soma R1 e R2, resultado em R3 | R3 ← R1 + R2 |
| `SUB R3, R1, R2` | Subtrai R2 de R1, resultado em R3 | R3 ← R1 - R2 |
| `AND R1, R2, R3` | AND bit a bit | R1 ← R2 AND R3 |
| `JMP 200` | Salta para endereço 200 | PC ← 200 |
| `JZ 300` | Salta se flag Zero = 1 | Se Z=1: PC ← 300 |

---

## 6. ⚡ Interrupções: Conceito e Tipos

### 6.1 O que é uma Interrupção?

Uma **interrupção** é um mecanismo que **suspende temporariamente** a execução normal do programa para atender um evento que requer atenção imediata.

```
    Execução normal:
    ─────────────────────────────────────────────────►
    Instr.1  Instr.2  Instr.3  Instr.4  Instr.5

    Com interrupção:
    ─────────────────┐                 ┌─────────────►
    Instr.1  Instr.2 │  Tratar        │ Instr.3  ...
                     │  Interrupção   │
                     └─────────────────┘
```

### 6.2 Por que Interrupções São Necessárias?

**Sem interrupções (polling):**

```
    CPU: "Teclado, tem tecla?"  → Não
    CPU: "Teclado, tem tecla?"  → Não
    CPU: "Teclado, tem tecla?"  → Não    ← DESPERDÍCIO!
    CPU: "Teclado, tem tecla?"  → Sim! → Processa
```

**Com interrupções:**

```
    CPU: executa programa normalmente...
    Teclado: "Ei, CPU! Tem tecla!" ← INTERRUPÇÃO
    CPU: para, trata tecla, volta ao programa
```

> 💡 Interrupções permitem que a CPU trabalhe de forma **eficiente**, sem ficar verificando dispositivos constantemente.

### 6.3 Tipos de Interrupções

| Tipo | Origem | Exemplos |
|------|--------|----------|
| **Hardware (externa)** | Dispositivos de E/S | Teclado, mouse, disco, rede, timer |
| **Software (trap)** | Instrução do programa | Chamada de sistema (syscall), breakpoint |
| **Exceção** | Erro durante execução | Divisão por zero, overflow, falha de página |

### 6.4 Detalhamento

```
    ┌─────────────────────────────────────────────┐
    │           TIPOS DE INTERRUPÇÃO              │
    ├─────────────────────────────────────────────┤
    │                                             │
    │  HARDWARE (Assíncronas)                     │
    │  ├── Mascaráveis (podem ser ignoradas)      │
    │  │   ├── Teclado pressionado               │
    │  │   ├── Dado chegou pela rede              │
    │  │   └── Timer expirou                      │
    │  └── Não-mascaráveis (NMI)                  │
    │      ├── Falha de memória                   │
    │      └── Falha de hardware crítica          │
    │                                             │
    │  SOFTWARE (Síncronas)                       │
    │  ├── Chamadas de sistema (syscall)          │
    │  │   ├── Abrir arquivo                      │
    │  │   └── Alocar memória                     │
    │  └── Instrução INT n                        │
    │                                             │
    │  EXCEÇÕES (Síncronas)                       │
    │  ├── Faults (recuperáveis)                  │
    │  │   ├── Page fault (falha de página)       │
    │  │   └── Segmentation fault                 │
    │  ├── Traps (programadas)                    │
    │  │   └── Breakpoint para debug              │
    │  └── Aborts (fatais)                        │
    │      └── Erro de hardware irrecuperável     │
    │                                             │
    └─────────────────────────────────────────────┘
```

---

## 7. 🔄 Ciclo de Tratamento de Interrupções

### 7.1 Passos do Tratamento

```
    CICLO DE TRATAMENTO DE INTERRUPÇÃO:
    ─────────────────────────────────────

    1. CPU completa a instrução atual

    2. CPU reconhece a interrupção
       └─ Verifica se a interrupção está habilitada

    3. SALVAR CONTEXTO
       ├─ PC (endereço de retorno) → pilha
       ├─ PSW (flags) → pilha
       └─ Registradores → pilha (se necessário)

    4. IDENTIFICAR a fonte da interrupção
       └─ Ler vetor de interrupção → endereço da ISR

    5. CARREGAR endereço da ISR (Interrupt Service Routine)
       └─ PC ← endereço da rotina de tratamento

    6. EXECUTAR a rotina de tratamento (ISR)

    7. RESTAURAR CONTEXTO
       ├─ Registradores ← pilha
       ├─ PSW ← pilha
       └─ PC ← pilha (endereço de retorno)

    8. RETORNAR à execução normal do programa
```

### 7.2 Diagrama Visual

```
    Programa principal:
    ═══════════════════════════╗          ╔═══════════════════
    Instr. N                  ║          ║ Instr. N+1
    ──────────────────────────╢          ╟──────────────────
                              ║          ║
                     Interrupção!   Retorno (IRET)
                              ║          ║
                              ╚═══╗  ╔═══╝
                                  ║  ║
    ISR (Interrupt Service     ┌──╨──╨──┐
    Routine):                  │ Salvar │
                               │contexto│
                               ├────────┤
                               │ Tratar │
                               │ evento │
                               ├────────┤
                               │Restaur.│
                               │contexto│
                               └────────┘
```

### 7.3 Vetor de Interrupções

A CPU usa uma **tabela de vetores** para saber qual rotina chamar para cada tipo de interrupção:

```
    TABELA DE VETORES DE INTERRUPÇÃO:
    ┌──────────┬───────────────────────────┐
    │  Vetor   │  Endereço da ISR          │
    ├──────────┼───────────────────────────┤
    │    0     │  0x0000 → Divisão por zero│
    │    1     │  0x0100 → Debug           │
    │    2     │  0x0200 → NMI             │
    │    3     │  0x0300 → Breakpoint      │
    │    ...   │  ...                      │
    │   32     │  0x2000 → Timer           │
    │   33     │  0x2100 → Teclado         │
    │   34     │  0x2200 → Disco           │
    │   ...    │  ...                      │
    └──────────┴───────────────────────────┘
```

---

## 8. 🔒 Prioridade e Mascaramento de Interrupções

### 8.1 Prioridade de Interrupções

Quando múltiplas interrupções ocorrem simultaneamente, a CPU atende a de **maior prioridade** primeiro:

```
    PRIORIDADE (exemplo típico):
    ─────────────────────────────
    Alta  │  Falha de hardware (NMI)
          │  Falha de memória
          │  Timer do sistema
          │  Disco / rede
          │  Teclado / mouse
    Baixa │  Impressora
```

### 8.2 Mascaramento

**Interrupções mascaráveis** podem ser temporariamente **desabilitadas**:

```
    CLI  → Clear Interrupt flag (desabilita interrupções mascaráveis)
    STI  → Set Interrupt flag (habilita interrupções mascaráveis)

    Exemplo:
    ─────────
    CLI              ← Desabilita interrupções
    MOV R1, [dados]  ← Seção CRÍTICA
    ADD R1, R2       ← (não pode ser interrompida)
    STORE [dados],R1 ←
    STI              ← Reabilita interrupções
```

> ⚠️ **Interrupções não-mascaráveis (NMI)** NUNCA podem ser desabilitadas. São reservadas para situações críticas (falha de hardware).

### 8.3 Registro de Máscara de Interrupção

```
    Registrador de Máscara:
    ┌───┬───┬───┬───┬───┬───┬───┬───┐
    │ 1 │ 1 │ 0 │ 1 │ 0 │ 1 │ 1 │ 0 │
    └───┴───┴───┴───┴───┴───┴───┴───┘
      │   │   │   │   │   │   │   │
      │   │   │   │   │   │   │   └── IRQ0: Desabilitado
      │   │   │   │   │   │   └────── IRQ1: Habilitado
      │   │   │   │   │   └────────── IRQ2: Habilitado
      │   │   │   │   └────────────── IRQ3: Desabilitado
      │   │   │   └────────────────── IRQ4: Habilitado
      │   │   └────────────────────── IRQ5: Desabilitado
      │   └────────────────────────── IRQ6: Habilitado
      └────────────────────────────── IRQ7: Habilitado

    Bit = 1 → interrupção habilitada
    Bit = 0 → interrupção mascarada (ignorada)
```

---

## 9. 🔀 Múltiplas Interrupções

### 9.1 Abordagem 1: Desabilitar Interrupções Durante o Tratamento

```
    Programa ───┐
                │ INT₁
                ▼
    ISR₁ ──────────────────┐
    (interrupções          │ (INT₂ fica pendente)
     desabilitadas)        │
                           ▼
    Programa ───────────────┐
                            │ INT₂ (agora atendida)
                            ▼
    ISR₂ ──────────────────
```

**Vantagem:** Simples de implementar  
**Desvantagem:** Interrupções de alta prioridade podem ser atrasadas

### 9.2 Abordagem 2: Interrupções Aninhadas (Nested Interrupts)

Uma interrupção de **maior prioridade** pode interromper a ISR de uma de **menor prioridade**:

```
    Programa ──────┐
                   │ INT₁ (prioridade baixa)
                   ▼
    ISR₁ ─────┐
              │ INT₂ (prioridade ALTA — interrompe ISR₁!)
              ▼
    ISR₂ ─────────┐
                   │ (ISR₂ termina)
                   ▼
    ISR₁ (continua)──┐
                      │ (ISR₁ termina)
                      ▼
    Programa (continua)──────────
```

> 💡 As interrupções aninhadas usam a **pilha** para empilhar múltiplos contextos. Cada nível de interrupção adiciona um frame na pilha.

---

## 10. 🚌 Arquitetura de Barramentos

### 10.1 Os Três Barramentos

```
    ┌──────────┐                              ┌──────────┐
    │   CPU    │                              │ Memória  │
    │          │    Barramento de Endereços    │          │
    │   MAR ═══╪══════════════════════════════╪═══       │
    │          │         (unidirecional)       │          │
    │          │                              │          │
    │   MBR ═══╪══════════════════════════════╪═══       │
    │          │    Barramento de Dados        │          │
    │          │         (bidirecional)        │          │
    │          │                              │          │
    │    UC ═══╪══════════════════════════════╪═══       │
    │          │    Barramento de Controle     │          │
    │          │     (READ, WRITE, etc.)       │          │
    └──────────┘                              └──────────┘
```

### 10.2 Descrição de Cada Barramento

| Barramento | Direção | Função | Largura típica |
|-----------|---------|--------|----------------|
| **Endereços** | CPU → Memória/E/S | Especifica **onde** ler/escrever | 32 ou 64 bits |
| **Dados** | CPU ↔ Memória/E/S | Transporta o **conteúdo** (dados/instruções) | 32 ou 64 bits |
| **Controle** | CPU ↔ Memória/E/S | Sinaliza **o que fazer** (ler, escrever, interrupção) | ~10–20 linhas |

### 10.3 Sinais do Barramento de Controle

| Sinal | Direção | Significado |
|-------|---------|-------------|
| **READ** | CPU → Memória | Solicita leitura |
| **WRITE** | CPU → Memória | Solicita escrita |
| **IRQ** | Dispositivo → CPU | Requisição de interrupção |
| **INTA** | CPU → Dispositivo | Reconhecimento de interrupção |
| **BUSREQ** | Dispositivo → CPU | Solicita o barramento |
| **BUSGNT** | CPU → Dispositivo | Concede o barramento |
| **CLOCK** | Gerador → Todos | Sinal de sincronização |
| **RESET** | Sistema → Todos | Reinicialização |

### 10.4 Hierarquia de Barramentos

```
    ┌──────────┐     Barramento do Processador     ┌──────────┐
    │   CPU    │◄══════════════════════════════════►│  Cache   │
    └──────────┘          (mais rápido)             └────┬─────┘
                                                         │
                          Barramento do Sistema          │
    ┌──────────┐◄════════════════════════════════════════╪════►┌──────────┐
    │  Memória │                                         │     │  Bridge  │
    └──────────┘                                         │     └────┬─────┘
                                                         │          │
                          Barramento de E/S              │          │
    ┌──────────┐◄════════════════════════════════════════╪══════════╪═══►
    │  Disco   │         (mais lento)                    │          │
    └──────────┘                                    ┌────┴──┐  ┌───┴────┐
                                                    │ USB   │  │ Rede   │
                                                    └───────┘  └────────┘
```

---

## 11. 📏 Largura do Barramento e Desempenho

### 11.1 Barramento de Endereços

A largura do barramento de endereços determina a **quantidade máxima de memória endereçável**:

```
    Memória máxima = 2^(largura do barramento de endereços)

    Exemplos:
    ┌──────────┬──────────────────────┐
    │ Largura  │ Memória endereçável  │
    ├──────────┼──────────────────────┤
    │ 16 bits  │ 2¹⁶ = 64 KB         │
    │ 20 bits  │ 2²⁰ = 1 MB          │
    │ 32 bits  │ 2³² = 4 GB          │
    │ 36 bits  │ 2³⁶ = 64 GB         │
    │ 48 bits  │ 2⁴⁸ = 256 TB        │
    │ 64 bits  │ 2⁶⁴ = 16 EB (exabytes)│
    └──────────┴──────────────────────┘
```

### 11.2 Barramento de Dados

A largura do barramento de dados determina **quantos bits são transferidos por ciclo**:

```
    Taxa de transferência = largura × frequência

    Exemplo:
    Barramento de 64 bits a 1 GHz:
    Taxa = 64 bits × 1×10⁹ Hz = 64×10⁹ bits/s = 8 GB/s
```

### 11.3 Impacto no Desempenho

| Barramento | Mais largo → | Impacto |
|-----------|-------------|---------|
| **Endereços** | Mais memória endereçável | Sistemas com mais RAM |
| **Dados** | Mais dados por transferência | Maior throughput |
| **Controle** | Mais sinais | Mais funcionalidades |

---

## 12. ⚖️ Arbitração de Barramento

### 12.1 O Problema

Quando múltiplos dispositivos querem usar o barramento **ao mesmo tempo**, é necessário um mecanismo de arbitração para decidir quem tem acesso.

### 12.2 Métodos de Arbitração

| Método | Descrição | Prós/Contras |
|--------|-----------|-------------|
| **Centralizada (Daisy Chain)** | Um árbitro central decide. Prioridade fixa pela posição física. | Simples; dispositivos distantes podem sofrer starvation |
| **Distribuída** | Cada dispositivo decide localmente baseado em protocolo | Mais justo; mais complexo |
| **Por polling** | Árbitro pergunta a cada dispositivo sequencialmente | Justo; mais lento |

```
    DAISY CHAIN (encadeamento):

    Árbitro → Dispositivo 1 → Dispositivo 2 → Dispositivo 3
    (maior                                     (menor
    prioridade)                                prioridade)

    O sinal de concessão (grant) passa por cada dispositivo.
    O primeiro que precisa do barramento "captura" o sinal.
```

---

## 13. 🔌 Padrões de Barramento

### 13.1 Evolução dos Padrões

| Padrão | Ano | Largura | Taxa máxima | Uso |
|--------|-----|---------|-------------|-----|
| **ISA** | 1981 | 16 bits | 8 MB/s | PCs antigos |
| **PCI** | 1992 | 32/64 bits | 533 MB/s | Placas de expansão |
| **AGP** | 1997 | 32 bits | 2.1 GB/s | Placas de vídeo |
| **PCI Express 1.0** | 2003 | Serial | 250 MB/s/lane | Moderno |
| **PCI Express 3.0** | 2010 | Serial | 985 MB/s/lane | Moderno |
| **PCI Express 4.0** | 2017 | Serial | 1.97 GB/s/lane | Atual |
| **PCI Express 5.0** | 2019 | Serial | 3.94 GB/s/lane | Atual |

### 13.2 PCI Express (PCIe)

O PCIe é o padrão atual para conexão de dispositivos de alto desempenho:

```
    Configurações PCIe:
    ┌─────────┬────────┬───────────────────────┐
    │ Config. │ Lanes  │ Taxa (PCIe 4.0)       │
    ├─────────┼────────┼───────────────────────┤
    │  x1     │   1    │  1.97 GB/s            │
    │  x4     │   4    │  7.88 GB/s (SSD NVMe) │
    │  x8     │   8    │  15.75 GB/s           │
    │  x16    │  16    │  31.51 GB/s (GPU)     │
    └─────────┴────────┴───────────────────────┘
```

### 13.3 USB (Universal Serial Bus)

| Versão | Ano | Taxa máxima | Nome comercial |
|--------|-----|-------------|---------------|
| USB 1.1 | 1998 | 12 Mbps | Full Speed |
| USB 2.0 | 2000 | 480 Mbps | Hi-Speed |
| USB 3.0 | 2008 | 5 Gbps | SuperSpeed |
| USB 3.1 | 2013 | 10 Gbps | SuperSpeed+ |
| USB 3.2 | 2017 | 20 Gbps | SuperSpeed+ |
| USB4 | 2019 | 40 Gbps | USB4 |

> 💡 Note a evolução: USB começou com 12 Mbps e hoje atinge 40 Gbps — um aumento de mais de 3.000×!

---

## 14. 🔗 Como Tudo Funciona Junto

### 14.1 Exemplo: Executando `x = a + b`

Supondo que `a` está no endereço 1000, `b` no endereço 1001, e `x` no endereço 1002:

```
    Programa na memória:
    ┌─────────┬────────────────────────┐
    │ End.100 │ LOAD R1, [1000]        │
    │ End.101 │ LOAD R2, [1001]        │
    │ End.102 │ ADD R3, R1, R2         │
    │ End.103 │ STORE [1002], R3       │
    └─────────┴────────────────────────┘
    
    Dados na memória:
    ┌─────────┬──────┐
    │End.1000 │  5   │  (a = 5)
    │End.1001 │  3   │  (b = 3)
    │End.1002 │  ?   │  (x = resultado)
    └─────────┴──────┘
```

**Execução detalhada:**

```
    ═══════════════════════════════════════════════════════
    INSTRUÇÃO 1: LOAD R1, [1000]
    ═══════════════════════════════════════════════════════
    BUSCA:
      MAR ← PC (=100)
      Barramento endereços: 100
      Barramento controle: READ
      Barramento dados: "LOAD R1, [1000]" → MBR → IR
      PC ← 101

    DECODIFICAÇÃO:
      UC: opcode = LOAD, destino = R1, endereço = 1000

    EXECUÇÃO:
      MAR ← 1000
      Barramento endereços: 1000
      Barramento controle: READ
      Barramento dados: 5 → MBR → R1
      
      Estado: R1 = 5, PC = 101

    ═══════════════════════════════════════════════════════
    INSTRUÇÃO 2: LOAD R2, [1001]
    ═══════════════════════════════════════════════════════
    (similar à anterior)
    Estado: R1 = 5, R2 = 3, PC = 102

    ═══════════════════════════════════════════════════════
    INSTRUÇÃO 3: ADD R3, R1, R2
    ═══════════════════════════════════════════════════════
    BUSCA: IR ← "ADD R3, R1, R2", PC ← 103

    DECODIFICAÇÃO:
      UC: opcode = ADD, destino = R3, fonte1 = R1, fonte2 = R2

    EXECUÇÃO:
      ULA recebe: A = R1 (5), B = R2 (3)
      ULA calcula: 5 + 3 = 8
      R3 ← 8
      PSW: Z=0 (não zero), N=0 (positivo), C=0, V=0

      Estado: R1 = 5, R2 = 3, R3 = 8, PC = 103

    ═══════════════════════════════════════════════════════
    INSTRUÇÃO 4: STORE [1002], R3
    ═══════════════════════════════════════════════════════
    BUSCA: IR ← "STORE [1002], R3", PC ← 104

    DECODIFICAÇÃO:
      UC: opcode = STORE, endereço = 1002, fonte = R3

    EXECUÇÃO:
      MAR ← 1002
      MBR ← R3 (= 8)
      Barramento endereços: 1002
      Barramento dados: 8
      Barramento controle: WRITE
      Memória[1002] ← 8

      Estado final: Memória[1002] = 8  (x = 5 + 3 = 8 ✓)
```

### 14.2 Se uma Interrupção Ocorrer...

Supondo que entre as instruções 2 e 3, o teclado gera uma interrupção:

```
    LOAD R1, [1000]    ← executada
    LOAD R2, [1001]    ← executada
    ──── INTERRUPÇÃO DO TECLADO! ────
         1. Salvar: PC(=102), PSW, R1, R2 → Pilha
         2. PC ← endereço da ISR do teclado (ex: 0x2100)
         3. Executar ISR (ler tecla, processar)
         4. Restaurar: R2, R1, PSW, PC ← Pilha
    ──── RETORNO ────
    ADD R3, R1, R2     ← continua normalmente
    STORE [1002], R3   ← executada
```

> 💡 O programa nem "percebe" que foi interrompido! O contexto é salvo e restaurado perfeitamente.

---

## 15. 📌 Resumo

| Conceito | Resumo |
|----------|--------|
| **PC** | Endereço da próxima instrução |
| **IR** | Instrução sendo executada |
| **MAR** | Endereço de memória a acessar |
| **MBR** | Dado sendo lido/escrito |
| **Ciclo de instrução** | Busca → Decodificação → Execução (repetir) |
| **Tipos de instrução** | Transferência, aritmética, lógica, controle, E/S |
| **Interrupção** | Suspensão temporária para atender evento urgente |
| **Tipos de interrupção** | Hardware, software (trap), exceção |
| **Vetor de interrupções** | Tabela com endereços das ISRs |
| **Mascaramento** | Desabilitar interrupções temporariamente |
| **Barramento de endereços** | Onde acessar (unidirecional) |
| **Barramento de dados** | O que transferir (bidirecional) |
| **Barramento de controle** | Como transferir (READ, WRITE, IRQ) |
| **PCIe** | Padrão moderno de barramento serial de alta velocidade |

> 🧠 **Mensagem principal:** O computador é uma máquina que executa um ciclo simples (buscar-decodificar-executar) bilhões de vezes por segundo. Interrupções permitem que ele responda a eventos externos de forma eficiente, e barramentos são as "estradas" que conectam todos os componentes. Entender esses mecanismos é fundamental para compreender como software e hardware trabalham juntos!

---

## 16. 📚 Leitura Complementar

- 📖 STALLINGS, W. **Arquitetura e Organização de Computadores**. Cap. 3 — Visão de Alto Nível da Função do Computador; Cap. 7 — Entrada/Saída.
- 📖 TANENBAUM, A. S. **Organização Estruturada de Computadores**. Cap. 2 — Organização de Sistemas de Computadores.
- 📖 PATTERSON, D.; HENNESSY, J. **Organização e Projeto de Computadores**. Cap. 4 — O Processador.
- 📖 NULL, L.; LOBUR, J. **Princípios Básicos de Arquitetura e Organização de Computadores**. Cap. 4 — A CPU.
- 🌐 [PCI Express Specification](https://pcisig.com/specifications)
- 🌐 [How Computers Work — Video Series](https://www.youtube.com/playlist?list=PLH2l6uzC4UEW0s7-KewFLBC1D0l6XRfye)

---

> ⬅️ [Aula 11 — Conceitos de Arquitetura](../11-conceitos-basicos-arquitetura-organizacao/README.md) | [Exemplos](./exemplos/README.md) | [Exercícios](./exercicios/README.md) ➡️
