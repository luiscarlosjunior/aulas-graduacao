/**
 * Exemplos Práticos de Manipulação de Strings
 * 
 * Este programa demonstra aplicações práticas e reais dos métodos de string
 * em situações que você encontrará no dia a dia da programação.
 * 
 * Casos de uso abordados:
 * - Validação e formatação de email
 * - Processamento de nomes
 * - Limpeza e normalização de texto
 * - Validação de senhas
 * - Parser simples de dados
 * - Manipulação de URLs e caminhos
 * - Geração de relatórios
 * 
 * @author luiscaparroz
 * @version 1.0
 * @since JDK 1.8
 */
public class ExemplosPraticosStrings {
    
    public static void main(String[] args) {
        System.out.println("=== EXEMPLOS PRÁTICOS: MANIPULAÇÃO DE STRINGS ===\n");
        
        // Executar todos os exemplos práticos
        exemploValidacaoEmail();
        exemploProcessamentoNomes();
        exemploLimpezaTexto();
        exemploValidacaoSenha();
        exemploParserDados();
        exemploManipulacaoUrl();
        exemploRelatorio();
        
        System.out.println("=== FIM DOS EXEMPLOS PRÁTICOS ===");
    }
    
    /**
     * Exemplo prático: Validação e formatação de emails
     */
    public static void exemploValidacaoEmail() {
        System.out.println("EXEMPLO PRÁTICO 1: Validação de Email");
        System.out.println("═══════════════════════════════════════");
        
        String[] emails = {
            "usuario@exemplo.com",
            "USUARIO@EXEMPLO.COM",
            "  usuario@exemplo.com  ",
            "usuario.teste@exemplo.com.br",
            "usuario@",
            "@exemplo.com",
            "usuario.exemplo.com",
            "usuário@exemplo.com"
        };
        
        for (String email : emails) {
            System.out.println("\nTestando email: \"" + email + "\"");
            
            // Limpeza básica
            String emailLimpo = email.trim().toLowerCase();
            
            // Validações básicas
            boolean valido = validarEmail(emailLimpo);
            boolean dominioBr = emailLimpo.endsWith(".br");
            
            System.out.println("  Email limpo: \"" + emailLimpo + "\"");
            System.out.println("  Válido: " + valido);
            System.out.println("  Domínio .br: " + dominioBr);
            
            if (valido) {
                String[] partes = emailLimpo.split("@");
                System.out.println("  Usuário: \"" + partes[0] + "\"");
                System.out.println("  Domínio: \"" + partes[1] + "\"");
            }
        }
        
        System.out.println();
    }
    
    /**
     * Validação simples de email
     */
    public static boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        // Verificações básicas
        return email.contains("@") && 
               !email.startsWith("@") && 
               !email.endsWith("@") &&
               email.indexOf("@") == email.lastIndexOf("@") &&
               email.contains(".") &&
               email.indexOf(".") > email.indexOf("@");
    }
    
    /**
     * Exemplo prático: Processamento e formatação de nomes
     */
    public static void exemploProcessamentoNomes() {
        System.out.println("EXEMPLO PRÁTICO 2: Processamento de Nomes");
        System.out.println("═══════════════════════════════════════════");
        
        String[] nomes = {
            "joão silva santos",
            "MARIA FERNANDA OLIVEIRA",
            "  pedro   henrique   ",
            "ana-clara dos santos",
            "josé da silva júnior"
        };
        
        for (String nome : nomes) {
            System.out.println("\nNome original: \"" + nome + "\"");
            
            String nomeFormatado = formatarNome(nome);
            String iniciais = obterIniciais(nomeFormatado);
            String primeiroNome = obterPrimeiroNome(nomeFormatado);
            String sobrenome = obterUltimoSobrenome(nomeFormatado);
            
            System.out.println("  Formatado: \"" + nomeFormatado + "\"");
            System.out.println("  Iniciais: \"" + iniciais + "\"");
            System.out.println("  Primeiro nome: \"" + primeiroNome + "\"");
            System.out.println("  Último sobrenome: \"" + sobrenome + "\"");
        }
        
        System.out.println();
    }
    
    /**
     * Formata um nome para o padrão "Primeira Letra Maiúscula"
     */
    public static String formatarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return "";
        }
        
        // Normalizar espaços e converter para minúsculo
        String nomeNormalizado = nome.trim().toLowerCase().replaceAll("\\s+", " ");
        
        // Capitalizar cada palavra
        String[] palavras = nomeNormalizado.split(" ");
        StringBuilder nomeFormatado = new StringBuilder();
        
        for (int i = 0; i < palavras.length; i++) {
            String palavra = palavras[i];
            
            // Pular preposições pequenas (exceto se for a primeira palavra)
            if (i > 0 && (palavra.equals("da") || palavra.equals("de") || palavra.equals("do") || 
                         palavra.equals("das") || palavra.equals("dos"))) {
                nomeFormatado.append(palavra);
            } else if (!palavra.isEmpty()) {
                // Capitalizar primeira letra
                nomeFormatado.append(Character.toUpperCase(palavra.charAt(0)));
                if (palavra.length() > 1) {
                    nomeFormatado.append(palavra.substring(1));
                }
            }
            
            if (i < palavras.length - 1) {
                nomeFormatado.append(" ");
            }
        }
        
        return nomeFormatado.toString();
    }
    
    /**
     * Obtém as iniciais de um nome
     */
    public static String obterIniciais(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return "";
        }
        
        String[] palavras = nome.trim().split("\\s+");
        StringBuilder iniciais = new StringBuilder();
        
        for (String palavra : palavras) {
            if (!palavra.isEmpty() && !palavra.equals("da") && !palavra.equals("de") && 
                !palavra.equals("do") && !palavra.equals("das") && !palavra.equals("dos")) {
                iniciais.append(Character.toUpperCase(palavra.charAt(0)));
            }
        }
        
        return iniciais.toString();
    }
    
    /**
     * Obtém o primeiro nome
     */
    public static String obterPrimeiroNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return "";
        }
        
        String[] palavras = nome.trim().split("\\s+");
        return palavras.length > 0 ? palavras[0] : "";
    }
    
    /**
     * Obtém o último sobrenome
     */
    public static String obterUltimoSobrenome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return "";
        }
        
        String[] palavras = nome.trim().split("\\s+");
        return palavras.length > 1 ? palavras[palavras.length - 1] : "";
    }
    
    /**
     * Exemplo prático: Limpeza e normalização de texto
     */
    public static void exemploLimpezaTexto() {
        System.out.println("EXEMPLO PRÁTICO 3: Limpeza de Texto");
        System.out.println("═════════════════════════════════════");
        
        String textoSujo = "  Este   é um TEXTO    com múltiplos    espaços,\n" +
                          "\tcaracteres   especiais!@#$%   e quebras\n" +
                          "de linha desnecessárias...   ";
        
        System.out.println("Texto original:");
        System.out.println("\"" + textoSujo.replace("\n", "\\n").replace("\t", "\\t") + "\"");
        
        // Diferentes níveis de limpeza
        String nivel1 = limparTextoBasico(textoSujo);
        String nivel2 = limparTextoAvancado(textoSujo);
        String nivel3 = normalizarTexto(textoSujo);
        
        System.out.println("\nLimpeza básica (trim e espaços):");
        System.out.println("\"" + nivel1 + "\"");
        
        System.out.println("\nLimpeza avançada (caracteres especiais):");
        System.out.println("\"" + nivel2 + "\"");
        
        System.out.println("\nNormalização completa:");
        System.out.println("\"" + nivel3 + "\"");
        
        // Estatísticas
        System.out.println("\nEstatísticas:");
        System.out.println("  Original: " + textoSujo.length() + " caracteres");
        System.out.println("  Nível 1: " + nivel1.length() + " caracteres");
        System.out.println("  Nível 2: " + nivel2.length() + " caracteres");
        System.out.println("  Nível 3: " + nivel3.length() + " caracteres");
        
        System.out.println();
    }
    
    /**
     * Limpeza básica de texto
     */
    public static String limparTextoBasico(String texto) {
        return texto.trim().replaceAll("\\s+", " ");
    }
    
    /**
     * Limpeza avançada removendo caracteres especiais
     */
    public static String limparTextoAvancado(String texto) {
        return texto.trim()
                   .replaceAll("\\s+", " ")
                   .replaceAll("[^a-zA-ZÀ-ÿ0-9\\s.,!?]", "");
    }
    
    /**
     * Normalização completa do texto
     */
    public static String normalizarTexto(String texto) {
        return texto.trim()
                   .toLowerCase()
                   .replaceAll("\\s+", " ")
                   .replaceAll("[^a-zA-ZÀ-ÿ0-9\\s]", "")
                   .replaceAll("\\s+", " ");
    }
    
    /**
     * Exemplo prático: Validação de senhas
     */
    public static void exemploValidacaoSenha() {
        System.out.println("EXEMPLO PRÁTICO 4: Validação de Senhas");
        System.out.println("════════════════════════════════════════");
        
        String[] senhas = {
            "123456",
            "password",
            "Password",
            "Password1",
            "Password1!",
            "P@ssw0rd123",
            "MinhaSenh@Segur@123"
        };
        
        for (String senha : senhas) {
            System.out.println("\nTestando senha: \"" + senha + "\"");
            
            int pontuacao = avaliarSenha(senha);
            String forca = classificarForcaSenha(pontuacao);
            
            System.out.println("  Comprimento: " + senha.length());
            System.out.println("  Tem minúscula: " + temMinuscula(senha));
            System.out.println("  Tem maiúscula: " + temMaiuscula(senha));
            System.out.println("  Tem número: " + temNumero(senha));
            System.out.println("  Tem especial: " + temCaractereEspecial(senha));
            System.out.println("  Pontuação: " + pontuacao + "/100");
            System.out.println("  Força: " + forca);
        }
        
        System.out.println();
    }
    
    /**
     * Avalia a força de uma senha (0-100)
     */
    public static int avaliarSenha(String senha) {
        int pontos = 0;
        
        // Comprimento
        if (senha.length() >= 8) pontos += 25;
        else if (senha.length() >= 6) pontos += 15;
        else if (senha.length() >= 4) pontos += 5;
        
        // Critérios de complexidade
        if (temMinuscula(senha)) pontos += 15;
        if (temMaiuscula(senha)) pontos += 15;
        if (temNumero(senha)) pontos += 15;
        if (temCaractereEspecial(senha)) pontos += 15;
        
        // Bônus por comprimento extra
        if (senha.length() >= 12) pontos += 10;
        if (senha.length() >= 16) pontos += 5;
        
        return Math.min(pontos, 100);
    }
    
    public static boolean temMinuscula(String senha) {
        return !senha.equals(senha.toUpperCase());
    }
    
    public static boolean temMaiuscula(String senha) {
        return !senha.equals(senha.toLowerCase());
    }
    
    public static boolean temNumero(String senha) {
        return senha.matches(".*\\d.*");
    }
    
    public static boolean temCaractereEspecial(String senha) {
        return senha.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }
    
    public static String classificarForcaSenha(int pontuacao) {
        if (pontuacao >= 80) return "Muito Forte 🟢";
        else if (pontuacao >= 60) return "Forte 🟡";
        else if (pontuacao >= 40) return "Média 🟠";
        else return "Fraca 🔴";
    }
    
    /**
     * Exemplo prático: Parser simples de dados CSV
     */
    public static void exemploParserDados() {
        System.out.println("EXEMPLO PRÁTICO 5: Parser de Dados CSV");
        System.out.println("════════════════════════════════════════");
        
        String csvData = "Nome,Idade,Cidade,Salario\n" +
                        "João Silva,30,São Paulo,5500.50\n" +
                        "Maria Santos,25,Rio de Janeiro,4200.00\n" +
                        "Pedro Costa,35,Belo Horizonte,6800.75";
        
        System.out.println("Dados CSV:");
        System.out.println(csvData);
        
        // Parser simples
        String[] linhas = csvData.split("\n");
        String[] cabecalho = linhas[0].split(",");
        
        System.out.println("\nDados processados:");
        System.out.println("Cabeçalho: " + String.join(" | ", cabecalho));
        System.out.println("-".repeat(50));
        
        for (int i = 1; i < linhas.length; i++) {
            String[] campos = linhas[i].split(",");
            
            System.out.printf("Registro %d:%n", i);
            for (int j = 0; j < cabecalho.length && j < campos.length; j++) {
                System.out.printf("  %s: %s%n", cabecalho[j], campos[j]);
            }
            
            // Processamento específico
            if (campos.length >= 4) {
                try {
                    double salario = Double.parseDouble(campos[3]);
                    String faixaSalarial = salario > 5000 ? "Alta" : salario > 3000 ? "Média" : "Baixa";
                    System.out.printf("  Faixa Salarial: %s%n", faixaSalarial);
                } catch (NumberFormatException e) {
                    System.out.println("  Salário: Formato inválido");
                }
            }
            System.out.println();
        }
    }
    
    /**
     * Exemplo prático: Manipulação de URLs e caminhos
     */
    public static void exemploManipulacaoUrl() {
        System.out.println("EXEMPLO PRÁTICO 6: Manipulação de URLs");
        System.out.println("════════════════════════════════════════");
        
        String[] urls = {
            "https://www.exemplo.com/produtos/categoria?id=123&cor=azul",
            "http://api.servico.com/v1/usuarios/456",
            "ftp://files.exemplo.com/downloads/arquivo.pdf",
            "/home/usuario/documentos/arquivo.txt"
        };
        
        for (String url : urls) {
            System.out.println("\nURL: \"" + url + "\"");
            
            analisarUrl(url);
        }
        
        System.out.println();
    }
    
    /**
     * Analisa componentes de uma URL
     */
    public static void analisarUrl(String url) {
        // Protocolo
        String protocolo = "";
        if (url.contains("://")) {
            protocolo = url.substring(0, url.indexOf("://"));
            System.out.println("  Protocolo: " + protocolo);
        }
        
        // Domínio (simplificado)
        String semProtocolo = url.contains("://") ? url.substring(url.indexOf("://") + 3) : url;
        String dominio = "";
        if (semProtocolo.contains("/")) {
            dominio = semProtocolo.substring(0, semProtocolo.indexOf("/"));
        } else {
            dominio = semProtocolo;
        }
        
        if (!dominio.isEmpty() && !dominio.startsWith("/")) {
            System.out.println("  Domínio: " + dominio);
        }
        
        // Caminho
        if (url.contains("/")) {
            int inicioPath = url.lastIndexOf("://") + 3;
            if (inicioPath < url.length()) {
                String resto = url.substring(inicioPath);
                if (resto.contains("/")) {
                    String caminho = resto.substring(resto.indexOf("/"));
                    if (caminho.contains("?")) {
                        caminho = caminho.substring(0, caminho.indexOf("?"));
                    }
                    System.out.println("  Caminho: " + caminho);
                }
            }
        }
        
        // Parâmetros
        if (url.contains("?")) {
            String parametros = url.substring(url.indexOf("?") + 1);
            System.out.println("  Parâmetros: " + parametros);
            
            String[] params = parametros.split("&");
            for (String param : params) {
                if (param.contains("=")) {
                    String[] keyValue = param.split("=", 2);
                    System.out.println("    " + keyValue[0] + " = " + keyValue[1]);
                }
            }
        }
        
        // Extensão do arquivo
        String nomeArquivo = url.substring(url.lastIndexOf("/") + 1);
        if (nomeArquivo.contains("?")) {
            nomeArquivo = nomeArquivo.substring(0, nomeArquivo.indexOf("?"));
        }
        if (nomeArquivo.contains(".")) {
            String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf(".") + 1);
            System.out.println("  Extensão: ." + extensao);
        }
    }
    
    /**
     * Exemplo prático: Geração de relatório formatado
     */
    public static void exemploRelatorio() {
        System.out.println("EXEMPLO PRÁTICO 7: Geração de Relatório");
        System.out.println("═════════════════════════════════════════");
        
        // Dados de exemplo
        String[][] vendas = {
            {"João Silva", "Notebook", "2500.00", "2"},
            {"Maria Santos", "Mouse", "50.00", "5"},
            {"Pedro Costa", "Teclado", "150.00", "3"},
            {"Ana Oliveira", "Monitor", "800.00", "1"}
        };
        
        StringBuilder relatorio = new StringBuilder();
        
        // Cabeçalho
        relatorio.append("RELATÓRIO DE VENDAS - ").append(java.time.LocalDate.now()).append("\n");
        relatorio.append("=".repeat(60)).append("\n\n");
        
        // Tabela
        relatorio.append(String.format("%-15s %-12s %10s %5s %12s%n", 
                                     "VENDEDOR", "PRODUTO", "PREÇO", "QTD", "TOTAL"));
        relatorio.append("-".repeat(60)).append("\n");
        
        double totalGeral = 0;
        
        for (String[] venda : vendas) {
            String vendedor = venda[0];
            String produto = venda[1];
            double preco = Double.parseDouble(venda[2]);
            int quantidade = Integer.parseInt(venda[3]);
            double total = preco * quantidade;
            totalGeral += total;
            
            relatorio.append(String.format("%-15s %-12s %10.2f %5d %12.2f%n",
                                         vendedor, produto, preco, quantidade, total));
        }
        
        // Rodapé
        relatorio.append("-".repeat(60)).append("\n");
        relatorio.append(String.format("%43s %12.2f%n", "TOTAL GERAL:", totalGeral));
        
        System.out.println(relatorio.toString());
        
        // Análise adicional
        System.out.println("ANÁLISE:");
        System.out.println("• Total de vendas: " + vendas.length);
        System.out.println("• Ticket médio: R$ " + String.format("%.2f", totalGeral / vendas.length));
        
        // Maior venda
        double maiorVenda = 0;
        String melhorVendedor = "";
        for (String[] venda : vendas) {
            double total = Double.parseDouble(venda[2]) * Integer.parseInt(venda[3]);
            if (total > maiorVenda) {
                maiorVenda = total;
                melhorVendedor = venda[0];
            }
        }
        System.out.println("• Maior venda: " + melhorVendedor + " (R$ " + String.format("%.2f", maiorVenda) + ")");
        
        System.out.println();
    }
}