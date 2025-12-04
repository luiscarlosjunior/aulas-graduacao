# Abstract Factory Pattern

O padrão Abstract Factory fornece uma interface para criar famílias de objetos relacionados ou dependentes sem especificar suas classes concretas.

## 🎯 Problema

Como criar famílias inteiras de objetos relacionados (produtos) de forma que:
- Os produtos de uma família sejam compatíveis entre si
- O código cliente não dependa de classes concretas específicas
- Seja fácil trocar toda a família de produtos de uma vez

**Exemplo prático**: Uma aplicação precisa ter tema claro e escuro. Cada tema tem botões, campos de texto, menus, etc. Todos os componentes de um tema devem ser consistentes.

## 💡 Solução

- Definir interfaces abstratas para cada tipo de produto (Botao, CampoTexto)
- Criar uma interface de factory abstrata que declara métodos para criar cada produto
- Implementar factories concretas, cada uma criando uma família completa de produtos
- O cliente usa apenas as interfaces abstratas

## 🏗️ Estrutura

```
        AbstractFactory
        ├── criarProdutoA(): AbstractProdutoA
        └── criarProdutoB(): AbstractProdutoB
                ↑
        ┌───────┴───────┐
        │               │
ConcreteFactory1  ConcreteFactory2
        │               │
    Cria produtos   Cria produtos
    da Família 1    da Família 2
```

## 📋 Implementação

### Interfaces dos Produtos
- **[Botao.java](Botao.java)** - Interface para botões
- **[CampoTexto.java](CampoTexto.java)** - Interface para campos de texto

### Produtos Concretos - Tema Claro
- **[BotaoClaro.java](BotaoClaro.java)** - Botão com estilo claro
- **[CampoTextoClaro.java](CampoTextoClaro.java)** - Campo de texto claro

### Produtos Concretos - Tema Escuro
- **[BotaoEscuro.java](BotaoEscuro.java)** - Botão com estilo escuro
- **[CampoTextoEscuro.java](CampoTextoEscuro.java)** - Campo de texto escuro

### Factories
- **[UIFactory.java](UIFactory.java)** - Interface da abstract factory
- **[UIFactoryClaro.java](UIFactoryClaro.java)** - Factory para tema claro
- **[UIFactoryEscuro.java](UIFactoryEscuro.java)** - Factory para tema escuro

### Cliente
- **[Aplicacao.java](Aplicacao.java)** - Classe cliente que usa a factory
- **[TesteAbstractFactory.java](TesteAbstractFactory.java)** - Testes e exemplos

## 🚀 Como Executar

```bash
javac *.java
java TesteAbstractFactory
```

## ✅ Vantagens

- **Consistência**: Garante que produtos de uma família sejam compatíveis
- **Isolamento**: Cliente não conhece classes concretas
- **Flexibilidade**: Fácil trocar famílias inteiras de produtos
- **Single Responsibility**: Criação isolada em factories
- **Open/Closed**: Fácil adicionar novas famílias sem modificar código existente

## ⚠️ Desvantagens

- **Complexidade**: Muitas classes e interfaces
- **Rigidez**: Difícil adicionar novos tipos de produtos (precisa alterar todas as factories)
- **Overhead**: Pode ser excessivo para sistemas simples
- **Hierarquia profunda**: Pode criar muitas camadas de abstração

## 🎯 Quando Usar

✅ **Use quando**:
- Sistema deve ser independente de como seus produtos são criados
- Sistema deve usar uma das várias famílias de produtos
- Família de produtos relacionados deve ser usada em conjunto
- Quer fornecer uma biblioteca de produtos sem expor implementações
- Precisa garantir consistência entre produtos relacionados

❌ **Evite quando**:
- Apenas um tipo de produto é criado
- Produtos não formam famílias coesas
- Sistema é simples e não precisa dessa flexibilidade
- Não há necessidade de trocar famílias de produtos

## 📝 Exemplo Prático: Sistema Multiplataforma

```java
// Produtos abstratos
interface Janela {
    void desenhar();
}

interface Botao {
    void renderizar();
}

// Abstract Factory
interface GUIFactory {
    Janela criarJanela();
    Botao criarBotao();
}

// Factory Windows
class WindowsFactory implements GUIFactory {
    public Janela criarJanela() {
        return new JanelaWindows();
    }
    
    public Botao criarBotao() {
        return new BotaoWindows();
    }
}

// Factory macOS
class MacFactory implements GUIFactory {
    public Janela criarJanela() {
        return new JanelaMac();
    }
    
    public Botao criarBotao() {
        return new BotaoMac();
    }
}

// Cliente
class Aplicacao {
    private GUIFactory factory;
    
    public Aplicacao(GUIFactory factory) {
        this.factory = factory;
    }
    
    public void criarUI() {
        Janela janela = factory.criarJanela();
        Botao botao = factory.criarBotao();
        
        janela.desenhar();
        botao.renderizar();
    }
}

// Uso
public class Main {
    public static void main(String[] args) {
        GUIFactory factory;
        
        String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacFactory();
        }
        
        Aplicacao app = new Aplicacao(factory);
        app.criarUI();
    }
}
```

## 🔄 Variações do Padrão

### 1. Factory como Singleton
Cada factory concreta pode ser singleton:
```java
public class UIFactoryEscuro implements UIFactory {
    private static UIFactoryEscuro instance;
    
    private UIFactoryEscuro() {}
    
    public static UIFactoryEscuro getInstance() {
        if (instance == null) {
            instance = new UIFactoryEscuro();
        }
        return instance;
    }
    // ... métodos de criação
}
```

### 2. Factory com Registro
Registro central de factories:
```java
public class FactoryRegistry {
    private static Map<String, UIFactory> factories = new HashMap<>();
    
    static {
        factories.put("claro", new UIFactoryClaro());
        factories.put("escuro", new UIFactoryEscuro());
    }
    
    public static UIFactory getFactory(String tipo) {
        return factories.get(tipo);
    }
}
```

### 3. Factory com Configuração
Factory determinada por arquivo de configuração:
```java
public class ConfigurableFactory {
    public static UIFactory criarFactory(Properties config) {
        String tema = config.getProperty("tema");
        
        switch(tema) {
            case "claro":
                return new UIFactoryClaro();
            case "escuro":
                return new UIFactoryEscuro();
            default:
                return new UIFactoryClaro();
        }
    }
}
```

## 🆚 Abstract Factory vs Factory Method

| Aspecto | Abstract Factory | Factory Method |
|---------|-----------------|----------------|
| **Foco** | Famílias de produtos | Produto único |
| **Complexidade** | Maior | Menor |
| **Hierarquia** | Factory e produtos | Apenas factory |
| **Uso** | Múltiplos produtos relacionados | Um tipo de produto |
| **Flexibilidade** | Troca família inteira | Troca implementação única |

## 💡 Dicas de Implementação

1. **Comece simples**: Adicione produtos conforme necessário
2. **Use interfaces**: Abstraia produtos e factories
3. **Considere Singleton**: Factories geralmente são singleton
4. **Valide consistência**: Garanta que produtos da mesma família sejam compatíveis
5. **Documente famílias**: Deixe claro quais produtos pertencem a cada família
6. **Teste isoladamente**: Teste cada família independentemente

## 🔗 Padrões Relacionados

- **Factory Method**: Abstract Factory usa Factory Methods
- **Singleton**: Factories costumam ser Singleton
- **Prototype**: Produtos podem ser clones ao invés de criados
- **Builder**: Factory pode usar Builder para criar produtos complexos
- **Facade**: Factory pode funcionar como facade para criação

## 📚 Exercícios

1. Adicione um terceiro tema (ex: tema azul ou alto contraste)
2. Implemente mais componentes (Checkbox, RadioButton, Menu)
3. Crie factories para diferentes plataformas (Mobile, Desktop, Web)
4. Implemente um sistema de preferências que salva o tema escolhido
5. Adicione animações diferentes para cada tema

## 🎓 Conceitos Acadêmicos

### Princípio da Inversão de Dependência (DIP)
O Abstract Factory exemplifica o DIP: o cliente depende de abstrações (interfaces) e não de implementações concretas.

### Lei de Demeter
O padrão reduz acoplamento: o cliente não precisa conhecer os detalhes de como os produtos são criados.

### Coesão
Produtos de uma família têm alta coesão - são projetados para trabalhar juntos e compartilham um tema comum.

### Open/Closed Principle
Fácil adicionar novas famílias (aberto para extensão) sem modificar código existente (fechado para modificação).
