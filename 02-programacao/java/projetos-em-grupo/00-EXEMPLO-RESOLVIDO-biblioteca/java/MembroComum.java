/* MembroComum É-UM Membro: prazo e limite padrão. */
public class MembroComum extends Membro {
    public MembroComum(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    @Override public int prazoDeEmprestimoDias() { return 14; }
    @Override public int limiteDeLivros()        { return 3; }
}
