package personagens;

public class Personagem {
    private String nome;
    private int vida;

    public Personagem(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
    }

    // Getters e Setters
    public String getNome() { return nome;}

    public void setNome(String nome) { this.nome = nome; }

    public int getVida() { return vida; }

    public void setVida(int vida) { this.vida = vida; }

    // Métodos comuns
    public void atacar() { System.out.println(nome + " atacou!"); }

    public void defender() { System.out.println(nome + " defendeu!"); }

    public void exibirStatus() { System.out.println("Nome: " + nome + " | Vida: " + vida); }
}
