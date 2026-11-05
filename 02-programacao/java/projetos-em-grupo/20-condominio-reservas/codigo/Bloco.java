import java.util.ArrayList;
import java.util.List;

/**
 * Bloco — o GRUPO da agregação: junta unidades que existem sozinhos.
 */
public class Bloco {
    private final String nome;

    public Bloco(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA unidades que já existem por conta própria.
    private final List<Unidade> unidades = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarUnidade(Unidade item) {
        if (item == null) throw new IllegalArgumentException("Unidade é obrigatório");
        if (!unidades.contains(item)) unidades.add(item);
    }

    public List<Unidade> getUnidades() { return List.copyOf(unidades); }
}
