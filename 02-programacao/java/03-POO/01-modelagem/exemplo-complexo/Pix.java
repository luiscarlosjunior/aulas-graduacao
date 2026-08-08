/** Forma de pagamento via Pix (com regra própria: confirmação instantânea). */
public class Pix extends Pagamento {

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento via PIX de R$ " + String.format("%.2f", valor));
        System.out.println("  Confirmado instantaneamente. ✅");
    }
}
