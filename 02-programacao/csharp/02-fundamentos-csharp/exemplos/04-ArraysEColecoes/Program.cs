// ============================================================
// Arrays e Coleções em C# — .NET 8
// ============================================================

Console.WriteLine("=== ARRAYS UNIDIMENSIONAIS ===");
// Declaração e inicialização
int[] numeros = { 10, 20, 30, 40, 50 };
string[] nomes = new string[3]; // Array com 3 posições (string vazias)
nomes[0] = "Alice"; nomes[1] = "Bob"; nomes[2] = "Carol";

Console.WriteLine($"Array numeros: [{string.Join(", ", numeros)}]");
Console.WriteLine($"Tamanho: {numeros.Length}");
Console.WriteLine($"Primeiro: {numeros[0]}, Último: {numeros[^1]}"); // ^1 = último (C# 8+)

// Percorrendo array
Console.Write("foreach: ");
foreach (var n in numeros) Console.Write($"{n} ");
Console.WriteLine();

// Array com operações do System.Array
Array.Sort(numeros);
Console.WriteLine($"Após Sort: [{string.Join(", ", numeros)}]");
Array.Reverse(numeros);
Console.WriteLine($"Após Reverse: [{string.Join(", ", numeros)}]");
Console.WriteLine($"IndexOf(30): {Array.IndexOf(numeros, 30)}");

Console.WriteLine("\n=== RANGE E INDEX (C# 8+) ===");
int[] dados = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
int[] primeiros3 = dados[..3];       // índices 0,1,2
int[] ultimos3   = dados[^3..];      // últimos 3
int[] meio       = dados[2..7];      // índices 2 a 6

Console.WriteLine($"dados[..3]:   [{string.Join(", ", primeiros3)}]");
Console.WriteLine($"dados[^3..]:  [{string.Join(", ", ultimos3)}]");
Console.WriteLine($"dados[2..7]:  [{string.Join(", ", meio)}]");

Console.WriteLine("\n=== ARRAYS MULTIDIMENSIONAIS ===");
// Matriz 3x3
int[,] matriz = {
    { 1, 2, 3 },
    { 4, 5, 6 },
    { 7, 8, 9 }
};

Console.WriteLine("Matriz 3x3:");
for (int i = 0; i < matriz.GetLength(0); i++)
{
    for (int j = 0; j < matriz.GetLength(1); j++)
        Console.Write($"{matriz[i, j]} ");
    Console.WriteLine();
}

Console.WriteLine("\n=== LIST<T> — LISTA DINÂMICA ===");
// List<T> cresce automaticamente — muito mais usado que arrays em dia a dia
var cidades = new List<string> { "São Paulo", "Rio de Janeiro", "Belo Horizonte" };

cidades.Add("Curitiba");
cidades.AddRange(new[] { "Porto Alegre", "Salvador" });
cidades.Insert(0, "Fortaleza"); // insere na posição 0

Console.WriteLine($"Cidades ({cidades.Count} itens):");
cidades.ForEach(c => Console.WriteLine($"  - {c}"));

cidades.Remove("Salvador");
Console.WriteLine($"\nApós remover Salvador: {cidades.Count} cidades");

Console.WriteLine($"Contém Curitiba: {cidades.Contains("Curitiba")}");
Console.WriteLine($"Índice de SP: {cidades.IndexOf("São Paulo")}");

// Ordenar e filtrar
cidades.Sort();
Console.WriteLine($"Ordenado: [{string.Join(", ", cidades)}]");

var grandes = cidades.FindAll(c => c.Length > 10);
Console.WriteLine($"Cidades com nome > 10 chars: [{string.Join(", ", grandes)}]");

Console.WriteLine("\n=== DICTIONARY<K,V> — DICIONÁRIO ===");
// Dicionário: pares chave → valor
var capitais = new Dictionary<string, string>
{
    ["SP"] = "São Paulo",
    ["RJ"] = "Rio de Janeiro",
    ["MG"] = "Belo Horizonte"
};

capitais.Add("PR", "Curitiba");
capitais["SC"] = "Florianópolis"; // Adiciona ou atualiza

Console.WriteLine("Capitais:");
foreach (var (sigla, capital) in capitais) // Deconstruction
    Console.WriteLine($"  {sigla}: {capital}");

// Verificar e acessar com segurança
if (capitais.TryGetValue("SP", out string? spCapital))
    Console.WriteLine($"\nCapital de SP: {spCapital}");

capitais.ContainsKey("RN"); // false
Console.WriteLine($"Keys: {string.Join(", ", capitais.Keys)}");

Console.WriteLine("\n=== HASHSET<T> — CONJUNTO SEM DUPLICATAS ===");
var conjunto1 = new HashSet<int> { 1, 2, 3, 4, 5 };
var conjunto2 = new HashSet<int> { 3, 4, 5, 6, 7 };

conjunto1.Add(5); // Ignorado — já existe
conjunto1.Add(6);

Console.WriteLine($"conjunto1: [{string.Join(", ", conjunto1)}]");
Console.WriteLine($"conjunto2: [{string.Join(", ", conjunto2)}]");

// Operações de conjunto
var intersecao = new HashSet<int>(conjunto1);
intersecao.IntersectWith(conjunto2);
Console.WriteLine($"Interseção: [{string.Join(", ", intersecao)}]");

var uniao = new HashSet<int>(conjunto1);
uniao.UnionWith(conjunto2);
Console.WriteLine($"União: [{string.Join(", ", uniao)}]");

Console.WriteLine("\n=== QUEUE<T> — FILA (FIFO) ===");
var fila = new Queue<string>();
fila.Enqueue("Primeiro");
fila.Enqueue("Segundo");
fila.Enqueue("Terceiro");

Console.WriteLine($"Fila: {fila.Count} itens");
Console.WriteLine($"Peek (sem remover): {fila.Peek()}");
Console.WriteLine($"Dequeue: {fila.Dequeue()}");
Console.WriteLine($"Dequeue: {fila.Dequeue()}");
Console.WriteLine($"Restante: {fila.Count} item");

Console.WriteLine("\n=== STACK<T> — PILHA (LIFO) ===");
var pilha = new Stack<int>();
pilha.Push(1);
pilha.Push(2);
pilha.Push(3);

Console.WriteLine($"Pilha: {pilha.Count} itens");
Console.WriteLine($"Peek: {pilha.Peek()}");
Console.WriteLine($"Pop: {pilha.Pop()}");
Console.WriteLine($"Pop: {pilha.Pop()}");

Console.WriteLine("\n=== COLLECTION INITIALIZER E SPREADS ===");
// C# 12: collection expressions
int[] arr1 = [1, 2, 3];
int[] arr2 = [4, 5, 6];
int[] combinado = [..arr1, ..arr2, 7, 8]; // spread operator

Console.WriteLine($"Collection expression: [{string.Join(", ", combinado)}]");

// Span<T> — performance sem alocação extra (avançado)
Span<int> span = combinado.AsSpan(0, 4);
Console.Write("Span dos 4 primeiros: ");
foreach (var s in span) Console.Write($"{s} ");
Console.WriteLine();
