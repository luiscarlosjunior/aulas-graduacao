public class Book implements Element {
    private String title;
    private double price;
    
    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }
    
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
