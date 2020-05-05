/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplojava;

import java.util.Scanner;

/**
 *
 * @author luisc
 */
public class ExemploRepeticao {
    public static void Exemplo01() {
        Scanner sc = new Scanner(System.in);
        
        int number;
        
        number = sc.nextInt();
        
        System.out.println("A tabuada do numero " + number + " é:");
        
        for (int i = 0; i < 10; i++) {
            System.out.println(number + " X " + (i+1) + " = " + (number * (i+1)));
        }
    }
    
    private static void Exemplo02() {
        int soma = 0;
                
        for (int i = 1; i <= 500; i++) {
            if (i%2 == 0) {
                soma = soma + i;
            }
        }
        
        // Outra forma de fazer é fazer que o salto do for seja de 2 em 2
        /*
        for (int i = 0; i <= 500; i += 2) {
                soma = soma + i;  
        }
        */
        
        System.out.println("A soma de 1 a 500 é de " + soma);
    }
   
}
