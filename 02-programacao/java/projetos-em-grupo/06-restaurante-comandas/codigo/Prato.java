/**
 * Prato — um item que existe por conta própria e pode ser agrupado em Cardapio.
 */
public class Prato {
    private final String nome;

    public Prato(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }
}
