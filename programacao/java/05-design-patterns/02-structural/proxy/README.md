# Proxy Pattern

O padrão Proxy fornece um substituto ou placeholder (representante) para controlar o acesso a um objeto. O proxy age como intermediário entre o cliente e o objeto real, adicionando funcionalidade de controle, otimização ou segurança sem alterar o objeto original.

## 🎯 Problema

Como controlar, otimizar ou adicionar funcionalidades ao acesso a um objeto sem modificá-lo? Situações comuns incluem:

- **Custo de criação**: Objeto é caro para criar (conexão de rede, grande arquivo)
- **Segurança**: Precisa controlar quem pode acessar o objeto
- **Localização**: Objeto está em outro espaço de endereçamento (servidor remoto)
- **Monitoramento**: Quer logar ou contar acessos ao objeto
- **Cache**: Quer cachear resultados de operações caras
- **Lazy initialization**: Criar objeto apenas quando realmente necessário

### Exemplo Real - Carregamento de Imagens
Uma galeria de fotos com centenas de imagens de alta resolução. Carregar todas na memória seria muito pesado. Um proxy (thumbnail) representa cada imagem, carregando a imagem completa apenas quando o usuário clica para visualizar.

### Outros Exemplos Comuns
- **Proxies de Banco de Dados**: Hibernate lazy loading - carrega objetos relacionados apenas quando acessados
- **Remote Proxy**: RMI (Remote Method Invocation) ou RPC - objeto local representa objeto remoto
- **Protection Proxy**: Sistema de arquivos - verificar permissões antes de permitir leitura/escrita
- **Smart Reference**: Contador de referências, garbage collection auxiliado

## 💡 Solução

Criar proxy que:
1. **Implementa mesma interface** do objeto real (Subject)
2. **Contém referência** ao objeto real (RealSubject)
3. **Controla acesso** e pode adicionar comportamento extra antes/depois de delegar
4. **Delega operações** ao objeto real quando apropriado
5. **Transparente para o cliente** - cliente não sabe se usa proxy ou objeto real

### Características Principais
- **Mesma interface**: Proxy e RealSubject implementam mesma interface
- **Substituibilidade**: Proxy pode substituir RealSubject em qualquer lugar
- **Controle de acesso**: Proxy decide se/quando chamar RealSubject
- **Funcionalidades adicionais**: Logging, caching, validação, lazy loading
- **Transparência**: Cliente não precisa saber que está usando proxy

## 🏗️ Estrutura

```
┌─────────────┐
│   Client    │
└─────────────┘
       │
       ▼
┌─────────────┐
│   Subject   │  ← Interface comum
│ (interface) │
└─────────────┘
       △
       │
    ├──┴───┐
    │      │
┌───────────┐  reference  ┌──────────────┐
│   Proxy   │────────────>│ RealSubject  │
└───────────┘             └──────────────┘
    │                            │
    │ Controls access to         │ Does real work
    │ + adds functionality       │
```

### Participantes

- **Subject**: Interface comum para RealSubject e Proxy
  - Define operações que podem ser acessadas
  - Permite que Proxy substitua RealSubject

- **RealSubject**: Objeto real que o proxy representa
  - Implementa funcionalidade real
  - Pode ser custoso criar ou acessar

- **Proxy**: Substituto do RealSubject
  - Mantém referência para RealSubject
  - Controla acesso ao RealSubject
  - Adiciona funcionalidades (lazy loading, cache, controle de acesso, etc.)
  - Pode criar RealSubject sob demanda

- **Client**: Trabalha com Subject (interface)
  - Não distingue entre Proxy e RealSubject
  - Código transparente ao uso de proxy

## 📝 Tipos de Proxy

O padrão Proxy tem várias variações, cada uma com propósito específico:

### 1. Virtual Proxy (Lazy Loading)
Adia criação de objetos caros até que sejam realmente necessários.

**Quando usar**: Objeto é custoso para criar mas pode não ser usado.

**Exemplo**:
```java
class ImagemProxy implements Imagem {
    private String filename;
    private ImagemReal imagemReal;  // Null até ser necessária
    
    public void exibir() {
        if (imagemReal == null) {
            imagemReal = new ImagemReal(filename);  // Lazy load
        }
        imagemReal.exibir();
    }
}
```

**Casos reais**:
- Carregamento de imagens grandes
- Hibernate lazy loading de relacionamentos
- Objetos COM em sistemas Windows

### 2. Protection Proxy (Controle de Acesso)
Controla acesso baseado em permissões ou credenciais.

**Quando usar**: Precisa restringir acesso a objeto baseado em regras.

**Exemplo**:
```java
class DocumentoProxy implements Documento {
    private DocumentoReal documento;
    private Usuario usuario;
    
    public void editar() {
        if (usuario.temPermissao("EDITAR")) {
            documento.editar();
        } else {
            throw new SecurityException("Sem permissão");
        }
    }
}
```

**Casos reais**:
- Sistemas de arquivos (permissões read/write/execute)
- APIs com controle de acesso por role
- Java RMI com SecurityManager

### 3. Remote Proxy
Representa objeto em espaço de endereçamento diferente (outro processo, servidor).

**Quando usar**: Objeto está em máquina remota.

**Exemplo**:
```java
class BancoRemotoProxy implements ServicoBanco {
    private String enderecoServidor;
    
    public double getSaldo(String conta) {
        // Faz chamada RPC/HTTP para servidor remoto
        Response resp = httpClient.get(enderecoServidor + "/saldo/" + conta);
        return resp.getBody().getSaldo();
    }
}
```

**Casos reais**:
- Java RMI (Remote Method Invocation)
- Web Services (SOAP, REST clients)
- CORBA stubs

### 4. Cache Proxy (Smart Proxy)
Armazena resultados de operações caras para evitar recálculo.

**Quando usar**: Operação é cara e resultado pode ser reutilizado.

**Exemplo**:
```java
class CalculadoraProxy implements Calculadora {
    private CalculadoraReal calculadora;
    private Map<String, Double> cache = new HashMap<>();
    
    public double calcular(String expressao) {
        if (cache.containsKey(expressao)) {
            return cache.get(expressao);  // Retorna do cache
        }
        double resultado = calculadora.calcular(expressao);
        cache.put(expressao, resultado);  // Cacheia
        return resultado;
    }
}
```

**Casos reais**:
- Hibernate second-level cache
- CDN (Content Delivery Network)
- Memoization de funções

### 5. Logging/Monitoring Proxy
Adiciona logging ou monitoramento sem modificar objeto original.

**Quando usar**: Quer rastrear uso do objeto.

**Exemplo**:
```java
class ServiceProxy implements Service {
    private ServiceReal service;
    private Logger logger;
    
    public void operacao() {
        logger.info("Iniciando operacao");
        long inicio = System.currentTimeMillis();
        
        service.operacao();
        
        long duracao = System.currentTimeMillis() - inicio;
        logger.info("Operacao completada em " + duracao + "ms");
    }
}
```

**Casos reais**:
- Spring AOP para logging
- Transaction management
- Performance monitoring

### 6. Synchronization Proxy
Controla acesso concorrente ao objeto real.

**Quando usar**: Objeto não é thread-safe mas precisa ser acessado por múltiplas threads.

**Exemplo**:
```java
class RecursoProxy implements Recurso {
    private RecursoReal recurso;
    private final Object lock = new Object();
    
    public void processar() {
        synchronized(lock) {
            recurso.processar();  // Acesso sincronizado
        }
    }
}
```

### 7. Copy-on-Write Proxy
Copia objeto apenas quando modificado, economizando memória.

**Quando usar**: Múltiplas referências para objeto grande que raramente é modificado.

**Exemplo**: Strings em Java (imutáveis), Copy-on-Write collections

## 📝 Implementações

### Sistema de Carregamento de Imagens (Virtual Proxy)

Este exemplo demonstra virtual proxy com lazy loading de imagens.

- **[Imagem.java](Imagem.java)** - Interface Subject que define operações sobre imagens
- **[ImagemReal.java](ImagemReal.java)** - RealSubject que carrega e exibe imagem real (operação cara)
- **[ImagemProxy.java](ImagemProxy.java)** - Proxy que adia carregamento até primeira exibição
- **[TesteProxy.java](TesteProxy.java)** - Demonstração comparando com e sem proxy

### Comportamento

**Sem Proxy** (Caro):
```java
Imagem img = new ImagemReal("foto.jpg");  // CARREGA IMEDIATAMENTE (caro!)
// ... tempo passa, imagem pode nem ser usada ...
img.exibir();
```

**Com Proxy** (Otimizado):
```java
Imagem img = new ImagemProxy("foto.jpg");  // NÃO CARREGA (rápido!)
// ... tempo passa ...
img.exibir();  // AGORA carrega (lazy), apenas se necessário
```

## 🚀 Como Executar

```bash
# Navegar para o diretório
cd proxy/

# Compilar todos os arquivos
javac *.java

# Executar o teste
java TesteProxy
```

## 📊 Exemplo de Saída Esperada

```
=== Sistema de Galeria - Virtual Proxy ===

--- Criando proxies (rápido) ---
[Proxy] Criando proxy para foto1.jpg
[Proxy] Criando proxy para foto2.jpg
[Proxy] Criando proxy para foto3.jpg
Proxies criados! (sem carregamento das imagens)

--- Exibindo primeira imagem ---
[ImagemReal] Carregando imagem foto1.jpg do disco...
[ImagemReal] Imagem carregada: foto1.jpg (2.5 MB)
[ImagemReal] Exibindo foto1.jpg

--- Exibindo primeira imagem novamente (já carregada) ---
[ImagemReal] Exibindo foto1.jpg

--- Exibindo segunda imagem ---
[ImagemReal] Carregando imagem foto2.jpg do disco...
[ImagemReal] Imagem carregada: foto2.jpg (3.1 MB)
[ImagemReal] Exibindo foto2.jpg

Nota: foto3.jpg nunca foi exibida, então nunca foi carregada!
Economia de memória: ~4 MB
```

## ✅ Vantagens

1. **Controle de Acesso Transparente**
   - Cliente não sabe que usa proxy
   - Controle pode ser adicionado/removido sem afetar cliente
   - Facilita implementação de políticas de segurança

2. **Lazy Initialization (Virtual Proxy)**
   - Economiza recursos não criando objetos até necessário
   - Melhora tempo de inicialização da aplicação
   - Reduz uso de memória para objetos não utilizados

3. **Performance (Cache Proxy)**
   - Cache transparente de resultados
   - Reduz operações caras (I/O, network, computação)
   - Melhora tempo de resposta

4. **Separação de Concerns**
   - Funcionalidades de controle separadas da lógica principal
   - Object permanece focado em sua responsabilidade
   - Logging, segurança, cache são preocupações ortogonais

5. **Open/Closed Principle**
   - Adiciona funcionalidades sem modificar RealSubject
   - Múltiplos proxies podem ser encadeados
   - Extensível para novas funcionalidades

6. **Localização Transparente (Remote Proxy)**
   - Esconde complexidade de comunicação remota
   - Cliente trata objeto remoto como local
   - Facilita distribuição de sistema

7. **Controle Fino**
   - Pode adicionar lógica antes e depois de chamar RealSubject
   - Validação de parâmetros
   - Transformação de dados

## ⚠️ Desvantagens

1. **Complexidade Adicional**
   - Mais classes no sistema
   - Código pode ficar mais difícil de entender
   - Debugging pode ser mais complexo (camadas extras)

2. **Overhead de Performance**
   - Camada extra de indireção
   - Cada chamada passa pelo proxy
   - Para operações simples e rápidas, overhead pode ser significativo

3. **Latência (Remote Proxy)**
   - Comunicação remota adiciona latência
   - Pode falhar (problemas de rede)
   - Precisa tratamento de erros de comunicação

4. **Manutenção**
   - Proxy precisa ser mantido sincronizado com RealSubject
   - Se interface de Subject muda, proxy também precisa mudar
   - Múltiplos proxies aumentam pontos de manutenção

5. **Possível Inconsistência (Cache Proxy)**
   - Cache pode ficar desatualizado
   - Precisa estratégia de invalidação
   - Trade-off entre consistência e performance

6. **Vazamento de Abstração**
   - Comportamento pode diferir sutilmente do RealSubject
   - Lazy loading pode causar delay inesperado na primeira chamada
   - Thread-safety pode ser diferente

7. **Complexidade em Cenários Avançados**
   - Encadear múltiplos proxies pode ser confuso
   - Combinar diferentes tipos de proxy requer cuidado
   - Ordem de proxies pode importar

## 🎯 Quando Usar

✅ **Use Proxy quando**:
- Precisa de lazy initialization (Virtual Proxy)
- Quer controlar acesso a recursos (Protection Proxy)
- Objeto está em localização remota (Remote Proxy)
- Precisa adicionar logging ou cache (Smart Proxy)
- Quer contagem de referências ou copy-on-write
- Precisa adicionar sincronização para thread-safety
- Objeto real é caro para criar ou operar
- Quer adicionar funcionalidades sem modificar código original

❌ **Evite Proxy quando**:
- Acesso direto é suficiente e não há necessidade de controle
- Overhead adicional não é aceitável (operações críticas de performance)
- Interface muda frequentemente (manutenção difícil)
- Complexidade adicional não é justificada
- Objeto real é simples e barato
- Transparência não é importante (cliente pode saber que usa intermediário)

## 💼 Casos de Uso Reais

### 1. Hibernate - Lazy Loading (Virtual Proxy)
```java
@Entity
public class Order {
    @Id
    private Long id;
    
    @OneToMany(fetch = FetchType.LAZY)  // Proxy!
    private List<OrderItem> items;  // Carregado apenas quando acessado
}

Order order = session.get(Order.class, 1L);  // Busca Order
// items ainda não foi carregado (proxy)
List<OrderItem> items = order.getItems();  // AGORA carrega (lazy)
```

### 2. Spring AOP - Logging e Transaction Proxy
```java
@Service
public class UserService {
    @Transactional  // Spring cria proxy para gerenciar transação
    public void createUser(User user) {
        userRepository.save(user);
        // Se exceção, proxy faz rollback
    }
}

// Spring cria dinamicamente:
class UserServiceProxy extends UserService {
    public void createUser(User user) {
        transaction.begin();
        try {
            super.createUser(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
```

### 3. Java RMI - Remote Proxy
```java
// Interface compartilhada
public interface Calculator extends Remote {
    int add(int a, int b) throws RemoteException;
}

// Cliente usa proxy local que representa objeto remoto
Calculator calc = (Calculator) Naming.lookup("rmi://server/Calculator");
int result = calc.add(5, 3);  // Chamada remota transparente
```

### 4. Collections.synchronizedList - Synchronization Proxy
```java
// Proxy que adiciona sincronização
List<String> list = Collections.synchronizedList(new ArrayList<>());

// Internamente:
class SynchronizedList<E> implements List<E> {
    private final List<E> list;
    private final Object mutex = new Object();
    
    public E get(int index) {
        synchronized(mutex) {
            return list.get(index);
        }
    }
    // ... todos métodos sincronizados
}
```

### 5. java.lang.reflect.Proxy - Dynamic Proxy
```java
InvocationHandler handler = new InvocationHandler() {
    public Object invoke(Object proxy, Method method, Object[] args) {
        System.out.println("Chamando: " + method.getName());
        return method.invoke(realObject, args);
    }
};

Service proxy = (Service) Proxy.newProxyInstance(
    Service.class.getClassLoader(),
    new Class[] { Service.class },
    handler
);

proxy.doSomething();  // Log automático!
```

### 6. Security Proxy - Sistema de Arquivos
```java
class FileProxy implements FileAccess {
    private FileAccess realFile;
    private User currentUser;
    
    public String read() {
        if (!hasPermission(currentUser, Permission.READ)) {
            throw new SecurityException("Sem permissão de leitura");
        }
        return realFile.read();
    }
    
    public void write(String content) {
        if (!hasPermission(currentUser, Permission.WRITE)) {
            throw new SecurityException("Sem permissão de escrita");
        }
        realFile.write(content);
    }
}
```

## 🔄 Proxy vs Outros Padrões

### Proxy vs Decorator
| Aspecto | Proxy | Decorator |
|---------|-------|-----------|
| **Propósito** | Controlar acesso | Adicionar responsabilidades |
| **Interface** | Mesma interface | Mesma interface |
| **Criação** | Geralmente cria RealSubject | Recebe objeto decorado |
| **Quando** | Controle, lazy loading, cache | Funcionalidades opcionais |
| **Foco** | Gestão do objeto | Extensão de comportamento |

```java
// Proxy - controla acesso
class ImageProxy implements Image {
    private ImageReal real;
    public void display() {
        if (real == null) real = new ImageReal();  // Lazy
        real.display();
    }
}

// Decorator - adiciona funcionalidade
class BorderDecorator implements Image {
    private Image image;
    public BorderDecorator(Image img) { this.image = img; }
    public void display() {
        drawBorder();  // Funcionalidade extra
        image.display();
    }
}
```

### Proxy vs Adapter
| Aspecto | Proxy | Adapter |
|---------|-------|---------|
| **Interface** | Mesma do RealSubject | Converte interfaces diferentes |
| **Propósito** | Controlar/otimizar | Compatibilidade |
| **Substituição** | Substitui objeto real | Adapta interface incompatível |

```java
// Proxy - mesma interface
interface Image { void display(); }
class ImageProxy implements Image { ... }
class ImageReal implements Image { ... }

// Adapter - interfaces diferentes
interface MediaPlayer { void play(); }
class MP3Adapter implements MediaPlayer {
    private MP4Player mp4;  // Interface diferente
    public void play() { mp4.playVideo(); }  // Adapta
}
```

### Proxy vs Facade
| Aspecto | Proxy | Facade |
|---------|-------|--------|
| **Número de objetos** | Um objeto | Subsistema com múltiplos objetos |
| **Interface** | Mesma interface | Interface simplificada nova |
| **Propósito** | Controlar acesso a um objeto | Simplificar subsistema complexo |

## 💻 Implementações Detalhadas

### Virtual Proxy com Lazy Loading
```java
interface Document {
    void display();
    String getContent();
}

class DocumentReal implements Document {
    private String filename;
    private String content;
    
    public DocumentReal(String filename) {
        this.filename = filename;
        loadFromDisk();  // Operação cara
    }
    
    private void loadFromDisk() {
        System.out.println("Carregando " + filename + " (operação cara)");
        try {
            Thread.sleep(2000);  // Simula I/O lento
            content = "Conteúdo do arquivo " + filename;
        } catch (InterruptedException e) { }
    }
    
    public void display() {
        System.out.println(content);
    }
    
    public String getContent() {
        return content;
    }
}

class DocumentProxy implements Document {
    private String filename;
    private DocumentReal document;  // Null até necessário
    
    public DocumentProxy(String filename) {
        this.filename = filename;
        // NÃO carrega ainda - lazy!
    }
    
    public void display() {
        ensureLoaded();
        document.display();
    }
    
    public String getContent() {
        ensureLoaded();
        return document.getContent();
    }
    
    private void ensureLoaded() {
        if (document == null) {
            document = new DocumentReal(filename);  // Lazy load
        }
    }
}

// Cliente
Document doc = new DocumentProxy("report.pdf");  // Rápido!
// ... tempo passa ...
doc.display();  // Agora carrega
```

### Protection Proxy com Controle de Acesso
```java
interface BankAccount {
    void withdraw(double amount);
    void deposit(double amount);
    double getBalance();
}

class BankAccountReal implements BankAccount {
    private double balance;
    
    public void withdraw(double amount) {
        balance -= amount;
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public double getBalance() {
        return balance;
    }
}

class BankAccountProxy implements BankAccount {
    private BankAccountReal account;
    private User currentUser;
    private User accountOwner;
    
    public BankAccountProxy(BankAccountReal account, User owner, User current) {
        this.account = account;
        this.accountOwner = owner;
        this.currentUser = current;
    }
    
    public void withdraw(double amount) {
        if (!currentUser.equals(accountOwner)) {
            throw new SecurityException("Apenas dono pode sacar");
        }
        account.withdraw(amount);
    }
    
    public void deposit(double amount) {
        // Qualquer um pode depositar
        account.deposit(amount);
    }
    
    public double getBalance() {
        if (!currentUser.equals(accountOwner) && 
            !currentUser.hasRole("ADMIN")) {
            throw new SecurityException("Sem permissão para ver saldo");
        }
        return account.getBalance();
    }
}
```

### Cache Proxy para Operações Caras
```java
interface ExpensiveService {
    ComplexResult calculate(String input);
}

class ExpensiveServiceReal implements ExpensiveService {
    public ComplexResult calculate(String input) {
        System.out.println("Calculando para: " + input);
        // Simulação de operação cara (2 segundos)
        try { Thread.sleep(2000); } catch (InterruptedException e) { }
        return new ComplexResult(input);
    }
}

class CacheProxy implements ExpensiveService {
    private ExpensiveServiceReal service;
    private Map<String, ComplexResult> cache;
    private Map<String, Long> cacheTime;
    private long TTL = 60000;  // 1 minuto
    
    public CacheProxy(ExpensiveServiceReal service) {
        this.service = service;
        this.cache = new ConcurrentHashMap<>();
        this.cacheTime = new ConcurrentHashMap<>();
    }
    
    public ComplexResult calculate(String input) {
        // Verificar cache
        if (cache.containsKey(input)) {
            long age = System.currentTimeMillis() - cacheTime.get(input);
            if (age < TTL) {
                System.out.println("Cache HIT para: " + input);
                return cache.get(input);
            } else {
                System.out.println("Cache EXPIRADO para: " + input);
                cache.remove(input);
                cacheTime.remove(input);
            }
        }
        
        // Cache miss - calcular
        System.out.println("Cache MISS para: " + input);
        ComplexResult result = service.calculate(input);
        
        // Cachear resultado
        cache.put(input, result);
        cacheTime.put(input, System.currentTimeMillis());
        
        return result;
    }
    
    public void clearCache() {
        cache.clear();
        cacheTime.clear();
    }
}

// Cliente
ExpensiveService service = new CacheProxy(new ExpensiveServiceReal());
service.calculate("A");  // Cache MISS - 2 segundos
service.calculate("B");  // Cache MISS - 2 segundos
service.calculate("A");  // Cache HIT - instantâneo!
```

### Dynamic Proxy com Java Reflection
```java
interface Service {
    void operation(String param);
    int calculate(int a, int b);
}

class ServiceImpl implements Service {
    public void operation(String param) {
        System.out.println("Operation: " + param);
    }
    
    public int calculate(int a, int b) {
        return a + b;
    }
}

// Handler que adiciona logging a qualquer método
class LoggingHandler implements InvocationHandler {
    private Object target;
    
    public LoggingHandler(Object target) {
        this.target = target;
    }
    
    public Object invoke(Object proxy, Method method, Object[] args) 
            throws Throwable {
        System.out.println("Antes de " + method.getName());
        System.out.println("Argumentos: " + Arrays.toString(args));
        
        long start = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        long duration = System.currentTimeMillis() - start;
        
        System.out.println("Depois de " + method.getName());
        System.out.println("Duração: " + duration + "ms");
        System.out.println("Resultado: " + result);
        
        return result;
    }
}

// Criar proxy dinamicamente
Service realService = new ServiceImpl();
Service proxy = (Service) Proxy.newProxyInstance(
    Service.class.getClassLoader(),
    new Class[] { Service.class },
    new LoggingHandler(realService)
);

// Usar proxy - logging automático!
proxy.operation("test");
int result = proxy.calculate(5, 3);
```

### Encadeamento de Proxies
```java
// Múltiplos proxies podem ser encadeados
Service real = new ServiceImpl();
Service cached = new CacheProxy(real);           // Cache
Service logged = new LoggingProxy(cached);       // + Logging
Service secured = new SecurityProxy(logged);     // + Security
Service synced = new SynchronizedProxy(secured); // + Sync

// Cliente usa proxy final
synced.operation("test");
// Ordem: Sync → Security → Logging → Cache → Real
```

## 🔗 Navegação

- [Voltar para Padrões Estruturais](../)
- [Anterior: Composite](../composite/)

## ❓ Perguntas e Respostas Frequentes

### Q1: Qual a diferença principal entre Proxy e Decorator?
**R**: **Propósito e controle sobre o objeto**:
- **Proxy**: Controla acesso ao objeto (quando criar, quem pode acessar, cache). Geralmente *cria* o RealSubject.
- **Decorator**: Adiciona funcionalidades. Recebe objeto já criado e o *envolve*.

```java
// Proxy - cria e controla
class ImageProxy implements Image {
    private ImageReal real;  // Cria quando necessário
    public void display() {
        if (real == null) real = new ImageReal();  // Controla criação
        real.display();
    }
}

// Decorator - recebe e estende
class BorderDecorator implements Image {
    public BorderDecorator(Image img) {  // Recebe já criado
        this.image = img;
    }
    public void display() {
        drawBorder();  // Adiciona funcionalidade
        image.display();
    }
}
```

### Q2: Proxy sempre precisa implementar mesma interface do RealSubject?
**R**: **Sim, para manter transparência**. Cliente deve poder usar Proxy e RealSubject intercambiavelmente. Se não implementar mesma interface, não é Proxy Pattern, pode ser Adapter ou outro padrão.

```java
// ✅ Correto - mesma interface
interface Service { void execute(); }
class ServiceReal implements Service { ... }
class ServiceProxy implements Service { ... }  // Mesma interface

// ❌ Incorreto - interface diferente
class ServiceProxy {
    void performExecution() { ... }  // Método diferente!
}
```

### Q3: Como implementar Proxy para classes que não têm interface?
**R**: Três opções:

**1. Extrair interface** (melhor):
```java
// Refatorar para extrair interface
interface Calculator {
    int add(int a, int b);
}

class CalculatorReal implements Calculator { ... }
class CalculatorProxy implements Calculator { ... }
```

**2. Usar herança** (limitado):
```java
class CalculatorReal {
    public int add(int a, int b) { ... }
}

class CalculatorProxy extends CalculatorReal {
    @Override
    public int add(int a, int b) {
        // Lógica do proxy
        return super.add(a, b);
    }
}
// Limitação: Só funciona se métodos não são final
```

**3. Dynamic Proxy com Reflection** (frameworks):
```java
// CGLib, ByteBuddy podem criar proxy de classes concretas
Calculator proxy = (Calculator) Enhancer.create(
    CalculatorReal.class,
    interceptor
);
```

### Q4: Virtual Proxy resolve problema de performance ou apenas adia?
**R**: **Depende do cenário**:

**Resolve quando**:
- Objeto pode nunca ser usado (economia real)
- Uso é espaçado no tempo (distribui carga)
- Permite inicialização mais rápida da aplicação

**Apenas adia quando**:
- Objeto será usado imediatamente após criação
- Todos objetos eventualmente serão usados

```java
// Resolve: Galeria com 1000 fotos, usuário vê 10
for (int i = 0; i < 1000; i++) {
    images[i] = new ImageProxy("photo" + i);  // Rápido
}
// Apenas 10 serão carregadas - economia real!

// Apenas adia: Serviço sempre usado
Service service = new ServiceProxy(...);  // Rápido
service.execute();  // Carrega agora - mesmo custo total
```

### Q5: Como lidar com thread-safety em Proxies?
**R**: **Depende do tipo de proxy e do RealSubject**:

```java
// 1. Double-checked locking para lazy loading
class ImageProxy implements Image {
    private volatile ImageReal real;  // volatile importante!
    
    public void display() {
        if (real == null) {  // Primeira verificação sem lock
            synchronized(this) {
                if (real == null) {  // Segunda verificação com lock
                    real = new ImageReal();
                }
            }
        }
        real.display();
    }
}

// 2. Synchronization Proxy
class SyncProxy implements Service {
    private Service real;
    private final Object lock = new Object();
    
    public void execute() {
        synchronized(lock) {
            real.execute();
        }
    }
}

// 3. ThreadLocal para cache por thread
class CacheProxy implements Service {
    private ThreadLocal<Map<String, Result>> cache = 
        ThreadLocal.withInitial(HashMap::new);
}
```

### Q6: Proxy pode modificar retorno do RealSubject?
**R**: **Sim, mas cuidado** - pode violar transparência:

```java
// ✅ OK - transformação útil e documentada
class FormattingProxy implements TextService {
    private TextService real;
    
    public String getText() {
        String text = real.getText();
        return text.toUpperCase();  // Transformação útil
    }
}

// ⚠️ Duvidoso - modifica semântica
class PriceProxy implements PriceService {
    public double getPrice() {
        double price = real.getPrice();
        return price * 0.9;  // Desconto escondido - confuso!
    }
}

// ❌ Errado - quebra contrato
class CountProxy implements Counter {
    public int getCount() {
        return real.getCount() + 1000;  // Mentira!
    }
}
```

### Q7: Como testar código que usa Proxy?
**R**: Estratégias de teste:

```java
// 1. Testar RealSubject diretamente
@Test
public void testRealService() {
    Service service = new ServiceReal();
    Result result = service.execute();
    assertEquals(expected, result);
}

// 2. Testar proxy com mock do RealSubject
@Test
public void testProxyCallsReal() {
    Service mockReal = mock(Service.class);
    Service proxy = new ServiceProxy(mockReal);
    
    proxy.execute();
    
    verify(mockReal).execute();  // Verifica delegação
}

// 3. Testar comportamento específico do proxy
@Test
public void testProxyCaching() {
    Service mockReal = mock(Service.class);
    when(mockReal.execute()).thenReturn(result);
    
    CacheProxy proxy = new CacheProxy(mockReal);
    
    proxy.execute();  // Primeira chamada
    proxy.execute();  // Segunda chamada
    
    verify(mockReal, times(1)).execute();  // Chamou real só uma vez
}

// 4. Testar segurança (Protection Proxy)
@Test(expected = SecurityException.class)
public void testProxyDeniesAccess() {
    User unauthorized = new User("guest");
    Service proxy = new SecurityProxy(real, unauthorized);
    
    proxy.execute();  // Deve lançar exceção
}
```

### Q8: Posso ter Proxy de Proxy? Quando fazer isso?
**R**: **Sim, proxies podem ser encadeados**. Útil para combinar diferentes funcionalidades:

```java
// Encadear proxies para combinar funcionalidades
Service real = new ServiceReal();

// Camada 1: Cache
Service cached = new CacheProxy(real);

// Camada 2: Logging
Service logged = new LoggingProxy(cached);

// Camada 3: Security
Service secured = new SecurityProxy(logged);

// Cliente usa proxy final
secured.execute();
// Fluxo: Security check → Log → Check cache → Real execution

// ⚠️ Cuidado: Ordem importa!
// Logging antes de cache vs cache antes de logging = comportamentos diferentes
```

**Quando usar**:
- Cada proxy tem responsabilidade única e clara
- Funcionalidades são ortogonais (independentes)
- Benefício justifica complexidade adicional

**Quando evitar**:
- Mais de 3-4 níveis (muito complexo)
- Performance crítica (muitas camadas de indireção)
- Proxies têm dependências entre si

### Q9: Como invalido cache em Cache Proxy?
**R**: Várias estratégias:

```java
class CacheProxy implements Service {
    private Map<String, Result> cache = new ConcurrentHashMap<>();
    
    // 1. TTL (Time To Live) - expira após tempo
    private Map<String, Long> timestamps = new ConcurrentHashMap<>();
    private long TTL = 60000;  // 1 minuto
    
    public Result execute(String key) {
        if (cache.containsKey(key)) {
            long age = System.currentTimeMillis() - timestamps.get(key);
            if (age < TTL) {
                return cache.get(key);  // Ainda válido
            }
            cache.remove(key);  // Expirou
        }
        // ... buscar do real ...
    }
    
    // 2. Invalidação explícita
    public void invalidate(String key) {
        cache.remove(key);
    }
    
    public void invalidateAll() {
        cache.clear();
    }
    
    // 3. LRU (Least Recently Used)
    private LinkedHashMap<String, Result> lruCache = 
        new LinkedHashMap<>(16, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > MAX_ENTRIES;
            }
        };
    
    // 4. Listener para mudanças no RealSubject
    public void onDataChanged(String key) {
        cache.remove(key);  // Invalida quando dados mudam
    }
}
```

### Q10: Proxy funciona com métodos estáticos?
**R**: **Não diretamente** - Proxy Pattern funciona com instâncias. Para métodos estáticos:

```java
// ❌ Não funciona - método estático
class ServiceReal {
    public static void execute() { ... }
}

class ServiceProxy {
    // Não pode interceptar chamadas estáticas!
}

// ✅ Solução 1: Tornar não-estático
interface Service {
    void execute();
}

class ServiceReal implements Service {
    public void execute() { ... }  // Instância
}

// ✅ Solução 2: Wrapper class
class StaticServiceWrapper {
    public void execute() {
        ServiceReal.execute();  // Delega para estático
    }
}

class ProxyForStaticService {
    private StaticServiceWrapper wrapper;
    public void execute() {
        // Lógica do proxy
        wrapper.execute();
    }
}
```

### Q11: Como Proxy se relaciona com Singleton?
**R**: Podem ser combinados:

```java
// Virtual Proxy usando Singleton para RealSubject
class DatabaseConnectionProxy implements Connection {
    private static DatabaseConnection realConnection;  // Singleton
    
    public void execute(String sql) {
        if (realConnection == null) {
            synchronized(DatabaseConnectionProxy.class) {
                if (realConnection == null) {
                    realConnection = DatabaseConnection.getInstance();
                }
            }
        }
        realConnection.execute(sql);
    }
}

// Ou Proxy pode ser Singleton
class ServiceProxy implements Service {
    private static ServiceProxy instance;
    private Service real;
    
    private ServiceProxy() {
        this.real = new ServiceReal();
    }
    
    public static ServiceProxy getInstance() {
        if (instance == null) {
            synchronized(ServiceProxy.class) {
                if (instance == null) {
                    instance = new ServiceProxy();
                }
            }
        }
        return instance;
    }
}
```

## 📝 Exercícios Práticos

### Exercício 1: Virtual Proxy para Base de Dados (Iniciante)
Implemente virtual proxy que adia conexão ao banco até primeira query.

**Requisitos**:
- Interface `DatabaseConnection` com métodos `connect()`, `query()`, `close()`
- `RealConnection` simula conexão cara (delay de 2 segundos)
- `ConnectionProxy` adia conexão até primeiro `query()`
- Log quando conexão é estabelecida

**Template**:
```java
interface DatabaseConnection {
    void query(String sql);
    void close();
}

class RealConnection implements DatabaseConnection {
    public RealConnection() {
        System.out.println("Estabelecendo conexão (operação cara)...");
        // Simular delay
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        System.out.println("Conectado!");
    }
    
    public void query(String sql) {
        System.out.println("Executando: " + sql);
    }
    
    public void close() {
        System.out.println("Conexão fechada");
    }
}

class ConnectionProxy implements DatabaseConnection {
    // Implemente lazy loading
}

// Teste
DatabaseConnection conn = new ConnectionProxy();  // Rápido!
// ... código pode nem usar conexão ...
conn.query("SELECT * FROM users");  // Agora conecta
```

### Exercício 2: Protection Proxy com Níveis de Acesso (Intermediário)
Sistema de documentos com controle de acesso por role.

**Requisitos**:
- Documentos têm níveis: PUBLIC, CONFIDENTIAL, SECRET
- Usuários têm roles: GUEST, USER, ADMIN
- GUEST: só PUBLIC
- USER: PUBLIC e CONFIDENTIAL
- ADMIN: todos
- Lançar `SecurityException` se acesso negado
- Log de tentativas de acesso

**Template**:
```java
enum AccessLevel { PUBLIC, CONFIDENTIAL, SECRET }
enum UserRole { GUEST, USER, ADMIN }

interface Document {
    String read();
    void write(String content);
    void delete();
}

class SecureDocument implements Document {
    private String content;
    private AccessLevel level;
    
    // Implemente métodos
}

class DocumentProxy implements Document {
    private SecureDocument document;
    private User currentUser;
    
    private boolean canRead() {
        // GUEST: PUBLIC
        // USER: PUBLIC, CONFIDENTIAL
        // ADMIN: todos
    }
    
    private boolean canWrite() {
        // USER e ADMIN podem escrever seus níveis
    }
    
    public String read() {
        if (!canRead()) {
            throw new SecurityException("Acesso negado");
        }
        log("READ attempt by " + currentUser);
        return document.read();
    }
    
    // Implemente write() e delete()
}
```

### Exercício 3: Cache Proxy com Estatísticas (Intermediário)
Proxy que cacheia resultados e mantém estatísticas de uso.

**Requisitos**:
- Interface `DataService` com `getData(String key)`
- Real service simula operação cara (500ms)
- Cache com TTL configurável (padrão 30 segundos)
- Estatísticas: hits, misses, hit rate, tempo médio
- Método `getStats()` retorna estatísticas
- Método `clearCache()` limpa cache e reseta stats

**Template**:
```java
interface DataService {
    String getData(String key);
}

class ExpensiveDataService implements DataService {
    public String getData(String key) {
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        return "Data for " + key;
    }
}

class CacheStats {
    long hits;
    long misses;
    long totalTime;
    
    double getHitRate() {
        long total = hits + misses;
        return total == 0 ? 0 : (double) hits / total * 100;
    }
    
    // Implemente outros métodos
}

class CachingProxy implements DataService {
    private DataService real;
    private Map<String, String> cache;
    private Map<String, Long> cacheTime;
    private CacheStats stats;
    private long TTL;
    
    public String getData(String key) {
        long start = System.currentTimeMillis();
        
        // Verificar cache
        // Atualizar estatísticas
        // Retornar resultado
        
        long duration = System.currentTimeMillis() - start;
        stats.totalTime += duration;
        return result;
    }
    
    public CacheStats getStats() {
        return stats;
    }
}

// Teste
DataService service = new CachingProxy(new ExpensiveDataService(), 30000);
service.getData("A");  // Miss - 500ms
service.getData("A");  // Hit - ~0ms
service.getData("B");  // Miss - 500ms
System.out.println("Hit rate: " + service.getStats().getHitRate() + "%");
```

### Exercício 4: Logging Proxy com AOP Style (Avançado)
Proxy genérico que adiciona logging a qualquer interface usando reflection.

**Requisitos**:
- Proxy dinâmico usando `java.lang.reflect.Proxy`
- Log antes e depois de cada método
- Log de exceções
- Medir e logar tempo de execução
- Funciona com qualquer interface
- Suporte a diferentes níveis de log (INFO, DEBUG, ERROR)

**Template**:
```java
class LoggingInvocationHandler implements InvocationHandler {
    private Object target;
    private Logger logger;
    
    public LoggingInvocationHandler(Object target) {
        this.target = target;
        this.logger = Logger.getLogger(target.getClass().getName());
    }
    
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        // Log antes
        logger.info("Calling " + method.getName());
        logger.debug("Arguments: " + Arrays.toString(args));
        
        long start = System.currentTimeMillis();
        Object result = null;
        
        try {
            result = method.invoke(target, args);
            return result;
        } catch (InvocationTargetException e) {
            logger.error("Exception in " + method.getName(), e.getCause());
            throw e.getCause();
        } finally {
            long duration = System.currentTimeMillis() - start;
            logger.info("Completed " + method.getName() + 
                       " in " + duration + "ms");
            logger.debug("Result: " + result);
        }
    }
}

class ProxyFactory {
    public static <T> T createLoggingProxy(T target, Class<T> interfaceType) {
        return (T) Proxy.newProxyInstance(
            interfaceType.getClassLoader(),
            new Class[] { interfaceType },
            new LoggingInvocationHandler(target)
        );
    }
}

// Uso - funciona com QUALQUER interface!
Calculator calc = new CalculatorImpl();
Calculator proxy = ProxyFactory.createLoggingProxy(calc, Calculator.class);
proxy.add(5, 3);  // Log automático!

UserService userService = new UserServiceImpl();
UserService proxy2 = ProxyFactory.createLoggingProxy(userService, UserService.class);
proxy2.createUser(user);  // Log automático!
```

### Exercício 5: Proxy Chain para Microservices (Avançado)
Sistema de proxies encadeados simulando chamadas a microserviços.

**Requisitos**:
- Interface `UserService` com operações CRUD
- Proxies encadeados:
  1. **CircuitBreakerProxy**: Abre circuito após N falhas, fecha após timeout
  2. **RetryProxy**: Retry automático com backoff exponencial
  3. **CacheProxy**: Cache com invalidação inteligente
  4. **LoggingProxy**: Log de todas operações
  5. **MetricsProxy**: Coleta métricas (latência, taxa de erro)
- Simular falhas aleatórias no service real
- Dashboard com métricas em tempo real

**Template**:
```java
interface UserService {
    User getUser(String id);
    void updateUser(User user);
    void deleteUser(String id);
}

class CircuitBreakerProxy implements UserService {
    private enum State { CLOSED, OPEN, HALF_OPEN }
    private State state = State.CLOSED;
    private int failureCount = 0;
    private int threshold = 5;
    private long openTime;
    private long timeout = 60000;  // 1 minuto
    
    public User getUser(String id) {
        if (state == State.OPEN) {
            if (System.currentTimeMillis() - openTime > timeout) {
                state = State.HALF_OPEN;
            } else {
                throw new CircuitBreakerOpenException();
            }
        }
        
        try {
            User user = realService.getUser(id);
            onSuccess();
            return user;
        } catch (Exception e) {
            onFailure();
            throw e;
        }
    }
    
    private void onSuccess() {
        failureCount = 0;
        if (state == State.HALF_OPEN) {
            state = State.CLOSED;
        }
    }
    
    private void onFailure() {
        failureCount++;
        if (failureCount >= threshold) {
            state = State.OPEN;
            openTime = System.currentTimeMillis();
        }
    }
}

class RetryProxy implements UserService {
    private int maxRetries = 3;
    private long initialDelay = 100;
    
    public User getUser(String id) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                return realService.getUser(id);
            } catch (Exception e) {
                attempt++;
                if (attempt >= maxRetries) throw e;
                
                long delay = initialDelay * (long)Math.pow(2, attempt);
                Thread.sleep(delay);  // Exponential backoff
            }
        }
        throw new MaxRetriesExceededException();
    }
}

// Montar chain
UserService real = new UserServiceImpl();
UserService cached = new CacheProxy(real);
UserService retry = new RetryProxy(cached);
UserService circuit = new CircuitBreakerProxy(retry);
UserService logged = new LoggingProxy(circuit);
UserService final = new MetricsProxy(logged);

// Cliente usa proxy final
User user = finalProxy.getUser("123");
```

## 🎓 Análise Acadêmica

### Princípios SOLID Aplicados

#### 1. Single Responsibility Principle (SRP)
- **RealSubject**: Responsável apenas pela lógica de negócio
- **Proxy**: Responsável apenas por controle de acesso/otimização
- Separação clara de concerns

#### 2. Open/Closed Principle (OCP)
- Pode adicionar novos proxies sem modificar RealSubject
- Proxies podem ser encadeados para compor funcionalidades
```java
// Novo proxy não afeta código existente
class NewProxy implements Service {
    private Service real;
    // Nova funcionalidade
}
```

#### 3. Liskov Substitution Principle (LSP)
- Proxy pode substituir RealSubject sem quebrar funcionalidade
- Cliente não precisa saber qual está usando
- Comportamento deve ser consistente

#### 4. Dependency Inversion Principle (DIP)
- Cliente depende de interface (Subject), não de implementações
- Proxy e RealSubject dependem de abstração

### Análise de Performance

| Tipo de Proxy | Overhead | Quando Compensa |
|---------------|----------|-----------------|
| Virtual Proxy | Baixo (~1 check) | Objeto caro e pode não ser usado |
| Cache Proxy | Médio (lookup) | Operação muito mais cara que lookup |
| Protection Proxy | Baixo (~1-2 checks) | Segurança é necessária |
| Remote Proxy | Alto (network) | Inevitável para objetos remotos |
| Logging Proxy | Baixo-Médio | Benefício de debugging vale overhead |

**Medições típicas** (operação de 1ms):
- Virtual Proxy: +0.001ms (check if null)
- Cache Proxy: +0.01ms (HashMap lookup)
- Protection Proxy: +0.005ms (permission check)
- Logging Proxy: +0.1ms (I/O de log)

### Trade-offs Arquiteturais

| Sem Proxy | Com Proxy |
|-----------|-----------|
| Mais simples | Mais flexível |
| Mais rápido (direto) | Controle fino |
| Sem overhead | Pequeno overhead |
| Difícil adicionar funcionalidades | Fácil estender |
| Acoplamento direto | Desacoplado |

### Padrões Relacionados e Sinergias

#### Proxy + Singleton
```java
// RealSubject como Singleton, Proxy gerencia acesso
class ServiceProxy implements Service {
    private static Service instance;
    
    public static Service getInstance() {
        if (instance == null) {
            instance = ServiceReal.getInstance();
        }
        return instance;
    }
}
```

#### Proxy + Factory
```java
// Factory cria proxy apropriado
class ServiceFactory {
    public static Service createService(boolean useCache) {
        Service real = new ServiceReal();
        return useCache ? new CacheProxy(real) : real;
    }
}
```

#### Proxy + Strategy
```java
// Proxy usa diferentes estratégias
class CacheProxy {
    private CacheStrategy strategy;  // LRU, LFU, TTL
    
    public void setStrategy(CacheStrategy strategy) {
        this.strategy = strategy;
    }
}
```

## 🔍 Detecção de Code Smells

**Você precisa de Proxy quando vê**:

### 1. Criação Cara Sem Lazy Loading
```java
// Smell: Sempre cria, mesmo se não usar
class Application {
    private HeavyResource resource = new HeavyResource();  // Cara!
    
    public void run() {
        // Pode nem usar resource
    }
}

// Solução: Virtual Proxy
class Application {
    private HeavyResourceProxy resource = new HeavyResourceProxy();
    // Cria apenas se usar
}
```

### 2. Código de Segurança Espalhado
```java
// Smell: Verificação duplicada em todo lugar
class Client1 {
    public void use() {
        if (!user.hasPermission()) throw new SecurityException();
        resource.operation();
    }
}

class Client2 {
    public void use() {
        if (!user.hasPermission()) throw new SecurityException();  // Duplicado!
        resource.operation();
    }
}

// Solução: Protection Proxy
class ProtectedResource {
    public void operation() {
        if (!user.hasPermission()) throw new SecurityException();
        real.operation();
    }
}
```

### 3. Cache Manual Espalhado
```java
// Smell: Lógica de cache em vários lugares
class Service1 {
    private Map<String, Result> cache = new HashMap<>();
    public Result get(String key) {
        if (cache.contains(key)) return cache.get(key);
        // ...
    }
}

// Solução: Cache Proxy centralizado
class CacheProxy {
    // Lógica de cache centralizada
}
```

## 📚 Referências e Leitura Adicional

### Livros
1. **"Design Patterns: Elements of Reusable Object-Oriented Software"** - Gang of Four
   - Capítulo Proxy (páginas 207-217)

2. **"Head First Design Patterns"** - Freeman & Freeman
   - Capítulo 11: Proxy Pattern

3. **"Pattern-Oriented Software Architecture Volume 3"** - Buschmann et al
   - Resource Lifecycle Manager patterns

### Implementações no Java
- `java.lang.reflect.Proxy` - Dynamic proxies
- `java.rmi.*` - Remote Method Invocation proxies
- Hibernate lazy loading
- Spring AOP proxies
- Collections.synchronized*/unmodifiable* - Wrapper proxies

### Frameworks que Usam Proxy
- **Spring Framework**: AOP, @Transactional, @Cacheable
- **Hibernate/JPA**: Lazy loading de entidades
- **Mockito**: Mock objects são proxies
- **CGLib/ByteBuddy**: Criação dinâmica de proxies

### Artigos
- [Refactoring Guru - Proxy](https://refactoring.guru/design-patterns/proxy)
- [SourceMaking - Proxy](https://sourcemaking.com/design_patterns/proxy)
- [Java Dynamic Proxies](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Proxy.html)
