# 🔬 Exemplos Práticos - Aula 15

## Sistema de Entrada e Saída (E/S)

---

## Exemplo 1: E/S Programada — Leitura de um Caractere do Teclado 📋

### Pseudocódigo Detalhado

```
CONSTANTES:
  TECLADO_STATUS  = 0xF000    // Endereço do registrador de status
  TECLADO_DADOS   = 0xF001    // Endereço do registrador de dados
  BIT_PRONTO      = 0x01      // Bit 0 = "dado disponível"

PROCEDIMENTO ler_caractere_polling():

  // Passo 1: Verificar se há dado disponível (POLLING)
  repita
      status ← LER_MEMÓRIA(TECLADO_STATUS)
  até que (status AND BIT_PRONTO) == 1    // Busy waiting!

  // Passo 2: Ler o dado
  caractere ← LER_MEMÓRIA(TECLADO_DADOS)

  // Passo 3: Retornar o caractere
  retornar caractere
```

### Análise de Tempo Desperdiçado

```
CPU a 3 GHz = 3.000.000.000 ciclos/segundo
Digitador rápido: 100 palavras/min ≈ 8 caracteres/segundo

Tempo entre teclas: 1/8 = 0,125 segundo = 125 ms
Ciclos desperdiçados por tecla: 3 × 10⁹ × 0,125 = 375.000.000 ciclos!

┌──────────────────────────────────────────────────────┐
│                DESPERDÍCIO POR POLLING                 │
│                                                       │
│  Cada verificação de status: ~10 ciclos               │
│  Verificações até tecla chegar: ~37.500.000           │
│  Ciclos desperdiçados: ~375.000.000 por tecla         │
│                                                       │
│  A CPU poderia executar ~375 MILHÕES de instruções    │
│  no tempo que fica esperando UMA tecla!               │
│                                                       │
│  Eficiência: ~0,000003% (3 partes por milhão!)        │
└──────────────────────────────────────────────────────┘
```

---

## Exemplo 2: E/S por Interrupção — Leitura do Teclado ⚡

### Pseudocódigo com Interrupção

```
// Buffer circular para armazenar teclas
BUFFER_TECLADO: vetor[256] de caractere
PONTEIRO_ESCRITA = 0
PONTEIRO_LEITURA = 0

// ========================================
// ISR (Interrupt Service Routine) - IRQ 1
// Executada automaticamente quando uma tecla é pressionada
// ========================================
PROCEDIMENTO ISR_teclado():
  // 1. CPU automaticamente salva contexto (PC, flags)

  // 2. Ler o caractere do controlador
  caractere ← LER_PORTA(0x60)    // Porta do teclado (x86)

  // 3. Armazenar no buffer circular
  BUFFER_TECLADO[PONTEIRO_ESCRITA] ← caractere
  PONTEIRO_ESCRITA ← (PONTEIRO_ESCRITA + 1) mod 256

  // 4. Enviar EOI (End of Interrupt) ao controlador
  ESCREVER_PORTA(0x20, 0x20)     // Sinal de fim de interrupção

  // 5. CPU automaticamente restaura contexto
  RETORNAR_DA_INTERRUPÇÃO

// ========================================
// Função chamada pelo programa do usuário
// ========================================
PROCEDIMENTO ler_caractere():
  // Espera até ter caractere no buffer
  enquanto PONTEIRO_LEITURA == PONTEIRO_ESCRITA:
      // Pode fazer yield() para outros processos
      // em vez de busy waiting
      YIELD()

  caractere ← BUFFER_TECLADO[PONTEIRO_LEITURA]
  PONTEIRO_LEITURA ← (PONTEIRO_LEITURA + 1) mod 256
  retornar caractere
```

### Fluxo Visual

```
Tempo ──────────────────────────────────────────────────────►

CPU:   [Programa A    ][   Programa B     ][ ISR ][Programa B]
       Calculando...   Navegador web...    Tecla!  Continua...

  Tecla pressionada aqui ─────────────────────┘
  CPU recebe IRQ, salva contexto de B,
  executa ISR (~100 ciclos), volta para B.

  Overhead: ~100 ciclos de ~375.000.000 disponíveis
  Eficiência: ~99,99997% ✅
```

---

## Exemplo 3: DMA — Transferência de Arquivo do Disco para a RAM 🚀

### Cenário: Ler 4 MB do SSD para a RAM

```
Configuração do DMA pela CPU:

// A CPU faz apenas ISSO (poucos ciclos):
ESCREVER(DMA_ENDERECO_MEMORIA, 0x10000000)  // Destino na RAM
ESCREVER(DMA_ENDERECO_DISCO, setor_inicio)  // Origem no disco
ESCREVER(DMA_CONTADOR, 4194304)              // 4 MB = 4.194.304 bytes
ESCREVER(DMA_CONTROLE, LEITURA | INICIAR)   // Comando: ler e iniciar

// Pronto! CPU agora executa outros programas!

═══ O que acontece em paralelo: ═══

CPU:    [configura DMA][         Executa outros programas          ][ISR]
                       │                                            │
DMA:                   [Transfere 4 MB do disco para a RAM........]
                       │     (CPU não participa!)                   │
SSD:                   [Lê setores e envia ao controlador DMA......]
                                                                    │
                                                          Interrupção!
                                                     "Transferência OK"
```

### Cálculo de Tempo Economizado

```
Cenário: Transferir 4 MB do SSD (taxa: 2 GB/s) para RAM

Tempo de transferência = 4 MB / 2 GB/s = 2 ms

Se usasse E/S Programada ou por Interrupção:
  CPU ficaria ocupada durante os 2 ms
  A 3 GHz: 2 ms × 3 × 10⁹ = 6.000.000 ciclos desperdiçados

Com DMA:
  CPU gasta ~100 ciclos para configurar
  CPU gasta ~200 ciclos para ISR final
  Total CPU: ~300 ciclos
  CPU livre: 6.000.000 - 300 = 5.999.700 ciclos para outros programas!

  Eficiência DMA: 99,995% da CPU livre ✅
```

---

## Exemplo 4: Comparação Prática das 3 Técnicas ⚖️

### Cenário: Imprimir um documento de 100 KB

```
Impressora: velocidade = 10 KB/s
Tempo de impressão: 100 KB / 10 KB/s = 10 segundos
CPU: 3 GHz

═══ E/S PROGRAMADA ═══
CPU:  [polling][send byte][polling][send byte]... por 10 segundos
Ciclos desperdiçados: 10 × 3 × 10⁹ = 30.000.000.000 (30 BILHÕES!)
CPU utilizada para E/S: 100%
CPU disponível para outros: 0%  ❌

═══ E/S POR INTERRUPÇÃO ═══
CPU:  [programa][ISR]...[programa][ISR]...[programa][ISR]...
Interrupções: 100 KB / 1 byte = 102.400 interrupções
Overhead por interrupção: ~500 ciclos
Total overhead: 102.400 × 500 = 51.200.000 ciclos
CPU utilizada para E/S: 51.200.000 / 30.000.000.000 = 0,17%
CPU disponível para outros: 99,83%  ✅

═══ DMA ═══
CPU:  [configurar DMA]...[programa livre]...[ISR final]
Overhead DMA: ~300 ciclos (config) + ~200 ciclos (ISR) = 500 ciclos
CPU utilizada para E/S: 500 / 30.000.000.000 = 0,0000017%
CPU disponível para outros: 99,9999983%  ✅✅

┌──────────────────┬──────────────────┬────────────────┐
│ Técnica          │ Ciclos gastos    │ CPU livre      │
├──────────────────┼──────────────────┼────────────────┤
│ E/S Programada   │ 30.000.000.000   │     0%         │
│ E/S Interrupção  │     51.200.000   │    99,83%      │
│ DMA              │            500   │   ~100%        │
└──────────────────┴──────────────────┴────────────────┘
```

---

## Exemplo 5: Vetor de Interrupções 📍

### Tabela de Vetores de Interrupção (x86 típica)

```
┌──────────┬─────────────────────────────────────────┐
│  Vetor   │ Descrição                               │
├──────────┼─────────────────────────────────────────┤
│  IRQ 0   │ Timer do sistema (relógio)               │
│  IRQ 1   │ Teclado                                  │
│  IRQ 2   │ Cascata para segundo PIC (controlador)   │
│  IRQ 3   │ Porta serial COM2                        │
│  IRQ 4   │ Porta serial COM1                        │
│  IRQ 5   │ Porta paralela LPT2 ou placa de som      │
│  IRQ 6   │ Controlador de disquete                   │
│  IRQ 7   │ Porta paralela LPT1                      │
│  IRQ 8   │ Relógio de tempo real (RTC)              │
│  IRQ 9   │ Redirecionado (ACPI)                     │
│  IRQ 10  │ Disponível (placa de rede, etc.)         │
│  IRQ 11  │ Disponível (USB, etc.)                   │
│  IRQ 12  │ Mouse PS/2                               │
│  IRQ 13  │ Coprocessador matemático (FPU)           │
│  IRQ 14  │ Controlador IDE primário (disco)         │
│  IRQ 15  │ Controlador IDE secundário               │
└──────────┴─────────────────────────────────────────┘

Funcionamento:
  1. Dispositivo gera sinal de interrupção na linha IRQ
  2. Controlador PIC/APIC recebe e prioriza
  3. PIC envia interrupção para a CPU
  4. CPU consulta a tabela de vetores:
     IRQ 1 → endereço 0x0000:0068 → ISR do teclado
  5. CPU salta para a ISR correspondente
```

---

## Exemplo 6: Prioridade de Interrupções 🏆

### Cenário: Múltiplas interrupções simultâneas

```
Tempo ──────────────────────────────────────────────────────►

       Programa        Disco        Teclado       Programa
CPU:  [Principal] → [ISR Disco] → [ISR Tecl.] → [Principal]

O que acontece:
  t1: CPU executando programa principal
  t2: Disco gera IRQ 14 (prioridade ALTA) E
      Teclado gera IRQ 1 (prioridade média) SIMULTANEAMENTE
  t3: CPU atende primeiro o DISCO (prioridade maior)
  t4: Após ISR do disco, atende o TECLADO
  t5: Volta ao programa principal

Interrupção ANINHADA (nested):
  t1: CPU executando programa principal
  t2: Teclado gera IRQ 1 → CPU atende
  t3: DURANTE a ISR do teclado, Disco gera IRQ 14
  t4: Como disco tem MAIOR prioridade:
      CPU salva contexto da ISR do teclado
      CPU atende ISR do disco
  t5: Retorna à ISR do teclado
  t6: Retorna ao programa principal

Pilha de contextos:
  ┌─────────────────┐
  │ ISR Disco       │ ← Executando agora
  ├─────────────────┤
  │ ISR Teclado     │ ← Pausada, esperando
  ├─────────────────┤
  │ Programa Princ. │ ← Pausado, esperando
  └─────────────────┘
```

---

## Exemplo 7: DMA com Cycle Stealing ⚙️

```
Modo Burst vs. Cycle Stealing:

═══ MODO BURST ═══
(DMA toma o barramento por toda a transferência)

Barramento: [CPU][CPU][DMA][DMA][DMA][DMA][DMA][DMA][CPU][CPU]
                       ├──── Bloco inteiro ────┤
CPU parada:            ████████████████████████

═══ MODO CYCLE STEALING ═══
(DMA "rouba" um ciclo por vez)

Barramento: [CPU][DMA][CPU][DMA][CPU][DMA][CPU][DMA][CPU][CPU]
                  │        │        │        │
            Transfere  Transfere Transfere Transfere
             1 dado     1 dado    1 dado    1 dado

CPU afetada: ~50% de redução de velocidade, mas NUNCA para totalmente

═══ MODO TRANSPARENTE ═══
(DMA usa apenas ciclos em que a CPU não precisa do barramento)

Barramento: [CPU][DMA][CPU][ - ][CPU][DMA][ - ][DMA][CPU]
                       └─┘              └─┘
                    CPU não   DMA usa   CPU não
                    usa bar.  quando    usa bar.
                              CPU não
                              precisa

CPU afetada: 0% de redução! (mas transferência mais lenta)
```

---

## Exemplo 8: E/S Mapeada em Memória vs. Isolada 🗺️

### Exemplo x86 (E/S Isolada)

```assembly
; Leitura do teclado (x86 - E/S por portas)
; Porta 0x64 = Status do controlador de teclado
; Porta 0x60 = Dados do teclado

esperar_tecla:
    IN  AL, 0x64        ; Lê status do controlador
    TEST AL, 0x01       ; Verifica bit "dado disponível"
    JZ  esperar_tecla   ; Se não, volta a verificar

    IN  AL, 0x60        ; Lê o scan code da tecla
    ; AL agora contém o código da tecla pressionada
```

### Exemplo ARM (E/S Mapeada em Memória)

```assembly
; Leitura de uma UART (ARM - E/S mapeada em memória)
; 0x101F1000 = Endereço base da UART
; Offset +0x18 = Registrador de flags (status)
; Offset +0x00 = Registrador de dados

    LDR R1, =0x101F1000  ; Base da UART

esperar_dado:
    LDR R0, [R1, #0x18]  ; Lê registrador de flags
    TST R0, #0x10        ; Verifica bit RXFE (RX FIFO empty)
    BNE esperar_dado     ; Se vazio, continua esperando

    LDR R0, [R1, #0x00]  ; Lê dado da UART
    ; R0 agora contém o byte recebido
```

### Comparação

```
┌──────────────────────────────────────────────────────┐
│                  COMPARAÇÃO                           │
├──────────────────────┬───────────────────────────────┤
│  E/S Isolada (x86)   │  E/S Mapeada em Memória (ARM)│
├──────────────────────┼───────────────────────────────┤
│  IN AL, porta        │  LDR R0, [R1, #offset]       │
│  OUT porta, AL       │  STR R0, [R1, #offset]       │
│  Instruções especiais│  Mesmas instruções da memória │
│  Espaço separado     │  Compartilha espaço de end.   │
│  Mais seguro (ring0) │  Pode usar proteção de MMU   │
│  Limitado (65536)    │  Endereçamento amplo          │
└──────────────────────┴───────────────────────────────┘
```

---

## Exemplo 9: Velocidade de Diferentes Barramentos 🚌

### Cenário: Transferir 1 GB de dados

```
┌────────────────┬─────────────────┬────────────────────┐
│   Barramento   │  Velocidade Máx │ Tempo para 1 GB    │
├────────────────┼─────────────────┼────────────────────┤
│ USB 2.0        │    60 MB/s      │    17,1 segundos   │
│ USB 3.0        │   625 MB/s      │     1,6 segundos   │
│ SATA III       │   600 MB/s      │     1,7 segundos   │
│ USB 3.2 (20G)  │  2.500 MB/s     │     0,41 segundos  │
│ PCIe 3.0 x4    │  3.940 MB/s     │     0,26 segundos  │
│ PCIe 4.0 x4    │  7.880 MB/s     │     0,13 segundos  │
│ PCIe 5.0 x4    │ 15.760 MB/s     │     0,065 segundos │
└────────────────┴─────────────────┴────────────────────┘

📌 Note a enorme diferença entre USB 2.0 (17s) e PCIe 5.0 (0,065s)
   = 262x mais rápido!
```

---

## Exemplo 10: Controlador de Interrupções Moderno (APIC) 🎛️

```
Sistema com APIC (Advanced Programmable Interrupt Controller):

┌──────────────────────────────────────────────────────┐
│                    CPU MULTINÚCLEO                     │
│                                                       │
│  ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐│
│  │ Núcleo 0│  │ Núcleo 1│  │ Núcleo 2│  │ Núcleo 3││
│  │ ┌─────┐ │  │ ┌─────┐ │  │ ┌─────┐ │  │ ┌─────┐ ││
│  │ │Local│ │  │ │Local│ │  │ │Local│ │  │ │Local│ ││
│  │ │APIC │ │  │ │APIC │ │  │ │APIC │ │  │ │APIC │ ││
│  │ └──┬──┘ │  │ └──┬──┘ │  │ └──┬──┘ │  │ └──┬──┘ ││
│  └────┼────┘  └────┼────┘  └────┼────┘  └────┼────┘│
│       └────────────┴────────────┴────────────┘     │
│                        │                            │
│                   ┌────┴────┐                       │
│                   │  I/O    │                       │
│                   │  APIC   │  ← Distribui IRQs    │
│                   └────┬────┘    entre os núcleos   │
│                        │                            │
└────────────────────────┼────────────────────────────┘
                         │
          ┌──────────────┼──────────────┐
     ┌────┴───┐    ┌────┴───┐    ┌────┴───┐
     │Teclado │    │ Disco  │    │  Rede  │
     │ IRQ 1  │    │ IRQ 14 │    │ IRQ 10 │
     └────────┘    └────────┘    └────────┘

Distribuição inteligente:
  - IRQ 1 (Teclado) → Núcleo 0
  - IRQ 14 (Disco)  → Núcleo 2
  - IRQ 10 (Rede)   → Núcleo 1
  Diferentes interrupções podem ser processadas
  SIMULTANEAMENTE em núcleos diferentes!
```

---

## 📝 Resumo dos Exemplos

| Exemplo | Conceito Demonstrado |
|---------|---------------------|
| 1 | E/S Programada (polling) - leitura do teclado |
| 2 | E/S por Interrupção - leitura do teclado com ISR |
| 3 | DMA - transferência de arquivo disco→RAM |
| 4 | Comparação numérica das 3 técnicas |
| 5 | Vetor de interrupções (tabela IRQ) |
| 6 | Prioridade e interrupções aninhadas |
| 7 | Modos de DMA (Burst, Cycle Stealing, Transparente) |
| 8 | E/S mapeada em memória vs. isolada (código assembly) |
| 9 | Velocidade de barramentos para 1 GB |
| 10 | APIC e distribuição de interrupções multinúcleo |
