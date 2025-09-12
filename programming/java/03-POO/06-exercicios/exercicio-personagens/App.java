import personagens.Guerreiro;
import personagens.Mago;

import java.lang.*;

public class App {
    public static void main(String[] args) throws Exception {
        getPersonagensRpg();
    }

   
    private static void getPersonagensRpg() {
        Guerreiro g = new Guerreiro("Thorin", 100, 30);
        Mago m = new Mago("Gandalf", 80, 50);

        // Status inicial
        g.exibirStatus();
        m.exibirStatus();

        // Ações
        g.atacar();
        g.golpeEspecial();

        m.atacar();
        m.lancarMagia();
        m.lancarMagia();

        // Atualizar atributos com setters
        m.setVida(70);
        g.setForca(40);

        System.out.println("\n--- Status Atualizado ---");
        g.exibirStatus();
        m.exibirStatus();
    }
}
