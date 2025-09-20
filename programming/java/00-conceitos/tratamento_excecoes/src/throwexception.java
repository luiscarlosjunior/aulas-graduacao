import java.io.*;
import java.rmi.RemoteException;

public class throwexception {

    public static void main(String[] args) {
        try {
            deposit(10.5);
        } catch (RemoteException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }
    }

    public static void deposit(double amount) throws RemoteException {
        // Implementação método
        // Irá lançar uma exceção
        throw new RemoteException("Erro na operação de depósito");
     }
}
