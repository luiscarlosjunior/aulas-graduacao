/**
 * ApoliceVida É-UM Apolice (pilar: HERANÇA) — o tipo especial (tem regras de cobertura e cálculo de prêmio próprios).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ApoliceVida extends Apolice {

    public ApoliceVida(String numero, double valorSegurado) {
        super(numero, valorSegurado);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularPremio() {
        return 950.00;   // prêmio pelas regras de vida
    }
}
