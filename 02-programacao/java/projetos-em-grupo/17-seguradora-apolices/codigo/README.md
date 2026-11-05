# 💻 Código pronto — Seguradora Protege+

> **O que é esta pasta.** Aqui está o sistema **Seguradora Protege+** (gerente de uma seguradora) **já implementado em
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

- **Quem pediu:** gerente de uma seguradora.
- **Peça central:** `Apolice`, com dois tipos — `ApoliceAuto` (comum) e `ApoliceVida` (tem regras de cobertura e cálculo de prêmio próprios).
- **Transação principal:** `Sinistro`, que passa pelas etapas **aberto → em análise → aprovado → pago; ou negado**.
- **Ligações (descritas na entrevista):** Um cliente tem VÁRIAS apólices; cada apólice pode gerar VÁRIOS sinistros.
- **Regra que não pode falhar (do cliente):** *não pode abrir sinistro em uma apólice vencida ou cancelada*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppSeguradoraProtege
```

Leia a saída **junto com** o arquivo `AppSeguradoraProtege.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Apolice.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `ApoliceAuto.java` | tipo comum | Herança + polimorfismo |
| `ApoliceVida.java` | tipo especial (tem regras de cobertura e cálculo de prêmio próprios) | Herança + polimorfismo |
| `Sinistro.java` | a transação central; estados: aberto → em análise → aprovado → pago; ou negado | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Apolice |
| `Cobertura.java` | parte que nasce dentro de Apolice | Composição ◆ |
| `Cliente.java` | grupo da agregação de Apolice | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorSinistro.java` | coordena abrir/avançar/marcarNegado | SRP + DIP |
| `AppSeguradoraProtege.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Apolice` é `abstract`: não faz sentido criar "um Apolice genérico". Ela guarda
o que é comum (numero, valorSegurado) e obriga cada tipo a responder `calcularPremio()`.
`ApoliceAuto` e `ApoliceVida` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Apolice`, mas cada uma responde
`calcularPremio()` do seu jeito — `ApoliceAuto`: prêmio pelas regras de auto; `ApoliceVida`: prêmio pelas regras de vida. Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Sinistro`:
- **invariante:** `valorIndenizacao` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `marcarNegado()` recusa se `Sinistro` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Sinistro` guarda uma referência ao `Apolice` ligado a ela (campo `apolice`).

**Composição (◆) — Apolice ◆ Cobertura.** Cada cobertura só existe dentro da apólice e some junto se a apólice for cancelada. No código, quem faz o `new Cobertura(...)` é
o **próprio todo**, dentro de `adicionarCobertura(...)` — e o construtor de `Cobertura` nem é público.

**Agregação (◇) — Cliente ◇ Apolice.** As apólices pertencem ao cliente, mas são apenas listadas/agrupadas por ele. No código, `adicionarApolice(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorSinistro` segue o **SRP** (só coordena o ciclo de vida de cada `Sinistro`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `sinistro.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorSinistro` (injeção de dependência).
2. Cria um `ApoliceAuto` e um `ApoliceVida` e pergunta `calcularPremio()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Sinistro` — ele nasce em `aberto`.
4. Adiciona partes (`Cobertura`) — **composição**.
5. Agrupa um item `Apolice` já existente em `Cliente` — **agregação**.
6. Avança o registro de `Sinistro` etapa por etapa até `pago` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Sinistro` e leva pelo caminho alternativo `negado`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Segurado**, **Atendente**, **Analista de sinistros**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Apolice
  - Abrir Sinistro
  - Avançar etapa da Sinistro
  - Negado Sinistro
  - Adicionar Cobertura em Apolice
  - Agrupar Apolice em Cliente
  - Consultar `calcularPremio()` de um Apolice
  - Listar os registros de Sinistro
- **Dicas:**
  - "Abrir Sinistro" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de ApoliceVida" é um bom `«extend»` de "Abrir Sinistro": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Negado Sinistro" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode abrir sinistro em uma apólice vencida ou cancelada*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Sinistro`, adicionar uma parte `Cobertura` e levar o registro até `pago`** — é o
  trecho dos passos 2, 3 e 5 do `AppSeguradoraProtege`.
- **Participantes (linhas de vida):** `AppSeguradoraProtege`, `GestorSinistro`, `Sinistro`, `Cobertura`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppSeguradoraProtege` → `GestorSinistro`: `abrir(comum, 150.00)`
  2. `GestorSinistro` → `Sinistro`: `new Sinistro(...)` (criação)
  3. `GestorSinistro` → `Notificador`: `notificar(...)`
  4. `AppSeguradoraProtege` → `Apolice`: `adicionarCobertura(...)` → que cria um objeto `Cobertura`
  5. `AppSeguradoraProtege` → `GestorSinistro`: `avancar(sinistro)` → `GestorSinistro` → `Sinistro`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode abrir sinistro em uma apólice vencida ou cancelada* entraria como um `alt` antes de criar o objeto `Sinistro` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Sinistro`.
- **Estados:** `aberto`, `em análise`, `aprovado`, `pago` e o caminho alternativo `negado`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `aberto` | `em análise` | `avancar()` | não estar em `pago` nem em `negado` |
| `em análise` | `aprovado` | `avancar()` | não estar em `pago` nem em `negado` |
| `aprovado` | `pago` | `avancar()` | não estar em `pago` nem em `negado` |
| `aberto` | `negado` | `marcarNegado()` | ainda não ter chegado em `pago` |
| `em análise` | `negado` | `marcarNegado()` | ainda não ter chegado em `pago` |
| `aprovado` | `negado` | `marcarNegado()` | ainda não ter chegado em `pago` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `pago` ou de `negado` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Sinistro.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Sinistro` como atividades, com uma **decisão** (losango)
para a regra *não pode abrir sinistro em uma apólice vencida ou cancelada*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Seguradora Protege+.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode abrir sinistro em uma apólice vencida ou cancelada* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorSinistro` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
