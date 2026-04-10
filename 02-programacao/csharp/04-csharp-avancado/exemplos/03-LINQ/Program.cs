// ============================================================
// LINQ — Language Integrated Query em C# — .NET 8
// A ferramenta mais poderosa do C# para trabalhar com dados
// ============================================================

// Dados de exemplo
var produtos = new List<Produto>
{
    new(1, "Notebook Dell",    "Eletrônico",   3500m, true),
    new(2, "Mouse Logitech",   "Eletrônico",    150m, true),
    new(3, "Mesa Escritório",  "Móvel",        1200m, false),
    new(4, "Cadeira Gamer",    "Móvel",        2800m, true),
    new(5, "Monitor LG",       "Eletrônico",   1800m, true),
    new(6, "Teclado Mecânico", "Eletrônico",    450m, false),
    new(7, "Headset Sony",     "Eletrônico",    800m, true),
    new(8, "Estante",          "Móvel",         650m, true),
};

Console.WriteLine("=== WHERE — FILTRAR ===");
var eletronicos = produtos.Where(p => p.Categoria == "Eletrônico");
Console.WriteLine("Eletrônicos:");
foreach (var p in eletronicos)
    Console.WriteLine($"  {p.Nome} - R$ {p.Preco:N2}");

Console.WriteLine("\n=== SELECT — PROJEÇÃO / TRANSFORMAÇÃO ===");
var nomes = produtos.Select(p => p.Nome);
Console.WriteLine($"Nomes: {string.Join(", ", nomes)}");

var resumo = produtos
    .Where(p => p.Ativo)
    .Select(p => new { p.Nome, p.Preco, Caro = p.Preco > 1000 });

Console.WriteLine("\nResumo (ativos):");
foreach (var r in resumo)
    Console.WriteLine($"  {r.Nome}: R${r.Preco:N2} | Caro: {r.Caro}");

Console.WriteLine("\n=== ORDERBY / ORDERBYDESCENDING ===");
var porPreco = produtos.OrderBy(p => p.Preco);
Console.WriteLine("Por preço crescente:");
foreach (var p in porPreco)
    Console.WriteLine($"  R${p.Preco:N2} - {p.Nome}");

var porPrecoDesc = produtos.OrderByDescending(p => p.Preco).Take(3);
Console.WriteLine("\nTop 3 mais caros:");
foreach (var p in porPrecoDesc)
    Console.WriteLine($"  R${p.Preco:N2} - {p.Nome}");

Console.WriteLine("\n=== GROUPBY — AGRUPAR ===");
var porCategoria = produtos.GroupBy(p => p.Categoria);

foreach (var grupo in porCategoria)
{
    Console.WriteLine($"\nCategoria: {grupo.Key} ({grupo.Count()} itens)");
    foreach (var p in grupo)
        Console.WriteLine($"  - {p.Nome} (R${p.Preco:N2})");
}

Console.WriteLine("\n=== AGREGAÇÕES ===");
decimal total    = produtos.Sum(p => p.Preco);
decimal media    = produtos.Average(p => p.Preco);
decimal maxPreco = produtos.Max(p => p.Preco);
decimal minPreco = produtos.Min(p => p.Preco);
int     qtd      = produtos.Count(p => p.Ativo);

Console.WriteLine($"Total de preços: R${total:N2}");
Console.WriteLine($"Preço médio:     R${media:N2}");
Console.WriteLine($"Mais caro:       R${maxPreco:N2}");
Console.WriteLine($"Mais barato:     R${minPreco:N2}");
Console.WriteLine($"Ativos:          {qtd}");

Console.WriteLine("\n=== FIRST, LAST, SINGLE ===");
var maisBarato   = produtos.MinBy(p => p.Preco);
var maisCaroElet = produtos.Where(p => p.Categoria == "Eletrônico").MaxBy(p => p.Preco);

Console.WriteLine($"Mais barato: {maisBarato?.Nome}");
Console.WriteLine($"Eletrônico mais caro: {maisCaroElet?.Nome}");

var primeiro = produtos.First(p => p.Preco > 1000);
var primeiroOuNull = produtos.FirstOrDefault(p => p.Preco > 10000);

Console.WriteLine($"Primeiro > R$1000: {primeiro.Nome}");
Console.WriteLine($"Primeiro > R$10000: {primeiroOuNull?.Nome ?? "Não encontrado"}");

Console.WriteLine("\n=== ANY, ALL, CONTAINS ===");
bool temMovel    = produtos.Any(p => p.Categoria == "Móvel");
bool todosAtivos = produtos.All(p => p.Ativo);
bool temNotebook = produtos.Any(p => p.Nome.Contains("Notebook"));

Console.WriteLine($"Tem Móvel:     {temMovel}");
Console.WriteLine($"Todos ativos:  {todosAtivos}");
Console.WriteLine($"Tem Notebook:  {temNotebook}");

Console.WriteLine("\n=== SKIP, TAKE — PAGINAÇÃO ===");
int pagina = 2, tamanhoPagina = 3;
var paginado = produtos
    .OrderBy(p => p.Id)
    .Skip((pagina - 1) * tamanhoPagina)
    .Take(tamanhoPagina);

Console.WriteLine($"Página {pagina} ({tamanhoPagina} por página):");
foreach (var p in paginado)
    Console.WriteLine($"  [{p.Id}] {p.Nome}");

Console.WriteLine("\n=== SELECT MANY — FLATTEN ===");
var pedidos = new List<Pedido>
{
    new("Alice", new[] { "Notebook", "Mouse" }),
    new("Bob",   new[] { "Cadeira", "Mesa", "Monitor" }),
    new("Carol", new[] { "Headset" }),
};

var todosItens = pedidos.SelectMany(p => p.Itens);
Console.WriteLine($"Todos os itens: {string.Join(", ", todosItens)}");

var itensComCliente = pedidos.SelectMany(
    p => p.Itens,
    (pedido, item) => $"{pedido.Cliente}: {item}"
);
foreach (var item in itensComCliente)
    Console.WriteLine($"  {item}");

Console.WriteLine("\n=== DISTINCT, UNION, INTERSECT, EXCEPT ===");
var lista1 = new[] { 1, 2, 3, 4, 5 };
var lista2 = new[] { 3, 4, 5, 6, 7 };
var comDup = new[] { 1, 2, 2, 3, 3, 3 };

Console.WriteLine($"Distinct: [{string.Join(", ", comDup.Distinct())}]");
Console.WriteLine($"Union:    [{string.Join(", ", lista1.Union(lista2))}]");
Console.WriteLine($"Intersect:[{string.Join(", ", lista1.Intersect(lista2))}]");
Console.WriteLine($"Except:   [{string.Join(", ", lista1.Except(lista2))}]");

Console.WriteLine("\n=== QUERY SYNTAX ===");
var queryResult =
    from p in produtos
    where p.Ativo && p.Preco > 500
    orderby p.Preco descending
    select new { p.Nome, p.Preco };

Console.WriteLine("Query syntax (ativos > R$500, mais caros primeiro):");
foreach (var r in queryResult)
    Console.WriteLine($"  {r.Nome}: R${r.Preco:N2}");

Console.WriteLine("\n=== DEFERRED EXECUTION ===");
var query = produtos.Where(p =>
{
    Console.Write("(avaliando) ");
    return p.Preco > 1000;
});

Console.WriteLine("Query criada (nada executado ainda)");
Console.WriteLine("Agora vou iterar:");
int count = query.Count();
Console.WriteLine($"\nTotal com preço > R$1000: {count}");

var lista = query.ToList();
Console.WriteLine($"ToList() Count: {lista.Count}");

// ============================================================
// DECLARAÇÕES DE TIPOS
// ============================================================

record Produto(int Id, string Nome, string Categoria, decimal Preco, bool Ativo);
record Pedido(string Cliente, IEnumerable<string> Itens);
