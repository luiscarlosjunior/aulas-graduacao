# 🎤 Entrevista Técnica — Pousada Bem-Estar (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Pousada Bem-Estar** (dono de uma pousada). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Pousada Bem-Estar**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Pousada Bem-Estar**, a peça central é **Hospede**. Tem dois tipos: o
**HospedeComum** (o comum) e o **HospedeVIP**, que **tem late check-out e isenção da taxa de serviço**. No fundo, os dois são um tipo de
**Hospede** — todos têm nome, documento e telefone e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `taxaDeServico()` de cada um, pro
**HospedeComum** paga a taxa de serviço cheia; já pro **HospedeVIP** é isento da taxa. A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o valor da diária não pode ser mexido de qualquer lugar (nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode existir reserva de um quarto já ocupado nas mesmas datas**.
Por isso o estado de **Reserva** só anda por operações (criada → confirmada → finalizada; ou cancelada), nunca "na mão".

**Cliente:** Agora as ligações. Um hóspede faz VÁRIAS reservas; cada reserva é de UM quarto.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Reserva ◆ Diaria**.
Cada diária pertence a uma única reserva e some junto se a reserva for cancelada. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Reserva ◇ Hospede** é só um **agrupamento**.
Os acompanhantes existem no cadastro por conta própria e continuam mesmo sem a reserva. Se **Reserva** sumir, **Hospede** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Hospede** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `taxaDeServico()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O valor da diária não pode ser mexido de qualquer lugar (nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode existir reserva de um quarto já ocupado nas mesmas datas" | **invariante**: o estado muda só por operação que valida |
| "**Reserva ◆ Diaria** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Reserva ◇ Hospede** (Hospede existe sozinho)" | **agregação** (losango vazio ◇) |
| "um hóspede faz VÁRIAS reservas; cada reserva é de UM quarto" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Hospede`).
2. **Herança** — `HospedeComum` e `HospedeVIP` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Reserva`).
4. **Polimorfismo** — a operação `taxaDeServico()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um hóspede faz VÁRIAS reservas; cada reserva é de UM quarto.
   - **Agregação** (losango **vazio** `◇`): `Reserva` junta `Hospedes` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Reserva` é feita de `Diarias` que nascem e morrem com ela.

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
- [ ] A operação `taxaDeServico()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Reserva`→`Diaria` é composição e `Reserva`→`Hospede` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
