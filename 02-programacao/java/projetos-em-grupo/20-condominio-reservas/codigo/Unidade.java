/**
 * Unidade — um item que existe por conta própria e pode ser agrupado em Bloco.
 */
public class Unidade {
    private final String nome;

    public Unidade(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }
}
