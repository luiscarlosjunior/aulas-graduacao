/**
 * Exemplo de Encapsulate What Varies
 * Encapsule aspectos que variam do código
 * 
 * BENEFÍCIO: Mudanças em aspectos variáveis não afetam código estável.
 * Facilita adição de novas variações.
 */
import java.util.*;

// ✅ Encapsula variação: Cálculo de frete
interface CalculadoraFrete {
    double calcular(double valorPedido, String destino);
    String getDescricao();
}

class FreteNormal implements CalculadoraFrete {
    @Override
    public double calcular(double valorPedido, String destino) {
        return 10.0; // Frete fixo
    }
    
    @Override
    public String getDescricao() {
        return "Frete Normal";
    }
}

class FreteExpress implements CalculadoraFrete {
    @Override
    public double calcular(double valorPedido, String destino) {
        return 25.0;
    }
    
    @Override
    public String getDescricao() {
        return "Frete Express";
    }
}

class FreteSedex implements CalculadoraFrete {
    @Override
    public double calcular(double valorPedido, String destino) {
        return 20.0;
    }
    
    @Override
    public String getDescricao() {
        return "Frete Sedex";
    }
}

class FreteGratis implements CalculadoraFrete {
    @Override
    public double calcular(double valorPedido, String destino) {
        // Variação: Grátis acima de R$ 200
        return valorPedido >= 200.0 ? 0.0 : 10.0;
    }
    
    @Override
    public String getDescricao() {
        return "Frete Grátis (acima de R$ 200)";
    }
}

// ✅ Adicionar novo tipo é criar nova classe - não modifica existentes!
class FreteInternacional implements CalculadoraFrete {
    @Override
    public double calcular(double valorPedido, String destino) {
        return 100.0;
    }
    
    @Override
    public String getDescricao() {
        return "Frete Internacional";
    }
}

// ✅ Encapsula variação: Cálculo de desconto
interface CalculadoraDesconto {
    double calcular(double subtotal);
    String getDescricao();
}

class DescontoPorValor implements CalculadoraDesconto {
    @Override
    public double calcular(double subtotal) {
        if (subtotal > 500) {
            return subtotal * 0.10; // 10%
        } else if (subtotal > 200) {
            return subtotal * 0.05; // 5%
        }
        return 0;
    }
    
    @Override
    public String getDescricao() {
        return "Desconto por Valor";
    }
}

class DescontoBlackFriday implements CalculadoraDesconto {
    @Override
    public double calcular(double subtotal) {
        return subtotal * 0.30; // 30%
    }
    
    @Override
    public String getDescricao() {
        return "Black Friday (30%)";
    }
}

// ✅ Item do pedido
class Item {
    private String nome;
    private double preco;
    
    public Item(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    
    public double getPreco() { return preco; }
    public String getNome() { return nome; }
}

// ✅ Pedido usa aspectos variáveis encapsulados
class Pedido {
    private List<Item> itens;
    private String destino;
    private CalculadoraFrete calculadoraFrete;
    private CalculadoraDesconto calculadoraDesconto;
    
    public Pedido(String destino, CalculadoraFrete frete, CalculadoraDesconto desconto) {
        this.itens = new ArrayList<>();
        this.destino = destino;
        this.calculadoraFrete = frete;
        this.calculadoraDesconto = desconto;
    }
    
    public void adicionarItem(Item item) {
        itens.add(item);
    }
    
    public double calcularTotal() {
        double subtotal = calcularSubtotal();
        double frete = calculadoraFrete.calcular(subtotal, destino);
        double desconto = calculadoraDesconto.calcular(subtotal);
        
        return subtotal + frete - desconto;
    }
    
    private double calcularSubtotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getPreco();
        }
        return total;
    }
    
    public void mostrarResumo() {
        double subtotal = calcularSubtotal();
        double frete = calculadoraFrete.calcular(subtotal, destino);
        double desconto = calculadoraDesconto.calcular(subtotal);
        double total = calcularTotal();
        
        System.out.println("\n--- RESUMO DO PEDIDO ---");
        System.out.println("Itens:");
        for (Item item : itens) {
            System.out.println("  - " + item.getNome() + ": R$ " + item.getPreco());
        }
        System.out.println("Subtotal: R$ " + subtotal);
        System.out.println(calculadoraFrete.getDescricao() + ": R$ " + frete);
        System.out.println(calculadoraDesconto.getDescricao() + ": -R$ " + desconto);
        System.out.println("TOTAL: R$ " + total);
    }
    
    // ✅ Pode trocar estratégias em runtime
    public void setCalculadoraFrete(CalculadoraFrete calculadoraFrete) {
        this.calculadoraFrete = calculadoraFrete;
    }
    
    public void setCalculadoraDesconto(CalculadoraDesconto calculadoraDesconto) {
        this.calculadoraDesconto = calculadoraDesconto;
    }
}

public class EncapsuleOQueVaria {
    public static void main(String[] args) {
        System.out.println("=== ENCAPSULATE WHAT VARIES ===");
        
        // Criar pedido com frete normal e desconto por valor
        Pedido pedido1 = new Pedido("SP", new FreteNormal(), new DescontoPorValor());
        pedido1.adicionarItem(new Item("Notebook", 3000.0));
        pedido1.adicionarItem(new Item("Mouse", 50.0));
        
        System.out.println("\nPedido 1:");
        pedido1.mostrarResumo();
        
        // Criar pedido com frete express e Black Friday
        Pedido pedido2 = new Pedido("RJ", new FreteExpress(), new DescontoBlackFriday());
        pedido2.adicionarItem(new Item("Teclado", 200.0));
        pedido2.adicionarItem(new Item("Monitor", 800.0));
        
        System.out.println("\nPedido 2:");
        pedido2.mostrarResumo();
        
        // Criar pedido com frete grátis
        Pedido pedido3 = new Pedido("MG", new FreteGratis(), new DescontoPorValor());
        pedido3.adicionarItem(new Item("Cadeira Gamer", 1500.0));
        
        System.out.println("\nPedido 3:");
        pedido3.mostrarResumo();
        
        System.out.println("\n=== BENEFÍCIOS ===");
        System.out.println("1. ✓ Mudanças em cálculo de frete isoladas");
        System.out.println("2. ✓ Novos tipos de frete não afetam Pedido");
        System.out.println("3. ✓ Estratégias podem ser trocadas em runtime");
        System.out.println("4. ✓ Cada calculadora testável isoladamente");
        System.out.println("5. ✓ Baixo acoplamento, alta coesão");
        System.out.println("6. ✓ Fácil adicionar novas variações");
    }
}
