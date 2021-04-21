
public class principal {

	public static void main(String[] args) {
		
		ContaPoupanca luis = new ContaPoupanca("001", "123", 1);
		ContaPoupanca lais = new ContaPoupanca("001", "1234", 2);
		ContaPoupanca roberta = new ContaPoupanca("001", "1235", 3);
		
		luis.depositar(500f);
		
		lais.depositar(1500f);
		
		roberta.depositar(865f);
		
		System.out.println(luis);
		System.out.println(lais);
		System.out.println(roberta);
	}
	
}
