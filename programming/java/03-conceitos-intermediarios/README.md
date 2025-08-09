# Conceitos Intermediários de Java

Esta seção aborda conceitos intermediários essenciais para desenvolvedores Java que já dominam os fundamentos e POO.

## 🎯 Objetivos

- Dominar as Collections Framework do Java
- Compreender e utilizar Generics
- Trabalhar com Enumerações
- Entender Annotations
- Aplicar programação funcional com Lambda e Streams

## 📋 Conteúdo

### [01 - Collections](01-collections/)
Framework de coleções do Java: List, Set, Map e suas implementações.

- **List**: ArrayList, LinkedList, Vector
- **Set**: HashSet, TreeSet, LinkedHashSet
- **Map**: HashMap, TreeMap, LinkedHashMap
- **Queue**: ArrayDeque, PriorityQueue

### [02 - Generics](02-generics/)
Tipos parametrizados para type-safety e reutilização de código.

- **Classes Genéricas**: Definição e uso
- **Métodos Genéricos**: Flexibilidade na criação
- **Wildcards**: Extends, super e unbounded
- **Type Erasure**: Como funciona internamente

### [03 - Enumerações](03-enum/)
Tipos enumerados para representar constantes e comportamentos.

- **Enum Básico**: Definição e uso
- **Enum com Métodos**: Comportamentos customizados
- **Enum com Construtor**: Inicialização de valores
- **EnumSet e EnumMap**: Coleções especializadas

### [04 - Annotations](04-annotations/)
Metadados para classes, métodos e campos.

- **Annotations Built-in**: @Override, @Deprecated, @SuppressWarnings
- **Annotations Customizadas**: Criação e uso
- **Reflection**: Processamento de annotations
- **Retention Policies**: Runtime, source, class

### [05 - Lambda e Streams](05-lambda-streams/)
Programação funcional introduzida no Java 8.

- **Lambda Expressions**: Sintaxe e uso
- **Functional Interfaces**: Predicate, Function, Consumer
- **Stream API**: Operações intermediárias e terminais
- **Collectors**: Agrupamento e redução

## 🚀 Como Estudar

1. **Pré-requisitos**: Complete fundamentos e POO primeiro
2. **Sequência**: Siga a ordem numérica das pastas
3. **Prática**: Execute todos os exemplos
4. **Combinação**: Combine diferentes conceitos em projetos

## 💡 Benefícios dos Conceitos Intermediários

### Collections
- **Performance**: Escolha da estrutura de dados adequada
- **Flexibilidade**: Operações em coleções de objetos
- **Iteração**: Percorrer elementos de forma eficiente

### Generics
- **Type Safety**: Erros detectados em tempo de compilação
- **Eliminação de Casts**: Código mais limpo
- **Reutilização**: Classes e métodos genéricos

### Enums
- **Type Safety**: Constantes tipadas
- **Funcionalidade**: Métodos e comportamentos
- **Legibilidade**: Código mais expressivo

### Annotations
- **Metadados**: Informações sobre o código
- **Frameworks**: Configuração por anotações
- **Processamento**: Geração de código

### Lambda e Streams
- **Concisão**: Código mais compacto
- **Funcional**: Paradigma funcional
- **Performance**: Operações paralelas

## 📚 Recursos Adicionais

- [Oracle Java Collections Tutorial](https://docs.oracle.com/javase/tutorial/collections/)
- [Effective Java - Joshua Bloch](https://www.oracle.com/java/technologies/javase/effectivejava.html)
- [Java 8 in Action](https://www.manning.com/books/java-8-in-action)

## 🔍 Exemplo Integrativo

```java
// Combinando múltiplos conceitos
public class ExemploIntegrativo {
    
    // Enum com comportamentos
    enum Status {
        ATIVO("Funcionando normalmente"),
        INATIVO("Temporariamente desabilitado");
        
        private final String descricao;
        Status(String descricao) { this.descricao = descricao; }
        public String getDescricao() { return descricao; }
    }
    
    // Classe genérica com annotations
    @Deprecated
    public static class Container<T> {
        private final List<T> items = new ArrayList<>();
        
        @SafeVarargs
        public final void addAll(T... items) {
            Collections.addAll(this.items, items);
        }
        
        // Stream com lambda
        public List<T> getFilteredItems(Predicate<T> filter) {
            return items.stream()
                       .filter(filter)
                       .collect(Collectors.toList());
        }
    }
}
```

---

**Anterior**: [Programação Orientada a Objetos](../02-programacao-orientada-objetos/) | **Próximo**: [Conceitos Avançados](../04-conceitos-avancados/)