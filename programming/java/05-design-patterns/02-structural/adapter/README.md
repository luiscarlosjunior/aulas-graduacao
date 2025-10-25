# Adapter Pattern

O padrão Adapter (também conhecido como Wrapper) converte a interface de uma classe em outra interface esperada pelos clientes. Permite que classes com interfaces incompatíveis trabalhem juntas.

## 🎯 Problema

Você precisa usar uma classe existente, mas sua interface não é compatível com o resto do seu código. Não é possível ou desejável modificar a classe original.

### Exemplo Real
Imagine que você tem um sistema que trabalha com dados em formato JSON, mas precisa integrar uma biblioteca legada que só trabalha com XML. O Adapter permite que você faça essa ponte sem modificar nenhum dos dois lados.

## 💡 Solução

Criar uma classe adaptadora que:
1. Implementa a interface esperada pelo cliente
2. Contém uma instância da classe a ser adaptada
3. Traduz as chamadas da interface esperada para a interface da classe adaptada

## 🏗️ Estrutura

```
┌──────────────┐         ┌──────────────┐
│   Cliente    │────────>│   Target     │
└──────────────┘         │ (interface)  │
                         └──────────────┘
                                △
                                │
                         ┌──────────────┐      ┌──────────────┐
                         │   Adapter    │─────>│   Adaptee    │
                         └──────────────┘      └──────────────┘
```

## 📋 Componentes

- **Target**: Interface esperada pelo cliente
- **Adaptee**: Classe existente com interface incompatível
- **Adapter**: Adapta a interface do Adaptee para a interface Target
- **Cliente**: Trabalha com objetos via interface Target

## 📝 Implementações

### [ProcessadorPagamento.java](ProcessadorPagamento.java)
Interface Target que define o contrato esperado pelo sistema.

### [PayPalAPI.java](PayPalAPI.java)
Classe Adaptee - API legada do PayPal com interface diferente.

### [StripeAPI.java](StripeAPI.java)
Classe Adaptee - API do Stripe com interface própria.

### [PayPalAdapter.java](PayPalAdapter.java)
Adapter que adapta a API do PayPal para a interface ProcessadorPagamento.

### [StripeAdapter.java](StripeAdapter.java)
Adapter que adapta a API do Stripe para a interface ProcessadorPagamento.

### [TesteAdapter.java](TesteAdapter.java)
Programa de demonstração do padrão Adapter.

## 🚀 Como Executar

```bash
# Compilar todos os arquivos
javac *.java

# Executar o teste
java TesteAdapter
```

## 📊 Exemplo de Saída Esperada

```
=== Sistema de Pagamento com Adapter ===

--- Processando com PayPal ---
PayPal: Processando pagamento de R$ 150.50
Pagamento processado com sucesso!

--- Processando com Stripe ---
Stripe: Charging R$ 250.75
Pagamento processado com sucesso!

--- Tentando pagamento inválido ---
Erro: Valor deve ser positivo
```

## ✅ Vantagens

1. **Reutilização de Código**
   - Permite usar classes existentes sem modificação
   - Não precisa reescrever funcionalidades já testadas

2. **Single Responsibility Principle**
   - Separa conversão de interface da lógica de negócio
   - Cada classe tem responsabilidade clara

3. **Open/Closed Principle**
   - Pode adicionar novos adapters sem modificar código cliente
   - Sistema aberto para extensão, fechado para modificação

4. **Desacoplamento**
   - Cliente não conhece detalhes das classes adaptadas
   - Facilita troca de implementações

## ⚠️ Desvantagens

1. **Complexidade**
   - Adiciona camada extra de abstração
   - Mais classes para manter

2. **Performance**
   - Chamadas passam por camada adicional
   - Pode ter pequeno overhead em operações críticas

3. **Sobrecarga de Código**
   - Para casos simples, pode ser over-engineering
   - Avalie custo-benefício da abstração

## 🎯 Quando Usar

✅ **Use Adapter quando**:
- Quer usar classe existente com interface incompatível
- Precisa integrar bibliotecas ou APIs de terceiros
- Quer criar classe reutilizável que coopera com classes não relacionadas
- Precisa usar várias subclasses, mas é impraticável adaptar suas interfaces via subclassing
- Não pode ou não deve modificar a classe original

❌ **Evite Adapter quando**:
- Pode modificar a interface original diretamente
- Sistema ainda está em design inicial (projete interfaces compatíveis desde o início)
- Overhead adicional é inaceitável
- Só uma classe precisa ser adaptada e é caso muito simples

## 🔄 Tipos de Adapter

### 1. Object Adapter (Composição)
```java
// Usa composição - mais comum
public class PayPalAdapter implements ProcessadorPagamento {
    private PayPalAPI paypal; // Composição
    
    public void processar(double valor) {
        paypal.fazerPagamento(valor);
    }
}
```

**Vantagens**:
- Mais flexível
- Pode adaptar classe e suas subclasses
- Favorece composição sobre herança

### 2. Class Adapter (Herança)
```java
// Usa herança múltipla (não suportado em Java)
// Em Java, usamos interface + herança
public class PayPalAdapter extends PayPalAPI implements ProcessadorPagamento {
    public void processar(double valor) {
        this.fazerPagamento(valor);
    }
}
```

**Limitações em Java**:
- Java não suporta herança múltipla
- Pode usar interface + extends, mas menos flexível
- Object Adapter é preferido em Java

## 🔗 Padrões Relacionados

### Adapter vs Decorator
- **Adapter**: Muda interface para compatibilidade
- **Decorator**: Mantém interface, adiciona funcionalidade
- **Similaridade**: Ambos envolvem wrapping

### Adapter vs Facade
- **Adapter**: Adapta uma interface específica
- **Facade**: Simplifica múltiplas interfaces
- **Diferença**: Adapter foca em compatibilidade, Facade em simplificação

### Adapter vs Proxy
- **Adapter**: Interface diferente
- **Proxy**: Mesma interface, controla acesso
- **Diferença**: Propósito distinto

### Adapter vs Bridge
- **Adapter**: Usado após design, para compatibilidade
- **Bridge**: Planejado no design, separa abstração de implementação
- **Diferença**: Adapter é retrofitting, Bridge é proativo

## 💼 Casos de Uso Reais

### 1. Java I/O Streams
```java
// InputStreamReader adapta InputStream para Reader
Reader reader = new InputStreamReader(
    new FileInputStream("file.txt")
);
```

### 2. Java Collections
```java
// Arrays.asList adapta array para List
List<String> list = Arrays.asList(stringArray);
```

### 3. JDBC Drivers
```java
// DriverManager adapta diferentes drivers de BD
Connection conn = DriverManager.getConnection(url);
```

## 📝 Exercícios Práticos

### Exercício 1: Adapter de Temperatura
Crie adapters para converter entre Celsius, Fahrenheit e Kelvin.

```java
interface Temperatura {
    double getEmCelsius();
}

class FahrenheitAdapter implements Temperatura {
    private FahrenheitSensor sensor;
    // Implemente conversão
}
```

### Exercício 2: Adapter de Formato de Data
Adapte diferentes formatos de data para um formato padrão.

```java
interface FormatadorData {
    String formatar(Date data);
}

// Adapte SimpleDateFormat, LocalDate, etc.
```

### Exercício 3: Adapter de API REST
Crie adapters para diferentes APIs REST (mock).

```java
interface ServicoUsuario {
    Usuario buscarPorId(int id);
}

// Adapte APIs com diferentes estruturas JSON
```

### Exercício 4: Adapter de Logger
Adapte diferentes frameworks de logging para uma interface comum.

```java
interface Logger {
    void log(String mensagem);
}

// Adapte System.out, java.util.logging, Log4j, etc.
```

## 🎓 Análise Acadêmica

### Princípios de Design Aplicados

1. **Interface Segregation Principle (ISP)**
   - Clientes não dependem de interfaces que não usam
   - Adapter cria interface específica para cada cliente

2. **Dependency Inversion Principle (DIP)**
   - Cliente depende de abstração (Target), não de implementação
   - Adapters são detalhes de implementação

3. **Open/Closed Principle (OCP)**
   - Sistema aberto para novos adapters
   - Fechado para modificação de código existente

### Trade-offs Arquiteturais

| Aspecto | Sem Adapter | Com Adapter |
|---------|-------------|-------------|
| Acoplamento | Alto | Baixo |
| Complexidade | Baixa | Média |
| Flexibilidade | Baixa | Alta |
| Performance | Melhor | Pequeno overhead |
| Manutenibilidade | Difícil | Melhor |

## 🔍 Detecção de Necessidade

**Você precisa de Adapter quando vê estes code smells**:
- Conversões manuais de interface espalhadas pelo código
- If/else ou switch baseado em tipo de classe
- Duplicação de código de integração
- Acoplamento direto com bibliotecas externas
- Dificuldade para trocar implementações

## 📚 Referências

- **Design Patterns: Elements of Reusable Object-Oriented Software** - Gang of Four
- **Head First Design Patterns** - Freeman & Freeman
- **Refactoring: Improving the Design of Existing Code** - Martin Fowler

## 🔗 Navegação

- [Voltar para Padrões Estruturais](../)
- [Próximo: Decorator Pattern](../decorator/)
