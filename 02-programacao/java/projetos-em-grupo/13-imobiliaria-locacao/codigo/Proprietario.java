import java.util.ArrayList;
import java.util.List;

/**
 * Proprietario — o GRUPO da agregação: junta imoveis que existem sozinhos.
 */
public class Proprietario {
    private final String nome;

    public Proprietario(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA imoveis que já existem por conta própria.
    private final List<Imovel> imoveis = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarImovel(Imovel item) {
        if (item == null) throw new IllegalArgumentException("Imovel é obrigatório");
        if (!imoveis.contains(item)) imoveis.add(item);
    }

    public List<Imovel> getImoveis() { return List.copyOf(imoveis); }
}
