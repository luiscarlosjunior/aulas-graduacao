/**
 * Vaga — um item que existe por conta própria e pode ser agrupado em Setor.
 */
public class Vaga {
    private final String nome;

    public Vaga(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }
}
