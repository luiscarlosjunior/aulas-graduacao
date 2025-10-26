/**
 * Exemplo de violação do LSP (Liskov Substitution Principle)
 * Problema clássico: Quadrado herdando de Retângulo
 * 
 * PROBLEMA: Quadrado não pode substituir Retângulo sem quebrar
 * o comportamento esperado, violando LSP.
 */

class Retangulo {
    protected int largura;
    protected int altura;
    
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
    
    public int getArea() {
        return largura * altura;
    }
}

// ❌ Quadrado herda de Retângulo - parece lógico matematicamente
// Mas viola LSP!
class Quadrado extends Retangulo {
    
    @Override
    public void setLargura(int largura) {
        // ❌ Modifica comportamento: altera AMBOS lados
        this.largura = largura;
        this.altura = largura; // Quebra expectativa!
    }
    
    @Override
    public void setAltura(int altura) {
        // ❌ Modifica comportamento: altera AMBOS lados
        this.largura = altura; // Quebra expectativa!
        this.altura = altura;
    }
}

public class FormaViolaLSP {
    
    // Este método espera comportamento de Retângulo
    public static void testarRetangulo(Retangulo r) {
        r.setLargura(5);
        r.setAltura(4);
        
        // Expectativa: área = 5 * 4 = 20
        int areaEsperada = 20;
        int areaReal = r.getArea();
        
        System.out.println("Largura: " + r.getLargura());
        System.out.println("Altura: " + r.getAltura());
        System.out.println("Área esperada: " + areaEsperada);
        System.out.println("Área real: " + areaReal);
        
        if (areaReal == areaEsperada) {
            System.out.println("✓ Teste PASSOU");
        } else {
            System.out.println("✗ Teste FALHOU - Violação de LSP!");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== VIOLAÇÃO DO LSP ===");
        
        System.out.println("\nTestando com Retangulo:");
        Retangulo ret = new Retangulo();
        testarRetangulo(ret); // Passa: área = 20
        
        System.out.println("\n" + "=".repeat(40));
        System.out.println("Testando com Quadrado:");
        Retangulo quad = new Quadrado(); // Quadrado usado como Retangulo
        testarRetangulo(quad); // FALHA: área = 16, não 20!
        
        System.out.println("\n=== PROBLEMA ===");
        System.out.println("Quadrado NÃO pode substituir Retângulo sem quebrar comportamento!");
        System.out.println("Quando setLargura(5) e setAltura(4) são chamados:");
        System.out.println("- Retângulo: largura=5, altura=4, área=20 ✓");
        System.out.println("- Quadrado: largura=4, altura=4, área=16 ✗");
        System.out.println("\nIsso VIOLA o Princípio de Substituição de Liskov!");
    }
}
