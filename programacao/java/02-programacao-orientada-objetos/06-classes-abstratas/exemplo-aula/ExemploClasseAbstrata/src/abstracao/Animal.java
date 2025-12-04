package abstracao;

// Serve como base comum para outras classes
public abstract class Animal {
    private String nome;
    private int idade;
    private double peso;

    public Animal(String nome, int idade, double peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
    }

    // TODO Getters - Setter

    public abstract void emitirSom();

    public void comer() {
        System.out.println("Animal comendo");
    }

    public void dormir() {
        System.out.println("Animal dormindo");
    }
}
