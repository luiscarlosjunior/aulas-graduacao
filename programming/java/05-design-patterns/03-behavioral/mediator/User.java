public class User {
    private String name;
    private ChatMediator mediator;
    
    public User(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
    
    public String getName() {
        return name;
    }
    
    public void send(String message) {
        System.out.println(name + " enviou: " + message);
        mediator.sendMessage(message, this);
    }
    
    public void receive(String message, User sender) {
        System.out.println("  → " + name + " recebeu de " + sender.getName() + ": " + message);
    }
}
