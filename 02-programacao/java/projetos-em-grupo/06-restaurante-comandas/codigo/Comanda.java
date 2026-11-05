import java.util.ArrayList;
import java.util.List;

/**
 * Comanda — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valorTotal nunca é negativo e o estado só muda por operações.
 */
public class Comanda {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"aberta", "fechada", "paga"};

    // ASSOCIAÇÃO: cada Comanda conhece o Cliente ligado a ela.
    private final Cliente cliente;
    private final double valorTotal;   // INVARIANTE: valorTotal >= 0
    private String estado;

    public Comanda(Cliente cliente, double valorTotal) {
        // PRÉ-CONDIÇÕES
        if (cliente == null) throw new IllegalArgumentException("Cliente é obrigatório");
        if (valorTotal < 0) throw new IllegalArgumentException("valorTotal não pode ser negativo: " + valorTotal);
        // só depois de validar TUDO, guardamos os valores
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa. */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (emEtapaFinal()) throw new IllegalStateException("Comanda já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Cliente getCliente() { return cliente; }
    public double getValorTotal() { return valorTotal; }
    public String getEstado() { return estado; }

    // COMPOSIÇÃO (◆): cada Pedido nasce dentro desta classe e morre com ela.
    private final List<Pedido> pedidos = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public Pedido adicionarPedido(String descricao, double valor) {
        Pedido parte = new Pedido(descricao, valor);
        pedidos.add(parte);
        return parte;
    }

    public List<Pedido> getPedidos() { return List.copyOf(pedidos); }
}
