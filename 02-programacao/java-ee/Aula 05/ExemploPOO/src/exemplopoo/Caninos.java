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
public class Caninos {
    
    // Atributos
    private int peso;
    private String corOlhos;
    private int quantPatas;
    
    // Encapsulamento
    public int getQuantPatas() {
        return quantPatas;
    }

    public void setQuantPatas(int quantPatas) {
        if(quantPatas > 4) {
            this.quantPatas = 4;
        }
        else {
            this.quantPatas = quantPatas;
        }        
    }
    
    public String getCorOlhos() {
        return corOlhos;
    }

    public void setCorOlhos(String corOlhos) {
        this.corOlhos = corOlhos;
    }
}
