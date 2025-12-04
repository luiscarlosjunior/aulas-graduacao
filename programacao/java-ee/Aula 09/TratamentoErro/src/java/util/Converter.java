/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author luisc
 */
public class Converter {
    
    private float peso;
    private float altura;
    
     /**
     * @return the peso
     */
    private float getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }

    /**
     * @return the altura
     */
    private float getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(float altura) {
        this.altura = altura;
    }
    
    public float retornarIMC() throws Exception {
        
        float resultado = 0.0f;
        
        try {
            resultado = getPeso() / (getAltura() * getAltura());
        } catch (Exception e) {
            throw new Exception();
        } 
        
        return resultado;
    }
            
}
