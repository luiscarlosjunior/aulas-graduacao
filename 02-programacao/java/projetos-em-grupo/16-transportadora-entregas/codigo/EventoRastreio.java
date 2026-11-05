/**
 * EventoRastreio — PARTE de uma COMPOSIÇÃO (◆) com Encomenda.
 * Cada evento de rastreio pertence à encomenda e some junto se ela for removida.
 * Repare: o construtor NÃO é público — só quem está no mesmo pacote (o todo) cria.
 */
public class EventoRastreio {
    private final String descricao;
    private final double valor;

    EventoRastreio(String descricao, double valor) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("descrição é obrigatória");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo");
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
}
