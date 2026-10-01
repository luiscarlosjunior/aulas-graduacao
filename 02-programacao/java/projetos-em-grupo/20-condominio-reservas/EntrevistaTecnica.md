# 🎤 Entrevista Técnica — Condomínio BemMorar (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Condomínio BemMorar** (síndico de um condomínio). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação**, dicas de tradução para **Java**, o **Main** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Condomínio BemMorar**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Condomínio BemMorar**, a peça central é **Morador**. Tem dois tipos: o
**MoradorAdimplente** (o comum) e o **MoradorInadimplente**, que **fica bloqueado de reservar até quitar**. No fundo, os dois são um tipo de
**Morador** — todos têm nome e documento e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `podeReservar()` de cada um, pro
**MoradorAdimplente** pode reservar normalmente; já pro **MoradorInadimplente** fica bloqueado até quitar. A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: a taxa da reserva não pode ser mexida por fora (nunca negativa). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode reservar uma área comum já reservada no mesmo período**.
Por isso o estado de **Reserva** só anda por operações (solicitada → confirmada; ou cancelada), nunca "na mão".

**Cliente:** Agora as ligações. Um morador pertence a UMA unidade e faz VÁRIAS reservas de áreas comuns.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Reserva ◆ Cobranca**.
A cobrança (taxa) nasce dentro da reserva e não existe sem ela. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Bloco ◇ Unidade** é só um **agrupamento**.
As unidades existem por conta própria e são apenas agrupadas por bloco. Se **Bloco** sumir, **Unidade** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Morador** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `podeReservar()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "A taxa da reserva não pode ser mexida por fora (nunca negativa)" | dado **privado** + operações → **encapsulamento** |
| "não pode reservar uma área comum já reservada no mesmo período" | **invariante**: o estado muda só por operação que valida |
| "**Reserva ◆ Cobranca** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Bloco ◇ Unidade** (Unidade existe sozinho)" | **agregação** (losango vazio ◇) |
| "um morador pertence a UMA unidade e faz VÁRIAS reservas de áreas comuns" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Morador`).
2. **Herança** — `MoradorAdimplente` e `MoradorInadimplente` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Reserva`).
4. **Polimorfismo** — a operação `podeReservar()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um morador pertence a UMA unidade e faz VÁRIAS reservas de áreas comuns.
   - **Agregação** (losango **vazio** `◇`): `Bloco` junta `Unidades` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Reserva` é feita de `Cobrancas` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Reserva` são protegidos.
- [ ] A operação `podeReservar()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Reserva`→`Cobranca` é composição e `Bloco`→`Unidade` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.

---

## 🔧 Como traduzir o modelo para Java (dicas)

> São **dicas de tradução**, não a solução pronta — os `// TODO` são seus. O foco é você saber
> **como declarar** cada coisa; o preenchimento vem da sua modelagem.

**1) Classe abstrata + herança (abstração e polimorfismo).** A base **não** se instancia e declara a
operação que muda por tipo; cada subtipo usa `extends` e `@Override`:

```java
public abstract class Morador {
    private String nome;                    // encapsulado: getter público, SEM setter cego
    protected Morador(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    public abstract boolean podeReservar();           // contrato: cada tipo responde do seu jeito
}

public class MoradorAdimplente extends Morador {
    public MoradorAdimplente(String nome) { super(nome); }
    @Override public boolean podeReservar() { return /* TODO: resposta do tipo comum */; }
}
// MoradorInadimplente segue o mesmo molde, com o comportamento diferente.
```

**2) Encapsulamento + invariante + pré/pós-condição.** Atributo `private`, mudado só por operação que
**valida na entrada (pré-condição)** e **garante o estado no fim (pós-condição)**:

```java
public class Reserva {
    private double valorTaxa;                  // INVARIANTE: nunca pode ficar negativo
    private String estado = "solicitada";

    public Reserva(double valorTaxa) {
        // PRÉ-CONDIÇÃO: rejeita valor inválido logo na entrada
        if (valorTaxa < 0) throw new IllegalArgumentException("valorTaxa não pode ser negativo");
        this.valorTaxa = valorTaxa;              // PÓS-CONDIÇÃO: nasce com o invariante válido
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
public class Reserva {
    private final List<Cobranca> itens = new ArrayList<>();   // as partes vivem aqui
    public void adicionarCobranca(String descricao, double valor) {
        itens.add(new Cobranca(descricao, valor));            // <-- o `new` é AQUI (nasce no todo)
    }
}
```

**4) Agregação (◇) — o grupo RECEBE algo que já existe.** Guarda **referências** a objetos criados
fora (que continuam existindo se o grupo sumir):

```java
public class Bloco {
    private final List<Unidade> itens = new ArrayList<>();
    public void adicionarUnidade(Unidade item) { itens.add(item); }   // recebe pronto (NÃO faz new)
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

public class AppCondominioBemMorar {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo tipo base, respostas diferentes
        Morador comum    = new MoradorAdimplente("Ana Souza");
        Morador especial = new MoradorInadimplente("Bruno Lima");
        System.out.println("MoradorAdimplente -> " + comum.podeReservar());
        System.out.println("MoradorInadimplente -> " + especial.podeReservar());

        // 2) ENCAPSULAMENTO + ESTADO — a Reserva valida e só muda por operação
        Reserva t = new Reserva(150.00);
        System.out.println("estado inicial: " + t.getEstado());
        t.confirmar();
        System.out.println("apos confirmar: " + t.getEstado());

        // 3) COMPOSIÇÃO (◆) — a parte nasce DENTRO do todo
        t.adicionarCobranca("exemplo", 50.00);

        // 4) AGREGAÇÃO (◇) — agrupa itens que JÁ existem
        Unidade item = new Unidade("Exemplo");
        Bloco grupo = new Bloco("Exemplo");
        grupo.adicionarUnidade(item);

        // 5) INVARIANTE / REGRA INEGOCIÁVEL — a tentativa inválida deve ser recusada
        try {
            Reserva invalida = new Reserva(-10.00);      // fere o invariante: valorTaxa < 0
            System.out.println("NAO deveria criar: " + invalida.getEstado());
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (invariante): " + e.getMessage());
        }
        // Regra do cliente que o seu código precisa garantir:
        // não pode reservar uma área comum já reservada no mesmo período
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
    class Morador {
        <<abstract>>
        -nome : String
        -documento : String
        +podeReservar() boolean*
    }
    class MoradorAdimplente {
        +podeReservar() boolean
    }
    class MoradorInadimplente {
        +podeReservar() boolean
    }
    class Reserva {
        -valorTaxa : double
        -estado : String
        +confirmar() void
        +cancelar() void
    }
    Morador <|-- MoradorAdimplente
    Morador <|-- MoradorInadimplente
    Morador "1" --> "*" Reserva : faz
    Reserva "1" *-- "*" Cobranca : contém
    Bloco "1" o-- "*" Unidade : agrupa
```

**Onde está cada coisa:**
- **Abstração/Herança:** `Morador` «abstract» → `MoradorAdimplente` / `MoradorInadimplente`.
- **Encapsulamento:** `Reserva` tem `-valorTaxa` e `-estado` privados; mudam só por `confirmar()`/`cancelar()`.
- **Polimorfismo:** `podeReservar()` é abstrata na base e redefinida em cada tipo.
- **Associação `-->`:** um morador pertence a UMA unidade e faz VÁRIAS reservas de áreas comuns.
- **Agregação `◇`:** `Bloco` o-- `Unidade` (existem sozinhos).
- **Composição `◆`:** `Reserva` *-- `Cobranca` (nascem e morrem juntos).

> Variações são aceitáveis — o essencial é **os quatro pilares + os três relacionamentos com
> multiplicidade**, e **nenhum setter que fure um invariante**.

</details>

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
