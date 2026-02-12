# 📝 Exercícios — Aula 12: Busca e Execução de Instruções, Interrupções, Barramentos

> Exercícios sobre ciclo de instrução, registradores, interrupções e barramentos

**Instruções:** Para exercícios de execução, mostre o estado de todos os registradores relevantes após cada passo. Para cálculos, mostre as fórmulas e unidades.

---

## 🟢 Nível Básico

### Exercício 1 — Registradores Fundamentais

**a)** Associe cada registrador à sua função:

| Registrador | Função |
|-------------|--------|
| PC | ( ) Armazena o dado lido/escrito na memória |
| IR | ( ) Armazena o endereço da próxima instrução |
| MAR | ( ) Armazena a instrução sendo executada |
| MBR | ( ) Armazena o endereço de memória a acessar |

**b)** Em qual registrador a instrução `ADD R1, R2, R3` fica armazenada durante sua decodificação?

**c)** Qual registrador é incrementado automaticamente após a busca de cada instrução?

---

### Exercício 2 — Fases do Ciclo de Instrução

Para a instrução `SUB R3, R1, R2` (R3 ← R1 - R2), descreva o que acontece em cada fase:

**a)** Fase de Busca (Fetch):
- Quais registradores são usados?
- Qual barramento é utilizado para buscar a instrução?

**b)** Fase de Decodificação (Decode):
- O que a UC faz?
- Quais campos da instrução são extraídos?

**c)** Fase de Execução (Execute):
- O que a ULA faz?
- Quais flags podem ser afetados?

---

### Exercício 3 — Tipos de Instruções

Classifique cada instrução na categoria correta:

| Instrução | Transferência | Aritmética | Lógica | Controle | E/S |
|-----------|:---:|:---:|:---:|:---:|:---:|
| `ADD R1, R2, R3` | | | | | |
| `LOAD R1, [500]` | | | | | |
| `AND R1, R2, R3` | | | | | |
| `JMP 200` | | | | | |
| `STORE [600], R1` | | | | | |
| `IN R1, [porta]` | | | | | |
| `JZ 300` | | | | | |
| `OR R1, R2, R3` | | | | | |
| `MOV R1, R2` | | | | | |
| `HALT` | | | | | |

---

### Exercício 4 — Barramentos

**a)** Complete a tabela:

| Barramento | Direção | O que transporta |
|-----------|---------|-------------------|
| Endereços | | |
| Dados | | |
| Controle | | |

**b)** Quando a CPU executa `LOAD R1, [500]`, quais barramentos são usados e com quais valores durante a fase de busca do operando?

**c)** E durante a instrução `STORE [600], R2`?

---

## 🟡 Nível Intermediário

### Exercício 5 — Execução Passo a Passo

Execute o programa abaixo, mostrando o estado dos registradores após cada instrução:

```
    Memória:
    End. 100: LOAD R1, [1000]
    End. 101: LOAD R2, [1001]
    End. 102: ADD R3, R1, R2
    End. 103: SUB R4, R1, R2
    End. 104: STORE [1002], R3
    End. 105: STORE [1003], R4
    End. 106: HALT

    Dados:
    End. 1000: 15
    End. 1001: 8
    End. 1002: 0
    End. 1003: 0
```

Preencha a tabela:

| PC | Instrução | R1 | R2 | R3 | R4 | Mem[1002] | Mem[1003] |
|----|-----------|----|----|----|----|-----------|-----------|
| 100 | | | | | | 0 | 0 |
| 101 | | | | | | | |
| 102 | | | | | | | |
| 103 | | | | | | | |
| 104 | | | | | | | |
| 105 | | | | | | | |
| 106 | | | | | | | |

---

### Exercício 6 — Desvio Condicional

Execute o programa com A=10, B=10 e depois com A=10, B=20:

```
    End. 200: LOAD R1, [1000]     ; R1 ← A
    End. 201: LOAD R2, [1001]     ; R2 ← B
    End. 202: SUB R3, R1, R2      ; R3 ← A - B
    End. 203: JZ 206              ; Se zero, pula para 206
    End. 204: STORE [1002], R1    ; Armazena A (são diferentes)
    End. 205: HALT
    End. 206: STORE [1002], R3    ; Armazena 0 (são iguais)
    End. 207: HALT
```

**a)** Com A=10, B=10: qual é o valor final de Memória[1002]? Trace a execução.

**b)** Com A=10, B=20: qual é o valor final de Memória[1002]? Trace a execução.

**c)** O que este programa faz em termos de lógica?

---

### Exercício 7 — Interrupções

**a)** Ordene os passos do tratamento de interrupção:

( ) Restaurar contexto  
( ) Executar ISR  
( ) Salvar contexto  
( ) Verificar se interrupção está habilitada  
( ) CPU completa instrução atual  
( ) Identificar fonte (vetor de interrupção)  
( ) Retornar ao programa (IRET)  

**b)** Explique a diferença entre:
- Interrupção de **hardware** e interrupção de **software**
- Interrupção **mascarável** e **não-mascarável**

**c)** Por que é necessário **salvar o contexto** antes de tratar uma interrupção?

---

### Exercício 8 — Cálculos de Barramento

**a)** Um processador tem barramento de endereços de 20 bits. Qual é a memória máxima endereçável?

**b)** Um barramento de dados tem 32 bits de largura e opera a 200 MHz. Qual é a taxa de transferência máxima em MB/s?

**c)** Se o barramento de endereços for ampliado de 32 para 36 bits, quantas vezes mais memória pode ser endereçada?

**d)** Uma placa de rede usa PCIe 3.0 x4. Qual é a taxa máxima de transferência?

---

## 🔴 Nível Avançado

### Exercício 9 — Programa com Loop e Interrupção

Execute o programa abaixo. Na 2ª iteração do loop, uma interrupção de timer ocorre após a instrução ADD:

```
    End. 300: LOAD R1, [2000]    ; R1 ← contador (início: 0)
    End. 301: ADD R1, R1, 1      ; R1 ← R1 + 1
    End. 302: STORE [2000], R1   ; salva contador
    End. 303: LOAD R2, [2001]    ; R2 ← limite (= 5)
    End. 304: SUB R3, R1, R2     ; R3 ← R1 - limite
    End. 305: JNZ 301            ; Se não zero, continua loop
    End. 306: HALT
```

**a)** Trace as 2 primeiras iterações do loop.

**b)** Descreva o que acontece quando a interrupção de timer ocorre após o ADD na 2ª iteração (PC estava prestes a ir para 302). Mostre o estado da pilha.

**c)** Após o retorno da interrupção, o programa continua corretamente? Explique.

---

### Exercício 10 — Projeto: Tratamento de Múltiplas Interrupções

Considere um sistema com as seguintes interrupções e prioridades:

| Dispositivo | Prioridade | Vetor |
|-------------|-----------|-------|
| Timer | 7 (alta) | 32 |
| Teclado | 5 | 33 |
| Disco | 4 | 34 |
| Impressora | 2 (baixa) | 35 |

**a)** O programa principal está executando. A impressora gera IRQ. Durante o tratamento da impressora, o teclado gera IRQ. O que acontece?

**b)** Continue o cenário: durante o tratamento do teclado, o timer gera IRQ. O que acontece?

**c)** Desenhe o diagrama de pilha mostrando todos os contextos empilhados.

**d)** Em que ordem as ISRs terminam e os contextos são desempilhados?

---

### Exercício 11 — Verdadeiro ou Falso

| | Afirmação | V/F |
|---|-----------|-----|
| a) | O PC armazena o endereço da instrução sendo executada | |
| b) | O MBR é usado tanto para leitura quanto para escrita na memória | |
| c) | Todas as instruções passam por todas as fases do ciclo de instrução | |
| d) | Interrupções de software são assíncronas | |
| e) | O barramento de endereços é bidirecional | |
| f) | Interrupções NMI podem ser desabilitadas pela instrução CLI | |
| g) | O vetor de interrupções contém os endereços das ISRs | |
| h) | Um barramento de endereços de 32 bits pode endereçar 4 GB de memória | |
| i) | PCIe é um barramento serial (não paralelo) | |
| j) | A fase de decodificação usa a ULA | |

---

### Exercício 12 — Análise de Desempenho de Barramento

Uma CPU opera a 3.2 GHz e precisa buscar, em média:
- 1 instrução de 32 bits por ciclo
- 0.3 dados de 64 bits por ciclo (30% das instruções acessam memória)

**a)** Calcule a demanda de bandwidth da CPU (em GB/s) para instruções e dados separadamente.

**b)** Se o barramento do sistema tem 64 bits e opera a 1.6 GHz, qual é o bandwidth disponível?

**c)** A demanda total excede o bandwidth disponível? Se sim, por quanto?

**d)** Explique como a cache resolve (ou ameniza) este problema.

**e)** Se a cache tem uma taxa de acerto (hit rate) de 95%, qual é a demanda real no barramento do sistema?

---

> 💡 **Dica geral:** Ao resolver exercícios de ciclo de instrução, mantenha uma tabela com o estado de todos os registradores — é a forma mais segura de não se perder na execução passo a passo.

---

> ⬅️ [Exemplos](../exemplos/README.md) | [Voltar para a Aula](../README.md)
