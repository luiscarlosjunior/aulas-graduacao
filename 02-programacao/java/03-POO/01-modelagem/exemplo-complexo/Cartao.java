/** Forma de pagamento em cartão (com regra própria: parcelas). */
public class Cartao extends Pagamento {
    private int parcelas;

    public Cartao(int parcelas) {
        this.parcelas = parcelas;
    }

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento em CARTÃO de R$ " + String.format("%.2f", valor));
        System.out.println("  Em " + parcelas + "x de R$ "
                + String.format("%.2f", valor / parcelas));
    }
}
