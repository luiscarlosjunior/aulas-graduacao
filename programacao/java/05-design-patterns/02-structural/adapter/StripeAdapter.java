/**
 * Adapter para Stripe
 * Adapta a interface do StripeAPI para ProcessadorPagamento
 * 
 * Demonstra como diferentes APIs podem ser adaptadas
 * para funcionar com a mesma interface
 */
public class StripeAdapter implements ProcessadorPagamento {
    // Composição - contém instância da classe adaptada
    private StripeAPI stripeAPI;
    
    /**
     * Construtor que recebe configuração do Stripe
     * @param apiKey Chave de API do Stripe
     */
    public StripeAdapter(String apiKey) {
        this.stripeAPI = new StripeAPI(apiKey);
    }
    
    /**
     * Implementa método da interface Target
     * Traduz chamada para método da classe Adaptee
     */
    @Override
    public boolean processar(double valor) {
        try {
            // Adapta chamada para método do Stripe
            // Note que o método tem nome diferente (charge)
            stripeAPI.charge(valor);
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public String getNomeProcessador() {
        return "Stripe";
    }
}
