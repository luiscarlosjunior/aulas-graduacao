/**
 * Ponto — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: horasTrabalhadas nunca é negativo e o estado só muda por operações.
 */
public class Ponto {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"aberto", "fechado"};

    // ASSOCIAÇÃO: cada Ponto conhece o Colaborador ligado a ela.
    private final Colaborador colaborador;
    private final double horasTrabalhadas;   // INVARIANTE: horasTrabalhadas >= 0
    private String estado;

    public Ponto(Colaborador colaborador, double horasTrabalhadas) {
        // PRÉ-CONDIÇÕES
        if (colaborador == null) throw new IllegalArgumentException("Colaborador é obrigatório");
        if (horasTrabalhadas < 0) throw new IllegalArgumentException("horasTrabalhadas não pode ser negativo: " + horasTrabalhadas);
        // só depois de validar TUDO, guardamos os valores
        this.colaborador = colaborador;
        this.horasTrabalhadas = horasTrabalhadas;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa. */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (estado.equals("ajustado")) throw new IllegalStateException("Ponto está ajustado: não avança mais");
        if (emEtapaFinal()) throw new IllegalStateException("Ponto já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    /** Caminho alternativo: vai para "ajustado". */
    public void marcarAjustado() {
        // PRÉ-CONDIÇÃO: o que já terminou (ou já está "ajustado") não muda mais
        if (emEtapaFinal()) throw new IllegalStateException("não dá: Ponto já está " + estado);
        if (estado.equals("ajustado")) throw new IllegalStateException("Ponto já está ajustado");
        estado = "ajustado";   // PÓS-CONDIÇÃO
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Colaborador getColaborador() { return colaborador; }
    public double getHorasTrabalhadas() { return horasTrabalhadas; }
    public String getEstado() { return estado; }
}
