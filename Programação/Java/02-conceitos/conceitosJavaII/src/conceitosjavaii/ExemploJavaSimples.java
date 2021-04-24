/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package exemplojavasimples;

import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author luisc
 */
public class ExemploJavaSimples {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
       // exemploEntrada();
       // media();
       exemplosVariaveis();
       
    }

    private static void executarConceitos()
    {
      do
      {

      } while(true)
    }

    private static void exemploBreak() {
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
              break;
            }
            System.out.println(i);
        }
    }
    
    private static void exemploArrayMultiInt() {
        int[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };
        for (int i = 0; i < myNumbers.length; ++i) {
          for(int j = 0; j < myNumbers[i].length; ++j) {
            System.out.println(myNumbers[i][j]);
          }
        }
    }
    
    private static void exemploForContinue() {
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
              continue;
            }
            System.out.println(i);
        }
    }
    
    private static void exemploForLoop() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }     
    }
    
    private static void exemploForEachLoop() {
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        for (String i : cars) {
          System.out.println(i);
        }
    }
    
    private static void exemploWhileLoop() {
        int i = 0;
        while (i < 5) {
          System.out.println(i);
          i++;
        }  
    }
    
    private static void exemploDoWhile() {
        int i = 0;
        do {
          System.out.println(i);
          i++;
        }
        while (i < 5);
    }
    
    private static void exemploSwitch(){
        int day = 4;
        switch (day) {
          case 1:
            System.out.println("Monday");
            break;
          case 2:
            System.out.println("Tuesday");
            break;
          case 3:
            System.out.println("Wednesday");
            break;
          case 4:
            System.out.println("Thursday");
            break;
          case 5:
            System.out.println("Friday");
            break;
          case 6:
            System.out.println("Saturday");
            break;
          case 7:
            System.out.println("Sunday");
            break;
        }
    }
    
    private static void exemplosVariaveis(){
        int meuNum = 5;               // integer (whole number)
        float meuFloatNum = 5.99f;    // floating point number
        char minhaLetra = 'D';         // character
        boolean meuBool = true;       // boolean
        String meuTexto = "Hello";     // String    
        System.out.println(meuNum);
        System.out.println(meuFloatNum);
        System.out.println(minhaLetra);
        System.out.println(meuBool);
        System.out.println(meuTexto);
    }
    
    private static void exemploEntrada(){
        Scanner ler = new Scanner(System.in); // 2. instanciando e criando um objeto Scanner
        int a, b;

        System.out.printf("Informe o primeiro valor: ");
        a = ler.nextInt(); // 3. entrada de dados (lendo um valor inteiro)

        System.out.printf("Informe o segundo valor.: ");
        b = ler.nextInt(); // 3. entrada de dados (lendo um valor inteiro)

        System.out.printf("\nResultados:\n");
        System.out.printf("%d + %d = %3d\n", a, b, (a + b));
        System.out.printf("%d - %d = %3d\n", a, b, (a - b));
        System.out.printf("%d * %d = %3d\n", a, b, (a * b));
        System.out.printf("%d / %d = %3d (divisão inteira)\n", a, b, (a / b));
        System.out.printf("%d / %d = %6.2f (divisão exata)\n", a, b, ((double)a / b));
    }
    
    private static void media() {
        double nota1, nota2;
        double media;
        
        nota1 = Double.parseDouble(JOptionPane.showInputDialog("Digite a primeira nota"));
        nota2 = Double.parseDouble(JOptionPane.showInputDialog("Digite a segunda nota"));
        
        media = (nota1 + nota2) / 2;
        
        JOptionPane.showMessageDialog(null, media);
    }
}
