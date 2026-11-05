import java.util.ArrayList;
import java.util.List;

/**
 * CatalogoServicos — o GRUPO da agregação: junta servicos que existem sozinhos.
 */
public class CatalogoServicos {
    private final String nome;

    public CatalogoServicos(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // AGREGAÇÃO (◇): só AGRUPA servicos que já existem por conta própria.
    private final List<Servico> servicos = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarServico(Servico item) {
        if (item == null) throw new IllegalArgumentException("Servico é obrigatório");
        if (!servicos.contains(item)) servicos.add(item);
    }

    public List<Servico> getServicos() { return List.copyOf(servicos); }
}
