/**
 * Exemplo de violação do princípio DRY (Don't Repeat Yourself)
 * Código duplicado em múltiplos lugares
 * 
 * PROBLEMA: Mudança na lógica requer atualizar todos os lugares.
 * Alto risco de inconsistência.
 */
public class SistemaVendasComDuplicacao {
    
    // ❌ DRY: Lógica de desconto duplicada para clientes regulares
    public double calcularPrecoClienteRegular(double precoBase, int quantidade) {
        double desconto = 0;
        if (quantidade > 100) {
            desconto = 0.15; // 15% desconto
        } else if (quantidade > 50) {
            desconto = 0.10; // 10% desconto
        } else if (quantidade > 10) {
            desconto = 0.05; // 5% desconto
        }
        return precoBase * quantidade * (1 - desconto);
    }
    
    // ❌ DRY: Mesma lógica duplicada para clientes VIP
    public double calcularPrecoClienteVIP(double precoBase, int quantidade) {
        double desconto = 0;
        if (quantidade > 100) {
            desconto = 0.15; // 15% desconto - DUPLICADO!
        } else if (quantidade > 50) {
            desconto = 0.10; // 10% desconto - DUPLICADO!
        } else if (quantidade > 10) {
            desconto = 0.05; // 5% desconto - DUPLICADO!
        }
        // VIPs têm desconto adicional
        desconto += 0.05;
        return precoBase * quantidade * (1 - desconto);
    }
    
    // ❌ DRY: Mesma lógica duplicada para pedidos online
    public double calcularPrecoOnline(double precoBase, int quantidade) {
        double desconto = 0;
        if (quantidade > 100) {
            desconto = 0.15; // DUPLICADO NOVAMENTE!
        } else if (quantidade > 50) {
            desconto = 0.10;
        } else if (quantidade > 10) {
            desconto = 0.05;
        }
        // Desconto adicional online
        desconto += 0.02;
        return precoBase * quantidade * (1 - desconto);
    }
    
    public static void main(String[] args) {
        SistemaVendasComDuplicacao sistema = new SistemaVendasComDuplicacao();
        
        System.out.println("=== SISTEMA COM DUPLICAÇÃO ===");
        System.out.println("Cliente Regular (120 unidades): R$ " + 
            sistema.calcularPrecoClienteRegular(10.0, 120));
        System.out.println("Cliente VIP (120 unidades): R$ " + 
            sistema.calcularPrecoClienteVIP(10.0, 120));
        System.out.println("Pedido Online (120 unidades): R$ " + 
            sistema.calcularPrecoOnline(10.0, 120));
        
        System.out.println("\n=== PROBLEMAS DA DUPLICAÇÃO ===");
        System.out.println("1. Lógica de desconto duplicada 3 vezes");
        System.out.println("2. Se mudar regra (>100 = 20%), precisa mudar 3 lugares");
        System.out.println("3. Alto risco de esquecer de atualizar um lugar");
        System.out.println("4. Inconsistências podem surgir facilmente");
        System.out.println("5. Código mais difícil de manter");
    }
}
