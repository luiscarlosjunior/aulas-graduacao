# 🎤 Entrevista Técnica — Escola Saber+ (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **Escola Saber+** (coordenador de uma escola). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação**, dicas de tradução para **Java**, o **Main** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **Escola Saber+**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **Escola Saber+**, a peça central é **Aluno**. Tem dois tipos: o
**AlunoRegular** (o comum) e o **AlunoBolsista**, que **tem mensalidade reduzida ou isenta**. No fundo, os dois são um tipo de
**Aluno** — todos têm nome, documento e número de matrícula e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `mensalidade()` de cada um, pro
**AlunoRegular** paga a mensalidade cheia; já pro **AlunoBolsista** paga mensalidade reduzida (ou zero). A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: a nota e o valor não podem ser mexidos por fora (nota de 0 a 10; valor nunca negativo). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode matricular o aluno numa turma sem vaga**.
Por isso o estado de **Matricula** só anda por operações (ativa → trancada → concluída; ou cancelada), nunca "na mão".

**Cliente:** Agora as ligações. Uma turma tem MUITOS alunos; um aluno pode estar em VÁRIAS turmas (a matrícula liga os dois).

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Matricula ◆ Nota**.
Cada nota pertence a uma matrícula e some junto se a matrícula for cancelada. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Turma ◇ Aluno** é só um **agrupamento**.
Os alunos existem por conta própria; a turma só os agrupa. Se **Turma** sumir, **Aluno** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Aluno** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `mensalidade()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "A nota e o valor não podem ser mexidos por fora (nota de 0 a 10; valor nunca negativo)" | dado **privado** + operações → **encapsulamento** |
| "não pode matricular o aluno numa turma sem vaga" | **invariante**: o estado muda só por operação que valida |
| "**Matricula ◆ Nota** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Turma ◇ Aluno** (Aluno existe sozinho)" | **agregação** (losango vazio ◇) |
| "uma turma tem MUITOS alunos; um aluno pode estar em VÁRIAS turmas (a matrícula liga os dois)" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Aluno`).
2. **Herança** — `AlunoRegular` e `AlunoBolsista` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Matricula`).
4. **Polimorfismo** — a operação `mensalidade()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): uma turma tem MUITOS alunos; um aluno pode estar em VÁRIAS turmas (a matrícula liga os dois).
   - **Agregação** (losango **vazio** `◇`): `Turma` junta `Alunos` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Matricula` é feita de `Notas` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Matricula` são protegidos.
- [ ] A operação `mensalidade()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Matricula`→`Nota` é composição e `Turma`→`Aluno` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.

---

## 🔧 Como traduzir o modelo para Java (dicas)

> São **dicas de tradução**, não a solução pronta — os `// TODO` são seus. O foco é você saber
> **como declarar** cada coisa; o preenchimento vem da sua modelagem.

**1) Classe abstrata + herança (abstração e polimorfismo).** A base **não** se instancia e declara a
operação que muda por tipo; cada subtipo usa `extends` e `@Override`:

```java
public abstract class Aluno {
    private String nome;                    // encapsulado: getter público, SEM setter cego
    protected Aluno(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    public abstract double mensalidade();           // contrato: cada tipo responde do seu jeito
}

public class AlunoRegular extends Aluno {
    public AlunoRegular(String nome) { super(nome); }
    @Override public double mensalidade() { return /* TODO: resposta do tipo comum */; }
}
// AlunoBolsista segue o mesmo molde, com o comportamento diferente.
```

**2) Encapsulamento + invariante + pré/pós-condição.** Atributo `private`, mudado só por operação que
**valida na entrada (pré-condição)** e **garante o estado no fim (pós-condição)**:

```java
public class Matricula {
    private double valorMensalidade;                  // INVARIANTE: nunca pode ficar negativo
    private String estado = "ativa";

    public Matricula(double valorMensalidade) {
        // PRÉ-CONDIÇÃO: rejeita valor inválido logo na entrada
        if (valorMensalidade < 0) throw new IllegalArgumentException("valorMensalidade não pode ser negativo");
        this.valorMensalidade = valorMensalidade;              // PÓS-CONDIÇÃO: nasce com o invariante válido
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
public class Matricula {
    private final List<Nota> itens = new ArrayList<>();   // as partes vivem aqui
    public void adicionarNota(String descricao, double valor) {
        itens.add(new Nota(descricao, valor));            // <-- o `new` é AQUI (nasce no todo)
    }
}
```

**4) Agregação (◇) — o grupo RECEBE algo que já existe.** Guarda **referências** a objetos criados
fora (que continuam existindo se o grupo sumir):

```java
public class Turma {
    private final List<Aluno> itens = new ArrayList<>();
    public void adicionarAluno(Aluno item) { itens.add(item); }   // recebe pronto (NÃO faz new)
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

public class AppEscolaSaber {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo tipo base, respostas diferentes
        Aluno comum    = new AlunoRegular("Ana Souza");
        Aluno especial = new AlunoBolsista("Bruno Lima");
        System.out.println("AlunoRegular -> " + comum.mensalidade());
        System.out.println("AlunoBolsista -> " + especial.mensalidade());

        // 2) ENCAPSULAMENTO + ESTADO — a Matricula valida e só muda por operação
        Matricula t = new Matricula(150.00);
        System.out.println("estado inicial: " + t.getEstado());
        t.confirmar();
        System.out.println("apos confirmar: " + t.getEstado());

        // 3) COMPOSIÇÃO (◆) — a parte nasce DENTRO do todo
        t.adicionarNota("exemplo", 50.00);

        // 4) AGREGAÇÃO (◇) — agrupa itens que JÁ existem
        Aluno item = new AlunoRegular("Exemplo");
        Turma grupo = new Turma("Exemplo");
        grupo.adicionarAluno(item);

        // 5) INVARIANTE / REGRA INEGOCIÁVEL — a tentativa inválida deve ser recusada
        try {
            Matricula invalida = new Matricula(-10.00);      // fere o invariante: valorMensalidade < 0
            System.out.println("NAO deveria criar: " + invalida.getEstado());
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (invariante): " + e.getMessage());
        }
        // Regra do cliente que o seu código precisa garantir:
        // não pode matricular o aluno numa turma sem vaga
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
    class Aluno {
        <<abstract>>
        -nome : String
        -matriculaId : String
        +mensalidade() double*
    }
    class AlunoRegular {
        +mensalidade() double
    }
    class AlunoBolsista {
        +mensalidade() double
    }
    class Matricula {
        -valorMensalidade : double
        -estado : String
        +confirmar() void
        +cancelar() void
    }
    Aluno <|-- AlunoRegular
    Aluno <|-- AlunoBolsista
    Aluno "1" --> "*" Matricula : faz
    Matricula "1" *-- "*" Nota : contém
    Turma "1" o-- "*" Aluno : agrupa
```

**Onde está cada coisa:**
- **Abstração/Herança:** `Aluno` «abstract» → `AlunoRegular` / `AlunoBolsista`.
- **Encapsulamento:** `Matricula` tem `-valorMensalidade` e `-estado` privados; mudam só por `confirmar()`/`cancelar()`.
- **Polimorfismo:** `mensalidade()` é abstrata na base e redefinida em cada tipo.
- **Associação `-->`:** uma turma tem MUITOS alunos; um aluno pode estar em VÁRIAS turmas (a matrícula liga os dois).
- **Agregação `◇`:** `Turma` o-- `Aluno` (existem sozinhos).
- **Composição `◆`:** `Matricula` *-- `Nota` (nascem e morrem juntos).

> Variações são aceitáveis — o essencial é **os quatro pilares + os três relacionamentos com
> multiplicidade**, e **nenhum setter que fure um invariante**.

</details>

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
