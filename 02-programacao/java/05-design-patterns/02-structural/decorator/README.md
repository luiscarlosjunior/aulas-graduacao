# Decorator Pattern

O padrão Decorator adiciona responsabilidades a objetos dinamicamente, fornecendo alternativa flexível à herança para estender funcionalidades.

## 🎯 Problema

Você precisa adicionar funcionalidades a objetos de forma dinâmica e transparente, sem afetar outros objetos da mesma classe. Usar herança para cada combinação resultaria em explosão de subclasses.

### Exemplo Real
Imagine um sistema de pedidos de café. Você tem café simples, mas pode adicionar leite, chocolate, chantilly, caramelo, etc. Criar uma classe para cada combinação (CafeComLeite, CafeComLeiteEChocolate, CafeComLeiteChocolateEChantilly, etc.) seria impraticável.

## 💡 Solução

Criar decoradores que:
1. Implementam a mesma interface do componente base
2. Contêm uma referência para um componente
3. Delegam operações ao componente e adicionam comportamento extra

## 🏗️ Estrutura

```
┌────────────────┐
│   Component    │
│  (interface)   │
└────────────────┘
        △
        │
        ├─────────────────────┐
        │                     │
┌───────────────┐    ┌───────────────────┐
│ConcreteComp   │    │   Decorator       │
└───────────────┘    │  (abstract)       │
                     └───────────────────┘
                              △
                              │
                  ┌───────────┴───────────┐
                  │                       │
          ┌───────────────┐       ┌──────────────┐
          │ConcreteDecA   │       │ConcreteDecB  │
          └───────────────┘       └──────────────┘
```

## 📋 Componentes

- **Component**: Interface comum para objetos que podem ter responsabilidades adicionadas
- **ConcreteComponent**: Objeto base ao qual responsabilidades adicionais podem ser anexadas
- **Decorator**: Mantém referência para um Component e define interface conforme Component
- **ConcreteDecorator**: Adiciona responsabilidades ao componente

## 📝 Implementações

### [Bebida.java](Bebida.java)
Interface Component - define contrato para bebidas.

### [CafeSimples.java](CafeSimples.java)
ConcreteComponent - implementação base de uma bebida.

### [BebidaDecorator.java](BebidaDecorator.java)
Decorator abstrato - base para todos os decoradores.

### [ComLeite.java](ComLeite.java)
ConcreteDecorator - adiciona leite à bebida.

### [ComChocolate.java](ComChocolate.java)
ConcreteDecorator - adiciona chocolate à bebida.

### [ComChantilly.java](ComChantilly.java)
ConcreteDecorator - adiciona chantilly à bebida.

### [ComCaramelo.java](ComCaramelo.java)
ConcreteDecorator - adiciona caramelo à bebida.

### [TesteDecorator.java](TesteDecorator.java)
Programa de demonstração do padrão Decorator.

## 🚀 Como Executar

```bash
# Compilar todos os arquivos
javac *.java

# Executar o teste
java TesteDecorator
```

## 📊 Exemplo de Saída Esperada

```
=== Cafeteria com Decorator Pattern ===

Pedido 1: Café simples
Descrição: Café Expresso
Custo: R$ 5.00

Pedido 2: Café com leite
Descrição: Café Expresso, Leite
Custo: R$ 6.50

Pedido 3: Café especial
Descrição: Café Expresso, Leite, Chocolate, Chantilly
Custo: R$ 10.00

Pedido 4: Super café
Descrição: Café Expresso, Leite, Chocolate, Caramelo, Chantilly
Custo: R$ 12.00
```

## ✅ Vantagens

1. **Flexibilidade**
   - Adiciona/remove responsabilidades em tempo de execução
   - Combina funcionalidades de diferentes formas

2. **Alternativa à Herança**
   - Evita explosão de subclasses
   - Não modifica classe original

3. **Single Responsibility Principle**
   - Cada decorator tem responsabilidade específica
   - Funcionalidades divididas em classes distintas

4. **Open/Closed Principle**
   - Aberto para extensão (novos decorators)
   - Fechado para modificação (componente base)

5. **Composição**
   - Favorece composição sobre herança
   - Maior flexibilidade que herança estática

## ⚠️ Desvantagens

1. **Complexidade**
   - Muitos objetos pequenos no sistema
   - Pode ser difícil debugar (pilha de wrappers)

2. **Ordem de Decoração**
   - Ordem pode importar (decorator de compressão antes de criptografia)
   - Requer cuidado na composição

3. **Identificação de Tipo**
   - Dificulta verificação de tipo (instanceof)
   - Objeto decorado é diferente do original

4. **Overhead**
   - Cada decorator adiciona camada de indireção
   - Pode impactar performance em operações críticas

## 🎯 Quando Usar

✅ **Use Decorator quando**:
- Precisa adicionar responsabilidades a objetos dinamicamente
- Funcionalidades podem ser retiradas ou combinadas
- Extensão por herança é impraticável (explosão de subclasses)
- Não pode ou não deve modificar classe original
- Quer adicionar funcionalidades de forma transparente

❌ **Evite Decorator quando**:
- Funcionalidades são fixas e simples
- Todas as combinações são conhecidas e limitadas
- Performance é extremamente crítica
- Ordem de aplicação pode causar bugs sutis

## 🔄 Decorator vs Herança

### Com Herança (Problemático)
```
Cafe
├── CafeComLeite
│   ├── CafeComLeiteEChocolate
│   │   ├── CafeComLeiteChocolateEChantilly
│   │   └── CafeComLeiteChocolateECaramelo
│   └── CafeComLeiteEChantilly
└── CafeComChocolate
    └── ... (explosão de classes!)
```

Para 4 adicionais, precisaríamos de 2^4 = 16 classes!

### Com Decorator (Elegante)
```
Cafe base + Decorators independentes
4 decorators → 4 classes (mais 1 base)
Combinações ilimitadas em runtime
```

## 💼 Casos de Uso Reais

### 1. Java I/O Streams
```java
// Múltiplos decorators em cadeia
BufferedReader reader = new BufferedReader(
    new InputStreamReader(
        new FileInputStream("file.txt")
    )
);
```

### 2. Java Swing Components
```java
// Adiciona scrollbar a painel
JScrollPane scrollPane = new JScrollPane(panel);
```

### 3. Servlet Filters (Java EE)
```java
// Filtros decoram requisições HTTP
@WebFilter("/api/*")
public class AuthFilter implements Filter {
    // Decora requisição adicionando autenticação
}
```

## 🔗 Padrões Relacionados

### Decorator vs Adapter
- **Decorator**: Mesma interface, adiciona funcionalidade
- **Adapter**: Muda interface para compatibilidade
- **Diferença**: Propósito (extensão vs adaptação)

### Decorator vs Proxy
- **Decorator**: Foca em adicionar responsabilidades
- **Proxy**: Foca em controlar acesso
- **Similaridade**: Estrutura similar (wrapping)

### Decorator vs Composite
- **Decorator**: Adiciona responsabilidades, um componente
- **Composite**: Agrupa componentes, múltiplos filhos
- **Diferença**: Decorator não é agregação

### Decorator vs Strategy
- **Decorator**: Muda comportamento externamente (wrapping)
- **Strategy**: Muda comportamento internamente (algoritmo)
- **Diferença**: Decorator mantém interface, Strategy muda implementação

## 📝 Exercícios Práticos

### Exercício 1: Decoradores de Texto
Crie decoradores para formatar texto (negrito, itálico, sublinhado).

```java
interface Texto {
    String renderizar();
}

class TextoSimples implements Texto {
    private String conteudo;
    // Implemente
}

class Negrito extends TextoDecorator {
    // Adicione tags <b>
}
```

### Exercício 2: Decoradores de Pizza
Sistema de pedidos de pizza com ingredientes opcionais.

```java
interface Pizza {
    String getDescricao();
    double getCusto();
}

// Base: Margherita, Calabresa
// Decorators: Queijo extra, Bacon, Azeitona, Borda recheada
```

### Exercício 3: Decoradores de Stream
Implemente decoradores para comprimir e criptografar dados.

```java
interface DataStream {
    void write(String data);
    String read();
}

class CompressaoDecorator extends StreamDecorator {
    // Comprime dados
}

class CriptografiaDecorator extends StreamDecorator {
    // Criptografa dados
}
```

### Exercício 4: Decoradores de Notificação
Sistema de notificações com múltiplos canais.

```java
interface Notificacao {
    void enviar(String mensagem);
}

// Decorators: Email, SMS, Push, Slack
// Combine múltiplos canais dinamicamente
```

## 🎓 Análise Acadêmica

### Princípios de Design Aplicados

1. **Open/Closed Principle (OCP)**
   - Classes abertas para extensão via decorators
   - Fechadas para modificação (não altera componente base)

2. **Single Responsibility Principle (SRP)**
   - Cada decorator tem responsabilidade única
   - Funcionalidades isoladas em classes distintas

3. **Liskov Substitution Principle (LSP)**
   - Decorator pode substituir componente base
   - Comportamento é estendido, não alterado

4. **Composition over Inheritance**
   - Usa composição dinâmica ao invés de herança estática
   - Maior flexibilidade em runtime

### Análise de Complexidade

**Sem Decorator**:
- N funcionalidades → 2^N classes (combinações)
- Rígido, difícil de manter

**Com Decorator**:
- N funcionalidades → N+1 classes (base + decorators)
- Flexível, fácil de estender

### Trade-offs

| Aspecto | Herança | Decorator |
|---------|---------|-----------|
| Flexibilidade | Estática | Dinâmica |
| Número de classes | Explosão | Linear |
| Complexidade código | Baixa | Média |
| Transparência | Direta | Wrappers |
| Extensibilidade | Difícil | Fácil |

## 🔍 Identificando Necessidade

**Sinais que você precisa de Decorator**:
- If/else ou switch para combinar funcionalidades
- Hierarquia de classes crescendo exponencialmente
- Necessidade de adicionar/remover comportamentos em runtime
- Funcionalidades opcionais que podem ser combinadas
- Código duplicado em várias subclasses

## 📚 Referências

- **Design Patterns: Elements of Reusable Object-Oriented Software** - Gang of Four
- **Head First Design Patterns** - Freeman & Freeman (excelente capítulo sobre Decorator)
- **Effective Java** - Joshua Bloch (patterns em Java I/O)

## 🔗 Navegação

- [Voltar para Padrões Estruturais](../)
- [Anterior: Adapter Pattern](../adapter/)
- [Próximo: Facade Pattern](../facade/)
