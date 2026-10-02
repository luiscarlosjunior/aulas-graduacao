package forma.pagamento;

import contrato.pagamento.MeioDePagamento;
import contrato.pagamento.Autenticavel;

/* Cripto cobra e autentica (assinatura da carteira). */
public class Cripto implements MeioDePagamento, Autenticavel {
    private boolean autenticado = false;

    @Override public String nome() { return "Cripto"; }

    @Override public boolean autenticar(String token) {
        autenticado = token != null;
        System.out.println("[Cripto] assinando a carteira... " + (autenticado ? "ok" : "falhou"));
        return autenticado;
    }

    @Override public boolean cobrar(double valor) {
        if (!autenticado) {
            System.out.println("[Cripto] recusado: carteira não assinada");
            return false;
        }
        System.out.printf("[Cripto] convertendo e cobrando R$ %.2f%n", valor);
        return true;
    }
}
