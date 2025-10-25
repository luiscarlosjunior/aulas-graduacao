# Builder Pattern

O padrão Builder separa a construção de um objeto complexo de sua representação, permitindo que o mesmo processo de construção crie diferentes representações.

## 🎯 Problema

Como criar objetos complexos que têm muitos parâmetros opcionais sem usar:
- Construtores telescópicos (vários construtores com diferentes combinações de parâmetros)
- Muitos setters que deixam o objeto em estado intermediário inválido
- Construtores com muitos parâmetros confusos

## 💡 Solução

- Separar a lógica de construção em uma classe Builder dedicada
- Permitir construção passo a passo
- Tornar o objeto final imutável
- Validar o objeto antes de construí-lo

## 🏗️ Estrutura

```
┌─────────────────┐
│    Produto      │  ← Objeto complexo
├─────────────────┤
│ - atributo1     │
│ - atributo2     │
│ - atributo3...  │
└─────────────────┘
        ↑
        │ cria
        │
┌─────────────────┐
│     Builder     │  ← Constrói o produto
├─────────────────┤
│ + setAtributo1()│ → retorna this
│ + setAtributo2()│ → retorna this
│ + build()       │ → retorna Produto
└─────────────────┘
```

## 📋 Implementação

### [Computador.java](Computador.java)
Classe produto que representa um computador com várias configurações opcionais.
- Construtor privado (apenas Builder pode criar)
- Atributos finais (imutabilidade)
- Builder interno como classe estática

### [TesteBuilder.java](TesteBuilder.java)
Programa de teste demonstrando diferentes configurações de computadores.

## 🚀 Como Executar

```bash
javac *.java
java TesteBuilder
```

## ✅ Vantagens

- **Legibilidade**: Código mais claro e fácil de entender
  ```java
  // Ao invés de:
  new Computador("i9", 32, "RTX4090", 2000, "SSD", true, true, "Windows", "NZXT")
  
  // Você tem:
  new Computador.Builder("i9", 32)
      .comPlacaVideo("RTX4090")
      .comArmazenamento(2000, "SSD")
      .comWifi()
      .build();
  ```

- **Flexibilidade**: Fácil adicionar novos parâmetros opcionais
- **Imutabilidade**: Objeto final não pode ser modificado após construção
- **Validação**: Validar o objeto antes de construí-lo
- **Fluência**: Interface fluente (method chaining) torna o código mais natural

## ⚠️ Desvantagens

- **Verbosidade**: Mais código para escrever
- **Complexidade**: Adiciona uma camada extra de abstração
- **Overhead**: Criação de um objeto adicional (o Builder)
- **Manutenção**: Mudanças no produto requerem mudanças no Builder

## 🎯 Quando Usar

✅ **Use quando**:
- Objeto tem muitos parâmetros (especialmente opcionais)
- Objeto requer inicialização complexa
- Quer criar diferentes representações do mesmo objeto
- Precisa de imutabilidade no objeto final
- Quer validar o objeto antes de construí-lo

❌ **Evite quando**:
- Objeto é simples com poucos parâmetros
- Não há parâmetros opcionais
- Construtor simples é suficiente
- Não precisa de diferentes representações

## 📝 Exemplo Prático: Pizza Builder

```java
public class Pizza {
    private String massa;        // obrigatório
    private String tamanho;      // obrigatório
    private String queijo;       // opcional
    private boolean tomate;      // opcional
    private boolean calabresa;   // opcional
    private boolean azeitona;    // opcional
    
    private Pizza(Builder builder) {
        this.massa = builder.massa;
        this.tamanho = builder.tamanho;
        this.queijo = builder.queijo;
        this.tomate = builder.tomate;
        this.calabresa = builder.calabresa;
        this.azeitona = builder.azeitona;
    }
    
    public static class Builder {
        // Obrigatórios
        private String massa;
        private String tamanho;
        
        // Opcionais com valores padrão
        private String queijo = "Mussarela";
        private boolean tomate = false;
        private boolean calabresa = false;
        private boolean azeitona = false;
        
        public Builder(String massa, String tamanho) {
            this.massa = massa;
            this.tamanho = tamanho;
        }
        
        public Builder comQueijo(String queijo) {
            this.queijo = queijo;
            return this;
        }
        
        public Builder comTomate() {
            this.tomate = true;
            return this;
        }
        
        public Builder comCalabresa() {
            this.calabresa = true;
            return this;
        }
        
        public Builder comAzeitona() {
            this.azeitona = true;
            return this;
        }
        
        public Pizza build() {
            return new Pizza(this);
        }
    }
}

// Uso:
Pizza margherita = new Pizza.Builder("Fina", "Grande")
    .comTomate()
    .build();

Pizza calabresaEspecial = new Pizza.Builder("Grossa", "Média")
    .comQueijo("Parmesão")
    .comCalabresa()
    .comAzeitona()
    .build();
```

## 🔄 Variações do Padrão

### 1. Builder Telescópico
Builder com métodos que aceitam múltiplos valores relacionados:
```java
builder.comArmazenamento(1000, "SSD")
```

### 2. Builder com Diretor
Classe adicional que encapsula sequências comuns de construção:
```java
public class ComputadorDirector {
    public Computador criarGamer() {
        return new Computador.Builder("i9", 32)
            .comPlacaVideo("RTX4090")
            .comArmazenamento(2000, "SSD")
            .build();
    }
}
```

### 3. Builder com Validação
Validar o estado antes do build():
```java
public Computador build() {
    if (memoriaRAM < 4) {
        throw new IllegalStateException("RAM mínima: 4GB");
    }
    return new Computador(this);
}
```

## 🆚 Builder vs Factory

| Aspecto | Builder | Factory |
|---------|---------|---------|
| **Propósito** | Construir objeto complexo passo a passo | Criar objeto em uma etapa |
| **Complexidade** | Objetos complexos com muitos parâmetros | Objetos simples ou médios |
| **Configuração** | Permite customização fina | Configuração pré-definida |
| **Ênfase** | Como construir | O que construir |

## 💡 Dicas de Implementação

1. **Builder como classe interna estática**: Acesso fácil ao construtor privado
2. **Retorne `this`**: Permite method chaining fluente
3. **Construtores obrigatórios**: Parâmetros obrigatórios no construtor do Builder
4. **Final no produto**: Torne o produto imutável com atributos final
5. **Validação no build()**: Valide o estado antes de criar o objeto
6. **Nomes claros**: Use nomes descritivos como `comWifi()`, `semBluetooth()`

## 🔗 Padrões Relacionados

- **Abstract Factory**: Pode usar Builder para construir produtos
- **Composite**: Builder pode construir árvores complexas
- **Prototype**: Builder pode inicializar clones
- **Singleton**: Director pode ser Singleton

## 📚 Exercícios

1. Implemente um Builder para `Email` (destinatários, assunto, corpo, anexos)
2. Crie um Builder para `RelatorioFinanceiro` com diferentes seções opcionais
3. Desenvolva um Builder para `Hamburguer` com ingredientes customizáveis
4. Implemente um Director para criar configurações pré-definidas de computadores

## 🎓 Conceitos Acadêmicos

### Imutabilidade
O Builder promove objetos imutáveis, que são mais seguros em ambientes multi-thread e mais fáceis de raciocinar sobre seu comportamento.

### Fluent Interface
O padrão Builder é um exemplo clássico de interface fluente, onde métodos retornam o próprio objeto para encadear chamadas.

### Separação de Responsabilidades
O Builder separa a lógica de construção da representação, seguindo o princípio da responsabilidade única (SRP).
