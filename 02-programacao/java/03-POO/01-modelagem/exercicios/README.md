# Exercícios — Modelagem em POO

> Estes exercícios treinam a habilidade mais importante: **ler um problema e recortar as
> classes**. Em modelagem, o processo importa mais que a resposta. Faça o esforço de
> desenhar (mesmo no papel) **antes** de espiar as dicas. Cada exercício tem:
> **Enunciado → Dicas → Gabarito comentado**.

Use sempre o **método dos 4 passos** do módulo:
1. Substantivos → classes/atributos  2. Características → atributos
3. Verbos → métodos  4. Ligações → relacionamentos

---

## 🟢 Exercício 1 — Estacionamento

**Enunciado:**
> *Um estacionamento controla a entrada e saída de veículos. Cada veículo tem uma placa e
> um horário de entrada. O estacionamento tem um número máximo de vagas e sabe registrar a
> entrada de um veículo (se houver vaga) e a saída. Ao registrar uma entrada sem vagas,
> deve avisar que está lotado.*

<details>
<summary>💡 Dicas (abra só depois de tentar)</summary>

- Substantivos importantes: **estacionamento**, **veículo** → 2 classes.
- `Veiculo` tem: `placa` (String), `horarioEntrada`.
- `Estacionamento` tem: `capacidade` (int) e uma **lista** de veículos dentro.
- Relacionamento: o `Estacionamento` **contém** `Veiculo`s enquanto estão estacionados →
  agregação (o veículo existe fora do estacionamento também).
- Comportamentos do estacionamento: `registrarEntrada(veiculo)`, `registrarSaida(placa)`,
  `estaLotado()`.
</details>

<details>
<summary>✅ Gabarito comentado</summary>

```java
import java.util.ArrayList;
import java.util.List;

public class Veiculo {
    private String placa;
    public Veiculo(String placa) { this.placa = placa; }
    public String getPlaca() { return placa; }
}

public class Estacionamento {
    private int capacidade;
    private List<Veiculo> vagasOcupadas = new ArrayList<>();

    public Estacionamento(int capacidade) { this.capacidade = capacidade; }

    public boolean estaLotado() {
        return vagasOcupadas.size() >= capacidade;
    }

    public void registrarEntrada(Veiculo v) {
        if (estaLotado()) {
            System.out.println("Estacionamento LOTADO! " + v.getPlaca() + " não entrou.");
        } else {
            vagasOcupadas.add(v);
            System.out.println(v.getPlaca() + " entrou. Vagas livres: "
                + (capacidade - vagasOcupadas.size()));
        }
    }

    public void registrarSaida(String placa) {
        vagasOcupadas.removeIf(v -> v.getPlaca().equals(placa));
        System.out.println(placa + " saiu.");
    }
}
```

**Por que assim?** A regra "tem vaga?" mora **dentro** do `Estacionamento` (`estaLotado`),
junto do dado `capacidade`. O veículo não sabe nada sobre lotação — cada classe cuida do
seu pedaço. Isso é encapsulamento nascendo naturalmente da boa modelagem.
</details>

---

## 🟡 Exercício 2 — Escola (herança + associação)

**Enunciado:**
> *Numa escola há **pessoas**: **alunos** e **professores**. Toda pessoa tem nome e idade.
> O aluno tem, além disso, uma matrícula e uma lista de notas, e sabe calcular sua média.
> O professor tem uma disciplina e um salário. Uma **turma** tem um professor responsável e
> vários alunos.*

<details>
<summary>💡 Dicas</summary>

- Teste "é um": *Aluno **é uma** Pessoa? Professor **é uma** Pessoa?* Sim → **herança**.
  Crie `Pessoa` (base) com `nome`, `idade`, e faça `Aluno extends Pessoa` e
  `Professor extends Pessoa`.
- `Aluno` acrescenta `matricula`, `List<Double> notas` e o método `calcularMedia()`.
- `Professor` acrescenta `disciplina`, `salario`.
- `Turma` **tem um** `Professor` (associação) e **tem vários** `Aluno`s (agregação — os
  alunos existem fora da turma).
</details>

<details>
<summary>✅ Gabarito comentado (esqueleto)</summary>

```java
public class Pessoa {
    protected String nome;
    protected int idade;
    public Pessoa(String nome, int idade) { this.nome = nome; this.idade = idade; }
    public String getNome() { return nome; }
}

public class Aluno extends Pessoa {
    private String matricula;
    private List<Double> notas = new ArrayList<>();
    public Aluno(String nome, int idade, String matricula) {
        super(nome, idade);              // reaproveita a base
        this.matricula = matricula;
    }
    public void adicionarNota(double n) { notas.add(n); }
    public double calcularMedia() {
        if (notas.isEmpty()) return 0;
        double soma = 0;
        for (double n : notas) soma += n;
        return soma / notas.size();
    }
}

public class Professor extends Pessoa {
    private String disciplina;
    private double salario;
    public Professor(String nome, int idade, String disciplina, double salario) {
        super(nome, idade);
        this.disciplina = disciplina;
        this.salario = salario;
    }
}

public class Turma {
    private Professor responsavel;         // associação
    private List<Aluno> alunos = new ArrayList<>();  // agregação
    public Turma(Professor responsavel) { this.responsavel = responsavel; }
    public void matricular(Aluno a) { alunos.add(a); }
}
```

**Ponto-chave:** `nome` e `idade` foram escritos **uma vez** na `Pessoa` e reaproveitados
por herança. Se amanhã surgir um `Funcionario` que também é `Pessoa`, é só estender.
Esse é o ganho da herança **quando o "é um" é verdadeiro**.
</details>

---

## 🔴 Exercício 3 — E-commerce (projeto integrador)

**Enunciado:**
> *Modele um mini e-commerce. Um **cliente** tem um **carrinho**. O carrinho contém
> **itens**, cada item referencia um **produto** (do catálogo) e uma quantidade. O cliente
> finaliza a compra, gerando um **pedido**, que tem um **status** (AGUARDANDO, PAGO,
> ENVIADO) e sabe calcular o valor total. O frete é calculado por uma **estratégia** que
> varia (frete grátis, frete fixo, frete por região).*

<details>
<summary>💡 Dicas</summary>

- Reaproveite as ideias do exemplo da pizzaria!
- **Composição:** `Carrinho` ◆ `ItemCarrinho`; `Pedido` ◆ itens.
- **Agregação:** `ItemCarrinho` ◇ `Produto` (produto vem do catálogo).
- **Associação:** `Pedido` → `Cliente`.
- **Status:** ótimo caso para um **`enum`** (`AGUARDANDO, PAGO, ENVIADO`).
- **Estratégia de frete:** crie uma classe/interface abstrata `CalculadoraFrete` com um
  método `calcular(pedido)`, e implemente `FreteGratis`, `FreteFixo`, `FretePorRegiao`.
  Isso é polimorfismo — e um empurrão para o padrão *Strategy* (módulo de design patterns).
- **Não modele o mundo inteiro.** Ignore pagamento, login, estoque real — foque no recorte
  do enunciado. Isso é praticar o "modele só o que o problema precisa".
</details>

<details>
<summary>✅ Gabarito — orientação (implemente você!)</summary>

Estrutura de classes sugerida (desenhe o diagrama antes):

| Classe | Responsabilidade | Relacionamentos |
|--------|------------------|-----------------|
| `Produto` | guardar nome e preço | — |
| `ItemCarrinho` | produto + quantidade; `subtotal()` | agrega `Produto` |
| `Carrinho` | lista de itens; `adicionar`, `total()` | compõe `ItemCarrinho` |
| `Cliente` | nome; `Carrinho` | tem um `Carrinho` |
| `Pedido` | itens, `StatusPedido`, `total()` | associa `Cliente`, compõe itens |
| `StatusPedido` (enum) | AGUARDANDO/PAGO/ENVIADO | — |
| `CalculadoraFrete` (abstrata) | `calcular(pedido)` | — |
| `FreteGratis`/`FreteFixo`/`FretePorRegiao` | regra de frete própria | herdam de `CalculadoraFrete` |

Comece pequeno: faça `Produto`, `ItemCarrinho` e `Carrinho.total()` rodando. Depois
acrescente `Pedido` e o `enum`. Por último, a estratégia de frete. **Modelagem é
iterativa** — cresça o modelo aos poucos, sempre com o código compilando.
</details>

---

## 🏆 Desafio final

Pegue **qualquer sistema que você usa** (um app de delivery, uma academia, uma locadora de
jogos) e, em 20 minutos:
1. Liste as **entidades** (substantivos).
2. Para cada uma, os **atributos** e **2–3 comportamentos**.
3. Desenhe os **relacionamentos** (use o teste "é um / tem um").
4. Faça um diagrama Mermaid `classDiagram` e cole no seu README.

Não existe resposta única — existe modelo que **serve ao problema**. Esse é o ofício.

---

[← Voltar ao módulo de Modelagem](../README.md)
