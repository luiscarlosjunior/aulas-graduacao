public class NewState implements State {
    public void handle(OrderContext context) {
        System.out.println("✓ Processando pedido novo...");
        context.setState(new ProcessingState());
    }
    
    public String getStateName() {
        return "NOVO";
    }
}
