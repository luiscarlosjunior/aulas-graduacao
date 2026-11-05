/**
 * Destino — um item que existe por conta própria e pode ser agrupado em Pacote.
 */
public class Destino {
    private final String nome;

    public Destino(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }
}
