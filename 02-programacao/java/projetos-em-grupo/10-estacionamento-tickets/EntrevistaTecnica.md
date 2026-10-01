# 🎤 Entrevista Técnica — Estacionamento VagaFácil (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Estacionamento VagaFácil** (administrador de um estacionamento). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação**, dicas de tradução para **Java**, o **Main** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Estacionamento VagaFácil**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Estacionamento VagaFácil**, a peça central é **Cliente**. Tem dois tipos: o
**ClienteAvulso** (o comum) e o **ClienteMensalista**, que **tem vaga garantida e não paga por hora**. No fundo, os dois são um tipo de
**Cliente** — todos têm nome e placa do veículo e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `calcularValor()` de cada um, pro
**ClienteAvulso** paga por hora/fração; já pro **ClienteMensalista** não paga por hora (mensalidade fixa). A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o estado da vaga (livre/ocupada) só muda por operações; o valor nunca é negativo. Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode ocupar uma vaga que já está ocupada**.
Por isso o estado de **Ticket** só anda por operações (aberto → em uso → fechado → pago), nunca "na mão".

**Cliente:** Agora as ligações. Um cliente gera VÁRIOS tickets; cada ticket ocupa UMA vaga.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Ticket ◆ Cobranca**.
A cobrança nasce dentro do ticket e não existe fora dele. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Setor ◇ Vaga** é só um **agrupamento**.
As vagas existem no setor por conta própria; o setor só as agrupa. Se **Setor** sumir, **Vaga** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Cliente** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `calcularValor()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O estado da vaga (livre/ocupada) só muda por operações; o valor nunca é negativo" | dado **privado** + operações → **encapsulamento** |
| "não pode ocupar uma vaga que já está ocupada" | **invariante**: o estado muda só por operação que valida |
| "**Ticket ◆ Cobranca** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Setor ◇ Vaga** (Vaga existe sozinho)" | **agregação** (losango vazio ◇) |
| "um cliente gera VÁRIOS tickets; cada ticket ocupa UMA vaga" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Cliente`).
2. **Herança** — `ClienteAvulso` e `ClienteMensalista` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Ticket`).
4. **Polimorfismo** — a operação `calcularValor()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um cliente gera VÁRIOS tickets; cada ticket ocupa UMA vaga.
   - **Agregação** (losango **vazio** `◇`): `Setor` junta `Vagas` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Ticket` é feita de `Cobrancas` que nascem e morrem com ela.

## 🖊️ Lembrete de notação (UML)

| Elemento | Como desenhar |
|----------|---------------|
| Classe abstrata | nome em *itálico* + `«abstract»` (não se cria direto) |
| Operação abstrata | em *itálico* (cada subtipo redefine) |
| Visibilidade | `-` privado · `+` público · `#` protegido |
| Herança | linha com **triângulo vazio** `──▷` apontando para a base |
| Associação | linha (com seta), com **multiplicidade** nas pontas |
| Agregação | losango **vazio** `◇` no lado do "todo" |
| Composição | losango **cheio** `◆` no lado do "todo" |

Multiplicidades: `1` (exatamente um), `0..1` (zero ou um), `*` (muitos), `1..*` (um ou mais).

## ✅ Critério de "pronto"

- [ ] Há uma **classe base abstrata** e os dois tipos herdando dela.
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Ticket` são protegidos.
- [ ] A operação `calcularValor()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Ticket`→`Cobranca` é composição e `Setor`→`Vaga` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.

---

## 🔧 Como traduzir o modelo para Java (dicas)

> São **dicas de tradução**, não a solução pronta — os `// TODO` são seus. O foco é você saber
> **como declarar** cada coisa; o preenchimento vem da sua modelagem.

**1) Classe abstrata + herança (abstração e polimorfismo).** A base **não** se instancia e declara a
operação que muda por tipo; cada subtipo usa `extends` e `@Override`:

```java
public abstract class Cliente {
    private String nome;                    // encapsulado: getter público, SEM setter cego
    protected Cliente(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    public abstract double calcularValor();           // contrato: cada tipo responde do seu jeito
}

public class ClienteAvulso extends Cliente {
    public ClienteAvulso(String nome) { super(nome); }
    @Override public double calcularValor() { return /* TODO: resposta do tipo comum */; }
}
// ClienteMensalista segue o mesmo molde, com o comportamento diferente.
```

**2) Encapsulamento + invariante + pré/pós-condição.** Atributo `private`, mudado só por operação que
**valida na entrada (pré-condição)** e **garante o estado no fim (pós-condição)**:

```java
public class Ticket {
    private double valor;                  // INVARIANTE: nunca pode ficar negativo
    private String estado = "aberto";

    public Ticket(double valor) {
        // PRÉ-CONDIÇÃO: rejeita valor inválido logo na entrada
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo");
        this.valor = valor;              // PÓS-CONDIÇÃO: nasce com o invariante válido
    }
    public void confirmar() {
        // PRÉ: não pode avançar se já foi cancelada
        if (estado.equals("cancelada")) throw new IllegalStateException("já cancelada");
        estado = "confirmada";              // PÓS: estado mudou de forma controlada
    }
    public String getEstado() { return estado; }
}
```

**3) Composição (◆) — a parte NASCE dentro do todo.** O todo guarda a lista e **cria** a parte lá
dentro (ela não chega pronta de fora):

```java
public class Ticket {
    private final List<Cobranca> itens = new ArrayList<>();   // as partes vivem aqui
    public void adicionarCobranca(String descricao, double valor) {
        itens.add(new Cobranca(descricao, valor));            // <-- o `new` é AQUI (nasce no todo)
    }
}
```

**4) Agregação (◇) — o grupo RECEBE algo que já existe.** Guarda **referências** a objetos criados
fora (que continuam existindo se o grupo sumir):

```java
public class Setor {
    private final List<Vaga> itens = new ArrayList<>();
    public void adicionarVaga(Vaga item) { itens.add(item); }   // recebe pronto (NÃO faz new)
}
```

> 🔑 A diferença entre ◆ e ◇ no código é **quem faz o `new`**: na **composição**, o todo cria a
> parte; na **agregação**, o objeto já existe e é só **referenciado**.


## ▶️ O `Main` para você seguir (o fluxo completo)

> Este `Main` é o **roteiro** do sistema: ele **usa** as classes e operações que você precisa criar.
> Copie-o e implemente as classes com **estas assinaturas** até ele **compilar e rodar**.
> ⚠️ Ele **não compila** enquanto as classes não existirem — *esse é o exercício*. Não mude o `Main`
> para fugir do modelo; ajuste as **suas classes** para atender a este fluxo.

```java
import java.util.*;

public class AppEstacionamentoVagaFacil {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo tipo base, respostas diferentes
        Cliente comum    = new ClienteAvulso("Ana Souza");
        Cliente especial = new ClienteMensalista("Bruno Lima");
        System.out.println("ClienteAvulso -> " + comum.calcularValor());
        System.out.println("ClienteMensalista -> " + especial.calcularValor());

        // 2) ENCAPSULAMENTO + ESTADO — a Ticket valida e só muda por operação
        Ticket t = new Ticket(150.00);
        System.out.println("estado inicial: " + t.getEstado());
        t.confirmar();
        System.out.println("apos confirmar: " + t.getEstado());

        // 3) COMPOSIÇÃO (◆) — a parte nasce DENTRO do todo
        t.adicionarCobranca("exemplo", 50.00);

        // 4) AGREGAÇÃO (◇) — agrupa itens que JÁ existem
        Vaga item = new Vaga("Exemplo");
        Setor grupo = new Setor("Exemplo");
        grupo.adicionarVaga(item);

        // 5) INVARIANTE / REGRA INEGOCIÁVEL — a tentativa inválida deve ser recusada
        try {
            Ticket invalida = new Ticket(-10.00);      // fere o invariante: valor < 0
            System.out.println("NAO deveria criar: " + invalida.getEstado());
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (invariante): " + e.getMessage());
        }
        // Regra do cliente que o seu código precisa garantir:
        // não pode ocupar uma vaga que já está ocupada
    }
}
```


---

<details>
<summary>👩‍🏫 <b>Gabarito (para o professor — não abra antes de tentar!)</b></summary>

Um modelo possível que atende a todos os critérios:

```mermaid
classDiagram
    direction LR
    class Cliente {
        <<abstract>>
        -nome : String
        -placa : String
        +calcularValor() double*
    }
    class ClienteAvulso {
        +calcularValor() double
    }
    class ClienteMensalista {
        +calcularValor() double
    }
    class Ticket {
        -valor : double
        -estado : String
        +confirmar() void
        +cancelar() void
    }
    Cliente <|-- ClienteAvulso
    Cliente <|-- ClienteMensalista
    Cliente "1" --> "*" Ticket : faz
    Ticket "1" *-- "*" Cobranca : contém
    Setor "1" o-- "*" Vaga : agrupa
```

**Onde está cada coisa:**
- **Abstração/Herança:** `Cliente` «abstract» → `ClienteAvulso` / `ClienteMensalista`.
- **Encapsulamento:** `Ticket` tem `-valor` e `-estado` privados; mudam só por `confirmar()`/`cancelar()`.
- **Polimorfismo:** `calcularValor()` é abstrata na base e redefinida em cada tipo.
- **Associação `-->`:** um cliente gera VÁRIOS tickets; cada ticket ocupa UMA vaga.
- **Agregação `◇`:** `Setor` o-- `Vaga` (existem sozinhos).
- **Composição `◆`:** `Ticket` *-- `Cobranca` (nascem e morrem juntos).

> Variações são aceitáveis — o essencial é **os quatro pilares + os três relacionamentos com
> multiplicidade**, e **nenhum setter que fure um invariante**.

</details>

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
