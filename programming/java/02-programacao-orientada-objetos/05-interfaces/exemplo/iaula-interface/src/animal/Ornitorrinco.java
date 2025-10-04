package animal;

import contrato.Cavador;
import contrato.Nadador;

public class Ornitorrinco extends Animal implements Nadador, Cavador  {
    // Construtor
    public Ornitorrinco(String nome, String especie) {
        super(nome, especie);
    }

    @Override
    public void cavar() {
        System.out.println("O Ornitorrinco está cavando.");
    }

    @Override
    public void nadar() {
        System.out.println("O Ornitorrinco está nadando.");
    }

    @Override
    public void emitirSom() {
        System.out.println("O Ornitorrinco está emitindo um som peculiar.");
    }
    
}