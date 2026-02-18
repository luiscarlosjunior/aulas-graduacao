package br.com.uninove.empresa;

public class Gerente extends Funcionario implements IFuncionario {
	private String senha;

	public Gerente(String nome, String rg, String cartao, double salario, String senha) {
		super(nome, rg, cartao, salario);
		this.senha = senha;
	}

	private String getSenha() {
		return senha;
	}
	
	private void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean alterarSenha(String senha) {
		// Validações e verificações
		setSenha(senha);
		
		return true;
	}
	
	@Override
	protected double bonificacao() {
		double bonificacao15cento = 
				super.getSalario() * 0.15;
		return super.bonificacao() + bonificacao15cento;
	}
	
	@Override
	public void mostrarTipoClasse() {
		System.out.println("Sou a classe Gerente");
	}

	@Override
	public void apelido(String apelido) {
		// TODO Auto-generated method stub
		System.out.println("Meu apelido é " + apelido);
	}

	@Override
	public void idade(int idade) {
		// TODO Auto-generated method stub
		System.out.println("Minha idade é " + idade);
	}
}
