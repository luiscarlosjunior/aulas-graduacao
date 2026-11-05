/**
 * AlunoRegular É-UM Aluno (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class AlunoRegular extends Aluno {

    public AlunoRegular(String nome, String matriculaId) {
        super(nome, matriculaId);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double mensalidade() {
        return 900.00;   // paga a mensalidade cheia
    }
}
