import java.util.ArrayList;
import java.util.List;

/**
 * Reserva — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valorDiaria nunca é negativo e o estado só muda por operações.
 * MÁQUINA DE ESTADOS: criada → confirmada → finalizada; ou cancelada.
 */
public class Reserva {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"criada", "confirmada", "finalizada"};

    // ASSOCIAÇÃO: cada Reserva conhece o Hospede ligado a ela.
    private final Hospede hospede;
    private final double valorDiaria;   // INVARIANTE: valorDiaria >= 0
    private String estado;

    public Reserva(Hospede hospede, double valorDiaria) {
        // PRÉ-CONDIÇÕES
        if (hospede == null) throw new IllegalArgumentException("Hospede é obrigatório");
        if (valorDiaria < 0) throw new IllegalArgumentException("valorDiaria não pode ser negativo: " + valorDiaria);
        // só depois de validar TUDO, guardamos os valores
        this.hospede = hospede;
        this.valorDiaria = valorDiaria;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa (criada → confirmada → finalizada). */
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

    public Hospede getHospede() { return hospede; }
    public double getValorDiaria() { return valorDiaria; }
    public String getEstado() { return estado; }

    // COMPOSIÇÃO (◆): cada Diaria nasce dentro desta classe e morre com ela.
    private final List<Diaria> diarias = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public Diaria adicionarDiaria(String descricao, double valor) {
        Diaria parte = new Diaria(descricao, valor);
        diarias.add(parte);
        return parte;
    }

    public List<Diaria> getDiarias() { return List.copyOf(diarias); }

    // AGREGAÇÃO (◇): só AGRUPA hospedes que já existem por conta própria.
    private final List<Hospede> hospedes = new ArrayList<>();

    /** Recebe um objeto PRONTO (não faz `new`) — isso é agregação. */
    public void adicionarHospede(Hospede item) {
        if (item == null) throw new IllegalArgumentException("Hospede é obrigatório");
        if (!hospedes.contains(item)) hospedes.add(item);
    }

    public List<Hospede> getHospedes() { return List.copyOf(hospedes); }
}
