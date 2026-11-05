import java.util.ArrayList;
import java.util.List;

/**
 * Consulta — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valor nunca é negativo e o estado só muda por operações.
 * MÁQUINA DE ESTADOS: marcada → realizada; ou cancelada.
 */
public class Consulta {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"marcada", "realizada"};

    // ASSOCIAÇÃO: cada Consulta conhece o Paciente ligado a ela.
    private final Paciente paciente;
    private final double valor;   // INVARIANTE: valor >= 0
    private String estado;

    public Consulta(Paciente paciente, double valor) {
        // PRÉ-CONDIÇÕES
        if (paciente == null) throw new IllegalArgumentException("Paciente é obrigatório");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo: " + valor);
        // só depois de validar TUDO, guardamos os valores
        this.paciente = paciente;
        this.valor = valor;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa (marcada → realizada). */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (estado.equals("cancelada")) throw new IllegalStateException("Consulta está cancelada: não avança mais");
        if (emEtapaFinal()) throw new IllegalStateException("Consulta já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    /** Caminho alternativo da máquina de estados: vai para "cancelada". */
    public void cancelar() {
        // PRÉ-CONDIÇÃO: o que já terminou (ou já está "cancelada") não muda mais
        if (emEtapaFinal()) throw new IllegalStateException("não dá: Consulta já está " + estado);
        if (estado.equals("cancelada")) throw new IllegalStateException("Consulta já está cancelada");
        estado = "cancelada";   // PÓS-CONDIÇÃO
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Paciente getPaciente() { return paciente; }
    public double getValor() { return valor; }
    public String getEstado() { return estado; }

    // COMPOSIÇÃO (◆): cada Prescricao nasce dentro desta classe e morre com ela.
    private final List<Prescricao> prescricoes = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public Prescricao adicionarPrescricao(String descricao, double valor) {
        Prescricao parte = new Prescricao(descricao, valor);
        prescricoes.add(parte);
        return parte;
    }

    public List<Prescricao> getPrescricoes() { return List.copyOf(prescricoes); }
}
