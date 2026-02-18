namespace poo_conta
{
    public class Conta
    {
        public Conta(int agencia, int numeroConta, int Saldo)
        {
            this.Agencia = agencia;
            this.NumeroConta = numeroConta;
            this.Saldo = Saldo;
        }
        
        public int Agencia { get; set; }
        public int NumeroConta { get; set; }
        private float _saldo;
        public float Saldo { 
            get{ return _saldo; }
            set{
               _saldo = value; 
            } 
        }
        
        public void Depositar(float dinheiro)
        {
            if (dinheiro > 0)
            {
                Saldo = dinheiro;
            }
        }
    }
}