import java.util.ArrayList;
import java.util.List;

/**
 * Cliente — o GRUPO da agregação: junta apolices que existem sozinhos.
 */
public class Cliente {
    private final String nome;

    public Cliente(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA apolices que já existem por conta própria.
    private final List<Apolice> apolices = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarApolice(Apolice item) {
        if (item == null) throw new IllegalArgumentException("Apolice é obrigatório");
        if (!apolices.contains(item)) apolices.add(item);
    }

    public List<Apolice> getApolices() { return List.copyOf(apolices); }
}
