# Herança - Reutilizando e Especializando Código

## 🎯 O que é Herança?

A **herança** é um dos pilares da POO que permite:

- **Reutilizar** código de uma classe existente (classe pai/superclasse)
- **Especializar** comportamentos criando classes filhas (subclasses)
- **Criar hierarquias** lógicas de classes relacionadas
- **Estabelecer relações** "É UM" entre objetos

**Analogia**: Como uma árvore genealógica - filhos herdam características dos pais (cor dos olhos, altura), mas também podem ter características próprias (personalidade, talentos específicos).

## 🔗 Sintaxe da Herança

### Definindo uma Classe Pai (Superclasse)

```java
public class Veiculo {  // Classe pai
    protected String marca;     // protected = visível para filhas
    protected String modelo;
    protected int ano;
    
    public Veiculo(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }
    
    public void ligar() {
        System.out.println("Veículo ligado!");
    }
    
    public void acelerar() {
        System.out.println("Acelerando...");
    }
}
```

### Criando uma Classe Filha (Subclasse)

```java
public class Carro extends Veiculo {  // Herda de Veiculo
    private int numeroPortas;
    
    public Carro(String marca, String modelo, int ano, int portas) {
        super(marca, modelo, ano);  // Chama construtor da classe pai
        this.numeroPortas = portas;
    }
    
    // Método específico da classe filha
    public void abrirPorta() {
        System.out.println("Abrindo porta do carro");
    }
    
    // Sobrescrevendo método da classe pai
    @Override
    public void acelerar() {
        System.out.println("Carro acelerando com " + numeroPortas + " portas");
    }
}
```

## 🔑 Palavras-Chave Importantes

### `extends` - Estabelece Herança
```java
class ClasseFilha extends ClassePai {
    // Herda todos os atributos e métodos da classe pai
    // Pode adicionar novos atributos e métodos
    // Pode sobrescrever métodos existentes
}
```

### `super` - Acessa Classe Pai
```java
super.metodo();           // Chama método da classe pai
super(parametros);        // Chama construtor da classe pai
super.atributo;          // Acessa atributo da classe pai
```

### `protected` - Visibilidade para Herança
```java
protected String atributo;  // Visível para subclasses
protected void metodo() {   // Acessível pelas classes filhas
    // implementação
}
```

### `@Override` - Sobrescrita de Métodos
```java
@Override
public void metodo() {
    // Nova implementação do método da classe pai
    super.metodo();  // Opcional: chama implementação original
    // Código adicional específico
}
```

## 📝 Tipos de Herança

### 1. **Herança Simples**
Uma classe filha herda de apenas uma classe pai (Java não permite herança múltipla de classes).

### 2. **Herança Multinível**
```java
class Animal { }
class Mamifero extends Animal { }
class Cachorro extends Mamifero { }
```

### 3. **Herança Hierárquica**
```java
class Veiculo { }
class Carro extends Veiculo { }
class Moto extends Veiculo { }
class Caminhao extends Veiculo { }
```

## 🏗️ Exemplo Prático Completo

### Hierarquia Animal (Clássica)

```java
// Classe pai
public class Animal {
    protected String nome;
    protected int idade;
    
    public Animal(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    public void dormir() {
        System.out.println(nome + " está dormindo");
    }
    
    public void emitirSom() {
        System.out.println(nome + " fez um som");
    }
}

// Classe filha 1
public class Cachorro extends Animal {
    private String raca;
    
    public Cachorro(String nome, int idade, String raca) {
        super(nome, idade);  // Chama construtor do Animal
        this.raca = raca;
    }
    
    @Override
    public void emitirSom() {
        System.out.println(nome + " está latindo: Au au!");
    }
    
    public void abanarRabo() {
        System.out.println(nome + " está abanando o rabo");
    }
}

// Classe filha 2
public class Gato extends Animal {
    private boolean ehDomestico;
    
    public Gato(String nome, int idade, boolean domestico) {
        super(nome, idade);
        this.ehDomestico = domestico;
    }
    
    @Override
    public void emitirSom() {
        System.out.println(nome + " está miando: Miau!");
    }
    
    public void subirArvore() {
        System.out.println(nome + " subiu na árvore");
    }
}
```

## 💡 Benefícios da Herança

### 1. **Reutilização de Código**
- Evita duplicação
- Aproveita funcionalidades já implementadas

### 2. **Organização Hierárquica**
- Estrutura lógica e intuitiva
- Facilita compreensão do sistema

### 3. **Facilita Manutenção**
- Mudanças na classe pai afetam automaticamente todas as filhas
- Centraliza comportamentos comuns

### 4. **Polimorfismo**
- Permite tratar objetos filhos como objetos pai
- Flexibilidade na programação

## ⚠️ Cuidados e Boas Práticas

### ✅ Use Herança Para Relações "É UM"
```java
// ✅ Correto: Cachorro É UM Animal
class Cachorro extends Animal { }

// ❌ Incorreto: Carro NÃO É UM Motor (é "TEM UM")
class Carro extends Motor { }  // EVITE!
```

### ✅ Prefira Composição Para "TEM UM"
```java
// ✅ Correto: Carro TEM UM Motor
class Carro {
    private Motor motor;  // Composição
}
```

### ✅ Evite Hierarquias Muito Profundas
```java
// ❌ Evite hierarquias muito profundas
class A extends B extends C extends D extends E { }

// ✅ Prefira hierarquias mais rasas
class Animal { }
class Mamifero extends Animal { }
class Cachorro extends Mamifero { }  // Máximo 3-4 níveis
```

## 🚀 Exercícios Práticos

1. **Crie uma hierarquia de Formas Geométricas**
   - Classe pai: `Forma` (com área e perímetro)
   - Classes filhas: `Retangulo`, `Circulo`, `Triangulo`

2. **Implemente uma hierarquia de Funcionários**
   - Classe pai: `Funcionario` (nome, salário, calcularSalario)
   - Classes filhas: `Gerente`, `Vendedor`, `Desenvolvedor`

3. **Teste polimorfismo**
   - Crie arrays de objetos pai apontando para objetos filhos
   - Chame métodos sobrescritos

## 🔗 Navegação

[← 02 - Encapsulamento](../02-encapsulamento/) | [04 - Polimorfismo →](../04-polimorfismo/)

---

**💡 Lembre-se**: Herança é sobre especialização e reutilização. Use-a para criar hierarquias lógicas onde existe uma relação natural "É UM" entre as classes!