/**
 * Exemplo seguindo princípio KISS (Keep It Simple, Stupid)
 * Solução simples, clara e direta para o problema
 * 
 * BENEFÍCIO: Código fácil de entender, testar e manter.
 * Resolve o mesmo problema com apenas ~20 linhas.
 */
public class Calculadora {
    
    public double somar(double a, double b) {
        return a + b;
    }
    
    public double subtrair(double a, double b) {
        return a - b;
    }
    
    public double multiplicar(double a, double b) {
        return a * b;
    }
    
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero não permitida");
        }
        return a / b;
    }
    
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        
        System.out.println("=== CALCULADORA SIMPLES (KISS) ===");
        System.out.println("5 + 3 = " + calc.somar(5, 3));
        System.out.println("5 - 3 = " + calc.subtrair(5, 3));
        System.out.println("5 * 3 = " + calc.multiplicar(5, 3));
        System.out.println("6 / 3 = " + calc.dividir(6, 3));
        
        System.out.println("\n=== BENEFÍCIOS DO KISS ===");
        System.out.println("1. Código simples e direto - fácil de entender");
        System.out.println("2. Apenas ~20 linhas vs ~70 linhas da versão complexa");
        System.out.println("3. Fácil de testar cada método");
        System.out.println("4. Fácil de manter e modificar");
        System.out.println("5. Não há complexidade desnecessária");
    }
}
