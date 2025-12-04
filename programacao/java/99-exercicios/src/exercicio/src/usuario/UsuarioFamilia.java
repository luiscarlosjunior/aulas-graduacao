package usuario;

public class UsuarioFamilia extends Usuario {

    private int numeroMembros;

    public UsuarioFamilia(int id, String nome, String email, int numeroMembros) {
        super(id, nome, email); // Chama o construtor da classe base Usuario
        this.numeroMembros = numeroMembros;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Número de Membros: " + numeroMembros);
    }

    public int getNumeroMembros() {
        return numeroMembros;
    }

    public void setNumeroMembros(int numeroMembros) {
        this.numeroMembros = numeroMembros;
    }

    public void adicionarMembro() {
        this.numeroMembros++;
        System.out.println("Membro adicionado. Total de membros agora: " + this.numeroMembros);
    }
}