# Padrões Estruturais (Structural Patterns)

Os padrões estruturais lidam com a composição de classes e objetos, formando estruturas maiores mantendo flexibilidade e eficiência. Eles usam herança e composição para criar novas funcionalidades.

## 🎯 Objetivos

- Facilitar a composição de objetos
- Simplificar relacionamentos entre entidades
- Adicionar funcionalidades de forma flexível
- Manter estruturas desacopladas e manutenívies

## 📖 Fundamentação Teórica

Os padrões estruturais aplicam o princípio da **composição sobre herança**, favorecendo estruturas flexíveis que podem ser modificadas dinamicamente. Eles permitem que classes trabalhem juntas de maneiras que seriam difíceis ou impossíveis usando apenas herança simples.

### Princípios Aplicados

1. **Composition over Inheritance**: Prefira composição à herança para maior flexibilidade
2. **Interface Segregation**: Adapte interfaces para necessidades específicas dos clientes
3. **Open/Closed Principle**: Extensível sem modificar código existente
4. **Single Responsibility**: Cada classe tem uma responsabilidade clara na estrutura

## 📋 Padrões Implementados

### [Adapter](adapter/)
**Propósito**: Converte a interface de uma classe em outra interface esperada pelos clientes. Permite que classes com interfaces incompatíveis trabalhem juntas.

**Cenários de Uso**:
- Integração com APIs legadas ou de terceiros
- Adaptação de bibliotecas externas
- Compatibilidade entre sistemas diferentes
- Reutilização de classes existentes com interfaces incompatíveis

**Exemplo Prático**: Sistema de pagamento que precisa integrar diferentes gateways (PayPal, Stripe, PagSeguro) com interfaces diferentes.

### [Decorator](decorator/)
**Propósito**: Adiciona responsabilidades a objetos dinamicamente, fornecendo alternativa flexível à herança para estender funcionalidades.

**Cenários de Uso**:
- Streams de I/O em Java (BufferedReader, FileReader)
- Adição dinâmica de funcionalidades a componentes UI
- Filtros de requisições HTTP
- Processamento de dados em camadas

**Exemplo Prático**: Sistema de pedidos onde pode-se adicionar embalagem especial, entrega expressa, seguro, etc., de forma dinâmica.

### [Facade](facade/)
**Propósito**: Fornece uma interface unificada e simplificada para um conjunto de interfaces em um subsistema complexo.

**Cenários de Uso**:
- Simplificação de bibliotecas complexas
- Camadas de serviço em arquiteturas
- APIs públicas que escondem complexidade interna
- Pontos de entrada unificados para subsistemas

**Exemplo Prático**: Sistema de home theater que encapsula a complexidade de controlar DVD player, amplificador, projetor, luzes, etc.

### [Composite](composite/)
**Propósito**: Compõe objetos em estruturas de árvore para representar hierarquias parte-todo. Permite que clientes tratem objetos individuais e composições de objetos de maneira uniforme.

**Cenários de Uso**:
- Estruturas de arquivos e pastas
- Componentes gráficos hierárquicos (GUI)
- Menus e submenus
- Estruturas organizacionais (empresa, departamentos, funcionários)

**Exemplo Prático**: Sistema de arquivos onde tanto arquivos quanto pastas podem ser tratados uniformemente.

### [Flyweight](flyweight/)
**Propósito**: Minimiza o uso de memória compartilhando o máximo de dados possível entre objetos similares. Separa estado intrínseco (compartilhado) de estado extrínseco (único).

**Cenários de Uso**:
- Editores de texto (compartilhar estilos de caracteres)
- Jogos 3D (compartilhar modelos, texturas, sprites)
- Sistemas de UI (compartilhar componentes visuais)
- Sistemas de mapas (compartilhar ícones de marcadores)
- Connection pools e thread pools
- Caching de objetos imutáveis

**Exemplo Prático**: Editor de texto onde milhões de caracteres compartilham poucos objetos de estilo (fonte, tamanho, cor), economizando 80-99% de memória.

### [Proxy](proxy/)
**Propósito**: Fornece um substituto ou placeholder para controlar o acesso a um objeto. Adiciona funcionalidade de controle sem alterar o objeto original.

**Cenários de Uso**:
- Lazy loading (Virtual Proxy)
- Controle de acesso (Protection Proxy)
- Logging e auditoria de operações
- Cache de resultados
- Acesso remoto (Remote Proxy)

**Exemplo Prático**: Carregamento lazy de imagens grandes, carregando apenas quando necessário para economizar memória.

## 💻 Comparação dos Padrões

| Padrão | Complexidade | Propósito Principal | Modifica Comportamento? |
|--------|-------------|---------------------|------------------------|
| Adapter | Baixa | Compatibilidade de interfaces | Não |
| Decorator | Média | Adicionar responsabilidades | Sim |
| Facade | Baixa | Simplificar interface | Não |
| Composite | Média | Estruturas hierárquicas | Não |
| Flyweight | Média | Economia de memória | Não |
| Proxy | Média | Controlar acesso | Pode controlar |

## 🔍 Quando Usar Cada Padrão

### Adapter
✅ **Use quando**:
- Precisa usar classe existente com interface incompatível
- Quer criar classe reutilizável que coopera com classes não relacionadas
- Precisa usar várias subclasses existentes, mas é impraticável adaptar suas interfaces via subclassing

❌ **Evite quando**:
- Pode modificar a interface original
- Sistema ainda está em design inicial (considere já projetar interfaces compatíveis)

### Decorator
✅ **Use quando**:
- Precisa adicionar responsabilidades a objetos dinamicamente
- Funcionalidades podem ser combinadas de diferentes formas
- Herança levaria a explosão de subclasses
- Precisa de funcionalidades opcionais

❌ **Evite quando**:
- Estrutura de classes é simples e estável
- Todas as funcionalidades são sempre necessárias
- Performance é crítica (decorators adicionam overhead)

### Facade
✅ **Use quando**:
- Quer fornecer interface simples para subsistema complexo
- Há muitas dependências entre clientes e classes de implementação
- Quer estruturar subsistema em camadas

❌ **Evite quando**:
- Clientes precisam de acesso direto a todas as funcionalidades
- Subsistema já é simples

### Composite
✅ **Use quando**:
- Quer representar hierarquias parte-todo de objetos
- Quer que clientes tratem objetos individuais e composições uniformemente
- Estrutura de dados pode ser representada como árvore

❌ **Evite quando**:
- Estrutura não é hierárquica
- Componentes individuais e composições têm interfaces muito diferentes

### Flyweight
✅ **Use quando**:
- Aplicação usa enorme quantidade de objetos similares
- Custo de armazenamento é alto e pode causar problemas de memória
- Maior parte do estado do objeto pode ser compartilhado (intrínseco)
- Estado extrínseco pode ser facilmente calculado ou armazenado separadamente
- Identidade dos objetos não é importante

❌ **Evite quando**:
- Há poucas instâncias (overhead não compensa)
- Objetos têm muito estado único (pouco para compartilhar)
- Estado é difícil de separar em intrínseco vs extrínseco
- Performance de acesso é mais crítica que memória

### Proxy
✅ **Use quando**:
- Precisa de referência mais versátil ou sofisticada que um ponteiro simples
- Lazy initialization é necessária
- Controle de acesso é requerido
- Quer adicionar logging/caching sem modificar objeto original

❌ **Evite quando**:
- Acesso direto é suficiente
- Overhead adicional não é aceitável

## 🏗️ Padrões Relacionados

### Semelhanças e Diferenças

**Adapter vs Proxy**:
- **Similaridade**: Ambos são wrappers ao redor de outros objetos
- **Diferença**: Adapter muda interface; Proxy mantém mesma interface

**Decorator vs Proxy**:
- **Similaridade**: Ambos envolvem objeto e delegam chamadas
- **Diferença**: Decorator adiciona responsabilidades; Proxy controla acesso

**Composite vs Decorator**:
- **Similaridade**: Estruturas recursivas, composição
- **Diferença**: Composite foca em representar hierarquias; Decorator foca em adicionar funcionalidades

**Facade vs Adapter**:
- **Similaridade**: Ambos envolvem wrapping de interfaces
- **Diferença**: Facade simplifica muitas interfaces; Adapter adapta uma interface

**Flyweight vs Proxy**:
- **Similaridade**: Ambos compartilham aspectos de controle de objetos
- **Diferença**: Flyweight foca em economia de memória via compartilhamento; Proxy foca em controlar acesso

**Flyweight vs Singleton**:
- **Similaridade**: Ambos envolvem compartilhamento/reuso de objetos
- **Diferença**: Flyweight tem múltiplas instâncias em pool; Singleton tem apenas uma instância global

## 🚀 Como Executar os Exemplos

```bash
# Navegar para a pasta de um padrão específico
cd adapter/

# Compilar todos os arquivos Java
javac *.java

# Executar o exemplo de teste
java TesteAdapter
```

## 💡 Boas Práticas

### Para Todos os Padrões Estruturais

1. **Mantenha Simplicidade**
   - Não use padrões estruturais se estrutura simples funciona
   - Avalie trade-off entre flexibilidade e complexidade

2. **Documente Intenções**
   - Deixe claro porque escolheu determinado padrão
   - Comente decisões de design não óbvias

3. **Considere Performance**
   - Cada camada adiciona overhead
   - Meça impacto em cenários críticos

4. **Teste Adequadamente**
   - Teste tanto componentes individuais quanto composições
   - Verifique comportamento em casos extremos

5. **Use Interfaces**
   - Programe para interfaces, não implementações
   - Facilita substituição e extensão

## 📝 Exercícios Práticos

### Nível Iniciante

1. **Adapter**: Crie adapter para diferentes formatos de data (ISO, BR, US)
2. **Facade**: Desenvolva facade para operações de arquivo (ler, escrever, deletar)
3. **Decorator**: Implemente decoradores para texto (negrito, itálico, sublinhado)

### Nível Intermediário

4. **Composite**: Construa sistema de menus com itens e submenus
5. **Proxy**: Implemente proxy de cache para operações caras
6. **Decorator + Facade**: Sistema de notificações com diferentes canais e facade unificada

### Nível Avançado

7. **Adapter + Composite**: Sistema de importação de dados de múltiplos formatos em estrutura hierárquica
8. **Proxy + Decorator**: Sistema de acesso a recursos com controle de permissões e logging
9. **Todos os padrões**: Sistema de processamento de documentos usando múltiplos padrões estruturais

## 🎯 Projeto Integrador

**Sistema de E-commerce - Camada de Apresentação e Estrutura**

Implemente:
- **Adapter**: Para diferentes APIs de pagamento
- **Decorator**: Para aplicar descontos, cupons, impostos dinamicamente
- **Facade**: Para simplificar processo de checkout
- **Composite**: Para estrutura de categorias e produtos
- **Proxy**: Para lazy loading de imagens de produtos

## 📚 Recursos Adicionais

### Leitura Recomendada

1. **"Head First Design Patterns"** - Capítulos sobre padrões estruturais
2. **"Design Patterns: Elements of Reusable Object-Oriented Software"** - GoF original
3. **"Refactoring: Improving the Design of Existing Code"** - Martin Fowler

### Links Úteis

- [Refactoring Guru - Structural Patterns](https://refactoring.guru/design-patterns/structural-patterns)
- [Java Design Patterns - Structural](https://java-design-patterns.com/patterns/#structural)
- [SourceMaking - Structural Patterns](https://sourcemaking.com/design_patterns/structural_patterns)

## 🔗 Navegação

- **Anterior**: [Padrões Criacionais](../01-creational/)
- **Próximo**: [Padrões Comportamentais](../03-behavioral/)
- **Início**: [Design Patterns em Java](../)

---

**Nota Educacional**: Estes exemplos são didáticos e simplificados. Em produção, considere:
- Tratamento robusto de erros
- Thread-safety quando aplicável
- Logging e monitoramento adequados
- Testes unitários e de integração
- Documentação completa
