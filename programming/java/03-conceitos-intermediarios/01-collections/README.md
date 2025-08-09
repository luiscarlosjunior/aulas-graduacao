# Collections Framework

O Collections Framework é uma arquitetura unificada para representar e manipular coleções de objetos em Java.

## 🎯 Objetivos

- Compreender a hierarquia das Collections
- Escolher a estrutura de dados adequada para cada situação
- Dominar operações de busca, inserção e remoção
- Otimizar performance com a escolha correta

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

### [ExemplosArrayList.java](ExemplosArrayList.java)
Demonstra uso completo de ArrayList com operações básicas e avançadas.

### [ExemplosHashSet.java](ExemplosHashSet.java)
Mostra como usar HashSet para eliminar duplicatas e operações de conjunto.

### [ExemplosHashMap.java](ExemplosHashMap.java)
Ilustra uso de HashMap para mapeamento chave-valor.

### [ComparacaoPerformance.java](ComparacaoPerformance.java)
Compara performance entre diferentes implementações.

### [TesteCollections.java](TesteCollections.java)
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

## 💡 Dicas Importantes

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

1. **Lista de Tarefas**: Implemente usando ArrayList com operações CRUD
2. **Agenda de Contatos**: Use HashMap para mapear nome->telefone
3. **Sistema de Votação**: Use Set para votos únicos
4. **Fila de Atendimento**: Implemente com PriorityQueue
5. **Cache LRU**: Implemente usando LinkedHashMap

## 🔗 Próximo Passo

Continue para [Generics](../02-generics/) para aprender sobre type safety em coleções.