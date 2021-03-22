using System;

namespace project_conta01
{
    internal class AbrirConta
    {
        internal void Abrir()
        {

            // Instânciando a classe contaCorrente
            ContaPoupanca cliente1 = new ContaPoupanca();
            ContaPoupanca cliente2 = new ContaPoupanca();

            // Criando uma conta corrente
            cliente1.Agencia = 1234;
            cliente1.NumeroConta = "123456";
            cliente1.DiaDeposito = 10;
            cliente1.Depositar(50);

            cliente2.Agencia = 1234;
            cliente2.NumeroConta = "123456";
            cliente2.DiaDeposito = 10;
            cliente2.Depositar(50);
            
            ExibirDadosBancarios(cliente1);
            ExibirDadosBancarios(cliente2);
        }

        private void ExibirDadosBancarios(ContaPoupanca cliente)
        {
            Console.WriteLine("Qual é o dia de deposito? ");
            Console.WriteLine(cliente.DiaDeposito);
            Console.WriteLine("Qual é o Numero da Conta? ");
            Console.WriteLine(cliente.NumeroConta);
            Console.WriteLine("Qual é o Agencia? ");
            Console.WriteLine(cliente.Agencia);
            Console.WriteLine("Meu Saldo? ");
            Console.WriteLine(cliente.Saldo(1234));
        }
        }

        
}