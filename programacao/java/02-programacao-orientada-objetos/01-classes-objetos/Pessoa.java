/**
 * Classe Pessoa - Exemplo básico de classe em Java
 * 
 * Demonstra a definição de uma classe simples com atributos,
 * construtores e métodos básicos.
 * 
 * @author Aulas Graduação
 */
public class Pessoa {
    
    // ===== ATRIBUTOS =====
    // Representam o estado/características do objeto
    private String nome;
    private int idade;
    private String email;
    private boolean ativo;
    
    // ===== CONSTRUTORES =====
    
    /**
     * Construtor padrão (sem parâmetros)
     */
    public Pessoa() {
        // Inicialização com valores padrão
        this.nome = "Não informado";
        this.idade = 0;
        this.email = "";
        this.ativo = true;
    }
    
    /**
     * Construtor com nome e idade
     * 
     * @param nome Nome da pessoa
     * @param idade Idade da pessoa
     */
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.email = "";
        this.ativo = true;
    }
    
    /**
     * Construtor completo
     * 
     * @param nome Nome da pessoa
     * @param idade Idade da pessoa
     * @param email Email da pessoa
     */
    public Pessoa(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.ativo = true;
    }
    
    // ===== MÉTODOS =====
    
    /**
     * Método para a pessoa se apresentar
     */
    public void apresentar() {
        System.out.println("Olá! Meu nome é " + nome + " e tenho " + idade + " anos.");
        if (!email.isEmpty()) {
            System.out.println("Meu email é: " + email);
        }
    }
    
    /**
     * Método para fazer aniversário (aumentar idade)
     */
    public void fazerAniversario() {
        this.idade++;
        System.out.println(nome + " fez aniversário! Agora tem " + idade + " anos.");
    }
    
    /**
     * Método para verificar se é maior de idade
     * 
     * @return true se maior de idade, false caso contrário
     */
    public boolean ehMaiorIdade() {
        return this.idade >= 18;
    }
    
    /**
     * Método para calcular o ano de nascimento
     * 
     * @return ano de nascimento (aproximado)
     */
    public int calcularAnoNascimento() {
        return 2024 - this.idade;
    }
    
    /**
     * Método para ativar/desativar a pessoa
     */
    public void alterarStatus() {
        this.ativo = !this.ativo;
        String status = this.ativo ? "ativada" : "desativada";
        System.out.println("Pessoa " + nome + " foi " + status + ".");
    }
    
    // ===== GETTERS E SETTERS =====
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            System.out.println("Nome não pode ser vazio!");
        }
    }
    
    public int getIdade() {
        return idade;
    }
    
    public void setIdade(int idade) {
        if (idade >= 0 && idade <= 150) {
            this.idade = idade;
        } else {
            System.out.println("Idade deve estar entre 0 e 150 anos!");
        }
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    // ===== MÉTODOS ESPECIAIS =====
    
    /**
     * Representação em string do objeto
     * 
     * @return string representando o objeto
     */
    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}