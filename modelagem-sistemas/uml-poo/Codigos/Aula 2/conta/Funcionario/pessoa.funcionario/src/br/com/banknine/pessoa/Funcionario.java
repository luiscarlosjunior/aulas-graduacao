package br.com.banknine.pessoa;

public class Funcionario extends PessoaFisica {
	
	private String cartao;

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}	
	
	public void ExibirInformacoes()
	{
		System.out.println("Nome do Funcionário: " + this.getNome());
		System.out.println("Número RG: " + this.getRg());
		System.out.println("Número Cartão: " + this.getCartao());	
	}
}
