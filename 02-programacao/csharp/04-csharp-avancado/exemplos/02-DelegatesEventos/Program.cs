// ============================================================
// Delegates, Events, Action, Func e Lambda em C# — .NET 8
// ============================================================

Console.WriteLine("=== DELEGATE — PONTEIRO PARA MÉTODO ===");

// Funções locais para atribuir ao delegate
static int  FnSomar(int a, int b) => a + b;
static int  FnSubtrair(int a, int b) => a - b;
static void FnLogConsole(string msg) => Console.WriteLine($"[Console] {msg}");
static void FnLogArquivo(string msg) => Console.WriteLine($"[Arquivo] {msg}");

Operacao op = FnSomar;
Console.WriteLine($"Somar(10, 3) = {op(10, 3)}");

op = FnSubtrair;
Console.WriteLine($"Subtrair(10, 3) = {op(10, 3)}");

// Multicast delegate — chama múltiplos métodos
Notificacao notif = FnLogConsole;
notif += FnLogArquivo;
notif("Sistema iniciado");

notif -= FnLogArquivo;
notif("Apenas console agora");

Console.WriteLine("\n=== ACTION — DELEGATE VOID ===");
Action<string>      imprimir = msg => Console.WriteLine($">> {msg}");
Action<int, int>    somar    = (a, b) => Console.WriteLine($"{a} + {b} = {a + b}");
Action              semParam = () => Console.WriteLine("Sem parâmetros");

imprimir("Olá, Action!");
somar(5, 3);
semParam();

void ProcessarLista(List<int> lista, Action<int> callback)
{
    foreach (var item in lista)
        callback(item);
}

var numeros = new List<int> { 1, 2, 3, 4, 5 };
ProcessarLista(numeros, n => Console.Write($"{n * 2} "));
Console.WriteLine();

Console.WriteLine("\n=== FUNC — DELEGATE COM RETORNO ===");
Func<int, int, int>  soma2     = (a, b) => a + b;
Func<string, int>    tamanho   = s => s.Length;
Func<int, bool>      ehPar     = n => n % 2 == 0;
Func<double, double> quadrado  = x => x * x;

Console.WriteLine($"soma(10, 5) = {soma2(10, 5)}");
Console.WriteLine($"tamanho(\"hello\") = {tamanho("hello")}");
Console.WriteLine($"ehPar(42) = {ehPar(42)}");
Console.WriteLine($"quadrado(3.5) = {quadrado(3.5)}");

Func<int, int> dobrar      = x => x * 2;
Func<int, int> incrementar = x => x + 1;
Func<int, int> dobrarEIncrementar = x => incrementar(dobrar(x));
Console.WriteLine($"dobrarEIncrementar(5) = {dobrarEIncrementar(5)}");

Console.WriteLine("\n=== PREDICATE<T> ===");
Predicate<string> ehVazio    = s => string.IsNullOrEmpty(s);
Predicate<int>    ehPositivo = n => n > 0;

Console.WriteLine($"ehVazio(\"\") = {ehVazio("")}");
Console.WriteLine($"ehPositivo(-5) = {ehPositivo(-5)}");

var lista2    = new List<int> { -3, -1, 0, 2, 4, 6 };
var positivos = lista2.FindAll(ehPositivo);
Console.WriteLine($"Positivos: [{string.Join(", ", positivos)}]");

Console.WriteLine("\n=== LAMBDA EXPRESSIONS ===");
Func<int, int> dobro = x => x * 2;

Func<int, string> classificar = n =>
{
    if (n < 0) return "negativo";
    if (n == 0) return "zero";
    return "positivo";
};

Console.WriteLine($"dobro(7) = {dobro(7)}");
Console.WriteLine($"classificar(-5) = {classificar(-5)}");

// Closure — captura variável do escopo externo
int multiplicador = 3;
Func<int, int> multiplicarPor = x => x * multiplicador;
Console.WriteLine($"multiplicarPor(5) = {multiplicarPor(5)}");

multiplicador = 10;
Console.WriteLine($"multiplicarPor(5) após mudar multiplicador = {multiplicarPor(5)}");

Console.WriteLine("\n=== EVENTS — EVENTOS ===");
var botao = new BotaoClique();

botao.Clicado += (sender, args) =>
    Console.WriteLine("Handler 1: Botão foi clicado!");
botao.Clicado += (sender, args) =>
    Console.WriteLine("Handler 2: Também reagi ao clique!");
botao.ClicadoComMensagem += (sender, msg) =>
    Console.WriteLine($"Mensagem do evento: {msg}");

botao.Clicar();
botao.ClicarComMensagem("Olá de um evento!");

Console.WriteLine("\n=== EXTENSION METHODS ===");
Console.WriteLine($"\"user@email.com\".EhEmail() = {"user@email.com".EhEmail()}");
Console.WriteLine($"\"joao da silva\".CapitalizarPalavras() = {"joao da silva".CapitalizarPalavras()}");
Console.WriteLine($"Ocorrências de 'a' em \"banana\": {"banana".ContarOcorrencias('a')}");

// ============================================================
// TIPOS — devem vir após todos os top-level statements
// ============================================================

// Delegates (type declarations — devem vir depois do código executável)
delegate int  Operacao(int a, int b);
delegate void Notificacao(string mensagem);

public class BotaoClique
{
    public event EventHandler? Clicado;
    public event EventHandler<string>? ClicadoComMensagem;

    public void Clicar()
    {
        Console.WriteLine("[Botão] Click detectado!");
        Clicado?.Invoke(this, EventArgs.Empty);
    }

    public void ClicarComMensagem(string msg)
    {
        ClicadoComMensagem?.Invoke(this, msg);
    }
}

public static class StringExtensions
{
    public static bool EhEmail(this string s) =>
        s.Contains('@') && s.Contains('.');

    public static string CapitalizarPalavras(this string s) =>
        string.Join(" ", s.Split(' ').Select(p =>
            p.Length > 0 ? char.ToUpper(p[0]) + p[1..].ToLower() : p));

    public static int ContarOcorrencias(this string s, char c) =>
        s.Count(x => x == c);
}
