/**
 * Classe Vendedor - Especialização de Funcionario
 * 
 * Vendedores recebem um salário base mais uma comissão sobre as vendas realizadas.
 * A comissão é calculada como uma porcentagem do valor total das vendas.
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class Vendedor extends Funcionario {
    private double comissao; // Porcentagem da comissão (ex: 0.05 para 5%)
    private double vendasRealizadas;
    
    /**
     * Construtor da classe Vendedor
     * @param nome Nome do vendedor
     * @param salarioBase Salário base
     * @param departamento Departamento
     * @param comissao Porcentagem de comissão (0.05 = 5%)
     */
    public Vendedor(String nome, double salarioBase, String departamento, double comissao) {
        super(nome, salarioBase, departamento);
        this.comissao = comissao;
        this.vendasRealizadas = 0.0;
    }
    
    public double getComissao() {
        return comissao;
    }
    
    /**
     * Registra uma venda realizada pelo vendedor
     * @param valor Valor da venda
     */
    public void registrarVenda(double valor) {
        if (valor > 0) {
            this.vendasRealizadas += valor;
            System.out.println("Venda de R$ " + String.format("%.2f", valor) + " registrada!");
        }
    }
    
    public double getVendasRealizadas() {
        return vendasRealizadas;
    }
    
    /**
     * Implementação do cálculo de salário para Vendedor
     * Salário = Salário Base + (Vendas * Comissão)
     */
    @Override
    public double calcularSalario() {
        return salarioBase + (vendasRealizadas * comissao);
    }
    
    /**
     * Implementação dos detalhes específicos do Vendedor
     */
    @Override
    public void exibirDetalhes() {
        double valorComissao = vendasRealizadas * comissao;
        System.out.println("Cargo: Vendedor");
        System.out.println("Taxa de Comissão: " + (comissao * 100) + "%");
        System.out.println("Vendas Realizadas: R$ " + String.format("%.2f", vendasRealizadas));
        System.out.println("Valor da Comissão: R$ " + String.format("%.2f", valorComissao));
    }
}
