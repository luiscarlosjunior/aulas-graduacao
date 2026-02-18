public class TesteState {
    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("║    PADRÃO STATE - Pedido E-Commerce   ║");
        System.out.println("╚═════════════════════════════════════════╝\n");
        
        OrderContext order = new OrderContext("#12345");
        
        System.out.println("\nProcessamento 1:");
        order.process();
        
        System.out.println("\nProcessamento 2:");
        order.process();
        
        System.out.println("\nProcessamento 3:");
        order.process();
        
        System.out.println("\nProcessamento 4:");
        order.process();
        
        System.out.println("\n✓ Padrão State demonstrado com sucesso!");
    }
}
