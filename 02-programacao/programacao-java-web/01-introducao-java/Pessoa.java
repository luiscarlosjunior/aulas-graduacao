/**
 * Classe Pessoa - Demonstra conceitos de Orientação a Objetos
 * Exemplifica encapsulamento, construtores e métodos
 * 
 * @author Apresentação Java Web
 */
public class Pessoa {
    
    // Atributos privados (encapsulamento)
    private String nome;
    private int idade;
    private String email;
    private double salario;
    
    // Construtor padrão
    public Pessoa() {
        this.nome = "Não informado";
        this.idade = 0;
        this.email = "não informado";
        this.salario = 0.0;
    }
    
    // Construtor com parâmetros
    public Pessoa(String nome, int idade, String email, double salario) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.salario = salario;
    }
    
    // Métodos getters
    public String getNome() {
        return nome;
    }
    
    public int getIdade() {
        return idade;
    }
    
    public String getEmail() {
        return email;
    }
    
    public double getSalario() {
        return salario;
    }
    
    // Métodos setters
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
    }
    
    public void setIdade(int idade) {
        if (idade >= 0 && idade <= 120) {
            this.idade = idade;
        }
    }
    
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }
    
    public void setSalario(double salario) {
        if (salario >= 0) {
            this.salario = salario;
        }
    }
    
    // Métodos de negócio
    public boolean isMaiorIdade() {
        return idade >= 18;
    }
    
    public double calcularSalarioAnual() {
        return salario * 12;
    }
    
    public String getCategoriaProfissional() {
        if (salario < 1500) {
            return "Iniciante";
        } else if (salario < 5000) {
            return "Intermediário";
        } else {
            return "Sênior";
        }
    }
    
    public void aumentarSalario(double percentual) {
        if (percentual > 0) {
            this.salario += this.salario * (percentual / 100);
        }
    }
    
    // Método toString para representação textual
    @Override
    public String toString() {
        return String.format(
            "Pessoa{nome='%s', idade=%d, email='%s', salario=R$%.2f, categoria='%s'}",
            nome, idade, email, salario, getCategoriaProfissional()
        );
    }
    
    // Método para exibir informações detalhadas
    public void exibirInformacoes() {
        System.out.println("=== Informações da Pessoa ===");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Email: " + email);
        System.out.printf("Salário: R$ %.2f%n", salario);
        System.out.println("Maior de idade: " + (isMaiorIdade() ? "Sim" : "Não"));
        System.out.println("Categoria: " + getCategoriaProfissional());
        System.out.printf("Salário anual: R$ %.2f%n", calcularSalarioAnual());
        System.out.println("============================");
    }
}