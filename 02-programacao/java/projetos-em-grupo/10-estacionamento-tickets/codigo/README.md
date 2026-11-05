# 💻 Código pronto — Estacionamento VagaFácil

> **O que é esta pasta.** Aqui está o sistema **Estacionamento VagaFácil** (administrador de um estacionamento) **já implementado em
> Java**, usando tudo o que vimos **até a aula de SOLID**: classes e objetos, encapsulamento,
> herança, polimorfismo, abstração, interfaces, associação, agregação, composição e dois princípios
> SOLID (SRP e DIP). O código é **simples de propósito** e **todo comentado**.
>
> 🎯 **Para que serve na aula de hoje:** o código já está pronto — o trabalho do grupo é **ler,
> rodar e entender** o sistema e, a partir dele, **criar os diagramas de comportamento**: **casos de
> uso**, **sequência** e **estados**. (O diagrama de classes vocês já fizeram na
> [Entrevista Técnica](../EntrevistaTecnica.md).)

---

## 1. O sistema em poucas palavras

- **Quem pediu:** administrador de um estacionamento.
- **Peça central:** `Cliente`, com dois tipos — `ClienteAvulso` (comum) e `ClienteMensalista` (tem vaga garantida e não paga por hora).
- **Transação principal:** `Ticket`, que passa pelas etapas **aberto → em uso → fechado → pago**.
- **Ligações (descritas na entrevista):** Um cliente gera VÁRIOS tickets; cada ticket ocupa UMA vaga.
- **Regra que não pode falhar (do cliente):** *não pode ocupar uma vaga que já está ocupada*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppEstacionamentoVagaFacil
```

Leia a saída **junto com** o arquivo `AppEstacionamentoVagaFacil.java`: cada bloco numerado (1 a 6) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Cliente.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `ClienteAvulso.java` | tipo comum | Herança + polimorfismo |
| `ClienteMensalista.java` | tipo especial (tem vaga garantida e não paga por hora) | Herança + polimorfismo |
| `Ticket.java` | a transação central; estados: aberto → em uso → fechado → pago | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Cliente |
| `Cobranca.java` | parte que nasce dentro de Ticket | Composição ◆ |
| `Setor.java` | grupo da agregação de Vaga | Agregação ◇ |
| `Vaga.java` | item que é agrupado por Setor | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorTicket.java` | coordena abrir/avançar | SRP + DIP |
| `AppEstacionamentoVagaFacil.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Cliente` é `abstract`: não faz sentido criar "um Cliente genérico". Ela guarda
o que é comum (nome, placa) e obriga cada tipo a responder `calcularValor()`.
`ClienteAvulso` e `ClienteMensalista` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Cliente`, mas cada uma responde
`calcularValor()` do seu jeito — `ClienteAvulso`: paga por hora/fração; `ClienteMensalista`: não paga por hora (mensalidade fixa). Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Ticket`:
- **invariante:** `valor` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Ticket` guarda uma referência ao `Cliente` ligado a ela (campo `cliente`).

**Composição (◆) — Ticket ◆ Cobranca.** A cobrança nasce dentro do ticket e não existe fora dele. No código, quem faz o `new Cobranca(...)` é
o **próprio todo**, dentro de `adicionarCobranca(...)` — e o construtor de `Cobranca` nem é público.

**Agregação (◇) — Setor ◇ Vaga.** As vagas existem no setor por conta própria; o setor só as agrupa. No código, `adicionarVaga(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorTicket` segue o **SRP** (só coordena o ciclo de vida de cada `Ticket`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `ticket.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorTicket` (injeção de dependência).
2. Cria um `ClienteAvulso` e um `ClienteMensalista` e pergunta `calcularValor()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Ticket` — ele nasce em `aberto`.
4. Adiciona partes (`Cobranca`) — **composição**.
5. Agrupa um item `Vaga` já existente em `Setor` — **agregação**.
6. Avança o registro de `Ticket` etapa por etapa até `pago` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.


---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Motorista**, **Operador do caixa**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Cliente
  - Abrir Ticket
  - Avançar etapa da Ticket
  - Adicionar Cobranca em Ticket
  - Agrupar Vaga em Setor
  - Consultar `calcularValor()` de um Cliente
  - Listar os registros de Ticket
- **Dicas:**
  - "Abrir Ticket" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de ClienteMensalista" é um bom `«extend»` de "Abrir Ticket": só acontece **às vezes** (quando o participante é do tipo especial).
  - Cuidado: `«include»` é o que acontece **sempre**; `«extend»` é o que acontece **só às vezes**.
  - A regra do cliente (*não pode ocupar uma vaga que já está ocupada*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Ticket`, adicionar uma parte `Cobranca` e levar o registro até `pago`** — é o
  trecho dos passos 2, 3 e 5 do `AppEstacionamentoVagaFacil`.
- **Participantes (linhas de vida):** `AppEstacionamentoVagaFacil`, `GestorTicket`, `Ticket`, `Cobranca`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppEstacionamentoVagaFacil` → `GestorTicket`: `abrir(comum, 150.00)`
  2. `GestorTicket` → `Ticket`: `new Ticket(...)` (criação)
  3. `GestorTicket` → `Notificador`: `notificar(...)`
  4. `AppEstacionamentoVagaFacil` → `Ticket`: `adicionarCobranca(...)` → que cria um objeto `Cobranca`
  5. `AppEstacionamentoVagaFacil` → `GestorTicket`: `avancar(ticket)` → `GestorTicket` → `Ticket`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode ocupar uma vaga que já está ocupada* entraria como um `alt` antes de criar o objeto `Ticket` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Ticket`.
- **Estados:** `aberto`, `em uso`, `fechado`, `pago`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `aberto` | `em uso` | `avancar()` | não estar em `pago` |
| `em uso` | `fechado` | `avancar()` | não estar em `pago` |
| `fechado` | `pago` | `avancar()` | não estar em `pago` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `pago` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Ticket.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Ticket` como atividades, com uma **decisão** (losango)
para a regra *não pode ocupar uma vaga que já está ocupada*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Estacionamento VagaFácil.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode ocupar uma vaga que já está ocupada* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorTicket` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
