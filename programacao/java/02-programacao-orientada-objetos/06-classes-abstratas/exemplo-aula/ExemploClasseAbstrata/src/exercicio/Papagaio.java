package exercicio;

public class Papagaio extends AnimalAereo {
    
    public Papagaio(String nome, int idade, double peso) {
        super(nome, idade, peso);
    }

    @Override
    public void emitirSom() {
        System.out.println("Squawk Squawk");
    }

}
