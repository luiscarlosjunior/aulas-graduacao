# Singleton Pattern

O padrão Singleton garante que uma classe tenha apenas uma instância e fornece um ponto de acesso global a essa instância.

## 🎯 Problema

Como garantir que uma classe tenha apenas uma instância e que essa instância seja facilmente acessível?

## 💡 Solução

- Fazer o construtor privado
- Criar um método estático que retorna a única instância
- Controlar a criação da instância

## 🏗️ Estrutura

```
┌─────────────────┐
│   Singleton     │
├─────────────────┤
│ - instance      │
├─────────────────┤
│ - Singleton()   │
│ + getInstance() │
│ + operacao()    │
└─────────────────┘
```

## 📋 Implementações

### [DatabaseConnection.java](DatabaseConnection.java)
Exemplo clássico de conexão com banco de dados usando Singleton thread-safe.

### [Logger.java](Logger.java) 
Sistema de log global usando Singleton com enum (mais seguro).

### [ConfiguracaoApp.java](ConfiguracaoApp.java)
Gerenciador de configurações da aplicação.

### [TesteSingleton.java](TesteSingleton.java)
Programa de teste demonstrando o uso dos diferentes Singletons.

## 🚀 Como Executar

```bash
javac *.java
java TesteSingleton
```

## ✅ Vantagens

- **Controle de acesso**: Apenas uma instância
- **Economia de recursos**: Não cria objetos desnecessários
- **Ponto de acesso global**: Fácil acesso em toda aplicação
- **Inicialização lazy**: Cria apenas quando necessário

## ⚠️ Desvantagens

- **Acoplamento**: Pode criar dependências globais
- **Testabilidade**: Difícil de mockar em testes
- **Concorrência**: Requer cuidados em ambientes multi-thread
- **Violação SRP**: Controla instanciação e funcionalidade

## 🧵 Thread Safety

### Problema
```java
// NÃO thread-safe
if (instance == null) {
    instance = new Singleton(); // Duas threads podem entrar aqui
}
```

### Soluções

1. **Synchronized Method** (mais lento)
```java
public static synchronized Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton();
    }
    return instance;
}
```

2. **Double-Checked Locking** (mais eficiente)
```java
public static Singleton getInstance() {
    if (instance == null) {
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
            }
        }
    }
    return instance;
}
```

3. **Enum Singleton** (mais seguro)
```java
public enum Singleton {
    INSTANCE;
    
    public void operacao() {
        // implementação
    }
}
```

## 🎯 Quando Usar

✅ **Use quando**:
- Precisa de exatamente uma instância (ex: conexão DB)
- Ponto de acesso global é necessário
- Instância deve ser lazy-initialized
- Controle rigoroso sobre recursos

❌ **Evite quando**:
- Pode ser substituído por injeção de dependência
- Testabilidade é prioridade
- Sistema pode precisar de múltiplas instâncias no futuro

## 📝 Exercícios

1. Implemente um Singleton para cache de aplicação
2. Crie um Singleton thread-safe para contador global
3. Desenvolva um Singleton para configurações de tema
4. Compare performance das diferentes implementações

## 🔗 Padrões Relacionados

- **Factory Method**: Pode usar Singleton para factory
- **Builder**: Builder pode ser Singleton
- **Facade**: Facade frequentemente é Singleton