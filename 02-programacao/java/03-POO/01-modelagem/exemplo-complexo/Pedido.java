import java.util.ArrayList;
import java.util.List;

/**
 * ENTIDADE CENTRAL: Pedido
 *
 * Reúne todos os relacionamentos do modelo:
 *  - Pedido É FEITO DE ItemPedidos ....... COMPOSIÇÃO (cria os itens internamente)
 *  - Pedido É DE UM Cliente .............. ASSOCIAÇÃO (conhece o cliente)
 *
 * Responsabilidades do pedido: adicionar itens e calcular o total (já aplicando
 * o desconto do cliente — repare no polimorfismo em ação).
 */
public class Pedido {
    private Cliente cliente;              // associação
    private List<ItemPedido> itens;       // composição

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    /**
     * O item é CRIADO dentro do pedido (composição): ele nasce e morre com o pedido.
     */
    public void adicionar(Pizza pizza, int quantidade) {
        itens.add(new ItemPedido(pizza, quantidade));
    }

    public double calcularTotal() {
        double soma = 0;
        for (ItemPedido item : itens) {
            soma += item.subtotal();
        }
        // Polimorfismo: se for VIP, calcularDesconto aplica 10%; se comum, retorna cheio.
        return cliente.calcularDesconto(soma);
    }

    public void imprimirNota() {
        System.out.println("--------------------------------------");
        System.out.println("Pedido de: " + cliente.getNome());
        for (ItemPedido item : itens) {
            System.out.println("  " + item);
        }
        System.out.println("TOTAL (com desconto aplicável): R$ "
                + String.format("%.2f", calcularTotal()));
        System.out.println("--------------------------------------");
    }
}
