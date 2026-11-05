/**
 * AlunoBolsista É-UM Aluno (pilar: HERANÇA) — o tipo especial (tem mensalidade reduzida ou isenta).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class AlunoBolsista extends Aluno {

    public AlunoBolsista(String nome, String matriculaId) {
        super(nome, matriculaId);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double mensalidade() {
        return 450.00;   // paga mensalidade reduzida (ou zero)
    }
}
