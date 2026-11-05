# 💻 Código pronto — Pousada Bem-Estar

> **O que é esta pasta.** Aqui está o sistema **Pousada Bem-Estar** (dono de uma pousada) **já implementado em
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

- **Quem pediu:** dono de uma pousada.
- **Peça central:** `Hospede`, com dois tipos — `HospedeComum` (comum) e `HospedeVIP` (tem late check-out e isenção da taxa de serviço).
- **Transação principal:** `Reserva`, que passa pelas etapas **criada → confirmada → finalizada; ou cancelada**.
- **Ligações (descritas na entrevista):** Um hóspede faz VÁRIAS reservas; cada reserva é de UM quarto.
- **Regra que não pode falhar (do cliente):** *não pode existir reserva de um quarto já ocupado nas mesmas datas*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppPousadaBemEstar
```

Leia a saída **junto com** o arquivo `AppPousadaBemEstar.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Hospede.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `HospedeComum.java` | tipo comum | Herança + polimorfismo |
| `HospedeVIP.java` | tipo especial (tem late check-out e isenção da taxa de serviço) | Herança + polimorfismo |
| `Reserva.java` | a transação central; estados: criada → confirmada → finalizada; ou cancelada | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Hospede |
| `Diaria.java` | parte que nasce dentro de Reserva | Composição ◆ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorReserva.java` | coordena abrir/avançar/cancelar | SRP + DIP |
| `AppPousadaBemEstar.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Hospede` é `abstract`: não faz sentido criar "um Hospede genérico". Ela guarda
o que é comum (nome, documento) e obriga cada tipo a responder `taxaDeServico()`.
`HospedeComum` e `HospedeVIP` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Hospede`, mas cada uma responde
`taxaDeServico()` do seu jeito — `HospedeComum`: paga a taxa de serviço cheia; `HospedeVIP`: é isento da taxa. Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Reserva`:
- **invariante:** `valorDiaria` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `cancelar()` recusa se `Reserva` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `Reserva` guarda uma referência ao `Hospede` ligado a ela (campo `hospede`).

**Composição (◆) — Reserva ◆ Diaria.** Cada diária pertence a uma única reserva e some junto se a reserva for cancelada. No código, quem faz o `new Diaria(...)` é
o **próprio todo**, dentro de `adicionarDiaria(...)` — e o construtor de `Diaria` nem é público.

**Agregação (◇) — Reserva ◇ Hospede.** Os acompanhantes existem no cadastro por conta própria e continuam mesmo sem a reserva. No código, `adicionarHospede(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorReserva` segue o **SRP** (só coordena o ciclo de vida de cada `Reserva`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `reserva.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorReserva` (injeção de dependência).
2. Cria um `HospedeComum` e um `HospedeVIP` e pergunta `taxaDeServico()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Reserva` — ele nasce em `criada`.
4. Adiciona partes (`Diaria`) — **composição**.
5. Agrupa um item `Hospede` já existente em `Reserva` — **agregação**.
6. Avança o registro de `Reserva` etapa por etapa até `finalizada` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Reserva` e leva pelo caminho alternativo `cancelada`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Hóspede**, **Recepcionista**, **Gerente da pousada**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Hospede
  - Abrir Reserva
  - Avançar etapa da Reserva
  - Cancelar Reserva
  - Adicionar Diaria em Reserva
  - Agrupar Hospede em Reserva
  - Consultar `taxaDeServico()` de um Hospede
  - Listar os registros de Reserva
- **Dicas:**
  - "Abrir Reserva" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de HospedeVIP" é um bom `«extend»` de "Abrir Reserva": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Cancelar Reserva" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode existir reserva de um quarto já ocupado nas mesmas datas*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Reserva`, adicionar uma parte `Diaria` e levar o registro até `finalizada`** — é o
  trecho dos passos 2, 3 e 5 do `AppPousadaBemEstar`.
- **Participantes (linhas de vida):** `AppPousadaBemEstar`, `GestorReserva`, `Reserva`, `Diaria`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppPousadaBemEstar` → `GestorReserva`: `abrir(comum, 150.00)`
  2. `GestorReserva` → `Reserva`: `new Reserva(...)` (criação)
  3. `GestorReserva` → `Notificador`: `notificar(...)`
  4. `AppPousadaBemEstar` → `Reserva`: `adicionarDiaria(...)` → que cria um objeto `Diaria`
  5. `AppPousadaBemEstar` → `GestorReserva`: `avancar(reserva)` → `GestorReserva` → `Reserva`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode existir reserva de um quarto já ocupado nas mesmas datas* entraria como um `alt` antes de criar o objeto `Reserva` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `Reserva`.
- **Estados:** `criada`, `confirmada`, `finalizada` e o caminho alternativo `cancelada`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `criada` | `confirmada` | `avancar()` | não estar em `finalizada` nem em `cancelada` |
| `confirmada` | `finalizada` | `avancar()` | não estar em `finalizada` nem em `cancelada` |
| `criada` | `cancelada` | `cancelar()` | ainda não ter chegado em `finalizada` |
| `confirmada` | `cancelada` | `cancelar()` | ainda não ter chegado em `finalizada` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `finalizada` ou de `cancelada` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `Reserva.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `Reserva` como atividades, com uma **decisão** (losango)
para a regra *não pode existir reserva de um quarto já ocupado nas mesmas datas*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Pousada Bem-Estar.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode existir reserva de um quarto já ocupado nas mesmas datas* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorReserva` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
