/**
 * ItemPacote — PARTE de uma COMPOSIÇÃO (◆) com Pacote.
 * Cada item (voo, hotel, passeio) só existe dentro do pacote e some junto com ele.
 * Repare: o construtor NÃO é público — só quem está no mesmo pacote (o todo) cria.
 */
public class ItemPacote {
    private final String descricao;
    private final double valor;

    ItemPacote(String descricao, double valor) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("descrição é obrigatória");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo");
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
}
