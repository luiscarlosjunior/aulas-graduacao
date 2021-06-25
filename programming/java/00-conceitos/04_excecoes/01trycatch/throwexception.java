import java.io.*;

public class throwexception {

    public static void main(String[] args) {
        deposit(10.5);
    }

    public static void deposit(double amount) throws RemoteException {
        // Implementação método
        // Irá lançar uma exceção
        throw new RemoteException();
     }
}
