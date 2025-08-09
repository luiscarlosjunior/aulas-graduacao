import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Servidor HTTP simples usando Java SE
 * Demonstra conceitos básicos de servidor web
 * 
 * @author Apresentação Java Web
 */
public class ServidorHTTPSimples {
    
    private static Map<String, String> usuarios = new HashMap<>();
    private static int contadorRequisicoes = 0;
    
    public static void main(String[] args) throws IOException {
        // Inicializar dados de exemplo
        inicializarDados();
        
        // Criar servidor HTTP na porta 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Definir rotas/endpoints
        server.createContext("/", new HomeHandler());
        server.createContext("/usuarios", new UsuariosHandler());
        server.createContext("/status", new StatusHandler());
        server.createContext("/tempo", new TempoHandler());
        
        // Configurar executor padrão
        server.setExecutor(null);
        
        // Iniciar servidor
        server.start();
        
        System.out.println("=== Servidor HTTP Iniciado ===");
        System.out.println("URL: http://localhost:8080");
        System.out.println("Endpoints disponíveis:");
        System.out.println("  GET /           - Página inicial");
        System.out.println("  GET /usuarios   - Lista de usuários");
        System.out.println("  GET /status     - Status do servidor");
        System.out.println("  GET /tempo      - Data/hora atual");
        System.out.println("==============================");
        System.out.println("Pressione Ctrl+C para parar o servidor");
    }
    
    private static void inicializarDados() {
        usuarios.put("1", "João Silva");
        usuarios.put("2", "Maria Santos");
        usuarios.put("3", "Pedro Oliveira");
        usuarios.put("4", "Ana Costa");
    }
    
    // Handler para página inicial
    static class HomeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            contadorRequisicoes++;
            
            String resposta = criarPaginaHTML("Página Inicial", 
                "<h2>Bem-vindo ao Servidor Java!</h2>" +
                "<p>Este é um exemplo de servidor HTTP implementado em Java.</p>" +
                "<h3>Endpoints disponíveis:</h3>" +
                "<ul>" +
                "<li><a href='/usuarios'>/usuarios</a> - Lista de usuários</li>" +
                "<li><a href='/status'>/status</a> - Status do servidor</li>" +
                "<li><a href='/tempo'>/tempo</a> - Data/hora atual</li>" +
                "</ul>" +
                "<p>Total de requisições: " + contadorRequisicoes + "</p>");
            
            enviarResposta(exchange, 200, resposta, "text/html");
        }
    }
    
    // Handler para usuários
    static class UsuariosHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            contadorRequisicoes++;
            
            String metodo = exchange.getRequestMethod();
            
            if ("GET".equals(metodo)) {
                StringBuilder html = new StringBuilder();
                html.append("<h2>Lista de Usuários</h2>");
                html.append("<table border='1'>");
                html.append("<tr><th>ID</th><th>Nome</th></tr>");
                
                for (Map.Entry<String, String> entry : usuarios.entrySet()) {
                    html.append("<tr>");
                    html.append("<td>").append(entry.getKey()).append("</td>");
                    html.append("<td>").append(entry.getValue()).append("</td>");
                    html.append("</tr>");
                }
                
                html.append("</table>");
                html.append("<p><a href='/'>← Voltar à página inicial</a></p>");
                
                String resposta = criarPaginaHTML("Usuários", html.toString());
                enviarResposta(exchange, 200, resposta, "text/html");
            } else {
                enviarResposta(exchange, 405, "Método não permitido", "text/plain");
            }
        }
    }
    
    // Handler para status do servidor
    static class StatusHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            contadorRequisicoes++;
            
            Runtime runtime = Runtime.getRuntime();
            long memoriaTotal = runtime.totalMemory();
            long memoriaLivre = runtime.freeMemory();
            long memoriaUsada = memoriaTotal - memoriaLivre;
            
            String json = String.format(
                "{\n" +
                "  \"status\": \"ativo\",\n" +
                "  \"requisicoes\": %d,\n" +
                "  \"usuarios_cadastrados\": %d,\n" +
                "  \"memoria_total_mb\": %.2f,\n" +
                "  \"memoria_usada_mb\": %.2f,\n" +
                "  \"memoria_livre_mb\": %.2f,\n" +
                "  \"timestamp\": \"%s\"\n" +
                "}",
                contadorRequisicoes,
                usuarios.size(),
                memoriaTotal / (1024.0 * 1024.0),
                memoriaUsada / (1024.0 * 1024.0),
                memoriaLivre / (1024.0 * 1024.0),
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            );
            
            enviarResposta(exchange, 200, json, "application/json");
        }
    }
    
    // Handler para data/hora
    static class TempoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            contadorRequisicoes++;
            
            LocalDateTime agora = LocalDateTime.now();
            String dataHora = agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            
            String html = "<h2>Data e Hora Atual</h2>" +
                         "<p><strong>" + dataHora + "</strong></p>" +
                         "<p>Timestamp: " + System.currentTimeMillis() + "</p>" +
                         "<p><a href='/'>← Voltar à página inicial</a></p>";
            
            String resposta = criarPaginaHTML("Data/Hora", html);
            enviarResposta(exchange, 200, resposta, "text/html");
        }
    }
    
    private static String criarPaginaHTML(String titulo, String conteudo) {
        return "<!DOCTYPE html>\n" +
               "<html lang='pt-BR'>\n" +
               "<head>\n" +
               "    <meta charset='UTF-8'>\n" +
               "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
               "    <title>" + titulo + " - Servidor Java</title>\n" +
               "    <style>\n" +
               "        body { font-family: Arial, sans-serif; margin: 40px; }\n" +
               "        h1 { color: #333; }\n" +
               "        table { border-collapse: collapse; width: 100%; }\n" +
               "        th, td { padding: 8px; text-align: left; }\n" +
               "        th { background-color: #f2f2f2; }\n" +
               "    </style>\n" +
               "</head>\n" +
               "<body>\n" +
               "    <h1>" + titulo + "</h1>\n" +
               "    " + conteudo + "\n" +
               "    <hr>\n" +
               "    <small>Servidor HTTP Java - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "</small>\n" +
               "</body>\n" +
               "</html>";
    }
    
    private static void enviarResposta(HttpExchange exchange, int codigo, String resposta, String contentType) throws IOException {
        // Adicionar headers CORS para permitir requisições de diferentes origens
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Content-Type", contentType + "; charset=UTF-8");
        
        byte[] bytes = resposta.getBytes("UTF-8");
        exchange.sendResponseHeaders(codigo, bytes.length);
        
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
        
        // Log da requisição
        System.out.printf("[%s] %s %s - %d%n",
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
            exchange.getRequestMethod(),
            exchange.getRequestURI().getPath(),
            codigo
        );
    }
}