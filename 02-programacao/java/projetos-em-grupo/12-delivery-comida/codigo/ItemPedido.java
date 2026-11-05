/**
 * ItemPedido — PARTE de uma COMPOSIÇÃO (◆) com Pedido.
 * Cada item pertence a um pedido e some junto se o pedido for cancelado.
 * Repare: o construtor NÃO é público — só quem está no mesmo pacote (o todo) cria.
 */
public class ItemPedido {
    private final String descricao;
    private final double valor;

    ItemPedido(String descricao, double valor) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("descrição é obrigatória");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo");
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
}
