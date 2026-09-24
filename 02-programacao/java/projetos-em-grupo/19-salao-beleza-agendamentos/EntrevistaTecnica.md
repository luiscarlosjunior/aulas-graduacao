# 🎤 Entrevista Técnica — Salão BelaForma (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Salão BelaForma** (dono de um salão de beleza). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Salão BelaForma**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Salão BelaForma**, a peça central é **Cliente**. Tem dois tipos: o
**ClienteComum** (o comum) e o **ClienteFidelidade**, que **acumula pontos e ganha descontos**. No fundo, os dois são um tipo de
**Cliente** — todos têm nome e telefone e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `calcularDesconto()` de cada um, pro
**ClienteComum** não tem desconto; já pro **ClienteFidelidade** ganha desconto por pontos. A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o preço do serviço não pode ser mexido por fora (nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode agendar dois serviços no mesmo horário do mesmo profissional**.
Por isso o estado de **Agendamento** só anda por operações (marcado → atendido; ou faltou/cancelado), nunca "na mão".

**Cliente:** Agora as ligações. Um profissional tem MUITOS agendamentos; cada agendamento é de UMA cliente para UM serviço.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Agendamento ◆ ItemServico**.
Cada serviço do agendamento só existe dentro dele e some junto se for cancelado. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Profissional ◇ Servico** é só um **agrupamento**.
Os serviços existem no catálogo por conta própria; o profissional só os oferece. Se **Profissional** sumir, **Servico** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Cliente** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `calcularDesconto()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O preço do serviço não pode ser mexido por fora (nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode agendar dois serviços no mesmo horário do mesmo profissional" | **invariante**: o estado muda só por operação que valida |
| "**Agendamento ◆ ItemServico** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Profissional ◇ Servico** (Servico existe sozinho)" | **agregação** (losango vazio ◇) |
| "um profissional tem MUITOS agendamentos; cada agendamento é de UMA cliente para UM serviço" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Cliente`).
2. **Herança** — `ClienteComum` e `ClienteFidelidade` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Agendamento`).
4. **Polimorfismo** — a operação `calcularDesconto()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um profissional tem MUITOS agendamentos; cada agendamento é de UMA cliente para UM serviço.
   - **Agregação** (losango **vazio** `◇`): `Profissional` junta `Servicos` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Agendamento` é feita de `ItemServicos` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Agendamento` são protegidos.
- [ ] A operação `calcularDesconto()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Agendamento`→`ItemServico` é composição e `Profissional`→`Servico` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.


[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
