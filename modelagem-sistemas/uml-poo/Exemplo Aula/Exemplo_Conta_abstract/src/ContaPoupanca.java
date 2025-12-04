
public class ContaPoupanca extends Conta {
	
	private float diaDeposito;
	
	public float getDiaDeposito() {
		return diaDeposito;
	}

	public void setDiaDeposito(float diaDeposito) {
		this.diaDeposito = diaDeposito;
	}

	public float verLucro() {
		return getSaldo();
	}
	
}
