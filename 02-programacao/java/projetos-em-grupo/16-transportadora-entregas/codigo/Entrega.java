/**
 * Entrega — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valorFrete nunca é negativo e o estado só muda por operações.
 * MÁQUINA DE ESTADOS: postada → em trânsito → saiu para entrega → entregue; ou devolvida.
 */
public class Entrega {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"postada", "em trânsito", "saiu para entrega", "entregue"};

    // ASSOCIAÇÃO: cada Entrega conhece o Encomenda ligado a ela.
    private final Encomenda encomenda;
    private final double valorFrete;   // INVARIANTE: valorFrete >= 0
    private String estado;

    public Entrega(Encomenda encomenda, double valorFrete) {
        // PRÉ-CONDIÇÕES
        if (encomenda == null) throw new IllegalArgumentException("Encomenda é obrigatório");
        if (valorFrete < 0) throw new IllegalArgumentException("valorFrete não pode ser negativo: " + valorFrete);
        // só depois de validar TUDO, guardamos os valores
        this.encomenda = encomenda;
        this.valorFrete = valorFrete;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa (postada → em trânsito → saiu para entrega → entregue). */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (estado.equals("devolvida")) throw new IllegalStateException("Entrega está devolvida: não avança mais");
        if (emEtapaFinal()) throw new IllegalStateException("Entrega já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    /** Caminho alternativo da máquina de estados: vai para "devolvida". */
    public void marcarDevolvida() {
        // PRÉ-CONDIÇÃO: o que já terminou (ou já está "devolvida") não muda mais
        if (emEtapaFinal()) throw new IllegalStateException("não dá: Entrega já está " + estado);
        if (estado.equals("devolvida")) throw new IllegalStateException("Entrega já está devolvida");
        estado = "devolvida";   // PÓS-CONDIÇÃO
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Encomenda getEncomenda() { return encomenda; }
    public double getValorFrete() { return valorFrete; }
    public String getEstado() { return estado; }
}
