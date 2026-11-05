/**
 * Assento — um item que existe por conta própria e pode ser agrupado em Sessao.
 */
public class Assento {
    private final String nome;

    public Assento(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }
}
