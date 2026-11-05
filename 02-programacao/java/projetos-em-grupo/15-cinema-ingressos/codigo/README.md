# 💻 Código pronto — Cinema TelaGrande

> **O que é esta pasta.** Aqui está o sistema **Cinema TelaGrande** (dono de um cinema) **já implementado em
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

- **Quem pediu:** dono de um cinema.
- **Peça central:** `Ingresso`, com dois tipos — `IngressoInteira` (comum) e `IngressoMeia` (custa metade e exige comprovação).
- **Transação principal:** `Venda`, que passa pelas etapas **aberta → paga; ou cancelada**.
- **Ligações (descritas na entrevista):** Uma sessão tem MUITOS assentos; cada ingresso reserva UM assento de UMA sessão.
- **Regra que não pode falhar (do cliente):** *não pode vender o mesmo assento duas vezes na mesma sessão*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppCinemaTelaGrande
```

Leia a saída **junto com** o arquivo `AppCinemaTelaGrande.java`: cada bloco numerado (1 a 7) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Ingresso.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `IngressoInteira.java` | tipo comum | Herança + polimorfismo |
| `IngressoMeia.java` | tipo especial (custa metade e exige comprovação) | Herança + polimorfismo |
| `Venda.java` | a transação central; estados: aberta → paga; ou cancelada | Encapsulamento, invariante, pré/pós, máquina de estados |
| `Sessao.java` | grupo da agregação de Assento | Agregação ◇ |
| `Assento.java` | item que é agrupado por Sessao | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorVenda.java` | coordena abrir/avançar/cancelar | SRP + DIP |
| `AppCinemaTelaGrande.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Ingresso` é `abstract`: não faz sentido criar "um Ingresso genérico". Ela guarda
o que é comum (assento, preco) e obriga cada tipo a responder `calcularPreco()`.
`IngressoInteira` e `IngressoMeia` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Ingresso`, mas cada uma responde
`calcularPreco()` do seu jeito — `IngressoInteira`: preço inteiro; `IngressoMeia`: metade do preço (com comprovação). Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `Venda`:
- **invariante:** `valorTotal` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim, e `cancelar()` recusa se `Venda` já terminou;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Aqui o `Venda` é dono dos `Ingresso` por composição (veja abaixo).

**Composição (◆) — Venda ◆ Ingresso.** Cada ingresso pertence a uma venda e some junto se a venda for cancelada. No código, quem faz o `new IngressoInteira(...)` é
o **próprio todo**, dentro de `adicionarIngresso(...)`.

**Agregação (◇) — Sessao ◇ Assento.** Os assentos existem na sala por conta própria; a sessão só os agrupa. No código, `adicionarAssento(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorVenda` segue o **SRP** (só coordena o ciclo de vida de cada `Venda`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `venda.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorVenda` (injeção de dependência).
2. Cria um `IngressoInteira` e um `IngressoMeia` e pergunta `calcularPreco()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `Venda` — ele nasce em `aberta`.
4. Adiciona partes (`Ingresso`) — **composição**.
5. Agrupa um item `Assento` já existente em `Sessao` — **agregação**.
6. Avança o registro de `Venda` etapa por etapa até `paga` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.
8. Abre outro registro de `Venda` e leva pelo caminho alternativo `cancelada`.

---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Espectador**, **Bilheteiro**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Ingresso
  - Abrir Venda
  - Avançar etapa da Venda
  - Cancelar Venda
  - Adicionar Ingresso em Venda
  - Agrupar Assento em Sessao
  - Consultar `calcularPreco()` de um Ingresso
  - Listar os registros de Venda
- **Dicas:**
  - "Abrir Venda" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de IngressoMeia" é um bom `«extend»` de "Abrir Venda": só acontece **às vezes** (quando o participante é do tipo especial).
  - "Cancelar Venda" é um caso de uso **próprio** (outro objetivo do ator) — não é extensão de "Avançar etapa".
  - A regra do cliente (*não pode vender o mesmo assento duas vezes na mesma sessão*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `Venda`, adicionar uma parte `Ingresso` e levar o registro até `paga`** — é o
  trecho dos passos 2, 3 e 5 do `AppCinemaTelaGrande`.
- **Participantes (linhas de vida):** `AppCinemaTelaGrande`, `GestorVenda`, `Venda`, `IngressoInteira`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppCinemaTelaGrande` → `GestorVenda`: `abrir(150.00)`
  2. `GestorVenda` → `Venda`: `new Venda(...)` (criação)
  3. `GestorVenda` → `Notificador`: `notificar(...)`
  4. `AppCinemaTelaGrande` → `Venda`: `adicionarIngresso(...)` → que cria um objeto `IngressoInteira`
  5. `AppCinemaTelaGrande` → `GestorVenda`: `avancar(venda)` → `GestorVenda` → `Venda`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode vender o mesmo assento duas vezes na mesma sessão* entraria como um `alt` antes de criar o objeto `Venda` (ver desafio extra).

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
para a regra *não pode vender o mesmo assento duas vezes na mesma sessão*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Cinema TelaGrande.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode vender o mesmo assento duas vezes na mesma sessão* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorVenda` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
