# Padrões Criacionais (Creational Patterns)

Os padrões criacionais abstraem o processo de criação de objetos, tornando o sistema independente de como seus objetos são criados, compostos e representados. Eles ajudam a tornar o sistema mais flexível ao delegar a responsabilidade de criação, escondendo a lógica de instanciação do cliente.

## 🎯 Objetivos Acadêmicos

Os padrões criacionais têm como objetivo principal resolver problemas relacionados à **instanciação de objetos**:

- **Encapsular conhecimento**: Ocultar detalhes de quais classes concretas são usadas
- **Flexibilizar a criação**: Permitir que o sistema decida em tempo de execução quais objetos criar
- **Reduzir acoplamento**: Diminuir dependências entre classes cliente e classes concretas
- **Promover reutilização**: Facilitar a criação de objetos similares ou relacionados
- **Controlar instanciação**: Gerenciar quando e como objetos são criados

## 📚 Por que Padrões Criacionais são Importantes?

Na programação orientada a objetos, a criação de objetos usando o operador `new` cria **acoplamento forte** com classes concretas. Isso torna o código:
- ❌ Difícil de manter
- ❌ Difícil de testar
- ❌ Difícil de estender

Os padrões criacionais resolvem isso ao **abstrair o processo de criação**, permitindo que o sistema seja:
- ✅ Mais flexível
- ✅ Mais extensível
- ✅ Mais testável

## 📋 Padrões Implementados

### [Singleton](singleton/)
**O que é**: Garante que uma classe tenha **apenas uma única instância** durante toda a execução do programa e fornece um **ponto de acesso global** a essa instância.

**Problema que resolve**: Como garantir que existe apenas um objeto de uma classe e que todos acessem a mesma instância?

**Conceitos acadêmicos**:
- **Instância única**: Previne múltiplas instâncias que desperdiçariam recursos
- **Acesso global**: Fornece ponto de acesso consistente
- **Lazy initialization**: Cria a instância apenas quando necessário
- **Thread safety**: Garante segurança em ambientes concorrentes

**Quando usar**:
- Conexões com banco de dados (pool de conexões)
- Logs de sistema (gravação centralizada)
- Configurações globais da aplicação
- Cache compartilhado
- Gerenciadores de recursos únicos

**Exemplo prático**: Um sistema delog onde todas as partes da aplicação precisam escrever no mesmo arquivo de log.

### [Factory Method](factory-method/)
**O que é**: Define uma **interface para criar objetos**, mas permite que as **subclasses decidam qual classe instanciar**. O Factory Method delega a instanciação para subclasses.

**Problema que resolve**: Como criar objetos sem especificar suas classes exatas, permitindo que subclasses alterem o tipo de objeto criado?

**Conceitos acadêmicos**:
- **Encapsulamento de criação**: Isola a lógica de criação em um método
- **Polimorfismo**: Usa herança para criar diferentes tipos
- **Open/Closed Principle**: Fácil adicionar novos tipos sem modificar código existente
- **Inversão de dependência**: Cliente depende de abstrações, não de implementações

**Quando usar**:
- Não se sabe de antemão quais tipos de objetos criar
- Classe quer delegar a criação para subclasses
- Localizar criação de objetos em um ponto central
- Facilitar adição de novos tipos sem alterar código cliente

**Exemplo prático**: Sistema de notificações que pode enviar por email, SMS ou push, decidido em tempo de execução.

### [Builder](builder/)
**O que é**: Separa a **construção de um objeto complexo** de sua representação, permitindo que o mesmo processo de construção crie **diferentes representações**. Constrói o objeto **passo a passo**.

**Problema que resolve**: Como criar objetos complexos com muitos parâmetros opcionais sem usar construtores telescópicos confusos?

**Conceitos acadêmicos**:
- **Separação de responsabilidades**: Construção vs representação
- **Fluent interface**: Permite encadeamento de métodos (method chaining)
- **Imutabilidade**: Objeto final é imutável e validado
- **Composição incremental**: Constrói objeto passo a passo

**Quando usar**:
- Objetos têm muitos parâmetros (especialmente opcionais)
- Processo de criação é complexo e tem várias etapas
- Quer criar diferentes representações do mesmo objeto
- Precisa de validação antes da criação final
- Quer tornar o objeto imutável após construção

**Exemplo prático**: Construir uma requisição HTTP complexa com headers opcionais, body, parâmetros, timeouts, etc.

### [Abstract Factory](abstract-factory/)
**O que é**: Fornece uma interface para criar **famílias de objetos relacionados ou dependentes** sem especificar suas classes concretas. É uma "fábrica de fábricas".

**Problema que resolve**: Como garantir que produtos relacionados (uma família) sejam criados de forma consistente e compatível?

**Conceitos acadêmicos**:
- **Famílias de produtos**: Agrupa produtos que devem ser usados juntos
- **Consistência**: Garante que produtos de uma família sejam compatíveis
- **Abstração de criação**: Cliente não conhece classes concretas
- **Princípio da inversão de dependência**: Depende de abstrações

**Quando usar**:
- Sistema deve ser independente de como produtos são criados
- Sistema deve trabalhar com múltiplas famílias de produtos
- Família de produtos relacionados deve ser usada em conjunto
- Quer garantir consistência entre produtos relacionados
- Biblioteca de produtos não deve expor implementações

**Exemplo prático**: Sistema com tema claro e escuro - cada tema tem botões, inputs, menus consistentes com o estilo escolhido.

### [Prototype](prototype/)
**O que é**: Permite criar novos objetos **copiando (clonando) instâncias existentes** (protótipos) ao invés de criar do zero. Evita o custo de criação quando ela é complexa.

**Problema que resolve**: Como criar novos objetos quando a criação é custosa e você já tem um objeto similar?

**Conceitos acadêmicos**:
- **Clonagem**: Cria cópias ao invés de instanciar
- **Deep vs Shallow copy**: Cópia profunda ou superficial
- **Performance**: Mais eficiente para objetos complexos
- **Redução de subclasses**: Evita hierarquias de factory

**Quando usar**:
- Criação de objetos é custosa (BD, cálculos, arquivos)
- Quer evitar hierarquia de subclasses factory
- Objetos têm poucos estados mas muitas variações
- Sistema deve ser independente de como produtos são criados
- Precisa criar objetos dinamicamente

**Exemplo prático**: Editor gráfico onde usuário copia formas (Ctrl+C, Ctrl+V) - clonar é mais eficiente que criar do zero.

## 🚀 Como Executar os Exemplos

Cada padrão tem sua própria pasta com exemplos completos e funcionais:

```bash
# Navegar para a pasta de um padrão
cd singleton/

# Compilar todos os arquivos
javac *.java

# Executar o exemplo de teste
java TesteSingleton
```

## 💡 Comparação dos Padrões

| Padrão | Complexidade | Flexibilidade | Foco Principal | Uso Comum |
|--------|-------------|---------------|----------------|-----------|
| **Singleton** | Baixa | Baixa | Uma única instância | Recursos globais únicos |
| **Factory Method** | Média | Média | Criação polimórfica | Tipos determinados em runtime |
| **Builder** | Alta | Alta | Construção passo a passo | Objetos complexos com muitos parâmetros |
| **Abstract Factory** | Alta | Alta | Famílias de produtos | Temas, plataformas, skins |
| **Prototype** | Média | Média | Clonagem de objetos | Objetos custosos de criar |

## 🆚 Quando Usar Qual Padrão?

### Escolha Singleton quando:
- ✅ Precisa de **exatamente uma instância** de uma classe
- ✅ Essa instância precisa de **acesso global**
- ✅ Exemplo: Logger, Configuração, Connection Pool

### Escolha Factory Method quando:
- ✅ Não sabe **qual classe concreta** instanciar até runtime
- ✅ Quer que **subclasses decidam** o tipo de objeto
- ✅ Exemplo: Notificações (Email/SMS/Push), Parsers (JSON/XML/CSV)

### Escolha Builder quando:
- ✅ Objeto tem **muitos parâmetros** (especialmente opcionais)
- ✅ Quer **construção passo a passo** clara e legível
- ✅ Objeto final deve ser **imutável**
- ✅ Exemplo: Requisição HTTP, Documento, Configuração complexa

### Escolha Abstract Factory quando:
- ✅ Precisa criar **famílias de objetos relacionados**
- ✅ Produtos devem ser **compatíveis entre si**
- ✅ Quer trocar **família inteira** facilmente
- ✅ Exemplo: UI Themes, Plataformas (Windows/Mac/Linux)

### Escolha Prototype quando:
- ✅ Criação do objeto é **custosa** (DB, cálculos, IO)
- ✅ Já tem objeto similar e quer **variações dele**
- ✅ Quer evitar **hierarquias de subclasses**
- ✅ Exemplo: Editor gráfico (copiar formas), Templates

## 🎓 Conceitos Acadêmicos Fundamentais

### 1. Encapsulamento de Criação
Todos os padrões criacionais **encapsulam a criação**, separando o "como criar" do "o que criar".

### 2. Programação para Interface
Cliente programa para **interfaces/abstrações**, não para implementações concretas.

### 3. Open/Closed Principle (OCP)
Sistema **aberto para extensão** (adicionar novos tipos) mas **fechado para modificação** (não altera código existente).

### 4. Inversão de Dependência (DIP)
Módulos de alto nível **não dependem de módulos de baixo nível**. Ambos dependem de abstrações.

### 5. Single Responsibility Principle (SRP)
Separar **responsabilidade de criação** da **lógica de negócio**.

## 📝 Exercícios Práticos para Alunos

### Nível Básico
1. **Singleton**: Implemente um gerenciador de configurações que lê de arquivo
2. **Factory Method**: Crie factory para diferentes tipos de veículos (Carro, Moto, Caminhão)
3. **Prototype**: Implemente clonagem de objetos Estudante com lista de notas

### Nível Intermediário
4. **Builder**: Construa builder para pizzas personalizadas com vários ingredientes
5. **Abstract Factory**: Desenvolva factories para diferentes bancos de dados (MySQL, PostgreSQL, MongoDB)
6. **Comparação**: Compare performance de Prototype vs construtor normal

### Nível Avançado
7. **Combinação**: Use Factory Method + Singleton para factories únicas
8. **Registro**: Implemente registro de protótipos com cache inteligente
9. **Projeto Real**: Sistema de relatórios usando Builder + Factory + Singleton

## 🔍 Perguntas para Reflexão

1. Por que usar padrão criacional ao invés de simplesmente `new Classe()`?
2. Qual a diferença entre Factory Method e Abstract Factory?
3. Quando Builder é melhor que construtor com muitos parâmetros?
4. Quais problemas podem ocorrer com Singleton em sistemas multi-thread?
5. Deep copy vs Shallow copy no Prototype - quando usar cada um?

## 📖 Material de Estudo Complementar

### Leituras Recomendadas
- Design Patterns: Elements of Reusable Object-Oriented Software (GoF)
- Head First Design Patterns
- Effective Java (Joshua Bloch) - Item 3: Singleton, Item 2: Builder

### Princípios SOLID Relacionados
- **S**ingle Responsibility: Separar criação de lógica
- **O**pen/Closed: Extensível sem modificação
- **L**iskov Substitution: Subtipos substituíveis
- **I**nterface Segregation: Interfaces específicas
- **D**ependency Inversion: Depender de abstrações

## 🔗 Próximos Passos

Após dominar os padrões criacionais, continue para:
- **[Padrões Estruturais](../02-structural/)**: Como compor objetos e classes
- **Padrões Comportamentais**: Como objetos colaboram e distribuem responsabilidades

## 💭 Resumo Executivo

Os **Padrões Criacionais** são essenciais para:
- ✅ Reduzir acoplamento entre classes
- ✅ Tornar código mais flexível e extensível
- ✅ Facilitar testes e manutenção
- ✅ Promover princípios SOLID
- ✅ Criar código mais limpo e profissional

**Lembre-se**: Padrões são **soluções**, não **requisitos**. Use quando o problema justificar a complexidade adicional!