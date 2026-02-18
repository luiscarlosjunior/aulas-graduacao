package animal;

public class Gato extends Animal {
    
    // Construtor
    public Gato(String nome, String especie) {
        super(nome, especie);
    }

    @Override
    public void emitirSom() {
        System.out.println("O gato está miando.");
    }
}