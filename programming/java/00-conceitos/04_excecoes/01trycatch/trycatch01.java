import java.io.*;

/*
   O método try-catch tenta capturar a exceção que será lançada
   para isso o Java trabalha com uma série de classes que tratam das excessões mais comuns
*/

public class trycatch01 {

   public static void main(String args[]) {
      try {
         int a[] = new int[2];
         System.out.println("Access element three :" + a[3]);
      } catch (ArrayIndexOutOfBoundsException e) {
         System.out.println("lançando a exceção  :" + e);
      }
      System.out.println("Fora do block");
   }
}