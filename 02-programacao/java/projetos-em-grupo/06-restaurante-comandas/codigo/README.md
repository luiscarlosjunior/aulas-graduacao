# 💻 Código pronto — Restaurante SaborLocal

> **O que é esta pasta.** Aqui está o sistema **Restaurante SaborLocal** (dono de um restaurante) **já implementado em
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

- **Quem pediu:** dono de um restaurante.
- **Peça central:** `Cliente`, com dois tipos — `ClienteComum` (comum) e `ClienteRodizio` (paga um valor fixo em vez de somar item a item).
- **Transação principal:** `Comanda`, que passa pelas etapas **aberta → fechada → paga**.
- **Ligações (descritas na entrevista):** Uma mesa tem UMA comanda por vez; a comanda é feita de VÁRIOS pedidos.
- **Regra que não pode falhar (do cliente):** *não pode abrir duas comandas para a mesma mesa ao mesmo tempo*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppRestauranteSaborLocal
```

Leia a saída **junto com** o arquivo `AppRestauranteSaborLocal.java`: cada bloco numerado (1 a 6) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Cliente.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `ClienteComum.java` | tipo comum | Herança + polimorfismo |
| `ClienteRodizio.java` | tipo especial (paga um valor fixo em vez de somar item a item) | Herança + polimorfismo |
| `Comanda.java` | a transação central; estados: aberta → fechada → paga | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Cliente |
| `Pedido.java` | parte que nasce dentro de Comanda | Composição ◆ |
| `Cardapio.java` | grupo da agregação de Prato | Agregação ◇ |
| `Prato.java` | item que é agrupado por Cardapio | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorComanda.java` | coordena abrir/avançar | SRP + DIP |
| `AppRestauranteSaborLocal.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Cliente` é `abstract`: não faz sentido criar "um Cliente genérico". Ela guarda
o que é comum (nome, documento) e obriga cada tipo a responder `calcularConta()`.
`ClienteComum` e `ClienteRodizio` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Cliente`, mas cada uma responde
`calcularConta()` do seu jeito — `ClienteComum`: soma item a item; `ClienteRodizio`: paga o valor fixo do rodízio. Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Comanda`:
- **invariante:** `valorTotal` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Comanda` guarda uma referência ao `Cliente` ligado a ela (campo `cliente`).

**Composição (◆) — Comanda ◆ Pedido.** Cada pedido pertence a uma comanda e some junto se a comanda for cancelada. No código, quem faz o `new Pedido(...)` é
o **próprio todo**, dentro de `adicionarPedido(...)` — e o construtor de `Pedido` nem é público.

**Agregação (◇) — Cardapio ◇ Prato.** Os pratos existem no cardápio por conta própria; a comanda só os referencia. No código, `adicionarPrato(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorComanda` segue o **SRP** (só coordena o ciclo de vida de cada `Comanda`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `comanda.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorComanda` (injeção de dependência).
2. Cria um `ClienteComum` e um `ClienteRodizio` e pergunta `calcularConta()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Comanda` — ele nasce em `aberta`.
4. Adiciona partes (`Pedido`) — **composição**.
5. Agrupa um item `Prato` já existente em `Cardapio` — **agregação**.
6. Avança o registro de `Comanda` etapa por etapa até `paga` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.


---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Cliente**, **Garçom**, **Caixa**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Cliente
  - Abrir Comanda
  - Avançar etapa da Comanda
  - Adicionar Pedido em Comanda
  - Agrupar Prato em Cardapio
  - Consultar `calcularConta()` de um Cliente
  - Listar os registros de Comanda
- **Dicas:**
  - "Abrir Comanda" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de ClienteRodizio" é um bom `«extend»` de "Abrir Comanda": só acontece **às vezes** (quando o participante é do tipo especial).
  - Cuidado: `«include»` é o que acontece **sempre**; `«extend»` é o que acontece **só às vezes**.
  - A regra do cliente (*não pode abrir duas comandas para a mesma mesa ao mesmo tempo*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Comanda`, adicionar uma parte `Pedido` e levar o registro até `paga`** — é o
  trecho dos passos 2, 3 e 5 do `AppRestauranteSaborLocal`.
- **Participantes (linhas de vida):** `AppRestauranteSaborLocal`, `GestorComanda`, `Comanda`, `Pedido`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppRestauranteSaborLocal` → `GestorComanda`: `abrir(comum, 150.00)`
  2. `GestorComanda` → `Comanda`: `new Comanda(...)` (criação)
  3. `GestorComanda` → `Notificador`: `notificar(...)`
  4. `AppRestauranteSaborLocal` → `Comanda`: `adicionarPedido(...)` → que cria um objeto `Pedido`
  5. `AppRestauranteSaborLocal` → `GestorComanda`: `avancar(comanda)` → `GestorComanda` → `Comanda`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode abrir duas comandas para a mesma mesa ao mesmo tempo* entraria como um `alt` antes de criar o objeto `Comanda` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Comanda`.
- **Estados:** `aberta`, `fechada`, `paga`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `aberta` | `fechada` | `avancar()` | não estar em `paga` |
| `fechada` | `paga` | `avancar()` | não estar em `paga` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `paga` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Comanda.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Comanda` como atividades, com uma **decisão** (losango)
para a regra *não pode abrir duas comandas para a mesma mesa ao mesmo tempo*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Restaurante SaborLocal.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode abrir duas comandas para a mesma mesa ao mesmo tempo* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorComanda` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
