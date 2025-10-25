/**
 * TesteObserver
 * 
 * Demonstração do padrão Observer aplicado a um sistema de e-commerce.
 * Simula mudanças de preço e estoque de produtos e como diferentes
 * componentes reagem automaticamente a essas mudanças.
 */
public class TesteObserver {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         DEMONSTRAÇÃO DO PADRÃO OBSERVER                   ║");
        System.out.println("║    Sistema de Monitoramento de Produtos E-Commerce        ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Criar subject (produto)
        ProductSubject notebook = new ProductSubject("Notebook Dell XPS 15", 8500.00);
        
        System.out.println("➤ Produto criado: " + notebook.getName());
        System.out.println("  Preço inicial: R$ " + String.format("%.2f", notebook.getPrice()));
        System.out.println("  Estoque inicial: " + notebook.getStock() + " unidades\n");
        
        // Criar observers
        System.out.println("═══════════════════════════════════════");
        System.out.println("FASE 1: Registrando Observers");
        System.out.println("═══════════════════════════════════════");
        
        Observer displayPrincipal = new PriceDisplay("Display Página Produto");
        Observer displayCarrinho = new PriceDisplay("Display Carrinho");
        Observer alertaCliente = new PriceAlertObserver("Alerta Cliente João", 7500.00);
        Observer controleEstoque = new StockObserver("Sistema de Estoque");
        
        notebook.attach(displayPrincipal);
        notebook.attach(displayCarrinho);
        notebook.attach(alertaCliente);
        notebook.attach(controleEstoque);
        
        // Teste 1: Mudança de preço
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("FASE 2: Mudanças de Preço");
        System.out.println("═══════════════════════════════════════");
        
        notebook.setPrice(8000.00);
        
        waitForUser();
        
        notebook.setPrice(7200.00);  // Dispara alerta de preço
        
        waitForUser();
        
        // Teste 2: Mudança de estoque
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("FASE 3: Mudanças de Estoque");
        System.out.println("═══════════════════════════════════════");
        
        notebook.setStock(8);   // Estoque baixo
        
        waitForUser();
        
        notebook.setStock(0);   // Produto esgotado
        
        waitForUser();
        
        // Teste 3: Remover observer
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("FASE 4: Removendo Observer");
        System.out.println("═══════════════════════════════════════");
        
        notebook.detach(alertaCliente);
        System.out.println("➤ Alerta de cliente foi desativado");
        
        waitForUser();
        
        notebook.setStock(50);  // Reposição
        
        waitForUser();
        
        notebook.setPrice(6500.00);  // Alerta não dispara mais
        
        // Teste 4: Demonstrar múltiplos produtos
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("FASE 5: Múltiplos Produtos");
        System.out.println("═══════════════════════════════════════");
        
        ProductSubject mouse = new ProductSubject("Mouse Logitech MX Master", 450.00);
        
        // Observer pode observar múltiplos subjects
        mouse.attach(controleEstoque);
        mouse.attach(new PriceDisplay("Display Mouse"));
        
        System.out.println("\n➤ Novo produto criado: " + mouse.getName());
        
        waitForUser();
        
        mouse.setPrice(380.00);
        
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("CONCLUSÃO");
        System.out.println("═══════════════════════════════════════");
        System.out.println("✓ Observers foram notificados automaticamente");
        System.out.println("✓ Baixo acoplamento entre Subject e Observers");
        System.out.println("✓ Fácil adicionar/remover observers dinamicamente");
        System.out.println("✓ Um observer pode observar múltiplos subjects");
        System.out.println("\n🎓 Padrão Observer demonstrado com sucesso!");
    }
    
    /**
     * Método auxiliar para pausar execução (apenas para visualização)
     */
    private static void waitForUser() {
        try {
            Thread.sleep(1000);  // Pausa de 1 segundo para melhor visualização
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
