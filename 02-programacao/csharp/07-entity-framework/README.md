# 07 — Entity Framework Core

> EF Core é o ORM padrão do .NET. Saber EF Core é obrigatório para trabalhar com qualquer aplicação .NET que acesse banco de dados — e isso é a maioria delas.

---

## O que é ORM?

**ORM (Object-Relational Mapper)** mapeia classes C# para tabelas do banco de dados. Em vez de escrever SQL manual, você trabalha com objetos C# e o EF Core gera o SQL automaticamente.

| Sem ORM | Com EF Core |
|---------|-------------|
| `SELECT * FROM Produtos WHERE Id = 1` | `ctx.Produtos.FindAsync(1)` |
| `INSERT INTO Produtos VALUES (...)` | `ctx.Produtos.Add(produto); ctx.SaveChangesAsync()` |
| `UPDATE Produtos SET Preco = 100 WHERE Id = 1` | Modifica o objeto e chama `SaveChangesAsync()` |

---

## Code First vs Database First

| Abordagem | Descrição | Quando usar |
|-----------|-----------|-------------|
| **Code First** | Você escreve as classes, EF cria o banco | Projetos novos — padrão atual |
| **Database First** | Banco existente, EF gera as classes | Sistemas legados |

```bash
# Code First: criar e aplicar migrations
dotnet add package Microsoft.EntityFrameworkCore.Tools
dotnet ef migrations add CriacaoInicial
dotnet ef database update

# Reverter migration
dotnet ef database update NomeDaMigrationAnterior

# Gerar script SQL
dotnet ef migrations script
```

---

## DbContext — O Centro do EF Core

```csharp
public class AppDbContext : DbContext
{
    // Cada DbSet representa uma tabela
    public DbSet<Produto>   Produtos   { get; set; }
    public DbSet<Categoria> Categorias { get; set; }
    public DbSet<Pedido>    Pedidos    { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder options)
        => options.UseSqlServer("Server=.;Database=MinhaLoja;...");

    // Ou com DI (ASP.NET Core):
    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        // Fluent API — configuração das entidades
        modelBuilder.Entity<Produto>(entity =>
        {
            entity.HasKey(p => p.Id);
            entity.Property(p => p.Nome).IsRequired().HasMaxLength(200);
            entity.Property(p => p.Preco).HasPrecision(18, 2);

            entity.HasOne(p => p.Categoria)
                  .WithMany(c => c.Produtos)
                  .HasForeignKey(p => p.CategoriaId);
        });
    }
}
```

---

## CRUD com EF Core

```csharp
await using var ctx = new AppDbContext();

// CREATE
var produto = new Produto { Nome = "Notebook", Preco = 3500m };
ctx.Produtos.Add(produto);
await ctx.SaveChangesAsync(); // Id preenchido automaticamente após save

// READ
var todos = await ctx.Produtos.ToListAsync();
var um = await ctx.Produtos.FindAsync(1); // Busca por PK (usa cache)
var filtrado = await ctx.Produtos
    .Where(p => p.Preco > 1000)
    .Include(p => p.Categoria) // Eager loading do relacionamento
    .OrderBy(p => p.Nome)
    .ToListAsync();

// UPDATE
var p = await ctx.Produtos.FindAsync(1);
p!.Preco = 3000m; // EF rastreia mudanças
await ctx.SaveChangesAsync();

// DELETE
ctx.Produtos.Remove(p);
await ctx.SaveChangesAsync();
```

---

## Relacionamentos

### 1:N (Um para Muitos) — mais comum
```csharp
public class Categoria
{
    public int Id { get; set; }
    public List<Produto> Produtos { get; set; } = new(); // navigation property
}

public class Produto
{
    public int       CategoriaId { get; set; } // FK
    public Categoria Categoria   { get; set; } = null!;
}
```

### N:N (Muitos para Muitos)
```csharp
// EF Core 5+ cria a tabela intermediária automaticamente
public class Pedido
{
    public List<Produto> Produtos { get; set; } = new();
}
public class Produto
{
    public List<Pedido> Pedidos { get; set; } = new();
}
```

---

## Loading Strategies

```csharp
// Eager Loading — carrega relacionamento junto (recomendado)
var produtos = await ctx.Produtos
    .Include(p => p.Categoria)
    .ThenInclude(c => c.SubCategoria)
    .ToListAsync();

// Lazy Loading — carrega sob demanda (evite, gera N+1 queries!)
// Requer: UseLazyLoadingProxies() + virtual nas navigation properties

// Explicit Loading — carrega quando você decidir
var produto = await ctx.Produtos.FindAsync(1);
await ctx.Entry(produto!).Reference(p => p.Categoria).LoadAsync();
```

---

## Performance e Boas Práticas

```csharp
// AsNoTracking — mais rápido para queries read-only
var produtos = await ctx.Produtos
    .AsNoTracking()
    .Where(p => p.Ativo)
    .ToListAsync();

// Projeção — busca apenas o que precisa
var nomes = await ctx.Produtos
    .Select(p => new { p.Id, p.Nome })
    .ToListAsync();

// ExecuteUpdate/ExecuteDelete (EF Core 7+) — sem carregar entidades
await ctx.Produtos
    .Where(p => p.CategoriaId == 1)
    .ExecuteUpdateAsync(s => s.SetProperty(p => p.Ativo, false));

// Paginação
var pagina = await ctx.Produtos
    .OrderBy(p => p.Id)
    .Skip((numeroPagina - 1) * tamanhoPagina)
    .Take(tamanhoPagina)
    .ToListAsync();
```

---

## Exemplo Prático

O exemplo [`01-EFCore-Basico/`](./exemplos/01-EFCore-Basico/) demonstra:
- Modelos com relacionamentos 1:N e N:N
- Configuração via Fluent API
- CRUD completo
- Agregações e GroupBy
- Transações
- SQL Raw
- Banco SQLite in-memory (sem instalação)

```bash
cd exemplos/01-EFCore-Basico
dotnet run
```

---

## Exercícios

1. Adicione uma entidade `Fornecedor` com relacionamento 1:N com `Produto`
2. Implemente um método de busca com paginação (`BuscarPaginado(pagina, tamanho)`)
3. Crie uma migration que adiciona coluna `DataAtualizacao` em `Produto`
4. Implemente soft delete: adicione `Deletado bool` e filtre automaticamente via query filter
5. Use `IQueryable<T>` para construir queries dinâmicas baseadas em filtros opcionais
6. Adicione um índice único em `Produto.Nome` via Fluent API

---

**Seção anterior:** [06 — ASP.NET Core](../06-aspnet-core/)  
**Próxima seção:** [08 — Testes Unitários](../08-testes-unitarios/)
