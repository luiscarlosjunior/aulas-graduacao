# Variáveis em Java
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
