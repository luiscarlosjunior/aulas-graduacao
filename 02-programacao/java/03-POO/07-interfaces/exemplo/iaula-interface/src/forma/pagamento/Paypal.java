package forma.pagamento;

import contrato.pagamento.MeioDePagamento;
import contrato.pagamento.Autenticavel;

/* PayPal cobra e autentica (login), mas não reembolsa automaticamente aqui. */
public class Paypal implements MeioDePagamento, Autenticavel {
    private boolean autenticado = false;

    @Override public String nome() { return "PayPal"; }

    @Override public boolean autenticar(String token) {
        autenticado = token != null && !token.isBlank();
        System.out.println("[PayPal] login " + (autenticado ? "ok" : "falhou"));
        return autenticado;
    }

    @Override public boolean cobrar(double valor) {
        if (!autenticado) {
            System.out.println("[PayPal] recusado: faça login primeiro");
            return false;
        }
        System.out.printf("[PayPal] cobrando R$ %.2f%n", valor);
        return true;
    }
}
