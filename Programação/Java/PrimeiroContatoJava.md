
# Primeiro Contato com Java
### Observação: Os conceitos tratados aqui diz respeito até o Java SE versão 8 (1.8), pode haver alterações nas versões mais novas, atualmente o Java se encontra da versão 14.

Para iniciar com a linguagem Java, será apresentado uma classe Java que escreve uma mensagem na tala. Apesar do exemplo ser simples, ele contém os itens fundamentais para a criação de qualquer aplicação em Java: elaboração do código, compilação e execução. Esses itens serão seguidos durante o processo de elaboração das aplicações. 

## Exemplo de "Olá, mundo!"

```java
    public static void main(String[] args) {
        System.out.println("Olá, mundo!");
    }
```


Todo programa em Java inicia-se com a palavra reservada **class** seguida do nome da classe (Geralmente é o mesmo nome do arquivo). Com a convenção definida pela Sun, todo nome de classe inicia-se com a letra maiúscula. Para mais detalhes de [convenção da linguagem](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)

Um par de chaves envolve todo o código da classe - sempre um programa em Java possui um classe que envolve o código.

Uma classe em Java é composta por métodos que podem conter outras estruturas de programa. Toda classe executável, isto é, toda classe será interpretada e executada deve, obrigatóriamente, possuir o **método main** (principal), que é invocado quando a classe é executada.

No exemplo acima, quando executado será invocado o método **main** que possui uma instrução de envio de mensagem para a tela (System.out.println). Não é exatamente uma instrução e sim uma classe da linguagem especializada em saída de dados.

>## Observação
> A linha _public static void main(String args[])_ aparece em todas as classes executáveis nesse mesmo formato. Sendo assim, todas as aplicações exemplificadas neste livro possuem essa linha como default.
>

A seguir é feito uma análise um pouco mais detalhada dessa linha. Mesmo que alguns conceitos podem paracer avançado, ao longo de outros arquivos iremos entrar com mais detalhes.

## public 

É um qualificador do método que indica que ele é acessivel externamente a essa classe. Isso é útil quando uma classe necessita utilizar alguma funcionalidade de outra classe, característica muito comum.

## static

É um qualificador que indica que o método deve ser compartilhado por todos os objetos que são criados a partir dessa classe. Isso é um assunto para orientação a objetos.

## void

É o valor de retorno do método. Quando não há nenhum valor, ela retorna void, uma espécie de valor vazio que deve ser esecializado. 

## main

Este é o nome do método que indica ao compilador o início do programa. É o método principal do programa, é por ele que o compilador java sabe por onde começar a executar um programa.

## (String args[])

É o argumento do método principal (main) e, por consequência, do programa todo; ele é um vetor de _strings_ formado por todos os argumentos passados ao programa na linha de comando do sistema operacional quando o programa é invocado.

## { ... }

"Abre-chaves" e "Fecha-chaves" delimitam um bloco de código semelhante ao inicio e fim.

----

Mais detalhes:
[Java exemplos](https://www.tutorialspoint.com/java/java_documentation.htm)

[Documentação Javadoc Oracle](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)

[Documentação Java](https://docs.oracle.com/javase/tutorial/tutorialLearningPaths.html)


