package personagem;

public class Personagem {
    private String nome;
    private int vida;

    // Construtor
    public Personagem(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
    }

    // Getters e Setters
    public String getNome() {
        // 
        return nome;
    }
    
    public void setNome(String nome, String senha) {
        if(senha.equals("admin")) {
            this.nome = nome;
        } else {
            System.out.println("Senha incorreta! Nome não alterado.");
        }
    }
    
    public int getVida() {
        return vida;
    }
    
    public void setVida(int vida) {
        if (vida <= 0) {
            this.vida = 0;
            System.out.println("Você morreu!");
        } else {
            this.vida = vida;
        }
    }

    public void atacar() {
        System.out.println(nome + " está atacando!");
    }

    public void defender() {
        System.out.println(nome + " está defendendo!");
    }

}
