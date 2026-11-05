import java.util.ArrayList;
import java.util.List;

/**
 * Venda — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valorTotal nunca é negativo e o estado só muda por operações.
 * MÁQUINA DE ESTADOS: aberta → paga; ou cancelada.
 */
public class Venda {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"aberta", "paga"};

    private final double valorTotal;   // INVARIANTE: valorTotal >= 0
    private String estado;

    public Venda(double valorTotal) {
        // PRÉ-CONDIÇÕES
        if (valorTotal < 0) throw new IllegalArgumentException("valorTotal não pode ser negativo: " + valorTotal);
        // só depois de validar TUDO, guardamos os valores
        this.valorTotal = valorTotal;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa (aberta → paga). */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (estado.equals("cancelada")) throw new IllegalStateException("Venda está cancelada: não avança mais");
        if (emEtapaFinal()) throw new IllegalStateException("Venda já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    /** Caminho alternativo da máquina de estados: vai para "cancelada". */
    public void cancelar() {
        // PRÉ-CONDIÇÃO: o que já terminou (ou já está "cancelada") não muda mais
        if (emEtapaFinal()) throw new IllegalStateException("não dá: Venda já está " + estado);
        if (estado.equals("cancelada")) throw new IllegalStateException("Venda já está cancelada");
        estado = "cancelada";   // PÓS-CONDIÇÃO
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public double getValorTotal() { return valorTotal; }
    public String getEstado() { return estado; }

    // COMPOSIÇÃO (◆): os ingressos nascem dentro desta classe e somem com ela.
    private final List<Ingresso> ingressos = new ArrayList<>();

    /** Cria a parte AQUI dentro (o `new` é do todo). especial=true cria IngressoMeia. */
    public Ingresso adicionarIngresso(String assento, double preco, boolean especial) {
        Ingresso novo = especial ? new IngressoMeia(assento, preco) : new IngressoInteira(assento, preco);
        ingressos.add(novo);
        return novo;
    }

    public List<Ingresso> getIngressos() { return List.copyOf(ingressos); }
}
