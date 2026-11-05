import java.util.ArrayList;
import java.util.List;

/**
 * Matricula — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valorMensalidade nunca é negativo e o estado só muda por operações.
 */
public class Matricula {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"ativa", "trancada", "concluída"};

    // ASSOCIAÇÃO: cada Matricula conhece o Aluno ligado a ela.
    private final Aluno aluno;
    private final double valorMensalidade;   // INVARIANTE: valorMensalidade >= 0
    private String estado;

    public Matricula(Aluno aluno, double valorMensalidade) {
        // PRÉ-CONDIÇÕES
        if (aluno == null) throw new IllegalArgumentException("Aluno é obrigatório");
        if (valorMensalidade < 0) throw new IllegalArgumentException("valorMensalidade não pode ser negativo: " + valorMensalidade);
        // só depois de validar TUDO, guardamos os valores
        this.aluno = aluno;
        this.valorMensalidade = valorMensalidade;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa. */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (estado.equals("cancelada")) throw new IllegalStateException("Matricula está cancelada: não avança mais");
        if (emEtapaFinal()) throw new IllegalStateException("Matricula já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    /** Caminho alternativo: vai para "cancelada". */
    public void cancelar() {
        // PRÉ-CONDIÇÃO: o que já terminou (ou já está "cancelada") não muda mais
        if (emEtapaFinal()) throw new IllegalStateException("não dá: Matricula já está " + estado);
        if (estado.equals("cancelada")) throw new IllegalStateException("Matricula já está cancelada");
        estado = "cancelada";   // PÓS-CONDIÇÃO
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Aluno getAluno() { return aluno; }
    public double getValorMensalidade() { return valorMensalidade; }
    public String getEstado() { return estado; }

    // COMPOSIÇÃO (◆): cada Nota nasce dentro desta classe e morre com ela.
    private final List<Nota> notas = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public Nota adicionarNota(String descricao, double valor) {
        Nota parte = new Nota(descricao, valor);
        notas.add(parte);
        return parte;
    }

    public List<Nota> getNotas() { return List.copyOf(notas); }
}
