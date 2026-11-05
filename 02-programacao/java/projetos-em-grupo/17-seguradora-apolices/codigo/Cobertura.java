/**
 * Cobertura — PARTE de uma COMPOSIÇÃO (◆) com Apolice.
 * Cada cobertura só existe dentro da apólice e some junto se a apólice for cancelada.
 * Repare: o construtor NÃO é público — só quem está no mesmo pacote (o todo) cria.
 */
public class Cobertura {
    private final String descricao;
    private final double valor;

    Cobertura(String descricao, double valor) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("descrição é obrigatória");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo");
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
}
