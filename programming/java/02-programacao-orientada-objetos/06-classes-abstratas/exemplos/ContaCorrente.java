/**
 * Classe ContaCorrente - Conta Bancária para movimentações diárias
 * 
 * Conta com:
 * - Taxa de manutenção mensal
 * - Taxa de saque por operação
 * - Sem rendimento
 * - Limite de cheque especial
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class ContaCorrente extends ContaBancaria {
    private double limiteEspecial;
    private double taxaManutencao;
    private static final double TAXA_SAQUE = 2.50;
    
    public ContaCorrente(String numeroConta, String titular, double saldoInicial, double limiteEspecial) {
        super(numeroConta, titular, saldoInicial);
        this.limiteEspecial = limiteEspecial;
        this.taxaManutencao = 15.00;
    }
    
    @Override
    public double calcularRendimento() {
        // Conta corrente não tem rendimento
        return 0.0;
    }
    
    @Override
    public double calcularTaxaSaque(double valor) {
        // Taxa fixa por saque
        return TAXA_SAQUE;
    }
    
    @Override
    public String getTipoConta() {
        return "Conta Corrente";
    }
    
    @Override
    public void exibirInformacoesEspecificas() {
        System.out.println("║ Limite Especial: R$ " + String.format("%.2f", limiteEspecial));
        System.out.println("║ Taxa Manutenção: R$ " + String.format("%.2f", taxaManutencao));
        System.out.println("║ Taxa por Saque: R$ " + String.format("%.2f", TAXA_SAQUE));
        System.out.println("║ Saldo Disponível: R$ " + String.format("%.2f", saldo + limiteEspecial));
    }
    
    /**
     * Método específico - cobra taxa de manutenção mensal
     */
    public void cobrarTaxaManutencao() {
        if (saldo >= taxaManutencao) {
            saldo -= taxaManutencao;
            System.out.println("✓ Taxa de manutenção de R$ " + String.format("%.2f", taxaManutencao) + " cobrada.");
        } else {
            System.out.println("⚠ Saldo insuficiente para cobrança da taxa de manutenção!");
        }
    }
    
    /**
     * Sobrescreve o método sacar para considerar o limite especial
     */
    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("✗ Valor inválido para saque!");
            return false;
        }
        
        double taxaSaque = calcularTaxaSaque(valor);
        double valorTotal = valor + taxaSaque;
        double saldoDisponivel = saldo + limiteEspecial;
        
        if (saldoDisponivel >= valorTotal) {
            saldo -= valorTotal;
            numeroOperacoes++;
            System.out.println("✓ Saque de R$ " + String.format("%.2f", valor) + " realizado!");
            System.out.println("  Taxa de saque: R$ " + String.format("%.2f", taxaSaque));
            System.out.println("  Novo saldo: R$ " + String.format("%.2f", saldo));
            
            if (saldo < 0) {
                System.out.println("  ⚠ Utilizando R$ " + String.format("%.2f", Math.abs(saldo)) + " do limite especial");
            }
            return true;
        } else {
            System.out.println("✗ Limite excedido! Saldo + limite: R$ " + String.format("%.2f", saldoDisponivel));
            return false;
        }
    }
}
