/**
 * ABSTRAÇÃO: Pagamento é uma classe ABSTRATA (um contrato).
 *
 * Ela define QUE existe a operação pagar(), mas não diz COMO — cada forma de
 * pagamento (Dinheiro, Cartao, Pix) implementa a sua regra. Não faz sentido
 * criar "um pagamento genérico", por isso a classe é abstrata (não instanciável).
 *
 * Este é o gancho perfeito para o módulo 06-abstracao e para o polimorfismo.
 */
public abstract class Pagamento {

    /** Método abstrato: obriga cada subclasse a definir seu jeito de pagar. */
    public abstract void pagar(double valor);
}
