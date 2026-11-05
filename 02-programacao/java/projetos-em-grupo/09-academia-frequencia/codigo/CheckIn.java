/**
 * CheckIn — PARTE de uma COMPOSIÇÃO (◆) com Aluno.
 * Cada check-in pertence a um aluno e some junto se o cadastro for removido.
 * Repare: o construtor NÃO é público — só quem está no mesmo pacote (o todo) cria.
 */
public class CheckIn {
    private final String descricao;
    private final double valor;

    CheckIn(String descricao, double valor) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("descrição é obrigatória");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo");
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
}
