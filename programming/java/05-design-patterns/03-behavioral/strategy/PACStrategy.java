/**
 * PACStrategy - Concrete Strategy
 * 
 * Estratégia de frete via PAC (Correios).
 * Opção mais econômica mas com prazo maior.
 * 
 * Cálculo: R$ 0.50 por kg + R$ 0.01 por km
 */
public class PACStrategy implements ShippingStrategy {
    
    private static final double PRICE_PER_KG = 0.50;
    private static final double PRICE_PER_KM = 0.01;
    private static final double BASE_FEE = 5.00;
    
    @Override
    public double calculateCost(double weight, int distance) {
        double cost = BASE_FEE + (weight * PRICE_PER_KG) + (distance * PRICE_PER_KM);
        return Math.round(cost * 100.0) / 100.0; // Arredonda para 2 casas decimais
    }
    
    @Override
    public String getName() {
        return "PAC (Correios)";
    }
    
    @Override
    public int estimateDeliveryDays(int distance) {
        // PAC: aproximadamente 1 dia para cada 200km
        int days = (int) Math.ceil(distance / 200.0);
        return Math.max(days, 5); // Mínimo 5 dias
    }
}
