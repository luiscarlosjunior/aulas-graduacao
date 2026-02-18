/**
 * Demonstração de Manipulação de Strings em Java
 * 
 * Este programa demonstra os principais métodos para manipular strings em Java.
 * Strings são sequências de caracteres e são fundamentais na programação.
 * 
 * Conceitos abordados:
 * - Métodos básicos de String
 * - Comparação de strings
 * - Transformações de texto
 * - Busca e substituição
 * - StringBuilder para construção eficiente
 * - Formatação de strings
 * 
 * @author luiscaparroz
 * @version 1.0
 * @since JDK 1.8
 */
public class ManipulacaoStrings {
    
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO: MANIPULAÇÃO DE STRINGS ===\n");
        
        // Executar todos os exemplos
        exemploMetodosBasicos();
        exemploComparacaoStrings();
        exemploTransformacoes();
        exemploBuscaSubstituicao();
        exemploSplitJoin();
        exemploStringBuilder();
        exemploFormatacao();
        
        System.out.println("\n=== FIM DOS EXEMPLOS ===");
    }
    
    /**
     * Demonstra métodos básicos para trabalhar com strings
     */
    public static void exemploMetodosBasicos() {
        System.out.println("EXEMPLO 1: Métodos Básicos de String");
        System.out.println("═══════════════════════════════════════");
        
        String texto = "Programação Java";
        
        // Obter informações básicas
        System.out.println("Texto original: \"" + texto + "\"");
        System.out.println("Comprimento: " + texto.length() + " caracteres");
        System.out.println("Está vazio? " + texto.isEmpty());
        System.out.println("Está em branco? " + texto.isBlank());
        
        // Acessar caracteres específicos
        System.out.println("\nAcesso a caracteres:");
        System.out.println("Primeiro caractere: '" + texto.charAt(0) + "'");
        System.out.println("Último caractere: '" + texto.charAt(texto.length() - 1) + "'");
        System.out.println("Caractere na posição 5: '" + texto.charAt(5) + "'");
        
        // Buscar posições
        System.out.println("\nBusca de posições:");
        System.out.println("Posição de 'a': " + texto.indexOf('a'));
        System.out.println("Última posição de 'a': " + texto.lastIndexOf('a'));
        System.out.println("Posição de \"Java\": " + texto.indexOf("Java"));
        System.out.println("Posição de \"Python\" (não existe): " + texto.indexOf("Python"));
        
        // Extrair substrings
        System.out.println("\nExtração de substrings:");
        System.out.println("Do índice 0 ao 11: \"" + texto.substring(0, 11) + "\"");
        System.out.println("A partir do índice 12: \"" + texto.substring(12) + "\"");
        
        System.out.println();
    }
    
    /**
     * Demonstra diferentes formas de comparar strings
     */
    public static void exemploComparacaoStrings() {
        System.out.println("EXEMPLO 2: Comparação de Strings");
        System.out.println("═════════════════════════════════");
        
        String texto1 = "Java";
        String texto2 = "java";
        String texto3 = "Java";
        String texto4 = new String("Java");
        
        System.out.println("texto1 = \"" + texto1 + "\"");
        System.out.println("texto2 = \"" + texto2 + "\"");
        System.out.println("texto3 = \"" + texto3 + "\"");
        System.out.println("texto4 = new String(\"Java\")");
        
        // Comparação de igualdade
        System.out.println("\nComparação de igualdade:");
        System.out.println("texto1.equals(texto2): " + texto1.equals(texto2));
        System.out.println("texto1.equals(texto3): " + texto1.equals(texto3));
        System.out.println("texto1.equals(texto4): " + texto1.equals(texto4));
        System.out.println("texto1 == texto3: " + (texto1 == texto3));
        System.out.println("texto1 == texto4: " + (texto1 == texto4));
        
        // Comparação ignorando maiúsculas/minúsculas
        System.out.println("\nComparação ignorando case:");
        System.out.println("texto1.equalsIgnoreCase(texto2): " + texto1.equalsIgnoreCase(texto2));
        
        // Comparação lexicográfica
        System.out.println("\nComparação lexicográfica:");
        System.out.println("\"Java\".compareTo(\"Python\"): " + "Java".compareTo("Python"));
        System.out.println("\"Python\".compareTo(\"Java\"): " + "Python".compareTo("Java"));
        System.out.println("\"Java\".compareTo(\"Java\"): " + "Java".compareTo("Java"));
        System.out.println("\"Java\".compareToIgnoreCase(\"java\"): " + "Java".compareToIgnoreCase("java"));
        
        // IMPORTANTE: Sempre use equals() para comparar strings!
        System.out.println("\n💡 LEMBRE-SE: Sempre use equals() para comparar o conteúdo de strings!");
        
        System.out.println();
    }
    
    /**
     * Demonstra transformações comuns em strings
     */
    public static void exemploTransformacoes() {
        System.out.println("EXEMPLO 3: Transformações de Strings");
        System.out.println("═══════════════════════════════════════");
        
        String texto = "  Aprendendo JAVA é Divertido!  ";
        
        System.out.println("Texto original: \"" + texto + "\"");
        
        // Mudanças de case
        System.out.println("\nMudanças de case:");
        System.out.println("toUpperCase(): \"" + texto.toUpperCase() + "\"");
        System.out.println("toLowerCase(): \"" + texto.toLowerCase() + "\"");
        
        // Remoção de espaços
        System.out.println("\nRemoção de espaços:");
        System.out.println("trim(): \"" + texto.trim() + "\"");
        System.out.println("strip(): \"" + texto.strip() + "\"");
        
        // Repetição de strings (Java 11+)
        String linha = "-";
        System.out.println("\nRepetição:");
        System.out.println("Linha repetida 20 vezes: " + linha.repeat(20));
        
        // Verificação de conteúdo
        System.out.println("\nVerificações de conteúdo:");
        String textoLimpo = texto.trim();
        System.out.println("Contém 'JAVA': " + textoLimpo.contains("JAVA"));
        System.out.println("Contém 'java' (ignorando case): " + textoLimpo.toLowerCase().contains("java"));
        System.out.println("Começa com 'Aprendendo': " + textoLimpo.startsWith("Aprendendo"));
        System.out.println("Termina com 'Divertido!': " + textoLimpo.endsWith("Divertido!"));
        
        System.out.println();
    }
    
    /**
     * Demonstra busca e substituição em strings
     */
    public static void exemploBuscaSubstituicao() {
        System.out.println("EXEMPLO 4: Busca e Substituição");
        System.out.println("══════════════════════════════════");
        
        String frase = "Java é incrível! Eu amo programar em Java.";
        
        System.out.println("Frase original: \"" + frase + "\"");
        
        // Substituições simples
        System.out.println("\nSubstituições:");
        System.out.println("Substituir 'Java' por 'Python':");
        System.out.println("  replace(): \"" + frase.replace("Java", "Python") + "\"");
        System.out.println("  replaceFirst(): \"" + frase.replaceFirst("Java", "Python") + "\"");
        
        // Substituição com regex
        String textoComNumeros = "Meu telefone é 11-98765-4321 e CEP 01234-567";
        System.out.println("\nTexto com números: \"" + textoComNumeros + "\"");
        System.out.println("Remover números: \"" + textoComNumeros.replaceAll("\\d", "X") + "\"");
        System.out.println("Remover hífens: \"" + textoComNumeros.replaceAll("-", "") + "\"");
        
        // Substituições mais complexas
        String email = "usuario@exemplo.com.br";
        System.out.println("\nEmail: \"" + email + "\"");
        System.out.println("Ocultar domínio: \"" + email.replaceAll("@.*", "@***") + "\"");
        
        System.out.println();
    }
    
    /**
     * Demonstra como dividir e juntar strings
     */
    public static void exemploSplitJoin() {
        System.out.println("EXEMPLO 5: Split e Join");
        System.out.println("══════════════════════════");
        
        // Dividindo strings
        String frase = "maçã,banana,laranja,uva,manga";
        System.out.println("Lista de frutas: \"" + frase + "\"");
        
        String[] frutas = frase.split(",");
        System.out.println("\nApós split(','):");
        for (int i = 0; i < frutas.length; i++) {
            System.out.println("  [" + i + "] = \"" + frutas[i] + "\"");
        }
        
        // Dividindo com regex mais complexo
        String texto = "palavra1    palavra2\tpalavra3\npalavra4";
        System.out.println("\nTexto com espaços mistos: \"" + texto.replace("\n", "\\n").replace("\t", "\\t") + "\"");
        String[] palavras = texto.split("\\s+");
        System.out.println("Palavras separadas:");
        for (String palavra : palavras) {
            System.out.println("  \"" + palavra + "\"");
        }
        
        // Juntando strings
        System.out.println("\nJuntando strings:");
        String frutasUnidas = String.join(" | ", frutas);
        System.out.println("Com ' | ': " + frutasUnidas);
        
        String frutasComVirgula = String.join(", ", frutas);
        System.out.println("Com ', ': " + frutasComVirgula);
        
        // Juntando com diferentes separadores
        String[] caminhos = {"home", "usuario", "documentos", "arquivo.txt"};
        String caminho = String.join("/", caminhos);
        System.out.println("Caminho: " + caminho);
        
        System.out.println();
    }
    
    /**
     * Demonstra o uso de StringBuilder para construção eficiente de strings
     */
    public static void exemploStringBuilder() {
        System.out.println("EXEMPLO 6: StringBuilder");
        System.out.println("═══════════════════════════");
        
        // Problema: concatenação ineficiente
        System.out.println("📚 CONCEITO: String é imutável em Java!");
        System.out.println("Cada concatenação cria uma nova string na memória.\n");
        
        // StringBuilder - forma eficiente
        StringBuilder sb = new StringBuilder();
        sb.append("Construindo");
        sb.append(" uma string");
        sb.append(" de forma");
        sb.append(" eficiente!");
        
        System.out.println("StringBuilder resultado: \"" + sb.toString() + "\"");
        
        // Métodos úteis do StringBuilder
        StringBuilder construtor = new StringBuilder("Java");
        System.out.println("\nMétodos do StringBuilder:");
        System.out.println("Inicial: \"" + construtor + "\"");
        
        construtor.append(" é fantástico");
        System.out.println("Após append: \"" + construtor + "\"");
        
        construtor.insert(4, " 17");
        System.out.println("Após insert: \"" + construtor + "\"");
        
        construtor.replace(0, 7, "Python");
        System.out.println("Após replace: \"" + construtor + "\"");
        
        construtor.reverse();
        System.out.println("Após reverse: \"" + construtor + "\"");
        
        construtor.reverse(); // Voltando ao normal
        construtor.delete(0, 6); // Remove "Python"
        construtor.insert(0, "Java");
        System.out.println("Após correções: \"" + construtor + "\"");
        
        // Exemplo prático: construindo HTML
        StringBuilder html = new StringBuilder();
        html.append("<html>")
            .append("<head><title>Página de Teste</title></head>")
            .append("<body>")
            .append("<h1>Bem-vindo!</h1>")
            .append("<p>Esta página foi gerada com StringBuilder.</p>")
            .append("</body>")
            .append("</html>");
        
        System.out.println("\nHTML gerado:");
        System.out.println(html.toString());
        
        System.out.println();
    }
    
    /**
     * Demonstra formatação de strings
     */
    public static void exemploFormatacao() {
        System.out.println("EXEMPLO 7: Formatação de Strings");
        System.out.println("═══════════════════════════════════");
        
        String nome = "Ana";
        int idade = 25;
        double salario = 5500.75;
        
        // String.format()
        System.out.println("String.format():");
        String informacoes = String.format("Nome: %s, Idade: %d, Salário: R$ %.2f", nome, idade, salario);
        System.out.println(informacoes);
        
        // System.out.printf()
        System.out.println("\nSystem.out.printf():");
        System.out.printf("Nome: %s%n", nome);
        System.out.printf("Idade: %d anos%n", idade);
        System.out.printf("Salário: R$ %,.2f%n", salario);
        
        // Formatação de números
        System.out.println("\nFormatação de números:");
        System.out.printf("Decimal: %d%n", 42);
        System.out.printf("Hexadecimal: %x%n", 42);
        System.out.printf("Octal: %o%n", 42);
        System.out.printf("Científico: %e%n", 1234.5);
        System.out.printf("Ponto flutuante: %.3f%n", Math.PI);
        
        // Alinhamento e preenchimento
        System.out.println("\nAlinhamento:");
        System.out.printf("Direita: '%10s'%n", "Java");
        System.out.printf("Esquerda: '%-10s'%n", "Java");
        System.out.printf("Preenchimento com zeros: '%010d'%n", 42);
        
        // Formatação de data/hora
        System.out.println("\nData e hora:");
        java.util.Date agora = new java.util.Date();
        System.out.printf("Data: %tF%n", agora);
        System.out.printf("Hora: %tT%n", agora);
        System.out.printf("Data/hora completa: %tc%n", agora);
        
        System.out.println();
    }
}