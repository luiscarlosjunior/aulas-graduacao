# Introdução à Linguagem Java

## História da Linguagem Java

Java foi criada em 1995 pela Sun Microsystems (posteriormente adquirida pela Oracle) com o objetivo de ser uma linguagem "Write Once, Run Anywhere" (WORA). A linguagem foi projetada por James Gosling e sua equipe para ser:

- **Simples**: Sintaxe limpa e familiar para programadores C/C++
- **Orientada a Objetos**: Paradigma principal de programação
- **Portável**: Execução em diferentes sistemas operacionais através da JVM
- **Segura**: Controle rigoroso de memória e verificação de tipos
- **Robusta**: Tratamento de exceções e gerenciamento automático de memória

### Evolução das Versões
- **Java 1.0 (1996)**: Primeira versão comercial
- **Java 2 (1998)**: Introdução das coleções e Swing
- **Java 5 (2004)**: Generics, anotações, autoboxing
- **Java 8 (2014)**: Expressões lambda e Streams
- **Java 11 (2018)**: LTS (Long Term Support)
- **Java 17 (2021)**: LTS atual com records e pattern matching

## Sintaxe Básica

### Estrutura de um Programa Java

Todo programa Java segue uma estrutura básica:

```java
// Declaração do pacote (opcional)
package com.exemplo;

// Importações (opcional)
import java.util.Scanner;

// Declaração da classe pública
public class MinhaClasse {
    
    // Método principal - ponto de entrada do programa
    public static void main(String[] args) {
        // Código do programa
        System.out.println("Olá, Mundo!");
    }
}
```

### Tipos de Dados Primitivos

Java possui 8 tipos primitivos:

| Tipo | Tamanho | Valor Padrão | Exemplo |
|------|---------|--------------|---------|
| byte | 8 bits | 0 | `byte b = 127;` |
| short | 16 bits | 0 | `short s = 32000;` |
| int | 32 bits | 0 | `int i = 100000;` |
| long | 64 bits | 0L | `long l = 999999999L;` |
| float | 32 bits | 0.0f | `float f = 3.14f;` |
| double | 64 bits | 0.0d | `double d = 3.14159;` |
| char | 16 bits | '\u0000' | `char c = 'A';` |
| boolean | 1 bit | false | `boolean flag = true;` |

### Variáveis e Constantes

```java
// Declaração de variáveis
int idade = 25;
String nome = "João";
double salario = 2500.50;

// Constantes (final)
final double PI = 3.14159;
final String EMPRESA = "MinhaEmpresa";
```

## Exemplos Práticos

### 1. Exemplo Simples - Hello World

Veja o exemplo no arquivo [HelloWorldJava.java](HelloWorldJava.java)

### 2. Exemplo com Entrada de Dados

Veja o exemplo no arquivo [EntradaDados.java](EntradaDados.java)

### 3. Exemplo com Orientação a Objetos

Veja o exemplo no arquivo [PessoaExemplo.java](PessoaExemplo.java) e [Pessoa.java](Pessoa.java)

## Conceitos Fundamentais

### 1. Orientação a Objetos
- **Classe**: Molde ou template para criar objetos
- **Objeto**: Instância de uma classe
- **Encapsulamento**: Controle de acesso aos dados
- **Herança**: Reutilização de código através de hierarquias
- **Polimorfismo**: Capacidade de um objeto assumir múltiplas formas

### 2. Plataforma Java
- **JDK (Java Development Kit)**: Ferramentas de desenvolvimento
- **JRE (Java Runtime Environment)**: Ambiente de execução
- **JVM (Java Virtual Machine)**: Máquina virtual que executa bytecode

### 3. Compilação e Execução
```bash
# Compilação: converte .java em .class (bytecode)
javac MinhaClasse.java

# Execução: JVM interpreta o bytecode
java MinhaClasse
```

## Vantagens para Desenvolvimento Web

Java é amplamente usado no desenvolvimento web por oferecer:

1. **Escalabilidade**: Capacidade de lidar com grandes volumes de usuários
2. **Segurança**: Controles rigorosos de acesso e autenticação
3. **Robustez**: Tratamento de exceções e gerenciamento de memória
4. **Portabilidade**: Execução em diferentes servidores e sistemas
5. **Ecossistema Rico**: Frameworks como Spring, JSF, Struts
6. **Performance**: Otimizações da JVM para aplicações enterprise

## Próximos Passos

Este módulo introduziu os fundamentos da linguagem Java. Nos próximos módulos, exploraremos:

- Arquitetura Cliente-Servidor
- Sistemas de Múltiplas Camadas
- Arquitetura Orientada a Serviços com REST

## Exercícios Propostos

1. Compile e execute todos os exemplos Java fornecidos
2. Modifique o exemplo HelloWorldJava para exibir seu nome
3. Crie uma classe Funcionario com atributos nome, cargo e salário
4. Implemente métodos para calcular bonificação baseada no cargo

## Referências

- [Documentação Oficial Oracle Java](https://docs.oracle.com/en/java/)
- [Java Tutorial Oracle](https://docs.oracle.com/javase/tutorial/)
- [OpenJDK](https://openjdk.org/)