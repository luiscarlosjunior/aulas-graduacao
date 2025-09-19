# Collections Framework

O Collections Framework é uma arquitetura unificada para representar e manipular coleções de objetos em Java. Introduzido no Java 1.2, este framework representa um dos melhores exemplos de como a **Programação Orientada a Objetos** pode ser aplicada para criar uma API elegante, extensível e eficiente.

## 🏛️ Collections Framework e Programação Orientada a Objetos

### Como o Collections Framework Exemplifica os Princípios da POO

O Collections Framework é um **exemplo magistral** de como os princípios da POO são aplicados na prática:

#### 1. **Encapsulamento** 🔒
```java
// As implementações internas são completamente ocultas
List<String> lista = new ArrayList<>(); // Usa array interno
List<String> lista2 = new LinkedList<>(); // Usa nós ligados

// O usuário não precisa saber como funciona internamente
lista.add("item");  // Funciona igual para ambas as implementações
```

#### 2. **Herança e Polimorfismo** 🎭
```java
// Hierarquia permite polimorfismo
Collection<String> col = new ArrayList<>(); // ArrayList É-UM Collection
col = new HashSet<>();                      // HashSet também É-UM Collection

// Mesmo código funciona com diferentes implementações
public void processarColecao(Collection<String> items) {
    for (String item : items) {
        System.out.println(item); // Funciona para ArrayList, HashSet, etc.
    }
}
```

#### 3. **Abstração** 🎨
```java
// Interfaces definem contratos claros
List<String> lista = new ArrayList<>();    // Lista ordenada com índices
Set<String> conjunto = new HashSet<>();    // Conjunto sem duplicatas
Map<String, Integer> mapa = new HashMap<>(); // Mapeamento chave-valor

// Cada abstração esconde complexidade e oferece operações específicas
```

#### 4. **Composição e Agregação** 🧩
```java
// Collections podem conter outras collections
List<List<String>> matrix = new ArrayList<>();
Map<String, List<Integer>> grupos = new HashMap<>();

// Padrão Composite: coleções são compostas por elementos
```

### Por Que Collections Framework é um Marco da POO?

| Aspecto | Como POO Ajudou | Benefício |
|---------|----------------|-----------|
| **Reutilização** | Herança de interfaces comuns | Uma vez aprendido, aplicável a todas |
| **Extensibilidade** | Novas implementações seguem contratos | Facilita adição de novas estruturas |
| **Polimorfismo** | Código genérico funciona com qualquer implementação | Flexibilidade sem modificar código |
| **Encapsulamento** | Detalhes internos protegidos | Mudanças internas não afetam usuários |

## 🎯 Objetivos

- Compreender como Collections Framework exemplifica POO
- Aplicar Collections em projetos orientados a objetos
- Escolher a estrutura de dados adequada para cada situação
- Dominar operações de busca, inserção e remoção
- Otimizar performance com a escolha correta
- Integrar Collections com classes personalizadas

## 📋 Hierarquia das Collections

```
Collection (Interface)
├── List (Interface)
│   ├── ArrayList (Class)
│   ├── LinkedList (Class)
│   └── Vector (Class)
├── Set (Interface)
│   ├── HashSet (Class)
│   ├── TreeSet (Class)
│   └── LinkedHashSet (Class)
└── Queue (Interface)
    ├── ArrayDeque (Class)
    └── PriorityQueue (Class)

Map (Interface) - Não herda de Collection
├── HashMap (Class)
├── TreeMap (Class)
└── LinkedHashMap (Class)
```

## 🖥️ Exemplos

### Exemplos Básicos
#### [ExemplosArrayList.java](ExemplosArrayList.java)
Demonstra uso completo de ArrayList com operações básicas e avançadas.

#### [ExemplosHashMap.java](ExemplosHashMap.java)
Ilustra uso de HashMap para mapeamento chave-valor.

### Exemplos com Programação Orientada a Objetos

#### [ColecoesPessoas.java](ColecoesPessoas.java)
Demonstra como usar Collections com objetos personalizados, aplicando conceitos de POO:
- Armazenamento de objetos da classe `Pessoa` em ArrayList
- Uso de comparadores para ordenação personalizada
- Aplicação de equals() e hashCode() em HashSet
- Mapeamento de objetos com HashMap

#### [SistemaEscolar.java](SistemaEscolar.java)
Sistema completo integrando Collections com múltiplas classes:
- Gerenciamento de alunos, professores e disciplinas
- Relacionamentos entre objetos usando Collections
- Padrões de design aplicados com Collections

#### [ComparacaoPerformance.java](ComparacaoPerformance.java)
Compara performance entre diferentes implementações.

#### [TesteCollections.java](TesteCollections.java)
Programa principal demonstrando todos os tipos de coleções.

## 🚀 Como Executar

```bash
javac *.java
java TesteCollections
```

## 📊 Comparação de Performance

| Operação | ArrayList | LinkedList | HashSet | HashMap |
|----------|-----------|------------|---------|---------|
| Busca    | O(1)      | O(n)       | O(1)    | O(1)    |
| Inserção | O(1)*     | O(1)       | O(1)    | O(1)    |
| Remoção  | O(n)      | O(1)**     | O(1)    | O(1)    |

*Amortizado, O(n) no pior caso (resize)
**Se tiver referência para o nó

## ✅ Quando Usar Cada Tipo

### List
- **ArrayList**: Acesso frequente por índice, poucas inserções/remoções no meio
- **LinkedList**: Muitas inserções/remoções, especialmente no início/fim
- **Vector**: Quando precisa de thread-safety (use Collections.synchronizedList() em ArrayList)

### Set
- **HashSet**: Elementos únicos, sem ordem específica, melhor performance
- **TreeSet**: Elementos únicos ordenados, implementa NavigableSet
- **LinkedHashSet**: Elementos únicos mantendo ordem de inserção

### Map
- **HashMap**: Mapeamento chave-valor, melhor performance, sem ordem
- **TreeMap**: Mapeamento ordenado por chave, implementa NavigableMap
- **LinkedHashMap**: Mapeamento mantendo ordem de inserção

### Queue
- **ArrayDeque**: Fila/pilha geral, melhor que Stack e LinkedList
- **PriorityQueue**: Fila com prioridade (heap)

## 🎨 Collections Framework e Design Patterns

### Padrões de Design Implementados no Framework

#### 1. **Iterator Pattern** 🔄
```java
// Permite percorrer coleções sem expor sua estrutura interna
List<String> lista = Arrays.asList("A", "B", "C");
Iterator<String> it = lista.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}

// Enhanced for usa Iterator internamente
for (String item : lista) {
    System.out.println(item);
}
```

#### 2. **Strategy Pattern** 🎯
```java
// Diferentes algoritmos de ordenação usando Comparator
List<Pessoa> pessoas = new ArrayList<>();
pessoas.add(new Pessoa("Ana", 25));
pessoas.add(new Pessoa("Bruno", 30));

// Estratégia 1: Ordenar por nome
pessoas.sort(Comparator.comparing(Pessoa::getNome));

// Estratégia 2: Ordenar por idade
pessoas.sort(Comparator.comparing(Pessoa::getIdade));

// Estratégia 3: Ordenar por critério complexo
pessoas.sort(Comparator.comparing(Pessoa::getNome)
                      .thenComparing(Pessoa::getIdade));
```

#### 3. **Factory Pattern** 🏭
```java
// Collections.unmodifiableList() cria versões imutáveis
List<String> original = new ArrayList<>();
List<String> imutavel = Collections.unmodifiableList(original);

// Collections.synchronizedList() cria versões thread-safe
List<String> threadSafe = Collections.synchronizedList(new ArrayList<>());
```

#### 4. **Template Method Pattern** 📋
```java
// AbstractList implementa operações comuns
public class MinhaLista<E> extends AbstractList<E> {
    private List<E> backing = new ArrayList<>();
    
    @Override
    public E get(int index) {
        return backing.get(index);
    }
    
    @Override
    public int size() {
        return backing.size();
    }
    
    // AbstractList fornece implementações padrão para
    // add(), remove(), iterator(), etc.
}
```

### 🏗️ Melhores Práticas: Collections + POO

#### 1. **Programe Para Interfaces, Não Implementações**
```java
// ❌ Ruim: Liga o código a implementação específica
ArrayList<Produto> produtos = new ArrayList<>();

// ✅ Bom: Permite mudança de implementação
List<Produto> produtos = new ArrayList<>();
// Pode mudar para LinkedList sem afetar o resto do código
```

#### 2. **Implemente equals() e hashCode() Corretamente**
```java
public class Produto {
    private String codigo;
    private String nome;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produto produto = (Produto) obj;
        return Objects.equals(codigo, produto.codigo);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
```

#### 3. **Use Generics Para Type Safety**
```java
// ❌ Raw types - perigoso
List lista = new ArrayList();
lista.add("String");
lista.add(123); // Mistura tipos!

// ✅ Com generics - type safe
List<String> lista = new ArrayList<>();
// lista.add(123); // Erro de compilação!
```

#### 4. **Considere Imutabilidade**
```java
public class ProdutoImmutable {
    private final String nome;
    private final double preco;
    private final List<String> categorias;
    
    public ProdutoImmutable(String nome, double preco, List<String> categorias) {
        this.nome = nome;
        this.preco = preco;
        // Cria cópia defensiva
        this.categorias = new ArrayList<>(categorias);
    }
    
    public List<String> getCategorias() {
        // Retorna cópia para manter imutabilidade
        return Collections.unmodifiableList(categorias);
    }
}
```

## 🔍 Collections e Relacionamentos de Objetos

### Modelando Relacionamentos do Mundo Real

#### 1. **Relacionamento Um-para-Muitos** (1:N)
```java
public class Professor {
    private String nome;
    private List<Disciplina> disciplinas; // Um professor pode ter muitas disciplinas
    
    public void adicionarDisciplina(Disciplina disciplina) {
        if (disciplinas == null) {
            disciplinas = new ArrayList<>();
        }
        disciplinas.add(disciplina);
        disciplina.setProfessor(this); // Bidirecional
    }
}

public class Disciplina {
    private String nome;
    private Professor professor; // Muitas disciplinas podem ter um professor
}
```

#### 2. **Relacionamento Muitos-para-Muitos** (N:M)
```java
public class Aluno {
    private String nome;
    private Set<Disciplina> disciplinas; // Aluno pode cursar muitas disciplinas
    
    public void matricularDisciplina(Disciplina disciplina) {
        if (disciplinas == null) {
            disciplinas = new HashSet<>();
        }
        disciplinas.add(disciplina);
        disciplina.adicionarAluno(this); // Bidirecional
    }
}

public class Disciplina {
    private String nome;
    private Set<Aluno> alunos; // Disciplina pode ter muitos alunos
    
    public void adicionarAluno(Aluno aluno) {
        if (alunos == null) {
            alunos = new HashSet<>();
        }
        alunos.add(aluno);
    }
}
```

#### 3. **Composição vs Agregação**
```java
// Composição: Pedido "possui" completamente os Itens
public class Pedido {
    private List<ItemPedido> itens; // Se pedido for deletado, itens também são
    
    public void adicionarItem(Produto produto, int quantidade) {
        if (itens == null) {
            itens = new ArrayList<>();
        }
        itens.add(new ItemPedido(produto, quantidade)); // Cria novo item
    }
}

// Agregação: Turma "usa" Alunos que existem independentemente
public class Turma {
    private List<Aluno> alunos; // Se turma for deletada, alunos continuam existindo
    
    public void adicionarAluno(Aluno aluno) {
        if (alunos == null) {
            alunos = new ArrayList<>();
        }
        alunos.add(aluno); // Referencia aluno existente
    }
}
```

## 💡 Dicas Importantes

### Collections Framework e POO

#### **Princípio da Responsabilidade Única aplicado a Collections**
```java
// ❌ Classe fazendo muitas coisas
public class ProdutoManager {
    private List<Produto> produtos;
    
    public void salvarNoBanco() { /* ... */ }
    public void enviarEmail() { /* ... */ }
    public void calcularImpostos() { /* ... */ }
}

// ✅ Cada classe tem uma responsabilidade
public class ProdutoRepository {
    private List<Produto> produtos;
    // Apenas gerencia a coleção
}

public class EmailService {
    // Apenas envia emails
}
```

### Capacidade Inicial
```java
// Especificar capacidade inicial evita redimensionamentos
List<String> lista = new ArrayList<>(1000);
Map<String, Integer> mapa = new HashMap<>(100);
```

### Thread Safety
```java
// Collections sincronizadas
List<String> listaSincronizada = Collections.synchronizedList(new ArrayList<>());
Map<String, String> mapaSincronizado = Collections.synchronizedMap(new HashMap<>());

// Para melhor performance em cenários concorrentes, use:
// ConcurrentHashMap, CopyOnWriteArrayList
```

### Operações Úteis
```java
// Ordenação
Collections.sort(lista);
Collections.reverse(lista);

// Busca
int index = Collections.binarySearch(lista, "elemento");

// Min/Max
String min = Collections.min(lista);
String max = Collections.max(lista);

// Conversões
String[] array = lista.toArray(new String[0]);
List<String> novaLista = Arrays.asList(array);
```

## 📝 Exercícios

### Exercícios Básicos
1. **Lista de Tarefas**: Implemente usando ArrayList com operações CRUD
2. **Agenda de Contatos**: Use HashMap para mapear nome->telefone
3. **Sistema de Votação**: Use Set para votos únicos
4. **Fila de Atendimento**: Implemente com PriorityQueue
5. **Cache LRU**: Implemente usando LinkedHashMap

### Exercícios Integrando Collections com POO

#### 🎓 **Exercício: Sistema de Biblioteca**
**Objetivo**: Criar um sistema que demonstre Collections + POO
```java
// Classes a implementar:
class Livro {
    private String isbn, titulo, autor;
    private boolean disponivel;
    // Implementar equals(), hashCode(), toString()
}

class Biblioteca {
    private List<Livro> acervo;
    private Map<String, Livro> catalogoPorISBN;
    private Set<String> autores;
    // Métodos: adicionarLivro(), buscarPorAutor(), emprestar(), devolver()
}
```

#### 🏢 **Exercício: Sistema de Funcionários**
**Objetivo**: Gerenciar funcionários usando diferentes Collections
```java
class Funcionario {
    private String nome, cpf, cargo;
    private double salario;
    // Implementar Comparable<Funcionario> para ordenação
}

class Empresa {
    private List<Funcionario> funcionarios;           // Lista geral
    private Map<String, Funcionario> funcionariosCPF; // Busca rápida
    private Map<String, List<Funcionario>> porCargo;  // Agrupamento
    // Métodos: contratar(), demitir(), promover(), relatorios()
}
```

#### 🎮 **Exercício: Jogo RPG**
**Objetivo**: Sistema de inventário e personagens
```java
class Item {
    private String nome, tipo;
    private int valor, peso;
}

class Personagem {
    private String nome, classe;
    private List<Item> inventario;        // Itens do personagem
    private Set<String> habilidades;      // Habilidades únicas
    private Map<String, Integer> stats;   // Força, agilidade, etc.
}

class Guilda {
    private List<Personagem> membros;
    private Map<String, Item> tesouro;    // Tesouro compartilhado
}
```

### 🏆 **Desafio Avançado: Sistema de E-commerce**
Crie um sistema completo integrando:
- **Produtos**: List, Map para categorias
- **Clientes**: Map para busca rápida, histórico de compras
- **Pedidos**: List de itens, relacionamentos entre objetos
- **Estoque**: Controle usando Collections

**Requisitos**:
- Implementar equals() e hashCode() corretamente
- Usar Comparator para diferentes ordenações
- Aplicar padrões de design (Observer, Strategy)
- Demonstrar polimorfismo com Collections

## 🔗 Próximo Passo

Continue para [Generics](../02-generics/) para aprender sobre type safety em coleções.

## 🎓 Conexão com Programação Orientada a Objetos

### Revisão dos Conceitos de POO Aplicados

Se você chegou até aqui, já viu como o Collections Framework é um **exemplo perfeito** da aplicação prática dos princípios de POO:

#### ✅ **Conceitos Revisados**
- **Encapsulamento**: Implementações internas ocultas
- **Herança**: Hierarquia de interfaces e classes
- **Polimorfismo**: Mesmo código funciona com diferentes implementações
- **Abstração**: Interfaces claras que escondem complexidade

#### 🔄 **Volte aos Fundamentos Se Necessário**
Se algum conceito de OOP não ficou claro durante este estudo:
- Revise [Classes e Objetos](../../02-programacao-orientada-objetos/01-classes-objetos/)
- Reforce [Encapsulamento](../../02-programacao-orientada-objetos/02-encapsulamento/)
- Pratique [Herança](../../02-programacao-orientada-objetos/03-heranca/)
- Domine [Polimorfismo](../../02-programacao-orientada-objetos/04-polimorfismo/)

#### 🚀 **Próximos Desafios**
1. **Generics**: Aprofunde type safety (próximo módulo)
2. **Streams**: Programação funcional com Collections
3. **Design Patterns**: Aplique padrões usando Collections
4. **Projeto Integrador**: Combine todos os conceitos

### 📚 **Recursos Complementares**

#### Documentação Oficial
- [Oracle Collections Trail](https://docs.oracle.com/javase/tutorial/collections/)
- [Java API Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/)

#### Livros Recomendados
- **"Effective Java"** by Joshua Bloch - Capítulos sobre Collections
- **"Java: The Complete Reference"** by Herbert Schildt
- **"Head First Design Patterns"** - Padrões aplicados a Collections

---

🎯 **Lembre-se**: Collections Framework não é apenas sobre armazenar dados. É sobre **aplicar POO** para criar código mais limpo, reutilizável e maintível!