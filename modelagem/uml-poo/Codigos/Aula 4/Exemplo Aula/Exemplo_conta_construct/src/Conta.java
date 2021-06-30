
public class Conta {
	
	private String agencia;
	private String conta;
	private float saldo;
	
	public float getSaldo() {
		return saldo;
	}
	public String getAgencia() {
		return this.agencia;
	}
	protected void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	protected void setConta(String conta) {
		this.conta = conta;
	}
	
	public Conta(String agencia, String conta)
	{
		setAgencia(agencia);
		setConta(conta);
	}
	
	
	public void depositar(float deposito) {
		saldo += deposito;
		System.out.println("Seu saldo é " + this.saldo);
	}
	public float verSaldo() {
		return this.saldo;
	}
	
}
