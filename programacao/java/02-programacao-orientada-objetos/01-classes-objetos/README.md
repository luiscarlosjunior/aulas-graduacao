# Classes e Objetos

Fundamentos da programação orientada a objetos: definição de classes, criação de objetos, atributos e métodos.

## 🎯 Objetivos

- Compreender o conceito de classe e objeto
- Aprender a definir atributos e métodos
- Entender construtores e inicialização
- Praticar criação e uso de objetos

## 📋 Conceitos Abordados

- **Classe**: Modelo ou template para criar objetos
- **Objeto**: Instância de uma classe
- **Atributos**: Variáveis que representam o estado do objeto
- **Métodos**: Funções que definem o comportamento do objeto
- **Construtor**: Método especial para inicializar objetos

## 🖥️ Exemplos

### [Pessoa.java](Pessoa.java)
Classe simples representando uma pessoa com atributos básicos.

### [ContaBancaria.java](ContaBancaria.java)
Exemplo prático de uma conta bancária com operações.

### [Carro.java](Carro.java)
Classe representando um carro com diferentes construtores.

### [TestePessoa.java](TestePessoa.java)
Programa principal demonstrando o uso da classe Pessoa.

### [TesteCarro.java](TesteCarro.java)
Programa demonstrando diferentes formas de criar objetos Carro.

## 🚀 Como Executar

```bash
# Compilar e executar exemplo de Pessoa
javac Pessoa.java TestePessoa.java
java TestePessoa

# Compilar e executar exemplo de Carro
javac Carro.java TesteCarro.java
java TesteCarro
```

## 💡 Conceitos Fundamentais

### Definição de Classe
```java
public class MinhaClasse {
    // Atributos
    private String nome;
    private int idade;
    
    // Construtor
    public MinhaClasse(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    // Métodos
    public void falar() {
        System.out.println("Olá!");
    }
}
```

### Criação de Objetos
```java
MinhaClasse objeto = new MinhaClasse("João", 30);
objeto.falar();
```

## 📝 Exercícios

1. Crie uma classe `Livro` com título, autor e número de páginas
2. Implemente uma classe `Produto` com nome, preço e categoria
3. Desenvolva uma classe `Aluno` com nome, matrícula e notas
4. Crie uma classe `Retângulo` com largura, altura e métodos para calcular área e perímetro

## 🔗 Próximo Passo

Continue para [Encapsulamento](../02-encapsulamento/) para aprender sobre proteção de dados.