/**
 * Exemplo de Composition over Inheritance
 * Preferir composição à herança para maior flexibilidade
 * 
 * PROBLEMA COM HERANÇA: Explosão combinatória de classes.
 * SOLUÇÃO COM COMPOSIÇÃO: Componentes reutilizáveis e flexíveis.
 */
import java.util.*;

// ✅ Composição: Estratégia de cálculo de bônus
interface CalculadoraBonus {
    double calcular(double salarioBase);
    String getDescricao();
}

class BonusAnual implements CalculadoraBonus {
    @Override
    public double calcular(double salarioBase) {
        return salarioBase * 0.10; // 10% bônus anual
    }
    
    @Override
    public String getDescricao() {
        return "Bônus Anual (10%)";
    }
}

class BonusTrimestral implements CalculadoraBonus {
    @Override
    public double calcular(double salarioBase) {
        return salarioBase * 0.03; // 3% bônus trimestral
    }
    
    @Override
    public String getDescricao() {
        return "Bônus Trimestral (3%)";
    }
}

class ComissaoVendas implements CalculadoraBonus {
    private double percentualComissao;
    
    public ComissaoVendas(double percentualComissao) {
        this.percentualComissao = percentualComissao;
    }
    
    @Override
    public double calcular(double salarioBase) {
        return salarioBase * percentualComissao;
    }
    
    @Override
    public String getDescricao() {
        return "Comissão de Vendas (" + (percentualComissao * 100) + "%)";
    }
}

class SemBonus implements CalculadoraBonus {
    @Override
    public double calcular(double salarioBase) {
        return 0;
    }
    
    @Override
    public String getDescricao() {
        return "Sem bônus";
    }
}

// ✅ Funcionário usa COMPOSIÇÃO, não herança
class Funcionario {
    private String nome;
    private double salarioBase;
    private List<CalculadoraBonus> calculadorasBonus;
    
    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.calculadorasBonus = new ArrayList<>();
    }
    
    // ✅ Pode adicionar/remover bônus dinamicamente
    public void adicionarBonus(CalculadoraBonus bonus) {
        calculadorasBonus.add(bonus);
        System.out.println("  + " + bonus.getDescricao() + " adicionado");
    }
    
    public void removerBonus(CalculadoraBonus bonus) {
        calculadorasBonus.remove(bonus);
        System.out.println("  - " + bonus.getDescricao() + " removido");
    }
    
    public double calcularSalario() {
        double total = salarioBase;
        for (CalculadoraBonus bonus : calculadorasBonus) {
            total += bonus.calcular(salarioBase);
        }
        return total;
    }
    
    public void mostrarDetalhes() {
        System.out.println("\nFuncionário: " + nome);
        System.out.println("Salário base: R$ " + salarioBase);
        System.out.println("Bônus ativos:");
        for (CalculadoraBonus bonus : calculadorasBonus) {
            double valor = bonus.calcular(salarioBase);
            System.out.println("  - " + bonus.getDescricao() + ": R$ " + valor);
        }
        System.out.println("Salário total: R$ " + calcularSalario());
    }
    
    public String getNome() { return nome; }
    public double getSalarioBase() { return salarioBase; }
}

public class ComposicaoSobreHeranca {
    public static void main(String[] args) {
        System.out.println("=== COMPOSITION OVER INHERITANCE ===");
        
        Funcionario func = new Funcionario("João Silva", 5000);
        
        System.out.println("\n1. Funcionário criado (sem bônus):");
        func.mostrarDetalhes();
        
        System.out.println("\n2. Adicionando bônus anual:");
        func.adicionarBonus(new BonusAnual());
        func.mostrarDetalhes();
        
        System.out.println("\n3. Adicionando bônus trimestral:");
        func.adicionarBonus(new BonusTrimestral());
        func.mostrarDetalhes();
        
        System.out.println("\n4. Adicionando comissão de vendas:");
        func.adicionarBonus(new ComissaoVendas(0.05));
        func.mostrarDetalhes();
        
        System.out.println("\n=== BENEFÍCIOS DA COMPOSIÇÃO ===");
        System.out.println("1. ✓ Sem explosão de classes (sem hierarquia)");
        System.out.println("2. ✓ Bônus podem ser adicionados/removidos em runtime");
        System.out.println("3. ✓ Fácil adicionar novos tipos de bônus");
        System.out.println("4. ✓ Cada calculadora é testável isoladamente");
        System.out.println("5. ✓ Baixo acoplamento e alta flexibilidade");
        System.out.println("6. ✓ Combine múltiplos bônus facilmente");
        
        System.out.println("\n=== COMPARAÇÃO COM HERANÇA ===");
        System.out.println("Herança: N bônus = 2^N classes (explosão combinatória)");
        System.out.println("Composição: N bônus = N classes (linear)");
        System.out.println("\nPara 3 tipos de bônus:");
        System.out.println("  Herança: 8 classes necessárias");
        System.out.println("  Composição: 3 classes necessárias");
    }
}
