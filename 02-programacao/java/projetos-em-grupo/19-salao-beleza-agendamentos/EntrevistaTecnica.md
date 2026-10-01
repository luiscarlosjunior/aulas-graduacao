# 🎤 Entrevista Técnica — Salão BelaForma (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Salão BelaForma** (dono de um salão de beleza). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação**, dicas de tradução para **Java**, o **Main** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Salão BelaForma**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Salão BelaForma**, a peça central é **Cliente**. Tem dois tipos: o
**ClienteComum** (o comum) e o **ClienteFidelidade**, que **acumula pontos e ganha descontos**. No fundo, os dois são um tipo de
**Cliente** — todos têm nome e telefone e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `calcularDesconto()` de cada um, pro
**ClienteComum** não tem desconto; já pro **ClienteFidelidade** ganha desconto por pontos. A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o preço do serviço não pode ser mexido por fora (nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode agendar dois serviços no mesmo horário do mesmo profissional**.
Por isso o estado de **Agendamento** só anda por operações (marcado → atendido; ou faltou/cancelado), nunca "na mão".

**Cliente:** Agora as ligações. Um profissional tem MUITOS agendamentos; cada agendamento é de UMA cliente para UM serviço.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Agendamento ◆ ItemServico**.
Cada serviço do agendamento só existe dentro dele e some junto se for cancelado. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Profissional ◇ Servico** é só um **agrupamento**.
Os serviços existem no catálogo por conta própria; o profissional só os oferece. Se **Profissional** sumir, **Servico** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Cliente** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `calcularDesconto()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O preço do serviço não pode ser mexido por fora (nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode agendar dois serviços no mesmo horário do mesmo profissional" | **invariante**: o estado muda só por operação que valida |
| "**Agendamento ◆ ItemServico** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Profissional ◇ Servico** (Servico existe sozinho)" | **agregação** (losango vazio ◇) |
| "um profissional tem MUITOS agendamentos; cada agendamento é de UMA cliente para UM serviço" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Cliente`).
2. **Herança** — `ClienteComum` e `ClienteFidelidade` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Agendamento`).
4. **Polimorfismo** — a operação `calcularDesconto()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um profissional tem MUITOS agendamentos; cada agendamento é de UMA cliente para UM serviço.
   - **Agregação** (losango **vazio** `◇`): `Profissional` junta `Servicos` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Agendamento` é feita de `ItemServicos` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Agendamento` são protegidos.
- [ ] A operação `calcularDesconto()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Agendamento`→`ItemServico` é composição e `Profissional`→`Servico` é agregação.
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
    public abstract double calcularDesconto();           // contrato: cada tipo responde do seu jeito
}

public class ClienteComum extends Cliente {
    public ClienteComum(String nome) { super(nome); }
    @Override public double calcularDesconto() { return /* TODO: resposta do tipo comum */; }
}
// ClienteFidelidade segue o mesmo molde, com o comportamento diferente.
```

**2) Encapsulamento + invariante + pré/pós-condição.** Atributo `private`, mudado só por operação que
**valida na entrada (pré-condição)** e **garante o estado no fim (pós-condição)**:

```java
public class Agendamento {
    private double valorTotal;                  // INVARIANTE: nunca pode ficar negativo
    private String estado = "marcado";

    public Agendamento(double valorTotal) {
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
public class Agendamento {
    private final List<ItemServico> itens = new ArrayList<>();   // as partes vivem aqui
    public void adicionarItemServico(String descricao, double valor) {
        itens.add(new ItemServico(descricao, valor));            // <-- o `new` é AQUI (nasce no todo)
    }
}
```

**4) Agregação (◇) — o grupo RECEBE algo que já existe.** Guarda **referências** a objetos criados
fora (que continuam existindo se o grupo sumir):

```java
public class Profissional {
    private final List<Servico> itens = new ArrayList<>();
    public void adicionarServico(Servico item) { itens.add(item); }   // recebe pronto (NÃO faz new)
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

public class AppSalaoBelaForma {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo tipo base, respostas diferentes
        Cliente comum    = new ClienteComum("Ana Souza");
        Cliente especial = new ClienteFidelidade("Bruno Lima");
        System.out.println("ClienteComum -> " + comum.calcularDesconto());
        System.out.println("ClienteFidelidade -> " + especial.calcularDesconto());

        // 2) ENCAPSULAMENTO + ESTADO — a Agendamento valida e só muda por operação
        Agendamento t = new Agendamento(150.00);
        System.out.println("estado inicial: " + t.getEstado());
        t.confirmar();
        System.out.println("apos confirmar: " + t.getEstado());

        // 3) COMPOSIÇÃO (◆) — a parte nasce DENTRO do todo
        t.adicionarItemServico("exemplo", 50.00);

        // 4) AGREGAÇÃO (◇) — agrupa itens que JÁ existem
        Servico item = new Servico("Exemplo");
        Profissional grupo = new Profissional("Exemplo");
        grupo.adicionarServico(item);

        // 5) INVARIANTE / REGRA INEGOCIÁVEL — a tentativa inválida deve ser recusada
        try {
            Agendamento invalida = new Agendamento(-10.00);      // fere o invariante: valorTotal < 0
            System.out.println("NAO deveria criar: " + invalida.getEstado());
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (invariante): " + e.getMessage());
        }
        // Regra do cliente que o seu código precisa garantir:
        // não pode agendar dois serviços no mesmo horário do mesmo profissional
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
        -telefone : String
        +calcularDesconto() double*
    }
    class ClienteComum {
        +calcularDesconto() double
    }
    class ClienteFidelidade {
        +calcularDesconto() double
    }
    class Agendamento {
        -valorTotal : double
        -estado : String
        +confirmar() void
        +cancelar() void
    }
    Cliente <|-- ClienteComum
    Cliente <|-- ClienteFidelidade
    Cliente "1" --> "*" Agendamento : faz
    Agendamento "1" *-- "*" ItemServico : contém
    Profissional "1" o-- "*" Servico : agrupa
```

**Onde está cada coisa:**
- **Abstração/Herança:** `Cliente` «abstract» → `ClienteComum` / `ClienteFidelidade`.
- **Encapsulamento:** `Agendamento` tem `-valorTotal` e `-estado` privados; mudam só por `confirmar()`/`cancelar()`.
- **Polimorfismo:** `calcularDesconto()` é abstrata na base e redefinida em cada tipo.
- **Associação `-->`:** um profissional tem MUITOS agendamentos; cada agendamento é de UMA cliente para UM serviço.
- **Agregação `◇`:** `Profissional` o-- `Servico` (existem sozinhos).
- **Composição `◆`:** `Agendamento` *-- `ItemServico` (nascem e morrem juntos).

> Variações são aceitáveis — o essencial é **os quatro pilares + os três relacionamentos com
> multiplicidade**, e **nenhum setter que fure um invariante**.

</details>

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
