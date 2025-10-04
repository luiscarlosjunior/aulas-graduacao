package forma.pagamento;

import contrato.pagamento.Pagamento;
import contrato.pagamento.Autenticavel;

public class Cripto implements Pagamento, Autenticavel {
    @Override
    public void processar() {
        System.out.println("Processando pagamento via Criptomoeda.");
    }
    
    @Override
    public void autenticar() {
        System.out.println("Autenticando pagamento via Criptomoeda.");
    }

}