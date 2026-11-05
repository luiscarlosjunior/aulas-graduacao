# 💻 Código pronto — Imobiliária LarCerto

> **O que é esta pasta.** Aqui está o sistema **Imobiliária LarCerto** (dono de uma imobiliária) **já implementado em
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

- **Quem pediu:** dono de uma imobiliária.
- **Peça central:** `Imovel`, com dois tipos — `ImovelResidencial` (comum) e `ImovelComercial` (tem regras de reajuste e impostos diferentes).
- **Transação principal:** `Contrato`, que passa pelas etapas **ativo → encerrado; ou cancelado**.
- **Ligações (descritas na entrevista):** Um proprietário tem VÁRIOS imóveis; cada contrato liga UM imóvel a UM inquilino.
- **Regra que não pode falhar (do cliente):** *não pode alugar um imóvel que já está com contrato ativo*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppImobiliariaLarCerto
```

Leia a saída **junto com** o arquivo `AppImobiliariaLarCerto.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Imovel.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `ImovelResidencial.java` | tipo comum | Herança + polimorfismo |
| `ImovelComercial.java` | tipo especial (tem regras de reajuste e impostos diferentes) | Herança + polimorfismo |
| `Contrato.java` | a transação central; estados: ativo → encerrado; ou cancelado | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Imovel |
| `Parcela.java` | parte que nasce dentro de Contrato | Composição ◆ |
| `Proprietario.java` | grupo da agregação de Imovel | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorContrato.java` | coordena abrir/avançar/cancelar | SRP + DIP |
| `AppImobiliariaLarCerto.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Imovel` é `abstract`: não faz sentido criar "um Imovel genérico". Ela guarda
o que é comum (endereco, valorBase) e obriga cada tipo a responder `calcularReajuste()`.
`ImovelResidencial` e `ImovelComercial` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Imovel`, mas cada uma responde
`calcularReajuste()` do seu jeito — `ImovelResidencial`: reajuste residencial; `ImovelComercial`: reajuste comercial (com impostos próprios). Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Contrato`:
- **invariante:** `valorAluguel` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `cancelar()` recusa se `Contrato` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Contrato` guarda uma referência ao `Imovel` ligado a ela (campo `imovel`).

**Composição (◆) — Contrato ◆ Parcela.** Cada parcela nasce dentro do contrato e some junto se ele for cancelado. No código, quem faz o `new Parcela(...)` é
o **próprio todo**, dentro de `adicionarParcela(...)` — e o construtor de `Parcela` nem é público.

**Agregação (◇) — Proprietario ◇ Imovel.** Os imóveis existem por conta própria; o proprietário só os agrupa. No código, `adicionarImovel(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorContrato` segue o **SRP** (só coordena o ciclo de vida de cada `Contrato`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `contrato.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorContrato` (injeção de dependência).
2. Cria um `ImovelResidencial` e um `ImovelComercial` e pergunta `calcularReajuste()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Contrato` — ele nasce em `ativo`.
4. Adiciona partes (`Parcela`) — **composição**.
5. Agrupa um item `Imovel` já existente em `Proprietario` — **agregação**.
6. Avança o registro de `Contrato` etapa por etapa até `encerrado` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Contrato` e leva pelo caminho alternativo `cancelado`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Inquilino**, **Proprietário**, **Corretor**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Imovel
  - Abrir Contrato
  - Avançar etapa da Contrato
  - Cancelar Contrato
  - Adicionar Parcela em Contrato
  - Agrupar Imovel em Proprietario
  - Consultar `calcularReajuste()` de um Imovel
  - Listar os registros de Contrato
- **Dicas:**
  - "Abrir Contrato" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de ImovelComercial" é um bom `«extend»` de "Abrir Contrato": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Cancelar Contrato" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode alugar um imóvel que já está com contrato ativo*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Contrato`, adicionar uma parte `Parcela` e levar o registro até `encerrado`** — é o
  trecho dos passos 2, 3 e 5 do `AppImobiliariaLarCerto`.
- **Participantes (linhas de vida):** `AppImobiliariaLarCerto`, `GestorContrato`, `Contrato`, `Parcela`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppImobiliariaLarCerto` → `GestorContrato`: `abrir(comum, 150.00)`
  2. `GestorContrato` → `Contrato`: `new Contrato(...)` (criação)
  3. `GestorContrato` → `Notificador`: `notificar(...)`
  4. `AppImobiliariaLarCerto` → `Contrato`: `adicionarParcela(...)` → que cria um objeto `Parcela`
  5. `AppImobiliariaLarCerto` → `GestorContrato`: `avancar(contrato)` → `GestorContrato` → `Contrato`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode alugar um imóvel que já está com contrato ativo* entraria como um `alt` antes de criar o objeto `Contrato` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Contrato`.
- **Estados:** `ativo`, `encerrado` e o caminho alternativo `cancelado`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `ativo` | `encerrado` | `avancar()` | não estar em `encerrado` nem em `cancelado` |
| `ativo` | `cancelado` | `cancelar()` | ainda não ter chegado em `encerrado` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `encerrado` ou de `cancelado` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Contrato.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Contrato` como atividades, com uma **decisão** (losango)
para a regra *não pode alugar um imóvel que já está com contrato ativo*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Imobiliária LarCerto.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode alugar um imóvel que já está com contrato ativo* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorContrato` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
