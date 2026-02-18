
public class Matematica {

	public static void main(String[] args) {
		// metodoMaxMin();
		// metodoArredondamento();	
		// metodoRaizQuadradaPotencia();
		// metodoPegarNumeroAleatorio();
	}

	private static void metodoPegarNumeroAleatorio() {
		double numeroMegaSena1 = Math.random() * 100;
		int numeroMegaSena2 = (int)(Math.random() * 100);
		
		System.out.println(numeroMegaSena1);
		System.out.println(numeroMegaSena2);
	}

	private static void metodoRaizQuadradaPotencia() {
		double numero1 = 900, numero2 = 12;
		double base = 9, potencia = 5;
		
		System.out.println("A raiz quadrada de 900 é " + Math.sqrt(numero1));
		System.out.println("A raiz quadrada de 12 é " + Math.sqrt(numero2));
		
		System.out.println("O numero 9 elevado a 5 é : " + Math.pow(base, potencia));
	}

	private static void metodoMaxMin() {
		double numero1 = 5.20, numero2 = 6.88, numero3 = 8.96;
		
		System.out.println("O maior valor entre o numero1 e numero2 é : " + 
		Math.max(numero1, numero2));

		System.out.println("O maior valor entre o numero2 e numero3 é : " + 
		Math.max(numero2, numero3));
		
		System.out.println("O menor valor entre o numero1 e numero2 é : " + 
				Math.min(numero1, numero2));

				System.out.println("O menor valor entre o numero2 e numero3 é : " + 
				Math.min(numero2, numero3));
	}

	private static void metodoArredondamento() {
		double numero1 = 5.20, numero2 = 6.88, numero3 = 8.96;
		double valorGasolina = 5.784;
		
		System.out.println(String.format("%.2f", valorGasolina));
		
		System.out.println("Arredondamento para baixo 5.78 = " +  Math.floor(numero1) );
		System.out.println("Arredondamento para baixo 6.88 = " +  Math.floor(numero2) );
		System.out.println("Arredondamento para baixo 8.96 = " +  Math.floor(numero3) );

		System.out.println("Arredondamento para cima 5.78 = " +  Math.ceil(numero1) );
		System.out.println("Arredondamento para cima 6.88 = " +  Math.ceil(numero2) );
		System.out.println("Arredondamento para cima 8.96 = " +  Math.ceil(numero3) );
	}
	
}
