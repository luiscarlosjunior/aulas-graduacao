public class Supervisor extends Handler {
    @Override
    public void handle(double valor) {
        if (valor <= 1000) {
            System.out.println("Supervisor aprovou o valor de: " + valor);
        } else if (proximoHandler != null) {
            proximoHandler.handle(valor);
        }
    }
    
}