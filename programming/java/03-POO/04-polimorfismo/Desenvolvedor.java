/**
 * Exemplo Clássico: Desenvolvedor (classe filha de Funcionario)
 * 
 * Demonstra polimorfismo específico para desenvolvedores de software:
 * - Cálculo de salário baseado em linguagens de programação e projetos
 * - Comportamentos específicos (codificar, revisar código, debugging)
 * - Participação específica em reuniões técnicas
 * 
 * @author Curso POO Java - Exemplo Clássico
 */
public class Desenvolvedor extends Funcionario {
    
    // ===== ATRIBUTOS ESPECÍFICOS DO DESENVOLVEDOR =====
    private String[] linguagensProgramacao;    // Linguagens que domina
    private String senioridade;                // Junior, Pleno, Senior
    private int projetosConcluidos;            // Número de projetos finalizados
    private boolean liderTecnico;              // Se é líder técnico
    private double bonusProjetividade;         // Bônus por produtividade
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor específico para desenvolvedor
     */
    public Desenvolvedor(String nome, String cpf, String matricula, double salarioBase,
                        String[] linguagensProgramacao, String senioridade) {
        
        // Chama construtor da classe pai
        super(nome, cpf, matricula, salarioBase, "Tecnologia");
        
        this.linguagensProgramacao = linguagensProgramacao;
        this.senioridade = senioridade;
        this.projetosConcluidos = 0;
        this.liderTecnico = false;
        this.bonusProjetividade = 0.0;
        
        // Ajusta salário baseado na senioridade
        ajustarSalarioPorSenioridade();
        
        System.out.println("💻 Desenvolvedor " + senioridade + " criado");
        System.out.println("🔧 Linguagens: " + String.join(", ", linguagensProgramacao));
    }
    
    // ===== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS (POLIMORFISMO) =====
    
    /**
     * Cálculo específico de salário para desenvolvedor
     * Demonstra polimorfismo - mesmo método, implementação diferente
     */
    @Override
    public double calcularSalario() {
        double salario = salarioBase;
        
        // Bônus por senioridade
        salario += calcularBonusSenioridade();
        
        // Bônus por linguagens de programação
        salario += calcularBonusLinguagens();
        
        // Bônus por projetos concluídos
        salario += projetosConcluidos * 200.0;
        
        // Bônus por liderança técnica
        if (liderTecnico) {
            salario += 1500.0;
        }
        
        // Bônus por experiência na empresa
        salario += salario * calcularBonusExperiencia();
        
        // Bônus de produtividade
        salario += bonusProjetividade;
        
        return salario;
    }
    
    /**
     * Implementação específica do trabalho de um desenvolvedor
     */
    @Override
    public void trabalhar() {
        if (!ativo) {
            System.out.println("❌ Desenvolvedor inativo não pode trabalhar!");
            return;
        }
        
        System.out.println("💻 " + nome + " está codificando...");
        
        // Simula escolha aleatória de atividade
        String[] atividades = {
            "Desenvolvendo nova funcionalidade",
            "Corrigindo bugs",
            "Revisando código de colegas",
            "Escrevendo testes unitários",
            "Atualizando documentação",
            "Participando de code review",
            "Refatorando código legado"
        };
        
        String atividadeAtual = atividades[(int)(Math.random() * atividades.length)];
        System.out.println("🔧 " + atividadeAtual + "...");
        
        // Simula progresso no projeto
        if (Math.random() > 0.7) { // 30% de chance
            concluirProjeto();
        }
    }
    
    /**
     * Participação específica em reuniões técnicas
     */
    @Override
    public void participarReuniao(String tipoReuniao) {
        if (!ativo) {
            System.out.println("❌ Desenvolvedor inativo não pode participar de reuniões!");
            return;
        }
        
        System.out.println("🤝 " + nome + " participando de: " + tipoReuniao);
        
        switch (tipoReuniao.toLowerCase()) {
            case "daily":
            case "scrum":
                System.out.println("📋 Reportando status das tasks e impedimentos");
                System.out.println("⏰ Estimando tempo para conclusão");
                break;
                
            case "planning":
                System.out.println("📊 Analisando complexidade técnica das histórias");
                System.out.println("🎯 Definindo critérios de aceitação");
                break;
                
            case "review":
                System.out.println("🔍 Demonstrando funcionalidades desenvolvidas");
                System.out.println("🐛 Reportando bugs encontrados");
                break;
                
            case "retrospective":
                System.out.println("💭 Compartilhando pontos de melhoria");
                System.out.println("🚀 Sugerindo otimizações no processo");
                break;
                
            case "arquitetura":
                if (liderTecnico || senioridade.equals("Senior")) {
                    System.out.println("🏗️ Definindo arquitetura da solução");
                    System.out.println("📐 Escolhendo tecnologias e padrões");
                } else {
                    System.out.println("👂 Ouvindo decisões arquiteturais");
                    System.out.println("❓ Fazendo perguntas técnicas");
                }
                break;
                
            default:
                System.out.println("👥 Participando ativamente da discussão");
                System.out.println("💡 Contribuindo com insights técnicos");
        }
    }
    
    // ===== MÉTODOS ESPECÍFICOS DO DESENVOLVEDOR =====
    
    /**
     * Codificar uma funcionalidade específica
     */
    public void codificar(String funcionalidade, String linguagem) {
        if (!ativo) {
            System.out.println("❌ Desenvolvedor inativo não pode codificar!");
            return;
        }
        
        // Verifica se conhece a linguagem
        boolean conheceLinguagem = false;
        for (String lang : linguagensProgramacao) {
            if (lang.equalsIgnoreCase(linguagem)) {
                conheceLinguagem = true;
                break;
            }
        }
        
        if (!conheceLinguagem) {
            System.out.println("❌ " + nome + " não conhece " + linguagem);
            System.out.println("📚 Recomenda-se treinamento ou ajuda de colega");
            return;
        }
        
        System.out.println("⌨️ " + nome + " está codificando " + funcionalidade + " em " + linguagem);
        System.out.println("💡 Aplicando boas práticas de desenvolvimento");
        
        // Simula tempo de desenvolvimento
        if (senioridade.equals("Senior")) {
            System.out.println("🚀 Desenvolvimento rápido (experiência sênior)");
        } else if (senioridade.equals("Pleno")) {
            System.out.println("⚖️ Desenvolvimento moderado (experiência plena)");
        } else {
            System.out.println("🐌 Desenvolvimento mais lento (aprendendo...)");
        }
    }
    
    /**
     * Revisar código de outro desenvolvedor
     */
    public void revisarCodigo(Desenvolvedor colega, String arquivo) {
        if (!ativo) {
            System.out.println("❌ Desenvolvedor inativo não pode revisar código!");
            return;
        }
        
        System.out.println("🔍 " + nome + " revisando código de " + colega.getNome());
        System.out.println("📄 Arquivo: " + arquivo);
        
        // Feedback baseado na senioridade
        if (senioridade.equals("Senior")) {
            System.out.println("🎯 Sugerindo melhorias arquiteturais");
            System.out.println("🔧 Identificando possíveis otimizações");
        } else if (senioridade.equals("Pleno")) {
            System.out.println("✅ Verificando padrões de código");
            System.out.println("🐛 Procurando por bugs potenciais");
        } else {
            System.out.println("📖 Aprendendo com o código do colega");
            System.out.println("❓ Fazendo perguntas para entender melhor");
        }
        
        System.out.println("✅ Code review concluído com sucesso!");
    }
    
    /**
     * Fazer debugging de um problema
     */
    public void debugging(String problema) {
        if (!ativo) {
            System.out.println("❌ Desenvolvedor inativo não pode fazer debugging!");
            return;
        }
        
        System.out.println("🐛 " + nome + " investigando: " + problema);
        System.out.println("🔍 Analisando logs e stack traces...");
        
        // Eficiência baseada na senioridade
        switch (senioridade) {
            case "Senior":
                System.out.println("🎯 Problema identificado rapidamente!");
                System.out.println("⚡ Correção aplicada com experiência");
                break;
            case "Pleno":
                System.out.println("🤔 Analisando possíveis causas...");
                System.out.println("✅ Problema resolvido após investigação");
                break;
            case "Junior":
                System.out.println("📚 Pesquisando soluções online...");
                System.out.println("🤝 Pedindo ajuda a desenvolvedores sêniores");
                System.out.println("💪 Problema resolvido com aprendizado!");
                break;
        }
    }
    
    /**
     * Aprender nova linguagem de programação
     */
    public void aprenderLinguagem(String novaLinguagem) {
        System.out.println("📚 " + nome + " está aprendendo " + novaLinguagem);
        
        // Verifica se já conhece
        for (String lang : linguagensProgramacao) {
            if (lang.equalsIgnoreCase(novaLinguagem)) {
                System.out.println("ℹ️ " + nome + " já conhece " + novaLinguagem);
                return;
            }
        }
        
        // Adiciona nova linguagem
        String[] novasLinguagens = new String[linguagensProgramacao.length + 1];
        System.arraycopy(linguagensProgramacao, 0, novasLinguagens, 0, linguagensProgramacao.length);
        novasLinguagens[linguagensProgramacao.length] = novaLinguagem;
        linguagensProgramacao = novasLinguagens;
        
        System.out.println("🎉 " + novaLinguagem + " adicionada às competências!");
        System.out.printf("💰 Salário atualizado: R$ %.2f\n", calcularSalario());
    }
    
    /**
     * Concluir um projeto
     */
    public void concluirProjeto() {
        projetosConcluidos++;
        System.out.println("🎯 " + nome + " concluiu um projeto!");
        System.out.println("📈 Total de projetos: " + projetosConcluidos);
        
        // Bônus de produtividade
        bonusProjetividade += 300.0;
        System.out.printf("💰 Bônus de produtividade: R$ %.2f\n", bonusProjetividade);
    }
    
    /**
     * Tornar-se líder técnico
     */
    public void tornarLiderTecnico() {
        if (!senioridade.equals("Senior")) {
            System.out.println("❌ Apenas desenvolvedores sêniores podem ser líderes técnicos!");
            return;
        }
        
        if (projetosConcluidos < 5) {
            System.out.println("❌ Necessário pelo menos 5 projetos concluídos para liderança!");
            return;
        }
        
        liderTecnico = true;
        System.out.println("🏆 " + nome + " promovido a Líder Técnico!");
        System.out.printf("💰 Novo salário: R$ %.2f\n", calcularSalario());
    }
    
    // ===== MÉTODOS AUXILIARES =====
    
    private void ajustarSalarioPorSenioridade() {
        switch (senioridade) {
            case "Junior":
                // Salário base já está correto
                break;
            case "Pleno":
                salarioBase *= 1.5; // 50% a mais que junior
                break;
            case "Senior":
                salarioBase *= 2.5; // 150% a mais que junior
                break;
        }
    }
    
    private double calcularBonusSenioridade() {
        return switch (senioridade) {
            case "Junior" -> 0.0;
            case "Pleno" -> 1000.0;
            case "Senior" -> 2500.0;
            default -> 0.0;
        };
    }
    
    private double calcularBonusLinguagens() {
        // R$ 300 por linguagem conhecida
        return linguagensProgramacao.length * 300.0;
    }
    
    // ===== GETTERS ESPECÍFICOS =====
    
    public String[] getLinguagensProgramacao() { return linguagensProgramacao.clone(); }
    public String getSenioridade() { return senioridade; }
    public int getProjetosConcluidos() { return projetosConcluidos; }
    public boolean isLiderTecnico() { return liderTecnico; }
    public double getBonusProjetividade() { return bonusProjetividade; }
    
    /**
     * Sobrescreve exibirInformacoes para incluir dados específicos
     */
    @Override
    public void exibirInformacoes() {
        // Chama método da classe pai
        super.exibirInformacoes();
        
        // Adiciona informações específicas do desenvolvedor
        System.out.println("=== Informações Específicas do Desenvolvedor ===");
        System.out.println("Senioridade: " + senioridade);
        System.out.println("Linguagens: " + String.join(", ", linguagensProgramacao));
        System.out.println("Projetos concluídos: " + projetosConcluidos);
        System.out.println("Líder técnico: " + (liderTecnico ? "Sim" : "Não"));
        System.out.printf("Bônus produtividade: R$ %.2f\n", bonusProjetividade);
        System.out.println("===============================================\n");
    }
    
    /**
     * Sobrescreve toString para formato específico
     */
    @Override
    public String toString() {
        return String.format("Desenvolvedor{nome='%s', senioridade='%s', linguagens=%d, projetos=%d, salario=%.2f}", 
                           nome, senioridade, linguagensProgramacao.length, 
                           projetosConcluidos, calcularSalario());
    }
}