# Exercícios Java - Coleção Avançada

Esta seção contém exercícios mais elaborados e soluções comentadas para consolidar todos os conceitos de Java aprendidos ao longo do curso.

## 🎯 Objetivos

- Aplicar conceitos integrados de Java
- Resolver problemas mais complexos
- Praticar algoritmos e estruturas de dados
- Desenvolver soluções otimizadas
- Implementar boas práticas de programação

## 📄 Conteúdo

### [CorrecaoExerciciosJava.java](CorrecaoExerciciosJava.java)

Arquivo principal contendo soluções detalhadas para exercícios avançados, organizados por nível de complexidade.

## 📋 Lista de Exercícios

### **Exercício 1D - Troca de Valores**
```java
private static void Exercicio1D()
```

**Objetivo**: Trocar valores entre duas variáveis usando uma variável auxiliar.

**Conceitos aplicados**:
- Manipulação de variáveis
- Algoritmo clássico de troca
- Variável auxiliar

**Solução**:
```java
int valorA = 10;
int valorB = 20;
int aux;

System.out.println("Antes: A=" + valorA + ", B=" + valorB);

// Troca usando variável auxiliar
aux = valorA;
valorA = valorB;
valorB = aux;

System.out.println("Depois: A=" + valorA + ", B=" + valorB);
```

### **Exercício 3B - Estruturas de Repetição Avançadas**
```java
private static void Exercicio3B()
```

**Objetivo**: Implementar algoritmos usando loops complexos.

**Conceitos aplicados**:
- Loops aninhados
- Controle de fluxo avançado
- Otimização de algoritmos

### **Exercício 3C - Manipulação de Arrays**
```java
private static void Exercicio3C()
```

**Objetivo**: Trabalhar com arrays e algoritmos de busca/ordenação.

**Conceitos aplicados**:
- Arrays unidimensionais e multidimensionais
- Algoritmos de busca
- Algoritmos de ordenação básicos

### **Exercício 3D - Problema Complexo (Ativo)**
```java
private static void Exercicio3D()
```

**Objetivo**: Resolver problema que integra múltiplos conceitos.

**Conceitos aplicados**:
- Integração de conceitos
- Lógica complexa
- Estruturas de dados

## 🚀 Como Executar

```bash
# Navegar até o diretório
cd "99-exercicios"

# Compilar o arquivo
javac CorrecaoExerciciosJava.java

# Executar (exercício 3D está ativo por padrão)
java CorrecaoExerciciosJava
```

### Para executar exercícios específicos:

Modifique o método `main` para descomentar o exercício desejado:

```java
public static void main(String[] args) {
    // Exercicio1D();    // Troca de valores
    // Exercicio3B();    // Loops avançados
    // Exercicio3C();    // Arrays e algoritmos
    Exercicio3D();       // Problema complexo (ativo)
}
```

## 💡 Níveis de Dificuldade

### 🟢 **Nível Básico** (Exercício 1D)
- Conceitos fundamentais
- Lógica simples
- Algoritmos clássicos

### 🟡 **Nível Intermediário** (Exercícios 3B, 3C)
- Estruturas de dados
- Algoritmos de busca e ordenação
- Loops complexos

### 🔴 **Nível Avançado** (Exercício 3D)
- Integração de conceitos
- Problemas do mundo real
- Otimização e performance

## 🎓 Competências Desenvolvidas

### **Pensamento Algorítmico**
- Decomposição de problemas
- Análise de complexidade
- Otimização de soluções

### **Estruturas de Dados**
- Arrays e matrizes
- Listas e coleções
- Manipulação eficiente de dados

### **Boas Práticas**
- Código limpo e legível
- Comentários significativos
- Organização e modularização

## 💡 Exercícios Complementares Sugeridos

### **Para Praticar Algoritmos:**

#### 1. **Ordenação**
```java
// Bubble Sort
public static void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        for (int j = 0; j < arr.length - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // Trocar elementos
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
```

#### 2. **Busca Binária**
```java
public static int buscaBinaria(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    
    return -1; // Não encontrado
}
```

#### 3. **Fibonacci Otimizado**
```java
public static long fibonacci(int n) {
    if (n <= 1) return n;
    
    long a = 0, b = 1;
    for (int i = 2; i <= n; i++) {
        long temp = a + b;
        a = b;
        b = temp;
    }
    return b;
}
```

### **Para Praticar POO:**

#### 4. **Sistema de Biblioteca**
```java
class Livro {
    private String titulo, autor;
    private boolean disponivel;
    
    // Construtores, getters, setters
    public void emprestar() { disponivel = false; }
    public void devolver() { disponivel = true; }
}

class Biblioteca {
    private List<Livro> livros;
    
    public void adicionarLivro(Livro livro) { /* ... */ }
    public Livro buscarPorTitulo(String titulo) { /* ... */ }
}
```

## 📊 Análise de Complexidade

| Exercício | Complexidade Temporal | Complexidade Espacial | Observações |
|-----------|----------------------|----------------------|-------------|
| 1D | O(1) | O(1) | Operação simples |
| 3B | O(n) a O(n²) | O(1) a O(n) | Depende do algoritmo |
| 3C | O(n log n) | O(n) | Se usar ordenação eficiente |
| 3D | Variável | Variável | Depende do problema |

## 🔧 Ferramentas e Técnicas

### **Debug e Teste**
```java
// Método auxiliar para debug
private static void debug(String mensagem, Object valor) {
    System.out.println("DEBUG: " + mensagem + " = " + valor);
}

// Teste simples
public static void testarFuncao() {
    int resultado = minhaFuncao(10);
    assert resultado == 100 : "Erro: resultado esperado 100";
    System.out.println("Teste passou!");
}
```

### **Medição de Performance**
```java
long inicio = System.currentTimeMillis();
// Código a ser medido
long fim = System.currentTimeMillis();
System.out.println("Tempo execução: " + (fim - inicio) + "ms");
```

## 📚 Recursos para Aprofundamento

### **Livros Recomendados**
- "Algorithms" - Robert Sedgewick
- "Clean Code" - Robert Martin
- "Effective Java" - Joshua Bloch

### **Plataformas de Prática**
- LeetCode (algoritmos)
- HackerRank (programação geral)
- Codewars (desafios progressivos)
- Project Euler (matemática computacional)

### **Tópicos Avançados**
- Estruturas de dados avançadas (árvores, grafos)
- Algoritmos de ordenação eficientes
- Programação dinâmica
- Design patterns

## 🎯 Próximos Desafios

1. **Implementar estrutura de dados própria** (LinkedList, Stack, Queue)
2. **Resolver problemas de grafos** (BFS, DFS, menor caminho)
3. **Criar mini-projetos** (calculadora científica, jogo da velha)
4. **Integrar com banco de dados** (JDBC, persistência)

---

**Voltar para**: [Início do Curso Java](../README.md) - Revisitar conceitos ou explorar outras seções.