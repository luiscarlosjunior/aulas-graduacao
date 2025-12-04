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
public class ExemploTipoConversao {
    
    public static void ExemploConversaoTipo() {
        int a = 10;
        float b = 10.5f;
        String c = "10";
        
        // Int para float
        float y = (float)a;
        
        // Int para double
        double z = (double)a;
        
        // String para int
        int d = Integer.parseInt(c);
        
        // String para float
        float e = Float.parseFloat(c);
        
        // String para vetor de bytes
        byte f[] = c.getBytes();
        
        // Int para string
        c = String.valueOf(d);
        c = String.valueOf(e);
        
        // byte para String
        c = new String(f);   
    }
}
