# 04 — C# Avançado

> Aqui estão os recursos que separam desenvolvedores júnior de sênior no mercado. Generics, LINQ, async/await e delegates são utilizados em todo projeto .NET profissional.

---

## Generics

Generics permitem escrever código reutilizável com segurança de tipos:

```csharp
// Sem generics: precisa de um método por tipo, ou usa object (sem segurança)
// Com generics: um método para qualquer tipo
T Maximo<T>(T a, T b) where T : IComparable<T>
    => a.CompareTo(b) >= 0 ? a : b;

Console.WriteLine(Maximo(10, 20));        // 20
Console.WriteLine(Maximo("abc", "xyz"));  // xyz
```

### Constraints (restrições)
```csharp
// where T : class      — apenas reference types
// where T : struct     — apenas value types
// where T : new()      — deve ter construtor sem parâmetros
// where T : BaseClass  — deve herdar de BaseClass
// where T : IInterface — deve implementar interface
```

---

## Delegates, Action e Func

```csharp
// Delegate: tipo para referência a método
delegate int Operacao(int a, int b);
Operacao soma = (a, b) => a + b;

// Action<T>: delegate que não retorna (void)
Action<string> imprimir = msg => Console.WriteLine(msg);
imprimir("Olá!");

// Func<T, TResult>: delegate que retorna valor
Func<int, bool> ehPar = n => n % 2 == 0;
Console.WriteLine(ehPar(4)); // true

// Predicate<T>: equivalente a Func<T, bool>
Predicate<string> ehVazio = string.IsNullOrEmpty;
```

---

## LINQ

LINQ (Language Integrated Query) é a forma idiomática de trabalhar com dados em C#:

```csharp
var produtos = new List<Produto> { ... };

// Method syntax (mais comum no mercado)
var resultado = produtos
    .Where(p => p.Ativo && p.Preco > 100)
    .OrderBy(p => p.Nome)
    .Select(p => new { p.Nome, p.Preco })
    .Take(10)
    .ToList();

// Query syntax (parecida com SQL)
var query =
    from p in produtos
    where p.Ativo
    orderby p.Preco descending
    select p;
```

### Operadores principais

| Operador | O que faz |
|----------|-----------|
| `Where` | Filtra |
| `Select` | Projeta/transforma |
| `OrderBy` / `ThenBy` | Ordena |
| `GroupBy` | Agrupa |
| `Join` | Junta duas coleções |
| `Sum`, `Average`, `Max`, `Min` | Agregações |
| `Any`, `All`, `Contains` | Verificação |
| `Take`, `Skip` | Paginação |
| `First`, `FirstOrDefault` | Acesso a elemento |
| `Distinct`, `Union`, `Intersect` | Operações de conjunto |
| `SelectMany` | Achata coleções aninhadas |

---

## Async / Await

```csharp
// Método assíncrono retorna Task ou Task<T>
async Task<string> BuscarDadosAsync(int id)
{
    await Task.Delay(100); // Simula I/O
    return $"Dados do ID {id}";
}

// Executar em paralelo (muito mais rápido que sequencial!)
var (r1, r2, r3) = await (
    BuscarDadosAsync(1),
    BuscarDadosAsync(2),
    BuscarDadosAsync(3)
);

// Ou com Task.WhenAll
string[] results = await Task.WhenAll(
    BuscarDadosAsync(1),
    BuscarDadosAsync(2)
);
```

**Regras de ouro:**
- Nunca use `.Result` ou `.Wait()` — causa deadlock
- Nomeie métodos async com sufixo `Async`
- Use `CancellationToken` para operações longas

---

## Exception Handling

```csharp
try
{
    var resultado = await OperacaoRiscosaAsync();
}
catch (HttpRequestException ex) when (ex.StatusCode == HttpStatusCode.NotFound)
{
    // Exception filter — só captura 404
    Console.WriteLine("Recurso não encontrado");
}
catch (Exception ex)
{
    Console.WriteLine($"Erro: {ex.Message}");
    throw; // Re-lança sem perder o stack trace
}
finally
{
    // Sempre executa — use para liberar recursos
    Console.WriteLine("Operação finalizada");
}

// Custom exception
public class NegocioException : Exception
{
    public string Codigo { get; }
    public NegocioException(string codigo, string mensagem) : base(mensagem)
        => Codigo = codigo;
}
```

---

## Exemplos Práticos

| Pasta | Conteúdo |
|-------|---------|
| [01-Generics](./exemplos/01-Generics/) | Métodos, classes e constraints genéricas |
| [02-DelegatesEventos](./exemplos/02-DelegatesEventos/) | Delegates, Action, Func, eventos, lambdas |
| [03-LINQ](./exemplos/03-LINQ/) | LINQ completo: filtro, projeção, agrupamento, join |
| [04-AsyncAwait](./exemplos/04-AsyncAwait/) | async/await, Task.WhenAll, CancellationToken |

---

## Exercícios

1. Implemente um `Repositorio<T>` genérico com `Adicionar`, `BuscarPorId` e `Filtrar`
2. Crie uma pipeline funcional: dado uma lista de números, filtre os pares, dobre cada um, some os maiores que 10
3. Usando LINQ, agrupe funcionários por departamento e calcule a média salarial de cada um
4. Implemente um método `ProcessarEmParaleloAsync` que processa uma lista de itens em paralelo com limite de concorrência (use `SemaphoreSlim`)
5. Crie um event system com `EventHandler<T>` para uma classe `Carrinho` que dispara eventos quando produtos são adicionados/removidos

---

**Seção anterior:** [03 — Orientação a Objetos](../03-orientacao-objetos/)  
**Próxima seção:** [05 — .NET Core & Dependency Injection](../05-dotnet-core/)
