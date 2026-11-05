# 💻 Código pronto — Petshop AuMiau

> **O que é esta pasta.** Aqui está o sistema **Petshop AuMiau** (dono de um petshop) **já implementado em
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

- **Quem pediu:** dono de um petshop.
- **Peça central:** `Cliente`, com dois tipos — `ClienteComum` (comum) e `ClientePacoteMensal` (tem banhos inclusos e prioridade na agenda).
- **Transação principal:** `Agendamento`, que passa pelas etapas **marcado → atendido; ou cancelado**.
- **Ligações (descritas na entrevista):** Um dono tem VÁRIOS pets; cada agendamento é de UM pet.
- **Regra que não pode falhar (do cliente):** *não pode agendar dois serviços no mesmo horário do mesmo profissional*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppPetshopAuMiau
```

Leia a saída **junto com** o arquivo `AppPetshopAuMiau.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Cliente.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `ClienteComum.java` | tipo comum | Herança + polimorfismo |
| `ClientePacoteMensal.java` | tipo especial (tem banhos inclusos e prioridade na agenda) | Herança + polimorfismo |
| `Agendamento.java` | a transação central; estados: marcado → atendido; ou cancelado | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Cliente |
| `ItemServico.java` | parte que nasce dentro de Agendamento | Composição ◆ |
| `Pet.java` | item que é agrupado por Cliente | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorAgendamento.java` | coordena abrir/avançar/cancelar | SRP + DIP |
| `AppPetshopAuMiau.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Cliente` é `abstract`: não faz sentido criar "um Cliente genérico". Ela guarda
o que é comum (nome, documento) e obriga cada tipo a responder `precoAgendamento()`.
`ClienteComum` e `ClientePacoteMensal` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Cliente`, mas cada uma responde
`precoAgendamento()` do seu jeito — `ClienteComum`: paga por serviço; `ClientePacoteMensal`: tem banhos inclusos no pacote. Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Agendamento`:
- **invariante:** `valorTotal` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `cancelar()` recusa se `Agendamento` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Agendamento` guarda uma referência ao `Cliente` ligado a ela (campo `cliente`).

**Composição (◆) — Agendamento ◆ ItemServico.** Cada serviço do agendamento só existe dentro dele e some junto se for cancelado. No código, quem faz o `new ItemServico(...)` é
o **próprio todo**, dentro de `adicionarItemServico(...)` — e o construtor de `ItemServico` nem é público.

**Agregação (◇) — Cliente ◇ Pet.** Os pets existem no cadastro por conta própria; o cliente (dono) só os agrupa. No código, `adicionarPet(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorAgendamento` segue o **SRP** (só coordena o ciclo de vida de cada `Agendamento`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `agendamento.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorAgendamento` (injeção de dependência).
2. Cria um `ClienteComum` e um `ClientePacoteMensal` e pergunta `precoAgendamento()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Agendamento` — ele nasce em `marcado`.
4. Adiciona partes (`ItemServico`) — **composição**.
5. Agrupa um item `Pet` já existente em `Cliente` — **agregação**.
6. Avança o registro de `Agendamento` etapa por etapa até `atendido` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Agendamento` e leva pelo caminho alternativo `cancelado`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Dono do pet**, **Atendente**, **Banhista/Tosador**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Cliente
  - Abrir Agendamento
  - Avançar etapa da Agendamento
  - Cancelar Agendamento
  - Adicionar ItemServico em Agendamento
  - Agrupar Pet em Cliente
  - Consultar `precoAgendamento()` de um Cliente
  - Listar os registros de Agendamento
- **Dicas:**
  - "Abrir Agendamento" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de ClientePacoteMensal" é um bom `«extend»` de "Abrir Agendamento": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Cancelar Agendamento" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode agendar dois serviços no mesmo horário do mesmo profissional*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Agendamento`, adicionar uma parte `ItemServico` e levar o registro até `atendido`** — é o
  trecho dos passos 2, 3 e 5 do `AppPetshopAuMiau`.
- **Participantes (linhas de vida):** `AppPetshopAuMiau`, `GestorAgendamento`, `Agendamento`, `ItemServico`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppPetshopAuMiau` → `GestorAgendamento`: `abrir(comum, 150.00)`
  2. `GestorAgendamento` → `Agendamento`: `new Agendamento(...)` (criação)
  3. `GestorAgendamento` → `Notificador`: `notificar(...)`
  4. `AppPetshopAuMiau` → `Agendamento`: `adicionarItemServico(...)` → que cria um objeto `ItemServico`
  5. `AppPetshopAuMiau` → `GestorAgendamento`: `avancar(agendamento)` → `GestorAgendamento` → `Agendamento`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode agendar dois serviços no mesmo horário do mesmo profissional* entraria como um `alt` antes de criar o objeto `Agendamento` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Agendamento`.
- **Estados:** `marcado`, `atendido` e o caminho alternativo `cancelado`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `marcado` | `atendido` | `avancar()` | não estar em `atendido` nem em `cancelado` |
| `marcado` | `cancelado` | `cancelar()` | ainda não ter chegado em `atendido` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `atendido` ou de `cancelado` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Agendamento.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Agendamento` como atividades, com uma **decisão** (losango)
para a regra *não pode agendar dois serviços no mesmo horário do mesmo profissional*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Petshop AuMiau.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode agendar dois serviços no mesmo horário do mesmo profissional* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorAgendamento` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
