namespace poo_conta
{
    class Program
    {
        static void Main(string[] args)
        {
            // Instânciando a classe cliente
            var cliente = new poo_conta.Cliente.Cliente();

            // Passando valores para o atributos do objeto
            cliente.CPF = "12345678910";
            cliente.Nome = "Xavier Bezzuti";
            cliente.Telefone = "11 4141-1245";
            // Faz instânciação da classe ContaPoupanca, já passando os valores 
            // necessários.
            cliente.ContaPoupanca = new Servicos.ContaPoupanca(1, 1, 100, 10);

            cliente.VisualizarDetalhesCliente();

        }

    }
}
