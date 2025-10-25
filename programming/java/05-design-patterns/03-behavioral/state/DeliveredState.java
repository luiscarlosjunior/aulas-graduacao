public class DeliveredState implements State {
    public void handle(OrderContext context) {
        System.out.println("✓ Pedido já foi entregue!");
    }
    
    public String getStateName() {
        return "ENTREGUE";
    }
}
