/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplojava;

/**
 *
 * @author luisc
 */
public class OperadoresRelacionais {
    
    public static void ExemploRelacionais() {
        // Os operadores relacionais possibilitam comparar valores ou expressões, retornando um resultado
        // lógico verdadeiro ou falso
        boolean entrada = true;
        boolean resultado;
        
        System.out.println("Exemplo de operades relacionais");
        System.out.println("Entrada: " + entrada);
        
        resultado = 10 > 10;
        System.out.println("Resultado se 10 > 10: " + resultado);
        resultado = 10 >= 10;
        System.out.println("Resultado se 10 >= 10: " + resultado);
        resultado = 10 < 10;
        System.out.println("Resultado se 10 < 10: " + resultado);
        resultado = 10 <= 10;
        System.out.println("Resultado se 10 <= 10: " + resultado);
        resultado = 10 == 10;
        System.out.println("Resultado se 10 == 10: " + resultado);
        resultado = 10 != 10;
        System.out.println("Resultado se 10 != 10: " + resultado);
        System.out.println("\n\n");
    }
    
    public static void ExemploOperadoresLogicos() {
        //São operadores que permitem avaliar o resultado lógico de diferentes operações aritméticas em
        // uma expressão
        boolean resultado;
        
        resultado = 10 == 10 && 10 >= 10;
        System.out.println("Resultado se 10 == 10 E 10 >= 10: " + resultado);
        resultado = 10 > 10 && 10 == 10;
        System.out.println("Resultado se 10 > 10 E 10 == 10: " + resultado);
        resultado = 10 >= 10 || 10 <= 10;
        System.out.println("Resultado se 10 >= 10 OU 10 < 15: " + resultado);
        resultado = 10 > 10 || 10 < 10;
        System.out.println("Resultado se 10 >= 10 OU 10 < 10: " + resultado);
        resultado = 10 == 10;
        System.out.println("Resultado se 10 == 10: " + resultado);
        resultado = !(10 == 10);
        System.out.println("Resultado se NOT 10 == 10: " + resultado);
        
    }
    
}
