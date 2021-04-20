
public class principal {

	public static void main(String[] args) {
		
		ContaPoupanca luis = new ContaPoupanca();
		ContaPoupanca lais = new ContaPoupanca();
		ContaPoupanca roberta = new ContaPoupanca();
		
		luis.setAgencia("0001");
		luis.setConta("123");
		luis.setDiaDeposito(1);
		luis.depositar(500f);
		
		lais.setAgencia("0001");
		lais.setConta("1234");
		lais.setDiaDeposito(1);
		lais.depositar(1500f);
		
		roberta.setAgencia("0001");
		roberta.setConta("1235");
		roberta.setDiaDeposito(1);
		roberta.depositar(865f);
		
		System.out.println(luis.getSaldo());
		System.out.println(lais.getSaldo());
		System.out.println(roberta.getSaldo());
	}
	
}
