/**
 * Classe de Teste do Padrão Adapter
 * 
 * Demonstra como o padrão Adapter permite que diferentes APIs
 * sejam usadas de forma uniforme através de uma interface comum
 */
public class TesteAdapter {
    
    /**
     * Método que processa pagamento usando qualquer ProcessadorPagamento
     * 
     * OBSERVAÇÃO IMPORTANTE:
     * Este método não sabe (e não precisa saber) qual implementação
     * está sendo usada. Trabalha apenas com a interface.
     * 
     * Este é o poder do Adapter Pattern!
     * 
     * @param processador Qualquer implementação de ProcessadorPagamento
     * @param valor Valor a ser processado
     */
    public static void processarPagamento(ProcessadorPagamento processador, double valor) {
        System.out.println("\n--- Processando com " + processador.getNomeProcessador() + " ---");
        boolean sucesso = processador.processar(valor);
        
        if (sucesso) {
            System.out.println("Pagamento processado com sucesso!");
        } else {
            System.out.println("Falha ao processar pagamento.");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Sistema de Pagamento com Adapter ===");
        
        // Criando adapters para diferentes APIs de pagamento
        // Ambos implementam ProcessadorPagamento
        ProcessadorPagamento paypal = new PayPalAdapter("usuario@example.com");
        ProcessadorPagamento stripe = new StripeAdapter("sk_test_12345678");
        
        // Cliente usa mesma interface para ambos
        // Não precisa conhecer detalhes de cada API
        processarPagamento(paypal, 150.50);
        processarPagamento(stripe, 250.75);
        
        // Demonstra tratamento de erro
        System.out.println("\n--- Tentando pagamento inválido ---");
        processarPagamento(paypal, -50.00);
        
        // Demonstra facilidade de trocar implementação
        System.out.println("\n=== Demonstração de Flexibilidade ===");
        System.out.println("Pode trocar facilmente entre processadores:");
        
        // Array polimórfico - diferentes implementações, mesma interface
        ProcessadorPagamento[] processadores = {
            new PayPalAdapter("vendedor@loja.com"),
            new StripeAdapter("sk_live_abcdefgh")
        };
        
        double valorCompra = 99.90;
        for (ProcessadorPagamento proc : processadores) {
            System.out.println("\nTestando com: " + proc.getNomeProcessador());
            proc.processar(valorCompra);
        }
        
        System.out.println("\n=== Benefícios do Adapter ===");
        System.out.println("1. Cliente não conhece detalhes das APIs");
        System.out.println("2. Fácil adicionar novos processadores");
        System.out.println("3. APIs originais não foram modificadas");
        System.out.println("4. Código cliente permanece simples e limpo");
    }
}
