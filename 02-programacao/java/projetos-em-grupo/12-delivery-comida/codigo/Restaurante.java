import java.util.ArrayList;
import java.util.List;

/**
 * Restaurante — o GRUPO da agregação: junta pratos que existem sozinhos.
 */
public class Restaurante {
    private final String nome;

    public Restaurante(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA pratos que já existem por conta própria.
    private final List<Prato> pratos = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarPrato(Prato item) {
        if (item == null) throw new IllegalArgumentException("Prato é obrigatório");
        if (!pratos.contains(item)) pratos.add(item);
    }

    public List<Prato> getPratos() { return List.copyOf(pratos); }
}
