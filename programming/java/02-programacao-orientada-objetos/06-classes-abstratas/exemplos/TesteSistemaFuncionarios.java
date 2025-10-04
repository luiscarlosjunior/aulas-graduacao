/**
 * Classe de Teste - Sistema de Funcionários
 * 
 * Demonstra o uso de classes abstratas no contexto de um sistema
 * de gerenciamento de funcionários com diferentes tipos de cargos.
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class TesteSistemaFuncionarios {
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║      SISTEMA DE GESTÃO DE FUNCIONÁRIOS - EMPRESA XYZ     ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        // Criando diferentes tipos de funcionários
        Gerente gerente = new Gerente(
            "Ana Silva", 
            8000.0, 
            "Tecnologia", 
            3000.0, 
            3  // Nível Senior
        );
        
        Vendedor vendedor = new Vendedor(
            "Carlos Souza", 
            3000.0, 
            "Vendas", 
            0.05  // 5% de comissão
        );
        
        Desenvolvedor dev1 = new Desenvolvedor(
            "Marina Costa", 
            6000.0, 
            "Tecnologia", 
            "Java, Python, JavaScript"
        );
        
        Desenvolvedor dev2 = new Desenvolvedor(
            "Pedro Santos", 
            5500.0, 
            "Tecnologia", 
            "C#, .NET, SQL"
        );
        
        // 1. Simulando atividades
        System.out.println("=== REGISTRANDO ATIVIDADES DO MÊS ===\n");
        
        // Vendedor registra vendas
        System.out.println("📊 " + vendedor.getNome() + " - Vendas:");
        vendedor.registrarVenda(15000.0);
        vendedor.registrarVenda(22000.0);
        vendedor.registrarVenda(18500.0);
        System.out.println("Total em vendas: R$ " + 
            String.format("%.2f", vendedor.getVendasRealizadas()) + "\n");
        
        // Desenvolvedores concluem projetos
        System.out.println("💻 " + dev1.getNome() + " - Projetos:");
        dev1.adicionarProjeto();
        dev1.adicionarProjeto();
        dev1.adicionarProjeto();
        System.out.println();
        
        System.out.println("💻 " + dev2.getNome() + " - Projetos:");
        dev2.adicionarProjeto();
        dev2.adicionarProjeto();
        System.out.println();
        
        // 2. Exibindo informações de todos os funcionários
        System.out.println("=== FOLHA DE PAGAMENTO DO MÊS ===\n");
        
        // Array polimórfico - Todos são tratados como Funcionario
        Funcionario[] funcionarios = {gerente, vendedor, dev1, dev2};
        
        double totalFolha = 0.0;
        for (Funcionario func : funcionarios) {
            func.exibirInformacoes();
            totalFolha += func.calcularSalario();
        }
        
        // 3. Resumo financeiro
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    RESUMO FINANCEIRO                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║  Total da Folha de Pagamento: R$ %19.2f    ║%n", totalFolha);
        System.out.printf("║  Número de Funcionários:      %26d    ║%n", funcionarios.length);
        System.out.printf("║  Média Salarial:              R$ %19.2f    ║%n", totalFolha / funcionarios.length);
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        // 4. Demonstrando polimorfismo
        System.out.println("=== DEMONSTRAÇÃO DE POLIMORFISMO ===\n");
        System.out.println("📌 Todos os objetos foram tratados como 'Funcionario'");
        System.out.println("📌 Cada um executou seu próprio calcularSalario()");
        System.out.println("📌 Cada um exibiu seus próprios detalhes específicos");
        System.out.println("📌 Isso é ABSTRAÇÃO em ação! 🚀\n");
        
        // 5. Análise por tipo
        System.out.println("=== ANÁLISE POR TIPO DE FUNCIONÁRIO ===\n");
        for (Funcionario func : funcionarios) {
            if (func instanceof Gerente) {
                System.out.println("👔 " + func.getNome() + " é um Gerente");
            } else if (func instanceof Vendedor) {
                System.out.println("📈 " + func.getNome() + " é um Vendedor");
            } else if (func instanceof Desenvolvedor) {
                System.out.println("💻 " + func.getNome() + " é um Desenvolvedor");
            }
        }
        
        System.out.println("\n✅ Sistema executado com sucesso!");
    }
}
