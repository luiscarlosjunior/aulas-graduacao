package personagens;

public class Mago extends Personagem {
    private int mana;

    public Mago(String nome, int vida, int mana) {
        super(nome, vida);
        this.mana = mana;
    }

    // Getter e Setter
    public int getMana() { return mana; }

    public void setMana(int mana) { this.mana = mana; }

    // Sobrescrita de método
    @Override
    public void atacar() {
        System.out.println(getNome() + " lançou uma magia de fogo gastando " + 10 + " de mana!");
    }

    public void lancarMagia() {
        if (mana >= 10) {
            mana -= 10;
            System.out.println(getNome() + " lançou uma magia poderosa! Mana restante: " + mana);
        } else {
            System.out.println(getNome() + " não tem mana suficiente para lançar magia!");
        }
    }
}
