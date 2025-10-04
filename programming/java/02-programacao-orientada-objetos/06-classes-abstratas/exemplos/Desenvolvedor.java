/**
 * Classe Desenvolvedor - Especialização de Funcionario
 * 
 * Desenvolvedores recebem um salário base mais um bônus por projeto concluído.
 * Cada projeto adiciona um valor fixo ao salário.
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class Desenvolvedor extends Funcionario {
    private String linguagensProgramacao;
    private int projetos;
    private double bonusPorProjeto;
    
    /**
     * Construtor da classe Desenvolvedor
     * @param nome Nome do desenvolvedor
     * @param salarioBase Salário base
     * @param departamento Departamento
     * @param linguagens Linguagens de programação que domina
     */
    public Desenvolvedor(String nome, double salarioBase, String departamento, String linguagens) {
        super(nome, salarioBase, departamento);
        this.linguagensProgramacao = linguagens;
        this.projetos = 0;
        this.bonusPorProjeto = 300.0; // R$ 300 por projeto
    }
    
    public String getLinguagens() {
        return linguagensProgramacao;
    }
    
    /**
     * Adiciona um projeto concluído ao histórico do desenvolvedor
     */
    public void adicionarProjeto() {
        this.projetos++;
        System.out.println("Projeto #" + projetos + " concluído! Bônus adicionado.");
    }
    
    public int getProjetos() {
        return projetos;
    }
    
    /**
     * Implementação do cálculo de salário para Desenvolvedor
     * Salário = Salário Base + (Projetos * Bônus por Projeto)
     */
    @Override
    public double calcularSalario() {
        return salarioBase + (projetos * bonusPorProjeto);
    }
    
    /**
     * Implementação dos detalhes específicos do Desenvolvedor
     */
    @Override
    public void exibirDetalhes() {
        System.out.println("Cargo: Desenvolvedor");
        System.out.println("Linguagens: " + linguagensProgramacao);
        System.out.println("Projetos Concluídos: " + projetos);
        System.out.println("Bônus por Projeto: R$ " + String.format("%.2f", bonusPorProjeto));
        System.out.println("Total de Bônus: R$ " + String.format("%.2f", projetos * bonusPorProjeto));
    }
}
