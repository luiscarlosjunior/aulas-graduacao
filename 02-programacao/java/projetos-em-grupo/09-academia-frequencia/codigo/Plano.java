/**
 * Plano — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valorMensalidade nunca é negativo e o estado só muda por operações.
 */
public class Plano {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"ativo", "vencido", "cancelado"};

    // ASSOCIAÇÃO: cada Plano conhece o Aluno ligado a ela.
    private final Aluno aluno;
    private final double valorMensalidade;   // INVARIANTE: valorMensalidade >= 0
    private String estado;

    public Plano(Aluno aluno, double valorMensalidade) {
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
        if (emEtapaFinal()) throw new IllegalStateException("Plano já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Aluno getAluno() { return aluno; }
    public double getValorMensalidade() { return valorMensalidade; }
    public String getEstado() { return estado; }
}
