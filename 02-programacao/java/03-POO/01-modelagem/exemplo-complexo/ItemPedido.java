/**
 * ENTIDADE: ItemPedido (uma linha do pedido)
 *
 * Une uma Pizza a uma quantidade. Note a diferença de relacionamentos:
 *  - ItemPedido -> Pizza  : AGREGAÇÃO (a pizza vem de fora, do cardápio)
 *  - Pedido     -> ItemPedido : COMPOSIÇÃO (o item só existe dentro do pedido)
 *
 * A responsabilidade do item é saber calcular o próprio subtotal.
 */
public class ItemPedido {
    private Pizza pizza;      // agregação: recebida de fora
    private int quantidade;

    public ItemPedido(Pizza pizza, int quantidade) {
        this.pizza = pizza;
        this.quantidade = quantidade;
    }

    public double subtotal() {
        return pizza.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return quantidade + "x " + pizza + " = R$ " + String.format("%.2f", subtotal());
    }
}
