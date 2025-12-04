import java.io.IOException;

public class exemplothrows {
    public static void main(String[] args) {
        try {
            // Chamando método que pode lançar exceção
            lerArquivo();
        } catch (IOException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }
        
        System.out.println("Programa terminou normalmente.");
    }
    
    // Método que declara que pode lançar IOException usando throws
    public static void lerArquivo() throws IOException {
        // Simulando uma operação que pode gerar IOException
        throw new IOException("Arquivo não encontrado");
    }
}