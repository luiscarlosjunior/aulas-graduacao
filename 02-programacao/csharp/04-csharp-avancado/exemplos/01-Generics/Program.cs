// ============================================================
// Generics em C# — .NET 8
// Reutilize código com segurança de tipos em tempo de compilação
// ============================================================

Console.WriteLine("=== MÉTODOS GENÉRICOS ===");

// Método genérico: T é o parâmetro de tipo
T[] CriarArray<T>(T valor, int tamanho)
{
    var arr = new T[tamanho];
    Array.Fill(arr, valor);
    return arr;
}

int[]    nums   = CriarArray(0, 5);
string[] nomes  = CriarArray("vazio", 3);
Console.WriteLine($"int[]:    [{string.Join(", ", nums)}]");
Console.WriteLine($"string[]: [{string.Join(", ", nomes)}]");

// Trocar dois valores — função clássica com generics
void Swap<T>(ref T a, ref T b)
{
    T temp = a;
    a = b;
    b = temp;
}

int x = 10, y = 20;
Swap(ref x, ref y);
Console.WriteLine($"Após Swap: x={x}, y={y}");

Console.WriteLine("\n=== CLASSE GENÉRICA — REPOSITÓRIO ===");

var repo = new Repositorio<string>();
repo.Adicionar("Alice");
repo.Adicionar("Bob");
repo.Adicionar("Carol");

Console.WriteLine($"Repositório tem {repo.Contar()} itens");
foreach (var item in repo.BuscarTodos())
    Console.WriteLine($"  - {item}");

Console.WriteLine("\n=== CONSTRAINTS (RESTRIÇÕES DE TIPO) ===");

// where T : struct     — T deve ser value type
// where T : class      — T deve ser reference type
// where T : new()      — T deve ter construtor sem parâmetros
// where T : BaseClass  — T deve herdar de BaseClass
// where T : IInterface — T deve implementar a interface

T Maximo<T>(T a, T b) where T : IComparable<T>
    => a.CompareTo(b) >= 0 ? a : b;

Console.WriteLine($"Máximo(10, 20): {Maximo(10, 20)}");
Console.WriteLine($"Máximo(3.14, 2.71): {Maximo(3.14, 2.71)}");
Console.WriteLine($"Máximo(\"abc\", \"xyz\"): {Maximo("abc", "xyz")}");

Console.WriteLine("\n=== CLASSE GENÉRICA PILHA<T> ===");

var pilhaInt    = new Pilha<int>();
var pilhaString = new Pilha<string>();

pilhaInt.Push(1); pilhaInt.Push(2); pilhaInt.Push(3);
Console.WriteLine($"Pilha int - Peek: {pilhaInt.Peek()}, Count: {pilhaInt.Count}");
Console.WriteLine($"Pop: {pilhaInt.Pop()}, {pilhaInt.Pop()}");

pilhaString.Push("primeiro");
pilhaString.Push("segundo");
Console.WriteLine($"Pilha string - Pop: {pilhaString.Pop()}");

Console.WriteLine("\n=== GENERIC PAIR<T1, T2> ===");

var par1 = new Par<string, int>("Idade", 30);
var par2 = new Par<string, decimal>("Salário", 5000m);

Console.WriteLine($"Par 1: {par1.Primeiro} = {par1.Segundo}");
Console.WriteLine($"Par 2: {par2.Primeiro} = {par2.Segundo}");

Console.WriteLine("\n=== COVARIÂNCIA E CONTRAVARIÂNCIA ===");
var gatos = new List<string> { "Miau", "Miaumiaumiau" };
IEnumerable<string> enumGatos = gatos; // covariância funciona aqui
Console.WriteLine($"Covariância IEnumerable: {string.Join(", ", enumGatos)}");

// ============================================================
// DECLARAÇÕES DE TIPOS — devem vir APÓS os top-level statements
// ============================================================

// Repositório genérico — padrão muito usado em projetos reais
public class Repositorio<T> where T : class
{
    private readonly List<T> _items = new();

    public void Adicionar(T item) => _items.Add(item);
    public IReadOnlyList<T> BuscarTodos() => _items.AsReadOnly();
    public T? BuscarPorIndice(int i) => i < _items.Count ? _items[i] : null;
    public int Contar() => _items.Count;
    public IEnumerable<T> Filtrar(Func<T, bool> predicado) => _items.Where(predicado);
}

// Implementação de uma Pilha genérica
public class Pilha<T>
{
    private readonly List<T> _dados = new();

    public void Push(T item) => _dados.Add(item);

    public T Pop()
    {
        if (_dados.Count == 0)
            throw new InvalidOperationException("Pilha vazia");
        var item = _dados[^1];
        _dados.RemoveAt(_dados.Count - 1);
        return item;
    }

    public T Peek() => _dados.Count > 0
        ? _dados[^1]
        : throw new InvalidOperationException("Pilha vazia");

    public int Count => _dados.Count;
    public bool Empty => _dados.Count == 0;
}

// Tipo genérico com múltiplos parâmetros
public record Par<T1, T2>(T1 Primeiro, T2 Segundo);
