package br.com.uninove.empresa;

public class PessoaJuridica extends Pessoa {
		
	private String cnpj;

	public PessoaJuridica(String nome, String cnpj) {
		super(nome);
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void mostraClasse()
	{
		System.out.println("Classe PessoaJuridica");
	}
	
}
