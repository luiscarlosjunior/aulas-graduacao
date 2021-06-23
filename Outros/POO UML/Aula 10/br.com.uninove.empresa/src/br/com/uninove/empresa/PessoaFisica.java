package br.com.uninove.empresa;

public class PessoaFisica extends Pessoa {

		private String RG;

		public String getRG() {
			return RG;
		}

		public void setRG(String rG) {
			RG = rG;
		}
	
		public void mostraClasse()
		{
			System.out.println("Classe PessoaFisica");
		}
		
}
