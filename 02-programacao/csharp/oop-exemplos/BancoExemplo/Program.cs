using BancoExemplo.Models;

namespace BancoExemplo
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("=== DEMONSTRAÇÃO DE OOP EM C# ===");
            Console.WriteLine("Conceitos demonstrados:");
            Console.WriteLine("1. Herança: ContaPoupanca herda de Conta");
            Console.WriteLine("2. Composição: Cliente tem uma ContaPoupanca");
            Console.WriteLine("3. Encapsulamento: Propriedades privadas e públicas");
            Console.WriteLine("4. Polimorfismo: Sobrescrita de métodos");
            Console.WriteLine();

            // Criando um cliente (demonstra encapsulamento)
            var cliente1 = new Cliente(
                nome: "João da Silva",
                cpf: "12345678901",
                telefone: "(11) 99999-9999",
                endereco: "Rua das Flores, 123"
            );

            // Criando conta poupança para o cliente (demonstra composição)
            cliente1.CriarContaPoupanca(
                agencia: 1234,
                numeroConta: 56789,
                saldoInicial: 1000.00m,
                diaAniversario: DateTime.Now.Day // Hoje é aniversário da conta
            );

            // Exibindo informações do cliente
            cliente1.ExibirInformacoesCompletas();

            // Realizando operações (demonstra herança e polimorfismo)
            cliente1.OperarConta();

            Console.WriteLine("\n" + new string('-', 50));

            // Criando outro cliente para demonstrar mais funcionalidades
            var cliente2 = new Cliente(
                nome: "Maria Santos",
                cpf: "98765432109",
                telefone: "(11) 88888-8888"
            );

            cliente2.CriarContaPoupanca(
                agencia: 5678,
                numeroConta: 12345,
                saldoInicial: 2500.00m,
                diaAniversario: 15 // Não é hoje
            );

            // Demonstrando polimorfismo - usando referência da classe base
            Console.WriteLine("\n=== DEMONSTRAÇÃO DE POLIMORFISMO ===");
            Conta conta = cliente2.ContaPoupanca; // Polimorfismo
            conta.Depositar(300.00m); // Chama o método sobrescrito
            conta.ConsultarSaldo();   // Chama o método sobrescrito

            Console.WriteLine("\n=== COMPARAÇÃO DE CONTAS ===");
            CompararContas(cliente1.ContaPoupanca, cliente2.ContaPoupanca);

            Console.WriteLine("\nPressione qualquer tecla para sair...");
            Console.ReadKey();
        }

        // Método auxiliar para demonstrar polimorfismo
        static void CompararContas(Conta conta1, Conta conta2)
        {
            Console.WriteLine("Comparando duas contas:");
            Console.WriteLine("\nConta 1:");
            conta1.ConsultarSaldo();
            
            Console.WriteLine("\nConta 2:");
            conta2.ConsultarSaldo();

            // Demonstrando que mesmo sendo do tipo Conta,
            // os métodos sobrescritos são chamados
            if (conta1.Saldo > conta2.Saldo)
                Console.WriteLine("\nConta 1 tem maior saldo.");
            else if (conta2.Saldo > conta1.Saldo)
                Console.WriteLine("\nConta 2 tem maior saldo.");
            else
                Console.WriteLine("\nAs contas têm o mesmo saldo.");
        }
    }
}
