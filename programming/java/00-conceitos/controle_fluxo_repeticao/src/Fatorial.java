public class Fatorial {

/**
 * @author luiscaparroz
 */

	public static void main(String[] args) {
		int fatorial = 1;
		for (int i = 1; i < 11; i++) {
			fatorial *= i;
			System.out.println("Fatorial de " + i + " = " + fatorial);
		}
	}
}