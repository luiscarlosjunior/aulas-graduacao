import java.util.ArrayList;
import java.util.List;

/**
 * Encomenda — classe ABSTRATA (pilar: ABSTRAÇÃO).
 * É a base comum de EncomendaComum e EncomendaExpressa: guarda o que os dois têm em comum.
 * Não existe "um Encomenda genérico" — por isso não se faz `new Encomenda(...)`.
 */
public abstract class Encomenda {
    // ENCAPSULAMENTO: atributos privados; só leitura por getters (não há setters).
    private final String codigo;
    private final double peso;

    protected Encomenda(String codigo, double peso) {
        // PRÉ-CONDIÇÕES: nenhum objeto nasce com dado inválido
        if (codigo == null || codigo.isBlank()) throw new IllegalArgumentException("codigo é obrigatório");
        if (peso < 0) throw new IllegalArgumentException("peso não pode ser negativo");
        this.codigo = codigo;
        this.peso = peso;
    }

    public String getCodigo() { return codigo; }
    public double getPeso() { return peso; }

    /**
     * POLIMORFISMO: a mesma pergunta, resposta diferente por tipo.
     * Significado: prazo de entrega em dias.
     */
    public abstract int prazoEntregaDias();

    // COMPOSIÇÃO (◆): cada EventoRastreio nasce dentro desta classe e morre com ela.
    private final List<EventoRastreio> eventosRastreio = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public EventoRastreio adicionarEventoRastreio(String descricao, double valor) {
        EventoRastreio parte = new EventoRastreio(descricao, valor);
        eventosRastreio.add(parte);
        return parte;
    }

    public List<EventoRastreio> getEventosRastreio() { return List.copyOf(eventosRastreio); }
}
