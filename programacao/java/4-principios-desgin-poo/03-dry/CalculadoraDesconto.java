/**
 * Exemplo seguindo princípio DRY (Don't Repeat Yourself)
 * Lógica de desconto centralizada - única fonte de verdade
 * 
 * BENEFÍCIO: Mudanças são feitas em um único lugar.
 * Elimina inconsistências e facilita manutenção.
 */
public class CalculadoraDesconto {
    
    // ✅ DRY: Lógica de desconto por quantidade em UM lugar
    public double calcularDescontoPorQuantidade(int quantidade) {
        if (quantidade > 100) {
            return 0.15; // 15% desconto
        } else if (quantidade > 50) {
            return 0.10; // 10% desconto
        } else if (quantidade > 10) {
            return 0.05; // 5% desconto
        }
        return 0.0; // Sem desconto
    }
    
    // Métodos específicos REUTILIZAM lógica base
    public double calcularPrecoClienteRegular(double precoBase, int quantidade) {
        double desconto = calcularDescontoPorQuantidade(quantidade);
        return precoBase * quantidade * (1 - desconto);
    }
    
    public double calcularPrecoClienteVIP(double precoBase, int quantidade) {
        double descontoBase = calcularDescontoPorQuantidade(quantidade);
        double descontoVIP = 0.05; // Desconto adicional VIP
        double descontoTotal = descontoBase + descontoVIP;
        return precoBase * quantidade * (1 - descontoTotal);
    }
    
    public double calcularPrecoOnline(double precoBase, int quantidade) {
        double descontoBase = calcularDescontoPorQuantidade(quantidade);
        double descontoOnline = 0.02; // Desconto adicional online
        double descontoTotal = descontoBase + descontoOnline;
        return precoBase * quantidade * (1 - descontoTotal);
    }
    
    public static void main(String[] args) {
        CalculadoraDesconto calculadora = new CalculadoraDesconto();
        
        System.out.println("=== CALCULADORA COM DRY ===");
        System.out.println("Cliente Regular (120 unidades): R$ " + 
            calculadora.calcularPrecoClienteRegular(10.0, 120));
        System.out.println("Cliente VIP (120 unidades): R$ " + 
            calculadora.calcularPrecoClienteVIP(10.0, 120));
        System.out.println("Pedido Online (120 unidades): R$ " + 
            calculadora.calcularPrecoOnline(10.0, 120));
        
        System.out.println("\n=== BENEFÍCIOS DO DRY ===");
        System.out.println("1. Lógica de desconto em UM lugar (Single Source of Truth)");
        System.out.println("2. Mudança na regra afeta automaticamente todos os usos");
        System.out.println("3. Sem risco de inconsistências");
        System.out.println("4. Código mais fácil de manter");
        System.out.println("5. Mudança em calcularDescontoPorQuantidade() atualiza TUDO");
    }
}
