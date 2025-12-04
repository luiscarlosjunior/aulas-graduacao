package br.com.uninove.empresa;

public abstract class Funcionario extends PessoaFisica {
	private String cartao;
	private double salario;
		
	public Funcionario(String nome, String rg, String cartao, double salario) {
		super(nome, rg);
		this.cartao = cartao;
		this.salario = salario;
	}
	
	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}
	
	public double getSalario() {
		return salario;
	}

	protected double bonificacao() {
		return this.salario * 0.1;
	}
	
	public abstract void mostrarTipoClasse();
	
}
