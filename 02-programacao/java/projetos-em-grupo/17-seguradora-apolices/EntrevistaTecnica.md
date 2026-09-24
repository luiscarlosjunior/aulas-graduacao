# 🎤 Entrevista Técnica — Seguradora Protege+ (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Seguradora Protege+** (gerente de uma seguradora). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Seguradora Protege+**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Seguradora Protege+**, a peça central é **Apolice**. Tem dois tipos: o
**ApoliceAuto** (o comum) e o **ApoliceVida**, que **tem regras de cobertura e cálculo de prêmio próprios**. No fundo, os dois são um tipo de
**Apolice** — todos têm número e valor segurado e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `calcularPremio()` de cada um, pro
**ApoliceAuto** prêmio calculado pelas regras de auto; já pro **ApoliceVida** prêmio calculado pelas regras de vida. A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o valor da indenização não pode ser mexido por fora (nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode abrir sinistro em uma apólice vencida ou cancelada**.
Por isso o estado de **Sinistro** só anda por operações (aberto → em análise → aprovado → pago; ou negado), nunca "na mão".

**Cliente:** Agora as ligações. Um cliente tem VÁRIAS apólices; cada apólice pode gerar VÁRIOS sinistros.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Apolice ◆ Cobertura**.
Cada cobertura só existe dentro da apólice e some junto se a apólice for cancelada. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Cliente ◇ Apolice** é só um **agrupamento**.
As apólices pertencem ao cliente, mas são listadas/agrupadas por ele. Se **Cliente** sumir, **Apolice** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Apolice** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `calcularPremio()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O valor da indenização não pode ser mexido por fora (nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode abrir sinistro em uma apólice vencida ou cancelada" | **invariante**: o estado muda só por operação que valida |
| "**Apolice ◆ Cobertura** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Cliente ◇ Apolice** (Apolice existe sozinho)" | **agregação** (losango vazio ◇) |
| "um cliente tem VÁRIAS apólices; cada apólice pode gerar VÁRIOS sinistros" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Apolice`).
2. **Herança** — `ApoliceAuto` e `ApoliceVida` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Sinistro`).
4. **Polimorfismo** — a operação `calcularPremio()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um cliente tem VÁRIAS apólices; cada apólice pode gerar VÁRIOS sinistros.
   - **Agregação** (losango **vazio** `◇`): `Cliente` junta `Apolices` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Apolice` é feita de `Coberturas` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Sinistro` são protegidos.
- [ ] A operação `calcularPremio()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Apolice`→`Cobertura` é composição e `Cliente`→`Apolice` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.



[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
