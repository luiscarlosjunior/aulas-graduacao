public class ProcessingState implements State {
    public void handle(OrderContext context) {
        System.out.println("✓ Preparando pedido para envio...");
        context.setState(new ShippedState());
    }
    
    public String getStateName() {
        return "PROCESSANDO";
    }
}
