/**
 * Classes Wrapper em Java
 * 
 * Demonstra o uso das classes wrapper que encapsulam
 * os tipos primitivos, oferecendo funcionalidades adicionais.
 * 
 * @author Aulas Graduação
 */
public class ClassesWrapper {
    
    public static void main(String[] args) {
        System.out.println("=== CLASSES WRAPPER EM JAVA ===\n");
        
        // ===== DECLARAÇÃO E INICIALIZAÇÃO =====
        System.out.println("--- DECLARAÇÃO E INICIALIZAÇÃO ---");
        
        // Usando construtores (depreciado desde Java 9)
        // Integer idade = new Integer(25); // Não recomendado
        
        // Usando métodos valueOf() - RECOMENDADO
        Integer idade = Integer.valueOf(25);
        Double altura = Double.valueOf(1.75);
        Boolean temCNH = Boolean.valueOf(true);
        Character inicial = Character.valueOf('J');
        
        System.out.println("Integer idade: " + idade);
        System.out.println("Double altura: " + altura);
        System.out.println("Boolean temCNH: " + temCNH);
        System.out.println("Character inicial: " + inicial);
        
        // ===== AUTOBOXING E UNBOXING =====
        System.out.println("\n--- AUTOBOXING E UNBOXING ---");
        
        // Autoboxing: primitivo -> wrapper automaticamente
        Integer numero = 42;  // int -> Integer
        Double preco = 29.99; // double -> Double
        
        // Unboxing: wrapper -> primitivo automaticamente
        int valorNumero = numero;  // Integer -> int
        double valorPreco = preco; // Double -> double
        
        System.out.println("Autoboxing - numero: " + numero);
        System.out.println("Unboxing - valorNumero: " + valorNumero);
        
        // ===== MÉTODOS ÚTEIS DAS CLASSES WRAPPER =====
        System.out.println("\n--- MÉTODOS ÚTEIS ---");
        
        // Parsing de strings
        String textoNumero = "123";
        String textoDecimal = "45.67";
        String textoBoolean = "true";
        
        int numeroConvertido = Integer.parseInt(textoNumero);
        double decimalConvertido = Double.parseDouble(textoDecimal);
        boolean booleanConvertido = Boolean.parseBoolean(textoBoolean);
        
        System.out.println("parseInt(\"123\"): " + numeroConvertido);
        System.out.println("parseDouble(\"45.67\"): " + decimalConvertido);
        System.out.println("parseBoolean(\"true\"): " + booleanConvertido);
        
        // Conversão para string
        String numeroComoString = Integer.toString(456);
        String decimalComoString = Double.toString(78.9);
        
        System.out.println("toString(456): " + numeroComoString);
        System.out.println("toString(78.9): " + decimalComoString);
        
        // ===== COMPARAÇÃO =====
        System.out.println("\n--- COMPARAÇÃO ---");
        
        Integer num1 = 100;
        Integer num2 = 100;
        Integer num3 = new Integer(100);
        
        // Para valores pequenos (-128 a 127), Java usa cache
        System.out.println("num1 == num2 (cache): " + (num1 == num2)); // true
        System.out.println("num1 == num3 (new): " + (num1 == num3));   // false
        System.out.println("num1.equals(num3): " + num1.equals(num3)); // true - SEMPRE use equals()
        
        // ===== VALORES NULOS =====
        System.out.println("\n--- VALORES NULOS ---");
        
        Integer numeroNulo = null;
        System.out.println("Wrapper pode ser null: " + numeroNulo);
        
        // Cuidado com NullPointerException
        try {
            int valor = numeroNulo; // Unboxing de null gera exceção
        } catch (NullPointerException e) {
            System.out.println("NullPointerException ao fazer unboxing de null");
        }
        
        // ===== MÉTODOS DE VERIFICAÇÃO =====
        System.out.println("\n--- MÉTODOS DE VERIFICAÇÃO ---");
        
        // Character tem muitos métodos úteis
        char letra = 'A';
        char digito = '5';
        char espaco = ' ';
        
        System.out.println("isLetter('A'): " + Character.isLetter(letra));
        System.out.println("isDigit('5'): " + Character.isDigit(digito));
        System.out.println("isWhitespace(' '): " + Character.isWhitespace(espaco));
        System.out.println("toUpperCase('a'): " + Character.toUpperCase('a'));
        System.out.println("toLowerCase('B'): " + Character.toLowerCase('B'));
        
        // ===== CONSTANTES ÚTEIS =====
        System.out.println("\n--- CONSTANTES ÚTEIS ---");
        
        System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("Double.POSITIVE_INFINITY: " + Double.POSITIVE_INFINITY);
        System.out.println("Double.NaN: " + Double.NaN);
        System.out.println("Boolean.TRUE: " + Boolean.TRUE);
        System.out.println("Character.MIN_VALUE (unicode): " + (int)Character.MIN_VALUE);
    }
}