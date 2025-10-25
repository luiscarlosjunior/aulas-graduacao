public class TesteInterpreter {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  PADRÃO INTERPRETER - Calculadora     ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        // 10 + 5
        Expression expr1 = new AddExpression(
            new NumberExpression(10),
            new NumberExpression(5)
        );
        System.out.println("10 + 5 = " + expr1.interpret());
        
        // 20 - 8
        Expression expr2 = new SubtractExpression(
            new NumberExpression(20),
            new NumberExpression(8)
        );
        System.out.println("20 - 8 = " + expr2.interpret());
        
        // (10 + 5) - (20 - 8)
        Expression expr3 = new SubtractExpression(expr1, expr2);
        System.out.println("(10 + 5) - (20 - 8) = " + expr3.interpret());
        
        System.out.println("\n✓ Interpreter demonstrado com sucesso!");
    }
}
