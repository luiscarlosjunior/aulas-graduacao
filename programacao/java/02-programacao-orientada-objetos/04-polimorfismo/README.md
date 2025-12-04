# Polimorfismo - Múltiplas Formas para o Mesmo Comportamento

## 🎯 O que é Polimorfismo?

**Polimorfismo** (do grego "muitas formas") é a capacidade de objetos de diferentes tipos responderem à mesma interface de maneiras específicas:

- **Mesma chamada** de método, **comportamentos diferentes**
- **Interface unificada** para **implementações variadas**
- **Flexibilidade** na programação e extensibilidade do código
- **Tratamento uniforme** de objetos relacionados

**Analogia**: Como um controle remoto universal - o mesmo botão "play" funciona diferente em TV (reproduz canal), DVD (reproduz filme), ou rádio (reproduz música), mas a operação é a mesma.

## 🔄 Tipos de Polimorfismo em Java

### 1. **Sobrecarga de Métodos (Overloading)**

**Mesmo nome**, **parâmetros diferentes**:

```java
public class Calculadora {
    // Mesmo nome, diferentes assinaturas
    public int somar(int a, int b) {
        return a + b;
    }
    
    public double somar(double a, double b) {
        return a + b;
    }
    
    public int somar(int a, int b, int c) {
        return a + b + c;
    }
    
    public String somar(String a, String b) {
        return a + b;  // Concatenação
    }
}

// Uso
Calculadora calc = new Calculadora();
calc.somar(2, 3);        // Chama versão int
calc.somar(2.5, 3.7);    // Chama versão double
calc.somar(1, 2, 3);     // Chama versão com 3 parâmetros
calc.somar("Olá ", "Mundo"); // Chama versão String
```

### 2. **Sobrescrita de Métodos (Overriding)**

**Classe filha redefine** método da classe pai:

```java
// Classe pai
public class Animal {
    public void emitirSom() {
        System.out.println("Som genérico de animal");
    }
    
    public void mover() {
        System.out.println("Animal se movendo");
    }
}

// Classes filhas com comportamentos específicos
public class Cachorro extends Animal {
    @Override
    public void emitirSom() {
        System.out.println("Au au au!");
    }
    
    @Override
    public void mover() {
        System.out.println("Cachorro correndo e abanando o rabo");
    }
}

public class Passaro extends Animal {
    @Override
    public void emitirSom() {
        System.out.println("Piu piu piu!");
    }
    
    @Override
    public void mover() {
        System.out.println("Pássaro voando graciosamente");
    }
}
```

### 3. **Polimorfismo Dinâmico (Runtime)**

**Referência pai** aponta para **objetos filhos**:

```java
public class TestePolimorfismo {
    public static void main(String[] args) {
        // Referência pai, objetos filhos
        Animal animal1 = new Cachorro();
        Animal animal2 = new Passaro();
        Animal animal3 = new Animal();
        
        // Mesmo método, comportamentos diferentes
        animal1.emitirSom();  // "Au au au!"
        animal2.emitirSom();  // "Piu piu piu!"
        animal3.emitirSom();  // "Som genérico de animal"
        
        // Arrays polimórficos
        Animal[] zoologico = {
            new Cachorro(),
            new Passaro(),
            new Gato()
        };
        
        // Tratamento uniforme
        for (Animal animal : zoologico) {
            animal.emitirSom();  // Cada um emite seu som específico
            animal.mover();      // Cada um se move de forma específica
        }
    }
}
```

## 🔑 Conceitos Importantes

### Dynamic Binding (Ligação Dinâmica)

Java decide **em tempo de execução** qual método chamar:

```java
Animal animal = new Cachorro();  // Tipo Animal, objeto Cachorro
animal.emitirSom();  // Chama método de Cachorro, não de Animal!
```

### Upcasting vs Downcasting

```java
// Upcasting (automático - seguro)
Animal animal = new Cachorro();  // ✅ Sempre funciona

// Downcasting (manual - pode falhar)
Cachorro dog = (Cachorro) animal;  // ✅ Funciona se animal for Cachorro
Gato cat = (Gato) animal;          // ❌ Erro! animal não é Gato

// Verificação segura
if (animal instanceof Cachorro) {
    Cachorro dog = (Cachorro) animal;
    dog.abanarRabo();  // Método específico de Cachorro
}
```

### Métodos Abstract e Polimorfismo

```java
public abstract class Forma {
    protected String cor;
    
    public abstract double calcularArea();  // Deve ser implementado
    public abstract double calcularPerimetro();
    
    public void pintar() {  // Método comum
        System.out.println("Pintando forma de " + cor);
    }
}

public class Retangulo extends Forma {
    private double largura, altura;
    
    @Override
    public double calcularArea() {
        return largura * altura;
    }
    
    @Override
    public double calcularPerimetro() {
        return 2 * (largura + altura);
    }
}

public class Circulo extends Forma {
    private double raio;
    
    @Override
    public double calcularArea() {
        return Math.PI * raio * raio;
    }
    
    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * raio;
    }
}
```

## 🏗️ Exemplo Prático: Sistema de Funcionários

```java
// Classe base
public abstract class Funcionario {
    protected String nome;
    protected double salarioBase;
    
    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }
    
    // Método polimórfico - cada tipo calcula diferente
    public abstract double calcularSalario();
    
    public void mostrarInfo() {
        System.out.println("Funcionário: " + nome);
        System.out.println("Salário: R$ " + calcularSalario());
    }
}

// Diferentes implementações
public class Vendedor extends Funcionario {
    private double vendas;
    private double comissao;
    
    public Vendedor(String nome, double salarioBase, double vendas, double comissao) {
        super(nome, salarioBase);
        this.vendas = vendas;
        this.comissao = comissao;
    }
    
    @Override
    public double calcularSalario() {
        return salarioBase + (vendas * comissao);
    }
}

public class Gerente extends Funcionario {
    private double bonus;
    
    public Gerente(String nome, double salarioBase, double bonus) {
        super(nome, salarioBase);
        this.bonus = bonus;
    }
    
    @Override
    public double calcularSalario() {
        return salarioBase + bonus;
    }
}

// Uso polimórfico
public class SistemaRH {
    public static void main(String[] args) {
        Funcionario[] empresa = {
            new Vendedor("João", 2000, 10000, 0.05),
            new Gerente("Maria", 5000, 2000),
            new Vendedor("Pedro", 2000, 15000, 0.05)
        };
        
        double folhaPagamento = 0;
        for (Funcionario func : empresa) {
            func.mostrarInfo();  // Polimorfismo em ação!
            folhaPagamento += func.calcularSalario();
        }
        
        System.out.println("Total da folha: R$ " + folhaPagamento);
    }
}
```

## 💡 Benefícios do Polimorfismo

### 1. **Flexibilidade**
- Código que funciona com classes pai automaticamente funciona com novas classes filhas

### 2. **Extensibilidade**
- Fácil adição de novos tipos sem modificar código existente

### 3. **Manutenibilidade**
- Menos código duplicado
- Tratamento uniforme de objetos relacionados

### 4. **Abstração**
- Cliente não precisa saber o tipo específico do objeto

## ⚠️ Cuidados e Boas Práticas

### ✅ Use @Override sempre
```java
@Override  // ✅ Detecta erros em tempo de compilação
public void metodo() {
    // implementação
}
```

### ✅ Prefira Interfaces para Polimorfismo
```java
interface Desenhavel {
    void desenhar();
}

class Circulo implements Desenhavel { /* ... */ }
class Retangulo implements Desenhavel { /* ... */ }

// Mais flexível que herança de classes
```

### ⚠️ Cuidado com Downcasting
```java
// ✅ Sempre verifique antes
if (objeto instanceof TipoEspecifico) {
    TipoEspecifico especifico = (TipoEspecifico) objeto;
    especifico.metodoEspecifico();
}
```

## 🚀 Exercícios Práticos

1. **Crie uma hierarquia de Instrumentos Musicais**
   - Classe pai: `Instrumento` (com método `tocar()`)
   - Classes filhas: `Piano`, `Violao`, `Bateria`
   - Teste polimorfismo criando uma orquestra

2. **Sistema de Pagamentos**
   - Interface: `ProcessadorPagamento` (método `processar()`)
   - Implementações: `CartaoCredito`, `PIX`, `Boleto`
   - Teste processamento polimórfico

3. **Formas Geométricas**
   - Classe abstrata: `Forma` (métodos `area()` e `perimetro()`)
   - Implementações: `Quadrado`, `Triangulo`, `Circulo`
   - Calcule área total de uma lista de formas

## 🔗 Navegação

[← 03 - Herança](../03-heranca/) | [05 - Interfaces →](../05-interfaces/)

---

**💡 Lembre-se**: Polimorfismo é sobre comportamento unificado com implementações específicas. É o que torna a POO verdadeiramente poderosa e flexível!