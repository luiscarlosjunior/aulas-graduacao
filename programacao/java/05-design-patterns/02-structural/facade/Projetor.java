/**
 * Subsistema - Projetor
 */
public class Projetor {
    
    public void ligar() {
        System.out.println("Projetor: Ligando...");
    }
    
    public void desligar() {
        System.out.println("Projetor: Desligando...");
    }
    
    public void modoWideScreen() {
        System.out.println("Projetor: Configurando para modo widescreen (16:9)");
    }
    
    public void setInput(String fonte) {
        System.out.println("Projetor: Entrada configurada para " + fonte);
    }
}
