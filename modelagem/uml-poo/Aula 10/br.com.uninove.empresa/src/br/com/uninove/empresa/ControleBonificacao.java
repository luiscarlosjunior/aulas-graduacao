package br.com.uninove.empresa;

public class ControleBonificacao {
	private double soma;

	public double getSoma() {
		return soma;
	}
	
	public void registra(Funcionario f) 
	{
		double bonificacao = f.bonificacao();
		this.soma += bonificacao;
	}
}
