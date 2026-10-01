# 🎤 Entrevista Técnica — Farmácia SaúdeJá (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Farmácia SaúdeJá** (dono de uma farmácia de bairro). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação**, dicas de tradução para **Java**, o **Main** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Farmácia SaúdeJá**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Farmácia SaúdeJá**, a peça central é **Produto**. Tem dois tipos: o
**ProdutoComum** (o comum) e o **MedicamentoControlado**, que **só pode ser vendido com receita registrada**. No fundo, os dois são um tipo de
**Produto** — todos têm nome, preço e estoque e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `exigeReceita()` de cada um, pro
**ProdutoComum** não exige receita; já pro **MedicamentoControlado** exige receita. A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o estoque do produto não pode ser mexido por fora (nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode vender um item sem estoque suficiente**.
Por isso o estado de **Venda** só anda por operações (aberta → paga; ou cancelada), nunca "na mão".

**Cliente:** Agora as ligações. Uma venda é feita de VÁRIOS itens; cada item aponta para UM produto.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Venda ◆ ItemVenda**.
Cada item da venda só existe dentro dela e some junto se a venda for cancelada. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Categoria ◇ Produto** é só um **agrupamento**.
Os produtos existem por conta própria; a categoria só os agrupa. Se **Categoria** sumir, **Produto** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Produto** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `exigeReceita()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O estoque do produto não pode ser mexido por fora (nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode vender um item sem estoque suficiente" | **invariante**: o estado muda só por operação que valida |
| "**Venda ◆ ItemVenda** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Categoria ◇ Produto** (Produto existe sozinho)" | **agregação** (losango vazio ◇) |
| "uma venda é feita de VÁRIOS itens; cada item aponta para UM produto" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Produto`).
2. **Herança** — `ProdutoComum` e `MedicamentoControlado` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Venda`).
4. **Polimorfismo** — a operação `exigeReceita()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): uma venda é feita de VÁRIOS itens; cada item aponta para UM produto.
   - **Agregação** (losango **vazio** `◇`): `Categoria` junta `Produtos` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Venda` é feita de `ItemVendas` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Venda` são protegidos.
- [ ] A operação `exigeReceita()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Venda`→`ItemVenda` é composição e `Categoria`→`Produto` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.

---

## 🔧 Como traduzir o modelo para Java (dicas)

> São **dicas de tradução**, não a solução pronta — os `// TODO` são seus. O foco é você saber
> **como declarar** cada coisa; o preenchimento vem da sua modelagem.

**1) Classe abstrata + herança (abstração e polimorfismo).** A base **não** se instancia e declara a
operação que muda por tipo; cada subtipo usa `extends` e `@Override`:

```java
public abstract class Produto {
    private String nome;                    // encapsulado: getter público, SEM setter cego
    protected Produto(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    public abstract boolean exigeReceita();           // contrato: cada tipo responde do seu jeito
}

public class ProdutoComum extends Produto {
    public ProdutoComum(String nome) { super(nome); }
    @Override public boolean exigeReceita() { return /* TODO: resposta do tipo comum */; }
}
// MedicamentoControlado segue o mesmo molde, com o comportamento diferente.
```

**2) Encapsulamento + invariante + pré/pós-condição.** Atributo `private`, mudado só por operação que
**valida na entrada (pré-condição)** e **garante o estado no fim (pós-condição)**:

```java
public class Venda {
    private double valorTotal;                  // INVARIANTE: nunca pode ficar negativo
    private String estado = "aberta";

    public Venda(double valorTotal) {
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
public class Venda {
    private final List<ItemVenda> itens = new ArrayList<>();   // as partes vivem aqui
    public void adicionarItemVenda(String descricao, double valor) {
        itens.add(new ItemVenda(descricao, valor));            // <-- o `new` é AQUI (nasce no todo)
    }
}
```

**4) Agregação (◇) — o grupo RECEBE algo que já existe.** Guarda **referências** a objetos criados
fora (que continuam existindo se o grupo sumir):

```java
public class Categoria {
    private final List<Produto> itens = new ArrayList<>();
    public void adicionarProduto(Produto item) { itens.add(item); }   // recebe pronto (NÃO faz new)
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

public class AppFarmaciaSaudeJa {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo tipo base, respostas diferentes
        Produto comum    = new ProdutoComum("Dipirona 500mg");
        Produto especial = new MedicamentoControlado("Ritalina 10mg");
        System.out.println("ProdutoComum -> " + comum.exigeReceita());
        System.out.println("MedicamentoControlado -> " + especial.exigeReceita());

        // 2) ENCAPSULAMENTO + ESTADO — a Venda valida e só muda por operação
        Venda t = new Venda(150.00);
        System.out.println("estado inicial: " + t.getEstado());
        t.confirmar();
        System.out.println("apos confirmar: " + t.getEstado());

        // 3) COMPOSIÇÃO (◆) — a parte nasce DENTRO do todo
        t.adicionarItemVenda("exemplo", 50.00);

        // 4) AGREGAÇÃO (◇) — agrupa itens que JÁ existem
        Produto item = new ProdutoComum("Exemplo");
        Categoria grupo = new Categoria("Exemplo");
        grupo.adicionarProduto(item);

        // 5) INVARIANTE / REGRA INEGOCIÁVEL — a tentativa inválida deve ser recusada
        try {
            Venda invalida = new Venda(-10.00);      // fere o invariante: valorTotal < 0
            System.out.println("NAO deveria criar: " + invalida.getEstado());
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (invariante): " + e.getMessage());
        }
        // Regra do cliente que o seu código precisa garantir:
        // não pode vender um item sem estoque suficiente
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
    class Produto {
        <<abstract>>
        -nome : String
        -preco : double
        -estoque : int
        +exigeReceita() boolean*
    }
    class ProdutoComum {
        +exigeReceita() boolean
    }
    class MedicamentoControlado {
        +exigeReceita() boolean
    }
    class Venda {
        -valorTotal : double
        -estado : String
        +confirmar() void
        +cancelar() void
    }
    Produto <|-- ProdutoComum
    Produto <|-- MedicamentoControlado
    Venda "*" --> "1" Produto : refere
    Venda "1" *-- "*" ItemVenda : contém
    Categoria "1" o-- "*" Produto : agrupa
```

**Onde está cada coisa:**
- **Abstração/Herança:** `Produto` «abstract» → `ProdutoComum` / `MedicamentoControlado`.
- **Encapsulamento:** `Venda` tem `-valorTotal` e `-estado` privados; mudam só por `confirmar()`/`cancelar()`.
- **Polimorfismo:** `exigeReceita()` é abstrata na base e redefinida em cada tipo.
- **Associação `-->`:** uma venda é feita de VÁRIOS itens; cada item aponta para UM produto.
- **Agregação `◇`:** `Categoria` o-- `Produto` (existem sozinhos).
- **Composição `◆`:** `Venda` *-- `ItemVenda` (nascem e morrem juntos).

> Variações são aceitáveis — o essencial é **os quatro pilares + os três relacionamentos com
> multiplicidade**, e **nenhum setter que fure um invariante**.

</details>

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
