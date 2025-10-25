public class ShippedState implements State {
    public void handle(OrderContext context) {
        System.out.println("✓ Pedido em transporte...");
        context.setState(new DeliveredState());
    }
    
    public String getStateName() {
        return "ENVIADO";
    }
}
