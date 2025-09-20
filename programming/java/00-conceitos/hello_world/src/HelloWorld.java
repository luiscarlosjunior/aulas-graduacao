/**
 * Programa Hello World - Primeiro programa em Java
 * 
 * Este é o programa mais simples que podemos escrever em Java.
 * Ele demonstra a estrutura básica de uma aplicação Java.
 * 
 * @author luiscaparroz
 * @version 1.0
 * @since JDK 1.8
 */
public class HelloWorld {

    /**
     * Método principal (main) - Ponto de entrada da aplicação
     * 
     * Este método é chamado automaticamente quando executamos o programa.
     * Todas as aplicações Java devem ter um método main com esta assinatura exata.
     * 
     * @param args - Argumentos passados pela linha de comando (array de strings)
     */
    public static void main(String[] args) {
        // System.out.println() imprime uma mensagem no console
        // System = classe que representa o sistema operacional
        // out = saída padrão (console/terminal)
        // println = método que imprime uma linha com quebra de linha no final
        System.out.println("Hello, world");
        
        // Demonstração: podemos imprimir múltiplas linhas
        System.out.println("Bem-vindo ao mundo da programação Java!");
        System.out.println("Este é meu primeiro programa.");
        
        // Diferença entre print() e println()
        System.out.print("Esta mensagem não quebra linha... ");
        System.out.print("Esta continua na mesma linha!");
        System.out.println(); // Quebra de linha vazia
        
        // Exemplo usando argumentos da linha de comando
        if (args.length > 0) {
            System.out.println("Você passou os seguintes argumentos:");
            for (int i = 0; i < args.length; i++) {
                System.out.println("Argumento " + (i + 1) + ": " + args[i]);
            }
        } else {
            System.out.println("Nenhum argumento foi passado pela linha de comando.");
            System.out.println("Tente executar: java HelloWorld seu_nome");
        }
    }
}
