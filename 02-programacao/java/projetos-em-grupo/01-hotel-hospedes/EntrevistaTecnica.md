# 🎤 Entrevista Técnica — Pousada Bem-Estar (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Pousada Bem-Estar** (dono de uma pousada). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação**, dicas de tradução para **Java**, o **Main** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Pousada Bem-Estar**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Pousada Bem-Estar**, a peça central é **Hospede**. Tem dois tipos: o
**HospedeComum** (o comum) e o **HospedeVIP**, que **tem late check-out e isenção da taxa de serviço**. No fundo, os dois são um tipo de
**Hospede** — todos têm nome, documento e telefone e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `taxaDeServico()` de cada um, pro
**HospedeComum** paga a taxa de serviço cheia; já pro **HospedeVIP** é isento da taxa. A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o valor da diária não pode ser mexido de qualquer lugar (nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode existir reserva de um quarto já ocupado nas mesmas datas**.
Por isso o estado de **Reserva** só anda por operações (criada → confirmada → finalizada; ou cancelada), nunca "na mão".

**Cliente:** Agora as ligações. Um hóspede faz VÁRIAS reservas; cada reserva é de UM quarto.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Reserva ◆ Diaria**.
Cada diária pertence a uma única reserva e some junto se a reserva for cancelada. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Reserva ◇ Hospede** é só um **agrupamento**.
Os acompanhantes existem no cadastro por conta própria e continuam mesmo sem a reserva. Se **Reserva** sumir, **Hospede** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Hospede** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `taxaDeServico()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O valor da diária não pode ser mexido de qualquer lugar (nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode existir reserva de um quarto já ocupado nas mesmas datas" | **invariante**: o estado muda só por operação que valida |
| "**Reserva ◆ Diaria** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Reserva ◇ Hospede** (Hospede existe sozinho)" | **agregação** (losango vazio ◇) |
| "um hóspede faz VÁRIAS reservas; cada reserva é de UM quarto" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Hospede`).
2. **Herança** — `HospedeComum` e `HospedeVIP` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Reserva`).
4. **Polimorfismo** — a operação `taxaDeServico()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um hóspede faz VÁRIAS reservas; cada reserva é de UM quarto.
   - **Agregação** (losango **vazio** `◇`): `Reserva` junta `Hospedes` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Reserva` é feita de `Diarias` que nascem e morrem com ela.

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
- [ ] A operação `taxaDeServico()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Reserva`→`Diaria` é composição e `Reserva`→`Hospede` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.

---

## 🔧 Como traduzir o modelo para Java (dicas)

> São **dicas de tradução**, não a solução pronta — os `// TODO` são seus. O foco é você saber
> **como declarar** cada coisa; o preenchimento vem da sua modelagem.

**1) Classe abstrata + herança (abstração e polimorfismo).** A base **não** se instancia e declara a
operação que muda por tipo; cada subtipo usa `extends` e `@Override`:

```java
public abstract class Hospede {
    private String nome;                    // encapsulado: getter público, SEM setter cego
    protected Hospede(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    public abstract double taxaDeServico();           // contrato: cada tipo responde do seu jeito
}

public class HospedeComum extends Hospede {
    public HospedeComum(String nome) { super(nome); }
    @Override public double taxaDeServico() { return /* TODO: resposta do tipo comum */; }
}
// HospedeVIP segue o mesmo molde, com o comportamento diferente.
```

**2) Encapsulamento + invariante + pré/pós-condição.** Atributo `private`, mudado só por operação que
**valida na entrada (pré-condição)** e **garante o estado no fim (pós-condição)**:

```java
public class Reserva {
    private double valorDiaria;                  // INVARIANTE: nunca pode ficar negativo
    private String estado = "criada";

    public Reserva(double valorDiaria) {
        // PRÉ-CONDIÇÃO: rejeita valor inválido logo na entrada
        if (valorDiaria < 0) throw new IllegalArgumentException("valorDiaria não pode ser negativo");
        this.valorDiaria = valorDiaria;              // PÓS-CONDIÇÃO: nasce com o invariante válido
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
    private final List<Diaria> itens = new ArrayList<>();   // as partes vivem aqui
    public void adicionarDiaria(String descricao, double valor) {
        itens.add(new Diaria(descricao, valor));            // <-- o `new` é AQUI (nasce no todo)
    }
}
```

**4) Agregação (◇) — o grupo RECEBE algo que já existe.** Guarda **referências** a objetos criados
fora (que continuam existindo se o grupo sumir):

```java
public class Reserva {
    private final List<Hospede> itens = new ArrayList<>();
    public void adicionarHospede(Hospede item) { itens.add(item); }   // recebe pronto (NÃO faz new)
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

public class AppPousadaBemEstar {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo tipo base, respostas diferentes
        Hospede comum    = new HospedeComum("Ana Souza");
        Hospede especial = new HospedeVIP("Bruno Lima");
        System.out.println("HospedeComum -> " + comum.taxaDeServico());
        System.out.println("HospedeVIP -> " + especial.taxaDeServico());

        // 2) ENCAPSULAMENTO + ESTADO — a Reserva valida e só muda por operação
        Reserva t = new Reserva(150.00);
        System.out.println("estado inicial: " + t.getEstado());
        t.confirmar();
        System.out.println("apos confirmar: " + t.getEstado());

        // 3) COMPOSIÇÃO (◆) — a parte nasce DENTRO do todo
        t.adicionarDiaria("exemplo", 50.00);

        // 4) AGREGAÇÃO (◇) — agrupa itens que JÁ existem
        Hospede item = new HospedeComum("Exemplo");
        t.adicionarHospede(item);

        // 5) INVARIANTE / REGRA INEGOCIÁVEL — a tentativa inválida deve ser recusada
        try {
            Reserva invalida = new Reserva(-10.00);      // fere o invariante: valorDiaria < 0
            System.out.println("NAO deveria criar: " + invalida.getEstado());
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (invariante): " + e.getMessage());
        }
        // Regra do cliente que o seu código precisa garantir:
        // não pode existir reserva de um quarto já ocupado nas mesmas datas
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
    class Hospede {
        <<abstract>>
        -nome : String
        -documento : String
        +taxaDeServico() double*
    }
    class HospedeComum {
        +taxaDeServico() double
    }
    class HospedeVIP {
        +taxaDeServico() double
    }
    class Reserva {
        -valorDiaria : double
        -estado : String
        +confirmar() void
        +cancelar() void
    }
    Hospede <|-- HospedeComum
    Hospede <|-- HospedeVIP
    Hospede "1" --> "*" Reserva : faz
    Reserva "1" *-- "*" Diaria : contém
    Reserva "1" o-- "*" Hospede : agrupa
```

**Onde está cada coisa:**
- **Abstração/Herança:** `Hospede` «abstract» → `HospedeComum` / `HospedeVIP`.
- **Encapsulamento:** `Reserva` tem `-valorDiaria` e `-estado` privados; mudam só por `confirmar()`/`cancelar()`.
- **Polimorfismo:** `taxaDeServico()` é abstrata na base e redefinida em cada tipo.
- **Associação `-->`:** um hóspede faz VÁRIAS reservas; cada reserva é de UM quarto.
- **Agregação `◇`:** `Reserva` o-- `Hospede` (existem sozinhos).
- **Composição `◆`:** `Reserva` *-- `Diaria` (nascem e morrem juntos).

> Variações são aceitáveis — o essencial é **os quatro pilares + os três relacionamentos com
> multiplicidade**, e **nenhum setter que fure um invariante**.

</details>

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
