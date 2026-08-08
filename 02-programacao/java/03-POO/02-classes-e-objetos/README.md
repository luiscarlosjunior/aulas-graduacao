# Classes e Objetos - Fundamentos da POO

## 🎯 Conceitos Fundamentais

### O que é uma Classe?
Uma **classe** é como um molde ou blueprint que define:
- **Atributos** (características): o que o objeto "tem"
- **Métodos** (comportamentos): o que o objeto "faz"

### O que é um Objeto?
Um **objeto** é uma instância específica de uma classe com valores concretos.

**Analogia simples**: 
- **Classe** = Planta de uma casa (modelo)
- **Objeto** = Casa construída (instância real)

## 📝 Sintaxe Básica

### Definindo uma Classe
```java
public class NomeDaClasse {
    // Atributos (características)
    tipo atributo1;
    tipo atributo2;
    
    // Construtor (como criar o objeto)
    public NomeDaClasse() {
        // inicialização
    }
    
    // Métodos (comportamentos)
    public void metodo1() {
        // código do comportamento
    }
}
```

### Criando e Usando um Objeto
```java
// 1. Criar o objeto
NomeDaClasse objeto = new NomeDaClasse();

// 2. Usar atributos
objeto.atributo1 = valor;

// 3. Chamar métodos
objeto.metodo1();
```

## 📚 Exemplos Práticos

### [CaoDomestico.java](CaoDomestico.java)
Exemplo completo de uma classe representando um cão doméstico.

### [Principal.java](Principal.java)
Demonstração de como criar e usar objetos da classe CaoDomestico.

## 🚀 Como Executar

```bash
# Compilar
javac *.java

# Executar
java Principal
```

## 💡 Exercícios Simples

1. **Modifique o peso** do cão e veja como muda o latido
2. **Crie múltiplos cães** com características diferentes
3. **Implemente os métodos vazios** (falar, andar, comer, dormir)

## 🔗 Próximo Tópico
[03 - Encapsulamento](../03-encapsulamento/) - Aprenda a proteger os dados da sua classe