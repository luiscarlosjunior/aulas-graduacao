# 💻 Código pronto — Loja ComprAqui

> **O que é esta pasta.** Aqui está o sistema **Loja ComprAqui** (dono de uma loja online) **já implementado em
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

- **Quem pediu:** dono de uma loja online.
- **Peça central:** `Cliente`, com dois tipos — `ClienteComum` (comum) e `ClienteVIP` (tem frete grátis e desconto progressivo).
- **Transação principal:** `Pedido`, que passa pelas etapas **novo → pago → enviado → entregue; ou cancelado**.
- **Ligações (descritas na entrevista):** Um cliente faz MUITOS pedidos; um pedido é feito de VÁRIOS itens.
- **Regra que não pode falhar (do cliente):** *não pode finalizar um pedido com produto sem estoque*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppLojaComprAqui
```

Leia a saída **junto com** o arquivo `AppLojaComprAqui.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Cliente.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `ClienteComum.java` | tipo comum | Herança + polimorfismo |
| `ClienteVIP.java` | tipo especial (tem frete grátis e desconto progressivo) | Herança + polimorfismo |
| `Pedido.java` | a transação central; estados: novo → pago → enviado → entregue; ou cancelado | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Cliente |
| `ItemPedido.java` | parte que nasce dentro de Pedido | Composição ◆ |
| `Categoria.java` | grupo da agregação de Produto | Agregação ◇ |
| `Produto.java` | item que é agrupado por Categoria | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorPedido.java` | coordena abrir/avançar/cancelar | SRP + DIP |
| `AppLojaComprAqui.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Cliente` é `abstract`: não faz sentido criar "um Cliente genérico". Ela guarda
o que é comum (nome, documento) e obriga cada tipo a responder `calcularFrete()`.
`ClienteComum` e `ClienteVIP` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Cliente`, mas cada uma responde
`calcularFrete()` do seu jeito — `ClienteComum`: paga o frete normal; `ClienteVIP`: tem frete grátis. Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Pedido`:
- **invariante:** `valorTotal` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `cancelar()` recusa se `Pedido` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Pedido` guarda uma referência ao `Cliente` ligado a ela (campo `cliente`).

**Composição (◆) — Pedido ◆ ItemPedido.** Cada item pertence a um pedido e some junto se o pedido for cancelado. No código, quem faz o `new ItemPedido(...)` é
o **próprio todo**, dentro de `adicionarItemPedido(...)` — e o construtor de `ItemPedido` nem é público.

**Agregação (◇) — Categoria ◇ Produto.** Os produtos existem no catálogo por conta própria; a categoria só os agrupa. No código, `adicionarProduto(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorPedido` segue o **SRP** (só coordena o ciclo de vida de cada `Pedido`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `pedido.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorPedido` (injeção de dependência).
2. Cria um `ClienteComum` e um `ClienteVIP` e pergunta `calcularFrete()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Pedido` — ele nasce em `novo`.
4. Adiciona partes (`ItemPedido`) — **composição**.
5. Agrupa um item `Produto` já existente em `Categoria` — **agregação**.
6. Avança o registro de `Pedido` etapa por etapa até `entregue` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Pedido` e leva pelo caminho alternativo `cancelado`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Cliente**, **Estoquista**, **Sistema de pagamento**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Cliente
  - Abrir Pedido
  - Avançar etapa da Pedido
  - Cancelar Pedido
  - Adicionar ItemPedido em Pedido
  - Agrupar Produto em Categoria
  - Consultar `calcularFrete()` de um Cliente
  - Listar os registros de Pedido
- **Dicas:**
  - "Abrir Pedido" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de ClienteVIP" é um bom `«extend»` de "Abrir Pedido": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Cancelar Pedido" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode finalizar um pedido com produto sem estoque*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Pedido`, adicionar uma parte `ItemPedido` e levar o registro até `entregue`** — é o
  trecho dos passos 2, 3 e 5 do `AppLojaComprAqui`.
- **Participantes (linhas de vida):** `AppLojaComprAqui`, `GestorPedido`, `Pedido`, `ItemPedido`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppLojaComprAqui` → `GestorPedido`: `abrir(comum, 150.00)`
  2. `GestorPedido` → `Pedido`: `new Pedido(...)` (criação)
  3. `GestorPedido` → `Notificador`: `notificar(...)`
  4. `AppLojaComprAqui` → `Pedido`: `adicionarItemPedido(...)` → que cria um objeto `ItemPedido`
  5. `AppLojaComprAqui` → `GestorPedido`: `avancar(pedido)` → `GestorPedido` → `Pedido`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode finalizar um pedido com produto sem estoque* entraria como um `alt` antes de criar o objeto `Pedido` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Pedido`.
- **Estados:** `novo`, `pago`, `enviado`, `entregue` e o caminho alternativo `cancelado`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `novo` | `pago` | `avancar()` | não estar em `entregue` nem em `cancelado` |
| `pago` | `enviado` | `avancar()` | não estar em `entregue` nem em `cancelado` |
| `enviado` | `entregue` | `avancar()` | não estar em `entregue` nem em `cancelado` |
| `novo` | `cancelado` | `cancelar()` | ainda não ter chegado em `entregue` |
| `pago` | `cancelado` | `cancelar()` | ainda não ter chegado em `entregue` |
| `enviado` | `cancelado` | `cancelar()` | ainda não ter chegado em `entregue` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `entregue` ou de `cancelado` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Pedido.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Pedido` como atividades, com uma **decisão** (losango)
para a regra *não pode finalizar um pedido com produto sem estoque*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Loja ComprAqui.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode finalizar um pedido com produto sem estoque* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorPedido` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
