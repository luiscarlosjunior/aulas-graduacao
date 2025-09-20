/**
 * Demonstração dos Tipos de Dados Primitivos em Java
 * 
 * Este programa demonstra como declarar, inicializar e usar os diferentes
 * tipos de dados primitivos disponíveis em Java, além de algumas operações básicas.
 * 
 * Tipos primitivos em Java: byte, short, int, long, float, double, char, boolean
 * 
 * @author luiscaparroz
 * @version 2.0
 * @since JDK 1.8
 */
public class TiposDados {	

    /**
     * Método principal que demonstra o uso de diferentes tipos de dados
     * @param args argumentos da linha de comando (não utilizados neste exemplo)
     */
    public static void main(String[] args) {
        
        System.out.println("=== DEMONSTRAÇÃO DOS TIPOS DE DADOS EM JAVA ===\n");
        
        // ==================== TIPOS NUMÉRICOS INTEIROS ====================
        
        // byte: menor tipo inteiro (8 bits) - valores de -128 a 127
        byte diasDaSemana = 7;
        System.out.println("TIPO BYTE:");
        System.out.println("Dias da semana (byte): " + diasDaSemana);
        System.out.println("Tamanho: 8 bits | Faixa: -128 a 127\n");
        
        // short: tipo inteiro médio (16 bits) - valores de -32,768 a 32,767
        short anoAtual = 2024;
        System.out.println("TIPO SHORT:");
        System.out.println("Ano atual (short): " + anoAtual);
        System.out.println("Tamanho: 16 bits | Faixa: -32,768 a 32,767\n");
        
        // int: tipo inteiro padrão (32 bits) - mais usado para números inteiros
        int populacaoBrasil = 215000000; // aproximadamente 215 milhões
        System.out.println("TIPO INT:");
        System.out.println("População do Brasil (int): " + populacaoBrasil);
        System.out.println("Tamanho: 32 bits | Faixa: -2,147,483,648 a 2,147,483,647\n");
        
        // long: maior tipo inteiro (64 bits) - para números muito grandes
        long distanciaLuaKm = 384400L; // Note o 'L' no final para indicar long
        System.out.println("TIPO LONG:");
        System.out.println("Distância até a Lua em km (long): " + distanciaLuaKm);
        System.out.println("Tamanho: 64 bits | Faixa: -9,223,372,036,854,775,808 a 9,223,372,036,854,775,807\n");
        
        // ==================== TIPOS NUMÉRICOS DECIMAIS ====================
        
        // float: precisão simples (32 bits) - até 7 dígitos decimais
        float altura = 1.85f; // Note o 'f' no final para indicar float
        System.out.println("TIPO FLOAT:");
        System.out.println("Minha altura (float): " + altura + "m");
        System.out.println("Tamanho: 32 bits | Precisão: ~7 dígitos decimais\n");
        
        // double: precisão dupla (64 bits) - até 15 dígitos decimais (padrão para decimais)
        double numeroPI = 3.141592653589793;
        System.out.println("TIPO DOUBLE:");
        System.out.println("Número PI (double): " + numeroPI);
        System.out.println("Tamanho: 64 bits | Precisão: ~15 dígitos decimais\n");
        
        // ==================== TIPO CARACTERE ====================
        
        // char: representa um único caractere Unicode (16 bits)
        char primeiraLetra = 'D';
        char simboloEspecial = '@';
        char numeroComoChar = '5'; // Atenção: '5' é diferente do número 5
        System.out.println("TIPO CHAR:");
        System.out.println("Primeira letra do meu nome (char): " + primeiraLetra);
        System.out.println("Símbolo especial (char): " + simboloEspecial);
        System.out.println("Número como caractere (char): " + numeroComoChar);
        System.out.println("Tamanho: 16 bits | Representa caracteres Unicode\n");
        
        // ==================== TIPO LÓGICO ====================
        
        // boolean: representa valores verdadeiro (true) ou falso (false)
        boolean souPessoa = true;
        boolean tenhoCarteiraMotorista = false;
        System.out.println("TIPO BOOLEAN:");
        System.out.println("Eu sou uma pessoa? " + souPessoa);
        System.out.println("Tenho carteira de motorista? " + tenhoCarteiraMotorista);
        System.out.println("Tamanho: 1 bit | Valores: true ou false\n");
        
        // ==================== TIPO STRING (CLASSE, NÃO PRIMITIVO) ====================
        
        // String: sequência de caracteres (classe, não tipo primitivo)
        String meuNome = "Luis Carlos";
        String saudacao = "Olá, mundo da programação!";
        System.out.println("TIPO STRING (classe):");
        System.out.println("Meu nome (String): " + meuNome);
        System.out.println("Saudação (String): " + saudacao);
        System.out.println("Observação: String é uma CLASSE, não um tipo primitivo\n");
        
        // ==================== OPERAÇÕES E CONVERSÕES ====================
        
        System.out.println("=== EXEMPLOS DE OPERAÇÕES ===\n");
        
        // Operações aritméticas
        int numero1 = 10;
        int numero2 = 3;
        System.out.println("Operações com inteiros:");
        System.out.println(numero1 + " + " + numero2 + " = " + (numero1 + numero2));
        System.out.println(numero1 + " - " + numero2 + " = " + (numero1 - numero2));
        System.out.println(numero1 + " * " + numero2 + " = " + (numero1 * numero2));
        System.out.println(numero1 + " / " + numero2 + " = " + (numero1 / numero2)); // Divisão inteira
        System.out.println(numero1 + " % " + numero2 + " = " + (numero1 % numero2)); // Resto da divisão
        
        // Concatenação de strings
        String nome = "Java";
        int versao = 21;
        System.out.println("\nConcatenação:");
        System.out.println("Linguagem: " + nome + " | Versão: " + versao);
        
        // Comparações
        System.out.println("\nComparações booleanas:");
        System.out.println("10 > 3: " + (10 > 3));
        System.out.println("5 == 5: " + (5 == 5));
        System.out.println("'A' == 'B': " + ('A' == 'B'));
        
        // ==================== DICAS IMPORTANTES ====================
        
        System.out.println("\n=== DICAS IMPORTANTES ===");
        System.out.println("1. Use 'int' para números inteiros na maioria dos casos");
        System.out.println("2. Use 'double' para números decimais na maioria dos casos");
        System.out.println("3. Use 'String' para textos");
        System.out.println("4. Use 'boolean' para valores verdadeiro/falso");
        System.out.println("5. Sempre inicialize suas variáveis antes de usar!");
        System.out.println("6. Cuidado com a divisão de inteiros: 10/3 = 3 (não 3.33)");
    }    
}
