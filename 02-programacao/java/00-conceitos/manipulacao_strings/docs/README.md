# Manipulação de Strings em Java

## 📋 Visão Geral

Strings (sequências de caracteres) são fundamentais na programação, presentes em praticamente todas as aplicações. Java oferece uma rica API para manipulação de texto, permitindo desde operações básicas até processamento complexo de dados textuais.

## 🎯 Objetivos de Aprendizado

Ao completar este tópico, você será capaz de:

- ✅ Compreender como Strings funcionam em Java (imutabilidade, pool de strings)
- ✅ Usar métodos essenciais da classe String
- ✅ Trabalhar com StringBuilder para eficiência
- ✅ Implementar validações e formatações de texto
- ✅ Processar e transformar dados textuais
- ✅ Aplicar expressões regulares básicas

## 🏗️ Conceitos Fundamentais

### Imutabilidade das Strings

```java
String texto = "Hello";
texto = texto + " World"; // Cria uma NOVA string, não modifica a original

// Internamente, o que acontece:
// 1. "Hello" é criado na memória
// 2. " World" é criado na memória  
// 3. Uma nova string "Hello World" é criada
// 4. A referência 'texto' aponta para a nova string
// 5. "Hello" original pode ser coletado pelo garbage collector
```

### Pool de Strings (String Pool)

```java
// Strings literais vão para o pool
String s1 = "Java";
String s2 = "Java";
System.out.println(s1 == s2); // true - mesma referência no pool

// new String() cria objeto fora do pool
String s3 = new String("Java");
System.out.println(s1 == s3); // false - referências diferentes
System.out.println(s1.equals(s3)); // true - mesmo conteúdo

// Forçar entrada no pool
String s4 = s3.intern();
System.out.println(s1 == s4); // true - agora no pool
```

## 🔧 Métodos Básicos da String

### Informações sobre a String

```java
String texto = "Aprendendo Java";

// Tamanho
int tamanho = texto.length(); // 15

// Verificar se está vazia
boolean vazia = texto.isEmpty(); // false

// Verificar se está vazia ou só espaços (Java 11+)
boolean blank = texto.isBlank(); // false

// Acessar caractere por posição
char primeiraLetra = texto.charAt(0); // 'A'
char ultimaLetra = texto.charAt(texto.length() - 1); // 'a'

// Encontrar posição de caractere ou substring
int posicao = texto.indexOf('e'); // 3 (primeira ocorrência)
int ultimaPosicao = texto.lastIndexOf('a'); // 14 (última ocorrência)
int posicaoJava = texto.indexOf("Java"); // 11
```

### Comparação de Strings

```java
String nome1 = "João";
String nome2 = "joão";
String nome3 = "JOÃO";

// Comparação exata (case-sensitive)
boolean iguais = nome1.equals(nome2); // false

// Comparação ignorando maiúsculas/minúsculas
boolean iguaisIgnoreCase = nome1.equalsIgnoreCase(nome2); // true

// Comparação lexicográfica
int comparacao = nome1.compareTo(nome3); // Número < 0, 0, ou > 0

// Começar com
boolean comeca = nome1.startsWith("Jo"); // true

// Terminar com
boolean termina = nome1.endsWith("ão"); // true

// Contém substring
boolean contem = nome1.contains("oã"); // true
```

### Transformações

```java
String texto = "  Aprendendo Java é Divertido!  ";

// Maiúscula e minúscula
String maiuscula = texto.toUpperCase(); // "  APRENDENDO JAVA É DIVERTIDO!  "
String minuscula = texto.toLowerCase(); // "  aprendendo java é divertido!  "

// Remover espaços
String semEspacos = texto.trim(); // "Aprendendo Java é Divertido!"
String semEspacosModerno = texto.strip(); // "Aprendendo Java é Divertido!" (Java 11+)

// Remover apenas no início ou fim (Java 11+)
String semEspacoInicio = texto.stripLeading();
String semEspacoFim = texto.stripTrailing();

// Substituições
String substituido = texto.replace("Java", "Python"); // Substitui todas ocorrências
String substituirPrimeira = texto.replaceFirst("a", "@"); // Só a primeira
String comRegex = texto.replaceAll("[aeiou]", "*"); // Com regex
```

### Extração de Substrings

```java
String frase = "Programação em Java";

// Substring a partir de posição
String parte1 = frase.substring(13); // "Java"

// Substring entre posições
String parte2 = frase.substring(0, 11); // "Programação"

// Dividir string
String[] palavras = frase.split(" "); // ["Programação", "em", "Java"]
String[] letras = frase.split(""); // Cada caractere

// Dividir com limite
String dados = "nome;idade;cidade;país";
String[] campos = dados.split(";", 3); // Máximo 3 partes
```

## 🔨 StringBuilder - Construção Eficiente

### Quando Usar StringBuilder

```java
// ❌ INEFICIENTE - cria muitas strings temporárias
String resultado = "";
for (int i = 1; i <= 1000; i++) {
    resultado += i + ", ";
}

// ✅ EFICIENTE - usa buffer interno
StringBuilder sb = new StringBuilder();
for (int i = 1; i <= 1000; i++) {
    sb.append(i).append(", ");
}
String resultado = sb.toString();
```

### Métodos do StringBuilder

```java
StringBuilder sb = new StringBuilder();

// Adicionar conteúdo
sb.append("Hello"); // "Hello"
sb.append(" ").append("World"); // "Hello World"
sb.append('!'); // "Hello World!"

// Inserir em posição específica
sb.insert(6, "Beautiful "); // "Hello Beautiful World!"

// Substituir parte
sb.replace(6, 15, "Amazing"); // "Hello Amazing World!"

// Deletar parte
sb.delete(6, 14); // "Hello World!"

// Reverter
sb.reverse(); // "!dlroW olleH"

// Converter para String
String resultado = sb.toString();

// Limpar conteúdo
sb.setLength(0); // ou sb.delete(0, sb.length())
```

## 💡 Casos de Uso Práticos

### 1. Validação de Email

```java
public class ValidadorEmail {
    
    public static boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        // Verificações básicas
        return email.contains("@") && 
               email.indexOf("@") > 0 && 
               email.lastIndexOf("@") == email.indexOf("@") && 
               email.indexOf("@") < email.lastIndexOf(".") && 
               email.lastIndexOf(".") < email.length() - 1;
    }
    
    public static String[] extrairPartesEmail(String email) {
        if (!validarEmail(email)) {
            return null;
        }
        
        int posicaoArroba = email.indexOf("@");
        String usuario = email.substring(0, posicaoArroba);
        String dominio = email.substring(posicaoArroba + 1);
        
        return new String[]{usuario, dominio};
    }
}
```

### 2. Formatação de Nomes

```java
public class FormatadorNome {
    
    public static String formatarNome(String nomeCompleto) {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            return "";
        }
        
        // Limpar e dividir
        String[] partes = nomeCompleto.trim().toLowerCase().split("\\s+");
        StringBuilder nomeFormatado = new StringBuilder();
        
        for (String parte : partes) {
            if (parte.length() > 0) {
                // Primeira letra maiúscula
                nomeFormatado.append(Character.toUpperCase(parte.charAt(0)))
                           .append(parte.substring(1))
                           .append(" ");
            }
        }
        
        return nomeFormatado.toString().trim();
    }
    
    public static String obterIniciais(String nomeCompleto) {
        String nomeFormatado = formatarNome(nomeCompleto);
        String[] partes = nomeFormatado.split(" ");
        StringBuilder iniciais = new StringBuilder();
        
        for (String parte : partes) {
            iniciais.append(parte.charAt(0)).append(".");
        }
        
        return iniciais.toString();
    }
}
```

### 3. Processador de Texto

```java
public class ProcessadorTexto {
    
    public static String limparTexto(String texto) {
        return texto.trim()
                   .replaceAll("\\s+", " ") // Múltiplos espaços -> um espaço
                   .replaceAll("[^\\w\\s]", ""); // Remove pontuação
    }
    
    public static int contarPalavras(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return 0;
        }
        return texto.trim().split("\\s+").length;
    }
    
    public static int contarVogais(String texto) {
        int contador = 0;
        String vogais = "aeiouAEIOU";
        
        for (int i = 0; i < texto.length(); i++) {
            if (vogais.indexOf(texto.charAt(i)) >= 0) {
                contador++;
            }
        }
        return contador;
    }
    
    public static String inverterPalavras(String frase) {
        String[] palavras = frase.split(" ");
        StringBuilder resultado = new StringBuilder();
        
        for (int i = palavras.length - 1; i >= 0; i--) {
            resultado.append(palavras[i]);
            if (i > 0) resultado.append(" ");
        }
        
        return resultado.toString();
    }
}
```

### 4. Gerador de Relatórios

```java
public class GeradorRelatorio {
    
    public static String criarCabecalho(String titulo, int largura) {
        StringBuilder sb = new StringBuilder();
        
        // Linha superior
        sb.append("+").append("-".repeat(largura - 2)).append("+\n");
        
        // Título centralizado
        int espacos = (largura - titulo.length() - 2) / 2;
        sb.append("|").append(" ".repeat(espacos))
          .append(titulo)
          .append(" ".repeat(largura - titulo.length() - espacos - 2))
          .append("|\n");
        
        // Linha inferior
        sb.append("+").append("-".repeat(largura - 2)).append("+\n");
        
        return sb.toString();
    }
    
    public static String formatarLinha(String... colunas) {
        StringBuilder sb = new StringBuilder("| ");
        
        for (int i = 0; i < colunas.length; i++) {
            sb.append(String.format("%-15s", colunas[i]));
            if (i < colunas.length - 1) {
                sb.append(" | ");
            }
        }
        sb.append(" |\n");
        
        return sb.toString();
    }
    
    public static String criarTabelaAlunos(String[][] alunos) {
        StringBuilder relatorio = new StringBuilder();
        
        relatorio.append(criarCabecalho("RELATÓRIO DE ALUNOS", 60));
        relatorio.append(formatarLinha("Nome", "Idade", "Nota"));
        relatorio.append("+").append("-".repeat(58)).append("+\n");
        
        for (String[] aluno : alunos) {
            relatorio.append(formatarLinha(aluno[0], aluno[1], aluno[2]));
        }
        
        relatorio.append("+").append("-".repeat(58)).append("+\n");
        
        return relatorio.toString();
    }
}
```

## 🎯 Formatação Avançada

### String.format() e printf()

```java
// Formatação de números
double valor = 1234.567;
String formatado = String.format("%.2f", valor); // "1234.57"

// Formatação com largura e alinhamento
String nome = "João";
String formatado2 = String.format("%-10s", nome); // "João      " (alinhado à esquerda)
String formatado3 = String.format("%10s", nome);  // "      João" (alinhado à direita)

// Formatação de inteiros
int numero = 42;
String hex = String.format("%x", numero);    // "2a" (hexadecimal)
String octal = String.format("%o", numero);  // "52" (octal)
String binario = String.format("%s", Integer.toBinaryString(numero)); // "101010"

// Formatação de data/hora
Date agora = new Date();
String dataFormatada = String.format("%tB %te, %tY", agora, agora, agora); // "Janeiro 15, 2024"

// Múltiplos valores
String relatorio = String.format("Nome: %s, Idade: %d, Salário: R$ %.2f", 
                                 "Maria", 30, 5500.75);
```

### Text Blocks (Java 15+)

```java
String json = """
    {
        "nome": "João Silva",
        "idade": 30,
        "email": "joao@email.com",
        "ativo": true
    }
    """;

String html = """
    <html>
        <body>
            <h1>Bem-vindo, %s!</h1>
            <p>Você tem %d mensagens.</p>
        </body>
    </html>
    """.formatted(nome, totalMensagens);
```

## 🔍 Expressões Regulares Básicas

### Padrões Comuns

```java
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexExemplos {
    
    // Validar CPF (formato: 000.000.000-00)
    public static boolean validarCPF(String cpf) {
        String padrao = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        return cpf.matches(padrao);
    }
    
    // Validar telefone (formato: (00) 00000-0000)
    public static boolean validarTelefone(String telefone) {
        String padrao = "\\(\\d{2}\\) \\d{5}-\\d{4}";
        return telefone.matches(padrao);
    }
    
    // Extrair números de um texto
    public static String[] extrairNumeros(String texto) {
        Pattern padrao = Pattern.compile("\\d+");
        Matcher matcher = padrao.matcher(texto);
        
        return matcher.results()
                     .map(result -> result.group())
                     .toArray(String[]::new);
    }
    
    // Substituir usando regex
    public static String mascaraEmail(String email) {
        return email.replaceAll("(\\w)[\\w.-]*@", "$1***@");
    }
}
```

## ⚠️ Armadilhas Comuns

### 1. Comparação com ==

```java
// ❌ PERIGOSO
String s1 = new String("teste");
String s2 = new String("teste");
if (s1 == s2) { // false - referências diferentes
    System.out.println("Iguais");
}

// ✅ CORRETO
if (s1.equals(s2)) { // true - conteúdo igual
    System.out.println("Iguais");
}
```

### 2. NullPointerException

```java
String texto = null;

// ❌ PERIGOSO
if (texto.equals("teste")) { ... } // NullPointerException

// ✅ SEGURO
if ("teste".equals(texto)) { ... } // Nunca dá NPE
// ou
if (texto != null && texto.equals("teste")) { ... }
```

### 3. Concatenação Ineficiente

```java
// ❌ INEFICIENTE para muitas concatenações
String resultado = "";
for (int i = 0; i < 1000; i++) {
    resultado += "item" + i + ", ";
}

// ✅ EFICIENTE
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append("item").append(i).append(", ");
}
String resultado = sb.toString();
```

## 🧪 Exercícios Práticos

### Nível Iniciante
1. **Contador de Caracteres**: Conte vogais, consoantes e espaços
2. **Validador de Senha**: Verificar comprimento, maiúsculas, números
3. **Formatador de Texto**: Capitalizar primeira letra de cada palavra

### Nível Intermediário
1. **Analisador de Log**: Extrair informações de linhas de log
2. **Gerador de Slug**: Converter título em URL amigável
3. **Calculadora de Expressões**: Parser simples para operações matemáticas

### Nível Avançado
1. **Sistema de Templates**: Substituir placeholders em templates
2. **Validador de Documentos**: CPF, CNPJ, cartão de crédito
3. **Analisador de Código**: Contador de linhas, comentários, métodos

## 🛠️ Boas Práticas

1. **Use StringBuilder**: Para múltiplas concatenações
2. **Valide entradas**: Sempre verifique null e vazio
3. **Use equals()**: Nunca == para comparar conteúdo
4. **Considere performance**: String operations podem ser custosas
5. **Normalize dados**: toLowerCase() antes de comparar
6. **Use constantes**: Para padrões regex reutilizáveis
7. **Documente regex**: Padrões complexos precisam comentários

## 📖 Próximos Passos

Após dominar manipulação de strings, você estará pronto para:
- [Programação Orientada a Objetos](../../03-POO/) - Classes e objetos
- [Collections Framework](../../03-conceitos-intermediarios/) - Listas, maps, sets
- [Entrada/Saída de Dados](../../arquivo-io/) - Leitura e escrita de arquivos

## 📚 Recursos Adicionais

- [Oracle String Tutorial](https://docs.oracle.com/javase/tutorial/java/data/strings.html)
- [Regular Expressions in Java](https://docs.oracle.com/javase/tutorial/essential/regex/)
- [String API Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html)

---

**💡 Dica**: Strings são uma das partes mais usadas de qualquer programa. Invista tempo dominando suas nuances e padrões comuns!