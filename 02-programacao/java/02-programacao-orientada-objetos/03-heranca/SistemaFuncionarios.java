/**
 * Herança em Java — Exemplo Industrial: Sistema de Funcionários
 *
 * ============================================================
 * POR QUE HERANÇA IMPORTA NA INDÚSTRIA?
 * ============================================================
 * Herança é usada em sistemas reais quando há uma hierarquia natural:
 *
 * - ERPs: Funcionario → Vendedor, Gerente, Diretor, CLT, PJ
 * - E-commerce: Produto → ProdutoFisico, ProdutoDigital, Servico
 * - Bancos: Conta → ContaCorrente, ContaPoupanca, ContaInvestimento
 * - Frameworks: HttpServlet, JpaRepository, RuntimeException
 *
 * A REGRA DO "É UM": Use herança quando a relação for "B É UM A"
 * - Vendedor É UM Funcionário ✅ (use herança)
 * - Carro É UM Veículo ✅ (use herança)
 * - Carro TEM UM Motor ❌ (use composição, não herança!)
 *
 * CUIDADO: Herança em excesso cria acoplamento forte.
 * Frameworks modernos como Spring preferem composição.
 * Mas entender herança é fundamental para trabalhar com
 * qualquer framework Java.
 * ============================================================
 *
 * @author Aulas Graduação
 * @version 2.0
 */

/**
 * Classe PAI (Superclasse): Funcionário genérico
 * Define os dados e comportamentos COMUNS a todos os funcionários
 */
abstract class Funcionario {

    // Atributos comuns a TODOS os tipos de funcionário
    protected String nome;
    protected String cpf;
    protected String departamento;
    protected double salarioBase;

    // Construtor da classe pai — chamado via super() nas subclasses
    public Funcionario(String nome, String cpf, String departamento, double salarioBase) {
        this.nome = nome;
        this.cpf = cpf;
        this.departamento = departamento;
        this.salarioBase = salarioBase;
    }

    // Método concreto: implementação padrão (subclasses podem sobrescrever)
    public void baterPonto() {
        System.out.printf("  👤 %s registrou ponto no departamento de %s%n",
            nome, departamento);
    }

    // Método abstrato: CADA subclasse DEVE implementar sua versão
    // Porque o cálculo de salário é diferente para cada tipo de funcionário!
    public abstract double calcularSalarioBruto();

    // Método com lógica de negócio baseado no salário — usa o abstract acima
    public double calcularDescontoINSS() {
        double salario = calcularSalarioBruto();
        // Tabela simplificada INSS 2024
        if (salario <= 1518.00) return salario * 0.075;
        if (salario <= 2594.25) return salario * 0.09;
        if (salario <= 3934.08) return salario * 0.12;
        return salario * 0.14;
    }

    public double calcularSalarioLiquido() {
        return calcularSalarioBruto() - calcularDescontoINSS();
    }

    // toString com informações básicas
    @Override
    public String toString() {
        return String.format(
            "  %-20s | %-15s | Bruto: R$%8.2f | Líquido: R$%8.2f",
            nome, departamento, calcularSalarioBruto(), calcularSalarioLiquido());
    }
}

/**
 * Classe FILHA: Funcionário CLT (Consolidação das Leis Trabalhistas)
 * Herda tudo do Funcionário e adiciona regras específicas de CLT
 */
class FuncionarioCLT extends Funcionario {

    private int horasExtrasMes;
    private double percentualHoraExtra = 1.5; // 50% a mais na hora extra

    public FuncionarioCLT(String nome, String cpf, String departamento,
                          double salarioBase, int horasExtrasMes) {
        super(nome, cpf, departamento, salarioBase); // Chama construtor pai
        this.horasExtrasMes = horasExtrasMes;
    }

    @Override
    public double calcularSalarioBruto() {
        // CLT: salário base + horas extras + adicional noturno + benefícios
        double valorHoraNormal = salarioBase / 220;  // 220h/mês é o padrão CLT
        double valorHoraExtra = valorHoraNormal * percentualHoraExtra;
        return salarioBase + (horasExtrasMes * valorHoraExtra);
    }

    @Override
    public void baterPonto() {
        super.baterPonto();  // Chama método do pai
        System.out.printf("    (CLT) Horas extras registradas: %d h%n", horasExtrasMes);
    }
}

/**
 * Classe FILHA: Gerente
 * Herda de FuncionarioCLT e adiciona bônus por metas
 */
class Gerente extends FuncionarioCLT {

    private double percentualBonusMeta;  // Bônus se meta foi atingida (%)
    private boolean metaAtingida;
    private int numFuncionariosGerenciados;

    public Gerente(String nome, String cpf, double salarioBase,
                   double percentualBonusMeta, boolean metaAtingida,
                   int numFuncionarios) {
        super(nome, cpf, "Gestão", salarioBase, 0);  // Gerentes normalmente não fazem horas extras
        this.percentualBonusMeta = percentualBonusMeta;
        this.metaAtingida = metaAtingida;
        this.numFuncionariosGerenciados = numFuncionarios;
    }

    @Override
    public double calcularSalarioBruto() {
        double base = super.calcularSalarioBruto(); // Pega cálculo da classe pai
        if (metaAtingida) {
            return base + (base * percentualBonusMeta / 100);
        }
        return base;
    }

    public void gerarRelatorioEquipe() {
        System.out.printf("  📊 %s gerencia %d funcionários | Meta: %s%n",
            nome, numFuncionariosGerenciados,
            metaAtingida ? "✅ Atingida (+R$" + String.format("%.0f", calcularSalarioBruto() * percentualBonusMeta/100) + " bônus)" : "❌ Não atingida");
    }
}

/**
 * Classe FILHA: Prestador de Serviços (PJ — Pessoa Jurídica)
 * Não é CLT — tem cálculo completamente diferente
 */
class PrestadorPJ extends Funcionario {

    private double valorHoraFaturado;
    private int horasTrabalhadasMes;
    private double impostosPJ; // ISS + outros tributos PJ

    public PrestadorPJ(String nome, String cnpj, String especialidade,
                       double valorHora, int horasMes) {
        super(nome, cnpj, especialidade, 0); // PJ não tem salário base fixo
        this.valorHoraFaturado = valorHora;
        this.horasTrabalhadasMes = horasMes;
        this.impostosPJ = 0.15; // ~15% de impostos sobre faturamento (simplificado)
    }

    @Override
    public double calcularSalarioBruto() {
        // PJ fatura por hora trabalhada, não salário fixo
        return valorHoraFaturado * horasTrabalhadasMes;
    }

    @Override
    public double calcularDescontoINSS() {
        // PJ paga impostos, não INSS da mesma forma
        return calcularSalarioBruto() * impostosPJ;
    }

    public double calcularFaturamentoBruto() {
        return calcularSalarioBruto();
    }
}

/**
 * Classe principal — Demonstração do sistema de funcionários
 */
public class SistemaFuncionarios {

    public static void main(String[] args) {
        System.out.println("=== HERANÇA: SISTEMA DE FOLHA DE PAGAMENTO ===\n");

        // Criando diferentes tipos de funcionários
        FuncionarioCLT dev = new FuncionarioCLT(
            "João Silva", "123.456.789-00", "Engenharia", 6500.00, 8);

        FuncionarioCLT analista = new FuncionarioCLT(
            "Maria Santos", "987.654.321-00", "Dados", 5200.00, 0);

        Gerente gerente = new Gerente(
            "Carlos Oliveira", "111.222.333-00", 12000.00, 20, true, 8);

        PrestadorPJ consultor = new PrestadorPJ(
            "Ana Costa", "12.345.678/0001-90", "Consultoria", 150.00, 160);

        // Processando folha — polimorfismo em ação!
        // Todos são Funcionario, mas cada um calcula diferente
        Funcionario[] equipe = {dev, analista, gerente, consultor};

        System.out.println("--- FOLHA DE PAGAMENTO DO MÊS ---");
        System.out.printf("  %-20s | %-15s | %-18s | %s%n",
            "Nome", "Departamento", "Salário Bruto", "Líquido");
        System.out.println("  " + "─".repeat(75));

        double totalFolha = 0;
        for (Funcionario f : equipe) {
            System.out.println(f);
            totalFolha += f.calcularSalarioLiquido();
        }

        System.out.println("  " + "─".repeat(75));
        System.out.printf("  %-38s R$%8.2f%n", "TOTAL LÍQUIDO FOLHA:", totalFolha);

        System.out.println("\n--- BATENDO PONTO (polimorfismo) ---");
        dev.baterPonto();      // Método sobrescrito de FuncionarioCLT
        gerente.baterPonto();  // Método da cadeia FuncionarioCLT → Gerente

        System.out.println("\n--- RELATÓRIO DO GERENTE ---");
        gerente.gerarRelatorioEquipe();

        System.out.println("\n--- BÔNUS DE APRENDIZADO ---");
        System.out.println("  calcularSalarioBruto() é chamado para TODOS,");
        System.out.println("  mas cada classe executa sua própria versão:");
        System.out.println("  - FuncionarioCLT: salário + horas extras");
        System.out.println("  - Gerente:        herda de CLT + adiciona bônus de meta");
        System.out.println("  - PrestadorPJ:    valor/hora × horas trabalhadas");
        System.out.println("  Isso é polimorfismo! Mesmo nome, comportamentos diferentes.");
    }
}
