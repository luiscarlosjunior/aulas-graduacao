package exercicio;

public abstract class AnimalAquatico extends Animal {
    
    public AnimalAquatico(String nome, int idade, double peso) {
        super(nome, idade, peso);
    }

    public void nadar() {
        System.out.println("Animal nadando");
    }

}
