# 03 — Orientação a Objetos em C#

> OOP é o paradigma central do C# e do mercado de trabalho. Aqui você aprende como modelar o mundo real em código, construindo sistemas manuteníveis e extensíveis.

---

## Classes e Objetos

Uma **classe** é um molde. Um **objeto** é uma instância desse molde.

```csharp
// Definindo uma classe
public class Produto
{
    // Propriedades (auto-properties)
    public int Id { get; set; }
    public string Nome { get; set; } = string.Empty;
    public decimal Preco { get; set; }

    // Construtor
    public Produto(int id, string nome, decimal preco)
    {
        Id    = id;
        Nome  = nome;
        Preco = preco;
    }

    // Método
    public string Descricao()
        => $"[{Id}] {Nome} - R$ {Preco:N2}";
}

// Criando objetos
var p1 = new Produto(1, "Notebook", 3500m);
var p2 = new Produto(2, "Mouse", 150m);

Console.WriteLine(p1.Descricao()); // [1] Notebook - R$ 3.500,00
```

---

## Propriedades

```csharp
public class Conta
{
    // Auto-property simples
    public string Titular { get; set; } = "";

    // Propriedade com validação
    private decimal _saldo;
    public decimal Saldo
    {
        get => _saldo;
        private set => _saldo = value >= 0 ? value : throw new ArgumentException("Saldo negativo");
    }

    // Computed property (sem backing field)
    public bool Positivo => _saldo > 0;

    // Init-only property (C# 9+) — só pode ser definida na inicialização
    public string Agencia { get; init; } = "";
}

// Object initializer com init
var conta = new Conta { Agencia = "0001", Titular = "João" };
```

---

## Construtores

```csharp
public class Pessoa
{
    public string Nome { get; }
    public int Idade { get; }
    public string? Email { get; set; }

    // Construtor principal
    public Pessoa(string nome, int idade)
    {
        Nome  = nome;
        Idade = idade;
    }

    // Construtor sobrecarregado — delega ao principal com ": this(...)"
    public Pessoa(string nome, int idade, string email) : this(nome, idade)
    {
        Email = email;
    }
}

// Construtores primários (C# 12) — mais conciso
public class Ponto(double x, double y)
{
    public double X { get; } = x;
    public double Y { get; } = y;
    public double Distancia => Math.Sqrt(X * X + Y * Y);
}
```

---

## Herança

```csharp
// Classe base
public abstract class Animal
{
    public string Nome { get; }

    protected Animal(string nome) { Nome = nome; }

    // Método virtual pode ser sobrescrito
    public virtual string EmitirSom() => "...";

    // Método abstrato DEVE ser sobrescrito
    public abstract string Descricao();
}

// Classe derivada
public class Cachorro : Animal
{
    public string Raca { get; }

    public Cachorro(string nome, string raca) : base(nome)
    {
        Raca = raca;
    }

    public override string EmitirSom() => "Au au!";

    public override string Descricao() => $"{Nome} ({Raca})";
}

// Polimorfismo
Animal animal = new Cachorro("Rex", "Pastor Alemão");
Console.WriteLine(animal.EmitirSom()); // "Au au!" — método do Cachorro
```

---

## Interfaces

```csharp
// Interface define um contrato
public interface IRepositorio<T>
{
    T? BuscarPorId(int id);
    IEnumerable<T> BuscarTodos();
    void Salvar(T entidade);
    void Deletar(int id);
}

// Implementação
public class ProdutoRepositorio : IRepositorio<Produto>
{
    private readonly List<Produto> _produtos = new();

    public Produto? BuscarPorId(int id) => _produtos.FirstOrDefault(p => p.Id == id);
    public IEnumerable<Produto> BuscarTodos() => _produtos;
    public void Salvar(Produto p) => _produtos.Add(p);
    public void Deletar(int id) => _produtos.RemoveAll(p => p.Id == id);
}
```

---

## Records (C# 9+)

Records são classes imutáveis por padrão, ideais para DTOs e value objects:

```csharp
// Record — imutável, igualdade por valor, ToString automático
public record Endereco(string Rua, string Cidade, string CEP);

var end1 = new Endereco("Av. Paulista", "São Paulo", "01310-100");
var end2 = new Endereco("Av. Paulista", "São Paulo", "01310-100");

Console.WriteLine(end1 == end2);    // true (igualdade por valor!)
Console.WriteLine(end1);            // Endereco { Rua = Av. Paulista, ... }

// "With" para criar cópia modificada
var end3 = end1 with { Cidade = "Campinas" };
```

---

## Enums

```csharp
public enum StatusPedido
{
    Pendente    = 0,
    Processando = 1,
    Enviado     = 2,
    Entregue    = 3,
    Cancelado   = 4
}

var status = StatusPedido.Enviado;
Console.WriteLine(status);          // "Enviado"
Console.WriteLine((int)status);     // 2

// Flags enum (combinação de valores)
[Flags]
public enum Permissoes
{
    Nenhuma = 0,
    Leitura = 1,
    Escrita = 2,
    Execucao = 4,
    Admin = Leitura | Escrita | Execucao // 7
}

var perm = Permissoes.Leitura | Permissoes.Escrita;
bool podeLer = perm.HasFlag(Permissoes.Leitura); // true
```

---

## Exemplo Prático — Sistema Bancário

O exemplo [`BancoExemplo/`](./BancoExemplo/) demonstra todos os conceitos acima em um contexto real:

- **`Conta.cs`** — classe base com encapsulamento (`Saldo` com setter protegido)
- **`ContaPoupanca.cs`** — herda de `Conta`, sobrescreve `Depositar()` e `ConsultarSaldo()`
- **`Cliente.cs`** — demonstra **composição** (Cliente *tem uma* ContaPoupanca)
- **`Program.cs`** — demonstra polimorfismo usando referência da classe base

```bash
cd BancoExemplo
dotnet run
```

---

## Exercícios

1. Crie uma hierarquia de `Forma` (Shape) com subclasses `Circulo`, `Retangulo` e `Triangulo`. Cada uma deve implementar `CalcularArea()` e `CalcularPerimetro()`
2. Implemente a interface `IComparable<T>` na classe `Produto` para ordenação por preço
3. Crie um `record` para representar um `Pedido` imutável com `Id`, `Cliente`, `Total` e `Status`
4. Implemente o padrão Repository genérico para uma lista de `Funcionario`
5. Adicione um enum `NivelCargo` (Júnior, Pleno, Sênior) e use-o na classe `Funcionario`

---

**Seção anterior:** [02 — Fundamentos do C#](../02-fundamentos-csharp/)  
**Próxima seção:** [04 — C# Avançado](../04-csharp-avancado/)
