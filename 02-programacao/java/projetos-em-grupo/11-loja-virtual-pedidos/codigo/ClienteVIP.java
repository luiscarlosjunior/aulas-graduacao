/**
 * ClienteVIP É-UM Cliente (pilar: HERANÇA) — o tipo especial (tem frete grátis e desconto progressivo).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ClienteVIP extends Cliente {

    public ClienteVIP(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularFrete() {
        return 0.00;   // tem frete grátis
    }
}
