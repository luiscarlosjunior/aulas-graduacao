import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class trycatchmessage {
    public static void main(String args[]) {
        FileReader fr = null;		
        // Aqui irá ocorre uma exceção, caso o arquivo não exista
        try {
           File file = new File("file.txt");
           fr = new FileReader(file); char [] a = new char[50];
           fr.read(a);   // Lê o contepudo do array
           for(char c : a)
           System.out.print(c);   // prints os caracteres um por um
        } catch (IOException e) {
            // Exibe uma mensagem da pilha de chamada 
           e.printStackTrace();
        }finally {
           try {
              fr.close();
           } catch (IOException ex) {		
              ex.printStackTrace();
           }
        }
     }
}
