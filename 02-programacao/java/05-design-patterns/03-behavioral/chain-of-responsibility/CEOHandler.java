public class CEOHandler extends Handler {
    public void handleRequest(double amount) {
        if (amount <= 10000) {
            System.out.println("✓ CEO aprovou: R$ " + amount);
        } else {
            System.out.println("✗ Valor muito alto, negado: R$ " + amount);
        }
    }
}
