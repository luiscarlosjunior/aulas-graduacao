# Tratamento de execeções

Um dos pontos fortes da maioria das linguagens de programaçao e a possibilidade 
de manipular e tratar exceções. A linguamge Java não é diferente e é um dos 
mecanismos poderosos para lidar com os exceções em tempo de execução 
para que o fluxo normal da aplicação não seja afetado.
Aqui será apresentado as exceções em Java, seu tipo e a diferença entre exceções 
verificadas e não verificadas.

## O que é exceção em Java

Exceções são erros ou imprevistos que podem ocorrer durante a execução de um programa Java. 
Nesses casos, o gerenciador o compilador aborta a execução e procura uma área de exceções;
Para isso a linguagem Java fornece recursos para lidar com essas exceções conhecida como 
manipulação de exceção. Usando a manipulação de exceção, podemos testar e evitar 
que o programa pare derrepende. Ao manipular essas exceções garantimos que nosso programa 
seja mais robusto.

## O que é tratamento de exceção
O tratamento de exceções é uma maneira de lidar com erros em tempo de execução, 
como _**ClassNotFoundException, IOException, SQLException, RemoteException**_, etc. 
Dessa forma podemos ter uma resposta mais personalizada e objetivo ao trabalhar com usuário
finais

## Vantagem do tratamento de exceções
Há diversas vantagens em manter um tratamento de exceções no programa, umas das vantagens
do manuseio de exceções é manter o fluxo normal da aplicação. 
Quando é identificado uma exceção normalmente é interrompido o fluxo normal do aplicativo, 
por isso usamos o manuseio de exceções. 

### Vamos fazer um cenário:

```java
declaração 1;    
declaração 2;  
declaração 3;  
declaração 4;  
declaração 5; //exceção ocorre  
declaração 6;  
declaração 7;  
declaração 8;  
declaração 9;  
declaração 10;  
``` 

No exemplo acima, vamos supor que haja 10 declarações no programa e que ocorra uma 
exceção na declaração 5, o resto do código não será executado, ou seja, a declaração 6 a 10 
não será executada. Dependendo de como for feito o tratamento de exceção, o resto da 
declaração será executada. É por isso que usamos o manuseio de exceções em Java.

## Tipos de exceções em Java

A seção abaixo foi retirado do site [Java Point](https://www.javatpoint.com/exception-handling-in-java#:~:text=In%20Java%2C%20an%20exception%20is%20an%20event%20that,errors%20such%20as%20ClassNotFoundException%2C%20IOException%2C%20SQLException%2C%20RemoteException%2C%20etc.)

Em Java há principalmente dois tipos de exceções: verificadas e desmarcadas. Aqui, um erro 
que acontece dentro do sistema é considerado como a **exceção não verificada**. 
De acordo com a **Oracle**, existem três tipos de exceções:

1. Exceção verificada
2. Exceção não verificada
3. erro

1) Exceção verificada
As classes que herdam diretamente a classe _**Throwable**_, exceto 
__**RuntimeException**__ e __**Error**__ são conhecidas como exceções verificadas, 
por exemplo, __**IOException, SQLException **__ etc. As exceções verificadas são 
verificadas na hora do compilado.

2) Exceção não verificada
As classes que herdam o __**RuntimeException**__ são conhecidas como exceções não 
verificadas, por exemplo, __**AithmeticException, NullPointerException, ArrayIndexOutOfBoundsException **__
etc. As exceções não verificadas não são verificadas na hora do compilado, 
mas são verificadas no tempo de execução.

3) Erro
O erro é irrecuperável, por exemplo, __**OutOfMemoryError, VirtualMachineError, AssertionError**__ etc.

## Estruturas para tratamento de exceções

### 1. Estrutura try-catch

A estrutura try-catch é fundamental para capturar e tratar exceções em Java:

```java
try {
    int a[] = new int[2];
    System.out.println("Access element three :" + a[3]);
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("lançando a exceção  :" + e);
}
System.out.println("Fora do block");
```

### 2. Bloco finally

O bloco finally sempre é executado, independentemente de ocorrer exceção ou não:

```java
int a[] = new int[2];
try {
   System.out.println("Acessando o elemento :" + a[3]);
} catch (ArrayIndexOutOfBoundsException e) {
   System.out.println("Exceção lançada:" + e);
} finally {
   a[0] = 6;
   System.out.println("Primeiro valor do elemento: " + a[0]);
   System.out.println("A declaração finally foi executada");
}
```

### 3. Cláusula throws

A cláusula throws é usada para declarar que um método pode lançar uma exceção:

```java
import java.io.IOException;

public static void lerArquivo() throws IOException {
    // Simulando uma operação que pode gerar IOException
    throw new IOException("Arquivo não encontrado");
}

// No método main ou chamador:
try {
    lerArquivo();
} catch (IOException e) {
    System.out.println("Erro capturado: " + e.getMessage());
}
```

### 4. Instrução throw

A instrução throw é usada para lançar uma exceção manualmente:

```java
public static void verificarIdade(int idade) {
    if (idade < 18) {
        throw new IllegalArgumentException("Idade deve ser maior ou igual a 18");
    }
    System.out.println("Idade verificada: " + idade);
}
```

### 5. Métodos getMessage() e printStackTrace()

Estes métodos ajudam a obter informações detalhadas sobre as exceções:

```java
try {
    int resultado = 10 / 0;
} catch (ArithmeticException e) {
    // getMessage() retorna a mensagem da exceção
    System.out.println("Mensagem da exceção: " + e.getMessage());
    
    // printStackTrace() mostra o rastreamento completo da pilha
    System.out.println("Rastreamento completo da pilha:");
    e.printStackTrace();
}
```

## Exemplos práticos

Os exemplos completos estão disponíveis nos diretórios:
- `programming/java/00-conceitos/04_excecoes/01trycatch/` (try-catch, finally, throws, throw)
- `programming/java/00-conceitos/04_excecoes/02recursos/` (getMessage, printStackTrace)

## Referências

- [Oracle Java Documentation - Exception Handling](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [Java Point - Exception Handling](https://www.javatpoint.com/exception-handling-in-java)

