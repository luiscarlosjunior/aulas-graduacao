/**
 * Teste Completo da Hierarquia de Funcionários - Exemplo Clássico da Literatura POO
 * 
 * Este teste demonstra polimorfismo avançado usando a hierarquia
 * Funcionario → Desenvolvedor/Gerente, que é um exemplo clássico encontrado
 * em livros como "Design Patterns", "Effective Java" e "Clean Code".
 * 
 * Conceitos demonstrados:
 * 1. Polimorfismo com arrays de objetos
 * 2. Métodos abstratos com implementações completamente diferentes
 * 3. Comportamentos específicos de cada classe
 * 4. Casting seguro e instanceof
 * 5. Overriding de métodos complexos
 * 6. Interação entre objetos polimórficos
 * 
 * @author Curso POO Java - Exemplo Clássico
 */
public class TesteFuncionarios {
    
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO: HIERARQUIA DE FUNCIONÁRIOS ===");
        System.out.println("📚 Exemplo clássico da literatura de POO para polimorfismo\n");
        
        // ===== 1. CRIAÇÃO DE FUNCIONÁRIOS DIFERENTES =====
        System.out.println("1️⃣ CRIANDO FUNCIONÁRIOS DE DIFERENTES TIPOS\n");
        
        // Criando desenvolvedores
        Desenvolvedor dev1 = new Desenvolvedor(
            "Ana Silva", "123.456.789-01", "DEV001", 4000.0,
            new String[]{"Java", "Python", "JavaScript"}, "Junior"
        );
        
        Desenvolvedor dev2 = new Desenvolvedor(
            "Carlos Santos", "987.654.321-02", "DEV002", 4000.0,
            new String[]{"Java", "Spring", "React", "Docker", "Kubernetes"}, "Senior"
        );
        
        // Criando gerentes
        Gerente ger1 = new Gerente(
            "Maria Costa", "555.666.777-03", "GER001", 8000.0,
            "Tecnologia", 8, new String[]{"Desenvolvimento", "DevOps", "QA"}
        );
        
        Gerente ger2 = new Gerente(
            "João Oliveira", "111.222.333-04", "GER002", 7000.0,
            "Vendas", 12, new String[]{"Vendas", "Marketing", "Customer Success"}
        );
        
        // ===== 2. DEMONSTRANDO POLIMORFISMO COM ARRAYS =====
        System.out.println("\n2️⃣ POLIMORFISMO - ARRAY DE FUNCIONÁRIOS\n");
        
        // Array polimórfico - todos são Funcionario, mas comportam-se diferentemente
        Funcionario[] empresa = {dev1, dev2, ger1, ger2};
        
        System.out.println("--- Processando folha de pagamento (polimorfismo) ---");
        double folhaPagamento = 0.0;
        
        for (Funcionario func : empresa) {
            System.out.printf("👤 %s (%s)\n", func.getNome(), func.getClass().getSimpleName());
            
            // POLIMORFISMO: mesmo método, comportamentos totalmente diferentes!
            double salario = func.calcularSalario();
            folhaPagamento += salario;
            
            System.out.printf("💰 Salário: R$ %.2f\n", salario);
            System.out.println("📊 Avaliação: " + func.avaliar());
            System.out.println();
        }
        
        System.out.printf("💸 Total da folha: R$ %.2f\n", folhaPagamento);
        
        // ===== 3. MÉTODOS POLIMÓRFICOS EM AÇÃO =====
        System.out.println("\n3️⃣ MÉTODOS POLIMÓRFICOS - TRABALHO E REUNIÕES\n");
        
        System.out.println("--- Todos trabalhando (cada um de forma diferente) ---");
        for (Funcionario func : empresa) {
            System.out.print("🔧 ");
            func.trabalhar(); // Polimorfismo: cada um trabalha diferente
            System.out.println();
        }
        
        System.out.println("--- Reunião geral da empresa ---");
        for (Funcionario func : empresa) {
            func.participarReuniao("All Hands"); // Mesmo método, respostas diferentes
            System.out.println();
        }
        
        // ===== 4. COMPORTAMENTOS ESPECÍFICOS (CASTING) =====
        System.out.println("4️⃣ COMPORTAMENTOS ESPECÍFICOS POR TIPO\n");
        
        for (Funcionario func : empresa) {
            System.out.println("Analisando: " + func.getNome());
            
            if (func instanceof Desenvolvedor) {
                System.out.println("✓ É um Desenvolvedor!");
                Desenvolvedor dev = (Desenvolvedor) func; // Cast seguro
                
                // Métodos específicos do desenvolvedor
                dev.codificar("Sistema de Login", "Java");
                dev.debugging("NullPointerException no módulo de autenticação");
                
                if (dev.getSenioridade().equals("Senior")) {
                    dev.tornarLiderTecnico();
                }
                
            } else if (func instanceof Gerente) {
                System.out.println("✓ É um Gerente!");
                Gerente ger = (Gerente) func; // Cast seguro
                
                // Métodos específicos do gerente
                ger.gerenciarEquipe("Desenvolver nova funcionalidade");
                ger.aprovarGasto(15000.0, "Compra de novos equipamentos");
                ger.definirMeta(50000.0);
            }
            System.out.println();
        }
        
        // ===== 5. INTERAÇÕES ENTRE FUNCIONÁRIOS =====
        System.out.println("5️⃣ INTERAÇÕES E COLABORAÇÃO\n");
        
        System.out.println("--- Gerente avaliando desenvolvedores ---");
        ger1.avaliarFuncionario(dev1);
        System.out.println();
        ger1.avaliarFuncionario(dev2);
        System.out.println();
        
        System.out.println("--- Code review entre desenvolvedores ---");
        dev2.revisarCodigo(dev1, "UserController.java");
        System.out.println();
        
        System.out.println("--- Desenvolvedor aprendendo nova tecnologia ---");
        dev1.aprenderLinguagem("Kotlin");
        System.out.println();
        
        // ===== 6. REUNIÕES ESPECÍFICAS =====
        System.out.println("6️⃣ REUNIÕES ESPECÍFICAS POR ÁREA\n");
        
        System.out.println("--- Reuniões técnicas (desenvolvedores) ---");
        dev1.participarReuniao("Daily");
        dev2.participarReuniao("Arquitetura");
        System.out.println();
        
        System.out.println("--- Reuniões estratégicas (gerentes) ---");
        ger1.participarReuniao("Diretoria");
        ger2.participarReuniao("Budget");
        System.out.println();
        
        // ===== 7. SIMULAÇÃO DE CRESCIMENTO PROFISSIONAL =====
        System.out.println("7️⃣ CRESCIMENTO E PROMOÇÕES\n");
        
        System.out.println("--- Desenvolvedor junior evoluindo ---");
        dev1.concluirProjeto();
        dev1.concluirProjeto();
        dev1.promover(2); // 2 anos de experiência
        System.out.println();
        
        System.out.println("--- Gerente expandindo equipe ---");
        ger1.expandirEquipe(3);
        ger1.atualizarResultado(25000.0);
        System.out.println();
        
        // ===== 8. COMPARAÇÕES E ANÁLISES =====
        System.out.println("8️⃣ ANÁLISES COMPARATIVAS\n");
        
        System.out.println("--- Comparando salários ---");
        for (int i = 0; i < empresa.length; i++) {
            for (int j = i + 1; j < empresa.length; j++) {
                Funcionario f1 = empresa[i];
                Funcionario f2 = empresa[j];
                
                int comparacao = f1.compararSalario(f2);
                String resultado;
                
                if (comparacao > 0) {
                    resultado = f1.getNome() + " ganha mais que " + f2.getNome();
                } else if (comparacao < 0) {
                    resultado = f2.getNome() + " ganha mais que " + f1.getNome();
                } else {
                    resultado = f1.getNome() + " e " + f2.getNome() + " ganham igual";
                }
                
                System.out.println("💰 " + resultado);
            }
        }
        
        // ===== 9. DEMONSTRAÇÃO DE POLIMORFISMO AVANÇADO =====
        System.out.println("\n9️⃣ POLIMORFISMO AVANÇADO\n");
        
        System.out.println("--- Método genérico que funciona para qualquer funcionário ---");
        realizarTreinamento(dev1, "Metodologias Ágeis");
        realizarTreinamento(ger1, "Liderança Estratégica");
        realizarTreinamento(dev2, "Clean Architecture");
        realizarTreinamento(ger2, "Negociação Avançada");
        
        System.out.println("\n--- Reunião virtual da empresa ---");
        organizarReuniaoVirtual("Resultados do Trimestre", empresa);
        
        // ===== 10. INFORMAÇÕES DETALHADAS =====
        System.out.println("\n🔟 INFORMAÇÕES DETALHADAS DE CADA FUNCIONÁRIO\n");
        
        for (Funcionario func : empresa) {
            func.exibirInformacoes(); // Polimorfismo: cada um exibe info específica
        }
        
        // ===== 11. SIMULAÇÃO DE CRISE E DEMISSÕES =====
        System.out.println("1️⃣1️⃣ SIMULAÇÃO DE REESTRUTURAÇÃO\n");
        
        System.out.println("--- Análise de performance para reestruturação ---");
        Funcionario piorPerformance = encontrarPiorPerformance(empresa);
        System.out.println("⚠️ Funcionário com menor salário (possível risco): " + 
                          piorPerformance.getNome());
        
        // Programa de desenvolvimento para funcionário em risco
        if (piorPerformance instanceof Desenvolvedor) {
            Desenvolvedor dev = (Desenvolvedor) piorPerformance;
            System.out.println("📚 Programa de desenvolvimento para " + dev.getNome() + ":");
            dev.aprenderLinguagem("Spring Boot");
            dev.participarTreinamento("Design Patterns");
        }
        
        // ===== 12. ESTATÍSTICAS FINAIS =====
        System.out.println("\n1️⃣2️⃣ ESTATÍSTICAS DA EMPRESA\n");
        
        gerarEstatisticas(empresa);
        
        // ===== RESUMO CONCEITUAL =====
        System.out.println("\n🎯 RESUMO DOS CONCEITOS DEMONSTRADOS\n");
        System.out.println("✅ Polimorfismo demonstrado com sucesso:");
        System.out.println("   1. Arrays polimórficos (Funcionario[] com diferentes tipos)");
        System.out.println("   2. Método calcularSalario() - MESMA assinatura, lógicas TOTALMENTE diferentes");
        System.out.println("   3. Métodos trabalhar() e participarReuniao() - comportamentos específicos");
        System.out.println("   4. Casting seguro com instanceof para acessar métodos específicos");
        System.out.println("   5. Interação entre objetos de diferentes tipos");
        System.out.println("   6. Métodos genéricos que funcionam para qualquer funcionário");
        System.out.println("   7. Sobrescrita de toString() e exibirInformacoes()");
        
        System.out.println("\n📚 Este é um dos exemplos mais poderosos de polimorfismo!");
        System.out.println("📖 Usado em livros clássicos: Design Patterns, Effective Java, Clean Code");
        System.out.println("🏆 Demonstra o verdadeiro poder da orientação a objetos");
        
        System.out.println("\n✅ Demonstração da hierarquia de funcionários concluída!");
    }
    
    // ===== MÉTODOS UTILITÁRIOS (DEMONSTRAM POLIMORFISMO) =====
    
    /**
     * Método genérico que funciona para qualquer tipo de funcionário
     * Demonstra como polimorfismo permite código reutilizável
     */
    public static void realizarTreinamento(Funcionario funcionario, String treinamento) {
        System.out.println("🎓 Organizando treinamento para " + funcionario.getNome());
        funcionario.participarTreinamento(treinamento);
        
        // Comportamento adicional baseado no tipo (sem casting)
        if (funcionario instanceof Gerente) {
            System.out.println("   📋 Gerente também receberá certificação de liderança");
        } else if (funcionario instanceof Desenvolvedor) {
            System.out.println("   💻 Desenvolvedor receberá acesso a laboratório prático");
        }
    }
    
    /**
     * Organizar reunião virtual para todos os funcionários
     */
    public static void organizarReuniaoVirtual(String tema, Funcionario[] funcionarios) {
        System.out.println("📺 Iniciando reunião virtual: " + tema);
        System.out.println("👥 Participantes: " + funcionarios.length + " funcionários\n");
        
        for (Funcionario func : funcionarios) {
            if (func.isAtivo()) {
                func.entrarReuniaoVirtual("Microsoft Teams");
                func.participarReuniao("Virtual"); // Polimorfismo!
                System.out.println();
            }
        }
        
        System.out.println("✅ Reunião virtual concluída com sucesso!");
    }
    
    /**
     * Encontrar funcionário com menor performance (menor salário)
     */
    public static Funcionario encontrarPiorPerformance(Funcionario[] funcionarios) {
        Funcionario pior = funcionarios[0];
        
        for (Funcionario func : funcionarios) {
            if (func.calcularSalario() < pior.calcularSalario()) { // Polimorfismo!
                pior = func;
            }
        }
        
        return pior;
    }
    
    /**
     * Gerar estatísticas da empresa
     */
    public static void gerarEstatisticas(Funcionario[] funcionarios) {
        double totalSalarios = 0.0;
        int desenvolvedores = 0;
        int gerentes = 0;
        int funcionariosAtivos = 0;
        
        System.out.println("📊 Relatório Estatístico da Empresa:");
        System.out.println("=====================================");
        
        for (Funcionario func : funcionarios) {
            if (func.isAtivo()) {
                funcionariosAtivos++;
                totalSalarios += func.calcularSalario(); // Polimorfismo!
                
                if (func instanceof Desenvolvedor) {
                    desenvolvedores++;
                } else if (func instanceof Gerente) {
                    gerentes++;
                }
            }
        }
        
        System.out.println("👥 Total de funcionários ativos: " + funcionariosAtivos);
        System.out.println("💻 Desenvolvedores: " + desenvolvedores);
        System.out.println("👔 Gerentes: " + gerentes);
        System.out.printf("💰 Folha de pagamento total: R$ %.2f\n", totalSalarios);
        System.out.printf("📈 Salário médio: R$ %.2f\n", totalSalarios / funcionariosAtivos);
        
        // Análise por tipo
        System.out.println("\n📋 Detalhamento por tipo:");
        for (Funcionario func : funcionarios) {
            if (func.isAtivo()) {
                System.out.printf("   %s: %s - R$ %.2f\n", 
                                func.getClass().getSimpleName(),
                                func.getNome(), 
                                func.calcularSalario());
            }
        }
        
        System.out.println("=====================================");
    }
}