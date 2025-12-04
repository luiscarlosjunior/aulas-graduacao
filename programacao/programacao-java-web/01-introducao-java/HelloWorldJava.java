/**
 * Exemplo básico de programa Java - Hello World
 * Demonstra a estrutura mínima de um programa Java
 * 
 * @author Apresentação Java Web
 */
public class HelloWorldJava {
    
    /**
     * Método principal - ponto de entrada do programa
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        // Exibir mensagem simples
        System.out.println("Olá, Mundo Java!");
        
        // Exemplo com variáveis
        String linguagem = "Java";
        int versao = 17;
        double performance = 95.5;
        
        System.out.println("Linguagem: " + linguagem);
        System.out.println("Versão: " + versao);
        System.out.println("Performance: " + performance + "%");
        
        // Exemplo com operações básicas
        int numero1 = 10;
        int numero2 = 20;
        int soma = numero1 + numero2;
        
        System.out.println(numero1 + " + " + numero2 + " = " + soma);
        
        // Exemplo com controle de fluxo
        if (soma > 25) {
            System.out.println("A soma é maior que 25");
        } else {
            System.out.println("A soma é menor ou igual a 25");
        }
        
        // Exemplo com loop
        System.out.println("Contagem de 1 a 5:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Número: " + i);
        }
    }
}