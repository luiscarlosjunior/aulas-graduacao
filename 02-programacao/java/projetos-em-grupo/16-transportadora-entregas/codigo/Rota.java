import java.util.ArrayList;
import java.util.List;

/**
 * Rota — o GRUPO da agregação: junta encomendas que existem sozinhos.
 */
public class Rota {
    private final String nome;

    public Rota(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA encomendas que já existem por conta própria.
    private final List<Encomenda> encomendas = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarEncomenda(Encomenda item) {
        if (item == null) throw new IllegalArgumentException("Encomenda é obrigatório");
        if (!encomendas.contains(item)) encomendas.add(item);
    }

    public List<Encomenda> getEncomendas() { return List.copyOf(encomendas); }
}
