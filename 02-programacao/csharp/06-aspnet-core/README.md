# 06 — ASP.NET Core

> ASP.NET Core é o framework para construção de APIs e aplicações web. É uma das tecnologias mais demandadas no mercado .NET. Aqui você aprende do zero a construir APIs REST profissionais.

---

## O que é ASP.NET Core?

ASP.NET Core é um framework multiplataforma, open source e de alta performance para construir:
- **APIs REST** (JSON, consumidas por front-ends e mobile)
- **gRPC services** (comunicação binária de alta performance)
- **Web apps** com Razor Pages ou MVC
- **SignalR** (real-time com WebSockets)

---

## Minimal API vs Controller-based

### Minimal API (C# 9+ / .NET 6+) — mais simples
```csharp
var app = WebApplication.Create(args);

app.MapGet("/produtos", () => new[] { "Notebook", "Mouse" });
app.MapPost("/produtos", (Produto p) => Results.Created($"/produtos/{p.Id}", p));

app.Run();
```

### Controller-based — mais estruturado (grandes projetos)
```csharp
[ApiController]
[Route("api/[controller]")]
public class ProdutosController : ControllerBase
{
    [HttpGet]
    public IActionResult Get() => Ok(new[] { "Notebook", "Mouse" });

    [HttpPost]
    public IActionResult Create([FromBody] Produto produto)
        => CreatedAtAction(nameof(Get), new { id = produto.Id }, produto);
}
```

> Para APIs simples e microsserviços, prefira **Minimal API**. Para sistemas grandes com muitos controladores, use **Controller-based**.

---

## HTTP Methods e Status Codes

| Método | Uso | Status de sucesso |
|--------|-----|------------------|
| GET | Ler recurso | 200 OK |
| POST | Criar recurso | 201 Created |
| PUT | Substituir recurso | 200 OK |
| PATCH | Atualizar parcialmente | 200 OK |
| DELETE | Remover recurso | 204 No Content |

```csharp
// GET
app.MapGet("/usuarios/{id}", (int id) => Results.Ok(new { Id = id }));

// POST com validação
app.MapPost("/usuarios", (CriarUsuarioDto dto) =>
{
    if (string.IsNullOrEmpty(dto.Nome))
        return Results.BadRequest("Nome é obrigatório");

    return Results.Created("/usuarios/1", dto);
});

// DELETE
app.MapDelete("/usuarios/{id}", (int id) => Results.NoContent());
```

---

## Routing e Parâmetros

```csharp
// Parâmetro de rota
app.MapGet("/produtos/{id:int}", (int id) => $"Produto {id}");

// Query string: GET /produtos?nome=notebook&pagina=2
app.MapGet("/produtos", (string? nome, int pagina = 1) =>
    $"Buscando '{nome}', página {pagina}");

// Body (JSON)
app.MapPost("/produtos", (Produto produto) => Results.Created("/produtos/1", produto));

// Header
app.MapGet("/info", (HttpRequest request) =>
{
    var userAgent = request.Headers.UserAgent.ToString();
    return Results.Ok(new { userAgent });
});
```

---

## Swagger / OpenAPI

```csharp
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// ...

app.UseSwagger();
app.UseSwaggerUI(); // Interface visual em /swagger

// Documentar endpoints
app.MapGet("/produtos", (IProdutoRepository repo) => Results.Ok(repo.BuscarTodos()))
    .WithName("ListarProdutos")
    .WithSummary("Retorna todos os produtos")
    .WithDescription("Retorna a lista completa de produtos cadastrados")
    .WithTags("Produtos")
    .Produces<IEnumerable<Produto>>()
    .ProducesProblem(404);
```

---

## Middleware Pipeline

```
Request → [Middleware 1] → [Middleware 2] → [Endpoint] → Response
              ↑                  ↑                            ↓
          Autenticação      Autorização               (pipeline volta)
```

```csharp
// Ordem importa! Sempre adicione middleware nesta ordem:
app.UseExceptionHandler("/error"); // Tratamento global de erros
app.UseHttpsRedirection();
app.UseAuthentication();           // Quem é você?
app.UseAuthorization();            // O que você pode fazer?
app.UseRateLimiter();              // Limites de requisição

// Middleware customizado
app.Use(async (context, next) =>
{
    Console.WriteLine($"Request: {context.Request.Method} {context.Request.Path}");
    await next(context);
    Console.WriteLine($"Response: {context.Response.StatusCode}");
});
```

---

## Results — Respostas HTTP

```csharp
Results.Ok(objeto)                    // 200 + JSON
Results.Created("/url", objeto)       // 201 + Location header
Results.NoContent()                   // 204
Results.BadRequest("msg")             // 400
Results.Unauthorized()                // 401
Results.Forbid()                      // 403
Results.NotFound("msg")               // 404
Results.Conflict("msg")               // 409
Results.Problem("detalhe", status: 500) // RFC 7807 Problem Details
```

---

## Exemplo Prático — CRUD de Produtos

O exemplo [`01-MinimalAPI/`](./exemplos/01-MinimalAPI/) implementa:
- CRUD completo (GET, POST, PUT, PATCH, DELETE)
- Filtros por query string
- Validação manual
- Swagger/OpenAPI
- Repositório in-memory (sem banco)

```bash
cd exemplos/01-MinimalAPI
dotnet run

# Acesse http://localhost:5000 para ver o Swagger
```

### Testando com curl
```bash
# Listar todos
curl http://localhost:5000/produtos

# Buscar por ID
curl http://localhost:5000/produtos/1

# Criar
curl -X POST http://localhost:5000/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome":"Teclado","categoria":"Eletrônico","preco":350}'

# Deletar
curl -X DELETE http://localhost:5000/produtos/1
```

---

## Exercícios

1. Adicione endpoint `GET /produtos/categorias` que retorna as categorias distintas
2. Implemente paginação no `GET /produtos`: `?pagina=1&tamanhoPagina=5`
3. Adicione validação com `FluentValidation` (`dotnet add package FluentValidation`)
4. Implemente um middleware de logging que registra todas as requests com duração
5. Adicione autenticação básica com JWT (`dotnet add package Microsoft.AspNetCore.Authentication.JwtBearer`)
6. Crie uma versão Controller-based da mesma API

---

**Seção anterior:** [05 — .NET Core & DI](../05-dotnet-core/)  
**Próxima seção:** [07 — Entity Framework Core](../07-entity-framework/)
