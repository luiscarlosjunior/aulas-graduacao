# 🎤 Entrevista Técnica — Imobiliária LarCerto (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Imobiliária LarCerto** (dono de uma imobiliária). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Imobiliária LarCerto**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Imobiliária LarCerto**, a peça central é **Imovel**. Tem dois tipos: o
**ImovelResidencial** (o comum) e o **ImovelComercial**, que **tem regras de reajuste e impostos diferentes**. No fundo, os dois são um tipo de
**Imovel** — todos têm endereço e valor base e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `calcularReajuste()` de cada um, pro
**ImovelResidencial** reajuste residencial; já pro **ImovelComercial** reajuste comercial (com impostos próprios). A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o valor do aluguel não pode ser mexido por fora (nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode alugar um imóvel que já está com contrato ativo**.
Por isso o estado de **Contrato** só anda por operações (ativo → encerrado; ou cancelado), nunca "na mão".

**Cliente:** Agora as ligações. Um proprietário tem VÁRIOS imóveis; cada contrato liga UM imóvel a UM inquilino.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Contrato ◆ Parcela**.
Cada parcela nasce dentro do contrato e some junto se ele for cancelado. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Proprietario ◇ Imovel** é só um **agrupamento**.
Os imóveis existem por conta própria; o proprietário só os agrupa. Se **Proprietario** sumir, **Imovel** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Imovel** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `calcularReajuste()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O valor do aluguel não pode ser mexido por fora (nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode alugar um imóvel que já está com contrato ativo" | **invariante**: o estado muda só por operação que valida |
| "**Contrato ◆ Parcela** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Proprietario ◇ Imovel** (Imovel existe sozinho)" | **agregação** (losango vazio ◇) |
| "um proprietário tem VÁRIOS imóveis; cada contrato liga UM imóvel a UM inquilino" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Imovel`).
2. **Herança** — `ImovelResidencial` e `ImovelComercial` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Contrato`).
4. **Polimorfismo** — a operação `calcularReajuste()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um proprietário tem VÁRIOS imóveis; cada contrato liga UM imóvel a UM inquilino.
   - **Agregação** (losango **vazio** `◇`): `Proprietario` junta `Imovels` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Contrato` é feita de `Parcelas` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Contrato` são protegidos.
- [ ] A operação `calcularReajuste()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Contrato`→`Parcela` é composição e `Proprietario`→`Imovel` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.



[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
