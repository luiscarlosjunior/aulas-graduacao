# 🎤 Entrevista Técnica — Condomínio BemMorar (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Condomínio BemMorar** (síndico de um condomínio). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Condomínio BemMorar**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Condomínio BemMorar**, a peça central é **Morador**. Tem dois tipos: o
**MoradorAdimplente** (o comum) e o **MoradorInadimplente**, que **fica bloqueado de reservar até quitar**. No fundo, os dois são um tipo de
**Morador** — todos têm nome e documento e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `podeReservar()` de cada um, pro
**MoradorAdimplente** pode reservar normalmente; já pro **MoradorInadimplente** fica bloqueado até quitar. A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: a taxa da reserva não pode ser mexida por fora (nunca negativa). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode reservar uma área comum já reservada no mesmo período**.
Por isso o estado de **Reserva** só anda por operações (solicitada → confirmada; ou cancelada), nunca "na mão".

**Cliente:** Agora as ligações. Um morador pertence a UMA unidade e faz VÁRIAS reservas de áreas comuns.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Reserva ◆ Cobranca**.
A cobrança (taxa) nasce dentro da reserva e não existe sem ela. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Bloco ◇ Unidade** é só um **agrupamento**.
As unidades existem por conta própria e são apenas agrupadas por bloco. Se **Bloco** sumir, **Unidade** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Morador** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `podeReservar()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "A taxa da reserva não pode ser mexida por fora (nunca negativa)" | dado **privado** + operações → **encapsulamento** |
| "não pode reservar uma área comum já reservada no mesmo período" | **invariante**: o estado muda só por operação que valida |
| "**Reserva ◆ Cobranca** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Bloco ◇ Unidade** (Unidade existe sozinho)" | **agregação** (losango vazio ◇) |
| "um morador pertence a UMA unidade e faz VÁRIAS reservas de áreas comuns" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Morador`).
2. **Herança** — `MoradorAdimplente` e `MoradorInadimplente` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Reserva`).
4. **Polimorfismo** — a operação `podeReservar()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um morador pertence a UMA unidade e faz VÁRIAS reservas de áreas comuns.
   - **Agregação** (losango **vazio** `◇`): `Bloco` junta `Unidades` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Reserva` é feita de `Cobrancas` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Reserva` são protegidos.
- [ ] A operação `podeReservar()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Reserva`→`Cobranca` é composição e `Bloco`→`Unidade` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.


[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
