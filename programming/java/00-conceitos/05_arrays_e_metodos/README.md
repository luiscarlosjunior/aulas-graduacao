# Arrays e Métodos em Java

Esta seção apresenta dois conceitos fundamentais da programação em Java: **Arrays** (estruturas de dados) e **Métodos** (funções), demonstrando como eles trabalham juntos para criar programas mais organizados e eficientes.

## 🎯 Objetivos de Aprendizado

Ao completar esta seção, você será capaz de:
- ✅ Criar e manipular arrays unidimensionais e bidimensionais
- ✅ Declarar, implementar e chamar métodos em Java
- ✅ Entender diferentes tipos de parâmetros e retornos
- ✅ Aplicar sobrecarga de métodos (method overloading)
- ✅ Compreender escopo de variáveis e passagem de parâmetros
- ✅ Criar programas interativos usando Scanner
- ✅ Combinar arrays e métodos para resolver problemas práticos

## 📚 Conceitos Fundamentais

### Arrays (Vetores e Matrizes)

Arrays são estruturas de dados que permitem armazenar múltiplos valores do mesmo tipo em uma única variável.

#### Características dos Arrays:
- **Tamanho fixo**: Definido na criação e não pode ser alterado
- **Tipo único**: Todos os elementos devem ser do mesmo tipo
- **Índice baseado em zero**: O primeiro elemento está na posição 0
- **Acesso direto**: Pode acessar qualquer elemento pelo índice

#### Sintaxe Básica:
```java
// Declaração e criação
int[] numeros = new int[5];           // Array de 5 inteiros
String[] nomes = new String[3];       // Array de 3 strings

// Inicialização direta
int[] valores = {10, 20, 30, 40, 50};
String[] frutas = {"Maçã", "Banana", "Laranja"};

// Acesso aos elementos
numeros[0] = 100;        // Define primeiro elemento
int primeiro = numeros[0]; // Lê primeiro elemento
int tamanho = numeros.length; // Obtém tamanho do array
```

### Métodos (Funções)

Métodos são blocos de código que realizam uma tarefa específica e podem ser reutilizados em diferentes partes do programa.

#### Vantagens dos Métodos:
- **Reutilização**: Evita repetição de código
- **Organização**: Divide o programa em partes menores
- **Manutenção**: Facilita correções e melhorias
- **Legibilidade**: Torna o código mais fácil de entender

#### Sintaxe Básica:
```java
// Método sem parâmetros e sem retorno
public static void exibirMensagem() {
    System.out.println("Olá, mundo!");
}

// Método com parâmetros e sem retorno
public static void saudar(String nome) {
    System.out.println("Olá, " + nome + "!");
}

// Método com parâmetros e com retorno
public static int somar(int a, int b) {
    return a + b;
}

// Chamada dos métodos
exibirMensagem();           // Chama método simples
saudar("Maria");           // Chama método com parâmetro
int resultado = somar(5, 3); // Chama método com retorno
```

## 📁 Arquivos da Seção

### [ExemplosArrays.java](ExemplosArrays.java)
Demonstração completa do uso de arrays em Java.

**Conceitos demonstrados:**
- Declaração e inicialização de arrays
- Arrays unidimensionais (vetores)
- Arrays bidimensionais (matrizes)
- Operações básicas (busca, ordenação, estatísticas)
- Arrays de diferentes tipos (int, double, String)
- Loops para percorrer arrays

**Exemplos práticos:**
- Sistema de votação
- Controle de estoque
- Análise de temperaturas
- Manipulação de notas de alunos

### [ExemplosMetodos.java](ExemplosMetodos.java)
Demonstração completa do uso de métodos em Java.

**Conceitos demonstrados:**
- Métodos com e sem parâmetros
- Métodos com e sem retorno
- Sobrecarga de métodos (overloading)
- Escopo de variáveis
- Passagem de parâmetros por valor
- Métodos que trabalham com arrays

**Exemplos práticos:**
- Sistema de validação de dados
- Conversor de temperatura
- Gerador de relatórios
- Calculadora modular

### [ProgramaInterativo.java](ProgramaInterativo.java)
Programa completo que demonstra entrada de dados usando Scanner.

**Funcionalidades:**
- 👤 Cadastro de pessoa com validações
- 🧮 Calculadora simples
- 📊 Análise de notas
- 🎯 Jogo de adivinhação
- 📝 Lista de tarefas
- 🌡️ Conversor de temperatura

**Conceitos demonstrados:**
- Importação de classes (`import java.util.Scanner`)
- Entrada de dados do usuário
- Validação de entrada
- Menus interativos
- Tratamento de erros
- Combinação de arrays, métodos e estruturas de controle

## 🚀 Como Executar os Exemplos

### 1. Compilação
```bash
javac ExemplosArrays.java
javac ExemplosMetodos.java
javac ProgramaInterativo.java
```

### 2. Execução
```bash
# Exemplos de arrays
java ExemplosArrays

# Exemplos de métodos
java ExemplosMetodos

# Programa interativo (requer entrada do usuário)
java ProgramaInterativo
```

### 3. Resultado Esperado

#### ExemplosArrays.java:
- Demonstrações de diferentes tipos de arrays
- Operações estatísticas (maior, menor, média)
- Manipulação de matrizes bidimensionais
- Exemplos práticos com dados reais

#### ExemplosMetodos.java:
- Exemplos de métodos com diferentes assinaturas
- Demonstração de sobrecarga de métodos
- Aplicações práticas em sistemas reais
- Organização modular do código

#### ProgramaInterativo.java:
- Menu interativo com múltiplas opções
- Validação robusta de entrada do usuário
- Funcionalidades completas e práticas
- Experiência de usuário profissional

## 💡 Conceitos Avançados

### Sobrecarga de Métodos (Method Overloading)

Java permite ter múltiplos métodos com o mesmo nome, desde que tenham parâmetros diferentes:

```java
// Diferentes tipos de parâmetros
public static int calcular(int a, int b) { return a * b; }
public static double calcular(double a, double b) { return a * b; }

// Diferentes quantidades de parâmetros
public static int calcular(int a, int b, int c) { return a * b * c; }

// Diferentes tipos em posições diferentes
public static String calcular(String a, String b) { return a + " " + b; }
```

### Arrays Bidimensionais (Matrizes)

Arrays de arrays, úteis para representar tabelas, grades, matrizes matemáticas:

```java
// Criação de matriz 3x4
int[][] matriz = new int[3][4];

// Inicialização direta
double[][] notas = {
    {8.5, 9.0, 7.5},  // Aluno 1
    {7.0, 8.5, 9.5},  // Aluno 2
    {9.5, 8.0, 8.5}   // Aluno 3
};

// Percorrer matriz com loops aninhados
for (int linha = 0; linha < matriz.length; linha++) {
    for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
        System.out.print(matriz[linha][coluna] + " ");
    }
    System.out.println();
}
```

### Entrada de Dados com Scanner

A classe Scanner permite ler entrada do usuário de forma robusta:

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);

// Diferentes tipos de entrada
String nome = scanner.nextLine();        // Linha completa
int idade = scanner.nextInt();           // Número inteiro
double altura = scanner.nextDouble();    // Número decimal
boolean ativo = scanner.nextBoolean();   // Valor booleano

scanner.close(); // Sempre fechar o scanner
```

## ⚠️ Erros Comuns e Como Evitar

### 1. ArrayIndexOutOfBoundsException
```java
// ERRO: Acessar índice inexistente
int[] array = new int[5];
int valor = array[5]; // Erro! Último índice é 4

// CORRETO: Verificar limites
if (index >= 0 && index < array.length) {
    int valor = array[index];
}
```

### 2. NullPointerException
```java
// ERRO: Array não inicializado
int[] numeros = null;
int tamanho = numeros.length; // Erro!

// CORRETO: Sempre inicializar
int[] numeros = new int[5];
int tamanho = numeros.length; // OK
```

### 3. Scanner não fechado
```java
// PROBLEMA: Scanner não fechado
Scanner scanner = new Scanner(System.in);
// ... uso do scanner ...
// Esqueceu de fechar!

// CORRETO: Sempre fechar
Scanner scanner = new Scanner(System.in);
// ... uso do scanner ...
scanner.close(); // Importante!
```

### 4. Método sem retorno
```java
// ERRO: Método declara retorno mas não retorna
public static int calcular(int a, int b) {
    int resultado = a + b;
    // Esqueceu do return!
}

// CORRETO: Sempre retornar quando declarado
public static int calcular(int a, int b) {
    int resultado = a + b;
    return resultado; // Obrigatório!
}
```

## 📊 Comparação: Com e Sem Arrays/Métodos

### Sem Arrays (problemático):
```java
int nota1 = 8, nota2 = 7, nota3 = 9, nota4 = 6, nota5 = 8;
int soma = nota1 + nota2 + nota3 + nota4 + nota5;
double media = soma / 5.0;
// Difícil de manter, não escalável
```

### Com Arrays (melhor):
```java
int[] notas = {8, 7, 9, 6, 8};
int soma = 0;
for (int nota : notas) {
    soma += nota;
}
double media = (double) soma / notas.length;
// Fácil de manter, escalável
```

### Sem Métodos (repetitivo):
```java
// Código repetido para cada cálculo
int soma1 = a1 + b1;
System.out.println("Resultado: " + soma1);

int soma2 = a2 + b2;
System.out.println("Resultado: " + soma2);
// Muita repetição!
```

### Com Métodos (organizado):
```java
public static void exibirSoma(int a, int b) {
    int soma = a + b;
    System.out.println("Resultado: " + soma);
}

exibirSoma(a1, b1); // Reutilização
exibirSoma(a2, b2); // Código limpo
```

## 🏆 Boas Práticas

### Arrays:
1. **Sempre verificar limites** antes de acessar elementos
2. **Usar for-each** quando só precisar ler valores
3. **Escolher nomes descritivos** para arrays (`idades` ao invés de `array1`)
4. **Inicializar arrays** sempre que possível
5. **Comentar arrays complexos** explicando sua estrutura

### Métodos:
1. **Um método, uma responsabilidade** (princípio da responsabilidade única)
2. **Nomes verbais** para métodos (`calcularMedia` ao invés de `media`)
3. **Validar parâmetros** antes de usar
4. **Documentar métodos** com comentários JavaDoc
5. **Evitar métodos muito longos** (máximo 20-30 linhas)

### Scanner:
1. **Sempre fechar** o Scanner após uso
2. **Validar entrada** do usuário
3. **Tratar erros** de entrada inválida
4. **Usar nextLine()** após nextInt() para limpar buffer
5. **Dar feedback** claro sobre entradas esperadas

## 📈 Próximos Passos

Após dominar arrays e métodos, você estará pronto para:

1. **Programação Orientada a Objetos**: Classes, objetos, encapsulamento
2. **Collections Framework**: ArrayList, HashMap, LinkedList
3. **Tratamento de Exceções**: try-catch, throws, exceções personalizadas
4. **Manipulação de Arquivos**: Leitura e escrita de dados
5. **Interfaces Gráficas**: Swing, JavaFX para aplicações visuais

## 🔗 Recursos Adicionais

- [Documentação Oracle - Arrays](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [Documentação Oracle - Métodos](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)
- [Java Scanner Class](https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html)

---

**Anterior**: [Tratamento de Exceções](../04_excecoes/) | **Próximo**: [Exercícios Práticos](../exercicios/)

**Desenvolvido para fins educacionais** - Pratique, experimente e divirta-se aprendendo Java! 🚀