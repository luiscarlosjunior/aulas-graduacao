/**
 * PriceAlertObserver - Concrete Observer
 * 
 * Observer que monitora o preço e emite alertas quando atinge
 * um valor específico (target price).
 * 
 * Útil para notificar clientes quando produto fica mais barato.
 */
public class PriceAlertObserver implements Observer {
    private String alertName;
    private double targetPrice;
    private boolean alerted;
    
    public PriceAlertObserver(String alertName, double targetPrice) {
        this.alertName = alertName;
        this.targetPrice = targetPrice;
        this.alerted = false;
    }
    
    /**
     * Verifica se preço atingiu valor alvo e emite alerta
     */
    @Override
    public void update(String productName, double price, int stock) {
        // Só alerta se preço caiu para valor alvo ou menor
        if (price <= targetPrice && !alerted) {
            System.out.println("🔔 [" + alertName + "] ⚠️  ALERTA DE PREÇO!");
            System.out.println("   " + productName + " atingiu preço desejado!");
            System.out.println("   Preço atual: R$ " + String.format("%.2f", price));
            System.out.println("   Preço alvo: R$ " + String.format("%.2f", targetPrice));
            alerted = true;
        } else if (price > targetPrice && alerted) {
            // Reset do alerta se preço subir novamente
            alerted = false;
        }
    }
}
