
public class ManipulacaoTexto {

	// Objetivo é o controle do fluxo
	public static void main(String[] args) {
		
		String frase = "Gosto de aprender java, c# e o que vier";
		String nome = "Luis Carlos";
		
		int tamanho;
		tamanho = frase.length();
		
		// System.out.println("O tamanho da frase é " + frase.length());
		// System.out.println("O tamanho da frase é " + tamanho);
		
		// System.out.println("" + frase.charAt(0));
		// System.out.println("" + frase.indexOf("j", 10));
		// System.out.println("" + frase.indexOf(" ", 0));
		
		int primeiroEspaco = nome.indexOf(" ", 0);
		System.out.println("" + nome.substring(primeiroEspaco));
		
	}

}
