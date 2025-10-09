package exercicio;

public class Peixe extends AnimalAquatico {
    public Peixe(String nome, int idade, double peso) {
        super(nome, idade, peso);
    }

    @Override
    public void emitirSom() {
        System.out.println("Blub Blub");
    }
}
