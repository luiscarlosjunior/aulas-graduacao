/**
 * Pet — um item que existe por conta própria e pode ser agrupado em Cliente.
 */
public class Pet {
    private final String nome;

    public Pet(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }
}
