# 🎤 Entrevista Técnica — Transportadora EntregaCerta (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Transportadora EntregaCerta** (dono de uma transportadora). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Transportadora EntregaCerta**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Transportadora EntregaCerta**, a peça central é **Encomenda**. Tem dois tipos: o
**EncomendaComum** (o comum) e o **EncomendaExpressa**, que **tem prazo menor e prioridade no transporte**. No fundo, os dois são um tipo de
**Encomenda** — todos têm código e peso e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `prazoEntregaDias()` de cada um, pro
**EncomendaComum** prazo normal; já pro **EncomendaExpressa** prazo menor (prioridade). A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o estado só muda por operações e na ordem certa; o frete nunca é negativo. Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode marcar como 'entregue' uma encomenda que nem saiu para entrega**.
Por isso o estado de **Entrega** só anda por operações (postada → em trânsito → saiu para entrega → entregue; ou devolvida), nunca "na mão".

**Cliente:** Agora as ligações. Uma encomenda tem MUITOS eventos de rastreio; cada entrega é de UMA encomenda para UM destinatário.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Encomenda ◆ EventoRastreio**.
Cada evento de rastreio pertence à encomenda e some junto se ela for removida. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Rota ◇ Encomenda** é só um **agrupamento**.
As encomendas existem por conta própria; a rota só as agrupa para o transporte. Se **Rota** sumir, **Encomenda** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Encomenda** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `prazoEntregaDias()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O estado só muda por operações e na ordem certa; o frete nunca é negativo" | dado **privado** + operações → **encapsulamento** |
| "não pode marcar como 'entregue' uma encomenda que nem saiu para entrega" | **invariante**: o estado muda só por operação que valida |
| "**Encomenda ◆ EventoRastreio** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Rota ◇ Encomenda** (Encomenda existe sozinho)" | **agregação** (losango vazio ◇) |
| "uma encomenda tem MUITOS eventos de rastreio; cada entrega é de UMA encomenda para UM destinatário" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Encomenda`).
2. **Herança** — `EncomendaComum` e `EncomendaExpressa` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Entrega`).
4. **Polimorfismo** — a operação `prazoEntregaDias()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): uma encomenda tem MUITOS eventos de rastreio; cada entrega é de UMA encomenda para UM destinatário.
   - **Agregação** (losango **vazio** `◇`): `Rota` junta `Encomendas` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Encomenda` é feita de `EventoRastreios` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Entrega` são protegidos.
- [ ] A operação `prazoEntregaDias()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Encomenda`→`EventoRastreio` é composição e `Rota`→`Encomenda` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.


[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
