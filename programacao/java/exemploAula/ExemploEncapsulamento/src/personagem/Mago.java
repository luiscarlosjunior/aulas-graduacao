package personagem;

public class Mago extends Personagem {
    private int mana;

    public Mago(String nome, int vida) {
        super(nome, vida);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void usarMagia() {
        System.out.println(getNome() + " está usando uma habilidade especial de Mago!");
    }
    
}
