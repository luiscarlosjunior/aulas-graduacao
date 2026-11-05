import java.util.ArrayList;
import java.util.List;

/**
 * Pedido — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valorTotal nunca é negativo e o estado só muda por operações.
 * MÁQUINA DE ESTADOS: recebido → preparando → a caminho → entregue; ou cancelado.
 */
public class Pedido {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"recebido", "preparando", "a caminho", "entregue"};

    // ASSOCIAÇÃO: cada Pedido conhece o Cliente ligado a ela.
    private final Cliente cliente;
    private final double valorTotal;   // INVARIANTE: valorTotal >= 0
    private String estado;

    public Pedido(Cliente cliente, double valorTotal) {
        // PRÉ-CONDIÇÕES
        if (cliente == null) throw new IllegalArgumentException("Cliente é obrigatório");
        if (valorTotal < 0) throw new IllegalArgumentException("valorTotal não pode ser negativo: " + valorTotal);
        // só depois de validar TUDO, guardamos os valores
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa (recebido → preparando → a caminho → entregue). */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (estado.equals("cancelado")) throw new IllegalStateException("Pedido está cancelado: não avança mais");
        if (emEtapaFinal()) throw new IllegalStateException("Pedido já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    /** Caminho alternativo da máquina de estados: vai para "cancelado". */
    public void cancelar() {
        // PRÉ-CONDIÇÃO: o que já terminou (ou já está "cancelado") não muda mais
        if (emEtapaFinal()) throw new IllegalStateException("não dá: Pedido já está " + estado);
        if (estado.equals("cancelado")) throw new IllegalStateException("Pedido já está cancelado");
        estado = "cancelado";   // PÓS-CONDIÇÃO
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Cliente getCliente() { return cliente; }
    public double getValorTotal() { return valorTotal; }
    public String getEstado() { return estado; }

    // COMPOSIÇÃO (◆): cada ItemPedido nasce dentro desta classe e morre com ela.
    private final List<ItemPedido> itensPedido = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public ItemPedido adicionarItemPedido(String descricao, double valor) {
        ItemPedido parte = new ItemPedido(descricao, valor);
        itensPedido.add(parte);
        return parte;
    }

    public List<ItemPedido> getItensPedido() { return List.copyOf(itensPedido); }
}
