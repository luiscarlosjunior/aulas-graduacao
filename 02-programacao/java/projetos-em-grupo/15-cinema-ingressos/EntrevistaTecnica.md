# 🎤 Entrevista Técnica — Cinema TelaGrande (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Cinema TelaGrande** (dono de um cinema). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação**, dicas de tradução para **Java**, o **Main** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Cinema TelaGrande**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Cinema TelaGrande**, a peça central é **Ingresso**. Tem dois tipos: o
**IngressoInteira** (o comum) e o **IngressoMeia**, que **custa metade e exige comprovação**. No fundo, os dois são um tipo de
**Ingresso** — todos têm assento e preço e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `calcularPreco()` de cada um, pro
**IngressoInteira** preço inteiro; já pro **IngressoMeia** metade do preço (com comprovação). A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o preço do ingresso não pode ser mexido por fora (nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode vender o mesmo assento duas vezes na mesma sessão**.
Por isso o estado de **Venda** só anda por operações (aberta → paga; ou cancelada), nunca "na mão".

**Cliente:** Agora as ligações. Uma sessão tem MUITOS assentos; cada ingresso reserva UM assento de UMA sessão.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Venda ◆ Ingresso**.
Cada ingresso pertence a uma venda e some junto se a venda for cancelada. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Sessao ◇ Assento** é só um **agrupamento**.
Os assentos existem na sala por conta própria; a sessão só os agrupa. Se **Sessao** sumir, **Assento** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Ingresso** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `calcularPreco()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O preço do ingresso não pode ser mexido por fora (nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode vender o mesmo assento duas vezes na mesma sessão" | **invariante**: o estado muda só por operação que valida |
| "**Venda ◆ Ingresso** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Sessao ◇ Assento** (Assento existe sozinho)" | **agregação** (losango vazio ◇) |
| "uma sessão tem MUITOS assentos; cada ingresso reserva UM assento de UMA sessão" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Ingresso`).
2. **Herança** — `IngressoInteira` e `IngressoMeia` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Venda`).
4. **Polimorfismo** — a operação `calcularPreco()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): uma sessão tem MUITOS assentos; cada ingresso reserva UM assento de UMA sessão.
   - **Agregação** (losango **vazio** `◇`): `Sessao` junta `Assentos` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Venda` é feita de `Ingressos` que nascem e morrem com ela.

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
- [ ] A operação `calcularPreco()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Venda`→`Ingresso` é composição e `Sessao`→`Assento` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.

---

## 🔧 Como traduzir o modelo para Java (dicas)

> São **dicas de tradução**, não a solução pronta — os `// TODO` são seus. O foco é você saber
> **como declarar** cada coisa; o preenchimento vem da sua modelagem.

**1) Classe abstrata + herança (abstração e polimorfismo).** A base **não** se instancia e declara a
operação que muda por tipo; cada subtipo usa `extends` e `@Override`:

```java
public abstract class Ingresso {
    private String nome;                    // encapsulado: getter público, SEM setter cego
    protected Ingresso(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    public abstract double calcularPreco();           // contrato: cada tipo responde do seu jeito
}

public class IngressoInteira extends Ingresso {
    public IngressoInteira(String nome) { super(nome); }
    @Override public double calcularPreco() { return /* TODO: resposta do tipo comum */; }
}
// IngressoMeia segue o mesmo molde, com o comportamento diferente.
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
    private final List<Ingresso> itens = new ArrayList<>();   // as partes vivem aqui
    public void adicionarIngresso(String descricao, double valor) {
        itens.add(new Ingresso(descricao, valor));            // <-- o `new` é AQUI (nasce no todo)
    }
}
```

**4) Agregação (◇) — o grupo RECEBE algo que já existe.** Guarda **referências** a objetos criados
fora (que continuam existindo se o grupo sumir):

```java
public class Sessao {
    private final List<Assento> itens = new ArrayList<>();
    public void adicionarAssento(Assento item) { itens.add(item); }   // recebe pronto (NÃO faz new)
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

public class AppCinemaTelaGrande {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo tipo base, respostas diferentes
        Ingresso comum    = new IngressoInteira("A12");
        Ingresso especial = new IngressoMeia("B07");
        System.out.println("IngressoInteira -> " + comum.calcularPreco());
        System.out.println("IngressoMeia -> " + especial.calcularPreco());

        // 2) ENCAPSULAMENTO + ESTADO — a Venda valida e só muda por operação
        Venda t = new Venda(150.00);
        System.out.println("estado inicial: " + t.getEstado());
        t.confirmar();
        System.out.println("apos confirmar: " + t.getEstado());

        // 3) COMPOSIÇÃO (◆) — a parte nasce DENTRO do todo
        t.adicionarIngresso("exemplo", 50.00);

        // 4) AGREGAÇÃO (◇) — agrupa itens que JÁ existem
        Assento item = new Assento("Exemplo");
        Sessao grupo = new Sessao("Exemplo");
        grupo.adicionarAssento(item);

        // 5) INVARIANTE / REGRA INEGOCIÁVEL — a tentativa inválida deve ser recusada
        try {
            Venda invalida = new Venda(-10.00);      // fere o invariante: valorTotal < 0
            System.out.println("NAO deveria criar: " + invalida.getEstado());
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (invariante): " + e.getMessage());
        }
        // Regra do cliente que o seu código precisa garantir:
        // não pode vender o mesmo assento duas vezes na mesma sessão
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
    class Ingresso {
        <<abstract>>
        -assento : String
        -preco : double
        +calcularPreco() double*
    }
    class IngressoInteira {
        +calcularPreco() double
    }
    class IngressoMeia {
        +calcularPreco() double
    }
    class Venda {
        -valorTotal : double
        -estado : String
        +confirmar() void
        +cancelar() void
    }
    Ingresso <|-- IngressoInteira
    Ingresso <|-- IngressoMeia
    Ingresso "*" --> "1" Sessao : para
    Venda "1" *-- "*" Ingresso : contém
    Sessao "1" o-- "*" Assento : agrupa
```

**Onde está cada coisa:**
- **Abstração/Herança:** `Ingresso` «abstract» → `IngressoInteira` / `IngressoMeia`.
- **Encapsulamento:** `Venda` tem `-valorTotal` e `-estado` privados; mudam só por `confirmar()`/`cancelar()`.
- **Polimorfismo:** `calcularPreco()` é abstrata na base e redefinida em cada tipo.
- **Associação `-->`:** uma sessão tem MUITOS assentos; cada ingresso reserva UM assento de UMA sessão.
- **Agregação `◇`:** `Sessao` o-- `Assento` (existem sozinhos).
- **Composição `◆`:** `Venda` *-- `Ingresso` (nascem e morrem juntos).

> Variações são aceitáveis — o essencial é **os quatro pilares + os três relacionamentos com
> multiplicidade**, e **nenhum setter que fure um invariante**.

</details>

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
