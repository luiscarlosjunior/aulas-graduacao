/**
 * Subsistema - Amplificador de som
 */
public class Amplificador {
    private int volume;
    
    public void ligar() {
        System.out.println("Amplificador: Ligando...");
    }
    
    public void desligar() {
        System.out.println("Amplificador: Desligando...");
    }
    
    public void setVolume(int nivel) {
        this.volume = nivel;
        System.out.println("Amplificador: Volume ajustado para " + nivel);
    }
    
    public void setSurroundSound() {
        System.out.println("Amplificador: Modo surround 5.1 ativado");
    }
}
