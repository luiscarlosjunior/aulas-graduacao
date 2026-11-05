/**
 * PacienteParticular É-UM Paciente (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class PacienteParticular extends Paciente {

    public PacienteParticular(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double valorConsulta() {
        return 250.00;   // paga o valor particular
    }
}
