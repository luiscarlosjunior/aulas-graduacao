package contrato.pagamento;

/*
 * CONTRATO principal do módulo de pagamento do Melodia.
 * Diz O QUE todo meio de pagamento precisa saber fazer — nunca O COMO.
 * A Assinatura depende DESTE contrato, e não de Cartao/Pix/Boleto concretos.
 */
public interface MeioDePagamento {
    /** Cobra o valor; devolve true se a cobrança foi aceita. */
    boolean cobrar(double valor);

    /** Nome amigável do meio (para recibos e logs). */
    String nome();
}
