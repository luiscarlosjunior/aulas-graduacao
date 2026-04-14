// ============================================================
// Encapsulamento em C# — Sistema de Produto E-commerce
//
// ============================================================
// POR QUE ENCAPSULAMENTO IMPORTA NA INDÚSTRIA?
// ============================================================
// Em C#, encapsulamento vai além de getters/setters do Java:
//
// 1. PROPRIEDADES (Properties): Sintaxe nativa para get/set com
//    validação — muito mais elegante que Java.
//    Todo framework .NET usa properties: Entity Framework,
//    ASP.NET Core, System.Text.Json, WPF, Blazor...
//
// 2. INIT-ONLY SETTERS (C# 9+): Imutabilidade após construção.
//    Impossível alterar após criar — ideal para DTOs de API.
//
// 3. RECORD TYPES (C# 9+): Classes imutáveis por padrão.
//    Usadas em Value Objects do DDD e respostas de API.
//
// 4. ACCESS MODIFIERS: public, private, protected, internal,
//    private protected, protected internal — mais granular que Java.
//
// Regra prática: Em .NET, NUNCA exponha fields públicos.
// Sempre use properties — mesmo que simples { get; set; }
// Entity Framework, Dapper e System.Text.Json dependem disso!
// ============================================================

using System.Text.RegularExpressions;

// ============================================================
// PROGRAMA PRINCIPAL (top-level statements — executado primeiro)
// ============================================================

Console.WriteLine("=== ENCAPSULAMENTO C#: SISTEMA DE PRODUTO E-COMMERCE ===\n");

var produto = new Produto("FONE-BT-001", "Fone Bluetooth Pro", 249.90m, 10);
produto.Descricao = "Fone sem fio com cancelamento de ruído ativo";
produto.PesoKg = 0.3;

Console.WriteLine("--- Produto Criado ---");
Console.WriteLine(produto);

Console.WriteLine("\n--- Verificações via Properties ---");
Console.WriteLine($"  Disponível: {produto.Disponivel}");
Console.WriteLine($"  Preço com 15% desconto: {produto.CalcularPrecoComDesconto(15):C}");

Console.WriteLine("\n--- Simulando Vendas ---");
produto.RegistrarVenda(3);
produto.RegistrarVenda(5);  // dispara alerta de estoque crítico
produto.RegistrarVenda(5);  // falha: sem estoque suficiente

Console.WriteLine("\n--- Validação de Property ---");
try
{
    produto.Preco = -50m;
}
catch (ArgumentException ex)
{
    Console.WriteLine($"  ❌ Validação funcionou: {ex.Message}");
}

Console.WriteLine("\n--- Estado Final ---");
Console.WriteLine(produto);
Console.WriteLine($"  Total de vendas: {produto.TotalVendas} unidades");

Console.WriteLine("\n--- Proteção de Dados ---");
Console.WriteLine("  produto.TotalVendas = 9999; → NÃO COMPILA (sem setter)");
Console.WriteLine("  produto._totalVendas = 9999; → NÃO COMPILA (private)");
Console.WriteLine("  Só RegistrarVenda() altera _totalVendas. Isso é encapsulamento!");

Console.WriteLine("\n--- Record DTO para Resposta de API ---");
var dto = new ProdutoDto(produto.Sku, produto.Nome, produto.Preco,
                          produto.QuantidadeEstoque, produto.Disponivel);
Console.WriteLine($"  DTO: {dto}");
Console.WriteLine("  Records são imutáveis: perfeitos para DTOs de API.");
Console.WriteLine("  System.Text.Json serializa automaticamente para: ");
Console.WriteLine($"  {{ \"sku\": \"{dto.Sku}\", \"nome\": \"{dto.Nome}\", \"preco\": {dto.Preco} ... }}");

// ============================================================
// CLASSES DO SISTEMA (declarações abaixo do programa principal)
// ============================================================


/// <summary>
/// Produto de e-commerce com encapsulamento completo.
/// Demonstra Properties com validação, computed properties,
/// e proteção de invariantes de negócio.
/// </summary>
public class Produto
{
    // ============================================================
    // BACKING FIELDS — campos privados que armazenam o valor real
    // ============================================================
    private string _sku = "";
    private string _nome = "";
    private decimal _preco;
    private int _quantidadeEstoque;
    private int _totalVendas;  // somente leitura externa — sem setter público!

    // ============================================================
    // PROPRIEDADES COM VALIDAÇÃO — C# faz isso nativamente!
    // ============================================================

    /// <summary>SKU: código único do produto (Stock Keeping Unit).</summary>
    public string Sku
    {
        get => _sku;
        set
        {
            if (string.IsNullOrWhiteSpace(value))
                throw new ArgumentException("SKU não pode ser vazio.");
            if (!Regex.IsMatch(value, @"^[A-Z0-9-]{5,20}$"))
                throw new ArgumentException($"SKU '{value}' inválido. Use maiúsculas, números e hífen (5-20 chars).");
            _sku = value;
        }
    }

    public string Nome
    {
        get => _nome;
        set
        {
            if (string.IsNullOrWhiteSpace(value) || value.Trim().Length < 3)
                throw new ArgumentException("Nome deve ter ao menos 3 caracteres.");
            _nome = value.Trim();
        }
    }

    public decimal Preco
    {
        get => _preco;
        set
        {
            if (value < 0.01m)
                throw new ArgumentException("Preço mínimo é R$ 0,01.");
            if (value > 1_000_000m)
                throw new ArgumentException("Preço acima do limite máximo.");
            _preco = value;
        }
    }

    public int QuantidadeEstoque
    {
        get => _quantidadeEstoque;
        set
        {
            if (value < 0)
                throw new ArgumentException("Estoque não pode ser negativo.");
            _quantidadeEstoque = value;
        }
    }

    // Auto-property simples
    public string? Descricao { get; set; }
    public double PesoKg { get; set; } = 0.5;
    public bool Ativo { get; private set; } = true;

    // Somente leitura — sem setter público (nem private set)
    public int TotalVendas => _totalVendas;

    // ============================================================
    // COMPUTED PROPERTIES — calculadas dinamicamente
    // ============================================================

    /// <summary>Produto está disponível para compra?</summary>
    public bool Disponivel => Ativo && _quantidadeEstoque > 0;

    /// <summary>Preço formatado para exibição — comum em ViewModels</summary>
    public string PrecoFormatado => $"R$ {_preco:N2}";

    // ============================================================
    // CONSTRUTOR — garante estado inicial válido
    // ============================================================
    public Produto(string sku, string nome, decimal preco, int estoque)
    {
        Sku = sku;       // usa property com validação
        Nome = nome;
        Preco = preco;
        QuantidadeEstoque = estoque;
    }

    // ============================================================
    // MÉTODOS DE NEGÓCIO
    // ============================================================

    public decimal CalcularPrecoComDesconto(decimal percentualDesconto)
    {
        if (percentualDesconto < 0 || percentualDesconto > 100)
            throw new ArgumentOutOfRangeException(nameof(percentualDesconto),
                "Percentual deve ser entre 0 e 100.");
        return _preco * (1 - percentualDesconto / 100);
    }

    /// <summary>
    /// Registra venda — controla estoque e totalVendas internamente.
    /// Nenhum código externo pode alterar _totalVendas diretamente.
    /// </summary>
    public bool RegistrarVenda(int quantidade)
    {
        if (!Disponivel)
        {
            Console.WriteLine($"  ❌ '{Nome}' indisponível para venda");
            return false;
        }
        if (quantidade > _quantidadeEstoque)
        {
            Console.WriteLine($"  ❌ Estoque insuficiente: {_quantidadeEstoque} disponível, {quantidade} solicitado");
            return false;
        }

        _quantidadeEstoque -= quantidade;
        _totalVendas += quantidade;

        Console.WriteLine($"  ✅ Venda: {quantidade}x '{Nome}' | Estoque: {_quantidadeEstoque} | Total vendido: {_totalVendas}");

        if (_quantidadeEstoque < 5)
            Console.WriteLine($"  ⚠️  ALERTA: Estoque crítico ({_quantidadeEstoque} unidades)!");

        return true;
    }

    public void DefinirAtivo(bool ativo)
    {
        Ativo = ativo;
        Console.WriteLine($"  Produto '{Nome}' {(ativo ? "ativado ✅" : "desativado ❌")}");
    }

    public override string ToString()
        => $"[{Sku}] {Nome} | {PrecoFormatado} | Estoque:{_quantidadeEstoque} | {(Disponivel ? "🟢 Disponível" : "🔴 Indisponível")}";
}

// ============================================================
// RECORD: imutável por padrão — DTOs de API (C# 9+)
// ============================================================

/// <summary>
/// DTO (Data Transfer Object) para resposta de API.
/// Records são imutáveis — ASP.NET Core serializa para JSON automaticamente.
/// </summary>
public record ProdutoDto(
    string Sku,
    string Nome,
    decimal Preco,
    int Estoque,
    bool Disponivel
);

// ============================================================
// PROGRAMA PRINCIPAL
