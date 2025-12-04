# Strategy Pattern (Padrão Estratégia)

O padrão Strategy define uma família de algoritmos, encapsula cada um deles e os torna intercambiáveis. Strategy permite que o algoritmo varie independentemente dos clientes que o utilizam.

## 🎯 Problema

Como permitir que um objeto use diferentes algoritmos ou comportamentos sem usar condicionais complexos (if/else, switch)?

**Exemplo Real**: Sistema de cálculo de frete de e-commerce que precisa suportar diferentes transportadoras:
- PAC (Correios - mais barato, mais lento)
- SEDEX (Correios - mais caro, mais rápido)  
- Transportadora privada (preço variável, prazos intermediários)

Como fazer isso sem ter vários `if/else` espalhados pelo código?

## 💡 Solução

O Strategy separa os algoritmos em classes distintas, cada uma implementando uma interface comum:
- **Strategy**: Interface que define o contrato do algoritmo
- **ConcreteStrategy**: Implementações específicas do algoritmo
- **Context**: Classe que usa a estratégia, mantém referência para uma Strategy

## 🏗️ Estrutura UML

```
┌─────────────────┐
│    Context      │
├─────────────────┤         usa        ┌──────────────────┐
│ - strategy      │◇──────────────────→│    Strategy      │
├─────────────────┤                    ├──────────────────┤
│ + setStrategy() │                    │ + execute()      │
│ + doWork()      │                    └──────────────────┘
└─────────────────┘                            △
                                               │
                                               │
                   ┌───────────────────────────┼───────────────────────────┐
                   │                           │                           │
        ┌──────────┴────────────┐   ┌─────────┴──────────┐   ┌───────────┴──────────┐
        │ ConcreteStrategyA     │   │ ConcreteStrategyB  │   │ ConcreteStrategyC    │
        ├───────────────────────┤   ├────────────────────┤   ├──────────────────────┤
        │ + execute()           │   │ + execute()        │   │ + execute()          │
        └───────────────────────┘   └────────────────────┘   └──────────────────────┘
```

## 📋 Componentes

### [ShippingStrategy.java](ShippingStrategy.java)
Interface que define o contrato para estratégias de cálculo de frete.

### [PACStrategy.java](PACStrategy.java)
Estratégia concreta para cálculo de frete via PAC (Correios).

### [SEDEXStrategy.java](SEDEXStrategy.java)
Estratégia concreta para cálculo de frete via SEDEX (Correios).

### [PrivateCarrierStrategy.java](PrivateCarrierStrategy.java)
Estratégia concreta para cálculo de frete via transportadora privada.

### [ShippingCalculator.java](ShippingCalculator.java)
Contexto que utiliza as estratégias para calcular frete.

### [TesteStrategy.java](TesteStrategy.java)
Programa principal que demonstra o uso do padrão Strategy.

## 🚀 Como Executar

```bash
# Compilar todos os arquivos
javac *.java

# Executar o teste
java TesteStrategy
```

## 📝 Exemplo de Uso

```java
// Criar contexto
ShippingCalculator calculator = new ShippingCalculator();

// Usar estratégia PAC
calculator.setStrategy(new PACStrategy());
double cost = calculator.calculate(5.0, 1000);  // 5kg, 1000km

// Trocar estratégia em tempo de execução
calculator.setStrategy(new SEDEXStrategy());
cost = calculator.calculate(5.0, 1000);

// Outra estratégia
calculator.setStrategy(new PrivateCarrierStrategy());
cost = calculator.calculate(5.0, 1000);
```

## ✅ Vantagens

1. **Eliminação de Condicionais**: Remove estruturas `if/else` ou `switch` complexas
2. **Open/Closed Principle**: Novas estratégias sem modificar código existente
3. **Substituição em Runtime**: Algoritmos podem ser trocados dinamicamente
4. **Encapsulamento**: Cada algoritmo é independente e encapsulado
5. **Testabilidade**: Fácil testar cada estratégia isoladamente
6. **Single Responsibility**: Cada estratégia tem uma responsabilidade única

## ⚠️ Desvantagens

1. **Número de Classes**: Aumenta o número de classes no sistema
2. **Overhead de Comunicação**: Cliente precisa conhecer as diferentes estratégias
3. **Complexidade Inicial**: Pode ser overkill para algoritmos simples
4. **Exposição de Implementação**: Cliente deve entender diferenças entre estratégias

## 🎓 Quando Usar

✅ **Use Strategy quando:**
- Você tem muitas variantes de um algoritmo
- Algoritmos devem ser selecionados em tempo de execução
- Você quer evitar condicionais complexos
- Classes diferem apenas no comportamento
- Você precisa isolar lógica de negócio de implementação de algoritmo

❌ **Evite Strategy quando:**
- Você tem apenas um ou dois algoritmos simples
- Algoritmo raramente muda
- Clientes não precisam conhecer diferentes implementações
- Overhead de múltiplas classes não justifica benefício

## 🔍 Strategy vs State vs Command

| Aspecto | Strategy | State | Command |
|---------|----------|-------|---------|
| **Propósito** | Algoritmos intercambiáveis | Comportamento baseado em estado | Encapsular requisições |
| **Mudança** | Cliente escolhe | Automática (interno) | Por execução |
| **Foco** | Como fazer algo | O que fazer baseado em estado | O que fazer e quando |
| **Substituição** | Explícita pelo cliente | Implícita por transições | N/A |

## 💡 Variações do Padrão

### 1. Strategy com Context Passado

```java
public interface ShippingStrategy {
    double calculate(ShippingContext context);
}

// Context tem mais informações
public class ShippingContext {
    private double weight;
    private int distance;
    private boolean express;
    private String destination;
    // ...
}
```

### 2. Strategy com Factory

```java
public class ShippingStrategyFactory {
    public static ShippingStrategy getStrategy(String type) {
        switch (type) {
            case "PAC": return new PACStrategy();
            case "SEDEX": return new SEDEXStrategy();
            default: return new PrivateCarrierStrategy();
        }
    }
}
```

### 3. Strategy com Enums (Java 8+)

```java
public enum ShippingStrategy {
    PAC((w, d) -> w * 0.5 + d * 0.01),
    SEDEX((w, d) -> w * 1.0 + d * 0.02),
    PRIVATE((w, d) -> w * 0.8 + d * 0.015);
    
    private BiFunction<Double, Integer, Double> calculator;
    
    ShippingStrategy(BiFunction<Double, Integer, Double> calc) {
        this.calculator = calc;
    }
    
    public double calculate(double weight, int distance) {
        return calculator.apply(weight, distance);
    }
}
```

### 4. Strategy com Lambda (Java 8+)

```java
// Interface funcional
@FunctionalInterface
public interface ShippingStrategy {
    double calculate(double weight, int distance);
}

// Uso com lambda
calculator.setStrategy((w, d) -> w * 0.5 + d * 0.01);
```

## 🌟 Exemplos do Mundo Real

1. **Java Collections Sort**:
```java
Collections.sort(list, new Comparator<String>() {
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }
});

// Com lambda (Java 8+)
Collections.sort(list, (s1, s2) -> s1.length() - s2.length());
```

2. **Spring Framework**:
```java
@Component
public class PaymentService {
    private PaymentStrategy strategy;
    
    public void processPayment(String type, double amount) {
        strategy = strategyFactory.getStrategy(type);
        strategy.process(amount);
    }
}
```

3. **Validação de Formulários**:
```java
validator.setStrategy(new EmailValidationStrategy());
validator.setStrategy(new PhoneValidationStrategy());
validator.setStrategy(new CPFValidationStrategy());
```

## 🎯 Exercícios Práticos

### Exercício 1: Sistema de Pagamento
Implemente diferentes estratégias de pagamento:
- Cartão de Crédito (com parcelas)
- Boleto (com desconto)
- PIX (instantâneo)

### Exercício 2: Compressão de Arquivos
Crie estratégias para diferentes algoritmos:
- ZIP compression
- GZIP compression
- RAR compression

### Exercício 3: Validação de Dados
Implemente validadores com estratégias:
- Email
- CPF/CNPJ
- Telefone
- CEP

### Exercício 4: Algoritmos de Ordenação
Compare performance de estratégias:
- Bubble Sort
- Quick Sort
- Merge Sort

---

**Navegação**:
- **Voltar**: [Padrões Comportamentais](../)
- **Anterior**: [Observer Pattern](../observer/)
- **Próximo**: [Command Pattern](../command/)
