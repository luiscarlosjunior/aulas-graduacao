/**
 * Soluções dos Exercícios de Manipulação de Strings
 * 
 * Esta classe contém as soluções completas para todos os exercícios
 * de manipulação de strings, organizadas por nível de dificuldade.
 * 
 * COMO USAR:
 * 1. Tente resolver os exercícios em ExerciciosStrings.java primeiro
 * 2. Após suas tentativas, compare com as soluções aqui
 * 3. Analise as diferentes abordagens e otimizações
 * 4. Execute este arquivo para ver as soluções funcionando
 * 
 * @author luiscaparroz
 * @version 1.0
 * @since JDK 1.8
 */
import java.util.*;

public class ExerciciosSolucoes {
    
    public static void main(String[] args) {
        System.out.println("=== SOLUÇÕES DOS EXERCÍCIOS DE STRINGS ===\n");
        
        // Demonstrar todas as soluções
        demonstrarNivelBasico();
        demonstrarNivelIntermediario();
        demonstrarNivelAvancado();
        
        System.out.println("=== FIM DAS SOLUÇÕES ===");
    }
    
    // ==================== DEMONSTRAÇÕES ====================
    
    public static void demonstrarNivelBasico() {
        System.out.println("🟢 SOLUÇÕES - NÍVEL BÁSICO");
        System.out.println("═══════════════════════════");
        
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
        String[] palavras = {"arara", "java", "osso", "programacao", "ovo", "radar"};
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
    
    public static void demonstrarNivelIntermediario() {
        System.out.println("🟡 SOLUÇÕES - NÍVEL INTERMEDIÁRIO");
        System.out.println("═════════════════════════════════════");
        
        // Exercício 6
        System.out.println("Exercício 6 - Validador de CPF:");
        String[] cpfs = {"12345678901", "111.222.333-44", "000.000.000-00", "123.456.789-09"};
        for (String cpf : cpfs) {
            System.out.println("CPF: \"" + cpf + "\" - Válido: " + validarCPF(cpf));
        }
        System.out.println();
        
        // Exercício 7
        System.out.println("Exercício 7 - Formatador de Telefone:");
        String[] telefones = {"11987654321", "1133334444", "85999887766", "123456"};
        for (String telefone : telefones) {
            System.out.println("\"" + telefone + "\" -> \"" + formatarTelefone(telefone) + "\"");
        }
        System.out.println();
        
        // Exercício 8
        System.out.println("Exercício 8 - Gerador de Slug:");
        String[] titulos = {"Como Aprender Java Rapidamente!", "Programação em Java 2024", "O que é POO?"};
        for (String titulo : titulos) {
            System.out.println("\"" + titulo + "\" -> \"" + gerarSlug(titulo) + "\"");
        }
        System.out.println();
        
        // Exercício 9
        System.out.println("Exercício 9 - Contador de Palavras:");
        String paragrafo = "Java é uma linguagem de programação. Java é multiplataforma.";
        System.out.println("Texto: \"" + paragrafo + "\"");
        System.out.println("Palavras únicas: " + contarPalavrasUnicas(paragrafo));
        System.out.println();
        
        // Exercício 10
        System.out.println("Exercício 10 - Validador de Senha Forte:");
        String[] senhas = {"123456", "password", "Password1", "MyP@ssw0rd123", "abcd", "ABCD1234"};
        for (String senha : senhas) {
            System.out.println("\"" + senha + "\" é forte? " + ehSenhaForte(senha));
        }
        System.out.println();
    }
    
    public static void demonstrarNivelAvancado() {
        System.out.println("🔴 SOLUÇÕES - NÍVEL AVANÇADO");
        System.out.println("═══════════════════════════");
        
        // Exercício 11
        System.out.println("Exercício 11 - Calculadora de Expressões:");
        String[] expressoes = {"2+3*4", "10-2*3", "8/2+1", "5*2-3"};
        for (String expressao : expressoes) {
            System.out.println("\"" + expressao + "\" = " + calcularExpressao(expressao));
        }
        System.out.println();
        
        // Exercício 12
        System.out.println("Exercício 12 - Highlight de Sintaxe:");
        String codigo = "public class Hello { public static void main() }";
        System.out.println("Código: \"" + codigo + "\"");
        System.out.println("Com highlight: \"" + highlightJava(codigo) + "\"");
        System.out.println();
        
        // Exercício 13
        System.out.println("Exercício 13 - Gerador de Senhas:");
        System.out.println("Senhas geradas:");
        for (int i = 0; i < 3; i++) {
            System.out.println("  8 chars sem especiais: \"" + gerarSenhaSegura(8, false) + "\"");
            System.out.println("  12 chars com especiais: \"" + gerarSenhaSegura(12, true) + "\"");
        }
        System.out.println();
        
        // Exercício 14
        System.out.println("Exercício 14 - Compressor de Texto:");
        String[] textos = {"aaabbbcccdddd", "abcdefg", "aabbccdd", "aaaaa"};
        for (String texto : textos) {
            System.out.println("\"" + texto + "\" -> \"" + comprimirTexto(texto) + "\"");
        }
        System.out.println();
        
        // Exercício 15
        System.out.println("Exercício 15 - Analisador de Texto:");
        String textoAnalise = "Este é um texto de exemplo para análise. Este texto tem várias palavras. Análise é importante!";
        System.out.println("Texto: \"" + textoAnalise + "\"");
        analisarTexto(textoAnalise);
        System.out.println();
    }
    
    // ==================== SOLUÇÕES DOS EXERCÍCIOS ====================
    
    // NÍVEL BÁSICO
    
    /**
     * SOLUÇÃO 1: Conta vogais em um texto
     */
    public static int contarVogais(String texto) {
        if (texto == null) return 0;
        
        int contador = 0;
        String vogais = "aeiouAEIOU";
        
        for (int i = 0; i < texto.length(); i++) {
            if (vogais.indexOf(texto.charAt(i)) != -1) {
                contador++;
            }
        }
        
        return contador;
    }
    
    /**
     * SOLUÇÃO 1: Conta consoantes em um texto
     */
    public static int contarConsoantes(String texto) {
        if (texto == null) return 0;
        
        int contador = 0;
        String vogais = "aeiouAEIOU";
        
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (Character.isLetter(c) && vogais.indexOf(c) == -1) {
                contador++;
            }
        }
        
        return contador;
    }
    
    /**
     * SOLUÇÃO 1: Conta espaços em um texto
     */
    public static int contarEspacos(String texto) {
        if (texto == null) return 0;
        
        int contador = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == ' ') {
                contador++;
            }
        }
        
        return contador;
    }
    
    /**
     * SOLUÇÃO 2: Inverte um texto
     */
    public static String inverterTexto(String texto) {
        if (texto == null) return null;
        
        StringBuilder sb = new StringBuilder(texto);
        return sb.reverse().toString();
        
        // Solução alternativa manual:
        // StringBuilder resultado = new StringBuilder();
        // for (int i = texto.length() - 1; i >= 0; i--) {
        //     resultado.append(texto.charAt(i));
        // }
        // return resultado.toString();
    }
    
    /**
     * SOLUÇÃO 3: Gera iniciais de um nome
     */
    public static String gerarIniciais(String nomeCompleto) {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            return "";
        }
        
        String[] palavras = nomeCompleto.trim().split("\\s+");
        StringBuilder iniciais = new StringBuilder();
        
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                iniciais.append(Character.toUpperCase(palavra.charAt(0)));
            }
        }
        
        return iniciais.toString();
    }
    
    /**
     * SOLUÇÃO 4: Verifica se uma palavra é palíndromo
     */
    public static boolean ehPalindromo(String palavra) {
        if (palavra == null) return false;
        
        String limpa = palavra.toLowerCase().replaceAll("[^a-z]", "");
        String invertida = inverterTexto(limpa);
        
        return limpa.equals(invertida);
    }
    
    /**
     * SOLUÇÃO 5: Capitaliza primeira letra de cada palavra
     */
    public static String capitalizarPrimeira(String frase) {
        if (frase == null || frase.isEmpty()) {
            return frase;
        }
        
        String[] palavras = frase.split(" ");
        StringBuilder resultado = new StringBuilder();
        
        for (int i = 0; i < palavras.length; i++) {
            String palavra = palavras[i];
            
            if (!palavra.isEmpty()) {
                resultado.append(Character.toUpperCase(palavra.charAt(0)));
                if (palavra.length() > 1) {
                    resultado.append(palavra.substring(1).toLowerCase());
                }
            }
            
            if (i < palavras.length - 1) {
                resultado.append(" ");
            }
        }
        
        return resultado.toString();
    }
    
    // NÍVEL INTERMEDIÁRIO
    
    /**
     * SOLUÇÃO 6: Valida CPF
     */
    public static boolean validarCPF(String cpf) {
        if (cpf == null) return false;
        
        // Remove formatação
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        
        // Verifica se tem 11 dígitos
        if (cpfLimpo.length() != 11) return false;
        
        // Verifica se não são todos iguais
        if (cpfLimpo.matches("(\\d)\\1{10}")) return false;
        
        try {
            // Validação do primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpfLimpo.charAt(i)) * (10 - i);
            }
            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito >= 10) primeiroDigito = 0;
            
            // Validação do segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpfLimpo.charAt(i)) * (11 - i);
            }
            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito >= 10) segundoDigito = 0;
            
            // Verifica se os dígitos calculados conferem
            return Character.getNumericValue(cpfLimpo.charAt(9)) == primeiroDigito &&
                   Character.getNumericValue(cpfLimpo.charAt(10)) == segundoDigito;
                   
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * SOLUÇÃO 7: Formata telefone
     */
    public static String formatarTelefone(String telefone) {
        if (telefone == null) return "";
        
        String numeros = telefone.replaceAll("[^0-9]", "");
        
        if (numeros.length() == 11) {
            // Celular: (XX) XXXXX-XXXX
            return String.format("(%s) %s-%s", 
                numeros.substring(0, 2),
                numeros.substring(2, 7),
                numeros.substring(7));
        } else if (numeros.length() == 10) {
            // Fixo: (XX) XXXX-XXXX
            return String.format("(%s) %s-%s", 
                numeros.substring(0, 2),
                numeros.substring(2, 6),
                numeros.substring(6));
        } else {
            return "Formato inválido";
        }
    }
    
    /**
     * SOLUÇÃO 8: Gera slug para URLs
     */
    public static String gerarSlug(String titulo) {
        if (titulo == null) return "";
        
        return titulo.toLowerCase()
                    .trim()
                    .replaceAll("[áàãâä]", "a")
                    .replaceAll("[éèêë]", "e")
                    .replaceAll("[íìîï]", "i")
                    .replaceAll("[óòõôö]", "o")
                    .replaceAll("[úùûü]", "u")
                    .replaceAll("[ç]", "c")
                    .replaceAll("[^a-z0-9\\s]", "")
                    .replaceAll("\\s+", "-")
                    .replaceAll("^-|-$", "");
    }
    
    /**
     * SOLUÇÃO 9: Conta palavras únicas
     */
    public static int contarPalavrasUnicas(String texto) {
        if (texto == null || texto.isEmpty()) return 0;
        
        String textoLimpo = texto.toLowerCase().replaceAll("[^a-zA-Z\\s]", "");
        String[] palavras = textoLimpo.split("\\s+");
        
        Set<String> palavrasUnicas = new HashSet<>();
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                palavrasUnicas.add(palavra);
            }
        }
        
        return palavrasUnicas.size();
    }
    
    /**
     * SOLUÇÃO 10: Verifica se senha é forte
     */
    public static boolean ehSenhaForte(String senha) {
        if (senha == null || senha.length() < 8) return false;
        
        boolean temMinuscula = !senha.equals(senha.toUpperCase());
        boolean temMaiuscula = !senha.equals(senha.toLowerCase());
        boolean temNumero = senha.matches(".*\\d.*");
        boolean temEspecial = senha.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
        
        return temMinuscula && temMaiuscula && temNumero && temEspecial;
    }
    
    // NÍVEL AVANÇADO
    
    /**
     * SOLUÇÃO 11: Calculadora de expressões simples
     */
    public static double calcularExpressao(String expressao) {
        if (expressao == null || expressao.isEmpty()) return 0;
        
        // Remove espaços
        expressao = expressao.replaceAll("\\s", "");
        
        try {
            // Esta é uma implementação simplificada
            // Para expressões mais complexas, seria necessário um parser completo
            
            // Primeiro resolve multiplicações e divisões
            while (expressao.contains("*") || expressao.contains("/")) {
                for (int i = 0; i < expressao.length(); i++) {
                    char op = expressao.charAt(i);
                    if (op == '*' || op == '/') {
                        // Encontra o número antes
                        int inicioNum1 = i - 1;
                        while (inicioNum1 > 0 && (Character.isDigit(expressao.charAt(inicioNum1 - 1)) || 
                               expressao.charAt(inicioNum1 - 1) == '.')) {
                            inicioNum1--;
                        }
                        
                        // Encontra o número depois
                        int fimNum2 = i + 1;
                        while (fimNum2 < expressao.length() - 1 && 
                               (Character.isDigit(expressao.charAt(fimNum2 + 1)) || 
                                expressao.charAt(fimNum2 + 1) == '.')) {
                            fimNum2++;
                        }
                        
                        double num1 = Double.parseDouble(expressao.substring(inicioNum1, i));
                        double num2 = Double.parseDouble(expressao.substring(i + 1, fimNum2 + 1));
                        
                        double resultado = (op == '*') ? num1 * num2 : num1 / num2;
                        
                        expressao = expressao.substring(0, inicioNum1) + resultado + 
                                   expressao.substring(fimNum2 + 1);
                        break;
                    }
                }
            }
            
            // Depois resolve somas e subtrações
            while (expressao.contains("+") || expressao.contains("-")) {
                for (int i = 1; i < expressao.length(); i++) { // Começa em 1 para evitar sinal negativo
                    char op = expressao.charAt(i);
                    if (op == '+' || op == '-') {
                        // Encontra o número antes
                        int inicioNum1 = i - 1;
                        while (inicioNum1 > 0 && (Character.isDigit(expressao.charAt(inicioNum1 - 1)) || 
                               expressao.charAt(inicioNum1 - 1) == '.')) {
                            inicioNum1--;
                        }
                        
                        // Encontra o número depois
                        int fimNum2 = i + 1;
                        while (fimNum2 < expressao.length() - 1 && 
                               (Character.isDigit(expressao.charAt(fimNum2 + 1)) || 
                                expressao.charAt(fimNum2 + 1) == '.')) {
                            fimNum2++;
                        }
                        
                        double num1 = Double.parseDouble(expressao.substring(inicioNum1, i));
                        double num2 = Double.parseDouble(expressao.substring(i + 1, fimNum2 + 1));
                        
                        double resultado = (op == '+') ? num1 + num2 : num1 - num2;
                        
                        expressao = expressao.substring(0, inicioNum1) + resultado + 
                                   expressao.substring(fimNum2 + 1);
                        break;
                    }
                }
            }
            
            return Double.parseDouble(expressao);
            
        } catch (Exception e) {
            return 0;
        }
    }
    
    /**
     * SOLUÇÃO 12: Highlight de palavras-chave Java
     */
    public static String highlightJava(String codigo) {
        if (codigo == null) return "";
        
        String[] palavrasChave = {
            "public", "private", "protected", "static", "final", "abstract",
            "class", "interface", "extends", "implements", "void", "int",
            "String", "boolean", "double", "float", "char", "byte", "short",
            "long", "if", "else", "for", "while", "do", "switch", "case",
            "break", "continue", "return", "try", "catch", "finally",
            "throw", "throws", "new", "this", "super", "null", "true", "false"
        };
        
        String resultado = codigo;
        for (String palavra : palavrasChave) {
            resultado = resultado.replaceAll("\\b" + palavra + "\\b", "[" + palavra + "]");
        }
        
        return resultado;
    }
    
    /**
     * SOLUÇÃO 13: Gera senha segura
     */
    public static String gerarSenhaSegura(int tamanho, boolean incluirEspeciais) {
        String minusculas = "abcdefghijklmnopqrstuvwxyz";
        String maiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";
        String especiais = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        
        String caracteres = minusculas + maiusculas + numeros;
        if (incluirEspeciais) {
            caracteres += especiais;
        }
        
        Random random = new Random();
        StringBuilder senha = new StringBuilder();
        
        // Garante pelo menos um de cada tipo
        senha.append(minusculas.charAt(random.nextInt(minusculas.length())));
        senha.append(maiusculas.charAt(random.nextInt(maiusculas.length())));
        senha.append(numeros.charAt(random.nextInt(numeros.length())));
        
        if (incluirEspeciais) {
            senha.append(especiais.charAt(random.nextInt(especiais.length())));
        }
        
        // Preenche o resto
        while (senha.length() < tamanho) {
            senha.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        
        // Embaralha a senha
        char[] array = senha.toString().toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        
        return new String(array);
    }
    
    /**
     * SOLUÇÃO 14: Comprime texto usando run-length encoding
     */
    public static String comprimirTexto(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        
        StringBuilder resultado = new StringBuilder();
        int contador = 1;
        char caracterAtual = texto.charAt(0);
        
        for (int i = 1; i < texto.length(); i++) {
            if (texto.charAt(i) == caracterAtual) {
                contador++;
            } else {
                resultado.append(caracterAtual);
                if (contador > 1) {
                    resultado.append(contador);
                }
                caracterAtual = texto.charAt(i);
                contador = 1;
            }
        }
        
        // Adiciona o último grupo
        resultado.append(caracterAtual);
        if (contador > 1) {
            resultado.append(contador);
        }
        
        return resultado.toString();
    }
    
    /**
     * SOLUÇÃO 15: Analisa texto completo
     */
    public static void analisarTexto(String texto) {
        if (texto == null) {
            System.out.println("  Texto nulo");
            return;
        }
        
        // Estatísticas básicas
        int totalCaracteres = texto.length();
        int caracteresComEspaco = totalCaracteres;
        int caracteresSemEspaco = texto.replaceAll("\\s", "").length();
        int espacos = caracteresComEspaco - caracteresSemEspaco;
        
        // Contagem de palavras
        String[] palavras = texto.toLowerCase().replaceAll("[^a-zA-Z\\s]", "").split("\\s+");
        int totalPalavras = palavras.length;
        
        // Contagem de frases (aproximada)
        int frases = texto.split("[.!?]+").length;
        
        // Palavra mais comum
        Map<String, Integer> frequenciaPalavras = new HashMap<>();
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                frequenciaPalavras.put(palavra, frequenciaPalavras.getOrDefault(palavra, 0) + 1);
            }
        }
        
        String palavraMaisComum = "";
        int maiorFrequencia = 0;
        for (Map.Entry<String, Integer> entry : frequenciaPalavras.entrySet()) {
            if (entry.getValue() > maiorFrequencia) {
                maiorFrequencia = entry.getValue();
                palavraMaisComum = entry.getKey();
            }
        }
        
        // Exibir resultados
        System.out.println("  Caracteres (com espaços): " + caracteresComEspaco);
        System.out.println("  Caracteres (sem espaços): " + caracteresSemEspaco);
        System.out.println("  Espaços: " + espacos);
        System.out.println("  Palavras: " + totalPalavras);
        System.out.println("  Palavras únicas: " + frequenciaPalavras.size());
        System.out.println("  Frases (aprox.): " + frases);
        System.out.println("  Palavra mais comum: \"" + palavraMaisComum + "\" (" + maiorFrequencia + " vezes)");
        System.out.println("  Média de palavras por frase: " + String.format("%.1f", (double) totalPalavras / frases));
        System.out.println("  Média de caracteres por palavra: " + String.format("%.1f", (double) caracteresSemEspaco / totalPalavras));
    }
}