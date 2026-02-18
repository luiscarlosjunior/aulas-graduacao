package cachorro;

public class Cachorro {
    // Atributos
    private String raca;
    private int idade;
    private String nome;
    private String corOlhos;

    public Cachorro(String raca, int idade, String nome, String corOlhos) {
        this.raca = raca;
        this.idade = idade;
        this.nome = nome;
        this.corOlhos = corOlhos;
    }

    // Getters e Setters
    public String getRaca(String usuario, String senha) {
        if (!usuario.equals("admin") || !senha.equals("admin123")) {
            return "Acesso negado. Usuário ou senha incorretos.";
        }
        // Validação de usuario
        return raca;
    }

    public String getCorOlhos() {
        return corOlhos;
    }
    
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade, String usuario, String senha) {
        if (!usuario.equals("admin") || !senha.equals("admin123")) {
            System.out.println("Acesso negado. Usuário ou senha incorretos.");
            return;
        }

        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Comportamentos (métodos)
    public void ladrar() {
        System.out.println("Au Au");
    }

    public void correr() {
        System.out.println("Correndo...");
    }

    public void dormir() {
        System.out.println("Zzzzzzzzz");
    }

    public void comer() {
        System.out.println("Comendo...");
    }

    public void acordar() {
        System.out.println("Acordando...");
    }
}
