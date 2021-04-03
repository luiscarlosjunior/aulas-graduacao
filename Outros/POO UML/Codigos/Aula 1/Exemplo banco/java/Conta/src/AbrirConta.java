public class AbrirConta {
    public void Abrir()
    {
        //Instânciação da classe ContaCorrente
        ContaPoupanca cliente1 = new ContaPoupanca();
        ContaPoupanca cliente2 = new ContaPoupanca();

        // Criando uma conta corrente
        cliente1.numeroConta = "123";
        cliente1.agencia = 1;
        cliente1.DiaDeposito = 10;

        cliente2.numeroConta = "999";
        cliente2.agencia = 2;
        cliente2.DiaDeposito = 5;

        System.out.println("Agência: " + cliente1.agencia);   
        System.out.println("Numero Conta: " + cliente1.numeroConta);   
        System.out.println("Dia deposito: " + cliente1.DiaDeposito);   
        System.out.println("Agência: " + cliente2.agencia);   
        System.out.println("Numero Conta: " + cliente2.numeroConta);   
        System.out.println("Dia deposito: " + cliente2.DiaDeposito);   
    }
}
