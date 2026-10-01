# 🎤 Entrevista Técnica — Delivery JáChega (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Delivery JáChega** (dono de um app de delivery). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação**, dicas de tradução para **Java**, o **Main** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Delivery JáChega**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Delivery JáChega**, a peça central é **Cliente**. Tem dois tipos: o
**ClienteComum** (o comum) e o **ClienteAssinante**, que **tem entrega grátis acima de um valor**. No fundo, os dois são um tipo de
**Cliente** — todos têm nome, telefone e endereço e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `taxaEntrega()` de cada um, pro
**ClienteComum** paga a taxa de entrega; já pro **ClienteAssinante** tem entrega grátis (acima do valor). A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: a taxa e o total não podem ser mexidos por fora (nunca negativos). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode atribuir dois entregadores ao mesmo pedido**.
Por isso o estado de **Pedido** só anda por operações (recebido → preparando → a caminho → entregue; ou cancelado), nunca "na mão".

**Cliente:** Agora as ligações. Um pedido é de UM cliente e UM restaurante, e é levado por UM entregador.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Pedido ◆ ItemPedido**.
Cada item pertence a um pedido e some junto se o pedido for cancelado. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Restaurante ◇ Prato** é só um **agrupamento**.
Os pratos existem no cardápio do restaurante por conta própria; o pedido só os referencia. Se **Restaurante** sumir, **Prato** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Cliente** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `taxaEntrega()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "A taxa e o total não podem ser mexidos por fora (nunca negativos)" | dado **privado** + operações → **encapsulamento** |
| "não pode atribuir dois entregadores ao mesmo pedido" | **invariante**: o estado muda só por operação que valida |
| "**Pedido ◆ ItemPedido** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Restaurante ◇ Prato** (Prato existe sozinho)" | **agregação** (losango vazio ◇) |
| "um pedido é de UM cliente e UM restaurante, e é levado por UM entregador" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Cliente`).
2. **Herança** — `ClienteComum` e `ClienteAssinante` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Pedido`).
4. **Polimorfismo** — a operação `taxaEntrega()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um pedido é de UM cliente e UM restaurante, e é levado por UM entregador.
   - **Agregação** (losango **vazio** `◇`): `Restaurante` junta `Pratos` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Pedido` é feita de `ItemPedidos` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Pedido` são protegidos.
- [ ] A operação `taxaEntrega()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Pedido`→`ItemPedido` é composição e `Restaurante`→`Prato` é agregação.
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
    public abstract double taxaEntrega();           // contrato: cada tipo responde do seu jeito
}

public class ClienteComum extends Cliente {
    public ClienteComum(String nome) { super(nome); }
    @Override public double taxaEntrega() { return /* TODO: resposta do tipo comum */; }
}
// ClienteAssinante segue o mesmo molde, com o comportamento diferente.
```

**2) Encapsulamento + invariante + pré/pós-condição.** Atributo `private`, mudado só por operação que
**valida na entrada (pré-condição)** e **garante o estado no fim (pós-condição)**:

```java
public class Pedido {
    private double valorTotal;                  // INVARIANTE: nunca pode ficar negativo
    private String estado = "recebido";

    public Pedido(double valorTotal) {
        // PRÉ-CONDIÇÃO: rejeita valor inválido logo na entrada
        if (valorTotal < 0) throw new IllegalArgumentException("valorTotal não pode ser negativo");
        this.valorTotal = valorTotal;              // PÓS-CONDIÇÃO: nasce com o invariante válido
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
public class Pedido {
    private final List<ItemPedido> itens = new ArrayList<>();   // as partes vivem aqui
    public void adicionarItemPedido(String descricao, double valor) {
        itens.add(new ItemPedido(descricao, valor));            // <-- o `new` é AQUI (nasce no todo)
    }
}
```

**4) Agregação (◇) — o grupo RECEBE algo que já existe.** Guarda **referências** a objetos criados
fora (que continuam existindo se o grupo sumir):

```java
public class Restaurante {
    private final List<Prato> itens = new ArrayList<>();
    public void adicionarPrato(Prato item) { itens.add(item); }   // recebe pronto (NÃO faz new)
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

public class AppDeliveryJaChega {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo tipo base, respostas diferentes
        Cliente comum    = new ClienteComum("Ana Souza");
        Cliente especial = new ClienteAssinante("Bruno Lima");
        System.out.println("ClienteComum -> " + comum.taxaEntrega());
        System.out.println("ClienteAssinante -> " + especial.taxaEntrega());

        // 2) ENCAPSULAMENTO + ESTADO — a Pedido valida e só muda por operação
        Pedido t = new Pedido(150.00);
        System.out.println("estado inicial: " + t.getEstado());
        t.confirmar();
        System.out.println("apos confirmar: " + t.getEstado());

        // 3) COMPOSIÇÃO (◆) — a parte nasce DENTRO do todo
        t.adicionarItemPedido("exemplo", 50.00);

        // 4) AGREGAÇÃO (◇) — agrupa itens que JÁ existem
        Prato item = new Prato("Exemplo");
        Restaurante grupo = new Restaurante("Exemplo");
        grupo.adicionarPrato(item);

        // 5) INVARIANTE / REGRA INEGOCIÁVEL — a tentativa inválida deve ser recusada
        try {
            Pedido invalida = new Pedido(-10.00);      // fere o invariante: valorTotal < 0
            System.out.println("NAO deveria criar: " + invalida.getEstado());
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (invariante): " + e.getMessage());
        }
        // Regra do cliente que o seu código precisa garantir:
        // não pode atribuir dois entregadores ao mesmo pedido
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
        -endereco : String
        +taxaEntrega() double*
    }
    class ClienteComum {
        +taxaEntrega() double
    }
    class ClienteAssinante {
        +taxaEntrega() double
    }
    class Pedido {
        -valorTotal : double
        -estado : String
        +confirmar() void
        +cancelar() void
    }
    Cliente <|-- ClienteComum
    Cliente <|-- ClienteAssinante
    Cliente "1" --> "*" Pedido : faz
    Pedido "1" *-- "*" ItemPedido : contém
    Restaurante "1" o-- "*" Prato : agrupa
```

**Onde está cada coisa:**
- **Abstração/Herança:** `Cliente` «abstract» → `ClienteComum` / `ClienteAssinante`.
- **Encapsulamento:** `Pedido` tem `-valorTotal` e `-estado` privados; mudam só por `confirmar()`/`cancelar()`.
- **Polimorfismo:** `taxaEntrega()` é abstrata na base e redefinida em cada tipo.
- **Associação `-->`:** um pedido é de UM cliente e UM restaurante, e é levado por UM entregador.
- **Agregação `◇`:** `Restaurante` o-- `Prato` (existem sozinhos).
- **Composição `◆`:** `Pedido` *-- `ItemPedido` (nascem e morrem juntos).

> Variações são aceitáveis — o essencial é **os quatro pilares + os três relacionamentos com
> multiplicidade**, e **nenhum setter que fure um invariante**.

</details>

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
