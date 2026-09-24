# 🎤 Entrevista Técnica — Farmácia SaúdeJá (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Farmácia SaúdeJá** (dono de uma farmácia de bairro). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Farmácia SaúdeJá**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Farmácia SaúdeJá**, a peça central é **Produto**. Tem dois tipos: o
**ProdutoComum** (o comum) e o **MedicamentoControlado**, que **só pode ser vendido com receita registrada**. No fundo, os dois são um tipo de
**Produto** — todos têm nome, preço e estoque e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `exigeReceita()` de cada um, pro
**ProdutoComum** não exige receita; já pro **MedicamentoControlado** exige receita. A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o estoque do produto não pode ser mexido por fora (nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode vender um item sem estoque suficiente**.
Por isso o estado de **Venda** só anda por operações (aberta → paga; ou cancelada), nunca "na mão".

**Cliente:** Agora as ligações. Uma venda é feita de VÁRIOS itens; cada item aponta para UM produto.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Venda ◆ ItemVenda**.
Cada item da venda só existe dentro dela e some junto se a venda for cancelada. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Categoria ◇ Produto** é só um **agrupamento**.
Os produtos existem por conta própria; a categoria só os agrupa. Se **Categoria** sumir, **Produto** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Produto** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `exigeReceita()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O estoque do produto não pode ser mexido por fora (nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode vender um item sem estoque suficiente" | **invariante**: o estado muda só por operação que valida |
| "**Venda ◆ ItemVenda** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Categoria ◇ Produto** (Produto existe sozinho)" | **agregação** (losango vazio ◇) |
| "uma venda é feita de VÁRIOS itens; cada item aponta para UM produto" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Produto`).
2. **Herança** — `ProdutoComum` e `MedicamentoControlado` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Venda`).
4. **Polimorfismo** — a operação `exigeReceita()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): uma venda é feita de VÁRIOS itens; cada item aponta para UM produto.
   - **Agregação** (losango **vazio** `◇`): `Categoria` junta `Produtos` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Venda` é feita de `ItemVendas` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Venda` são protegidos.
- [ ] A operação `exigeReceita()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Venda`→`ItemVenda` é composição e `Categoria`→`Produto` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.



[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
