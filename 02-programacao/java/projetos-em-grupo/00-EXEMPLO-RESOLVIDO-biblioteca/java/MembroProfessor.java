/* MembroProfessor É-UM Membro: prazo maior e limite maior (mesmo contrato, resposta diferente). */
public class MembroProfessor extends Membro {
    public MembroProfessor(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    @Override public int prazoDeEmprestimoDias() { return 30; }
    @Override public int limiteDeLivros()        { return 10; }
}
