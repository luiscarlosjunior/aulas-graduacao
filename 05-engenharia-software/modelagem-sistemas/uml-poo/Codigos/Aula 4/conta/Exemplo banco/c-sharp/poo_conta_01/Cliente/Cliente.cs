using poo_conta.Servicos;

namespace poo_conta.Cliente
{
    public class Cliente
    {
        public string CPF { get; set; }
        public string Nome { get; set; }
        
        public string Telefone { get; set; }
        
        public string Endereco { get; set; }
        
        // Faz a relação entre a classe Cliente e a conta poupança
        public ContaPoupanca ContaPoupanca { get; set; }
                
        public void VisualizarDetalhesCliente()
        {
            string relatorio = 
                $"\n\n******************************************************" +
                $"\nA conta de número: {ContaPoupanca.NumeroConta} " + 
                $"de agência {ContaPoupanca.Agencia}, pertencentes ao cliente de nome\n" +
                $"{Nome.ToUpper()} e de CPF: {CPF}\n" +
                $"localizado no endereço {Endereco} e de telefone {Telefone} foi criado" + 
                $"com sucesso!\n" +
                $"\n******************************************************\n";

            System.Console.WriteLine(relatorio);
        }

    }
}