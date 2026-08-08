# Tratamento de Exceções em Java

## 📋 Visão Geral

O tratamento de exceções é um mecanismo fundamental em Java que permite lidar com erros e situações inesperadas durante a execução de um programa. Em vez de deixar o programa "quebrar", podemos capturar esses erros e tomar ações apropriadas para manter a aplicação funcionando de forma robusta.

## 🎯 Objetivos de Aprendizado

Ao completar este tópico, você será capaz de:

- ✅ Compreender o que são exceções e por que são importantes
- ✅ Usar blocos `try-catch-finally` efetivamente
- ✅ Distinguir entre exceções verificadas e não verificadas
- ✅ Criar e lançar suas próprias exceções
- ✅ Aplicar boas práticas no tratamento de erros
- ✅ Debuggar problemas usando informações de exceções

## 🚨 O que são Exceções?

Exceções são eventos que ocorrem durante a execução de um programa e interrompem o fluxo normal das instruções. Elas representam condições excepcionais que o programa pode encontrar.

### Exemplos Comuns de Exceções
```java
// 1. ArrayIndexOutOfBoundsException
int[] numeros = {1, 2, 3};
System.out.println(numeros[5]); // Índice não existe!

// 2. NullPointerException
String texto = null;
System.out.println(texto.length()); // Tentando usar null!

// 3. ArithmeticException
int resultado = 10 / 0; // Divisão por zero!

// 4. NumberFormatException
int numero = Integer.parseInt("abc"); // "abc" não é número!
```

## 🛡️ Estrutura try-catch-finally

### Sintaxe Básica
```java
try {
    // Código que pode gerar exceção
} catch (TipoExceção e) {
    // Código para lidar com a exceção
} finally {
    // Código que sempre executa (opcional)
}
```

### Exemplo Prático: Divisão Segura
```java
public class ExemploTryCatch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Digite o primeiro número: ");
            int num1 = sc.nextInt();
            
            System.out.print("Digite o segundo número: ");
            int num2 = sc.nextInt();
            
            int resultado = num1 / num2;
            System.out.println("Resultado: " + resultado);
            
        } catch (ArithmeticException e) {
            System.out.println("Erro: Não é possível dividir por zero!");
            System.out.println("Mensagem técnica: " + e.getMessage());
            
        } catch (InputMismatchException e) {
            System.out.println("Erro: Digite apenas números inteiros!");
            
        } finally {
            System.out.println("Operação finalizada.");
            sc.close();
        }
    }
}
```

## 🔍 Tipos de Exceções

### 1. Exceções Verificadas (Checked Exceptions)
Devem ser tratadas ou declaradas. O compilador força você a lidar com elas.

```java
// Exemplo: leitura de arquivo
import java.io.*;

public void lerArquivo() {
    try {
        FileReader arquivo = new FileReader("dados.txt");
        BufferedReader br = new BufferedReader(arquivo);
        String linha = br.readLine();
        System.out.println(linha);
        br.close();
        
    } catch (FileNotFoundException e) {
        System.out.println("Arquivo não encontrado!");
        
    } catch (IOException e) {
        System.out.println("Erro ao ler o arquivo!");
    }
}
```

### 2. Exceções Não Verificadas (Unchecked Exceptions)
Não precisam ser declaradas, mas devem ser tratadas para robustez.

```java
// RuntimeException e suas subclasses
try {
    String[] nomes = {"Ana", "João"};
    System.out.println(nomes[10]); // ArrayIndexOutOfBoundsException
    
    String texto = null;
    texto.toLowerCase(); // NullPointerException
    
    int numero = Integer.parseInt("xyz"); // NumberFormatException
    
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Índice inválido no array!");
    
} catch (NullPointerException e) {
    System.out.println("Tentativa de usar referência nula!");
    
} catch (NumberFormatException e) {
    System.out.println("Formato de número inválido!");
}
```

### 3. Errors
Problemas graves que normalmente não devem ser tratados pelo código.

```java
// Exemplos de Errors (não trate estes!):
// OutOfMemoryError - Falta de memória
// StackOverflowError - Estouro de pilha
// VirtualMachineError - Problemas na JVM
```

## 🎯 Múltiplos Catches

### Catches Específicos
```java
try {
    // Código que pode gerar diferentes exceções
    String texto = sc.nextLine();
    int numero = Integer.parseInt(texto);
    int resultado = 100 / numero;
    
} catch (NumberFormatException e) {
    System.out.println("Formato de número inválido!");
    
} catch (ArithmeticException e) {
    System.out.println("Erro matemático: " + e.getMessage());
    
} catch (Exception e) {
    // Catch genérico - deve ser o último
    System.out.println("Erro inesperado: " + e.getMessage());
}
```

### Multi-catch (Java 7+)
```java
try {
    // Código que pode gerar exceções
    
} catch (NumberFormatException | ArithmeticException e) {
    System.out.println("Erro numérico: " + e.getMessage());
    
} catch (Exception e) {
    System.out.println("Outro erro: " + e.getMessage());
}
```

## 🔧 Bloco finally

O bloco `finally` sempre executa, independente de exceções.

```java
FileReader arquivo = null;
try {
    arquivo = new FileReader("dados.txt");
    // Processamento do arquivo
    
} catch (FileNotFoundException e) {
    System.out.println("Arquivo não encontrado!");
    
} finally {
    // Cleanup sempre executa
    if (arquivo != null) {
        try {
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Erro ao fechar arquivo!");
        }
    }
    System.out.println("Recursos liberados.");
}
```

### Try-with-resources (Java 7+)
Melhor forma de gerenciar recursos:

```java
// Fecha automaticamente os recursos
try (FileReader arquivo = new FileReader("dados.txt");
     BufferedReader br = new BufferedReader(arquivo)) {
     
    String linha = br.readLine();
    System.out.println(linha);
    
} catch (IOException e) {
    System.out.println("Erro ao processar arquivo: " + e.getMessage());
}
// arquivo e br são fechados automaticamente
```

## 🚀 Lançando Exceções

### throw - Lançar Exceção Manualmente
```java
public void verificarIdade(int idade) {
    if (idade < 0) {
        throw new IllegalArgumentException("Idade não pode ser negativa!");
    }
    if (idade > 150) {
        throw new IllegalArgumentException("Idade não pode ser maior que 150!");
    }
    System.out.println("Idade válida: " + idade);
}

// Uso:
try {
    verificarIdade(-5);
} catch (IllegalArgumentException e) {
    System.out.println("Erro: " + e.getMessage());
}
```

### throws - Declarar Exceções
```java
public void lerArquivoComThrows() throws IOException {
    FileReader arquivo = new FileReader("dados.txt");
    // Se IOException ocorrer, será propagada para quem chama
}

public void metodoQueChamaa() {
    try {
        lerArquivoComThrows();
    } catch (IOException e) {
        System.out.println("Erro ao ler arquivo: " + e.getMessage());
    }
}
```

## 🎨 Criando Exceções Personalizadas

### Exceção Personalizada Simples
```java
public class IdadeInvalidaException extends Exception {
    public IdadeInvalidaException(String mensagem) {
        super(mensagem);
    }
    
    public IdadeInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

// Uso da exceção personalizada
public class ValidadorIdade {
    public void validarIdade(int idade) throws IdadeInvalidaException {
        if (idade < 0) {
            throw new IdadeInvalidaException("Idade não pode ser negativa: " + idade);
        }
        if (idade > 150) {
            throw new IdadeInvalidaException("Idade muito alta: " + idade);
        }
    }
}

// Usando a exceção
public static void main(String[] args) {
    ValidadorIdade validador = new ValidadorIdade();
    
    try {
        validador.validarIdade(-10);
    } catch (IdadeInvalidaException e) {
        System.out.println("Erro de validação: " + e.getMessage());
        e.printStackTrace(); // Mostra o stack trace
    }
}
```

### Exceção com Informações Extras
```java
public class ContaSaldoInsuficienteException extends Exception {
    private double saldoAtual;
    private double valorSaque;
    
    public ContaSaldoInsuficienteException(String mensagem, 
                                          double saldoAtual, 
                                          double valorSaque) {
        super(mensagem);
        this.saldoAtual = saldoAtual;
        this.valorSaque = valorSaque;
    }
    
    public double getSaldoAtual() { return saldoAtual; }
    public double getValorSaque() { return valorSaque; }
    public double getDiferenca() { return valorSaque - saldoAtual; }
}

// Uso:
try {
    conta.sacar(1000.0);
} catch (ContaSaldoInsuficienteException e) {
    System.out.printf("Saldo insuficiente! Saldo: %.2f, Saque: %.2f, Falta: %.2f%n",
                      e.getSaldoAtual(), e.getValorSaque(), e.getDiferenca());
}
```

## 🔍 Informações Úteis das Exceções

### Métodos Importantes
```java
try {
    // código que pode gerar exceção
    
} catch (Exception e) {
    // Mensagem da exceção
    System.out.println("Mensagem: " + e.getMessage());
    
    // Tipo da exceção
    System.out.println("Tipo: " + e.getClass().getSimpleName());
    
    // Stack trace completo
    e.printStackTrace();
    
    // Causa raiz (se houver)
    if (e.getCause() != null) {
        System.out.println("Causa: " + e.getCause().getMessage());
    }
    
    // Stack trace como string
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    String stackTrace = sw.toString();
}
```

## 💡 Padrões e Boas Práticas

### 1. Validação com Exceções
```java
public class Usuario {
    private String email;
    private int idade;
    
    public void setEmail(String email) throws IllegalArgumentException {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email deve conter @");
        }
        this.email = email;
    }
    
    public void setIdade(int idade) throws IllegalArgumentException {
        if (idade < 0 || idade > 150) {
            throw new IllegalArgumentException("Idade deve estar entre 0 e 150");
        }
        this.idade = idade;
    }
}
```

### 2. Método de Retry
```java
public boolean executarComRetry(int maxTentativas) {
    for (int tentativa = 1; tentativa <= maxTentativas; tentativa++) {
        try {
            // Operação que pode falhar
            operacaoQuePodefAlhar();
            return true; // Sucesso
            
        } catch (Exception e) {
            System.out.printf("Tentativa %d falhou: %s%n", tentativa, e.getMessage());
            
            if (tentativa == maxTentativas) {
                System.out.println("Todas as tentativas falharam!");
                return false;
            }
            
            // Aguarda antes da próxima tentativa
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
    }
    return false;
}
```

### 3. Wrapper para Operações Perigosas
```java
public class OperacaoSegura {
    public static <T> Optional<T> executarSeguro(Supplier<T> operacao) {
        try {
            return Optional.of(operacao.get());
        } catch (Exception e) {
            System.out.println("Operação falhou: " + e.getMessage());
            return Optional.empty();
        }
    }
}

// Uso:
Optional<Integer> resultado = OperacaoSegura.executarSeguro(() -> {
    return Integer.parseInt("123");
});

if (resultado.isPresent()) {
    System.out.println("Resultado: " + resultado.get());
} else {
    System.out.println("Operação falhou");
}
```

## ⚠️ Armadilhas Comuns

### 1. Engolir Exceções (Swallowing)
```java
// ❌ MUITO RUIM - "engole" a exceção
try {
    operacaoPerigosa();
} catch (Exception e) {
    // Não faz nada - muito perigoso!
}

// ✅ MELHOR - pelo menos registra
try {
    operacaoPerigosa();
} catch (Exception e) {
    System.err.println("Erro ignorado: " + e.getMessage());
    // Ou use um logger real
}
```

### 2. Catch Muito Genérico
```java
// ❌ Catch muito genérico no início
try {
    // código
} catch (Exception e) {  // Captura TUDO
    // ...
} catch (IOException e) {  // Nunca será executado!
    // ...
}

// ✅ Do mais específico para o mais genérico
try {
    // código
} catch (FileNotFoundException e) {
    // ...
} catch (IOException e) {
    // ...
} catch (Exception e) {
    // ...
}
```

### 3. Recursos Não Liberados
```java
// ❌ Perigoso - arquivo pode não ser fechado
FileReader arquivo = null;
try {
    arquivo = new FileReader("dados.txt");
    // processamento
} catch (IOException e) {
    System.out.println("Erro: " + e.getMessage());
    return; // Sai sem fechar o arquivo!
}
arquivo.close(); // Pode não executar

// ✅ Sempre use try-with-resources
try (FileReader arquivo = new FileReader("dados.txt")) {
    // processamento
} catch (IOException e) {
    System.out.println("Erro: " + e.getMessage());
}
// arquivo fechado automaticamente
```

## 🧪 Exercícios Práticos

### Nível Iniciante
1. **Calculadora Robusta**: Crie uma calculadora que trata divisão por zero e entrada inválida
2. **Validador de Email**: Implemente validação com exceções personalizadas
3. **Leitor de Números**: Peça números ao usuário e trate entradas inválidas

### Nível Intermediário
1. **Sistema de Login**: Implemente com tentativas limitadas e bloqueio
2. **Processador de Arquivo**: Leia arquivo com tratamento completo de erros
3. **Conversor de Dados**: Converta entre formatos com validação robusta

### Nível Avançado
1. **Sistema de Retry Inteligente**: Implemente retry com backoff exponencial
2. **Cadeia de Responsabilidade**: Multiple handlers para diferentes tipos de erro
3. **Log de Auditoria**: Sistema que registra todas as exceções com contexto

## 🛠️ Boas Práticas Resumidas

1. **Seja específico**: Capture exceções específicas, não apenas `Exception`
2. **Falhe rápido**: Valide parâmetros no início dos métodos
3. **Forneça contexto**: Mensagens de erro claras e informativas
4. **Libere recursos**: Use try-with-resources sempre que possível
5. **Não ignore exceções**: Sempre faça algo com a exceção capturada
6. **Log adequadamente**: Use sistemas de logging em vez de `System.out`
7. **Documente exceções**: Use Javadoc para documentar exceções lançadas

## 📖 Próximos Passos

Após dominar tratamento de exceções, você estará pronto para:
- [Arrays e Métodos](../arrays_e_metodos/) - Organização de código e dados
- [Manipulação de Strings](../manipulacao_strings/) - Processamento de texto
- [Programação Orientada a Objetos](../../03-POO/) - Conceitos avançados

## 📚 Recursos Adicionais

- [Oracle Exception Handling](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [Effective Java - Chapter 10: Exceptions](https://www.oreilly.com/library/view/effective-java/9780134686097/)
- [Clean Code: Error Handling](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

---

**💡 Dica**: Exceções bem tratadas são a diferença entre um programa amador e um profissional. Invista tempo aprendendo a lidar com erros corretamente!