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
public class POOExemplo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        CaoDomestico cd = new CaoDomestico();
        
        cd.nome = "Pluto";
        cd.corOlhos = "Azuis";
        cd.peso = 53;
        cd.quantPatas = 4;
        
        cd.latir();
    }
    
}
