/**
 * Exemplo Clássico da Literatura: Hierarquia de Funcionários
 * 
 * Este é um dos exemplos mais utilizados para demonstrar polimorfismo,
 * aparecendo em livros clássicos como "Design Patterns" (Gang of Four)
 * e "Effective Java" (Joshua Bloch).
 * 
 * Classe abstrata Funcionario - base para todos os tipos de funcionários
 * 
 * @author Curso POO Java - Exemplo Clássico
 */
public abstract class Funcionario {
    
    // ===== ATRIBUTOS COMUNS A TODOS OS FUNCIONÁRIOS =====
    protected String nome;              // Nome completo
    protected String cpf;               // CPF (documento único)
    protected String matricula;         // Matrícula da empresa
    protected double salarioBase;       // Salário base mensal
    protected String departamento;      // Departamento onde trabalha
    protected int anosEmpresa;          // Anos de experiência na empresa
    protected boolean ativo;            // Se o funcionário está ativo
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor base para todos os funcionários
     */
    public Funcionario(String nome, String cpf, String matricula, 
                      double salarioBase, String departamento) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.salarioBase = salarioBase;
        this.departamento = departamento;
        this.anosEmpresa = 0;
        this.ativo = true;
        
        System.out.println("👤 Funcionário " + nome + " cadastrado na empresa");
    }
    
    // ===== MÉTODOS ABSTRATOS (POLIMORFISMO OBRIGATÓRIO) =====
    
    /**
     * Cada tipo de funcionário calcula o salário de forma diferente
     * Este é o método principal que demonstra polimorfismo
     */
    public abstract double calcularSalario();
    
    /**
     * Cada tipo de funcionário tem responsabilidades específicas
     */
    public abstract void trabalhar();
    
    /**
     * Cada tipo de funcionário participa de reuniões de forma diferente
     */
    public abstract void participarReuniao(String tipoReuniao);
    
    // ===== MÉTODOS COMUNS (COMPORTAMENTO PADRÃO) =====
    
    /**
     * Bater ponto - comportamento comum que pode ser sobrescrito
     */
    public void baterPonto() {
        if (!ativo) {
            System.out.println("❌ Funcionário " + nome + " não está ativo!");
            return;
        }
        
        System.out.println("🕐 " + nome + " bateu ponto às " + 
                          java.time.LocalTime.now().toString().substring(0, 5));
    }
    
    /**
     * Participar de treinamento
     */
    public void participarTreinamento(String tipoTreinamento) {
        if (!ativo) {
            System.out.println("❌ Funcionário inativo não pode participar de treinamentos!");
            return;
        }
        
        System.out.println("📚 " + nome + " está participando do treinamento: " + tipoTreinamento);
        System.out.println("💡 Conhecimento adquirido registrado no sistema");
    }
    
    /**
     * Solicitar férias
     */
    public boolean solicitarFerias(int dias) {
        if (!ativo) {
            System.out.println("❌ Funcionário inativo não pode solicitar férias!");
            return false;
        }
        
        if (anosEmpresa < 1) {
            System.out.println("❌ " + nome + " ainda não tem direito a férias (menos de 1 ano na empresa)");
            return false;
        }
        
        if (dias > 30) {
            System.out.println("❌ Período de férias não pode exceder 30 dias");
            return false;
        }
        
        System.out.println("✅ Férias de " + dias + " dias aprovadas para " + nome);
        return true;
    }
    
    /**
     * Avaliar funcionário (método que pode ser sobrescrito)
     */
    public String avaliar() {
        double salario = calcularSalario(); // Usa polimorfismo!
        
        if (salario > 10000) {
            return "Excelente";
        } else if (salario > 6000) {
            return "Bom";
        } else if (salario > 3000) {
            return "Regular";
        } else {
            return "Precisa melhorar";
        }
    }
    
    /**
     * Promover funcionário (aumenta anos de experiência)
     */
    public void promover(int anosAdicionais) {
        if (!ativo) {
            System.out.println("❌ Não é possível promover funcionário inativo!");
            return;
        }
        
        int anosAnteriores = this.anosEmpresa;
        this.anosEmpresa += anosAdicionais;
        
        System.out.println("🎉 " + nome + " foi promovido!");
        System.out.println("📈 Experiência: " + anosAnteriores + " → " + anosEmpresa + " anos");
        System.out.printf("💰 Novo salário: R$ %.2f\n", calcularSalario());
    }
    
    /**
     * Demitir funcionário
     */
    public void demitir(String motivo) {
        if (!ativo) {
            System.out.println("⚠️ " + nome + " já foi demitido anteriormente!");
            return;
        }
        
        this.ativo = false;
        System.out.println("❌ " + nome + " foi demitido da empresa");
        System.out.println("📋 Motivo: " + motivo);
        System.out.println("💼 Último salário: R$ " + String.format("%.2f", calcularSalario()));
    }
    
    // ===== GETTERS E SETTERS =====
    
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getMatricula() { return matricula; }
    public double getSalarioBase() { return salarioBase; }
    public String getDepartamento() { return departamento; }
    public int getAnosEmpresa() { return anosEmpresa; }
    public boolean isAtivo() { return ativo; }
    
    public void setSalarioBase(double salarioBase) {
        if (salarioBase > 0) {
            this.salarioBase = salarioBase;
        }
    }
    
    public void setAnosEmpresa(int anosEmpresa) {
        if (anosEmpresa >= 0) {
            this.anosEmpresa = anosEmpresa;
        }
    }
    
    /**
     * Exibir informações do funcionário
     */
    public void exibirInformacoes() {
        System.out.println("\n=== Informações do Funcionário ===");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Departamento: " + departamento);
        System.out.printf("Salário base: R$ %.2f\n", salarioBase);
        System.out.printf("Salário calculado: R$ %.2f\n", calcularSalario()); // Polimorfismo!
        System.out.println("Anos na empresa: " + anosEmpresa);
        System.out.println("Status: " + (ativo ? "Ativo" : "Inativo"));
        System.out.println("Avaliação: " + avaliar());
        System.out.println("================================\n");
    }
    
    /**
     * Comparar funcionários por salário
     */
    public int compararSalario(Funcionario outro) {
        double meuSalario = this.calcularSalario();
        double outroSalario = outro.calcularSalario();
        
        return Double.compare(meuSalario, outroSalario);
    }
    
    /**
     * Representação textual do funcionário
     */
    @Override
    public String toString() {
        return String.format("%s{nome='%s', matricula='%s', salario=%.2f, departamento='%s'}", 
                           this.getClass().getSimpleName(), nome, matricula, 
                           calcularSalario(), departamento);
    }
    
    /**
     * Método utilitário para calcular bônus baseado nos anos de empresa
     */
    protected double calcularBonusExperiencia() {
        return anosEmpresa * 0.05; // 5% por ano de experiência
    }
    
    /**
     * Método para simular reunião virtual
     */
    public void entrarReuniaoVirtual(String plataforma) {
        if (!ativo) {
            System.out.println("❌ Funcionário inativo não pode participar de reuniões!");
            return;
        }
        
        System.out.println("💻 " + nome + " entrou na reunião via " + plataforma);
        System.out.println("🎤 Microfone testado, câmera ligada");
    }
}