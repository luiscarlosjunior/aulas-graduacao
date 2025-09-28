package usuario;

public class Usuario {
    private int id;
    private String nome;
    private String email;

    public Usuario(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void fazerLogin() {
        // Lógica de login
        System.out.println("Usuário " + nome + " fez login.");
    }

    public void ouvirConteudo() {
        // Lógica para ouvir conteúdo
        System.out.println("Usuário " + nome + " está ouvindo conteúdo.");
    }

    public void exibirInfo() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
    }

}
