/**
 * ClienteRodizio É-UM Cliente (pilar: HERANÇA) — o tipo especial (paga um valor fixo em vez de somar item a item).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClienteRodizio extends Cliente {

    public ClienteRodizio(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularConta() {
        return 89.90;   // paga o valor fixo do rodízio
    }
}
