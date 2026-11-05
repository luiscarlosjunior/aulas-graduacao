/**
 * ColaboradorEstagiario É-UM Colaborador (pilar: HERANÇA) — o tipo especial (tem carga horária e regras de hora extra diferentes).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ColaboradorEstagiario extends Colaborador {

    public ColaboradorEstagiario(String nome, String matricula) {
        super(nome, matricula);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularAdicional() {
        return 0.00;   // regra de estágio (carga reduzida)
    }
}
