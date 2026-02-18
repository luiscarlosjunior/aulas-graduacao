# O que é java
Java é uma linguagem de programação de alto nível originalmente desenvolvida pela Sun Microsystems e lançada em 1995. 
Java é executado em uma variedade de plataformas, como Windows, Mac OS, e as várias versões do UNIX. 
Esta referência irá levá-lo através de abordagens simples e práticas enquanto aprende a linguagem de programação Java.

## Por que aprender programação java?
Java é uma OBRIGAÇÃO para estudantes e profissionais que trabalham para se tornar um grande Engenheiro de Software especialmente 
quando eles estão trabalhando em Domínio de Desenvolvimento de Software. Vou listar algumas das principais vantagens de aprender programação Java:

## Características da Linguagem Java

  A linguagem Java possui diversas características que podem gerar páginas e páginas de conceitos, entretanto esses detalhes não serão discutidos aqui. 
  As principais características aqui enfocadas serão: 

**Orientado para objetos** − Em Java, tudo é um objeto. Java pode ser facilmente estendido, uma vez que é baseado no modelo objeto. Esses objetos podem simular
o mundo real, 

**Plataforma independente** − Ao contrário de muitas outras linguagens de programação, incluindo C e C++, quando java é compilado, ele não é compilado em máquina específica da plataforma, mas em código de byte independente da plataforma. Este código byte é distribuído pela web e interpretado pela Máquina Virtual (JVM) em qualquer plataforma em que esteja sendo executado. Se você entende o conceito básico de OOP Java, seria fácil dominar.

**Seguro** − Com o recurso seguro do Java, ele permite desenvolver sistemas livres de vírus e sem adulterações. As técnicas de autenticação são baseadas em criptografia de chave pública.

**Neutro em arquitetura** − O compilador Java gera um formato de arquivo de objeto neutro em arquitetura, o que torna o código compilado executável em muitos processadores, com a presença do sistema de tempo de execução Java.

**Portátil** − Ser neutro em arquitetura e não ter aspectos dependentes de implementação da especificação torna Java portátil. O compilador em Java é escrito no ANSI C com um limite de portabilidade limpa, que é um subconjunto POSIX.

**Robusto** − Java faz um esforço para eliminar situações propensas a erros, enfatizando principalmente na verificação de erros de tempo de compilação e verificação do tempo de execução.

## Primeiro Contato com Java.
Nosso primeiro exemplo em Java é o famoso Hello, World. Nesse sentido, será apresentado uma classe java que escreve uma mensagem na tela. Apesar do exemplo simples, ele contém os itens fundamentais para a criação de qualquer aplicação em Java: elaboração do código, compilação e execução. 

```java
public class OlaMundo {

   // Este é o primeiro programa.
   public static void main(String []args) {
      System.out.println("Olá, mundo"); // Exibe Olá mundo no console
   }
}
```
Todo programa Java começa com a palavra reservada **class** seguida do nome da classe. Como convenção, todo nome de classe inicia-se com letra maiúscula. Aqui iremos manter a convenção usada.

[Convenção de código java Oracle](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf)

  Uma classe em Java é composta por métodos (considerados funções ou _procedures_ em outras linguagens de programação) que podem conter outras estruturas de programa. Toda classe executável, isto é, toda classe que será interpretada e executada deve, obrigatóriamente, possuir o **método main** (principal), que é invocado quando a classe é executada. 
  
  Neste caso, quando a classe for executada, será invocado o método _main_ que possui duas instruções para envio de mensagens na tela (System.out.println). Não é exatamente uma instrução e sim uma classe da linguagem especializada em saída de dados.
  
 ### Observação
 > A linha public static void main (String args[]) aparece em todas as classes executáveis nesse mesmo formato.
  
## Linha principal

  A linha do método principal possui o seguinte formato: _public static void main(String args[])_; praticamente todas as aplicações têm essa linha. A única coisa que pode ser alterada na linha é a variável args que pode receber outro nome de acordo com o desejo do programador. A seguir, será feita uma análise mais detalhada dessa linha. <span style="color:blue">some *blue* text</span>
  
## public 

  É um qualificador do método (pode ser usado em qualquer método, não apenas no **_main_**) que indica que ele é acessivel externamente a essa classe. Isso é útil quando uma classe necessita utilizar alguma funcionalidade de outra classe, característica muito comum na linguagem Java.

## static

  Qualificador que indica que o método deve ser compartilhado por todos os objetos que são criados a partir dessa classe sem precisar fazer um objeto. Static é “de classe”, ou seja, uma variável static da classe.

## void

  É o valor de retorno do método. Quando não há nenhum valor, ela retorna _void_, uma espécie de valor que deve ser especializado. Seu objetivo é simplesmente representar o tipo de retorno vazio como classe e conter um valor público Class<Void>. 

## main

  Este é o nome do método que indica ao compilador do inicio do programa. É o método principal, em todas as variáveis, argumentos e instruções são interpretados e processados para a execução do programa.

## (String args[])
  É o argumento do método principal (main) e, por consequência, do programa todo. Esse comando representa os argumentos passados para ao programa na linha de comando do sistema operacional quando o programa é invocado.
  
## { ... }

  "Abre-chaves" e "fecha-chaves" delimitam um bloco de código semelhante ao BEGIN e END de outras linguagens como Pascal. 
  
### Observação
> Java é sensível maiúscula/minúscula das palavra usadas. Logo, uma variável com o nome de "Numero" é diferente de uma com o nome de "numero".
> 

## Referências
[Java doc](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf)

