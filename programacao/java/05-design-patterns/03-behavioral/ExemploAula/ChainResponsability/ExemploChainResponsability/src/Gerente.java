public class Gerente extends Handler {
    @Override
    public void handle(double valor) {
        if (valor <= 5000) {
            System.out.println("Gerente aprovou o valor de: " + valor);
        } else if (proximoHandler != null) {
            proximoHandler.handle(valor);
        }
    }
     
}