package forma.pagamento;

import contrato.pagamento.Pagamento;
import contrato.pagamento.Reembolsavel;

public class Pix implements Pagamento, Reembolsavel {

    @Override
    public void processar() {
        System.out.println("Processando pagamento via Pix.");
    }
    
    @Override
    public void reembolsar(double valor) {
        System.out.println("Reembolsando " + valor + " via Pix.");
    }

}
