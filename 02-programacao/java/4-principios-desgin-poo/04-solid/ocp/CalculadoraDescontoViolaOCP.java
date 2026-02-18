/**
 * Exemplo de violação do OCP (Open/Closed Principle)
 * Classe que requer modificação para adicionar novas funcionalidades
 * 
 * PROBLEMA: Adicionar novo tipo de desconto requer modificar
 * a classe existente, violando OCP (fechado para modificação).
 */
public class CalculadoraDescontoViolaOCP {
    
    // ❌ OCP: Adicionar novo tipo requer modificar este método
    public double calcularDesconto(String tipoDesconto, double valor) {
        if (tipoDesconto.equals("CLIENTE_REGULAR")) {
            return valor * 0.05; // 5% desconto
        } else if (tipoDesconto.equals("CLIENTE_VIP")) {
            return valor * 0.15; // 15% desconto
        } else if (tipoDesconto.equals("NATAL")) {
            return valor * 0.20; // 20% desconto
        } else if (tipoDesconto.equals("BLACK_FRIDAY")) {
            return valor * 0.30; // 30% desconto
        }
        // Para adicionar "DIA_DAS_MAES" preciso modificar ESTE código!
        // Viola OCP: não está fechado para modificação
        return 0;
    }
    
    public double calcularPrecoFinal(String tipoDesconto, double valor) {
        double desconto = calcularDesconto(tipoDesconto, valor);
        return valor - desconto;
    }
    
    public static void main(String[] args) {
        CalculadoraDescontoViolaOCP calculadora = new CalculadoraDescontoViolaOCP();
        
        System.out.println("=== VIOLAÇÃO DO OCP ===");
        System.out.println("Preço original: R$ 100.00");
        System.out.println("Cliente Regular: R$ " + 
            calculadora.calcularPrecoFinal("CLIENTE_REGULAR", 100.0));
        System.out.println("Cliente VIP: R$ " + 
            calculadora.calcularPrecoFinal("CLIENTE_VIP", 100.0));
        System.out.println("Black Friday: R$ " + 
            calculadora.calcularPrecoFinal("BLACK_FRIDAY", 100.0));
        
        System.out.println("\n=== PROBLEMAS ===");
        System.out.println("1. Para adicionar novo tipo de desconto, preciso MODIFICAR a classe");
        System.out.println("2. Viola OCP: não está fechado para modificação");
        System.out.println("3. Método calcularDesconto() cresce indefinidamente");
        System.out.println("4. Risco de quebrar funcionalidade existente ao adicionar nova");
        System.out.println("5. Difícil de testar novos descontos isoladamente");
        
        System.out.println("\n=== EXEMPLO ===");
        System.out.println("Para adicionar 'DIA_DAS_MAES', preciso:");
        System.out.println("1. Abrir esta classe");
        System.out.println("2. Modificar o método calcularDesconto()");
        System.out.println("3. Adicionar novo else-if");
        System.out.println("4. Isso VIOLA o princípio Open/Closed!");
    }
}
