# Program to Interfaces, Not Implementations
## Programe para Interfaces, Não para Implementações

## 📖 Visão Geral

**Program to Interfaces, Not Implementations** (Programe para Interfaces, Não para Implementações) é um princípio fundamental de design orientado a objetos que estabelece que código deve depender de abstrações (interfaces ou classes abstratas) ao invés de implementações concretas. Este princípio promove flexibilidade, desacoplamento e manutenibilidade.

## 🎯 Definição

> "Program to an interface, not an implementation."
>
> "Programe para uma interface, não para uma implementação."
>
> -- Gang of Four, Design Patterns (1994)

**O princípio estabelece:**
- **Variáveis**, **parâmetros** e **retornos** devem ser de tipos **abstratos** (interfaces/classes abstratas)
- Não exponha detalhes de implementação
- Código cliente não deve conhecer classes concretas

## 📚 Origem e História

### Gang of Four (1994)

O princípio foi formulado pelo **Gang of Four** em seu livro **"Design Patterns: Elements of Reusable Object-Oriented Software"** (1994), onde é apresentado como um dos princípios fundamentais de design OO.

### Relação com Outros Princípios

Este princípio é praticamente sinônimo do **Dependency Inversion Principle (DIP)** de Robert C. Martin. Ambos enfatizam dependência de abstrações ao invés de concreções.

### Evolução

- **1990s**: Formulação e disseminação via Design Patterns
- **2000s**: Popularização através de frameworks (Spring DI, etc.)
- **2010s**: Amplamente adotado como prática padrão

## 🔍 O Que Significa "Programar para Interface"?

### Não Significa...
❌ Criar interface para toda classe
❌ Nunca usar classes concretas
❌ Overhead desnecessário

### Significa...
✅ Usar tipos abstratos onde variação é esperada
✅ Depender de contratos, não implementações
✅ Desacoplar código de detalhes específicos

## 🎯 Por Que Este Princípio é Importante?

### 1. **Facilita Substituição de Implementações**
Trocar implementação sem modificar código cliente.

```java
// Cliente usa interface
List<String> lista = new ArrayList<>();
// Fácil trocar para
List<String> lista = new LinkedList<>();
```

### 2. **Reduz Acoplamento**
Baixo acoplamento entre módulos - cliente não conhece detalhes.

### 3. **Melhora Testabilidade**
Fácil criar mocks/stubs para testes.

```java
// Interface permite mock fácil
EmailSender sender = mock(EmailSender.class);
```

### 4. **Promove Flexibilidade**
Sistema pode evoluir sem quebrar código existente.

### 5. **Suporta Design Patterns**
Muitos patterns dependem deste princípio (Strategy, Factory, etc.).

## ❌ Violação: Programando para Implementações

### Exemplo: Dependência de Classes Concretas

```java
// ❌ Código acoplado a implementações concretas

public class ProcessadorPagamento {
    
    // ❌ Depende de classe concreta
    public void processar(Pedido pedido) {
        // ❌ Cria instância diretamente - acoplamento forte
        ArrayList<String> itens = new ArrayList<>(); // Deveria ser List
        
        for (Item item : pedido.getItens()) {
            itens.add(item.getNome());
        }
        
        // ❌ Usa classe concreta específica
        PayPalGateway gateway = new PayPalGateway(); // Deveria ser interface
        gateway.processarPagamento(pedido.getValor());
        
        // ❌ Usa implementação concreta de logger
        ConsoleLogger logger = new ConsoleLogger(); // Deveria ser interface
        logger.log("Pagamento processado");
    }
}
```

**Problemas:**
- Impossível trocar `PayPal` por outro gateway sem modificar código
- Impossível usar `LinkedList` mais eficiente para itens
- Impossível testar com mock
- Alto acoplamento com implementações específicas

## ✅ Seguindo o Princípio: Programando para Interfaces

### Exemplo: Dependência de Abstrações

```java
// ✅ Interfaces definem contratos

public interface GatewayPagamento {
    boolean processar(double valor);
}

public interface Logger {
    void log(String mensagem);
}

// ✅ Implementações concretas

public class PayPalGateway implements GatewayPagamento {
    @Override
    public boolean processar(double valor) {
        System.out.println("Processando via PayPal: R$ " + valor);
        return true;
    }
}

public class StripeGateway implements GatewayPagamento {
    @Override
    public boolean processar(double valor) {
        System.out.println("Processando via Stripe: R$ " + valor);
        return true;
    }
}

public class ConsoleLogger implements Logger {
    @Override
    public void log(String mensagem) {
        System.out.println("[LOG] " + mensagem);
    }
}

public class FileLogger implements Logger {
    @Override
    public void log(String mensagem) {
        // Escreve em arquivo
        System.out.println("[FILE LOG] " + mensagem);
    }
}

// ✅ Código depende de abstrações

public class ProcessadorPagamento {
    private GatewayPagamento gateway;
    private Logger logger;
    
    // ✅ Dependency Injection via construtor
    public ProcessadorPagamento(GatewayPagamento gateway, Logger logger) {
        this.gateway = gateway;
        this.logger = logger;
    }
    
    public void processar(Pedido pedido) {
        // ✅ Usa tipo abstrato (interface List)
        List<String> itens = new ArrayList<>();
        
        for (Item item : pedido.getItens()) {
            itens.add(item.getNome());
        }
        
        // ✅ Usa interface, não implementação
        boolean sucesso = gateway.processar(pedido.getValor());
        
        if (sucesso) {
            logger.log("Pagamento processado com sucesso");
        } else {
            logger.log("Falha no pagamento");
        }
    }
}

// ✅ Uso flexível:

public class Main {
    public static void main(String[] args) {
        Pedido pedido = new Pedido();
        
        // ✅ Configuração 1: PayPal + Console
        ProcessadorPagamento proc1 = new ProcessadorPagamento(
            new PayPalGateway(),
            new ConsoleLogger()
        );
        proc1.processar(pedido);
        
        // ✅ Configuração 2: Stripe + File (sem modificar ProcessadorPagamento!)
        ProcessadorPagamento proc2 = new ProcessadorPagamento(
            new StripeGateway(),
            new FileLogger()
        );
        proc2.processar(pedido);
        
        // ✅ Teste com mocks
        ProcessadorPagamento procTeste = new ProcessadorPagamento(
            new MockGateway(),
            new MockLogger()
        );
        procTeste.processar(pedido);
    }
}
```

**Benefícios:**
- ✅ Fácil trocar implementações
- ✅ Baixo acoplamento
- ✅ Testável com mocks
- ✅ Flexível e extensível

## 📋 Aplicação Prática do Princípio

### 1. **Variáveis Devem Ser de Tipos Abstratos**

```java
// ❌ Tipo concreto
ArrayList<String> lista = new ArrayList<>();
HashMap<String, Integer> mapa = new HashMap<>();

// ✅ Tipo abstrato (interface)
List<String> lista = new ArrayList<>();
Map<String, Integer> mapa = new HashMap<>();
```

### 2. **Parâmetros Devem Ser de Tipos Abstratos**

```java
// ❌ Parâmetro concreto
public void processar(ArrayList<Item> itens) { }

// ✅ Parâmetro abstrato
public void processar(List<Item> itens) { }
```

### 3. **Retornos Devem Ser de Tipos Abstratos**

```java
// ❌ Retorno concreto
public ArrayList<Usuario> buscarUsuarios() { }

// ✅ Retorno abstrato
public List<Usuario> buscarUsuarios() { }
```

### 4. **Dependências Injetadas via Interfaces**

```java
// ✅ Injeta interface, não implementação
public class Servico {
    private Repositorio repo; // Interface
    
    public Servico(Repositorio repo) {
        this.repo = repo;
    }
}
```

### 5. **Factory Retorna Interface**

```java
// ✅ Factory retorna interface
public interface VeiculoFactory {
    Veiculo criar(); // Retorna interface
}
```

## 📊 Exemplos de Coleções Java

### Programando para Interfaces

```java
// ✅ Boas práticas com Collections

// List - não ArrayList
List<String> nomes = new ArrayList<>();

// Set - não HashSet
Set<Integer> numeros = new HashSet<>();

// Map - não HashMap
Map<String, Pessoa> pessoas = new HashMap<>();

// Queue - não LinkedList
Queue<Task> tarefas = new LinkedList<>();

// Benefício: Fácil trocar implementação
List<String> nomes = new LinkedList<>(); // Troca sem quebrar código
```

## 📋 Como Identificar Violações

### Sinais de Violação:

1. **Tipo Concreto em Declarações**
```java
// ❌ Violação
ArrayList<String> lista = new ArrayList<>();
HashMap<String, Integer> mapa = new HashMap<>();
```

2. **`new` em Código de Alto Nível**
```java
// ❌ Alto nível criando dependências
public class Servico {
    private MySQLRepo repo = new MySQLRepo();
}
```

3. **Casting para Tipos Concretos**
```java
// ❌ Cast para tipo concreto
if (objeto instanceof ArrayList) {
    ArrayList lista = (ArrayList) objeto;
}
```

4. **Conhecimento de Detalhes de Implementação**
```java
// ❌ Código conhece detalhes
public void processar(MySQLDatabase db) {
    // Conhece que é MySQL específico
}
```

### Perguntas-Chave:

```
❓ Poderia usar interface/classe abstrata?
   → Se SIM e não usou, viola princípio

❓ Código quebra se trocar implementação?
   → Se SIM, viola princípio

❓ Código conhece detalhes de implementação?
   → Se SIM, viola princípio
```

## 📋 Diretrizes Práticas

### 1. **Regra de Ouro das Coleções**
```java
// Use interface no tipo, implementação na criação
List<T> lista = new ArrayList<>();  // ✅
Set<T> conjunto = new HashSet<>();   // ✅
Map<K,V> mapa = new HashMap<>();     // ✅
```

### 2. **Dependency Injection Sempre via Interface**
```java
public class Servico {
    private Repositorio repo;    // Interface
    private Logger log;          // Interface
    private EmailSender email;   // Interface
    
    public Servico(Repositorio repo, Logger log, EmailSender email) {
        this.repo = repo;
        this.log = log;
        this.email = email;
    }
}
```

### 3. **APIs Públicas Retornam Interfaces**
```java
// ✅ API pública retorna interface
public interface UsuarioService {
    List<Usuario> buscarTodos(); // List, não ArrayList
    Set<Permissao> getPermissoes(); // Set, não HashSet
}
```

### 4. **Evite Expor Implementação**
```java
// ❌ Expõe implementação
public ArrayList<Item> getItens() {
    return itens; // Expõe ArrayList
}

// ✅ Retorna interface
public List<Item> getItens() {
    return itens; // Retorna List
}
```

## ⚖️ Pragmatismo e Exceções

### Quando Classes Concretas São Aceitáveis:

1. **Classes de Biblioteca Padrão Estáveis**
```java
// ✅ String, Integer, LocalDate são estáveis
String nome = "João";
Integer idade = 25;
LocalDate data = LocalDate.now();
```

2. **Value Objects**
```java
// ✅ Value objects são OK como concretos
public class Endereco {
    private String rua;
    private String cidade;
}
```

3. **Única Implementação Provável**
```java
// Se REALMENTE só haverá uma implementação,
// interface pode ser overhead desnecessário
```

### Não Crie Interface Para Tudo

```java
// ❌ Over-engineering
public interface StringWrapper {
    String getValue();
}

// Para classes simples, concreto pode ser OK
public class Config {
    private String valor;
}
```

## 🔗 Relação com Outros Princípios

- **DIP (Dependency Inversion)**: Praticamente o mesmo princípio
- **OCP (Open/Closed)**: Interfaces facilitam extensão
- **LSP (Liskov Substitution)**: Substituibilidade funciona via interfaces
- **ISP (Interface Segregation)**: Interfaces devem ser focadas

## 📚 Exemplos Práticos

Veja os exemplos de código neste diretório:
- `ProgrameParaInterfaces.java` - Implementação com interfaces vs classes concretas

## 🎯 Exercícios Práticos

1. **Análise**: Revise código - quantas declarações usam tipos concretos?
2. **Refatoração**: Troque tipos concretos por interfaces onde apropriado
3. **Teste**: Veja como fica mais fácil testar com interfaces
4. **Design**: Em novo código, comece pensando em interfaces

## 📖 Leituras Recomendadas

1. **"Design Patterns"** - Gang of Four (1994) - Formulação original
2. **"Effective Java"** - Joshua Bloch (2018) - Item 64: "Refer to objects by their interfaces"
3. **"Clean Code"** - Robert C. Martin (2008) - Abstrações e interfaces

## 💭 Citações Inspiradoras

> "Refer to objects by their interfaces." - Joshua Bloch

> "Program to an interface, not an implementation. This lets you vary the implementation independently from programs that use it." - Gang of Four

> "The most flexible systems are those in which source code dependencies refer only to abstractions, not to concretions." - Robert C. Martin

---

**Lembre-se:** Programar para interfaces não significa criar interface para cada classe. Significa usar abstrações (interfaces/classes abstratas) onde variação ou substituição é esperada, promovendo flexibilidade e desacoplamento. Use interfaces estrategicamente para pontos de extensão e variação, não indiscriminadamente.
