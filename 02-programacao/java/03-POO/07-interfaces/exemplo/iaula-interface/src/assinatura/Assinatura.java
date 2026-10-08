package assinatura;

import contrato.pagamento.MeioDePagamento;
import contrato.pagamento.Autenticavel;
import contrato.pagamento.Reembolsavel;

/*
 * Assinatura é a prova de que a interface vale a pena: ela depende do CONTRATO
 * MeioDePagamento e NÃO conhece Cartao/Pix/Boleto. Por isso, acrescentar um meio
 * novo não muda uma linha daqui (aberto para extensão, fechado para modificação).
 *
 * As capacidades OPCIONAIS (autenticar/reembolsar) são tratadas com `instanceof`:
 * a Assinatura só pede se o meio REALMENTE assinou aquele contrato.
 */
public class Assinatura {
    private final Plano plano;
    private final MeioDePagamento meio;   // o CONTRATO, não a classe concreta
    private boolean ativa = false;

    public Assinatura(Plano plano, MeioDePagamento meio) {
        if (plano == null || meio == null)
            throw new IllegalArgumentException("Plano e meio de pagamento são obrigatórios.");
        this.plano = plano;
        this.meio = meio;
    }

    public boolean ativar(String token) {
        // Capacidade OPCIONAL: só autentica quem é Autenticavel
        if (meio instanceof Autenticavel) {
            if (!((Autenticavel) meio).autenticar(token)) {
                System.out.println("Falha na autenticação — assinatura não ativada.");
                return false;
            }
        }
        // Polimorfismo: cada meio cobra do seu jeito, a Assinatura não sabe qual é
        boolean ok = meio.cobrar(plano.getPrecoMensal());
        if (ok) {
            ativa = true;
            System.out.println("✓ Assinatura '" + plano.getNome() + "' ativada via " + meio.nome());
        }
        return ok;
    }

    public boolean cancelar() {
        if (!ativa) {
            System.out.println("Assinatura não está ativa.");
            return false;
        }
        ativa = false;
        // Capacidade OPCIONAL: só reembolsa quem é Reembolsavel
        if (meio instanceof Reembolsavel) {
            ((Reembolsavel) meio).reembolsar(plano.getPrecoMensal());
        } else {
            System.out.println("[" + meio.nome() + "] não suporta reembolso automático — tratar manual.");
        }
        System.out.println("✓ Assinatura cancelada.");
        return true;
    }

    public boolean isAtiva() { return ativa; }
}
