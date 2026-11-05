# 💻 Código pronto — Transportadora EntregaCerta

> **O que é esta pasta.** Aqui está o sistema **Transportadora EntregaCerta** (dono de uma transportadora) **já implementado em
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

- **Quem pediu:** dono de uma transportadora.
- **Peça central:** `Encomenda`, com dois tipos — `EncomendaComum` (comum) e `EncomendaExpressa` (tem prazo menor e prioridade no transporte).
- **Transação principal:** `Entrega`, que passa pelas etapas **postada → em trânsito → saiu para entrega → entregue; ou devolvida**.
- **Ligações (descritas na entrevista):** Uma encomenda tem MUITOS eventos de rastreio; cada entrega é de UMA encomenda para UM destinatário.
- **Regra que não pode falhar (do cliente):** *não pode marcar como 'entregue' uma encomenda que nem saiu para entrega*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppTransportadoraEntregaCerta
```

Leia a saída **junto com** o arquivo `AppTransportadoraEntregaCerta.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Encomenda.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `EncomendaComum.java` | tipo comum | Herança + polimorfismo |
| `EncomendaExpressa.java` | tipo especial (tem prazo menor e prioridade no transporte) | Herança + polimorfismo |
| `Entrega.java` | a transação central; estados: postada → em trânsito → saiu para entrega → entregue; ou devolvida | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Encomenda |
| `EventoRastreio.java` | parte que nasce dentro de Encomenda | Composição ◆ |
| `Rota.java` | grupo da agregação de Encomenda | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorEntrega.java` | coordena abrir/avançar/marcarDevolvida | SRP + DIP |
| `AppTransportadoraEntregaCerta.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Encomenda` é `abstract`: não faz sentido criar "um Encomenda genérico". Ela guarda
o que é comum (codigo, peso) e obriga cada tipo a responder `prazoEntregaDias()`.
`EncomendaComum` e `EncomendaExpressa` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Encomenda`, mas cada uma responde
`prazoEntregaDias()` do seu jeito — `EncomendaComum`: prazo normal; `EncomendaExpressa`: prazo menor (prioridade). Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Entrega`:
- **invariante:** `valorFrete` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `marcarDevolvida()` recusa se `Entrega` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Entrega` guarda uma referência ao `Encomenda` ligado a ela (campo `encomenda`).

**Composição (◆) — Encomenda ◆ EventoRastreio.** Cada evento de rastreio pertence à encomenda e some junto se ela for removida. No código, quem faz o `new EventoRastreio(...)` é
o **próprio todo**, dentro de `adicionarEventoRastreio(...)` — e o construtor de `EventoRastreio` nem é público.

**Agregação (◇) — Rota ◇ Encomenda.** As encomendas existem por conta própria; a rota só as agrupa para o transporte. No código, `adicionarEncomenda(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorEntrega` segue o **SRP** (só coordena o ciclo de vida de cada `Entrega`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `entrega.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorEntrega` (injeção de dependência).
2. Cria um `EncomendaComum` e um `EncomendaExpressa` e pergunta `prazoEntregaDias()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Entrega` — ele nasce em `postada`.
4. Adiciona partes (`EventoRastreio`) — **composição**.
5. Agrupa um item `Encomenda` já existente em `Rota` — **agregação**.
6. Avança o registro de `Entrega` etapa por etapa até `entregue` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Entrega` e leva pelo caminho alternativo `devolvida`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Remetente**, **Operador**, **Entregador**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Encomenda
  - Abrir Entrega
  - Avançar etapa da Entrega
  - Devolvida Entrega
  - Adicionar EventoRastreio em Encomenda
  - Agrupar Encomenda em Rota
  - Consultar `prazoEntregaDias()` de um Encomenda
  - Listar os registros de Entrega
- **Dicas:**
  - "Abrir Entrega" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de EncomendaExpressa" é um bom `«extend»` de "Abrir Entrega": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Devolvida Entrega" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode marcar como 'entregue' uma encomenda que nem saiu para entrega*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Entrega`, adicionar uma parte `EventoRastreio` e levar o registro até `entregue`** — é o
  trecho dos passos 2, 3 e 5 do `AppTransportadoraEntregaCerta`.
- **Participantes (linhas de vida):** `AppTransportadoraEntregaCerta`, `GestorEntrega`, `Entrega`, `EventoRastreio`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppTransportadoraEntregaCerta` → `GestorEntrega`: `abrir(comum, 150.00)`
  2. `GestorEntrega` → `Entrega`: `new Entrega(...)` (criação)
  3. `GestorEntrega` → `Notificador`: `notificar(...)`
  4. `AppTransportadoraEntregaCerta` → `Encomenda`: `adicionarEventoRastreio(...)` → que cria um objeto `EventoRastreio`
  5. `AppTransportadoraEntregaCerta` → `GestorEntrega`: `avancar(entrega)` → `GestorEntrega` → `Entrega`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode marcar como 'entregue' uma encomenda que nem saiu para entrega* entraria como um `alt` antes de criar o objeto `Entrega` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Entrega`.
- **Estados:** `postada`, `em trânsito`, `saiu para entrega`, `entregue` e o caminho alternativo `devolvida`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `postada` | `em trânsito` | `avancar()` | não estar em `entregue` nem em `devolvida` |
| `em trânsito` | `saiu para entrega` | `avancar()` | não estar em `entregue` nem em `devolvida` |
| `saiu para entrega` | `entregue` | `avancar()` | não estar em `entregue` nem em `devolvida` |
| `postada` | `devolvida` | `marcarDevolvida()` | ainda não ter chegado em `entregue` |
| `em trânsito` | `devolvida` | `marcarDevolvida()` | ainda não ter chegado em `entregue` |
| `saiu para entrega` | `devolvida` | `marcarDevolvida()` | ainda não ter chegado em `entregue` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `entregue` ou de `devolvida` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Entrega.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Entrega` como atividades, com uma **decisão** (losango)
para a regra *não pode marcar como 'entregue' uma encomenda que nem saiu para entrega*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Transportadora EntregaCerta.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode marcar como 'entregue' uma encomenda que nem saiu para entrega* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorEntrega` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
