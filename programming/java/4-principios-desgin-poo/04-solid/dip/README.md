# DIP - Dependency Inversion Principle
## Princípio da Inversão de Dependência

## 📖 Visão Geral

O **Dependency Inversion Principle (DIP)** estabelece que módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações. Além disso, abstrações não devem depender de detalhes - detalhes devem depender de abstrações. Este princípio "inverte" a direção tradicional de dependência em design de software.

## 🎯 Definição

> **A.** High-level modules should not depend on low-level modules. Both should depend on abstractions.
>
> **B.** Abstractions should not depend on details. Details should depend on abstractions.
>
> **A.** Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações.
>
> **B.** Abstrações não devem depender de detalhes. Detalhes devem depender de abstrações.
>
> -- Robert C. Martin

## 📚 Origem e História

### Robert C. Martin (1996)

Robert C. Martin formulou DIP em 1996, publicado em seu artigo "The Dependency Inversion Principle" no C++ Report.

### Revolução Conceitual

DIP representou mudança fundamental em como pensamos sobre dependências em sistemas OO. Em design tradicional, módulos de alto nível (lógica de negócio) dependem de módulos de baixo nível (detalhes de infraestrutura). DIP inverte isso.

### Técnicas Relacionadas

DIP é frequentemente implementado através de:
- **Dependency Injection (DI)**: Injeção de dependências
- **Inversion of Control (IoC)**: Inversão de controle
- **Containers DI**: Spring, Guice, etc.

## 🔍 Entendendo a "Inversão"

### Dependência Tradicional (Sem DIP)
```
[Alto Nível: Lógica de Negócio]
           ↓ depende de
[Baixo Nível: Detalhes de Infraestrutura]
```

Exemplo:
```
ProcessadorPedidos (alto nível)
       ↓
MySQLDatabase (baixo nível)
```

### Dependência Invertida (Com DIP)
```
[Alto Nível: Lógica de Negócio]  →  [Abstração: Interface]
                                           ↑
                                    implementa
                                           |
                [Baixo Nível: Detalhes de Infraestrutura]
```

Exemplo:
```
ProcessadorPedidos → RepositorioDados (interface)
                            ↑
                     MySQLDatabase implementa
```

**A inversão:** Baixo nível agora depende de abstração definida pelo alto nível!

## 🎯 Por Que DIP é Importante?

### 1. **Reduz Acoplamento**
Lógica de negócio não está acoplada a detalhes de implementação específicos.

### 2. **Aumenta Flexibilidade**
Fácil trocar implementações (MySQL → PostgreSQL, Email → SMS, etc.).

### 3. **Melhora Testabilidade**
Fácil substituir dependências reais por mocks/stubs em testes.

### 4. **Promove Reutilização**
Lógica de negócio pode ser reutilizada com diferentes infraestruturas.

### 5. **Facilita Manutenção**
Mudanças em detalhes de baixo nível não afetam lógica de alto nível.

## ❌ Violação de DIP

### Exemplo: Dependência Direta de Implementação

```java
// ❌ Violação de DIP: Classe de alto nível depende diretamente de baixo nível

// Baixo nível: Detalhe de implementação
public class MySQLDatabase {
    public void salvarDados(String dados) {
        System.out.println("Salvando no MySQL: " + dados);
        // Lógica específica do MySQL
    }
    
    public String buscarDados(String id) {
        return "Dados do MySQL";
    }
}

// Alto nível: Lógica de negócio
public class ProcessadorPedidos {
    // ❌ Dependência direta de classe concreta
    private MySQLDatabase database = new MySQLDatabase();
    
    public void processar(String pedido) {
        // Lógica de negócio...
        database.salvarDados(pedido);
        
        // ❌ Problemas:
        // 1. Impossível trocar para PostgreSQL sem modificar esta classe
        // 2. Impossível testar sem MySQL real
        // 3. Alto acoplamento com implementação específica
        // 4. ProcessadorPedidos (alto nível) depende de MySQLDatabase (baixo nível)
    }
}
```

**Problemas:**
- Alto acoplamento: `ProcessadorPedidos` conhece detalhes de `MySQLDatabase`
- Inflexível: trocar banco requer modificar `ProcessadorPedidos`
- Difícil testar: necessita MySQL real para testes
- Violação de OCP: não aberto para extensão, modificação necessária

## ✅ Seguindo DIP: Dependência de Abstração

```java
// ✅ DIP: Abstração definida por módulo de alto nível

// Interface (abstração) - definida pela necessidade do alto nível
public interface RepositorioDados {
    void salvar(String dados);
    String buscar(String id);
}

// ✅ Implementações concretas DEPENDEM da abstração
public class MySQLDatabase implements RepositorioDados {
    @Override
    public void salvar(String dados) {
        System.out.println("Salvando no MySQL: " + dados);
        // Detalhes específicos do MySQL
    }
    
    @Override
    public String buscar(String id) {
        return "Dados do MySQL";
    }
}

public class PostgreSQLDatabase implements RepositorioDados {
    @Override
    public void salvar(String dados) {
        System.out.println("Salvando no PostgreSQL: " + dados);
        // Detalhes específicos do PostgreSQL
    }
    
    @Override
    public String buscar(String id) {
        return "Dados do PostgreSQL";
    }
}

public class MongoDBDatabase implements RepositorioDados {
    @Override
    public void salvar(String dados) {
        System.out.println("Salvando no MongoDB: " + dados);
        // Detalhes específicos do MongoDB
    }
    
    @Override
    public String buscar(String id) {
        return "Dados do MongoDB";
    }
}

// ✅ Classe de alto nível depende de abstração
public class ProcessadorPedidos {
    private RepositorioDados repositorio;
    
    // ✅ Dependency Injection via construtor
    public ProcessadorPedidos(RepositorioDados repositorio) {
        this.repositorio = repositorio;
    }
    
    public void processar(String pedido) {
        // Lógica de negócio...
        repositorio.salvar(pedido);
        
        // ✅ Benefícios:
        // 1. Pode usar qualquer implementação de RepositorioDados
        // 2. Fácil de testar com mock
        // 3. Baixo acoplamento
        // 4. ProcessadorPedidos depende de abstração, não concreção
    }
}

// ✅ Configuração e uso (Dependency Injection)
public class Main {
    public static void main(String[] args) {
        // Configuração externa - escolhe implementação
        RepositorioDados repo = new MySQLDatabase();
        ProcessadorPedidos processador = new ProcessadorPedidos(repo);
        processador.processar("Pedido #123");
        
        // ✅ Fácil trocar implementação
        repo = new MongoDBDatabase();
        processador = new ProcessadorPedidos(repo);
        processador.processar("Pedido #456");
        
        // ✅ Teste com mock
        RepositorioDados mockRepo = new RepositorioMockParaTestes();
        processador = new ProcessadorPedidos(mockRepo);
        processador.processar("Pedido teste");
    }
}

// Mock para testes
class RepositorioMockParaTestes implements RepositorioDados {
    @Override
    public void salvar(String dados) {
        System.out.println("Mock: Dados salvos - " + dados);
    }
    
    @Override
    public String buscar(String id) {
        return "Mock: Dados de teste";
    }
}
```

**Benefícios:**
- ✅ Baixo acoplamento: `ProcessadorPedidos` não conhece detalhes de implementação
- ✅ Flexível: fácil trocar implementação
- ✅ Testável: pode usar mocks em testes
- ✅ Segue OCP: extensível sem modificação

## 🛠️ Técnicas para Implementar DIP

### 1. **Dependency Injection via Construtor**
```java
public class Servico {
    private Repositorio repo;
    
    // ✅ DI via construtor - preferido
    public Servico(Repositorio repo) {
        this.repo = repo;
    }
}
```

### 2. **Dependency Injection via Setter**
```java
public class Servico {
    private Repositorio repo;
    
    // ✅ DI via setter - quando configuração pode mudar
    public void setRepositorio(Repositorio repo) {
        this.repo = repo;
    }
}
```

### 3. **Dependency Injection via Interface**
```java
public interface RepositorioConfigurado {
    void configurarRepositorio(Repositorio repo);
}

public class Servico implements RepositorioConfigurado {
    private Repositorio repo;
    
    @Override
    public void configurarRepositorio(Repositorio repo) {
        this.repo = repo;
    }
}
```

### 4. **IoC Container (Spring, Guice, etc.)**
```java
// Spring Framework
@Service
public class ProcessadorPedidos {
    private final RepositorioDados repositorio;
    
    @Autowired // Spring injeta automaticamente
    public ProcessadorPedidos(RepositorioDados repositorio) {
        this.repositorio = repositorio;
    }
}
```

### 5. **Factory Pattern**
```java
public interface RepositorioFactory {
    Repositorio criar();
}

public class ServicoPedidos {
    private Repositorio repo;
    
    public ServicoPedidos(RepositorioFactory factory) {
        this.repo = factory.criar();
    }
}
```

## 📋 Como Identificar Violações de DIP

### Sinais de Violação:

1. **`new` Dentro de Classe de Alto Nível**
```java
// ❌ Criando dependência concreta diretamente
public class Servico {
    private MySQLRepo repo = new MySQLRepo(); // Violação!
}
```

2. **Import de Pacotes de Baixo Nível**
```java
// ❌ Alto nível importando detalhes
import com.mysql.jdbc.Driver; // Detalhe de infraestrutura
public class LogicaNegocio {
    // Violação!
}
```

3. **Impossível Testar sem Infraestrutura**
```java
// ❌ Teste requer banco real
@Test
public void teste() {
    Servico s = new Servico(); // Cria MySQL interno
    // Precisa de MySQL rodando!
}
```

4. **Conhecimento de Detalhes de Implementação**
```java
// ❌ Alto nível conhece detalhes de baixo nível
public void processar() {
    String sql = "SELECT * FROM..."; // Conhece SQL!
    // Violação!
}
```

### Perguntas-Chave:

```
❓ Classe de negócio cria suas próprias dependências?
   → Se SIM, viola DIP

❓ Impossível trocar implementação sem modificar código?
   → Se SIM, viola DIP

❓ Testes requerem infraestrutura real (BD, rede, etc)?
   → Se SIM, viola DIP
```

## 📋 Diretrizes Práticas

### 1. **Defina Interfaces no Módulo de Alto Nível**
```java
// Módulo de negócio define o que precisa
package com.empresa.negocio;

public interface RepositorioPedidos {
    void salvar(Pedido p);
}

// Módulo de infraestrutura implementa
package com.empresa.infraestrutura;

public class RepositorioPedidosMySQL implements RepositorioPedidos {
    // Implementação
}
```

### 2. **Use Abstrações em Parâmetros e Retornos**
```java
// ✅ Usa interfaces
public void processar(Repositorio repo) { }
public Validador criarValidador() { }

// ❌ Usa classes concretas
public void processar(MySQLRepo repo) { }
public ValidadorEmail criarValidador() { }
```

### 3. **Injete Todas as Dependências Externas**
```java
// ✅ Todas as dependências injetadas
public class Servico {
    private Repositorio repo;
    private Logger log;
    private EmailSender email;
    
    public Servico(Repositorio repo, Logger log, EmailSender email) {
        this.repo = repo;
        this.log = log;
        this.email = email;
    }
}
```

### 4. **Evite Static Methods para Dependências**
```java
// ❌ Dependência estática - difícil de mockar
Database.getInstance().salvar(dados);

// ✅ Dependência injetada - fácil de mockar
repositorio.salvar(dados);
```

## ⚖️ DIP e Pragmatismo

### Quando DIP Pode ser Overkill:

1. **Código Simples e Estável**
   - Scripts pequenos, protótipos
   - Código que nunca vai precisar de múltiplas implementações

2. **Dependências Muito Estáveis**
   - Classes da biblioteca padrão (String, List, etc.)
   - Frameworks muito estáveis

3. **Over-Engineering**
   - Criar interface para tudo "só para seguir DIP"
   - Abstrações sem benefício real

### Use DIP Quando:

- ✅ Múltiplas implementações existem ou são prováveis
- ✅ Testabilidade é importante
- ✅ Flexibilidade para trocar implementações é necessária
- ✅ Módulo será reutilizado em diferentes contextos

## 🔗 Relação com Outros Princípios SOLID

- **OCP**: DIP é técnica chave para alcançar OCP
- **SRP**: Classes com responsabilidade única são mais fáceis de abstrair
- **LSP**: Inversão depende de substituibilidade funcionar
- **ISP**: Interfaces segregadas facilitam inversão de dependência

## 📚 Exemplos Práticos

Veja os exemplos de código neste diretório que demonstram dependência direta vs. inversão de dependência.

## 🎯 Exercícios Práticos

1. **Análise**: Identifique onde seu código cria dependências com `new`
2. **Abstração**: Crie interfaces para dependências concretas
3. **Injeção**: Refatore para usar Dependency Injection
4. **Teste**: Veja como fica mais fácil testar com mocks

## 📖 Leituras Recomendadas

1. **"Agile Software Development"** - Robert C. Martin (2002) - DIP explicado
2. **"Dependency Injection"** - Mark Seemann (2011) - Livro completo sobre DI
3. **"Clean Architecture"** - Robert C. Martin (2017) - DIP em arquitetura

## 💭 Citações Inspiradoras

> "The most flexible systems are those in which source code dependencies refer only to abstractions, not to concretions." - Robert C. Martin

> "Depend on abstractions, not on concretions." - Gang of Four

---

**Lembre-se:** DIP não é apenas sobre usar interfaces. É sobre inverter a direção de dependência - fazendo módulos de baixo nível (detalhes) dependerem de abstrações definidas por módulos de alto nível (lógica de negócio). Isso torna seu sistema flexível, testável e manutenível.
