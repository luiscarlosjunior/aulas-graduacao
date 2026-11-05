import java.util.ArrayList;
import java.util.List;

/**
 * Pacote — o TODO da composição com ItemPacote (as partes nascem aqui dentro). o GRUPO da agregação: junta destinos que existem sozinhos.
 */
public class Pacote {
    private final String nome;

    public Pacote(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("nome é obrigatório");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // COMPOSIÇÃO (◆): cada ItemPacote nasce dentro desta classe e morre com ela.
    private final List<ItemPacote> itensPacote = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public ItemPacote adicionarItemPacote(String descricao, double valor) {
        ItemPacote parte = new ItemPacote(descricao, valor);
        itensPacote.add(parte);
        return parte;
    }

    public List<ItemPacote> getItensPacote() { return List.copyOf(itensPacote); }

    // AGREGAÇÃO (◇): só AGRUPA destinos que já existem por conta própria.
    private final List<Destino> destinos = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarDestino(Destino item) {
        if (item == null) throw new IllegalArgumentException("Destino é obrigatório");
        if (!destinos.contains(item)) destinos.add(item);
    }

    public List<Destino> getDestinos() { return List.copyOf(destinos); }
}
