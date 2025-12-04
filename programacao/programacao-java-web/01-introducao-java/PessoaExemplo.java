/**
 * Exemplo prático da classe Pessoa
 * Demonstra instanciação, uso de métodos e manipulação de objetos
 * 
 * @author Apresentação Java Web
 */
public class PessoaExemplo {
    
    public static void main(String[] args) {
        System.out.println("=== Exemplo de Orientação a Objetos em Java ===\n");
        
        // Criando objetos usando construtor padrão
        Pessoa pessoa1 = new Pessoa();
        System.out.println("Pessoa1 (construtor padrão): " + pessoa1);
        
        // Configurando dados usando setters
        pessoa1.setNome("Maria Silva");
        pessoa1.setIdade(28);
        pessoa1.setEmail("maria.silva@email.com");
        pessoa1.setSalario(3500.00);
        
        System.out.println("Pessoa1 (após configuração): " + pessoa1);
        pessoa1.exibirInformacoes();
        
        // Criando objeto usando construtor com parâmetros
        Pessoa pessoa2 = new Pessoa("João Santos", 35, "joao.santos@empresa.com", 6500.00);
        System.out.println("Pessoa2 (construtor completo): " + pessoa2);
        pessoa2.exibirInformacoes();
        
        // Demonstrando métodos de negócio
        System.out.println("=== Operações de Negócio ===");
        
        // Aumento salarial
        System.out.printf("Salário atual de %s: R$ %.2f%n", pessoa1.getNome(), pessoa1.getSalario());
        pessoa1.aumentarSalario(10); // Aumento de 10%
        System.out.printf("Salário após aumento de 10%%: R$ %.2f%n", pessoa1.getSalario());
        
        System.out.printf("Salário atual de %s: R$ %.2f%n", pessoa2.getNome(), pessoa2.getSalario());
        pessoa2.aumentarSalario(5); // Aumento de 5%
        System.out.printf("Salário após aumento de 5%%: R$ %.2f%n", pessoa2.getSalario());
        
        // Comparando categorias profissionais
        System.out.println("\n=== Comparação de Categorias ===");
        System.out.println(pessoa1.getNome() + " - Categoria: " + pessoa1.getCategoriaProfissional());
        System.out.println(pessoa2.getNome() + " - Categoria: " + pessoa2.getCategoriaProfissional());
        
        // Criando mais exemplos para demonstrar diferentes categorias
        Pessoa estagiario = new Pessoa("Pedro Oliveira", 19, "pedro@estudante.com", 800.00);
        Pessoa diretor = new Pessoa("Ana Costa", 45, "ana.costa@empresa.com", 12000.00);
        
        System.out.println("\n=== Diferentes Níveis Profissionais ===");
        System.out.println("Estagiário: " + estagiario);
        System.out.println("Diretor: " + diretor);
        
        // Array de pessoas para demonstrar coleções
        Pessoa[] equipe = {pessoa1, pessoa2, estagiario, diretor};
        
        System.out.println("\n=== Relatório da Equipe ===");
        double totalFolha = 0;
        int totalMaiores = 0;
        
        for (int i = 0; i < equipe.length; i++) {
            Pessoa p = equipe[i];
            System.out.printf("%d. %s - %s anos - %s - R$ %.2f%n", 
                i + 1, p.getNome(), p.getIdade(), p.getCategoriaProfissional(), p.getSalario());
            
            totalFolha += p.getSalario();
            if (p.isMaiorIdade()) {
                totalMaiores++;
            }
        }
        
        System.out.printf("\nTotal da folha salarial: R$ %.2f%n", totalFolha);
        System.out.printf("Pessoas maiores de idade: %d de %d%n", totalMaiores, equipe.length);
        System.out.printf("Média salarial: R$ %.2f%n", totalFolha / equipe.length);
        
        // Demonstrando validações
        System.out.println("\n=== Testando Validações ===");
        Pessoa pessoaTeste = new Pessoa();
        
        pessoaTeste.setNome(""); // Nome vazio - não deve alterar
        System.out.println("Nome após tentar definir vazio: " + pessoaTeste.getNome());
        
        pessoaTeste.setIdade(-5); // Idade inválida - não deve alterar
        System.out.println("Idade após tentar definir -5: " + pessoaTeste.getIdade());
        
        pessoaTeste.setEmail("email-inválido"); // Email sem @ - não deve alterar
        System.out.println("Email após tentar definir inválido: " + pessoaTeste.getEmail());
        
        pessoaTeste.setSalario(-1000); // Salário negativo - não deve alterar
        System.out.println("Salário após tentar definir negativo: " + pessoaTeste.getSalario());
        
        System.out.println("\n=== Fim do Exemplo ===");
    }
}