import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Cliente Socket TCP simples
 * Conecta ao ServidorSocket e permite interação via linha de comando
 * 
 * @author Apresentação Java Web
 */
public class ClienteSocket {
    
    private static final String SERVIDOR_HOST = "localhost";
    private static final int SERVIDOR_PORTA = 9999;
    
    public static void main(String[] args) {
        System.out.println("=== Cliente Socket TCP ===");
        System.out.println("Conectando ao servidor " + SERVIDOR_HOST + ":" + SERVIDOR_PORTA);
        System.out.println("Certifique-se de que o ServidorSocket está rodando!");
        System.out.println();
        
        try (Socket socket = new Socket(SERVIDOR_HOST, SERVIDOR_PORTA);
             PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("Conectado com sucesso!");
            System.out.println("===========================");
            
            // Thread para receber mensagens do servidor
            Thread receptorMensagens = new Thread(() -> {
                try {
                    String mensagemServidor;
                    while ((mensagemServidor = entrada.readLine()) != null) {
                        System.out.println(mensagemServidor);
                    }
                } catch (IOException e) {
                    System.err.println("Conexão com servidor perdida: " + e.getMessage());
                }
            });
            
            receptorMensagens.setDaemon(true);
            receptorMensagens.start();
            
            // Aguardar mensagens iniciais do servidor
            Thread.sleep(1000);
            
            // Loop principal para enviar comandos
            System.out.println("\n=== Interface de Comandos ===");
            while (true) {
                System.out.print("> ");
                String comando = scanner.nextLine().trim();
                
                if (comando.isEmpty()) {
                    continue;
                }
                
                // Comandos especiais do cliente
                if (comando.equalsIgnoreCase("ajuda")) {
                    mostrarAjudaCliente();
                    continue;
                }
                
                if (comando.equalsIgnoreCase("testar")) {
                    executarTestesAutomaticos(saida);
                    continue;
                }
                
                if (comando.equalsIgnoreCase("sair")) {
                    saida.println("QUIT");
                    Thread.sleep(500); // Dar tempo para o servidor responder
                    break;
                }
                
                // Enviar comando para o servidor
                saida.println(comando);
                
                // Se foi QUIT, sair do loop
                if (comando.equalsIgnoreCase("QUIT")) {
                    Thread.sleep(500); // Dar tempo para o servidor responder
                    break;
                }
                
                // Pausa para permitir que a resposta seja exibida
                Thread.sleep(100);
            }
            
        } catch (IOException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
            System.err.println("Verifique se o servidor está rodando na porta " + SERVIDOR_PORTA);
        } catch (InterruptedException e) {
            System.err.println("Thread interrompida: " + e.getMessage());
        }
        
        System.out.println("\nCliente desconectado.");
    }
    
    private static void mostrarAjudaCliente() {
        System.out.println();
        System.out.println("=== Ajuda do Cliente ===");
        System.out.println("Comandos especiais do cliente:");
        System.out.println("  ajuda   - Mostrar esta ajuda");
        System.out.println("  testar  - Executar testes automáticos");
        System.out.println("  sair    - Desconectar do servidor");
        System.out.println();
        System.out.println("Comandos do servidor:");
        System.out.println("  HELP    - Ajuda do servidor");
        System.out.println("  TEMPO   - Data/hora atual");
        System.out.println("  CALC <num1> <op> <num2> - Calculadora");
        System.out.println("  ECHO <mensagem> - Repetir mensagem");
        System.out.println("  STATUS  - Status do servidor");
        System.out.println("  QUIT    - Desconectar");
        System.out.println();
        System.out.println("Exemplos:");
        System.out.println("  CALC 15 + 25");
        System.out.println("  CALC 100 / 4");
        System.out.println("  ECHO Olá servidor!");
        System.out.println("========================");
    }
    
    private static void executarTestesAutomaticos(PrintWriter saida) {
        System.out.println();
        System.out.println("=== Executando Testes Automáticos ===");
        
        String[] comandosTeste = {
            "HELP",
            "TEMPO",
            "STATUS",
            "ECHO Teste automático do cliente",
            "CALC 10 + 5",
            "CALC 20 - 8",
            "CALC 7 * 6",
            "CALC 100 / 4",
            "CALC 10 / 0",  // Teste de divisão por zero
            "CALC abc + 5", // Teste de entrada inválida
            "COMANDO_INEXISTENTE"
        };
        
        for (String comando : comandosTeste) {
            System.out.println("Enviando: " + comando);
            saida.println(comando);
            
            try {
                Thread.sleep(1000); // Pausa entre comandos para ver as respostas
            } catch (InterruptedException e) {
                System.err.println("Teste interrompido: " + e.getMessage());
                break;
            }
        }
        
        System.out.println("=== Testes Concluídos ===");
    }
}