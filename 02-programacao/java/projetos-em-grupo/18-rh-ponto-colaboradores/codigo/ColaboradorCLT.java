/**
 * ColaboradorCLT É-UM Colaborador (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class ColaboradorCLT extends Colaborador {

    public ColaboradorCLT(String nome, String matricula) {
        super(nome, matricula);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double calcularAdicional() {
        return 1.50;   // hora extra pela regra CLT
    }
}
