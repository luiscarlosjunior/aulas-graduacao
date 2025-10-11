/**
 * Demonstração do Princípio da Substituição de Liskov (LSP)
 * Liskov Substitution Principle
 * 
 * Subtipos devem ser substituíveis por seus tipos base
 * sem quebrar o funcionamento do programa
 * 
 * @author Sistema de Ensino - Princípios SOLID
 */

import java.util.ArrayList;
import java.util.List;

// ==========================================
// EXEMPLO RUIM - Violando LSP
// ==========================================

class RetanguloRuim {
    protected int largura;
    protected int altura;
    
    public void setLargura(int largura) {
        this.largura = largura;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    public int getLargura() { return largura; }
    public int getAltura() { return altura; }
    
    public int getArea() {
        return largura * altura;
    }
    
    public String getInfo() {
        return String.format("Retângulo %dx%d", largura, altura);
    }
}

// Quadrado É-UM Retângulo? Na matemática sim, mas...
class QuadradoRuim extends RetanguloRuim {
    @Override
    public void setLargura(int largura) {
        this.largura = largura;
        this.altura = largura; // Força quadrado!
    }
    
    @Override
    public void setAltura(int altura) {
        this.largura = altura; // Força quadrado!
        this.altura = altura;
    }
    
    @Override
    public String getInfo() {
        return String.format("Quadrado %dx%d", largura, altura);
    }
}

// ==========================================
// EXEMPLO BOM - Seguindo LSP
// ==========================================

// Interface comum para todas as formas
interface Forma {
    double calcularArea();
    double calcularPerimetro();
    String getDescricao();
    void desenhar();
}

// Retângulo implementa Forma
class Retangulo implements Forma {
    private double largura;
    private double altura;
    
    public Retangulo(double largura, double altura) {
        this.largura = largura;
        this.altura = altura;
    }
    
    public void redimensionar(double largura, double altura) {
        this.largura = largura;
        this.altura = altura;
    }
    
    @Override
    public double calcularArea() {
        return largura * altura;
    }
    
    @Override
    public double calcularPerimetro() {
        return 2 * (largura + altura);
    }
    
    @Override
    public String getDescricao() {
        return String.format("Retângulo %.1fx%.1f", largura, altura);
    }
    
    @Override
    public void desenhar() {
        System.out.println("  ┌" + "─".repeat((int)largura * 2) + "┐");
        for (int i = 0; i < altura; i++) {
            System.out.println("  │" + " ".repeat((int)largura * 2) + "│");
        }
        System.out.println("  └" + "─".repeat((int)largura * 2) + "┘");
    }
}

// Quadrado implementa Forma independentemente
class Quadrado implements Forma {
    private double lado;
    
    public Quadrado(double lado) {
        this.lado = lado;
    }
    
    public void redimensionar(double lado) {
        this.lado = lado;
    }
    
    @Override
    public double calcularArea() {
        return lado * lado;
    }
    
    @Override
    public double calcularPerimetro() {
        return 4 * lado;
    }
    
    @Override
    public String getDescricao() {
        return String.format("Quadrado %.1fx%.1f", lado, lado);
    }
    
    @Override
    public void desenhar() {
        System.out.println("  ┌" + "─".repeat((int)lado * 2) + "┐");
        for (int i = 0; i < lado; i++) {
            System.out.println("  │" + " ".repeat((int)lado * 2) + "│");
        }
        System.out.println("  └" + "─".repeat((int)lado * 2) + "┘");
    }
}

// Outras formas também implementam a interface
class Circulo implements Forma {
    private double raio;
    
    public Circulo(double raio) {
        this.raio = raio;
    }
    
    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }
    
    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * raio;
    }
    
    @Override
    public String getDescricao() {
        return String.format("Círculo r=%.1f", raio);
    }
    
    @Override
    public void desenhar() {
        int size = (int)raio * 2;
        System.out.println("     " + "_".repeat(size));
        System.out.println("   /   " + " ".repeat(Math.max(0, size-2)) + "   \\");
        System.out.println("  |     " + " ".repeat(Math.max(0, size-2)) + "     |");
        System.out.println("   \\   " + " ".repeat(Math.max(0, size-2)) + "   /");
        System.out.println("     " + "‾".repeat(size));
    }
}

// Processador que funciona com QUALQUER forma
class ProcessadorFormas {
    public void processarFormas(List<Forma> formas) {
        System.out.println("Processando formas:");
        double areaTotal = 0;
        double perimetroTotal = 0;
        
        for (Forma forma : formas) {
            System.out.println("\n  " + forma.getDescricao());
            System.out.printf("    Área: %.2f\n", forma.calcularArea());
            System.out.printf("    Perímetro: %.2f\n", forma.calcularPerimetro());
            forma.desenhar();
            
            areaTotal += forma.calcularArea();
            perimetroTotal += forma.calcularPerimetro();
        }
        
        System.out.printf("\nTotais:\n");
        System.out.printf("  Área total: %.2f\n", areaTotal);
        System.out.printf("  Perímetro total: %.2f\n", perimetroTotal);
    }
}

// ==========================================
// OUTRO EXEMPLO: Aves
// ==========================================

// Interface que define comportamento comum
interface Ave {
    void comer();
    void dormir();
    String getNome();
}

// Interface separada para aves que voam
interface AveVoadora extends Ave {
    void voar();
}

class Pardal implements AveVoadora {
    @Override
    public void comer() {
        System.out.println("🐦 Pardal comendo sementes");
    }
    
    @Override
    public void dormir() {
        System.out.println("😴 Pardal dormindo no ninho");
    }
    
    @Override
    public void voar() {
        System.out.println("🦅 Pardal voando baixo");
    }
    
    @Override
    public String getNome() {
        return "Pardal";
    }
}

class Aguia implements AveVoadora {
    @Override
    public void comer() {
        System.out.println("🦅 Águia caçando presa");
    }
    
    @Override
    public void dormir() {
        System.out.println("😴 Águia repousando no alto");
    }
    
    @Override
    public void voar() {
        System.out.println("🦅 Águia planando nas alturas");
    }
    
    @Override
    public String getNome() {
        return "Águia";
    }
}

// Pinguim NÃO voa - não implementa AveVoadora!
class Pinguim implements Ave {
    @Override
    public void comer() {
        System.out.println("🐧 Pinguim comendo peixe");
    }
    
    @Override
    public void dormir() {
        System.out.println("😴 Pinguim dormindo em grupo");
    }
    
    public void nadar() {
        System.out.println("🏊 Pinguim nadando rápido");
    }
    
    @Override
    public String getNome() {
        return "Pinguim";
    }
}

// ==========================================
// DEMONSTRAÇÃO E TESTES
// ==========================================

public class ExemploLSP {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  PRINCÍPIO DA SUBSTITUIÇÃO DE LISKOV (LSP)              ║");
        System.out.println("║  Liskov Substitution Principle                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        // Exemplo RUIM
        System.out.println("❌ EXEMPLO RUIM - Substituição quebra comportamento:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploRuim();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Exemplo BOM - Formas
        System.out.println("✅ EXEMPLO BOM - Substituição funciona perfeitamente:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploBom();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Exemplo BOM - Aves
        System.out.println("✅ EXEMPLO BOM - Hierarquia correta de Aves:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploAves();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Análise
        imprimirAnalise();
    }
    
    private static void demonstrarExemploRuim() {
        // Teste que funciona para Retângulo
        System.out.println("Teste com RetanguloRuim:");
        RetanguloRuim ret = new RetanguloRuim();
        testarRetangulo(ret);
        
        System.out.println("\nTeste com QuadradoRuim (herda de RetanguloRuim):");
        RetanguloRuim quad = new QuadradoRuim(); // Substituição!
        testarRetangulo(quad); // QUEBRA!
        
        System.out.println("\n⚠️  PROBLEMA:");
        System.out.println("   • QuadradoRuim NÃO pode substituir RetanguloRuim!");
        System.out.println("   • Viola expectativa de que largura e altura são independentes");
        System.out.println("   • Comportamento muda de forma inesperada");
        System.out.println("   • Cliente precisa saber se é Retângulo ou Quadrado");
    }
    
    private static void testarRetangulo(RetanguloRuim r) {
        r.setLargura(5);
        r.setAltura(4);
        
        int areaEsperada = 5 * 4; // 20
        int areaObtida = r.getArea();
        
        System.out.println("  " + r.getInfo());
        System.out.println("  Configurado: largura=5, altura=4");
        System.out.println("  Área esperada: " + areaEsperada);
        System.out.println("  Área obtida: " + areaObtida);
        
        if (areaObtida == areaEsperada) {
            System.out.println("  ✓ Teste PASSOU");
        } else {
            System.out.println("  ✗ Teste FALHOU - Violação do LSP!");
        }
    }
    
    private static void demonstrarExemploBom() {
        // Criar lista de formas
        List<Forma> formas = new ArrayList<>();
        formas.add(new Retangulo(5, 3));
        formas.add(new Quadrado(4));
        formas.add(new Circulo(3));
        
        // Processador funciona com QUALQUER forma
        ProcessadorFormas processador = new ProcessadorFormas();
        processador.processarFormas(formas);
        
        System.out.println("\n✅ VANTAGENS:");
        System.out.println("   ✓ Todas as formas podem ser substituídas umas pelas outras");
        System.out.println("   ✓ ProcessadorFormas funciona com qualquer implementação de Forma");
        System.out.println("   ✓ Comportamento previsível e consistente");
        System.out.println("   ✓ Sem verificações de tipo ou casts");
    }
    
    private static void demonstrarExemploAves() {
        // Aves que voam
        List<AveVoadora> avesVoadoras = new ArrayList<>();
        avesVoadoras.add(new Pardal());
        avesVoadoras.add(new Aguia());
        
        System.out.println("Aves voadoras:");
        for (AveVoadora ave : avesVoadoras) {
            System.out.println("\n" + ave.getNome() + ":");
            ave.comer();
            ave.voar(); // Todas podem voar!
        }
        
        // Todas as aves (voadoras ou não)
        List<Ave> todasAves = new ArrayList<>();
        todasAves.add(new Pardal());
        todasAves.add(new Aguia());
        todasAves.add(new Pinguim()); // Não voa!
        
        System.out.println("\n\nTodas as aves (comportamento comum):");
        for (Ave ave : todasAves) {
            System.out.println("\n" + ave.getNome() + ":");
            ave.comer();
            ave.dormir();
            
            // Comportamento específico de pinguim
            if (ave instanceof Pinguim) {
                ((Pinguim) ave).nadar();
            }
        }
        
        System.out.println("\n✅ VANTAGENS:");
        System.out.println("   ✓ Pinguim NÃO herda comportamento de voar");
        System.out.println("   ✓ Interfaces segregadas (Ave vs AveVoadora)");
        System.out.println("   ✓ Substituição segura dentro de cada hierarquia");
        System.out.println("   ✓ Não viola expectativas do cliente");
    }
    
    private static void imprimirAnalise() {
        System.out.println("📊 ANÁLISE COMPARATIVA\n");
        
        System.out.println("┌────────────────────────┬──────────────┬──────────────┐");
        System.out.println("│ Métrica                │ Sem LSP      │ Com LSP      │");
        System.out.println("├────────────────────────┼──────────────┼──────────────┤");
        System.out.println("│ Substituição segura    │ Não          │ Sim          │");
        System.out.println("│ Comportamento          │ Imprevisível │ Consistente  │");
        System.out.println("│ Verificações de tipo   │ Necessárias  │ Desnecessár. │");
        System.out.println("│ Polimorfismo           │ Quebrado     │ Funcional    │");
        System.out.println("│ Confiabilidade         │ Baixa        │ Alta         │");
        System.out.println("└────────────────────────┴──────────────┴──────────────┘");
        
        System.out.println("\n💡 PRINCÍPIO CHAVE:");
        System.out.println("   \"Subtipos devem ser substituíveis por seus tipos base\"");
        System.out.println("\n🎯 REGRAS PARA SEGUIR LSP:");
        System.out.println("   1. Pré-condições não podem ser fortalecidas em subclasses");
        System.out.println("   2. Pós-condições não podem ser enfraquecidas em subclasses");
        System.out.println("   3. Invariantes da classe base devem ser preservadas");
        System.out.println("   4. Não lance exceções que a classe base não lança");
        System.out.println("\n🚫 SINAIS DE VIOLAÇÃO:");
        System.out.println("   • Uso de instanceof para verificar tipo");
        System.out.println("   • Exceções inesperadas em subclasses");
        System.out.println("   • Sobrescritas que invalidam comportamento");
        System.out.println("   • Cliente precisa conhecer subclasse específica");
        System.out.println("\n✅ BENEFÍCIO PRINCIPAL:");
        System.out.println("   Polimorfismo correto e substituição segura de objetos");
    }
}
