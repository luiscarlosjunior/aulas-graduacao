/**
 * Diaria — PARTE de uma COMPOSIÇÃO (◆) com Reserva.
 * Cada diária pertence a uma única reserva e some junto se a reserva for cancelada.
 * Repare: o construtor NÃO é público — só quem está no mesmo pacote (o todo) cria.
 */
public class Diaria {
    private final String descricao;
    private final double valor;

    Diaria(String descricao, double valor) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("descrição é obrigatória");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo");
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
}
