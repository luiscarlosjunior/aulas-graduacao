/**
 * Sistema Universitário - Exemplo Integrado de POO
 * 
 * Classe abstrata Pessoa - base para todas as pessoas na universidade
 * 
 * Este exemplo demonstra TODOS os conceitos de POO integrados:
 * - Classes e Objetos: Diferentes tipos de pessoas
 * - Encapsulamento: Dados protegidos e validados
 * - Herança: Estudante e Professor herdam de Pessoa
 * - Polimorfismo: Comportamentos diferentes para cada tipo
 * - Abstração: Interface e classe abstrata
 * 
 * @author Curso POO Java - Exemplo Integrado
 */
public abstract class Pessoa {
    
    // ===== ATRIBUTOS PROTEGIDOS (HERANÇA) =====
    protected String nome;              // Nome completo
    protected String cpf;               // CPF (documento único)
    protected String email;             // Email institucional
    protected String telefone;          // Telefone de contato
    protected String endereco;          // Endereço residencial
    protected int idade;                // Idade da pessoa
    protected boolean ativo;            // Se está ativo na universidade
    
    // ===== CONSTRUTOR =====
    
    /**
     * Construtor base para todas as pessoas
     */
    public Pessoa(String nome, String cpf, String email, int idade) {
        setNome(nome);      // Usa setter para validação
        setCpf(cpf);        // Usa setter para validação
        setEmail(email);    // Usa setter para validação
        setIdade(idade);    // Usa setter para validação
        this.ativo = true;
        
        System.out.println("👤 Pessoa " + nome + " cadastrada no sistema universitário");
    }
    
    // ===== MÉTODOS ABSTRATOS (POLIMORFISMO OBRIGATÓRIO) =====
    
    /**
     * Cada tipo de pessoa tem uma função específica na universidade
     */
    public abstract void exercerFuncao();
    
    /**
     * Cada tipo de pessoa participa de atividades específicas
     */
    public abstract void participarAtividade(String atividade);
    
    /**
     * Cada tipo de pessoa tem responsabilidades diferentes
     */
    public abstract String[] getResponsabilidades();
    
    // ===== MÉTODOS COMUNS (COMPORTAMENTO PADRÃO) =====
    
    /**
     * Entrar no campus - comportamento comum
     */
    public void entrarCampus() {
        if (!ativo) {
            System.out.println("❌ " + nome + " não está ativo na universidade!");
            return;
        }
        
        System.out.println("🏫 " + nome + " entrou no campus universitário");
        System.out.println("🆔 Cartão de identificação validado");
    }
    
    /**
     * Sair do campus
     */
    public void sairCampus() {
        System.out.println("🏫 " + nome + " saiu do campus universitário");
        System.out.println("⏰ Registro de saída: " + java.time.LocalTime.now().toString().substring(0, 5));
    }
    
    /**
     * Usar biblioteca - comportamento comum que pode ser especializado
     */
    public void usarBiblioteca() {
        if (!ativo) {
            System.out.println("❌ Pessoa inativa não pode usar a biblioteca!");
            return;
        }
        
        System.out.println("📚 " + nome + " está usando a biblioteca");
        System.out.println("🔍 Acesso ao acervo disponível conforme permissões");
    }
    
    /**
     * Usar restaurante universitário
     */
    public void usarRestaurante() {
        if (!ativo) {
            System.out.println("❌ Pessoa inativa não pode usar o restaurante!");
            return;
        }
        
        System.out.println("🍽️ " + nome + " está no restaurante universitário");
        // Diferentes tipos podem ter preços diferentes
        double preco = calcularPrecoRefeicao();
        System.out.printf("💰 Preço da refeição: R$ %.2f\n", preco);
    }
    
    /**
     * Método que pode ser sobrescrito para preços específicos
     */
    protected double calcularPrecoRefeicao() {
        return 5.00; // Preço padrão
    }
    
    /**
     * Atualizar dados pessoais
     */
    public void atualizarDados(String novoTelefone, String novoEndereco) {
        if (novoTelefone != null && !novoTelefone.trim().isEmpty()) {
            this.telefone = novoTelefone.trim();
            System.out.println("📞 Telefone atualizado para: " + telefone);
        }
        
        if (novoEndereco != null && !novoEndereco.trim().isEmpty()) {
            this.endereco = novoEndereco.trim();
            System.out.println("🏠 Endereço atualizado");
        }
    }
    
    /**
     * Desativar pessoa do sistema
     */
    public void desativar(String motivo) {
        if (!ativo) {
            System.out.println("⚠️ " + nome + " já estava inativo!");
            return;
        }
        
        this.ativo = false;
        System.out.println("❌ " + nome + " foi desativado do sistema universitário");
        System.out.println("📋 Motivo: " + motivo);
    }
    
    /**
     * Reativar pessoa no sistema
     */
    public void reativar() {
        if (ativo) {
            System.out.println("ℹ️ " + nome + " já estava ativo!");
            return;
        }
        
        this.ativo = true;
        System.out.println("✅ " + nome + " foi reativado no sistema universitário");
    }
    
    // ===== GETTERS E SETTERS COM VALIDAÇÃO (ENCAPSULAMENTO) =====
    
    public String getNome() { return nome; }
    
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio!");
        }
        if (nome.trim().length() < 2) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres!");
        }
        this.nome = nome.trim();
    }
    
    public String getCpf() { return cpf; }
    
    public void setCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException("CPF deve estar no formato XXX.XXX.XXX-XX!");
        }
        this.cpf = cpf;
    }
    
    public String getEmail() { return email; }
    
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido!");
        }
        // Email institucional deve ter domínio da universidade
        if (!email.endsWith("@universidade.edu.br")) {
            email = email.split("@")[0] + "@universidade.edu.br";
            System.out.println("📧 Email convertido para institucional: " + email);
        }
        this.email = email;
    }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    
    public int getIdade() { return idade; }
    
    public void setIdade(int idade) {
        if (idade < 16 || idade > 100) {
            throw new IllegalArgumentException("Idade deve estar entre 16 e 100 anos!");
        }
        this.idade = idade;
    }
    
    public boolean isAtivo() { return ativo; }
    
    /**
     * Exibir informações básicas da pessoa
     */
    public void exibirInformacoes() {
        System.out.println("\n=== Informações da Pessoa ===");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Telefone: " + (telefone != null ? telefone : "Não informado"));
        System.out.println("Endereço: " + (endereco != null ? endereco : "Não informado"));
        System.out.println("Status: " + (ativo ? "Ativo" : "Inativo"));
        System.out.println("Tipo: " + this.getClass().getSimpleName());
        System.out.println("============================");
    }
    
    /**
     * Método toString para representação textual
     */
    @Override
    public String toString() {
        return String.format("%s{nome='%s', email='%s', idade=%d, ativo=%s}", 
                           this.getClass().getSimpleName(), nome, email, idade, ativo);
    }
    
    /**
     * Método equals para comparação
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Pessoa pessoa = (Pessoa) obj;
        return cpf.equals(pessoa.cpf); // CPF é único
    }
    
    /**
     * Método hashCode baseado no CPF
     */
    @Override
    public int hashCode() {
        return cpf.hashCode();
    }
    
    /**
     * Verificar se a pessoa pode acessar determinado recurso
     */
    public boolean podeAcessar(String recurso) {
        if (!ativo) return false;
        
        // Verificação básica - pode ser sobrescrita
        return switch (recurso.toLowerCase()) {
            case "biblioteca", "restaurante", "campus" -> true;
            default -> false;
        };
    }
    
    /**
     * Calcular tempo na universidade (método que pode ser sobrescrito)
     */
    public abstract int calcularTempoUniversidade();
}