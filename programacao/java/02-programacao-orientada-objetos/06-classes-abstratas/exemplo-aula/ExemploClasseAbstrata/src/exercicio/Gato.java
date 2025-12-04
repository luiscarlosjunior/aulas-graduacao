package exercicio;

public class Gato extends AnimalTerrestre {
    
    public Gato(String nome, int idade, double peso) {
        super(nome, idade, peso);
    }

    @Override
    public void emitirSom() {
        System.out.println("Miau Miau");
    }
    
}
