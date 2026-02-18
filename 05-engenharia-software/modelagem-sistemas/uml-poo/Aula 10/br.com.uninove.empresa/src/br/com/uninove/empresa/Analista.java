package br.com.uninove.empresa;

public class Analista extends Funcionario {

	public Analista(String nome, String rg, String cartao, double salario) {
		super(nome, rg, cartao, salario);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void mostrarTipoClasse() {
		System.out.println("Estou na classe Analista");
	}
		
}
