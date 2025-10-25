# Design Patterns em Java

Esta seção apresenta os principais padrões de design (design patterns) utilizados no desenvolvimento de software orientado a objetos, com implementações práticas em Java.

## 📖 Contexto Acadêmico e Histórico

Design Patterns (Padrões de Projeto) são soluções típicas para problemas recorrentes no desenvolvimento de software orientado a objetos. O conceito foi popularizado pelo livro "Design Patterns: Elements of Reusable Object-Oriented Software" (1994), escrito por Erich Gamma, Richard Helm, Ralph Johnson e John Vlissides, conhecidos como "Gang of Four" (GoF).

### Fundamentos Teóricos

Do ponto de vista acadêmico, os design patterns representam:

1. **Abstração de Soluções**: Capturam a essência de soluções bem-sucedidas em um formato reutilizável, abstraindo detalhes de implementação específicos.

2. **Vocabulário Comum**: Estabelecem uma linguagem compartilhada entre desenvolvedores, facilitando a comunicação técnica e a documentação arquitetural.

3. **Conhecimento Consolidado**: Documentam décadas de experiência em engenharia de software, transformando conhecimento tácito em conhecimento explícito.

4. **Princípios de Design**: Incorporam princípios fundamentais como baixo acoplamento, alta coesão, encapsulamento e separação de responsabilidades.

### Relação com Princípios SOLID

Os design patterns são intimamente relacionados aos princípios SOLID:

- **S**ingle Responsibility Principle (Princípio da Responsabilidade Única)
- **O**pen/Closed Principle (Princípio Aberto/Fechado)
- **L**iskov Substitution Principle (Princípio da Substituição de Liskov)
- **I**nterface Segregation Principle (Princípio da Segregação de Interface)
- **D**ependency Inversion Principle (Princípio da Inversão de Dependência)

Muitos padrões exemplificam a aplicação prática desses princípios, oferecendo estruturas concretas que promovem código mais manutenível e extensível.

## 🎯 Objetivos de Aprendizagem

- Compreender a fundamentação teórica dos padrões de design
- Identificar problemas recorrentes que justificam o uso de padrões
- Aprender quando e como aplicar cada padrão adequadamente
- Implementar soluções reutilizáveis e bem estruturadas
- Desenvolver código mais flexível, testável e manutenível
- Avaliar trade-offs entre diferentes abordagens de design

## 📋 Categorias de Padrões

Os 23 padrões do GoF são organizados em três categorias principais, baseadas em seu propósito:

### [01 - Padrões Criacionais (Creational Patterns)](01-creational/)

**Propósito Acadêmico**: Abstrair o processo de instanciação de objetos, tornando o sistema independente de como os objetos são criados, compostos e representados. Promovem o encapsulamento do conhecimento sobre quais classes concretas o sistema utiliza.

**Fundamentação**: Estes padrões aplicam o princípio da inversão de dependência (DIP), fazendo o código depender de abstrações ao invés de implementações concretas.

Padrões implementados:

- **Singleton**: Garante uma única instância de uma classe e fornece ponto de acesso global
  - *Cenário*: Conexão com banco de dados, logger de sistema, gerenciador de configurações
  - *Princípio*: Controle de instanciação e acesso global controlado
  
- **Factory Method**: Define interface para criação, delegando a escolha da classe concreta para subclasses
  - *Cenário*: Sistema de notificações (email, SMS, push), criação de documentos (PDF, Word, Excel)
  - *Princípio*: Open/Closed Principle - aberto para extensão, fechado para modificação
  
- **Abstract Factory**: Cria famílias de objetos relacionados sem especificar classes concretas
  - *Cenário*: Interface gráfica multiplataforma (Windows, Linux, Mac), temas de aplicação
  - *Princípio*: Consistência entre objetos relacionados
  
- **Builder**: Constrói objetos complexos passo a passo, separando construção da representação
  - *Cenário*: Construção de queries SQL, montagem de relatórios, builders de objetos com muitos parâmetros
  - *Princípio*: Single Responsibility - separa lógica de construção
  
- **Prototype**: Cria novos objetos clonando instâncias existentes
  - *Cenário*: Cópia de objetos complexos, cache de objetos pré-configurados
  - *Princípio*: Evita acoplamento com classes concretas no processo de criação

### [02 - Padrões Estruturais (Structural Patterns)](02-structural/)

**Propósito Acadêmico**: Lidam com a composição de classes e objetos, formando estruturas maiores mantendo flexibilidade e eficiência. Usam herança e composição para criar novas funcionalidades.

**Fundamentação**: Aplicam princípios de composição sobre herança, favorecendo estruturas flexíveis que podem ser modificadas dinamicamente.

Padrões principais:

- **Adapter**: Converte interface de uma classe em outra esperada pelos clientes
  - *Cenário*: Integração com APIs legadas, adaptação de bibliotecas de terceiros
  - *Princípio*: Interface Segregation - clientes não dependem de interfaces que não usam
  
- **Decorator**: Adiciona responsabilidades a objetos dinamicamente, alternativa flexível à herança
  - *Cenário*: Streams de I/O em Java, decoração de componentes UI, filtros de requisições
  - *Princípio*: Open/Closed - extensão sem modificação da classe base
  
- **Facade**: Fornece interface unificada para um conjunto de interfaces de um subsistema
  - *Cenário*: Simplificação de bibliotecas complexas, camadas de serviço
  - *Princípio*: Redução de acoplamento entre subsistemas
  
- **Composite**: Compõe objetos em estruturas de árvore representando hierarquias parte-todo
  - *Cenário*: Estruturas de arquivos e pastas, componentes gráficos hierárquicos
  - *Princípio*: Uniformidade no tratamento de objetos individuais e composições
  
- **Proxy**: Fornece substituto ou placeholder para controlar acesso a um objeto
  - *Cenário*: Lazy loading, controle de acesso, logging de operações, cache
  - *Princípio*: Separação de responsabilidades de controle de acesso

### [03 - Padrões Comportamentais (Behavioral Patterns)](03-behavioral/)

**Propósito Acadêmico**: Caracterizam algoritmos e atribuição de responsabilidades entre objetos, descrevendo padrões de comunicação entre objetos. Focam em como objetos colaboram e distribuem responsabilidades.

**Fundamentação**: Implementam baixo acoplamento através de comunicação flexível, permitindo que objetos interajam sem conhecer detalhes de implementação uns dos outros.

Padrões principais:

- **Observer**: Define dependência um-para-muitos, notificando dependentes sobre mudanças de estado
  - *Cenário*: Event listeners, sistemas de notificação, Model-View em MVC
  - *Princípio*: Baixo acoplamento entre subject e observers
  
- **Strategy**: Define família de algoritmos encapsulados e intercambiáveis
  - *Cenário*: Algoritmos de ordenação, validações diferentes, métodos de pagamento
  - *Princípio*: Dependency Inversion - depender de abstrações de algoritmos
  
- **Command**: Encapsula requisições como objetos, permitindo parametrização e queueing
  - *Cenário*: Undo/Redo, macro commands, agendamento de tarefas
  - *Princípio*: Single Responsibility - separa invocação de execução
  
- **State**: Permite objeto alterar comportamento quando seu estado interno muda
  - *Cenário*: Máquinas de estado, fluxos de trabalho, conexões de rede
  - *Princípio*: Open/Closed - adicionar estados sem modificar contexto
  
- **Template Method**: Define esqueleto de algoritmo, delegando passos para subclasses
  - *Cenário*: Frameworks com pontos de extensão, processos com variações
  - *Princípio*: Hollywood Principle - "Don't call us, we'll call you"

## 💻 Exemplos Práticos Simplificados

Para facilitar o entendimento inicial, aqui estão exemplos básicos de alguns padrões:

### Exemplo 1: Singleton (Padrão Criacional)

```java
/**
 * Exemplo simples de Singleton
 * Garante apenas uma instância da classe
 * 
 * NOTA EDUCACIONAL: Esta é uma versão simplificada para demonstração.
 * Para ambientes multi-thread, use synchronized, double-checked locking,
 * ou implementação com enum (veja exemplos completos nos subdiretórios).
 */
public class ConfiguracaoApp {
    // Única instância (estática)
    private static ConfiguracaoApp instancia;
    
    // Dados de configuração
    private String idioma;
    private String tema;
    
    // Construtor privado (não pode ser instanciado de fora)
    private ConfiguracaoApp() {
        this.idioma = "PT-BR";
        this.tema = "claro";
    }
    
    // Método público para obter a instância única
    public static ConfiguracaoApp getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracaoApp();
        }
        return instancia;
    }
    
    // Métodos de negócio
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
    public String getIdioma() {
        return idioma;
    }
}

// Uso:
ConfiguracaoApp config1 = ConfiguracaoApp.getInstancia();
config1.setIdioma("EN-US");

ConfiguracaoApp config2 = ConfiguracaoApp.getInstancia();
System.out.println(config2.getIdioma()); // "EN-US" - mesma instância!
```

### Exemplo 2: Factory Method (Padrão Criacional)

```java
/**
 * Exemplo simples de Factory Method
 * Cria diferentes tipos de transporte sem especificar classe exata
 */

// Interface comum
interface Transporte {
    void entregar();
}

// Implementações concretas
class Caminhao implements Transporte {
    public void entregar() {
        System.out.println("Entrega por terra usando caminhão");
    }
}

class Navio implements Transporte {
    public void entregar() {
        System.out.println("Entrega por mar usando navio");
    }
}

// Factory abstrata
abstract class LogisticaFactory {
    // Factory Method - subclasses decidem qual objeto criar
    abstract Transporte criarTransporte();
    
    // Método que usa o factory method
    public void planejarEntrega() {
        Transporte transporte = criarTransporte();
        transporte.entregar();
    }
}

// Factories concretas
class LogisticaTerrestre extends LogisticaFactory {
    Transporte criarTransporte() {
        return new Caminhao();
    }
}

class LogisticaMaritima extends LogisticaFactory {
    Transporte criarTransporte() {
        return new Navio();
    }
}

// Uso:
LogisticaFactory logistica = new LogisticaTerrestre();
logistica.planejarEntrega(); // "Entrega por terra usando caminhão"
```

### Exemplo 3: Observer (Padrão Comportamental)

```java
/**
 * Exemplo simples de Observer
 * Notifica múltiplos objetos sobre mudanças de estado
 * 
 * NOTA: Em código real, imports ficam no topo do arquivo
 */
import java.util.ArrayList;
import java.util.List;

// Interface Observer
interface Observer {
    void atualizar(String mensagem);
}

// Classe Observable (Subject)
class CanalNoticias {
    private List<Observer> inscritos = new ArrayList<>();
    private String ultimaNoticia;
    
    // Adiciona observer
    public void inscrever(Observer observer) {
        inscritos.add(observer);
    }
    
    // Remove observer
    public void desinscrever(Observer observer) {
        inscritos.remove(observer);
    }
    
    // Notifica todos os observers
    public void publicarNoticia(String noticia) {
        this.ultimaNoticia = noticia;
        notificarInscritos();
    }
    
    private void notificarInscritos() {
        for (Observer observer : inscritos) {
            observer.atualizar(ultimaNoticia);
        }
    }
}

// Observers concretos
class UsuarioApp implements Observer {
    private String nome;
    
    public UsuarioApp(String nome) {
        this.nome = nome;
    }
    
    public void atualizar(String mensagem) {
        System.out.println(nome + " recebeu notificação: " + mensagem);
    }
}

// Uso:
CanalNoticias canal = new CanalNoticias();

UsuarioApp user1 = new UsuarioApp("João");
UsuarioApp user2 = new UsuarioApp("Maria");

canal.inscrever(user1);
canal.inscrever(user2);

canal.publicarNoticia("Nova versão disponível!");
// Output:
// João recebeu notificação: Nova versão disponível!
// Maria recebeu notificação: Nova versão disponível!
```

### Exemplo 4: Strategy (Padrão Comportamental)

```java
/**
 * Exemplo simples de Strategy
 * Permite trocar algoritmos de validação dinamicamente
 * 
 * NOTA EDUCACIONAL: As validações são simplificadas para demonstração.
 * Em produção, use bibliotecas especializadas para validação.
 */

// Interface Strategy
interface EstrategiaValidacao {
    boolean validar(String texto);
}

// Estratégias concretas
class ValidacaoEmail implements EstrategiaValidacao {
    public boolean validar(String texto) {
        // Validação simplificada: contém @ e .
        // Em produção, use regex completo ou biblioteca
        return texto.contains("@") && texto.contains(".");
    }
}

class ValidacaoTelefone implements EstrategiaValidacao {
    public boolean validar(String texto) {
        // Valida telefone brasileiro: 10 ou 11 dígitos
        return texto.matches("\\d{10,11}");
    }
}

class ValidacaoSenhaForte implements EstrategiaValidacao {
    public boolean validar(String texto) {
        return texto.length() >= 8 && 
               texto.matches(".*[A-Z].*") &&  // Tem maiúscula
               texto.matches(".*[0-9].*");    // Tem número
    }
}

// Contexto que usa a estratégia
class ValidadorCampo {
    private EstrategiaValidacao estrategia;
    
    public void setEstrategia(EstrategiaValidacao estrategia) {
        this.estrategia = estrategia;
    }
    
    public boolean validar(String texto) {
        if (estrategia == null) {
            throw new IllegalStateException("Estratégia não definida");
        }
        return estrategia.validar(texto);
    }
}

// Uso:
ValidadorCampo validador = new ValidadorCampo();

// Valida email
validador.setEstrategia(new ValidacaoEmail());
System.out.println(validador.validar("user@example.com")); // true

// Valida telefone
validador.setEstrategia(new ValidacaoTelefone());
System.out.println(validador.validar("11987654321")); // true

// Valida senha
validador.setEstrategia(new ValidacaoSenhaForte());
System.out.println(validador.validar("Senha123")); // true
```

### Exemplo 5: Decorator (Padrão Estrutural)

```java
/**
 * Exemplo simples de Decorator
 * Adiciona funcionalidades a objetos dinamicamente
 */

// Interface comum
interface Cafe {
    String getDescricao();
    double getCusto();
}

// Componente base
class CafeSimples implements Cafe {
    public String getDescricao() {
        return "Café";
    }
    
    public double getCusto() {
        return 3.00;
    }
}

// Decorator abstrato
abstract class CafeDecorator implements Cafe {
    protected Cafe cafe;
    
    public CafeDecorator(Cafe cafe) {
        this.cafe = cafe;
    }
}

// Decorators concretos
class ComLeite extends CafeDecorator {
    public ComLeite(Cafe cafe) {
        super(cafe);
    }
    
    public String getDescricao() {
        return cafe.getDescricao() + ", Leite";
    }
    
    public double getCusto() {
        return cafe.getCusto() + 1.50;
    }
}

class ComChocolate extends CafeDecorator {
    public ComChocolate(Cafe cafe) {
        super(cafe);
    }
    
    public String getDescricao() {
        return cafe.getDescricao() + ", Chocolate";
    }
    
    public double getCusto() {
        return cafe.getCusto() + 2.00;
    }
}

// Uso:
Cafe cafe = new CafeSimples();
System.out.println(cafe.getDescricao() + " = R$ " + cafe.getCusto());
// "Café = R$ 3.0"

cafe = new ComLeite(cafe);
System.out.println(cafe.getDescricao() + " = R$ " + cafe.getCusto());
// "Café, Leite = R$ 4.5"

cafe = new ComChocolate(cafe);
System.out.println(cafe.getDescricao() + " = R$ " + cafe.getCusto());
// "Café, Leite, Chocolate = R$ 6.5"
```

## 🎓 Análise Crítica: Quando Usar Design Patterns

### Perspectiva Acadêmica sobre Aplicabilidade

Do ponto de vista da engenharia de software, design patterns não são "balas de prata". Sua aplicação requer análise criteriosa do contexto, requisitos e trade-offs envolvidos.

### ✅ **Benefícios Comprovados**

1. **Reutilização de Conhecimento**: Soluções testadas e validadas pela comunidade ao longo de décadas
   - Reduz tempo de design arquitetural
   - Evita reinvenção de soluções conhecidas

2. **Comunicação Eficiente**: Vocabulário técnico compartilhado
   - Facilita revisões de código
   - Melhora documentação técnica
   - Acelera onboarding de novos desenvolvedores

3. **Flexibilidade Arquitetural**: Código adaptável a mudanças
   - Facilita evolução do sistema
   - Reduz impacto de modificações
   - Suporta extensibilidade

4. **Manutenibilidade**: Estruturas bem organizadas e compreensíveis
   - Separação clara de responsabilidades
   - Código auto-documentado
   - Facilita debugging

5. **Qualidade de Software**: Aplicação de boas práticas consolidadas
   - Redução de acoplamento
   - Aumento de coesão
   - Melhor testabilidade

### ⚠️ **Considerações e Limitações**

1. **Complexidade Desnecessária**: "Over-engineering"
   - Não use padrões apenas por usar
   - Avalie custo-benefício da abstração
   - Mantenha simplicidade quando possível (KISS)

2. **Curva de Aprendizado**: Requer conhecimento prévio
   - Desenvolvedores júnior podem ter dificuldade
   - Exige tempo de estudo e prática
   - Pode reduzir produtividade inicial

3. **Performance**: Camadas adicionais podem impactar desempenho
   - Indireção adicional em tempo de execução
   - Criação de objetos extras
   - Avalie requisitos de performance críticos

4. **Contexto Específico**: Nem sempre aplicável
   - Cada problema tem seu contexto único
   - Padrões podem não se adequar perfeitamente
   - Adaptações podem ser necessárias

5. **Manutenção do Código**: Abstrações mal aplicadas dificultam manutenção
   - Excesso de indireção confunde
   - Pode obscurecer fluxo de execução
   - Dificulta rastreamento de bugs

### 📊 Critérios de Decisão para Aplicar Patterns

Use este framework de decisão baseado em critérios objetivos:

**APLICAR quando:**
- ✅ Problema recorrente identificado no domínio
- ✅ Código tende a mudar frequentemente nessa área
- ✅ Múltiplas implementações alternativas são previsíveis
- ✅ Benefício de manutenibilidade supera custo de complexidade
- ✅ Equipe compreende o padrão proposto
- ✅ Requisitos de extensibilidade são claros

**EVITAR quando:**
- ❌ Problema é único e não recorrente
- ❌ Solução simples e direta já existe
- ❌ Requisitos são estáveis e não mudam
- ❌ Performance é crítica e padrão adiciona overhead
- ❌ Equipe não tem familiaridade com o padrão
- ❌ Aplicação é protótipo ou descartável

### 🔍 Anti-Patterns Comuns

Conheça armadilhas comuns ao usar design patterns:

1. **Pattern Overload**: Usar muitos padrões desnecessariamente
   ```
   Sintoma: Código excessivamente abstrato e difícil de seguir
   Solução: Simplifique, remova abstrações não justificadas
   ```

2. **Golden Hammer**: "Se tudo que você tem é um martelo, tudo parece prego"
   ```
   Sintoma: Forçar o mesmo padrão em todos os problemas
   Solução: Analise cada problema individualmente
   ```

3. **Pattern Zealotry**: Insistir em patterns por dogmatismo
   ```
   Sintoma: Rejeitar soluções simples em favor de patterns
   Solução: Priorize simplicidade e praticidade
   ```

4. **Copy-Paste Pattern**: Copiar implementação sem entender
   ```
   Sintoma: Código que não se adequa ao contexto real
   Solução: Entenda o problema antes de aplicar solução
   ```

## 🚀 Metodologia de Estudo Acadêmica

### Abordagem Estruturada de Aprendizagem

Para dominar design patterns de forma efetiva, recomenda-se seguir uma metodologia estruturada baseada em pedagogia de software engineering:

### 1. **Fase de Fundamentação Teórica**

**Objetivos**: Compreender o "porquê" antes do "como"

- **Estude o Problema**: Entenda o problema que o padrão resolve
  - Qual dor ele alivia?
  - Que trade-offs existiam antes?
  - Por que soluções simples não funcionam?

- **Análise de Forças**: Identifique forças conflitantes
  - Flexibilidade vs Simplicidade
  - Performance vs Manutenibilidade
  - Acoplamento vs Coesão

- **Contexto de Aplicação**: Quando é apropriado
  - Pré-condições necessárias
  - Consequências da aplicação
  - Alternativas viáveis

### 2. **Fase de Estudo Estrutural**

**Objetivos**: Dominar a estrutura e mecânica do padrão

- **Diagrama UML**: Analise a estrutura estática
  - Identifique participantes (classes/interfaces)
  - Compreenda relacionamentos
  - Entenda colaborações

- **Diagrama de Sequência**: Compreenda o comportamento dinâmico
  - Fluxo de mensagens entre objetos
  - Ordem de interações
  - Responsabilidades de cada participante

- **Variações do Padrão**: Conheça implementações alternativas
  - Eager vs Lazy initialization (Singleton)
  - Simple Factory vs Factory Method
  - GoF patterns vs variações modernas

### 3. **Fase de Implementação Prática**

**Objetivos**: Consolidar conhecimento através da prática

**Sequência Recomendada**:

1. **Padrões Criacionais** (1-2 semanas)
   - Comece com Singleton (mais simples)
   - Avance para Factory Method
   - Estude Builder e Abstract Factory
   - Pratique Prototype

2. **Padrões Estruturais** (2-3 semanas)
   - Inicie com Adapter e Facade (mais intuitivos)
   - Continue com Decorator e Proxy
   - Finalize com Composite e Bridge

3. **Padrões Comportamentais** (3-4 semanas)
   - Comece com Strategy e Template Method
   - Pratique Observer e Command
   - Estude State e Chain of Responsibility
   - Aprofunde em Iterator, Mediator, Memento, Visitor

### 4. **Fase de Aplicação e Refinamento**

**Objetivos**: Desenvolver intuição e expertise prática

- **Exercícios Guiados**: Implemente exemplos fornecidos
  - Execute o código
  - Modifique parâmetros
  - Observe o comportamento
  - Faça debug para entender fluxo

- **Projetos Práticos**: Identifique cenários reais
  - Refatore código existente aplicando patterns
  - Implemente mini-projetos do zero
  - Compare soluções com e sem patterns

- **Code Review**: Análise crítica de código
  - Revise implementações de colegas
  - Submeta seu código para revisão
  - Discuta decisões de design

### 5. **Fase de Integração e Maestria**

**Objetivos**: Combinar padrões e desenvolver arquiteturas robustas

- **Combinação de Padrões**: Patterns raramente existem isolados
  - MVC combina Observer, Strategy, Composite
  - DAO usa Factory e Singleton
  - Builder pode usar Composite

- **Análise de Frameworks**: Estude uso em código real
  - Spring Framework (Dependency Injection, Proxy, Template Method)
  - Java Collections (Iterator, Decorator, Strategy)
  - GUI Frameworks (Observer, Composite, Factory)

- **Design de Arquitetura**: Aplique patterns no design de sistemas
  - Identifique pontos de variação
  - Escolha patterns apropriados
  - Documente decisões arquiteturais

### 📚 Estratégias de Estudo Complementares

1. **Estudo Comparativo**: Compare padrões similares
   - Factory Method vs Abstract Factory vs Builder
   - Strategy vs State vs Command
   - Decorator vs Proxy vs Adapter

2. **Análise de Trade-offs**: Documente vantagens e desvantagens
   - Crie tabelas comparativas
   - Liste cenários ideais vs inadequados
   - Avalie impacto em qualidade de código

3. **Implementação Incremental**: Evolua código sem patterns para com patterns
   - Comece com solução ingênua
   - Identifique problemas (code smells)
   - Refatore aplicando pattern apropriado
   - Compare versões

4. **Prática Deliberada**: Foco em áreas de dificuldade
   - Reimplemente patterns que não domina
   - Varie os domínios de aplicação
   - Explique patterns para outros (técnica Feynman)

## 📊 Guia de Seleção de Padrões

Use este fluxograma mental para escolher o padrão apropriado:

### Se o Problema é sobre CRIAÇÃO DE OBJETOS:

- Precisa de **exatamente uma instância**? → **Singleton**
- Decisão de criação deve ser **delegada**? → **Factory Method**
- Precisa criar **famílias de objetos relacionados**? → **Abstract Factory**
- Objeto tem **muitos parâmetros opcionais**? → **Builder**
- Criação é **cara** e objetos são **similares**? → **Prototype**

### Se o Problema é sobre ESTRUTURA E COMPOSIÇÃO:

- Precisa **converter interface incompatível**? → **Adapter**
- Quer **adicionar funcionalidade dinamicamente**? → **Decorator**
- Precisa **simplificar interface complexa**? → **Facade**
- Estrutura em **árvore com objetos compostos**? → **Composite**
- Precisa **controlar acesso** a objeto? → **Proxy**
- Desacoplamento de **abstração e implementação**? → **Bridge**

### Se o Problema é sobre COMPORTAMENTO E COMUNICAÇÃO:

- Objetos dependentes devem ser **notificados de mudanças**? → **Observer**
- Precisa **trocar algoritmos** em tempo de execução? → **Strategy**
- Quer **encapsular requisições** como objetos? → **Command**
- Comportamento muda com **estado interno**? → **State**
- Precisa definir **esqueleto de algoritmo**? → **Template Method**
- Sequência de handlers para **processar requisição**? → **Chain of Responsibility**
- Acesso sequencial sem expor **estrutura interna**? → **Iterator**
- Centralizar **comunicação complexa** entre objetos? → **Mediator**

## 📚 Exemplos Práticos por Padrão

Cada padrão neste diretório inclui:

### Estrutura Pedagógica

1. **Explicação do Problema**: Contexto e motivação
   - Cenário real que justifica o padrão
   - Problemas que surgem sem o padrão
   - Forças conflitantes envolvidas

2. **Diagrama UML**: Visualização da estrutura
   - Participantes e seus papéis
   - Relacionamentos entre classes
   - Colaborações e fluxo de controle

3. **Implementação em Java**: Código comentado e didático
   - Exemplos progressivos (simples → complexo)
   - Comentários explicativos inline
   - Boas práticas de codificação

4. **Exemplo Prático**: Casos de uso concretos
   - Cenários do mundo real
   - Código executável e testável
   - Saída esperada documentada

5. **Variações e Considerações**: Adaptações e alternativas
   - Diferentes formas de implementação
   - Trade-offs de cada variação
   - Quando preferir uma sobre outra

6. **Exercícios Práticos**: Atividades para consolidação
   - Exercícios guiados com dicas
   - Desafios de refatoração
   - Projetos mini para prática

## 🔗 Recursos Adicionais para Estudo

### Livros Fundamentais (em ordem de complexidade)

1. **"Head First Design Patterns"** - Freeman & Freeman
   - Abordagem visual e didática, ideal para iniciantes
   - Exemplos práticos em Java
   - Exercícios e desafios

2. **"Design Patterns: Elements of Reusable OO Software"** - Gang of Four
   - Obra original e referência definitiva
   - Linguagem mais acadêmica
   - Exemplos em C++ e Smalltalk

3. **"Padrões de Projeto: Soluções Reutilizáveis"** - Gamma et al (tradução)
   - Versão em português do livro do GoF
   - Mantém rigor técnico original

4. **"Refactoring: Improving the Design of Existing Code"** - Martin Fowler
   - Como identificar oportunidades para patterns
   - Técnicas de refatoração sistemática
   - Catálogo de code smells

### Recursos Online

- **[Refactoring Guru - Design Patterns](https://refactoring.guru/design-patterns)**
  - Explicações visuais excelentes
  - Exemplos em múltiplas linguagens
  - Comparações entre patterns

- **[Java Design Patterns](https://java-design-patterns.com/)**
  - Implementações modernas em Java
  - Patterns além dos 23 do GoF
  - Código open source para estudo

- **[SourceMaking - Design Patterns](https://sourcemaking.com/design_patterns)**
  - Tutoriais estruturados
  - Anti-patterns documentados
  - Exemplos práticos

- **[Gang of Four Design Patterns - Wikipedia](https://en.wikipedia.org/wiki/Design_Patterns)**
  - Visão geral e contexto histórico
  - Referências acadêmicas
  - Críticas e discussões

### Vídeos e Cursos

- **YouTube**: Busque por "Design Patterns" + nome do pattern específico
- **Coursera/edX**: Cursos de Software Architecture e Design
- **Pluralsight/Udemy**: Cursos práticos com projetos

## 💡 Dicas Importantes e Princípios Fundamentais

### Princípios de Design Orientado a Objetos

Antes de aplicar patterns, domine estes princípios fundamentais:

1. **KISS - Keep It Simple, Stupid**
   - Simplicidade é virtude
   - Não complique desnecessariamente
   - Solução mais simples que funciona é a melhor

2. **YAGNI - You Aren't Gonna Need It**
   - Não implemente funcionalidade especulativa
   - Adicione complexidade apenas quando necessário
   - Refatore quando o problema real aparecer

3. **DRY - Don't Repeat Yourself**
   - Evite duplicação de código e lógica
   - Uma única fonte de verdade
   - Abstraia commonalities

4. **SOLID Principles**
   - **S**ingle Responsibility: Uma classe, uma responsabilidade
   - **O**pen/Closed: Aberto para extensão, fechado para modificação
   - **L**iskov Substitution: Subtipos devem ser substituíveis
   - **I**nterface Segregation: Interfaces específicas > interfaces gerais
   - **D**ependency Inversion: Dependa de abstrações, não de concreções

5. **Separation of Concerns**
   - Separe diferentes aspectos do sistema
   - Cada módulo com responsabilidade clara
   - Facilita manutenção e evolução

6. **Composition over Inheritance**
   - Prefira composição à herança
   - Herança cria acoplamento forte
   - Composição oferece mais flexibilidade

7. **Program to Interfaces, not Implementations**
   - Dependa de abstrações
   - Facilita substituição de implementações
   - Reduz acoplamento

8. **Encapsulate What Varies**
   - Identifique o que muda
   - Encapsule aspectos variáveis
   - Isole impacto de mudanças

### Relação entre Princípios e Patterns

Design patterns são **manifestações concretas** desses princípios:

- **Factory Method** aplica Open/Closed e Dependency Inversion
- **Strategy** encapsula variação de algoritmos
- **Decorator** usa composition over inheritance
- **Observer** aplica Separation of Concerns
- **Adapter** usa programming to interfaces

## 🎯 Exercícios Práticos Progressivos

### Nível Iniciante

1. **Singleton**: Implemente um gerenciador de cache thread-safe
2. **Factory Method**: Crie factory para diferentes tipos de arquivos (PDF, Excel, CSV)
3. **Strategy**: Desenvolva sistema de cálculo de frete com diferentes estratégias
4. **Observer**: Implemente sistema de notificações para mudanças de preço

### Nível Intermediário

5. **Builder**: Construa builder para relatórios complexos com múltiplas seções
6. **Decorator**: Sistema de filtros de imagem aplicáveis em cadeia
7. **Command**: Implementar sistema de undo/redo para editor de texto
8. **State**: Máquina de estados para pedido (novo → processando → enviado → entregue)

### Nível Avançado

9. **Composite + Visitor**: Sistema de arquivos com operações de busca
10. **Abstract Factory + Bridge**: UI multiplataforma com múltiplos temas
11. **Chain of Responsibility + Command**: Sistema de aprovação de despesas
12. **Mediator + Observer**: Chat room com múltiplos usuários

### Projeto Integrador

**Sistema de E-commerce Completo**
- Use Factory para criação de produtos
- Singleton para carrinho de compras
- Observer para notificações
- Strategy para métodos de pagamento
- State para status do pedido
- Decorator para embalagem e entrega
- Command para histórico de ações

## 📈 Avaliação de Aprendizado

### Checklist de Domínio de um Pattern

Para considerar que você domina um pattern, você deve ser capaz de:

- [ ] Explicar o problema que ele resolve sem olhar documentação
- [ ] Desenhar o diagrama UML de memória
- [ ] Identificar quando usá-lo em código real
- [ ] Implementar do zero sem consultar exemplos
- [ ] Listar vantagens e desvantagens
- [ ] Comparar com patterns similares
- [ ] Adaptar para diferentes contextos
- [ ] Combinar com outros patterns
- [ ] Identificar quando NÃO usar
- [ ] Ensinar o pattern para outra pessoa

### Projeto Final Sugerido

Desenvolva um sistema que utilize pelo menos:
- 2 padrões criacionais
- 2 padrões estruturais
- 3 padrões comportamentais

Documente:
- Por que cada pattern foi escolhido
- Que alternativas foram consideradas
- Que problemas cada pattern resolve
- Trade-offs de cada decisão

---

**Navegação**: 
- **Anterior**: [Conceitos Intermediários](../03-conceitos-intermediarios/) 
- **Próximo**: [Frameworks e Bibliotecas](../06-frameworks-bibliotecas/)
- **Início**: Escolha uma categoria acima para começar seus estudos