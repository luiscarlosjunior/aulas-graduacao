import java.util.ArrayList;
import java.util.List;

/**
 * Ticket — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valor nunca é negativo e o estado só muda por operações.
 */
public class Ticket {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"aberto", "em uso", "fechado", "pago"};

    // ASSOCIAÇÃO: cada Ticket conhece o Cliente ligado a ela.
    private final Cliente cliente;
    private final double valor;   // INVARIANTE: valor >= 0
    private String estado;

    public Ticket(Cliente cliente, double valor) {
        // PRÉ-CONDIÇÕES
        if (cliente == null) throw new IllegalArgumentException("Cliente é obrigatório");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo: " + valor);
        // só depois de validar TUDO, guardamos os valores
        this.cliente = cliente;
        this.valor = valor;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa. */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (emEtapaFinal()) throw new IllegalStateException("Ticket já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Cliente getCliente() { return cliente; }
    public double getValor() { return valor; }
    public String getEstado() { return estado; }

    // COMPOSIÇÃO (◆): cada Cobranca nasce dentro desta classe e morre com ela.
    private final List<Cobranca> cobrancas = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public Cobranca adicionarCobranca(String descricao, double valor) {
        Cobranca parte = new Cobranca(descricao, valor);
        cobrancas.add(parte);
        return parte;
    }

    public List<Cobranca> getCobrancas() { return List.copyOf(cobrancas); }
}
