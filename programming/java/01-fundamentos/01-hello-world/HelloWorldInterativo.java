import java.util.Scanner;

/**
 * Hello World Interativo - Com entrada do usuário
 * 
 * Este programa demonstra como receber entrada do usuário
 * usando a classe Scanner.
 * 
 * @author Aulas Graduação
 */
public class HelloWorldInterativo {
    
    public static void main(String[] args) {
        // Criar um objeto Scanner para ler entrada do usuário
        Scanner scanner = new Scanner(System.in);
        
        // Saudação inicial
        System.out.println("=== PROGRAMA HELLO WORLD INTERATIVO ===");
        System.out.println();
        
        // Solicitar o nome do usuário
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        
        // Solicitar a idade
        System.out.print("Digite sua idade: ");
        int idade = scanner.nextInt();
        
        // Limpar o buffer (importante após nextInt())
        scanner.nextLine();
        
        // Solicitar a linguagem de programação favorita
        System.out.print("Qual sua linguagem de programação favorita? ");
        String linguagem = scanner.nextLine();
        
        // Exibir mensagem personalizada
        System.out.println();
        System.out.println("=== RESULTADO ===");
        System.out.println("Olá, " + nome + "!");
        System.out.println("Você tem " + idade + " anos.");
        System.out.println("Que legal que você gosta de " + linguagem + "!");
        System.out.println("Bem-vindo ao mundo da programação Java!");
        
        // Fechar o scanner (boa prática)
        scanner.close();
        
        System.out.println();
        System.out.println("Programa finalizado. Obrigado por participar!");
    }
}