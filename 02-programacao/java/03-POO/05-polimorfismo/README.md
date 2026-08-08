# Polimorfismo - Múltiplas Formas para o Mesmo Método

## 🎯 O que é Polimorfismo?

**Polimorfismo** significa "múltiplas formas". Em Java, permite que:
- **Mesma interface** tenha **diferentes implementações**
- **Mesmo método** se comporte **diferentemente** conforme o objeto
- **Referência pai** possa apontar para **objetos filhos**

**Analogia**: Como um controle remoto universal - o mesmo botão "play" funciona diferente em TV, DVD, ou rádio.

## 🔄 Tipos de Polimorfismo

### 1. **Sobrecarga (Overloading)**
Mesmo nome de método, **parâmetros diferentes**:
```java
public void calcular(int a, int b)         // Versão 1
public void calcular(double a, double b)   // Versão 2  
public void calcular(int a, int b, int c)  // Versão 3
```

### 2. **Sobrescrita (Overriding)**
Classe filha **redefine** método da classe pai:
```java
// Classe pai
public void emitirSom() { 
    System.out.println("Som genérico"); 
}

// Classe filha
@Override
public void emitirSom() { 
    System.out.println("Miau!"); 
}
```

### 3. **Polimorfismo de Interface**
Diferentes classes implementam a **mesma interface**:
```java
interface Voador {
    void voar();
}

class Aviao implements Voador { /* implementação */ }
class Passaro implements Voador { /* implementação */ }
```

## 🔑 Conceitos Importantes

### Dynamic Binding (Ligação Dinâmica)
```java
Animal animal = new Cachorro();  // Referência pai, objeto filho
animal.emitirSom();              // Chama método de Cachorro (não Animal)
```

### instanceof e Cast
```java
if (animal instanceof Cachorro) {
    Cachorro dog = (Cachorro) animal;  // Cast seguro
    dog.latir();  // Método específico
}
```

## 📚 Exemplos Práticos

### Exemplo Original: Formas Geométricas
### [Forma.java](Forma.java)
Classe abstrata representando formas geométricas.

### [Retangulo.java](Retangulo.java)
### [Circulo.java](Circulo.java)
Classes concretas que implementam formas específicas.

### [Calculadora.java](Calculadora.java)
Demonstração de sobrecarga de métodos.

### [TestePolimorfismo.java](TestePolimorfismo.java)
Exemplos práticos de todos os tipos de polimorfismo.

### 🏆 Exemplo Clássico da Literatura: Hierarquia de Funcionários
**Um dos exemplos mais poderosos para demonstrar polimorfismo, encontrado em "Design Patterns", "Effective Java" e "Clean Code"!**

### [Funcionario.java](Funcionario.java)
Classe abstrata base com métodos abstratos (calcularSalario, trabalhar, participarReuniao)

### [Desenvolvedor.java](Desenvolvedor.java)
Especialização para desenvolvedores (linguagens, senioridade, projetos, code review)

### [Gerente.java](Gerente.java)
Especialização para gerentes (equipe, metas, orçamento, aprovações)

### [TesteFuncionarios.java](TesteFuncionarios.java)
Demonstração completa de polimorfismo avançado com interações complexas.

## 🚀 Como Executar

```bash
# Compilar
javac *.java

# Executar exemplo original (formas geométricas)
java TestePolimorfismo

# Executar exemplo clássico (funcionários)
java TesteFuncionarios
```

## 💡 Vantagens do Polimorfismo

1. **Flexibilidade**: Código funciona com diferentes tipos
2. **Extensibilidade**: Fácil adicionar novos tipos
3. **Manutenibilidade**: Mudanças localizadas
4. **Reutilização**: Mesmo código, múltiplos comportamentos

### 🎯 Por que a Hierarquia de Funcionários é um Exemplo Clássico?

O exemplo **Funcionario → Desenvolvedor/Gerente** é considerado um dos melhores para ensinar polimorfismo porque:

- **Realismo**: Todos entendem a diferença entre desenvolvedores e gerentes
- **Polimorfismo claro**: `calcularSalario()` tem lógicas **completamente diferentes**
- **Comportamentos distintos**: Cada tipo trabalha e participa de reuniões de forma única
- **Interações complexas**: Gerentes avaliam desenvolvedores, desenvolvores fazem code review
- **Extensibilidade**: Fácil adicionar Designer, Analista, Estagiário, etc.

Este exemplo aparece em livros clássicos como:
- 📖 "Design Patterns" (Gang of Four)
- 📖 "Effective Java" (Joshua Bloch)  
- 📖 "Clean Code" (Robert Martin)
- 📖 "Head First Design Patterns"

## ⚠️ Cuidados

- Cast incorreto pode gerar `ClassCastException`
- Use `instanceof` antes de fazer cast
- Polimorfismo só funciona com métodos, não atributos
- Referência pai não acessa métodos específicos do filho

## 🔗 Navegação
[← 04 - Herança](../04-heranca/) | [06 - Abstração →](../06-abstracao/)