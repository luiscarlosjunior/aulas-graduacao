/**
 * Pedido — PARTE de uma COMPOSIÇÃO (◆) com Comanda.
 * Cada pedido pertence a uma comanda e some junto se a comanda for cancelada.
 * Repare: o construtor NÃO é público — só quem está no mesmo pacote (o todo) cria.
 */
public class Pedido {
    private final String descricao;
    private final double valor;

    Pedido(String descricao, double valor) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("descrição é obrigatória");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo");
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
}
