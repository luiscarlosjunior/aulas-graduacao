/**
 * AlunoPlanoFamilia É-UM Aluno (pilar: HERANÇA) — o tipo especial (divide a mensalidade entre dependentes).
 * Reaproveita tudo da base e só redefine o que muda.
 */
public class AlunoPlanoFamilia extends Aluno {

    public AlunoPlanoFamilia(String nome, String documento) {
        super(nome, documento);   // a validação dos dados comuns é feita na base
    }

    @Override
    public double valorMensalidade() {
        return 60.00;   // divide a mensalidade entre a família
    }
}
