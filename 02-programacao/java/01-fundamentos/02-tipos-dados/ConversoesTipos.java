/**
 * Conversões entre Tipos em Java
 * 
 * Demonstra conversões implícitas (widening) e explícitas (narrowing)
 * entre diferentes tipos de dados.
 * 
 * @author Aulas Graduação
 */
public class ConversoesTipos {
    
    public static void main(String[] args) {
        System.out.println("=== CONVERSÕES ENTRE TIPOS ===\n");
        
        // ===== CONVERSÕES IMPLÍCITAS (WIDENING) =====
        System.out.println("--- CONVERSÕES IMPLÍCITAS (WIDENING) ---");
        System.out.println("De tipo menor para tipo maior - automática, sem perda de dados\n");
        
        // byte -> short -> int -> long -> float -> double
        byte valorByte = 10;
        short valorShort = valorByte;    // byte -> short
        int valorInt = valorShort;       // short -> int
        long valorLong = valorInt;       // int -> long
        float valorFloat = valorLong;    // long -> float
        double valorDouble = valorFloat; // float -> double
        
        System.out.println("byte: " + valorByte);
        System.out.println("short: " + valorShort);
        System.out.println("int: " + valorInt);
        System.out.println("long: " + valorLong);
        System.out.println("float: " + valorFloat);
        System.out.println("double: " + valorDouble);
        
        // char para int
        char caractere = 'A';
        int codigoASCII = caractere; // char -> int (código Unicode)
        System.out.println("\nchar 'A' -> int: " + codigoASCII);
        
        // ===== CONVERSÕES EXPLÍCITAS (NARROWING) =====
        System.out.println("\n--- CONVERSÕES EXPLÍCITAS (NARROWING) ---");
        System.out.println("De tipo maior para tipo menor - manual, pode haver perda de dados\n");
        
        // double -> float -> long -> int -> short -> byte
        double numeroGrande = 123.456;
        float paraFloat = (float) numeroGrande;   // Perda de precisão
        long paraLong = (long) paraFloat;         // Perda da parte decimal
        int paraInt = (int) paraLong;             // Possível perda se valor for muito grande
        short paraShort = (short) paraInt;        // Possível perda se > 32767
        byte paraByte = (byte) paraShort;         // Possível perda se > 127
        
        System.out.println("double original: " + numeroGrande);
        System.out.println("para float: " + paraFloat);
        System.out.println("para long: " + paraLong);
        System.out.println("para int: " + paraInt);
        System.out.println("para short: " + paraShort);
        System.out.println("para byte: " + paraByte);
        
        // ===== DEMONSTRAÇÃO DE OVERFLOW =====
        System.out.println("\n--- DEMONSTRAÇÃO DE OVERFLOW ---");
        
        int valorGrande = 130;
        byte overflow = (byte) valorGrande; // 130 > 127 (max byte)
        System.out.println("int 130 -> byte: " + overflow + " (overflow!)");
        
        // Explicação: 130 em binário vira -126 em byte (complemento de dois)
        
        // ===== CONVERSÕES COM STRINGS =====
        System.out.println("\n--- CONVERSÕES COM STRINGS ---");
        
        // String para primitivos
        String textoNumero = "42";
        String textoDecimal = "3.14";
        String textoBoolean = "true";
        
        int numeroDeString = Integer.parseInt(textoNumero);
        double decimalDeString = Double.parseDouble(textoDecimal);
        boolean booleanDeString = Boolean.parseBoolean(textoBoolean);
        
        System.out.println("String \"42\" -> int: " + numeroDeString);
        System.out.println("String \"3.14\" -> double: " + decimalDeString);
        System.out.println("String \"true\" -> boolean: " + booleanDeString);
        
        // Primitivos para String
        int numero = 100;
        double decimal = 2.718;
        boolean verdadeiro = true;
        
        String numeroParaString = String.valueOf(numero);
        String decimalParaString = String.valueOf(decimal);
        String booleanParaString = String.valueOf(verdadeiro);
        
        System.out.println("int 100 -> String: \"" + numeroParaString + "\"");
        System.out.println("double 2.718 -> String: \"" + decimalParaString + "\"");
        System.out.println("boolean true -> String: \"" + booleanParaString + "\"");
        
        // ===== CONVERSÕES COM CÁLCULOS =====
        System.out.println("\n--- CONVERSÕES EM CÁLCULOS ---");
        
        // Promoção automática em expressões
        byte a = 10;
        byte b = 20;
        // byte resultado = a + b; // ERRO! Resultado é int
        int resultado = a + b; // Correto
        
        System.out.println("byte + byte = int: " + resultado);
        
        // Operações com diferentes tipos
        int inteiro = 10;
        double decimalOp = 3.0;
        double resultadoMisto = inteiro / decimalOp; // int promovido para double
        
        System.out.println("int / double = double: " + resultadoMisto);
        
        // ===== CUIDADOS E BOAS PRÁTICAS =====
        System.out.println("\n--- CUIDADOS E BOAS PRÁTICAS ---");
        
        // 1. Divisão inteira
        int x = 5;
        int y = 2;
        int divisaoInteira = x / y;           // 2 (não 2.5!)
        double divisaoCorreta = (double) x / y; // 2.5
        
        System.out.println("5 / 2 (int): " + divisaoInteira);
        System.out.println("5 / 2 (double): " + divisaoCorreta);
        
        // 2. Cuidado com parseXxx - pode gerar NumberFormatException
        try {
            int numeroInvalido = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter \"abc\" para int: " + e.getMessage());
        }
        
        // 3. Verificação antes de conversões que podem perder dados
        long numeroLongo = 2147483648L; // Maior que Integer.MAX_VALUE
        if (numeroLongo <= Integer.MAX_VALUE && numeroLongo >= Integer.MIN_VALUE) {
            int conversaoSegura = (int) numeroLongo;
            System.out.println("Conversão segura: " + conversaoSegura);
        } else {
            System.out.println("Conversão unsafe: valor " + numeroLongo + " não cabe em int");
        }
    }
}