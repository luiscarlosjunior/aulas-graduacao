package br.com.uninove.empresa;

public class Principal {

	public static void main(String[] args) {		
		
		criarFuncionarios();
	}

	private static void criarFuncionarios() {
		ControleBonificacao cb = new ControleBonificacao();
				
		Gerente g1 = 
				new Gerente("Jo�o", "13246", "123", 5000.00, "123");
		Funcionario a1 = 
				new Analista("Joaquim", "555", "123", 3500.00);
		
		//cb.registra(g1);
		//cb.registra(a1);
		
		System.out.println("Bem-vindo, " + g1.getNome());
		System.out.println("A sua bonifica��o � de " + g1.bonificacao() + "\n");
		System.out.println("A sua bonifica��o � de " + g1.bonificacao() + "\n");
		
		System.out.println("Bem-vindo, " + a1.getNome());
		System.out.println("A sua bonifica��o � de " + a1.bonificacao() + "\n");
		
		
		System.out.println("-----------------------------");
		System.out.println("Total de bonifica��o " + cb.getSoma());
		System.out.println("-----------------------------");
	}

	private static void exemploPolimorfismo() {
		Pessoa pessoa = null;
		int tipo = 6;
		
		/*switch (tipo) {
			case 1: pessoa = new Pessoa(); break;
			case 2: pessoa = new PessoaFisica(); break;
			case 3: pessoa = new PessoaJuridica(); break;
			case 4: pessoa = new Funcionario(); break;
			case 5: pessoa = new Gerente(); break;
			case 6: pessoa = new Analista(); break;
		default:
			System.out.println("N�o conhe�o esse tipo de pessoa");
		}
		
		pessoa.mostraClasse();*/
	}

	private static void criarFuncionario() {
		/*Funcionario funcionario = new Funcionario();
		
		definirDadosFuncionario(funcionario);	
		
		imprimirDados(funcionario);*/
	}

	private static void definirDadosFuncionario(Funcionario funcionario) {
		/*funcionario.setNome("Luisa Stranghg");
		funcionario.setRG("132465798");
		funcionario.setCartao("132465");*/
	}

	private static void imprimirDados(Funcionario funcionario) {
		System.out.println(funcionario.getCartao()); 
		System.out.println(funcionario.getRG());
		System.out.println(funcionario.getNome());
	}
	
}
