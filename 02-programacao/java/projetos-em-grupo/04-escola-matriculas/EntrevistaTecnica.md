# 🎤 Entrevista Técnica — Escola Saber+ (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Escola Saber+** (coordenador de uma escola). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Escola Saber+**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Escola Saber+**, a peça central é **Aluno**. Tem dois tipos: o
**AlunoRegular** (o comum) e o **AlunoBolsista**, que **tem mensalidade reduzida ou isenta**. No fundo, os dois são um tipo de
**Aluno** — todos têm nome, documento e número de matrícula e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `mensalidade()` de cada um, pro
**AlunoRegular** paga a mensalidade cheia; já pro **AlunoBolsista** paga mensalidade reduzida (ou zero). A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: a nota e o valor não podem ser mexidos por fora (nota de 0 a 10; valor nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode matricular o aluno numa turma sem vaga**.
Por isso o estado de **Matricula** só anda por operações (ativa → trancada → concluída; ou cancelada), nunca "na mão".

**Cliente:** Agora as ligações. Uma turma tem MUITOS alunos; um aluno pode estar em VÁRIAS turmas (a matrícula liga os dois).

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Matricula ◆ Nota**.
Cada nota pertence a uma matrícula e some junto se a matrícula for cancelada. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Turma ◇ Aluno** é só um **agrupamento**.
Os alunos existem por conta própria; a turma só os agrupa. Se **Turma** sumir, **Aluno** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Aluno** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `mensalidade()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "A nota e o valor não podem ser mexidos por fora (nota de 0 a 10; valor nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode matricular o aluno numa turma sem vaga" | **invariante**: o estado muda só por operação que valida |
| "**Matricula ◆ Nota** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Turma ◇ Aluno** (Aluno existe sozinho)" | **agregação** (losango vazio ◇) |
| "uma turma tem MUITOS alunos; um aluno pode estar em VÁRIAS turmas (a matrícula liga os dois)" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Aluno`).
2. **Herança** — `AlunoRegular` e `AlunoBolsista` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Matricula`).
4. **Polimorfismo** — a operação `mensalidade()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): uma turma tem MUITOS alunos; um aluno pode estar em VÁRIAS turmas (a matrícula liga os dois).
   - **Agregação** (losango **vazio** `◇`): `Turma` junta `Alunos` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Matricula` é feita de `Notas` que nascem e morrem com ela.

## 🖊️ Lembrete de notação (UML)

| Elemento | Como desenhar |
|----------|---------------|
| Classe abstrata | nome em *itálico* + `«abstract»` (não se cria direto) |
| Operação abstrata | em *itálico* (cada subtipo redefine) |
| Visibilidade | `-` privado · `+` público · `#` protegido |
| Herança | linha com **triângulo vazio** `──▷` apontando para a base |
| Associação | linha (com seta), com **multiplicidade** nas pontas |
| Agregação | losango **vazio** `◇` no lado do "todo" |
| Composição | losango **cheio** `◆` no lado do "todo" |

Multiplicidades: `1` (exatamente um), `0..1` (zero ou um), `*` (muitos), `1..*` (um ou mais).

## ✅ Critério de "pronto"

- [ ] Há uma **classe base abstrata** e os dois tipos herdando dela.
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Matricula` são protegidos.
- [ ] A operação `mensalidade()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Matricula`→`Nota` é composição e `Turma`→`Aluno` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.



[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
