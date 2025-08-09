namespace BancoExemplo.Models
{
    /// <summary>
    /// Classe base Conta que demonstra conceitos de encapsulamento
    /// </summary>
    public class Conta
    {
        // Propriedades públicas com encapsulamento
        public int Agencia { get; set; }
        public int NumeroConta { get; set; }
        
        // Campo privado para demonstrar encapsulamento
        private decimal _saldo;
        
        // Propriedade com lógica de validação
        public decimal Saldo 
        { 
            get { return _saldo; }
            protected set 
            {
                if (value >= 0)
                    _saldo = value;
            }
        }

        // Construtor
        public Conta(int agencia, int numeroConta, decimal saldoInicial = 0)
        {
            Agencia = agencia;
            NumeroConta = numeroConta;
            Saldo = saldoInicial;
        }

        // Método virtual que pode ser sobrescrito nas classes filhas
        public virtual void Depositar(decimal valor)
        {
            if (valor > 0)
            {
                _saldo += valor;
                Console.WriteLine($"Depósito de R${valor:F2} realizado. Saldo atual: R${_saldo:F2}");
            }
            else
            {
                Console.WriteLine("Valor de depósito deve ser positivo.");
            }
        }

        // Método virtual para sacar dinheiro
        public virtual bool Sacar(decimal valor)
        {
            if (valor > 0 && valor <= _saldo)
            {
                _saldo -= valor;
                Console.WriteLine($"Saque de R${valor:F2} realizado. Saldo atual: R${_saldo:F2}");
                return true;
            }
            else
            {
                Console.WriteLine("Saque não autorizado. Valor inválido ou saldo insuficiente.");
                return false;
            }
        }

        // Método para consultar saldo
        public virtual void ConsultarSaldo()
        {
            Console.WriteLine($"Agência: {Agencia}, Conta: {NumeroConta}, Saldo: R${_saldo:F2}");
        }
    }
}