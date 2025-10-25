/**
 * ShippingStrategy - Interface Strategy
 * 
 * Define o contrato que todas as estratégias de cálculo de frete devem seguir.
 * Cada estratégia implementa este método de forma diferente.
 */
public interface ShippingStrategy {
    /**
     * Calcula o custo de frete baseado em peso e distância
     * 
     * @param weight Peso do pacote em kg
     * @param distance Distância em km
     * @return Custo do frete em reais
     */
    double calculateCost(double weight, int distance);
    
    /**
     * Retorna o nome da estratégia de envio
     * 
     * @return Nome descritivo da transportadora
     */
    String getName();
    
    /**
     * Calcula prazo de entrega estimado
     * 
     * @param distance Distância em km
     * @return Prazo em dias úteis
     */
    int estimateDeliveryDays(int distance);
}
