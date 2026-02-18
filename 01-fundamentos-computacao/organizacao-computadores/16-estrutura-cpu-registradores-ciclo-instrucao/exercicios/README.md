# ✏️ Exercícios - Aula 16

## Estrutura da CPU, Registradores e Ciclo de Instrução

---

### 📌 Instruções

- Responda cada exercício de forma completa e justificada
- Para exercícios de trace, mostre TODAS as micro-operações
- Indique o estado dos registradores após cada instrução
- Exercícios marcados com ⭐ são de maior dificuldade

---

### Exercício 1 - Componentes da CPU

a) Desenhe (em formato de texto/diagrama) a estrutura interna da CPU, identificando os quatro componentes principais.

b) Para cada componente, explique sua função em uma frase:
   - ALU
   - Unidade de Controle
   - Registradores
   - Barramento Interno

c) Qual é a diferença entre a implementação hardwired e microprogramada da Unidade de Controle? Cite uma vantagem de cada abordagem.

---

### Exercício 2 - Registradores

a) Classifique cada registrador abaixo como **propósito geral** ou **propósito especial** e descreva sua função:

| Registrador | Classificação | Função |
|------------|---------------|--------|
| RAX | ? | ? |
| PC (Program Counter) | ? | ? |
| IR (Instruction Register) | ? | ? |
| SP (Stack Pointer) | ? | ? |
| PSW/FLAGS | ? | ? |
| MAR | ? | ? |
| MBR/MDR | ? | ? |
| R8 (x86-64) | ? | ? |

b) O que aconteceria se o PC fosse eliminado do projeto da CPU? A CPU ainda poderia funcionar?

c) Por que o IR precisa ser um registrador separado? Não bastaria manter a instrução no MBR?

---

### Exercício 3 - Flags e PSW

Determine o estado das flags Z (Zero), N (Negativo/Sign), C (Carry) e V (Overflow) após cada operação abaixo. Considere aritmética de 8 bits com sinal (complemento de 2):

a) `ADD: 100 + 50`

b) `ADD: 200 + 100` (considere sem sinal para Carry, com sinal para Overflow)

c) `SUB: 30 - 30`

d) `SUB: 10 - 25`

e) `ADD: -128 + (-1)` (em complemento de 2 com 8 bits)

---

### Exercício 4 - Ciclo de Instrução (Fetch)

Descreva detalhadamente as micro-operações da fase de Fetch, respondendo:

a) Quais registradores são usados e qual o papel de cada um?

b) Em quantos ciclos de clock a fase de Fetch é completada? Justifique.

c) Por que o PC é incrementado DURANTE o fetch (e não depois da execução)?

d) Se uma instrução tem 4 bytes de comprimento e o PC atual vale `0x0040FF00`, qual será o valor do PC após o fetch?

---

### Exercício 5 - Trace de Instruções

Faça o trace completo (todas as micro-operações, ciclo a ciclo) de cada instrução abaixo. Para cada ciclo, indique o estado de todos os registradores relevantes:

a) `SUB R3, R1, R2` onde R1 = 50, R2 = 18, PC = 0x100

b) `STORE R4, [0x3000]` onde R4 = 0xABCD1234, PC = 0x200

c) `JNZ 0x500` onde PSW tem flag Z = 0, PC = 0x300

---

### Exercício 6 - Programa Completo ⭐

Dado o seguinte programa e estado inicial da memória:

```
Memória:
  0x100: 8     (valor A)
  0x104: 3     (valor B)
  0x108: ?     (resultado)

Programa (começando em 0x000):
  0x000: LOAD R1, [0x100]    ; R1 ← A
  0x004: LOAD R2, [0x104]    ; R2 ← B
  0x008: SUB  R3, R1, R2     ; R3 ← A - B
  0x00C: MUL  R4, R1, R2     ; R4 ← A × B
  0x010: ADD  R5, R3, R4     ; R5 ← (A-B) + (A×B)
  0x014: STORE R5, [0x108]   ; Mem[0x108] ← resultado
  0x018: HALT
```

a) Execute o programa instrução por instrução, mostrando o estado dos registradores R1-R5 após cada instrução.

b) Qual o valor final armazenado em Mem[0x108]?

c) Quantos acessos à memória ocorrem no total (fetch + dados)?

d) Se cada instrução leva 5 ciclos e o clock é 2 GHz, quanto tempo o programa leva para executar?

---

### Exercício 7 - Formato de Instruções

a) Para uma ISA com instruções de 32 bits e 64 registradores, calcule:
   - Quantos bits são necessários para especificar um registrador?
   - Se o formato é de 3 operandos (R-type), quantos bits sobram para o opcode?

b) Codifique a instrução `ADD R5, R12, R31` no formato R do MIPS, sabendo:
   - opcode = 000000
   - funct (ADD) = 100000
   - shamt = 00000
   - R5=00101, R12=01100, R31=11111

c) Decodifique a instrução MIPS `0x8D280064` (32 bits). Identifique opcode, registradores e imediato. Qual instrução ela representa?

---

### Exercício 8 - Desempenho da CPU ⭐

Um processador de 3 GHz executa um programa com a seguinte distribuição de instruções:

| Tipo | Porcentagem | CPI |
|------|------------|-----|
| Aritmética/Lógica | 45% | 1 |
| Load | 25% | 4 |
| Store | 10% | 4 |
| Branch | 15% | 2 |
| Outros | 5% | 1 |

O programa tem 5 × 10⁹ instruções.

a) Calcule o CPI médio.

b) Calcule o tempo de execução do programa.

c) Se um novo compilador reduz as instruções de Load de 25% para 15% (substituindo por operações registrador-registrador com CPI=1), qual o novo CPI e tempo de execução?

d) Qual foi o speedup obtido pelo novo compilador?

---

### Exercício 9 - Pipeline (Introdução)

Considere uma CPU sem pipeline que completa uma instrução a cada 5 ciclos (5 fases × 1 ciclo cada).

a) Se um programa tem 100 instruções, quantos ciclos leva para executar sem pipeline?

b) Com um pipeline de 5 estágios ideal (sem hazards), quantos ciclos leva para executar as 100 instruções?

c) Qual o speedup do pipeline?

d) Na prática, o pipeline não é ideal. Se 20% das instruções causam stalls de 1 ciclo cada, quantos ciclos são gastos?

e) Qual o speedup real do pipeline neste caso?

---

### Exercício 10 - Chamada de Sub-rotina e Pilha

Dado o programa:

```
Estado inicial: SP = 0x7FFC, PC = 0x1000

0x1000: MOV R1, #5
0x1004: CALL 0x2000        ; Chama sub-rotina
0x1008: ADD R1, R1, R3     ; Continua após retorno
0x100C: HALT

; Sub-rotina:
0x2000: PUSH R1            ; Salva R1 na pilha
0x2004: MUL R3, R1, R1     ; R3 = R1 × R1
0x2008: POP R1             ; Restaura R1
0x200C: RET                ; Retorna
```

a) Trace a execução completa, mostrando o valor de PC, SP, R1 e R3 após cada instrução.

b) Qual o conteúdo da pilha (endereço e valor) nos momentos em que ela está mais "cheia"?

c) Qual o valor final de R1 após o HALT?

---

### Exercício 11 - Micro-operações e Sinais de Controle ⭐

Para a instrução `ADD R1, R2, R3`, liste:

a) As micro-operações de cada fase (Fetch, Decode, Execute, Store).

b) Para cada ciclo de clock, indique quais sinais de controle devem estar ativos:

| Ciclo | Micro-operação | Sinais Ativos |
|-------|---------------|---------------|
| t1 | ? | ? |
| t2 | ? | ? |
| t3 | ? | ? |
| t4 | ? | ? |
| t5 | ? | ? |
| t6 | ? | ? |

c) Quantos sinais de controle DIFERENTES são necessários para executar essa instrução?

---

### Exercício 12 - Questão Dissertativa ⭐

Um colega afirma: *"Para fazer um processador mais rápido, basta aumentar a frequência do clock."*

Escreva um texto de 15 a 20 linhas que:

a) Explique por que essa afirmação é incompleta ou incorreta.

b) Discuta os três fatores da equação de desempenho: `Tempo = N × CPI × T_ciclo`.

c) Dê exemplos de como o CPI pode variar entre processadores com o mesmo clock.

d) Mencione como técnicas como pipeline e execução superescalar afetam o desempenho sem aumentar o clock.

e) Cite o problema da "Power Wall" (muro de potência) que limita o aumento do clock.

---

## 📊 Gabarito Resumido

<details>
<summary>Clique para ver as respostas resumidas</summary>

**Exercício 3:**
- a) 100+50=150: Z=0, N=1 (150 em complemento 2 = negativo), C=0, V=1 (overflow: positivo+positivo=negativo)
- b) 200+100=300: Z=0, N=0 (300 mod 256=44), C=1 (>255), V=0
- c) 30-30=0: Z=1, N=0, C=0, V=0
- d) 10-25=-15: Z=0, N=1, C=1 (borrow), V=0
- e) -128+(-1)=-129: Z=0, N=0 (0x7F=127), C=1, V=1 (overflow)

**Exercício 4d:**
- PC = 0x0040FF00 + 4 = **0x0040FF04**

**Exercício 6:**
- a/b) R1=8, R2=3, R3=5, R4=24, R5=29. Mem[0x108]=**29**
- c) 7 fetches + 2 loads + 1 store = **10 acessos à memória**
- d) 7 instruções × 5 ciclos = 35 ciclos. T = 35 / 2GHz = **17,5 ns**

**Exercício 7a:**
- 64 registradores = 2⁶ → **6 bits** por registrador
- 3 operandos × 6 bits = 18 bits. Opcode = 32 - 18 = **14 bits**

**Exercício 8:**
- a) CPI = 0,45×1 + 0,25×4 + 0,10×4 + 0,15×2 + 0,05×1 = **2,15**
- b) T = 5×10⁹ × 2,15 / 3×10⁹ = **3,58 s**
- c) Novo CPI = 0,45×1 + 0,15×4 + 0,10×4 + 0,15×2 + **0,15×1** = **1,75**
   T = 5×10⁹ × 1,75 / 3×10⁹ = **2,92 s**
- d) Speedup = 3,58 / 2,92 = **1,23x**

**Exercício 9:**
- a) 100 × 5 = **500 ciclos**
- b) 5 + (100-1) = **104 ciclos**
- c) Speedup = 500/104 = **4,81x**
- d) 104 + 20×1 = **124 ciclos**
- e) Speedup = 500/124 = **4,03x**

**Exercício 10:**
- c) R1 = **5** (foi salvo e restaurado pela pilha)
- R3 = **25** (5 × 5)

</details>

---

> 💡 **Dica:** Para exercícios de trace, use uma tabela para acompanhar o estado de TODOS os registradores a cada ciclo. Isso evita erros e facilita a verificação. Comece sempre pela fase de Fetch, que é igual para todas as instruções.
