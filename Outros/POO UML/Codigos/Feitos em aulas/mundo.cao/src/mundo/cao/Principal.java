package mundo.cao;

public class Principal {

	public static void main(String[] args) {
		
		// Criar um objeto - Instânciando
		Cachorro dog = new Cachorro(); 
		
		dog.raca = "Poddle";
		dog.idade = 2;
		dog.corPelo = "Branco";
		dog.tipoPelo = "Ondulado";
		
		// Comportamento
		dog.Acordar();
		
		System.out.println("A minha raça é " 
						+ dog.raca);
		
	}
	
}
