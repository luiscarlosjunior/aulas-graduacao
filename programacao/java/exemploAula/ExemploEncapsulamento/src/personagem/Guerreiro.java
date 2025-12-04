package personagem;

public class Guerreiro extends Personagem {
    private int forca;

    public Guerreiro(String nome, int vida) {
        super(nome, vida);
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void usarEspada() {
        System.out.println(getNome() + " está usando uma habilidade especial de Guerreiro!");
    }
}
