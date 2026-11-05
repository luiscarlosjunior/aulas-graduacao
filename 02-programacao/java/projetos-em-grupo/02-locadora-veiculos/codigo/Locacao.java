import java.util.ArrayList;
import java.util.List;

/**
 * Locacao — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valorDiaria nunca é negativo e o estado só muda por operações.
 */
public class Locacao {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"aberta", "em andamento", "finalizada"};

    // ASSOCIAÇÃO: cada Locacao conhece o Cliente ligado a ela.
    private final Cliente cliente;
    private final double valorDiaria;   // INVARIANTE: valorDiaria >= 0
    private String estado;

    public Locacao(Cliente cliente, double valorDiaria) {
        // PRÉ-CONDIÇÕES
        if (cliente == null) throw new IllegalArgumentException("Cliente é obrigatório");
        if (valorDiaria < 0) throw new IllegalArgumentException("valorDiaria não pode ser negativo: " + valorDiaria);
        // só depois de validar TUDO, guardamos os valores
        this.cliente = cliente;
        this.valorDiaria = valorDiaria;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa. */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (estado.equals("cancelada")) throw new IllegalStateException("Locacao está cancelada: não avança mais");
        if (emEtapaFinal()) throw new IllegalStateException("Locacao já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    /** Caminho alternativo: vai para "cancelada". */
    public void cancelar() {
        // PRÉ-CONDIÇÃO: o que já terminou (ou já está "cancelada") não muda mais
        if (emEtapaFinal()) throw new IllegalStateException("não dá: Locacao já está " + estado);
        if (estado.equals("cancelada")) throw new IllegalStateException("Locacao já está cancelada");
        estado = "cancelada";   // PÓS-CONDIÇÃO
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Cliente getCliente() { return cliente; }
    public double getValorDiaria() { return valorDiaria; }
    public String getEstado() { return estado; }

    // COMPOSIÇÃO (◆): cada ItemCobranca nasce dentro desta classe e morre com ela.
    private final List<ItemCobranca> itensCobranca = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public ItemCobranca adicionarItemCobranca(String descricao, double valor) {
        ItemCobranca parte = new ItemCobranca(descricao, valor);
        itensCobranca.add(parte);
        return parte;
    }

    public List<ItemCobranca> getItensCobranca() { return List.copyOf(itensCobranca); }
}
