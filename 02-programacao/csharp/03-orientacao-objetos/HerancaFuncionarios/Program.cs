// ============================================================
// Herança em C# — Sistema de Folha de Pagamento
//
// ============================================================
// POR QUE HERANÇA IMPORTA NA INDÚSTRIA?
// ============================================================
// Herança em C# tem diferenças importantes em relação a Java:
//
// 1. CLASSES SÃO SELADAS POR PADRÃO no .NET:
//    Precisa escrever 'virtual' no método pai e 'override' no filho.
//    Isso é design by default — previne herança acidental.
//    Princípio: "design for inheritance or prohibit it" (Joshua Bloch)
//
// 2. ABSTRACT CLASSES vs INTERFACES:
//    - abstract class: use quando há CÓDIGO compartilhado + contrato
//    - interface: use quando é só contrato (diferentes hierarquias)
//    Regra: prefira interfaces quando possível (mais flexível)
//
// 3. SEALED: impede herança adicional — para classes de segurança,
//    valor final e performance (JIT pode otimizar melhor)
//
// 4. BASE KEYWORD: equivalente ao super() do Java
//
// Exemplos reais de hierarquia de herança no .NET:
// - Exception → SystemException → IOException → FileNotFoundException
// - Stream → FileStream, MemoryStream, NetworkStream
// - DbContext (Entity Framework) → seu AppDbContext
// ============================================================

// ============================================================
// PROGRAMA PRINCIPAL
// ============================================================

Console.WriteLine("=== HERANÇA C#: SISTEMA DE FOLHA DE PAGAMENTO ===\n");

var dev       = new FuncionarioClt("João Silva",     "123.456.789-00", "Engenharia", 6500m, 8);
var analista  = new FuncionarioClt("Maria Santos",   "987.654.321-00", "Dados",      5200m, 0);
var gerente   = new Gerente(       "Carlos Oliveira","111.222.333-00", 12000m, 20, true, 8);
var consultor = new PrestadorPj(   "Ana Costa",      "12.345.678/0001","Consultoria",150m, 160);

// Polimorfismo: todos são Funcionario, cada um calcula diferente
Funcionario[] equipe = { dev, analista, gerente, consultor };

Console.WriteLine("--- FOLHA DE PAGAMENTO ---");
Console.WriteLine($"  {"Nome",-20} | {"Depto",-12} | {"Bruto",14} | {"Líquido",14}");
Console.WriteLine("  " + new string('─', 70));

decimal totalFolha = 0;
foreach (var f in equipe)
{
    Console.WriteLine(f);  // chama ToString() sobrescrito
    totalFolha += f.CalcularSalarioLiquido();
}

Console.WriteLine("  " + new string('─', 70));
Console.WriteLine($"  {"TOTAL LÍQUIDO:",36} R${totalFolha,9:N2}");

Console.WriteLine("\n--- BATENDO PONTO (polimorfismo em ação) ---");
dev.BaterPonto();      // FuncionarioClt com horas extras
gerente.BaterPonto();  // Gerente → FuncionarioClt → Funcionario

Console.WriteLine("\n--- RELATÓRIO DO GERENTE ---");
gerente.ExibirRelatorioEquipe();

Console.WriteLine("\n--- TYPEOF E IS: Verificando tipos em runtime ---");
foreach (var f in equipe)
{
    string tipo = f switch
    {
        Gerente g       => $"Gerente (meta: {(g.MetaAtingida ? "✅" : "❌")})",
        FuncionarioClt  => "Funcionário CLT",
        PrestadorPj     => "Prestador PJ",
        _               => "Funcionário genérico"
    };
    Console.WriteLine($"  {f.Nome}: {tipo} | GetType: {f.GetType().Name}");
}

Console.WriteLine("\n--- LIÇÃO ---");
Console.WriteLine("  CalcularSalarioBruto() é abstrato em Funcionario.");
Console.WriteLine("  Cada subclasse implementa sua versão:");
Console.WriteLine("  - FuncionarioClt: salário base + horas extras");
Console.WriteLine("  - Gerente:        herda de CLT + adiciona bônus de meta");
Console.WriteLine("  - PrestadorPj:    valor/hora × horas trabalhadas");
Console.WriteLine("  O foreach chama o mesmo método, comportamentos diferentes = POLIMORFISMO!");

// ============================================================
// CLASSES DO SISTEMA
// ============================================================

// ============================================================
// CLASSE ABSTRATA — define contrato + comportamento base
// ============================================================

/// <summary>
/// Classe base abstrata: Funcionário.
/// Contém dados e comportamentos COMUNS a todos os tipos.
/// abstract: não pode ser instanciada diretamente — exige uma subclasse.
/// </summary>
public abstract class Funcionario
{
    public string Nome { get; }
    public string Cpf { get; }
    public string Departamento { get; protected set; }
    public decimal SalarioBase { get; protected set; }

    protected Funcionario(string nome, string cpf, string departamento, decimal salarioBase)
    {
        Nome = nome;
        Cpf = cpf;
        Departamento = departamento;
        SalarioBase = salarioBase;
    }

    // Método virtual: tem implementação padrão, mas pode ser sobrescrito
    public virtual void BaterPonto()
        => Console.WriteLine($"  👤 {Nome} ({GetType().Name}) registrou ponto em {Departamento}");

    // Método ABSTRATO: sem implementação — cada subclasse DEVE implementar
    // Por que? Porque o cálculo de salário é completamente diferente!
    public abstract decimal CalcularSalarioBruto();

    // Método que usa o abstrato acima — Template Method Pattern
    public virtual decimal CalcularDescontoINSS()
    {
        decimal salario = CalcularSalarioBruto();
        // Tabela INSS 2024 (simplificada)
        return salario switch
        {
            <= 1518.00m  => salario * 0.075m,
            <= 2594.25m  => salario * 0.09m,
            <= 3934.08m  => salario * 0.12m,
            _            => salario * 0.14m
        };
    }

    public decimal CalcularSalarioLiquido()
        => CalcularSalarioBruto() - CalcularDescontoINSS();

    public override string ToString()
        => $"  {Nome,-20} | {Departamento,-12} | " +
           $"Bruto: R${CalcularSalarioBruto(),9:N2} | Líquido: R${CalcularSalarioLiquido(),9:N2}";
}

// ============================================================
// SUBCLASSE 1: Funcionário CLT
// ============================================================
public class FuncionarioClt : Funcionario
{
    public int HorasExtrasMes { get; set; }
    private const decimal MultiplHoraExtra = 1.5m;  // 50% a mais
    private const int HorasMensaisClt = 220;

    public FuncionarioClt(string nome, string cpf, string departamento,
                          decimal salarioBase, int horasExtrasMes = 0)
        : base(nome, cpf, departamento, salarioBase)  // base = super() do Java
    {
        HorasExtrasMes = horasExtrasMes;
    }

    // override: implementa o método abstrato do pai
    public override decimal CalcularSalarioBruto()
    {
        decimal valorHoraNormal = SalarioBase / HorasMensaisClt;
        decimal valorHoraExtra  = valorHoraNormal * MultiplHoraExtra;
        return SalarioBase + (HorasExtrasMes * valorHoraExtra);
    }

    public override void BaterPonto()
    {
        base.BaterPonto();  // chama implementação do pai
        if (HorasExtrasMes > 0)
            Console.WriteLine($"    (CLT) Horas extras no mês: {HorasExtrasMes}h");
    }
}

// ============================================================
// SUBCLASSE 2: Gerente (herda de FuncionarioClt)
// ============================================================
public class Gerente : FuncionarioClt
{
    public decimal PercentualBonusMeta { get; }
    public bool MetaAtingida { get; set; }
    public int NumFuncionariosGerenciados { get; }

    public Gerente(string nome, string cpf, decimal salarioBase,
                   decimal percentualBonus, bool metaAtingida, int numFuncionarios)
        : base(nome, cpf, "Gestão", salarioBase, horasExtrasMes: 0)
    {
        PercentualBonusMeta = percentualBonus;
        MetaAtingida = metaAtingida;
        NumFuncionariosGerenciados = numFuncionarios;
    }

    public override decimal CalcularSalarioBruto()
    {
        decimal base_ = base.CalcularSalarioBruto();  // herda cálculo CLT
        return MetaAtingida
            ? base_ + (base_ * PercentualBonusMeta / 100)
            : base_;
    }

    public void ExibirRelatorioEquipe()
    {
        decimal bonus = MetaAtingida
            ? SalarioBase * PercentualBonusMeta / 100
            : 0m;
        Console.WriteLine(
            $"  📊 {Nome} gerencia {NumFuncionariosGerenciados} pessoas | " +
            $"Meta: {(MetaAtingida ? $"✅ Bônus R${bonus:N0}" : "❌ Não atingida")}");
    }
}

// ============================================================
// SUBCLASSE 3: Prestador PJ (sem herança de CLT — hierarquia diferente)
// ============================================================
public class PrestadorPj : Funcionario
{
    public decimal ValorHoraFaturado { get; }
    public int HorasTrabalhadasMes { get; set; }
    private const decimal AliquotaImpostosPJ = 0.15m;  // 15% simplificado

    public PrestadorPj(string nome, string cnpj, string especialidade,
                       decimal valorHora, int horasMes)
        : base(nome, cnpj, especialidade, salarioBase: 0)
    {
        ValorHoraFaturado = valorHora;
        HorasTrabalhadasMes = horasMes;
    }

    // PJ fatura por hora — não tem salário fixo
    public override decimal CalcularSalarioBruto()
        => ValorHoraFaturado * HorasTrabalhadasMes;

    // PJ paga impostos diferentes — sobrescreve também o desconto
    public override decimal CalcularDescontoINSS()
        => CalcularSalarioBruto() * AliquotaImpostosPJ;
}

// ============================================================
// PROGRAMA PRINCIPAL
