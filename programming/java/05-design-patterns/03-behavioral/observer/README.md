# Observer Pattern (Padrão Observador)

O padrão Observer define uma dependência um-para-muitos entre objetos, de modo que quando um objeto muda de estado, todos os seus dependentes são notificados e atualizados automaticamente.

## 🎯 Problema

Como notificar múltiplos objetos automaticamente quando outro objeto muda de estado, sem criar forte acoplamento entre eles?

**Exemplo Real**: Imagine um sistema de e-commerce onde o preço de um produto pode mudar. Diferentes componentes precisam ser notificados:
- Display de preço na página do produto
- Lista de favoritos do usuário
- Sistema de alertas de preço
- Relatórios de vendas

Como fazer isso sem que o objeto `Produto` precise conhecer todos esses componentes?

## 💡 Solução

O Observer define:
- **Subject (Observable)**: Objeto observado que mantém lista de observers e os notifica
- **Observer**: Interface que define método de atualização
- **ConcreteObserver**: Implementações que reagem às notificações

## 🏗️ Estrutura UML

```
┌─────────────────┐         notifica      ┌──────────────────┐
│    Subject      │◇────────────────────→│    Observer      │
├─────────────────┤                       ├──────────────────┤
│ - observers     │                       │                  │
├─────────────────┤                       ├──────────────────┤
│ + attach()      │                       │ + update()       │
│ + detach()      │                       └──────────────────┘
│ + notify()      │                              △
└─────────────────┘                              │
        △                                        │
        │                                        │
┌───────┴──────────┐                    ┌────────┴─────────┐
│ ConcreteSubject  │                    │ ConcreteObserver │
├──────────────────┤                    ├──────────────────┤
│ - state          │                    │ - state          │
├──────────────────┤                    ├──────────────────┤
│ + getState()     │                    │ + update()       │
│ + setState()     │                    └──────────────────┘
└──────────────────┘
```

## 📋 Componentes

### [Subject.java](Subject.java)
Interface que define operações para gerenciar observers (attach, detach, notify).

### [Observer.java](Observer.java)
Interface que define o método de atualização chamado quando o subject muda.

### [ProductSubject.java](ProductSubject.java)
Subject concreto que representa um produto com preço observável.

### [PriceDisplay.java](PriceDisplay.java)
Observer que exibe o preço atual do produto.

### [PriceAlertObserver.java](PriceAlertObserver.java)
Observer que emite alerta quando o preço atinge determinado valor.

### [StockObserver.java](StockObserver.java)
Observer que monitora disponibilidade de estoque.

### [TesteObserver.java](TesteObserver.java)
Programa principal que demonstra o padrão Observer.

## 🚀 Como Executar

```bash
# Compilar todos os arquivos
javac *.java

# Executar o teste
java TesteObserver
```

## 📝 Exemplo de Uso

```java
// Criar subject (produto)
ProductSubject produto = new ProductSubject("Notebook", 3000.00);

// Criar observers
Observer display = new PriceDisplay("Display Principal");
Observer alert = new PriceAlertObserver("Alerta Cliente", 2500.00);
Observer stock = new StockObserver("Controle Estoque");

// Registrar observers
produto.attach(display);
produto.attach(alert);
produto.attach(stock);

// Mudar preço - todos observers são notificados
produto.setPrice(2800.00);
produto.setPrice(2400.00);  // Dispara alerta de preço

// Remover observer
produto.detach(alert);
produto.setPrice(2300.00);  // Alert não é mais notificado
```

## ✅ Vantagens

1. **Baixo Acoplamento**: Subject e observers são fracamente acoplados
   - Subject não conhece detalhes dos observers
   - Observers podem ser adicionados/removidos dinamicamente

2. **Open/Closed Principle**: 
   - Novos observers sem modificar subject
   - Extensível sem alterar código existente

3. **Broadcast de Comunicação**:
   - Um evento notifica múltiplos objetos
   - Comunicação um-para-muitos eficiente

4. **Notificação Automática**:
   - Observers sempre sincronizados
   - Não precisa polling manual

5. **Suporte a Múltiplos Observers**:
   - Número ilimitado de observers
   - Cada um pode ter comportamento diferente

## ⚠️ Desvantagens

1. **Ordem de Notificação Incerta**:
   - Observers são notificados em ordem não garantida
   - Pode causar problemas se houver dependências

2. **Memory Leaks**:
   - Observers não desanexados podem causar vazamento de memória
   - Importante implementar detach corretamente

3. **Atualizações em Cascata**:
   - Update pode disparar outros updates
   - Risco de loops infinitos ou performance degradada

4. **Overhead de Notificações**:
   - Todos observers são notificados mesmo se não interessados
   - Pode ser ineficiente com muitos observers

5. **Debugging Complexo**:
   - Fluxo de execução menos óbvio
   - Dificulta rastreamento de causa-efeito

## 🎓 Conceitos Relacionados

### Push vs Pull Model

**Push Model** (usado neste exemplo):
```java
// Subject envia dados na notificação
void update(String productName, double price);
```
- Subject empurra informações para observers
- Observers recebem todos os dados necessários
- Mais eficiente se observers precisam de poucos dados

**Pull Model**:
```java
// Observer busca dados do subject
void update(Subject subject);
// Dentro do observer:
double price = ((ProductSubject) subject).getPrice();
```
- Observers buscam dados que precisam do subject
- Mais flexível: observer decide o que precisa
- Melhor quando diferentes observers precisam de dados diferentes

### Observer vs Pub/Sub

**Observer** (este padrão):
- Subject conhece seus observers
- Acoplamento em tempo de compilação
- Comunicação síncrona típica

**Pub/Sub** (Event Bus):
- Publishers e subscribers não se conhecem
- Mediador (event bus) no meio
- Comunicação assíncrona típica

## 🔍 Variações do Padrão

### 1. Observer com Dados Específicos

```java
// Interface específica com dados necessários
interface PriceObserver {
    void onPriceChanged(String product, double oldPrice, double newPrice);
}
```

### 2. Observer com Filtros

```java
// Observer só é notificado em condições específicas
public void attach(Observer o, Predicate<State> filter) {
    observers.put(o, filter);
}

private void notifyObservers() {
    for (Map.Entry<Observer, Predicate<State>> entry : observers.entrySet()) {
        if (entry.getValue().test(currentState)) {
            entry.getKey().update();
        }
    }
}
```

### 3. Observer com Java util.Observable

```java
// Usando classe Observable do Java (deprecated desde Java 9)
import java.util.Observable;
import java.util.Observer;

class Product extends Observable {
    private double price;
    
    public void setPrice(double price) {
        this.price = price;
        setChanged();
        notifyObservers(price);
    }
}
```

### 4. Observer com PropertyChangeListener (Java Beans)

```java
import java.beans.*;

class Product {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private double price;
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    
    public void setPrice(double price) {
        double oldPrice = this.price;
        this.price = price;
        support.firePropertyChange("price", oldPrice, price);
    }
}
```

## 🌟 Padrões Relacionados

1. **Mediator**: Centraliza comunicação complexa entre objetos
   - Observer: comunicação um-para-muitos
   - Mediator: comunicação muitos-para-muitos

2. **MVC (Model-View-Controller)**: 
   - Model é Subject
   - Views são Observers
   - Controller manipula Model

3. **Event Sourcing**:
   - Armazena mudanças como eventos
   - Observers reagem a eventos

## 💡 Quando Usar

✅ **Use Observer quando:**
- Mudança em objeto requer atualização de outros objetos
- Número de objetos dependentes é desconhecido ou pode mudar
- Você quer notificação automática sem polling
- Você precisa de baixo acoplamento entre componentes
- Sistema reativo ou event-driven

❌ **Evite Observer quando:**
- Notificações são excessivamente frequentes (overhead)
- Ordem de notificação é crítica
- Relação entre objetos é simples e direta
- Performance é crítica e há muitos observers

## 📚 Exemplos do Mundo Real

1. **Java Swing/AWT**: 
   ```java
   button.addActionListener(e -> System.out.println("Clicked!"));
   ```

2. **Android**: 
   ```java
   liveData.observe(this, data -> updateUI(data));
   ```

3. **RxJava/Reactive Streams**:
   ```java
   observable.subscribe(data -> process(data));
   ```

4. **Spring Events**:
   ```java
   @EventListener
   public void handleEvent(CustomEvent event) { }
   ```

5. **JavaScript DOM Events**:
   ```javascript
   element.addEventListener('click', handler);
   ```

## 🎯 Exercícios Práticos

### Exercício 1: Newsletter System
Implemente sistema de newsletter onde:
- Usuários podem se inscrever/desinscrever
- Publicador envia notícias
- Diferentes canais: email, SMS, push notification

### Exercício 2: Dashboard Multi-View
Crie dashboard com múltiplas visualizações:
- Dados centrais mudam (ex: vendas)
- Gráfico de barras atualiza
- Tabela de dados atualiza
- Sumário estatístico atualiza

### Exercício 3: Weather Station
Sistema de estação meteorológica:
- Sensores captam temperatura, umidade, pressão
- Múltiplos displays mostram dados
- Alertas são disparados em condições específicas

### Exercício 4: Stock Market Monitor
Monitor de ações:
- Preços de ações mudam
- Portfolio do usuário atualiza valor total
- Alertas de preço são disparados
- Gráficos são atualizados em tempo real

## 🔧 Implementação Thread-Safe

Para ambientes multi-thread:

```java
public class ThreadSafeSubject {
    private final List<Observer> observers = 
        Collections.synchronizedList(new ArrayList<>());
    
    public synchronized void attach(Observer o) {
        observers.add(o);
    }
    
    public synchronized void detach(Observer o) {
        observers.remove(o);
    }
    
    protected void notifyObservers() {
        List<Observer> observersCopy;
        synchronized (this) {
            observersCopy = new ArrayList<>(observers);
        }
        for (Observer observer : observersCopy) {
            observer.update();
        }
    }
}
```

---

**Navegação**:
- **Voltar**: [Padrões Comportamentais](../)
- **Próximo**: [Strategy Pattern](../strategy/)
