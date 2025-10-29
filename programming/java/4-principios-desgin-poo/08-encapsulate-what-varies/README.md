# Encapsulate What Varies
## Encapsule o Que Varia

## 📖 Visão Geral

**Encapsulate What Varies** (Encapsule o Que Varia) é um princípio fundamental de design que estabelece que devemos identificar os aspectos de nosso código que variam ou mudam com frequência e separá-los dos aspectos que permanecem estáveis. Ao encapsular variação, isolamos o impacto de mudanças e tornamos o sistema mais manutenível e adaptável.

## 🎯 Definição

> "Encapsulate the concept that varies."
>
> "Encapsule o conceito que varia."
>
> -- Gang of Four, Design Patterns (1994)

**Complemento de Robert C. Martin:**
> "Identify the aspects of your application that vary and separate them from what stays the same."
>
> "Identifique os aspectos de sua aplicação que variam e separe-os do que permanece igual."

**O princípio estabelece:**
- **Identifique** o que muda no sistema
- **Encapsule** aspectos variáveis em abstrações separadas
- **Isole** código estável de código variável
- **Proteja** partes estáveis de impacto de mudanças

## 📚 Origem e História

### Gang of Four (1994)

O princípio foi formulado pelo **Gang of Four** em seu livro **"Design Patterns"** (1994), onde é apresentado como fundamento para muitos design patterns.

### Relação com Design Patterns

Este princípio é a base para diversos patterns:
- **Strategy Pattern**: Encapsula algoritmos variáveis
- **State Pattern**: Encapsula estados variáveis
- **Template Method**: Encapsula passos variáveis
- **Factory Pattern**: Encapsula criação variável

### Evolução

O princípio foi amplamente adotado e refinado ao longo dos anos, tornando-se prática fundamental em design OO moderno.

## 🔍 Identificando o Que Varia

### Perguntas-Chave:

```
❓ Quais partes do sistema tendem a mudar?
❓ Onde novos requisitos tipicamente impactam?
❓ Quais aspectos variam entre diferentes contextos?
❓ O que muda entre diferentes clientes/configurações?
```

### Exemplos de Variação:

1. **Algoritmos**: Diferentes formas de calcular, ordenar, validar
2. **Formato**: PDF, Excel, HTML, JSON
3. **Protocolo**: HTTP, FTP, WebSocket
4. **Plataforma**: Windows, Linux, Mac
5. **Configuração**: Desenvolvimento, Produção, Teste
6. **Comportamento**: Agressivo, Defensivo, Normal
7. **Estratégia de Negócio**: Desconto, Frete, Imposto

## 🎯 Por Que Encapsular Variação é Importante?

### 1. **Isola Impacto de Mudanças**
Mudanças em aspectos variáveis não afetam código estável.

### 2. **Facilita Adição de Variações**
Adicionar nova variação = criar nova classe, sem modificar existentes.

### 3. **Reduz Riscos**
Mudanças são localizadas, reduzindo riscos de regressão.

### 4. **Melhora Testabilidade**
Variações podem ser testadas isoladamente.

### 5. **Promove Reutilização**
Variações encapsuladas podem ser reutilizadas em contextos diferentes.

## ❌ Violação: Aspectos Variáveis Não Encapsulados

### Exemplo: Lógica Variável Misturada

```java
// ❌ Lógica de cálculo de frete não encapsulada
// Cada mudança afeta a classe inteira

public class Pedido {
    private List<Item> itens;
    private String endereco;
    private String tipoEntrega;
    
    public double calcularTotal() {
        double subtotal = calcularSubtotal();
        double frete = 0;
        
        // ❌ Lógica de frete misturada - varia com frequência
        if (tipoEntrega.equals("NORMAL")) {
            frete = 10.0;
        } else if (tipoEntrega.equals("EXPRESS")) {
            frete = 25.0;
        } else if (tipoEntrega.equals("SEDEX")) {
            frete = 20.0;
        } else if (tipoEntrega.equals("RETIRADA")) {
            frete = 0;
        }
        // Adicionar novo tipo = modificar este método
        
        // ❌ Lógica de desconto também misturada - varia
        double desconto = 0;
        if (subtotal > 500) {
            desconto = subtotal * 0.10;
        } else if (subtotal > 200) {
            desconto = subtotal * 0.05;
        }
        
        return subtotal + frete - desconto;
    }
    
    private double calcularSubtotal() {
        return itens.stream()
            .mapToDouble(Item::getPreco)
            .sum();
    }
}
```

**Problemas:**
- Mudança em cálculo de frete afeta toda classe
- Adicionar tipo de entrega requer modificar método
- Lógica de frete e desconto misturadas
- Impossível testar cálculos isoladamente
- Viola Open/Closed Principle

## ✅ Seguindo o Princípio: Aspectos Variáveis Encapsulados

### Exemplo: Variação Devidamente Encapsulada

```java
// ✅ Encapsula variação: Cálculo de frete

public interface CalculadoraFrete {
    double calcular(Pedido pedido);
}

public class FreteNormal implements CalculadoraFrete {
    @Override
    public double calcular(Pedido pedido) {
        return 10.0;
    }
}

public class FreteExpress implements CalculadoraFrete {
    @Override
    public double calcular(Pedido pedido) {
        return 25.0;
    }
}

public class FreteSedex implements CalculadoraFrete {
    @Override
    public double calcular(Pedido pedido) {
        return 20.0;
    }
}

public class FreteRetirada implements CalculadoraFrete {
    @Override
    public double calcular(Pedido pedido) {
        return 0;
    }
}

// ✅ Adicionar novo tipo é criar nova classe - não modifica existentes!
public class FreteInternacional implements CalculadoraFrete {
    @Override
    public double calcular(Pedido pedido) {
        return 100.0;
    }
}

// ✅ Encapsula variação: Cálculo de desconto

public interface CalculadoraDesconto {
    double calcular(double subtotal);
}

public class DescontoPorValor implements CalculadoraDesconto {
    @Override
    public double calcular(double subtotal) {
        if (subtotal > 500) {
            return subtotal * 0.10;
        } else if (subtotal > 200) {
            return subtotal * 0.05;
        }
        return 0;
    }
}

public class DescontoProgressivo implements CalculadoraDesconto {
    @Override
    public double calcular(double subtotal) {
        // Outra estratégia de desconto
        return subtotal * 0.08;
    }
}

// ✅ Pedido usa aspectos variáveis encapsulados

public class Pedido {
    private List<Item> itens;
    private String endereco;
    private CalculadoraFrete calculadoraFrete;
    private CalculadoraDesconto calculadoraDesconto;
    
    public Pedido(CalculadoraFrete frete, CalculadoraDesconto desconto) {
        this.itens = new ArrayList<>();
        this.calculadoraFrete = frete;
        this.calculadoraDesconto = desconto;
    }
    
    public double calcularTotal() {
        double subtotal = calcularSubtotal();
        double frete = calculadoraFrete.calcular(this);
        double desconto = calculadoraDesconto.calcular(subtotal);
        
        return subtotal + frete - desconto;
    }
    
    private double calcularSubtotal() {
        return itens.stream()
            .mapToDouble(Item::getPreco)
            .sum();
    }
    
    public List<Item> getItens() {
        return itens;
    }
    
    // ✅ Pode trocar estratégias em runtime
    public void setCalculadoraFrete(CalculadoraFrete calculadoraFrete) {
        this.calculadoraFrete = calculadoraFrete;
    }
    
    public void setCalculadoraDesconto(CalculadoraDesconto calculadoraDesconto) {
        this.calculadoraDesconto = calculadoraDesconto;
    }
}

// ✅ Uso flexível:

public class Main {
    public static void main(String[] args) {
        // Cria pedido com frete normal e desconto por valor
        Pedido pedido = new Pedido(
            new FreteNormal(),
            new DescontoPorValor()
        );
        
        System.out.println("Total: " + pedido.calcularTotal());
        
        // ✅ Muda para frete express em runtime
        pedido.setCalculadoraFrete(new FreteExpress());
        
        // ✅ Adiciona novo tipo sem modificar código existente
        pedido.setCalculadoraFrete(new FreteInternacional());
    }
}
```

**Benefícios:**
- ✅ Mudanças em frete isoladas em classes específicas
- ✅ Novos tipos de frete não afetam `Pedido`
- ✅ Cálculos testáveis isoladamente
- ✅ Baixo acoplamento, alta coesão
- ✅ Segue Open/Closed Principle

## 📊 Padrões que Encapsulam Variação

### 1. **Strategy Pattern**
Encapsula família de algoritmos.

```java
public interface EstrategiaOrdenacao {
    void ordenar(List<Integer> lista);
}

public class OrdenacaoBubbleSort implements EstrategiaOrdenacao { }
public class OrdenacaoQuickSort implements EstrategiaOrdenacao { }
```

### 2. **State Pattern**
Encapsula estados e transições.

```java
public interface Estado {
    void processar(Contexto ctx);
}

public class EstadoAberto implements Estado { }
public class EstadoProcessando implements Estado { }
public class EstadoFechado implements Estado { }
```

### 3. **Template Method Pattern**
Encapsula passos variáveis de algoritmo.

```java
public abstract class ProcessadorRelatorio {
    public final void processar() {
        coletarDados();
        formatarDados(); // Varia
        exportar();      // Varia
    }
    
    protected abstract void formatarDados();
    protected abstract void exportar();
}
```

### 4. **Factory Pattern**
Encapsula criação de objetos.

```java
public interface VeiculoFactory {
    Veiculo criar(); // Criação varia
}

public class CarroFactory implements VeiculoFactory { }
public class MotoFactory implements VeiculoFactory { }
```

## 📋 Como Identificar Variação

### Técnicas de Identificação:

1. **Análise de Histórico**
```
- Revise histórico de commits
- Quais áreas mudam frequentemente?
- Onde bugs são mais comuns?
```

2. **Análise de Requisitos**
```
- Quais requisitos variam entre clientes?
- Quais funcionalidades têm múltiplas versões?
- O que é configurável?
```

3. **Code Smells**
```
- Múltiplos if/else ou switch cases
- Código duplicado com pequenas variações
- Flags/parâmetros para controlar comportamento
```

4. **Perguntas ao Time**
```
- "O que muda frequentemente?"
- "Onde temos dificuldade para adicionar features?"
- "Quais partes quebram quando mudamos?"
```

## 📋 Diretrizes Práticas

### 1. **Identifique Pontos de Variação**
```
Passo 1: Analise sistema
Passo 2: Identifique o que varia
Passo 3: O que permanece estável?
Passo 4: Separe variável de estável
```

### 2. **Crie Abstrações para Variações**
```java
// Identifica: "Como notificar varia"
// Cria abstração:
public interface Notificador {
    void notificar(String mensagem);
}

// Implementações variáveis:
public class NotificadorEmail implements Notificador { }
public class NotificadorSMS implements Notificador { }
public class NotificadorPush implements Notificador { }
```

### 3. **Use Dependency Injection**
```java
// Injeta variação
public class Servico {
    private Notificador notificador;
    
    public Servico(Notificador notificador) {
        this.notificador = notificador;
    }
}
```

### 4. **Prefira Configuração Externa**
```java
// Variação configurável externamente
Properties config = new Properties();
String tipo = config.getProperty("notificador.tipo");

Notificador notificador = NotificadorFactory.criar(tipo);
```

## ⚖️ Balanceando Encapsulamento e Pragmatismo

### Não Encapsule Prematuramente

```
❌ Evite: Criar abstração para algo que nunca muda
✅ Faça: Encapsule quando variação é real ou iminente
```

### Regra de Três

```
Primeira vez: Implemente diretamente
Segunda vez: Note similaridade, mas tolere
Terceira vez: Refatore e encapsule variação

Razão: Duas instâncias podem ser coincidência
Três instâncias indicam padrão real de variação
```

### YAGNI Aplica-se

Não crie abstrações para variações hipotéticas. Encapsule quando:
- ✅ Variação existe agora
- ✅ Requisitos indicam variação futura próxima
- ✅ Histórico mostra mudanças frequentes nessa área

## 🔗 Relação com Outros Princípios

- **OCP (Open/Closed)**: Encapsular variação facilita extensão
- **SRP (Single Responsibility)**: Cada variação tem sua classe
- **Strategy Pattern**: Implementação direta de encapsular variação
- **DIP (Dependency Inversion)**: Variação através de abstrações

## 📚 Exemplos Práticos

Veja os exemplos de código neste diretório:
- `EncapsuleOQueVaria.java` - Implementação com variação encapsulada

## 🎯 Exercícios Práticos

1. **Identificação**: Encontre if/else ou switch que representa variação
2. **Encapsulamento**: Refatore para encapsular variação em classes
3. **Extensão**: Adicione nova variação sem modificar código existente
4. **Análise**: Compare facilidade de mudança antes/depois

## 📖 Leituras Recomendadas

1. **"Design Patterns"** - Gang of Four (1994) - Formulação original
2. **"Head First Design Patterns"** - Freeman & Freeman (2004) - Strategy Pattern
3. **"Refactoring"** - Martin Fowler (1999) - Replace Conditional with Polymorphism

## 💭 Citações Inspiradoras

> "Identify the aspects of your application that vary and separate them from what stays the same." - Gang of Four

> "Encapsulate the concept that varies." - Gang of Four

> "The key to creating maintainable code is discovering what varies and encapsulating it." - Robert C. Martin

---

**Lembre-se:** Encapsular o que varia não é sobre prever o futuro. É sobre identificar pontos de variação reais no sistema e isolá-los através de abstrações, tornando mudanças localizadas e controladas. Quando algo muda, você deve poder adicionar ou modificar uma única classe encapsulada, não modificar código espalhado pelo sistema.
