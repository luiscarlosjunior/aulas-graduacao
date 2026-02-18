public class exemplogetmessage {
    public static void main(String[] args) {
        try {
            // Tentativa de divisão por zero
            int resultado = 10 / 0;
        } catch (ArithmeticException e) {
            // Usando getMessage() para obter a mensagem da exceção
            System.out.println("Mensagem da exceção: " + e.getMessage());
            
            // Usando printStackTrace() para mostrar o rastreamento completo
            System.out.println("\nRastreamento completo da pilha:");
            e.printStackTrace();
        }
        
        System.out.println("\nPrograma continua executando...");
    }
}