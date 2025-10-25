/**
 * Retângulo - forma concreta que implementa clonagem
 * 
 * @author Aulas Graduação
 */
public class Retangulo extends Forma {
    
    private int largura;
    private int altura;
    
    public Retangulo(int largura, int altura) {
        super();
        this.largura = largura;
        this.altura = altura;
        System.out.println("▭ Retângulo original criado " + largura + "x" + altura);
    }
    
    private Retangulo(Retangulo retangulo) {
        super(retangulo);
        this.largura = retangulo.largura;
        this.altura = retangulo.altura;
        System.out.println("▭ Retângulo clonado " + largura + "x" + altura);
    }
    
    @Override
    public Prototipo clonar() {
        return new Retangulo(this);
    }
    
    @Override
    public void exibirInfo() {
        System.out.println("\n▭ RETÂNGULO");
        System.out.println("   Largura: " + largura);
        System.out.println("   Altura: " + altura);
        System.out.println("   Cor: " + cor);
        System.out.println("   Posição: (" + x + ", " + y + ")");
        System.out.println("   Área: " + calcularArea());
    }
    
    public int calcularArea() {
        return largura * altura;
    }
    
    public void setDimensoes(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }
    
    public int getLargura() {
        return largura;
    }
    
    public int getAltura() {
        return altura;
    }
}
