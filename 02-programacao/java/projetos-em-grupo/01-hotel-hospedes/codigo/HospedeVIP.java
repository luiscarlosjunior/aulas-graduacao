/**
 * HospedeVIP É-UM Hospede (pilar: HERANÇA) — o tipo especial (tem late check-out e isenção da taxa de serviço).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class HospedeVIP extends Hospede {

    public HospedeVIP(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double taxaDeServico() {
        return 0.00;   // é isento da taxa
    }
}
