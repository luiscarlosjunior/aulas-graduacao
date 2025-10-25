/**
 * Adapter para PayPal
 * Adapta a interface do PayPalAPI para ProcessadorPagamento
 * 
 * Este é um Object Adapter (usa composição)
 * Encapsula a API do PayPal e traduz as chamadas
 */
public class PayPalAdapter implements ProcessadorPagamento {
    // Composição - contém instância da classe adaptada
    private PayPalAPI paypalAPI;
    
    /**
     * Construtor que recebe configuração do PayPal
     * @param email Email da conta PayPal
     */
    public PayPalAdapter(String email) {
        this.paypalAPI = new PayPalAPI(email);
    }
    
    /**
     * Implementa método da interface Target
     * Traduz chamada para método da classe Adaptee
     */
    @Override
    public boolean processar(double valor) {
        try {
            // Adapta chamada para método do PayPal
            paypalAPI.fazerPagamento(valor);
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public String getNomeProcessador() {
        return "PayPal (" + paypalAPI.getEmail() + ")";
    }
}
