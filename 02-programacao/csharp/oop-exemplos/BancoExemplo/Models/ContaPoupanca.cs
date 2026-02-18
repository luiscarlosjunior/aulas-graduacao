namespace BancoExemplo.Models
{
    /// <summary>
    /// Classe ContaPoupanca que herda de Conta (demonstra herança)
    /// Adiciona funcionalidades específicas de poupança
    /// </summary>
    public class ContaPoupanca : Conta
    {
        // Propriedade específica da conta poupança
        public int DiaAniversario { get; set; }
        public decimal TaxaRendimento { get; set; }

        // Construtor que chama o construtor da classe base
        public ContaPoupanca(int agencia, int numeroConta, decimal saldoInicial = 0, 
                           int diaAniversario = 1, decimal taxaRendimento = 0.005m) 
            : base(agencia, numeroConta, saldoInicial)
        {
            DiaAniversario = diaAniversario;
            TaxaRendimento = taxaRendimento;
        }

        // Sobrescrita do método depositar (polimorfismo)
        public override void Depositar(decimal valor)
        {
            if (valor > 0)
            {
                base.Depositar(valor);
                Console.WriteLine("Depósito realizado em conta poupança.");
            }
        }

        // Método específico da conta poupança para calcular rendimento
        public decimal CalcularRendimento()
        {
            decimal rendimento = Saldo * TaxaRendimento;
            return rendimento;
        }

        // Método para aplicar rendimento mensal
        public void AplicarRendimento()
        {
            decimal rendimento = CalcularRendimento();
            if (rendimento > 0)
            {
                Depositar(rendimento);
                Console.WriteLine($"Rendimento de R${rendimento:F2} aplicado na conta poupança.");
            }
        }

        // Sobrescrita do método de consultar saldo
        public override void ConsultarSaldo()
        {
            Console.WriteLine($"=== CONTA POUPANÇA ===");
            base.ConsultarSaldo();
            Console.WriteLine($"Dia Aniversário: {DiaAniversario}");
            Console.WriteLine($"Taxa de Rendimento: {TaxaRendimento:P2}");
            Console.WriteLine($"Próximo Rendimento: R${CalcularRendimento():F2}");
        }

        // Método específico para verificar se é dia de aniversário
        public bool VerificarAniversario()
        {
            int diaAtual = DateTime.Now.Day;
            return diaAtual == DiaAniversario;
        }
    }
}