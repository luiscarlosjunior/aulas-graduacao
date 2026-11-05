/**
 * HospedeComum É-UM Hospede (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class HospedeComum extends Hospede {

    public HospedeComum(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double taxaDeServico() {
        return 15.00;   // paga a taxa de serviço cheia
    }
}
