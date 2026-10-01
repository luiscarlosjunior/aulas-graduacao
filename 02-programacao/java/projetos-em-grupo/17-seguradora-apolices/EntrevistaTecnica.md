# 🎤 Entrevista Técnica — Seguradora Protege+ (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Seguradora Protege+** (gerente de uma seguradora). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação**, dicas de tradução para **Java**, o **Main** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Seguradora Protege+**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Seguradora Protege+**, a peça central é **Apolice**. Tem dois tipos: o
**ApoliceAuto** (o comum) e o **ApoliceVida**, que **tem regras de cobertura e cálculo de prêmio próprios**. No fundo, os dois são um tipo de
**Apolice** — todos têm número e valor segurado e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `calcularPremio()` de cada um, pro
**ApoliceAuto** prêmio pelas regras de auto; já pro **ApoliceVida** prêmio pelas regras de vida. A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o valor da indenização não pode ser mexido por fora (nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode abrir sinistro em uma apólice vencida ou cancelada**.
Por isso o estado de **Sinistro** só anda por operações (aberto → em análise → aprovado → pago; ou negado), nunca "na mão".

**Cliente:** Agora as ligações. Um cliente tem VÁRIAS apólices; cada apólice pode gerar VÁRIOS sinistros.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Apolice ◆ Cobertura**.
Cada cobertura só existe dentro da apólice e some junto se a apólice for cancelada. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Cliente ◇ Apolice** é só um **agrupamento**.
As apólices pertencem ao cliente, mas são apenas listadas/agrupadas por ele. Se **Cliente** sumir, **Apolice** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Apolice** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `calcularPremio()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O valor da indenização não pode ser mexido por fora (nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode abrir sinistro em uma apólice vencida ou cancelada" | **invariante**: o estado muda só por operação que valida |
| "**Apolice ◆ Cobertura** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Cliente ◇ Apolice** (Apolice existe sozinho)" | **agregação** (losango vazio ◇) |
| "um cliente tem VÁRIAS apólices; cada apólice pode gerar VÁRIOS sinistros" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Apolice`).
2. **Herança** — `ApoliceAuto` e `ApoliceVida` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Sinistro`).
4. **Polimorfismo** — a operação `calcularPremio()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um cliente tem VÁRIAS apólices; cada apólice pode gerar VÁRIOS sinistros.
   - **Agregação** (losango **vazio** `◇`): `Cliente` junta `Apolices` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Apolice` é feita de `Coberturas` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Sinistro` são protegidos.
- [ ] A operação `calcularPremio()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Apolice`→`Cobertura` é composição e `Cliente`→`Apolice` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.

---

## 🔧 Como traduzir o modelo para Java (dicas)

> São **dicas de tradução**, não a solução pronta — os `// TODO` são seus. O foco é você saber
> **como declarar** cada coisa; o preenchimento vem da sua modelagem.

**1) Classe abstrata + herança (abstração e polimorfismo).** A base **não** se instancia e declara a
operação que muda por tipo; cada subtipo usa `extends` e `@Override`:

```java
public abstract class Apolice {
    private String nome;                    // encapsulado: getter público, SEM setter cego
    protected Apolice(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    public abstract double calcularPremio();           // contrato: cada tipo responde do seu jeito
}

public class ApoliceAuto extends Apolice {
    public ApoliceAuto(String nome) { super(nome); }
    @Override public double calcularPremio() { return /* TODO: resposta do tipo comum */; }
}
// ApoliceVida segue o mesmo molde, com o comportamento diferente.
```

**2) Encapsulamento + invariante + pré/pós-condição.** Atributo `private`, mudado só por operação que
**valida na entrada (pré-condição)** e **garante o estado no fim (pós-condição)**:

```java
public class Sinistro {
    private double valorIndenizacao;                  // INVARIANTE: nunca pode ficar negativo
    private String estado = "aberto";

    public Sinistro(double valorIndenizacao) {
        // PRÉ-CONDIÇÃO: rejeita valor inválido logo na entrada
        if (valorIndenizacao < 0) throw new IllegalArgumentException("valorIndenizacao não pode ser negativo");
        this.valorIndenizacao = valorIndenizacao;              // PÓS-CONDIÇÃO: nasce com o invariante válido
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
public class Apolice {
    private final List<Cobertura> itens = new ArrayList<>();   // as partes vivem aqui
    public void adicionarCobertura(String descricao, double valor) {
        itens.add(new Cobertura(descricao, valor));            // <-- o `new` é AQUI (nasce no todo)
    }
}
```

**4) Agregação (◇) — o grupo RECEBE algo que já existe.** Guarda **referências** a objetos criados
fora (que continuam existindo se o grupo sumir):

```java
public class Cliente {
    private final List<Apolice> itens = new ArrayList<>();
    public void adicionarApolice(Apolice item) { itens.add(item); }   // recebe pronto (NÃO faz new)
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

public class AppSeguradoraProtege {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo tipo base, respostas diferentes
        Apolice comum    = new ApoliceAuto("AP-1001");
        Apolice especial = new ApoliceVida("AP-2002");
        System.out.println("ApoliceAuto -> " + comum.calcularPremio());
        System.out.println("ApoliceVida -> " + especial.calcularPremio());

        // 2) ENCAPSULAMENTO + ESTADO — a Sinistro valida e só muda por operação
        Sinistro t = new Sinistro(150.00);
        System.out.println("estado inicial: " + t.getEstado());
        t.confirmar();
        System.out.println("apos confirmar: " + t.getEstado());

        // 3) COMPOSIÇÃO (◆) — a parte nasce DENTRO do todo
        comum.adicionarCobertura("exemplo", 50.00);

        // 4) AGREGAÇÃO (◇) — agrupa itens que JÁ existem
        Apolice item = new ApoliceAuto("Exemplo");
        Cliente grupo = new Cliente("Exemplo");
        grupo.adicionarApolice(item);

        // 5) INVARIANTE / REGRA INEGOCIÁVEL — a tentativa inválida deve ser recusada
        try {
            Sinistro invalida = new Sinistro(-10.00);      // fere o invariante: valorIndenizacao < 0
            System.out.println("NAO deveria criar: " + invalida.getEstado());
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (invariante): " + e.getMessage());
        }
        // Regra do cliente que o seu código precisa garantir:
        // não pode abrir sinistro em uma apólice vencida ou cancelada
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
    class Apolice {
        <<abstract>>
        -numero : String
        -valorSegurado : double
        +calcularPremio() double*
    }
    class ApoliceAuto {
        +calcularPremio() double
    }
    class ApoliceVida {
        +calcularPremio() double
    }
    class Sinistro {
        -valorIndenizacao : double
        -estado : String
        +confirmar() void
        +cancelar() void
    }
    Apolice <|-- ApoliceAuto
    Apolice <|-- ApoliceVida
    Sinistro "*" --> "1" Apolice : refere
    Apolice "1" *-- "*" Cobertura : contém
    Cliente "1" o-- "*" Apolice : agrupa
```

**Onde está cada coisa:**
- **Abstração/Herança:** `Apolice` «abstract» → `ApoliceAuto` / `ApoliceVida`.
- **Encapsulamento:** `Sinistro` tem `-valorIndenizacao` e `-estado` privados; mudam só por `confirmar()`/`cancelar()`.
- **Polimorfismo:** `calcularPremio()` é abstrata na base e redefinida em cada tipo.
- **Associação `-->`:** um cliente tem VÁRIAS apólices; cada apólice pode gerar VÁRIOS sinistros.
- **Agregação `◇`:** `Cliente` o-- `Apolice` (existem sozinhos).
- **Composição `◆`:** `Apolice` *-- `Cobertura` (nascem e morrem juntos).

> Variações são aceitáveis — o essencial é **os quatro pilares + os três relacionamentos com
> multiplicidade**, e **nenhum setter que fure um invariante**.

</details>

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
