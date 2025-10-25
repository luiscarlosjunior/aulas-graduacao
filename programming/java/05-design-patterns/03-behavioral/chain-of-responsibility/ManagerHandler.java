public class ManagerHandler extends Handler {
    public void handleRequest(double amount) {
        if (amount <= 1000) {
            System.out.println("✓ Gerente aprovou: R$ " + amount);
        } else if (nextHandler != null) {
            System.out.println("  Gerente encaminhou para Diretor");
            nextHandler.handleRequest(amount);
        }
    }
}
