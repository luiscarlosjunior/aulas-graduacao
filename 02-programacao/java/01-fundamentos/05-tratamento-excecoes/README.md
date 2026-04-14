# Tratamento de Exceções em Java

Esta seção aborda como lidar com erros de forma profissional em Java — uma das competências que mais diferenciam desenvolvedores juniores de seniors.

## 🎯 Objetivos

- Entender a hierarquia de exceções do Java
- Usar `try-catch-finally` corretamente
- Criar exceções customizadas para o seu domínio
- Aplicar `try-with-resources` para gerenciar recursos
- Conhecer as melhores práticas da indústria

## 🏭 Por que isso Importa na Indústria?

| Situação | Sem try-catch | Com try-catch |
|----------|---------------|---------------|
| API retorna JSON malformado | Sistema quebra | Loga o erro, usa valor padrão, continua |
| Banco de dados fora do ar | Aplicação trava | Tenta novamente, notifica ops team |
| Arquivo não encontrado | NullPointerException | Mensagem amigável, fallback para defaults |
| Conversão de tipo inválida | NumberFormatException para usuário | Valida, retorna erro descritivo |

> 💡 **Fato real**: Segundo o relatório da Rollbar (2021), **NullPointerException** é o erro #1 mais comum em produção (Java), seguido de **ArrayIndexOutOfBoundsException**. Tratamento correto desses erros é essencial.

## 🔑 Hierarquia de Exceções Java

```
Throwable
├── Error (NÃO capture — erro grave do JVM)
│   ├── OutOfMemoryError       → sem memória disponível
│   ├── StackOverflowError     → recursão infinita
│   └── VirtualMachineError
│
└── Exception
    ├── RuntimeException (Unchecked — não obriga try-catch)
    │   ├── NullPointerException          → objeto null chamado
    │   ├── ArrayIndexOutOfBoundsException → índice inválido
    │   ├── ClassCastException            → cast impossível
    │   ├── NumberFormatException         → "abc" para int
    │   ├── IllegalArgumentException      → argumento inválido
    │   └── IllegalStateException         → estado inconsistente
    │
    └── Checked Exception (obriga try-catch ou throws)
        ├── IOException         → falha de I/O (arquivo, rede)
        ├── FileNotFoundException → arquivo não existe
        ├── SQLException        → erro no banco de dados
        └── ParseException      → falha ao parsear data/string
```

## 📋 Conteúdo

### 📄 [TratamentoExcecoes.java](TratamentoExcecoes.java)

Exemplos completos com cenários reais:

1. **try-catch básico** — processando dados de API externa com valores inválidos
2. **Múltiplas exceções** — sistema de pagamento com diferentes tipos de erro
3. **finally** — garantindo fechamento de recursos (arquivos, conexões)
4. **Exceções customizadas** — `EstoqueInsuficienteException`, `ProdutoBloqueadoException`
5. **Boas práticas** — o que fazer e o que evitar

## 🚀 Como Executar

```bash
# Navegar até o diretório
cd 05-tratamento-excecoes/

# Compilar
javac TratamentoExcecoes.java

# Executar
java TratamentoExcecoes
```

## 🔧 Sintaxe Fundamental

### try-catch básico

```java
try {
    // código que pode lançar exceção
    int resultado = Integer.parseInt("abc");  // lança NumberFormatException
    
} catch (NumberFormatException e) {
    // trata o erro específico
    System.out.println("Valor inválido: " + e.getMessage());
}
```

### Múltiplas exceções

```java
try {
    conectarBancoDeDados();
    executarQuery();
    
} catch (SQLException e) {
    // erro específico de banco de dados
    logger.error("Erro SQL: {}", e.getMessage());
    
} catch (ConnectionException e) {
    // erro de conexão
    alertarTimeOps("Banco indisponível!");
    
} catch (Exception e) {
    // SEMPRE por último: captura qualquer outra exceção
    logger.error("Erro inesperado", e);
}
```

### try-catch-finally

```java
Connection conexao = null;
try {
    conexao = abrirConexao();
    // ... operações ...
    
} catch (SQLException e) {
    logger.error("Erro na query", e);
    
} finally {
    // SEMPRE executa — mesmo se houver exceção ou return
    if (conexao != null) {
        conexao.close();  // garante que a conexão seja fechada
    }
}
```

### try-with-resources (Java 7+ — forma moderna)

```java
// O Java fecha os recursos AUTOMATICAMENTE (implementam AutoCloseable)
try (Connection conn = abrirConexao();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
    
    ResultSet rs = stmt.executeQuery();
    // processa resultados
    
} catch (SQLException e) {
    logger.error("Erro SQL", e);
}
// conn e stmt são fechados automaticamente — sem finally necessário!
```

### Criar e lançar exceção customizada

```java
// Definição da exceção de domínio
public class SaldoInsuficienteException extends RuntimeException {
    private final double saldoAtual;
    private final double valorSolicitado;
    
    public SaldoInsuficienteException(double saldoAtual, double valorSolicitado) {
        super(String.format(
            "Saldo insuficiente. Disponível: R$%.2f, Solicitado: R$%.2f",
            saldoAtual, valorSolicitado));
        this.saldoAtual = saldoAtual;
        this.valorSolicitado = valorSolicitado;
    }
    
    public double getSaldoAtual() { return saldoAtual; }
}

// Lançando no código
public void sacar(double valor) {
    if (valor > saldo) {
        throw new SaldoInsuficienteException(saldo, valor);
    }
    saldo -= valor;
}

// Capturando
try {
    conta.sacar(5000);
} catch (SaldoInsuficienteException e) {
    System.out.println(e.getMessage());
    System.out.println("Saldo atual: R$" + e.getSaldoAtual());
}
```

## 💡 Boas Práticas (do mercado)

### ✅ 1. Seja Específico nas Exceções
```java
// RUIM: captura tudo, perde contexto do erro
catch (Exception e) { }

// BOM: capture o tipo específico
catch (NullPointerException e) { }
catch (NumberFormatException e) { }
```

### ✅ 2. Nunca Ignore Exceções
```java
// GRAVÍSSIMO: bug silencioso!
catch (Exception e) {
    // vazio — o erro some sem deixar rastro
}

// MÍNIMO aceitável:
catch (Exception e) {
    logger.error("Erro ao processar pedido {}: {}", pedidoId, e.getMessage(), e);
}
```

### ✅ 3. Use Exceções Customizadas para Negócio
```java
// Genérico e pouco expressivo:
throw new RuntimeException("Erro 400");

// Específico e expressivo:
throw new LimiteCartaoExcedidoException(limiteCartao, valorCompra);
```

### ✅ 4. Prefira try-with-resources
```java
// Antiquado (pré-Java 7):
FileReader fr = null;
try {
    fr = new FileReader("arquivo.txt");
    // ...
} finally {
    if (fr != null) fr.close();
}

// Moderno (Java 7+):
try (FileReader fr = new FileReader("arquivo.txt")) {
    // fr é fechado automaticamente!
}
```

### ✅ 5. Checked vs Unchecked — Quando Usar Cada Um
```java
// Checked Exception (extends Exception):
// Use quando o CHAMADOR pode se recuperar do erro
// Exemplos: FileNotFoundException, SQLException
public void lerArquivo(String path) throws FileNotFoundException { }

// Unchecked Exception (extends RuntimeException):  
// Use para erros de programação ou regras de negócio
// O chamador não é obrigado a tratar — mas pode!
public void sacar(double valor) {
    if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
}
```

## ❗ Erros Comuns

### 1. Ordem errada nos catch
```java
// ERRADO: Exception é superclasse, captura tudo antes das específicas
catch (Exception e) { ... }
catch (NullPointerException e) { ... }  // nunca alcançado!

// CORRETO: mais específico primeiro
catch (NullPointerException e) { ... }
catch (Exception e) { ... }  // genérico sempre por último
```

### 2. Perder a exceção original
```java
// RUIM: perde a causa original (dificulta debugging)
catch (Exception e) {
    throw new RuntimeException("Erro no sistema");
}

// BOM: preserva a causa (chained exceptions)
catch (Exception e) {
    throw new RuntimeException("Erro ao processar pedido", e);  // ← 'e' como cause
}
```

### 3. Swallowing exceptions (engolir exceção)
```java
// PERIGOSO: o sistema parece OK mas há um bug escondido
try {
    salvarNoBanco(dados);
} catch (Exception e) {
    System.out.println("Ocorreu um erro.");  // ← sem rethrow, sem log adequado
}
// Desenvolvedor acha que funcionou — mas não salvou nada!
```

## 📝 Exercícios

1. **Calculadora Robusta**: Implemente divisão com tratamento de `ArithmeticException` (divisão por zero)
2. **Parser de Idades**: Receba um array de Strings com idades, converta para int ignorando inválidos
3. **Conta Bancária Segura**: Método `sacar()` com exceção `SaldoInsuficienteException` customizada
4. **Leitura de Arquivo**: Simule leitura de arquivo usando try-with-resources e trate FileNotFoundException

## 🔗 Navegação

[← 04 - Controle de Fluxo](../04-controle-fluxo/) | [→ POO](../../03-POO/)
