// ============================================================
// Operadores e Expressões em C# — .NET 8
// ============================================================

Console.WriteLine("=== OPERADORES ARITMÉTICOS ===");
int a = 10, b = 3;

Console.WriteLine($"a = {a}, b = {b}");
Console.WriteLine($"Adição:        {a} + {b} = {a + b}");
Console.WriteLine($"Subtração:     {a} - {b} = {a - b}");
Console.WriteLine($"Multiplicação: {a} * {b} = {a * b}");
Console.WriteLine($"Divisão:       {a} / {b} = {a / b}  (inteiro! trunca)");
Console.WriteLine($"Módulo (resto):{a} % {b} = {a % b}");
Console.WriteLine($"Divisão real:  {(double)a} / {b} = {(double)a / b}");

// Operadores de incremento/decremento
int x = 5;
Console.WriteLine($"\nx = {x}");
Console.WriteLine($"x++ (pós): {x++} → x agora é {x}");
Console.WriteLine($"++x (pré): {++x} → x agora é {x}");
Console.WriteLine($"x-- (pós): {x--} → x agora é {x}");
Console.WriteLine($"--x (pré): {--x} → x agora é {x}");

Console.WriteLine("\n=== OPERADORES DE ATRIBUIÇÃO ===");
int n = 100;
Console.WriteLine($"n = {n}");
n += 10;  Console.WriteLine($"n += 10 → {n}");
n -= 5;   Console.WriteLine($"n -= 5  → {n}");
n *= 2;   Console.WriteLine($"n *= 2  → {n}");
n /= 3;   Console.WriteLine($"n /= 3  → {n}");
n %= 7;   Console.WriteLine($"n %%= 7  → {n}");

Console.WriteLine("\n=== OPERADORES RELACIONAIS (COMPARAÇÃO) ===");
int p = 10, q = 20;
Console.WriteLine($"p = {p}, q = {q}");
Console.WriteLine($"p == q: {p == q}");
Console.WriteLine($"p != q: {p != q}");
Console.WriteLine($"p >  q: {p > q}");
Console.WriteLine($"p <  q: {p < q}");
Console.WriteLine($"p >= q: {p >= q}");
Console.WriteLine($"p <= q: {p <= q}");

Console.WriteLine("\n=== OPERADORES LÓGICOS ===");
bool verdadeiro = true, falso = false;
Console.WriteLine($"true && true:   {verdadeiro && verdadeiro}");
Console.WriteLine($"true && false:  {verdadeiro && falso}");
Console.WriteLine($"true || false:  {verdadeiro || falso}");
Console.WriteLine($"false || false: {falso || falso}");
Console.WriteLine($"!true:          {!verdadeiro}");
Console.WriteLine($"!false:         {!falso}");

// Short-circuit evaluation
int contador = 0;
bool resultado = (5 > 3) || (++contador > 0); // contador NÃO é incrementado!
Console.WriteLine($"\nShort-circuit OR: contador = {contador} (não avaliou o lado direito)");

Console.WriteLine("\n=== OPERADOR TERNÁRIO ===");
int idade = 20;
string categoria = idade >= 18 ? "Adulto" : "Menor";
Console.WriteLine($"Idade {idade}: {categoria}");

// Ternário aninhado (use com moderação — prejudica legibilidade)
int nota = 75;
string conceito = nota >= 90 ? "A" : nota >= 70 ? "B" : nota >= 50 ? "C" : "F";
Console.WriteLine($"Nota {nota}: {conceito}");

Console.WriteLine("\n=== OPERADORES BITWISE ===");
int bits1 = 0b1100; // 12 em binário
int bits2 = 0b1010; // 10 em binário
Console.WriteLine($"bits1 = {bits1:D2} ({Convert.ToString(bits1, 2).PadLeft(4, '0')})");
Console.WriteLine($"bits2 = {bits2:D2} ({Convert.ToString(bits2, 2).PadLeft(4, '0')})");
Console.WriteLine($"AND (&): {bits1 & bits2} ({Convert.ToString(bits1 & bits2, 2).PadLeft(4, '0')})");
Console.WriteLine($"OR  (|): {bits1 | bits2} ({Convert.ToString(bits1 | bits2, 2).PadLeft(4, '0')})");
Console.WriteLine($"XOR (^): {bits1 ^ bits2} ({Convert.ToString(bits1 ^ bits2, 2).PadLeft(4, '0')})");
Console.WriteLine($"NOT (~): {~bits1}");
Console.WriteLine($"Shift left  (<<1): {bits1 << 1} (multiplica por 2)");
Console.WriteLine($"Shift right (>>1): {bits1 >> 1} (divide por 2)");

Console.WriteLine("\n=== OPERADORES NULL ===");
string? texto = null;

// Null-coalescing: ?? retorna valor da direita se esquerda for null
string resultado2 = texto ?? "valor padrão";
Console.WriteLine($"texto ?? \"valor padrão\": {resultado2}");

// Null-coalescing assignment: ??= atribui se for null
texto ??= "atribuído com ??=";
Console.WriteLine($"texto ??= ...: {texto}");

// Null-conditional: ?. não lança exceção se null
string? nulo = null;
int? tamanho = nulo?.Length; // null, sem exceção
Console.WriteLine($"nulo?.Length: {tamanho?.ToString() ?? "null"}");

string vivo = "C# é ótimo!";
Console.WriteLine($"vivo?.Length: {vivo?.Length}");

Console.WriteLine("\n=== OPERADOR IS E PATTERN MATCHING ===");
object obj = "Hello, World!";

// is verifica o tipo
if (obj is string str)
    Console.WriteLine($"É uma string com {str.Length} caracteres");

// Pattern matching com condições (when)
int valor = 42;
string classificacao = valor switch
{
    < 0                => "negativo",
    0                  => "zero",
    > 0 and < 10       => "pequeno",
    >= 10 and <= 100   => "médio",
    _                  => "grande" // _ é o wildcard (caso padrão)
};
Console.WriteLine($"Valor {valor} é: {classificacao}");

Console.WriteLine("\n=== PRECEDÊNCIA DE OPERADORES ===");
// Assim como na matemática, operadores têm precedência
int result1 = 2 + 3 * 4;       // 14 (multiplicação antes)
int result2 = (2 + 3) * 4;     // 20 (parênteses primeiro)
bool result3 = true || false && false; // true (&& tem precedência sobre ||)

Console.WriteLine($"2 + 3 * 4 = {result1}");
Console.WriteLine($"(2 + 3) * 4 = {result2}");
Console.WriteLine($"true || false && false = {result3}");
