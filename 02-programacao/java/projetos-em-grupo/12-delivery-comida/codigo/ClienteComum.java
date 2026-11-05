/**
 * ClienteComum É-UM Cliente (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClienteComum extends Cliente {

    public ClienteComum(String nome, String endereco) {
        super(nome, endereco);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double taxaEntrega() {
        return 7.99;   // paga a taxa de entrega
    }
}
