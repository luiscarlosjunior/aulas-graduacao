package exercicio;

public abstract class AnimalTerrestre extends Animal {
    
    public AnimalTerrestre(String nome, int idade, double peso) {
        super(nome, idade, peso);
    }

    public void correr() {
        System.out.println("Animal correndo");
    }

}