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
public class ExemploPOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cao cao = new Cao();
        Cao cao2 = new Cao();
                        
        cao.setCorOlhos("verdes");
        System.out.println(cao.getCorOlhos());
        cao.setQuantPatas(3);
        cao.andar();
        
        cao2.setCorOlhos("verdes");
        cao2.setQuantPatas(4);
        cao2.andar();
        
    }
    
}
