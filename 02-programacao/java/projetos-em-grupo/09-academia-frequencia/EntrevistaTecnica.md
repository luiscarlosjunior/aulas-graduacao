# 🎤 Entrevista Técnica — Academia CorpoAtivo (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Academia CorpoAtivo** (dono de uma academia). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Academia CorpoAtivo**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Academia CorpoAtivo**, a peça central é **Aluno**. Tem dois tipos: o
**AlunoComum** (o comum) e o **AlunoPlanoFamilia**, que **divide a mensalidade entre dependentes**. No fundo, os dois são um tipo de
**Aluno** — todos têm nome e documento e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `valorMensalidade()` de cada um, pro
**AlunoComum** paga a mensalidade individual; já pro **AlunoPlanoFamilia** divide a mensalidade entre a família. A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: a situação do plano só muda por operações (não pode 'pular' de cancelado para ativo sem renovar). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode fazer check-in com o plano vencido**.
Por isso o estado de **Plano** só anda por operações (ativo → vencido → cancelado), nunca "na mão".

**Cliente:** Agora as ligações. Um aluno tem UM plano ativo e MUITOS check-ins.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Aluno ◆ CheckIn**.
Cada check-in pertence a um aluno e some junto se o cadastro for removido. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Turma ◇ Aluno** é só um **agrupamento**.
Os alunos existem por conta própria; a turma só os agrupa. Se **Turma** sumir, **Aluno** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Aluno** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `valorMensalidade()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "A situação do plano só muda por operações (não pode 'pular' de cancelado para ativo sem renovar)" | dado **privado** + operações → **encapsulamento** |
| "não pode fazer check-in com o plano vencido" | **invariante**: o estado muda só por operação que valida |
| "**Aluno ◆ CheckIn** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Turma ◇ Aluno** (Aluno existe sozinho)" | **agregação** (losango vazio ◇) |
| "um aluno tem UM plano ativo e MUITOS check-ins" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Aluno`).
2. **Herança** — `AlunoComum` e `AlunoPlanoFamilia` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Plano`).
4. **Polimorfismo** — a operação `valorMensalidade()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um aluno tem UM plano ativo e MUITOS check-ins.
   - **Agregação** (losango **vazio** `◇`): `Turma` junta `Alunos` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Aluno` é feita de `CheckIns` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Plano` são protegidos.
- [ ] A operação `valorMensalidade()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Aluno`→`CheckIn` é composição e `Turma`→`Aluno` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.



[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
