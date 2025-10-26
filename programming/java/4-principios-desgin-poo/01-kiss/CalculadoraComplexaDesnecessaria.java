/**
 * Exemplo de violação do princípio KISS
 * Complexidade desnecessária para problema simples
 * 
 * PROBLEMA: Este código usa padrões de design (Factory, Builder) para 
 * uma calculadora simples, tornando-o excessivamente complexo.
 */
public class CalculadoraComplexaDesnecessaria {
    
    // Interface complexa sem necessidade
    public interface OperacaoMatematica {
        double executar(double a, double b);
    }
    
    // Factory desnecessária para problema simples
    public static class OperacaoFactory {
        public OperacaoMatematica criarOperacao(String tipo) {
            switch(tipo) {
                case "SOMA":
                    return (a, b) -> a + b;
                case "SUBTRACAO":
                    return (a, b) -> a - b;
                case "MULTIPLICACAO":
                    return (a, b) -> a * b;
                case "DIVISAO":
                    return (a, b) -> a / b;
                default:
                    throw new IllegalArgumentException("Operação inválida");
            }
        }
    }
    
    // Builder desnecessário para estrutura simples
    public static class ResultadoBuilder {
        private double valor;
        private String operacao;
        private boolean sucesso;
        
        public ResultadoBuilder comValor(double valor) {
            this.valor = valor;
            return this;
        }
        
        public ResultadoBuilder comOperacao(String operacao) {
            this.operacao = operacao;
            return this;
        }
        
        public ResultadoBuilder comSucesso(boolean sucesso) {
            this.sucesso = sucesso;
            return this;
        }
        
        public Resultado build() {
            return new Resultado(valor, operacao, sucesso);
        }
    }
    
    // Classe de resultado excessivamente complexa
    public static class Resultado {
        private final double valor;
        private final String operacao;
        private final boolean sucesso;
        
        public Resultado(double valor, String operacao, boolean sucesso) {
            this.valor = valor;
            this.operacao = operacao;
            this.sucesso = sucesso;
        }
        
        public double getValor() { return valor; }
        public String getOperacao() { return operacao; }
        public boolean isSucesso() { return sucesso; }
    }
    
    // Método principal usando toda essa complexidade
    public Resultado calcular(double a, double b, String operacao) {
        OperacaoFactory factory = new OperacaoFactory();
        OperacaoMatematica op = factory.criarOperacao(operacao);
        double resultado = op.executar(a, b);
        
        return new ResultadoBuilder()
            .comValor(resultado)
            .comOperacao(operacao)
            .comSucesso(true)
            .build();
    }
    
    public static void main(String[] args) {
        CalculadoraComplexaDesnecessaria calc = new CalculadoraComplexaDesnecessaria();
        Resultado res = calc.calcular(5, 3, "SOMA");
        System.out.println("Resultado: " + res.getValor()); // 8.0 - muita cerimônia para simples soma!
        
        System.out.println("\n=== PROBLEMAS DESTE CÓDIGO ===");
        System.out.println("1. ~70 linhas de código para operações matemáticas básicas");
        System.out.println("2. Múltiplas classes e abstrações desnecessárias");
        System.out.println("3. Difícil de entender e manter");
        System.out.println("4. Over-engineering - complexidade sem justificativa");
    }
}
