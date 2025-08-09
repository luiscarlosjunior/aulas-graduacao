import java.util.Scanner;

/**
 * Exemplo de entrada de dados em Java
 * Demonstra como receber entrada do usuário
 * 
 * @author Apresentação Java Web
 */
public class EntradaDados {
    
    public static void main(String[] args) {
        // Criar objeto Scanner para ler entrada do usuário
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar e ler dados do usuário
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Digite sua idade: ");
        int idade = scanner.nextInt();
        
        System.out.print("Digite seu salário: ");
        double salario = scanner.nextDouble();
        
        // Processar e exibir informações
        System.out.println("\n--- Informações do Usuário ---");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade + " anos");
        System.out.printf("Salário: R$ %.2f%n", salario);
        
        // Validações básicas
        if (idade >= 18) {
            System.out.println("Status: Maior de idade");
        } else {
            System.out.println("Status: Menor de idade");
        }
        
        // Cálculo de categoria salarial
        String categoria;
        if (salario < 1500) {
            categoria = "Iniciante";
        } else if (salario < 5000) {
            categoria = "Intermediário";
        } else {
            categoria = "Sênior";
        }
        
        System.out.println("Categoria Profissional: " + categoria);
        
        // Calcular salário anual
        double salarioAnual = salario * 12;
        System.out.printf("Salário Anual: R$ %.2f%n", salarioAnual);
        
        // Fechar o scanner
        scanner.close();
        
        System.out.println("\nPrograma finalizado com sucesso!");
    }
}