/**
 * Hello World Detalhado - Com explicações comentadas
 * 
 * Este programa demonstra cada parte da estrutura básica
 * de uma aplicação Java com comentários explicativos.
 * 
 * @author Aulas Graduação
 */
public class HelloWorldDetalhado {
    
    /*
     * ESTRUTURA BÁSICA DE UM PROGRAMA JAVA:
     * 
     * 1. Declaração da classe (deve ter o mesmo nome do arquivo)
     * 2. Método main - ponto de entrada do programa
     * 3. Instruções dentro do método main
     */
    
    /**
     * Método main - onde o programa começa a executar
     * 
     * Características importantes:
     * - public: pode ser acessado de qualquer lugar
     * - static: pertence à classe, não a uma instância específica
     * - void: não retorna nenhum valor
     * - main: nome especial reconhecido pela JVM
     * - String[] args: parâmetros da linha de comando
     */
    public static void main(String[] args) {
        
        // System.out.println() - imprime texto e quebra linha
        System.out.println("=== PROGRAMA HELLO WORLD DETALHADO ===");
        
        // Múltiplas saídas
        System.out.println("Olá, mundo!");
        System.out.println("Este é meu primeiro programa em Java.");
        
        // System.out.print() - imprime sem quebrar linha
        System.out.print("Texto sem quebra... ");
        System.out.println("continuando na mesma linha!");
        
        // Concatenação de strings
        String linguagem = "Java";
        System.out.println("Estou aprendendo " + linguagem + "!");
        
        // Caracteres especiais
        System.out.println("Quebra de linha:\nNova linha aqui!");
        System.out.println("Tabulação:\tTexto com tab");
        System.out.println("Aspas: \"Texto entre aspas\"");
        
        System.out.println("=== FIM DO PROGRAMA ===");
    }
}