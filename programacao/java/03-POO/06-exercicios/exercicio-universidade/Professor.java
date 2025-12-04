/**
 * Professor - Exemplo de Herança e Polimorfismo no Sistema Universitário
 * 
 * Demonstra:
 * - Herança: extends Pessoa
 * - Encapsulamento: atributos private com validação
 * - Polimorfismo: implementações específicas diferentes do Estudante
 * - Sobrescrita: comportamentos específicos de professor
 */
public class Professor extends Pessoa {
    
    // ===== ATRIBUTOS ESPECÍFICOS DO PROFESSOR =====
    private String registro;            // Registro funcional único
    private String departamento;        // Departamento acadêmico
    private String titulacao;           // Graduação, Mestrado, Doutorado, Pós-Doc
    private double salario;             // Salário mensal
    private String regime;              // 20h, 40h, Dedicação Exclusiva
    private int anosExperiencia;        // Anos de experiência docente
    private int publicacoes;            // Número de publicações científicas
    private boolean coordenador;        // Se é coordenador de curso
    private String[] disciplinas;       // Disciplinas que ministra
    private int anoContratacao;         // Ano de contratação
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor para professor
     */
    public Professor(String nome, String cpf, String email, int idade,
                    String registro, String departamento, String titulacao, 
                    String regime, int anoContratacao) {
        
        // Chama construtor da classe pai
        super(nome, cpf, email, idade);
        
        // Inicializa atributos específicos
        setRegistro(registro);
        setDepartamento(departamento);
        setTitulacao(titulacao);
        setRegime(regime);
        this.anoContratacao = anoContratacao;
        this.anosExperiencia = 0;
        this.publicacoes = 0;
        this.coordenador = false;
        this.disciplinas = new String[0];
        this.salario = calcularSalarioBase();
        
        System.out.println("👨‍🏫 Professor contratado no departamento de " + departamento);
        System.out.println("📋 Registro: " + registro + " - " + regime);
    }
    
    // ===== IMPLEMENTAÇÃO DOS MÉTODOS ABSTRATOS (POLIMORFISMO) =====
    
    /**
     * Função específica do professor na universidade
     */
    @Override
    public void exercerFuncao() {
        if (!ativo) {
            System.out.println("❌ Professor inativo não pode exercer função!");
            return;
        }
        
        System.out.println("👨‍🏫 " + nome + " está exercendo atividades docentes");
        System.out.println("🏛️ Departamento: " + departamento);
        System.out.println("🎓 Titulação: " + titulacao);
        System.out.printf("💰 Salário: R$ %.2f\n", salario);
        
        // Atividades principais do professor
        System.out.println("\n📚 Atividades principais:");
        System.out.println("• Ministrar aulas e disciplinas");
        System.out.println("• Orientar estudantes e pesquisas");
        System.out.println("• Desenvolver pesquisa científica");
        System.out.println("• Participar de atividades administrativas");
        
        // Simula atividade atual
        String[] atividades = {
            "Ministrando aula de " + gerarDisciplina(),
            "Orientando trabalho de conclusão de curso",
            "Desenvolvendo projeto de pesquisa",
            "Participando de reunião departamental",
            "Corrigindo provas e trabalhos",
            "Preparando material didático"
        };
        
        String atividade = atividades[(int)(Math.random() * atividades.length)];
        System.out.println("🎯 Atividade atual: " + atividade);
    }
    
    /**
     * Participação específica em atividades acadêmicas
     */
    @Override
    public void participarAtividade(String atividade) {
        if (!ativo) {
            System.out.println("❌ Professor inativo não pode participar de atividades!");
            return;
        }
        
        System.out.println("👨‍🏫 " + nome + " participando de: " + atividade);
        
        switch (atividade.toLowerCase()) {
            case "aula":
                System.out.println("📚 Ministrando conteúdo didático");
                System.out.println("👥 Interagindo com estudantes");
                System.out.println("❓ Respondendo dúvidas e questões");
                break;
                
            case "orientacao":
                System.out.println("🎓 Orientando trabalhos acadêmicos");
                System.out.println("📖 Fornecendo direcionamento científico");
                System.out.println("💡 Compartilhando experiência e conhecimento");
                break;
                
            case "pesquisa":
                System.out.println("🔬 Desenvolvendo pesquisa científica");
                System.out.println("📊 Coletando e analisando dados");
                if (publicacoes > 0) {
                    System.out.println("📄 Preparando publicação científica");
                }
                break;
                
            case "reuniao":
                if (coordenador) {
                    System.out.println("🏛️ Liderando reunião como coordenador");
                    System.out.println("📋 Definindo diretrizes departamentais");
                } else {
                    System.out.println("👥 Participando de discussões departamentais");
                    System.out.println("💭 Contribuindo com expertise técnica");
                }
                break;
                
            case "extensao":
                System.out.println("🌍 Desenvolvendo projeto de extensão");
                System.out.println("🤝 Conectando universidade e sociedade");
                System.out.println("📢 Compartilhando conhecimento acadêmico");
                break;
                
            case "pos-graduacao":
                if (titulacao.equals("Doutorado") || titulacao.equals("Pós-Doutorado")) {
                    System.out.println("🎓 Atuando na pós-graduação");
                    System.out.println("📚 Ministrando disciplinas avançadas");
                    System.out.println("🔬 Orientando mestrandos e doutorandos");
                } else {
                    System.out.println("📚 Colaborando com programa de pós-graduação");
                    System.out.println("🤝 Apoiando pesquisas de alto nível");
                }
                break;
                
            default:
                System.out.println("🎯 Aplicando expertise acadêmica na atividade");
                System.out.println("📈 Contribuindo para excelência universitária");
        }
    }
    
    /**
     * Responsabilidades específicas do professor
     */
    @Override
    public String[] getResponsabilidades() {
        String[] responsabilidadesBase = {
            "Ministrar aulas conforme programa da disciplina",
            "Elaborar e aplicar avaliações",
            "Orientar trabalhos e pesquisas acadêmicas",
            "Manter-se atualizado na área de expertise",
            "Desenvolver pesquisa científica",
            "Participar de atividades administrativas",
            "Contribuir com projetos de extensão",
            "Publicar trabalhos científicos"
        };
        
        if (coordenador) {
            String[] responsabilidadesCoordenador = {
                "Coordenar atividades do curso/departamento",
                "Gerenciar corpo docente",
                "Aprovar mudanças curriculares",
                "Representar o departamento em instâncias superiores"
            };
            
            // Combina responsabilidades
            String[] todas = new String[responsabilidadesBase.length + responsabilidadesCoordenador.length];
            System.arraycopy(responsabilidadesBase, 0, todas, 0, responsabilidadesBase.length);
            System.arraycopy(responsabilidadesCoordenador, 0, todas, responsabilidadesBase.length, responsabilidadesCoordenador.length);
            return todas;
        }
        
        return responsabilidadesBase;
    }
    
    /**
     * Calcular tempo na universidade
     */
    @Override
    public int calcularTempoUniversidade() {
        int anoAtual = java.time.Year.now().getValue();
        return anoAtual - anoContratacao;
    }
    
    // ===== MÉTODOS ESPECÍFICOS DO PROFESSOR =====
    
    /**
     * Ministrar disciplina
     */
    public void ministrarDisciplina(String disciplina, int cargaHoraria) {
        if (!ativo) {
            System.out.println("❌ Professor inativo não pode ministrar disciplinas!");
            return;
        }
        
        System.out.println("📚 " + nome + " ministrando: " + disciplina);
        System.out.println("⏰ Carga horária: " + cargaHoraria + " horas");
        System.out.println("🎯 Departamento: " + departamento);
        
        // Adiciona disciplina ao array
        String[] novasDisciplinas = new String[disciplinas.length + 1];
        System.arraycopy(disciplinas, 0, novasDisciplinas, 0, disciplinas.length);
        novasDisciplinas[disciplinas.length] = disciplina;
        disciplinas = novasDisciplinas;
        
        System.out.println("✅ Disciplina adicionada ao portfolio do professor");
        System.out.println("📊 Total de disciplinas: " + disciplinas.length);
    }
    
    /**
     * Orientar estudante
     */
    public void orientarEstudante(Estudante estudante, String tipoOrientacao) {
        if (!ativo) {
            System.out.println("❌ Professor inativo não pode orientar estudantes!");
            return;
        }
        
        System.out.println("🎓 " + nome + " orientando " + estudante.getNome());
        System.out.println("📋 Tipo: " + tipoOrientacao);
        
        switch (tipoOrientacao.toLowerCase()) {
            case "tcc":
                System.out.println("📄 Orientação de Trabalho de Conclusão de Curso");
                System.out.println("📚 Definindo tema e metodologia");
                System.out.println("⏰ Cronograma de encontros estabelecido");
                break;
                
            case "ic":
                if (publicacoes >= 3) {
                    System.out.println("🔬 Orientação de Iniciação Científica");
                    System.out.println("📊 Projeto de pesquisa em andamento");
                    System.out.println("🎯 Desenvolvimento de habilidades investigativas");
                } else {
                    System.out.println("⚠️ Professor precisa ter mais publicações para orientar IC");
                }
                break;
                
            case "estagio":
                System.out.println("🏢 Orientação de estágio supervisionado");
                System.out.println("🤝 Acompanhamento de atividades práticas");
                System.out.println("📈 Avaliação de desempenho profissional");
                break;
                
            case "mestrado":
                if (titulacao.equals("Doutorado") || titulacao.equals("Pós-Doutorado")) {
                    System.out.println("🎓 Orientação de dissertação de mestrado");
                    System.out.println("📚 Pesquisa de nível avançado");
                } else {
                    System.out.println("❌ Apenas doutores podem orientar mestrado");
                }
                break;
                
            case "doutorado":
                if (titulacao.equals("Doutorado") || titulacao.equals("Pós-Doutorado")) {
                    if (publicacoes >= 10) {
                        System.out.println("🏆 Orientação de tese de doutorado");
                        System.out.println("🔬 Pesquisa de excelência acadêmica");
                    } else {
                        System.out.println("⚠️ Necessárias mais publicações para orientar doutorado");
                    }
                } else {
                    System.out.println("❌ Apenas doutores podem orientar doutorado");
                }
                break;
                
            default:
                System.out.println("📖 Orientação acadêmica geral");
                System.out.println("💡 Compartilhando conhecimento e experiência");
        }
    }
    
    /**
     * Publicar trabalho científico
     */
    public void publicarTrabalho(String titulo, String revista) {
        if (!ativo) {
            System.out.println("❌ Professor inativo não pode publicar trabalhos!");
            return;
        }
        
        publicacoes++;
        System.out.println("📄 " + nome + " publicou trabalho científico:");
        System.out.println("📰 Título: " + titulo);
        System.out.println("📚 Revista: " + revista);
        System.out.println("📊 Total de publicações: " + publicacoes);
        
        // Atualiza salário baseado em publicações
        double bonusPublicacao = 200.0; // R$ 200 por publicação
        salario += bonusPublicacao;
        System.out.printf("💰 Bônus de publicação: R$ %.2f (novo salário: R$ %.2f)\n", 
                         bonusPublicacao, salario);
        
        // Verifica elegibilidade para coordenação
        if (publicacoes >= 5 && anosExperiencia >= 3 && !coordenador) {
            System.out.println("🏆 Professor elegível para coordenação de curso!");
        }
    }
    
    /**
     * Tornar-se coordenador
     */
    public boolean tornarCoordenador(String areaCoordencao) {
        if (!ativo) {
            System.out.println("❌ Professor inativo não pode ser coordenador!");
            return false;
        }
        
        if (coordenador) {
            System.out.println("ℹ️ " + nome + " já é coordenador!");
            return false;
        }
        
        // Critérios para coordenação
        if (anosExperiencia < 3) {
            System.out.println("❌ Necessária experiência mínima de 3 anos");
            return false;
        }
        
        if (publicacoes < 5) {
            System.out.println("❌ Necessárias pelo menos 5 publicações");
            return false;
        }
        
        if (!titulacao.equals("Doutorado") && !titulacao.equals("Pós-Doutorado")) {
            System.out.println("❌ Necessária titulação de doutor");
            return false;
        }
        
        coordenador = true;
        double aumentoSalarial = salario * 0.25; // 25% de aumento
        salario += aumentoSalarial;
        
        System.out.println("🏆 " + nome + " nomeado coordenador de " + areaCoordencao + "!");
        System.out.printf("💰 Aumento salarial: R$ %.2f (novo salário: R$ %.2f)\n", 
                         aumentoSalarial, salario);
        System.out.println("📋 Novas responsabilidades administrativas ativadas");
        
        return true;
    }
    
    /**
     * Solicitar progressão na carreira
     */
    public void solicitarProgressao() {
        if (!ativo) {
            System.out.println("❌ Professor inativo não pode solicitar progressão!");
            return;
        }
        
        System.out.println("📈 " + nome + " solicitando progressão na carreira");
        System.out.println("📊 Análise de critérios:");
        System.out.println("   • Anos de experiência: " + anosExperiencia);
        System.out.println("   • Publicações: " + publicacoes);
        System.out.println("   • Titulação: " + titulacao);
        System.out.println("   • Coordenador: " + (coordenador ? "Sim" : "Não"));
        
        // Simula avaliação para progressão
        int pontos = 0;
        pontos += anosExperiencia * 2;
        pontos += publicacoes * 3;
        pontos += coordenador ? 10 : 0;
        
        switch (titulacao) {
            case "Graduação" -> pontos += 5;
            case "Especialização" -> pontos += 8;
            case "Mestrado" -> pontos += 12;
            case "Doutorado" -> pontos += 20;
            case "Pós-Doutorado" -> pontos += 25;
        }
        
        System.out.println("🎯 Pontuação total: " + pontos);
        
        if (pontos >= 30) {
            double aumento = salario * 0.15; // 15% de aumento
            salario += aumento;
            System.out.println("✅ Progressão APROVADA!");
            System.out.printf("💰 Aumento salarial: R$ %.2f (novo salário: R$ %.2f)\n", 
                             aumento, salario);
        } else {
            System.out.println("❌ Progressão NEGADA - critérios insuficientes");
            System.out.println("📚 Sugestão: Aumentar publicações e experiência");
        }
    }
    
    /**
     * Usar biblioteca com privilégios de professor
     */
    @Override
    public void usarBiblioteca() {
        super.usarBiblioteca(); // Chama método da classe pai
        
        if (ativo) {
            System.out.println("📖 Acesso total: acervo completo, obras raras");
            System.out.println("💻 Uso ilimitado de computadores");
            System.out.println("📚 Empréstimo especial: 20 livros por 30 dias");
            System.out.println("🔍 Acesso a bases científicas internacionais");
            
            if (coordenador) {
                System.out.println("🏛️ Privilégios de coordenador: acesso administrativo");
            }
        }
    }
    
    /**
     * Preço diferenciado para professor no restaurante
     */
    @Override
    protected double calcularPrecoRefeicao() {
        return 8.00; // Preço para servidores
    }
    
    // ===== MÉTODOS AUXILIARES =====
    
    private double calcularSalarioBase() {
        double base = 3000.0; // Salário base
        
        // Ajuste por titulação
        switch (titulacao) {
            case "Graduação" -> base *= 1.0;
            case "Especialização" -> base *= 1.2;
            case "Mestrado" -> base *= 1.8;
            case "Doutorado" -> base *= 2.5;
            case "Pós-Doutorado" -> base *= 3.0;
        }
        
        // Ajuste por regime
        switch (regime) {
            case "20h" -> base *= 1.0;
            case "40h" -> base *= 2.0;
            case "Dedicação Exclusiva" -> base *= 2.8;
        }
        
        return base;
    }
    
    private String gerarDisciplina() {
        String[] disciplinas = {
            "Introdução à " + departamento,
            "Metodologia Científica",
            "Seminários Avançados",
            "Tópicos Especiais",
            "Projeto de Pesquisa"
        };
        return disciplinas[(int)(Math.random() * disciplinas.length)];
    }
    
    // ===== GETTERS E SETTERS ESPECÍFICOS =====
    
    public String getRegistro() { return registro; }
    
    public void setRegistro(String registro) {
        if (registro == null || !registro.matches("\\d{6}")) {
            throw new IllegalArgumentException("Registro deve ter 6 dígitos!");
        }
        this.registro = registro;
    }
    
    public String getDepartamento() { return departamento; }
    
    public void setDepartamento(String departamento) {
        if (departamento == null || departamento.trim().isEmpty()) {
            throw new IllegalArgumentException("Departamento não pode ser vazio!");
        }
        this.departamento = departamento.trim();
    }
    
    public String getTitulacao() { return titulacao; }
    
    public void setTitulacao(String titulacao) {
        String[] titulacoesValidas = {"Graduação", "Especialização", "Mestrado", "Doutorado", "Pós-Doutorado"};
        boolean valida = false;
        for (String t : titulacoesValidas) {
            if (t.equals(titulacao)) {
                valida = true;
                break;
            }
        }
        if (!valida) {
            throw new IllegalArgumentException("Titulação inválida!");
        }
        this.titulacao = titulacao;
    }
    
    public String getRegime() { return regime; }
    
    public void setRegime(String regime) {
        if (!regime.equals("20h") && !regime.equals("40h") && !regime.equals("Dedicação Exclusiva")) {
            throw new IllegalArgumentException("Regime deve ser: 20h, 40h ou Dedicação Exclusiva");
        }
        this.regime = regime;
    }
    
    public double getSalario() { return salario; }
    public int getAnosExperiencia() { return anosExperiencia; }
    public void setAnosExperiencia(int anosExperiencia) { this.anosExperiencia = anosExperiencia; }
    public int getPublicacoes() { return publicacoes; }
    public boolean isCoordenador() { return coordenador; }
    public String[] getDisciplinas() { return disciplinas.clone(); }
    public int getAnoContratacao() { return anoContratacao; }
    
    /**
     * Sobrescreve exibirInformacoes para incluir dados específicos
     */
    @Override
    public void exibirInformacoes() {
        // Chama método da classe pai
        super.exibirInformacoes();
        
        // Adiciona informações específicas do professor
        System.out.println("=== Informações Profissionais ===");
        System.out.println("Registro: " + registro);
        System.out.println("Departamento: " + departamento);
        System.out.println("Titulação: " + titulacao);
        System.out.println("Regime: " + regime);
        System.out.printf("Salário: R$ %.2f\n", salario);
        System.out.println("Anos de experiência: " + anosExperiencia);
        System.out.println("Publicações: " + publicacoes);
        System.out.println("Coordenador: " + (coordenador ? "Sim" : "Não"));
        System.out.println("Disciplinas ministradas: " + disciplinas.length);
        System.out.println("Ano de contratação: " + anoContratacao);
        System.out.println("Tempo na universidade: " + calcularTempoUniversidade() + " anos");
        System.out.println("================================\n");
    }
    
    /**
     * Sobrescreve toString para formato específico
     */
    @Override
    public String toString() {
        return String.format("Professor{nome='%s', registro='%s', departamento='%s', titulacao='%s', salario=%.2f}", 
                           nome, registro, departamento, titulacao, salario);
    }
}