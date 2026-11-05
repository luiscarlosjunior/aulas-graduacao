import java.util.ArrayList;
import java.util.List;

/**
 * Reserva — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valorTaxa nunca é negativo e o estado só muda por operações.
 * MÁQUINA DE ESTADOS: solicitada → confirmada; ou cancelada.
 */
public class Reserva {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"solicitada", "confirmada"};

    // ASSOCIAÇÃO: cada Reserva conhece o Morador ligado a ela.
    private final Morador morador;
    private final double valorTaxa;   // INVARIANTE: valorTaxa >= 0
    private String estado;

    public Reserva(Morador morador, double valorTaxa) {
        // PRÉ-CONDIÇÕES
        if (morador == null) throw new IllegalArgumentException("Morador é obrigatório");
        if (valorTaxa < 0) throw new IllegalArgumentException("valorTaxa não pode ser negativo: " + valorTaxa);
        // só depois de validar TUDO, guardamos os valores
        this.morador = morador;
        this.valorTaxa = valorTaxa;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa (solicitada → confirmada). */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (estado.equals("cancelada")) throw new IllegalStateException("Reserva está cancelada: não avança mais");
        if (emEtapaFinal()) throw new IllegalStateException("Reserva já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    /** Caminho alternativo da máquina de estados: vai para "cancelada". */
    public void cancelar() {
        // PRÉ-CONDIÇÃO: o que já terminou (ou já está "cancelada") não muda mais
        if (emEtapaFinal()) throw new IllegalStateException("não dá: Reserva já está " + estado);
        if (estado.equals("cancelada")) throw new IllegalStateException("Reserva já está cancelada");
        estado = "cancelada";   // PÓS-CONDIÇÃO
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Morador getMorador() { return morador; }
    public double getValorTaxa() { return valorTaxa; }
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
