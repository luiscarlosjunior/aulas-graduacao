# 🎤 Entrevista Técnica — Clínica VidaPlena (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Clínica VidaPlena** (dono de uma clínica). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação**, dicas de tradução para **Java**, o **Main** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Clínica VidaPlena**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Clínica VidaPlena**, a peça central é **Paciente**. Tem dois tipos: o
**PacienteParticular** (o comum) e o **PacienteConvenio**, que **tem valor diferente e exige autorização do convênio**. No fundo, os dois são um tipo de
**Paciente** — todos têm nome, documento e telefone e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `valorConsulta()` de cada um, pro
**PacienteParticular** paga o valor particular; já pro **PacienteConvenio** paga o valor do convênio (com autorização). A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: o valor da consulta não pode ser mexido por fora (nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode marcar dois pacientes no mesmo horário do mesmo médico**.
Por isso o estado de **Consulta** só anda por operações (marcada → realizada; ou cancelada), nunca "na mão".

**Cliente:** Agora as ligações. Um médico tem MUITAS consultas; cada consulta é de UM paciente com UM médico.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Consulta ◆ Prescricao**.
A prescrição nasce dentro da consulta e não existe fora dela. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Convenio ◇ Paciente** é só um **agrupamento**.
Os pacientes existem por conta própria; o convênio só os agrupa. Se **Convenio** sumir, **Paciente** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Paciente** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `valorConsulta()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "O valor da consulta não pode ser mexido por fora (nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode marcar dois pacientes no mesmo horário do mesmo médico" | **invariante**: o estado muda só por operação que valida |
| "**Consulta ◆ Prescricao** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Convenio ◇ Paciente** (Paciente existe sozinho)" | **agregação** (losango vazio ◇) |
| "um médico tem MUITAS consultas; cada consulta é de UM paciente com UM médico" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Paciente`).
2. **Herança** — `PacienteParticular` e `PacienteConvenio` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Consulta`).
4. **Polimorfismo** — a operação `valorConsulta()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um médico tem MUITAS consultas; cada consulta é de UM paciente com UM médico.
   - **Agregação** (losango **vazio** `◇`): `Convenio` junta `Pacientes` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Consulta` é feita de `Prescricaos` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Consulta` são protegidos.
- [ ] A operação `valorConsulta()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Consulta`→`Prescricao` é composição e `Convenio`→`Paciente` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.

---

## 🔧 Como traduzir o modelo para Java (dicas)

> São **dicas de tradução**, não a solução pronta — os `// TODO` são seus. O foco é você saber
> **como declarar** cada coisa; o preenchimento vem da sua modelagem.

**1) Classe abstrata + herança (abstração e polimorfismo).** A base **não** se instancia e declara a
operação que muda por tipo; cada subtipo usa `extends` e `@Override`:

```java
public abstract class Paciente {
    private String nome;                    // encapsulado: getter público, SEM setter cego
    protected Paciente(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    public abstract double valorConsulta();           // contrato: cada tipo responde do seu jeito
}

public class PacienteParticular extends Paciente {
    public PacienteParticular(String nome) { super(nome); }
    @Override public double valorConsulta() { return /* TODO: resposta do tipo comum */; }
}
// PacienteConvenio segue o mesmo molde, com o comportamento diferente.
```

**2) Encapsulamento + invariante + pré/pós-condição.** Atributo `private`, mudado só por operação que
**valida na entrada (pré-condição)** e **garante o estado no fim (pós-condição)**:

```java
public class Consulta {
    private double valor;                  // INVARIANTE: nunca pode ficar negativo
    private String estado = "marcada";

    public Consulta(double valor) {
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
public class Consulta {
    private final List<Prescricao> itens = new ArrayList<>();   // as partes vivem aqui
    public void adicionarPrescricao(String descricao, double valor) {
        itens.add(new Prescricao(descricao, valor));            // <-- o `new` é AQUI (nasce no todo)
    }
}
```

**4) Agregação (◇) — o grupo RECEBE algo que já existe.** Guarda **referências** a objetos criados
fora (que continuam existindo se o grupo sumir):

```java
public class Convenio {
    private final List<Paciente> itens = new ArrayList<>();
    public void adicionarPaciente(Paciente item) { itens.add(item); }   // recebe pronto (NÃO faz new)
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

public class AppClinicaVidaPlena {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo tipo base, respostas diferentes
        Paciente comum    = new PacienteParticular("Ana Souza");
        Paciente especial = new PacienteConvenio("Bruno Lima");
        System.out.println("PacienteParticular -> " + comum.valorConsulta());
        System.out.println("PacienteConvenio -> " + especial.valorConsulta());

        // 2) ENCAPSULAMENTO + ESTADO — a Consulta valida e só muda por operação
        Consulta t = new Consulta(150.00);
        System.out.println("estado inicial: " + t.getEstado());
        t.confirmar();
        System.out.println("apos confirmar: " + t.getEstado());

        // 3) COMPOSIÇÃO (◆) — a parte nasce DENTRO do todo
        t.adicionarPrescricao("exemplo", 50.00);

        // 4) AGREGAÇÃO (◇) — agrupa itens que JÁ existem
        Paciente item = new PacienteParticular("Exemplo");
        Convenio grupo = new Convenio("Exemplo");
        grupo.adicionarPaciente(item);

        // 5) INVARIANTE / REGRA INEGOCIÁVEL — a tentativa inválida deve ser recusada
        try {
            Consulta invalida = new Consulta(-10.00);      // fere o invariante: valor < 0
            System.out.println("NAO deveria criar: " + invalida.getEstado());
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (invariante): " + e.getMessage());
        }
        // Regra do cliente que o seu código precisa garantir:
        // não pode marcar dois pacientes no mesmo horário do mesmo médico
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
    class Paciente {
        <<abstract>>
        -nome : String
        -documento : String
        +valorConsulta() double*
    }
    class PacienteParticular {
        +valorConsulta() double
    }
    class PacienteConvenio {
        +valorConsulta() double
    }
    class Consulta {
        -valor : double
        -estado : String
        +confirmar() void
        +cancelar() void
    }
    Paciente <|-- PacienteParticular
    Paciente <|-- PacienteConvenio
    Paciente "1" --> "*" Consulta : faz
    Consulta "1" *-- "*" Prescricao : contém
    Convenio "1" o-- "*" Paciente : agrupa
```

**Onde está cada coisa:**
- **Abstração/Herança:** `Paciente` «abstract» → `PacienteParticular` / `PacienteConvenio`.
- **Encapsulamento:** `Consulta` tem `-valor` e `-estado` privados; mudam só por `confirmar()`/`cancelar()`.
- **Polimorfismo:** `valorConsulta()` é abstrata na base e redefinida em cada tipo.
- **Associação `-->`:** um médico tem MUITAS consultas; cada consulta é de UM paciente com UM médico.
- **Agregação `◇`:** `Convenio` o-- `Paciente` (existem sozinhos).
- **Composição `◆`:** `Consulta` *-- `Prescricao` (nascem e morrem juntos).

> Variações são aceitáveis — o essencial é **os quatro pilares + os três relacionamentos com
> multiplicidade**, e **nenhum setter que fure um invariante**.

</details>

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
