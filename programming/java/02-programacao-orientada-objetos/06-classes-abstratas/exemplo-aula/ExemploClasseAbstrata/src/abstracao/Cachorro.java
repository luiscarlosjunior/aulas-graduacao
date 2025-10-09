package abstracao;

public class Cachorro extends Animal {
    public Cachorro(String nome, int idade, double peso) {
        super(nome, idade, peso);
    }

    @Override
    public void emitirSom() {
        System.out.println("Au Au");
    }

    @Override
    public void comer() {
        System.out.println("Cachorro comendo ração");
    }

    @Override
    public void dormir() {
        System.out.println("Cachorro dormindo");
    }
    
}
