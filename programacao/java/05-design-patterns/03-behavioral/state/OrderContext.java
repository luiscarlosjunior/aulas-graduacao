public class OrderContext {
    private State currentState;
    private String orderId;
    
    public OrderContext(String orderId) {
        this.orderId = orderId;
        this.currentState = new NewState();
        System.out.println("📦 Pedido " + orderId + " criado");
    }
    
    public void setState(State state) {
        this.currentState = state;
        System.out.println("➜ Estado: " + state.getStateName());
    }
    
    public void process() {
        currentState.handle(this);
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public String getCurrentState() {
        return currentState.getStateName();
    }
}
