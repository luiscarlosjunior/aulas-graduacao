/**
 * Produto — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de ProdutoComum e MedicamentoControlado: guarda o que os dois têm em comum.
 * Não existe "um Produto genérico" — por isso não se faz `new Produto(...)`.
 */
public abstract class Produto {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String nome;
    private final double preco;
    private final int estoque;

    protected Produto(String nome, double preco, int estoque) {
        // PRÉ-CONDIÇÕES: nenhum objeto nasce com dado inválido
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        if (preco < 0) throw new IllegalArgumentException("preco não pode ser negativo");
        if (estoque < 0) throw new IllegalArgumentException("estoque não pode ser negativo");
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }

    /**
     * POLIMORFISMO: a mesma pergunta, resposta diferente por tipo.
     * Significado: true se o produto exige receita.
     */
    public abstract boolean exigeReceita();
}
