# 🎤 Entrevista Técnica — Clínica VidaPlena (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Clínica VidaPlena** (dono de uma clínica). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Clínica VidaPlena**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Clínica VidaPlena**, a peça central é **Paciente**. Tem dois tipos: o
**PacienteParticular** (o comum) e o **PacienteConvenio**, que **tem valor diferente e exige autorização do convênio**. No fundo, os dois são um tipo de
**Paciente** — todos têm nome, documento e telefone e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `valorConsulta()` de cada um, pro
**PacienteParticular** paga o valor particular; já pro **PacienteConvenio** paga o valor do convênio (com autorização). A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o valor da consulta não pode ser mexido por fora (nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode marcar dois pacientes no mesmo horário do mesmo médico**.
Por isso o estado de **Consulta** só anda por operações (marcada → realizada; ou cancelada), nunca "na mão".

**Cliente:** Agora as ligações. Um médico tem MUITAS consultas; cada consulta é de UM paciente com UM médico.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Consulta ◆ Prescricao**.
A prescrição nasce dentro da consulta e não existe fora dela. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Convenio ◇ Paciente** é só um **agrupamento**.
Os pacientes existem por conta própria; o convênio só os agrupa. Se **Convenio** sumir, **Paciente** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Paciente** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `valorConsulta()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O valor da consulta não pode ser mexido por fora (nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode marcar dois pacientes no mesmo horário do mesmo médico" | **invariante**: o estado muda só por operação que valida |
| "**Consulta ◆ Prescricao** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Convenio ◇ Paciente** (Paciente existe sozinho)" | **agregação** (losango vazio ◇) |
| "um médico tem MUITAS consultas; cada consulta é de UM paciente com UM médico" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Paciente`).
2. **Herança** — `PacienteParticular` e `PacienteConvenio` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Consulta`).
4. **Polimorfismo** — a operação `valorConsulta()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um médico tem MUITAS consultas; cada consulta é de UM paciente com UM médico.
   - **Agregação** (losango **vazio** `◇`): `Convenio` junta `Pacientes` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Consulta` é feita de `Prescricaos` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Consulta` são protegidos.
- [ ] A operação `valorConsulta()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Consulta`→`Prescricao` é composição e `Convenio`→`Paciente` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.



[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
