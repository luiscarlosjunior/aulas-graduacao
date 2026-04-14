// ============================================================
// Controle de Fluxo em C# — .NET 8
//
// ============================================================
// POR QUE CONTROLE DE FLUXO IMPORTA NA INDÚSTRIA?
// ============================================================
// Controle de fluxo bem escrito é o que separa código amador
// de código profissional:
//
// 1. EARLY RETURN (retorno antecipado): Padrão fundamental em
//    Clean Code. Evita o "arrow code" — if aninhados que crescem
//    para a direita e se tornam impossíveis de manter.
//    Todo desenvolvedor sênior usa early return por default.
//
// 2. SWITCH EXPRESSION (C# 8+): Substitui if/else em cascata e
//    é usado em 100% do código C# moderno para roteamento,
//    mapeamento de status, e lógica de estados.
//
// 3. PATTERN MATCHING (C# 9+): Permite desestruturar objetos e
//    fazer múltiplas verificações em uma sintaxe limpa.
//    É a base do CQRS pattern em DDD moderno.
//
// 4. FOR / FOREACH: Processamento em lote é o core de sistemas
//    ERP, ETL, filas de mensagens (RabbitMQ, Kafka).
//
// 5. WHILE com retry: Padrão fundamental em microsserviços para
//    lidar com falhas temporárias de rede (exponential backoff).
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

// ============================================================
// EXEMPLO INDUSTRIAL 1: Análise de Crédito com Early Return
// (Anti-padrão vs. padrão Clean Code)
// ============================================================
Console.WriteLine("\n=== EXEMPLO REAL: ANÁLISE DE CRÉDITO (Early Return) ===");

// ANTI-PADRÃO: Arrow code — if aninhados que crescem para a direita
// static bool AnalisarCreditoArrow(decimal renda, int score, bool possuiRestricoes) {
//     if (renda > 0) {
//         if (!possuiRestricoes) {
//             if (score >= 300) {
//                 return true; // muito aninhado!
//             }
//         }
//     }
//     return false;
// }

// PADRÃO PROFISSIONAL: Early Return — cada condição de falha sai imediatamente
string AnalisarCredito(decimal renda, int score, bool possuiRestricoes, decimal valorSolicitado)
{
    // Guard clauses — cada "portão" valida uma condição
    if (renda <= 0)
        return "❌ Reprovado: renda inválida";
    if (possuiRestricoes)
        return "❌ Reprovado: cliente com restrições no CPF";
    if (score < 300)
        return $"❌ Reprovado: score {score} abaixo do mínimo (300)";

    // Lógica de aprovação (chegou aqui = passou todos os gates)
    decimal limiteAprovado = score switch
    {
        >= 800 => renda * 12,   // excelente: 12x a renda
        >= 600 => renda * 8,    // bom: 8x
        >= 400 => renda * 4,    // regular: 4x
        _      => renda * 1.5m  // baixo mas acima do mínimo: 1.5x
    };

    if (valorSolicitado > limiteAprovado)
        return $"⚠️  Aprovado parcialmente: limite é R${limiteAprovado:N0}, solicitado R${valorSolicitado:N0}";

    return $"✅ Aprovado: R${valorSolicitado:N0} | Score: {score} | Limite disponível: R${limiteAprovado:N0}";
}

Console.WriteLine(AnalisarCredito(0m,      750, false, 10_000m));
Console.WriteLine(AnalisarCredito(5000m,   750, true,  10_000m));
Console.WriteLine(AnalisarCredito(5000m,   200, false, 10_000m));
Console.WriteLine(AnalisarCredito(5000m,   850, false, 10_000m));
Console.WriteLine(AnalisarCredito(5000m,   650, false, 50_000m));

// ============================================================
// EXEMPLO INDUSTRIAL 2: Roteamento de Pagamento com Switch Expression
// (Gateway de Pagamento — Cielo, Rede, PagSeguro, Stripe)
// ============================================================
Console.WriteLine("\n=== EXEMPLO REAL: ROTEAMENTO DE PAGAMENTO ===");
// (record Pagamento está declarado no final do arquivo, após todo o código top-level)

string ProcessarPagamento(Pagamento pag)
{
    // Switch expression sobre múltiplas propriedades — C# 8+
    var (gateway, taxa, prazoRecebimento) = pag.Metodo switch
    {
        "PIX"     => ("Banco Central", 0.0m,   "Imediato"),
        "CREDITO" when pag.Parcelas == 1  => ("Cielo",  0.0199m, "D+30"),
        "CREDITO" when pag.Parcelas <= 6  => ("Cielo",  0.0299m, "D+30"),
        "CREDITO" => ("Cielo",  0.0399m, "D+30"),
        "DEBITO"  => ("Rede",   0.0149m, "D+1"),
        "BOLETO"  => ("Bradesco", 0.0m,  "D+3 após pagamento"),
        _         => throw new InvalidOperationException($"Método '{pag.Metodo}' não suportado")
    };

    decimal taxaValor = pag.Valor * taxa;
    decimal liquido   = pag.Valor - taxaValor;

    return $"  [{pag.Metodo}] Gateway: {gateway} | " +
           $"Taxa: R${taxaValor:N2} | Líquido: R${liquido:N2} | Recebimento: {prazoRecebimento}";
}

var pagamentos = new[]
{
    new Pagamento("PIX",     150.00m),
    new Pagamento("CREDITO", 500.00m, 3),
    new Pagamento("CREDITO", 500.00m, 12),
    new Pagamento("DEBITO",  250.00m),
    new Pagamento("BOLETO",  800.00m),
};

foreach (var pag in pagamentos)
    Console.WriteLine(ProcessarPagamento(pag));

// ============================================================
// EXEMPLO INDUSTRIAL 3: Retry com Backoff Exponencial
// (Microsserviços — lidar com falhas temporárias de rede)
// ============================================================
Console.WriteLine("\n=== EXEMPLO REAL: RETRY COM BACKOFF EXPONENCIAL ===");
Console.WriteLine("(Padrão usado em toda comunicação entre microsserviços)");

var random = new Random(42); // seed fixo para saída determinística
int tentativaRetry = 0;
int maxTentativas = 4;
bool sucesso = false;

while (tentativaRetry < maxTentativas && !sucesso)
{
    tentativaRetry++;
    int delayMs = (int)Math.Pow(2, tentativaRetry - 1) * 100; // 100ms, 200ms, 400ms, 800ms

    // Simula falha aleatória (70% de chance de falhar nas primeiras tentativas)
    bool falhouAgora = tentativaRetry < 3 || random.NextDouble() < 0.3;

    Console.Write($"  Tentativa {tentativaRetry}/{maxTentativas} (delay: {delayMs}ms)... ");

    if (falhouAgora)
    {
        Console.WriteLine("❌ Serviço externo indisponível — aguardando e retentando");
        if (tentativaRetry < maxTentativas)
            Thread.Sleep(delayMs); // Em produção real aguarda o delay
    }
    else
    {
        Console.WriteLine("✅ Sucesso!");
        sucesso = true;
    }
}

if (!sucesso)
    Console.WriteLine("  💀 Máximo de tentativas atingido — circuito aberto!");


// Tipos usados no programa acima
record Pagamento(string Metodo, decimal Valor, int Parcelas = 1);
