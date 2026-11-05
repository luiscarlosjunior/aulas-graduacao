/**
 * PacienteConvenio É-UM Paciente (pilar: HERANÇA) — o tipo especial (tem valor diferente e exige autorização do convênio).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class PacienteConvenio extends Paciente {

    public PacienteConvenio(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double valorConsulta() {
        return 120.00;   // paga o valor do convênio (com autorização)
    }
}
