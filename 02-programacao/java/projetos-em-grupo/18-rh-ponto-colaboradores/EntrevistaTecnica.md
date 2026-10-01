# 🎤 Entrevista Técnica — RH PontoCerto (desenhe no draw.io)

> **Como funciona este exercício.** Abaixo está uma **conversa** entre a equipe e o cliente do
> sistema **RH PontoCerto** (analista de RH de uma empresa média). **Leia com atenção** e, a partir do que as pessoas dizem,
> **desenhe um diagrama de classes** no [draw.io](https://app.diagrams.net) (ou app.diagrams.net).
> Não é para programar — é para **modelar**.
>
> 🎯 **Seu diagrama precisa mostrar os quatro pilares da OO e os três tipos de relacionamento**
> (associação, agregação e composição). No fim há a **lista do que entregar**, um **lembrete da
> notação**, dicas de tradução para **Java**, o **Main** e um **gabarito** (para o professor).

---

## 🗣️ A entrevista

**Gerente:** Pessoal, vamos organizar o sistema da **RH PontoCerto**. Vou trazer o cliente pra ele
explicar, do jeito dele, o que o sistema precisa ter. Anotem tudo e depois a gente transforma em
diagrama.

**Cliente:** No sistema **RH PontoCerto**, a peça central é **Colaborador**. Tem dois tipos: o
**ColaboradorCLT** (o comum) e o **ColaboradorEstagiario**, que **tem carga horária e regras de hora extra diferentes**. No fundo, os dois são um tipo de
**Colaborador** — todos têm nome e matrícula e compartilham a mesma base.

**Dev sênior:** Entendi. Então tem uma parte **igual pra todos** e uma parte **específica** de cada
tipo. Guardem isso — cheira a uma base comum com dois tipos que herdam dela.

**Cliente:** Isso. E o comportamento muda por tipo: olhando o `calcularAdicional()` de cada um, pro
**ColaboradorCLT** hora extra pela regra CLT; já pro **ColaboradorEstagiario** regra de estágio (carga reduzida). A **mesma** operação, **resposta
diferente conforme o tipo**.

**Analista de qualidade:** Um ponto sério: as horas não podem ser mexidas por fora (marcação inválida, como 30:00, é recusada). Ninguém pode mexer nesses dados **por fora** —
tem que ser por **operações controladas**. E tem uma regra que **NÃO pode falhar**: **não pode registrar saída antes da entrada no mesmo dia**.
Por isso o estado de **Ponto** só anda por operações (aberto → fechado; ou ajustado), nunca "na mão".

**Cliente:** Agora as ligações. Um colaborador tem MUITOS registros de ponto e pertence a UM departamento.

**Cliente:** Tem uma ligação de **parte-todo que nasce e morre junto**: **Colaborador ◆ RegistroPonto**.
Cada marcação pertence ao colaborador e some junto se o cadastro for removido. (pense em **composição** — losango cheio ◆)

**Dev sênior:** Não confunda com o outro caso: **Departamento ◇ Colaborador** é só um **agrupamento**.
Os colaboradores existem por conta própria; o departamento só os agrupa. Se **Departamento** sumir, **Colaborador** continua existindo — isso é **agregação**
(losango vazio ◇), bem diferente da composição.

**Gerente:** Fechou. Com isso dá pra desenhar o modelo. Mãos à obra.

---

## 🧭 Dicas de leitura (pistas escondidas na conversa)

| O que apareceu na conversa | Onde isso te leva |
|----------------------------|-------------------|
| "os dois são um tipo de **Colaborador** (mesma base)" | um tipo **geral** (base) e tipos **específicos** → **herança** |
| "a **mesma** operação `calcularAdicional()`, resposta muda por tipo" | operação **redefinida** em cada tipo → **polimorfismo** |
| "As horas não podem ser mexidas por fora (marcação inválida, como 30:00, é recusada)" | dado **privado** + operações → **encapsulamento** |
| "não pode registrar saída antes da entrada no mesmo dia" | **invariante**: o estado muda só por operação que valida |
| "**Colaborador ◆ RegistroPonto** (nascem e somem juntos)" | **composição** (losango cheio ◆) |
| "**Departamento ◇ Colaborador** (Colaborador existe sozinho)" | **agregação** (losango vazio ◇) |
| "um colaborador tem MUITOS registros de ponto e pertence a UM departamento" | **associação** com **multiplicidade** |

---

## 📋 O que você deve entregar (no draw.io)

Um **diagrama de classes** que contenha, de forma clara:

1. **Abstração** — uma classe **base** que não é criada diretamente (ex.: `Colaborador`).
2. **Herança** — `ColaboradorCLT` e `ColaboradorEstagiario` ligados à base pelo triângulo vazio `──▷`.
3. **Encapsulamento** — atributos sensíveis como **privados** (`-`) e **operações públicas** (`+`)
   que os controlam (proteger o valor e o estado da `Ponto`).
4. **Polimorfismo** — a operação `calcularAdicional()` declarada na base e **redefinida** em cada tipo.
5. **Os três relacionamentos**, cada um com sua **multiplicidade**:
   - **Associação** (linha/seta): um colaborador tem MUITOS registros de ponto e pertence a UM departamento.
   - **Agregação** (losango **vazio** `◇`): `Departamento` junta `Colaboradors` que existem sozinhos.
   - **Composição** (losango **cheio** `◆`): `Colaborador` é feita de `RegistroPontos` que nascem e morrem com ela.

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
- [ ] Nenhum atributo sensível está público; o valor e o estado da `Ponto` são protegidos.
- [ ] A operação `calcularAdicional()` aparece na base e é **redefinida** em cada tipo.
- [ ] Aparecem os **três** relacionamentos: **associação**, **agregação** (`◇`) e **composição** (`◆`).
- [ ] Você consegue **explicar** por que `Colaborador`→`RegistroPonto` é composição e `Departamento`→`Colaborador` é agregação.
- [ ] Todas as ligações têm **multiplicidade**.

---

## 🔧 Como traduzir o modelo para Java (dicas)

> São **dicas de tradução**, não a solução pronta — os `// TODO` são seus. O foco é você saber
> **como declarar** cada coisa; o preenchimento vem da sua modelagem.

**1) Classe abstrata + herança (abstração e polimorfismo).** A base **não** se instancia e declara a
operação que muda por tipo; cada subtipo usa `extends` e `@Override`:

```java
public abstract class Colaborador {
    private String nome;                    // encapsulado: getter público, SEM setter cego
    protected Colaborador(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    public abstract double calcularAdicional();           // contrato: cada tipo responde do seu jeito
}

public class ColaboradorCLT extends Colaborador {
    public ColaboradorCLT(String nome) { super(nome); }
    @Override public double calcularAdicional() { return /* TODO: resposta do tipo comum */; }
}
// ColaboradorEstagiario segue o mesmo molde, com o comportamento diferente.
```

**2) Encapsulamento + invariante + pré/pós-condição.** Atributo `private`, mudado só por operação que
**valida na entrada (pré-condição)** e **garante o estado no fim (pós-condição)**:

```java
public class Ponto {
    private double horasTrabalhadas;                  // INVARIANTE: nunca pode ficar negativo
    private String estado = "aberto";

    public Ponto(double horasTrabalhadas) {
        // PRÉ-CONDIÇÃO: rejeita valor inválido logo na entrada
        if (horasTrabalhadas < 0) throw new IllegalArgumentException("horasTrabalhadas não pode ser negativo");
        this.horasTrabalhadas = horasTrabalhadas;              // PÓS-CONDIÇÃO: nasce com o invariante válido
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
public class Colaborador {
    private final List<RegistroPonto> itens = new ArrayList<>();   // as partes vivem aqui
    public void adicionarRegistroPonto(String descricao, double valor) {
        itens.add(new RegistroPonto(descricao, valor));            // <-- o `new` é AQUI (nasce no todo)
    }
}
```

**4) Agregação (◇) — o grupo RECEBE algo que já existe.** Guarda **referências** a objetos criados
fora (que continuam existindo se o grupo sumir):

```java
public class Departamento {
    private final List<Colaborador> itens = new ArrayList<>();
    public void adicionarColaborador(Colaborador item) { itens.add(item); }   // recebe pronto (NÃO faz new)
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

public class AppRHPontoCerto {
    public static void main(String[] args) {
        // 1) HERANÇA + POLIMORFISMO — mesmo tipo base, respostas diferentes
        Colaborador comum    = new ColaboradorCLT("Ana Souza");
        Colaborador especial = new ColaboradorEstagiario("Bruno Lima");
        System.out.println("ColaboradorCLT -> " + comum.calcularAdicional());
        System.out.println("ColaboradorEstagiario -> " + especial.calcularAdicional());

        // 2) ENCAPSULAMENTO + ESTADO — a Ponto valida e só muda por operação
        Ponto t = new Ponto(150.00);
        System.out.println("estado inicial: " + t.getEstado());
        t.confirmar();
        System.out.println("apos confirmar: " + t.getEstado());

        // 3) COMPOSIÇÃO (◆) — a parte nasce DENTRO do todo
        comum.adicionarRegistroPonto("exemplo", 50.00);

        // 4) AGREGAÇÃO (◇) — agrupa itens que JÁ existem
        Colaborador item = new ColaboradorCLT("Exemplo");
        Departamento grupo = new Departamento("Exemplo");
        grupo.adicionarColaborador(item);

        // 5) INVARIANTE / REGRA INEGOCIÁVEL — a tentativa inválida deve ser recusada
        try {
            Ponto invalida = new Ponto(-10.00);      // fere o invariante: horasTrabalhadas < 0
            System.out.println("NAO deveria criar: " + invalida.getEstado());
        } catch (IllegalArgumentException e) {
            System.out.println("Recusado (invariante): " + e.getMessage());
        }
        // Regra do cliente que o seu código precisa garantir:
        // não pode registrar saída antes da entrada no mesmo dia
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
    class Colaborador {
        <<abstract>>
        -nome : String
        -matricula : String
        +calcularAdicional() double*
    }
    class ColaboradorCLT {
        +calcularAdicional() double
    }
    class ColaboradorEstagiario {
        +calcularAdicional() double
    }
    class Ponto {
        -horasTrabalhadas : double
        -estado : String
        +confirmar() void
        +cancelar() void
    }
    Colaborador <|-- ColaboradorCLT
    Colaborador <|-- ColaboradorEstagiario
    Colaborador "1" --> "*" Ponto : faz
    Colaborador "1" *-- "*" RegistroPonto : contém
    Departamento "1" o-- "*" Colaborador : agrupa
```

**Onde está cada coisa:**
- **Abstração/Herança:** `Colaborador` «abstract» → `ColaboradorCLT` / `ColaboradorEstagiario`.
- **Encapsulamento:** `Ponto` tem `-horasTrabalhadas` e `-estado` privados; mudam só por `confirmar()`/`cancelar()`.
- **Polimorfismo:** `calcularAdicional()` é abstrata na base e redefinida em cada tipo.
- **Associação `-->`:** um colaborador tem MUITOS registros de ponto e pertence a UM departamento.
- **Agregação `◇`:** `Departamento` o-- `Colaborador` (existem sozinhos).
- **Composição `◆`:** `Colaborador` *-- `RegistroPonto` (nascem e morrem juntos).

> Variações são aceitáveis — o essencial é **os quatro pilares + os três relacionamentos com
> multiplicidade**, e **nenhum setter que fure um invariante**.

</details>

---

[⬅️ Briefing do projeto](README.md) · [🗓️ Plano de evolução](PLANO-DE-EVOLUCAO.md)
