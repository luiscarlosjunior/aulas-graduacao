/**
 * Produto — um item que existe por conta própria e pode ser agrupado em Categoria.
 */
public class Produto {
    private final String nome;

    public Produto(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }
}
