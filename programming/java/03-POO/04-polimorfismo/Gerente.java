/**
 * Exemplo Clássico: Gerente (classe filha de Funcionario)
 * 
 * Demonstra polimorfismo específico para gerentes:
 * - Cálculo de salário baseado em equipe gerenciada e metas
 * - Comportamentos específicos (gerenciar equipe, definir metas, aprovar decisões)
 * - Liderança em reuniões estratégicas
 * 
 * @author Curso POO Java - Exemplo Clássico
 */
public class Gerente extends Funcionario {
    
    // ===== ATRIBUTOS ESPECÍFICOS DO GERENTE =====
    private int tamanhoEquipe;              // Número de subordinados
    private double metaMensal;              // Meta mensal em vendas/produtividade
    private double resultadoAtual;          // Resultado atual do mês
    private String[] areasResponsabilidade; // Áreas que gerencia
    private boolean autoridade;             // Autoridade para aprovar gastos
    private double limiteBudget;            // Limite de orçamento para aprovação
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor específico para gerente
     */
    public Gerente(String nome, String cpf, String matricula, double salarioBase,
                   String departamento, int tamanhoEquipe, String[] areasResponsabilidade) {
        
        // Chama construtor da classe pai
        super(nome, cpf, matricula, salarioBase, departamento);
        
        this.tamanhoEquipe = tamanhoEquipe;
        this.areasResponsabilidade = areasResponsabilidade;
        this.metaMensal = calcularMetaBasica();
        this.resultadoAtual = 0.0;
        this.autoridade = true;
        this.limiteBudget = calcularLimiteBudget();
        
        System.out.println("👔 Gerente criado com " + tamanhoEquipe + " subordinados");
        System.out.println("🎯 Áreas: " + String.join(", ", areasResponsabilidade));
    }
    
    // ===== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS (POLIMORFISMO) =====
    
    /**
     * Cálculo específico de salário para gerente
     * Demonstra polimorfismo - mesmo método, implementação totalmente diferente
     */
    @Override
    public double calcularSalario() {
        double salario = salarioBase;
        
        // Bônus por tamanho da equipe (R$ 500 por subordinado)
        salario += tamanhoEquipe * 500.0;
        
        // Bônus por áreas de responsabilidade
        salario += areasResponsabilidade.length * 800.0;
        
        // Bônus por atingimento de meta
        double percentualMeta = (metaMensal > 0) ? (resultadoAtual / metaMensal) : 0;
        if (percentualMeta >= 1.0) {
            salario += salario * 0.15; // 15% de bônus por atingir meta
        }
        if (percentualMeta >= 1.2) {
            salario += salario * 0.10; // Mais 10% por superar em 20%
        }
        
        // Bônus por experiência na empresa (maior que funcionários comuns)
        salario += salario * (calcularBonusExperiencia() * 1.5);
        
        // Bônus de liderança fixa
        salario += 2000.0;
        
        return salario;
    }
    
    /**
     * Implementação específica do trabalho de um gerente
     */
    @Override
    public void trabalhar() {
        if (!ativo) {
            System.out.println("❌ Gerente inativo não pode trabalhar!");
            return;
        }
        
        System.out.println("👔 " + nome + " está gerenciando equipe e departamento...");
        
        // Simula atividades gerenciais
        String[] atividades = {
            "Revisando relatórios de desempenho",
            "Conduzindo one-on-ones com a equipe",
            "Analisando métricas do departamento",
            "Planejando estratégias de melhoria",
            "Aprovando solicitações e orçamentos",
            "Definindo metas para próximo período",
            "Resolvendo conflitos da equipe"
        };
        
        String atividadeAtual = atividades[(int)(Math.random() * atividades.length)];
        System.out.println("📋 " + atividadeAtual + "...");
        
        // Simula progresso nas metas
        if (Math.random() > 0.6) { // 40% de chance
            double progresso = Math.random() * 1000 + 500;
            atualizarResultado(progresso);
        }
    }
    
    /**
     * Participação específica em reuniões estratégicas
     */
    @Override
    public void participarReuniao(String tipoReuniao) {
        if (!ativo) {
            System.out.println("❌ Gerente inativo não pode participar de reuniões!");
            return;
        }
        
        System.out.println("🤝 " + nome + " liderando reunião: " + tipoReuniao);
        
        switch (tipoReuniao.toLowerCase()) {
            case "diretoria":
                System.out.println("📊 Apresentando resultados do departamento");
                System.out.println("💼 Discutindo estratégias corporativas");
                System.out.println("💰 Justificando investimentos e orçamentos");
                break;
                
            case "equipe":
                System.out.println("🎯 Alinhando metas e objetivos");
                System.out.println("📈 Compartilhando feedback de desempenho");
                System.out.println("🚀 Motivando equipe para resultados");
                break;
                
            case "projeto":
                System.out.println("📋 Acompanhando progresso dos projetos");
                System.out.println("⚠️ Identificando riscos e bloqueios");
                System.out.println("🔄 Realocando recursos conforme necessário");
                break;
                
            case "budget":
                System.out.println("💸 Analisando gastos do departamento");
                System.out.println("📊 Propondo otimizações de custo");
                System.out.println("🎯 Definindo prioridades de investimento");
                break;
                
            case "estrategia":
                System.out.println("🗺️ Definindo direcionamento estratégico");
                System.out.println("🔍 Analisando concorrência e mercado");
                System.out.println("💡 Propondo inovações e melhorias");
                break;
                
            default:
                System.out.println("👥 Liderando discussão de forma assertiva");
                System.out.println("📝 Tomando decisões importantes");
                System.out.println("📞 Definindo próximos passos e responsáveis");
        }
    }
    
    // ===== MÉTODOS ESPECÍFICOS DO GERENTE =====
    
    /**
     * Gerenciar equipe - delegar tarefas
     */
    public void gerenciarEquipe(String tarefa) {
        if (!ativo) {
            System.out.println("❌ Gerente inativo não pode gerenciar equipe!");
            return;
        }
        
        System.out.println("👥 " + nome + " delegando tarefa para equipe:");
        System.out.println("📋 Tarefa: " + tarefa);
        System.out.println("👤 Equipe: " + tamanhoEquipe + " funcionários");
        
        // Estratégia baseada no tamanho da equipe
        if (tamanhoEquipe <= 5) {
            System.out.println("🎯 Gestão direta - acompanhamento individual");
        } else if (tamanhoEquipe <= 15) {
            System.out.println("📊 Gestão intermediária - acompanhamento por subgrupos");
        } else {
            System.out.println("🏢 Gestão estratégica - delegação para supervisores");
        }
        
        System.out.println("✅ Tarefa delegada com clareza e prazo definido");
    }
    
    /**
     * Avaliar funcionário da equipe
     */
    public void avaliarFuncionario(Funcionario funcionario) {
        if (!ativo) {
            System.out.println("❌ Gerente inativo não pode avaliar funcionários!");
            return;
        }
        
        System.out.println("📝 " + nome + " avaliando " + funcionario.getNome());
        
        // Análise do desempenho
        String avaliacao = funcionario.avaliar();
        double salario = funcionario.calcularSalario();
        
        System.out.println("📊 Avaliação: " + avaliacao);
        System.out.printf("💰 Salário atual: R$ %.2f\n", salario);
        
        // Feedback baseado na avaliação
        switch (avaliacao) {
            case "Excelente":
                System.out.println("🏆 Desempenho excepcional - considerar promoção");
                System.out.println("💎 Funcionário de alto valor para a empresa");
                break;
            case "Bom":
                System.out.println("✅ Desempenho satisfatório - manter motivação");
                System.out.println("📈 Identificar oportunidades de crescimento");
                break;
            case "Regular":
                System.out.println("⚠️ Desempenho abaixo do esperado");
                System.out.println("🎯 Definir plano de desenvolvimento individual");
                break;
            default:
                System.out.println("❌ Desempenho insatisfatório - ação corretiva necessária");
                System.out.println("📚 Programa de capacitação obrigatório");
        }
    }
    
    /**
     * Aprovar gasto/investimento
     */
    public boolean aprovarGasto(double valor, String descricao) {
        if (!ativo) {
            System.out.println("❌ Gerente inativo não pode aprovar gastos!");
            return false;
        }
        
        if (!autoridade) {
            System.out.println("❌ " + nome + " não tem autoridade para aprovar gastos!");
            return false;
        }
        
        System.out.println("💰 " + nome + " analisando solicitação de gasto:");
        System.out.println("💵 Valor: R$ " + String.format("%.2f", valor));
        System.out.println("📝 Descrição: " + descricao);
        System.out.printf("🏦 Limite disponível: R$ %.2f\n", limiteBudget);
        
        if (valor <= limiteBudget) {
            System.out.println("✅ APROVADO - Gasto dentro do limite de autoridade");
            limiteBudget -= valor; // Consome o orçamento
            return true;
        } else {
            System.out.println("❌ NEGADO - Valor excede limite de autoridade");
            System.out.println("⬆️ Necessário aprovação da diretoria");
            return false;
        }
    }
    
    /**
     * Definir meta para o período
     */
    public void definirMeta(double novaMeta) {
        double metaAnterior = this.metaMensal;
        this.metaMensal = novaMeta;
        
        System.out.println("🎯 " + nome + " definiu nova meta:");
        System.out.printf("📊 Meta anterior: R$ %.2f\n", metaAnterior);
        System.out.printf("📈 Nova meta: R$ %.2f\n", novaMeta);
        
        double variacao = ((novaMeta - metaAnterior) / metaAnterior) * 100;
        if (variacao > 0) {
            System.out.printf("📈 Aumento de %.1f%%\n", variacao);
        } else if (variacao < 0) {
            System.out.printf("📉 Redução de %.1f%%\n", Math.abs(variacao));
        } else {
            System.out.println("➡️ Meta mantida");
        }
    }
    
    /**
     * Atualizar resultado atual
     */
    public void atualizarResultado(double novoResultado) {
        this.resultadoAtual += novoResultado;
        
        double percentualMeta = (metaMensal > 0) ? (resultadoAtual / metaMensal) * 100 : 0;
        
        System.out.printf("📊 Resultado atualizado: R$ %.2f\n", resultadoAtual);
        System.out.printf("🎯 Progresso da meta: %.1f%%\n", percentualMeta);
        
        if (percentualMeta >= 100) {
            System.out.println("🎉 META ATINGIDA! Parabéns à equipe!");
        } else if (percentualMeta >= 80) {
            System.out.println("🚀 Muito próximo da meta - acelerar esforços!");
        } else if (percentualMeta >= 50) {
            System.out.println("⚠️ Resultado abaixo do esperado - revisar estratégia");
        } else {
            System.out.println("❌ Resultado crítico - ação imediata necessária");
        }
    }
    
    /**
     * Expandir equipe (contratar)
     */
    public void expandirEquipe(int novosFuncionarios) {
        if (!autoridade) {
            System.out.println("❌ Sem autoridade para expandir equipe!");
            return;
        }
        
        // Custo estimado de contratação
        double custoContratacao = novosFuncionarios * 8000.0; // R$ 8k por contratação
        
        if (!aprovarGasto(custoContratacao, "Contratação de " + novosFuncionarios + " funcionários")) {
            return;
        }
        
        int equipeAnterior = this.tamanhoEquipe;
        this.tamanhoEquipe += novosFuncionarios;
        
        System.out.println("👥 Equipe expandida:");
        System.out.println("   📊 Antes: " + equipeAnterior + " funcionários");
        System.out.println("   📈 Depois: " + tamanhoEquipe + " funcionários");
        System.out.printf("💰 Novo salário: R$ %.2f\n", calcularSalario());
        
        // Ajusta meta proporcionalmente
        definirMeta(metaMensal * 1.2); // 20% a mais por expansão
    }
    
    // ===== MÉTODOS AUXILIARES =====
    
    private double calcularMetaBasica() {
        // Meta baseada no tamanho da equipe e departamento
        double metaBase = tamanhoEquipe * 5000.0; // R$ 5k por funcionário
        
        // Ajuste por departamento
        switch (departamento.toLowerCase()) {
            case "vendas":
                metaBase *= 2.0; // Vendas tem metas maiores
                break;
            case "marketing":
                metaBase *= 1.5;
                break;
            case "tecnologia":
                metaBase *= 1.3;
                break;
            case "operações":
                metaBase *= 1.1;
                break;
        }
        
        return metaBase;
    }
    
    private double calcularLimiteBudget() {
        // Limite baseado no nível hierárquico e tamanho da equipe
        double limite = 50000.0; // Limite base
        limite += tamanhoEquipe * 2000.0; // R$ 2k por subordinado
        limite += anosEmpresa * 5000.0; // R$ 5k por ano de experiência
        
        return limite;
    }
    
    // ===== GETTERS ESPECÍFICOS =====
    
    public int getTamanhoEquipe() { return tamanhoEquipe; }
    public double getMetaMensal() { return metaMensal; }
    public double getResultadoAtual() { return resultadoAtual; }
    public String[] getAreasResponsabilidade() { return areasResponsabilidade.clone(); }
    public boolean temAutoridade() { return autoridade; }
    public double getLimiteBudget() { return limiteBudget; }
    
    /**
     * Sobrescreve exibirInformacoes para incluir dados específicos
     */
    @Override
    public void exibirInformacoes() {
        // Chama método da classe pai
        super.exibirInformacoes();
        
        // Adiciona informações específicas do gerente
        System.out.println("=== Informações Específicas do Gerente ===");
        System.out.println("Tamanho da equipe: " + tamanhoEquipe + " funcionários");
        System.out.printf("Meta mensal: R$ %.2f\n", metaMensal);
        System.out.printf("Resultado atual: R$ %.2f\n", resultadoAtual);
        System.out.printf("Progresso: %.1f%%\n", (metaMensal > 0) ? (resultadoAtual / metaMensal) * 100 : 0);
        System.out.println("Áreas de responsabilidade: " + String.join(", ", areasResponsabilidade));
        System.out.printf("Limite de orçamento: R$ %.2f\n", limiteBudget);
        System.out.println("==========================================\n");
    }
    
    /**
     * Sobrescreve toString para formato específico
     */
    @Override
    public String toString() {
        return String.format("Gerente{nome='%s', equipe=%d, areas=%d, meta=%.0f%%, salario=%.2f}", 
                           nome, tamanhoEquipe, areasResponsabilidade.length,
                           (metaMensal > 0) ? (resultadoAtual / metaMensal) * 100 : 0,
                           calcularSalario());
    }
}