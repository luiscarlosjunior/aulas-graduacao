import java.util.ArrayList;
import java.util.List;

/**
 * ProductSubject - Concrete Subject
 * 
 * Representa um produto em um sistema de e-commerce.
 * Notifica observers quando preço ou estoque mudam.
 * 
 * Este é o objeto sendo observado (Observable).
 */
public class ProductSubject implements Subject {
    // Lista de observers registrados
    private List<Observer> observers;
    
    // Estado do produto
    private String name;
    private double price;
    private int stock;
    
    /**
     * Construtor
     * 
     * @param name Nome do produto
     * @param price Preço inicial
     */
    public ProductSubject(String name, double price) {
        this.observers = new ArrayList<>();
        this.name = name;
        this.price = price;
        this.stock = 100; // Estoque inicial padrão
    }
    
    /**
     * Adiciona observer à lista
     */
    @Override
    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("✓ Observer adicionado: " + observer.getClass().getSimpleName());
        }
    }
    
    /**
     * Remove observer da lista
     */
    @Override
    public void detach(Observer observer) {
        if (observers.remove(observer)) {
            System.out.println("✗ Observer removido: " + observer.getClass().getSimpleName());
        }
    }
    
    /**
     * Notifica todos os observers sobre mudanças
     * 
     * Percorre a lista de observers e chama o método update de cada um,
     * passando os dados atualizados (Push Model).
     */
    @Override
    public void notifyObservers() {
        System.out.println("\n📢 Notificando " + observers.size() + " observer(s)...");
        for (Observer observer : observers) {
            observer.update(name, price, stock);
        }
        System.out.println();
    }
    
    /**
     * Atualiza o preço e notifica observers
     * 
     * @param price Novo preço
     */
    public void setPrice(double price) {
        if (this.price != price) {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("💰 Mudança de Preço: " + this.name);
            System.out.println("   De: R$ " + String.format("%.2f", this.price) + 
                             " → Para: R$ " + String.format("%.2f", price));
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            
            this.price = price;
            notifyObservers();
        }
    }
    
    /**
     * Atualiza o estoque e notifica observers
     * 
     * @param stock Nova quantidade em estoque
     */
    public void setStock(int stock) {
        if (this.stock != stock) {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("📦 Mudança de Estoque: " + this.name);
            System.out.println("   De: " + this.stock + " → Para: " + stock);
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            
            this.stock = stock;
            notifyObservers();
        }
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getStock() {
        return stock;
    }
}
