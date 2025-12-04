# Hello World - Primeiro Programa em Java

## 📋 Visão Geral

O programa "Hello World" é tradicionalmente o primeiro programa que todo programador escreve ao aprender uma nova linguagem de programação. Este simples programa demonstra a estrutura fundamental de uma aplicação Java e introduz conceitos essenciais da linguagem.

## 🎯 Objetivos de Aprendizado

Ao completar este tópico, você será capaz de:

- ✅ Compreender a estrutura básica de um programa Java
- ✅ Identificar e explicar cada componente do código
- ✅ Compilar e executar um programa Java simples
- ✅ Entender o papel do método `main()`
- ✅ Usar a classe `System` para saída de dados
- ✅ Trabalhar com argumentos da linha de comando

## 📚 Conceitos Fundamentais

### 1. Estrutura de uma Classe Java

```java
public class HelloWorld {
    // Conteúdo da classe
}
```

**Elementos importantes:**
- **`public`**: Modificador de acesso que torna a classe visível para outras classes
- **`class`**: Palavra-chave que define uma classe em Java
- **`HelloWorld`**: Nome da classe (deve corresponder ao nome do arquivo)
- **`{ }`**: Delimitadores que definem o escopo da classe

### 2. O Método Main

```java
public static void main(String[] args) {
    // Código executável
}
```

**Análise detalhada:**
- **`public`**: Permite que a JVM acesse o método de fora da classe
- **`static`**: Permite chamar o método sem criar uma instância da classe
- **`void`**: Indica que o método não retorna valor
- **`main`**: Nome obrigatório do método de entrada
- **`String[] args`**: Array de strings para receber argumentos da linha de comando

### 3. Saída de Dados com System.out

Java fornece várias formas de exibir informações no console:

#### `System.out.println()`
```java
System.out.println("Mensagem com quebra de linha");
```
- Imprime a mensagem e adiciona uma quebra de linha no final

#### `System.out.print()`
```java
System.out.print("Mensagem sem quebra de linha");
```
- Imprime a mensagem sem adicionar quebra de linha

#### `System.out.printf()`
```java
System.out.printf("Olá, %s! Você tem %d anos.\n", "João", 25);
```
- Permite formatação avançada das mensagens

### 4. Comentários em Java

```java
// Comentário de linha única

/*
 * Comentário de múltiplas linhas
 * Pode ocupar várias linhas
 */

/**
 * Comentário de documentação (Javadoc)
 * Usado para gerar documentação automática
 * @author Nome do autor
 * @version Versão do código
 */
```

## 🛠️ Processo de Compilação e Execução

### Passo 1: Compilação
```bash
javac HelloWorld.java
```
- O compilador Java (`javac`) converte o código fonte (.java) em bytecode (.class)
- Gera o arquivo `HelloWorld.class`

### Passo 2: Execução
```bash
java HelloWorld
```
- A JVM (Java Virtual Machine) executa o bytecode
- Procura e executa o método `main()`

### Passando Argumentos
```bash
java HelloWorld argumento1 argumento2 "argumento com espaços"
```
- Os argumentos são recebidos no array `args` do método main

## 💡 Conceitos Importantes

### Convenções de Nomenclatura
- **Classes**: PascalCase (primeira letra maiúscula) - `HelloWorld`
- **Métodos e variáveis**: camelCase (primeira letra minúscula) - `main`, `numeroDeUsuarios`
- **Constantes**: UPPER_SNAKE_CASE - `MAX_USUARIOS`

### Boas Práticas
1. **Nome do arquivo**: Deve ser idêntico ao nome da classe
2. **Indentação**: Use espaços ou tabs consistentemente (recomendado: 4 espaços)
3. **Comentários**: Documente o propósito do código, não apenas o que ele faz
4. **Organização**: Uma classe pública por arquivo

## 🔧 Possíveis Erros e Soluções

### Erro: "Could not find or load main class HelloWorld"
**Causa**: Nome do arquivo não corresponde ao nome da classe
**Solução**: Verificar se o arquivo se chama `HelloWorld.java` e a classe é `public class HelloWorld`

### Erro: "main method not found"
**Causa**: Assinatura incorreta do método main
**Solução**: Verificar se a assinatura é exatamente: `public static void main(String[] args)`

### Erro: "illegal character"
**Causa**: Caracteres especiais no código (ex: aspas curvas copiadas de documentos)
**Solução**: Reescrever o código usando apenas caracteres ASCII padrão

## 🧪 Exercícios Práticos

### Nível Iniciante
1. Modifique o programa para exibir seu nome
2. Adicione mais mensagens de saudação
3. Teste passando diferentes argumentos pela linha de comando

### Nível Intermediário
1. Crie um programa que conte quantos argumentos foram passados
2. Implemente uma verificação se argumentos foram fornecidos
3. Adicione comentários Javadoc completos

### Nível Avançado
1. Crie um programa que processe argumentos específicos (ex: --help, --version)
2. Implemente validação dos argumentos de entrada
3. Adicione formatação colorida na saída (usando códigos ANSI)

## 📖 Próximos Passos

Após dominar o Hello World, você estará pronto para:
- [Tipos de Dados](../tipos_de_dados/) - Aprender sobre variáveis e tipos primitivos
- [Entrada de Dados](../entrada_dados/) - Receber dados do usuário
- [Operadores](../operadores/) - Realizar cálculos e comparações

## 📚 Recursos Adicionais

- [Oracle Java Documentation](https://docs.oracle.com/javase/tutorial/getStarted/index.html)
- [Java Platform API Specification](https://docs.oracle.com/en/java/javase/17/docs/api/)
- [Java Code Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html)

---

**💡 Dica**: O Hello World pode parecer simples, mas dominar seus conceitos é fundamental para o sucesso em Java. Pratique variações e experimente modificações!