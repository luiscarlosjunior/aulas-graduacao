public abstract class Handler {
    protected Handler proximoHandler;

    public Handler setNext(Handler proximoHandler) {
        this.proximoHandler = proximoHandler;
        return proximoHandler;
    }

    public abstract void handle(double valor);
}