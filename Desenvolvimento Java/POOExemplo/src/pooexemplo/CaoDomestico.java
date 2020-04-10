/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pooexemplo;

/**
 *
 * @author luisc
 */
public class CaoDomestico {
     
    public String nome;
    public int peso;
    public String corOlhos;
    public int quantPatas;
    
    public CaoDomestico()
    {
    }
     
    public CaoDomestico(String nome)
    {
        this.nome = nome;
    }
     
    public String getNome()
    {
        return "Nome do Curso retornado " + nome;
    }
    
    public void falar(){
        //MÉTODO FALAR
    }
     
    public void andar(){
        //MÉTODO ANDAR
    } 
     
    public void comer(){
        //MÉTODO COMER
    }
     
    public void dormir(){
        //MÉTODO DORMIR
    }
    
    void latir(){
        if(peso > 60)
            System.out.println("Wooof, Wooof!");
        else if(peso > 14)
            System.out.println("Ruff!, Ruff!");
        else
            System.out.println("Yip!, Yip!");
    }
}
