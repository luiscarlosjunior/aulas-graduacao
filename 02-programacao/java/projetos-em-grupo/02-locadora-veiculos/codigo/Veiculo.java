/**
 * Veiculo — um item que existe por conta própria e pode ser agrupado em Categoria.
 */
public class Veiculo {
    private final String nome;

    public Veiculo(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }
}
