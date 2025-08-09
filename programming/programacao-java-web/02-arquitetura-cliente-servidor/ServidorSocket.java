import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Servidor Socket TCP simples
 * Demonstra comunicação de baixo nível cliente-servidor
 * 
 * @author Apresentação Java Web
 */
public class ServidorSocket {
    
    private static final int PORTA = 9999;
    private static boolean servidorAtivo = true;
    private static int contadorClientes = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Servidor Socket TCP ===");
        System.out.println("Porta: " + PORTA);
        System.out.println("Aguardando conexões...");
        System.out.println("Pressione Ctrl+C para parar");
        System.out.println("===========================");
        
        // Pool de threads para atender múltiplos clientes
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            
            while (servidorAtivo) {
                try {
                    // Aceitar conexão do cliente
                    Socket clienteSocket = serverSocket.accept();
                    contadorClientes++;
                    
                    // Processar cliente em thread separada
                    executorService.submit(new ClienteHandler(clienteSocket, contadorClientes));
                    
                } catch (IOException e) {
                    if (servidorAtivo) {
                        System.err.println("Erro ao aceitar conexão: " + e.getMessage());
                    }
                }
            }
            
        } catch (IOException e) {
            System.err.println("Erro ao iniciar servidor: " + e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
    
    /**
     * Handler para processar cada cliente conectado
     */
    static class ClienteHandler implements Runnable {
        private Socket clienteSocket;
        private int clienteId;
        private PrintWriter saida;
        private BufferedReader entrada;
        
        public ClienteHandler(Socket socket, int id) {
            this.clienteSocket = socket;
            this.clienteId = id;
        }
        
        @Override
        public void run() {
            String enderecoCliente = clienteSocket.getInetAddress().getHostAddress();
            
            System.out.printf("[%s] Cliente %d conectado de %s%n",
                obterTimestamp(), clienteId, enderecoCliente);
            
            try {
                // Configurar streams de comunicação
                entrada = new BufferedReader(
                    new InputStreamReader(clienteSocket.getInputStream())
                );
                saida = new PrintWriter(
                    clienteSocket.getOutputStream(), true
                );
                
                // Enviar mensagem de boas-vindas
                saida.println("=== Servidor Socket TCP ===");
                saida.println("Cliente ID: " + clienteId);
                saida.println("Endereço: " + enderecoCliente);
                saida.println("Conectado em: " + obterTimestamp());
                saida.println();
                saida.println("Comandos disponíveis:");
                saida.println("  TEMPO - Obter data/hora atual");
                saida.println("  CALC <num1> <operador> <num2> - Calculadora");
                saida.println("  ECHO <mensagem> - Repetir mensagem");
                saida.println("  STATUS - Status do servidor");
                saida.println("  HELP - Mostrar ajuda");
                saida.println("  QUIT - Desconectar");
                saida.println("Digite um comando:");
                
                // Loop de processamento de comandos
                String comandoCliente;
                while ((comandoCliente = entrada.readLine()) != null) {
                    comandoCliente = comandoCliente.trim();
                    
                    if (comandoCliente.equalsIgnoreCase("QUIT")) {
                        saida.println("Desconectando... Até logo!");
                        break;
                    }
                    
                    String resposta = processarComando(comandoCliente);
                    saida.println(resposta);
                    saida.println("Digite outro comando (ou QUIT para sair):");
                }
                
            } catch (IOException e) {
                System.err.printf("[%s] Erro na comunicação com cliente %d: %s%n",
                    obterTimestamp(), clienteId, e.getMessage());
            } finally {
                fecharConexao();
            }
        }
        
        private String processarComando(String comando) {
            String[] partes = comando.split("\\s+");
            String cmd = partes[0].toUpperCase();
            
            switch (cmd) {
                case "TEMPO":
                    return "Data/Hora atual: " + obterTimestamp();
                
                case "CALC":
                    return processarCalculadora(partes);
                
                case "ECHO":
                    if (partes.length > 1) {
                        return "Echo: " + comando.substring(5);
                    } else {
                        return "Uso: ECHO <mensagem>";
                    }
                
                case "STATUS":
                    Runtime runtime = Runtime.getRuntime();
                    long memoriaTotal = runtime.totalMemory() / (1024 * 1024);
                    long memoriaLivre = runtime.freeMemory() / (1024 * 1024);
                    return String.format(
                        "Status do Servidor:\n" +
                        "  Clientes atendidos: %d\n" +
                        "  Memória total: %d MB\n" +
                        "  Memória livre: %d MB\n" +
                        "  Tempo ativo: %s",
                        contadorClientes, memoriaTotal, memoriaLivre, obterTimestamp()
                    );
                
                case "HELP":
                    return "Comandos disponíveis:\n" +
                           "  TEMPO - Data/hora atual\n" +
                           "  CALC <num1> <op> <num2> - Calculadora (+, -, *, /)\n" +
                           "  ECHO <msg> - Repetir mensagem\n" +
                           "  STATUS - Status do servidor\n" +
                           "  HELP - Esta ajuda\n" +
                           "  QUIT - Desconectar";
                
                default:
                    return "Comando não reconhecido: " + cmd + "\nDigite HELP para ver os comandos disponíveis.";
            }
        }
        
        private String processarCalculadora(String[] partes) {
            if (partes.length != 4) {
                return "Uso: CALC <numero1> <operador> <numero2>\nExemplo: CALC 10 + 5";
            }
            
            try {
                double num1 = Double.parseDouble(partes[1]);
                String operador = partes[2];
                double num2 = Double.parseDouble(partes[3]);
                double resultado;
                
                switch (operador) {
                    case "+":
                        resultado = num1 + num2;
                        break;
                    case "-":
                        resultado = num1 - num2;
                        break;
                    case "*":
                        resultado = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            return "Erro: Divisão por zero!";
                        }
                        resultado = num1 / num2;
                        break;
                    default:
                        return "Operador inválido: " + operador + "\nUse: +, -, *, /";
                }
                
                return String.format("Resultado: %.2f %s %.2f = %.2f", num1, operador, num2, resultado);
                
            } catch (NumberFormatException e) {
                return "Erro: Números inválidos. Use apenas números.";
            }
        }
        
        private void fecharConexao() {
            try {
                System.out.printf("[%s] Cliente %d desconectado%n",
                    obterTimestamp(), clienteId);
                
                if (entrada != null) entrada.close();
                if (saida != null) saida.close();
                if (clienteSocket != null) clienteSocket.close();
                
            } catch (IOException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
    
    private static String obterTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}