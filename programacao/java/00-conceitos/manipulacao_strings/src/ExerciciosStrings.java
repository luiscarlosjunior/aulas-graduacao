/**
 * Exercícios de Manipulação de Strings em Java
 * 
 * Esta classe contém exercícios práticos para consolidar os conceitos
 * de manipulação de strings. Os exercícios são organizados por níveis
 * de dificuldade e cobrem cenários reais de programação.
 * 
 * INSTRUÇÕES:
 * 1. Leia cada exercício com atenção
 * 2. Implemente a solução no método correspondente
 * 3. Execute o programa para testar suas soluções
 * 4. Compare com as soluções em ExerciciosSolucoes.java
 * 
 * @author luiscaparroz
 * @version 1.0
 * @since JDK 1.8
 */
public class ExerciciosStrings {
    
    public static void main(String[] args) {
        System.out.println("=== EXERCÍCIOS DE MANIPULAÇÃO DE STRINGS ===\n");
        
        // Executar todos os testes
        testarNivelBasico();
        testarNivelIntermediario();
        testarNivelAvancado();
        
        System.out.println("=== FIM DOS EXERCÍCIOS ===");
    }
    
    // ==================== NÍVEL BÁSICO ====================
    
    public static void testarNivelBasico() {
        System.out.println("🟢 EXERCÍCIOS - NÍVEL BÁSICO");
        System.out.println("═══════════════════════════════");
        
        // Exercício 1
        System.out.println("Exercício 1 - Contador de Caracteres:");
        String texto1 = "Programação Java";
        System.out.println("Texto: \"" + texto1 + "\"");
        System.out.println("Vogais: " + contarVogais(texto1));
        System.out.println("Consoantes: " + contarConsoantes(texto1));
        System.out.println("Espaços: " + contarEspacos(texto1));
        System.out.println();
        
        // Exercício 2
        System.out.println("Exercício 2 - Inversor de Texto:");
        String texto2 = "Java";
        System.out.println("Original: \"" + texto2 + "\"");
        System.out.println("Invertido: \"" + inverterTexto(texto2) + "\"");
        System.out.println();
        
        // Exercício 3
        System.out.println("Exercício 3 - Gerador de Iniciais:");
        String nome = "João Silva Santos";
        System.out.println("Nome: \"" + nome + "\"");
        System.out.println("Iniciais: \"" + gerarIniciais(nome) + "\"");
        System.out.println();
        
        // Exercício 4
        System.out.println("Exercício 4 - Verificar Palíndromo:");
        String[] palavras = {"arara", "java", "osso", "programacao"};
        for (String palavra : palavras) {
            System.out.println("\"" + palavra + "\" é palíndromo? " + ehPalindromo(palavra));
        }
        System.out.println();
        
        // Exercício 5
        System.out.println("Exercício 5 - Capitalizar Primeira Letra:");
        String frase = "java é uma linguagem poderosa";
        System.out.println("Original: \"" + frase + "\"");
        System.out.println("Capitalizada: \"" + capitalizarPrimeira(frase) + "\"");
        System.out.println();
    }
    
    // ==================== NÍVEL INTERMEDIÁRIO ====================
    
    public static void testarNivelIntermediario() {
        System.out.println("🟡 EXERCÍCIOS - NÍVEL INTERMEDIÁRIO");
        System.out.println("═══════════════════════════════════════");
        
        // Exercício 6
        System.out.println("Exercício 6 - Validador de CPF:");
        String[] cpfs = {"12345678901", "111.222.333-44", "000.000.000-00"};
        for (String cpf : cpfs) {
            System.out.println("CPF: \"" + cpf + "\" - Válido: " + validarCPF(cpf));
        }
        System.out.println();
        
        // Exercício 7
        System.out.println("Exercício 7 - Formatador de Telefone:");
        String[] telefones = {"11987654321", "1133334444", "85999887766"};
        for (String telefone : telefones) {
            System.out.println("\"" + telefone + "\" -> \"" + formatarTelefone(telefone) + "\"");
        }
        System.out.println();
        
        // Exercício 8
        System.out.println("Exercício 8 - Gerador de Slug:");
        String titulo = "Como Aprender Java Rapidamente!";
        System.out.println("Título: \"" + titulo + "\"");
        System.out.println("Slug: \"" + gerarSlug(titulo) + "\"");
        System.out.println();
        
        // Exercício 9
        System.out.println("Exercício 9 - Contador de Palavras:");
        String paragrafo = "Java é uma linguagem de programação. Java é multiplataforma.";
        System.out.println("Texto: \"" + paragrafo + "\"");
        System.out.println("Palavras únicas: " + contarPalavrasUnicas(paragrafo));
        System.out.println();
        
        // Exercício 10
        System.out.println("Exercício 10 - Validador de Senha Forte:");
        String[] senhas = {"123456", "password", "Password1", "MyP@ssw0rd123"};
        for (String senha : senhas) {
            System.out.println("\"" + senha + "\" é forte? " + ehSenhaForte(senha));
        }
        System.out.println();
    }
    
    // ==================== NÍVEL AVANÇADO ====================
    
    public static void testarNivelAvancado() {
        System.out.println("🔴 EXERCÍCIOS - NÍVEL AVANÇADO");
        System.out.println("═════════════════════════════════");
        
        // Exercício 11
        System.out.println("Exercício 11 - Calculadora de Expressões:");
        String expressao = "2+3*4";
        System.out.println("Expressão: \"" + expressao + "\"");
        System.out.println("Resultado: " + calcularExpressao(expressao));
        System.out.println();
        
        // Exercício 12
        System.out.println("Exercício 12 - Highlight de Sintaxe:");
        String codigo = "public class Hello { public static void main() }";
        System.out.println("Código: \"" + codigo + "\"");
        System.out.println("Com highlight: \"" + highlightJava(codigo) + "\"");
        System.out.println();
        
        // Exercício 13
        System.out.println("Exercício 13 - Gerador de Senhas:");
        int tamanho = 12;
        boolean incluirEspeciais = true;
        String senhaGerada = gerarSenhaSegura(tamanho, incluirEspeciais);
        System.out.println("Senha gerada (" + tamanho + " chars): \"" + senhaGerada + "\"");
        System.out.println();
        
        // Exercício 14
        System.out.println("Exercício 14 - Compressor de Texto:");
        String textoOriginal = "aaabbbcccdddd";
        System.out.println("Original: \"" + textoOriginal + "\"");
        System.out.println("Comprimido: \"" + comprimirTexto(textoOriginal) + "\"");
        System.out.println();
        
        // Exercício 15
        System.out.println("Exercício 15 - Analisador de Texto:");
        String textoAnalise = "Este é um texto de exemplo para análise. Este texto tem várias palavras.";
        System.out.println("Análise do texto:");
        analisarTexto(textoAnalise);
        System.out.println();
    }
    
    // ==================== MÉTODOS DOS EXERCÍCIOS ====================
    // Implemente as soluções abaixo
    
    // NÍVEL BÁSICO
    
    /**
     * Exercício 1: Conte o número de vogais em um texto
     * Exemplo: "Java" -> 2 (a, a)
     */
    public static int contarVogais(String texto) {
        // TODO: Implementar contagem de vogais (a, e, i, o, u)
        return 0; // Substitua pela implementação
    }
    
    /**
     * Exercício 1: Conte o número de consoantes em um texto
     * Exemplo: "Java" -> 2 (J, v)
     */
    public static int contarConsoantes(String texto) {
        // TODO: Implementar contagem de consoantes
        return 0; // Substitua pela implementação
    }
    
    /**
     * Exercício 1: Conte o número de espaços em um texto
     * Exemplo: "Java é bom" -> 2
     */
    public static int contarEspacos(String texto) {
        // TODO: Implementar contagem de espaços
        return 0; // Substitua pela implementação
    }
    
    /**
     * Exercício 2: Inverta um texto
     * Exemplo: "Java" -> "avaJ"
     */
    public static String inverterTexto(String texto) {
        // TODO: Implementar inversão de texto
        return ""; // Substitua pela implementação
    }
    
    /**
     * Exercício 3: Gere as iniciais de um nome
     * Exemplo: "João Silva Santos" -> "JSS"
     */
    public static String gerarIniciais(String nomeCompleto) {
        // TODO: Implementar geração de iniciais
        return ""; // Substitua pela implementação
    }
    
    /**
     * Exercício 4: Verifique se uma palavra é palíndromo
     * Exemplo: "arara" -> true, "java" -> false
     */
    public static boolean ehPalindromo(String palavra) {
        // TODO: Implementar verificação de palíndromo
        return false; // Substitua pela implementação
    }
    
    /**
     * Exercício 5: Capitalize a primeira letra de cada palavra
     * Exemplo: "java é bom" -> "Java É Bom"
     */
    public static String capitalizarPrimeira(String frase) {
        // TODO: Implementar capitalização da primeira letra
        return ""; // Substitua pela implementação
    }
    
    // NÍVEL INTERMEDIÁRIO
    
    /**
     * Exercício 6: Valide um CPF (formato: XXX.XXX.XXX-XX ou apenas números)
     * Exemplo: "123.456.789-01" -> verificar se é válido
     */
    public static boolean validarCPF(String cpf) {
        // TODO: Implementar validação de CPF
        return false; // Substitua pela implementação
    }
    
    /**
     * Exercício 7: Formate um telefone no padrão (XX) XXXXX-XXXX
     * Exemplo: "11987654321" -> "(11) 98765-4321"
     */
    public static String formatarTelefone(String telefone) {
        // TODO: Implementar formatação de telefone
        return ""; // Substitua pela implementação
    }
    
    /**
     * Exercício 8: Gere um slug URL-friendly a partir de um título
     * Exemplo: "Como Aprender Java!" -> "como-aprender-java"
     */
    public static String gerarSlug(String titulo) {
        // TODO: Implementar geração de slug
        return ""; // Substitua pela implementação
    }
    
    /**
     * Exercício 9: Conte palavras únicas em um texto
     * Exemplo: "Java é bom. Java é útil." -> 4 (Java, é, bom, útil)
     */
    public static int contarPalavrasUnicas(String texto) {
        // TODO: Implementar contagem de palavras únicas
        return 0; // Substitua pela implementação
    }
    
    /**
     * Exercício 10: Verifique se uma senha é forte
     * Critérios: 8+ chars, maiúscula, minúscula, número, caractere especial
     */
    public static boolean ehSenhaForte(String senha) {
        // TODO: Implementar verificação de senha forte
        return false; // Substitua pela implementação
    }
    
    // NÍVEL AVANÇADO
    
    /**
     * Exercício 11: Calcule uma expressão matemática simples
     * Exemplo: "2+3*4" -> 14 (considere precedência)
     */
    public static double calcularExpressao(String expressao) {
        // TODO: Implementar calculadora de expressões
        return 0.0; // Substitua pela implementação
    }
    
    /**
     * Exercício 12: Destaque palavras-chave de Java em um código
     * Exemplo: "public class" -> "[public] [class]"
     */
    public static String highlightJava(String codigo) {
        // TODO: Implementar highlight de sintaxe
        return ""; // Substitua pela implementação
    }
    
    /**
     * Exercício 13: Gere uma senha segura
     * Parâmetros: tamanho, incluir caracteres especiais
     */
    public static String gerarSenhaSegura(int tamanho, boolean incluirEspeciais) {
        // TODO: Implementar gerador de senhas
        return ""; // Substitua pela implementação
    }
    
    /**
     * Exercício 14: Comprima texto usando run-length encoding
     * Exemplo: "aaabbbccc" -> "a3b3c3"
     */
    public static String comprimirTexto(String texto) {
        // TODO: Implementar compressão de texto
        return ""; // Substitua pela implementação
    }
    
    /**
     * Exercício 15: Analise um texto e exiba estatísticas
     * Mostrar: caracteres, palavras, frases, palavra mais comum
     */
    public static void analisarTexto(String texto) {
        // TODO: Implementar análise completa de texto
        System.out.println("  Análise não implementada ainda");
    }
}