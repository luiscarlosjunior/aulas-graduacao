/**
 * ItemOS — PARTE de uma COMPOSIÇÃO (◆) com OrdemServico.
 * Cada item (serviço/peça) da OS só existe dentro dela e some junto se a OS for cancelada.
 * Repare: o construtor NÃO é público — só quem está no mesmo pacote (o todo) cria.
 */
public class ItemOS {
    private final String descricao;
    private final double valor;

    ItemOS(String descricao, double valor) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("descrição é obrigatória");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo");
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
}
