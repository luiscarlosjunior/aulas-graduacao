# Factory Method Pattern

O padrão Factory Method define uma interface para criar objetos, mas permite que as subclasses decidam qual classe instanciar.

## 🎯 Problema

Como criar objetos sem especificar suas classes exatas, delegando a decisão de instanciação para subclasses?

## 💡 Solução

- Definir uma interface ou classe abstrata para criação
- Implementar o método de criação em classes concretas
- Permitir extensibilidade para novos tipos

## 🏗️ Estrutura

```
    Creator
    ├── factoryMethod(): Product
    └── operacao()
         ↓
┌─────────────────┐
│ ConcreteCreator │
└─────────────────┘
         ↓
┌─────────────────┐
│ ConcreteProduct │
└─────────────────┘
```

## 📋 Implementações

### [NotificacaoFactory.java](NotificacaoFactory.java)
Factory abstrata para diferentes tipos de notificações.

### [EmailNotificacao.java](EmailNotificacao.java)
Implementação concreta para notificações por email.

### [SMSNotificacao.java](SMSNotificacao.java)
Implementação concreta para notificações por SMS.

### [PushNotificacao.java](PushNotificacao.java)
Implementação concreta para notificações push.

### [TesteFactoryMethod.java](TesteFactoryMethod.java)
Programa de teste demonstrando o uso do padrão.

## 🚀 Como Executar

```bash
javac *.java
java TesteFactoryMethod
```

## ✅ Vantagens

- **Flexibilidade**: Fácil adição de novos produtos
- **Desacoplamento**: Creator não depende de classes concretas
- **Reutilização**: Código comum no creator base
- **Polimorfismo**: Uso uniforme de diferentes produtos

## ⚠️ Desvantagens

- **Complexidade**: Pode criar hierarquias desnecessárias
- **Código adicional**: Mais classes para manter
- **Indireção**: Uma camada extra de abstração

## 🎯 Quando Usar

✅ **Use quando**:
- Não sabe de antemão quais tipos criar
- Classe deve delegar criação para subclasses
- Processo de criação pode variar
- Precisa isolar a lógica de criação

❌ **Evite quando**:
- Tipos de objetos são fixos e conhecidos
- Criação é simples e direta
- Sistema não precisa de extensibilidade

## 📝 Exemplo Prático

```java
// Factory abstrata
abstract class VeiculoFactory {
    public abstract Veiculo criarVeiculo();
    
    public void processar() {
        Veiculo v = criarVeiculo();
        v.acelerar();
    }
}

// Factory concreta
class CarroFactory extends VeiculoFactory {
    public Veiculo criarVeiculo() {
        return new Carro();
    }
}
```

## 🔗 Padrões Relacionados

- **Abstract Factory**: Factory de families de objetos
- **Builder**: Construção complexa vs criação simples
- **Prototype**: Clonagem vs criação nova