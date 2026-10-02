package forma.pagamento;

import contrato.pagamento.MeioDePagamento;

/*
 * Boleto é o caso mais simples: SÓ cobra. Não autentica e não reembolsa
 * sozinho — por isso implementa apenas MeioDePagamento. (Graças às interfaces
 * separadas, ele não é obrigado a ter métodos vazios de autenticar/reembolsar.)
 */
public class Boleto implements MeioDePagamento {

    @Override public String nome() { return "Boleto"; }

    @Override public boolean cobrar(double valor) {
        System.out.printf("[Boleto] gerado no valor de R$ %.2f (compensa em 1-3 dias)%n", valor);
        return true;
    }
}
