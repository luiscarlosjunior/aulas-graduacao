# Hello World - Seu Primeiro Programa Java

Este é o tradicional programa "Hello World" - o primeiro programa que todo programador escreve ao aprender uma nova linguagem.

## 🎯 Objetivo

Compreender a estrutura básica de um programa Java e como compilar e executar código Java.

## 📋 Conceitos Fundamentais

### Estrutura Básica de uma Classe Java

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, world");
    }
}
```

**Elementos importantes:**
- `public class HelloWorld`: Declaração da classe pública
- `public static void main(String[] args)`: Método principal (ponto de entrada)
- `System.out.println()`: Comando para imprimir texto no console

### O Método main()

O método `main()` é especial porque:
- É o **ponto de entrada** de qualquer aplicação Java
- Deve ser `public` (acessível de qualquer lugar)
- Deve ser `static` (pode ser chamado sem criar uma instância da classe)
- Retorna `void` (não retorna nenhum valor)
- Recebe um array de `String` como parâmetro (argumentos da linha de comando)

### System.out.println()

- `System`: Classe que representa o sistema
- `out`: Campo estático que representa a saída padrão (console)
- `println()`: Método que imprime uma linha e adiciona quebra de linha

## 🚀 Como Executar

### 1. Compilação
```bash
javac HelloWorld.java
```
Este comando gera o arquivo `HelloWorld.class` (bytecode Java)

### 2. Execução
```bash
java HelloWorld
```
Este comando executa o bytecode na JVM (Java Virtual Machine)

### 3. Resultado Esperado
```
Hello, world
```

## 🔍 Análise do Código

### Nome da Classe e Arquivo
- O nome da classe (`HelloWorld`) deve ser **exatamente** igual ao nome do arquivo (`HelloWorld.java`)
- Java é case-sensitive (diferencia maiúsculas de minúsculas)

### Convenções de Nomenclatura
- Classes: PascalCase (`HelloWorld`, `MinhaClasse`)
- Métodos e variáveis: camelCase (`meuMetodo`, `minhaVariavel`)

## 💡 Experimentos Sugeridos

1. **Modificar a mensagem**: Altere o texto dentro de `println()`
2. **Múltiplas mensagens**: Adicione mais comandos `System.out.println()`
3. **Usar `print()` vs `println()`**: Veja a diferença entre os dois métodos
4. **Argumentos da linha de comando**: Tente imprimir `args[0]` e execute com parâmetros

### Exemplo com argumentos:
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, world");
        if(args.length > 0) {
            System.out.println("Olá, " + args[0] + "!");
        }
    }
}
```

Execute com: `java HelloWorld "Seu Nome"`

## ❗ Erros Comuns

1. **Nome da classe diferente do arquivo**: Erro de compilação
2. **Esquecer o método main()**: Erro de execução
3. **Sintaxe incorreta**: Pontos e vírgulas, chaves, parênteses

## 📚 Conceitos Relacionados

- **JVM (Java Virtual Machine)**: Onde o código Java é executado
- **Bytecode**: Código intermediário gerado após compilação
- **Compilação**: Processo de transformar código fonte em bytecode
- **Classpath**: Caminho onde a JVM procura as classes

---

**Próximo**: [Tipos de Dados](../01_Tipos%20de%20dados/) - Aprenda sobre variáveis e tipos primitivos em Java.