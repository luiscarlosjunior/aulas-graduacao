// ============================================================
// Operadores e Expressões em C# — .NET 8
//
// ============================================================
// POR QUE OPERADORES IMPORTAM NA INDÚSTRIA?
// ============================================================
// Operadores bem usados tornam o código mais LEGÍVEL e SEGURO:
//
// 1. ARITMÉTICOS: Cálculos financeiros precisam de cuidado.
//    Ex: calcular ICMS, descontos, juros compostos
//
// 2. LÓGICOS: Controle de acesso, regras de negócio, validações.
//    Ex: isAdmin && isActive — padrão em sistemas IAM (AWS, Azure AD)
//    Short-circuit: evita NullReferenceException em produção!
//    user != null && user.IsActive  ← se user é null, IsActive NÃO é avaliado
//
// 3. BITWISE: Permissões de sistema (Unix chmod), flags de configuração.
//    Ex: FileAccess.Read | FileAccess.Write (bit flags do próprio .NET!)
//
// 4. NULL OPERATORS (??, ??=, ?.): Essenciais em C# moderno.
//    Em quase todo método de serviço você verá: value ?? defaultValue
//
// 5. PATTERN MATCHING (switch expression): Elimina if/else aninhados.
//    C# 8+ e é o padrão em código moderno .NET.
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

// ============================================================
// EXEMPLO INDUSTRIAL 1: Cálculo de preço com impostos
// ============================================================
Console.WriteLine("\n=== EXEMPLO REAL: CÁLCULO DE NOTA FISCAL ===");

decimal precoBase     = 1000m;
decimal aliquotaICMS  = 0.12m;   // 12% ICMS (varia por estado)
decimal aliquotaIPI   = 0.05m;   // 5% IPI
decimal aliquotaPIS   = 0.0065m; // 0,65% PIS
decimal aliquotaCOFINS= 0.03m;   // 3% COFINS

// Operadores aritméticos em cálculo fiscal real
decimal icms   = precoBase * aliquotaICMS;
decimal ipi    = precoBase * aliquotaIPI;
decimal pis    = precoBase * aliquotaPIS;
decimal cofins = precoBase * aliquotaCOFINS;
decimal totalImpostos = icms + ipi + pis + cofins;
decimal precoFinal    = precoBase + totalImpostos;

Console.WriteLine($"  Preço base:       R$ {precoBase:N2}");
Console.WriteLine($"  ICMS ({aliquotaICMS:P0}):    R$ {icms:N2}");
Console.WriteLine($"  IPI  ({aliquotaIPI:P0}):    R$ {ipi:N2}");
Console.WriteLine($"  PIS  ({aliquotaPIS:P2}):  R$ {pis:N2}");
Console.WriteLine($"  COFINS ({aliquotaCOFINS:P0}):  R$ {cofins:N2}");
Console.WriteLine($"  Total impostos:   R$ {totalImpostos:N2}");
Console.WriteLine($"  Preço final NF:   R$ {precoFinal:N2}");

// ============================================================
// EXEMPLO INDUSTRIAL 2: Sistema de permissões com bit flags
// (Como o .NET usa internamente: FileAccess, FileShare, etc.)
// ============================================================
Console.WriteLine("\n=== EXEMPLO REAL: PERMISSÕES COM BIT FLAGS ===");

// Cada permissão é um bit — pode combinar várias com OR
const int PERM_LEITURA    = 0b0001;  // 1
const int PERM_ESCRITA    = 0b0010;  // 2
const int PERM_EXCLUSAO   = 0b0100;  // 4
const int PERM_ADMIN      = 0b1000;  // 8

// Criando perfis de permissão combinando flags
int perfilLeitura  = PERM_LEITURA;
int perfilEditor   = PERM_LEITURA | PERM_ESCRITA;
int perfilGerente  = PERM_LEITURA | PERM_ESCRITA | PERM_EXCLUSAO;
int perfilAdmin    = PERM_LEITURA | PERM_ESCRITA | PERM_EXCLUSAO | PERM_ADMIN;

// Verificando permissão com AND bitwise
bool podeEditar = (perfilGerente & PERM_ESCRITA) != 0;
bool ehAdmin    = (perfilGerente & PERM_ADMIN)   != 0;

Console.WriteLine($"  Perfil Gerente (binário): {Convert.ToString(perfilGerente, 2).PadLeft(4, '0')}");
Console.WriteLine($"  Pode editar: {podeEditar}");
Console.WriteLine($"  É admin:     {ehAdmin}");
Console.WriteLine($"  Perfil Admin (binário):   {Convert.ToString(perfilAdmin, 2).PadLeft(4, '0')}");

// Isso é exatamente como o .NET implementa FileAccess.Read | FileAccess.Write!

// ============================================================
// EXEMPLO INDUSTRIAL 3: Controle de acesso com operadores lógicos
// Pattern: IAM (Identity and Access Management) — AWS, Azure AD
// ============================================================
Console.WriteLine("\n=== EXEMPLO REAL: CONTROLE DE ACESSO IAM ===");

bool usuarioAtivo       = true;
bool emailVerificado    = true;
bool autenticadoMFA     = false;
bool ehAdministrador    = false;
string? departamento    = "Financeiro";

// Regras de acesso compostas — short-circuit evaluation é crucial aqui
// Se usuarioAtivo é false, as outras condições NÃO são avaliadas
bool podeAcessarSistema = usuarioAtivo && emailVerificado;
bool podeAcessarDados   = podeAcessarSistema && autenticadoMFA;
bool podeVerRelatorio   = podeAcessarSistema && 
                          (ehAdministrador || departamento == "Financeiro");

Console.WriteLine($"  Pode acessar sistema:      {podeAcessarSistema}");
Console.WriteLine($"  Pode acessar dados sensíveis: {podeAcessarDados} (exige MFA)");
Console.WriteLine($"  Pode ver relatório financeiro: {podeVerRelatorio}");

// Operador ternário em classificação de risco
decimal saldo = 15_000m;
string nivelRisco = saldo switch
{
    > 100_000m  => "Baixo",
    > 10_000m   => "Médio",
    > 1_000m    => "Alto",
    _           => "Crítico"
};
Console.WriteLine($"\n  Saldo R${saldo:N0} → Nível de risco: {nivelRisco}");

