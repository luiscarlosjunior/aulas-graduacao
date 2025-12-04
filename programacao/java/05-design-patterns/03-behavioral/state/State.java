public interface State {
    void handle(OrderContext context);
    String getStateName();
}
