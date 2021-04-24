package conceitosJava;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Conceitos {

	/**
	 * @author Prof. Luis Caparroz Santos
	 * Exemplo de uso de: 
	 * variáveis
	 * Estruturas de fluxo
	 * Estrutura de controle
	 * Entrada de dados
	 * 
	 */
	
	// Para saber mais
	// https://www.caelum.com.br/apostila-java-orientacao-objetos/variaveis-primitivas-e-controle-de-fluxo
	// http://java.meritcampus.com/core-java-topics/java-data-types
	// https://www.w3schools.com/java/default.asp
    public static void main(String[] args) {
    	/* Para pode usar chamar as funções, 
    	 * retire os comentários, salve e execute o
    	 * o programa. Todas as funções, estão presentes
    	 * neste mesmo arquivo    	
    	*/
    	
    	exemploEntrada();
    	//TiposDados.exemplosVariaveis();
    	//EstruturaRepeticao.exemploArrayMultiInt();
    	//EstruturaRepeticao.exemploForContinue();
    	//EstruturaRepeticao.exemploForLoop();
    	//EstruturaRepeticao.exemploForEachLoop();
    	//EstruturaRepeticao.exemploWhileLoop();
    	//EstruturaRepeticao.exemploDoWhile();
    	//EstruturaFluxo.exemploSwitch();
    	//exemploMedia();
    }
	    
    private static void exemploBreak() {
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
              break;
            }
            System.out.println(i);
        }
    }
    
    private static void exemploEntrada(){
        Scanner ler = new Scanner(System.in); // 2. instanciando e criando um objeto Scanner
        int a, b;

        System.out.printf("Informe o primeiro valor (Escreva aqui ->): ");
        a = ler.nextInt(); // 3. entrada de dados (lendo um valor inteiro)

        System.out.printf("Informe o segundo valor. (Escreva aqui ->): ");
        b = ler.nextInt(); // 3. entrada de dados (lendo um valor inteiro)

        System.out.printf("\nResultados:\n");
        System.out.printf("%d + %d = %3d\n", a, b, (a + b));
        System.out.printf("%d - %d = %3d\n", a, b, (a - b));
        System.out.printf("%d * %d = %3d\n", a, b, (a * b));
        System.out.printf("%d / %d = %3d (divisão inteira)\n", a, b, (a / b));
        System.out.printf("%d / %d = %6.2f (divisão exata)\n", a, b, ((double)a / b));
    }
    
    private static void exemploMedia() {
        double nota1, nota2;
        double media;
        
        nota1 = Double.parseDouble(JOptionPane.showInputDialog("Digite a primeira nota"));
        nota2 = Double.parseDouble(JOptionPane.showInputDialog("Digite a segunda nota"));
        
        media = (nota1 + nota2) / 2;
        
        JOptionPane.showMessageDialog(null, media);
    }
}
