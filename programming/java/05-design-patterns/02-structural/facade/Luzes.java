/**
 * Subsistema - Controle de luzes
 */
public class Luzes {
    private int intensidade;
    
    public void apagar() {
        intensidade = 0;
        System.out.println("Luzes: Apagadas");
    }
    
    public void acender() {
        intensidade = 100;
        System.out.println("Luzes: Acesas (100%)");
    }
    
    public void dim(int nivel) {
        this.intensidade = nivel;
        System.out.println("Luzes: Intensidade ajustada para " + nivel + "%");
    }
}
