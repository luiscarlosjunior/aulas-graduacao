/**
 * Classe Adaptee - API legada do PayPal
 * Esta classe já existe e não pode ser modificada
 * Tem interface diferente da esperada pelo sistema
 */
public class PayPalAPI {
    private String email;
    
    public PayPalAPI(String email) {
        this.email = email;
    }
    
    /**
     * Método da API do PayPal - interface diferente
     * @param amount Valor em formato diferente
     */
    public void fazerPagamento(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo");
        }
        System.out.println("PayPal: Processando pagamento de R$ " + amount);
        System.out.println("PayPal: Conta: " + email);
    }
    
    public String getEmail() {
        return email;
    }
}
