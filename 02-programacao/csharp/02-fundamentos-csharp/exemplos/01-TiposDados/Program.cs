// ============================================================
// Tipos de Dados em C# — .NET 8
//
// ============================================================
// POR QUE TIPOS DE DADOS IMPORTAM NA INDÚSTRIA?
// ============================================================
// Escolher o tipo certo evita bugs silenciosos e prejuízo real:
//
// ❌ double preco = 0.1 + 0.2;  → 0.30000000000000004 (BUG!)
//    Em produção: um sistema bancário com double pode acumular
//    erros de centavos em milhões de transações diárias.
//
// ✅ decimal preco = 0.1m + 0.2m; → 0.3 (CORRETO)
//    Regra de ouro: SEMPRE use decimal para valores monetários.
//
// Além disso:
// - int? (nullable) é padrão em APIs REST — campo pode vir null do JSON
// - long é necessário para IDs de sistemas com bilhões de registros
//   (Twitter usava int e esgotou os IDs em 2009!)
// - var melhora legibilidade sem perder a segurança de tipos
// - string? com #nullable enable previne NullReferenceException
//   que é a exception mais comum em produção em sistemas .NET
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

// ============================================================
// EXEMPLO INDUSTRIAL: Sistema de Pedidos — tipos certos
// ============================================================
Console.WriteLine("\n=== EXEMPLO REAL: PEDIDO DE E-COMMERCE ===");

long   pedidoId       = 9_876_543_210L;     // long: bilhões de pedidos (Amazon faz isso)
string clienteNome    = "Maria Oliveira";
decimal subtotal      = 1_299.90m;           // decimal: nunca double para dinheiro!
decimal desconto      = 0.15m;               // 15% de desconto
decimal valorDesconto = subtotal * desconto;
decimal total         = subtotal - valorDesconto;
bool   fretGratis     = total > 299m;        // bool: regra de negócio simples
int    quantItens     = 3;
string? cupomAplicado = null;               // nullable: cupom pode não existir

Console.WriteLine($"Pedido #: {pedidoId}");
Console.WriteLine($"Cliente:  {clienteNome}");
Console.WriteLine($"Itens:    {quantItens}");
Console.WriteLine($"Subtotal: R$ {subtotal:N2}");
Console.WriteLine($"Desconto: R$ {valorDesconto:N2} ({desconto:P0})");
Console.WriteLine($"Total:    R$ {total:N2}");
Console.WriteLine($"Frete grátis: {fretGratis}");
Console.WriteLine($"Cupom: {cupomAplicado ?? "Nenhum"}");  // ?? é null-coalescing

// Mostrando o problema clássico de double com dinheiro
Console.WriteLine("\n--- Por que NÃO usar double para dinheiro? ---");
double doubleErro   = 0.1 + 0.2;
decimal decimalCerto = 0.1m + 0.2m;
Console.WriteLine($"double  0.1 + 0.2 = {doubleErro}");   // 0.30000000000000004
Console.WriteLine($"decimal 0.1 + 0.2 = {decimalCerto}");  // 0.3
Console.WriteLine("→ Em 1 milhão de transações por dia, o erro em double se acumula!");

// ============================================================
// NULLABLE TYPES: Campos opcionais em APIs e banco de dados
// ============================================================
Console.WriteLine("\n=== NULLABLE TYPES — APIS E BANCO DE DADOS ===");
// Quando você recebe JSON de uma API, campos podem ser null:
// { "nome": "João", "telefone": null, "dataNascimento": null }

string  nomeObrigatorio    = "João Silva";      // não pode ser null
string? telefoneOpcional   = null;              // pode ser null (campo opcional)
int?    idadeApiOpcional   = null;              // int não aceita null, mas int? sim
DateTime? dataAniversario  = null;

// Null-conditional operator: ?. — evita NullReferenceException
int? tamanhoTelefone = telefoneOpcional?.Length;
Console.WriteLine($"Nome: {nomeObrigatorio}");
Console.WriteLine($"Telefone: {telefoneOpcional ?? "Não informado"}");
Console.WriteLine($"Tamanho do telefone: {tamanhoTelefone?.ToString() ?? "N/A"}");

// Operador ??= — atribuição condicional a null (muito útil em inicialização)
telefoneOpcional ??= "Sem telefone";
Console.WriteLine($"Após ??=: {telefoneOpcional}");

// ============================================================
// TryParse: validação robusta de entrada do usuário / API
// ============================================================
Console.WriteLine("\n=== TRYPARSE — VALIDAÇÃO ROBUSTA ===");
// Em sistemas reais, nunca faça int.Parse() direto — pode lançar exceção!
// Use TryParse para validar dados vindos de usuários ou APIs externas

string[] entradasAPI = { "42", "abc", "99999999", "3.14", "" };

foreach (string entrada in entradasAPI)
{
    if (int.TryParse(entrada, out int valor))
        Console.WriteLine($"  '{entrada}' → int válido: {valor}");
    else
        Console.WriteLine($"  '{entrada}' → inválido, ignorado (sem exceção!)");
}

