import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * RestClient - Cliente para consumir APIs REST
 * Demonstra como fazer chamadas HTTP para APIs REST em Java
 * 
 * @author Apresentação Java Web
 */
public class RestClient {
    
    private static final String BASE_URL = "http://localhost:8080/api";
    private static final String CONTENT_TYPE = "application/json";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Cliente REST Java ===");
        System.out.println("Conectando à API: " + BASE_URL);
        System.out.println("Certifique-se de que o RestServer está rodando!");
        System.out.println();
        
        // Testar conexão
        if (!testarConexao()) {
            System.err.println("Erro: Não foi possível conectar ao servidor REST");
            System.err.println("Inicie o RestServer antes de executar este cliente");
            return;
        }
        
        while (true) {
            exibirMenu();
            
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir quebra de linha
                
                switch (opcao) {
                    case 1:
                        listarUsuarios();
                        break;
                    case 2:
                        buscarUsuarioPorId(scanner);
                        break;
                    case 3:
                        criarUsuario(scanner);
                        break;
                    case 4:
                        atualizarUsuario(scanner);
                        break;
                    case 5:
                        deletarUsuario(scanner);
                        break;
                    case 6:
                        listarComFiltros(scanner);
                        break;
                    case 7:
                        testarHealthCheck();
                        break;
                    case 8:
                        executarTestesAutomaticos();
                        break;
                    case 0:
                        System.out.println("Encerrando cliente REST...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
                
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
                
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
                scanner.nextLine(); // Limpar buffer
            }
        }
    }
    
    private static void exibirMenu() {
        System.out.println("\n=== Menu REST Client ===");
        System.out.println("1. Listar usuários");
        System.out.println("2. Buscar usuário por ID");
        System.out.println("3. Criar novo usuário");
        System.out.println("4. Atualizar usuário");
        System.out.println("5. Deletar usuário");
        System.out.println("6. Listar com filtros");
        System.out.println("7. Health Check");
        System.out.println("8. Executar testes automáticos");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static boolean testarConexao() {
        try {
            RestResponse response = fazerRequisicao("GET", "/usuarios", null);
            return response.codigo >= 200 && response.codigo < 300;
        } catch (Exception e) {
            return false;
        }
    }
    
    private static void listarUsuarios() {
        try {
            System.out.println("\n--- Listando Usuários ---");
            
            RestResponse response = fazerRequisicao("GET", "/usuarios", null);
            
            System.out.println("Status: " + response.codigo);
            System.out.println("Response:\n" + formatarJSON(response.corpo));
            
        } catch (Exception e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
    }
    
    private static void buscarUsuarioPorId(Scanner scanner) {
        try {
            System.out.print("Digite o ID do usuário: ");
            long id = scanner.nextLong();
            scanner.nextLine();
            
            System.out.println("\n--- Buscando Usuário " + id + " ---");
            
            RestResponse response = fazerRequisicao("GET", "/usuarios/" + id, null);
            
            System.out.println("Status: " + response.codigo);
            
            if (response.codigo == 200) {
                System.out.println("Usuário encontrado:");
                System.out.println(formatarJSON(response.corpo));
            } else if (response.codigo == 404) {
                System.out.println("Usuário não encontrado");
            } else {
                System.out.println("Erro: " + response.corpo);
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
    }
    
    private static void criarUsuario(Scanner scanner) {
        try {
            System.out.println("\n--- Criar Novo Usuário ---");
            
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Perfil (USER/ADMIN): ");
            String perfil = scanner.nextLine();
            
            String json = String.format("""
                {
                  "nome": "%s",
                  "email": "%s",
                  "perfil": "%s"
                }
                """, nome, email, perfil.isEmpty() ? "USER" : perfil);
            
            RestResponse response = fazerRequisicao("POST", "/usuarios", json);
            
            System.out.println("Status: " + response.codigo);
            
            if (response.codigo == 201) {
                System.out.println("Usuário criado com sucesso:");
                System.out.println(formatarJSON(response.corpo));
            } else {
                System.out.println("Erro ao criar usuário:");
                System.out.println(response.corpo);
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao criar usuário: " + e.getMessage());
        }
    }
    
    private static void atualizarUsuario(Scanner scanner) {
        try {
            System.out.print("Digite o ID do usuário para atualizar: ");
            long id = scanner.nextLong();
            scanner.nextLine();
            
            System.out.println("\n--- Atualizar Usuário " + id + " ---");
            System.out.println("(Deixe em branco para não alterar o campo)");
            
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("Novo email: ");
            String email = scanner.nextLine();
            
            System.out.print("Novo perfil: ");
            String perfil = scanner.nextLine();
            
            // Construir JSON apenas com campos preenchidos
            StringBuilder jsonBuilder = new StringBuilder("{");
            boolean primeiro = true;
            
            if (!nome.isEmpty()) {
                jsonBuilder.append("\"nome\": \"").append(nome).append("\"");
                primeiro = false;
            }
            
            if (!email.isEmpty()) {
                if (!primeiro) jsonBuilder.append(", ");
                jsonBuilder.append("\"email\": \"").append(email).append("\"");
                primeiro = false;
            }
            
            if (!perfil.isEmpty()) {
                if (!primeiro) jsonBuilder.append(", ");
                jsonBuilder.append("\"perfil\": \"").append(perfil).append("\"");
            }
            
            jsonBuilder.append("}");
            
            RestResponse response = fazerRequisicao("PUT", "/usuarios/" + id, jsonBuilder.toString());
            
            System.out.println("Status: " + response.codigo);
            
            if (response.codigo == 200) {
                System.out.println("Usuário atualizado com sucesso:");
                System.out.println(formatarJSON(response.corpo));
            } else if (response.codigo == 404) {
                System.out.println("Usuário não encontrado");
            } else {
                System.out.println("Erro ao atualizar usuário:");
                System.out.println(response.corpo);
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }
    
    private static void deletarUsuario(Scanner scanner) {
        try {
            System.out.print("Digite o ID do usuário para deletar: ");
            long id = scanner.nextLong();
            scanner.nextLine();
            
            System.out.print("Tem certeza que deseja deletar o usuário " + id + "? (s/N): ");
            String confirmacao = scanner.nextLine();
            
            if (!confirmacao.toLowerCase().startsWith("s")) {
                System.out.println("Operação cancelada");
                return;
            }
            
            System.out.println("\n--- Deletando Usuário " + id + " ---");
            
            RestResponse response = fazerRequisicao("DELETE", "/usuarios/" + id, null);
            
            System.out.println("Status: " + response.codigo);
            
            if (response.codigo == 204) {
                System.out.println("Usuário deletado com sucesso");
            } else if (response.codigo == 404) {
                System.out.println("Usuário não encontrado");
            } else {
                System.out.println("Erro ao deletar usuário:");
                System.out.println(response.corpo);
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }
    
    private static void listarComFiltros(Scanner scanner) {
        try {
            System.out.println("\n--- Listar com Filtros ---");
            
            System.out.print("Página (padrão 1): ");
            String paginaStr = scanner.nextLine();
            int pagina = paginaStr.isEmpty() ? 1 : Integer.parseInt(paginaStr);
            
            System.out.print("Tamanho da página (padrão 5): ");
            String tamanhoStr = scanner.nextLine();
            int tamanho = tamanhoStr.isEmpty() ? 5 : Integer.parseInt(tamanhoStr);
            
            System.out.print("Filtrar por perfil (USER/ADMIN ou vazio): ");
            String perfil = scanner.nextLine();
            
            StringBuilder url = new StringBuilder("/usuarios?page=" + pagina + "&size=" + tamanho);
            
            if (!perfil.isEmpty()) {
                url.append("&perfil=").append(perfil);
            }
            
            RestResponse response = fazerRequisicao("GET", url.toString(), null);
            
            System.out.println("Status: " + response.codigo);
            System.out.println("Response:\n" + formatarJSON(response.corpo));
            
        } catch (Exception e) {
            System.err.println("Erro ao listar com filtros: " + e.getMessage());
        }
    }
    
    private static void testarHealthCheck() {
        try {
            System.out.println("\n--- Health Check ---");
            
            RestResponse response = fazerRequisicao("GET", "/../health", null);
            
            System.out.println("Status: " + response.codigo);
            System.out.println("Health Status:\n" + formatarJSON(response.corpo));
            
        } catch (Exception e) {
            System.err.println("Erro no health check: " + e.getMessage());
        }
    }
    
    private static void executarTestesAutomaticos() {
        System.out.println("\n=== Executando Testes Automáticos ===");
        
        try {
            // 1. Teste de listagem
            System.out.println("1. Testando listagem de usuários...");
            RestResponse response = fazerRequisicao("GET", "/usuarios", null);
            System.out.println("   ✓ Status: " + response.codigo + " (esperado: 200)");
            
            // 2. Teste de criação
            System.out.println("2. Testando criação de usuário...");
            String novoUsuario = """
                {
                  "nome": "Teste Automático",
                  "email": "teste@automatico.com",
                  "perfil": "USER"
                }
                """;
            
            response = fazerRequisicao("POST", "/usuarios", novoUsuario);
            System.out.println("   ✓ Status: " + response.codigo + " (esperado: 201)");
            
            // Extrair ID do usuário criado
            String idUsuario = extrairIdDoJSON(response.corpo);
            
            if (idUsuario != null) {
                // 3. Teste de busca
                System.out.println("3. Testando busca por ID...");
                response = fazerRequisicao("GET", "/usuarios/" + idUsuario, null);
                System.out.println("   ✓ Status: " + response.codigo + " (esperado: 200)");
                
                // 4. Teste de atualização
                System.out.println("4. Testando atualização...");
                String atualizacao = """
                    {
                      "nome": "Teste Atualizado"
                    }
                    """;
                response = fazerRequisicao("PUT", "/usuarios/" + idUsuario, atualizacao);
                System.out.println("   ✓ Status: " + response.codigo + " (esperado: 200)");
                
                // 5. Teste de deleção
                System.out.println("5. Testando deleção...");
                response = fazerRequisicao("DELETE", "/usuarios/" + idUsuario, null);
                System.out.println("   ✓ Status: " + response.codigo + " (esperado: 204)");
            }
            
            // 6. Teste de erro 404
            System.out.println("6. Testando erro 404...");
            response = fazerRequisicao("GET", "/usuarios/99999", null);
            System.out.println("   ✓ Status: " + response.codigo + " (esperado: 404)");
            
            // 7. Teste de health check
            System.out.println("7. Testando health check...");
            response = fazerRequisicao("GET", "/../health", null);
            System.out.println("   ✓ Status: " + response.codigo + " (esperado: 200)");
            
            System.out.println("\n=== Testes Concluídos ===");
            
        } catch (Exception e) {
            System.err.println("Erro nos testes automáticos: " + e.getMessage());
        }
    }
    
    private static RestResponse fazerRequisicao(String metodo, String endpoint, String corpo) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        // Configurar requisição
        connection.setRequestMethod(metodo);
        connection.setRequestProperty("Content-Type", CONTENT_TYPE);
        connection.setRequestProperty("Accept", CONTENT_TYPE);
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(10000);
        
        // Enviar corpo se necessário
        if (corpo != null && !corpo.isEmpty()) {
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                os.write(corpo.getBytes(StandardCharsets.UTF_8));
            }
        }
        
        // Ler resposta
        int codigo = connection.getResponseCode();
        
        BufferedReader reader;
        if (codigo >= 200 && codigo < 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        
        StringBuilder response = new StringBuilder();
        String linha;
        while ((linha = reader.readLine()) != null) {
            response.append(linha).append("\n");
        }
        reader.close();
        
        connection.disconnect();
        
        return new RestResponse(codigo, response.toString().trim());
    }
    
    private static String formatarJSON(String json) {
        // Formatação simples para melhor visualização
        return json.replace(",", ",\n  ")
                  .replace("{", "{\n  ")
                  .replace("}", "\n}")
                  .replace("[", "[\n  ")
                  .replace("]", "\n]");
    }
    
    private static String extrairIdDoJSON(String json) {
        // Extração simples do ID do JSON
        int startIndex = json.indexOf("\"id\":");
        if (startIndex == -1) return null;
        
        startIndex += 5; // Tamanho de "\"id\":"
        int endIndex = json.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = json.indexOf("}", startIndex);
        }
        
        if (endIndex == -1) return null;
        
        return json.substring(startIndex, endIndex).trim();
    }
    
    // Classe para resposta REST
    static class RestResponse {
        int codigo;
        String corpo;
        
        RestResponse(int codigo, String corpo) {
            this.codigo = codigo;
            this.corpo = corpo;
        }
    }
}