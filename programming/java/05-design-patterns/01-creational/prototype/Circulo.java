/**
 * Círculo - forma concreta que implementa clonagem
 * 
 * @author Aulas Graduação
 */
public class Circulo extends Forma {
    
    private int raio;
    
    public Circulo(int raio) {
        super();
        this.raio = raio;
        System.out.println("⭕ Círculo original criado com raio " + raio);
    }
    
    private Circulo(Circulo circulo) {
        super(circulo);
        this.raio = circulo.raio;
        System.out.println("⭕ Círculo clonado com raio " + raio);
    }
    
    @Override
    public Prototipo clonar() {
        return new Circulo(this);
    }
    
    @Override
    public void exibirInfo() {
        System.out.println("\n⭕ CÍRCULO");
        System.out.println("   Raio: " + raio);
        System.out.println("   Cor: " + cor);
        System.out.println("   Posição: (" + x + ", " + y + ")");
        System.out.println("   Área: " + calcularArea());
    }
    
    public double calcularArea() {
        return Math.PI * raio * raio;
    }
    
    public void setRaio(int raio) {
        this.raio = raio;
    }
    
    public int getRaio() {
        return raio;
    }
}
