public class TesteChain {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  PADRÃO CHAIN OF RESPONSIBILITY       ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        Handler manager = new ManagerHandler();
        Handler director = new DirectorHandler();
        Handler ceo = new CEOHandler();
        
        manager.setNext(director);
        director.setNext(ceo);
        
        System.out.println("Pedido 1:");
        manager.handleRequest(500);
        
        System.out.println("\nPedido 2:");
        manager.handleRequest(3000);
        
        System.out.println("\nPedido 3:");
        manager.handleRequest(8000);
        
        System.out.println("\nPedido 4:");
        manager.handleRequest(15000);
        
        System.out.println("\n✓ Chain of Responsibility demonstrado!");
    }
}
