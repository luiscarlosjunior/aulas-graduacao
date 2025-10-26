/**
 * Exemplo seguindo OCP (Open/Closed Principle)
 * Aberto para extensão, fechado para modificação
 * 
 * BENEFÍCIO: Novos tipos de desconto são adicionados criando novas classes,
 * sem modificar código existente.
 */

// ✅ OCP: Interface abstrata - define contrato
interface Desconto {
    double calcular(double valor);
}

// ✅ OCP: Cada tipo de desconto é uma classe que ESTENDE funcionalidade
class DescontoClienteRegular implements Desconto {
    @Override
    public double calcular(double valor) {
        return valor * 0.05; // 5% desconto
    }
}

class DescontoClienteVIP implements Desconto {
    @Override
    public double calcular(double valor) {
        return valor * 0.15; // 15% desconto
    }
}

class DescontoNatal implements Desconto {
    @Override
    public double calcular(double valor) {
        return valor * 0.20; // 20% desconto
    }
}

class DescontoBlackFriday implements Desconto {
    @Override
    public double calcular(double valor) {
        return valor * 0.30; // 30% desconto
    }
}

// ✅ OCP: Adicionar novo desconto é FÁCIL - apenas criar nova classe!
// Não precisa modificar nenhuma classe existente!
class DescontoDiaDosMaes implements Desconto {
    @Override
    public double calcular(double valor) {
        return valor * 0.25; // 25% desconto
    }
}

// ✅ OCP: Calculadora está FECHADA para modificação
// Mas ABERTA para extensão (aceita qualquer Desconto)
class CalculadoraDesconto {
    
    public double calcularPrecoFinal(Desconto desconto, double valor) {
        double valorDesconto = desconto.calcular(valor);
        return valor - valorDesconto;
    }
}

public class CalculadoraDescontoSegueOCP {
    public static void main(String[] args) {
        CalculadoraDesconto calculadora = new CalculadoraDesconto();
        double precoOriginal = 100.0;
        
        System.out.println("=== SEGUINDO OCP ===");
        System.out.println("Preço original: R$ " + precoOriginal);
        
        // Usando diferentes estratégias de desconto
        System.out.println("\nCliente Regular: R$ " + 
            calculadora.calcularPrecoFinal(new DescontoClienteRegular(), precoOriginal));
        
        System.out.println("Cliente VIP: R$ " + 
            calculadora.calcularPrecoFinal(new DescontoClienteVIP(), precoOriginal));
        
        System.out.println("Black Friday: R$ " + 
            calculadora.calcularPrecoFinal(new DescontoBlackFriday(), precoOriginal));
        
        // ✅ Novo desconto adicionado SEM modificar CalculadoraDesconto!
        System.out.println("Dia das Mães: R$ " + 
            calculadora.calcularPrecoFinal(new DescontoDiaDosMaes(), precoOriginal));
        
        System.out.println("\n=== BENEFÍCIOS DO OCP ===");
        System.out.println("1. ✓ ABERTO para extensão - fácil adicionar novos tipos");
        System.out.println("2. ✓ FECHADO para modificação - não altera código existente");
        System.out.println("3. ✓ Novo desconto = nova classe (não modifica existentes)");
        System.out.println("4. ✓ Zero risco de quebrar funcionalidade existente");
        System.out.println("5. ✓ Cada desconto é testável isoladamente");
        System.out.println("6. ✓ Código mais seguro e manutenível");
    }
}
