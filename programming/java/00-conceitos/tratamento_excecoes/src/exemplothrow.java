public class exemplothrow {
    public static void main(String[] args) {
        try {
            verificarIdade(15);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        try {
            verificarIdade(25);
            System.out.println("Idade válida!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    public static void verificarIdade(int idade) {
        // Usando throw para lançar uma exceção manualmente
        if (idade < 18) {
            throw new IllegalArgumentException("Idade deve ser maior ou igual a 18");
        }
        System.out.println("Idade verificada: " + idade);
    }
}