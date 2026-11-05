# 💻 Código pronto — Escola Saber+

> **O que é esta pasta.** Aqui está o sistema **Escola Saber+** (coordenador de uma escola) **já implementado em
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

- **Quem pediu:** coordenador de uma escola.
- **Peça central:** `Aluno`, com dois tipos — `AlunoRegular` (comum) e `AlunoBolsista` (tem mensalidade reduzida ou isenta).
- **Transação principal:** `Matricula`, que passa pelas etapas **ativa → trancada → concluída; ou cancelada**.
- **Ligações (descritas na entrevista):** Uma turma tem MUITOS alunos; um aluno pode estar em VÁRIAS turmas (a matrícula liga os dois).
- **Regra que não pode falhar (do cliente):** *não pode matricular o aluno numa turma sem vaga*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppEscolaSaber
```

Leia a saída **junto com** o arquivo `AppEscolaSaber.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Aluno.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `AlunoRegular.java` | tipo comum | Herança + polimorfismo |
| `AlunoBolsista.java` | tipo especial (tem mensalidade reduzida ou isenta) | Herança + polimorfismo |
| `Matricula.java` | a transação central; estados: ativa → trancada → concluída; ou cancelada | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Aluno |
| `Nota.java` | parte que nasce dentro de Matricula | Composição ◆ |
| `Turma.java` | grupo da agregação de Aluno | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorMatricula.java` | coordena abrir/avançar/cancelar | SRP + DIP |
| `AppEscolaSaber.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Aluno` é `abstract`: não faz sentido criar "um Aluno genérico". Ela guarda
o que é comum (nome, matriculaId) e obriga cada tipo a responder `mensalidade()`.
`AlunoRegular` e `AlunoBolsista` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Aluno`, mas cada uma responde
`mensalidade()` do seu jeito — `AlunoRegular`: paga a mensalidade cheia; `AlunoBolsista`: paga mensalidade reduzida (ou zero). Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Matricula`:
- **invariante:** `valorMensalidade` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `cancelar()` recusa se `Matricula` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Matricula` guarda uma referência ao `Aluno` ligado a ela (campo `aluno`).

**Composição (◆) — Matricula ◆ Nota.** Cada nota pertence a uma matrícula e some junto se a matrícula for cancelada. No código, quem faz o `new Nota(...)` é
o **próprio todo**, dentro de `adicionarNota(...)` — e o construtor de `Nota` nem é público.

**Agregação (◇) — Turma ◇ Aluno.** Os alunos existem por conta própria; a turma só os agrupa. No código, `adicionarAluno(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorMatricula` segue o **SRP** (só coordena o ciclo de vida de cada `Matricula`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `matricula.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorMatricula` (injeção de dependência).
2. Cria um `AlunoRegular` e um `AlunoBolsista` e pergunta `mensalidade()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Matricula` — ele nasce em `ativa`.
4. Adiciona partes (`Nota`) — **composição**.
5. Agrupa um item `Aluno` já existente em `Turma` — **agregação**.
6. Avança o registro de `Matricula` etapa por etapa até `concluída` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Matricula` e leva pelo caminho alternativo `cancelada`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Aluno**, **Secretaria**, **Coordenador**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Aluno
  - Abrir Matricula
  - Avançar etapa da Matricula
  - Cancelar Matricula
  - Adicionar Nota em Matricula
  - Agrupar Aluno em Turma
  - Consultar `mensalidade()` de um Aluno
  - Listar os registros de Matricula
- **Dicas:**
  - "Abrir Matricula" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de AlunoBolsista" é um bom `«extend»` de "Abrir Matricula": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Cancelar Matricula" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode matricular o aluno numa turma sem vaga*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Matricula`, adicionar uma parte `Nota` e levar o registro até `concluída`** — é o
  trecho dos passos 2, 3 e 5 do `AppEscolaSaber`.
- **Participantes (linhas de vida):** `AppEscolaSaber`, `GestorMatricula`, `Matricula`, `Nota`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppEscolaSaber` → `GestorMatricula`: `abrir(comum, 150.00)`
  2. `GestorMatricula` → `Matricula`: `new Matricula(...)` (criação)
  3. `GestorMatricula` → `Notificador`: `notificar(...)`
  4. `AppEscolaSaber` → `Matricula`: `adicionarNota(...)` → que cria um objeto `Nota`
  5. `AppEscolaSaber` → `GestorMatricula`: `avancar(matricula)` → `GestorMatricula` → `Matricula`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode matricular o aluno numa turma sem vaga* entraria como um `alt` antes de criar o objeto `Matricula` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Matricula`.
- **Estados:** `ativa`, `trancada`, `concluída` e o caminho alternativo `cancelada`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `ativa` | `trancada` | `avancar()` | não estar em `concluída` nem em `cancelada` |
| `trancada` | `concluída` | `avancar()` | não estar em `concluída` nem em `cancelada` |
| `ativa` | `cancelada` | `cancelar()` | ainda não ter chegado em `concluída` |
| `trancada` | `cancelada` | `cancelar()` | ainda não ter chegado em `concluída` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `concluída` ou de `cancelada` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Matricula.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Matricula` como atividades, com uma **decisão** (losango)
para a regra *não pode matricular o aluno numa turma sem vaga*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Escola Saber+.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode matricular o aluno numa turma sem vaga* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorMatricula` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
