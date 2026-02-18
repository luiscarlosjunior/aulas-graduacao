/**
 * Classe Adaptee - API do Stripe
 * Biblioteca externa com interface própria
 * Não pode ser modificada diretamente
 */
public class StripeAPI {
    private String apiKey;
    
    public StripeAPI(String apiKey) {
        this.apiKey = apiKey;
    }
    
    /**
     * Método da API do Stripe - nome e comportamento diferentes
     * @param value Valor a ser cobrado
     */
    public void charge(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        System.out.println("Stripe: Charging R$ " + value);
        System.out.println("Stripe: API Key: " + maskApiKey(apiKey));
    }
    
    /**
     * Mascara a API key para segurança
     */
    private String maskApiKey(String key) {
        if (key.length() <= 4) {
            return "****";
        }
        return "****" + key.substring(key.length() - 4);
    }
    
    public String getApiKey() {
        return apiKey;
    }
}
