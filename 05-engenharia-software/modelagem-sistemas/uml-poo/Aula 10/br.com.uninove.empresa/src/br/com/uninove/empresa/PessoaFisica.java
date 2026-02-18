package br.com.uninove.empresa;

public class PessoaFisica extends Pessoa {

		private String rg;
		
		public PessoaFisica(String nome, String rg)
		{
			// Validações
			super(nome);
			setRG(rg);
		}
		
		public String getRG() {
			return rg;
		}

		private void setRG(String rg) {
			// Várias validações
			this.rg = rg;
		}
	
		public void mostraClasse()
		{
			System.out.println("Classe PessoaFisica");
		}
		
}
