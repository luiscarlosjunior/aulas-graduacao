# Operadores
### Observação: Os conceitos tratados aqui diz respeito até o Java SE versão 8 (1.8), pode haver alterações nas versões mais novas, atualmente o Java se encontra da versão 14.
Como em toda a linguagem de programação existem diversos tipos de operadores que, em resumo, são simbolos que o compilador usa para interpretar algum tipo de computação lógica ou aritmética. Alguns dos operadores são:

1. Operadores Aritméticos
2. Operadores Relacionais
3. Operadores Lógicos
4. Operadores de atribuição

## Operadores Aritméticos

Operadores aritméticos são operadores que são responsável por permitir realizar computação aritméticas.

|**Função**         | **Sinal** | **Exemplo** |
|----               |----       |----        |
|Adição             | +         | x + y      |
|Subtração          | -         | x - y      |
|Mutiplicação       | *         | x * y      |
|Divisão            | /         | x / y      |
|Resto Divisão      | %         | x % y      |
|Sinal Negativo     | -         | -y         |
|Sinal Positivo     | +         | +x         |
|Incremento unitário| ++        | ++x        |
|Decremento unitário| --        | --x        |

## Exemplo de declaração de um operadores

```java
    public static void main(String[] args) {

        // Declaração e inicialização de variáveis
        int x = 10; int y = 3;
        // Varias operações com as variáveis
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("-x = " + (-x));
        System.out.println("x/y = " + (x/y));
        System.out.println("Resto de x por y = " + (x%y));
        System.out.println("Inteiro de x por y = " + (int)(x/y));
        System.out.println("x + 1 = " + (++y));
    }
```
obs: O primeiro sinal de + é um operador de concateção.

## Operadores Relacionais

Operadores relacionais possibilitam comparar calores ou expressões, retornando um resultado lógico verdadeiro ou falso.

|**Função**         | **Caractere(s) utilizados**   | **Exemplo** |
|----               |----                           |----         |
|Adição             | ==                            | x == y      |
|Subtração          | !=                            | x != y      |
|Mutiplicação       | >                             | x > y       |
|Divisão            | >=                            | x >= y      |
|Resto Divisão      | <                             | x < y       |
|Sinal Negativo     | <=                            | x <= y      |

## Exemplo de declaração relacionais
```java
    public static void main(String[] args) {

        // Declaração e inicialização de variáveis
        int x = 10; int y = 3;
        // Varias operações com as variáveis
        System.out.println("x == y " + (x == y));
        System.out.println("x != y " + (x != y));
        System.out.println("x > y " + (x > y));
        System.out.println("x >= y " + (x >= y));
        System.out.println("x < y " + (x < y));
        System.out.println("x <= y " + (x <= y));
    }
```

## Operadores Lógicos
São operadores que permitem avaliar o resultado lógico de diferentes operações aritméticas em uma expressão.

|**Função**         | **Caractere(s) utilizados**   | **Exemplo** |
|----               |----                           |----         |
|E lógico ou AND    | &&                            | x && y      |
|Ou lógico ou OR    | ||                            | x || y      |
|Negação ou NOT     | !                             | !x          |

## Exemplo de declaração operadores lógicos
```java
    public static void main(String[] args) {

        // Declaração e inicialização de variáveis
        bool x = true; bool y = false;
        // Varias operações com as variáveis
        System.out.println("x && y " + (x && y));
        System.out.println("x || y " + (x || y));
        System.out.println("!x " + (!x ));
    }
```

## Operadores de atribuição

Os operadores de atribuição são usados para atribuir valores a variáveis.
No exemplo abaixo, estamos atribuindo um valor dez a uma variável do tipo int.

```java
int valor = 10;
```

## Abaixo uma tabela com os operadores de atribuição

|**Operado**        | **Exemplo**   | **Mesmo que** |
|----               |----           |----           |
| =                 | x = 5         | x = 5         |
| +=                | x += 5        | x = x + 5     |
| -=                | x -= 5        | x = x - 5     |
| *=                | x *= 5        | x = x * 5     |
| /=                | x /= 5        | x = x / 5     |
| &=                | x &= 5        | x = x & 5     |
| \|=                | x |= 5        | x = x | 5     |
| ^=                | x ^= 5        | x = x ^ 5     |
| >>=               | x >>= 5       | x = x >> 5    |
| <<=               | x <<= 5       | x = x << 5    |

Mais detalhes: [Java Point](https://www.javatpoint.com/operators-in-java#:~:text=There%20are%20many%20types%20of%20operators%20in%20Java,Operator%2C%207%20Ternary%20Operator%20and%208%20Assignment%20Operator.)
