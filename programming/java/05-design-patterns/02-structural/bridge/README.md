# Bridge Pattern

O padrão Bridge (Ponte) desacopla uma abstração de sua implementação, permitindo que ambas variem independentemente. Separa a interface (abstração) da implementação, colocando-as em hierarquias de classes distintas.

## 🎯 Problema

Quando você tem uma hierarquia de classes que pode crescer em múltiplas dimensões independentes, usar apenas herança resulta em explosão combinatorial de classes.

### Exemplo Real

Imagine um sistema de controles remotos que precisa controlar diferentes dispositivos (TV, Rádio, Home Theater). Se você também tem diferentes tipos de controles (básico, avançado, universal), usar apenas herança criaria:

```
ControleBasicoTV
ControleBasicoRadio
ControleBasicoHomeTheater
ControleAvancadoTV
ControleAvancadoRadio
ControleAvancadoHomeTheater
ControleUniversalTV
ControleUniversalRadio
ControleUniversalHomeTheater
```

Para 3 tipos de controle × 3 dispositivos = **9 classes**!  
Com 5 controles × 5 dispositivos = **25 classes**!

## 💡 Solução

Separar as duas dimensões (abstrações e implementações) em hierarquias independentes e criar uma "ponte" entre elas através de composição:

1. **Abstração**: Controles remotos (básico, avançado, universal)
2. **Implementação**: Dispositivos (TV, Rádio, Home Theater)
3. **Ponte**: Controle mantém referência para Dispositivo

Agora: 3 controles + 3 dispositivos = **6 classes**!

## 🏗️ Estrutura

```
┌────────────────┐                    ┌────────────────┐
│  Abstraction   │────────────────────>│ Implementor    │
│                │ bridge (composição) │  (interface)   │
└────────────────┘                    └────────────────┘
        △                                      △
        │                                      │
        │                                      │
┌───────────────────┐               ┌─────────┴──────────┐
│RefinedAbstraction │               │                    │
└───────────────────┘         ┌──────────────┐  ┌──────────────┐
                              │ConcreteImplA │  │ConcreteImplB │
                              └──────────────┘  └──────────────┘
```

### Estrutura no Exemplo

```
┌────────────────┐                    ┌────────────────┐
│ ControleRemoto │─────────────────────>│  Dispositivo   │
│                │    dispositivo      │  (interface)   │
└────────────────┘                    └────────────────┘
        △                                      △
        │                                      │
        │                                      │
┌─────────────────────┐             ┌─────────┴──────────┐
│ControleRemoto       │             │                    │
│Avancado             │         ┌───────┐           ┌────────┐
└─────────────────────┘         │  TV   │           │ Radio  │
                                └───────┘           └────────┘
```

## 📋 Componentes

- **Abstraction (ControleRemoto)**: Define interface de alto nível, mantém referência para Implementor
- **RefinedAbstraction (ControleRemotoAvancado)**: Estende Abstraction com funcionalidades extras
- **Implementor (Dispositivo)**: Define interface para implementações concretas
- **ConcreteImplementor (TV, Radio)**: Implementam a interface Implementor

## 📝 Implementações

### [Dispositivo.java](Dispositivo.java)
Interface Implementor - define contratos para dispositivos controláveis.

### [TV.java](TV.java)
ConcreteImplementor - implementação específica para televisão.

### [Radio.java](Radio.java)
ConcreteImplementor - implementação específica para rádio.

### [ControleRemoto.java](ControleRemoto.java)
Abstraction - controle remoto básico que trabalha com qualquer dispositivo.

### [ControleRemotoAvancado.java](ControleRemotoAvancado.java)
RefinedAbstraction - controle com funcionalidades avançadas.

### [TesteBridge.java](TesteBridge.java)
Programa de demonstração do padrão Bridge.

## 🚀 Como Executar

```bash
# Compilar todos os arquivos
javac *.java

# Executar o teste
java TesteBridge
```

## 📊 Exemplo de Saída Esperada

```
=== Teste com Controle Remoto Básico ===

--- Controlando TV com controle básico ---
TV Samsung 55": Ligando...
TV Samsung 55": Volume ajustado para 40%
TV Samsung 55": Volume ajustado para 50%
TV Samsung 55": Canal alterado para 2
TV Samsung 55": Canal alterado para 3

--------------------
Dispositivo: TV Samsung 55"
Status: Ligado
Volume: 50%
Canal: 3
--------------------

--- Controlando Rádio com controle básico ---
Rádio Sony XM-750: Ligando...
Rádio Sony XM-750: Volume ajustado para 30%
Rádio Sony XM-750: Estação alterada para 101 FM

=== Teste com Controle Remoto Avançado ===

--- Controlando TV com controle avançado ---
TV LG OLED 65": Ligando...
Controle Avançado: Ativando volume máximo
TV LG OLED 65": Volume ajustado para 100%
Controle Avançado: Mudando para canal 25

=== Benefícios do Padrão Bridge ===

1. SEPARAÇÃO DE RESPONSABILIDADES
   - Abstrações (ControleRemoto) e Implementações (Dispositivo) separadas
   - Cada hierarquia pode evoluir independentemente
```

## ✅ Vantagens

1. **Desacoplamento**
   - Abstração e implementação são independentes
   - Mudanças em uma não afetam a outra
   - Facilita manutenção e evolução

2. **Extensibilidade**
   - Adicionar novas abstrações sem modificar implementações
   - Adicionar novas implementações sem modificar abstrações
   - Segue Open/Closed Principle

3. **Redução de Classes**
   - N abstrações + M implementações = N+M classes
   - Sem Bridge: N×M classes (explosão combinatorial)
   - Economiza exponencialmente com crescimento

4. **Flexibilidade em Runtime**
   - Pode trocar implementação dinamicamente
   - Configuração flexível de combinações
   - Mesmo objeto de abstração pode usar diferentes implementações

5. **Single Responsibility Principle**
   - Abstração foca em lógica de alto nível
   - Implementação foca em detalhes específicos
   - Responsabilidades bem separadas

6. **Reutilização**
   - Implementações podem ser compartilhadas
   - Abstrações podem trabalhar com múltiplas implementações
   - Código mais modular e reutilizável

## ⚠️ Desvantagens

1. **Complexidade Inicial**
   - Adiciona camadas extras de abstração
   - Pode ser over-engineering para casos simples
   - Requer planejamento arquitetural

2. **Indireção**
   - Chamadas passam por camada adicional
   - Pode dificultar debugging
   - Leve overhead de performance

3. **Curva de Aprendizado**
   - Requer entendimento de composição avançada
   - Não é intuitivo para iniciantes
   - Documentação é crucial

## 🎯 Quando Usar

✅ **Use Bridge quando**:
- Quer evitar vínculo permanente entre abstração e implementação
- Abstrações e implementações devem ser extensíveis por subclasses
- Mudanças na implementação não devem impactar clientes
- Tem explosão de classes devido a múltiplas dimensões
- Quer compartilhar implementações entre múltiplos objetos
- Precisa trocar implementação em runtime

❌ **Evite Bridge quando**:
- Sistema é simples com apenas uma dimensão de variação
- Abstração e implementação não precisam variar independentemente
- Overhead adicional não é justificável
- Equipe não tem familiaridade com padrões avançados

## 🔄 Bridge vs Outros Padrões

### Bridge vs Adapter

| Aspecto | Bridge | Adapter |
|---------|--------|---------|
| **Propósito** | Separar abstração de implementação | Adaptar interface incompatível |
| **Planejamento** | Projetado antecipadamente | Aplicado após o design |
| **Intenção** | Permitir variação independente | Fazer classes incompatíveis trabalharem juntas |
| **Estrutura** | Duas hierarquias separadas | Uma hierarquia adaptando outra |
| **Momento** | Design time | Retrofit/Integration time |

**Exemplo**:
- **Bridge**: Sistema de desenho com formas (círculo, quadrado) e renderizadores (vetorial, raster) - planejado para ambos variarem
- **Adapter**: Integrar biblioteca de pagamento legada no seu sistema - não planejado, necessidade surgiu depois

### Bridge vs Strategy

| Aspecto | Bridge | Strategy |
|---------|--------|----------|
| **Foco** | Estrutura (múltiplas dimensões) | Comportamento (algoritmos) |
| **Variação** | Abstração E implementação | Apenas algoritmo |
| **Hierarquia** | Duas hierarquias completas | Uma interface + implementações |
| **Complexidade** | Maior (duas dimensões) | Menor (uma dimensão) |

**Exemplo**:
- **Bridge**: Controle remoto (abstração) + Dispositivo (implementação) - ambas hierarquias complexas
- **Strategy**: Ordenação com diferentes algoritmos (QuickSort, MergeSort) - foca só no algoritmo

### Bridge vs Abstract Factory

- **Bridge**: Separa abstração de implementação em runtime
- **Abstract Factory**: Cria famílias de objetos relacionados
- **Diferença**: Bridge foca em uso, Factory foca em criação
- **Podem ser combinados**: Factory pode criar implementações para Bridge

## 💼 Casos de Uso Reais

### 1. Java AWT/Swing (GUI)

```java
// Abstração: componentes independentes de plataforma
Component component = new Button("Click me");

// Implementação: peer nativo da plataforma
// WindowsPeer, LinuxPeer, MacOSPeer
// Componente delega para peer específico da plataforma
```

### 2. JDBC (Database Drivers)

```java
// Abstração: API JDBC padronizada
Connection conn = DriverManager.getConnection(url);
Statement stmt = conn.createStatement();

// Implementação: drivers específicos de cada BD
// MySQLDriver, PostgreSQLDriver, OracleDriver
// Mesma abstração, múltiplas implementações
```

### 3. Logging Frameworks

```java
// Abstração: SLF4J (Simple Logging Facade for Java)
Logger logger = LoggerFactory.getLogger(MyClass.class);
logger.info("Message");

// Implementação: Logback, Log4j, java.util.logging
// Troca implementação sem mudar código
```

### 4. Graphics Rendering

```java
// Abstração: Formas geométricas
Shape circle = new Circle(renderer, 10, 20, 5);
Shape square = new Square(renderer, 30, 40, 15);

// Implementação: Diferentes renderizadores
// VectorRenderer, RasterRenderer, OpenGLRenderer
```

### 5. Payment Processing

```java
// Abstração: diferentes tipos de pagamento
Payment payment = new CreditCardPayment(gateway);
Payment subscription = new RecurringPayment(gateway);

// Implementação: diferentes gateways
// PayPalGateway, StripeGateway, MercadoPagoGateway
```

## 📝 Exercícios Práticos

### Exercício 1: Sistema de Mensagens

Crie um sistema Bridge para envio de mensagens.

**Abstrações**: Mensagem simples, Mensagem urgente, Mensagem agendada  
**Implementações**: Email, SMS, WhatsApp, Slack

```java
interface CanalMensagem {
    void enviar(String destinatario, String mensagem);
}

class Email implements CanalMensagem {
    // Implementação específica
}

class Mensagem {
    protected CanalMensagem canal;
    
    public Mensagem(CanalMensagem canal) {
        this.canal = canal;
    }
    
    public abstract void enviarPara(String destinatario, String texto);
}
```

### Exercício 2: Sistema de Desenho

Implemente formas geométricas com diferentes renderizadores.

**Abstrações**: Círculo, Quadrado, Triângulo  
**Implementações**: Renderizador Vetorial, Renderizador Raster, Renderizador 3D

```java
interface Renderizador {
    void renderizarCirculo(double x, double y, double raio);
    void renderizarQuadrado(double x, double y, double lado);
}

abstract class Forma {
    protected Renderizador renderizador;
    
    public Forma(Renderizador renderizador) {
        this.renderizador = renderizador;
    }
    
    public abstract void desenhar();
}
```

### Exercício 3: Sistema de Persistência

Crie Bridge para salvar diferentes tipos de documentos em diferentes formatos.

**Abstrações**: DocumentoTexto, Planilha, Apresentação  
**Implementações**: FormatoJSON, FormatoXML, FormatoBinario

### Exercício 4: Sistema de Relatórios

Implemente geração de relatórios com diferentes formatos de saída.

**Abstrações**: RelatórioSimples, RelatórioDetalhado, RelatórioExecutivo  
**Implementações**: ExportadorPDF, ExportadorExcel, ExportadorHTML

## 🎓 Análise Acadêmica

### Princípios de Design Aplicados

1. **Open/Closed Principle (OCP)**
   - Aberto para extensão: novas abstrações e implementações
   - Fechado para modificação: código existente não muda
   - Extensibilidade em duas dimensões independentes

2. **Single Responsibility Principle (SRP)**
   - Abstração: lógica de alto nível e interface
   - Implementação: detalhes específicos da plataforma
   - Separação clara de responsabilidades

3. **Dependency Inversion Principle (DIP)**
   - Abstração depende de interface, não implementação concreta
   - Implementações são intercambiáveis
   - Inversão de controle através da ponte

4. **Composition over Inheritance**
   - Usa composição (ponte) ao invés de herança
   - Maior flexibilidade que herança múltipla
   - Evita problemas de hierarquias profundas

### Análise de Complexidade

**Sem Bridge (apenas herança)**:
- Número de classes: **N × M**
- N = número de abstrações
- M = número de implementações
- Crescimento: **Exponencial**

**Com Bridge**:
- Número de classes: **N + M**
- Crescimento: **Linear**

**Exemplo Prático**:

| Cenário | Sem Bridge | Com Bridge | Economia |
|---------|------------|------------|----------|
| 2×2 | 4 | 4 | 0% |
| 3×3 | 9 | 6 | 33% |
| 4×4 | 16 | 8 | 50% |
| 5×5 | 25 | 10 | 60% |
| 10×10 | 100 | 20 | 80% |

Quanto mais dimensões crescem, maior a economia!

### Trade-offs Arquiteturais

| Aspecto | Sem Bridge | Com Bridge |
|---------|------------|------------|
| **Número de Classes** | N×M | N+M |
| **Acoplamento** | Alto | Baixo |
| **Flexibilidade** | Rígida | Alta |
| **Complexidade Conceitual** | Baixa | Alta |
| **Manutenibilidade** | Difícil | Fácil |
| **Testabilidade** | Difícil | Fácil |
| **Performance** | Direta | Pequena indireção |

### Teoria dos Grafos

Bridge cria estrutura de **grafo bipartido**:
- Conjunto A: Abstrações
- Conjunto B: Implementações
- Arestas: Qualquer abstração pode conectar com qualquer implementação
- Flexibilidade: N×M combinações possíveis com apenas N+M nós

## 🔍 Identificando Necessidade de Bridge

**Você precisa de Bridge quando vê**:

1. **Explosão de Classes**
   ```java
   WindowsButton, MacButton, LinuxButton
   WindowsCheckbox, MacCheckbox, LinuxCheckbox
   WindowsTextfield, MacTextfield, LinuxTextfield
   // Padrão claro: Componente × Plataforma
   ```

2. **Hierarquia com Múltiplas Dimensões**
   - Variação em abstração (tipos diferentes)
   - Variação em implementação (plataformas diferentes)
   - Cada combinação gera nova classe

3. **Código Duplicado entre Classes Similares**
   ```java
   class WindowsButton {
       // 80% do código é igual ao MacButton
       // Só muda implementação de renderização
   }
   ```

4. **Dificuldade para Adicionar Nova Variação**
   - Adicionar nova plataforma requer N novas classes
   - Adicionar novo componente requer M novas classes

5. **Necessidade de Trocar Implementação em Runtime**
   ```java
   // Quer fazer isso dinamicamente
   shape.setRenderer(new VectorRenderer());
   shape.setRenderer(new RasterRenderer());
   ```

## 🔗 Padrões Relacionados e Complementares

### Padrões que Usam Bridge Internamente

1. **Abstract Factory + Bridge**
   ```java
   // Factory cria implementações adequadas
   GUIFactory factory = getFactory(); // Windows ou Mac
   Button button = factory.createButton();
   
   // Bridge permite trocar implementação
   button.setRenderer(renderer);
   ```

2. **Builder + Bridge**
   ```java
   // Builder constrói abstração com implementação
   Report report = new ReportBuilder()
       .setType(ReportType.DETAILED)
       .setExporter(new PDFExporter()) // Bridge
       .build();
   ```

### Sequência de Evolução

1. **Começa simples**: Classes diretas
2. **Cresce**: Herança para variações
3. **Explode**: N×M classes
4. **Refatora**: Aplica Bridge
5. **Mantém**: Crescimento linear

## 📚 Referências Acadêmicas

### Livros Essenciais

1. **"Design Patterns: Elements of Reusable Object-Oriented Software"**
   - Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides (GoF)
   - Capítulo: Bridge (páginas 151-161)
   - Definição canônica do padrão

2. **"Head First Design Patterns"**
   - Eric Freeman, Elisabeth Robson
   - Explicação visual e didática
   - Ótimo para iniciantes

3. **"Patterns of Enterprise Application Architecture"**
   - Martin Fowler
   - Uso de Bridge em sistemas corporativos

### Artigos Científicos

- **"Improving Design Patterns with Aspect-Oriented Programming"**
  - Discute evolução do Bridge
  
- **"Pattern-Oriented Software Architecture"**
  - Frank Buschmann et al.
  - Bridge em arquitetura de software

## 🛠️ Implementação em Outras Linguagens

### C++
```cpp
// Suporta herança múltipla, mas Bridge ainda é útil
class Abstraction {
protected:
    Implementor* implementor;
public:
    Abstraction(Implementor* impl) : implementor(impl) {}
    virtual void operation() = 0;
};
```

### Python
```python
# Duck typing facilita, mas estrutura ajuda organização
class Abstraction:
    def __init__(self, implementor):
        self.implementor = implementor
    
    def operation(self):
        return self.implementor.operation_impl()
```

### C#
```csharp
// Similar a Java, sem herança múltipla
public abstract class Abstraction {
    protected IImplementor implementor;
    
    public Abstraction(IImplementor implementor) {
        this.implementor = implementor;
    }
}
```

## 💡 Dicas de Implementação

### 1. Nomeação Clara
```java
// BOM: deixa claro que é Bridge
class ControleRemoto {
    protected Dispositivo dispositivo; // Nome da ponte
}

// RUIM: nomes genéricos
class ClassA {
    protected InterfaceB b; // Que é isso?
}
```

### 2. Imutabilidade da Ponte (quando possível)
```java
public class ControleRemoto {
    private final Dispositivo dispositivo; // final
    
    public ControleRemoto(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }
    // Não fornece setter
}
```

### 3. Factory para Criação
```java
public class ControleFactory {
    public static ControleRemoto criar(
        TipoControle tipo, 
        Dispositivo dispositivo
    ) {
        switch(tipo) {
            case BASICO: return new ControleRemoto(dispositivo);
            case AVANCADO: return new ControleRemotoAvancado(dispositivo);
            default: throw new IllegalArgumentException();
        }
    }
}
```

### 4. Validação na Construção
```java
public ControleRemoto(Dispositivo dispositivo) {
    if (dispositivo == null) {
        throw new IllegalArgumentException(
            "Dispositivo não pode ser null"
        );
    }
    this.dispositivo = dispositivo;
}
```

## 🎯 Checklist de Implementação

Ao implementar Bridge, certifique-se de:

- [ ] Identificou duas dimensões de variação independentes
- [ ] Criou interface/abstração para implementação
- [ ] Criou hierarquia de abstrações
- [ ] Implementou ponte via composição (não herança)
- [ ] Abstrações delegam para implementações
- [ ] Possível adicionar novas abstrações sem modificar implementações
- [ ] Possível adicionar novas implementações sem modificar abstrações
- [ ] Testou múltiplas combinações de abstração-implementação
- [ ] Documentou intenção do design
- [ ] Validou que benefícios superam complexidade adicional

## 🎮 Exemplo Interativo

Para entender melhor, tente este exercício mental:

**Sem Bridge**: Imagine criar uma classe para cada combinação
```
ControleBasicoParaTV
ControleBasicoParaRadio
ControleAvancadoParaTV
ControleAvancadoParaRadio
... 
```

**Com Bridge**: Uma ponte conecta abstrações e implementações
```java
ControleRemoto controle = new ControleBasico(new TV());
controle = new ControleAvancado(new Radio());
// Qualquer controle + qualquer dispositivo!
```

**Pergunta**: E se adicionar SmartPhone como novo dispositivo?
- **Sem Bridge**: +N novas classes (uma por tipo de controle)
- **Com Bridge**: +1 nova classe (SmartPhone)

## 🔗 Navegação

- [Voltar para Padrões Estruturais](../)
- [Anterior: Flyweight Pattern](../flyweight/)
- [Próximo: Adapter Pattern](../adapter/)

---

## 📖 Resumo Executivo

**Bridge Pattern** é essencial quando:
- ✅ Tem múltiplas dimensões de variação
- ✅ Quer evitar explosão de classes
- ✅ Precisa de extensibilidade em duas direções
- ✅ Deseja desacoplamento entre abstração e implementação

**Resultado**: Sistema mais flexível, manutenível e escalável, com crescimento linear ao invés de exponencial.

**Lembre-se**: "Bridge today, save classes tomorrow!"
