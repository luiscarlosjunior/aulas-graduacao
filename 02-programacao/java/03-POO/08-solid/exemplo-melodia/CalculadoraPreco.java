/* SRP: a ÚNICA responsabilidade desta classe é calcular o preço final.
 * OCP: ela não conhece os descontos concretos — recebe o contrato Desconto. */
public class CalculadoraPreco {
    public double calcular(Plano plano, Desconto desconto) {
        return desconto.aplicar(plano.getPrecoMensal());
    }
}
