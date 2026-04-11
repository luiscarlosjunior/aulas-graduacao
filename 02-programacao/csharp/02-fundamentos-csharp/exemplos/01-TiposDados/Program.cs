// ============================================================
// Tipos de Dados em C# — .NET 8
// Demonstra os tipos fundamentais da linguagem
// ============================================================

Console.WriteLine("=== TIPOS INTEIROS ===");
byte    valorByte   = 255;           // 0 a 255 (8 bits, sem sinal)
sbyte   valorSByte  = -128;          // -128 a 127
short   valorShort  = 32_767;        // _ é separador de milhar (legibilidade)
int     valorInt    = 2_147_483_647; // Tipo inteiro mais comum
long    valorLong   = 9_223_372_036_854_775_807L; // L indica long literal
uint    valorUInt   = 4_294_967_295U;
ulong   valorULong  = 18_446_744_073_709_551_615UL;

Console.WriteLine($"byte:  {valorByte}");
Console.WriteLine($"int:   {valorInt}");
Console.WriteLine($"long:  {valorLong}");

Console.WriteLine("\n=== TIPOS DE PONTO FLUTUANTE ===");
float   valorFloat   = 3.14f;           // 7 dígitos de precisão (f obrigatório)
double  valorDouble  = 3.14159265358979; // 15-17 dígitos (padrão para cálculos)
decimal valorDecimal = 3.14159265358979323846m; // 28-29 dígitos (use para DINHEIRO)

Console.WriteLine($"float:   {valorFloat}");
Console.WriteLine($"double:  {valorDouble}");
Console.WriteLine($"decimal: {valorDecimal}");

// IMPORTANTE: float e double têm imprecisão!
Console.WriteLine($"\nProblema com double: {0.1 + 0.2}");          // 0.30000000000000004
Console.WriteLine($"Correto com decimal: {0.1m + 0.2m}");          // 0.3

Console.WriteLine("\n=== OUTROS TIPOS PRIMITIVOS ===");
bool   ativo   = true;
char   letra   = 'A';
string nome    = "C# .NET";

Console.WriteLine($"bool:   {ativo}");
Console.WriteLine($"char:   {letra} (código Unicode: {(int)letra})");
Console.WriteLine($"string: {nome}");

Console.WriteLine("\n=== VAR E TYPE INFERENCE ===");
// var: o compilador infere o tipo — ainda é fortemente tipado!
var numero    = 42;          // int
var pi        = 3.14;        // double
var mensagem  = "Olá";       // string
var hoje      = DateTime.Now; // DateTime

Console.WriteLine($"var numero:   {numero.GetType().Name} = {numero}");
Console.WriteLine($"var pi:       {pi.GetType().Name} = {pi}");
Console.WriteLine($"var mensagem: {mensagem.GetType().Name} = {mensagem}");
Console.WriteLine($"var hoje:     {hoje.GetType().Name} = {hoje:dd/MM/yyyy}");

Console.WriteLine("\n=== NULLABLE TYPES (int?, string?) ===");
// Tipos value normalmente não podem ser null — nullable resolve isso
int?    idadeOpcional  = null;
double? salarioOpcional = null;
string? emailOpcional   = null; // Com #nullable enable, string já pode ser null

idadeOpcional = 25;

// HasValue e Value
if (idadeOpcional.HasValue)
    Console.WriteLine($"Idade: {idadeOpcional.Value}");

// Null-coalescing operator: ?? retorna valor padrão se null
double salario = salarioOpcional ?? 0.0;
Console.WriteLine($"Salário (null-coalescing): {salario}");

// Null-coalescing assignment: ??=
emailOpcional ??= "sem-email@exemplo.com";
Console.WriteLine($"Email: {emailOpcional}");

Console.WriteLine("\n=== CONVERSÕES ===");
// Conversão implícita (sem perda de dados)
int    intVal    = 100;
long   longVal   = intVal;   // OK: int cabe em long
double doubleVal = intVal;   // OK: int cabe em double

Console.WriteLine($"int -> long: {longVal}");
Console.WriteLine($"int -> double: {doubleVal}");

// Conversão explícita (cast) — pode perder dados
double pi2    = 3.99;
int    piInt  = (int)pi2;   // Trunca! perde 0.99
Console.WriteLine($"double {pi2} -> int (cast): {piInt}");

// Convert — mais seguro, lança exceção em overflow
string numeroStr = "42";
int    numeroConv = Convert.ToInt32(numeroStr);
Console.WriteLine($"string \"42\" -> int: {numeroConv}");

// TryParse — mais seguro ainda, não lança exceção
bool ok = int.TryParse("abc", out int resultado);
Console.WriteLine($"TryParse(\"abc\"): sucesso={ok}, valor={resultado}");

Console.WriteLine("\n=== STRINGS — OPERAÇÕES ESSENCIAIS ===");
string texto = "  Olá, Mundo do .NET!  ";

Console.WriteLine($"Original:     '{texto}'");
Console.WriteLine($"Trim:         '{texto.Trim()}'");
Console.WriteLine($"ToUpper:      '{texto.Trim().ToUpper()}'");
Console.WriteLine($"ToLower:      '{texto.Trim().ToLower()}'");
Console.WriteLine($"Length:       {texto.Trim().Length}");
Console.WriteLine($"Contains:     {texto.Contains("Mundo")}");
Console.WriteLine($"Replace:      '{texto.Trim().Replace("Mundo", "C#")}'");
Console.WriteLine($"Substring(7): '{texto.Trim().Substring(7)}'");
Console.WriteLine($"Split:        {string.Join("|", texto.Trim().Split(' '))}");
Console.WriteLine($"StartsWith:   {texto.Trim().StartsWith("Olá")}");
Console.WriteLine($"IndexOf:      {texto.Trim().IndexOf("Mundo")}");

// Interpolação de strings
string produto = "Notebook";
decimal preco  = 3_500.99m;
Console.WriteLine($"\nInterpolação: {produto} custa R$ {preco:N2}");

// Verbatim string — não processa escape sequences
string caminho = @"C:\Users\Usuario\Documents\arquivo.txt";
Console.WriteLine($"Verbatim: {caminho}");

// Raw string literal (C# 11+)
string json = """
    {
        "nome": "João",
        "idade": 30
    }
    """;
Console.WriteLine($"Raw string:\n{json}");

Console.WriteLine("\n=== OBJECT E DYNAMIC ===");
// object é a base de todos os tipos em .NET
object obj = 42;
Console.WriteLine($"object: {obj} (tipo: {obj.GetType().Name})");

obj = "agora sou string";
Console.WriteLine($"object: {obj} (tipo: {obj.GetType().Name})");

// dynamic: verificação de tipo em tempo de EXECUÇÃO (use com cautela)
dynamic dyn = 100;
Console.WriteLine($"dynamic: {dyn}");
dyn = "virei string";
Console.WriteLine($"dynamic: {dyn}");
