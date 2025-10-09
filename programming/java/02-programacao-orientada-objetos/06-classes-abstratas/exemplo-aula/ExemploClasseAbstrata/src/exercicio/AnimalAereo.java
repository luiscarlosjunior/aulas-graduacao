package exercicio;

public abstract class AnimalAereo extends Animal {
    
    public AnimalAereo(String nome, int idade, double peso) {
        super(nome, idade, peso);
    }

    public void voar() {
        System.out.println("Animal voando");
    }

}
