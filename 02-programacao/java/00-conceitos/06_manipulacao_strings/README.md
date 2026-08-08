# Manipulação de Strings em Java

Esta seção apresenta uma cobertura completa sobre **manipulação de strings** em Java, um dos tópicos mais importantes na programação. Strings estão presentes em praticamente todos os programas, desde entrada de dados até processamento de texto e geração de relatórios.

## 🎯 Objetivos de Aprendizado

### Ao completar esta seção, você será capaz de:
- ✅ Entender o conceito de **imutabilidade** das strings em Java
- ✅ Dominar os **métodos essenciais** da classe String
- ✅ **Comparar strings** corretamente (evitando armadilhas comuns)
- ✅ **Transformar e formatar** texto de acordo com necessidades específicas
- ✅ **Buscar e substituir** conteúdo em strings
- ✅ **Dividir e juntar** strings eficientemente
- ✅ Usar **StringBuilder** para construção eficiente de strings
- ✅ **Formatar strings** profissionalmente
- ✅ Aplicar manipulação de strings em **cenários reais**

## 📁 Arquivos da Seção

### [ManipulacaoStrings.java](ManipulacaoStrings.java)
Demonstração completa dos métodos fundamentais para trabalhar com strings.

**Conceitos demonstrados:**
- Métodos básicos (`length()`, `charAt()`, `indexOf()`, `substring()`)
- Comparação de strings (`equals()`, `equalsIgnoreCase()`, `compareTo()`)
- Transformações (`toUpperCase()`, `toLowerCase()`, `trim()`)
- Busca e substituição (`contains()`, `startsWith()`, `replace()`, `replaceAll()`)
- Split e join (`split()`, `String.join()`)
- StringBuilder para construção eficiente
- Formatação de strings (`String.format()`, `printf()`)

### [ExemplosPraticosStrings.java](ExemplosPraticosStrings.java)
Aplicações práticas e reais dos conceitos de manipulação de strings.

**Casos de uso reais:**
- 📧 Validação e formatação de emails
- 👤 Processamento e formatação de nomes
- 🧹 Limpeza e normalização de texto
- 🔒 Validação de senhas com critérios de segurança
- 📊 Parser simples de dados CSV
- 🌐 Manipulação de URLs e caminhos
- 📄 Geração de relatórios formatados

### [ExerciciosStrings.java](ExerciciosStrings.java)
Exercícios práticos organizados por níveis de dificuldade para consolidar o aprendizado.

**Exercícios incluídos:**
- 🟢 **Nível Básico**: Contador de caracteres, inversor de texto, gerador de iniciais, verificação de palíndromo, capitalização
- 🟡 **Nível Intermediário**: Validação de CPF, formatação de telefone, geração de slug, contador de palavras, validação de senha forte
- 🔴 **Nível Avançado**: Calculadora de expressões, highlight de sintaxe, gerador de senhas, compressão de texto, análise completa de texto

### [ExerciciosSolucoes.java](ExerciciosSolucoes.java)
Soluções completas e comentadas para todos os exercícios propostos.

**Características das soluções:**
- ✅ Implementações otimizadas e eficientes
- 📝 Comentários explicativos detalhados
- 🔄 Diferentes abordagens quando aplicável
- ⚡ Boas práticas de programação
- 🧪 Tratamento de casos especiais e validações

## 🚀 Como Executar os Exemplos

### 1. Compilação
```bash
javac ManipulacaoStrings.java
javac ExemplosPraticosStrings.java
javac ExerciciosStrings.java
javac ExerciciosSolucoes.java
```

### 2. Execução
```bash
# Conceitos fundamentais
java ManipulacaoStrings

# Exemplos práticos
java ExemplosPraticosStrings

# Exercícios para praticar
java ExerciciosStrings

# Soluções dos exercícios
java ExerciciosSolucoes
```

### 3. Resultado Esperado
- **ManipulacaoStrings**: Demonstra todos os métodos com exemplos didáticos
- **ExemplosPraticosStrings**: Mostra aplicações reais em cenários do dia a dia
- **ExerciciosStrings**: Apresenta exercícios com resultados vazios para implementação
- **ExerciciosSolucoes**: Exibe as soluções funcionais de todos os exercícios

## 📚 Conceitos Fundamentais

### 🔑 **Conceito Chave: Imutabilidade**

**IMPORTANTE:** Strings em Java são **imutáveis**. Isso significa que:

```java
String texto = "Java";
texto = texto + " é fantástico!";  // Cria uma NOVA string, não modifica a original
```

**Implicações:**
- ✅ **Segurança**: Strings não podem ser alteradas acidentalmente
- ✅ **Thread-safe**: Podem ser compartilhadas entre threads
- ⚠️ **Performance**: Concatenações excessivas podem ser custosas
- 💡 **Solução**: Use StringBuilder para muitas concatenações

### 📏 **Métodos Básicos**

#### Informações sobre a String
```java
String texto = "Programação Java";

texto.length()           // 16 - comprimento
texto.isEmpty()          // false - está vazia?
texto.isBlank()          // false - está em branco? (Java 11+)
texto.charAt(0)          // 'P' - caractere na posição
texto.indexOf('a')       // 4 - primeira posição do caractere
texto.lastIndexOf('a')   // 15 - última posição do caractere
texto.substring(0, 11)   // "Programação" - substring
```

#### 🔍 **Comparação de Strings**

**❌ NUNCA FAÇA ISSO:**
```java
String a = "Java";
String b = "Java";
if (a == b) { /* ERRADO! Compara referências, não conteúdo */ }
```

**✅ SEMPRE FAÇA ISSO:**
```java
String a = "Java";
String b = "Java";

// Comparação de igualdade
if (a.equals(b)) { /* CORRETO! Compara conteúdo */ }

// Comparação ignorando maiúsculas/minúsculas
if (a.equalsIgnoreCase("java")) { /* true */ }

// Comparação lexicográfica (ordem alfabética)
int resultado = a.compareTo("Python");  // Negativo: Java vem antes de Python
```

### 🔄 **Transformações**

```java
String texto = "  Aprendendo JAVA é Divertido!  ";

texto.toUpperCase()      // "  APRENDENDO JAVA É DIVERTIDO!  "
texto.toLowerCase()      // "  aprendendo java é divertido!  "
texto.trim()            // "Aprendendo JAVA é Divertido!"
texto.strip()           // "Aprendendo JAVA é Divertido!" (Java 11+)

// Repetição (Java 11+)
"-".repeat(20)          // "--------------------"
```

### 🔎 **Busca e Verificação**

```java
String frase = "Java é uma linguagem de programação";

frase.contains("Java")           // true
frase.startsWith("Java")         // true
frase.endsWith("programação")    // true
frase.indexOf("linguagem")       // 12
```

### 🔄 **Substituição**

```java
String texto = "Java é incrível! Java é poderoso!";

texto.replace("Java", "Python")     // Substitui todas as ocorrências
texto.replaceFirst("Java", "Python") // Substitui apenas a primeira
texto.replaceAll("\\d", "X")        // Substitui usando regex (números por X)
```

### ✂️ **Split e Join**

```java
// Dividindo strings
String csv = "maçã,banana,laranja";
String[] frutas = csv.split(",");   // ["maçã", "banana", "laranja"]

// Juntando strings
String resultado = String.join(" | ", frutas);  // "maçã | banana | laranja"
```

### 🏗️ **StringBuilder - Construção Eficiente**

**Quando usar StringBuilder:**
- Muitas concatenações em loop
- Construção dinâmica de strings
- Performance é crítica

```java
// ❌ Ineficiente (cria muitas strings temporárias)
String resultado = "";
for (int i = 0; i < 1000; i++) {
    resultado += "Item " + i + ", ";
}

// ✅ Eficiente (reutiliza buffer interno)
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append("Item ").append(i).append(", ");
}
String resultado = sb.toString();
```

**Métodos úteis do StringBuilder:**
```java
StringBuilder sb = new StringBuilder("Java");
sb.append(" é fantástico")     // Adiciona no final
  .insert(4, " 17")            // Insere na posição
  .replace(0, 4, "Python")     // Substitui intervalo
  .reverse()                   // Inverte
  .delete(0, 6);               // Remove intervalo
```

### 🎨 **Formatação de Strings**

#### String.format()
```java
String nome = "Ana";
int idade = 25;
double salario = 5500.75;

String info = String.format("Nome: %s, Idade: %d, Salário: R$ %.2f", 
                            nome, idade, salario);
// "Nome: Ana, Idade: 25, Salário: R$ 5500,75"
```

#### System.out.printf()
```java
System.out.printf("%-10s %5d %8.2f%n", "João", 30, 1500.50);
//                ^^^      ^^^  ^^^
//              esquerda  direita decimal
```

**Especificadores de formato:**
- `%s` - String
- `%d` - Inteiro decimal
- `%f` - Ponto flutuante
- `%x` - Hexadecimal
- `%o` - Octal
- `%e` - Notação científica
- `%n` - Nova linha (independente do sistema)

## 💡 Casos de Uso Práticos

### 1. **Validação de Email**
```java
public static boolean validarEmail(String email) {
    return email != null && 
           email.contains("@") && 
           !email.startsWith("@") && 
           !email.endsWith("@") &&
           email.indexOf("@") == email.lastIndexOf("@");
}
```

### 2. **Formatação de Nome**
```java
public static String formatarNome(String nome) {
    String[] palavras = nome.trim().toLowerCase().split("\\s+");
    StringBuilder nomeFormatado = new StringBuilder();
    
    for (String palavra : palavras) {
        if (!palavra.isEmpty()) {
            nomeFormatado.append(Character.toUpperCase(palavra.charAt(0)))
                        .append(palavra.substring(1))
                        .append(" ");
        }
    }
    
    return nomeFormatado.toString().trim();
}
```

### 3. **Limpeza de Texto**
```java
public static String limparTexto(String texto) {
    return texto.trim()                    // Remove espaços das bordas
               .replaceAll("\\s+", " ")   // Normaliza espaços múltiplos
               .replaceAll("[^\\w\\s]", ""); // Remove caracteres especiais
}
```

### 4. **Parser CSV Simples**
```java
public static void processarCSV(String csvData) {
    String[] linhas = csvData.split("\n");
    String[] cabecalho = linhas[0].split(",");
    
    for (int i = 1; i < linhas.length; i++) {
        String[] valores = linhas[i].split(",");
        // Processar cada linha...
    }
}
```

## ⚠️ Armadilhas Comuns e Como Evitar

### 1. **Comparação com `==`**
```java
// ❌ ERRADO
String a = new String("Java");
String b = new String("Java");
if (a == b) { /* false - compara referências! */ }

// ✅ CORRETO
if (a.equals(b)) { /* true - compara conteúdo */ }
```

### 2. **NullPointerException**
```java
// ❌ PERIGOSO
String texto = null;
if (texto.equals("Java")) { /* NullPointerException! */ }

// ✅ SEGURO
if ("Java".equals(texto)) { /* false - sem exceção */ }
// ou
if (texto != null && texto.equals("Java")) { /* seguro */ }
```

### 3. **Concatenação Excessiva**
```java
// ❌ INEFICIENTE
String resultado = "";
for (int i = 0; i < 10000; i++) {
    resultado += "item" + i;  // Cria nova string a cada iteração
}

// ✅ EFICIENTE
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 10000; i++) {
    sb.append("item").append(i);
}
String resultado = sb.toString();
```

### 4. **Regex Desnecessário**
```java
// ❌ DESNECESSÁRIO (para substituição simples)
texto.replaceAll("Java", "Python");

// ✅ MAIS EFICIENTE
texto.replace("Java", "Python");
```

## 🏆 Boas Práticas

### 1. **Use equals() para Comparação**
```java
// Sempre use equals() ou equalsIgnoreCase()
if (texto.equals("esperado")) { }
if (texto.equalsIgnoreCase("ESPERADO")) { }
```

### 2. **Valide Entrada**
```java
public static String processarTexto(String entrada) {
    if (entrada == null || entrada.trim().isEmpty()) {
        return "";  // ou lance uma exceção
    }
    // Processar...
}
```

### 3. **Use StringBuilder para Construções Complexas**
```java
// Para múltiplas concatenações ou loops
StringBuilder html = new StringBuilder()
    .append("<html>")
    .append("<body>")
    .append("<h1>Título</h1>")
    .append("</body>")
    .append("</html>");
```

### 4. **Normalize Entrada do Usuário**
```java
String entrada = input.trim().toLowerCase();
```

### 5. **Use Constantes para Strings Especiais**
```java
private static final String SEPARADOR = ",";
private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
```

## 📊 Comparação: Performance dos Métodos

| Operação | Método Eficiente | Método Ineficiente | Diferença |
|----------|------------------|-------------------|-----------|
| Concatenação múltipla | StringBuilder | String += | ~100x mais lento |
| Busca simples | contains() | replaceAll() + regex | ~10x mais rápido |
| Substituição literal | replace() | replaceAll() | ~5x mais rápido |
| Comparação | equals() | compareTo() == 0 | Mais legível |

## 💪 Exercícios Práticos

Esta seção inclui uma coleção abrangente de exercícios para consolidar seu aprendizado sobre manipulação de strings. Os exercícios estão organizados em três níveis de dificuldade:

### 🟢 **Nível Básico** (Exercícios 1-5)
Exercícios fundamentais para iniciantes:
- **Contador de Caracteres**: Conte vogais, consoantes e espaços
- **Inversor de Texto**: Inverta strings usando diferentes técnicas
- **Gerador de Iniciais**: Extraia iniciais de nomes completos
- **Verificador de Palíndromo**: Detecte palavras que se leem igual de trás para frente
- **Capitalizador**: Capitalize a primeira letra de cada palavra

### 🟡 **Nível Intermediário** (Exercícios 6-10)
Exercícios práticos para desenvolvedores com conhecimento básico:
- **Validador de CPF**: Implementação completa com algoritmo de validação
- **Formatador de Telefone**: Formate números no padrão brasileiro
- **Gerador de Slug**: Converta títulos em URLs amigáveis
- **Contador de Palavras Únicas**: Análise estatística de texto
- **Validador de Senha Forte**: Verificação de critérios de segurança

### 🔴 **Nível Avançado** (Exercícios 11-15)
Desafios complexos para programadores experientes:
- **Calculadora de Expressões**: Parser e avaliador de expressões matemáticas
- **Highlight de Sintaxe**: Destaque de palavras-chave em código Java
- **Gerador de Senhas Seguras**: Criação de senhas com critérios customizáveis
- **Compressor de Texto**: Implementação de run-length encoding
- **Analisador de Texto**: Análise estatística completa de documentos

### 📚 **Como Usar os Exercícios**

1. **Execute os exercícios**: `java ExerciciosStrings`
   - Veja os problemas propostos
   - Observe que as implementações estão vazias (retornam valores padrão)

2. **Implemente suas soluções**: Edite `ExerciciosStrings.java`
   - Complete os métodos com suas implementações
   - Teste executando o programa novamente

3. **Compare com as soluções**: `java ExerciciosSolucoes`
   - Veja implementações completas e otimizadas
   - Aprenda diferentes técnicas e abordagens
   - Entenda as boas práticas aplicadas

4. **Experimente variações**:
   - Modifique os dados de teste
   - Adicione novos casos especiais
   - Implemente soluções alternativas

## 🧪 Experimentos Sugeridos

### Para Iniciantes:
1. **Contador de caracteres**: Conte vogais, consoantes e espaços em um texto ✅ [Exercício 1]
2. **Inversor de texto**: Inverta palavras ou frases ✅ [Exercício 2]
3. **Gerador de iniciais**: Extraia iniciais de nomes completos ✅ [Exercício 3]

### Para Intermediários:
1. **Validador de CPF**: Implemente validação com formatação ✅ [Exercício 6]
2. **Formatador de telefone**: (11) 99999-9999 ✅ [Exercício 7]
3. **Gerador de slug**: Converta títulos em URLs amigáveis ✅ [Exercício 8]

### Para Avançados:
1. **Parser de expressões**: Avalie expressões matemáticas simples ✅ [Exercício 11]
2. **Highlight de sintaxe**: Destaque palavras-chave em código ✅ [Exercício 12]
3. **Gerador de senhas**: Com critérios personalizáveis ✅ [Exercício 13]

### 💡 Como Praticar:
1. **Comece com os exercícios**: Execute `java ExerciciosStrings` para ver os problemas
2. **Implemente as soluções**: Edite os métodos em `ExerciciosStrings.java`
3. **Compare com as respostas**: Execute `java ExerciciosSolucoes` para ver as implementações
4. **Experimente variações**: Modifique os exercícios para criar novos desafios

## 📈 Próximos Passos

Após dominar manipulação de strings, você estará pronto para:

1. **[Arrays e Métodos](../05_arrays_e_metodos/)** - Estruturas de dados mais complexas
2. **[Programação Orientada a Objetos](../../03-POO/)** - Conceitos avançados
3. **Expressões Regulares** - Pattern matching avançado
4. **Internacionalização** - Suporte a múltiplos idiomas
5. **Processamento de arquivos** - Leitura e escrita de texto

## 🔗 Recursos Adicionais

### Documentação Oficial:
- [String (Java SE)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html)
- [StringBuilder (Java SE)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StringBuilder.html)
- [Pattern (Regex)](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html)

### Ferramentas Online:
- [Regex101](https://regex101.com/) - Testador de expressões regulares
- [String Escape Utils](https://www.freeformatter.com/java-dotnet-escape.html) - Escape de caracteres

### Tópicos Relacionados:
- Expressões Regulares (Regex)
- Locale e Internacionalização
- Character encoding (UTF-8, ASCII)
- Text Processing Libraries

---

## 💭 Reflexão Final

Manipulação de strings é uma habilidade fundamental que você usará em praticamente todos os programas Java. Desde validação de entrada até geração de relatórios, as técnicas apresentadas aqui são a base para resolver problemas reais.

**Lembre-se:**
- 🔑 **Imutabilidade** é o conceito chave
- ⚖️ **equals()** sempre para comparação
- 🏗️ **StringBuilder** para performance
- 🛡️ **Validação** evita problemas
- 🎯 **Prática** leva à perfeição

**Próximo passo:** Execute os exemplos, experimente variações e aplique esses conceitos em seus próprios projetos!

---

**Anterior**: [Exceções](../04_excecoes/) | **Próximo**: [Arrays e Métodos](../05_arrays_e_metodos/)