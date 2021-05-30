# Estrutura de repetição
São estruturas que permitem que um bloco de código fique se repetindo diversas vezes, e alterando seu comportamento durante cada repetição. Qualquer que seja a estrutura de repetição, ela contém quatro elementos fundamentais: inicialização, condição, corpo e iteração. A inicialização compõe-se de todo código que determina a condição inicial da repetição. A condição é uma expressão booleana avaliada após cada leitura do corpo e determina se uma nova leitura deve ser feita ou se a estrutura de repetição deve ser encerrada. O corpo compõe-se de todas as instruções que são executadas repetidamente. A iteração é a instrução que deve ser executada depois do corpo e antes de uma nova repetição.

>Observação: Sempre se atente em qual será a sua condição de parada, pois somente assim o laço saberá quando parar, assim evitando criar um **laço infinito**.

## Laços de repetição em Java
A linguagem de programação Java fornece os seguintes tipos de loop para lidar com os requisitos de looping. Clique nos links a seguir para verificar seus detalhes.

|Sr.No.	|Loop & Description
|----   |----
|1	|**while loop**: Repete uma declaração ou grupo de declarações enquanto uma determinada condição for verdadeira. Ele testa a condição antes de executar o corpo do loop.
|2	|**for loop**: Executa uma sequência de instruções várias vezes e abrevia o código que gerencia a variável de loop.
|3	|**do...while loop**: Como uma instrução while, exceto que testa a condição no final do corpo do loop.

----
## Estrutura de repetição While...loop | Do...While

Parte do texto abaixo foi tirado da documentação da oracle neste link: [Oracle](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/while.html#:~:text=The%20Java%20programming%20language%20also%20provides%20a%20do-while,bottom%20of%20the%20loop%20instead%20of%20the%20top.)

Uma instrução de loop de while na linguagem de programação Java executa repetidamente uma instrução de destino, desde que uma determinada condição seja verdadeira. Veja a sintaxe em genérica em seguida:

```java
while(<condicao>)
{
    <conjunto de instrução>
}
```

A declaração avalia a expressão, que deve retornar um valor. Se a expressão avaliar **true**, a instrução executará a instrução(s) no bloco. A declaração continua testando a expressão e executando seu bloqueio até que a expressão avalie. O uso da instrução para imprimir os valores de 1 a 10 pode ser realizado como no seguinte programa:



```java
// Faz um laço que irá exibir um número de 0 até 10
public static void main(String[] args) {

    int contador = 0;
    while (contador <= 10) {
        System.out.println(contador);
        contador++;
    }
}
```
> Resultado
>>0
1
2
3
4
5
6
7
8
9
10

---

Você pode implementar um loop infinito usando a instrução da seguinte forma: while

```java
while (true){
    // Seu código
}
```
### A linguagem de programação Java também fornece a seguinte forma: do-while

```java
do {
     statement(s)
} while (expression);

```

A diferença entre e é que avalia sua expressão na parte inferior do loop em vez da parte superior. Portanto, as declarações dentro do bloco são sempre executadas pelo menos uma vez, como mostrado no seguinte programa DoWhileDemo: do-whilewhiledo-whiledo

```java
class DoWhileExemplo {
    public static void main(String[] args){
        int count = 1;
        do {
            System.out.println("Contador: " + count);
            count++;
        } while (count < 11);
    }
}

```
---

## Estrutura de repetição for

A declaração fornece uma maneira compacta de iterar sobre uma gama de valores. Os programadores frequentemente se referem a ele como o "for loop" devido à maneira como ele gira repetidamente até que uma condição específica seja satisfeita. A forma geral da declaração pode ser expressa da seguinte forma:

```java
for (iniciador; condição de término;incremento) {
    statement(s)
}
```

### Ao usar este tipo de instrução, tenha em mente que:


* A expressão de _**inicialização**_ inicia o loop; e é executado uma vez, como o loop começa;
* Quando a expressão de _**condição de término**_ avalia, o loop termina;
* A expressão de incremento é invocada após cada iteração através do loop; é perfeitamente aceitável para esta expressão incrementar ou decretar um valor.

O programa a seguir, ForDemo, usa a forma geral da declaração para imprimir os números de 1 a 10 para a saída padrão:

```java
class ForDemo {
    public static void main(String[] args){
         for(int i=1; i<11; i++){
              System.out.println("Contador: " + i);
         }
    }
}
```

>Observe como a variável dentro da expressão de inicialização. O escopo dessa variável estende-se desde sua declaração até o final do bloco. 

Se a variável que controla uma declaração não for necessária fora do loop, é melhor declarar a variável na expressão de inicialização. Os nomes , , e são frequentemente usados para controlar loops; declará-los dentro da expressão de inicialização limita sua vida útil e reduz erros.

**As três expressões do loop são opcionais**; um loop infinito pode ser criado da seguinte forma:

```java
// infinite loop
for ( ; ; ) {
    // your code goes here
}
```

A instrução também tem outra forma projetada para iteração através de Coleções e arrays Este formulário às vezes é referido como o aprimorado para a declaração, e pode ser usado para tornar seus loops mais compactos e fáceis de ler. Para demonstrar, considere o seguinte vetor, que contém os números de 1 a 10:

```java
int[] numbers = {1,2,3,4,5,6,7,8,9,10};
```

O programa a seguir, EnhancedForDemo, usa o __**enhanced**__ para loop através do array:

```java
class EnhancedForDemo {
    public static void main(String[] args){
         int[] numbers = 
             {1,2,3,4,5,6,7,8,9,10};
         for (int item : numbers) {
             System.out.println("Contador: " + item);
         }
    }
}
```

Neste exemplo, a variável detém o valor atual da matriz de números. A saída deste programa é a mesma de antes.

É recomendado utilizar esta forma da declaração em vez da forma geral sempre que possível.

---

## Mais exemplos de laços de repetição 

```java
	public static void exemploArrayMultiInt() {
        int[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };
        for (int i = 0; i < myNumbers.length; ++i) {
          for(int j = 0; j < myNumbers[i].length; ++j) {
            System.out.println(myNumbers[i][j]);
          }
        }
    }
    
    public static void exemploForContinue() {
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
              continue;
            }
            System.out.println(i);
        }
    }
    
    public static void exemploForLoop() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }     
    }
    
    public static void exemploForEachLoop() {
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        for (String i : cars) {
          System.out.println(i);
        }
    }
    
    public static void exemploWhileLoop() {
        int i = 0;
        while (i < 5) {
          System.out.println(i);
          i++;
        }  
    }
    
    public static void exemploDoWhile() {
        int i = 0;
        do {
          System.out.println(i);
          i++;
        }
        while (i < 5);
    }
```

---

# Referências

[Documentação Oracle](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/flow.html)