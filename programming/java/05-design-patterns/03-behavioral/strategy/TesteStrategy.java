/**
 * TesteStrategy
 * 
 * Demonstração do padrão Strategy aplicado a cálculo de frete.
 * Mostra como diferentes algoritmos podem ser trocados em tempo de execução.
 */
public class TesteStrategy {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         DEMONSTRAÇÃO DO PADRÃO STRATEGY                   ║");
        System.out.println("║         Sistema de Cálculo de Frete                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Criar o contexto (calculadora de frete)
        ShippingCalculator calculator = new ShippingCalculator();
        
        // Dados do envio
        double weight = 10.0;  // 10 kg
        int distance = 800;    // 800 km
        
        System.out.println("📦 DADOS DO ENVIO");
        System.out.println("   Peso: " + weight + " kg");
        System.out.println("   Distância: " + distance + " km\n");
        
        // ═══════════════════════════════════════
        // Teste 1: Estratégia PAC
        // ═══════════════════════════════════════
        System.out.println("═══════════════════════════════════════");
        System.out.println("TESTE 1: Cálculo com PAC");
        System.out.println("═══════════════════════════════════════\n");
        
        calculator.setStrategy(new PACStrategy());
        calculator.showQuote(weight, distance);
        
        // ═══════════════════════════════════════
        // Teste 2: Estratégia SEDEX
        // ═══════════════════════════════════════
        System.out.println("═══════════════════════════════════════");
        System.out.println("TESTE 2: Cálculo com SEDEX");
        System.out.println("═══════════════════════════════════════\n");
        
        calculator.setStrategy(new SEDEXStrategy());
        calculator.showQuote(weight, distance);
        
        // ═══════════════════════════════════════
        // Teste 3: Estratégia Transportadora
        // ═══════════════════════════════════════
        System.out.println("═══════════════════════════════════════");
        System.out.println("TESTE 3: Cálculo com Transportadora");
        System.out.println("═══════════════════════════════════════\n");
        
        calculator.setStrategy(new PrivateCarrierStrategy());
        calculator.showQuote(weight, distance);
        
        // ═══════════════════════════════════════
        // Teste 4: Comparação de Estratégias
        // ═══════════════════════════════════════
        System.out.println("═══════════════════════════════════════");
        System.out.println("TESTE 4: Comparação de Opções");
        System.out.println("═══════════════════════════════════════\n");
        
        compareStrategies(20.0, 1500);
        
        // ═══════════════════════════════════════
        // Teste 5: Envios Diferentes
        // ═══════════════════════════════════════
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("TESTE 5: Cenários Diferentes");
        System.out.println("═══════════════════════════════════════\n");
        
        testDifferentScenarios(calculator);
        
        // Conclusão
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("CONCLUSÃO");
        System.out.println("═══════════════════════════════════════");
        System.out.println("✓ Estratégias trocadas em tempo de execução");
        System.out.println("✓ Sem condicionais (if/else) no código cliente");
        System.out.println("✓ Fácil adicionar novas estratégias");
        System.out.println("✓ Cada estratégia encapsulada e testável");
        System.out.println("\n🎓 Padrão Strategy demonstrado com sucesso!");
    }
    
    /**
     * Compara todas as estratégias lado a lado
     */
    private static void compareStrategies(double weight, int distance) {
        ShippingStrategy[] strategies = {
            new PACStrategy(),
            new SEDEXStrategy(),
            new PrivateCarrierStrategy()
        };
        
        System.out.println("📊 Comparando opções para:");
        System.out.println("   Peso: " + weight + " kg");
        System.out.println("   Distância: " + distance + " km\n");
        
        System.out.println("┌────────────────────────┬──────────────┬────────────────┐");
        System.out.println("│ Modalidade             │ Custo (R$)   │ Prazo (dias)   │");
        System.out.println("├────────────────────────┼──────────────┼────────────────┤");
        
        for (ShippingStrategy strategy : strategies) {
            double cost = strategy.calculateCost(weight, distance);
            int days = strategy.estimateDeliveryDays(distance);
            
            System.out.printf("│ %-22s │ %12.2f │ %14d │%n", 
                strategy.getName(), cost, days);
        }
        
        System.out.println("└────────────────────────┴──────────────┴────────────────┘");
    }
    
    /**
     * Testa diferentes cenários de envio
     */
    private static void testDifferentScenarios(ShippingCalculator calculator) {
        // Cenário 1: Envio leve e curto
        System.out.println("Cenário 1: Pacote leve, distância curta");
        calculator.setStrategy(new PACStrategy());
        calculator.showQuote(2.0, 100);
        
        // Cenário 2: Envio pesado e longo
        System.out.println("Cenário 2: Pacote pesado, distância longa");
        calculator.setStrategy(new PrivateCarrierStrategy());
        calculator.showQuote(60.0, 1800);
        
        // Cenário 3: Envio expresso
        System.out.println("Cenário 3: Entrega expressa");
        calculator.setStrategy(new SEDEXStrategy());
        calculator.showQuote(5.0, 500);
    }
}
