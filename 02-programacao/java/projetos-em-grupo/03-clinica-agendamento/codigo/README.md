# 💻 Código pronto — Clínica VidaPlena

> **O que é esta pasta.** Aqui está o sistema **Clínica VidaPlena** (dono de uma clínica) **já implementado em
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

- **Quem pediu:** dono de uma clínica.
- **Peça central:** `Paciente`, com dois tipos — `PacienteParticular` (comum) e `PacienteConvenio` (tem valor diferente e exige autorização do convênio).
- **Transação principal:** `Consulta`, que passa pelas etapas **marcada → realizada; ou cancelada**.
- **Ligações (descritas na entrevista):** Um médico tem MUITAS consultas; cada consulta é de UM paciente com UM médico.
- **Regra que não pode falhar (do cliente):** *não pode marcar dois pacientes no mesmo horário do mesmo médico*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppClinicaVidaPlena
```

Leia a saída **junto com** o arquivo `AppClinicaVidaPlena.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Paciente.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `PacienteParticular.java` | tipo comum | Herança + polimorfismo |
| `PacienteConvenio.java` | tipo especial (tem valor diferente e exige autorização do convênio) | Herança + polimorfismo |
| `Consulta.java` | a transação central; estados: marcada → realizada; ou cancelada | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Paciente |
| `Prescricao.java` | parte que nasce dentro de Consulta | Composição ◆ |
| `Convenio.java` | grupo da agregação de Paciente | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorConsulta.java` | coordena abrir/avançar/cancelar | SRP + DIP |
| `AppClinicaVidaPlena.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Paciente` é `abstract`: não faz sentido criar "um Paciente genérico". Ela guarda
o que é comum (nome, documento) e obriga cada tipo a responder `valorConsulta()`.
`PacienteParticular` e `PacienteConvenio` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Paciente`, mas cada uma responde
`valorConsulta()` do seu jeito — `PacienteParticular`: paga o valor particular; `PacienteConvenio`: paga o valor do convênio (com autorização). Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Consulta`:
- **invariante:** `valor` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `cancelar()` recusa se `Consulta` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Consulta` guarda uma referência ao `Paciente` ligado a ela (campo `paciente`).

**Composição (◆) — Consulta ◆ Prescricao.** A prescrição nasce dentro da consulta e não existe fora dela. No código, quem faz o `new Prescricao(...)` é
o **próprio todo**, dentro de `adicionarPrescricao(...)` — e o construtor de `Prescricao` nem é público.

**Agregação (◇) — Convenio ◇ Paciente.** Os pacientes existem por conta própria; o convênio só os agrupa. No código, `adicionarPaciente(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorConsulta` segue o **SRP** (só coordena o ciclo de vida de cada `Consulta`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `consulta.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorConsulta` (injeção de dependência).
2. Cria um `PacienteParticular` e um `PacienteConvenio` e pergunta `valorConsulta()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Consulta` — ele nasce em `marcada`.
4. Adiciona partes (`Prescricao`) — **composição**.
5. Agrupa um item `Paciente` já existente em `Convenio` — **agregação**.
6. Avança o registro de `Consulta` etapa por etapa até `realizada` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Consulta` e leva pelo caminho alternativo `cancelada`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Paciente**, **Recepcionista**, **Médico**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Paciente
  - Abrir Consulta
  - Avançar etapa da Consulta
  - Cancelar Consulta
  - Adicionar Prescricao em Consulta
  - Agrupar Paciente em Convenio
  - Consultar `valorConsulta()` de um Paciente
  - Listar os registros de Consulta
- **Dicas:**
  - "Abrir Consulta" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de PacienteConvenio" é um bom `«extend»` de "Abrir Consulta": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Cancelar Consulta" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode marcar dois pacientes no mesmo horário do mesmo médico*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Consulta`, adicionar uma parte `Prescricao` e levar o registro até `realizada`** — é o
  trecho dos passos 2, 3 e 5 do `AppClinicaVidaPlena`.
- **Participantes (linhas de vida):** `AppClinicaVidaPlena`, `GestorConsulta`, `Consulta`, `Prescricao`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppClinicaVidaPlena` → `GestorConsulta`: `abrir(comum, 150.00)`
  2. `GestorConsulta` → `Consulta`: `new Consulta(...)` (criação)
  3. `GestorConsulta` → `Notificador`: `notificar(...)`
  4. `AppClinicaVidaPlena` → `Consulta`: `adicionarPrescricao(...)` → que cria um objeto `Prescricao`
  5. `AppClinicaVidaPlena` → `GestorConsulta`: `avancar(consulta)` → `GestorConsulta` → `Consulta`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode marcar dois pacientes no mesmo horário do mesmo médico* entraria como um `alt` antes de criar o objeto `Consulta` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Consulta`.
- **Estados:** `marcada`, `realizada` e o caminho alternativo `cancelada`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `marcada` | `realizada` | `avancar()` | não estar em `realizada` nem em `cancelada` |
| `marcada` | `cancelada` | `cancelar()` | ainda não ter chegado em `realizada` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `realizada` ou de `cancelada` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Consulta.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Consulta` como atividades, com uma **decisão** (losango)
para a regra *não pode marcar dois pacientes no mesmo horário do mesmo médico*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Clínica VidaPlena.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode marcar dois pacientes no mesmo horário do mesmo médico* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorConsulta` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
