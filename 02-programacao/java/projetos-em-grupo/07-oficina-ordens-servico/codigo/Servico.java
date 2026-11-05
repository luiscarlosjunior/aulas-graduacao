/**
 * Servico — um item que existe por conta própria e pode ser agrupado em CatalogoServicos.
 */
public class Servico {
    private final String nome;

    public Servico(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }
}
