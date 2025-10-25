/**
 * Subsistema - DVD Player
 * Classe com métodos específicos para controle de DVD
 */
public class DVDPlayer {
    private String filme;
    
    public void ligar() {
        System.out.println("DVD Player: Ligando...");
    }
    
    public void desligar() {
        System.out.println("DVD Player: Desligando...");
    }
    
    public void inserirDVD(String filme) {
        this.filme = filme;
        System.out.println("DVD Player: Inserindo DVD '" + filme + "'");
    }
    
    public void play() {
        System.out.println("DVD Player: Reproduzindo '" + filme + "'");
    }
    
    public void stop() {
        System.out.println("DVD Player: Parando reprodução");
    }
    
    public void ejetar() {
        System.out.println("DVD Player: Ejetando DVD");
        filme = null;
    }
}
