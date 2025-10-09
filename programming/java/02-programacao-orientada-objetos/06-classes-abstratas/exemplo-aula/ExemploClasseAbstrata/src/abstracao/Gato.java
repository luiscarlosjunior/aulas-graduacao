package abstracao;

public class Gato extends Animal {
    public Gato(String nome, int idade, double peso) {
        super(nome, idade, peso);
    }

    @Override
    public void emitirSom() {
        System.out.println("Miau Miau");
    }

    @Override
    public void comer() {
        System.out.println("Gato comendo ração");
    }

    @Override
    public void dormir() {
        System.out.println("Gato dormindo");
    }
    
}
