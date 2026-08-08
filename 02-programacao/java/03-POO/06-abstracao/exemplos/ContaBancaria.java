/**
 * Classe abstrata ContaBancaria - Sistema Bancário
 * 
 * Representa a base para todos os tipos de contas bancárias.
 * Define operações comuns (depósito, saque, transferência) e
 * delega o cálculo de rendimento para cada tipo específico.
 * 
 * Demonstra:
 * - Compartilhamento de código comum
 * - Encapsulamento de regras de negócio
 * - Polimorfismo em operações bancárias
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public abstract class ContaBancaria {
    protected String numeroConta;
    protected String titular;
    protected double saldo;
    protected int numeroOperacoes;
    
    /**
     * Construtor da classe ContaBancaria
     * @param numeroConta Número único da conta
     * @param titular Nome do titular da conta
     * @param saldoInicial Saldo inicial da conta
     */
    public ContaBancaria(String numeroConta, String titular, double saldoInicial) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.numeroOperacoes = 0;
    }
    
    // Getters
    public String getNumeroConta() {
        return numeroConta;
    }
    
    public String getTitular() {
        return titular;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public int getNumeroOperacoes() {
        return numeroOperacoes;
    }
    
    /**
     * Método concreto - implementação comum para todas as contas
     * Deposita um valor na conta
     * @param valor Valor a ser depositado
     */
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            numeroOperacoes++;
            System.out.println("✓ Depósito de R$ " + String.format("%.2f", valor) + " realizado com sucesso!");
            System.out.println("  Novo saldo: R$ " + String.format("%.2f", saldo));
        } else {
            System.out.println("✗ Valor inválido para depósito!");
        }
    }
    
    /**
     * Método concreto - implementação comum para todas as contas
     * Realiza saque se houver saldo suficiente
     * @param valor Valor a ser sacado
     * @return true se o saque foi realizado, false caso contrário
     */
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("✗ Valor inválido para saque!");
            return false;
        }
        
        double taxaSaque = calcularTaxaSaque(valor);
        double valorTotal = valor + taxaSaque;
        
        if (saldo >= valorTotal) {
            saldo -= valorTotal;
            numeroOperacoes++;
            System.out.println("✓ Saque de R$ " + String.format("%.2f", valor) + " realizado!");
            if (taxaSaque > 0) {
                System.out.println("  Taxa de saque: R$ " + String.format("%.2f", taxaSaque));
            }
            System.out.println("  Novo saldo: R$ " + String.format("%.2f", saldo));
            return true;
        } else {
            System.out.println("✗ Saldo insuficiente! Saldo atual: R$ " + String.format("%.2f", saldo));
            return false;
        }
    }
    
    /**
     * Método concreto - transferência entre contas
     * @param destino Conta de destino
     * @param valor Valor a ser transferido
     * @return true se a transferência foi realizada
     */
    public boolean transferir(ContaBancaria destino, double valor) {
        System.out.println("\n→ Transferindo R$ " + String.format("%.2f", valor) + 
                         " para " + destino.getTitular() + "...");
        
        if (sacar(valor)) {
            destino.depositar(valor);
            System.out.println("✓ Transferência concluída com sucesso!");
            return true;
        } else {
            System.out.println("✗ Transferência não realizada!");
            return false;
        }
    }
    
    /**
     * Método concreto que utiliza método abstrato (Template Method)
     * Exibe todas as informações da conta
     */
    public void exibirExtrato() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║         EXTRATO BANCÁRIO                    ║");
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║ Tipo: " + getTipoConta());
        System.out.println("║ Conta: " + numeroConta);
        System.out.println("║ Titular: " + titular);
        System.out.println("║ Saldo Atual: R$ " + String.format("%.2f", saldo));
        System.out.println("║ Operações realizadas: " + numeroOperacoes);
        exibirInformacoesEspecificas();
        System.out.println("╚════════════════════════════════════════════╝");
    }
    
    /**
     * Método abstrato - cada tipo de conta calcula o rendimento de forma diferente
     * @return Valor do rendimento calculado
     */
    public abstract double calcularRendimento();
    
    /**
     * Método abstrato - cada tipo de conta tem uma taxa de saque diferente
     * @param valor Valor do saque
     * @return Taxa a ser cobrada
     */
    public abstract double calcularTaxaSaque(double valor);
    
    /**
     * Método abstrato - cada tipo de conta retorna sua descrição
     * @return Nome do tipo da conta
     */
    public abstract String getTipoConta();
    
    /**
     * Método abstrato - cada tipo de conta exibe informações específicas
     */
    public abstract void exibirInformacoesEspecificas();
    
    /**
     * Método concreto - aplica o rendimento calculado
     */
    public void aplicarRendimento() {
        double rendimento = calcularRendimento();
        if (rendimento > 0) {
            saldo += rendimento;
            System.out.println("✓ Rendimento de R$ " + String.format("%.2f", rendimento) + " aplicado!");
            System.out.println("  Novo saldo: R$ " + String.format("%.2f", saldo));
        }
    }
}
