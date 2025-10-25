/**
 * Classe de Teste do Padrão Decorator
 * 
 * Demonstra como decoradores podem ser combinados dinamicamente
 * para criar diferentes configurações de bebidas
 */
public class TesteDecorator {
    
    /**
     * Método auxiliar para exibir informações de uma bebida
     */
    private static void exibirBebida(String nomePedido, Bebida bebida) {
        System.out.println("\n" + nomePedido);
        System.out.println("Descrição: " + bebida.getDescricao());
        System.out.printf("Custo: R$ %.2f%n", bebida.getCusto());
    }
    
    public static void main(String[] args) {
        System.out.println("=== Cafeteria com Decorator Pattern ===");
        
        // Pedido 1: Café simples (sem decoradores)
        Bebida cafe1 = new CafeSimples();
        exibirBebida("Pedido 1: Café simples", cafe1);
        
        // Pedido 2: Café com leite (um decorator)
        // Note: decorador envolve o componente base
        Bebida cafe2 = new ComLeite(new CafeSimples());
        exibirBebida("Pedido 2: Café com leite", cafe2);
        
        // Pedido 3: Café com leite, chocolate e chantilly (múltiplos decoradores)
        // IMPORTANTE: Decoradores são encadeados, cada um envolve o anterior
        Bebida cafe3 = new ComChantilly(
                           new ComChocolate(
                               new ComLeite(
                                   new CafeSimples()
                               )
                           )
                       );
        exibirBebida("Pedido 3: Café especial", cafe3);
        
        // Pedido 4: Super café com tudo (demonstra flexibilidade)
        Bebida cafe4 = new ComChantilly(
                           new ComCaramelo(
                               new ComChocolate(
                                   new ComLeite(
                                       new CafeSimples()
                                   )
                               )
                           )
                       );
        exibirBebida("Pedido 4: Super café", cafe4);
        
        // Demonstração: Construção passo a passo
        System.out.println("\n=== Construindo Bebida Passo a Passo ===");
        Bebida bebida = new CafeSimples();
        System.out.println("1. Base: " + bebida.getDescricao() + " - R$ " + bebida.getCusto());
        
        bebida = new ComLeite(bebida);
        System.out.println("2. +Leite: " + bebida.getDescricao() + " - R$ " + bebida.getCusto());
        
        bebida = new ComChocolate(bebida);
        System.out.println("3. +Chocolate: " + bebida.getDescricao() + " - R$ " + bebida.getCusto());
        
        // Demonstração: Mesmos decoradores, ordem diferente (resultado igual)
        System.out.println("\n=== Ordem de Decoração ===");
        Bebida ordem1 = new ComChocolate(new ComLeite(new CafeSimples()));
        Bebida ordem2 = new ComLeite(new ComChocolate(new CafeSimples()));
        
        System.out.println("Ordem 1 (Leite depois Chocolate): " + ordem1.getDescricao());
        System.out.printf("Custo: R$ %.2f%n", ordem1.getCusto());
        
        System.out.println("\nOrdem 2 (Chocolate depois Leite): " + ordem2.getDescricao());
        System.out.printf("Custo: R$ %.2f%n", ordem2.getCusto());
        
        System.out.println("\nNota: Custo é o mesmo, mas descrição reflete ordem de aplicação");
        
        // Demonstração: Flexibilidade em runtime
        System.out.println("\n=== Vantagens do Decorator ===");
        System.out.println("1. Adiciona funcionalidades dinamicamente");
        System.out.println("2. Combina decoradores de diferentes formas");
        System.out.println("3. Evita explosão de subclasses");
        System.out.println("4. Cada decorator tem responsabilidade única");
        System.out.println("5. Fácil adicionar novos decoradores sem modificar código existente");
        
        // Comparação com herança
        System.out.println("\n=== Decorator vs Herança ===");
        System.out.println("Com 4 adicionais (Leite, Chocolate, Chantilly, Caramelo):");
        System.out.println("- Herança: 2^4 = 16 classes para todas as combinações");
        System.out.println("- Decorator: 5 classes (1 base + 4 decoradores)");
        System.out.println("- Combinações possíveis: Infinitas em runtime!");
    }
}
