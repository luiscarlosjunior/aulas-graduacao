public class TesteIterator {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      PADRÃO ITERATOR - Livros         ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        BookCollection collection = new BookCollection();
        collection.addBook("Design Patterns - GoF");
        collection.addBook("Clean Code - Martin");
        collection.addBook("Refactoring - Fowler");
        
        System.out.println("📚 Iterando sobre livros:");
        Iterator<String> iterator = collection.createIterator();
        while (iterator.hasNext()) {
            System.out.println("  • " + iterator.next());
        }
        
        System.out.println("\n✓ Iterator demonstrado com sucesso!");
    }
}
