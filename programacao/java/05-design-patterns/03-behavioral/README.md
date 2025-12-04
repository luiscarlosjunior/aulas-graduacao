# Padrões Comportamentais (Behavioral Patterns)

Os padrões comportamentais se concentram nos algoritmos e na atribuição de responsabilidades entre objetos. Eles não apenas descrevem padrões de objetos ou classes, mas também os padrões de comunicação entre eles.

## 📖 Contexto Acadêmico

### Fundamentação Teórica

Os padrões comportamentais caracterizam formas complexas de controle de fluxo que são difíceis de seguir em tempo de execução. Eles deslocam o foco do fluxo de controle para permitir que você se concentre apenas nas maneiras como os objetos se interconectam.

**Princípios Fundamentais:**

1. **Comunicação entre Objetos**: Facilitam a comunicação entre objetos de forma flexível e desacoplada
2. **Distribuição de Responsabilidades**: Definem como as responsabilidades são distribuídas entre objetos
3. **Encapsulamento de Comportamento**: Encapsulam comportamentos variáveis em objetos separados
4. **Baixo Acoplamento**: Reduzem dependências diretas entre objetos colaboradores

### Categorias de Padrões Comportamentais

Os padrões comportamentais podem ser classificados em duas categorias:

**1. Padrões que lidam com algoritmos e comportamentos:**
- Strategy
- Template Method
- Command
- Interpreter

**2. Padrões que lidam com comunicação entre objetos:**
- Observer
- Mediator
- Chain of Responsibility
- Iterator
- Memento
- State
- Visitor

## 🎯 Objetivos de Aprendizagem

Ao estudar os padrões comportamentais, você será capaz de:

- Identificar quando e como distribuir responsabilidades entre objetos
- Implementar comunicação flexível e desacoplada entre componentes
- Encapsular comportamentos variáveis de forma efetiva
- Criar sistemas mais extensíveis e manuteníveis
- Aplicar princípios de design como Open/Closed e Single Responsibility
- Desenvolver código que facilita a adição de novos comportamentos

## 📋 Catálogo de Padrões Implementados

### [Observer](observer/)

**Definição**: Define uma dependência um-para-muitos entre objetos, de modo que quando um objeto muda de estado, todos os seus dependentes são notificados e atualizados automaticamente.

**Problema que Resolve**: Como notificar múltiplos objetos sobre mudanças em outro objeto sem criar forte acoplamento?

**Cenários de Uso:**
- Sistemas de notificação (newsletters, alertas)
- Implementação do padrão MVC (Model-View-Controller)
- Event listeners em interfaces gráficas
- Sistemas de publicação/assinatura (pub/sub)
- Monitoramento de mudanças em tempo real

**Exemplo Prático**: Sistema de monitoramento de preços que notifica múltiplos displays quando o preço de um produto muda.

**Princípios Aplicados:**
- Baixo acoplamento entre Subject e Observers
- Open/Closed Principle: novos observers podem ser adicionados sem modificar o subject

---

### [Strategy](strategy/)

**Definição**: Define uma família de algoritmos, encapsula cada um deles e os torna intercambiáveis. Strategy permite que o algoritmo varie independentemente dos clientes que o utilizam.

**Problema que Resolve**: Como selecionar algoritmos diferentes em tempo de execução sem usar estruturas condicionais complexas?

**Cenários de Uso:**
- Diferentes algoritmos de ordenação ou busca
- Múltiplas formas de validação de dados
- Variados métodos de pagamento (cartão, boleto, PIX)
- Diferentes estratégias de cálculo (impostos, descontos, frete)
- Algoritmos de compressão ou criptografia intercambiáveis

**Exemplo Prático**: Sistema de cálculo de frete com diferentes estratégias (PAC, SEDEX, transportadora).

**Princípios Aplicados:**
- Dependency Inversion: código cliente depende de abstrações
- Open/Closed: novas estratégias sem modificar contexto
- Single Responsibility: cada estratégia tem uma responsabilidade única

---

### [Command](command/)

**Definição**: Encapsula uma requisição como um objeto, permitindo parametrizar clientes com diferentes requisições, enfileirar requisições e implementar operações que podem ser desfeitas.

**Problema que Resolve**: Como desacoplar o objeto que invoca uma operação do objeto que sabe como executá-la?

**Cenários de Uso:**
- Implementação de undo/redo em editores
- Sistemas de macro e scripts
- Agendamento de tarefas
- Logs de transações
- Implementação de filas de requisições
- Callbacks e event handling

**Exemplo Prático**: Editor de texto com funcionalidades de desfazer/refazer operações de edição.

**Princípios Aplicados:**
- Single Responsibility: separa invocação de execução
- Open/Closed: novos comandos sem modificar invocador

---

### [State](state/)

**Definição**: Permite que um objeto altere seu comportamento quando seu estado interno muda. O objeto parecerá ter mudado sua classe.

**Problema que Resolve**: Como implementar máquinas de estado sem usar condicionais complexos que são difíceis de manter?

**Cenários de Uso:**
- Máquinas de estado em jogos (personagem parado, andando, pulando)
- Fluxos de workflow (pedido: novo → processando → enviado → entregue)
- Conexões de rede (conectando, conectado, desconectado)
- Estados de documentos (rascunho, revisão, publicado)
- Controle de threads (novo, executando, bloqueado, terminado)

**Exemplo Prático**: Sistema de pedido de e-commerce com diferentes estados e transições.

**Princípios Aplicados:**
- Open/Closed: adicionar novos estados sem modificar contexto
- Single Responsibility: cada estado encapsula seu comportamento
- Eliminação de condicionais complexos

---

### [Template Method](template-method/)

**Definição**: Define o esqueleto de um algoritmo em uma operação, postergando alguns passos para as subclasses. Template Method permite que subclasses redefinam certos passos de um algoritmo sem mudar sua estrutura.

**Problema que Resolve**: Como definir a estrutura de um algoritmo permitindo que partes específicas sejam customizadas?

**Cenários de Uso:**
- Frameworks com pontos de extensão (hooks)
- Processos de ETL (Extract, Transform, Load) com variações
- Algoritmos com estrutura comum mas detalhes variáveis
- Workflows com passos obrigatórios e opcionais
- Geração de relatórios com formatos diferentes

**Exemplo Prático**: Sistema de processamento de documentos (PDF, Word, Excel) com estrutura comum.

**Princípios Aplicados:**
- Hollywood Principle: "Don't call us, we'll call you"
- Open/Closed: extensão via herança sem modificar algoritmo base
- DRY: código comum na classe base

---

### [Chain of Responsibility](chain-of-responsibility/)

**Definição**: Evita o acoplamento do remetente de uma requisição ao seu receptor, dando a mais de um objeto a chance de tratar a requisição. Encadeia os objetos receptores e passa a requisição ao longo da cadeia até que um objeto a trate.

**Problema que Resolve**: Como processar requisições sem acoplar o remetente aos possíveis receptores?

**Cenários de Uso:**
- Sistemas de aprovação hierárquica (gerente → diretor → CEO)
- Filtros de requisições HTTP (autenticação, validação, logging)
- Event bubbling em interfaces gráficas
- Processamento de exceções
- Middlewares em aplicações web

**Exemplo Prático**: Sistema de aprovação de despesas com diferentes níveis de autoridade.

**Princípios Aplicados:**
- Open/Closed: adicionar handlers sem modificar código existente
- Single Responsibility: cada handler trata um tipo específico
- Redução de acoplamento entre remetente e receptor

---

### [Iterator](iterator/)

**Definição**: Fornece uma maneira de acessar sequencialmente os elementos de um objeto agregado sem expor sua representação subjacente.

**Problema que Resolve**: Como percorrer elementos de uma coleção sem expor sua estrutura interna?

**Cenários de Uso:**
- Iteração sobre estruturas de dados personalizadas
- Navegação em árvores e grafos
- Acesso a diferentes tipos de coleções de forma uniforme
- Implementação de lazy loading de dados
- Iteração com filtros e transformações

**Exemplo Prático**: Iterador customizado para percorrer uma estrutura de dados especial (árvore, grafo).

**Princípios Aplicados:**
- Single Responsibility: separação entre coleção e iteração
- Interface Segregation: interface simples de iteração
- Encapsulamento da estrutura interna

---

### [Mediator](mediator/)

**Definição**: Define um objeto que encapsula como um conjunto de objetos interage. Mediator promove o acoplamento fraco ao evitar que objetos se refiram uns aos outros explicitamente.

**Problema que Resolve**: Como reduzir o acoplamento entre múltiplos objetos que precisam se comunicar?

**Cenários de Uso:**
- Sistemas de chat (chat room coordena comunicação entre usuários)
- Coordenação de componentes em interfaces gráficas
- Controle de tráfego aéreo (torre controla aviões)
- Gerenciamento de eventos em aplicações complexas
- Orquestração de serviços

**Exemplo Prático**: Sistema de chat room que coordena mensagens entre múltiplos usuários.

**Princípios Aplicados:**
- Redução de acoplamento many-to-many para one-to-many
- Single Responsibility: mediador centraliza lógica de comunicação
- Simplificação de dependências entre objetos

---

### [Memento](memento/)

**Definição**: Sem violar o encapsulamento, captura e externaliza o estado interno de um objeto, de modo que o objeto possa ser restaurado para esse estado mais tarde.

**Problema que Resolve**: Como salvar e restaurar o estado de um objeto sem expor sua implementação interna?

**Cenários de Uso:**
- Implementação de undo/redo
- Salvamento de checkpoints em jogos
- Transações com rollback
- Histórico de versões (version control)
- Backup e restauração de estado

**Exemplo Prático**: Editor de texto com funcionalidade de desfazer mudanças.

**Princípios Aplicados:**
- Encapsulamento: estado interno não é exposto
- Single Responsibility: originator gerencia estado, memento armazena
- Separação entre salvamento e lógica de negócio

---

### [Visitor](visitor/)

**Definição**: Representa uma operação a ser executada nos elementos de uma estrutura de objetos. Visitor permite definir uma nova operação sem mudar as classes dos elementos sobre os quais opera.

**Problema que Resolve**: Como adicionar novas operações a uma estrutura de objetos sem modificar essas classes?

**Cenários de Uso:**
- Operações sobre estruturas de árvore (AST - Abstract Syntax Tree)
- Análise e processamento de documentos complexos
- Geração de relatórios sobre objetos heterogêneos
- Compiladores e interpretadores
- Exportação de dados em múltiplos formatos

**Exemplo Prático**: Sistema de exportação de dados para diferentes formatos (JSON, XML, CSV).

**Princípios Aplicados:**
- Open/Closed: novas operações sem modificar elementos
- Single Responsibility: operações separadas dos elementos
- Double Dispatch para escolha de método correto

---

### [Interpreter](interpreter/)

**Definição**: Dada uma linguagem, define uma representação para sua gramática juntamente com um interpretador que usa a representação para interpretar sentenças na linguagem.

**Problema que Resolve**: Como interpretar e avaliar expressões de uma linguagem ou gramática específica?

**Cenários de Uso:**
- Interpretadores de linguagens de domínio específico (DSL)
- Avaliação de expressões matemáticas
- Processamento de regras de negócio
- Parsers de configuração
- Query builders e SQL parsers

**Exemplo Prático**: Interpretador de expressões matemáticas simples.

**Princípios Aplicados:**
- Composite para estrutura de expressões
- Cada regra gramatical é uma classe
- Facilita implementação de gramáticas

---

## 📊 Comparação entre Padrões Comportamentais

### Padrões de Comunicação

| Padrão | Tipo de Comunicação | Número de Objetos | Acoplamento |
|--------|---------------------|-------------------|-------------|
| **Observer** | Um-para-muitos | 1 subject, N observers | Baixo |
| **Mediator** | Muitos-para-muitos | N colegas via 1 mediador | Médio |
| **Chain of Responsibility** | Um-para-um (sequencial) | N handlers em cadeia | Baixo |

### Padrões de Algoritmos

| Padrão | Flexibilidade | Uso de Herança | Substituibilidade |
|--------|---------------|----------------|-------------------|
| **Strategy** | Runtime | Não (composição) | Alta |
| **Template Method** | Compile-time | Sim | Média |
| **Command** | Runtime | Não | Alta |

### Padrões de Estado/Comportamento

| Padrão | Mudança de Comportamento | Baseado em | Complexidade |
|--------|--------------------------|------------|--------------|
| **State** | Automática (baseada em estado) | Estado interno | Média |
| **Strategy** | Manual (pelo cliente) | Algoritmo escolhido | Baixa |
| **Command** | Por execução | Comando selecionado | Baixa |

## 🎓 Guia de Estudo Recomendado

### Ordem de Aprendizado Progressiva

**Nível Iniciante** (Semana 1-2):
1. **Strategy** - Mais simples e intuitivo
2. **Observer** - Muito usado e fácil de entender
3. **Template Method** - Conceito simples de herança

**Nível Intermediário** (Semana 3-4):
4. **Command** - Encapsulamento de ações
5. **State** - Similar a Strategy mas mais complexo
6. **Iterator** - Padrão comum em Java

**Nível Avançado** (Semana 5-6):
7. **Chain of Responsibility** - Cadeia de processamento
8. **Mediator** - Gerenciamento de interações complexas
9. **Memento** - Gerenciamento de estado histórico
10. **Visitor** - Padrão mais complexo
11. **Interpreter** - Implementação de DSL

## 💻 Exercícios Práticos por Padrão

### Observer
1. Sistema de notificações multi-canal (email, SMS, push)
2. Dashboard que atualiza múltiplos gráficos
3. Sistema de leilão com múltiplos licitantes

### Strategy
1. Calculadora de impostos por região
2. Sistema de ordenação com múltiplos algoritmos
3. Validadores de formulário intercambiáveis

### Command
1. Controle remoto universal
2. Sistema de macros para automação
3. Sistema de agendamento de tarefas

### State
1. Player de música (parado, tocando, pausado)
2. Processo de pedido e-commerce
3. Semáforo inteligente

### Template Method
1. Processadores de diferentes tipos de arquivo
2. Algoritmos de importação de dados
3. Geradores de relatórios

### Chain of Responsibility
1. Sistema de autenticação e autorização
2. Processador de requisições HTTP com filtros
3. Sistema de suporte com escalação

### Iterator
1. Iterador para árvore binária
2. Iterador com filtros customizados
3. Iterador preguiçoso (lazy)

### Mediator
1. Sistema de chat multi-usuário
2. Formulário com campos interdependentes
3. Sistema de controle de tráfego

### Memento
1. Editor de imagens com undo/redo
2. Sistema de versionamento de documentos
3. Jogo com sistema de save/load

### Visitor
1. Exportador de estrutura de objetos
2. Calculador de impostos sobre produtos
3. Gerador de relatórios HTML/PDF

### Interpreter
1. Calculadora de expressões
2. Sistema de regras de negócio
3. Parser de comandos simples

## 🔍 Quando Usar Cada Padrão?

### Use **Observer** quando:
- Mudança em um objeto requer atualização de outros
- Número de objetos dependentes é desconhecido ou variável
- Você quer baixo acoplamento entre objetos

### Use **Strategy** quando:
- Você tem várias variantes de um algoritmo
- Algoritmo deve ser selecionado em runtime
- Você quer evitar condicionais complexos

### Use **Command** quando:
- Você quer parametrizar objetos com operações
- Você precisa de undo/redo
- Você quer enfileirar, agendar ou registrar operações

### Use **State** quando:
- Comportamento de objeto depende de seu estado
- Operações têm condicionais complexos baseados em estado
- Estados são bem definidos e as transições são claras

### Use **Template Method** quando:
- Múltiplas classes têm algoritmos similares
- Você quer controlar pontos de extensão
- Você quer evitar duplicação de código

### Use **Chain of Responsibility** quando:
- Mais de um objeto pode tratar uma requisição
- O handler não é conhecido a priori
- Conjunto de handlers deve ser definido dinamicamente

### Use **Iterator** quando:
- Você precisa acessar elementos sem expor representação
- Você quer suportar múltiplas travessias simultâneas
- Você quer interface uniforme para diferentes coleções

### Use **Mediator** quando:
- Objetos se comunicam de forma complexa
- Reutilização de objeto é difícil devido a dependências
- Comportamento distribuído deve ser customizável

### Use **Memento** quando:
- Você precisa salvar/restaurar estado de objeto
- Acesso direto violaria encapsulamento
- Você quer implementar undo/rollback

### Use **Visitor** quando:
- Estrutura contém classes com interfaces diferentes
- Operações dependem das classes concretas
- Você quer adicionar operações sem modificar classes

### Use **Interpreter** quando:
- Você tem gramática simples para interpretar
- Eficiência não é prioridade crítica
- Você quer representar regras ou expressões

## ⚠️ Anti-Patterns e Armadilhas Comuns

### Observer
- **God Observable**: Subject com muitos tipos de eventos diferentes
- **Observer Leaks**: Observers não removidos causando memory leaks
- **Update Storms**: Cascata de notificações causando loops infinitos

### Strategy
- **Strategy Explosion**: Criar strategy para cada pequena variação
- **Wrong Abstraction**: Interface da strategy muito específica ou genérica
- **State vs Strategy Confusion**: Usar strategy quando state é mais apropriado

### Command
- **Anemic Commands**: Commands que são apenas DTOs sem lógica
- **Command Overhead**: Criar command para operações triviais
- **Missing Undo Logic**: Implementar execute mas não undo adequadamente

### State
- **State Explosion**: Criar estado para cada combinação possível
- **Shared State Data**: Estados compartilhando dados mutáveis
- **Missing Transitions**: Não tratar todas transições possíveis

### Template Method
- **Too Many Hooks**: Método template com muitos pontos de extensão
- **Rigid Structure**: Template inflexível que força implementações inadequadas
- **Inheritance Hell**: Hierarquia profunda de templates

## 📚 Recursos Adicionais

### Livros Recomendados
1. "Design Patterns: Elements of Reusable Object-Oriented Software" - GoF
2. "Head First Design Patterns" - Freeman & Freeman
3. "Refactoring to Patterns" - Joshua Kerievsky

### Links Úteis
- [Refactoring Guru - Behavioral Patterns](https://refactoring.guru/design-patterns/behavioral-patterns)
- [SourceMaking - Behavioral Patterns](https://sourcemaking.com/design_patterns/behavioral_patterns)
- [Java Design Patterns - Behavioral](https://java-design-patterns.com/patterns/#behavioral)

## 🎯 Checklist de Domínio

Para considerar que você domina os padrões comportamentais, você deve:

- [ ] Explicar a diferença entre padrões de comunicação e de algoritmo
- [ ] Identificar qual padrão usar em cenários reais
- [ ] Implementar cada padrão sem consultar documentação
- [ ] Combinar múltiplos padrões comportamentais
- [ ] Refatorar código procedural para usar padrões adequados
- [ ] Avaliar trade-offs entre padrões similares
- [ ] Identificar anti-patterns e armadilhas comuns
- [ ] Aplicar padrões em projetos reais

---

**Navegação**: 
- **Voltar**: [Design Patterns](../)
- **Anterior**: [Padrões Estruturais](../02-structural/)
- **Início**: [Programming Java](../../)
