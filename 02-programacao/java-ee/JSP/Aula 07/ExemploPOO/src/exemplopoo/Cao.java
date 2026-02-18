/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplopoo;

/**
 *
 * @author luisc
 */

public class Cao extends Caninos{
    
    public String nome;
    
    public void falar(){
        //MÉTODO FALAR
    }
     
    public void andar(){
        if (getQuantPatas() == 4) {
            System.out.println("Andando 10 centimetros");
        } else {
            System.out.println("Andando 5 centimetros");
        }
        
    } 
     
    public void comer(){
        //MÉTODO COMER
    }
     
    public void dormir(){
        //MÉTODO DORMIR
    }
    
}
