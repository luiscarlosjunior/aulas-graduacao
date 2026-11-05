# 💻 Código pronto — Academia CorpoAtivo

> **O que é esta pasta.** Aqui está o sistema **Academia CorpoAtivo** (dono de uma academia) **já implementado em
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

- **Quem pediu:** dono de uma academia.
- **Peça central:** `Aluno`, com dois tipos — `AlunoComum` (comum) e `AlunoPlanoFamilia` (divide a mensalidade entre dependentes).
- **Transação principal:** `Plano`, que passa pelas etapas **ativo → vencido → cancelado**.
- **Ligações (descritas na entrevista):** Um aluno tem UM plano ativo e MUITOS check-ins.
- **Regra que não pode falhar (do cliente):** *não pode fazer check-in com o plano vencido*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppAcademiaCorpoAtivo
```

Leia a saída **junto com** o arquivo `AppAcademiaCorpoAtivo.java`: cada bloco numerado (1 a 6) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Aluno.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `AlunoComum.java` | tipo comum | Herança + polimorfismo |
| `AlunoPlanoFamilia.java` | tipo especial (divide a mensalidade entre dependentes) | Herança + polimorfismo |
| `Plano.java` | a transação central; estados: ativo → vencido → cancelado | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Aluno |
| `CheckIn.java` | parte que nasce dentro de Aluno | Composição ◆ |
| `Turma.java` | grupo da agregação de Aluno | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorPlano.java` | coordena abrir/avançar | SRP + DIP |
| `AppAcademiaCorpoAtivo.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Aluno` é `abstract`: não faz sentido criar "um Aluno genérico". Ela guarda
o que é comum (nome, documento) e obriga cada tipo a responder `valorMensalidade()`.
`AlunoComum` e `AlunoPlanoFamilia` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Aluno`, mas cada uma responde
`valorMensalidade()` do seu jeito — `AlunoComum`: paga a mensalidade individual; `AlunoPlanoFamilia`: divide a mensalidade entre a família. Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Plano`:
- **invariante:** `valorMensalidade` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Plano` guarda uma referência ao `Aluno` ligado a ela (campo `aluno`).

**Composição (◆) — Aluno ◆ CheckIn.** Cada check-in pertence a um aluno e some junto se o cadastro for removido. No código, quem faz o `new CheckIn(...)` é
o **próprio todo**, dentro de `adicionarCheckIn(...)` — e o construtor de `CheckIn` nem é público.

**Agregação (◇) — Turma ◇ Aluno.** Os alunos existem por conta própria; a turma só os agrupa. No código, `adicionarAluno(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorPlano` segue o **SRP** (só coordena o ciclo de vida de cada `Plano`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `plano.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorPlano` (injeção de dependência).
2. Cria um `AlunoComum` e um `AlunoPlanoFamilia` e pergunta `valorMensalidade()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Plano` — ele nasce em `ativo`.
4. Adiciona partes (`CheckIn`) — **composição**.
5. Agrupa um item `Aluno` já existente em `Turma` — **agregação**.
6. Avança o registro de `Plano` etapa por etapa até `cancelado` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.


---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Aluno**, **Recepcionista**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Aluno
  - Abrir Plano
  - Avançar etapa da Plano
  - Adicionar CheckIn em Aluno
  - Agrupar Aluno em Turma
  - Consultar `valorMensalidade()` de um Aluno
  - Listar os registros de Plano
- **Dicas:**
  - "Abrir Plano" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de AlunoPlanoFamilia" é um bom `«extend»` de "Abrir Plano": só acontece **às vezes** (quando o participante é do tipo especial).
  - Cuidado: `«include»` é o que acontece **sempre**; `«extend»` é o que acontece **só às vezes**.
  - A regra do cliente (*não pode fazer check-in com o plano vencido*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Plano`, adicionar uma parte `CheckIn` e levar o registro até `cancelado`** — é o
  trecho dos passos 2, 3 e 5 do `AppAcademiaCorpoAtivo`.
- **Participantes (linhas de vida):** `AppAcademiaCorpoAtivo`, `GestorPlano`, `Plano`, `CheckIn`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppAcademiaCorpoAtivo` → `GestorPlano`: `abrir(comum, 150.00)`
  2. `GestorPlano` → `Plano`: `new Plano(...)` (criação)
  3. `GestorPlano` → `Notificador`: `notificar(...)`
  4. `AppAcademiaCorpoAtivo` → `Aluno`: `adicionarCheckIn(...)` → que cria um objeto `CheckIn`
  5. `AppAcademiaCorpoAtivo` → `GestorPlano`: `avancar(plano)` → `GestorPlano` → `Plano`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode fazer check-in com o plano vencido* entraria como um `alt` antes de criar o objeto `Plano` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Plano`.
- **Estados:** `ativo`, `vencido`, `cancelado`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `ativo` | `vencido` | `avancar()` | não estar em `cancelado` |
| `vencido` | `cancelado` | `avancar()` | não estar em `cancelado` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `cancelado` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Plano.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Plano` como atividades, com uma **decisão** (losango)
para a regra *não pode fazer check-in com o plano vencido*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Academia CorpoAtivo.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode fazer check-in com o plano vencido* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorPlano` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
