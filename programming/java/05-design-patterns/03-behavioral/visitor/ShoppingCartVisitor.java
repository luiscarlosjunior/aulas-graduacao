public class ShoppingCartVisitor implements Visitor {
    private double total = 0;
    
    public void visit(Book book) {
        System.out.println("Livro: " + book.getTitle() + " - R$ " + book.getPrice());
        total += book.getPrice();
    }
    
    public void visit(Fruit fruit) {
        double cost = fruit.getPricePerKg() * fruit.getWeight();
        System.out.println("Fruta: " + fruit.getName() + " (" + fruit.getWeight() + "kg) - R$ " + cost);
        total += cost;
    }
    
    public double getTotal() {
        return total;
    }
}
