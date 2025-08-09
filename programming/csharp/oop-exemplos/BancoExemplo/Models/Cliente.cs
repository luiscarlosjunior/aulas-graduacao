namespace BancoExemplo.Models
{
    /// <summary>
    /// Classe Cliente que demonstra composição/agregação
    /// Um cliente tem uma conta poupança (relação "tem um")
    /// </summary>
    public class Cliente
    {
        // Propriedades do cliente
        public string Nome { get; set; }
        public string CPF { get; set; }
        public string Telefone { get; set; }
        public string Endereco { get; set; }

        // Composição: Cliente tem uma ContaPoupanca
        public ContaPoupanca? ContaPoupanca { get; set; }

        // Construtor
        public Cliente(string nome, string cpf, string telefone = "", string endereco = "")
        {
            Nome = nome;
            CPF = cpf;
            Telefone = telefone;
            Endereco = endereco;
        }

        // Método para criar conta poupança para o cliente
        public void CriarContaPoupanca(int agencia, int numeroConta, decimal saldoInicial = 0, 
                                     int diaAniversario = 1)
        {
            ContaPoupanca = new ContaPoupanca(agencia, numeroConta, saldoInicial, diaAniversario);
            Console.WriteLine($"Conta poupança criada para o cliente {Nome}!");
        }

        // Método para exibir informações completas do cliente
        public void ExibirInformacoesCompletas()
        {
            Console.WriteLine("\n" + new string('=', 50));
            Console.WriteLine("           INFORMAÇÕES DO CLIENTE");
            Console.WriteLine(new string('=', 50));
            Console.WriteLine($"Nome: {Nome}");
            Console.WriteLine($"CPF: {CPF}");
            
            if (!string.IsNullOrEmpty(Telefone))
                Console.WriteLine($"Telefone: {Telefone}");
            
            if (!string.IsNullOrEmpty(Endereco))
                Console.WriteLine($"Endereço: {Endereco}");

            Console.WriteLine();

            if (ContaPoupanca != null)
            {
                ContaPoupanca.ConsultarSaldo();
                
                if (ContaPoupanca.VerificarAniversario())
                {
                    Console.WriteLine("🎉 Hoje é dia de aniversário da conta!");
                    Console.WriteLine("Rendimento pode ser aplicado.");
                }
            }
            else
            {
                Console.WriteLine("Cliente não possui conta poupança.");
            }
            
            Console.WriteLine(new string('=', 50));
        }

        // Método para realizar operações na conta
        public void OperarConta()
        {
            if (ContaPoupanca == null)
            {
                Console.WriteLine($"Cliente {Nome} não possui conta poupança.");
                return;
            }

            Console.WriteLine($"\n=== OPERAÇÕES PARA {Nome.ToUpper()} ===");
            
            // Demonstrar operações
            ContaPoupanca.Depositar(500.00m);
            ContaPoupanca.Depositar(200.50m);
            ContaPoupanca.Sacar(100.00m);
            
            // Aplicar rendimento se for dia de aniversário
            if (ContaPoupanca.VerificarAniversario())
            {
                ContaPoupanca.AplicarRendimento();
            }
            
            Console.WriteLine();
            ContaPoupanca.ConsultarSaldo();
        }

        // Validação de CPF (método auxiliar)
        public bool ValidarCPF()
        {
            // Implementação simplificada para exemplo
            return !string.IsNullOrEmpty(CPF) && CPF.Length == 11;
        }
    }
}