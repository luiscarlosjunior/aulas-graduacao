# LSP - Liskov Substitution Principle
## Princípio da Substituição de Liskov

## 📖 Visão Geral

O **Liskov Substitution Principle (LSP)** estabelece que objetos de uma superclasse devem poder ser substituídos por objetos de suas subclasses sem quebrar o comportamento do programa. Em outras palavras, se S é um subtipo de T, então objetos do tipo T podem ser substituídos por objetos do tipo S sem alterar as propriedades desejáveis do programa.

## 🎯 Definição

> "Subtypes must be substitutable for their base types."
>
> "Subtipos devem ser substituíveis por seus tipos base."
>
> -- Robert C. Martin (simplificação)

**Definição Formal de Barbara Liskov (1987):**
> "If for each object o1 of type S there is an object o2 of type T such that for all programs P defined in terms of T, the behavior of P is unchanged when o1 is substituted for o2, then S is a subtype of T."

## 📚 Origem e História

### Barbara Liskov (1987)

**Barbara Liskov** apresentou o princípio em sua palestra "Data Abstraction and Hierarchy" na conferência OOPSLA 1987. Posteriormente, foi formalizado em seu artigo de 1988 com Jeannette Wing.

### Contribuição Fundamental

Liskov definiu rigorosamente quando um subtipo pode substituir seu tipo base, estabelecendo fundamentos matemáticos para herança correta em programação orientada a objetos.

### Prêmio Turing

Barbara Liskov recebeu o **Prêmio Turing** em 2008, parcialmente por suas contribuições à abstração de dados e este princípio.

## 🔍 Entendendo Substituibilidade

### O Que é Substituibilidade?

Um objeto de uma subclasse deve poder ser usado onde um objeto da superclasse é esperado, **sem** que o código cliente precise saber a diferença.

```java
// Se funciona com tipo base:
Animal animal = new Animal();
animal.fazerSom(); // OK

// Deve funcionar com subtipo:
Animal animal = new Cachorro(); // Cachorro extends Animal
animal.fazerSom(); // Deve funcionar da mesma forma
```

### Expectativas do Cliente

Código cliente tem expectativas baseadas no contrato da superclasse. Subclasses devem **honrar** essas expectativas.

## 🎯 Por Que LSP é Importante?

### 1. **Polimorfismo Confiável**
LSP garante que polimorfismo funciona corretamente. Você pode usar subclasses onde superclasses são esperadas com confiança.

### 2. **Reduz Surpresas**
Código cliente não precisa fazer verificações especiais por tipo de subclasse.

### 3. **Facilita Extensão**
Novas subclasses podem ser adicionadas sem quebrar código existente.

### 4. **Promove Design Correto**
LSP força você a pensar cuidadosamente sobre hierarquias de herança.

### 5. **Melhora Testabilidade**
Testes escritos para superclasse devem passar para todas as subclasses.

## ❌ Violação Clássica de LSP: Retângulo e Quadrado

### O Problema Matemático vs Programação

Matematicamente, um quadrado **é um** retângulo (relação "is-a"). Mas em programação OO, essa hierarquia viola LSP!

```java
// ❌ Violação de LSP: Quadrado herda de Retângulo
public class Retangulo {
    protected int largura;
    protected int altura;
    
    public void setLargura(int largura) {
        this.largura = largura;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    public int getArea() {
        return largura * altura;
    }
}

public class Quadrado extends Retangulo {
    
    @Override
    public void setLargura(int largura) {
        // ❌ Modifica comportamento: altera AMBOS lados
        this.largura = largura;
        this.altura = largura; // Quebra expectativa!
    }
    
    @Override
    public void setAltura(int altura) {
        // ❌ Modifica comportamento: altera AMBOS lados
        this.largura = altura; // Quebra expectativa!
        this.altura = altura;
    }
}

// Teste que demonstra violação:
public void testarRetangulo(Retangulo r) {
    r.setLargura(5);
    r.setAltura(4);
    
    // Expectativa: área = 5 * 4 = 20
    assert r.getArea() == 20 : "Esperado 20, obtido " + r.getArea();
}

// ✅ Funciona com Retangulo
Retangulo ret = new Retangulo();
testarRetangulo(ret); // Passa: área = 20

// ❌ FALHA com Quadrado - violação de LSP!
Retangulo quad = new Quadrado();
testarRetangulo(quad); // FALHA: área = 16, não 20!
```

**Por que viola LSP:**
- Código esperava poder definir largura e altura independentemente
- `Quadrado` quebra essa expectativa ao forçar lados iguais
- Substituir `Retangulo` por `Quadrado` quebra comportamento

## ✅ Seguindo LSP: Design Correto

```java
// ✅ Seguindo LSP: Interface comum sem hierarquia problemática
public interface Forma {
    int getArea();
}

public class Retangulo implements Forma {
    private int largura;
    private int altura;
    
    public Retangulo(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }
    
    public void setLargura(int largura) {
        this.largura = largura;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    @Override
    public int getArea() {
        return largura * altura;
    }
}

public class Quadrado implements Forma {
    private int lado;
    
    public Quadrado(int lado) {
        this.lado = lado;
    }
    
    public void setLado(int lado) {
        this.lado = lado;
    }
    
    @Override
    public int getArea() {
        return lado * lado;
    }
}

// Cliente usa interface comum
public int calcularAreaTotal(List<Forma> formas) {
    int total = 0;
    for (Forma forma : formas) {
        total += forma.getArea();
    }
    return total;
}

// ✅ Ambos são substituíveis como Forma
List<Forma> formas = new ArrayList<>();
formas.add(new Retangulo(5, 4));
formas.add(new Quadrado(3));
int total = calcularAreaTotal(formas); // Funciona perfeitamente!
```

## 📋 Regras para Seguir LSP

### 1. **Pré-condições não podem ser fortalecidas**
Subclasse não pode exigir mais que superclasse.

```java
// ❌ Violação
public class Animal {
    public void comer(Comida c) {
        // Aceita qualquer comida
    }
}

public class Gato extends Animal {
    @Override
    public void comer(Comida c) {
        if (!(c instanceof Ração)) {
            throw new IllegalArgumentException("Gato só come ração");
        }
        // ❌ Pré-condição mais forte: apenas Ração
    }
}
```

### 2. **Pós-condições não podem ser enfraquecidas**
Subclasse deve garantir pelo menos o que superclasse garante.

```java
// ❌ Violação
public class ContaBancaria {
    public double sacar(double valor) {
        // Garante: saldo >= 0 após saque
        if (saldo >= valor) {
            saldo -= valor;
            return valor;
        }
        return 0;
    }
}

public class ContaEspecial extends ContaBancaria {
    @Override
    public double sacar(double valor) {
        saldo -= valor; // ❌ Permite saldo negativo!
        return valor;   // Quebra garantia da superclasse
    }
}
```

### 3. **Invariantes devem ser preservadas**
Condições que sempre são verdadeiras na superclasse devem permanecer verdadeiras.

```java
// ✅ Invariante: saldo sempre >= 0
public class ContaBancaria {
    protected double saldo;
    
    public void depositar(double valor) {
        saldo += valor;
        // Invariante: saldo >= 0 mantida
    }
}
```

### 4. **Método não deve lançar exceções não esperadas**
```java
// ❌ Violação
public class Arquivo {
    public void ler() {
        // Não lança exceções
    }
}

public class ArquivoRemoto extends Arquivo {
    @Override
    public void ler() throws NetworkException { // ❌ Nova exceção!
        // Código cliente não espera NetworkException
    }
}
```

## 📋 Como Identificar Violações de LSP

### Sinais de Violação:

1. **Instanceof ou Casting**
```java
// ❌ Código cliente verifica tipo específico
if (forma instanceof Quadrado) {
    // tratamento especial
} else if (forma instanceof Retangulo) {
    // tratamento diferente
}
// Se precisa verificar tipo, substituibilidade está quebrada
```

2. **Exceções em Subclasses**
```java
// ❌ Método da subclasse lança UnsupportedOperationException
@Override
public void voar() {
    throw new UnsupportedOperationException("Pinguim não voa");
}
// Indica que subclasse não é realmente um tipo substituível
```

3. **Comportamento Modificado**
```java
// ❌ Subclasse tem comportamento radicalmente diferente
public class Pato extends Ave {
    @Override
    public void voar() {
        // Voa normalmente
    }
}

public class Pinguim extends Ave {
    @Override
    public void voar() {
        // Fica parado (não voa!)
        // Quebra expectativa
    }
}
```

4. **Testes Falhando**
Se testes da superclasse falham para subclasse, LSP está violado.

## 📋 Diretrizes Práticas

### 1. **Design por Contrato (DbC)**
```java
// Defina contratos claros
/**
 * Pré-condição: valor > 0
 * Pós-condição: saldo diminui em valor
 * Invariante: saldo >= 0
 */
public void sacar(double valor) {
    if (valor <= 0) throw new IllegalArgumentException();
    // ...
}

// Subclasses devem respeitar contrato
```

### 2. **Prefira Composição sobre Herança**
Se herança viola LSP, use composição:

```java
// Em vez de Quadrado extends Retangulo
public class Quadrado {
    private int lado;
    // Sem herança problemática
}

public class Retangulo {
    private int largura, altura;
    // Sem herança problemática
}
```

### 3. **Use Herança Apenas para "IS-A" Verdadeiro**
```java
// ✅ Cachorro realmente É UM Animal (comportamento compatível)
public class Cachorro extends Animal { }

// ❌ Quadrado NÃO é substituível por Retângulo (comportamento incompatível)
// public class Quadrado extends Retangulo { } // Evite!
```

### 4. **Teste de Substituibilidade**
```
Pergunte: "Se eu substituir A por B, o código quebra?"
Se SIM → Viola LSP
Se NÃO → Segue LSP
```

## ⚖️ LSP e Pragmatismo

### Nem Toda Relação "IS-A" é Programaticamente Correta

- Biologicamente, pinguim é uma ave
- Matematicamente, quadrado é um retângulo
- Mas programaticamente, pode violar LSP

**Solução:** Modele baseado em comportamento necessário, não em taxonomia real.

## 🔗 Relação com Outros Princípios SOLID

- **OCP**: LSP é necessário para OCP funcionar - extensões devem ser substituíveis
- **DIP**: Abstrações dependem de LSP para polimorfismo funcionar
- **SRP**: Classes com responsabilidade única são mais fáceis de tornar substituíveis

## 📚 Exemplos Práticos

Veja os exemplos de código neste diretório:
- `FormaSegueLSP.java` - Implementação com substituibilidade correta
- `FormaViolaLSP.java` - Exemplo de herança que viola substituibilidade

## 🎯 Exercícios Práticos

1. **Análise**: Revise hierarquias de herança em seu código
2. **Teste**: Escreva testes para superclasse e execute para subclasses
3. **Identificação**: Encontre `instanceof` ou `getClass()` - pode indicar violação
4. **Refatoração**: Corrija hierarquias problemáticas com composição ou interfaces

## 📖 Leituras Recomendadas

1. **"Data Abstraction and Hierarchy"** - Barbara Liskov (1988) - Artigo original
2. **"Agile Software Development"** - Robert C. Martin (2002) - LSP explicado
3. **"Effective Java"** - Joshua Bloch (2018) - Capítulo sobre herança

## 💭 Citações Inspiradoras

> "The LSP is important because it is one of the main attributes that enables the Open-Closed Principle." - Robert C. Martin

> "Inheritance is often useful, but more often than not, it is inappropriate." - Barbara Liskov

---

**Lembre-se:** LSP não é apenas sobre herança - é sobre contratos e substituibilidade. Se uma subclasse não pode honrar completamente o contrato da superclasse, não use herança. Use composição ou interfaces para expressar relações de forma que respeite a substituibilidade.
