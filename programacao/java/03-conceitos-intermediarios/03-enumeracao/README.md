# Enumerações (Enums)

Enumerações (Enums) são tipos especiais de classe introduzidos no Java 5 que representam um grupo fixo e finito de constantes relacionadas. Diferentemente de simples constantes inteiras ou strings, Enums em Java são classes completas que podem ter campos, métodos, construtores e implementar interfaces, proporcionando uma forma robusta e **type-safe** de representar conjuntos finitos de valores bem definidos.

## 🏛️ Enumerações e Programação Orientada a Objetos

### Como Enums Exemplificam os Princípios da POO

Enumerações em Java não são apenas listas de constantes; são classes especiais que demonstram a aplicação sofisticada dos princípios fundamentais da Programação Orientada a Objetos:

#### 1. **Encapsulamento** 🔒

```java
enum StatusPedido {
    PENDENTE("Aguardando processamento", false),
    ENTREGUE("Pedido finalizado", true);
    
    private final String descricao;  // Campo privado
    private final boolean finalizado; // Estado encapsulado
    
    StatusPedido(String descricao, boolean finalizado) {
        this.descricao = descricao;
        this.finalizado = finalizado;
    }
    
    public String getDescricao() { return descricao; }
    public boolean isFinalizado() { return finalizado; }
}
```

O encapsulamento em enums protege o estado interno das constantes, expondo apenas os métodos necessários para interação. Cada constante enum é um **singleton** imutável, garantindo consistência e thread-safety naturalmente.

#### 2. **Herança Implícita** 🎭

Todos os enums estendem implicitamente a classe `java.lang.Enum<E>`, herdando métodos fundamentais:

```java
// Métodos herdados de java.lang.Enum
String name()           // Nome da constante
int ordinal()          // Posição (índice)
String toString()      // Representação textual
int compareTo(E o)     // Comparação por ordinal
```

Embora enums não possam estender outras classes (Java não suporta herança múltipla), eles podem **implementar interfaces**, permitindo polimorfismo e comportamento padronizado:

```java
interface Descritivel {
    String getDescricao();
}

enum Prioridade implements Descritivel {
    BAIXA, MEDIA, ALTA;
    
    @Override
    public String getDescricao() {
        return "Prioridade: " + name();
    }
}
```

#### 3. **Polimorfismo** 🎨

Enums suportam polimorfismo através de métodos abstratos que cada constante deve implementar, permitindo que cada constante tenha comportamento único:

```java
enum Operacao {
    SOMA {
        @Override
        public double calcular(double x, double y) {
            return x + y;
        }
    },
    SUBTRACAO {
        @Override
        public double calcular(double x, double y) {
            return x - y;
        }
    };
    
    public abstract double calcular(double x, double y);
}

// Uso polimórfico
Operacao op = Operacao.SOMA;
double resultado = op.calcular(10, 5); // 15
```

#### 4. **Abstração** 🎭

Enums fornecem abstração de alto nível para conceitos de domínio:

```java
// Abstração de dias da semana
enum DiaSemana {
    DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO;
    
    public boolean isDiaUtil() {
        return this != SABADO && this != DOMINGO;
    }
}

// Uso abstrato - não precisamos saber que internamente é um int
DiaSemana hoje = DiaSemana.QUARTA;
if (hoje.isDiaUtil()) {
    System.out.println("Dia de trabalho!");
}
```

### Por Que Enums São Superiores a Constantes Primitivas?

#### Problema das Constantes Inteiras (Anti-Pattern)

Antes dos enums, era comum usar constantes inteiras para representar conjuntos de valores:

```java
// ❌ Anti-pattern: Constantes inteiras
public class Status {
    public static final int PENDENTE = 0;
    public static final int PROCESSANDO = 1;
    public static final int CONCLUIDO = 2;
}

// Problemas:
int status = 5;              // ❌ Valor inválido aceito
int status = Status.PENDENTE; 
status = 999;                // ❌ Não há type safety
String desc = ???            // ❌ Como obter descrição?
```

#### Solução com Enums

```java
// ✅ Enum type-safe
enum Status {
    PENDENTE("Aguardando", 0),
    PROCESSANDO("Em andamento", 50),
    CONCLUIDO("Finalizado", 100);
    
    private final String descricao;
    private final int progresso;
    
    Status(String descricao, int progresso) {
        this.descricao = descricao;
        this.progresso = progresso;
    }
    
    public String getDescricao() { return descricao; }
    public int getProgresso() { return progresso; }
}

// Vantagens:
Status status = Status.PENDENTE;  // ✅ Type-safe
status = Status.CONCLUIDO;        // ✅ Apenas valores válidos
String desc = status.getDescricao(); // ✅ Dados associados
```

## 🎯 Objetivos

Este módulo tem como objetivos proporcionar compreensão profunda e aplicada de enumerações em Java, capacitando o estudante a:

- **Compreender a natureza das enumerações** como classes especiais que representam conjuntos finitos de constantes, entendendo sua implementação interna como singletons imutáveis e sua relação com a classe `java.lang.Enum<E>`.

- **Dominar type safety com enums** aproveitando a verificação de tipos em tempo de compilação para prevenir erros, eliminando uso de "magic numbers" e strings literais que são propensos a erros de digitação e difíceis de manter.

- **Criar enums com estado e comportamento** implementando campos privados, construtores e métodos para encapsular dados e lógica relacionados às constantes, transformando enums simples em objetos completos com responsabilidades bem definidas.

- **Implementar enums com métodos abstratos** utilizando constant-specific method implementations para permitir que cada constante tenha comportamento único, aplicando polimorfismo de forma elegante e type-safe.

- **Utilizar EnumSet e EnumMap** dominando coleções especializadas que oferecem performance superior através de implementações otimizadas baseadas em bit vectors e arrays indexados, apropriadas para flags, conjuntos de opções e mapeamentos com chaves enum.

- **Aplicar enums em design patterns** reconhecendo e implementando padrões de projeto onde enums são naturalmente adequados, como Strategy Pattern, State Pattern, Singleton Pattern e Factory Pattern.

- **Modelar domínios com enums** identificando conjuntos finitos de valores no domínio do problema e modelando-os com enums de forma que capture regras de negócio, transições de estado válidas e invariantes do sistema.

## 📋 Fundamentos Teóricos

### O Que São Enumerações?

Enumerações são **tipos de dados definidos pelo usuário** que consistem em um conjunto nomeado de constantes relacionadas. Em Java, enums são muito mais do que simples listas de valores - são **classes completas** com capacidades especiais:

#### Características Fundamentais

1. **Conjunto Finito e Conhecido**: O número de valores possíveis é fixo e conhecido em tempo de compilação.

2. **Type Safety**: O compilador garante que apenas valores válidos podem ser atribuídos a variáveis enum.

3. **Singleton por Constante**: Cada constante enum é uma instância única (singleton) da classe enum, garantida pela JVM.

4. **Imutabilidade**: Constantes enum são intrinsecamente imutáveis, todos os campos devem ser finais.

5. **Comparação Eficiente**: Enums podem ser comparados por identidade (==) porque cada constante é um singleton.

6. **Serialização Segura**: A JVM garante que apenas uma instância de cada constante existe, mesmo após serialização/deserialização.

### Anatomia de um Enum

```java
public enum DiaSemana {        // ← Palavra-chave 'enum'
    // Constantes (devem ser declaradas primeiro)
    DOMINGO(false),
    SEGUNDA(true),
    TERCA(true),
    QUARTA(true),
    QUINTA(true),
    SEXTA(true),
    SABADO(false);             // ← Ponto-e-vírgula quando há mais membros
    
    // Campos (geralmente private final)
    private final boolean diaUtil;
    
    // Construtor (sempre private, implícito ou explícito)
    DiaSemana(boolean diaUtil) {
        this.diaUtil = diaUtil;
    }
    
    // Métodos de instância
    public boolean isDiaUtil() {
        return diaUtil;
    }
    
    // Métodos estáticos
    public static int quantosDiasUteis() {
        int count = 0;
        for (DiaSemana dia : values()) {
            if (dia.isDiaUtil()) count++;
        }
        return count;
    }
}
```

### Métodos Implícitos de Enum

Todo enum herda automaticamente estes métodos de `java.lang.Enum<E>`:

| Método | Descrição | Complexidade |
|--------|-----------|--------------|
| `String name()` | Retorna o nome da constante exatamente como declarado | O(1) |
| `int ordinal()` | Retorna a posição da constante (começando em 0) | O(1) |
| `String toString()` | Retorna representação string (por padrão, igual a name()) | O(1) |
| `static E valueOf(String name)` | Converte string para enum (lança IllegalArgumentException se inválido) | O(1)* |
| `static E[] values()` | Retorna array com todas as constantes na ordem de declaração | O(n) |
| `int compareTo(E o)` | Compara pela ordem de declaração (ordinal) | O(1) |
| `boolean equals(Object other)` | Verifica igualdade (equivalente a ==) | O(1) |

*\*values() cria novo array a cada chamada; valueOf() usa mapa interno otimizado*

### Hierarquia e Restrições

```
java.lang.Object
    ↑
java.lang.Enum<E extends Enum<E>>
    ↑
SeuEnum (extends Enum<SeuEnum> implicitamente)
```

**Restrições Importantes:**

- ❌ Enums **não podem** estender outras classes (já estendem Enum)
- ✅ Enums **podem** implementar interfaces
- ❌ Enums **não podem** ser estendidos (são implicitamente final)
- ✅ Enums **podem** ter classes internas (inner classes)
- ❌ Construtores de enum **devem** ser privados (implícito ou explícito)

## 📚 Tipos de Enumerações

### 1. Enum Simples

Representação básica de um conjunto de constantes sem estado adicional:

```java
enum DiaSemana {
    DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO
}

enum StatusPedido {
    PENDENTE, PROCESSANDO, ENVIADO, ENTREGUE, CANCELADO
}
```

**Quando Usar:**
- Conjunto simples de valores sem lógica associada
- Apenas identificadores de tipo
- Alternativa type-safe a strings ou inteiros literais

### 2. Enum com Campos e Construtor

Enums com estado interno associado a cada constante:

```java
enum Planeta {
    MERCURIO(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    TERRA(5.976e+24, 6.37814e6),
    MARTE(6.421e+23, 3.3972e6);
    
    private final double massa;  // em kg
    private final double raio;   // em metros
    
    Planeta(double massa, double raio) {
        this.massa = massa;
        this.raio = raio;
    }
    
    public double getMassa() { return massa; }
    public double getRaio() { return raio; }
    
    public double gravidadeSuperficial() {
        double G = 6.67300E-11;
        return G * massa / (raio * raio);
    }
}
```

**Características:**
- Cada constante é inicializada com valores específicos
- Construtor é chamado uma única vez por constante
- Campos devem ser final para garantir imutabilidade
- Permite associar múltiplos valores a cada constante

### 3. Enum com Métodos Concretos

Enums com comportamento compartilhado por todas as constantes:

```java
enum Moeda {
    REAL("R$", "BRL", 1.00),
    DOLAR("$", "USD", 5.20),
    EURO("€", "EUR", 6.15);
    
    private final String simbolo;
    private final String codigo;
    private final double taxaConversao;
    
    Moeda(String simbolo, String codigo, double taxaConversao) {
        this.simbolo = simbolo;
        this.codigo = codigo;
        this.taxaConversao = taxaConversao;
    }
    
    // Método concreto compartilhado
    public double converterPara(double valor, Moeda destino) {
        double valorEmReais = valor * this.taxaConversao;
        return valorEmReais / destino.taxaConversao;
    }
    
    public String formatar(double valor) {
        return String.format("%s %.2f", simbolo, valor);
    }
}

// Uso
double valorDolar = 100.0;
double valorEuro = Moeda.DOLAR.converterPara(valorDolar, Moeda.EURO);
```

### 4. Enum com Métodos Abstratos (Constant-Specific Method Implementation)

Cada constante implementa seu próprio comportamento único:

```java
enum Operacao {
    SOMA("+") {
        @Override
        public double calcular(double x, double y) {
            return x + y;
        }
    },
    SUBTRACAO("-") {
        @Override
        public double calcular(double x, double y) {
            return x - y;
        }
    },
    MULTIPLICACAO("*") {
        @Override
        public double calcular(double x, double y) {
            return x * y;
        }
    },
    DIVISAO("/") {
        @Override
        public double calcular(double x, double y) {
            if (y == 0) throw new ArithmeticException("Divisão por zero");
            return x / y;
        }
    };
    
    private final String simbolo;
    
    Operacao(String simbolo) {
        this.simbolo = simbolo;
    }
    
    public String getSimbolo() {
        return simbolo;
    }
    
    // Método abstrato - cada constante deve implementar
    public abstract double calcular(double x, double y);
}

// Uso polimórfico
Operacao op = Operacao.MULTIPLICACAO;
double resultado = op.calcular(5, 3); // 15
```

**Vantagens:**
- Elimina switch statements
- Comportamento específico encapsulado na constante
- Type-safe e extensível
- Impossível esquecer de implementar para nova constante

### 5. Enum Implementando Interface

Enums podem implementar interfaces para padronizar comportamento:

```java
interface Descritivel {
    String getDescricao();
    String getCategorias();
}

enum TipoProduto implements Descritivel {
    ELETRONICO("Eletrônicos e Tecnologia"),
    LIVRO("Livros e Mídia"),
    ROUPA("Vestuário e Moda"),
    ALIMENTO("Alimentos e Bebidas");
    
    private final String categoria;
    
    TipoProduto(String categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public String getDescricao() {
        return "Produto do tipo: " + name();
    }
    
    @Override
    public String getCategorias() {
        return categoria;
    }
}
```

## 🔧 EnumSet e EnumMap

### EnumSet: Coleções Eficientes para Enums

`EnumSet` é uma implementação especializada de `Set` otimizada para tipos enum. Internamente, usa um **bit vector** quando o enum tem até 64 constantes, oferecendo:

- **Performance Excepcional**: Operações em O(1) verdadeiro
- **Uso Mínimo de Memória**: Um bit por constante
- **Iteração na Ordem de Declaração**: Previsível e ordenada

#### Criação de EnumSet

```java
enum Permissao {
    LER, ESCREVER, EXECUTAR, DELETAR
}

// EnumSet vazio
EnumSet<Permissao> vazio = EnumSet.noneOf(Permissao.class);

// EnumSet com todos os elementos
EnumSet<Permissao> todas = EnumSet.allOf(Permissao.class);

// EnumSet com elementos específicos
EnumSet<Permissao> basicas = EnumSet.of(Permissao.LER, Permissao.ESCREVER);

// EnumSet com range
EnumSet<Permissao> range = EnumSet.range(Permissao.LER, Permissao.EXECUTAR);

// EnumSet complementar
EnumSet<Permissao> avancadas = EnumSet.complementOf(basicas);
```

#### Operações com EnumSet

```java
EnumSet<Permissao> admin = EnumSet.allOf(Permissao.class);
EnumSet<Permissao> usuario = EnumSet.of(Permissao.LER, Permissao.ESCREVER);

// União
EnumSet<Permissao> uniao = EnumSet.copyOf(admin);
uniao.addAll(usuario);

// Interseção
EnumSet<Permissao> comum = EnumSet.copyOf(admin);
comum.retainAll(usuario);

// Diferença
EnumSet<Permissao> exclusivas = EnumSet.copyOf(admin);
exclusivas.removeAll(usuario);

// Verificação
boolean pode = admin.contains(Permissao.DELETAR);
boolean temTodas = admin.containsAll(usuario);
```

#### Casos de Uso de EnumSet

1. **Flags e Opções**: Representar conjunto de flags booleanas

```java
enum EstiloProduto {
    NOVO, PROMOCAO, DESTAQUE, FRETE_GRATIS, EXCLUSIVO
}

class Produto {
    private EnumSet<EstiloProduto> estilos = EnumSet.noneOf(EstiloProduto.class);
    
    public void adicionarEstilo(EstiloProduto estilo) {
        estilos.add(estilo);
    }
    
    public boolean temEstilo(EstiloProduto estilo) {
        return estilos.contains(estilo);
    }
}
```

2. **Permissões e Controle de Acesso**

```java
class Usuario {
    private EnumSet<Permissao> permissoes;
    
    public boolean podeExecutar(Permissao permissao) {
        return permissoes.contains(permissao);
    }
    
    public void concederPermissoes(EnumSet<Permissao> novas) {
        permissoes.addAll(novas);
    }
}
```

### EnumMap: Mapeamentos Eficientes com Chaves Enum

`EnumMap` é uma implementação especializada de `Map` otimizada para chaves enum. Internamente, usa um **array indexado pelo ordinal** do enum:

- **Performance Superior a HashMap**: Acesso e inserção em O(1) real
- **Uso Eficiente de Memória**: Array compacto
- **Iteração Ordenada**: Na ordem de declaração do enum
- **Type Safety**: Chaves garantidamente válidas

#### Criação e Uso de EnumMap

```java
enum DiaSemana {
    DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO
}

// Criar EnumMap
EnumMap<DiaSemana, String> horarios = new EnumMap<>(DiaSemana.class);

// Adicionar elementos
horarios.put(DiaSemana.SEGUNDA, "8h - 18h");
horarios.put(DiaSemana.TERCA, "8h - 18h");
horarios.put(DiaSemana.SABADO, "9h - 13h");

// Acessar valores
String horarioSegunda = horarios.get(DiaSemana.SEGUNDA);

// Iterar
for (Map.Entry<DiaSemana, String> entry : horarios.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

#### EnumMap com Valores Complexos

```java
enum Departamento {
    TI, RH, VENDAS, MARKETING
}

// EnumMap com List como valor
EnumMap<Departamento, List<String>> funcionarios = 
    new EnumMap<>(Departamento.class);

funcionarios.put(Departamento.TI, 
    Arrays.asList("João", "Maria", "Pedro"));
funcionarios.put(Departamento.RH, 
    Arrays.asList("Ana", "Carlos"));

// EnumMap com objetos customizados
class ConfiguracaoDepartamento {
    int orcamento;
    String gerente;
    // ...
}

EnumMap<Departamento, ConfiguracaoDepartamento> configs = 
    new EnumMap<>(Departamento.class);
```

#### Comparação de Performance

```
Operação            | EnumSet vs HashSet | EnumMap vs HashMap |
--------------------|--------------------|--------------------|
Inserção            | 3-5x mais rápido   | 2-3x mais rápido   |
Busca (contains/get)| 4-6x mais rápido   | 2-4x mais rápido   |
Iteração            | 2-3x mais rápido   | 1.5-2x mais rápido |
Uso de Memória      | 10-20x menor       | 3-5x menor         |
```

*Medições baseadas em benchmarks com enums de 10-50 constantes*

## 💡 Padrões de Design com Enums

### 1. Strategy Pattern

Enum com métodos abstratos implementa naturalmente o Strategy Pattern:

```java
enum TipoDesconto {
    NENHUM {
        @Override
        public double aplicar(double valor) {
            return valor;
        }
    },
    PERCENTUAL_10 {
        @Override
        public double aplicar(double valor) {
            return valor * 0.90;
        }
    },
    VALOR_FIXO {
        @Override
        public double aplicar(double valor) {
            return Math.max(0, valor - 50);
        }
    };
    
    public abstract double aplicar(double valor);
}
```

### 2. State Pattern

Enums modelam estados com transições válidas:

```java
enum EstadoConexao {
    DESCONECTADO {
        @Override
        public EstadoConexao conectar() {
            return CONECTANDO;
        }
    },
    CONECTANDO {
        @Override
        public EstadoConexao conectar() {
            return CONECTADO;
        }
    },
    CONECTADO {
        @Override
        public EstadoConexao desconectar() {
            return DESCONECTADO;
        }
    };
    
    public EstadoConexao conectar() {
        throw new IllegalStateException("Não pode conectar");
    }
    
    public EstadoConexao desconectar() {
        throw new IllegalStateException("Não pode desconectar");
    }
}
```

### 3. Singleton Pattern

Enum é a forma mais simples e segura de implementar Singleton:

```java
enum Configuracao {
    INSTANCE;
    
    private Properties props = new Properties();
    
    public void carregar(String arquivo) {
        // carregar configurações
    }
    
    public String obter(String chave) {
        return props.getProperty(chave);
    }
}

// Uso
Configuracao.INSTANCE.carregar("config.properties");
String valor = Configuracao.INSTANCE.obter("chave");
```

**Vantagens sobre Singleton tradicional:**
- Thread-safe garantido pela JVM
- Proteção contra reflexão
- Proteção contra serialização
- Código mais simples e conciso

### 4. Factory Pattern

Enums podem encapsular lógica de criação:

```java
enum TipoVeiculo {
    CARRO {
        @Override
        public Veiculo criar(String modelo) {
            return new Carro(modelo);
        }
    },
    MOTO {
        @Override
        public Veiculo criar(String modelo) {
            return new Moto(modelo);
        }
    };
    
    public abstract Veiculo criar(String modelo);
}
```

## 🎓 Boas Práticas e Recomendações

### Quando Usar Enums

✅ **Use enums quando:**

1. **Conjunto Finito e Conhecido**: Você tem um conjunto fixo de valores relacionados conhecidos em tempo de compilação
   ```java
   enum Mes { JAN, FEV, MAR, ABR, MAI, JUN, JUL, AGO, SET, OUT, NOV, DEZ }
   ```

2. **Type Safety é Importante**: Você quer prevenir erros de tipo em tempo de compilação
   ```java
   // Ao invés de: void setStatus(String status)
   void setStatus(StatusPedido status)  // ✅ Type-safe
   ```

3. **Switch Statements**: Você tem lógica condicional baseada em valores discretos
   ```java
   switch (diaSemana) {
       case SABADO, DOMINGO -> "Fim de semana";
       default -> "Dia útil";
   }
   ```

4. **Comportamento Associado**: Você precisa associar comportamento a valores
   ```java
   enum Operacao {
       SOMA { double calcular(double a, double b) { return a + b; } }
   }
   ```

❌ **Não use enums quando:**

1. **Conjunto Variável**: Valores podem mudar em runtime ou vir de fonte externa (BD, arquivo)
2. **Muitos Valores**: Conjunto muito grande (centenas) de valores
3. **Hierarquia Complexa**: Precisa de hierarquia profunda de tipos (use classes)

### Práticas Recomendadas

#### 1. Campos Devem Ser Final

```java
// ✅ Correto - imutabilidade garantida
enum Status {
    ATIVO("Funcionando", true);
    
    private final String descricao;  // ✅ final
    private final boolean ativo;     // ✅ final
    
    Status(String descricao, boolean ativo) {
        this.descricao = descricao;
        this.ativo = ativo;
    }
}
```

#### 2. Use EnumSet Para Coleções de Enums

```java
// ❌ Evitar
Set<Permissao> permissoes = new HashSet<>();

// ✅ Preferir
EnumSet<Permissao> permissoes = EnumSet.noneOf(Permissao.class);
```

#### 3. Use EnumMap Para Mapeamentos com Chave Enum

```java
// ❌ Evitar
Map<DiaSemana, String> horarios = new HashMap<>();

// ✅ Preferir
EnumMap<DiaSemana, String> horarios = new EnumMap<>(DiaSemana.class);
```

#### 4. Override toString() Quando Necessário

```java
enum Status {
    EM_ANDAMENTO("Em Andamento"),
    CONCLUIDO("Concluído");
    
    private final String descricao;
    
    Status(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return descricao;  // ✅ Exibe descrição ao invés de "EM_ANDAMENTO"
    }
}
```

#### 5. Implemente Métodos Estáticos Utilitários

```java
enum Status {
    PENDENTE, ATIVO, INATIVO, CANCELADO;
    
    // ✅ Método utilitário estático
    public static Status fromString(String texto) {
        try {
            return valueOf(texto.toUpperCase());
        } catch (IllegalArgumentException e) {
            return PENDENTE; // valor padrão
        }
    }
    
    public static List<Status> getAtivos() {
        return Arrays.asList(PENDENTE, ATIVO);
    }
}
```

#### 6. Use Constant-Specific Methods Para Comportamento Único

```java
// ❌ Evitar switch
enum TipoArquivo {
    PDF, DOCX, XLSX;
    
    public String getExtensao() {
        switch(this) {
            case PDF: return ".pdf";
            case DOCX: return ".docx";
            case XLSX: return ".xlsx";
            default: return "";
        }
    }
}

// ✅ Preferir constant-specific method
enum TipoArquivo {
    PDF(".pdf", "application/pdf"),
    DOCX(".docx", "application/vnd.openxmlformats"),
    XLSX(".xlsx", "application/vnd.ms-excel");
    
    private final String extensao;
    private final String mimeType;
    
    TipoArquivo(String extensao, String mimeType) {
        this.extensao = extensao;
        this.mimeType = mimeType;
    }
    
    public String getExtensao() { return extensao; }
    public String getMimeType() { return mimeType; }
}
```

## 🖥️ Exemplos Práticos

Este diretório contém exemplos progressivos e completos de enumerações:

### [ExemploEnumBasico.java](ExemploEnumBasico.java)
Demonstra conceitos fundamentais de enumerações:
- Declaração e uso básico de enums
- Métodos estáticos: `values()`, `valueOf()`, `ordinal()`, `name()`
- Uso com switch statements (tradicional e moderno)
- Comparação de enums (==, equals, compareTo)
- Iteração sobre constantes enum
- Type safety e prevenção de erros

### [ExemploEnumComMetodos.java](ExemploEnumComMetodos.java)
Demonstra enums com comportamento:
- Enum com métodos abstratos (Operação matemática)
- Constant-specific method implementation
- Enums com cálculos complexos (Planetas)
- Enums com métodos de formatação (NívelLog)
- Aplicação prática: calculadora polimórfica

### [ExemploEnumComConstrutores.java](ExemploEnumComConstrutores.java)
Demonstra enums com estado interno:
- Enums com múltiplos campos (TamanhoRoupa)
- Construtores parametrizados
- Enums com lógica de negócio (FormaPagamento)
- Métodos estáticos utilitários
- Validação e cálculos com dados encapsulados

### [ExemploEnumSetEnumMap.java](ExemploEnumSetEnumMap.java)
Demonstra coleções especializadas para enums:
- Operações com EnumSet (criação, união, interseção, diferença)
- EnumMap com diferentes tipos de valores
- Sistema de permissões com EnumSet e EnumMap
- Comparação de performance: EnumSet vs HashSet, EnumMap vs HashMap
- Análise de complexidade e uso de memória

### [SistemaGerenciamentoPedidos.java](SistemaGerenciamentoPedidos.java)
Exemplo completo e integrado aplicando todos os conceitos:
- Sistema real de gerenciamento de pedidos
- Status com transições válidas (State Pattern)
- Categorias com cálculos de margem de lucro
- Prioridades de entrega com custos
- EnumMap para estatísticas
- Validação de transições de estado
- Histórico de mudanças
- Relatórios consolidados

## 📊 Complexidade e Performance

### Operações Comuns

| Operação | Complexidade | Observação |
|----------|-------------|------------|
| `enum.name()` | O(1) | Acesso direto ao campo |
| `enum.ordinal()` | O(1) | Acesso direto ao campo |
| `enum == enum` | O(1) | Comparação por identidade |
| `valueOf(String)` | O(1) | Usa mapa interno otimizado |
| `values()` | O(n) | Cria novo array (evite em loops) |
| `EnumSet.contains()` | O(1) | Operação bit a bit |
| `EnumMap.get()` | O(1) | Acesso por array indexado |

### Otimizações

```java
// ❌ Ineficiente - values() chamado múltiplas vezes
for (int i = 0; i < 1000; i++) {
    for (Status s : Status.values()) {  // O(n) cada iteração
        // processo
    }
}

// ✅ Eficiente - valores em cache
Status[] valores = Status.values();
for (int i = 0; i < 1000; i++) {
    for (Status s : valores) {
        // processo
    }
}

// ✅ Melhor ainda - EnumSet para iteração frequente
EnumSet<Status> statusAtivos = EnumSet.of(Status.ATIVO, Status.PENDENTE);
for (Status s : statusAtivos) {  // Muito eficiente
    // processo
}
```

## 🔍 Internals: Como Enums Funcionam

### Compilação de Enums

Quando você escreve:

```java
enum Cor {
    VERMELHO, VERDE, AZUL
}
```

O compilador gera algo equivalente a:

```java
public final class Cor extends Enum<Cor> {
    public static final Cor VERMELHO = new Cor("VERMELHO", 0);
    public static final Cor VERDE = new Cor("VERDE", 1);
    public static final Cor AZUL = new Cor("AZUL", 2);
    
    private static final Cor[] $VALUES = {VERMELHO, VERDE, AZUL};
    
    private Cor(String name, int ordinal) {
        super(name, ordinal);
    }
    
    public static Cor[] values() {
        return $VALUES.clone();
    }
    
    public static Cor valueOf(String name) {
        return Enum.valueOf(Cor.class, name);
    }
}
```

### Garantias da JVM

1. **Singleton**: Apenas uma instância de cada constante existe na JVM
2. **Thread-Safe**: Inicialização é thread-safe por natureza
3. **Serialização**: Após deserialização, referências são mantidas corretamente
4. **Reflexão**: Não é possível criar novas instâncias via reflexão

## 🎯 Exercícios Propostos

### Nível Básico

1. **Sistema de Notas**: Crie um enum `Conceito` (A, B, C, D, F) com método que retorna se foi aprovado (>= C).

2. **Meses do Ano**: Crie enum `Mes` com número de dias e método para verificar se tem 31 dias.

3. **Prioridades**: Enum `Prioridade` (BAIXA, MEDIA, ALTA, URGENTE) com comparação de importância.

### Nível Intermediário

4. **Conversor de Unidades**: Enum com METRO, QUILOMETRO, MILHA com métodos de conversão entre unidades.

5. **Sistema de Pagamento**: Enum com diferentes formas de pagamento, taxas e validação de valores.

6. **Máquina de Estados**: Implemente enum representando estados de um pedido com transições válidas.

### Nível Avançado

7. **Sistema de Permissões Completo**: Use EnumSet e EnumMap para criar sistema de roles e permissões hierárquico.

8. **Processador de Comandos**: Enum que processa diferentes tipos de comandos com Strategy Pattern.

9. **Sistema de Workflows**: Modele workflow complexo com estados, transições condicionais e validações.

## 📚 Recursos Adicionais

### Documentação Oficial

- **[Enum Types Tutorial](https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html)**: Tutorial oficial da Oracle sobre enums
- **[Enum Javadoc](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Enum.html)**: Documentação da classe java.lang.Enum
- **[EnumSet Javadoc](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/EnumSet.html)**: Documentação do EnumSet
- **[EnumMap Javadoc](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/EnumMap.html)**: Documentação do EnumMap

### Livros Recomendados

- **Effective Java (Joshua Bloch)** - Items 34-38: Capítulo dedicado a enums
  - Item 34: Use enums instead of int constants
  - Item 35: Use instance fields instead of ordinals
  - Item 36: Use EnumSet instead of bit fields
  - Item 37: Use EnumMap instead of ordinal indexing
  - Item 38: Emulate extensible enums with interfaces

### Artigos e Tutoriais

- **[Baeldung - A Guide to Java Enums](https://www.baeldung.com/a-guide-to-java-enums)**: Tutorial completo com exemplos práticos
- **[Java Enum Best Practices](https://www.baeldung.com/java-enum-simple)**: Boas práticas e padrões com enums

## 💭 Conclusão

Enumerações em Java são muito mais poderosas do que simples listas de constantes. Como **classes completas**, elas encapsulam dados e comportamento, oferecem **type safety** rigorosa e permitem implementação elegante de diversos **padrões de design**. 

O domínio de enumerações, incluindo suas formas avançadas com métodos abstratos e o uso de coleções especializadas (EnumSet e EnumMap), é essencial para escrever código Java profissional, type-safe e performático. Enums bem projetados tornam o código mais legível, manutenível e menos propenso a erros, representando conjuntos finitos de valores de forma que captura naturalmente regras de negócio e invariantes do domínio.

---

**Próximo**: [Annotations](../04-annotations/) | **Anterior**: [Generics](../02-generics/) | **Início**: [Conceitos Intermediários](../README.md)
