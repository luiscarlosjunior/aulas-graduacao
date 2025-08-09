# Tratamento de Exceções em Java

Esta seção apresenta o sistema de tratamento de exceções em Java, fundamental para criar aplicações robustas e confiáveis.

## 🎯 Objetivos

- Compreender o conceito de exceções em Java
- Aprender a usar blocos `try-catch-finally`
- Conhecer a diferença entre exceções checked e unchecked
- Saber quando usar `throws` e `throw`
- Criar exceções personalizadas
- Aplicar boas práticas no tratamento de exceções

## 📋 Conceitos Fundamentais

### O que são Exceções?

Exceções são eventos que interrompem o fluxo normal de execução do programa. Em Java, todas as exceções são objetos que herdam da classe `Throwable`.

### Hierarquia de Exceções

```
Throwable
├── Error (erros do sistema - não devem ser capturados)
└── Exception
    ├── RuntimeException (unchecked exceptions)
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   └── IllegalArgumentException
    └── Checked Exceptions
        ├── IOException
        ├── SQLException
        └── ClassNotFoundException
```

## 📄 Estrutura Try-Catch-Finally

### Sintaxe Básica

```java
try {
    // código que pode gerar exceção
} catch (TipoExcecao e) {
    // tratamento da exceção
} finally {
    // código que sempre executa (opcional)
}
```

## 📄 Análise dos Exemplos

### Diretório `01trycatch/`

#### [trycatch01.java](01trycatch/trycatch01.java)
Exemplo básico de captura de exceção:

```java
try {
    int a[] = new int[2];
    System.out.println("Access element three :" + a[3]);  // Erro!
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Lançando a exceção: " + e);
}
System.out.println("Fora do bloco");
```

**Conceitos demonstrados:**
- Bloco `try` contendo código que pode falhar
- Captura específica de `ArrayIndexOutOfBoundsException`
- Continuidade do programa após tratamento da exceção

#### [tryfinally.java](01trycatch/tryfinally.java)
Demonstra o uso do bloco `finally`:

```java
try {
    // código que pode gerar exceção
} finally {
    // este código SEMPRE executa
    // mesmo se houver exceção ou return
}
```

#### [throwexception.java](01trycatch/throwexception.java)
Como lançar exceções manualmente:

```java
if (condicaoInvalida) {
    throw new IllegalArgumentException("Argumento inválido");
}
```

### Diretório `02recursos/`

#### [exemplothrows.java](02recursos/exemplothrows.java)
Demonstra o uso de `throws` em assinaturas de métodos:

```java
public static void lerArquivo() throws IOException {
    // Método declara que pode lançar IOException
    throw new IOException("Arquivo não encontrado");
}

public static void main(String[] args) {
    try {
        lerArquivo();  // Deve ser capturada ou propagada
    } catch (IOException e) {
        System.out.println("Erro capturado: " + e.getMessage());
    }
}
```

#### [exemplothrow.java](02recursos/exemplothrow.java)
Lançamento manual de exceções com `throw`.

#### [exemplogetmessage.java](02recursos/exemplogetmessage.java)
Como obter informações das exceções:

```java
catch (Exception e) {
    System.out.println("Mensagem: " + e.getMessage());
    System.out.println("Tipo: " + e.getClass().getName());
    e.printStackTrace();  // Stack trace completo
}
```

#### [trycatchmessage.java](02recursos/trycatchmessage.java)
Diferentes formas de exibir informações de exceções.

## 🔧 Tipos de Exceções

### 1. Checked Exceptions
Devem ser tratadas ou declaradas com `throws`:

```java
// Deve ser tratada
try {
    FileReader file = new FileReader("arquivo.txt");
} catch (FileNotFoundException e) {
    // tratamento obrigatório
}

// Ou declarada
public void lerArquivo() throws FileNotFoundException {
    FileReader file = new FileReader("arquivo.txt");
}
```

### 2. Unchecked Exceptions (RuntimeException)
Não precisam ser tratadas obrigatoriamente:

```java
// Pode ser tratada (opcional)
String texto = null;
try {
    int length = texto.length();  // NullPointerException
} catch (NullPointerException e) {
    System.out.println("Texto é nulo!");
}
```

## 🔄 Múltiplas Exceções

### Vários blocos catch:

```java
try {
    // código perigoso
} catch (FileNotFoundException e) {
    // arquivo não encontrado
} catch (IOException e) {
    // outros problemas de I/O
} catch (Exception e) {
    // qualquer outra exceção
}
```

### Multi-catch (Java 7+):

```java
try {
    // código perigoso
} catch (IOException | SQLException e) {
    // trata ambas as exceções da mesma forma
    System.out.println("Erro: " + e.getMessage());
}
```

## 🎯 Try-with-resources

Para recursos que precisam ser fechados:

```java
try (FileReader file = new FileReader("arquivo.txt");
     BufferedReader buffer = new BufferedReader(file)) {
    
    return buffer.readLine();
    // Recursos são fechados automaticamente
}
```

## 🚀 Como Executar os Exemplos

```bash
# Navegar até o diretório de exceções
cd "04_excecoes"

# Executar exemplos básicos
cd 01trycatch
javac trycatch01.java
java trycatch01

# Executar exemplos avançados
cd ../02recursos
javac exemplothrows.java
java exemplothrows
```

## 💡 Criando Exceções Personalizadas

```java
// Checked exception personalizada
public class MinhaExcecao extends Exception {
    public MinhaExcecao(String mensagem) {
        super(mensagem);
    }
}

// Unchecked exception personalizada
public class MinhaRuntimeException extends RuntimeException {
    public MinhaRuntimeException(String mensagem) {
        super(mensagem);
    }
}

// Uso
public void validarIdade(int idade) throws MinhaExcecao {
    if (idade < 0) {
        throw new MinhaExcecao("Idade não pode ser negativa");
    }
}
```

## 💡 Boas Práticas

### 1. Seja específico nas exceções
```java
// Evite:
catch (Exception e) {
    // muito genérico
}

// Prefira:
catch (FileNotFoundException e) {
    // específico e claro
}
```

### 2. Não ignore exceções
```java
// NUNCA faça:
try {
    // código perigoso
} catch (Exception e) {
    // ignorar completamente - PÉSSIMA prática
}

// Faça pelo menos:
catch (Exception e) {
    e.printStackTrace();  // ou log adequado
}
```

### 3. Use finally para limpeza
```java
FileInputStream file = null;
try {
    file = new FileInputStream("arquivo.txt");
    // usar arquivo
} catch (IOException e) {
    // tratar erro
} finally {
    if (file != null) {
        try {
            file.close();
        } catch (IOException e) {
            // log do erro de fechamento
        }
    }
}
```

### 4. Documente exceções em métodos
```java
/**
 * Lê arquivo do sistema
 * @param nomeArquivo nome do arquivo a ser lido
 * @throws FileNotFoundException se arquivo não existir
 * @throws IOException se houver erro de leitura
 */
public String lerArquivo(String nomeArquivo) 
    throws FileNotFoundException, IOException {
    // implementação
}
```

## ⚠️ Erros Comuns

1. **Capturar Exception muito genérica**: Dificulta identificação de problemas
2. **Não documentar exceções**: Outros desenvolvedores não sabem o que esperar
3. **Usar exceções para controle de fluxo**: Exceções são para casos excepcionais
4. **Não fazer limpeza de recursos**: Vazamentos de memória e handles

## 📚 Informações da Exceção

### Métodos úteis da classe Exception:

```java
try {
    // código que falha
} catch (Exception e) {
    String mensagem = e.getMessage();           // Mensagem da exceção
    String classe = e.getClass().getName();     // Nome da classe da exceção
    StackTraceElement[] stack = e.getStackTrace(); // Stack trace
    
    e.printStackTrace();     // Imprime stack trace no console
    e.printStackTrace(stream); // Imprime em PrintStream específico
}
```

## 💡 Experimentos Sugeridos

1. **Validador de entrada**: Crie métodos que validam dados e lançam exceções específicas
2. **Calculadora robusta**: Trate divisão por zero e entradas inválidas
3. **Leitor de arquivo**: Implemente leitura de arquivo com tratamento completo
4. **Sistema de login**: Crie exceções para diferentes tipos de falha de autenticação

## 📚 Conceitos Relacionados

- **Logging**: Frameworks como Log4j para registrar exceções
- **Debugging**: Como usar exceções para encontrar bugs
- **Design by Contract**: Pré-condições, pós-condições e invariantes
- **Fail-fast**: Falhar rapidamente para detectar problemas cedo

---

**Próximo**: [Exercícios](../exercicios/) - Pratique os conceitos fundamentais com exercícios elaborados.