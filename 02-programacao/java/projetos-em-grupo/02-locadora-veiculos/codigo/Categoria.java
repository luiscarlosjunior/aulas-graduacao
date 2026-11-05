import java.util.ArrayList;
import java.util.List;

/**
 * Categoria — o GRUPO da agregação: junta veiculos que existem sozinhos.
 */
public class Categoria {
    private final String nome;

    public Categoria(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA veiculos que já existem por conta própria.
    private final List<Veiculo> veiculos = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarVeiculo(Veiculo item) {
        if (item == null) throw new IllegalArgumentException("Veiculo é obrigatório");
        if (!veiculos.contains(item)) veiculos.add(item);
    }

    public List<Veiculo> getVeiculos() { return List.copyOf(veiculos); }
}
