import javax.swing.*;

public class ControleFluxo {

	public static void main(String[] args) {
		//souMaior18Anos();
		//souMaior18AlturaMaior160();
		numeroPorExtenso();
	}
	
	public static void numeroPorExtenso() {
		
		String numero = JOptionPane.showInputDialog(null, "Forne�a um n�mero inteiro 0 a 9: ");
		int valor = Integer.parseInt(numero);
		
		String extenso = "";
		
		switch(valor)
		{
		case 0: 
			extenso = "zero";
			break;
		case 1: 
			extenso = "Um";
			break;
		case 2: 
			extenso = "Dois"; 
			break;
		case 3: 
			extenso = "Tr�s"; 
			break;
		case 4: 
			extenso = "Quatro"; 
			break;
		case 5: 
			extenso = "Cinco"; 
			break;
			default: extenso = "Digite entre 0 e 5";
		}
		
		System.out.println("O valor do n�mero por extenso � " + extenso);
		
	}
	
	public static void souMaior18AlturaMaior160Refatorado() {
		int idade = 15;
		float altura = 1.39f;
				
		boolean souMaior18Anos = idade >= 18; 
		boolean souMaior160 = altura >= 1.6;
		boolean resposta = souMaior18Anos && souMaior160;
		
		if(resposta)
			System.out.println("Voc� � maior de idade e altura maior que 1.60");
		else if ((souMaior18Anos) && (!souMaior160)) // curto-circuito
			System.out.println("Sou maior de Idade e menor que 1.60");
		else if ((!souMaior18Anos) && (souMaior160))
			System.out.println("Sou maior que 1.60, por�m sou menor de idade");
		else
			System.out.println("Voc� � menor de idade e altura menor que 1.60");
	}
		
	public static void souMaior18AlturaMaior160() {
		int idade = 15;
		float altura = 1.39f;
				
		boolean souMaior18Anos = idade >= 18; 
		boolean souMaior160 = altura >= 1.6;
		boolean resposta = souMaior18Anos && souMaior160;
		
		if(resposta) {
			System.out.println("Voc� � maior de idade e altura maior que 1.60");
		}
		else if ((souMaior18Anos == true) && (souMaior160 == false)) {
			System.out.println("Sou maior de Idade e menor que 1.60");
		}
		else if ((souMaior18Anos == false) && (souMaior160 == true)) {
			System.out.println("Sou maior que 1.60, por�m sou menor de idade");
		} 
		else {
			System.out.println("Voc� � menor de idade e altura menor que 1.60");
		}	
	}
	
	// M�todo 
	public static void souMaior18Anos() {
		int idade = 27;
		boolean souMaior18Anos = idade >= 18; 
				
		if(souMaior18Anos) {
			System.out.println("Voc� � maior de idade");
		} else {
			System.out.println("Voc� � menor de idade");
		}	
	}
}
