# 💻 Código pronto — Condomínio BemMorar

> **O que é esta pasta.** Aqui está o sistema **Condomínio BemMorar** (síndico de um condomínio) **já implementado em
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

- **Quem pediu:** síndico de um condomínio.
- **Peça central:** `Morador`, com dois tipos — `MoradorAdimplente` (comum) e `MoradorInadimplente` (fica bloqueado de reservar até quitar).
- **Transação principal:** `Reserva`, que passa pelas etapas **solicitada → confirmada; ou cancelada**.
- **Ligações (descritas na entrevista):** Um morador pertence a UMA unidade e faz VÁRIAS reservas de áreas comuns.
- **Regra que não pode falhar (do cliente):** *não pode reservar uma área comum já reservada no mesmo período*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppCondominioBemMorar
```

Leia a saída **junto com** o arquivo `AppCondominioBemMorar.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Morador.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `MoradorAdimplente.java` | tipo comum | Herança + polimorfismo |
| `MoradorInadimplente.java` | tipo especial (fica bloqueado de reservar até quitar) | Herança + polimorfismo |
| `Reserva.java` | a transação central; estados: solicitada → confirmada; ou cancelada | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Morador |
| `Cobranca.java` | parte que nasce dentro de Reserva | Composição ◆ |
| `Bloco.java` | grupo da agregação de Unidade | Agregação ◇ |
| `Unidade.java` | item que é agrupado por Bloco | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorReserva.java` | coordena abrir/avançar/cancelar | SRP + DIP |
| `AppCondominioBemMorar.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Morador` é `abstract`: não faz sentido criar "um Morador genérico". Ela guarda
o que é comum (nome, documento) e obriga cada tipo a responder `podeReservar()`.
`MoradorAdimplente` e `MoradorInadimplente` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Morador`, mas cada uma responde
`podeReservar()` do seu jeito — `MoradorAdimplente`: pode reservar normalmente; `MoradorInadimplente`: fica bloqueado até quitar. Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Reserva`:
- **invariante:** `valorTaxa` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `cancelar()` recusa se `Reserva` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Reserva` guarda uma referência ao `Morador` ligado a ela (campo `morador`).

**Composição (◆) — Reserva ◆ Cobranca.** A cobrança (taxa) nasce dentro da reserva e não existe sem ela. No código, quem faz o `new Cobranca(...)` é
o **próprio todo**, dentro de `adicionarCobranca(...)` — e o construtor de `Cobranca` nem é público.

**Agregação (◇) — Bloco ◇ Unidade.** As unidades existem por conta própria e são apenas agrupadas por bloco. No código, `adicionarUnidade(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorReserva` segue o **SRP** (só coordena o ciclo de vida de cada `Reserva`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `reserva.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorReserva` (injeção de dependência).
2. Cria um `MoradorAdimplente` e um `MoradorInadimplente` e pergunta `podeReservar()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Reserva` — ele nasce em `solicitada`.
4. Adiciona partes (`Cobranca`) — **composição**.
5. Agrupa um item `Unidade` já existente em `Bloco` — **agregação**.
6. Avança o registro de `Reserva` etapa por etapa até `confirmada` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Reserva` e leva pelo caminho alternativo `cancelada`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Morador**, **Síndico**, **Porteiro**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Morador
  - Abrir Reserva
  - Avançar etapa da Reserva
  - Cancelar Reserva
  - Adicionar Cobranca em Reserva
  - Agrupar Unidade em Bloco
  - Consultar `podeReservar()` de um Morador
  - Listar os registros de Reserva
- **Dicas:**
  - "Abrir Reserva" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de MoradorInadimplente" é um bom `«extend»` de "Abrir Reserva": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Cancelar Reserva" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode reservar uma área comum já reservada no mesmo período*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Reserva`, adicionar uma parte `Cobranca` e levar o registro até `confirmada`** — é o
  trecho dos passos 2, 3 e 5 do `AppCondominioBemMorar`.
- **Participantes (linhas de vida):** `AppCondominioBemMorar`, `GestorReserva`, `Reserva`, `Cobranca`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppCondominioBemMorar` → `GestorReserva`: `abrir(comum, 150.00)`
  2. `GestorReserva` → `Reserva`: `new Reserva(...)` (criação)
  3. `GestorReserva` → `Notificador`: `notificar(...)`
  4. `AppCondominioBemMorar` → `Reserva`: `adicionarCobranca(...)` → que cria um objeto `Cobranca`
  5. `AppCondominioBemMorar` → `GestorReserva`: `avancar(reserva)` → `GestorReserva` → `Reserva`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode reservar uma área comum já reservada no mesmo período* entraria como um `alt` antes de criar o objeto `Reserva` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Reserva`.
- **Estados:** `solicitada`, `confirmada` e o caminho alternativo `cancelada`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `solicitada` | `confirmada` | `avancar()` | não estar em `confirmada` nem em `cancelada` |
| `solicitada` | `cancelada` | `cancelar()` | ainda não ter chegado em `confirmada` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `confirmada` ou de `cancelada` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Reserva.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Reserva` como atividades, com uma **decisão** (losango)
para a regra *não pode reservar uma área comum já reservada no mesmo período*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Condomínio BemMorar.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode reservar uma área comum já reservada no mesmo período* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorReserva` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
