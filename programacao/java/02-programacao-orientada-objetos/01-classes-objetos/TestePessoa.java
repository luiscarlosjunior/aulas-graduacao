/**
 * Programa de teste para a classe Pessoa
 * 
 * Demonstra como criar objetos, usar construtores diferentes
 * e chamar métodos da classe Pessoa.
 * 
 * @author Aulas Graduação
 */
public class TestePessoa {
    
    public static void main(String[] args) {
        System.out.println("=== TESTE DA CLASSE PESSOA ===\n");
        
        // ===== CRIANDO OBJETOS COM DIFERENTES CONSTRUTORES =====
        System.out.println("--- CRIANDO OBJETOS ---");
        
        // Usando construtor padrão
        Pessoa pessoa1 = new Pessoa();
        System.out.println("Pessoa 1 (construtor padrão): " + pessoa1);
        
        // Usando construtor com nome e idade
        Pessoa pessoa2 = new Pessoa("Ana Silva", 25);
        System.out.println("Pessoa 2 (nome e idade): " + pessoa2);
        
        // Usando construtor completo
        Pessoa pessoa3 = new Pessoa("Carlos Santos", 30, "carlos@email.com");
        System.out.println("Pessoa 3 (completo): " + pessoa3);
        
        System.out.println();
        
        // ===== USANDO MÉTODOS DOS OBJETOS =====
        System.out.println("--- USANDO MÉTODOS ---");
        
        // Método apresentar
        pessoa2.apresentar();
        System.out.println();
        
        pessoa3.apresentar();
        System.out.println();
        
        // Método fazer aniversário
        pessoa2.fazerAniversario();
        System.out.println();
        
        // Verificar se é maior de idade
        System.out.println(pessoa2.getNome() + " é maior de idade? " + pessoa2.ehMaiorIdade());
        
        // Criar uma pessoa menor de idade para teste
        Pessoa pessoaMenor = new Pessoa("João", 16);
        System.out.println(pessoaMenor.getNome() + " é maior de idade? " + pessoaMenor.ehMaiorIdade());
        
        System.out.println();
        
        // ===== USANDO GETTERS E SETTERS =====
        System.out.println("--- USANDO GETTERS E SETTERS ---");
        
        // Modificando dados usando setters
        pessoa1.setNome("Maria Oliveira");
        pessoa1.setIdade(22);
        pessoa1.setEmail("maria@email.com");
        
        System.out.println("Pessoa 1 após modificações: " + pessoa1);
        
        // Testando validações dos setters
        System.out.println("\nTestando validações:");
        pessoa1.setNome(""); // Nome vazio - deve mostrar erro
        pessoa1.setIdade(-5); // Idade inválida - deve mostrar erro
        pessoa1.setIdade(200); // Idade inválida - deve mostrar erro
        
        System.out.println();
        
        // ===== OUTROS MÉTODOS =====
        System.out.println("--- OUTROS MÉTODOS ---");
        
        // Calcular ano de nascimento
        System.out.println(pessoa2.getNome() + " nasceu aproximadamente em: " + 
                          pessoa2.calcularAnoNascimento());
        
        // Alterar status
        pessoa2.alterarStatus(); // Desativar
        pessoa2.alterarStatus(); // Reativar
        
        System.out.println();
        
        // ===== DEMONSTRAÇÃO DE MÚLTIPLOS OBJETOS =====
        System.out.println("--- MÚLTIPLOS OBJETOS ---");
        
        Pessoa[] pessoas = {
            new Pessoa("Alice", 28, "alice@email.com"),
            new Pessoa("Bruno", 35),
            new Pessoa("Carla", 19, "carla@email.com"),
            new Pessoa("Diego", 17)
        };
        
        System.out.println("Lista de pessoas:");
        for (int i = 0; i < pessoas.length; i++) {
            System.out.println((i + 1) + ". " + pessoas[i]);
            System.out.println("   Maior de idade: " + pessoas[i].ehMaiorIdade());
            System.out.println("   Ano nascimento: " + pessoas[i].calcularAnoNascimento());
            System.out.println();
        }
        
        // ===== ESTATÍSTICAS =====
        System.out.println("--- ESTATÍSTICAS ---");
        
        int totalMaiores = 0;
        int somaIdades = 0;
        
        for (Pessoa pessoa : pessoas) {
            if (pessoa.ehMaiorIdade()) {
                totalMaiores++;
            }
            somaIdades += pessoa.getIdade();
        }
        
        double mediaIdades = (double) somaIdades / pessoas.length;
        
        System.out.println("Total de pessoas: " + pessoas.length);
        System.out.println("Maiores de idade: " + totalMaiores);
        System.out.println("Menores de idade: " + (pessoas.length - totalMaiores));
        System.out.println("Média de idades: " + String.format("%.1f", mediaIdades));
        
        System.out.println("\n=== FIM DO TESTE ===");
    }
}