import java.util.ArrayList;
import java.util.List;

/**
 * Contrato — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valorAluguel nunca é negativo e o estado só muda por operações.
 */
public class Contrato {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"ativo", "encerrado"};

    // ASSOCIAÇÃO: cada Contrato conhece o Imovel ligado a ela.
    private final Imovel imovel;
    private final double valorAluguel;   // INVARIANTE: valorAluguel >= 0
    private String estado;

    public Contrato(Imovel imovel, double valorAluguel) {
        // PRÉ-CONDIÇÕES
        if (imovel == null) throw new IllegalArgumentException("Imovel é obrigatório");
        if (valorAluguel < 0) throw new IllegalArgumentException("valorAluguel não pode ser negativo: " + valorAluguel);
        // só depois de validar TUDO, guardamos os valores
        this.imovel = imovel;
        this.valorAluguel = valorAluguel;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa. */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (estado.equals("cancelado")) throw new IllegalStateException("Contrato está cancelado: não avança mais");
        if (emEtapaFinal()) throw new IllegalStateException("Contrato já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    /** Caminho alternativo: vai para "cancelado". */
    public void cancelar() {
        // PRÉ-CONDIÇÃO: o que já terminou (ou já está "cancelado") não muda mais
        if (emEtapaFinal()) throw new IllegalStateException("não dá: Contrato já está " + estado);
        if (estado.equals("cancelado")) throw new IllegalStateException("Contrato já está cancelado");
        estado = "cancelado";   // PÓS-CONDIÇÃO
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Imovel getImovel() { return imovel; }
    public double getValorAluguel() { return valorAluguel; }
    public String getEstado() { return estado; }

    // COMPOSIÇÃO (◆): cada Parcela nasce dentro desta classe e morre com ela.
    private final List<Parcela> parcelas = new ArrayList<>();

    /** O TODO cria a PARTE (o `new` fica aqui dentro) — isso é composição. */
    public Parcela adicionarParcela(String descricao, double valor) {
        Parcela parte = new Parcela(descricao, valor);
        parcelas.add(parte);
        return parte;
    }

    public List<Parcela> getParcelas() { return List.copyOf(parcelas); }
}
