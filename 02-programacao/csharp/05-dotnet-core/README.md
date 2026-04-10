# 05 — .NET Core: Dependency Injection, Host e Configuration

> Esta seção cobre a infraestrutura fundamental de qualquer aplicação .NET moderna. Se você quer trabalhar com ASP.NET Core ou qualquer serviço profissional, precisará dominar esses conceitos.

---

## Dependency Injection (DI)

**Injeção de Dependência** é o padrão mais importante do .NET moderno. Em vez de criar objetos dentro das classes, você os **declara como dependências** e o framework os fornece.

### Sem DI (problema)
```csharp
public class PedidoService
{
    // Acoplamento forte: difícil de testar, trocar implementação, etc.
    private readonly EmailService _email = new EmailService();
    private readonly SmsService   _sms   = new SmsService();
}
```

### Com DI (solução)
```csharp
public class PedidoService
{
    private readonly IEmailService _email; // Depende da ABSTRAÇÃO
    private readonly ISmsService   _sms;

    // Injeção pelo construtor — padrão recomendado
    public PedidoService(IEmailService email, ISmsService sms)
    {
        _email = email;
        _sms   = sms;
    }
}
```

---

## IServiceCollection — Registrando Serviços

```csharp
var builder = Host.CreateDefaultBuilder(args)
    .ConfigureServices(services =>
    {
        // Singleton: mesma instância durante toda a vida da aplicação
        services.AddSingleton<ICacheService, MemoryCacheService>();

        // Scoped: uma instância por request (ideal para DbContext)
        services.AddScoped<IUserRepository, UserRepository>();
        services.AddScoped<IPedidoService, PedidoService>();

        // Transient: nova instância a cada injeção
        services.AddTransient<IEmailService, SmtpEmailService>();

        // Registrar implementação diretamente (sem interface)
        services.AddSingleton<ConfiguracaoApp>();
    });
```

### Quando usar cada lifetime?

| Lifetime | Instância | Quando usar |
|----------|-----------|-------------|
| **Singleton** | 1 por aplicação | Cache, configuração, clientes HTTP stateless |
| **Scoped** | 1 por request | DbContext, Unit of Work, repositórios |
| **Transient** | Nova a cada uso | Serviços leves, stateless |

> **Cuidado!** Injetar um Scoped em um Singleton causa erro em produção (captive dependency). O ASP.NET Core valida isso no startup.

---

## Generic Host

O **Generic Host** é o container principal de qualquer aplicação .NET:

```csharp
var host = Host.CreateDefaultBuilder(args)
    .ConfigureServices(services =>
    {
        services.AddHostedService<MeuServico>();
        // ... outros serviços
    })
    .Build();

await host.RunAsync();

// Serviço em background
public class MeuServico : BackgroundService
{
    protected override async Task ExecuteAsync(CancellationToken stoppingToken)
    {
        while (!stoppingToken.IsCancellationRequested)
        {
            Console.WriteLine("Processando...");
            await Task.Delay(1000, stoppingToken);
        }
    }
}
```

---

## Configuration (appsettings.json)

```json
// appsettings.json
{
  "ConnectionStrings": {
    "Default": "Server=localhost;Database=MeuBanco;..."
  },
  "EmailConfig": {
    "Host": "smtp.gmail.com",
    "Porta": 587,
    "UsarTLS": true
  },
  "Logging": {
    "LogLevel": {
      "Default": "Information"
    }
  }
}
```

```csharp
// Acessando configuração
var host = Host.CreateDefaultBuilder(args)
    .ConfigureServices((context, services) =>
    {
        // Options pattern — mapeia seção para classe
        services.Configure<EmailConfig>(
            context.Configuration.GetSection("EmailConfig"));
    });

// Na classe que usa:
public class EmailService
{
    private readonly EmailConfig _config;

    public EmailService(IOptions<EmailConfig> options)
    {
        _config = options.Value;
    }
}
```

---

## Logging com ILogger

```csharp
public class PedidoService
{
    private readonly ILogger<PedidoService> _logger;

    public PedidoService(ILogger<PedidoService> logger)
        => _logger = logger;

    public void ProcessarPedido(int id)
    {
        _logger.LogInformation("Processando pedido {PedidoId}", id);

        try
        {
            // ... lógica
            _logger.LogDebug("Pedido {PedidoId} validado com sucesso", id);
        }
        catch (Exception ex)
        {
            _logger.LogError(ex, "Erro ao processar pedido {PedidoId}", id);
            throw;
        }
    }
}
```

Níveis de log: `Trace` < `Debug` < `Information` < `Warning` < `Error` < `Critical`

---

## Exemplo Prático

O exemplo [`01-DependencyInjection/`](./exemplos/01-DependencyInjection/) demonstra:

- Registro de serviços com Singleton, Scoped e Transient
- Injeção de dependências por construtor
- Hosted Service com `IHostedService`
- Logging com `ILogger`

```bash
cd exemplos/01-DependencyInjection
dotnet run
```

---

## Exercícios

1. Crie uma aplicação com `BackgroundService` que processa uma fila de pedidos a cada 5 segundos
2. Implemente o Options Pattern: crie uma classe `DatabaseConfig` e leia-a do `appsettings.json`
3. Registre um serviço de duas formas: como `AddSingleton` e `AddTransient`. Observe a diferença no número de instâncias criadas
4. Crie um `IHttpClientFactory` customizado para chamadas a uma API externa
5. Implemente um serviço de auditoria que usa `ILogger` para registrar todas as operações

---

**Seção anterior:** [04 — C# Avançado](../04-csharp-avancado/)  
**Próxima seção:** [06 — ASP.NET Core](../06-aspnet-core/)
