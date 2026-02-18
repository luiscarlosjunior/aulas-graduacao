import contrato.pagamento.Autenticavel;
import contrato.pagamento.Pagamento;

import forma.pagamento.*;

public class App {
    public static void main(String[] args) throws Exception {
        Pagamento pagamento = new Cartao();
        Pagamento pagamento2 = new Pix();
        Pagamento pagamento3 = new Boleto();

        Autenticavel autenticavel = new Cartao();
        Autenticavel autenticavel2 = new Cripto();
        Autenticavel autenticavel3 = new Paypal();

        pagamento.processar();
        pagamento2.processar();
        pagamento3.processar();
        autenticavel.autenticar();
        autenticavel2.autenticar();
        autenticavel3.autenticar();
    }
}
