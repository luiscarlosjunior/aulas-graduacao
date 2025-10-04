/**
 * Classe Gerente - Especialização de Funcionario
 * 
 * Gerentes recebem um bônus fixo além do salário base,
 * e o valor do bônus pode variar de acordo com o nível gerencial.
 * 
 * @author Curso POO Java - Universidade Nove de Julho
 */
public class Gerente extends Funcionario {
    private double bonus;
    private int nivel; // 1: Junior, 2: Pleno, 3: Senior
    
    /**
     * Construtor da classe Gerente
     * @param nome Nome do gerente
     * @param salarioBase Salário base
     * @param departamento Departamento
     * @param bonus Bônus adicional
     * @param nivel Nível gerencial (1-3)
     */
    public Gerente(String nome, double salarioBase, String departamento, double bonus, int nivel) {
        super(nome, salarioBase, departamento);
        this.bonus = bonus;
        this.nivel = nivel;
    }
    
    public double getBonus() {
        return bonus;
    }
    
    public int getNivel() {
        return nivel;
    }
    
    /**
     * Implementação do cálculo de salário para Gerente
     * Salário = Salário Base + Bônus + (Nível * 500)
     */
    @Override
    public double calcularSalario() {
        return salarioBase + bonus + (nivel * 500.0);
    }
    
    /**
     * Implementação dos detalhes específicos do Gerente
     */
    @Override
    public void exibirDetalhes() {
        String nivelStr = switch(nivel) {
            case 1 -> "Júnior";
            case 2 -> "Pleno";
            case 3 -> "Sênior";
            default -> "Não definido";
        };
        System.out.println("Cargo: Gerente " + nivelStr);
        System.out.println("Bônus: R$ " + String.format("%.2f", bonus));
        System.out.println("Adicional de Nível: R$ " + String.format("%.2f", nivel * 500.0));
    }
}
