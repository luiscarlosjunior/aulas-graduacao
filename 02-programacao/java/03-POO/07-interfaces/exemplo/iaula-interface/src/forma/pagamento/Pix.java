package forma.pagamento;

import contrato.pagamento.MeioDePagamento;
import contrato.pagamento.Reembolsavel;

/*
 * Pix cobra e reembolsa, mas NÃO é Autenticavel (não assina esse contrato).
 * Ou seja: cada classe implementa só os contratos que realmente cumpre.
 */
public class Pix implements MeioDePagamento, Reembolsavel {
    private final String chave;

    public Pix(String chave) { this.chave = chave; }

    @Override public String nome() { return "Pix"; }

    @Override public boolean cobrar(double valor) {
        System.out.printf("[Pix %s] cobrando R$ %.2f (sem taxa)%n", chave, valor);
        return true;
    }

    @Override public boolean reembolsar(double valor) {
        System.out.printf("[Pix] devolvendo R$ %.2f%n", valor);
        return true;
    }
}
