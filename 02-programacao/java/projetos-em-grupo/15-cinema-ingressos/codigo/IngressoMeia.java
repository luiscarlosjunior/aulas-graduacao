/**
 * IngressoMeia É-UM Ingresso (pilar: HERANÇA) — o tipo especial (custa metade e exige comprovação).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class IngressoMeia extends Ingresso {

    public IngressoMeia(String assento, double preco) {
        super(assento, preco);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularPreco() {
        return 20.00;   // metade do preço (com comprovação)
    }
}
