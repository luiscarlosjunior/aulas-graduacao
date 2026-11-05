/**
 * Nota — PARTE de uma COMPOSIÇÃO (◆) com Matricula.
 * Cada nota pertence a uma matrícula e some junto se a matrícula for cancelada.
 * Repare: o construtor NÃO é público — só quem está no mesmo pacote (o todo) cria.
 */
public class Nota {
    private final String descricao;
    private final double valor;

    Nota(String descricao, double valor) {
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("descrição é obrigatória");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo");
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
}
