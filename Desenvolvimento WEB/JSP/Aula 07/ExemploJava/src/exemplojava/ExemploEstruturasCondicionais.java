/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplojava;

import java.util.Scanner;

public class ExemploEstruturasCondicionais {
    
    private static void Exemplo01() {
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
    
    private static void Exemplo02(){
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
}
