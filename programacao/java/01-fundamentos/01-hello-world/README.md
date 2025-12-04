# Hello World - Primeiro Programa Java

Este é o tradicional primeiro programa que todo programador Java escreve. Vamos entender cada parte dele.

## 🎯 Objetivo

Compreender a estrutura básica de um programa Java e como executá-lo.

## 📋 Conceitos Abordados

- Estrutura básica de uma classe Java
- Método `main` como ponto de entrada
- Comando `System.out.println()` para saída de dados
- Compilação e execução de programas Java

## 🖥️ Exemplos

### [HelloWorld.java](HelloWorld.java)
O programa mais simples possível em Java.

### [HelloWorldDetalhado.java](HelloWorldDetalhado.java)
Versão com comentários explicativos sobre cada parte do código.

### [HelloWorldInterativo.java](HelloWorldInterativo.java)
Versão que interage com o usuário usando Scanner.

## 🚀 Como Executar

1. **Compilar**:
   ```bash
   javac HelloWorld.java
   ```

2. **Executar**:
   ```bash
   java HelloWorld
   ```

## 💡 Explicação do Código

```java
public class HelloWorld {           // Declaração da classe
    public static void main(String[] args) {  // Método principal
        System.out.println("Hello, world");   // Imprime na tela
    }
}
```

- **public class**: Define uma classe pública chamada HelloWorld
- **public static void main**: Método especial onde o programa inicia
- **String[] args**: Parâmetros da linha de comando
- **System.out.println()**: Imprime texto na saída padrão

## 🔍 Pontos Importantes

- O nome da classe deve ser igual ao nome do arquivo
- Java é case-sensitive (diferencia maiúsculas de minúsculas)
- Toda instrução deve terminar com ponto e vírgula (;)
- Chaves {} delimitam blocos de código

## 📝 Exercícios

1. Modifique o programa para imprimir seu nome
2. Adicione mais linhas de saída
3. Crie um programa que exibe uma mensagem de boas-vindas

## 🔗 Próximo Passo

Continue para [Tipos de Dados](../02-tipos-dados/) para aprender sobre variáveis e tipos de dados em Java.