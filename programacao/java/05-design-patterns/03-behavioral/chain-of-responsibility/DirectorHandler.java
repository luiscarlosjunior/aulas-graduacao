public class DirectorHandler extends Handler {
    public void handleRequest(double amount) {
        if (amount <= 5000) {
            System.out.println("✓ Diretor aprovou: R$ " + amount);
        } else if (nextHandler != null) {
            System.out.println("  Diretor encaminhou para CEO");
            nextHandler.handleRequest(amount);
        }
    }
}
