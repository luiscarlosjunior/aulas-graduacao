/**
 * PrivateCarrierStrategy - Concrete Strategy
 * 
 * Estratégia de frete via transportadora privada.
 * Custo intermediário com prazos variáveis.
 * 
 * Cálculo: R$ 0.80 por kg + R$ 0.015 por km
 * Desconto para distâncias maiores
 */
public class PrivateCarrierStrategy implements ShippingStrategy {
    
    private static final double PRICE_PER_KG = 0.80;
    private static final double PRICE_PER_KM = 0.015;
    private static final double BASE_FEE = 8.00;
    
    @Override
    public double calculateCost(double weight, int distance) {
        double cost = BASE_FEE + (weight * PRICE_PER_KG) + (distance * PRICE_PER_KM);
        
        // Desconto para longas distâncias
        if (distance > 1000) {
            cost *= 0.90; // 10% de desconto
        }
        
        // Desconto para grandes volumes
        if (weight > 50) {
            cost *= 0.85; // 15% de desconto adicional
        }
        
        return Math.round(cost * 100.0) / 100.0;
    }
    
    @Override
    public String getName() {
        return "Transportadora Privada";
    }
    
    @Override
    public int estimateDeliveryDays(int distance) {
        // Transportadora: aproximadamente 1 dia para cada 300km
        int days = (int) Math.ceil(distance / 300.0);
        return Math.max(days, 3); // Mínimo 3 dias
    }
}
