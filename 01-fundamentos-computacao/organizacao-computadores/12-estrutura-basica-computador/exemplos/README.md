# 📊 Exemplos — Aula 12: Busca e Execução de Instruções, Interrupções, Barramentos

> Exemplos de ciclo de instrução, cenários de interrupção e cálculos de barramento

---

## 🔍 Parte 1 — Ciclo de Instrução

### Exemplo 1: Instrução LOAD (Transferência de Dados)

**Instrução:** `LOAD R1, [500]` — Carrega o conteúdo do endereço 500 para R1.

**Estado inicial:** PC = 200, Memória[200] = "LOAD R1, [500]", Memória[500] = 42

**Execução passo a passo:**

| Fase | Passo | Ação | Estado |
|------|-------|------|--------|
| **Busca** | 1 | MAR ← PC | MAR = 200 |
| | 2 | MBR ← Memória[200] | MBR = "LOAD R1,[500]" |
| | 3 | IR ← MBR | IR = "LOAD R1,[500]" |
| | 4 | PC ← PC + 1 | PC = 201 |
| **Decodif.** | 5 | UC lê opcode = LOAD | destino=R1, end=500 |
| **Execução** | 6 | MAR ← 500 | MAR = 500 |
| | 7 | MBR ← Memória[500] | MBR = 42 |
| | 8 | R1 ← MBR | **R1 = 42** |

**Uso dos barramentos:**

| Passo | Barr. Endereço | Barr. Dados | Barr. Controle |
|-------|---------------|-------------|----------------|
| 2 | 200 | "LOAD R1,[500]" | READ |
| 7 | 500 | 42 | READ |

---

### Exemplo 2: Instrução de Desvio Condicional

**Instrução:** `JZ 300` — Salta para endereço 300 se flag Zero = 1.

**Cenário A:** Flag Z = 1 (resultado anterior foi zero)

| Fase | Ação | Resultado |
|------|------|-----------|
| Busca | IR ← "JZ 300", PC ← PC+1 | PC = 151 |
| Decodif. | UC identifica: JZ, endereço = 300 | — |
| Execução | Z = 1? **Sim** → PC ← 300 | **PC = 300** |

**Cenário B:** Flag Z = 0 (resultado anterior não foi zero)

| Fase | Ação | Resultado |
|------|------|-----------|
| Busca | IR ← "JZ 300", PC ← PC+1 | PC = 151 |
| Decodif. | UC identifica: JZ, endereço = 300 | — |
| Execução | Z = 0? **Não** → PC mantém | **PC = 151** (próxima instrução) |

> 💡 A instrução de desvio modifica o fluxo sequencial do programa alterando o PC.

---

### Exemplo 3: Programa Completo — Calcular |A-B|

**Programa:** Calcula o valor absoluto da diferença entre A (end. 1000) e B (end. 1001).

```
    End. 100: LOAD R1, [1000]     ; R1 ← A
    End. 101: LOAD R2, [1001]     ; R2 ← B
    End. 102: SUB R3, R1, R2      ; R3 ← A - B
    End. 103: JN 106              ; Se negativo, vai para 106
    End. 104: STORE [1002], R3    ; Resultado positivo, armazena
    End. 105: HALT                ; Fim
    End. 106: NEG R3              ; R3 ← -R3 (inverte sinal)
    End. 107: STORE [1002], R3    ; Armazena |A-B|
    End. 108: HALT                ; Fim
```

**Execução com A=3, B=7:**

| PC | Instrução | Ação | Resultado |
|-----|-----------|------|-----------|
| 100 | LOAD R1, [1000] | R1 ← 3 | R1=3 |
| 101 | LOAD R2, [1001] | R2 ← 7 | R2=7 |
| 102 | SUB R3, R1, R2 | R3 ← 3-7 = -4 | R3=-4, N=1 |
| 103 | JN 106 | N=1? Sim → PC ← 106 | PC=106 |
| 106 | NEG R3 | R3 ← 4 | R3=4 |
| 107 | STORE [1002], R3 | Memória[1002] ← 4 | Mem[1002]=4 |
| 108 | HALT | Para | |

**Resultado:** Memória[1002] = 4 = |3-7| ✓

---

## 🔍 Parte 2 — Cenários de Interrupção

### Exemplo 4: Interrupção de Teclado

**Cenário:** O programa está executando um loop quando o usuário pressiona uma tecla.

```
    Programa (loop de cálculo):
    End. 200: LOAD R1, [1000]
    End. 201: ADD R1, R1, R2
    End. 202: STORE [1000], R1    ← interrupção aqui!
    End. 203: JMP 200
```

**Sequência de eventos:**

```
    1. CPU termina STORE [1000], R1 (end. 202)

    2. CPU verifica interrupções → Teclado pediu IRQ!

    3. SALVAR CONTEXTO:
       Pilha ← PC (203)      ; endereço de retorno
       Pilha ← PSW           ; flags de status
       Pilha ← R1, R2        ; registradores usados pela ISR

    4. PC ← Vetor[33] = 0x2100  ; endereço da ISR do teclado

    5. EXECUTAR ISR DO TECLADO:
       0x2100: IN R5, [porta_teclado]  ; lê código da tecla
       0x2101: STORE [buffer], R5      ; armazena no buffer
       0x2102: ...                     ; processa tecla
       0x2103: IRET                    ; retorna da interrupção

    6. RESTAURAR CONTEXTO:
       R2, R1 ← Pilha
       PSW ← Pilha
       PC ← Pilha (203)

    7. CPU continua: JMP 200 (como se nada tivesse acontecido)
```

---

### Exemplo 5: Interrupção de Timer (Troca de Processo)

**Cenário:** O sistema operacional usa um timer para implementar multitarefa.

```
    Timer configurado: gerar interrupção a cada 10 ms

    Processo A executando:
    ────────────────────────┐
    Instrução 1             │ 
    Instrução 2             │ INTERRUPÇÃO DO TIMER!
    Instrução 3             │
    ────────────────────────┘
                            │
    ISR do Timer:           ▼
    ┌────────────────────────────────────────┐
    │ 1. Salvar contexto de A               │
    │ 2. Escalonador escolhe próximo processo│
    │ 3. Restaurar contexto de B            │
    │ 4. IRET                               │
    └────────────────────────────────────────┘
                            │
    Processo B executando:  ▼
    ────────────────────────
    Instrução 1
    Instrução 2
    ...
```

> 💡 É assim que seu computador executa "vários programas ao mesmo tempo" — na verdade, alterna rapidamente entre eles usando interrupções do timer!

---

### Exemplo 6: Interrupções Aninhadas

**Cenário:** Interrupção de disco (prioridade média) ocorre, e durante seu tratamento, uma interrupção de timer (prioridade alta) também ocorre.

```
    Estado da Pilha:

    Programa principal (PC=500, PSW=0x00)
    │
    │ IRQ Disco (prioridade 5)
    ▼
    ┌─────────────────────┐
    │ Pilha:              │
    │   PC=500, PSW=0x00  │  ← contexto do programa
    └─────────────────────┘
    ISR do Disco executando...
    │
    │ IRQ Timer (prioridade 7 — MAIOR!)
    ▼
    ┌─────────────────────┐
    │ Pilha:              │
    │   PC_disco, PSW     │  ← contexto da ISR do disco
    │   PC=500, PSW=0x00  │  ← contexto do programa
    └─────────────────────┘
    ISR do Timer executando...
    │
    │ IRET (retorna do timer)
    ▼
    ISR do Disco continua...
    │
    │ IRET (retorna do disco)
    ▼
    Programa principal continua (PC=500)
```

---

## 🔍 Parte 3 — Cálculos de Barramento

### Exemplo 7: Memória Endereçável

**Problema:** Um processador tem barramento de endereços de 24 bits. Qual é a memória máxima endereçável?

```
    Memória = 2²⁴ = 16.777.216 bytes = 16 MB
```

**Se cada célula armazena 1 byte (endereçamento por byte):**

| Barramento | Cálculo | Memória máxima |
|-----------|---------|---------------|
| 16 bits | 2¹⁶ | 64 KB |
| 20 bits | 2²⁰ | 1 MB |
| 24 bits | 2²⁴ | 16 MB |
| 32 bits | 2³² | 4 GB |
| 64 bits | 2⁶⁴ | 16 EB |

---

### Exemplo 8: Taxa de Transferência do Barramento

**Problema:** Um barramento de dados tem 64 bits de largura e opera a 800 MHz. Qual é a taxa de transferência máxima?

```
    Taxa = largura × frequência
         = 64 bits × 800 × 10⁶ Hz
         = 51.200 × 10⁶ bits/s
         = 51,2 Gbps
         = 6,4 GB/s
```

**Comparando diferentes configurações:**

| Largura | Frequência | Taxa |
|---------|-----------|------|
| 32 bits | 100 MHz | 400 MB/s |
| 64 bits | 100 MHz | 800 MB/s |
| 64 bits | 400 MHz | 3.2 GB/s |
| 64 bits | 800 MHz | 6.4 GB/s |
| 128 bits | 800 MHz | 12.8 GB/s |

> 💡 Dobrar a largura OU a frequência dobra a taxa de transferência!

---

### Exemplo 9: PCIe — Cálculo de Bandwidth

**Problema:** Uma placa de vídeo usa PCIe 4.0 x16. Qual é a taxa bidirecional total?

```
    PCIe 4.0: 1.97 GB/s por lane (cada direção)
    
    x16: 16 lanes
    
    Taxa (cada direção) = 16 × 1.97 = 31.52 GB/s
    Taxa bidirecional = 2 × 31.52 = 63.04 GB/s
```

**Comparação de gerações PCIe x16:**

| Geração | Por lane | x16 total (cada dir.) |
|---------|---------|----------------------|
| PCIe 3.0 | 985 MB/s | 15.75 GB/s |
| PCIe 4.0 | 1.97 GB/s | 31.51 GB/s |
| PCIe 5.0 | 3.94 GB/s | 63.02 GB/s |

---

### Exemplo 10: Impacto do Barramento no Gargalo

**Problema:** Uma CPU executa a 4 GHz (4 bilhões de ciclos/s) e precisa buscar 1 instrução por ciclo. Cada instrução tem 32 bits. O barramento de dados tem 32 bits e opera a 1 GHz. Há gargalo?

```
    CPU precisa: 4 × 10⁹ instruções/s × 32 bits = 128 Gbps
    
    Barramento oferece: 32 bits × 1 × 10⁹ = 32 Gbps
    
    Demanda/Oferta = 128/32 = 4×
    
    A CPU pede 4× mais do que o barramento pode fornecer!
    → GARGALO SEVERO
    → A CPU ficará ociosa 75% do tempo esperando dados
```

**Soluções:**

```
    1. Cache L1: Armazena instruções frequentes perto da CPU
       → Reduz acessos ao barramento em 90%+
    
    2. Barramento mais largo: 64 ou 128 bits
       → Dobra ou quadruplica a taxa
    
    3. Barramento mais rápido: Frequência maior
       → Proporcional ao aumento
    
    4. Pipeline: Busca instrução enquanto executa a anterior
       → Esconde a latência parcialmente
```

---

> 💡 **Dica geral:** Para exercícios de ciclo de instrução, sempre acompanhe o estado de todos os registradores (PC, IR, MAR, MBR, R1, R2...) após cada passo. Isso ajuda a evitar erros e a entender o fluxo.

---

> ⬅️ [Exercícios](../exercicios/README.md) | [Voltar para a Aula](../README.md)
