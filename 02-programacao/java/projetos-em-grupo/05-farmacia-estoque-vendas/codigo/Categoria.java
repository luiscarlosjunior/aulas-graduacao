import java.util.ArrayList;
import java.util.List;

/**
 * Categoria — o GRUPO da agregação: junta produtos que existem sozinhos.
 */
public class Categoria {
    private final String nome;

    public Categoria(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA produtos que já existem por conta própria.
    private final List<Produto> produtos = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarProduto(Produto item) {
        if (item == null) throw new IllegalArgumentException("Produto é obrigatório");
        if (!produtos.contains(item)) produtos.add(item);
    }

    public List<Produto> getProdutos() { return List.copyOf(produtos); }
}
