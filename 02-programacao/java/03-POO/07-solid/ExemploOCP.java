/**
 * Demonstração do Princípio Aberto/Fechado (OCP)
 * Open/Closed Principle
 * 
 * Aberto para EXTENSÃO, Fechado para MODIFICAÇÃO
 * 
 * @author Sistema de Ensino - Princípios SOLID
 */

// ==========================================
// EXEMPLO RUIM - Violando OCP
// ==========================================

class CalculadoraDescontoRuim {
    public double calcular(String tipoCliente, double valor) {
        // Cada novo tipo requer MODIFICAR este código!
        if (tipoCliente.equals("Regular")) {
            return valor * 0.95; // 5% desconto
        } else if (tipoCliente.equals("Premium")) {
            return valor * 0.90; // 10% desconto
        } else if (tipoCliente.equals("VIP")) {
            return valor * 0.80; // 20% desconto
        }
        // E se quisermos adicionar "Corporativo"? Modificar aqui!
        // E se quisermos adicionar "Estudante"? Modificar aqui!
        return valor;
    }
}

// ==========================================
// EXEMPLO BOM - Seguindo OCP
// ==========================================

// Interface para estratégia de desconto
interface EstrategiaDesconto {
    double aplicarDesconto(double valor);
    String getDescricao();
}

// Implementações concretas (EXTENSÕES)
class DescontoClienteRegular implements EstrategiaDesconto {
    @Override
    public double aplicarDesconto(double valor) {
        return valor * 0.95; // 5% desconto
    }
    
    @Override
    public String getDescricao() {
        return "Cliente Regular (5% desconto)";
    }
}

class DescontoClientePremium implements EstrategiaDesconto {
    @Override
    public double aplicarDesconto(double valor) {
        return valor * 0.90; // 10% desconto
    }
    
    @Override
    public String getDescricao() {
        return "Cliente Premium (10% desconto)";
    }
}

class DescontoClienteVIP implements EstrategiaDesconto {
    @Override
    public double aplicarDesconto(double valor) {
        return valor * 0.80; // 20% desconto
    }
    
    @Override
    public String getDescricao() {
        return "Cliente VIP (20% desconto)";
    }
}

// Novas extensões ADICIONADAS sem modificar código existente!
class DescontoClienteCorporativo implements EstrategiaDesconto {
    @Override
    public double aplicarDesconto(double valor) {
        return valor * 0.75; // 25% desconto
    }
    
    @Override
    public String getDescricao() {
        return "Cliente Corporativo (25% desconto)";
    }
}

class DescontoEstudante implements EstrategiaDesconto {
    @Override
    public double aplicarDesconto(double valor) {
        return valor * 0.85; // 15% desconto
    }
    
    @Override
    public String getDescricao() {
        return "Estudante (15% desconto)";
    }
}

class DescontoBlackFriday implements EstrategiaDesconto {
    @Override
    public double aplicarDesconto(double valor) {
        return valor * 0.50; // 50% desconto
    }
    
    @Override
    public String getDescricao() {
        return "Promoção Black Friday (50% desconto)";
    }
}

// Calculadora que usa estratégia (FECHADA para modificação)
class CalculadoraDesconto {
    private EstrategiaDesconto estrategia;
    
    public CalculadoraDesconto(EstrategiaDesconto estrategia) {
        this.estrategia = estrategia;
    }
    
    public double calcular(double valor) {
        return estrategia.aplicarDesconto(valor);
    }
    
    public void setEstrategia(EstrategiaDesconto estrategia) {
        this.estrategia = estrategia;
    }
    
    public String getDescricaoDesconto() {
        return estrategia.getDescricao();
    }
}

// ==========================================
// OUTRO EXEMPLO: Sistema de Formas Geométricas
// ==========================================

// Interface para formas (permite extensão)
interface FormaOCP {
    double calcularArea();
    String getNome();
}

class RetanguloOCP implements FormaOCP {
    private double largura;
    private double altura;
    
    public RetanguloOCP(double largura, double altura) {
        this.largura = largura;
        this.altura = altura;
    }
    
    @Override
    public double calcularArea() {
        return largura * altura;
    }
    
    @Override
    public String getNome() {
        return String.format("Retângulo %.1fx%.1f", largura, altura);
    }
}

class CirculoOCP implements FormaOCP {
    private double raio;
    
    public CirculoOCP(double raio) {
        this.raio = raio;
    }
    
    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }
    
    @Override
    public String getNome() {
        return String.format("Círculo r=%.1f", raio);
    }
}

// Nova forma ADICIONADA sem modificar código existente!
class TrianguloOCP implements FormaOCP {
    private double base;
    private double altura;
    
    public TrianguloOCP(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }
    
    @Override
    public double calcularArea() {
        return (base * altura) / 2;
    }
    
    @Override
    public String getNome() {
        return String.format("Triângulo %.1fx%.1f", base, altura);
    }
}

// Calculadora que funciona com QUALQUER forma (fechada para modificação)
class CalculadoraArea {
    public double calcularAreaTotal(FormaOCP[] formas) {
        double total = 0;
        for (FormaOCP forma : formas) {
            total += forma.calcularArea();
        }
        return total;
    }
    
    public void imprimirAreas(FormaOCP[] formas) {
        System.out.println("Áreas calculadas:");
        for (FormaOCP forma : formas) {
            System.out.printf("  %s: %.2f\n", 
                            forma.getNome(), forma.calcularArea());
        }
    }
}

// ==========================================
// DEMONSTRAÇÃO E TESTES
// ==========================================

public class ExemploOCP {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  PRINCÍPIO ABERTO/FECHADO (OCP)                         ║");
        System.out.println("║  Open/Closed Principle                                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        // Exemplo RUIM
        System.out.println("❌ EXEMPLO RUIM - Modificação para cada novo tipo:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploRuim();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Exemplo BOM - Descontos
        System.out.println("✅ EXEMPLO BOM - Extensão sem modificação:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploBom();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Exemplo BOM - Formas geométricas
        System.out.println("✅ EXEMPLO BOM - Formas Geométricas:");
        System.out.println("─────────────────────────────────────────────────────────\n");
        demonstrarExemploFormas();
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Análise
        imprimirAnalise();
    }
    
    private static void demonstrarExemploRuim() {
        CalculadoraDescontoRuim calc = new CalculadoraDescontoRuim();
        double valorCompra = 1000.0;
        
        System.out.println("Valor da compra: R$ " + valorCompra);
        System.out.println("\nDescontos disponíveis:");
        System.out.println("  Regular:  R$ " + 
                         String.format("%.2f", calc.calcular("Regular", valorCompra)));
        System.out.println("  Premium:  R$ " + 
                         String.format("%.2f", calc.calcular("Premium", valorCompra)));
        System.out.println("  VIP:      R$ " + 
                         String.format("%.2f", calc.calcular("VIP", valorCompra)));
        
        System.out.println("\n⚠️  PROBLEMAS:");
        System.out.println("   • Para adicionar 'Corporativo': modificar método calcular()");
        System.out.println("   • Para adicionar 'Estudante': modificar método calcular()");
        System.out.println("   • Cada mudança requer recompilar e retestar TUDO");
        System.out.println("   • Código já testado é modificado (risco de bugs)");
        System.out.println("   • Viola princípio de fechamento para modificação");
    }
    
    private static void demonstrarExemploBom() {
        double valorCompra = 1000.0;
        System.out.println("Valor da compra: R$ " + valorCompra + "\n");
        
        // Array de todas as estratégias (incluindo as NOVAS!)
        EstrategiaDesconto[] estrategias = {
            new DescontoClienteRegular(),
            new DescontoClientePremium(),
            new DescontoClienteVIP(),
            new DescontoClienteCorporativo(),    // NOVA - adicionada!
            new DescontoEstudante(),              // NOVA - adicionada!
            new DescontoBlackFriday()             // NOVA - adicionada!
        };
        
        CalculadoraDesconto calculadora = new CalculadoraDesconto(estrategias[0]);
        
        System.out.println("Descontos disponíveis:");
        for (EstrategiaDesconto estrategia : estrategias) {
            calculadora.setEstrategia(estrategia);
            double valorFinal = calculadora.calcular(valorCompra);
            double economia = valorCompra - valorFinal;
            System.out.printf("  %s\n", estrategia.getDescricao());
            System.out.printf("    Valor final: R$ %.2f (economiza R$ %.2f)\n", 
                            valorFinal, economia);
        }
        
        System.out.println("\n✅ VANTAGENS:");
        System.out.println("   ✓ Novas estratégias ADICIONADAS sem modificar código existente");
        System.out.println("   ✓ CalculadoraDesconto permanece INALTERADA");
        System.out.println("   ✓ Código testado permanece estável");
        System.out.println("   ✓ Fácil adicionar DescontoCorporativo, DescontoEstudante, etc.");
        System.out.println("   ✓ Sem risco de quebrar funcionalidades existentes");
    }
    
    private static void demonstrarExemploFormas() {
        // Criar formas (incluindo nova forma Triangulo!)
        FormaOCP[] formas = {
            new RetanguloOCP(5, 4),
            new CirculoOCP(3),
            new TrianguloOCP(6, 4)  // NOVA forma - adicionada sem modificar nada!
        };
        
        // Calculadora funciona com TODAS as formas (código não modificado!)
        CalculadoraArea calculadora = new CalculadoraArea();
        
        calculadora.imprimirAreas(formas);
        
        double areaTotal = calculadora.calcularAreaTotal(formas);
        System.out.printf("\nÁrea total: %.2f\n", areaTotal);
        
        System.out.println("\n✅ VANTAGENS:");
        System.out.println("   ✓ Triangulo ADICIONADO sem modificar CalculadoraArea");
        System.out.println("   ✓ Podemos adicionar Trapézio, Losango, Hexágono...");
        System.out.println("   ✓ Cada forma é testada independentemente");
        System.out.println("   ✓ Sistema EXTENSÍVEL mas ESTÁVEL");
    }
    
    private static void imprimirAnalise() {
        System.out.println("📊 ANÁLISE COMPARATIVA\n");
        
        System.out.println("┌────────────────────────┬──────────────┬──────────────┐");
        System.out.println("│ Métrica                │ Sem OCP      │ Com OCP      │");
        System.out.println("├────────────────────────┼──────────────┼──────────────┤");
        System.out.println("│ Adicionar novo tipo    │ Modificar    │ Adicionar    │");
        System.out.println("│ Risco de regressão     │ Alto         │ Baixo        │");
        System.out.println("│ Código já testado      │ Afetado      │ Intocado     │");
        System.out.println("│ Extensibilidade        │ Difícil      │ Fácil        │");
        System.out.println("│ Manutenibilidade       │ Baixa        │ Alta         │");
        System.out.println("└────────────────────────┴──────────────┴──────────────┘");
        
        System.out.println("\n💡 PRINCÍPIO CHAVE:");
        System.out.println("   \"Aberto para EXTENSÃO, Fechado para MODIFICAÇÃO\"");
        System.out.println("\n🎯 COMO IDENTIFICAR VIOLAÇÃO:");
        System.out.println("   • Muitos if/else ou switch para tipos");
        System.out.println("   • Modificar código existente para adicionar funcionalidade");
        System.out.println("   • Comentários como 'TODO: adicionar tipo X'");
        System.out.println("\n✅ SOLUÇÃO:");
        System.out.println("   • Use interfaces ou classes abstratas");
        System.out.println("   • Implemente Strategy Pattern");
        System.out.println("   • Adicione comportamento através de herança/composição");
        System.out.println("\n✅ BENEFÍCIO PRINCIPAL:");
        System.out.println("   Sistema estável que cresce sem quebrar código existente");
    }
}
