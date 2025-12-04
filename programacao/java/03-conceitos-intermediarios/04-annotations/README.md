# Annotations em Java

Annotations (anotações) são uma forma de metadados introduzida no Java 5 (JDK 1.5) que permite adicionar informações declarativas ao código-fonte. Estas informações podem ser processadas em tempo de compilação por ferramentas de processamento de annotations (annotation processors) ou em tempo de execução através de reflection, permitindo configuração declarativa, validação automática, geração de código e outros recursos poderosos que transformaram fundamentalmente a forma como desenvolvemos aplicações Java.

## 🎯 O Que São Annotations?

Annotations são essencialmente metadados - dados sobre dados. Elas fornecem informações sobre o programa que não fazem parte da lógica do programa em si. Annotations não têm efeito direto na operação do código que anotam, mas podem afetar a forma como o programa é tratado por ferramentas e bibliotecas.

### Características Fundamentais

**Metadados Declarativos**: Annotations permitem expressar informações de configuração e metadados diretamente no código-fonte, eliminando a necessidade de arquivos de configuração externos (XML, properties) em muitos casos. Esta abordagem torna o código mais auto-contido e facilita a manutenção.

**Múltiplos Níveis de Processamento**: Annotations podem ser processadas em diferentes momentos:
- **Tempo de Compilação**: Annotation processors podem validar código, gerar código adicional ou produzir arquivos de configuração durante a compilação
- **Tempo de Deploy**: Ferramentas de build e deploy podem ler annotations para configurar a aplicação
- **Tempo de Execução**: Através de reflection, o código pode inspecionar annotations e modificar seu comportamento dinamicamente

**Type-Safe**: Diferentemente de comentários ou configurações XML, annotations são verificadas pelo compilador, proporcionando segurança de tipos e detecção precoce de erros.

## 📚 Conceitos Fundamentais

### Anatomia de uma Annotation

Uma annotation é declarada usando a palavra-chave `@interface`, similar a uma interface, mas com sintaxe específica:

```java
@interface MinhaAnnotation {
    String value();
    int prioridade() default 1;
}
```

**Elementos de Annotation**: São similares a métodos abstratos de interfaces, mas têm características especiais:
- Não podem ter parâmetros
- Não podem ter cláusulas throws
- Tipos de retorno são restritos: primitivos, String, Class, enum, annotation ou arrays destes tipos
- Podem ter valores padrão usando a palavra-chave `default`

**Sintaxe de Uso**: Annotations são aplicadas usando o símbolo `@` seguido do nome da annotation:

```java
@MinhaAnnotation(value = "importante", prioridade = 5)
public class MinhaClasse {
    // ...
}
```

### Retention Policies (Políticas de Retenção)

A meta-annotation `@Retention` especifica por quanto tempo as informações da annotation devem ser retidas. Existem três políticas de retenção:

#### SOURCE (RetentionPolicy.SOURCE)
```java
@Retention(RetentionPolicy.SOURCE)
public @interface GeradoPor {
    String ferramenta();
}
```

**Características**:
- Annotations são descartadas pelo compilador
- Não aparecem no bytecode (.class files)
- Disponíveis apenas no código-fonte

**Casos de Uso**:
- Ferramentas de análise de código-fonte
- Geradores de código que processam código-fonte
- IDEs que fornecem hints visuais
- Documentação e comentários estruturados

**Exemplo Prático**: `@SuppressWarnings` é retida apenas no código-fonte, pois o compilador a processa e não há necessidade de mantê-la no bytecode.

#### CLASS (RetentionPolicy.CLASS) - PADRÃO
```java
@Retention(RetentionPolicy.CLASS)
public @interface AnalisadoEmBytecode {
    String analisador();
}
```

**Características**:
- Annotations são mantidas no arquivo .class
- NÃO estão disponíveis em runtime via reflection
- Esta é a retenção padrão se @Retention não for especificada

**Casos de Uso**:
- Ferramentas de análise de bytecode
- Frameworks que processam bytecode durante o class loading
- Instrumentação de código em tempo de carregamento
- Otimizações específicas da JVM

**Exemplo Prático**: Annotations usadas por ferramentas como FindBugs ou checkers de null-safety que analisam bytecode.

#### RUNTIME (RetentionPolicy.RUNTIME)
```java
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuracao {
    String servidor();
    int porta() default 8080;
}
```

**Características**:
- Annotations são mantidas no arquivo .class
- Estão disponíveis em runtime via reflection
- Podem influenciar o comportamento do programa em execução

**Casos de Uso**:
- Frameworks de injeção de dependências (Spring, CDI)
- Mapeamento objeto-relacional (JPA/Hibernate)
- Configuração de serviços web (JAX-RS, Spring MVC)
- Frameworks de testes (JUnit, TestNG)
- Validação de dados (Bean Validation)

**Exemplo Prático**: `@Override`, embora pareça ser RUNTIME, é na verdade SOURCE. Já `@Entity` do JPA é RUNTIME pois frameworks precisam inspecioná-la durante a execução.

### Targets (Alvos de Aplicação)

A meta-annotation `@Target` especifica quais elementos de código podem ser anotados. Os valores possíveis são definidos no enum `ElementType`:

```java
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DocumentacaoAPI {
    String descricao();
    String versao();
}
```

#### ElementType.TYPE
**Aplicável a**: Classes, interfaces (incluindo annotation types), enums e records

```java
@DocumentacaoAPI(descricao = "Serviço de autenticação", versao = "2.0")
public class ServicoAutenticacao {
    // ...
}
```

**Casos de Uso**: Configurações de classe, mapeamento de entidades, configuração de componentes.

#### ElementType.FIELD
**Aplicável a**: Campos de classe (incluindo constantes de enum)

```java
@Inject
private ServicoEmail servicoEmail;

@Column(name = "nome_completo", length = 100)
private String nome;
```

**Casos de Uso**: Injeção de dependências, mapeamento de colunas de banco de dados, validações de campo.

#### ElementType.METHOD
**Aplicável a**: Métodos

```java
@PostMapping("/usuarios")
@Transactional
public Usuario criarUsuario(@RequestBody Usuario usuario) {
    // ...
}
```

**Casos de Uso**: Mapeamento de endpoints, transações, testes unitários, aspectos.

#### ElementType.PARAMETER
**Aplicável a**: Parâmetros de métodos e construtores

```java
public void processar(@NotNull @Valid Usuario usuario, 
                      @RequestParam("id") Long id) {
    // ...
}
```

**Casos de Uso**: Validação de parâmetros, injeção de valores, binding de requisições HTTP.

#### ElementType.CONSTRUCTOR
**Aplicável a**: Construtores

```java
@Autowired
public ServicoUsuario(RepositorioUsuario repositorio) {
    this.repositorio = repositorio;
}
```

**Casos de Uso**: Injeção de dependências via construtor.

#### ElementType.LOCAL_VARIABLE
**Aplicável a**: Variáveis locais

```java
public void processar() {
    @SuppressWarnings("unchecked")
    List<String> lista = (List<String>) obterLista();
}
```

**Casos de Uso**: Supressão de warnings, hints para ferramentas de análise.

#### ElementType.ANNOTATION_TYPE
**Aplicável a**: Outras annotations (meta-annotations)

```java
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MetaAnnotation {
    // ...
}
```

**Casos de Uso**: Criação de annotations que anotam outras annotations, composição de annotations.

#### ElementType.PACKAGE
**Aplicável a**: Declarações de pacote (arquivo package-info.java)

```java
@ParametersAreNonnullByDefault
package com.exemplo.servico;

import javax.annotation.ParametersAreNonnullByDefault;
```

**Casos de Uso**: Configurações que se aplicam a todo um pacote, políticas de null-safety.

#### ElementType.TYPE_PARAMETER (Java 8+)
**Aplicável a**: Parâmetros de tipo genérico

```java
public class Caixa<@NonNull T> {
    private T conteudo;
}
```

**Casos de Uso**: Type checkers, null-safety em generics.

#### ElementType.TYPE_USE (Java 8+)
**Aplicável a**: Qualquer uso de tipo

```java
@NotNull String texto;
List<@NotNull String> lista;
@Readonly List<String> listaImutavel;
```

**Casos de Uso**: Type checkers avançados, frameworks de null-safety, análise estática.

### Meta-Annotations Adicionais

#### @Documented
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperacaoPublica {
    String descricao();
}
```

**Propósito**: Indica que a annotation deve aparecer na documentação Javadoc gerada.

**Comportamento**:
- Sem @Documented: Annotation não aparece no Javadoc
- Com @Documented: Annotation e seus valores aparecem no Javadoc

**Casos de Uso**: Annotations que fazem parte da API pública e devem ser documentadas, como `@Deprecated`.

#### @Inherited
```java
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Auditavel {
    String responsavel();
}
```

**Propósito**: Indica que a annotation é automaticamente herdada por subclasses.

**Comportamento**:
- Aplica-se APENAS a annotations de classes (TYPE)
- Se uma classe é anotada, todas as suas subclasses herdam a annotation
- NÃO funciona com interfaces, métodos ou campos

**Exemplo**:
```java
@Auditavel(responsavel = "Admin")
public class EntidadeBase { }

// Esta classe herda @Auditavel automaticamente
public class Usuario extends EntidadeBase { }
```

**Limitação Importante**: A herança ocorre apenas em hierarquia de classes, não através de implementação de interfaces.

#### @Repeatable (Java 8+)
```java
@Repeatable(Agendamentos.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Agendamento {
    String cron();
    String zona() default "UTC";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Agendamentos {
    Agendamento[] value();
}
```

**Propósito**: Permite aplicar a mesma annotation múltiplas vezes ao mesmo elemento.

**Requisitos**:
- Deve declarar uma annotation container (Agendamentos) que contenha um array da annotation repetível
- O container deve ter @Retention e @Target compatíveis ou mais abrangentes

**Uso**:
```java
@Agendamento(cron = "0 0 8 * * ?")  // Todo dia às 8h
@Agendamento(cron = "0 0 20 * * ?") // Todo dia às 20h
public void sincronizarDados() {
    // ...
}
```

**Antes do Java 8**: Era necessário usar a annotation container explicitamente:
```java
@Agendamentos({
    @Agendamento(cron = "0 0 8 * * ?"),
    @Agendamento(cron = "0 0 20 * * ?")
})
```

## 🏛️ Annotations Predefinidas (Built-in)

O Java fornece um conjunto de annotations predefinidas que são amplamente utilizadas e demonstram os padrões de uso de annotations.

### @Override
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
```

**Propósito**: Indica que um método está sobrescrevendo um método da superclasse ou implementando um método de interface.

**Funcionamento**:
- O compilador verifica se o método realmente sobrescreve um método da superclasse
- Se não houver método correspondente na hierarquia, o compilador gera erro
- Previne erros sutis causados por typos em nomes de métodos ou assinaturas incorretas

**Exemplo**:
```java
public class Animal {
    public void emitirSom() {
        System.out.println("Som genérico");
    }
}

public class Cachorro extends Animal {
    @Override
    public void emitirSom() { // Correto
        System.out.println("Au au!");
    }
    
    @Override
    public void emitirSon() { // ERRO DE COMPILAÇÃO: método não existe na superclasse
        System.out.println("Som errado");
    }
}
```

**Melhor Prática**: Sempre use @Override ao sobrescrever métodos. Muitas IDEs podem configurar avisos se você esquecer esta annotation.

### @Deprecated
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, 
               MODULE, PARAMETER, TYPE})
public @interface Deprecated {
    String since() default "";
    boolean forRemoval() default false;
}
```

**Propósito**: Marca elementos de API como obsoletos, indicando que não devem mais ser usados.

**Evolução Java 9**:
- `since`: Indica a versão em que o elemento foi marcado como deprecated
- `forRemoval`: Indica se o elemento será removido em versão futura

**Exemplo**:
```java
public class CalculadoraLegada {
    
    /**
     * @deprecated Use {@link #calcularNovoMetodo(int, int)} em vez disso.
     * Este método será removido na versão 3.0.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    public int calcularVelho(int a, int b) {
        return a + b;
    }
    
    public int calcularNovoMetodo(int a, int b) {
        return a + b;
    }
}

// Uso gera warning
CalculadoraLegada calc = new CalculadoraLegada();
calc.calcularVelho(5, 3); // Warning: deprecated
```

**Melhor Prática**: 
- Sempre documente WHY o elemento está deprecated e WHAT usar no lugar
- Use parâmetros `since` e `forRemoval` para clareza
- Mantenha compatibilidade backwards até a remoção efetiva

### @SuppressWarnings
```java
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
@Retention(RetentionPolicy.SOURCE)
public @interface SuppressWarnings {
    String[] value();
}
```

**Propósito**: Instrui o compilador a suprimir warnings específicos.

**Warnings Comuns**:
- `"unchecked"`: Operações unchecked com generics
- `"deprecation"`: Uso de elementos deprecated
- `"rawtypes"`: Uso de raw types sem generics
- `"unused"`: Código não utilizado
- `"all"`: Todos os warnings (use com extrema cautela!)

**Exemplo**:
```java
public class ExemploSupressao {
    
    @SuppressWarnings("unchecked")
    public <T> List<T> criarListaLegada() {
        List lista = new ArrayList(); // Raw type
        lista.add("string");
        return lista; // Unchecked cast
    }
    
    @SuppressWarnings({"unused", "deprecation"})
    private void metodoComMultiplosSuspressions() {
        String variavelNaoUsada = "valor";
        Date data = new Date(2020, 1, 1); // Deprecated constructor
    }
}
```

**Melhor Prática**: 
- Use o escopo mais restrito possível (variável local > método > classe)
- Apenas suprima warnings que você entende completamente
- Documente POR QUE você está suprimindo o warning
- Evite `"all"` - seja específico

### @FunctionalInterface (Java 8+)
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FunctionalInterface {
}
```

**Propósito**: Indica que uma interface é funcional (possui exatamente um método abstrato) e pode ser implementada usando lambda expressions ou method references.

**Verificação do Compilador**:
- Interface deve ter exatamente um método abstrato
- Pode ter múltiplos métodos default e static
- Pode herdar métodos abstratos de Object (equals, hashCode, toString)

**Exemplo**:
```java
@FunctionalInterface
public interface Processador<T> {
    void processar(T item);
    
    // Métodos default são permitidos
    default void processorComLog(T item) {
        System.out.println("Processando: " + item);
        processar(item);
    }
    
    // Métodos static são permitidos
    static <T> Processador<T> criarNoop() {
        return item -> { /* não faz nada */ };
    }
}

// Uso com lambda
Processador<String> proc = texto -> System.out.println(texto.toUpperCase());
proc.processar("hello");

// Uso com method reference
Processador<String> proc2 = System.out::println;
```

**Interfaces Funcionais Predefinidas** (pacote `java.util.function`):
- `Predicate<T>`: T -> boolean
- `Function<T,R>`: T -> R
- `Consumer<T>`: T -> void
- `Supplier<T>`: () -> T
- `UnaryOperator<T>`: T -> T
- `BinaryOperator<T>`: (T, T) -> T
- E muitas outras variantes especializadas

### @SafeVarargs (Java 7+)
```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface SafeVarargs {
}
```

**Propósito**: Suprime warnings sobre operações potencialmente unsafe com parâmetros varargs de tipos genéricos.

**Contexto**: Varargs com generics podem causar heap pollution devido ao type erasure.

**Exemplo**:
```java
public class ExemploVarargs {
    
    // Sem @SafeVarargs: gera warning
    @SafeVarargs
    public final <T> void imprimirTodos(T... elementos) {
        for (T elemento : elementos) {
            System.out.println(elemento);
        }
    }
    
    // Uso seguro
    public void usar() {
        imprimirTodos("A", "B", "C");
        imprimirTodos(1, 2, 3);
    }
    
    // UNSAFE - não use @SafeVarargs aqui!
    public <T> void unsafeMethod(T... elementos) {
        Object[] array = elementos; // Heap pollution possível
        array[0] = "String"; // Pode causar ClassCastException em runtime
    }
}
```

**Requisitos**:
- Método deve ser `static`, `final` ou `private` (Java 9+)
- Método deve realmente ser seguro (não causar heap pollution)

**Melhor Prática**: Use apenas quando tiver certeza de que o método é seguro. Não use para suprimir warnings sem entender o problema.

## 🎨 Criando Annotations Customizadas

Criar suas próprias annotations permite estender a linguagem com metadados específicos do domínio, facilitando configuração, validação e processamento declarativo.

### Anatomia Completa de uma Annotation

```java
import java.lang.annotation.*;

/**
 * Annotation para marcar métodos que requerem autenticação.
 * 
 * Esta annotation pode ser processada em runtime por um framework
 * de segurança para verificar permissões antes da execução do método.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequerAutenticacao {
    
    /**
     * Roles necessárias para acessar o recurso.
     * @return array de roles
     */
    String[] roles() default {};
    
    /**
     * Indica se todas as roles são necessárias (AND) ou apenas uma (OR).
     * @return true para AND, false para OR
     */
    boolean requererTodas() default false;
    
    /**
     * Prioridade de verificação (maior = mais importante).
     * @return prioridade
     */
    int prioridade() default 0;
    
    /**
     * Mensagem de erro personalizada se autenticação falhar.
     * @return mensagem de erro
     */
    String mensagemErro() default "Acesso negado";
}
```

### Padrões de Design com Annotations

#### 1. Annotation de Marcação (Marker Annotation)
Annotations sem elementos, usadas apenas para marcar código:

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transacional {
    // Sem elementos - apenas marca o método
}

// Uso
@Transacional
public void salvarUsuario(Usuario usuario) {
    // Framework detecta @Transacional e gerencia transação automaticamente
}
```

#### 2. Single-Value Annotation
Annotation com um único elemento chamado `value`, permitindo sintaxe simplificada:

```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tamanho {
    int value(); // Elemento especial "value"
}

// Uso simplificado (sem especificar "value = ")
@Tamanho(100)
private String nome;

// Equivalente a:
@Tamanho(value = 100)
private String nome;
```

#### 3. Multi-Value Annotation
Annotation com múltiplos elementos:

```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidacaoTexto {
    int minimo() default 0;
    int maximo() default Integer.MAX_VALUE;
    String regex() default "";
    String mensagem() default "Texto inválido";
}

// Uso com múltiplos valores
@ValidacaoTexto(minimo = 5, maximo = 50, 
                regex = "[a-zA-Z]+", 
                mensagem = "Nome deve conter apenas letras")
private String nome;
```

#### 4. Composed Annotations (Meta-Annotations)
Annotations que combinam múltiplas annotations:

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component // Spring annotation
@Transactional
@Documented
public @interface Servico {
    String value() default "";
}

// Uso - aplica automaticamente @Component e @Transactional
@Servico
public class ServicoUsuario {
    // ...
}
```

## 🔬 Processamento de Annotations via Reflection

Reflection é a API do Java que permite inspecionar e manipular código em tempo de execução. É fundamental para processar annotations com `RetentionPolicy.RUNTIME`.

### API de Reflection para Annotations

```java
import java.lang.reflect.*;

public class ProcessadorAnnotations {
    
    public static void processarClasse(Class<?> classe) {
        // Verificar se classe tem annotation
        if (classe.isAnnotationPresent(RequerAutenticacao.class)) {
            RequerAutenticacao auth = classe.getAnnotation(RequerAutenticacao.class);
            System.out.println("Classe requer roles: " + 
                             Arrays.toString(auth.roles()));
        }
        
        // Obter todas as annotations da classe
        Annotation[] annotations = classe.getAnnotations();
        for (Annotation ann : annotations) {
            System.out.println("Annotation: " + ann.annotationType().getName());
        }
        
        // Processar métodos
        for (Method metodo : classe.getDeclaredMethods()) {
            processarMetodo(metodo);
        }
        
        // Processar campos
        for (Field campo : classe.getDeclaredFields()) {
            processarCampo(campo);
        }
    }
    
    private static void processarMetodo(Method metodo) {
        if (metodo.isAnnotationPresent(RequerAutenticacao.class)) {
            RequerAutenticacao auth = metodo.getAnnotation(RequerAutenticacao.class);
            System.out.println("Método " + metodo.getName() + 
                             " requer roles: " + Arrays.toString(auth.roles()));
            
            // Processar annotations de parâmetros
            Annotation[][] paramAnnotations = metodo.getParameterAnnotations();
            for (int i = 0; i < paramAnnotations.length; i++) {
                for (Annotation ann : paramAnnotations[i]) {
                    System.out.println("Parâmetro " + i + " tem: " + ann);
                }
            }
        }
    }
    
    private static void processarCampo(Field campo) {
        ValidacaoTexto validacao = campo.getAnnotation(ValidacaoTexto.class);
        if (validacao != null) {
            System.out.println("Campo " + campo.getName() + 
                             " válido entre " + validacao.minimo() + 
                             " e " + validacao.maximo());
        }
    }
}
```

### Framework Simplificado de Validação

Exemplo prático de como frameworks usam annotations e reflection:

```java
public class ValidadorSimples {
    
    public static <T> List<String> validar(T objeto) throws IllegalAccessException {
        List<String> erros = new ArrayList<>();
        Class<?> classe = objeto.getClass();
        
        for (Field campo : classe.getDeclaredFields()) {
            campo.setAccessible(true); // Permite acesso a campos privados
            
            // Validar @NotNull
            if (campo.isAnnotationPresent(NotNull.class)) {
                Object valor = campo.get(objeto);
                if (valor == null) {
                    NotNull ann = campo.getAnnotation(NotNull.class);
                    erros.add(campo.getName() + ": " + ann.mensagem());
                }
            }
            
            // Validar @ValidacaoTexto
            if (campo.isAnnotationPresent(ValidacaoTexto.class)) {
                Object valor = campo.get(objeto);
                if (valor instanceof String) {
                    String texto = (String) valor;
                    ValidacaoTexto val = campo.getAnnotation(ValidacaoTexto.class);
                    
                    if (texto.length() < val.minimo() || texto.length() > val.maximo()) {
                        erros.add(campo.getName() + ": " + val.mensagem());
                    }
                    
                    if (!val.regex().isEmpty() && !texto.matches(val.regex())) {
                        erros.add(campo.getName() + ": " + val.mensagem());
                    }
                }
            }
        }
        
        return erros;
    }
}

// Uso
public class Usuario {
    @NotNull(mensagem = "Nome não pode ser nulo")
    @ValidacaoTexto(minimo = 3, maximo = 50, mensagem = "Nome inválido")
    private String nome;
    
    @NotNull(mensagem = "Email não pode ser nulo")
    @ValidacaoTexto(regex = "^[A-Za-z0-9+_.-]+@(.+)$", 
                    mensagem = "Email inválido")
    private String email;
    
    // construtores, getters, setters
}

// Validação
Usuario usuario = new Usuario();
usuario.setNome("Jo"); // Muito curto
usuario.setEmail("email-invalido"); // Formato inválido

List<String> erros = ValidadorSimples.validar(usuario);
erros.forEach(System.out::println);
```

## 🏭 Annotation Processing (Processamento em Tempo de Compilação)

Annotation processors são ferramentas poderosas que processam annotations durante a compilação, podendo gerar código, validar regras e produzir recursos.

### Conceitos Fundamentais

**Pluggable Annotation Processing API** (JSR 269): Introduzida no Java 6, permite criar processors que são invocados pelo compilador.

**Vantagens**:
- Detecção de erros em tempo de compilação
- Geração automática de código boilerplate
- Produção de arquivos de configuração
- Validação de regras de negócio sem overhead de runtime

**Limitações**:
- Não pode modificar código existente (apenas ler)
- Pode apenas gerar novos arquivos
- Executa durante compilação, aumentando tempo de build

### Estrutura de um Annotation Processor

```java
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("com.exemplo.MinhaAnnotation")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class MeuProcessor extends AbstractProcessor {
    
    private Messager messager;
    private Filer filer;
    
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        filer = processingEnv.getFiler();
    }
    
    @Override
    public boolean process(Set<? extends TypeElement> annotations, 
                          RoundEnvironment roundEnv) {
        
        for (TypeElement annotation : annotations) {
            Set<? extends Element> elements = 
                roundEnv.getElementsAnnotatedWith(annotation);
            
            for (Element element : elements) {
                // Validar
                if (!element.getKind().isClass()) {
                    messager.printMessage(
                        Diagnostic.Kind.ERROR,
                        "@MinhaAnnotation só pode ser aplicada a classes",
                        element
                    );
                    return true;
                }
                
                // Processar e gerar código
                processarElemento(element);
            }
        }
        
        return true;
    }
    
    private void processarElemento(Element element) {
        // Gerar código, validar regras, etc.
        String nomeClasse = element.getSimpleName().toString();
        messager.printMessage(
            Diagnostic.Kind.NOTE,
            "Processando classe: " + nomeClasse
        );
        
        // Exemplo: gerar classe Builder
        // gerarBuilder(element);
    }
}
```

### Registrando o Processor

**Método 1: META-INF/services**
Criar arquivo `META-INF/services/javax.annotation.processing.Processor`:
```
com.exemplo.MeuProcessor
```

**Método 2: @AutoService** (Google Auto Service Library)
```java
import com.google.auto.service.AutoService;

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.exemplo.MinhaAnnotation")
public class MeuProcessor extends AbstractProcessor {
    // ...
}
```

### Casos de Uso Reais

1. **Lombok**: Gera getters, setters, constructors, builders automaticamente
2. **Dagger**: Gera código para injeção de dependências
3. **Room** (Android): Gera implementações de DAOs para acesso a banco de dados
4. **AutoValue**: Gera classes de valor imutáveis
5. **Butterknife**: Gerava view binding (substituído por View Binding/Jetpack Compose)

## 📖 Exemplos Práticos

### 1. Sistema de Validação
Ver arquivo `ExemploValidacao.java` para implementação completa de sistema de validação baseado em annotations.

### 2. Framework de Testes Simplificado
Ver arquivo `ExemploTestes.java` para mini-framework de testes similar ao JUnit.

### 3. Injeção de Dependências Simples
Ver arquivo `ExemploInjecaoDependencias.java` para sistema básico de DI.

### 4. Mapeamento Objeto-Relacional
Ver arquivo `ExemploORM.java` para exemplo de mapeamento de entidades.

### 5. API REST com Annotations
Ver arquivo `ExemploRestAPI.java` para simulação de framework REST.

## 🚀 Como Executar

Todos os exemplos são autocontidos e podem ser executados individualmente:

```bash
# Compilar um exemplo específico
javac ExemploAnnotationsBuiltIn.java

# Executar
java ExemploAnnotationsBuiltIn

# Compilar todos os exemplos
javac *.java

# Executar exemplo principal
java ExemploAnnotations
```

## 💡 Melhores Práticas

### 1. Escolha a Retention Policy Apropriada
- **SOURCE**: Para ferramentas de análise, IDEs, ou quando processamento em compilação é suficiente
- **CLASS**: Para ferramentas de bytecode, análise de deploy
- **RUNTIME**: Apenas quando reflection em runtime é absolutamente necessário (tem overhead de performance)

### 2. Seja Específico com @Target
Restrinja onde a annotation pode ser aplicada para prevenir uso incorreto:
```java
// Ruim - muito permissivo
@Target(ElementType.TYPE)

// Bom - específico
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
```

### 3. Forneça Valores Padrão Sensatos
```java
public @interface Configuracao {
    String servidor() default "localhost";
    int porta() default 8080;
    boolean ssl() default false;
}
```

### 4. Documente Extensivamente
Annotations fazem parte da API pública. Documente:
- O que a annotation faz
- Quando usar
- Exemplo de uso
- Comportamento esperado
- Limitações

```java
/**
 * Marca métodos que devem ser executados de forma assíncrona.
 * 
 * <p>O método anotado será executado em uma thread separada.
 * O método não deve retornar valores (void) ou retornar Future/CompletableFuture.
 * 
 * <p>Exemplo:
 * <pre>
 * &#64;Assincrono
 * public void enviarEmail(String destinatario) {
 *     // Código para enviar email
 * }
 * </pre>
 * 
 * @since 1.0
 * @see java.util.concurrent.Future
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Assincrono {
    // ...
}
```

### 5. Valide Combinações de Annotations
Use annotation processors para validar que annotations são usadas corretamente:
```java
// Validar que @Transactional não é usado com @Assincrono
if (metodo.isAnnotationPresent(Transactional.class) && 
    metodo.isAnnotationPresent(Assincrono.class)) {
    throw new IllegalStateException(
        "@Transactional não pode ser usado com @Assincrono"
    );
}
```

### 6. Considere Composição
Em vez de criar muitas annotations similares, considere composição:
```java
// Em vez de @ServiceTransactional, @RepositoryTransactional, etc.
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Transactional
public @interface Servico {
    String value() default "";
}
```

### 7. Performance com Reflection
Cache resultados de reflection para melhor performance:
```java
private static final Map<Class<?>, List<Field>> cacheFields = new ConcurrentHashMap<>();

public static List<Field> obterCamposAnotados(Class<?> classe) {
    return cacheFields.computeIfAbsent(classe, c -> {
        return Arrays.stream(c.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(MinhaAnnotation.class))
                    .collect(Collectors.toList());
    });
}
```

## ⚠️ Armadilhas Comuns

### 1. Usar Retention Incorreta
```java
// ERRO: Tentando acessar annotation SOURCE em runtime
@Retention(RetentionPolicy.SOURCE)
public @interface MinhaAnnotation { }

// Em runtime:
if (classe.isAnnotationPresent(MinhaAnnotation.class)) { // Sempre false!
    // Nunca executado
}
```

### 2. Esquecer @Inherited em Hierarquias
```java
// Sem @Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Auditavel { }

@Auditavel
class Base { }

class Derivada extends Base { } // NÃO herda @Auditavel!
```

### 3. Confundir @Target
```java
@Target(ElementType.METHOD)
public @interface MinhaAnnotation { }

@MinhaAnnotation // ERRO: Não pode ser aplicada a classes
public class MinhaClasse { }
```

### 4. Arrays em Annotations
```java
// ERRO: Arrays não podem ter null
public @interface Config {
    String[] valores(); // Não pode ser null, mas pode ser array vazio
}

// Uso correto
@Config(valores = {}) // Array vazio OK
@Config(valores = {"a", "b"}) // Com valores OK
// @Config(valores = null) // ERRO de compilação
```

### 5. Type Erasure com Generics
```java
// Não é possível usar type parameter em annotation
public @interface Container<T> { // ERRO: Generics não permitido
    Class<T> tipo();
}

// Solução: usar Class<?> ou wildcard
public @interface Container {
    Class<?> tipo();
}
```

## 🎓 Frameworks que Usam Annotations Extensivamente

### Spring Framework
```java
@SpringBootApplication
public class Application { }

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return service.buscar(id);
    }
    
    @PostMapping
    @Transactional
    public Usuario criar(@RequestBody @Valid Usuario usuario) {
        return service.salvar(usuario);
    }
}
```

### JPA/Hibernate
```java
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nome;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
    
    @ManyToMany
    @JoinTable(name = "usuario_perfil",
              joinColumns = @JoinColumn(name = "usuario_id"),
              inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private Set<Perfil> perfis;
}
```

### Bean Validation
```java
public class Usuario {
    @NotNull(message = "Nome é obrigatório")
    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String nome;
    
    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    
    @Min(value = 18, message = "Idade mínima é 18")
    @Max(value = 100, message = "Idade máxima é 100")
    private Integer idade;
    
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", 
            message = "CPF inválido")
    private String cpf;
}
```

### JUnit 5
```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculadoraTeste {
    
    private Calculadora calc;
    
    @BeforeAll
    void configurarTodos() {
        // Setup para todos os testes
    }
    
    @BeforeEach
    void configurarCada() {
        calc = new Calculadora();
    }
    
    @Test
    @DisplayName("Deve somar dois números positivos")
    void testeSomaPositivos() {
        assertEquals(5, calc.somar(2, 3));
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testeMultiplosValores(int numero) {
        assertTrue(numero > 0);
    }
    
    @RepeatedTest(10)
    void testeRepetido() {
        // Executado 10 vezes
    }
    
    @Disabled("Temporariamente desabilitado")
    @Test
    void testeDesabilitado() {
        // Não executado
    }
}
```

## 🔗 Recursos Adicionais

### Documentação Oficial
- [The Java Tutorials - Annotations](https://docs.oracle.com/javase/tutorial/java/annotations/)
- [JLS - Annotations](https://docs.oracle.com/javase/specs/jls/se17/html/jls-9.html#jls-9.6)
- [Javadoc - java.lang.annotation](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/annotation/package-summary.html)
- [Pluggable Annotation Processing API](https://docs.oracle.com/en/java/javase/17/docs/api/java.compiler/javax/annotation/processing/package-summary.html)

### Livros Recomendados
- **"Effective Java" - Joshua Bloch**: Itens 39-41 sobre annotations
- **"Java 8 in Action"**: Capítulos sobre annotations e processamento
- **"Core Java, Volume I"**: Capítulo sobre annotations

### Artigos e Tutoriais
- [Baeldung - Java Annotations](https://www.baeldung.com/java-default-annotations)
- [Oracle - Annotation Processing](https://docs.oracle.com/javase/8/docs/technotes/guides/apt/GettingStarted.html)
- [Creating Custom Annotations](https://www.baeldung.com/java-custom-annotation)

### Ferramentas
- **Lombok**: Geração automática de código
- **Google Auto**: Bibliotecas para annotation processing
- **MapStruct**: Geração de mapeadores de objetos
- **Immutables**: Geração de classes imutáveis
- **Checker Framework**: Type checking avançado

## 📝 Exercícios

### Básico
1. Crie uma annotation `@Author` que armazene nome e data de criação
2. Implemente validação com `@Range(min, max)` para números
3. Crie `@Log` que imprime mensagem antes/depois da execução do método

### Intermediário
4. Implemente sistema de cache usando `@Cacheable`
5. Crie `@Retry` que reexecuta método em caso de falha
6. Implemente `@Scheduled` para execução periódica de métodos

### Avançado
7. Crie annotation processor que valida nomes de métodos
8. Implemente mini-framework de injeção de dependências
9. Crie sistema de serialização/deserialização JSON usando annotations

### Desafio
10. Implemente framework de testes completo inspirado no JUnit
11. Crie sistema de mapeamento objeto-relacional simples
12. Desenvolva framework de validação similar ao Bean Validation

## 🎯 Conclusão

Annotations são uma ferramenta poderosa que transforma a forma como configuramos, validamos e processamos código Java. Elas:

- **Reduzem boilerplate** através de configuração declarativa
- **Melhoram legibilidade** tornando intenções explícitas
- **Facilitam metaprogramação** permitindo código que analisa código
- **Suportam frameworks modernos** como Spring, Hibernate, JUnit
- **Permitem validações em tempo de compilação** aumentando qualidade do código
- **Habilitam geração automática de código** reduzindo erros e esforço manual

O domínio de annotations é essencial para o desenvolvedor Java moderno, sendo fundamental para trabalhar com praticamente todos os frameworks e bibliotecas enterprise. Através da compreensão profunda de como criar, processar e aplicar annotations, você ganha uma ferramenta versátil para resolver problemas complexos de forma elegante e declarativa.

---

**Anterior**: [Enumerações](../03-enum/) | **Próximo**: [Lambda e Streams](../05-lambda-streams/)
