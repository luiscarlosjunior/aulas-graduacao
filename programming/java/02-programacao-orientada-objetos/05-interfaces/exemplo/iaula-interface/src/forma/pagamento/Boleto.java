package forma.pagamento;

import contrato.pagamento.Pagamento;

public class Boleto implements Pagamento {

    @Override
    public void processar() {
        System.out.println("Processando pagamento via Boleto.");
    }
    
}
