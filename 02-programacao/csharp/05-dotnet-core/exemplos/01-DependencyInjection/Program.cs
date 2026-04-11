// ============================================================
// Dependency Injection com Generic Host — .NET 8
// O padrão central de toda aplicação .NET moderna
// ============================================================

using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

// ============================================================
// PROGRAMA PRINCIPAL — configuração do Host (top-level statements)
// ============================================================

Console.WriteLine("Configurando Generic Host com Dependency Injection...\n");

var host = Host.CreateDefaultBuilder(args)
    .ConfigureLogging(logging =>
    {
        logging.ClearProviders();
        logging.AddConsole();
        logging.SetMinimumLevel(LogLevel.Warning);
    })
    .ConfigureServices(services =>
    {
        // SINGLETON — Uma instância para toda a vida da aplicação
        services.AddSingleton<IEmailService, EmailService>();

        // TRANSIENT — Nova instância a cada injeção
        services.AddTransient<ISmsService, SmsService>();

        // SCOPED — Uma instância por escopo (ex: por request HTTP)
        services.AddScoped<IProdutoRepository, InMemoryProdutoRepository>();

        // Serviço composto
        services.AddTransient<INotificacaoService, NotificacaoService>();

        // Hosted Service — executado automaticamente
        services.AddHostedService<AplicacaoPrincipal>();
    })
    .Build();

await host.RunAsync();

// ============================================================
// INTERFACES — Contratos (Abstrações)
// ============================================================

public interface IEmailService
{
    Task EnviarAsync(string destinatario, string assunto, string corpo);
}

public interface ISmsService
{
    Task EnviarAsync(string telefone, string mensagem);
}

public interface INotificacaoService
{
    Task NotificarAsync(string destinatario, string mensagem);
}

public interface IProdutoRepository
{
    Produto? BuscarPorId(int id);
    IEnumerable<Produto> BuscarTodos();
    void Salvar(Produto produto);
}

// ============================================================
// MODELOS
// ============================================================

public record Produto(int Id, string Nome, decimal Preco);

// ============================================================
// IMPLEMENTAÇÕES
// ============================================================

public class EmailService : IEmailService
{
    private readonly ILogger<EmailService> _logger;

    public EmailService(ILogger<EmailService> logger)
    {
        _logger = logger;
        _logger.LogInformation("EmailService criado (Singleton)");
    }

    public async Task EnviarAsync(string destinatario, string assunto, string corpo)
    {
        await Task.Delay(10);
        _logger.LogInformation("E-mail enviado para {Destinatario}: {Assunto}", destinatario, assunto);
        Console.WriteLine($"  [Email] Para: {destinatario} | Assunto: {assunto}");
    }
}

public class SmsService : ISmsService
{
    private static int _contador = 0;
    private readonly int _id = ++_contador;
    private readonly ILogger<SmsService> _logger;

    public SmsService(ILogger<SmsService> logger)
    {
        _logger = logger;
        _logger.LogInformation("SmsService #{Id} criado (Transient)", _id);
    }

    public async Task EnviarAsync(string telefone, string mensagem)
    {
        await Task.Delay(10);
        _logger.LogInformation("SMS enviado para {Telefone}", telefone);
        Console.WriteLine($"  [SMS #{_id}] Para: {telefone} | {mensagem}");
    }
}

public class InMemoryProdutoRepository : IProdutoRepository
{
    private static int _scopeId = 0;
    private readonly int _id = ++_scopeId;
    private readonly List<Produto> _dados = new()
    {
        new(1, "Notebook", 3500m),
        new(2, "Mouse",     150m),
        new(3, "Teclado",   300m),
    };

    public InMemoryProdutoRepository()
    {
        Console.WriteLine($"  [Repo #{_id}] InMemoryProdutoRepository criado (Scoped)");
    }

    public Produto? BuscarPorId(int id) => _dados.FirstOrDefault(p => p.Id == id);
    public IEnumerable<Produto> BuscarTodos() => _dados;
    public void Salvar(Produto produto) => _dados.Add(produto);
}

public class NotificacaoService : INotificacaoService
{
    private readonly IEmailService _email;
    private readonly ISmsService _sms;
    private readonly ILogger<NotificacaoService> _logger;

    public NotificacaoService(
        IEmailService email,
        ISmsService sms,
        ILogger<NotificacaoService> logger)
    {
        _email  = email;
        _sms    = sms;
        _logger = logger;
    }

    public async Task NotificarAsync(string destinatario, string mensagem)
    {
        _logger.LogInformation("Notificando {Destinatario}", destinatario);
        await _email.EnviarAsync(destinatario, "Notificação", mensagem);
        await _sms.EnviarAsync("(11) 99999-0000", mensagem);
    }
}

public class AplicacaoPrincipal : IHostedService
{
    private readonly INotificacaoService _notificacao;
    private readonly IProdutoRepository _repo;
    private readonly ILogger<AplicacaoPrincipal> _logger;
    private readonly IHostApplicationLifetime _lifetime;

    public AplicacaoPrincipal(
        INotificacaoService notificacao,
        IProdutoRepository repo,
        ILogger<AplicacaoPrincipal> logger,
        IHostApplicationLifetime lifetime)
    {
        _notificacao = notificacao;
        _repo        = repo;
        _logger      = logger;
        _lifetime    = lifetime;
    }

    public async Task StartAsync(CancellationToken cancellationToken)
    {
        Console.WriteLine("=== DEMONSTRAÇÃO DE DEPENDENCY INJECTION ===\n");

        Console.WriteLine("1. Buscando produtos do repositório:");
        var produtos = _repo.BuscarTodos();
        foreach (var p in produtos)
            Console.WriteLine($"   [{p.Id}] {p.Nome} - R${p.Preco:N2}");

        Console.WriteLine("\n2. Enviando notificações:");
        await _notificacao.NotificarAsync("joao@empresa.com", "Pedido confirmado!");
        await _notificacao.NotificarAsync("maria@empresa.com", "Produto enviado!");

        Console.WriteLine("\n3. Adicionando produto:");
        _repo.Salvar(new Produto(4, "Monitor", 1800m));
        Console.WriteLine($"   Total de produtos: {_repo.BuscarTodos().Count()}");

        Console.WriteLine("\n=== LIFETIMES ===");
        Console.WriteLine("Singleton: EmailService — mesma instância sempre");
        Console.WriteLine("Transient: SmsService — nova instância por injeção");
        Console.WriteLine("Scoped:    Repository — mesma instância por request\n");

        _lifetime.StopApplication();
    }

    public Task StopAsync(CancellationToken cancellationToken)
    {
        _logger.LogInformation("Aplicação encerrada.");
        return Task.CompletedTask;
    }
}
