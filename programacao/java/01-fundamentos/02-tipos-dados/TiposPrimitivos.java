/**
 * Tipos Primitivos em Java
 * 
 * Demonstra todos os tipos primitivos disponíveis em Java
 * com exemplos práticos de declaração, inicialização e uso.
 * 
 * @author Aulas Graduação
 */
public class TiposPrimitivos {
    
    public static void main(String[] args) {
        System.out.println("=== TIPOS PRIMITIVOS EM JAVA ===\n");
        
        // ===== TIPOS INTEIROS =====
        System.out.println("--- TIPOS INTEIROS ---");
        
        // byte: 8 bits (-128 a 127)
        byte idade = 25;
        System.out.println("byte - idade: " + idade + " (faixa: -128 a 127)");
        
        // short: 16 bits (-32,768 a 32,767)
        short populacaoCidade = 15000;
        System.out.println("short - população da cidade: " + populacaoCidade + " (faixa: -32,768 a 32,767)");
        
        // int: 32 bits (-2^31 a 2^31-1) - MAIS COMUM
        int salario = 5000;
        System.out.println("int - salário: " + salario + " (faixa: -2^31 a 2^31-1)");
        
        // long: 64 bits (-2^63 a 2^63-1) - usar 'L' no final
        long populacaoMundial = 7800000000L;
        System.out.println("long - população mundial: " + populacaoMundial + " (faixa: -2^63 a 2^63-1)");
        
        // ===== TIPOS DECIMAIS =====
        System.out.println("\n--- TIPOS DECIMAIS ---");
        
        // float: 32 bits (precisão simples) - usar 'f' no final
        float altura = 1.75f;
        System.out.println("float - altura: " + altura + "m (precisão simples)");
        
        // double: 64 bits (precisão dupla) - MAIS COMUM PARA DECIMAIS
        double pi = 3.14159265359;
        System.out.println("double - PI: " + pi + " (precisão dupla)");
        
        // ===== TIPO CARACTERE =====
        System.out.println("\n--- TIPO CARACTERE ---");
        
        // char: 16 bits (caractere Unicode)
        char inicial = 'J';
        char simbolo = '@';
        char unicode = '\u0041'; // 'A' em Unicode
        System.out.println("char - inicial: " + inicial);
        System.out.println("char - símbolo: " + simbolo);
        System.out.println("char - Unicode \\u0041: " + unicode);
        
        // ===== TIPO LÓGICO =====
        System.out.println("\n--- TIPO LÓGICO ---");
        
        // boolean: true ou false
        boolean temCNH = true;
        boolean ehMenorIdade = false;
        System.out.println("boolean - tem CNH: " + temCNH);
        System.out.println("boolean - é menor de idade: " + ehMenorIdade);
        
        // ===== DEMONSTRAÇÃO DE LIMITES =====
        System.out.println("\n--- VALORES MÁXIMOS E MÍNIMOS ---");
        System.out.println("byte min: " + Byte.MIN_VALUE + ", max: " + Byte.MAX_VALUE);
        System.out.println("short min: " + Short.MIN_VALUE + ", max: " + Short.MAX_VALUE);
        System.out.println("int min: " + Integer.MIN_VALUE + ", max: " + Integer.MAX_VALUE);
        System.out.println("long min: " + Long.MIN_VALUE + ", max: " + Long.MAX_VALUE);
        System.out.println("float min: " + Float.MIN_VALUE + ", max: " + Float.MAX_VALUE);
        System.out.println("double min: " + Double.MIN_VALUE + ", max: " + Double.MAX_VALUE);
        
        // ===== VALORES PADRÃO =====
        System.out.println("\n--- VALORES PADRÃO (em campos de classe) ---");
        System.out.println("byte/short/int: 0");
        System.out.println("long: 0L");
        System.out.println("float: 0.0f");
        System.out.println("double: 0.0d");
        System.out.println("char: '\\u0000' (null character)");
        System.out.println("boolean: false");
    }
}