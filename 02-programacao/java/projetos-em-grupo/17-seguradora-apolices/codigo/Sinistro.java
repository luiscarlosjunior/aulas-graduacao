/**
 * Sinistro — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valorIndenizacao nunca é negativo e o estado só muda por operações.
 */
public class Sinistro {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"aberto", "em análise", "aprovado", "pago"};

    // ASSOCIAÇÃO: cada Sinistro conhece o Apolice ligado a ela.
    private final Apolice apolice;
    private final double valorIndenizacao;   // INVARIANTE: valorIndenizacao >= 0
    private String estado;

    public Sinistro(Apolice apolice, double valorIndenizacao) {
        // PRÉ-CONDIÇÕES
        if (apolice == null) throw new IllegalArgumentException("Apolice é obrigatório");
        if (valorIndenizacao < 0) throw new IllegalArgumentException("valorIndenizacao não pode ser negativo: " + valorIndenizacao);
        // só depois de validar TUDO, guardamos os valores
        this.apolice = apolice;
        this.valorIndenizacao = valorIndenizacao;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa. */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (estado.equals("negado")) throw new IllegalStateException("Sinistro está negado: não avança mais");
        if (emEtapaFinal()) throw new IllegalStateException("Sinistro já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    /** Caminho alternativo: vai para "negado". */
    public void marcarNegado() {
        // PRÉ-CONDIÇÃO: o que já terminou (ou já está "negado") não muda mais
        if (emEtapaFinal()) throw new IllegalStateException("não dá: Sinistro já está " + estado);
        if (estado.equals("negado")) throw new IllegalStateException("Sinistro já está negado");
        estado = "negado";   // PÓS-CONDIÇÃO
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Apolice getApolice() { return apolice; }
    public double getValorIndenizacao() { return valorIndenizacao; }
    public String getEstado() { return estado; }
}
