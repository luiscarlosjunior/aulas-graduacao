import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * RestServer - Servidor REST simples implementado com Java SE
 * Demonstra uma API REST completa para gerenciar usuários
 * 
 * @author Apresentação Java Web
 */
public class RestServer {
    
    private static Map<Long, Usuario> usuarios = new ConcurrentHashMap<>();
    private static AtomicLong proximoId = new AtomicLong(1);
    
    public static void main(String[] args) throws IOException {
        // Dados iniciais
        inicializarDados();
        
        // Criar servidor HTTP
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Configurar rotas REST
        server.createContext("/api/usuarios", new UsuariosHandler());
        server.createContext("/api/usuarios/", new UsuarioHandler());
        server.createContext("/health", new HealthHandler());
        server.createContext("/", new DocumentacaoHandler());
        
        server.setExecutor(null);
        server.start();
        
        System.out.println("=== Servidor REST Iniciado ===");
        System.out.println("URL: http://localhost:8080");
        System.out.println("Documentação: http://localhost:8080");
        System.out.println("Health Check: http://localhost:8080/health");
        System.out.println();
        System.out.println("Endpoints disponíveis:");
        System.out.println("GET    /api/usuarios           - Listar usuários");
        System.out.println("POST   /api/usuarios           - Criar usuário");
        System.out.println("GET    /api/usuarios/{id}      - Buscar usuário");
        System.out.println("PUT    /api/usuarios/{id}      - Atualizar usuário");
        System.out.println("DELETE /api/usuarios/{id}      - Deletar usuário");
        System.out.println("===============================");
        System.out.println("Pressione Ctrl+C para parar");
    }
    
    private static void inicializarDados() {
        usuarios.put(1L, new Usuario(1L, "João Silva", "joao@teste.com", "USER"));
        usuarios.put(2L, new Usuario(2L, "Maria Santos", "maria@teste.com", "ADMIN"));
        usuarios.put(3L, new Usuario(3L, "Pedro Oliveira", "pedro@teste.com", "USER"));
        proximoId.set(4);
    }
    
    // Handler para coleção de usuários (/api/usuarios)
    static class UsuariosHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            
            enableCORS(exchange);
            
            switch (method) {
                case "GET":
                    listarUsuarios(exchange);
                    break;
                case "POST":
                    criarUsuario(exchange);
                    break;
                case "OPTIONS":
                    enviarResponse(exchange, 200, "", "text/plain");
                    break;
                default:
                    enviarResponse(exchange, 405, 
                        "{\"error\": \"Método não permitido: " + method + "\"}", 
                        "application/json");
            }
        }
        
        private void listarUsuarios(HttpExchange exchange) throws IOException {
            try {
                // Parse query parameters
                String query = exchange.getRequestURI().getQuery();
                Map<String, String> params = parseQueryParams(query);
                
                List<Usuario> resultado = new ArrayList<>(usuarios.values());
                
                // Filtrar por perfil se especificado
                if (params.containsKey("perfil")) {
                    String perfil = params.get("perfil").toUpperCase();
                    resultado = resultado.stream()
                        .filter(u -> u.perfil.equals(perfil))
                        .toList();
                }
                
                // Paginação
                int page = Integer.parseInt(params.getOrDefault("page", "1"));
                int size = Integer.parseInt(params.getOrDefault("size", "10"));
                
                int start = (page - 1) * size;
                int end = Math.min(start + size, resultado.size());
                
                List<Usuario> paginados = new ArrayList<>();
                if (start < resultado.size()) {
                    paginados = resultado.subList(start, end);
                }
                
                // Criar resposta paginada
                String json = String.format("""
                    {
                      "data": [%s],
                      "pagination": {
                        "current_page": %d,
                        "total_pages": %d,
                        "total_items": %d,
                        "items_per_page": %d
                      }
                    }
                    """,
                    paginados.stream()
                        .map(Usuario::toJson)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse(""),
                    page,
                    (int) Math.ceil((double) resultado.size() / size),
                    resultado.size(),
                    size
                );
                
                enviarResponse(exchange, 200, json, "application/json");
                
            } catch (Exception e) {
                enviarResponse(exchange, 500, 
                    "{\"error\": \"Erro interno: " + e.getMessage() + "\"}", 
                    "application/json");
            }
        }
        
        private void criarUsuario(HttpExchange exchange) throws IOException {
            try {
                String body = lerRequestBody(exchange);
                Usuario usuario = Usuario.fromJson(body);
                
                // Validações
                if (usuario.nome == null || usuario.nome.trim().isEmpty()) {
                    enviarResponse(exchange, 400, 
                        "{\"error\": \"Nome é obrigatório\"}", 
                        "application/json");
                    return;
                }
                
                if (usuario.email == null || !usuario.email.contains("@")) {
                    enviarResponse(exchange, 400, 
                        "{\"error\": \"Email inválido\"}", 
                        "application/json");
                    return;
                }
                
                // Verificar se email já existe
                boolean emailExiste = usuarios.values().stream()
                    .anyMatch(u -> u.email.equals(usuario.email));
                
                if (emailExiste) {
                    enviarResponse(exchange, 409, 
                        "{\"error\": \"Email já está em uso\"}", 
                        "application/json");
                    return;
                }
                
                // Criar usuário
                usuario.id = proximoId.getAndIncrement();
                usuario.dataCriacao = LocalDateTime.now();
                
                if (usuario.perfil == null) {
                    usuario.perfil = "USER";
                }
                
                usuarios.put(usuario.id, usuario);
                
                // Adicionar headers de localização
                exchange.getResponseHeaders().add("Location", "/api/usuarios/" + usuario.id);
                
                enviarResponse(exchange, 201, usuario.toJson(), "application/json");
                
            } catch (Exception e) {
                enviarResponse(exchange, 400, 
                    "{\"error\": \"Dados inválidos: " + e.getMessage() + "\"}", 
                    "application/json");
            }
        }
    }
    
    // Handler para usuário específico (/api/usuarios/{id})
    static class UsuarioHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            
            enableCORS(exchange);
            
            try {
                Long id = extrairIdDaURL(path);
                
                switch (method) {
                    case "GET":
                        buscarUsuario(exchange, id);
                        break;
                    case "PUT":
                        atualizarUsuario(exchange, id);
                        break;
                    case "DELETE":
                        deletarUsuario(exchange, id);
                        break;
                    case "OPTIONS":
                        enviarResponse(exchange, 200, "", "text/plain");
                        break;
                    default:
                        enviarResponse(exchange, 405, 
                            "{\"error\": \"Método não permitido: " + method + "\"}", 
                            "application/json");
                }
            } catch (NumberFormatException e) {
                enviarResponse(exchange, 400, 
                    "{\"error\": \"ID inválido\"}", 
                    "application/json");
            }
        }
        
        private void buscarUsuario(HttpExchange exchange, Long id) throws IOException {
            Usuario usuario = usuarios.get(id);
            
            if (usuario == null) {
                enviarResponse(exchange, 404, 
                    "{\"error\": \"Usuário não encontrado\"}", 
                    "application/json");
                return;
            }
            
            enviarResponse(exchange, 200, usuario.toJson(), "application/json");
        }
        
        private void atualizarUsuario(HttpExchange exchange, Long id) throws IOException {
            Usuario usuarioExistente = usuarios.get(id);
            
            if (usuarioExistente == null) {
                enviarResponse(exchange, 404, 
                    "{\"error\": \"Usuário não encontrado\"}", 
                    "application/json");
                return;
            }
            
            try {
                String body = lerRequestBody(exchange);
                Usuario dadosAtualizacao = Usuario.fromJson(body);
                
                // Atualizar campos
                if (dadosAtualizacao.nome != null) {
                    usuarioExistente.nome = dadosAtualizacao.nome;
                }
                if (dadosAtualizacao.email != null) {
                    usuarioExistente.email = dadosAtualizacao.email;
                }
                if (dadosAtualizacao.perfil != null) {
                    usuarioExistente.perfil = dadosAtualizacao.perfil;
                }
                
                usuarioExistente.dataAtualizacao = LocalDateTime.now();
                
                enviarResponse(exchange, 200, usuarioExistente.toJson(), "application/json");
                
            } catch (Exception e) {
                enviarResponse(exchange, 400, 
                    "{\"error\": \"Dados inválidos: " + e.getMessage() + "\"}", 
                    "application/json");
            }
        }
        
        private void deletarUsuario(HttpExchange exchange, Long id) throws IOException {
            Usuario usuario = usuarios.remove(id);
            
            if (usuario == null) {
                enviarResponse(exchange, 404, 
                    "{\"error\": \"Usuário não encontrado\"}", 
                    "application/json");
                return;
            }
            
            enviarResponse(exchange, 204, "", "application/json");
        }
        
        private Long extrairIdDaURL(String path) {
            String[] partes = path.split("/");
            return Long.parseLong(partes[partes.length - 1]);
        }
    }
    
    // Handler para health check
    static class HealthHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            enableCORS(exchange);
            
            String health = String.format("""
                {
                  "status": "UP",
                  "timestamp": "%s",
                  "details": {
                    "usuarios_count": %d,
                    "memory_mb": %.2f,
                    "uptime": "Running"
                  }
                }
                """,
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                usuarios.size(),
                (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024.0 * 1024.0)
            );
            
            enviarResponse(exchange, 200, health, "application/json");
        }
    }
    
    // Handler para documentação
    static class DocumentacaoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>API REST - Documentação</title>
                    <style>
                        body { font-family: Arial, sans-serif; margin: 40px; }
                        .endpoint { background: #f5f5f5; padding: 15px; margin: 10px 0; border-radius: 5px; }
                        .method { font-weight: bold; color: #fff; padding: 5px 10px; border-radius: 3px; }
                        .get { background-color: #61affe; }
                        .post { background-color: #49cc90; }
                        .put { background-color: #fca130; }
                        .delete { background-color: #f93e3e; }
                        code { background: #f0f0f0; padding: 2px 5px; border-radius: 3px; }
                    </style>
                </head>
                <body>
                    <h1>API REST - Usuários</h1>
                    <p>Servidor REST simples implementado em Java SE</p>
                    
                    <div class="endpoint">
                        <span class="method get">GET</span> <code>/api/usuarios</code>
                        <p>Lista todos os usuários com paginação</p>
                        <p><strong>Query Params:</strong> page, size, perfil</p>
                    </div>
                    
                    <div class="endpoint">
                        <span class="method post">POST</span> <code>/api/usuarios</code>
                        <p>Cria um novo usuário</p>
                        <p><strong>Body:</strong> {"nome": "João", "email": "joao@teste.com", "perfil": "USER"}</p>
                    </div>
                    
                    <div class="endpoint">
                        <span class="method get">GET</span> <code>/api/usuarios/{id}</code>
                        <p>Busca usuário por ID</p>
                    </div>
                    
                    <div class="endpoint">
                        <span class="method put">PUT</span> <code>/api/usuarios/{id}</code>
                        <p>Atualiza usuário existente</p>
                    </div>
                    
                    <div class="endpoint">
                        <span class="method delete">DELETE</span> <code>/api/usuarios/{id}</code>
                        <p>Remove usuário</p>
                    </div>
                    
                    <div class="endpoint">
                        <span class="method get">GET</span> <code>/health</code>
                        <p>Status do servidor</p>
                    </div>
                    
                    <h3>Exemplos com curl:</h3>
                    <pre>
# Listar usuários
curl http://localhost:8080/api/usuarios

# Buscar usuário específico
curl http://localhost:8080/api/usuarios/1

# Criar usuário
curl -X POST http://localhost:8080/api/usuarios \\
  -H "Content-Type: application/json" \\
  -d '{"nome": "Ana Silva", "email": "ana@teste.com"}'

# Atualizar usuário
curl -X PUT http://localhost:8080/api/usuarios/1 \\
  -H "Content-Type: application/json" \\
  -d '{"nome": "João Santos"}'

# Deletar usuário
curl -X DELETE http://localhost:8080/api/usuarios/1
                    </pre>
                </body>
                </html>
                """;
            
            enviarResponse(exchange, 200, html, "text/html");
        }
    }
    
    // Métodos utilitários
    private static void enableCORS(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }
    
    private static void enviarResponse(HttpExchange exchange, int codigo, String resposta, String contentType) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", contentType + "; charset=UTF-8");
        
        byte[] bytes = resposta.getBytes(StandardCharsets.UTF_8);
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
    
    private static String lerRequestBody(HttpExchange exchange) throws IOException {
        try (InputStream is = exchange.getRequestBody()) {
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
    
    private static Map<String, String> parseQueryParams(String query) {
        Map<String, String> params = new HashMap<>();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return params;
    }
    
    // Classe Usuario simples
    static class Usuario {
        Long id;
        String nome;
        String email;
        String perfil;
        LocalDateTime dataCriacao;
        LocalDateTime dataAtualizacao;
        
        public Usuario() {}
        
        public Usuario(Long id, String nome, String email, String perfil) {
            this.id = id;
            this.nome = nome;
            this.email = email;
            this.perfil = perfil;
            this.dataCriacao = LocalDateTime.now();
        }
        
        public String toJson() {
            return String.format("""
                {
                  "id": %d,
                  "nome": "%s",
                  "email": "%s",
                  "perfil": "%s",
                  "data_criacao": "%s"%s
                }
                """,
                id,
                nome != null ? nome : "",
                email != null ? email : "",
                perfil != null ? perfil : "",
                dataCriacao != null ? dataCriacao.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "",
                dataAtualizacao != null ? 
                    ",\n  \"data_atualizacao\": \"" + dataAtualizacao.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\"" : ""
            );
        }
        
        public static Usuario fromJson(String json) {
            Usuario usuario = new Usuario();
            
            // Parse simples (em produção, usar biblioteca como Jackson)
            json = json.replaceAll("[{}\"\\s]", "");
            String[] campos = json.split(",");
            
            for (String campo : campos) {
                String[] keyValue = campo.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    
                    switch (key) {
                        case "nome": usuario.nome = value; break;
                        case "email": usuario.email = value; break;
                        case "perfil": usuario.perfil = value; break;
                    }
                }
            }
            
            return usuario;
        }
    }
}