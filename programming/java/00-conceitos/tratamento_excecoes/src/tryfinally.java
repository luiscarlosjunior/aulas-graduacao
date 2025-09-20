import java.io.*;

public class tryfinally {
    
    public static void main(String args[]) {
        int a[] = new int[2];
        try {
           System.out.println("Acessando o elemento :" + a[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
           System.out.println("Exceção lançada:" + e);
        } finally {
           a[0] = 6;
           System.out.println("Primeiro valor do elemento: " + a[0]);
           System.out.println("A declaração finally foi executada");
        }
     }

}
