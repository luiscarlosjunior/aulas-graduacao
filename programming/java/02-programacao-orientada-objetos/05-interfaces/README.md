# Interfaces - Contratos e Múltipla Implementação

## 🎯 O que são Interfaces?

Uma **interface** em Java é um contrato que define:

- **Quais métodos** uma classe deve implementar (sem definir como)
- **Constantes** que podem ser compartilhadas
- **Comportamentos comuns** para classes não relacionadas hierarquicamente
- **Múltipla herança de comportamento** (uma classe pode implementar várias interfaces)

**Analogia**: Como um contrato de trabalho - define o que deve ser feito (responsabilidades), mas não como fazer. Diferentes pessoas podem cumprir o mesmo contrato de maneiras diferentes.

## 📋 Sintaxe e Características

### Definindo uma Interface

```java
public interface Voavel {
    // Constantes (implicitamente public static final)
    int VELOCIDADE_MAXIMA = 1000;
    String TIPO_MOVIMENTO = "Aéreo";
    
    // Métodos abstratos (implicitamente public abstract)
    void decolar();
    void voar();
    void aterrissar();
    double calcularTempoVoo(double distancia);
    
    // Método default (Java 8+) - implementação padrão
    default void planar() {
        System.out.println("Planando suavemente...");
    }
    
    // Método estático (Java 8+)
    static void informacoesVoo() {
        System.out.println("Informações sobre voo disponíveis.");
    }
}
```

### Implementando uma Interface

```java
public class Aviao implements Voavel {
    private String modelo;
    private boolean voando;
    
    @Override
    public void decolar() {
        System.out.println("Avião " + modelo + " decolando da pista...");
        voando = true;
    }
    
    @Override
    public void voar() {
        if (voando) {
            System.out.println("Avião voando a " + VELOCIDADE_MAXIMA + " km/h");
        }
    }
    
    @Override
    public void aterrissar() {
        System.out.println("Avião " + modelo + " aterrissando...");
        voando = false;
    }
    
    @Override
    public double calcularTempoVoo(double distancia) {
        return distancia / VELOCIDADE_MAXIMA;
    }
    
    // Pode usar método default da interface ou sobrescrever
    // planar() já está disponível sem implementar
}
```

### Múltipla Implementação

```java
// Uma classe pode implementar várias interfaces
public class Passaro implements Voavel, Animal {
    private String especie;
    
    // Implementa métodos de Voavel
    @Override
    public void decolar() {
        System.out.println("Pássaro " + especie + " batendo as asas...");
    }
    
    @Override
    public void voar() {
        System.out.println("Pássaro voando graciosamente");
    }
    
    @Override
    public void aterrissar() {
        System.out.println("Pássaro pousando no galho");
    }
    
    @Override
    public double calcularTempoVoo(double distancia) {
        return distancia / 50; // Velocidade menor que avião
    }
    
    // Implementa métodos de Animal
    @Override
    public void comer() {
        System.out.println("Pássaro se alimentando");
    }
    
    @Override
    public void dormir() {
        System.out.println("Pássaro dormindo no ninho");
    }
}
```

## 🔧 Tipos de Métodos em Interfaces

### 1. **Métodos Abstratos** (Padrão)
```java
public interface Exemplo {
    void metodoObrigatorio();  // Deve ser implementado
}
```

### 2. **Métodos Default** (Java 8+)
```java
public interface Exemplo {
    default void metodoComImplementacao() {
        System.out.println("Implementação padrão");
    }
}
```

### 3. **Métodos Estáticos** (Java 8+)
```java
public interface Exemplo {
    static void metodoUtilitario() {
        System.out.println("Método estático da interface");
    }
}
```

### 4. **Métodos Privados** (Java 9+)
```java
public interface Exemplo {
    default void metodoPublico() {
        metodoPrivado(); // Reutilização interna
    }
    
    private void metodoPrivado() {
        System.out.println("Usado apenas internamente");
    }
}
```

## 🏗️ Exemplo Prático: Sistema de Pagamentos

```java
// Interface principal
public interface ProcessadorPagamento {
    double TAXA_MAXIMA = 0.10; // 10%
    
    boolean processar(double valor);
    double calcularTaxa(double valor);
    String obterTipoPagamento();
    
    // Método default para validação comum
    default boolean validarValor(double valor) {
        return valor > 0 && valor <= 10000;
    }
    
    // Método estático utilitário
    static void exibirInformacoesSistema() {
        System.out.println("Sistema de Pagamentos v2.0");
        System.out.println("Taxa máxima permitida: " + TAXA_MAXIMA);
    }
}

// Interface adicional para operações online
public interface PagamentoOnline {
    void autenticar(String token);
    boolean verificarConexao();
}

// Implementação: Cartão de Crédito
public class CartaoCredito implements ProcessadorPagamento, PagamentoOnline {
    private String numero;
    private boolean autenticado;
    
    @Override
    public boolean processar(double valor) {
        if (!validarValor(valor)) {
            System.out.println("Valor inválido para cartão");
            return false;
        }
        
        if (!autenticado) {
            System.out.println("Cartão não autenticado");
            return false;
        }
        
        System.out.println("Processando R$ " + valor + " no cartão " + numero);
        return true;
    }
    
    @Override
    public double calcularTaxa(double valor) {
        return valor * 0.03; // 3% para cartão
    }
    
    @Override
    public String obterTipoPagamento() {
        return "Cartão de Crédito";
    }
    
    @Override
    public void autenticar(String token) {
        // Simula autenticação
        this.autenticado = token.length() > 10;
        System.out.println("Cartão " + (autenticado ? "autenticado" : "não autenticado"));
    }
    
    @Override
    public boolean verificarConexao() {
        // Simula verificação de conexão
        return true;
    }
}

// Implementação: PIX
public class PIX implements ProcessadorPagamento, PagamentoOnline {
    private String chave;
    
    @Override
    public boolean processar(double valor) {
        if (!validarValor(valor)) return false;
        
        System.out.println("Transferindo R$ " + valor + " via PIX");
        return true;
    }
    
    @Override
    public double calcularTaxa(double valor) {
        return 0; // PIX sem taxa
    }
    
    @Override
    public String obterTipoPagamento() {
        return "PIX";
    }
    
    @Override
    public void autenticar(String token) {
        System.out.println("PIX autenticado via token bancário");
    }
    
    @Override
    public boolean verificarConexao() {
        return true;
    }
}

// Implementação: Boleto (apenas ProcessadorPagamento)
public class Boleto implements ProcessadorPagamento {
    private String codigoBarras;
    
    @Override
    public boolean processar(double valor) {
        if (!validarValor(valor)) return false;
        
        System.out.println("Gerando boleto de R$ " + valor);
        return true;
    }
    
    @Override
    public double calcularTaxa(double valor) {
        return 2.50; // Taxa fixa
    }
    
    @Override
    public String obterTipoPagamento() {
        return "Boleto Bancário";
    }
}
```

### Sistema de Uso Polimórfico

```java
public class SistemaPagamento {
    public static void main(String[] args) {
        // Array polimórfico de interfaces
        ProcessadorPagamento[] processadores = {
            new CartaoCredito(),
            new PIX(),
            new Boleto()
        };
        
        double valorCompra = 150.00;
        
        System.out.println("=== PROCESSAMENTO DE PAGAMENTOS ===");
        ProcessadorPagamento.exibirInformacoesSistema();
        
        for (ProcessadorPagamento proc : processadores) {
            System.out.println("\n--- " + proc.obterTipoPagamento() + " ---");
            
            // Autenticação para pagamentos online
            if (proc instanceof PagamentoOnline) {
                PagamentoOnline online = (PagamentoOnline) proc;
                online.autenticar("token_seguro_123456");
            }
            
            // Processamento
            boolean sucesso = proc.processar(valorCompra);
            if (sucesso) {
                double taxa = proc.calcularTaxa(valorCompra);
                double total = valorCompra + taxa;
                System.out.println("Taxa: R$ " + taxa);
                System.out.println("Total: R$ " + total);
            }
        }
    }
}
```

## 💡 Vantagens das Interfaces

### 1. **Múltipla Herança de Comportamento**
- Java não permite herança múltipla de classes
- Mas permite implementar múltiplas interfaces

### 2. **Desacoplamento**
- Código depende de interfaces, não implementações
- Facilita testes e manutenção

### 3. **Flexibilidade**
- Diferentes classes podem implementar a mesma interface
- Permite polimorfismo sem herança

### 4. **Contratos Claros**
- Define exatamente o que uma classe deve fazer
- Documentação viva do comportamento esperado

## 🔄 Interface vs Classe Abstrata

| Aspecto | Interface | Classe Abstrata |
|---------|-----------|-----------------|
| **Herança** | Múltipla implementação | Herança simples |
| **Métodos** | Abstratos, default, static | Abstratos + concretos |
| **Atributos** | Apenas constantes | Qualquer tipo |
| **Construtor** | Não tem | Pode ter |
| **Quando usar** | Contratos, múltipla herança | Código comum + abstração |

## ⚠️ Boas Práticas

### ✅ Nomeação Clara
```java
interface Corrivel {    // Comportamento
interface Comparavel {  // Capacidade
interface Processador { // Função
```

### ✅ Interfaces Pequenas e Focadas
```java
// ✅ Interface focada
interface Desenhavel {
    void desenhar();
}

// ❌ Interface muito grande
interface SuperInterface {
    void desenhar();
    void calcular();
    void processar();
    void validar();
    // ... muitos métodos não relacionados
}
```

### ✅ Use Composição de Interfaces
```java
interface Leitor {
    String ler();
}

interface Escritor {
    void escrever(String dados);
}

interface ProcessadorArquivo extends Leitor, Escritor {
    // Combina comportamentos
}
```

## 🚀 Exercícios Práticos

1. **Sistema de Dispositivos Eletrônicos**
   - Interface: `DispositivoEletronico` (ligar, desligar, status)
   - Implementações: `Smartphone`, `Tablet`, `Laptop`

2. **Sistema de Formas Geométricas**
   - Interface: `CalculavelArea` (calcularArea)
   - Interface: `Desenhavel` (desenhar)
   - Classes que implementam ambas

3. **Sistema de Notificações**
   - Interface: `Notificador` (enviarNotificacao)
   - Implementações: `Email`, `SMS`, `PushNotification`

## 🔗 Navegação

[← 04 - Polimorfismo](../04-polimorfismo/) | [06 - Classes Abstratas →](../06-classes-abstratas/)

---

**💡 Lembre-se**: Interfaces definem O QUE fazer, não COMO fazer. São contratos que garantem que certas funcionalidades estarão disponíveis!