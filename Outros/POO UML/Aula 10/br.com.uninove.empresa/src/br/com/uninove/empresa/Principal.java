package br.com.uninove.empresa;

public class Principal {

	public static void main(String[] args) {		
		//criarFuncionario();
		
		Pessoa pessoa = null;
		int tipo = 4;
		
		switch (tipo) {
			case 1: pessoa = new Pessoa(); break;
			case 2: pessoa = new PessoaFisica(); break;
			case 3: pessoa = new PessoaJuridica(); break;
			case 4: pessoa = new Funcionario(); break;
		default:
			System.out.println("Não conheço esse tipo de pessoa");
		}
		
		pessoa.mostraClasse();
	}

	private static void criarFuncionario() {
		Funcionario funcionario = new Funcionario();
		
		definirDadosFuncionario(funcionario);	
		
		imprimirDados(funcionario);
	}

	private static void definirDadosFuncionario(Funcionario funcionario) {
		funcionario.setNome("Luisa Stranghg");
		funcionario.setRG("132465798");
		funcionario.setCartao("132465");
	}

	private static void imprimirDados(Funcionario funcionario) {
		System.out.println(funcionario.getCartao()); 
		System.out.println(funcionario.getRG());
		System.out.println(funcionario.getNome());
	}
	
}
