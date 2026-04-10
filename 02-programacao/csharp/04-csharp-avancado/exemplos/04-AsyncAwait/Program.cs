// ============================================================
// Async/Await em C# — .NET 8
// Programação assíncrona sem travar a thread principal
// ============================================================

Console.WriteLine("=== POR QUE ASYNC? ===");
Console.WriteLine("Operações I/O (banco de dados, HTTP, disco) são lentas.");
Console.WriteLine("Com async/await, a thread fica livre enquanto espera.\n");

Console.WriteLine("=== TASK — A BASE DO ASYNC ===");

Task tarefa1 = Task.Run(() => Console.WriteLine("Tarefa em background!"));
await tarefa1;

Task<int> calculo = Task.Run(() =>
{
    int soma = 0;
    for (int i = 1; i <= 1000; i++) soma += i;
    return soma;
});
int resultado = await calculo;
Console.WriteLine($"Soma de 1 a 1000 = {resultado}\n");

Console.WriteLine("=== MÉTODO ASYNC ===");

static async Task<string> BuscarDadosAsync(string recurso, int delayMs)
{
    Console.WriteLine($"  Iniciando busca de '{recurso}'...");
    await Task.Delay(delayMs);
    Console.WriteLine($"  '{recurso}' recebido após {delayMs}ms");
    return $"Dados de {recurso}";
}

var dados = await BuscarDadosAsync("usuários", 100);
Console.WriteLine($"Resultado: {dados}\n");

Console.WriteLine("=== AWAIT MÚLTIPLOS — SEQUENCIAL vs PARALELO ===");

var stopwatch = System.Diagnostics.Stopwatch.StartNew();
Console.WriteLine("Sequencial:");
var r1 = await BuscarDadosAsync("config", 200);
var r2 = await BuscarDadosAsync("produtos", 200);
var r3 = await BuscarDadosAsync("clientes", 200);
stopwatch.Stop();
Console.WriteLine($"Sequencial levou: {stopwatch.ElapsedMilliseconds}ms\n");

// PARALELO com Task.WhenAll — muito mais rápido!
stopwatch.Restart();
Console.WriteLine("Paralelo com Task.WhenAll:");
string[] resultadosParalelos = await Task.WhenAll(
    BuscarDadosAsync("config", 200),
    BuscarDadosAsync("produtos", 200),
    BuscarDadosAsync("clientes", 200)
);
stopwatch.Stop();
Console.WriteLine($"Paralelo levou: {stopwatch.ElapsedMilliseconds}ms");
Console.WriteLine($"Resultados: {string.Join(", ", resultadosParalelos.Select(r => r[..8]))}\n");

Console.WriteLine("=== TASK.WHENANY — CORRIDA ===");
var corrida = new[]
{
    BuscarDadosAsync("servidor1", 300),
    BuscarDadosAsync("servidor2", 100),
    BuscarDadosAsync("servidor3", 200),
};
var vencedora = await Task.WhenAny(corrida);
Console.WriteLine($"Primeira a concluir: {await vencedora}\n");

Console.WriteLine("=== CANCELLATION TOKEN — CANCELAR OPERAÇÕES ===");

static async Task<string> BuscarComCancelamentoAsync(string recurso, CancellationToken ct)
{
    for (int i = 0; i < 10; i++)
    {
        ct.ThrowIfCancellationRequested();
        await Task.Delay(50, ct);
        Console.Write($"  {recurso}:{i} ");
    }
    Console.WriteLine();
    return $"Concluído: {recurso}";
}

using var cts = new CancellationTokenSource();
cts.CancelAfter(180);

try
{
    var dados2 = await BuscarComCancelamentoAsync("download", cts.Token);
    Console.WriteLine(dados2);
}
catch (OperationCanceledException)
{
    Console.WriteLine("\nOperação cancelada!");
}

Console.WriteLine("\n=== EXCEPTION HANDLING ASYNC ===");

static async Task<int> OperacaoQuePoderFalharAsync(bool falhar)
{
    await Task.Delay(50);
    if (falhar)
        throw new InvalidOperationException("Algo deu errado na operação async!");
    return 42;
}

try
{
    int valor = await OperacaoQuePoderFalharAsync(true);
    Console.WriteLine($"Valor: {valor}");
}
catch (InvalidOperationException ex)
{
    Console.WriteLine($"Exceção capturada: {ex.Message}");
}

Console.WriteLine("\n=== VALUETASK — PERFORMANCE ===");
static async ValueTask<int> ContarAsync(int n)
{
    if (n <= 0) return 0;
    await Task.Delay(10);
    return n;
}

int c1 = await ContarAsync(-1);
int c2 = await ContarAsync(5);
Console.WriteLine($"ValueTask resultados: {c1}, {c2}");

Console.WriteLine("\n=== ASYNC STREAM (IAsyncEnumerable) ===");

static async IAsyncEnumerable<int> GerarNumerosAsync(int quantidade)
{
    for (int i = 1; i <= quantidade; i++)
    {
        await Task.Delay(20);
        yield return i * 10;
    }
}

Console.Write("Async stream: ");
await foreach (var num in GerarNumerosAsync(5))
    Console.Write($"{num} ");
Console.WriteLine();

Console.WriteLine("\n=== BOAS PRÁTICAS ASYNC ===");
Console.WriteLine("✓ Sempre propague async (async all the way down)");
Console.WriteLine("✓ Use Task.WhenAll para operações independentes");
Console.WriteLine("✓ Use CancellationToken em operações longas");
Console.WriteLine("✓ Nomeie métodos async com sufixo Async (ex: GetUserAsync)");
Console.WriteLine("✗ Nunca use .Result ou .Wait() — causa deadlock!");
