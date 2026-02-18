package animal;

import contrato.Cavador;

public class Cachorro extends Animal implements Cavador {
    // Construtor
    public Cachorro(String nome, String especie) {
        super(nome, especie);
    }

    @Override
    public void cavar() {
        System.out.println("O Cachorro está cavando.");
    }

    @Override
    public void emitirSom() {
        System.out.println("O Cachorro está latindo.");
    }
}
