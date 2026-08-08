# Generics em Java

Generics representam um dos recursos mais fundamentais e poderosos introduzidos no Java 5 (JDK 1.5), proporcionando um mecanismo robusto para criar classes, interfaces e métodos que operam com tipos parametrizados. Este recurso revolucionou a forma como escrevemos código em Java, trazendo **type safety em tempo de compilação**, eliminando a necessidade de type casting explícito e prevenindo erros de `ClassCastException` em tempo de execução.

A programação genérica permite a criação de componentes altamente reutilizáveis que podem trabalhar com diferentes tipos de objetos mantendo a segurança de tipos. Antes dos Generics, as coleções armazenavam referências a `Object`, exigindo casting manual e possibilitando erros em tempo de execução. Com Generics, o compilador pode verificar a correção dos tipos e inserir casts automaticamente, tornando o código mais seguro e expressivo.

## 🎯 Objetivos de Aprendizagem

Este módulo tem como objetivos principais:

- **Compreender Type Safety**: Entender como Generics fornecem segurança de tipos em tempo de compilação, prevenindo erros que só seriam detectados em runtime
- **Dominar Classes e Métodos Genéricos**: Aprender a criar classes e métodos que declaram type parameters e podem trabalhar com qualquer tipo de referência
- **Aplicar Bounded Type Parameters**: Utilizar restrições de tipo para limitar os tipos que podem ser usados como argumentos de tipo
- **Trabalhar com Wildcards**: Compreender e aplicar wildcards (curingas) para maior flexibilidade: unbounded `<?>`, upper bounded `<? extends T>` e lower bounded `<? super T>`
- **Entender Type Erasure**: Compreender o mecanismo interno pelo qual o compilador Java implementa Generics e suas limitações
- **Aplicar Generics em Contextos Reais**: Desenvolver habilidade para usar Generics em padrões de design e arquiteturas de software profissionais

## 📋 Conceitos Fundamentais

### O Problema Antes dos Generics

Antes do Java 5, trabalhar com coleções requeria casting manual e não havia verificação de tipos em tempo de compilação:

```java
// Código pré-Java 5 (sem Generics)
List lista = new ArrayList();
lista.add("String");
lista.add(Integer.valueOf(123)); // Sem erro de compilação!

String texto = (String) lista.get(0); // Cast manual necessário
String texto2 = (String) lista.get(1); // ClassCastException em runtime! 💥
```

**Problemas**:
- ❌ Sem verificação de tipos em tempo de compilação
- ❌ Necessidade de casting manual em toda leitura
- ❌ Possibilidade de `ClassCastException` em runtime
- ❌ Código verboso e propenso a erros

### A Solução com Generics

Com Generics, o compilador garante type safety:

```java
// Código moderno com Generics
List<String> lista = new ArrayList<>();
lista.add("String");
// lista.add(123); // ✅ Erro de compilação - tipo incompatível!

String texto = lista.get(0); // ✅ Sem casting necessário
// Impossível obter ClassCastException relacionada ao tipo da lista
```

**Benefícios**:
- ✅ Type safety verificada em tempo de compilação
- ✅ Eliminação de casts explícitos
- ✅ Código mais legível e expressivo
- ✅ Detecção precoce de erros de tipo
- ✅ Melhor documentação através dos tipos

## 🏗️ Componentes de Generics

### 1. Type Parameters (Parâmetros de Tipo)

Type parameters são variáveis de tipo que representam tipos a serem especificados posteriormente:

**Convenções de Nomenclatura**:
- `T` - Type (tipo genérico geral)
- `E` - Element (elemento de coleção)
- `K` - Key (chave de mapa)
- `V` - Value (valor de mapa)
- `N` - Number (número)
- `R` - Return (tipo de retorno)

### 2. Classes Genéricas

Classes que declaram um ou mais type parameters:

```java
public class Caixa<T> {
    private T conteudo;
    
    public void guardar(T item) {
        this.conteudo = item;
    }
    
    public T recuperar() {
        return conteudo;
    }
}

// Uso
Caixa<String> caixaTexto = new Caixa<>();
caixaTexto.guardar("Mensagem");
String msg = caixaTexto.recuperar(); // Sem casting!

Caixa<Integer> caixaNumero = new Caixa<>();
caixaNumero.guardar(42);
Integer num = caixaNumero.recuperar(); // Type safe!
```

### 3. Métodos Genéricos

Métodos que declaram seus próprios type parameters:

```java
public class Utilitarios {
    // Método genérico - note o <T> antes do tipo de retorno
    public static <T> void imprimir(T elemento) {
        System.out.println(elemento);
    }
    
    // Método genérico com retorno
    public static <T> T obterPrimeiro(List<T> lista) {
        return lista.isEmpty() ? null : lista.get(0);
    }
}

// Uso - tipo inferido automaticamente
Utilitarios.imprimir("Texto");
Utilitarios.imprimir(123);
String primeiro = Utilitarios.obterPrimeiro(Arrays.asList("A", "B", "C"));
```

### 4. Bounded Type Parameters (Parâmetros de Tipo Limitados)

Restringe os tipos que podem ser usados como argumentos:

#### Upper Bound (extends)
```java
// T deve ser Number ou subtipo de Number
public class CalculadoraNumerica<T extends Number> {
    public double somar(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }
}

// Válido
CalculadoraNumerica<Integer> calcInt = new CalculadoraNumerica<>();
CalculadoraNumerica<Double> calcDouble = new CalculadoraNumerica<>();

// Inválido - erro de compilação
// CalculadoraNumerica<String> calcString = new CalculadoraNumerica<>();
```

#### Multiple Bounds
```java
// T deve implementar Comparable E Serializable
public class Processador<T extends Comparable<T> & Serializable> {
    public T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
}
```

### 5. Wildcards (Curingas)

Wildcards representam tipos desconhecidos e proporcionam flexibilidade adicional:

#### Unbounded Wildcard `<?>`
```java
// Aceita lista de qualquer tipo
public static void imprimirLista(List<?> lista) {
    for (Object elemento : lista) {
        System.out.println(elemento);
    }
}

// Funciona com qualquer tipo
imprimirLista(Arrays.asList(1, 2, 3));
imprimirLista(Arrays.asList("A", "B", "C"));
```

#### Upper Bounded Wildcard `<? extends T>` - Covariância
```java
// Aceita lista de Number ou qualquer subtipo (Integer, Double, etc.)
public static double somar(List<? extends Number> numeros) {
    double soma = 0;
    for (Number num : numeros) {
        soma += num.doubleValue();
    }
    return soma;
}

// Funciona com subtipos de Number
somar(Arrays.asList(1, 2, 3));           // List<Integer>
somar(Arrays.asList(1.5, 2.5, 3.5));     // List<Double>
somar(Arrays.asList(1L, 2L, 3L));        // List<Long>
```

**Regra**: Pode **ler** como `T`, mas **não pode escrever** (apenas null)

#### Lower Bounded Wildcard `<? super T>` - Contravariância
```java
// Aceita lista de Integer ou qualquer supertipo (Number, Object)
public static void adicionarInteiros(List<? super Integer> lista) {
    lista.add(1);
    lista.add(2);
    lista.add(3);
}

List<Integer> inteiros = new ArrayList<>();
List<Number> numeros = new ArrayList<>();
List<Object> objetos = new ArrayList<>();

adicionarInteiros(inteiros); // ✅
adicionarInteiros(numeros);  // ✅
adicionarInteiros(objetos);  // ✅
```

**Regra**: Pode **escrever** `T`, mas **leitura** retorna apenas `Object`

### 6. PECS - Producer Extends, Consumer Super

Princípio fundamental para usar wildcards corretamente:

- **Producer Extends**: Se um tipo **produz** (retorna) valores de T, use `<? extends T>`
- **Consumer Super**: Se um tipo **consome** (recebe) valores de T, use `<? super T>`

```java
// Produtor: fornece elementos - usa extends
public static <T> void copiar(List<? extends T> origem, 
                               List<? super T> destino) {
    for (T elemento : origem) {  // Lê de origem (producer)
        destino.add(elemento);   // Escreve em destino (consumer)
    }
}

List<Integer> inteiros = Arrays.asList(1, 2, 3);
List<Number> numeros = new ArrayList<>();
copiar(inteiros, numeros); // ✅ Integer extends Number
```

### 7. Type Erasure (Apagamento de Tipos)

Generics são implementados através de **type erasure**: informações de tipo são removidas em tempo de compilação para manter compatibilidade binária com código pré-Generics.

#### Como Funciona
```java
// Código fonte
public class Caixa<T> {
    private T item;
    public void set(T item) { this.item = item; }
    public T get() { return item; }
}

// Após type erasure (bytecode)
public class Caixa {
    private Object item;
    public void set(Object item) { this.item = item; }
    public Object get() { return item; }
}

// Com bounded type
public class CaixaNumerica<T extends Number> {
    private T item;
}

// Após type erasure
public class CaixaNumerica {
    private Number item; // Substituído pelo bound
}
```

#### Limitações Devido ao Type Erasure

1. **Não pode criar instâncias de tipo genérico**:
```java
// ❌ Erro de compilação
T obj = new T();
```

2. **Não pode criar arrays de tipos parametrizados**:
```java
// ❌ Erro de compilação
T[] array = new T[10];
List<String>[] array = new List<String>[10];
```

3. **Não pode usar instanceof com tipos parametrizados**:
```java
// ❌ Erro de compilação
if (obj instanceof List<String>) { }

// ✅ Válido
if (obj instanceof List<?>) { }
```

4. **Não pode ter métodos estáticos genéricos que usem type parameter da classe**:
```java
public class Exemplo<T> {
    // ❌ Erro - tipo estático não pode referenciar T da classe
    // public static T metodo() { }
    
    // ✅ Válido - método genérico com próprio type parameter
    public static <U> U metodo(U param) { return param; }
}
```

5. **Não pode usar tipos primitivos como argumentos de tipo**:
```java
// ❌ Erro - primitivos não são permitidos
// List<int> lista = new ArrayList<>();

// ✅ Use wrapper classes
List<Integer> lista = new ArrayList<>();
```

## 🎨 Generics e Design Patterns

Generics são fundamentais para implementar padrões de design de forma elegante e type-safe:

### Repository Pattern
```java
public interface Repository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void delete(T entity);
}

public class UserRepository implements Repository<User, Long> {
    // Implementação específica para User
}
```

### Factory Pattern
```java
public interface Factory<T> {
    T create();
}

public class ProductFactory implements Factory<Product> {
    @Override
    public Product create() {
        return new Product();
    }
}
```

### Builder Pattern
```java
public class Builder<T> {
    private T object;
    
    public Builder<T> set(Consumer<T> setter) {
        setter.accept(object);
        return this;
    }
    
    public T build() {
        return object;
    }
}
```

## 💡 Melhores Práticas

### ✅ DO - Faça

1. **Use Generics para type safety**:
```java
// ✅ Bom
List<String> nomes = new ArrayList<>();

// ❌ Evite raw types
List nomes = new ArrayList();
```

2. **Prefira interfaces genéricas em vez de implementações**:
```java
// ✅ Bom
List<String> lista = new ArrayList<>();
Map<String, Integer> mapa = new HashMap<>();

// ❌ Evite
ArrayList<String> lista = new ArrayList<>();
HashMap<String, Integer> mapa = new HashMap<>();
```

3. **Use bounded types quando apropriado**:
```java
// ✅ Bom - limita a tipos que fazem sentido
public <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) > 0 ? a : b;
}
```

4. **Aplique o princípio PECS**:
```java
// ✅ Bom - flexibilidade máxima
public void processar(List<? extends Produto> produtos) { }
public void adicionar(List<? super Produto> destino) { }
```

5. **Forneça type witnesses quando necessário**:
```java
// Se inferência falhar, especifique o tipo
List<String> lista = Collections.<String>emptyList();
```

### ❌ DON'T - Evite

1. **Não use raw types** (exceto em casos de compatibilidade legada):
```java
// ❌ Evite
List lista = new ArrayList();

// ✅ Use Generics
List<Object> lista = new ArrayList<>();
// ou
List<?> lista = new ArrayList<>();
```

2. **Não ignore avisos de unchecked**:
```java
// ❌ Evite suprimir sem entender
@SuppressWarnings("unchecked")
List<String> lista = (List<String>) obj;

// ✅ Use alternativas type-safe
```

3. **Não misture tipos parametrizados com raw types**:
```java
// ❌ Evite
List<String> strings = new ArrayList();

// ✅ Use Generics consistentemente
List<String> strings = new ArrayList<>();
```

4. **Não crie arrays de tipos parametrizados**:
```java
// ❌ Erro de compilação
List<String>[] array = new List<String>[10];

// ✅ Alternativas
List<List<String>> lista = new ArrayList<>();
// ou
@SuppressWarnings("unchecked")
List<String>[] array = (List<String>[]) new List<?>[10];
```

## 📊 Comparação: Com e Sem Generics

| Aspecto | Sem Generics | Com Generics |
|---------|--------------|--------------|
| **Type Safety** | Runtime (ClassCastException) | Compile time |
| **Casting** | Manual e frequente | Automático |
| **Legibilidade** | Menor (casts em todo lugar) | Maior (intenção clara) |
| **Manutenção** | Mais difícil | Mais fácil |
| **Detecção de Erros** | Tardia (runtime) | Precoce (compile time) |
| **Performance** | Mesma (após erasure) | Mesma |
| **Reutilização** | Limitada | Máxima |

## 🖥️ Exemplos Práticos

Este módulo inclui exemplos progressivos que demonstram todos os aspectos de Generics:

### Exemplos Básicos

#### [ClasseGenericaBasica.java](ClasseGenericaBasica.java)
Introdução a classes genéricas com exemplo de `Caixa<T>` que pode armazenar qualquer tipo de objeto de forma type-safe.

#### [MetodoGenerico.java](MetodoGenerico.java)
Demonstra criação e uso de métodos genéricos, incluindo inferência de tipos e métodos utilitários genéricos.

#### [ParLista.java](ParLista.java)
Exemplo de classe genérica com múltiplos type parameters (`Par<K, V>`), similar ao conceito de Map.Entry.

### Exemplos Intermediários

#### [BoundedTypeParameters.java](BoundedTypeParameters.java)
Explora bounded type parameters com upper bounds, múltiplos bounds e casos de uso práticos com Number e Comparable.

#### [GenericsComColecoes.java](GenericsComColecoes.java)
Integração de Generics com Collections Framework, demonstrando uso correto de List, Set e Map genéricos.

#### [WildcardsExemplos.java](WildcardsExemplos.java)
Cobertura completa de wildcards: unbounded `<?>`, upper bounded `<? extends T>` e lower bounded `<? super T>`, com exemplos do princípio PECS.

### Exemplos Avançados

#### [TypeErasureDemo.java](TypeErasureDemo.java)
Demonstração de type erasure, suas implicações e limitações, incluindo reflexão e bridge methods.

#### [InterfaceGenerica.java](InterfaceGenerica.java)
Criação de interfaces genéricas e implementação por classes concretas, demonstrando Repository pattern.

#### [GenericosAvancados.java](GenericosAvancados.java)
Tópicos avançados incluindo recursive type bounds, self-referential generics e técnicas avançadas de design.

### Exemplo Prático Real

#### [SistemaRepositorio.java](SistemaRepositorio.java)
Sistema completo de repositório genérico aplicando padrões de design profissionais, demonstrando Repository e Factory patterns com Generics.

## 🚀 Como Executar

Todos os exemplos são auto-contidos e podem ser executados individualmente:

```bash
# Navegar para a pasta
cd programming/java/03-conceitos-intermediarios/02-generics/

# Compilar um exemplo específico
javac ClasseGenericaBasica.java

# Executar
java ClasseGenericaBasica

# Ou compilar e executar todos de uma vez
javac *.java
java ClasseGenericaBasica
java MetodoGenerico
java ParLista
# ... etc
```

## 📖 Roteiro de Estudo Recomendado

Para maximizar o aprendizado, siga esta sequência:

### Fase 1 - Fundamentos (Essencial)
1. Leia a seção "O Problema Antes dos Generics" para entender o contexto histórico
2. Estude e execute **ClasseGenericaBasica.java** - compreenda type parameters básicos
3. Pratique com **MetodoGenerico.java** - domine métodos genéricos
4. Explore **ParLista.java** - entenda múltiplos type parameters

### Fase 2 - Intermediário (Importante)
5. Estude **BoundedTypeParameters.java** - aprenda a restringir tipos
6. Execute **GenericsComColecoes.java** - integre Generics com Collections
7. **Exercício**: Crie sua própria classe genérica com bounded types

### Fase 3 - Avançado (Crítico)
8. Domine **WildcardsExemplos.java** - compreenda todos os tipos de wildcards
9. Aprenda o princípio **PECS** (Producer Extends, Consumer Super)
10. Pratique escolher entre `<? extends T>` e `<? super T>`

### Fase 4 - Profissional (Especialização)
11. Estude **TypeErasureDemo.java** - entenda as limitações
12. Explore **InterfaceGenerica.java** - padrões de design
13. Analise **GenericosAvancados.java** - técnicas avançadas
14. Implemente **SistemaRepositorio.java** - caso real completo

### Fase 5 - Prática e Consolidação
15. Resolva os exercícios propostos
16. Crie um projeto integrando múltiplos conceitos
17. Refatore código existente para usar Generics
18. Estude código de bibliotecas populares (Collections API, Spring Framework)

## 💻 Exercícios Práticos

### Exercícios Básicos

#### 1. Caixa de Ferramentas Genérica
Crie uma classe `Estoque<T>` que:
- Armazene múltiplos itens do tipo T
- Tenha métodos adicionar, remover, buscar
- Mantenha controle de quantidade

#### 2. Comparador Genérico
Implemente um método genérico que:
- Compare dois objetos do mesmo tipo
- Retorne o maior segundo um critério
- Funcione com qualquer tipo Comparable

#### 3. Conversor de Coleções
Crie um método genérico que:
- Converta List para Set
- Converta Set para List
- Preserve o tipo genérico

### Exercícios Intermediários

#### 4. Sistema de Cache
Implemente um cache genérico com:
- Chave e valor genéricos (K, V)
- Limite de tamanho
- Política LRU (Least Recently Used)
- Operações type-safe

#### 5. Processador de Números
Crie uma classe que:
- Use bounded types com Number
- Implemente operações matemáticas
- Funcione com Integer, Double, Long, etc.
- Mantenha type safety

#### 6. Validador Genérico
Desenvolva um framework de validação:
```java
interface Validator<T> {
    boolean isValid(T value);
    String getErrorMessage();
}

class EmailValidator implements Validator<String> { }
class AgeValidator implements Validator<Integer> { }
```

### Exercícios Avançados

#### 7. Repository Pattern Completo
Implemente um sistema com:
```java
interface Repository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    boolean existsById(ID id);
}

// Implementações para User, Product, Order
```

#### 8. Builder Genérico
Crie um Builder pattern genérico:
- Aceite qualquer tipo de objeto
- Permita configuração fluente
- Valide antes de construir
- Use bounded types apropriados

#### 9. Pipeline de Processamento
Desenvolva um pipeline funcional:
```java
class Pipeline<T> {
    Pipeline<T> filter(Predicate<? super T> predicate);
    <R> Pipeline<R> map(Function<? super T, ? extends R> mapper);
    List<T> collect();
}
```

### Desafio Final: Sistema de Gerenciamento Acadêmico

Crie um sistema completo que integre todos os conceitos:

**Requisitos**:
1. **Entidades**: Aluno, Professor, Disciplina, Nota
2. **Repositórios**: Genéricos para cada entidade
3. **Serviços**: Lógica de negócio com Generics
4. **Validadores**: Framework de validação genérico
5. **Consultas**: Sistema de queries type-safe

**Estrutura Sugerida**:
```java
// Entidades base
interface Entity<ID> {
    ID getId();
    void setId(ID id);
}

// Repository genérico
interface Repository<T extends Entity<ID>, ID> {
    // CRUD operations
}

// Service genérico
abstract class BaseService<T extends Entity<ID>, ID> {
    protected Repository<T, ID> repository;
    // Operações comuns
}

// Implementações específicas
class Aluno implements Entity<Long> { }
class AlunoRepository implements Repository<Aluno, Long> { }
class AlunoService extends BaseService<Aluno, Long> { }
```

**Critérios de Avaliação**:
- ✅ Type safety completa
- ✅ Uso correto de bounded types
- ✅ Aplicação apropriada de wildcards
- ✅ Implementação de padrões de design
- ✅ Código limpo e reutilizável
- ✅ Tratamento de edge cases

## 🔍 Troubleshooting: Problemas Comuns

### Erro: "Cannot create a generic array"
```java
// ❌ Problema
T[] array = new T[10];

// ✅ Solução 1: Use ArrayList
List<T> lista = new ArrayList<>();

// ✅ Solução 2: Use reflection (avançado)
@SuppressWarnings("unchecked")
T[] array = (T[]) Array.newInstance(clazz, size);
```

### Erro: "Type mismatch"
```java
// ❌ Problema
List<Object> objetos = new ArrayList<String>(); // Erro!

// ✅ Solução: Use wildcards
List<?> objetos = new ArrayList<String>();
// ou especifique o tipo correto
List<String> strings = new ArrayList<>();
```

### Warning: "Unchecked cast"
```java
// ⚠️ Warning
@SuppressWarnings("unchecked")
List<String> strings = (List<String>) obj;

// ✅ Melhor: Evite casting de tipos parametrizados
// Use métodos que retornem o tipo correto
```

### Erro: "Incompatible types"
```java
// ❌ Problema
List<? extends Number> numeros = new ArrayList<Integer>();
numeros.add(Integer.valueOf(1)); // Erro!

// ✅ Solução: Entenda PECS
// Producer Extends: pode LER, não pode ESCREVER
Number n = numeros.get(0); // ✅ OK
```

## 📚 Recursos Adicionais

### Documentação Oficial
- [Oracle Java Tutorials - Generics](https://docs.oracle.com/javase/tutorial/java/generics/)
- [Java Language Specification - Generics](https://docs.oracle.com/javase/specs/jls/se17/html/jls-4.html#jls-4.5)
- [Java API Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/)

### Livros Recomendados
- **"Effective Java" - Joshua Bloch**: Capítulos 26-33 sobre Generics (essencial!)
- **"Java Generics and Collections" - Maurice Naftalin**: Referência definitiva sobre Generics
- **"Core Java Volume I" - Cay Horstmann**: Capítulo sobre Generics bem explicado

### Artigos e Tutoriais
- [Baeldung - Java Generics](https://www.baeldung.com/java-generics)
- [Oracle - Type Erasure](https://docs.oracle.com/javase/tutorial/java/generics/erasure.html)
- [IBM Developer - Understanding Generics](https://developer.ibm.com/articles/j-jtp01255/)

### Ferramentas
- **IntelliJ IDEA**: Inspeções e refatorações para Generics
- **Eclipse**: Suporte completo a Generics e inferência de tipos
- **SonarQube**: Análise de código para uso correto de Generics

## 🎓 Conexão com Outros Conceitos

### Pré-requisitos
Antes de estudar Generics, você deve dominar:
- [Classes e Objetos](../../03-POO/02-classes-e-objetos/)
- [Herança e Polimorfismo](../../03-POO/04-heranca/)
- [Interfaces](../../03-POO/07-interfaces/)
- [Collections Framework](../01-collections/)

### Próximos Passos
Após dominar Generics, você estará preparado para:
- [Enumerações](../03-enum/) - Tipos especiais que usam Generics internamente
- [Annotations](../04-annotations/) - Metadados que podem ser genéricos
- [Lambda e Streams](../05-lambda-streams/) - Programação funcional com types genéricos
- Design Patterns avançados com Generics

### Integração
Generics são fundamentais para:
- **Collections API**: List<T>, Set<T>, Map<K,V>
- **Stream API**: Stream<T>, Optional<T>
- **Frameworks**: Spring (dependency injection), Hibernate (persistence)
- **Testing**: Mockito, JUnit 5
- **Functional Programming**: Function<T,R>, Predicate<T>, Consumer<T>

## ⚡ Dicas de Performance

Embora Generics sejam implementados via type erasure (sem custo de runtime), existem considerações:

### ✅ Boas Práticas de Performance

1. **Use tipos primitivos através de autoboxing com cuidado**:
```java
// ⚠️ Autoboxing pode ter custo
List<Integer> numeros = new ArrayList<>();
for (int i = 0; i < 1000000; i++) {
    numeros.add(i); // Boxing de int -> Integer
}

// ✅ Para performance crítica, considere arrays primitivos
int[] numeros = new int[1000000];
```

2. **Especifique capacidade inicial quando conhecida**:
```java
// ✅ Evita redimensionamentos
List<String> grande = new ArrayList<>(10000);
Map<String, Integer> mapa = new HashMap<>(1000);
```

3. **Reutilize instâncias quando possível**:
```java
// ✅ Singleton para listas vazias
List<String> vazia = Collections.emptyList();

// ✅ Reutilize comparadores
Comparator<String> comp = String.CASE_INSENSITIVE_ORDER;
```

## 🏆 Quando Você Dominar Generics

Você saberá que dominou Generics quando conseguir:

- ✅ Criar classes e métodos genéricos reutilizáveis e type-safe
- ✅ Escolher entre `<T>`, `<? extends T>` e `<? super T>` apropriadamente
- ✅ Aplicar o princípio PECS naturalmente
- ✅ Entender mensagens de erro do compilador relacionadas a Generics
- ✅ Explicar type erasure e suas limitações
- ✅ Implementar padrões de design usando Generics
- ✅ Ler e entender código genérico em frameworks como Collections e Spring
- ✅ Refatorar código sem Generics para usar type parameters
- ✅ Tomar decisões arquiteturais sobre quando usar Generics

## 💬 Glossário de Termos

- **Type Parameter**: Variável de tipo declarada em <> (ex: T, E, K, V)
- **Type Argument**: Tipo concreto passado para substituir type parameter (ex: String, Integer)
- **Generic Type**: Classe ou interface com type parameters
- **Parameterized Type**: Instanciação de generic type com type arguments específicos
- **Raw Type**: Uso de generic type sem type arguments (não recomendado)
- **Bounded Type Parameter**: Type parameter com restrições (extends ou super)
- **Wildcard**: Tipo desconhecido representado por ? 
- **Type Erasure**: Remoção de informações de tipo em tempo de compilação
- **Bridge Method**: Método sintético criado pelo compilador para manter polimorfismo após erasure
- **Reifiable Type**: Tipo cuja informação está completamente disponível em runtime
- **Non-Reifiable Type**: Tipo cuja informação é perdida por type erasure

---

## 🎯 Resumo Final

Generics são **essenciais** para programação Java moderna:

**Por que usar Generics?**
- ✅ **Segurança**: Erros detectados em compile time
- ✅ **Clareza**: Código auto-documentado
- ✅ **Reutilização**: Componentes que funcionam com qualquer tipo
- ✅ **Manutenção**: Refatoração mais segura

**Conceitos-chave para lembrar**:
1. Type parameters proporcionam type safety em compile time
2. Bounded types restringem tipos aceitos
3. Wildcards adicionam flexibilidade (PECS!)
4. Type erasure remove tipos em runtime
5. Generics são fundamentais para APIs modernas

**Próximos passos**:
- ✅ Pratique os exemplos fornecidos
- ✅ Resolva os exercícios progressivamente
- ✅ Refatore código legado para usar Generics
- ✅ Estude implementação de Collections API
- ✅ Explore Generics em frameworks profissionais

---

**Anterior**: [Collections](../01-collections/) | **Próximo**: [Enumerações](../03-enum/)
