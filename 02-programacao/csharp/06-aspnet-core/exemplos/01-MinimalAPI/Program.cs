// ============================================================
// Minimal API com ASP.NET Core 8 — CRUD completo
// Sistema de gerenciamento de Produtos
// ============================================================

using Microsoft.AspNetCore.Mvc;

var builder = WebApplication.CreateBuilder(args);

// ============================================================
// SERVIÇOS (Dependency Injection)
// ============================================================
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(c =>
{
    c.SwaggerDoc("v1", new()
    {
        Title   = "Produtos API",
        Version = "v1",
        Description = "API de exemplo — Minimal API com ASP.NET Core 8"
    });
});

// Repositório em memória (sem banco de dados)
builder.Services.AddSingleton<IProdutoRepository, InMemoryProdutoRepository>();

var app = builder.Build();

// ============================================================
// MIDDLEWARE PIPELINE
// ============================================================
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI(c =>
    {
        c.SwaggerEndpoint("/swagger/v1/swagger.json", "Produtos API v1");
        c.RoutePrefix = string.Empty; // Swagger na raiz: http://localhost:5000
    });
}

app.UseHttpsRedirection();

// ============================================================
// ENDPOINTS — CRUD de Produtos
// ============================================================

// GET /produtos — Lista todos
app.MapGet("/produtos", (IProdutoRepository repo) =>
{
    var produtos = repo.BuscarTodos();
    return Results.Ok(produtos);
})
.WithName("ListarProdutos")
.WithSummary("Lista todos os produtos")
.WithTags("Produtos");

// GET /produtos/{id} — Busca por ID
app.MapGet("/produtos/{id:int}", (int id, IProdutoRepository repo) =>
{
    var produto = repo.BuscarPorId(id);
    return produto is not null
        ? Results.Ok(produto)
        : Results.NotFound(new { Mensagem = $"Produto {id} não encontrado" });
})
.WithName("BuscarProduto")
.WithSummary("Busca produto por ID")
.WithTags("Produtos");

// GET /produtos/buscar?nome=notebook — Busca por nome
app.MapGet("/produtos/buscar", (string? nome, string? categoria, IProdutoRepository repo) =>
{
    var produtos = repo.BuscarTodos();

    if (!string.IsNullOrEmpty(nome))
        produtos = produtos.Where(p => p.Nome.Contains(nome, StringComparison.OrdinalIgnoreCase));

    if (!string.IsNullOrEmpty(categoria))
        produtos = produtos.Where(p => p.Categoria.Equals(categoria, StringComparison.OrdinalIgnoreCase));

    return Results.Ok(produtos);
})
.WithName("BuscarProdutos")
.WithSummary("Busca produtos por filtros")
.WithTags("Produtos");

// POST /produtos — Cria novo produto
app.MapPost("/produtos", (CriarProdutoDto dto, IProdutoRepository repo) =>
{
    // Validação manual (em projetos reais use FluentValidation)
    if (string.IsNullOrWhiteSpace(dto.Nome))
        return Results.BadRequest(new { Mensagem = "Nome é obrigatório" });

    if (dto.Preco <= 0)
        return Results.BadRequest(new { Mensagem = "Preço deve ser maior que zero" });

    var produto = repo.Criar(dto.Nome, dto.Categoria, dto.Preco, dto.Descricao);

    // 201 Created com header Location
    return Results.CreatedAtRoute(
        "BuscarProduto",
        new { id = produto.Id },
        produto
    );
})
.WithName("CriarProduto")
.WithSummary("Cria novo produto")
.WithTags("Produtos");

// PUT /produtos/{id} — Atualiza produto
app.MapPut("/produtos/{id:int}", (int id, AtualizarProdutoDto dto, IProdutoRepository repo) =>
{
    var produto = repo.BuscarPorId(id);
    if (produto is null)
        return Results.NotFound(new { Mensagem = $"Produto {id} não encontrado" });

    if (string.IsNullOrWhiteSpace(dto.Nome))
        return Results.BadRequest(new { Mensagem = "Nome é obrigatório" });

    var atualizado = repo.Atualizar(id, dto.Nome, dto.Categoria, dto.Preco, dto.Descricao);
    return Results.Ok(atualizado);
})
.WithName("AtualizarProduto")
.WithSummary("Atualiza produto existente")
.WithTags("Produtos");

// PATCH /produtos/{id}/preco — Atualiza apenas o preço
app.MapPatch("/produtos/{id:int}/preco", (int id, [FromBody] AlterarPrecoDto dto, IProdutoRepository repo) =>
{
    var produto = repo.BuscarPorId(id);
    if (produto is null)
        return Results.NotFound();

    if (dto.NovoPreco <= 0)
        return Results.BadRequest(new { Mensagem = "Preço deve ser maior que zero" });

    var atualizado = produto with { Preco = dto.NovoPreco };
    repo.Atualizar(id, atualizado.Nome, atualizado.Categoria, atualizado.Preco, atualizado.Descricao);
    return Results.Ok(atualizado);
})
.WithName("AtualizarPreco")
.WithSummary("Atualiza apenas o preço de um produto")
.WithTags("Produtos");

// DELETE /produtos/{id} — Remove produto
app.MapDelete("/produtos/{id:int}", (int id, IProdutoRepository repo) =>
{
    var produto = repo.BuscarPorId(id);
    if (produto is null)
        return Results.NotFound(new { Mensagem = $"Produto {id} não encontrado" });

    repo.Deletar(id);
    return Results.NoContent(); // 204 No Content
})
.WithName("DeletarProduto")
.WithSummary("Remove produto")
.WithTags("Produtos");

// GET /produtos/estatisticas — Endpoint extra com LINQ
app.MapGet("/produtos/estatisticas", (IProdutoRepository repo) =>
{
    var produtos = repo.BuscarTodos().ToList();
    if (!produtos.Any())
        return Results.Ok(new { Mensagem = "Nenhum produto cadastrado" });

    var stats = new
    {
        Total      = produtos.Count,
        PrecoMedio = produtos.Average(p => p.Preco),
        Mais_Caro  = produtos.MaxBy(p => p.Preco),
        Mais_Barato = produtos.MinBy(p => p.Preco),
        PorCategoria = produtos
            .GroupBy(p => p.Categoria)
            .Select(g => new { Categoria = g.Key, Quantidade = g.Count(), TotalPrecos = g.Sum(p => p.Preco) })
    };

    return Results.Ok(stats);
})
.WithName("Estatisticas")
.WithSummary("Estatísticas dos produtos")
.WithTags("Produtos");

Console.WriteLine("API iniciando...");
Console.WriteLine("Acesse: http://localhost:5000 para o Swagger");
app.Run();

// ============================================================
// MODELOS
// ============================================================

public record Produto(
    int     Id,
    string  Nome,
    string  Categoria,
    decimal Preco,
    string? Descricao = null
);

public record CriarProdutoDto(
    string  Nome,
    string  Categoria,
    decimal Preco,
    string? Descricao = null
);

public record AtualizarProdutoDto(
    string  Nome,
    string  Categoria,
    decimal Preco,
    string? Descricao = null
);

public record AlterarPrecoDto(decimal NovoPreco);

// ============================================================
// REPOSITÓRIO (In-Memory)
// ============================================================

public interface IProdutoRepository
{
    IEnumerable<Produto> BuscarTodos();
    Produto? BuscarPorId(int id);
    Produto Criar(string nome, string categoria, decimal preco, string? descricao);
    Produto? Atualizar(int id, string nome, string categoria, decimal preco, string? descricao);
    bool Deletar(int id);
}

public class InMemoryProdutoRepository : IProdutoRepository
{
    private readonly List<Produto> _produtos = new()
    {
        new(1, "Notebook Dell",     "Eletrônico",   3500m, "Intel i7, 16GB RAM, SSD 512GB"),
        new(2, "Mouse Logitech",    "Eletrônico",    150m, "Mouse sem fio 2.4GHz"),
        new(3, "Mesa Escritório",   "Móvel",        1200m, "Mesa L 140x60cm"),
        new(4, "Cadeira Gamer",     "Móvel",        2800m, "Ergonômica com apoio lombar"),
        new(5, "Monitor LG 27\"",   "Eletrônico",   1800m, "IPS Full HD 144Hz"),
    };
    private int _nextId = 6;

    public IEnumerable<Produto> BuscarTodos() => _produtos;

    public Produto? BuscarPorId(int id) => _produtos.FirstOrDefault(p => p.Id == id);

    public Produto Criar(string nome, string categoria, decimal preco, string? descricao)
    {
        var produto = new Produto(_nextId++, nome, categoria, preco, descricao);
        _produtos.Add(produto);
        return produto;
    }

    public Produto? Atualizar(int id, string nome, string categoria, decimal preco, string? descricao)
    {
        int index = _produtos.FindIndex(p => p.Id == id);
        if (index < 0) return null;

        var atualizado = new Produto(id, nome, categoria, preco, descricao);
        _produtos[index] = atualizado;
        return atualizado;
    }

    public bool Deletar(int id)
    {
        int removidos = _produtos.RemoveAll(p => p.Id == id);
        return removidos > 0;
    }
}
