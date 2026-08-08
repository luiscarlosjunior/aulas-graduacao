/**
 * ENTIDADE: Pizza (um item do cardápio)
 *
 * A Pizza existe de forma independente — ela é do cardápio da pizzaria, não
 * "nasce e morre" dentro de um pedido. Por isso o relacionamento com ItemPedido
 * é AGREGAÇÃO (a mesma pizza do cardápio pode aparecer em vários pedidos).
 */
public class Pizza {
    private String sabor;
    private String tamanho;
    private double preco;

    public Pizza(String sabor, String tamanho, double preco) {
        this.sabor = sabor;
        this.tamanho = tamanho;
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Pizza " + sabor + " (" + tamanho + ")";
    }
}
