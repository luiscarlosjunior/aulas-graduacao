# 🎤 Entrevista Técnica — Agência MundoAfora (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Agência MundoAfora** (dono de uma agência de viagens). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação**, dicas de tradução para **Java**, o **Main** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Agência MundoAfora**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Agência MundoAfora**, a peça central é **Cliente**. Tem dois tipos: o
**ClienteComum** (o comum) e o **ClientePremium**, que **inclui traslado e seguro-viagem**. No fundo, os dois são um tipo de
**Cliente** — todos têm nome e documento e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `valorComExtras()` de cada um, pro
**ClienteComum** paga só o pacote; já pro **ClientePremium** inclui traslado e seguro no valor. A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o número de vagas e o valor não podem ser mexidos por fora (vagas nunca abaixo de zero). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode reservar mais vagas do que o pacote oferece**.
Por isso o estado de **Reserva** só anda por operações (pendente → confirmada; ou cancelada), nunca "na mão".

**Cliente:** Agora as ligações. Um pacote tem MUITAS reservas; cada reserva é de UM cliente para UM pacote.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Pacote ◆ ItemPacote**.
Cada item (voo, hotel, passeio) só existe dentro do pacote e some junto com ele. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Pacote ◇ Destino** é só um **agrupamento**.
Os destinos existem por conta própria; o pacote só os agrupa no roteiro. Se **Pacote** sumir, **Destino** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Cliente** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `valorComExtras()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O número de vagas e o valor não podem ser mexidos por fora (vagas nunca abaixo de zero)" | dado **privado** + operações → **encapsulamento** |
| "não pode reservar mais vagas do que o pacote oferece" | **invariante**: o estado muda só por operação que valida |
| "**Pacote ◆ ItemPacote** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Pacote ◇ Destino** (Destino existe sozinho)" | **agregação** (losango vazio ◇) |
| "um pacote tem MUITAS reservas; cada reserva é de UM cliente para UM pacote" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Cliente`).
2. **Herança** — `ClienteComum` e `ClientePremium` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Reserva`).
4. **Polimorfismo** — a operação `valorComExtras()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um pacote tem MUITAS reservas; cada reserva é de UM cliente para UM pacote.
   - **Agregação** (losango **vazio** `◇`): `Pacote` junta `Destinos` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Pacote` é feita de `ItemPacotes` que nascem e morrem com ela.

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
- [ ] A operação `valorComExtras()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Pacote`→`ItemPacote` é composição e `Pacote`→`Destino` é agregação.
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
    public abstract double valorComExtras();           // contrato: cada tipo responde do seu jeito
}

public class ClienteComum extends Cliente {
    public ClienteComum(String nome) { super(nome); }
    @Override public double valorComExtras() { return /* TODO: resposta do tipo comum */; }
}
// ClientePremium segue o mesmo molde, com o comportamento diferente.
```

**2) Encapsulamento + invariante + pré/pós-condição.** Atributo `private`, mudado só por operação que
**valida na entrada (pré-condição)** e **garante o estado no fim (pós-condição)**:

```java
public class Reserva {
    private double valor;                  // INVARIANTE: nunca pode ficar negativo
    private String estado = "pendente";

    public Reserva(double valor) {
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
public class Pacote {
    private final List<ItemPacote> itens = new ArrayList<>();   // as partes vivem aqui
    public void adicionarItemPacote(String descricao, double valor) {
        itens.add(new ItemPacote(descricao, valor));            // <-- o `new` é AQUI (nasce no todo)
    }
}
```

**4) Agregação (◇) — o grupo RECEBE algo que já existe.** Guarda **referências** a objetos criados
fora (que continuam existindo se o grupo sumir):

```java
public class Pacote {
    private final List<Destino> itens = new ArrayList<>();
    public void adicionarDestino(Destino item) { itens.add(item); }   // recebe pronto (NÃO faz new)
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

public class AppAgenciaMundoAfora {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo tipo base, respostas diferentes
        Cliente comum    = new ClienteComum("Ana Souza");
        Cliente especial = new ClientePremium("Bruno Lima");
        System.out.println("ClienteComum -> " + comum.valorComExtras());
        System.out.println("ClientePremium -> " + especial.valorComExtras());

        // 2) ENCAPSULAMENTO + ESTADO — a Reserva valida e só muda por operação
        Reserva t = new Reserva(150.00);
        System.out.println("estado inicial: " + t.getEstado());
        t.confirmar();
        System.out.println("apos confirmar: " + t.getEstado());

        // 3) COMPOSIÇÃO (◆) — a parte nasce DENTRO do todo
        Pacote todo = new Pacote("Exemplo");
        todo.adicionarItemPacote("exemplo", 50.00);

        // 4) AGREGAÇÃO (◇) — agrupa itens que JÁ existem
        Destino item = new Destino("Exemplo");
        Pacote grupo = new Pacote("Exemplo");
        grupo.adicionarDestino(item);

        // 5) INVARIANTE / REGRA INEGOCIÁVEL — a tentativa inválida deve ser recusada
        try {
            Reserva invalida = new Reserva(-10.00);      // fere o invariante: valor < 0
            System.out.println("NAO deveria criar: " + invalida.getEstado());
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (invariante): " + e.getMessage());
        }
        // Regra do cliente que o seu código precisa garantir:
        // não pode reservar mais vagas do que o pacote oferece
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
        -documento : String
        +valorComExtras() double*
    }
    class ClienteComum {
        +valorComExtras() double
    }
    class ClientePremium {
        +valorComExtras() double
    }
    class Reserva {
        -valor : double
        -estado : String
        +confirmar() void
        +cancelar() void
    }
    Cliente <|-- ClienteComum
    Cliente <|-- ClientePremium
    Cliente "1" --> "*" Reserva : faz
    Pacote "1" *-- "*" ItemPacote : contém
    Pacote "1" o-- "*" Destino : agrupa
```

**Onde está cada coisa:**
- **Abstração/Herança:** `Cliente` «abstract» → `ClienteComum` / `ClientePremium`.
- **Encapsulamento:** `Reserva` tem `-valor` e `-estado` privados; mudam só por `confirmar()`/`cancelar()`.
- **Polimorfismo:** `valorComExtras()` é abstrata na base e redefinida em cada tipo.
- **Associação `-->`:** um pacote tem MUITAS reservas; cada reserva é de UM cliente para UM pacote.
- **Agregação `◇`:** `Pacote` o-- `Destino` (existem sozinhos).
- **Composição `◆`:** `Pacote` *-- `ItemPacote` (nascem e morrem juntos).

> Variações são aceitáveis — o essencial é **os quatro pilares + os três relacionamentos com
> multiplicidade**, e **nenhum setter que fure um invariante**.

</details>

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
