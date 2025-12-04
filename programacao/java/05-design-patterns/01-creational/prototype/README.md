# Prototype Pattern

O padrão Prototype permite criar novos objetos copiando instâncias existentes (protótipos) ao invés de criar do zero, especialmente útil quando a criação é custosa.

## 🎯 Problema

Como criar novos objetos quando:
- A criação de um objeto é complexa ou custosa
- Você quer evitar criar muitas subclasses apenas para diferentes configurações
- Objetos têm muitos estados possíveis mas poucos são realmente usados
- Quer criar objetos dinamicamente sem conhecer suas classes exatas

**Exemplo prático**: Um editor gráfico onde usuários copiam formas (Ctrl+C, Ctrl+V). É mais eficiente clonar uma forma existente do que criar uma nova do zero.

## 💡 Solução

- Criar uma interface Prototype com método `clonar()`
- Objetos implementam a interface e fornecem sua própria lógica de clonagem
- Cliente clona objetos ao invés de instanciar novas classes
- Opcionalmente, usar um Registro de Protótipos para gerenciar protótipos comuns

## 🏗️ Estrutura

```
    <<interface>>
     Prototype
    ├── clonar()
         ↑
    ┌────┴────┐
    │         │
Concreto1  Concreto2
    │         │
clonar() { clonar() {
  return    return
  copy(this) copy(this)
}         }
```

## 📋 Implementação

### Interface e Classes Base
- **[Prototipo.java](Prototipo.java)** - Interface para objetos clonáveis
- **[Forma.java](Forma.java)** - Classe abstrata para formas geométricas

### Protótipos Concretos
- **[Circulo.java](Circulo.java)** - Círculo clonável
- **[Retangulo.java](Retangulo.java)** - Retângulo clonável
- **[Documento.java](Documento.java)** - Documento com clonagem profunda

### Gerenciamento
- **[RegistroPrototipos.java](RegistroPrototipos.java)** - Cache de protótipos
- **[TestePrototype.java](TestePrototype.java)** - Exemplos e testes

## 🚀 Como Executar

```bash
javac *.java
java TestePrototype
```

## ✅ Vantagens

- **Performance**: Clonagem é mais rápida que criação para objetos complexos
- **Reduz subclasses**: Não precisa de factory para cada variação
- **Dinâmico**: Adiciona/remove protótipos em tempo de execução
- **Flexibilidade**: Cria novos objetos variando valores
- **Desacoplamento**: Cliente não depende de classes concretas

## ⚠️ Desvantagens

- **Clonagem complexa**: Objetos com referências circulares são difíceis de clonar
- **Deep vs Shallow**: Precisa decidir tipo de cópia (profunda/rasa)
- **Método clone()**: Em Java, requer cuidado com Cloneable e exceptions
- **Manutenção**: Toda classe precisa implementar clonagem

## 🎯 Quando Usar

✅ **Use quando**:
- Criação de objetos é custosa (consulta BD, cálculos complexos)
- Sistema deve ser independente de como produtos são criados
- Classes a instanciar são especificadas em tempo de execução
- Quer evitar hierarquia de factories paralela
- Objetos têm poucos estados diferentes

❌ **Evite quando**:
- Objetos são simples e baratos de criar
- Clonagem seria mais complexa que criação normal
- Não há necessidade de criar cópias independentes
- Objetos têm muitas dependências externas

## 📝 Tipos de Clonagem

### 1. Shallow Copy (Cópia Rasa)
Copia apenas atributos primitivos. Referências apontam para os mesmos objetos.

```java
public class Circulo implements Prototipo {
    private int raio;
    private String cor;  // String é imutável, não há problema
    
    public Prototipo clonar() {
        try {
            return (Circulo) super.clone();  // Shallow copy
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
```

### 2. Deep Copy (Cópia Profunda)
Copia objetos internos também, criando cópias completamente independentes.

```java
public class Documento implements Prototipo {
    private String titulo;
    private List<String> tags;
    private Configuracao config;
    
    private Documento(Documento original) {
        this.titulo = original.titulo;
        
        // Deep copy da lista
        this.tags = new ArrayList<>(original.tags);
        
        // Deep copy do objeto interno
        this.config = original.config.clonar();
    }
    
    public Prototipo clonar() {
        return new Documento(this);
    }
}
```

## 🔧 Registro de Protótipos

Um registro central que mantém protótipos pré-configurados:

```java
RegistroPrototipos registro = new RegistroPrototipos();

// Registrar protótipos
Circulo circuloPadrao = new Circulo(10);
circuloPadrao.setCor("Azul");
registro.registrar("circulo-azul", circuloPadrao);

// Clonar quando necessário
Circulo novoCirculo = (Circulo) registro.clonar("circulo-azul");
```

**Vantagens do Registro**:
- Cache de protótipos comuns
- Acesso centralizado
- Fácil adicionar/remover protótipos
- Clientes não precisam conhecer classes concretas

## 📊 Performance: Criação vs Clonagem

```
Teste com 1000 objetos complexos:
┌─────────────────┬──────────────┐
│ Método          │ Tempo (ms)   │
├─────────────────┼──────────────┤
│ Criação normal  │ 45 ms        │
│ Clonagem        │ 12 ms        │
└─────────────────┴──────────────┘
Ganho: ~73% mais rápido
```

A clonagem é mais eficiente quando a criação envolve:
- Consultas a banco de dados
- Leitura de arquivos
- Cálculos complexos
- Inicialização de muitos atributos

## 🆚 Prototype vs Factory Method

| Aspecto | Prototype | Factory Method |
|---------|-----------|----------------|
| **Criação** | Por clonagem | Por instanciação |
| **Performance** | Mais rápido para objetos complexos | Criação normal |
| **Configuração** | Copia estado existente | Cria novo estado |
| **Hierarquia** | Não requer subclasses | Requer subclasses factory |
| **Uso** | Muitas variações do mesmo tipo | Diferentes tipos de objetos |

## 💡 Dicas de Implementação

1. **Escolha o tipo de cópia**: Avalie se shallow ou deep copy é apropriado
2. **Construtor de cópia**: Prefira construtor privado de cópia ao invés de clone()
3. **Imutabilidade**: Strings e tipos primitivos não precisam deep copy
4. **Teste independência**: Verifique que clones são realmente independentes
5. **Considere Serialização**: Para deep copy complexo, serialização pode ajudar
6. **Valide estado**: Garanta que clones estejam em estado válido

## 🔗 Padrões Relacionados

- **Abstract Factory**: Pode usar Prototype ao invés de Factory Method
- **Composite**: Prototypes são úteis para clonar estruturas composite
- **Decorator**: Decorators podem ser clonados como prototypes
- **Command**: Commands podem ser prototypes para facilitar undo/redo
- **Memento**: Prototype pode implementar memento para salvar estados

## 📚 Exercícios

1. Implemente clonagem para uma classe `Pessoa` com endereço (deep copy)
2. Crie um registro de protótipos para diferentes tipos de relatórios
3. Desenvolva um sistema de templates de e-mail usando Prototype
4. Compare performance de criação vs clonagem com objetos complexos
5. Implemente clonagem para uma árvore (estrutura recursiva)

## 🎓 Conceitos Acadêmicos

### Clone() vs Copy Constructor
Java fornece método `clone()` via `Cloneable`, mas tem problemas:
- Pode lançar `CloneNotSupportedException`
- Shallow copy por padrão
- Problemas com herança
- Considerado problemático por muitos desenvolvedores

**Solução recomendada**: Usar construtor de cópia privado:
```java
private Circulo(Circulo original) {
    this.raio = original.raio;
    this.cor = original.cor;
}

public Prototipo clonar() {
    return new Circulo(this);
}
```

### Problema de Referências Circulares
Clonagem profunda pode falhar com referências circulares:
```java
class Pessoa {
    String nome;
    Pessoa melhorAmigo;  // Pode apontar de volta!
}
```

**Soluções**:
- Manter mapa de objetos já clonados
- Usar serialização com cuidado
- Evitar referências circulares no design

### Serialização para Deep Copy
```java
public Object deepClone() {
    try {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);
        
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        return in.readObject();
    } catch (Exception e) {
        return null;
    }
}
```

**Nota**: Classe deve ser `Serializable` e é mais lento que cópia manual.
