/* DIP: depende das ABSTRAÇÕES (CalculadoraPreco e MeioDePagamento), injetadas no
 * construtor. Trocar o meio de pagamento ou o desconto NÃO muda esta classe. */
public class ServicoAssinatura {
    private final CalculadoraPreco calc;
    private final MeioDePagamento meio;

    public ServicoAssinatura(CalculadoraPreco calc, MeioDePagamento meio) {
        this.calc = calc;
        this.meio = meio;
    }

    public boolean assinar(Plano plano, Desconto desconto) {
        double preco = calc.calcular(plano, desconto);
        System.out.printf("Plano %s (%s) -> R$ %.2f via %s%n",
                plano.getNome(), desconto.descricao(), preco, meio.nome());
        return meio.cobrar(preco);
    }
}
