namespace poo_conta.Servicos
{
    public class ContaPoupanca : Conta
    {
        public ContaPoupanca(int agencia, int numeroConta, int saldo, int diaDeposito) : 
            base(agencia, numeroConta, saldo)
        {
            DiaDeposito = diaDeposito;
        }

        public int DiaDeposito { get; set; }

        public float VerLucro()
        {
            return (float)(base.Saldo * 1.1); 
        }
    }
}