import java.util.ArrayList;
import java.util.List;

/**
 * Departamento — o GRUPO da agregação: junta colaboradores que existem sozinhos.
 */
public class Departamento {
    private final String nome;

    public Departamento(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA colaboradores que já existem por conta própria.
    private final List<Colaborador> colaboradores = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarColaborador(Colaborador item) {
        if (item == null) throw new IllegalArgumentException("Colaborador é obrigatório");
        if (!colaboradores.contains(item)) colaboradores.add(item);
    }

    public List<Colaborador> getColaboradores() { return List.copyOf(colaboradores); }
}
