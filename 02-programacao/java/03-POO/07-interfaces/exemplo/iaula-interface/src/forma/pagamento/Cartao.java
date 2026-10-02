package forma.pagamento;

import contrato.pagamento.MeioDePagamento;
import contrato.pagamento.Autenticavel;
import contrato.pagamento.Reembolsavel;

/*
 * Cartão assina TRÊS contratos: cobra, autentica e reembolsa.
 * Isso é múltipla implementação — impossível com herança de classe.
 */
public class Cartao implements MeioDePagamento, Autenticavel, Reembolsavel {
    private final String numero;
    private boolean autenticado = false;

    public Cartao(String numero) { this.numero = numero; }

    @Override public String nome() { return "Cartão de crédito"; }

    @Override public boolean autenticar(String token) {
        autenticado = token != null && token.length() >= 6;
        System.out.println("[Cartão] autenticando... " + (autenticado ? "ok" : "falhou"));
        return autenticado;
    }

    @Override public boolean cobrar(double valor) {
        if (!autenticado) {               // regra do cartão: precisa autenticar antes
            System.out.println("[Cartão] recusado: não autenticado");
            return false;
        }
        System.out.printf("[Cartão %s] cobrando R$ %.2f%n", mascara(), valor);
        return true;
    }

    @Override public boolean reembolsar(double valor) {
        System.out.printf("[Cartão] reembolsando R$ %.2f%n", valor);
        return true;
    }

    private String mascara() {
        return "****" + numero.substring(Math.max(0, numero.length() - 4));
    }
}
