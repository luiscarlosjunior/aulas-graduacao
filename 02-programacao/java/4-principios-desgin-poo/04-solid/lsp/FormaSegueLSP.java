/**
 * Exemplo seguindo LSP (Liskov Substitution Principle)
 * Interface comum sem hierarquia problemática
 * 
 * BENEFÍCIO: Retângulo e Quadrado são substituíveis como Forma,
 * sem comportamentos inesperados.
 */
import java.util.*;

// ✅ LSP: Interface comum
interface Forma {
    int getArea();
    String getTipo();
}

// ✅ LSP: Retângulo implementa Forma
class Retangulo implements Forma {
    private int largura;
    private int altura;
    
    public Retangulo(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }
    
    public void setLargura(int largura) {
        this.largura = largura;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    public int getLargura() {
        return largura;
    }
    
    public int getAltura() {
        return altura;
    }
    
    @Override
    public int getArea() {
        return largura * altura;
    }
    
    @Override
    public String getTipo() {
        return "Retângulo";
    }
}

// ✅ LSP: Quadrado implementa Forma (não herda de Retângulo)
class Quadrado implements Forma {
    private int lado;
    
    public Quadrado(int lado) {
        this.lado = lado;
    }
    
    public void setLado(int lado) {
        this.lado = lado;
    }
    
    public int getLado() {
        return lado;
    }
    
    @Override
    public int getArea() {
        return lado * lado;
    }
    
    @Override
    public String getTipo() {
        return "Quadrado";
    }
}

// ✅ LSP: Círculo também implementa Forma
class Circulo implements Forma {
    private int raio;
    
    public Circulo(int raio) {
        this.raio = raio;
    }
    
    @Override
    public int getArea() {
        return (int)(Math.PI * raio * raio);
    }
    
    @Override
    public String getTipo() {
        return "Círculo";
    }
}

public class FormaSegueLSP {
    
    // ✅ Este método aceita qualquer Forma
    // TODAS as formas são substituíveis sem problemas
    public static void imprimirArea(Forma forma) {
        System.out.println(forma.getTipo() + " - Área: " + forma.getArea());
    }
    
    // ✅ Calcula área total de múltiplas formas
    public static int calcularAreaTotal(List<Forma> formas) {
        int total = 0;
        for (Forma forma : formas) {
            total += forma.getArea();
        }
        return total;
    }
    
    public static void main(String[] args) {
        System.out.println("=== SEGUINDO LSP ===");
        
        // Criando diferentes formas
        Forma ret = new Retangulo(5, 4);
        Forma quad = new Quadrado(3);
        Forma circ = new Circulo(5);
        
        // ✅ Todas as formas podem ser usadas da mesma maneira
        System.out.println("\nImprimindo áreas individuais:");
        imprimirArea(ret);   // 20
        imprimirArea(quad);  // 9
        imprimirArea(circ);  // 78
        
        // ✅ Todas são substituíveis em uma lista
        List<Forma> formas = new ArrayList<>();
        formas.add(ret);
        formas.add(quad);
        formas.add(circ);
        
        System.out.println("\nÁrea total: " + calcularAreaTotal(formas)); // 107
        
        // ✅ Modificando formas individuais - cada uma funciona corretamente
        System.out.println("\nModificando formas:");
        Retangulo r = new Retangulo(10, 5);
        r.setLargura(8);
        System.out.println("Retângulo modificado - Área: " + r.getArea()); // 40
        
        Quadrado q = new Quadrado(4);
        q.setLado(6);
        System.out.println("Quadrado modificado - Área: " + q.getArea()); // 36
        
        System.out.println("\n=== BENEFÍCIOS DO LSP ===");
        System.out.println("1. ✓ Todas as formas são substituíveis como Forma");
        System.out.println("2. ✓ Sem comportamentos inesperados");
        System.out.println("3. ✓ Retângulo e Quadrado têm APIs apropriadas");
        System.out.println("4. ✓ Fácil adicionar novas formas");
        System.out.println("5. ✓ Código mais robusto e previsível");
    }
}
