/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exempoclasse;

/**
 *
 * @author luisc
 */
public class Principal {
	
	// Principal fun��o da aplica��o
    public static void main(String[] args) {
    	// Vamos criar um novo cachorro do tipo C�o Domestico
        CaoDomestico cd = new CaoDomestico();
        
        cd.nome = "Pluto";
        cd.corOlhos = "Azuis";
        cd.peso = 53;
        cd.quantPatas = 4;
        
        cd.latir();
    }
    
}
