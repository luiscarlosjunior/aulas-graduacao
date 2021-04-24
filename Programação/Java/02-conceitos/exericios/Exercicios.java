package exericios;

// Importar o pacote javauitil
import java.util.Scanner;
/**
 *
 * @author luiscaparroz
 */

 public class Exercicios {

    public static void main(String[] args) {
        // Instanciar e criar um objeto Scanner usando padrão de entrada System.in
        //ativ01();
        ativ03d();
        
    }
    
    private void ativ01() {
        Scanner ler = new Scanner(System.in);
        
        double C = 0.0;
        double F = 0.0;
        
        System.out.println("Informe o valor em graus Celsius");
        C = ler.nextDouble();
        
        F = (9*C+160)/5;
        
        System.out.println("Fahrenheit: " + F);
    }
    
    private static void ativ02a() {
        int A, B;
        
        A = 10;
        B = 20;
        
        if (A > B) {
            System.out.println("A diferença entre o A e o B é de " + (A-B));
        }
        else {
            System.out.println("A diferença entre o B e o A é de " + (B-A));
        }
    }
    
    private static void ativ02b(){
        Scanner sc = new Scanner(System.in);
        
        int number;
        
        System.out.println("Informe um valor");
        number = sc.nextInt();
        
        if (number < 0) {
            System.out.println("O módulo do número: |" + 
                    number + "| é " + (number * -1));
        }
        else
        {
            System.out.println("O módulo do número: |" + 
                number + "| é " + number);
        }
    }

    private static void ativ02c() {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        
        System.out.println("Entre com um valor entre 1 e 9");
        try {
            number = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Só é permitido valores numericos");
        }
                
        if (number >= 1 && number < 9) {
            System.out.println("O valor está na faixa permitida");
        }
        else
        {
            System.out.println("O valor não está na faixa permitida");
        }
    }
    
    private static void ativ02d() {
        
        int A, B, C;
        
        A = -10;
        B = 50; 
        C = 0;
        
        if (A <= B && A <= C) {
            if (B <= C) {
                System.out.format("%d, %d, %d", A, B, C);
            }
            else
            {
                System.out.format("%d, %d, %d", A, C, B);
            }
        } else if (B <= A && B <= C) {
            if (A <= C) {
                System.out.format("%d, %d, %d", B, A, C);
            }
            else
            {
                System.out.format("%d, %d, %d", B, C, A);
            }
        }else if (C <= A && C <= B) {
            if (A <= B) {
                System.out.format("%d, %d, %d", C, A, B);
            }
            else
            {
                System.out.format("%d, %d, %d", C, B, A);
            }
        }
    }
    
    private static void ativ02e() {
        Scanner sc = new Scanner(System.in);
        
        int A, B, C, D;
        
        System.out.println("Entre com um valor");
        A = sc.nextInt();
        System.out.println("Entre com um valor");
        B = sc.nextInt();
        System.out.println("Entre com um valor");
        C = sc.nextInt();
        System.out.println("Entre com um valor");
        D = sc.nextInt();
        
        System.out.println("Os vsalore validos são");
        
        if (!(A%2 != 0 && A%3 != 0)) {
            System.out.println(A);
        }
        if (!(B%2 != 0 && B%3 != 0)) {
            System.out.println(B);
        }
        if (!(C%2 != 0 && C%3 != 0)) {
            System.out.println(C);
        }
        if (!(D%2 != 0 && D%3 != 0)) {
            System.out.println(D);
        }
    }
    
    private static void ativ03a() {
        Scanner sc = new Scanner(System.in);
        
        int number;
        
        number = sc.nextInt();
        
        System.out.println("A tabuada do numero " + number + " é:");
        
        for (int i = 0; i < 10; i++) {
            System.out.println(number + " X " + (i+1) + " = " + (number * (i+1)));
        }
    }
    
    private static void ativ03c() {
        Scanner sc = new Scanner(System.in);
        int x, number = 0, maior = 0, menor = 0;
        
        while(number >= 0)
        {
            System.out.println("Informe um numero");
            number = sc.nextInt();
            
            if (number < 0) {
                break;
            }
            
            if (number > maior) {
                maior = number;
            } else if (number <= menor){
                menor = number;
            }
        }
        
        System.out.println("O maior numero é: " + maior + "\nO menor numero é: " + menor);
    }
    
    private static void ativ03d() {
        int fat = 1;
        int j;
        
        for (int i = 1; i < 10; i+= 2) {
            j = i;
            while(j > 1) {
                fat *= j;
                j--;
            }
            System.out.println("Fatorial de i é " + fat);
            fat = 1;
        }
    }
}