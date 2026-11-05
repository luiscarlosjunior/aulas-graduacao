# 💻 Código pronto — RH PontoCerto

> **O que é esta pasta.** Aqui está o sistema **RH PontoCerto** (analista de RH de uma empresa média) **já implementado em
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

- **Quem pediu:** analista de RH de uma empresa média.
- **Peça central:** `Colaborador`, com dois tipos — `ColaboradorCLT` (comum) e `ColaboradorEstagiario` (tem carga horária e regras de hora extra diferentes).
- **Transação principal:** `Ponto`, que passa pelas etapas **aberto → fechado; ou ajustado**.
- **Ligações (descritas na entrevista):** Um colaborador tem MUITOS registros de ponto e pertence a UM departamento.
- **Regra que não pode falhar (do cliente):** *não pode registrar saída antes da entrada no mesmo dia*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppRHPontoCerto
```

Leia a saída **junto com** o arquivo `AppRHPontoCerto.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Colaborador.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `ColaboradorCLT.java` | tipo comum | Herança + polimorfismo |
| `ColaboradorEstagiario.java` | tipo especial (tem carga horária e regras de hora extra diferentes) | Herança + polimorfismo |
| `Ponto.java` | a transação central; estados: aberto → fechado; ou ajustado | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Colaborador |
| `RegistroPonto.java` | parte que nasce dentro de Colaborador | Composição ◆ |
| `Departamento.java` | grupo da agregação de Colaborador | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorPonto.java` | coordena abrir/avançar/marcarAjustado | SRP + DIP |
| `AppRHPontoCerto.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Colaborador` é `abstract`: não faz sentido criar "um Colaborador genérico". Ela guarda
o que é comum (nome, matricula) e obriga cada tipo a responder `calcularAdicional()`.
`ColaboradorCLT` e `ColaboradorEstagiario` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Colaborador`, mas cada uma responde
`calcularAdicional()` do seu jeito — `ColaboradorCLT`: hora extra pela regra CLT; `ColaboradorEstagiario`: regra de estágio (carga reduzida). Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Ponto`:
- **invariante:** `horasTrabalhadas` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `marcarAjustado()` recusa se `Ponto` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Ponto` guarda uma referência ao `Colaborador` ligado a ela (campo `colaborador`).

**Composição (◆) — Colaborador ◆ RegistroPonto.** Cada marcação pertence ao colaborador e some junto se o cadastro for removido. No código, quem faz o `new RegistroPonto(...)` é
o **próprio todo**, dentro de `adicionarRegistroPonto(...)` — e o construtor de `RegistroPonto` nem é público.

**Agregação (◇) — Departamento ◇ Colaborador.** Os colaboradores existem por conta própria; o departamento só os agrupa. No código, `adicionarColaborador(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorPonto` segue o **SRP** (só coordena o ciclo de vida de cada `Ponto`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `ponto.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorPonto` (injeção de dependência).
2. Cria um `ColaboradorCLT` e um `ColaboradorEstagiario` e pergunta `calcularAdicional()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Ponto` — ele nasce em `aberto`.
4. Adiciona partes (`RegistroPonto`) — **composição**.
5. Agrupa um item `Colaborador` já existente em `Departamento` — **agregação**.
6. Avança o registro de `Ponto` etapa por etapa até `fechado` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Ponto` e leva pelo caminho alternativo `ajustado`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Colaborador**, **Analista de RH**, **Gestor da área**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Colaborador
  - Abrir Ponto
  - Avançar etapa da Ponto
  - Ajustado Ponto
  - Adicionar RegistroPonto em Colaborador
  - Agrupar Colaborador em Departamento
  - Consultar `calcularAdicional()` de um Colaborador
  - Listar os registros de Ponto
- **Dicas:**
  - "Abrir Ponto" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de ColaboradorEstagiario" é um bom `«extend»` de "Abrir Ponto": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Ajustado Ponto" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode registrar saída antes da entrada no mesmo dia*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Ponto`, adicionar uma parte `RegistroPonto` e levar o registro até `fechado`** — é o
  trecho dos passos 2, 3 e 5 do `AppRHPontoCerto`.
- **Participantes (linhas de vida):** `AppRHPontoCerto`, `GestorPonto`, `Ponto`, `RegistroPonto`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppRHPontoCerto` → `GestorPonto`: `abrir(comum, 150.00)`
  2. `GestorPonto` → `Ponto`: `new Ponto(...)` (criação)
  3. `GestorPonto` → `Notificador`: `notificar(...)`
  4. `AppRHPontoCerto` → `Colaborador`: `adicionarRegistroPonto(...)` → que cria um objeto `RegistroPonto`
  5. `AppRHPontoCerto` → `GestorPonto`: `avancar(ponto)` → `GestorPonto` → `Ponto`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode registrar saída antes da entrada no mesmo dia* entraria como um `alt` antes de criar o objeto `Ponto` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Ponto`.
- **Estados:** `aberto`, `fechado` e o caminho alternativo `ajustado`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `aberto` | `fechado` | `avancar()` | não estar em `fechado` nem em `ajustado` |
| `aberto` | `ajustado` | `marcarAjustado()` | ainda não ter chegado em `fechado` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `fechado` ou de `ajustado` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Ponto.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Ponto` como atividades, com uma **decisão** (losango)
para a regra *não pode registrar saída antes da entrada no mesmo dia*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema RH PontoCerto.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode registrar saída antes da entrada no mesmo dia* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorPonto` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
