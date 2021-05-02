# Variáveis em Java
### Observação: Os conceitos tratados aqui diz respeito até o Java SE versão 8 (1.8), pode haver alterações nas versões mais novas, atualmente o Java se encontra da versão 14.
Variáveis são formas de armazenar dados na memória do computador. Assim como toda linguagem de programação, java tem formas de armazenas os tipos de dados.
Uma variável é assinada com um tipo de data. Existem três tipos de variáveis em java: **local, global e estática**.

Variável é o nome de uma área reservada de alocação de memória. Em outras palavras, é um nome de memória alocada. É uma combinação de "vary + able" isso significa que seu valor pode ser modificado.

## Exemplo de declaração de uma variável

```java
    public static void main(String[] args) {

        //declarando uma variável numérica inteira
        int valor;
        //atribuindo o valor 100 para a variável
        valor = 100;

        //imprimindo o valor da variável na saída padrão
        System.out.println(valor);
    }
```

## Exemplo de Variáveis em Java

<details>
  <summary> Código fonte de exemplo </summary>  
  
  ``` java
  public static void exemplosVariaveis() {
    	
    	  // Exemplo número inteiro
        int idade = 5;
        // Exemplo número de precisão
        float altura = 1.85f;
        // Exemplo número de precisão dupla (até 15 casas após a virgula)
        double numeroPI = 3.1415926562;
        // Exemplo de do tipo Char
        char umaLetra = 'D';
        // Exemplo de do tipo Char
        boolean souPessoa = true;
        // Exemplo de do tipo Char
        String meuTexto = "Hello";     
                
        // Imprimindo os valores
        System.out.println("Minha idade é " + idade);
        System.out.println("Minha altura é " + altura);
        System.out.println("O numero PI é aproximadamente " + numeroPI);
        System.out.println("Eu sou uma pessoa ? " + souPessoa);
        System.out.println("Um texto qualquer " + meuTexto);
    }
  
  ```
</details>

## Declaração de constantes
Constantes são variáveis especiais que quando forem declaradas obrigatóriamente o valore terá que ser atribuido e uma vez atribuido este valor não altera.
Em java não existe variáveis do tipo constantes, o que existe é um tipo de variável especial com comportamento parecido. 

Java possui variáveis somente leitura, ou como valor finalizado. Em java, essa variável é chamada de final.
Veja alguns exemplos: 

```java
final double PI = 3.1415;
final BigDecimal taxa;
```
Ao contrário da definição de variáveis constantes, não é necessário declarar e, logo em seguida, atribuir valor para a variável. Por exemplo:

```java
// Declarando a variável
final double PI;
.
. // Linhas de códigos
.
// Atribuindo valor à variável
PI = 3.1415;
```

Mais detalhes veja em: [Stackoverflow](https://pt.stackoverflow.com/questions/354519/como-criar-constantes-em-java)
[Constante em Java](https://www.javatpoint.com/java-constant)

## Comentários

Comentários são linhas adicionadas ao programa que servem para facilitar o entendimento por parte do programador, ou ainda por outro pessoa que irá trabalhar em seu programa.
As linhas de comentários são desconsiderados pelo compilador.
Em java, há três tipos de comentários: linha única, múltiplas linhas e de documentação.

* Para inserir linhas únicas utiliza-se //
* Para inserir mútiplas linhas utiliza-se /* para iniciar e */ para fechar
* Para inserir comentários para gerar uma documentação é usado /** e é encerrado com */. Para realizar a documentação o Java utiliza-se de um utilitário chamado **Javadoc** 

### Veja alguns exemplos

```java
// Declarando a variável
/** Exemplo de comentário para documentação
* Podemos colocar tags para ter informações do app 
*  @author  Luis Caparroz
* @version 1.0
* @since   2021 
*/
public static void exemplosVariaveis() {
    	
    	 // Exemplo de um comentário de linha única
        int idade = 5;
        
        /* Exemplo de comentário de múltipla linha
            float altura = 1.85f;
            double numeroPI = 3.1415926562;
            char umaLetra = 'D';
        */
        
        // Imprimindo os valores
        System.out.println("Minha idade é " + idade);
        /*
            System.out.println("Minha altura é " + altura);
            System.out.println("O numero PI é aproximadamente " + numeroPI);
        */
    }

```

Mais detalhes:

[Java exemplos](https://www.tutorialspoint.com/java/java_documentation.htm)

[Documentação Javadoc Oracle](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)

[Documentação Java](https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html)


