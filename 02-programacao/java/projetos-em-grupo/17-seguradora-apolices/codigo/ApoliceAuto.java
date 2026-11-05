/**
 * ApoliceAuto É-UM Apolice (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ApoliceAuto extends Apolice {

    public ApoliceAuto(String numero, double valorSegurado) {
        super(numero, valorSegurado);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularPremio() {
        return 1800.00;   // prêmio pelas regras de auto
    }
}
