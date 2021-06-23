package br.com.uninove.empresa;

public class Funcionario extends PessoaFisica {
	
	private String cartao;

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}
	
	public void mostraClasse()
	{
		System.out.println("Classe Funcionario");
	}
	
}
