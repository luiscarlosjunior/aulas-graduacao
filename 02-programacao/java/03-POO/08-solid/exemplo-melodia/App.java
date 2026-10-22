public class App {
    public static void main(String[] args) {
        Plano premium = new Plano("Premium", 19.90);
        CalculadoraPreco calc = new CalculadoraPreco();

        // DIP: injetamos a abstração de pagamento (Pix)
        ServicoAssinatura servico = new ServicoAssinatura(calc, new Pix("ana@melodia.com"));

        // OCP: trocamos o desconto SEM mexer na CalculadoraPreco nem no serviço
        servico.assinar(premium, new SemDesconto());
        servico.assinar(premium, new DescontoEstudante());
        servico.assinar(premium, new DescontoAnual());

        System.out.println("---");
        // DIP: trocamos o MEIO sem mudar o serviço
        ServicoAssinatura comCartao = new ServicoAssinatura(calc, new Cartao("3456"));
        comCartao.assinar(premium, new DescontoEstudante());
    }
}
