import java.util.ArrayList;
import java.util.List;

/**
 * Sessao — o GRUPO da agregação: junta assentos que existem sozinhos.
 */
public class Sessao {
    private final String nome;

    public Sessao(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA assentos que já existem por conta própria.
    private final List<Assento> assentos = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarAssento(Assento item) {
        if (item == null) throw new IllegalArgumentException("Assento é obrigatório");
        if (!assentos.contains(item)) assentos.add(item);
    }

    public List<Assento> getAssentos() { return List.copyOf(assentos); }
}
