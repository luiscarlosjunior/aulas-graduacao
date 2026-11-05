# 💻 Código pronto — Farmácia SaúdeJá

> **O que é esta pasta.** Aqui está o sistema **Farmácia SaúdeJá** (dono de uma farmácia de bairro) **já implementado em
> Java**, usando tudo o que vimos **até a aula de SOLID**: classes e objetos, encapsulamento,
> herança, polimorfismo, abstração, interfaces, associação, agregação, composição e dois princípios
> SOLID (SRP e DIP). O código é **simples de propósito** e **todo comentado**.
>
> 🎯 **Para que serve na aula de hoje:** o código já está pronto — o trabalho do grupo é **ler,
> rodar e entender** o sistema e, a partir dele, **criar os diagramas de comportamento**: **casos de
> uso**, **sequência** e **estados**. (O diagrama de classes vocês já fizeram na
> [Entrevista Técnica](../EntrevistaTecnica.md).)

---

## 1. O sistema em poucas palavras

- **Quem pediu:** dono de uma farmácia de bairro.
- **Peça central:** `Produto`, com dois tipos — `ProdutoComum` (comum) e `MedicamentoControlado` (só pode ser vendido com receita registrada).
- **Transação principal:** `Venda`, que passa pelas etapas **aberta → paga; ou cancelada**.
- **Ligações (descritas na entrevista):** Uma venda é feita de VÁRIOS itens; cada item aponta para UM produto.
- **Regra que não pode falhar (do cliente):** *não pode vender um item sem estoque suficiente*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppFarmaciaSaudeJa
```

Leia a saída **junto com** o arquivo `AppFarmaciaSaudeJa.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Produto.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `ProdutoComum.java` | tipo comum | Herança + polimorfismo |
| `MedicamentoControlado.java` | tipo especial (só pode ser vendido com receita registrada) | Herança + polimorfismo |
| `Venda.java` | a transação central; estados: aberta → paga; ou cancelada | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Produto |
| `ItemVenda.java` | parte que nasce dentro de Venda | Composição ◆ |
| `Categoria.java` | grupo da agregação de Produto | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorVenda.java` | coordena abrir/avançar/cancelar | SRP + DIP |
| `AppFarmaciaSaudeJa.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Produto` é `abstract`: não faz sentido criar "um Produto genérico". Ela guarda
o que é comum (nome, preco, estoque) e obriga cada tipo a responder `exigeReceita()`.
`ProdutoComum` e `MedicamentoControlado` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Produto`, mas cada uma responde
`exigeReceita()` do seu jeito — `ProdutoComum`: não exige receita; `MedicamentoControlado`: exige receita. Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Venda`:
- **invariante:** `valorTotal` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `cancelar()` recusa se `Venda` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Venda` guarda uma referência ao `Produto` ligado a ela (campo `produto`).

**Composição (◆) — Venda ◆ ItemVenda.** Cada item da venda só existe dentro dela e some junto se a venda for cancelada. No código, quem faz o `new ItemVenda(...)` é
o **próprio todo**, dentro de `adicionarItemVenda(...)` — e o construtor de `ItemVenda` nem é público.

**Agregação (◇) — Categoria ◇ Produto.** Os produtos existem por conta própria; a categoria só os agrupa. No código, `adicionarProduto(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorVenda` segue o **SRP** (só coordena o ciclo de vida de cada `Venda`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `venda.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorVenda` (injeção de dependência).
2. Cria um `ProdutoComum` e um `MedicamentoControlado` e pergunta `exigeReceita()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Venda` — ele nasce em `aberta`.
4. Adiciona partes (`ItemVenda`) — **composição**.
5. Agrupa um item `Produto` já existente em `Categoria` — **agregação**.
6. Avança o registro de `Venda` etapa por etapa até `paga` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Venda` e leva pelo caminho alternativo `cancelada`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Cliente**, **Balconista**, **Farmacêutico**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Produto
  - Abrir Venda
  - Avançar etapa da Venda
  - Cancelar Venda
  - Adicionar ItemVenda em Venda
  - Agrupar Produto em Categoria
  - Consultar `exigeReceita()` de um Produto
  - Listar os registros de Venda
- **Dicas:**
  - "Abrir Venda" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de MedicamentoControlado" é um bom `«extend»` de "Abrir Venda": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Cancelar Venda" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode vender um item sem estoque suficiente*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Venda`, adicionar uma parte `ItemVenda` e levar o registro até `paga`** — é o
  trecho dos passos 2, 3 e 5 do `AppFarmaciaSaudeJa`.
- **Participantes (linhas de vida):** `AppFarmaciaSaudeJa`, `GestorVenda`, `Venda`, `ItemVenda`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppFarmaciaSaudeJa` → `GestorVenda`: `abrir(comum, 150.00)`
  2. `GestorVenda` → `Venda`: `new Venda(...)` (criação)
  3. `GestorVenda` → `Notificador`: `notificar(...)`
  4. `AppFarmaciaSaudeJa` → `Venda`: `adicionarItemVenda(...)` → que cria um objeto `ItemVenda`
  5. `AppFarmaciaSaudeJa` → `GestorVenda`: `avancar(venda)` → `GestorVenda` → `Venda`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode vender um item sem estoque suficiente* entraria como um `alt` antes de criar o objeto `Venda` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Venda`.
- **Estados:** `aberta`, `paga` e o caminho alternativo `cancelada`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `aberta` | `paga` | `avancar()` | não estar em `paga` nem em `cancelada` |
| `aberta` | `cancelada` | `cancelar()` | ainda não ter chegado em `paga` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `paga` ou de `cancelada` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Venda.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Venda` como atividades, com uma **decisão** (losango)
para a regra *não pode vender um item sem estoque suficiente*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Farmácia SaúdeJá.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode vender um item sem estoque suficiente* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorVenda` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
