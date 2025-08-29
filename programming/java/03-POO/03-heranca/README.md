# Herança - Reutilizando Código Entre Classes

## 🎯 O que é Herança?

A **herança** permite criar novas classes baseadas em classes existentes:
- **Classe Pai** (superclasse): classe que é estendida
- **Classe Filha** (subclasse): classe que herda da pai
- **Reutilização**: aproveita código já implementado
- **Especialização**: adiciona comportamentos específicos

**Analogia**: Como uma árvore genealógica - filhos herdam características dos pais, mas podem ter suas próprias particularidades.

## 🔗 Sintaxe da Herança

### Definindo uma Classe Pai
```java
public class Animal {  // Classe pai
    protected String nome;
    
    public void dormir() {
        System.out.println(nome + " está dormindo");
    }
}
```

### Criando uma Classe Filha
```java
public class Cachorro extends Animal {  // Herda de Animal
    public void latir() {  // Método específico
        System.out.println(nome + " está latindo!");
    }
}
```

## 🔑 Palavras-Chave Importantes

### `extends`
```java
class ClasseFilha extends ClassePai {
    // Herda tudo da classe pai
    // Pode adicionar novos métodos
    // Pode sobrescrever métodos existentes
}
```

### `super`
```java
super.metodo();        // Chama método da classe pai
super(parametros);     // Chama construtor da classe pai
```

### `protected`
```java
protected String atributo;  // Visível para subclasses
```

## 📝 Sobrescrita de Métodos

### Override (Sobrescrever)
```java
@Override
public void metodo() {
    // Nova implementação do método da classe pai
    super.metodo();  // Opcional: chama implementação original
    // Código adicional específico
}
```

## 📚 Exemplos Práticos

### Exemplo Original: Hierarquia Animal
### [Animal.java](Animal.java)
Classe pai com comportamentos comuns a todos os animais.

### [Cachorro.java](Cachorro.java)
Classe filha que herda de Animal e adiciona comportamentos específicos.

### [Gato.java](Gato.java)
Outra classe filha demonstrando especialização diferente.

### [TesteHeranca.java](TesteHeranca.java)
Demonstração prática do uso da herança com animais.

### 🏆 Exemplo Clássico da Literatura: Hierarquia de Veículos
**Um dos exemplos mais famosos da POO, presente em quase todos os livros de programação orientada a objetos!**

### [Veiculo.java](Veiculo.java)
Classe abstrata base com características comuns (marca, modelo, acelerar, buzinar, etc.)

### [Carro.java](Carro.java)
Especialização para automóveis (portas, transmissão, ar-condicionado, ré, etc.)

### [Motocicleta.java](Motocicleta.java)
Especialização para motos (cilindradas, empinar, guidão, capacete, etc.)

### [TesteVeiculos.java](TesteVeiculos.java)
Demonstração completa da hierarquia clássica de veículos.

## 🚀 Como Executar

```bash
# Compilar
javac *.java

# Executar exemplo original (animais)
java TesteHeranca

# Executar exemplo clássico (veículos)
java TesteVeiculos
```

## 💡 Benefícios da Herança

1. **Reutilização**: Evita duplicação de código
2. **Organização**: Hierarquia lógica de classes
3. **Manutenção**: Mudanças na classe pai afetam todas as filhas
4. **Polimorfismo**: Uma referência pai pode apontar para objeto filho

## ⚠️ Cuidados com Herança

- Use herança para relações "É UM" (Cachorro **é um** Animal)
- Prefira composição para relações "TEM UM" (Carro **tem um** Motor)
- Evite hierarquias muito profundas
- Considere usar interfaces quando apropriado

## 🔗 Navegação
[← 02 - Encapsulamento](../02-encapsulamento/) | [04 - Polimorfismo →](../04-polimorfismo/)