// ============================================================
// Entity Framework Core 8 — SQLite In-Memory
// Demonstra CRUD completo, relacionamentos e LINQ com banco
// ============================================================

using Microsoft.EntityFrameworkCore;

await using var ctx = new LojaDbContext();
await ctx.Database.EnsureCreatedAsync();

Console.WriteLine("=== ENTITY FRAMEWORK CORE 8 — DEMO ===\n");

// ============================================================
// CREATE
// ============================================================
Console.WriteLine("--- CREATE ---");

var novoProduto = new Produto
{
    Nome        = "Teclado Mecânico",
    Preco       = 450m,
    Estoque     = 20,
    CategoriaId = 1
};

ctx.Produtos.Add(novoProduto);
await ctx.SaveChangesAsync();
Console.WriteLine($"Produto criado com ID: {novoProduto.Id}");

var novosPedidos = new List<Pedido>
{
    new() { Cliente = "João Silva" },
    new() { Cliente = "Maria Santos" }
};
await ctx.Pedidos.AddRangeAsync(novosPedidos);
await ctx.SaveChangesAsync();
Console.WriteLine($"Pedidos criados: {novosPedidos.Count}");

// ============================================================
// READ
// ============================================================
Console.WriteLine("\n--- READ ---");

var todosProdutos = await ctx.Produtos.ToListAsync();
Console.WriteLine($"Total de produtos: {todosProdutos.Count}");

var eletronicos = await ctx.Produtos
    .Where(p => p.CategoriaId == 1 && p.Ativo)
    .OrderBy(p => p.Preco)
    .ToListAsync();

Console.WriteLine($"\nEletrônicos ({eletronicos.Count}):");
foreach (var p in eletronicos)
    Console.WriteLine($"  [{p.Id}] {p.Nome} - R${p.Preco:N2}");

// Include — Eager Loading (carrega relacionamento)
var produtosComCategoria = await ctx.Produtos
    .Include(p => p.Categoria)
    .Where(p => p.Preco > 500)
    .OrderByDescending(p => p.Preco)
    .Select(p => new { p.Nome, p.Preco, CategoriaNome = p.Categoria.Nome })
    .ToListAsync();

Console.WriteLine("\nProdutos > R$500 com categoria:");
foreach (var p in produtosComCategoria)
    Console.WriteLine($"  {p.Nome} ({p.CategoriaNome}) - R${p.Preco:N2}");

// FindAsync — busca por PK (usa cache interno)
var produto = await ctx.Produtos.FindAsync(1);
Console.WriteLine($"\nFind ID 1: {produto?.Nome ?? "não encontrado"}");

decimal precoMedio   = await ctx.Produtos.AverageAsync(p => p.Preco);
int     totalEstoque = await ctx.Produtos.SumAsync(p => p.Estoque);
Console.WriteLine($"Preço médio: R${precoMedio:N2}");
Console.WriteLine($"Total em estoque: {totalEstoque} unidades");

// GroupBy
var porCategoria = await ctx.Produtos
    .GroupBy(p => p.CategoriaId)
    .Select(g => new { CategoriaId = g.Key, Quantidade = g.Count(), PrecoTotal = g.Sum(p => p.Preco) })
    .ToListAsync();

Console.WriteLine("\nProdutos por categoria:");
foreach (var g in porCategoria)
    Console.WriteLine($"  Categoria {g.CategoriaId}: {g.Quantidade} produtos, total R${g.PrecoTotal:N2}");

// ============================================================
// UPDATE
// ============================================================
Console.WriteLine("\n--- UPDATE ---");

var produtoAtualizar = await ctx.Produtos.FindAsync(novoProduto.Id);
if (produtoAtualizar is not null)
{
    produtoAtualizar.Preco   = 399m;
    produtoAtualizar.Estoque = 25;
    await ctx.SaveChangesAsync();
    Console.WriteLine($"Produto {produtoAtualizar.Id} atualizado: R${produtoAtualizar.Preco:N2}");
}

// ExecuteUpdate (EF Core 7+) — atualiza direto no banco
int atualizados = await ctx.Produtos
    .Where(p => p.CategoriaId == 3)
    .ExecuteUpdateAsync(s => s.SetProperty(p => p.Preco, p => p.Preco * 0.9m));
Console.WriteLine($"Livros com 10% de desconto: {atualizados} produtos");

// ============================================================
// DELETE
// ============================================================
Console.WriteLine("\n--- DELETE ---");

var produtoRemover = await ctx.Produtos.FindAsync(novoProduto.Id);
if (produtoRemover is not null)
{
    ctx.Produtos.Remove(produtoRemover);
    await ctx.SaveChangesAsync();
    Console.WriteLine($"Produto {novoProduto.Id} removido");
}

int removidos = await ctx.Produtos
    .Where(p => p.Estoque == 0)
    .ExecuteDeleteAsync();
Console.WriteLine($"Produtos sem estoque removidos: {removidos}");

// ============================================================
// TRANSAÇÃO
// ============================================================
Console.WriteLine("\n--- TRANSAÇÃO ---");

await using var transaction = await ctx.Database.BeginTransactionAsync();
try
{
    var pedido = new Pedido { Cliente = "Ana Costa" };
    ctx.Pedidos.Add(pedido);
    await ctx.SaveChangesAsync();

    var item = new ItemPedido
    {
        PedidoId      = pedido.Id,
        ProdutoId     = 1,
        Quantidade    = 2,
        PrecoUnitario = 3500m
    };
    ctx.ItensPedido.Add(item);
    await ctx.SaveChangesAsync();

    await transaction.CommitAsync();
    Console.WriteLine($"Pedido {pedido.Id} para {pedido.Cliente} criado com transação!");
}
catch (Exception ex)
{
    await transaction.RollbackAsync();
    Console.WriteLine($"Transação revertida: {ex.Message}");
}

// ============================================================
// SQL RAW
// ============================================================
Console.WriteLine("\n--- SQL RAW ---");

var produtosSql = await ctx.Produtos
    .FromSqlRaw("SELECT * FROM Produtos WHERE Preco > {0}", 100m)
    .ToListAsync();
Console.WriteLine($"SQL raw retornou {produtosSql.Count} produtos");

Console.WriteLine("\n=== DEMO CONCLUÍDA ===");

// ============================================================
// MODELOS — devem vir APÓS os top-level statements
// ============================================================

public class Categoria
{
    public int    Id   { get; set; }
    public string Nome { get; set; } = string.Empty;
    public List<Produto> Produtos { get; set; } = new();
}

public class Produto
{
    public int      Id          { get; set; }
    public string   Nome        { get; set; } = string.Empty;
    public decimal  Preco       { get; set; }
    public int      Estoque     { get; set; }
    public bool     Ativo       { get; set; } = true;
    public DateTime CriadoEm   { get; set; } = DateTime.UtcNow;
    public int      CategoriaId { get; set; }
    public Categoria Categoria  { get; set; } = null!;
    public List<ItemPedido> Itens { get; set; } = new();
}

public class Pedido
{
    public int      Id         { get; set; }
    public string   Cliente    { get; set; } = string.Empty;
    public DateTime DataPedido { get; set; } = DateTime.UtcNow;
    public StatusPedido Status { get; set; } = StatusPedido.Pendente;
    public decimal Total => Itens.Sum(i => i.PrecoUnitario * i.Quantidade);
    public List<ItemPedido> Itens { get; set; } = new();
}

public class ItemPedido
{
    public int     Id             { get; set; }
    public int     PedidoId       { get; set; }
    public int     ProdutoId      { get; set; }
    public int     Quantidade     { get; set; }
    public decimal PrecoUnitario  { get; set; }
    public Pedido  Pedido         { get; set; } = null!;
    public Produto Produto        { get; set; } = null!;
}

public enum StatusPedido { Pendente, Processando, Enviado, Entregue, Cancelado }

public class LojaDbContext : DbContext
{
    public DbSet<Categoria>  Categorias  { get; set; }
    public DbSet<Produto>    Produtos    { get; set; }
    public DbSet<Pedido>     Pedidos     { get; set; }
    public DbSet<ItemPedido> ItensPedido { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder options)
    {
        options.UseInMemoryDatabase("LojaDemo");
        options.LogTo(_ => { }, Microsoft.Extensions.Logging.LogLevel.None);
    }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Produto>(entity =>
        {
            entity.HasKey(p => p.Id);
            entity.Property(p => p.Nome).IsRequired().HasMaxLength(200);
            entity.Property(p => p.Preco).HasPrecision(18, 2);

            entity.HasOne(p => p.Categoria)
                  .WithMany(c => c.Produtos)
                  .HasForeignKey(p => p.CategoriaId)
                  .OnDelete(DeleteBehavior.Restrict);
        });

        modelBuilder.Entity<ItemPedido>(entity =>
        {
            entity.HasKey(i => i.Id);
            entity.Property(i => i.PrecoUnitario).HasPrecision(18, 2);
            entity.HasOne(i => i.Pedido).WithMany(p => p.Itens).HasForeignKey(i => i.PedidoId);
            entity.HasOne(i => i.Produto).WithMany(p => p.Itens).HasForeignKey(i => i.ProdutoId);
        });

        modelBuilder.Entity<Categoria>().HasData(
            new Categoria { Id = 1, Nome = "Eletrônicos" },
            new Categoria { Id = 2, Nome = "Móveis" },
            new Categoria { Id = 3, Nome = "Livros" }
        );

        modelBuilder.Entity<Produto>().HasData(
            new Produto { Id = 1, Nome = "Notebook Dell",  Preco = 3500m, Estoque = 10, CategoriaId = 1 },
            new Produto { Id = 2, Nome = "Mouse Logitech", Preco = 150m,  Estoque = 50, CategoriaId = 1 },
            new Produto { Id = 3, Nome = "Mesa Escritório",Preco = 1200m, Estoque = 5,  CategoriaId = 2 },
            new Produto { Id = 4, Nome = "Clean Code",     Preco = 80m,   Estoque = 30, CategoriaId = 3 },
            new Produto { Id = 5, Nome = "Monitor LG",     Preco = 1800m, Estoque = 8,  CategoriaId = 1 }
        );
    }
}
