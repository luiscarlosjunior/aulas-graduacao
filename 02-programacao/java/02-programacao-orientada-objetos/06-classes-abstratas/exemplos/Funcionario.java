/**
 * Classe abstrata Funcionario - Sistema de Funcionários
 * 
 * Representa a base para todos os tipos de funcionários de uma empresa.
 * Define atributos e comportamentos comuns, delegando o cálculo de salário
 * específico para cada tipo de funcionário.
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public abstract class Funcionario {
    protected String nome;
    protected double salarioBase;
    protected String departamento;
    
    /**
     * Construtor da classe Funcionario
     * @param nome Nome do funcionário
     * @param salarioBase Salário base do funcionário
     * @param departamento Departamento onde o funcionário trabalha
     */
    public Funcionario(String nome, double salarioBase, String departamento) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.departamento = departamento;
    }
    
    // Getters
    public String getNome() {
        return nome;
    }
    
    public double getSalarioBase() {
        return salarioBase;
    }
    
    public String getDepartamento() {
        return departamento;
    }
    
    /**
     * Método abstrato para calcular o salário
     * Cada tipo de funcionário implementa sua própria lógica
     * @return Salário total do funcionário
     */
    public abstract double calcularSalario();
    
    /**
     * Método abstrato para exibir detalhes específicos
     * Cada tipo de funcionário exibe suas informações específicas
     */
    public abstract void exibirDetalhes();
    
    /**
     * Método concreto para exibir informações básicas
     * Utiliza métodos abstratos (Template Method Pattern)
     */
    public void exibirInformacoes() {
        System.out.println("=== INFORMAÇÕES DO FUNCIONÁRIO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Departamento: " + departamento);
        System.out.println("Salário Base: R$ " + String.format("%.2f", salarioBase));
        System.out.println("Salário Total: R$ " + String.format("%.2f", calcularSalario()));
        exibirDetalhes();
        System.out.println("================================\n");
    }
}
