package forma.pagamento;

import contrato.pagamento.Pagamento;
import contrato.pagamento.Autenticavel;

public class Paypal implements Pagamento, Autenticavel {

    @Override
    public void processar() {
        System.out.println("Processando pagamento via Paypal.");
    }
    
    @Override
    public void autenticar() {
        System.out.println("Autenticando via Paypal.");
    }
    
}