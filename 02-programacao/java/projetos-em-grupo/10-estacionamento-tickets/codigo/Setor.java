import java.util.ArrayList;
import java.util.List;

/**
 * Setor — o GRUPO da agregação: junta vagas que existem sozinhos.
 */
public class Setor {
    private final String nome;

    public Setor(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA vagas que já existem por conta própria.
    private final List<Vaga> vagas = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarVaga(Vaga item) {
        if (item == null) throw new IllegalArgumentException("Vaga é obrigatório");
        if (!vagas.contains(item)) vagas.add(item);
    }

    public List<Vaga> getVagas() { return List.copyOf(vagas); }
}
