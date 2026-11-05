# 💻 Código pronto — Oficina MotorOK

> **O que é esta pasta.** Aqui está o sistema **Oficina MotorOK** (dono de uma oficina mecânica) **já implementado em
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

- **Quem pediu:** dono de uma oficina mecânica.
- **Peça central:** `Cliente`, com dois tipos — `ClienteComum` (comum) e `ClienteFrota` (tem tabela de preço própria e prazo diferenciado).
- **Transação principal:** `OrdemServico`, que passa pelas etapas **aberta → aprovada → em execução → concluída → paga**.
- **Ligações (descritas na entrevista):** Um cliente tem VÁRIAS ordens de serviço; cada OS é de UM veículo.
- **Regra que não pode falhar (do cliente):** *não pode executar a OS antes de o orçamento ser aprovado*.

---

## 2. Como rodar

```bash
cd codigo
javac *.java
java AppOficinaMotorOK
```

Leia a saída **junto com** o arquivo `AppOficinaMotorOK.java`: cada bloco numerado (1 a 6) mostra um
conceito funcionando.

---

## 3. Mapa das classes

| Arquivo | Papel no sistema | Conceito que ele mostra |
|---------|------------------|-------------------------|
| `Cliente.java` | classe abstrata, base dos dois tipos | Abstração + encapsulamento |
| `ClienteComum.java` | tipo comum | Herança + polimorfismo |
| `ClienteFrota.java` | tipo especial (tem tabela de preço própria e prazo diferenciado) | Herança + polimorfismo |
| `OrdemServico.java` | a transação central; estados: aberta → aprovada → em execução → concluída → paga | Encapsulamento, invariante, pré/pós, máquina de estados, associação com Cliente |
| `ItemOS.java` | parte que nasce dentro de OrdemServico | Composição ◆ |
| `CatalogoServicos.java` | grupo da agregação de Servico | Agregação ◇ |
| `Servico.java` | item que é agrupado por CatalogoServicos | Agregação ◇ |
| `Notificador.java` | interface (contrato) de aviso | Interface + DIP |
| `NotificadorConsole.java` | implementação que escreve no console | Implementa a interface |
| `GestorOrdemServico.java` | coordena abrir/avançar | SRP + DIP |
| `AppOficinaMotorOK.java` | o `main`: o fluxo completo | Ponto de partida dos diagramas |

---

## 4. Como ler o código (explicação do professor)

**Abstração e herança.** `Cliente` é `abstract`: não faz sentido criar "um Cliente genérico". Ela guarda
o que é comum (nome, documento) e obriga cada tipo a responder `precoMaoDeObra()`.
`ClienteComum` e `ClienteFrota` usam `extends` e chamam `super(...)` para reaproveitar a validação.

**Polimorfismo.** No `main`, as duas variáveis são do tipo `Cliente`, mas cada uma responde
`precoMaoDeObra()` do seu jeito — `ClienteComum`: paga a tabela padrão; `ClienteFrota`: paga a tabela da frota (contrato). Ninguém precisa
de `if (tipo == ...)`.

**Encapsulamento, invariante, pré e pós-condição.** Todos os atributos são `private` e não há
*setters*. Em `OrdemServico`:
- **invariante:** `valorTotal` nunca é negativo e o estado é sempre uma das etapas válidas;
- **pré-condição:** o construtor recusa valor negativo; `avancar()` recusa se já está no fim;
- **pós-condição:** depois de `avancar()`, o estado andou **exatamente uma** etapa.

**Associação.** Cada `OrdemServico` guarda uma referência ao `Cliente` ligado a ela (campo `cliente`).

**Composição (◆) — OrdemServico ◆ ItemOS.** Cada item (serviço/peça) da OS só existe dentro dela e some junto se a OS for cancelada. No código, quem faz o `new ItemOS(...)` é
o **próprio todo**, dentro de `adicionarItemOS(...)` — e o construtor de `ItemOS` nem é público.

**Agregação (◇) — CatalogoServicos ◇ Servico.** Os serviços existem no catálogo por conta própria; a OS só os referencia. No código, `adicionarServico(...)` **recebe um objeto
pronto** (criado fora) e só guarda a referência.

**Interface + SOLID.** `Notificador` é um contrato; `NotificadorConsole` o implementa.
`GestorOrdemServico` segue o **SRP** (só coordena o ciclo de vida de cada `OrdemServico`) e o **DIP** (depende da
interface `Notificador`, recebida no construtor — trocar por e-mail não muda o Gestor).

**Mensagens.** Repare que o Gestor **não muda o estado na mão**: ele **pede** `ordemServico.avancar()` e o
próprio objeto decide se pode. Essas chamadas entre objetos são as **mensagens** do diagrama de sequência.

---

## 5. O fluxo do `main` (passo a passo)

1. Cria o `NotificadorConsole` e o `GestorOrdemServico` (injeção de dependência).
2. Cria um `ClienteComum` e um `ClienteFrota` e pergunta `precoMaoDeObra()` aos dois (polimorfismo).
3. Pede ao Gestor para **abrir** um registro de `OrdemServico` — ele nasce em `aberta`.
4. Adiciona partes (`ItemOS`) — **composição**.
5. Agrupa um item `Servico` já existente em `CatalogoServicos` — **agregação**.
6. Avança o registro de `OrdemServico` etapa por etapa até `paga` — **máquina de estados**.
7. Tenta ações proibidas (avançar depois do fim; valor negativo) — **pré-condições** recusam.


---

## 6. 🎯 Exercício em grupo — os diagramas de comportamento

> Façam **no draw.io**, um diagrama por página. Usem **o código como fonte da verdade**: nomes de
> classes, métodos e estados têm que bater com os arquivos `.java`.

### 6.1 Diagrama de Casos de Uso

**Pergunta que ele responde:** *quem usa o sistema e para fazer o quê?*

- **Atores sugeridos:** **Cliente**, **Atendente**, **Mecânico**.
- **Casos de uso sugeridos** (escolham e nomeiem com **verbo + objeto**):
  - Cadastrar Cliente
  - Abrir OrdemServico
  - Avançar etapa da OrdemServico
  - Adicionar ItemOS em OrdemServico
  - Agrupar Servico em CatalogoServicos
  - Consultar `precoMaoDeObra()` de um Cliente
  - Listar os registros de OrdemServico
- **Dicas:**
  - "Abrir OrdemServico" pode **incluir** (`«include»`) "Validar dados" (é o que o construtor faz sempre).
  - "Aplicar benefício de ClienteFrota" é um bom `«extend»` de "Abrir OrdemServico": só acontece **às vezes** (quando o participante é do tipo especial).
  - Cuidado: `«include»` é o que acontece **sempre**; `«extend»` é o que acontece **só às vezes**.
  - A regra do cliente (*não pode executar a OS antes de o orçamento ser aprovado*) costuma aparecer como um caso de uso incluído ou como uma nota.
  - Caso de uso **não** é tela nem método: é um **objetivo do ator**.

### 6.2 Diagrama de Sequência

**Pergunta que ele responde:** *em que ordem os objetos conversam para realizar um processo?*

- **Processo pedido:** **abrir um registro de `OrdemServico`, adicionar uma parte `ItemOS` e levar o registro até `paga`** — é o
  trecho dos passos 2, 3 e 5 do `AppOficinaMotorOK`.
- **Participantes (linhas de vida):** `AppOficinaMotorOK`, `GestorOrdemServico`, `OrdemServico`, `ItemOS`, `Notificador`.
- **Mensagens que devem aparecer** (sigam o código):
  1. `AppOficinaMotorOK` → `GestorOrdemServico`: `abrir(comum, 150.00)`
  2. `GestorOrdemServico` → `OrdemServico`: `new OrdemServico(...)` (criação)
  3. `GestorOrdemServico` → `Notificador`: `notificar(...)`
  4. `AppOficinaMotorOK` → `OrdemServico`: `adicionarItemOS(...)` → que cria um objeto `ItemOS`
  5. `AppOficinaMotorOK` → `GestorOrdemServico`: `avancar(ordemServico)` → `GestorOrdemServico` → `OrdemServico`: `avancar()` → `notificar(...)`
- **Dicas:**
  - Use um fragmento **`loop`** para o "avança até o fim".
  - Use um fragmento **`alt`** para mostrar o caso do valor negativo (o construtor lança exceção).
  - A regra *não pode executar a OS antes de o orçamento ser aprovado* entraria como um `alt` antes de criar o objeto `OrdemServico` (ver desafio extra).

### 6.3 Diagrama de Estados (máquina de estados)

**Pergunta que ele responde:** *por quais estados um objeto passa e o que o faz mudar?*

- **Classe pedida:** `OrdemServico`.
- **Estados:** `aberta`, `aprovada`, `em execução`, `concluída`, `paga`. Marquem o **estado inicial** (●) e o(s) **estado(s) final(is)** (◉).
- **Transições implementadas no código:**

| De | Para | Evento (método) | Condição de guarda |
|----|------|-----------------|--------------------|
| `aberta` | `aprovada` | `avancar()` | não estar em `paga` |
| `aprovada` | `em execução` | `avancar()` | não estar em `paga` |
| `em execução` | `concluída` | `avancar()` | não estar em `paga` |
| `concluída` | `paga` | `avancar()` | não estar em `paga` |

- **Dicas:**
  - O nome da seta é o **evento** (o método chamado); entre colchetes vai a **guarda**, ex.: `[valor >= 0]`.
  - Mostrem também o que **não** é permitido: a partir de `paga` não sai nenhuma seta de `avancar()`.
  - O array `ETAPAS` em `OrdemServico.java` é a lista oficial de estados.

### 6.4 (Bônus) Diagrama de Atividades
Desenhem o processo "do pedido ao fim" de `OrdemServico` como atividades, com uma **decisão** (losango)
para a regra *não pode executar a OS antes de o orçamento ser aprovado*.

---

## 7. O que entregar

- [ ] Os três diagramas (casos de uso, sequência, estados) no draw.io, cada um em uma página.
- [ ] Os nomes de classes, métodos e estados **iguais** aos do código.
- [ ] Uma frase por diagrama explicando **que pergunta ele responde** no sistema Oficina MotorOK.
- [ ] (Opcional) o diagrama de atividades.

## 8. 🚀 Desafio extra (código)

A regra do cliente — *não pode executar a OS antes de o orçamento ser aprovado* — está **descrita**, mas o código ainda não a verifica.
Implementem essa verificação no `GestorOrdemServico` (por exemplo, recusando a abertura com uma exceção)
**sem** quebrar o SRP: a regra é do processo, então mora no Gestor. Depois, atualizem o
diagrama de sequência com o `alt` correspondente.

---

[⬅️ Briefing do projeto](../README.md) · [🎤 Entrevista Técnica](../EntrevistaTecnica.md) · [🗓️ Plano de evolução](../PLANO-DE-EVOLUCAO.md)
