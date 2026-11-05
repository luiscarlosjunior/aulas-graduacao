/**
 * AlunoComum É-UM Aluno (pilar: HERANÇA) — o tipo comum.
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class AlunoComum extends Aluno {

    public AlunoComum(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double valorMensalidade() {
        return 120.00;   // paga a mensalidade individual
    }
}
