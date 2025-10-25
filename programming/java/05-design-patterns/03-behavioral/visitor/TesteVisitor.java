public class TesteVisitor {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    PADRÃO VISITOR - Carrinho Compras  ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        Element[] items = {
            new Book("Design Patterns", 89.90),
            new Book("Clean Code", 79.90),
            new Fruit("Maçã", 5.50, 2.0),
            new Fruit("Banana", 3.20, 1.5)
        };
        
        ShoppingCartVisitor visitor = new ShoppingCartVisitor();
        
        System.out.println("Itens no carrinho:");
        for (Element item : items) {
            item.accept(visitor);
        }
        
        System.out.println("\n💰 Total: R$ " + String.format("%.2f", visitor.getTotal()));
        System.out.println("\n✓ Visitor demonstrado com sucesso!");
    }
}
