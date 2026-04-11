// ============================================================
// Controle de Fluxo em C# — .NET 8
// ============================================================

Console.WriteLine("=== IF / ELSE ===");
int temperatura = 28;

if (temperatura > 35)
    Console.WriteLine("Muito quente!");
else if (temperatura > 25)
    Console.WriteLine($"Quente ({temperatura}°C) — use protetor solar");
else if (temperatura > 15)
    Console.WriteLine("Agradável");
else
    Console.WriteLine("Frio!");

Console.WriteLine("\n=== SWITCH / CASE CLÁSSICO ===");
int diaSemana = DateTime.Now.DayOfWeek switch
{
    DayOfWeek.Monday    => 2,
    DayOfWeek.Tuesday   => 3,
    DayOfWeek.Wednesday => 4,
    DayOfWeek.Thursday  => 5,
    DayOfWeek.Friday    => 6,
    DayOfWeek.Saturday  => 7,
    _                   => 1
};

// switch statement tradicional
switch (diaSemana)
{
    case 1:
        Console.WriteLine("Domingo");
        break;
    case 2:
        Console.WriteLine("Segunda-feira");
        break;
    case 6:
    case 7:
        Console.WriteLine("Fim de semana!");
        break;
    default:
        Console.WriteLine($"Dia {diaSemana} da semana");
        break;
}

Console.WriteLine("\n=== SWITCH EXPRESSION (C# 8+) ===");
// Switch expression — mais conciso e retorna valor
string nomeDia = DateTime.Now.DayOfWeek switch
{
    DayOfWeek.Sunday    => "Domingo",
    DayOfWeek.Monday    => "Segunda-feira",
    DayOfWeek.Tuesday   => "Terça-feira",
    DayOfWeek.Wednesday => "Quarta-feira",
    DayOfWeek.Thursday  => "Quinta-feira",
    DayOfWeek.Friday    => "Sexta-feira",
    DayOfWeek.Saturday  => "Sábado",
    _                   => "Desconhecido"
};
Console.WriteLine($"Hoje é: {nomeDia}");

// Switch com pattern matching e guards (when)
int[] notas = { 95, 80, 65, 45, 30 };
foreach (int nota in notas)
{
    string conceito = nota switch
    {
        >= 90           => "Excelente (A)",
        >= 70           => "Bom (B)",
        >= 50           => "Regular (C)",
        >= 30           => "Insuficiente (D)",
        _               => "Reprovado (F)"
    };
    Console.WriteLine($"Nota {nota}: {conceito}");
}

Console.WriteLine("\n=== LOOP FOR ===");
// For clássico: inicialização; condição; incremento
for (int i = 1; i <= 5; i++)
    Console.Write($"{i} ");
Console.WriteLine();

// For decrescente
for (int i = 10; i >= 1; i -= 2)
    Console.Write($"{i} ");
Console.WriteLine();

// For com múltiplas variáveis
for (int i = 0, j = 10; i < j; i++, j--)
    Console.Write($"({i},{j}) ");
Console.WriteLine();

Console.WriteLine("\n=== LOOP FOREACH ===");
string[] frutas = { "Maçã", "Banana", "Laranja", "Uva", "Manga" };
foreach (string fruta in frutas)
    Console.Write($"{fruta} ");
Console.WriteLine();

// Foreach com índice usando índice manual
for (int i = 0; i < frutas.Length; i++)
    Console.Write($"[{i}]{frutas[i]} ");
Console.WriteLine();

Console.WriteLine("\n=== LOOP WHILE ===");
int numero = 1;
while (numero <= 5)
{
    Console.Write($"{numero} ");
    numero++;
}
Console.WriteLine();

// Lê entrada até receber "sair" — exemplo conceitual (não executa aqui)
// while (Console.ReadLine() != "sair") { ... }

Console.WriteLine("\n=== LOOP DO-WHILE ===");
// do-while executa pelo menos uma vez
int tentativa = 0;
do
{
    tentativa++;
    Console.WriteLine($"Tentativa {tentativa}");
} while (tentativa < 3);

Console.WriteLine("\n=== BREAK E CONTINUE ===");
Console.Write("Break ao encontrar 5: ");
for (int i = 1; i <= 10; i++)
{
    if (i == 5) break;
    Console.Write($"{i} ");
}
Console.WriteLine();

Console.Write("Continue (pula pares): ");
for (int i = 1; i <= 10; i++)
{
    if (i % 2 == 0) continue;
    Console.Write($"{i} ");
}
Console.WriteLine();

Console.WriteLine("\n=== LOOPS ANINHADOS COM LABELED BREAK ===");
// Tabuada do 1 ao 3
for (int i = 1; i <= 3; i++)
{
    for (int j = 1; j <= 5; j++)
        Console.Write($"{i}x{j}={i*j} ");
    Console.WriteLine();
}

Console.WriteLine("\n=== PATTERN MATCHING AVANÇADO (C# 9+) ===");
object[] objetos = { 42, "hello", 3.14, true, null!, new int[] { 1, 2, 3 } };

foreach (object obj in objetos)
{
    string descricao = obj switch
    {
        int n when n > 0    => $"Inteiro positivo: {n}",
        int n               => $"Inteiro não-positivo: {n}",
        string s when s.Length > 3 => $"String longa: '{s}'",
        string s            => $"String curta: '{s}'",
        double d            => $"Double: {d:F2}",
        bool b              => $"Boolean: {b}",
        null                => "Nulo!",
        int[] arr           => $"Array de int com {arr.Length} elementos",
        _                   => $"Outro tipo: {obj.GetType().Name}"
    };
    Console.WriteLine($"  {descricao}");
}

Console.WriteLine("\n=== GOTO (RARAMENTE USADO) ===");
int contador = 0;
inicio:
    contador++;
    if (contador < 3)
        goto inicio;
Console.WriteLine($"Contador com goto: {contador}");
