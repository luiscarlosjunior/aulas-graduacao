# Arrays e Métodos em Java

## 📋 Visão Geral

Arrays e métodos são conceitos fundamentais que permitem organizar dados e código de forma eficiente. Arrays armazenam múltiplos elementos do mesmo tipo, enquanto métodos organizam lógica em blocos reutilizáveis, tornando programas mais estruturados e fáceis de manter.

## 🎯 Objetivos de Aprendizado

Ao completar este tópico, você será capaz de:

- ✅ Declarar, inicializar e manipular arrays unidimensionais e multidimensionais
- ✅ Criar e usar métodos com diferentes tipos de parâmetros e retorno
- ✅ Entender sobrecarga de métodos (overloading)
- ✅ Trabalhar com arrays como parâmetros de métodos
- ✅ Aplicar boas práticas na criação de métodos
- ✅ Usar a classe Scanner para entrada de dados interativa

## 📊 Arrays em Java

### Declaração e Inicialização

```java
// Declaração
int[] numeros;
String[] nomes;

// Declaração com tamanho
int[] idades = new int[5]; // Array com 5 posições

// Inicialização com valores
int[] notas = {8, 7, 9, 6, 10};
String[] frutas = {"Maçã", "Banana", "Laranja"};

// Inicialização mista
double[] precos = new double[]{12.50, 8.90, 15.30};
```

### Acessando Elementos

```java
int[] numeros = {10, 20, 30, 40, 50};

// Acessar elemento (índice começa em 0)
System.out.println("Primeiro: " + numeros[0]); // 10
System.out.println("Último: " + numeros[4]);   // 50

// Modificar elemento
numeros[2] = 35;
System.out.println("Terceiro: " + numeros[2]); // 35

// Tamanho do array
System.out.println("Tamanho: " + numeros.length); // 5
```

### Percorrendo Arrays

```java
String[] nomes = {"Ana", "João", "Maria", "Pedro"};

// Loop for tradicional
for (int i = 0; i < nomes.length; i++) {
    System.out.println("Posição " + i + ": " + nomes[i]);
}

// Enhanced for (for-each)
for (String nome : nomes) {
    System.out.println("Nome: " + nome);
}

// While
int index = 0;
while (index < nomes.length) {
    System.out.println(nomes[index]);
    index++;
}
```

### Arrays Multidimensionais

```java
// Matriz 3x3
int[][] matriz = new int[3][3];

// Inicialização com valores
int[][] tabela = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Acessando elementos
tabela[0][0] = 10; // Primeira linha, primeira coluna
System.out.println(tabela[1][2]); // Segunda linha, terceira coluna

// Percorrendo matriz
for (int linha = 0; linha < tabela.length; linha++) {
    for (int coluna = 0; coluna < tabela[linha].length; coluna++) {
        System.out.print(tabela[linha][coluna] + " ");
    }
    System.out.println(); // Nova linha
}

// Enhanced for com matriz
for (int[] linha : tabela) {
    for (int elemento : linha) {
        System.out.print(elemento + " ");
    }
    System.out.println();
}
```

## 🔧 Métodos em Java

### Estrutura Básica

```java
public static tipoRetorno nomeMetodo(parametros) {
    // Corpo do método
    return valor; // Se tipoRetorno não for void
}
```

### Métodos Simples

```java
// Método sem parâmetros e sem retorno
public static void saudar() {
    System.out.println("Olá! Bem-vindo ao programa!");
}

// Método com parâmetros e sem retorno
public static void saudarPessoa(String nome) {
    System.out.println("Olá, " + nome + "! Como você está?");
}

// Método com retorno
public static int somar(int a, int b) {
    return a + b;
}

// Método com múltiplos parâmetros
public static double calcularMedia(double nota1, double nota2, double nota3) {
    return (nota1 + nota2 + nota3) / 3.0;
}
```

### Usando os Métodos

```java
public static void main(String[] args) {
    // Chamando método sem parâmetros
    saudar();
    
    // Chamando método com parâmetros
    saudarPessoa("Maria");
    
    // Usando método com retorno
    int resultado = somar(10, 20);
    System.out.println("Soma: " + resultado);
    
    // Usando método diretamente
    System.out.println("Média: " + calcularMedia(8.5, 7.0, 9.2));
}
```

### Sobrecarga de Métodos (Overloading)

```java
// Diferentes versões do mesmo método
public static int somar(int a, int b) {
    return a + b;
}

public static double somar(double a, double b) {
    return a + b;
}

public static int somar(int a, int b, int c) {
    return a + b + c;
}

public static String somar(String a, String b) {
    return a + b; // Concatenação
}

// Uso
public static void main(String[] args) {
    System.out.println(somar(5, 3));           // int + int
    System.out.println(somar(5.5, 3.2));       // double + double
    System.out.println(somar(1, 2, 3));        // três inteiros
    System.out.println(somar("Olá", " Mundo")); // strings
}
```

## 🔄 Arrays e Métodos Juntos

### Passando Arrays para Métodos

```java
// Método que recebe array como parâmetro
public static void imprimirArray(int[] array) {
    System.out.print("Array: [");
    for (int i = 0; i < array.length; i++) {
        System.out.print(array[i]);
        if (i < array.length - 1) {
            System.out.print(", ");
        }
    }
    System.out.println("]");
}

// Método que modifica array
public static void dobrarValores(int[] array) {
    for (int i = 0; i < array.length; i++) {
        array[i] *= 2;
    }
}

// Método que retorna array
public static int[] criarSequencia(int tamanho) {
    int[] sequencia = new int[tamanho];
    for (int i = 0; i < tamanho; i++) {
        sequencia[i] = i + 1;
    }
    return sequencia;
}

// Uso
public static void main(String[] args) {
    int[] numeros = {1, 2, 3, 4, 5};
    
    imprimirArray(numeros);           // [1, 2, 3, 4, 5]
    dobrarValores(numeros);           // Modifica o array original
    imprimirArray(numeros);           // [2, 4, 6, 8, 10]
    
    int[] nova = criarSequencia(3);   // [1, 2, 3]
    imprimirArray(nova);
}
```

### Operações Comuns com Arrays

```java
// Encontrar maior elemento
public static int encontrarMaior(int[] array) {
    int maior = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] > maior) {
            maior = array[i];
        }
    }
    return maior;
}

// Calcular soma
public static int somarElementos(int[] array) {
    int soma = 0;
    for (int elemento : array) {
        soma += elemento;
    }
    return soma;
}

// Buscar elemento
public static int buscarElemento(int[] array, int valor) {
    for (int i = 0; i < array.length; i++) {
        if (array[i] == valor) {
            return i; // Retorna o índice
        }
    }
    return -1; // Não encontrado
}

// Inverter array
public static void inverterArray(int[] array) {
    int tamanho = array.length;
    for (int i = 0; i < tamanho / 2; i++) {
        int temp = array[i];
        array[i] = array[tamanho - 1 - i];
        array[tamanho - 1 - i] = temp;
    }
}
```

## 📥 Entrada de Dados com Scanner

### Lendo Diferentes Tipos

```java
import java.util.Scanner;

public static void exemploEntrada() {
    Scanner sc = new Scanner(System.in);
    
    // Lendo inteiro
    System.out.print("Digite sua idade: ");
    int idade = sc.nextInt();
    
    // Lendo double
    System.out.print("Digite sua altura: ");
    double altura = sc.nextDouble();
    
    // Limpar buffer antes de ler string
    sc.nextLine();
    
    // Lendo string
    System.out.print("Digite seu nome: ");
    String nome = sc.nextLine();
    
    System.out.println("Nome: " + nome);
    System.out.println("Idade: " + idade);
    System.out.println("Altura: " + altura);
    
    sc.close();
}
```

### Preenchendo Array com Scanner

```java
public static int[] lerArray(int tamanho) {
    Scanner sc = new Scanner(System.in);
    int[] array = new int[tamanho];
    
    System.out.println("Digite " + tamanho + " números:");
    for (int i = 0; i < tamanho; i++) {
        System.out.print("Número " + (i + 1) + ": ");
        array[i] = sc.nextInt();
    }
    
    return array;
}

// Menu interativo
public static void menuInterativo() {
    Scanner sc = new Scanner(System.in);
    int opcao;
    
    do {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Calcular média");
        System.out.println("2. Encontrar maior");
        System.out.println("3. Buscar elemento");
        System.out.println("0. Sair");
        System.out.print("Opção: ");
        opcao = sc.nextInt();
        
        switch (opcao) {
            case 1:
                calcularMediaInterativa(sc);
                break;
            case 2:
                encontrarMaiorInterativo(sc);
                break;
            case 3:
                buscarElementoInterativo(sc);
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    } while (opcao != 0);
    
    sc.close();
}
```

## 💡 Padrões e Casos de Uso

### 1. Calculadora de Estatísticas

```java
public class EstatisticasArray {
    
    public static double calcularMedia(double[] valores) {
        double soma = 0;
        for (double valor : valores) {
            soma += valor;
        }
        return soma / valores.length;
    }
    
    public static double encontrarMediana(double[] valores) {
        // Primeiro ordenar (algoritmo simples)
        ordenarArray(valores);
        
        int meio = valores.length / 2;
        if (valores.length % 2 == 0) {
            return (valores[meio - 1] + valores[meio]) / 2.0;
        } else {
            return valores[meio];
        }
    }
    
    public static void ordenarArray(double[] array) {
        // Bubble sort simples
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    double temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
    
    public static void exibirEstatisticas(double[] valores) {
        System.out.println("=== ESTATÍSTICAS ===");
        System.out.printf("Média: %.2f%n", calcularMedia(valores));
        System.out.printf("Mediana: %.2f%n", encontrarMediana(valores));
        System.out.printf("Maior: %.2f%n", encontrarMaior(valores));
        System.out.printf("Menor: %.2f%n", encontrarMenor(valores));
    }
}
```

### 2. Jogo da Forca Simples

```java
public class JogoForca {
    
    public static boolean jogar(String palavra) {
        char[] letrasAdivinhadas = new char[palavra.length()];
        boolean[] acertos = new boolean[palavra.length()];
        int tentativasRestantes = 6;
        Scanner sc = new Scanner(System.in);
        
        // Inicializar array de letras
        for (int i = 0; i < letrasAdivinhadas.length; i++) {
            letrasAdivinhadas[i] = '_';
        }
        
        while (tentativasRestantes > 0 && !palavraCompleta(acertos)) {
            exibirEstado(letrasAdivinhadas, tentativasRestantes);
            
            System.out.print("Digite uma letra: ");
            char letra = sc.next().charAt(0);
            
            if (verificarLetra(palavra, letra, letrasAdivinhadas, acertos)) {
                System.out.println("Acertou!");
            } else {
                tentativasRestantes--;
                System.out.println("Errou! Tentativas restantes: " + tentativasRestantes);
            }
        }
        
        return palavraCompleta(acertos);
    }
    
    private static boolean verificarLetra(String palavra, char letra, 
                                        char[] adivinhadas, boolean[] acertos) {
        boolean acertou = false;
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == letra) {
                adivinhadas[i] = letra;
                acertos[i] = true;
                acertou = true;
            }
        }
        return acertou;
    }
    
    private static boolean palavraCompleta(boolean[] acertos) {
        for (boolean acerto : acertos) {
            if (!acerto) return false;
        }
        return true;
    }
    
    private static void exibirEstado(char[] letras, int tentativas) {
        System.out.print("Palavra: ");
        for (char letra : letras) {
            System.out.print(letra + " ");
        }
        System.out.println("\nTentativas restantes: " + tentativas);
    }
}
```

## ⚠️ Armadilhas Comuns

### 1. IndexOutOfBoundsException

```java
// ❌ PERIGOSO
int[] array = new int[5];
array[5] = 10; // Erro! Índices vão de 0 a 4

// ✅ SEGURO
if (indice >= 0 && indice < array.length) {
    array[indice] = valor;
}
```

### 2. Arrays não Inicializados

```java
// ❌ NullPointerException
int[] numeros = null;
System.out.println(numeros.length); // Erro!

// ✅ Sempre inicializar
int[] numeros = new int[5]; // ou = {1, 2, 3, 4, 5};
```

### 3. Modificação de Arrays em Métodos

```java
// Arrays são passados por referência!
public static void modificarArray(int[] array) {
    array[0] = 999; // Modifica o array original!
}

// Se não quer modificar o original, crie uma cópia
public static int[] copiarArray(int[] original) {
    int[] copia = new int[original.length];
    for (int i = 0; i < original.length; i++) {
        copia[i] = original[i];
    }
    return copia;
}
```

## 🧪 Exercícios Práticos

### Nível Iniciante
1. **Calculadora de Notas**: Array de notas com média, maior e menor
2. **Lista de Compras**: Array de strings com busca e exibição
3. **Contador de Vogais**: Método que conta vogais em uma palavra

### Nível Intermediário
1. **Sistema de Vendas**: Arrays para produtos, preços e quantidades
2. **Jogo de Número Secreto**: Array de tentativas com estatísticas
3. **Agenda Telefônica**: Arrays paralelos para nomes e telefones

### Nível Avançado
1. **Sistema de Biblioteca**: Múltiplos arrays com relacionamentos
2. **Jogo da Velha**: Matriz 3x3 com verificação de vitória
3. **Calculadora Científica**: Métodos para diferentes operações matemáticas

## 🛠️ Boas Práticas

1. **Inicialize sempre**: Evite arrays null
2. **Valide índices**: Sempre verifique limites antes de acessar
3. **Use enhanced for**: Quando não precisar do índice
4. **Métodos pequenos**: Uma responsabilidade por método
5. **Nomes descritivos**: `calcularMedia()` em vez de `calc()`
6. **Documente parâmetros**: Use Javadoc para métodos públicos
7. **Feche Scanner**: Evite vazamentos de recursos

## 📖 Próximos Passos

Após dominar arrays e métodos, você estará pronto para:
- [Manipulação de Strings](../manipulacao_strings/) - Processamento avançado de texto
- [Programação Orientada a Objetos](../../02-programacao-orientada-objetos/) - Classes e objetos
- [Collections Framework](../../03-conceitos-intermediarios/) - Estruturas de dados avançadas

## 📚 Recursos Adicionais

- [Oracle Arrays Tutorial](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [Clean Code: Functions](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Arrays Class Documentation](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Arrays.html)

---

**💡 Dica**: Arrays e métodos são a base da programação estruturada. Domine-os bem antes de avançar para programação orientada a objetos!