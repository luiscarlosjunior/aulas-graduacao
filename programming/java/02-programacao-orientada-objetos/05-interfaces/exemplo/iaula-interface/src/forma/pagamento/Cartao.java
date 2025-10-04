package forma.pagamento;

import contrato.pagamento.*;

public class Cartao implements Pagamento, Autenticavel, Reembolsavel {
    
    @Override
    public void processar() {
        System.out.println("Processando pagamento via Cartão.");
    }
    
    @Override
    public void autenticar() {
        System.out.println("Autenticando pagamento via Cartão.");
    }
    
    @Override
    public void reembolsar(double valor) {
        System.out.println("Reembolsando " + valor + " via Cartão.");
    }

}
