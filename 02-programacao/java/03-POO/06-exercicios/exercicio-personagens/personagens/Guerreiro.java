package personagens;

public class Guerreiro extends Personagem {
    private int forca;

    public Guerreiro(String nome, int vida, int forca) {
        super(nome, vida);
        this.forca = forca;
    }

    // Getter e Setter
    public int getForca() { return forca; }

    public void setForca(int forca) { this.forca = forca; }

    // Sobrescrita de método
    @Override
    public void atacar() {
        System.out.println(getNome() + " atacou com sua espada causando " + forca + " de dano!");
    }

    public void golpeEspecial() {
        System.out.println(getNome() + " usou o GOLPE ESPECIAL! Dano crítico!");
    }
}
