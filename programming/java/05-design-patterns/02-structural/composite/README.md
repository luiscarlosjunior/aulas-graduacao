# Composite Pattern

O padrão Composite compõe objetos em estruturas de árvore para representar hierarquias parte-todo. Permite que clientes tratem objetos individuais e composições de forma uniforme, aplicando operações recursivamente em toda a estrutura.

## 🎯 Problema

Como representar hierarquias parte-todo onde objetos individuais e grupos precisam ser tratados uniformemente? Como executar operações em uma estrutura hierárquica inteira sem se preocupar se cada elemento é individual ou é uma composição?

### Exemplo Real
Imagine um sistema de arquivos: você pode copiar um arquivo único ou uma pasta inteira com subpastas e arquivos. O comando `cp` não precisa saber se está copiando um arquivo ou uma hierarquia completa - ambos são tratados uniformemente. Outro exemplo: em uma interface gráfica, um botão e um painel com múltiplos componentes podem ambos responder a `draw()`.

### Cenários Comuns
- **Sistema de Arquivos**: Arquivos e pastas (pastas contêm arquivos e outras pastas)
- **Interface Gráfica**: Componentes simples (botão, label) e containers (painel, janela)
- **Estrutura Organizacional**: Funcionários individuais e departamentos (que contêm funcionários e subdepartamentos)
- **Menus**: Itens de menu e submenus
- **Expressões Matemáticas**: Números e operações compostas
- **Documentos**: Parágrafos, seções, capítulos

## 💡 Solução

Criar estrutura de árvore onde:
1. **Interface comum** (Component) para objetos simples e compostos
2. **Folhas** (Leaf) implementam operações básicas sem filhos
3. **Composites** mantêm coleção de filhos e delegam operações recursivamente
4. **Cliente** trabalha com Component, sem distinguir entre Leaf e Composite

### Características Principais
- Estrutura recursiva: Composites podem conter outros Composites
- Transparência: Cliente não precisa saber se trabalha com objeto simples ou composto
- Operações uniformes: Mesma interface para todos os componentes
- Delegação: Composite delega operações para seus filhos

## 🏗️ Estrutura

```
┌─────────────┐
│  Component  │
│ (interface) │
└─────────────┘
       △
       │
    ├──┴──┐
    │     │
┌───────┐ ┌──────────┐
│ Leaf  │ │Composite │
└───────┘ └──────────┘
             │ children
             └─────────> Component*
```

## 📋 Componentes

- **Component**: Interface ou classe abstrata que declara operações comuns
  - Define interface para objetos na composição
  - Pode implementar comportamento padrão
  - Declara interface para acessar/gerenciar componentes filhos
  
- **Leaf**: Objeto folha (sem filhos)
  - Representa objetos terminais da composição
  - Implementa operações de Component
  - Não tem filhos (métodos de adicionar/remover lançam exceção ou não fazem nada)
  
- **Composite**: Objeto composto (com filhos)
  - Define comportamento para componentes com filhos
  - Armazena componentes filhos (List, Array, etc.)
  - Implementa operações delegando para filhos
  - Implementa métodos para adicionar/remover filhos

- **Cliente**: Manipula objetos através da interface Component
  - Não distingue entre Leaf e Composite
  - Trata toda hierarquia uniformemente

## 📝 Implementações

### Sistema de Arquivos

- **[ElementoSistemaArquivos.java](ElementoSistemaArquivos.java)** - Interface Component que define operações comuns
- **[Arquivo.java](Arquivo.java)** - Leaf (elemento simples sem filhos)
- **[Pasta.java](Pasta.java)** - Composite (contém outros elementos e delega operações)
- **[TesteComposite.java](TesteComposite.java)** - Demonstração com criação de hierarquia de arquivos e pastas

## 🚀 Como Executar

```bash
# Navegar para o diretório
cd composite/

# Compilar todos os arquivos
javac *.java

# Executar o teste
java TesteComposite
```

## 📊 Exemplo de Saída Esperada

```
=== Sistema de Arquivos com Composite Pattern ===

Estrutura criada:
Raiz/
  ├── arquivo1.txt (10 KB)
  ├── Documentos/
  │   ├── relatorio.docx (50 KB)
  │   └── apresentacao.pptx (200 KB)
  └── Projetos/
      ├── codigo.java (15 KB)
      └── Libs/
          └── biblioteca.jar (500 KB)

Tamanho total da raiz: 775 KB
Tamanho de Documentos: 250 KB
Tamanho de arquivo1.txt: 10 KB
```

## ✅ Vantagens

1. **Uniformidade no Tratamento**
   - Cliente trata objetos simples e compostos da mesma forma
   - Não precisa de if/else ou instanceof para distinguir tipos
   - Código cliente mais simples e limpo

2. **Facilidade de Adicionar Novos Componentes**
   - Open/Closed Principle: aberto para extensão
   - Novos tipos de Leaf ou Composite não afetam código existente
   - Apenas implementam interface Component

3. **Estruturas Hierárquicas Naturais**
   - Representa naturalmente hierarquias parte-todo
   - Estrutura de árvore é intuitiva e recursiva
   - Operações se propagam automaticamente pela hierarquia

4. **Operações Recursivas Simplificadas**
   - Operações em Composite são automaticamente aplicadas a toda subárvore
   - Não precisa navegar manualmente pela estrutura
   - Delegação automática simplifica implementação

5. **Flexibilidade**
   - Estrutura pode crescer dinamicamente
   - Fácil reorganizar hierarquia em tempo de execução
   - Composições podem ser alteradas sem afetar clientes

## ⚠️ Desvantagens

1. **Generalização Excessiva**
   - Interface muito geral pode ser confusa
   - Leaf tem métodos de adicionar/remover que não fazem sentido
   - Pode violar Interface Segregation Principle

2. **Dificuldade em Restringir Componentes**
   - Difícil restringir tipos de filhos que um Composite pode ter
   - Exemplo: Pasta que só aceita arquivos .txt
   - Verificações podem precisar ser feitas em runtime

3. **Complexidade Adicional**
   - Para estruturas simples, pode ser over-engineering
   - Adiciona camadas de abstração
   - Pode dificultar debugging de hierarquias profundas

4. **Performance**
   - Operações recursivas podem ser custosas em hierarquias profundas
   - Cada nível adiciona overhead de chamada de método
   - Necessário cuidado com estruturas muito grandes

5. **Design Trade-offs**
   - Transparência vs Segurança: tornar interface uniforme pode sacrificar type safety
   - Pode levar a erros em runtime (ex: tentar adicionar filho a Leaf)

## 🎯 Quando Usar

✅ **Use Composite quando**:
- Precisa representar hierarquias parte-todo de objetos
- Quer que clientes tratem objetos individuais e composições uniformemente
- Estrutura de dados pode ser representada como árvore
- Precisa executar operações recursivamente em toda hierarquia
- Quer adicionar novos tipos de componentes facilmente
- Hierarquia pode crescer e mudar dinamicamente

❌ **Evite Composite quando**:
- Estrutura não é hierárquica ou é lista simples
- Componentes individuais e composições têm interfaces muito diferentes
- Precisa de restrições fortes sobre tipos de filhos
- Estrutura é fixa e simples (array ou lista é suficiente)
- Performance de operações recursivas é crítica
- Hierarquia é muito rasa (1-2 níveis apenas)

## 💼 Casos de Uso Reais

### 1. Java AWT/Swing - Interface Gráfica
```java
// Container é Composite, Button/Label são Leaf
JPanel panel = new JPanel();      // Composite
panel.add(new JButton("OK"));     // Leaf
panel.add(new JLabel("Nome:"));   // Leaf

JPanel mainPanel = new JPanel();  // Composite
mainPanel.add(panel);             // Adiciona outro Composite
mainPanel.repaint();              // Operação recursiva em toda árvore
```

### 2. Estrutura Organizacional
```java
// Departamento como Composite, Funcionário como Leaf
Departamento ti = new Departamento("TI");
ti.adicionar(new Funcionario("João", "Dev"));
ti.adicionar(new Funcionario("Maria", "QA"));

Departamento empresa = new Departamento("Empresa");
empresa.adicionar(ti);
empresa.adicionar(new Departamento("RH"));

// Calcula salário total recursivamente
double totalSalarios = empresa.calcularCustoTotal();
```

### 3. Sistema de Menus
```java
// Menu é Composite, MenuItem é Leaf
Menu arquivo = new Menu("Arquivo");
arquivo.adicionar(new MenuItem("Novo", acao1));
arquivo.adicionar(new MenuItem("Abrir", acao2));

Menu arquivoRecente = new Menu("Recentes");  // Submenu
arquivoRecente.adicionar(new MenuItem("doc1.txt", acao3));
arquivo.adicionar(arquivoRecente);

MenuBar barra = new MenuBar();
barra.adicionar(arquivo);
```

### 4. Expressões Matemáticas
```java
// Número é Leaf, Operação é Composite
interface Expressao {
    double avaliar();
}

class Numero implements Expressao {
    public double avaliar() { return valor; }
}

class Soma implements Expressao {
    private Expressao esq, dir;
    public double avaliar() { 
        return esq.avaliar() + dir.avaliar(); 
    }
}

// (2 + 3) * 4
Expressao expr = new Multiplicacao(
    new Soma(new Numero(2), new Numero(3)),
    new Numero(4)
);
```

## 🔄 Implementação em Java

### Abordagem 1: Interface Única (Transparência)
```java
// Mais transparente, mas menos seguro
interface Component {
    void operacao();
    void adicionar(Component c);     // Leaf lançará exceção
    void remover(Component c);       // Leaf lançará exceção
    Component getFilho(int i);       // Leaf lançará exceção
}
```

**Vantagens**: Cliente não precisa saber se é Leaf ou Composite
**Desvantagens**: Leaf tem métodos que não fazem sentido

### Abordagem 2: Interface Separada (Segurança)
```java
// Mais seguro, mas menos transparente
interface Component {
    void operacao();
}

interface CompositeInterface extends Component {
    void adicionar(Component c);
    void remover(Component c);
    Component getFilho(int i);
}
```

**Vantagens**: Type safety, Leaf não tem métodos inválidos
**Desvantagens**: Cliente precisa fazer cast ou verificar tipo

### Implementação Recomendada para Java
```java
// Compromisso: métodos de gestão só em Composite
public interface Component {
    void operacao();
    // Métodos opcionais com implementação padrão
    default void adicionar(Component c) {
        throw new UnsupportedOperationException();
    }
}

public class Leaf implements Component {
    public void operacao() { 
        // Implementação 
    }
    // Não sobrescreve adicionar - usa exceção padrão
}

public class Composite implements Component {
    private List<Component> filhos = new ArrayList<>();
    
    @Override
    public void adicionar(Component c) {
        filhos.add(c);
    }
    
    public void operacao() {
        for (Component filho : filhos) {
            filho.operacao();  // Delega recursivamente
        }
    }
}
```

## 🔗 Navegação

- [Voltar para Padrões Estruturais](../)
- [Anterior: Facade](../facade/)
- [Próximo: Proxy](../proxy/)

## ❓ Perguntas e Respostas Frequentes

### Q1: Qual a diferença entre Composite e árvores de dados tradicionais?
**R**: Composite é um padrão de design focado em tratamento uniforme, enquanto árvores tradicionais focam em estrutura de dados. Composite enfatiza que cliente não precisa distinguir entre Leaf e Composite, permitindo polimorfismo. Árvores tradicionais geralmente têm APIs distintas para nós e folhas.

### Q2: Como evito que Leaf tenha métodos inválidos (adicionar, remover)?
**R**: Três abordagens:
1. **Exceção**: Leaf lança `UnsupportedOperationException` (padrão Java)
2. **Método vazio**: Leaf não faz nada (silenciosamente ignora)
3. **Interface separada**: Composite implementa interface extra com métodos de gestão (mais type-safe, menos transparente)

A abordagem 1 é mais comum em Java, pois detecta erros de programação.

### Q3: Composite deve conhecer seu pai?
**R**: Depende dos requisitos:
- **Sem referência ao pai**: Mais simples, árvore unidirecional
- **Com referência ao pai**: Permite navegação para cima, útil para operações como "remover-se" ou "caminho completo"

Se adicionar referência ao pai:
```java
public class Component {
    protected Component pai;
    
    public void adicionar(Component c) {
        filhos.add(c);
        c.setPai(this);  // Mantém referência bidirecional
    }
}
```

### Q4: Como implementar operação que só faz sentido para Composite?
**R**: Use método em Composite, não em Component:
```java
public class Pasta extends Component {
    public int contarArquivos() {  // Específico de Pasta
        int total = 0;
        for (Component filho : filhos) {
            if (filho instanceof Arquivo) total++;
            else if (filho instanceof Pasta) 
                total += ((Pasta)filho).contarArquivos();
        }
        return total;
    }
}
```

Ou use Visitor Pattern para operações complexas específicas.

### Q5: Como ordenar componentes dentro de Composite?
**R**: Use estrutura de dados ordenada:
```java
public class Composite implements Component {
    private List<Component> filhos = new ArrayList<>();
    
    public void adicionar(Component c) {
        filhos.add(c);
        Collections.sort(filhos, comparador);  // Ordena após adicionar
    }
    
    // Ou use TreeSet se ordem é sempre mantida
    private Set<Component> filhos = new TreeSet<>(comparador);
}
```

### Q6: Composite Pattern vs Decorator Pattern - quando usar cada um?
**R**: 
- **Composite**: Estrutura hierárquica, múltiplos filhos, operações recursivas
  - Exemplo: Pasta contém múltiplos arquivos e subpastas
  - Foco: Agregar e tratar conjunto como unidade
  
- **Decorator**: Cadeia linear, um componente decorado, adiciona responsabilidades
  - Exemplo: Bebida decorada com leite, depois chocolate
  - Foco: Adicionar comportamento dinamicamente

Ambos podem ser combinados: Composite para estrutura, Decorator para funcionalidades adicionais.

### Q7: Como evitar recursão infinita em estruturas cíclicas?
**R**: Composite assume estrutura de árvore (sem ciclos). Para evitar ciclos:
```java
public void adicionar(Component c) {
    if (c == this) 
        throw new IllegalArgumentException("Não pode adicionar a si mesmo");
    
    // Verificar se c já está em ancestrais
    Component ancestral = this.pai;
    while (ancestral != null) {
        if (ancestral == c) 
            throw new IllegalArgumentException("Ciclo detectado");
        ancestral = ancestral.getPai();
    }
    
    filhos.add(c);
}
```

### Q8: Como implementar busca eficiente em hierarquia Composite?
**R**: Algumas estratégias:
```java
// 1. Busca em profundidade (DFS)
public Component buscar(String nome) {
    if (this.nome.equals(nome)) return this;
    
    for (Component filho : filhos) {
        Component resultado = filho.buscar(nome);
        if (resultado != null) return resultado;
    }
    return null;
}

// 2. Cache/Índice para buscas frequentes
private Map<String, Component> indice = new HashMap<>();

public void adicionar(Component c) {
    filhos.add(c);
    indice.put(c.getNome(), c);  // Indexa por nome
}

// 3. Iterator para percorrer sem recursão explícita
public Iterator<Component> iterator() {
    return new CompositeIterator(this);
}
```

### Q9: Como serializar/deserializar estrutura Composite?
**R**: 
```java
// Usando JSON (com biblioteca como Gson)
@Serializable
public class Pasta implements Component {
    @SerializedName("nome")
    private String nome;
    
    @SerializedName("filhos")
    private List<Component> filhos;
    
    // Gson trata recursão automaticamente
}

// Ou implementar Serializable para serialização Java nativa
public class Pasta implements Component, Serializable {
    private static final long serialVersionUID = 1L;
    private transient Component pai;  // Não serializa referência pai
    // ...
}
```

### Q10: Como lidar com operações que retornam valores agregados?
**R**: Leaf retorna seu valor, Composite agrega valores dos filhos:
```java
interface Component {
    int getTamanho();
}

class Arquivo implements Component {
    public int getTamanho() {
        return tamanhoBytes;  // Retorna seu próprio tamanho
    }
}

class Pasta implements Component {
    public int getTamanho() {
        int total = 0;
        for (Component filho : filhos) {
            total += filho.getTamanho();  // Soma tamanhos dos filhos
        }
        return total;
    }
}
```

### Q11: Composite funciona bem com padrões de concorrência?
**R**: Requer sincronização cuidadosa:
```java
public class Pasta implements Component {
    private final List<Component> filhos = 
        Collections.synchronizedList(new ArrayList<>());
    
    public synchronized void adicionar(Component c) {
        filhos.add(c);
    }
    
    public int getTamanho() {
        synchronized(filhos) {
            return filhos.stream()
                .mapToInt(Component::getTamanho)
                .sum();
        }
    }
}
```

Ou use estruturas concurrent do Java:
```java
private final CopyOnWriteArrayList<Component> filhos = 
    new CopyOnWriteArrayList<>();
```

## 📝 Exercícios Práticos

### Exercício 1: Sistema de Menus (Iniciante)
Implemente sistema de menus e itens de menu.

**Requisitos**:
- Menu pode conter itens de menu e submenus
- Item de menu executa ação quando selecionado
- Menu imprime estrutura hierárquica com indentação
- Suporte a ícones (emoji) para menus e itens

**Template**:
```java
interface ComponenteMenu {
    void exibir(int nivel);
    void executar();
}

class ItemMenu implements ComponenteMenu {
    private String nome;
    private Runnable acao;
    // Implemente
}

class Menu implements ComponenteMenu {
    private String nome;
    private List<ComponenteMenu> itens = new ArrayList<>();
    // Implemente adicionar, remover, exibir recursivo
}

// Teste
Menu principal = new Menu("📁 Principal");
principal.adicionar(new ItemMenu("📄 Novo", () -> System.out.println("Criando...")));

Menu arquivo = new Menu("📁 Arquivo");
arquivo.adicionar(new ItemMenu("💾 Salvar", () -> System.out.println("Salvando...")));
arquivo.adicionar(new ItemMenu("📂 Abrir", () -> System.out.println("Abrindo...")));
principal.adicionar(arquivo);
```

**Desafio extra**: Adicione atalhos de teclado e habilitação/desabilitação de itens.

### Exercício 2: Calculadora de Expressões (Intermediário)
Crie avaliador de expressões matemáticas usando Composite.

**Requisitos**:
- Números são Leaf
- Operações (+, -, *, /) são Composite com 2 filhos
- Suporte a expressões aninhadas: `(2 + 3) * (4 - 1)`
- Método `avaliar()` calcula resultado
- Método `toString()` exibe expressão com parênteses

**Template**:
```java
interface Expressao {
    double avaliar();
    String paraString();
}

class Numero implements Expressao {
    private double valor;
    // Implemente
}

abstract class OperacaoBinaria implements Expressao {
    protected Expressao esquerda, direita;
    
    public OperacaoBinaria(Expressao esq, Expressao dir) {
        this.esquerda = esq;
        this.direita = dir;
    }
}

class Soma extends OperacaoBinaria {
    public double avaliar() {
        return esquerda.avaliar() + direita.avaliar();
    }
    // Implemente toString
}

// Implemente Subtracao, Multiplicacao, Divisao
```

**Teste**:
```java
// (10 + 5) * 2
Expressao expr = new Multiplicacao(
    new Soma(new Numero(10), new Numero(5)),
    new Numero(2)
);
System.out.println(expr.paraString());  // "((10.0 + 5.0) * 2.0)"
System.out.println(expr.avaliar());     // 30.0
```

**Desafio extra**: Adicione operações unárias (negação, raiz quadrada) e funções (sin, cos).

### Exercício 3: Estrutura Organizacional (Intermediário)
Modele empresa com departamentos e funcionários.

**Requisitos**:
- Funcionário é Leaf com nome, cargo, salário
- Departamento é Composite com nome e lista de membros
- Calcular custo total (soma salários recursivamente)
- Contar total de funcionários
- Buscar funcionário por nome
- Imprimir organograma com hierarquia

**Template**:
```java
interface ComponenteOrganizacional {
    String getNome();
    double calcularCusto();
    int contarFuncionarios();
    void imprimir(int nivel);
    ComponenteOrganizacional buscar(String nome);
}

class Funcionario implements ComponenteOrganizacional {
    private String nome;
    private String cargo;
    private double salario;
    // Implemente todos os métodos
}

class Departamento implements ComponenteOrganizacional {
    private String nome;
    private List<ComponenteOrganizacional> membros = new ArrayList<>();
    private ComponenteOrganizacional gerente;  // Opcional
    // Implemente
}
```

**Desafio extra**: Adicione cálculo de nível hierárquico e exportação para formato JSON/XML.

### Exercício 4: Sistema de Arquivos com Permissões (Avançado)
Estenda exemplo de sistema de arquivos com controle de acesso.

**Requisitos**:
- Arquivo com conteúdo, tamanho, permissões (leitura, escrita, execução)
- Pasta com permissões herdadas ou próprias
- Verificar permissões recursivamente
- Calcular tamanho total considerando permissões (arquivos sem leitura = 0)
- Listar apenas arquivos com permissão de leitura
- Suporte a links simbólicos (referência a outro elemento)

**Template**:
```java
enum Permissao { LEITURA, ESCRITA, EXECUCAO }

interface ElementoSistema {
    String getNome();
    long getTamanho(Usuario usuario);
    boolean temPermissao(Usuario usuario, Permissao p);
    void listar(Usuario usuario, int nivel);
}

class Usuario {
    private String nome;
    private Set<String> grupos;
    // Implemente
}

class Arquivo implements ElementoSistema {
    private String proprietario;
    private Set<Permissao> permissoes;
    // Implemente verificação de permissão
}

class LinkSimbolico implements ElementoSistema {
    private ElementoSistema alvo;
    // Delega para alvo, cuidado com ciclos!
}
```

### Exercício 5: Renderizador de Interface Gráfica (Avançado)
Crie sistema de componentes gráficos simples.

**Requisitos**:
- Componentes básicos: Botão, Label, TextField (Leaf)
- Containers: Painel, Janela (Composite)
- Cada componente tem posição (x, y) e tamanho (largura, altura)
- Método `render()` desenha componente (print simulado)
- Container posiciona filhos automaticamente (layout)
- Suporte a eventos (click) que se propagam

**Template**:
```java
interface Componente {
    void render(Graphics g);
    void aoClicar(int x, int y);
    Rectangle getBounds();
}

class Botao implements Componente {
    private String texto;
    private int x, y, largura, altura;
    private Consumer<MouseEvent> callback;
    // Implemente
}

abstract class Container implements Componente {
    protected List<Componente> filhos = new ArrayList<>();
    
    public void adicionar(Componente c) {
        filhos.add(c);
        aplicarLayout();  // Recalcula posições
    }
    
    protected abstract void aplicarLayout();
}

class PainelFluxo extends Container {
    protected void aplicarLayout() {
        // Organiza componentes em linha
    }
}
```

**Desafio extra**: Implemente diferentes layouts (Grid, Border), z-order para sobreposição, e temas visuais.

## 🎓 Análise Acadêmica

### Princípios SOLID Aplicados

#### 1. Single Responsibility Principle (SRP)
- **Leaf**: Responsável apenas por comportamento individual
- **Composite**: Responsável por gerenciar filhos e delegar operações
- **Separação clara**: Lógica de negócio vs estrutura hierárquica

#### 2. Open/Closed Principle (OCP)
- **Aberto**: Fácil adicionar novos tipos de Component (extensão)
- **Fechado**: Código cliente não muda ao adicionar novos componentes
```java
// Novo tipo não afeta código existente
class PastaCompactada extends Pasta {
    // Nova funcionalidade
}
```

#### 3. Liskov Substitution Principle (LSP)
- Qualquer Component pode substituir outro sem quebrar funcionalidade
- Leaf e Composite são substituíveis onde Component é esperado
- Cliente não precisa saber qual tipo está usando

#### 4. Interface Segregation Principle (ISP)
- **Trade-off**: Composite pode violar ISP se forçar Leaf a ter métodos de gestão
- **Solução**: Interface mínima + métodos opcionais com comportamento padrão
```java
interface Component {
    void operacao();  // Essencial
    default void adicionar(Component c) {
        throw new UnsupportedOperationException();
    }
}
```

#### 5. Dependency Inversion Principle (DIP)
- Cliente depende de abstração (Component), não de implementações concretas
- Composite também depende de Component, não de classes concretas
```java
public class Composite implements Component {
    private List<Component> filhos;  // Depende de abstração
}
```

### Complexidade Algorítmica

**Operações Básicas** (n = número total de nós):

| Operação | Complexidade | Observação |
|----------|--------------|------------|
| Adicionar filho | O(1) | Adiciona ao final da lista |
| Remover filho | O(k) | k = número de filhos diretos |
| Buscar por nome | O(n) | DFS/BFS em toda árvore |
| Operação recursiva | O(n) | Visita cada nó uma vez |
| Calcular altura | O(n) | Percorre árvore inteira |

**Espaço**:
- O(n) para armazenar n componentes
- O(h) pilha de recursão, onde h = altura da árvore

### Comparação com Outras Estruturas

| Estrutura | Uniformidade | Flexibilidade | Complexidade |
|-----------|--------------|---------------|--------------|
| Composite Pattern | ✓ Alta | ✓ Alta | Média |
| Árvore Tradicional | ✗ Baixa | Média | Baixa |
| Array/List | ✗ N/A | ✓ Alta | Baixa |
| Graph | ✗ Baixa | ✓✓ Muito Alta | Alta |

### Design Trade-offs

#### Transparência vs Segurança
```java
// Transparência: Interface uniforme, mas menos seguro
interface Component {
    void adicionar(Component c);  // Leaf pode lançar exceção
}

// Segurança: Type-safe, mas menos transparente  
interface Leaf extends Component { }
interface Composite extends Component {
    void adicionar(Component c);  // Só Composite tem
}
```

**Conclusão**: Java favorece transparência com exceções em runtime, pois:
- Cliente mais simples (não precisa cast)
- Erros são detectados (exceção vs silêncio)
- Alinhado com filosofia Java de detectar erros cedo

### Padrões Relacionados e Sinergia

#### Composite + Iterator
Percorrer hierarquia sem expor estrutura interna:
```java
public Iterator<Component> iterator() {
    return new CompositeIterator(this);
}

// Cliente usa iterator uniformemente
for (Component c : composite) {
    c.operacao();
}
```

#### Composite + Visitor
Adicionar operações sem modificar hierarquia:
```java
interface Visitor {
    void visitLeaf(Leaf l);
    void visitComposite(Composite c);
}

// Nova operação: export to XML
class XMLExporter implements Visitor {
    public void visitComposite(Composite c) {
        // Gera XML recursivamente
    }
}
```

#### Composite + Chain of Responsibility
Eventos se propagam pela hierarquia:
```java
public boolean handleEvent(Event e) {
    if (canHandle(e)) {
        processEvent(e);
        return true;
    }
    // Propaga para pai ou filhos
    for (Component filho : filhos) {
        if (filho.handleEvent(e)) return true;
    }
    return false;
}
```

## 🔍 Detecção de Code Smells

**Você precisa de Composite quando vê**:

1. **Código cliente com if/else por tipo**:
```java
// Smell: Cliente distingue tipos
if (elemento instanceof Arquivo) {
    ((Arquivo)elemento).processar();
} else if (elemento instanceof Pasta) {
    for (Elemento e : ((Pasta)elemento).getFilhos()) {
        // processar recursivamente...
    }
}

// Solução: Interface uniforme
elemento.processar();  // Funciona para ambos
```

2. **Explosão de métodos de processamento**:
```java
// Smell: Métodos separados para cada tipo
void processarArquivo(Arquivo a) { ... }
void processarPasta(Pasta p) {
    for (Arquivo a : p.getArquivos()) processarArquivo(a);
    for (Pasta p : p.getPastas()) processarPasta(p);
}

// Solução: Método polimórfico
void processar() { ... }  // Em cada classe
```

3. **Duplicação de código de navegação**:
```java
// Smell: Lógica de navegação duplicada em todo cliente
for (Pasta p : pastas) {
    for (Arquivo a : p.getArquivos()) {
        // ... operação ...
    }
    for (Pasta sub : p.getSubpastas()) {
        // ... mesma operação recursiva ...
    }
}
```

## 📚 Referências e Leitura Adicional

### Livros
1. **"Design Patterns: Elements of Reusable Object-Oriented Software"** - Gang of Four
   - Capítulo sobre Composite (páginas 163-173)
   - Descrição original do padrão

2. **"Head First Design Patterns"** - Freeman & Freeman
   - Exemplos práticos e visuais
   - Comparações com outros padrões

3. **"Pattern-Oriented Software Architecture"** - Buschmann et al.
   - Composite em contexto arquitetural
   - Trade-offs e variações

### Artigos Online
- [Refactoring Guru - Composite](https://refactoring.guru/design-patterns/composite)
- [SourceMaking - Composite](https://sourcemaking.com/design_patterns/composite)
- [Oracle Java Tutorials - Swing Components](https://docs.oracle.com/javase/tutorial/uiswing/components/index.html)

### Exemplos no Java SE/EE
- `java.awt.Container` e `Component` - Interface gráfica
- `javax.swing.JComponent` - Componentes Swing
- `org.w3c.dom.Node` - DOM XML
- `java.nio.file.FileSystem` - Sistema de arquivos
