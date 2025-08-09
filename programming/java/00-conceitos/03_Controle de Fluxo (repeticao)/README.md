# Controle de Fluxo - Estruturas de Repetição

Esta seção apresenta as estruturas de repetição (loops) em Java, que permitem executar código múltiplas vezes de forma eficiente.

## 🎯 Objetivos

- Compreender os diferentes tipos de loops em Java
- Aprender quando usar cada estrutura de repetição
- Conhecer os comandos `break` e `continue`
- Trabalhar com loops aninhados
- Aplicar boas práticas em estruturas de repetição

## 📋 Tipos de Loops em Java

### 1. Loop `for`

Ideal quando você sabe quantas vezes quer repetir:

```java
for (inicialização; condição; incremento) {
    // código a ser repetido
}
```

**Exemplo:**
```java
for (int i = 0; i < 5; i++) {
    System.out.println("Iteração: " + i);
}
```

### 2. Loop `while`

Repete enquanto uma condição for verdadeira:

```java
while (condição) {
    // código a ser repetido
    // lembre-se de modificar a condição!
}
```

**Exemplo:**
```java
int contador = 0;
while (contador < 5) {
    System.out.println("Contador: " + contador);
    contador++;
}
```

### 3. Loop `do-while`

Executa pelo menos uma vez, depois verifica a condição:

```java
do {
    // código a ser repetido
} while (condição);
```

### 4. Enhanced for (for-each)

Para percorrer arrays e coleções:

```java
for (tipo elemento : array) {
    // usar elemento
}
```

## 📄 Análise dos Exemplos

### [EstruturaRepeticao.java](EstruturaRepeticao.java)
Arquivo principal com exemplos de todos os tipos de loops:

#### Exemplo de For Loop
```java
public static void exemploForLoop() {
    for (int i = 0; i < 5; i++) {
        System.out.println(i);
    }     
}
```

#### Exemplo de While Loop
```java
public static void exemploWhileLoop() {
    int i = 0;
    while (i < 5) {
        System.out.println(i);
        i++;
    }  
}
```

#### Exemplo de Do-While
```java
public static void exemploDoWhile() {
    int i = 0;
    do {
        System.out.println(i);
        i++;
    } while (i < 5);
}
```

#### Exemplo de For-Each
```java
public static void exemploForEachLoop() {
    String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
    for (String carro : cars) {
        System.out.println(carro);
    }
}
```

#### Exemplo de Array Multidimensional
```java
public static void exemploArrayMultiInt() {
    int[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };
    for (int i = 0; i < myNumbers.length; ++i) {
        for(int j = 0; j < myNumbers[i].length; ++j) {
            System.out.println(myNumbers[i][j]);
        }
    }
}
```

### Exemplos em Subdiretórios

#### [While/TestaWhile.java](While/TestaWhile.java)
Exemplos específicos do loop `while`.

#### [for/TestaFor.java](for/TestaFor.java)
Exemplos específicos do loop `for`.

#### [for/Fatorial.java](for/Fatorial.java)
Cálculo de fatorial usando loop `for`:
```java
// Exemplo: 5! = 5 × 4 × 3 × 2 × 1 = 120
int numero = 5;
int fatorial = 1;
for (int i = 1; i <= numero; i++) {
    fatorial *= i;
}
```

#### [for/MultiplosDeTresAteCem.java](for/MultiplosDeTresAteCem.java)
Encontrar múltiplos de 3 até 100:
```java
for (int i = 3; i <= 100; i += 3) {
    System.out.println(i);
}
```

## 🔧 Comandos de Controle de Loop

### `break`
Sai completamente do loop:

```java
for (int i = 0; i < 10; i++) {
    if (i == 5) {
        break;  // Sai do loop quando i == 5
    }
    System.out.println(i);  // Imprime 0, 1, 2, 3, 4
}
```

### `continue`
Pula para a próxima iteração:

```java
for (int i = 0; i < 10; i++) {
    if (i == 4) {
        continue;  // Pula quando i == 4
    }
    System.out.println(i);  // Imprime 0, 1, 2, 3, 5, 6, 7, 8, 9
}
```

## 🔄 Loops Aninhados

Loops dentro de outros loops:

```java
// Tabela de multiplicação
for (int i = 1; i <= 10; i++) {
    for (int j = 1; j <= 10; j++) {
        System.out.print((i * j) + "\t");
    }
    System.out.println();  // Nova linha após cada linha da tabela
}
```

## 🚀 Como Executar os Exemplos

```bash
# Navegar até o diretório
cd "03_Controle de Fluxo (repeticao)"

# Compilar e executar o exemplo principal
javac EstruturaRepeticao.java
java EstruturaRepeticao

# Executar exemplos específicos
cd for
javac Fatorial.java
java Fatorial
```

## 💡 Quando Usar Cada Loop

### Use `for` quando:
- Souber o número exato de iterações
- Precisar de um contador
- Trabalhar com arrays (usando índices)

### Use `while` quando:
- A condição de parada depender de algo além de um contador
- Não souber quantas iterações serão necessárias
- Ler dados até encontrar um valor específico

### Use `do-while` quando:
- Precisar executar o código pelo menos uma vez
- Validar entrada do usuário
- Menus que devem aparecer pelo menos uma vez

### Use `for-each` quando:
- Percorrer todos os elementos de um array/coleção
- Não precisar do índice
- Quiser código mais limpo e legível

## 💡 Boas Práticas

### 1. Evite loops infinitos
```java
// CUIDADO! Loop infinito:
while (true) {
    // sem break ou mudança na condição
}

// Correto:
boolean continuar = true;
while (continuar) {
    // lógica que pode definir continuar = false
}
```

### 2. Use nomes descritivos para variáveis
```java
// Evite:
for (int i = 0; i < students.length; i++) {
    // ...
}

// Prefira:
for (int studentIndex = 0; studentIndex < students.length; studentIndex++) {
    // ...
}

// Ou melhor ainda:
for (Student student : students) {
    // ...
}
```

### 3. Minimize o escopo das variáveis
```java
// Bom: variável i só existe no loop
for (int i = 0; i < 10; i++) {
    // usar i
}
// i não existe mais aqui
```

### 4. Cuidado com performance em loops aninhados
```java
// Evite cálculos repetidos
for (int i = 0; i < array.length; i++) {  // array.length calculado a cada iteração
    // ...
}

// Prefira:
int length = array.length;
for (int i = 0; i < length; i++) {
    // ...
}
```

## 💡 Experimentos Sugeridos

1. **Números primos**: Encontre todos os números primos até 100
2. **Padrões**: Crie padrões com asteriscos usando loops aninhados
3. **Calculadora**: Menu que executa até o usuário escolher sair
4. **Jogo de adivinhação**: Loop até o usuário acertar o número

### Exemplo de Padrão:
```java
// Triângulo de asteriscos
for (int i = 1; i <= 5; i++) {
    for (int j = 1; j <= i; j++) {
        System.out.print("*");
    }
    System.out.println();
}
```

## ❗ Erros Comuns

1. **Loop infinito**: Esquecer de modificar a variável de controle
2. **Off-by-one error**: `i <= array.length` ao invés de `i < array.length`
3. **Modificar array durante iteração**: Pode causar comportamento inesperado
4. **Usar for-each quando precisa do índice**: Use for tradicional nesses casos

## 📚 Conceitos Relacionados

- **Complexidade temporal**: Como loops afetam a performance (O(n), O(n²))
- **Iteradores**: Interface para percorrer coleções
- **Streams**: Alternativa funcional para processamento de coleções
- **Recursão**: Alternativa aos loops para alguns problemas

---

**Próximo**: [Exceções](../04_excecoes/) - Aprenda sobre tratamento de erros e exceções em Java.