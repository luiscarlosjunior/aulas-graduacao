# Resumo C - Introdução da linguagem

A linguagem C é uma linguagem de propósito geral, desenvolvido em 1972 por Dennis M. Ritchie

## Estrutura do programa

Antes de estudarmos a construção de um algoritmo em C, vamos entender como funciona a estrutura do C
. O C consiste em basicamente nas seguintes partes:

-   Comandos de préprocessamento
-   Funções
-   Variáveis
-   Estruturas e expressões
-   Comentários

Vamos dar uma olhada no código de "Hello World"

```c
#include <stdio.h>

int main() {
    // Meu primeiro programa
    printf("Olá, mundo");
    
    return 0;
}
```
### Destrinchando o Hello World

- A primeira linha do programa _#include <stdio.h>_ é um comando préprocessador, 
no qual diz ao compilador que ele deve incluir o arquivo stdio.h na compilação atual.
- A linha ```int main()``` é a função principal onde o programa sabe por onde começar a executar
- A próxima linha ```// Meu pri... ``` será ignorada pelo compilador e server para adicionar informação ao programa.
- A próxima linha ```printf(...)``` é uma função do C no qual é responsável por exibir a mensagem "Hello world" na tela
- A penúltima linha ```return 0;``` termina a função _main()_ e returna o valor 0 


## Conceitos Básicos

## Tokens em C
Um programa escrito em C consiste em vários _tokens_ e um _token_ nada mais é do que uma *palavra-chave, um identificador, uma constante, uma string, ou um simbolo*.   
Por exemplo, veja os seguintes tokens:

```c
printf("Hello, world! \n");
```

Se o compilador fosse analisar essa linha acima, os _tokens_ individuais seriam:

```c
printf
(
    "Hello, World! \n"
)
;
```

Como pode ser visto anteriormente, em uma fase específica de compilação ele irá separar os tokens para poder fazer as análises e verificar se estão conforme a definição da linguagem.   

## Ponto e virgula

Em C, ponto e virgula ```;``` é uma estrutara que é utilizada para terminar uma declaração de estrutura individual. O ponto e virgula indica o fim de uma entidade lógica
Por exemplo:
```c
printf("Hello, world! \n");
return 0;
```

## Comentários

## Identificadores

## Palavras-Chaves

## Espaços em Branco
