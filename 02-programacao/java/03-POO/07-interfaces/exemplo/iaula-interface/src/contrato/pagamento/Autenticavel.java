package contrato.pagamento;

/*
 * Contrato de CAPACIDADE OPCIONAL: só alguns meios precisam autenticar antes
 * de cobrar (cartão, PayPal, cripto). Boleto e Pix NÃO implementam isto —
 * por isso é uma interface separada (princípio da Segregação de Interfaces).
 */
public interface Autenticavel {
    /** Autentica com um token/senha; devolve true se passou. */
    boolean autenticar(String token);
}
