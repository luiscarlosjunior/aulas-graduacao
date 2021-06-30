
public class ContaPoupanca extends Conta {
	private int diaDeposito;
	
	public ContaPoupanca(String agencia, String conta, int diaDeposito) {
		super(agencia, conta);
		this.diaDeposito = diaDeposito;
	}

	
	public int getDiaDeposito() {
		return diaDeposito;
	}

	public void setDiaDeposito(int diaDeposito) {
		this.diaDeposito = diaDeposito;
	}

	public float verLucro() {
		return getSaldo();
	}
	
	/*public String toString() {
		return "Exemplos de saída";
	}*/
	
}
