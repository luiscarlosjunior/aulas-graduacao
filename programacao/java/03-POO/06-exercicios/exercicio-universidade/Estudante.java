/**
 * Estudante - Exemplo de Herança e Polimorfismo no Sistema Universitário
 * 
 * Demonstra:
 * - Herança: extends Pessoa
 * - Encapsulamento: atributos private com getters/setters
 * - Polimorfismo: implementações específicas dos métodos abstratos
 * - Sobrescrita: métodos com comportamentos específicos de estudante
 */
public class Estudante extends Pessoa {
    
    // ===== ATRIBUTOS ESPECÍFICOS DO ESTUDANTE =====
    private String matricula;           // Matrícula única do estudante
    private String curso;               // Curso que está cursando
    private int semestre;               // Semestre atual (1-10)
    private double cra;                 // Coeficiente de Rendimento Acadêmico (0-10)
    private int creditosCompletos;      // Créditos já completados
    private int creditosNecessarios;    // Total de créditos necessários para formar
    private boolean bolsista;           // Se possui bolsa de estudos
    private String tipoBolsa;           // Tipo da bolsa (integral, parcial, etc.)
    private int anoIngresso;            // Ano de ingresso na universidade
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor para estudante
     */
    public Estudante(String nome, String cpf, String email, int idade,
                    String matricula, String curso, int semestre, int anoIngresso) {
        
        // Chama construtor da classe pai
        super(nome, cpf, email, idade);
        
        // Inicializa atributos específicos
        setMatricula(matricula);
        setCurso(curso);
        setSemestre(semestre);
        this.anoIngresso = anoIngresso;
        this.cra = 0.0;
        this.creditosCompletos = 0;
        this.creditosNecessarios = calcularCreditosNecessarios();
        this.bolsista = false;
        this.tipoBolsa = null;
        
        System.out.println("🎓 Estudante matriculado no curso de " + curso);
        System.out.println("📋 Matrícula: " + matricula);
    }
    
    // ===== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS (POLIMORFISMO) =====
    
    /**
     * Função específica do estudante na universidade
     */
    @Override
    public void exercerFuncao() {
        if (!ativo) {
            System.out.println("❌ Estudante inativo não pode exercer função!");
            return;
        }
        
        System.out.println("📚 " + nome + " está estudando e frequentando aulas");
        System.out.println("📖 Curso: " + curso + " - " + semestre + "º semestre");
        System.out.printf("📊 CRA atual: %.2f\n", cra);
        
        // Simula atividades do estudante
        String[] atividades = {
            "Assistindo aula de " + gerarDisciplina(),
            "Fazendo trabalho em grupo",
            "Estudando para prova",
            "Participando de projeto de extensão",
            "Usando laboratório de informática"
        };
        
        String atividade = atividades[(int)(Math.random() * atividades.length)];
        System.out.println("🎯 " + atividade);
    }
    
    /**
     * Participação específica em atividades acadêmicas
     */
    @Override
    public void participarAtividade(String atividade) {
        if (!ativo) {
            System.out.println("❌ Estudante inativo não pode participar de atividades!");
            return;
        }
        
        System.out.println("🎓 " + nome + " participando de: " + atividade);
        
        switch (atividade.toLowerCase()) {
            case "aula":
                System.out.println("👨‍🏫 Prestando atenção na explicação do professor");
                System.out.println("📝 Fazendo anotações importantes");
                break;
                
            case "prova":
                System.out.println("✍️ Resolvendo questões da avaliação");
                double notaSimulada = Math.random() * 10;
                System.out.printf("📊 Nota estimada: %.1f\n", notaSimulada);
                if (notaSimulada >= 7.0) {
                    atualizarCRA(0.1); // Melhora CRA
                }
                break;
                
            case "seminario":
                System.out.println("🎤 Apresentando trabalho acadêmico");
                System.out.println("📊 Demonstrando conhecimento adquirido");
                break;
                
            case "monitoria":
                if (cra >= 8.0) {
                    System.out.println("👨‍🏫 Ajudando colegas como monitor");
                    System.out.println("💡 Compartilhando conhecimento");
                } else {
                    System.out.println("📚 Recebendo ajuda da monitoria");
                    System.out.println("🤝 Tirando dúvidas com monitor");
                }
                break;
                
            case "iniciacao cientifica":
                if (semestre >= 3) {
                    System.out.println("🔬 Desenvolvendo pesquisa científica");
                    System.out.println("📄 Contribuindo para produção acadêmica");
                } else {
                    System.out.println("⚠️ Iniciação científica disponível a partir do 3º semestre");
                }
                break;
                
            default:
                System.out.println("👥 Participando ativamente da atividade");
                System.out.println("📈 Enriquecendo formação acadêmica");
        }
    }
    
    /**
     * Responsabilidades específicas do estudante
     */
    @Override
    public String[] getResponsabilidades() {
        return new String[]{
            "Frequentar aulas regularmente",
            "Entregar trabalhos e atividades no prazo",
            "Estudar para provas e avaliações",
            "Manter CRA satisfatório (>= 5.0)",
            "Cumprir carga horária mínima",
            "Respeitar código de conduta estudantil",
            "Participar de atividades complementares"
        };
    }
    
    /**
     * Calcular tempo na universidade
     */
    @Override
    public int calcularTempoUniversidade() {
        int anoAtual = java.time.Year.now().getValue();
        return anoAtual - anoIngresso;
    }
    
    // ===== MÉTODOS ESPECÍFICOS DO ESTUDANTE =====
    
    /**
     * Matricular em disciplina
     */
    public void matricularDisciplina(String disciplina, int creditos) {
        if (!ativo) {
            System.out.println("❌ Estudante inativo não pode se matricular!");
            return;
        }
        
        if (cra < 5.0 && semestre > 2) {
            System.out.println("❌ CRA insuficiente para matrícula!");
            System.out.printf("📊 CRA atual: %.2f (mínimo: 5.0)\n", cra);
            return;
        }
        
        System.out.println("📝 " + nome + " se matriculou em: " + disciplina);
        System.out.println("🎯 Créditos da disciplina: " + creditos);
        System.out.println("📅 Início das aulas em breve");
    }
    
    /**
     * Concluir disciplina (ganhar créditos)
     */
    public void concluirDisciplina(String disciplina, double nota, int creditos) {
        System.out.println("✅ " + nome + " concluiu: " + disciplina);
        System.out.printf("📊 Nota final: %.1f\n", nota);
        
        if (nota >= 5.0) {
            creditosCompletos += creditos;
            System.out.println("🎉 Disciplina aprovada!");
            System.out.println("📈 Créditos obtidos: " + creditos);
            System.out.println("📊 Total de créditos: " + creditosCompletos + "/" + creditosNecessarios);
            
            // Atualiza CRA baseado na nota
            double impactoCRA = (nota - 5.0) * 0.1;
            atualizarCRA(impactoCRA);
            
            // Verifica se pode avançar de semestre
            verificarProgressao();
            
        } else {
            System.out.println("❌ Disciplina reprovada!");
            System.out.println("🔄 Necessário cursar novamente");
            atualizarCRA(-0.2); // Penaliza CRA
        }
    }
    
    /**
     * Solicitar bolsa de estudos
     */
    public boolean solicitarBolsa(String tipoBolsa) {
        if (!ativo) {
            System.out.println("❌ Estudante inativo não pode solicitar bolsa!");
            return false;
        }
        
        if (bolsista) {
            System.out.println("ℹ️ " + nome + " já possui bolsa: " + this.tipoBolsa);
            return false;
        }
        
        // Critérios para bolsa
        boolean atendeCriterios = false;
        String motivo = "";
        
        switch (tipoBolsa.toLowerCase()) {
            case "merito":
                atendeCriterios = cra >= 8.5;
                motivo = atendeCriterios ? "CRA excelente" : "CRA insuficiente (min: 8.5)";
                break;
                
            case "social":
                // Simulação: 50% de chance baseado em critérios socioeconômicos
                atendeCriterios = Math.random() > 0.5;
                motivo = atendeCriterios ? "Critérios socioeconômicos atendidos" : "Não atende critérios socioeconômicos";
                break;
                
            case "pesquisa":
                atendeCriterios = semestre >= 3 && cra >= 7.0;
                motivo = atendeCriterios ? "Apto para pesquisa" : "Requer 3º sem. e CRA >= 7.0";
                break;
                
            case "esporte":
                atendeCriterios = Math.random() > 0.7; // 30% de chance
                motivo = atendeCriterios ? "Habilidades esportivas comprovadas" : "Não atende critérios esportivos";
                break;
        }
        
        System.out.println("🎓 Solicitação de bolsa " + tipoBolsa + " para " + nome);
        System.out.println("📋 Critério: " + motivo);
        
        if (atendeCriterios) {
            this.bolsista = true;
            this.tipoBolsa = tipoBolsa;
            System.out.println("✅ Bolsa " + tipoBolsa + " APROVADA!");
            System.out.println("💰 Benefícios ativados no sistema");
            return true;
        } else {
            System.out.println("❌ Solicitação de bolsa NEGADA");
            System.out.println("📚 Sugestão: Melhorar desempenho acadêmico");
            return false;
        }
    }
    
    /**
     * Usar biblioteca com privilégios de estudante
     */
    @Override
    public void usarBiblioteca() {
        super.usarBiblioteca(); // Chama método da classe pai
        
        if (ativo) {
            System.out.println("📖 Acesso a: livros didáticos, periódicos científicos");
            System.out.println("💻 Pode usar computadores por 4 horas/dia");
            System.out.println("📚 Limite de empréstimo: 5 livros por 15 dias");
            
            if (bolsista) {
                System.out.println("🎓 Privilégio de bolsista: empréstimo estendido");
            }
        }
    }
    
    /**
     * Preço especial para estudante no restaurante
     */
    @Override
    protected double calcularPrecoRefeicao() {
        double preco = 3.50; // Preço estudantil
        
        if (bolsista) {
            preco = 1.50; // Desconto para bolsista
        }
        
        return preco;
    }
    
    /**
     * Formar-se (concluir curso)
     */
    public boolean formar() {
        if (!ativo) {
            System.out.println("❌ Estudante inativo não pode se formar!");
            return false;
        }
        
        if (creditosCompletos < creditosNecessarios) {
            System.out.println("❌ Créditos insuficientes para formatura!");
            System.out.println("📊 Necessário: " + creditosNecessarios + ", Completo: " + creditosCompletos);
            return false;
        }
        
        if (cra < 5.0) {
            System.out.println("❌ CRA insuficiente para formatura (mínimo: 5.0)!");
            System.out.printf("📊 CRA atual: %.2f\n", cra);
            return false;
        }
        
        System.out.println("🎓 PARABÉNS! " + nome + " está apto para formatura!");
        System.out.println("🏆 Curso: " + curso);
        System.out.printf("📊 CRA final: %.2f\n", cra);
        System.out.println("📜 Diploma será emitido");
        
        // Desativa do sistema como estudante
        desativar("Formatura concluída");
        
        return true;
    }
    
    // ===== MÉTODOS AUXILIARES =====
    
    private void atualizarCRA(double impacto) {
        double novcoCRA = Math.max(0.0, Math.min(10.0, cra + impacto));
        double anterior = this.cra;
        this.cra = novcoCRA;
        
        if (impacto > 0) {
            System.out.printf("📈 CRA melhorou: %.2f → %.2f\n", anterior, cra);
        } else if (impacto < 0) {
            System.out.printf("📉 CRA reduziu: %.2f → %.2f\n", anterior, cra);
        }
    }
    
    private void verificarProgressao() {
        int creditosEsperados = semestre * (creditosNecessarios / 8); // 8 semestres médios
        
        if (creditosCompletos >= creditosEsperados * 1.2) {
            System.out.println("🚀 Estudante adiantado no curso!");
        } else if (creditosCompletos < creditosEsperados * 0.8) {
            System.out.println("⚠️ Estudante atrasado - considerar orientação acadêmica");
        }
    }
    
    private String gerarDisciplina() {
        String[] disciplinas = {
            "Cálculo I", "Física Geral", "Programação", "Estatística",
            "Algoritmos", "Banco de Dados", "Redes", "Sistemas Operacionais"
        };
        return disciplinas[(int)(Math.random() * disciplinas.length)];
    }
    
    private int calcularCreditosNecessarios() {
        // Simulação baseada no curso
        return switch (curso.toLowerCase()) {
            case "engenharia", "medicina" -> 240;
            case "direito", "administração" -> 200;
            case "ciência da computação", "sistemas de informação" -> 180;
            default -> 160;
        };
    }
    
    // ===== GETTERS E SETTERS ESPECÍFICOS =====
    
    public String getMatricula() { return matricula; }
    
    public void setMatricula(String matricula) {
        if (matricula == null || !matricula.matches("\\d{8}")) {
            throw new IllegalArgumentException("Matrícula deve ter 8 dígitos!");
        }
        this.matricula = matricula;
    }
    
    public String getCurso() { return curso; }
    
    public void setCurso(String curso) {
        if (curso == null || curso.trim().isEmpty()) {
            throw new IllegalArgumentException("Curso não pode ser vazio!");
        }
        this.curso = curso.trim();
    }
    
    public int getSemestre() { return semestre; }
    
    public void setSemestre(int semestre) {
        if (semestre < 1 || semestre > 15) {
            throw new IllegalArgumentException("Semestre deve estar entre 1 e 15!");
        }
        this.semestre = semestre;
    }
    
    public double getCra() { return cra; }
    public int getCreditosCompletos() { return creditosCompletos; }
    public int getCreditosNecessarios() { return creditosNecessarios; }
    public boolean isBolsista() { return bolsista; }
    public String getTipoBolsa() { return tipoBolsa; }
    public int getAnoIngresso() { return anoIngresso; }
    
    /**
     * Sobrescreve exibirInformacoes para incluir dados específicos
     */
    @Override
    public void exibirInformacoes() {
        // Chama método da classe pai
        super.exibirInformacoes();
        
        // Adiciona informações específicas do estudante
        System.out.println("=== Informações Acadêmicas ===");
        System.out.println("Matrícula: " + matricula);
        System.out.println("Curso: " + curso);
        System.out.println("Semestre: " + semestre + "º");
        System.out.printf("CRA: %.2f\n", cra);
        System.out.println("Créditos: " + creditosCompletos + "/" + creditosNecessarios);
        System.out.println("Ano de ingresso: " + anoIngresso);
        System.out.println("Tempo na universidade: " + calcularTempoUniversidade() + " anos");
        System.out.println("Bolsista: " + (bolsista ? "Sim (" + tipoBolsa + ")" : "Não"));
        System.out.printf("Progresso: %.1f%%\n", (double)creditosCompletos / creditosNecessarios * 100);
        System.out.println("============================\n");
    }
    
    /**
     * Sobrescreve toString para formato específico
     */
    @Override
    public String toString() {
        return String.format("Estudante{nome='%s', matricula='%s', curso='%s', semestre=%d, cra=%.2f}", 
                           nome, matricula, curso, semestre, cra);
    }
}