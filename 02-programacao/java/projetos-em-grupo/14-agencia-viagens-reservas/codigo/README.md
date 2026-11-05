# 💻 Código pronto — Agência MundoAfora

> **O que é esta pasta.** Aqui está o sistema **Agência MundoAfora** (dono de uma agência de viagens) **já implementado em
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

- **Quem pediu:** dono de uma agência de viagens.
- **Peça central:** `Cliente`, com dois tipos — `ClienteComum` (comum) e `ClientePremium` (inclui traslado e seguro-viagem).
- **Transação principal:** `Reserva`, que passa pelas etapas **pendente → confirmada; ou cancelada**.
- **Ligações (descritas na entrevista):** Um pacote tem MUITAS reservas; cada reserva é de UM cliente para UM pacote.
- **Regra que não pode falhar (do cliente):** *não pode reservar mais vagas do que o pacote oferece*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppAgenciaMundoAfora
```

Leia a saída **junto com** o arquivo `AppAgenciaMundoAfora.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Cliente.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `ClienteComum.java` | tipo comum | Herança + polimorfismo |
| `ClientePremium.java` | tipo especial (inclui traslado e seguro-viagem) | Herança + polimorfismo |
| `Reserva.java` | a transação central; estados: pendente → confirmada; ou cancelada | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Cliente |
| `ItemPacote.java` | parte que nasce dentro de Pacote | Composição ◆ |
| `Pacote.java` | todo da composição com ItemPacote e grupo da agregação de Destino | Composição ◆ |
| `Destino.java` | item que é agrupado por Pacote | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorReserva.java` | coordena abrir/avançar/cancelar | SRP + DIP |
| `AppAgenciaMundoAfora.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Cliente` é `abstract`: não faz sentido criar "um Cliente genérico". Ela guarda
o que é comum (nome, documento) e obriga cada tipo a responder `valorComExtras()`.
`ClienteComum` e `ClientePremium` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Cliente`, mas cada uma responde
`valorComExtras()` do seu jeito — `ClienteComum`: paga só o pacote; `ClientePremium`: inclui traslado e seguro no valor. Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Reserva`:
- **invariante:** `valor` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `cancelar()` recusa se `Reserva` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Reserva` guarda uma referência ao `Cliente` ligado a ela (campo `cliente`).

**Composição (◆) — Pacote ◆ ItemPacote.** Cada item (voo, hotel, passeio) só existe dentro do pacote e some junto com ele. No código, quem faz o `new ItemPacote(...)` é
o **próprio todo**, dentro de `adicionarItemPacote(...)` — e o construtor de `ItemPacote` nem é público.

**Agregação (◇) — Pacote ◇ Destino.** Os destinos existem por conta própria; o pacote só os agrupa no roteiro. No código, `adicionarDestino(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorReserva` segue o **SRP** (só coordena o ciclo de vida de cada `Reserva`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `reserva.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorReserva` (injeção de dependência).
2. Cria um `ClienteComum` e um `ClientePremium` e pergunta `valorComExtras()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Reserva` — ele nasce em `pendente`.
4. Adiciona partes (`ItemPacote`) — **composição**.
5. Agrupa um item `Destino` já existente em `Pacote` — **agregação**.
6. Avança o registro de `Reserva` etapa por etapa até `confirmada` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Reserva` e leva pelo caminho alternativo `cancelada`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Cliente**, **Agente de viagens**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Cliente
  - Abrir Reserva
  - Avançar etapa da Reserva
  - Cancelar Reserva
  - Adicionar ItemPacote em Pacote
  - Agrupar Destino em Pacote
  - Consultar `valorComExtras()` de um Cliente
  - Listar os registros de Reserva
- **Dicas:**
  - "Abrir Reserva" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de ClientePremium" é um bom `«extend»` de "Abrir Reserva": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Cancelar Reserva" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode reservar mais vagas do que o pacote oferece*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Reserva`, adicionar uma parte `ItemPacote` e levar o registro até `confirmada`** — é o
  trecho dos passos 2, 3 e 5 do `AppAgenciaMundoAfora`.
- **Participantes (linhas de vida):** `AppAgenciaMundoAfora`, `GestorReserva`, `Reserva`, `Pacote`, `ItemPacote`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppAgenciaMundoAfora` → `GestorReserva`: `abrir(comum, 150.00)`
  2. `GestorReserva` → `Reserva`: `new Reserva(...)` (criação)
  3. `GestorReserva` → `Notificador`: `notificar(...)`
  4. `AppAgenciaMundoAfora` → `Pacote`: `adicionarItemPacote(...)` → que cria um objeto `ItemPacote`
  5. `AppAgenciaMundoAfora` → `GestorReserva`: `avancar(reserva)` → `GestorReserva` → `Reserva`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode reservar mais vagas do que o pacote oferece* entraria como um `alt` antes de criar o objeto `Reserva` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Reserva`.
- **Estados:** `pendente`, `confirmada` e o caminho alternativo `cancelada`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `pendente` | `confirmada` | `avancar()` | não estar em `confirmada` nem em `cancelada` |
| `pendente` | `cancelada` | `cancelar()` | ainda não ter chegado em `confirmada` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `confirmada` ou de `cancelada` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Reserva.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Reserva` como atividades, com uma **decisão** (losango)
para a regra *não pode reservar mais vagas do que o pacote oferece*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Agência MundoAfora.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode reservar mais vagas do que o pacote oferece* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorReserva` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
