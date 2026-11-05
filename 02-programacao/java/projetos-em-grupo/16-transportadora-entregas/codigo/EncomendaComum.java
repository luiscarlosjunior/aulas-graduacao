/**
 * EncomendaComum É-UM Encomenda (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class EncomendaComum extends Encomenda {

    public EncomendaComum(String codigo, double peso) {
        super(codigo, peso);   // a validação dos dados comuns é feita na base
    }

    @Override
    public int prazoEntregaDias() {
        return 7;   // prazo normal
    }
}
