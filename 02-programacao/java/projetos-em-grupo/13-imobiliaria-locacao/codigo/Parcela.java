/**
 * Parcela — PARTE de uma COMPOSIÇÃO (◆) com Contrato.
 * Cada parcela nasce dentro do contrato e some junto se ele for cancelado.
 * Repare: o construtor NÃO é público — só quem está no mesmo pacote (o todo) cria.
 */
public class Parcela {
    private final String descricao;
    private final double valor;

    Parcela(String descricao, double valor) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("descrição é obrigatória");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo");
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
}
