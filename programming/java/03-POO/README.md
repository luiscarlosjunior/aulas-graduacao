# Programação Orientada a Objetos - Introdução Prática

Esta seção apresenta uma introdução prática aos conceitos fundamentais de Programação Orientada a Objetos (POO) em Java através de um exemplo simples e didático.

## 🎯 Objetivos

- Compreender o conceito de classes e objetos
- Aprender sobre atributos e métodos
- Entender encapsulamento básico
- Praticar instanciação de objetos
- Aplicar conceitos de POO em exemplo prático

## 📋 Conceitos Fundamentais

### Classe vs Objeto

- **Classe**: Template ou molde que define características e comportamentos
- **Objeto**: Instância específica de uma classe com valores concretos

**Analogia**: Se a classe é a "planta da casa", o objeto é a "casa construída".

## 📄 Análise dos Exemplos

### [CaoDomestico.java](CaoDomestico.java)

Classe que representa um cão doméstico com características e comportamentos típicos.

#### **Atributos (Características)**
```java
public String nome;        // Nome do cão
public int peso;          // Peso em kg
public String corOlhos;   // Cor dos olhos
public int quantPatas;    // Quantidade de patas (normalmente 4)
```

#### **Construtores**
```java
// Construtor padrão (sem parâmetros)
public CaoDomestico() {
}

// Construtor com nome
public CaoDomestico(String nome) {
    this.nome = nome;
}
```

#### **Métodos (Comportamentos)**
```java
// Getter personalizado
public String getNome() {
    return "Nome do Curso retornado " + nome;
}

// Comportamentos básicos (a implementar)
public void falar() { /* TODO */ }
public void andar() { /* TODO */ }
public void comer() { /* TODO */ }
public void dormir() { /* TODO */ }

// Comportamento específico implementado
void latir() {
    if (peso > 60)
        System.out.println("Wooof, Wooof!");      // Cão grande
    else if (peso > 14)
        System.out.println("Ruff!, Ruff!");       // Cão médio
    else
        System.out.println("Yip!, Yip!");         // Cão pequeno
}
```

### [Principal.java](Principal.java)

Classe principal que demonstra o uso da classe `CaoDomestico`.

#### **Criação e Uso do Objeto**
```java
public static void main(String[] args) {
    // 1. Criar uma instância (objeto) da classe CaoDomestico
    CaoDomestico cd = new CaoDomestico();
    
    // 2. Definir características do objeto
    cd.nome = "Pluto";
    cd.corOlhos = "Azuis";
    cd.peso = 53;
    cd.quantPatas = 4;
    
    // 3. Chamar comportamento do objeto
    cd.latir();  // Saída: "Ruff!, Ruff!" (peso entre 14 e 60)
}
```

## 🔧 Conceitos Demonstrados

### 1. **Encapsulamento Básico**
```java
// Atributos públicos (acessíveis diretamente)
public String nome;
public int peso;

// Método que acessa atributo interno
public String getNome() {
    return "Nome do Curso retornado " + nome;
}
```

### 2. **Polimorfismo Básico (Sobrecarga)**
```java
// Dois construtores com assinaturas diferentes
public CaoDomestico()           // Sem parâmetros
public CaoDomestico(String nome) // Com parâmetro nome
```

### 3. **Lógica Condicional em Métodos**
```java
void latir() {
    if (peso > 60)              // Cão grande
        System.out.println("Wooof, Wooof!");
    else if (peso > 14)         // Cão médio
        System.out.println("Ruff!, Ruff!");
    else                        // Cão pequeno
        System.out.println("Yip!, Yip!");
}
```

### 4. **Instanciação e Configuração**
```java
CaoDomestico cd = new CaoDomestico();  // Criação
cd.nome = "Pluto";                     // Configuração
cd.latir();                            // Uso
```

## 🚀 Como Executar o Exemplo

```bash
# Navegar até o diretório
cd "03-POO"

# Compilar ambos os arquivos
javac *.java

# Executar a classe principal
java Principal
```

**Saída esperada**:
```
Ruff!, Ruff!
```

## 💡 Experimentos Sugeridos

### 1. **Diferentes Tipos de Latido**
Teste com diferentes pesos:

```java
// Cão pequeno
cd.peso = 10;
cd.latir();  // "Yip!, Yip!"

// Cão grande
cd.peso = 70;
cd.latir();  // "Wooof, Wooof!"
```

### 2. **Múltiplos Objetos**
```java
CaoDomestico cao1 = new CaoDomestico("Rex");
CaoDomestico cao2 = new CaoDomestico("Bella");

cao1.peso = 80;
cao2.peso = 5;

cao1.latir();  // "Wooof, Wooof!"
cao2.latir();  // "Yip!, Yip!"
```

### 3. **Implementar Métodos Pendentes**
```java
public void falar() {
    System.out.println(nome + " está fazendo sons de cão!");
}

public void andar() {
    System.out.println(nome + " está caminhando com " + quantPatas + " patas.");
}
```

## 🔧 Melhorias Sugeridas

### 1. **Encapsulamento Adequado**
```java
// Ao invés de atributos públicos:
public String nome;

// Use atributos privados com getters/setters:
private String nome;

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}
```

### 2. **Validação nos Setters**
```java
public void setPeso(int peso) {
    if (peso > 0) {
        this.peso = peso;
    } else {
        throw new IllegalArgumentException("Peso deve ser positivo");
    }
}
```

### 3. **Método toString()**
```java
@Override
public String toString() {
    return "CaoDomestico{" +
           "nome='" + nome + '\'' +
           ", peso=" + peso +
           ", corOlhos='" + corOlhos + '\'' +
           ", quantPatas=" + quantPatas +
           '}';
}
```

### 4. **Enum para Tamanhos**
```java
public enum Tamanho {
    PEQUENO, MEDIO, GRANDE
}

public Tamanho getTamanho() {
    if (peso > 60) return Tamanho.GRANDE;
    if (peso > 14) return Tamanho.MEDIO;
    return Tamanho.PEQUENO;
}
```

## 💡 Exercícios Propostos

### Básico:
1. **Criar outras raças**: `Gato`, `Passaro`, `Peixe` com comportamentos específicos
2. **Adicionar mais atributos**: `idade`, `cor`, `raca`
3. **Implementar métodos pendentes**: `falar()`, `andar()`, `comer()`, `dormir()`

### Intermediário:
1. **Sistema de Pets**: Classe que gerencia múltiplos animais
2. **Herança**: Classe `Animal` como pai de `CaoDomestico`
3. **Interface**: `Domestico` com métodos que pets devem implementar

### Avançado:
1. **Veterinária**: Sistema completo com dono, pet, consultas
2. **Serialização**: Salvar/carregar pets de arquivo
3. **Collections**: Lista de pets com busca e filtros

## ❗ Problemas Identificados

### 1. **Naming Convention**
- Package: `exempoclasse` → `exemploClasse` ou `exemplo.classe`
- Consistência na nomenclatura

### 2. **Encapsulamento**
- Atributos públicos quebram encapsulamento
- Falta validação de dados

### 3. **Métodos Incompletos**
- Vários métodos com `// TODO`
- Falta implementação de comportamentos

## 📚 Conceitos Relacionados

- **Herança**: Classes que estendem outras classes
- **Polimorfismo**: Mesmo método, comportamentos diferentes
- **Interfaces**: Contratos que classes devem implementar
- **Abstração**: Classes abstratas e métodos abstratos
- **Design Patterns**: Padrões como Factory, Observer

---

**Próximo**: [Conceitos Intermediários](../03-conceitos-intermediarios/) - Aprofunde em Collections, Generics e conceitos avançados de POO.