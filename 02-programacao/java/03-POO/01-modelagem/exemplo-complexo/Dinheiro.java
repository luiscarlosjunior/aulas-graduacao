/** Forma de pagamento em dinheiro (com regra própria: troco). */
public class Dinheiro extends Pagamento {
    private double valorEntregue;

    public Dinheiro(double valorEntregue) {
        this.valorEntregue = valorEntregue;
    }

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento em DINHEIRO de R$ " + String.format("%.2f", valor));
        double troco = valorEntregue - valor;
        if (troco >= 0) {
            System.out.println("  Troco: R$ " + String.format("%.2f", troco));
        } else {
            System.out.println("  [!] Valor entregue insuficiente.");
        }
    }
}
