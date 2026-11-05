/**
 * IngressoInteira É-UM Ingresso (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class IngressoInteira extends Ingresso {

    public IngressoInteira(String assento, double preco) {
        super(assento, preco);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularPreco() {
        return 40.00;   // preço inteiro
    }
}
