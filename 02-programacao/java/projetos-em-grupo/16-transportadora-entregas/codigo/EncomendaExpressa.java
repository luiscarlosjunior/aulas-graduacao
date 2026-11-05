/**
 * EncomendaExpressa É-UM Encomenda (pilar: HERANÇA) — o tipo especial (tem prazo menor e prioridade no transporte).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class EncomendaExpressa extends Encomenda {

    public EncomendaExpressa(String codigo, double peso) {
        super(codigo, peso);   // a validação dos dados comuns é feita na base
    }

    @Override
    public int prazoEntregaDias() {
        return 2;   // prazo menor (prioridade)
    }
}
