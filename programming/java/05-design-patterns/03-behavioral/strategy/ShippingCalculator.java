/**
 * ShippingCalculator - Context
 * 
 * Classe que utiliza as estratégias de frete.
 * Mantém referência para uma estratégia e delega o cálculo a ela.
 * 
 * O contexto não conhece detalhes de implementação das estratégias,
 * apenas usa a interface comum.
 */
public class ShippingCalculator {
    
    private ShippingStrategy strategy;
    
    /**
     * Construtor padrão sem estratégia definida
     */
    public ShippingCalculator() {
        this.strategy = null;
    }
    
    /**
     * Construtor com estratégia inicial
     * 
     * @param strategy Estratégia a ser utilizada
     */
    public ShippingCalculator(ShippingStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Define ou altera a estratégia de cálculo
     * 
     * Este método permite trocar a estratégia em tempo de execução,
     * demonstrando a flexibilidade do padrão Strategy.
     * 
     * @param strategy Nova estratégia a ser utilizada
     */
    public void setStrategy(ShippingStrategy strategy) {
        this.strategy = strategy;
        System.out.println("✓ Estratégia definida: " + strategy.getName());
    }
    
    /**
     * Calcula o custo de frete usando a estratégia atual
     * 
     * @param weight Peso do pacote em kg
     * @param distance Distância em km
     * @return Custo do frete
     * @throws IllegalStateException se nenhuma estratégia foi definida
     */
    public double calculate(double weight, int distance) {
        if (strategy == null) {
            throw new IllegalStateException("Nenhuma estratégia de frete foi definida!");
        }
        return strategy.calculateCost(weight, distance);
    }
    
    /**
     * Obtém prazo de entrega usando a estratégia atual
     * 
     * @param distance Distância em km
     * @return Prazo em dias úteis
     * @throws IllegalStateException se nenhuma estratégia foi definida
     */
    public int getDeliveryTime(int distance) {
        if (strategy == null) {
            throw new IllegalStateException("Nenhuma estratégia de frete foi definida!");
        }
        return strategy.estimateDeliveryDays(distance);
    }
    
    /**
     * Exibe informações completas do frete
     * 
     * @param weight Peso do pacote em kg
     * @param distance Distância em km
     */
    public void showQuote(double weight, int distance) {
        if (strategy == null) {
            System.out.println("❌ Erro: Nenhuma estratégia definida!");
            return;
        }
        
        double cost = calculate(weight, distance);
        int days = getDeliveryTime(distance);
        
        System.out.println("┌─────────────────────────────────────────────");
        System.out.println("│ Modalidade: " + strategy.getName());
        System.out.println("│ Peso: " + weight + " kg");
        System.out.println("│ Distância: " + distance + " km");
        System.out.println("├─────────────────────────────────────────────");
        System.out.println("│ Custo: R$ " + String.format("%.2f", cost));
        System.out.println("│ Prazo: " + days + " dias úteis");
        System.out.println("└─────────────────────────────────────────────\n");
    }
}
