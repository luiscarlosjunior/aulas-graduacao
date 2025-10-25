/**
 * PriceDisplay - Concrete Observer
 * 
 * Observer que exibe o preço atual do produto.
 * Representa um componente de UI que mostra o preço na interface.
 */
public class PriceDisplay implements Observer {
    private String displayName;
    
    public PriceDisplay(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * Atualiza o display quando notificado
     */
    @Override
    public void update(String productName, double price, int stock) {
        System.out.println("📺 [" + displayName + "] Atualizando display:");
        System.out.println("   Produto: " + productName);
        System.out.println("   Preço: R$ " + String.format("%.2f", price));
        System.out.println("   Status: " + (stock > 0 ? "Em estoque" : "Indisponível"));
    }
}
