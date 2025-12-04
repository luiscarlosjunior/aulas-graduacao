/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correcaoexerciciosjava;

import java.util.Scanner;

/**
 *
 * @author luisc
 */
public class CorrecaoExerciciosJava {

    public static void main(String[] args) {
        // Exercicio1D();
        // Exercicio3B();
        // Exercicio3C();
           Exercicio3D();
    }
 
    private static void Exercicio1D() {
        // Criei uma instância
        int valorA;
        int valorB;
        int aux;
        
        // Inicializei a variável
        valorA = 10;
        valorB = 20;
        
        aux = valorA;
        
        valorA = valorB;
        valorB = aux;
        
        System.out.println("O valor A é " + valorA + 
                "\nO valor B é " + valorB);
    }
    
    private static void Exercicio3B() {
        int soma = 0;
        
        // 1° maneira
        /*
        for (int i = 0; i <= 500; i++) {
            if (i % 2 == 0) {
                soma += i;
            }
        }
        */
        
        for (int i = 0; i <= 500; i += 2) {
            soma += i;
        }
        
        System.out.println("A soma dos pares existentes" + 
                "entre 1 a 500 é: " + soma);
    }

    private static void Exercicio3C() {
        Scanner sc = new Scanner(System.in);
        
        int numero = 0;
        int maior = 0;
        int menor = 0;
        
        while(numero >= 0) {
            
            System.out.println("Informe um número");
            numero = sc.nextInt();
            
            if (numero < 0) {
                break;
            }
            
            if (numero > maior) {
                maior = numero;
            } else if (numero < menor || menor == 0) {
                menor = numero;
            }
        }
        
        System.out.println("O mairo número é: " + maior + 
                "\nO menor numero é: " + menor);
    }
    
    private static void Exercicio3D() {
        int fat = 1;
        int v;
        // 1 3 5 7 9
        for (int i = 1; i < 9; i += 2) {
            v = i;
            while(v > 1) {
                fat *= v;
                v--;
            }
        }
        
        System.out.println("Fatorial do impares entre 1 e 10 é: " 
                + fat);
    }
    
}
