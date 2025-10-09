/**
 * Classe ContaInvestimento - Conta Bancária para investimentos
 * 
 * Conta com:
 * - Alto rendimento (1.5% ao mês)
 * - Taxa de saque progressiva (maior quanto maior o valor)
 * - Saldo mínimo exigido
 * - Penalidade por saldo baixo
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class ContaInvestimento extends ContaBancaria {
    private double taxaRendimento;
    private double saldoMinimo;
    private static final double PENALIDADE_SALDO_BAIXO = 50.00;
    
    public ContaInvestimento(String numeroConta, String titular, double saldoInicial) {
        super(numeroConta, titular, saldoInicial);
        this.taxaRendimento = 0.015; // 1.5% ao mês
        this.saldoMinimo = 1000.00;
    }
    
    @Override
    public double calcularRendimento() {
        // Rendimento maior que poupança
        double rendimento = saldo * taxaRendimento;
        
        // Bônus se saldo for alto
        if (saldo > 50000) {
            rendimento *= 1.2; // 20% de bônus
            System.out.println("  🌟 Bônus de 20% por saldo elevado!");
        }
        
        return rendimento;
    }
    
    @Override
    public double calcularTaxaSaque(double valor) {
        // Taxa progressiva: quanto maior o saque, maior a taxa
        if (valor < 1000) {
            return valor * 0.01; // 1%
        } else if (valor < 5000) {
            return valor * 0.015; // 1.5%
        } else {
            return valor * 0.02; // 2%
        }
    }
    
    @Override
    public String getTipoConta() {
        return "Conta Investimento";
    }
    
    @Override
    public void exibirInformacoesEspecificas() {
        System.out.println("║ Taxa de Rendimento: " + (taxaRendimento * 100) + "% ao mês");
        System.out.println("║ Rendimento Previsto: R$ " + String.format("%.2f", calcularRendimento()));
        System.out.println("║ Saldo Mínimo: R$ " + String.format("%.2f", saldoMinimo));
        
        if (saldo < saldoMinimo) {
            System.out.println("║ ⚠ ATENÇÃO: Saldo abaixo do mínimo!");
        }
    }
    
    /**
     * Método específico - verifica e cobra penalidade se saldo abaixo do mínimo
     */
    public void verificarSaldoMinimo() {
        if (saldo < saldoMinimo) {
            saldo -= PENALIDADE_SALDO_BAIXO;
            System.out.println("⚠ Penalidade de R$ " + String.format("%.2f", PENALIDADE_SALDO_BAIXO) + 
                             " aplicada por saldo abaixo do mínimo!");
            System.out.println("  Novo saldo: R$ " + String.format("%.2f", saldo));
        }
    }
    
    /**
     * Método específico - aplica rendimento e verifica saldo mínimo
     */
    public void processarMensalidade() {
        System.out.println("\n📊 Processando mensalidade da Conta Investimento...");
        aplicarRendimento();
        verificarSaldoMinimo();
    }
}
