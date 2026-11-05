# 💻 Código pronto — Locadora RodaLivre

> **O que é esta pasta.** Aqui está o sistema **Locadora RodaLivre** (dono de uma locadora de carros) **já implementado em
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

- **Quem pediu:** dono de uma locadora de carros.
- **Peça central:** `Cliente`, com dois tipos — `ClienteComum` (comum) e `ClientePremium` (tem desconto na diária e prioridade na reserva).
- **Transação principal:** `Locacao`, que passa pelas etapas **aberta → em andamento → finalizada; ou cancelada**.
- **Ligações (descritas na entrevista):** Um cliente faz VÁRIAS locações; cada locação é de UM veículo.
- **Regra que não pode falhar (do cliente):** *não pode alugar um veículo que já está locado no período*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppLocadoraRodaLivre
```

Leia a saída **junto com** o arquivo `AppLocadoraRodaLivre.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Cliente.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `ClienteComum.java` | tipo comum | Herança + polimorfismo |
| `ClientePremium.java` | tipo especial (tem desconto na diária e prioridade na reserva) | Herança + polimorfismo |
| `Locacao.java` | a transação central; estados: aberta → em andamento → finalizada; ou cancelada | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Cliente |
| `ItemCobranca.java` | parte que nasce dentro de Locacao | Composição ◆ |
| `Categoria.java` | grupo da agregação de Veiculo | Agregação ◇ |
| `Veiculo.java` | item que é agrupado por Categoria | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorLocacao.java` | coordena abrir/avançar/cancelar | SRP + DIP |
| `AppLocadoraRodaLivre.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Cliente` é `abstract`: não faz sentido criar "um Cliente genérico". Ela guarda
o que é comum (nome, documento) e obriga cada tipo a responder `descontoDiaria()`.
`ClienteComum` e `ClientePremium` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Cliente`, mas cada uma responde
`descontoDiaria()` do seu jeito — `ClienteComum`: não tem desconto; `ClientePremium`: tem desconto na diária. Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Locacao`:
- **invariante:** `valorDiaria` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `cancelar()` recusa se `Locacao` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Locacao` guarda uma referência ao `Cliente` ligado a ela (campo `cliente`).

**Composição (◆) — Locacao ◆ ItemCobranca.** Cada item (diária, multa) só existe dentro da locação e some junto com ela. No código, quem faz o `new ItemCobranca(...)` é
o **próprio todo**, dentro de `adicionarItemCobranca(...)` — e o construtor de `ItemCobranca` nem é público.

**Agregação (◇) — Categoria ◇ Veiculo.** Os veículos existem por conta própria; a categoria só os agrupa. No código, `adicionarVeiculo(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorLocacao` segue o **SRP** (só coordena o ciclo de vida de cada `Locacao`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `locacao.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorLocacao` (injeção de dependência).
2. Cria um `ClienteComum` e um `ClientePremium` e pergunta `descontoDiaria()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Locacao` — ele nasce em `aberta`.
4. Adiciona partes (`ItemCobranca`) — **composição**.
5. Agrupa um item `Veiculo` já existente em `Categoria` — **agregação**.
6. Avança o registro de `Locacao` etapa por etapa até `finalizada` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Locacao` e leva pelo caminho alternativo `cancelada`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Cliente**, **Atendente da locadora**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Cliente
  - Abrir Locacao
  - Avançar etapa da Locacao
  - Cancelar Locacao
  - Adicionar ItemCobranca em Locacao
  - Agrupar Veiculo em Categoria
  - Consultar `descontoDiaria()` de um Cliente
  - Listar os registros de Locacao
- **Dicas:**
  - "Abrir Locacao" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de ClientePremium" é um bom `«extend»` de "Abrir Locacao": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Cancelar Locacao" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode alugar um veículo que já está locado no período*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Locacao`, adicionar uma parte `ItemCobranca` e levar o registro até `finalizada`** — é o
  trecho dos passos 2, 3 e 5 do `AppLocadoraRodaLivre`.
- **Participantes (linhas de vida):** `AppLocadoraRodaLivre`, `GestorLocacao`, `Locacao`, `ItemCobranca`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppLocadoraRodaLivre` → `GestorLocacao`: `abrir(comum, 150.00)`
  2. `GestorLocacao` → `Locacao`: `new Locacao(...)` (criação)
  3. `GestorLocacao` → `Notificador`: `notificar(...)`
  4. `AppLocadoraRodaLivre` → `Locacao`: `adicionarItemCobranca(...)` → que cria um objeto `ItemCobranca`
  5. `AppLocadoraRodaLivre` → `GestorLocacao`: `avancar(locacao)` → `GestorLocacao` → `Locacao`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode alugar um veículo que já está locado no período* entraria como um `alt` antes de criar o objeto `Locacao` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Locacao`.
- **Estados:** `aberta`, `em andamento`, `finalizada` e o caminho alternativo `cancelada`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `aberta` | `em andamento` | `avancar()` | não estar em `finalizada` nem em `cancelada` |
| `em andamento` | `finalizada` | `avancar()` | não estar em `finalizada` nem em `cancelada` |
| `aberta` | `cancelada` | `cancelar()` | ainda não ter chegado em `finalizada` |
| `em andamento` | `cancelada` | `cancelar()` | ainda não ter chegado em `finalizada` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `finalizada` ou de `cancelada` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Locacao.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Locacao` como atividades, com uma **decisão** (losango)
para a regra *não pode alugar um veículo que já está locado no período*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Locadora RodaLivre.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode alugar um veículo que já está locado no período* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorLocacao` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
