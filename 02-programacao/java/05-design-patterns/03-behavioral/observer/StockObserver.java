/**
 * StockObserver - Concrete Observer
 * 
 * Observer que monitora o estoque do produto e emite alertas
 * quando o estoque está baixo ou zerado.
 * 
 * Útil para sistemas de gestão de inventário.
 */
public class StockObserver implements Observer {
    private String observerName;
    private static final int LOW_STOCK_THRESHOLD = 10;
    
    public StockObserver(String observerName) {
        this.observerName = observerName;
    }
    
    /**
     * Monitora estoque e emite alertas apropriados
     */
    @Override
    public void update(String productName, double price, int stock) {
        System.out.println("📊 [" + observerName + "] Monitorando estoque:");
        System.out.println("   Produto: " + productName);
        System.out.println("   Estoque atual: " + stock + " unidades");
        
        // Alertas baseados em quantidade
        if (stock == 0) {
            System.out.println("   ❌ CRÍTICO: Produto esgotado!");
        } else if (stock <= LOW_STOCK_THRESHOLD) {
            System.out.println("   ⚠️  ATENÇÃO: Estoque baixo! Reabastecer em breve.");
        } else {
            System.out.println("   ✓ Estoque OK");
        }
    }
}
