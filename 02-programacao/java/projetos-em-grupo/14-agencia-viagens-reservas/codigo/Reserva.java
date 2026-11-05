/**
 * Reserva — a "transação" central do sistema.
 * ENCAPSULAMENTO + INVARIANTE: valor nunca é negativo e o estado só muda por operações.
 * MÁQUINA DE ESTADOS: pendente → confirmada; ou cancelada.
 */
public class Reserva {
    // As etapas na ordem certa — não dá para pular nenhuma.
    private static final String[] ETAPAS = {"pendente", "confirmada"};

    // ASSOCIAÇÃO: cada Reserva conhece o Cliente ligado a ela.
    private final Cliente cliente;
    private final double valor;   // INVARIANTE: valor >= 0
    private String estado;

    public Reserva(Cliente cliente, double valor) {
        // PRÉ-CONDIÇÕES
        if (cliente == null) throw new IllegalArgumentException("Cliente é obrigatório");
        if (valor < 0) throw new IllegalArgumentException("valor não pode ser negativo: " + valor);
        // só depois de validar TUDO, guardamos os valores
        this.cliente = cliente;
        this.valor = valor;
        this.estado = ETAPAS[0];   // PÓS-CONDIÇÃO: nasce na primeira etapa
    }

    /** Avança UMA etapa (pendente → confirmada). */
    public void avancar() {
        // PRÉ-CONDIÇÕES: não pode estar no fim nem fora do caminho normal
        if (estado.equals("cancelada")) throw new IllegalStateException("Reserva está cancelada: não avança mais");
        if (emEtapaFinal()) throw new IllegalStateException("Reserva já está na última etapa: " + estado);
        estado = ETAPAS[posicao() + 1];   // PÓS-CONDIÇÃO: andou exatamente uma etapa
    }

    /** Caminho alternativo da máquina de estados: vai para "cancelada". */
    public void cancelar() {
        // PRÉ-CONDIÇÃO: o que já terminou (ou já está "cancelada") não muda mais
        if (emEtapaFinal()) throw new IllegalStateException("não dá: Reserva já está " + estado);
        if (estado.equals("cancelada")) throw new IllegalStateException("Reserva já está cancelada");
        estado = "cancelada";   // PÓS-CONDIÇÃO
    }

    public boolean emEtapaFinal() { return estado.equals(ETAPAS[ETAPAS.length - 1]); }

    private int posicao() {
        for (int i = 0; i < ETAPAS.length; i++) if (ETAPAS[i].equals(estado)) return i;
        return -1;
    }

    public Cliente getCliente() { return cliente; }
    public double getValor() { return valor; }
    public String getEstado() { return estado; }
}
