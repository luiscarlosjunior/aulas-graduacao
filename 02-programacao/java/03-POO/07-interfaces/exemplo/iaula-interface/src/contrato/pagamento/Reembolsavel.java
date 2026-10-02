package contrato.pagamento;

/*
 * Outra CAPACIDADE OPCIONAL: só alguns meios sabem devolver o dinheiro
 * automaticamente (cartão, Pix). Boleto, por exemplo, não reembolsa sozinho.
 */
public interface Reembolsavel {
    /** Devolve o valor; true se o reembolso foi aceito. */
    boolean reembolsar(double valor);
}
