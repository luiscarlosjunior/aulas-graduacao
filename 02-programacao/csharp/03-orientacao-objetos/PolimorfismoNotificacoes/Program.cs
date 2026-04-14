// ============================================================
// Polimorfismo em C# — Sistema de Notificações Multi-Canal
//
// ============================================================
// POR QUE POLIMORFISMO IMPORTA NA INDÚSTRIA?
// ============================================================
// Polimorfismo é o que torna sistemas EXTENSÍVEIS sem modificar
// código existente — fundamento do Open/Closed Principle (SOLID):
//
// PROBLEMA REAL: Sistema que envia notificações.
// Hoje: email. Amanhã: SMS. Depois: WhatsApp, Push, Slack, Teams...
//
// SEM polimorfismo:
//   void EnviarNotificacao(string tipo, string mensagem) {
//       if (tipo == "EMAIL")  { ... }         // modifica código existente!
//       else if (tipo == "SMS") { ... }        // risco de quebrar email
//       else if (tipo == "SLACK") { ... }     // if/else cresce infinito
//   }
//
// COM polimorfismo (interfaces):
//   foreach (var canal in canais)
//       canal.Enviar(msg);   // não sabe QUAL tipo, só chama o método
//   → Para adicionar WhatsApp: crie a classe, registre, pronto!
//   → Zero mudança no código existente.
//
// Onde isso aparece no mercado:
// - ASP.NET Core usa IHostedService para background services
// - Entity Framework usa IDbContextFactory para criar contextos
// - Dependency Injection usa IServiceCollection — mesma interface,
//   múltiplas implementações (Scoped, Singleton, Transient)
// - Serilog/NLog: múltiplos Sinks (arquivo, console, Seq, Elasticsearch)
// ============================================================

// ============================================================
// PROGRAMA PRINCIPAL
// ============================================================

Console.WriteLine("=== POLIMORFISMO C#: SISTEMA DE NOTIFICAÇÕES ===\n");

Console.WriteLine("--- Registrando canais ---");
var gerenciador = new GerenciadorNotificacoes();
gerenciador.AdicionarCanal(new NotificacaoEmail("smtp.empresa.com.br", 587));
gerenciador.AdicionarCanal(new NotificacaoSms("Twilio"));
gerenciador.AdicionarCanal(new NotificacaoPush("br.com.empresa.app"));
gerenciador.AdicionarCanal(new NotificacaoSlack("alertas-pedidos"));

// Notificação de pagamento confirmado
await gerenciador.NotificarTodosAsync(
    "joao@cliente.com",
    "✅ Pagamento Confirmado — Pedido #10472",
    "Seu pagamento de R$299,90 foi aprovado! Envio em até 2 dias úteis."
);

// Alerta interno de estoque
await gerenciador.NotificarTodosAsync(
    "ops-team@empresa.com",
    "⚠️ Estoque Crítico — FONE-BT-001",
    "Apenas 2 unidades restantes. Reposição urgente necessária."
);

Console.WriteLine("\n--- Open/Closed Principle em ação ---");
Console.WriteLine("  GerenciadorNotificacoes.NotificarTodosAsync() NUNCA muda.");
Console.WriteLine("  Para adicionar WhatsApp amanhã:");
Console.WriteLine("    1. class NotificacaoWhatsApp : IServicoNotificacao { ... }");
Console.WriteLine("    2. gerenciador.AdicionarCanal(new NotificacaoWhatsApp(...));");
Console.WriteLine("    ✅ Zero linhas alteradas no código existente!");
Console.WriteLine();
Console.WriteLine("--- Async/Await + Polimorfismo ---");
Console.WriteLine("  Task.WhenAll() chama EnviarAsync() em todos os canais em PARALELO.");
Console.WriteLine("  Mesmo método (EnviarAsync), comportamentos diferentes = POLIMORFISMO!");
Console.WriteLine("  Em produção: email pode demorar 300ms, SMS 50ms — o paralelo economiza tempo.");

Console.WriteLine("\n--- Sobrecarga (Overloading) — outro tipo de polimorfismo ---");
Console.WriteLine("  Overloading: mesmo nome de método, parâmetros diferentes.");
Console.WriteLine("  O compilador escolhe qual versão chamar pelos tipos dos argumentos:");
Console.WriteLine();

// Nota: funções locais em top-level statements NÃO suportam overloading em C#.
// Em classes normais, isso compila e funciona perfeitamente:
//
//   class Calculadora {
//       public static decimal Calcular(decimal a, decimal b) => a + b;      // para decimal
//       public static int     Calcular(int a, int b)     => a + b;          // para int
//       public static string  Calcular(string a, int n)  => a.PadLeft(n);   // para string
//   }
//
//   Console.WriteLine(Calculadora.Calcular(10.5m, 5m));  // chama versão decimal
//   Console.WriteLine(Calculadora.Calcular(10, 5));      // chama versão int
//   Console.WriteLine(Calculadora.Calcular("HA", 3));    // chama versão string
//
// Isso é polimorfismo em TEMPO DE COMPILAÇÃO (static dispatch).
// A versão de Java também demonstra isso na prática.

Console.WriteLine("  Exemplos de overloading no próprio .NET:");
Console.WriteLine($"  Console.WriteLine(42)     — versão int");
Console.WriteLine($"  Console.WriteLine(3.14)   — versão double");
Console.WriteLine($"  Console.WriteLine(true)   — versão bool");
Console.WriteLine($"  Console.WriteLine(\"texto\") — versão string");
Console.WriteLine("  São 18 sobrecargas de WriteLine — todas com o mesmo nome!");


// ============================================================
// CLASSES E INTERFACES DO SISTEMA
// ============================================================

// ============================================================
// INTERFACE: Define o CONTRATO de qualquer canal de notificação
// ============================================================

/// <summary>
/// Interface que qualquer canal de notificação deve implementar.
/// Código que usa esta interface não precisa saber qual implementação está por baixo.
/// </summary>
public interface IServicoNotificacao
{
    string Tipo { get; }  // "EMAIL", "SMS", etc.
    Task<bool> EnviarAsync(string destinatario, string assunto, string mensagem);
}

// ============================================================
// IMPLEMENTAÇÕES — cada uma conhece seu protocolo/API
// ============================================================

public class NotificacaoEmail : IServicoNotificacao
{
    public string Tipo => "EMAIL";
    private readonly string _servidorSmtp;
    private readonly int _porta;

    public NotificacaoEmail(string servidorSmtp, int porta)
    {
        _servidorSmtp = servidorSmtp;
        _porta = porta;
    }

    public async Task<bool> EnviarAsync(string destinatario, string assunto, string mensagem)
    {
        // Em produção: conectaria ao SMTP com MailKit/SendGrid/AWS SES
        await Task.Delay(50);  // simula I/O assíncrono
        Console.WriteLine($"  📧 EMAIL → {destinatario}");
        Console.WriteLine($"     Assunto: {assunto}");
        Console.WriteLine($"     SMTP: {_servidorSmtp}:{_porta}");
        return true;
    }
}

public class NotificacaoSms : IServicoNotificacao
{
    public string Tipo => "SMS";
    private readonly string _provedora;
    private const int LimiteSms = 160;

    public NotificacaoSms(string provedora) => _provedora = provedora;

    public async Task<bool> EnviarAsync(string destinatario, string assunto, string mensagem)
    {
        await Task.Delay(30);
        string texto = mensagem.Length > LimiteSms
            ? mensagem[..(LimiteSms - 3)] + "..."
            : mensagem;
        Console.WriteLine($"  📱 SMS → {destinatario} via {_provedora}");
        Console.WriteLine($"     Texto: {texto}");
        return true;
    }
}

public class NotificacaoPush : IServicoNotificacao
{
    public string Tipo => "PUSH";
    private readonly string _appId;

    public NotificacaoPush(string appId) => _appId = appId;

    public async Task<bool> EnviarAsync(string destinatario, string assunto, string mensagem)
    {
        await Task.Delay(20);
        Console.WriteLine($"  🔔 PUSH → {destinatario} (App: {_appId})");
        Console.WriteLine($"     Título: {assunto}");
        Console.WriteLine($"     Corpo: {(mensagem.Length > 50 ? mensagem[..47] + "..." : mensagem)}");
        return true;
    }
}

public class NotificacaoSlack : IServicoNotificacao
{
    public string Tipo => "SLACK";
    private readonly string _canal;

    public NotificacaoSlack(string canal) => _canal = canal;

    public async Task<bool> EnviarAsync(string destinatario, string assunto, string mensagem)
    {
        await Task.Delay(40);
        Console.WriteLine($"  💬 SLACK → canal #{_canal}");
        Console.WriteLine($"     *{assunto}*: {mensagem}");
        return true;
    }
}

// ============================================================
// GERENCIADOR: código que usa a interface — NUNCA sabe o tipo concreto
// ============================================================

/// <summary>
/// Gerencia múltiplos canais de notificação.
/// Usa IServicoNotificacao — não importa se é Email, SMS, Push ou Slack.
/// Para adicionar um novo canal: implemente a interface e registre aqui.
/// O código DESTE MÉTODO nunca precisa mudar!
/// </summary>
public class GerenciadorNotificacoes
{
    private readonly List<IServicoNotificacao> _servicos = [];

    public void AdicionarCanal(IServicoNotificacao servico)
    {
        _servicos.Add(servico);
        Console.WriteLine($"  ➕ Canal '{servico.Tipo}' registrado");
    }

    public async Task<int> NotificarTodosAsync(string destinatario, string assunto, string mensagem)
    {
        Console.WriteLine($"\n  🚀 Enviando para: {destinatario}");
        Console.WriteLine($"  {new string('─', 50)}");

        // Task.WhenAll: envia por TODOS os canais em PARALELO (async!)
        var tarefas = _servicos.Select(s => s.EnviarAsync(destinatario, assunto, mensagem));
        bool[] resultados = await Task.WhenAll(tarefas);

        int sucessos = resultados.Count(r => r);
        Console.WriteLine($"  Resultado: {sucessos}/{_servicos.Count} canais com sucesso");
        return sucessos;
    }
}

// ============================================================
// PROGRAMA PRINCIPAL
