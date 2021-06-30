package br.com.uninove.empresa;

public class Pessoa {
	
	private String nome;
	
	public Pessoa(String nome)
	{
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void mostraClasse()
	{
		System.out.println("Classe Pessoa");
	}
	
}
