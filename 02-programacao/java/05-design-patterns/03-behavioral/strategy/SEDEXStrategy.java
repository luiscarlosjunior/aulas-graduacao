/**
 * SEDEXStrategy - Concrete Strategy
 * 
 * Estratégia de frete via SEDEX (Correios).
 * Opção mais rápida mas com custo maior.
 * 
 * Cálculo: R$ 1.00 por kg + R$ 0.02 por km
 */
public class SEDEXStrategy implements ShippingStrategy {
    
    private static final double PRICE_PER_KG = 1.00;
    private static final double PRICE_PER_KM = 0.02;
    private static final double BASE_FEE = 10.00;
    
    @Override
    public double calculateCost(double weight, int distance) {
        double cost = BASE_FEE + (weight * PRICE_PER_KG) + (distance * PRICE_PER_KM);
        return Math.round(cost * 100.0) / 100.0;
    }
    
    @Override
    public String getName() {
        return "SEDEX (Correios)";
    }
    
    @Override
    public int estimateDeliveryDays(int distance) {
        // SEDEX: aproximadamente 1 dia para cada 400km
        int days = (int) Math.ceil(distance / 400.0);
        return Math.max(days, 2); // Mínimo 2 dias
    }
}
