/**
 * Classe ContaPoupanca - Conta Bancária para poupança
 * 
 * Conta com:
 * - Rendimento mensal (0.5% ao mês)
 * - Sem taxa de saque
 * - Sem taxa de manutenção
 * - Limite de 3 saques gratuitos por mês
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class ContaPoupanca extends ContaBancaria {
    private double taxaRendimento;
    private int saquesRealizados;
    private static final int LIMITE_SAQUES_GRATUITOS = 3;
    private static final double TAXA_SAQUE_EXTRA = 3.00;
    
    public ContaPoupanca(String numeroConta, String titular, double saldoInicial) {
        super(numeroConta, titular, saldoInicial);
        this.taxaRendimento = 0.005; // 0.5% ao mês
        this.saquesRealizados = 0;
    }
    
    @Override
    public double calcularRendimento() {
        // Rendimento baseado no saldo atual
        return saldo * taxaRendimento;
    }
    
    @Override
    public double calcularTaxaSaque(double valor) {
        // Primeiros 3 saques são gratuitos
        if (saquesRealizados < LIMITE_SAQUES_GRATUITOS) {
            saquesRealizados++;
            return 0.0;
        }
        return TAXA_SAQUE_EXTRA;
    }
    
    @Override
    public String getTipoConta() {
        return "Conta Poupança";
    }
    
    @Override
    public void exibirInformacoesEspecificas() {
        System.out.println("║ Taxa de Rendimento: " + (taxaRendimento * 100) + "% ao mês");
        System.out.println("║ Rendimento Previsto: R$ " + String.format("%.2f", calcularRendimento()));
        System.out.println("║ Saques Gratuitos: " + (LIMITE_SAQUES_GRATUITOS - saquesRealizados) + " restantes");
    }
    
    /**
     * Método específico - reinicia contador de saques (simulação de novo mês)
     */
    public void reiniciarContadorSaques() {
        saquesRealizados = 0;
        System.out.println("✓ Contador de saques reiniciado para novo período.");
    }
    
    /**
     * Método específico - simula aniversário da poupança
     */
    public void aniversarioPoupanca() {
        System.out.println("\n🎂 Aniversário da Poupança!");
        aplicarRendimento();
        reiniciarContadorSaques();
    }
}
