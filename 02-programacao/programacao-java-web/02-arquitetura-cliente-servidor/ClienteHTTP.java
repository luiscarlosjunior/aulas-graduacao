import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Cliente HTTP simples para testar o servidor
 * Demonstra como fazer requisições HTTP em Java
 * 
 * @author Apresentação Java Web
 */
public class ClienteHTTP {
    
    private static final String BASE_URL = "http://localhost:8080";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Cliente HTTP Java ===");
        System.out.println("Certifique-se de que o ServidorHTTPSimples está rodando!");
        System.out.println("URL do servidor: " + BASE_URL);
        System.out.println();
        
        while (true) {
            exibirMenu();
            
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir quebra de linha
                
                switch (opcao) {
                    case 1:
                        testarEndpoint("/");
                        break;
                    case 2:
                        testarEndpoint("/usuarios");
                        break;
                    case 3:
                        testarEndpoint("/status");
                        break;
                    case 4:
                        testarEndpoint("/tempo");
                        break;
                    case 5:
                        System.out.print("Digite o endpoint (ex: /usuarios): ");
                        String endpoint = scanner.nextLine();
                        testarEndpoint(endpoint);
                        break;
                    case 6:
                        testarMultiplasRequisicoes();
                        break;
                    case 0:
                        System.out.println("Encerrando cliente...");
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
        System.out.println("\n=== Menu de Opções ===");
        System.out.println("1. Testar página inicial (/)");
        System.out.println("2. Listar usuários (/usuarios)");
        System.out.println("3. Ver status do servidor (/status)");
        System.out.println("4. Ver data/hora (/tempo)");
        System.out.println("5. Testar endpoint personalizado");
        System.out.println("6. Teste de carga (múltiplas requisições)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void testarEndpoint(String endpoint) {
        try {
            System.out.println("\n--- Testando: " + endpoint + " ---");
            
            long inicio = System.currentTimeMillis();
            HttpResponse response = fazerRequisicaoGET(endpoint);
            long tempoResposta = System.currentTimeMillis() - inicio;
            
            System.out.println("Status: " + response.codigo + " " + response.mensagem);
            System.out.println("Content-Type: " + response.contentType);
            System.out.println("Tamanho: " + response.conteudo.length() + " caracteres");
            System.out.println("Tempo de resposta: " + tempoResposta + " ms");
            
            if (response.contentType.contains("application/json")) {
                System.out.println("\n--- Resposta JSON ---");
                System.out.println(formatarJSON(response.conteudo));
            } else if (response.contentType.contains("text/html")) {
                System.out.println("\n--- Título da página ---");
                String titulo = extrairTitulo(response.conteudo);
                System.out.println(titulo != null ? titulo : "Título não encontrado");
            } else {
                System.out.println("\n--- Conteúdo ---");
                System.out.println(response.conteudo.substring(0, Math.min(500, response.conteudo.length())));
                if (response.conteudo.length() > 500) {
                    System.out.println("... (truncado)");
                }
            }
            
        } catch (Exception e) {
            System.err.println("Erro ao acessar " + endpoint + ": " + e.getMessage());
        }
    }
    
    private static void testarMultiplasRequisicoes() {
        System.out.println("\n--- Teste de Carga ---");
        int numeroRequisicoes = 10;
        String endpoint = "/status";
        
        System.out.println("Fazendo " + numeroRequisicoes + " requisições para " + endpoint);
        
        long inicioTotal = System.currentTimeMillis();
        int sucessos = 0;
        int falhas = 0;
        long tempoMedio = 0;
        
        for (int i = 1; i <= numeroRequisicoes; i++) {
            try {
                System.out.print("Requisição " + i + "/" + numeroRequisicoes + "... ");
                
                long inicio = System.currentTimeMillis();
                HttpResponse response = fazerRequisicaoGET(endpoint);
                long tempo = System.currentTimeMillis() - inicio;
                
                if (response.codigo == 200) {
                    sucessos++;
                    tempoMedio += tempo;
                    System.out.println("OK (" + tempo + " ms)");
                } else {
                    falhas++;
                    System.out.println("ERRO " + response.codigo);
                }
                
                // Pequena pausa entre requisições
                Thread.sleep(100);
                
            } catch (Exception e) {
                falhas++;
                System.out.println("FALHA: " + e.getMessage());
            }
        }
        
        long tempoTotal = System.currentTimeMillis() - inicioTotal;
        
        System.out.println("\n--- Resultado do Teste ---");
        System.out.println("Total de requisições: " + numeroRequisicoes);
        System.out.println("Sucessos: " + sucessos);
        System.out.println("Falhas: " + falhas);
        System.out.println("Tempo total: " + tempoTotal + " ms");
        if (sucessos > 0) {
            System.out.println("Tempo médio: " + (tempoMedio / sucessos) + " ms");
            System.out.println("Requisições/segundo: " + Math.round((sucessos * 1000.0) / tempoTotal));
        }
    }
    
    private static HttpResponse fazerRequisicaoGET(String endpoint) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        // Configurar requisição
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "ClienteHTTP-Java/1.0");
        connection.setRequestProperty("Accept", "*/*");
        connection.setConnectTimeout(5000); // 5 segundos
        connection.setReadTimeout(10000);   // 10 segundos
        
        // Obter resposta
        int codigo = connection.getResponseCode();
        String mensagem = connection.getResponseMessage();
        String contentType = connection.getContentType();
        
        // Ler conteúdo
        BufferedReader reader;
        if (codigo >= 200 && codigo < 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        
        StringBuilder conteudo = new StringBuilder();
        String linha;
        while ((linha = reader.readLine()) != null) {
            conteudo.append(linha).append("\n");
        }
        reader.close();
        
        connection.disconnect();
        
        return new HttpResponse(codigo, mensagem, contentType, conteudo.toString());
    }
    
    private static String formatarJSON(String json) {
        // Formatação simples de JSON para melhor visualização
        return json.replace(",", ",\n  ")
                  .replace("{", "{\n  ")
                  .replace("}", "\n}");
    }
    
    private static String extrairTitulo(String html) {
        int inicioTitulo = html.indexOf("<title>");
        int fimTitulo = html.indexOf("</title>");
        
        if (inicioTitulo != -1 && fimTitulo != -1 && fimTitulo > inicioTitulo) {
            return html.substring(inicioTitulo + 7, fimTitulo);
        }
        
        return null;
    }
    
    // Classe interna para representar uma resposta HTTP
    private static class HttpResponse {
        int codigo;
        String mensagem;
        String contentType;
        String conteudo;
        
        HttpResponse(int codigo, String mensagem, String contentType, String conteudo) {
            this.codigo = codigo;
            this.mensagem = mensagem;
            this.contentType = contentType != null ? contentType : "text/plain";
            this.conteudo = conteudo;
        }
    }
}