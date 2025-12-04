# Facade Pattern

O padrão Facade fornece uma interface unificada e simplificada para um conjunto complexo de interfaces em um subsistema. Ele define uma interface de nível mais alto que torna o subsistema mais fácil de usar, sem esconder as funcionalidades originais para casos avançados.

## 🎯 Problema

Subsistemas complexos com muitas classes interdependentes e interfaces complicadas são difíceis de usar e entender. Clientes precisam:
- Conhecer muitos detalhes internos de implementação
- Lidar com múltiplas classes e suas dependências
- Seguir sequências complexas de chamadas
- Repetir código de configuração em vários lugares
- Acoplar-se diretamente às implementações do subsistema

### Exemplo Real - Home Theater
Para assistir um filme em um home theater completo, você precisa:
1. Ligar o amplificador e configurar entrada de áudio
2. Definir o volume para 5
3. Ligar o projetor e colocar em modo widescreen
4. Baixar a tela de projeção
5. Ligar o DVD player
6. Colocar o DVD e pressionar play
7. Ajustar as luzes para modo cinema

**Imagine fazer isso toda vez!** Uma facade simplifica tudo isso para: `homeTheater.assistirFilme("Matrix")`.

### Outros Exemplos Comuns
- **Biblioteca de Compilação**: GCC tem centenas de opções, mas makefiles fornecem interface simples
- **Frameworks Web**: Spring Boot oculta complexidade de configuração com `@SpringBootApplication`
- **APIs de Terceiros**: Cliente REST facade para API complexa com autenticação, retry, parsing
- **Bancos de Dados**: JPA/Hibernate simplifica JDBC complexo

## 💡 Solução

Criar uma classe Facade que:
1. **Fornece interface simples e de alto nível** para operações comuns
2. **Encapsula complexidade** do subsistema e suas interdependências
3. **Delega chamadas** para os objetos apropriados do subsistema na ordem correta
4. **Não impede acesso direto** - subsistema continua acessível para casos avançados
5. **Agrupa operações relacionadas** em métodos convenientes

### Características Principais
- **Simplificação**: Interface mais simples que as do subsistema
- **Desacoplamento**: Clientes não dependem diretamente de classes do subsistema
- **Ponto de entrada único**: Um lugar para começar a usar o subsistema
- **Configuração padrão**: Valores sensatos para casos de uso comuns
- **Flexibilidade preservada**: Acesso direto ainda possível quando necessário

## 🏗️ Estrutura

```
┌─────────────┐
│   Cliente   │  ←  Interface simples, não conhece subsistema
└─────────────┘
       │
       ▼
┌─────────────┐      ┌──────────────┐
│   Facade    │─────>│ SubsistemaA  │  ← Classes complexas
└─────────────┘      └──────────────┘      do subsistema
       │             ┌──────────────┐
       ├────────────>│ SubsistemaB  │
       │             └──────────────┘
       │             ┌──────────────┐
       └────────────>│ SubsistemaC  │
                     └──────────────┘
```

### Participantes

- **Facade**: 
  - Conhece quais classes do subsistema são responsáveis por cada operação
  - Delega requisições para objetos apropriados do subsistema
  - Fornece métodos convenientes que encapsulam fluxos complexos

- **Classes do Subsistema**:
  - Implementam funcionalidades especializadas
  - Não conhecem a Facade (sem dependência reversa)
  - Podem ser usadas diretamente se necessário

- **Cliente**:
  - Usa Facade ao invés de interagir com subsistema diretamente
  - Mais simples, menos acoplado
  - Pode acessar subsistema diretamente para casos avançados

## 📝 Implementações

### Sistema de Home Theater

Este exemplo demonstra como simplificar o controle de um sistema de home theater complexo.

- **[DVDPlayer.java](DVDPlayer.java)** - Subsistema para reprodução de DVD (ligar, desligar, play, pause, stop)
- **[Amplificador.java](Amplificador.java)** - Subsistema de áudio (volume, entrada, surround)
- **[Projetor.java](Projetor.java)** - Subsistema de projeção (ligar, modo widescreen, desligar)
- **[Luzes.java](Luzes.java)** - Subsistema de iluminação (dim, bright)
- **[HomeTheaterFacade.java](HomeTheaterFacade.java)** - Facade que unifica tudo em interface simples
- **[TesteFacade.java](TesteFacade.java)** - Demonstração comparando uso com e sem facade

### Operações Simplificadas

**Sem Facade** (Complexo - 7+ passos):
```java
amplificador.ligar();
amplificador.setVolume(5);
dvd.ligar();
dvd.play(filme);
projetor.ligar();
projetor.wideScreenMode();
luzes.dim(10);
```

**Com Facade** (Simples - 1 chamada):
```java
homeTheater.assistirFilme(filme);
```

## 🚀 Como Executar

```bash
# Navegar para o diretório
cd facade/

# Compilar todos os arquivos
javac *.java

# Executar o teste
java TesteFacade
```

## 📊 Exemplo de Saída Esperada

```
=== Home Theater - SEM Facade (complexo) ===
[Amplificador] Ligando...
[Amplificador] Definindo volume para 5
[Amplificador] Definindo entrada: DVD
[DVD Player] Ligando...
[DVD Player] Reproduzindo: Matrix
[Projetor] Ligando...
[Projetor] Modo widescreen ativado
[Luzes] Diminuindo intensidade para 10%

Assistindo filme... 🍿

[DVD Player] Pausando...
[Projetor] Desligando...
[Amplificador] Desligando...
[DVD Player] Desligando...
[Luzes] Iluminação normal restaurada

=== Home Theater - COM Facade (simples) ===
Iniciando sistema de home theater...
[Amplificador] Ligando...
[Amplificador] Definindo volume para 5
[Amplificador] Definindo entrada: DVD
[DVD Player] Ligando...
[DVD Player] Reproduzindo: Matrix
[Projetor] Ligando...
[Projetor] Modo widescreen ativado
[Luzes] Diminuindo intensidade para 10%
Pronto! Aproveite o filme! 🎬

Assistindo filme... 🍿

Encerrando sistema de home theater...
[DVD Player] Parando...
[DVD Player] Desligando...
[Amplificador] Desligando...
[Projetor] Desligando...
[Luzes] Iluminação normal restaurada
Tudo desligado! ✓
```

## ✅ Vantagens

1. **Simplicidade para o Cliente**
   - Interface mais fácil de usar e entender
   - Reduz curva de aprendizado do subsistema
   - Menos erros de uso incorreto
   - Código cliente mais legível e mantível

2. **Desacoplamento**
   - Cliente não depende diretamente de classes do subsistema
   - Mudanças internas não afetam cliente
   - Facilita substituição de componentes do subsistema
   - Permite evolução independente

3. **Manutenibilidade Melhorada**
   - Mudanças no subsistema isoladas na Facade
   - Um lugar para atualizar quando subsistema muda
   - Lógica de coordenação centralizada
   - Easier to test (mock facade vs multiple subsystem classes)

4. **Redução de Dependências**
   - Cliente tem apenas uma dependência (Facade) ao invés de muitas
   - Grafo de dependências mais simples
   - Menos acoplamento entre camadas
   - Facilita migração para novos subsistemas

5. **Promoção de Boas Práticas**
   - Encoraja uso correto do subsistema
   - Previne uso inadequado de APIs internas
   - Documenta fluxos de trabalho recomendados
   - Fornece configuração padrão sensata

6. **Organização e Estrutura**
   - Agrupa funcionalidades relacionadas
   - Define ponto de entrada claro
   - Melhora arquitetura em camadas
   - Facilita onboarding de novos desenvolvedores

## ⚠️ Desvantagens

1. **Risco de God Object**
   - Facade pode se tornar muito grande e fazer demais
   - Viola Single Responsibility se não cuidadoso
   - Pode virar dumping ground para toda lógica
   - Solução: Dividir em múltiplas facades especializadas

2. **Limitação de Funcionalidades**
   - Pode não expor todas as capacidades do subsistema
   - Casos avançados podem precisar contornar facade
   - Trade-off entre simplicidade e completude
   - Solução: Manter subsistema acessível diretamente

3. **Acoplamento Interno**
   - Facade fica acoplada ao subsistema
   - Mudanças significativas no subsistema afetam Facade
   - Precisa conhecer detalhes de implementação
   - Pode se tornar frágil se subsistema muda frequentemente

4. **Complexidade Escondida**
   - Pode ocultar problemas de desempenho
   - Dificulta debug de issues no subsistema
   - Abstrações podem vazar (leaky abstraction)
   - Desenvolvedores podem não entender o que acontece por baixo

5. **Overhead Adicional**
   - Camada extra de indireção
   - Pequeno overhead de performance
   - Mais código para manter
   - Pode ser desnecessário para subsistemas já simples

6. **Múltiplos Facades**
   - Se várias facades para mesmo subsistema, pode confundir
   - Inconsistência entre facades diferentes
   - Duplicação de lógica
   - Solução: Definir clara separação de responsabilidades

## 🎯 Quando Usar

✅ **Use Facade quando**:
- Quer fornecer interface simples para subsistema complexo
- Subsistema tem muitas classes interdependentes difíceis de usar
- Há muitas dependências entre clientes e classes de implementação
- Quer estruturar subsistema em camadas (facade para cada camada)
- Precisa desacoplar cliente de detalhes de implementação
- Deseja um ponto de entrada único e intuitivo
- Quer padronizar como subsistema é usado

❌ **Evite Facade quando**:
- Subsistema já é simples e fácil de usar
- Clientes precisam de acesso direto a todas as funcionalidades do subsistema
- Facade ficaria apenas repassando chamadas sem adicionar valor
- Subsistema tem apenas 1-2 classes
- Performance é crítica e não pode ter camada extra
- Subsistema muda frequentemente (facade precisaria mudar também)

## 💼 Casos de Uso Reais

### 1. Java Database Connectivity (JDBC) - DataSource Facade
```java
// Sem Facade: JDBC puro (complexo)
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection(url, user, pwd);
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, param);
ResultSet rs = stmt.executeQuery();
// ... processar, fechar recursos ...

// Com Facade: JdbcTemplate do Spring (simples)
JdbcTemplate jdbc = new JdbcTemplate(dataSource);
List<User> users = jdbc.query(sql, new BeanPropertyRowMapper<>(User.class));
```

### 2. Spring Framework - @SpringBootApplication
```java
// Facade que configura automaticamente:
// - Component scanning
// - Auto-configuration
// - Property source loading
@SpringBootApplication  // Facade annotation
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);  // Facade method
    }
}

// Sem facade, precisaria:
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan
// + configuração manual de dezenas de componentes
```

### 3. SLF4J - Logging Facade
```java
// Facade para diferentes implementações de log
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClass {
    private static final Logger log = LoggerFactory.getLogger(MyClass.class);
    
    public void method() {
        log.info("Mensagem");  // Funciona com Log4j, Logback, JUL...
    }
}

// Cliente não conhece implementação concreta (Log4j2, Logback, etc.)
// Apenas troca dependência para mudar implementação
```

### 4. Hibernate/JPA - EntityManager Facade
```java
// Facade simplifica operações complexas de persistência
EntityManager em = entityManagerFactory.createEntityManager();

em.getTransaction().begin();
User user = new User("João");
em.persist(user);               // Insert simples
em.getTransaction().commit();

User found = em.find(User.class, 1);  // Select simples
em.remove(found);               // Delete simples

// Esconde: SQL generation, connection management, transaction handling,
// caching, dirty checking, lazy loading, etc.
```

### 5. Apache Commons Email - Email Facade
```java
// Facade simplifica JavaMail complexo
Email email = new SimpleEmail();
email.setHostName("smtp.gmail.com");
email.setSmtpPort(587);
email.setFrom("from@example.com");
email.addTo("to@example.com");
email.setSubject("Test");
email.setMsg("Mensagem");
email.send();  // Uma linha vs dezenas com JavaMail puro

// Esconde: Session, Transport, MimeMessage, Address parsing, etc.
```

### 6. RestTemplate - HTTP Client Facade (Spring)
```java
// Facade para operações HTTP
RestTemplate rest = new RestTemplate();

// GET simples
User user = rest.getForObject("http://api/users/1", User.class);

// POST simples
User created = rest.postForObject("http://api/users", newUser, User.class);

// Esconde: Connection management, serialization/deserialization,
// error handling, header setup, etc.
```

## 🔄 Facade vs Outros Padrões

### Facade vs Adapter
| Aspecto | Facade | Adapter |
|---------|--------|---------|
| **Propósito** | Simplificar interface complexa | Tornar interfaces incompatíveis compatíveis |
| **Número de classes** | Múltiplas classes do subsistema | Tipicamente uma classe |
| **Interface** | Nova interface simplificada | Converte interface existente |
| **Foco** | Facilitar uso | Compatibilidade |
| **Quando usar** | Subsistema complexo | Interface incompatível |

**Exemplo**:
```java
// Facade - simplifica múltiplas classes
class HomeTheaterFacade {
    void watchMovie() {
        // Orquestra DVD, Amplifier, Projector, Lights
    }
}

// Adapter - adapta uma interface
class PayPalAdapter implements PaymentProcessor {
    private PayPalAPI paypal;
    // Adapta interface do PayPal
}
```

### Facade vs Proxy
| Aspecto | Facade | Proxy |
|---------|--------|-------|
| **Interface** | Diferente e mais simples | Mesma interface do objeto real |
| **Propósito** | Simplificar | Controlar acesso |
| **Objeto** | Subsistema com múltiplas classes | Único objeto |
| **Substituição** | Não substitui subsistema | Substitui objeto real |

**Exemplo**:
```java
// Facade - interface diferente
class ComputerFacade {
    void start() { cpu.load(); memory.load(); disk.read(); }
}

// Proxy - mesma interface
class ImageProxy implements Image {
    void display() { 
        if (realImage == null) realImage = new RealImage();
        realImage.display(); 
    }
}
```

### Facade vs Mediator
| Aspecto | Facade | Mediator |
|---------|--------|----------|
| **Comunicação** | Unidirecional (cliente → subsistema) | Bidirecional (componentes ↔ mediator) |
| **Conhecimento** | Subsistema não conhece Facade | Componentes conhecem Mediator |
| **Objetivo** | Simplificar interface | Desacoplar componentes |
| **Encapsulamento** | Encapsula subsistema | Encapsula interações |

**Exemplo**:
```java
// Facade - subsistema não conhece facade
class DatabaseFacade {
    void saveUser(User u) { 
        connection.save(u);  // Connection não conhece Facade
    }
}

// Mediator - componentes conhecem mediator
class ChatRoom {  // Mediator
    void sendMessage(String msg, User from) { ... }
}
class User {
    private ChatRoom chatRoom;  // Conhece Mediator
    void send(String msg) { chatRoom.sendMessage(msg, this); }
}
```

## 💻 Implementação Detalhada

### Estrutura Básica
```java
// Classes do subsistema (complexas)
class CPU {
    void freeze() { System.out.println("CPU freeze"); }
    void jump(long position) { System.out.println("CPU jump: " + position); }
    void execute() { System.out.println("CPU execute"); }
}

class Memory {
    void load(long position, byte[] data) {
        System.out.println("Memory load");
    }
}

class HardDrive {
    byte[] read(long lba, int size) {
        System.out.println("HardDrive read");
        return new byte[size];
    }
}

// Facade (simples)
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;
    
    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
    
    public void start() {
        cpu.freeze();
        memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
        cpu.jump(BOOT_ADDRESS);
        cpu.execute();
    }
}

// Cliente (muito simples!)
public class Main {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();  // Uma linha vs muitas
    }
}
```

### Facade com Múltiplos Perfis
```java
// Facade pode oferecer diferentes níveis de controle
public class VideoConverterFacade {
    
    // Método simples - usa defaults
    public void convertQuick(String filename) {
        convert(filename, "mp4", "720p", "default");
    }
    
    // Método intermediário - alguns parâmetros
    public void convert(String filename, String format) {
        convert(filename, format, "720p", "default");
    }
    
    // Método completo - controle total
    public void convert(String filename, String format, 
                       String quality, String codec) {
        // Orquestra VideoFile, Codec, AudioMixer, BitrateReader
        VideoFile file = new VideoFile(filename);
        Codec codec = CodecFactory.extract(codec);
        AudioMixer audio = new AudioMixer();
        BitrateReader reader = new BitrateReader(quality);
        
        // ... conversão complexa ...
    }
}
```

### Facade com Factory
```java
// Combinar Facade com Factory para máxima simplicidade
public class HomeTheaterFacadeFactory {
    
    public static HomeTheaterFacade createStandard() {
        return new HomeTheaterFacade(
            new DVDPlayer(),
            new Amplificador(),
            new Projetor(),
            new Luzes()
        );
    }
    
    public static HomeTheaterFacade createPremium() {
        return new HomeTheaterFacade(
            new BluRayPlayer(),     // Melhor player
            new SurroundAmplifier(), // Som surround
            new ProjetorLaser(),     // Melhor projetor
            new SmartLights()        // Luzes inteligentes
        );
    }
}

// Cliente ainda mais simples
HomeTheaterFacade theater = HomeTheaterFacadeFactory.createStandard();
```

## 🔗 Navegação

- [Voltar para Padrões Estruturais](../)
- [Anterior: Decorator](../decorator/)
- [Próximo: Composite](../composite/)

## ❓ Perguntas e Respostas Frequentes

### Q1: Facade viola o princípio de Single Responsibility por fazer muitas coisas?
**R**: Não necessariamente. A responsabilidade única da Facade é **coordenar e simplificar o acesso ao subsistema**. Porém, se a Facade começa a ter lógica de negócio complexa ou responsabilidades não relacionadas, aí sim viola SRP. Solução: dividir em múltiplas Facades especializadas.

```java
// ❌ Ruim - Facade com múltiplas responsabilidades
class SystemFacade {
    void processOrder() { ... }      // E-commerce
    void sendEmail() { ... }         // Comunicação
    void generateReport() { ... }    // Relatórios
    void calculateTax() { ... }      // Finanças
}

// ✅ Bom - Facades especializadas
class OrderFacade { void processOrder() { ... } }
class EmailFacade { void sendEmail() { ... } }
class ReportFacade { void generateReport() { ... } }
class TaxFacade { void calculateTax() { ... } }
```

### Q2: Devo criar uma Facade para cada subsistema ou uma Facade geral?
**R**: Depende da complexidade e do contexto:
- **Facade por subsistema**: Melhor para sistemas grandes, mantém SRP, facilita manutenção
- **Facade geral**: Aceitável para sistemas menores ou quando subsistemas estão intimamente relacionados

**Regra prática**: Se sua Facade tem mais de 10-15 métodos públicos, considere dividir.

### Q3: Cliente pode acessar subsistema diretamente ou deve ser forçado a usar Facade?
**R**: **Cliente deve poder acessar diretamente**. Facade não deve ser gateway obrigatório. Razões:
- Casos avançados podem precisar de controle fino
- Facade pode não expor 100% das funcionalidades
- Força uso de Facade cria acoplamento rígido

```java
// ✅ Bom - ambos acessíveis
HomeTheaterFacade facade = new HomeTheaterFacade();
facade.watchMovie("Matrix");  // Simples

// Para casos avançados, acesso direto
Amplificador amp = facade.getAmplificador();  // Expõe se necessário
amp.setVolume(11);  // Controle fino quando preciso
```

### Q4: Facade deve manter estado ou ser stateless?
**R**: **Geralmente stateless é melhor**, mas depende:

```java
// Stateless - mais simples, thread-safe
class EmailFacade {
    public void send(EmailConfig config, String to, String msg) {
        // Cria objetos a cada chamada
        EmailSender sender = new EmailSender(config);
        sender.send(to, msg);
    }
}

// Stateful - melhor performance, reutiliza conexões
class DatabaseFacade {
    private Connection connection;  // Mantém estado
    
    public DatabaseFacade(String url) {
        this.connection = DriverManager.getConnection(url);
    }
    
    public void save(User user) {
        // Reutiliza connection
    }
    
    public void close() {
        connection.close();
    }
}
```

**Quando usar cada**:
- **Stateless**: Operações simples, sem recursos caros, ambiente concorrente
- **Stateful**: Recursos caros (conexões, pools), necessário manter contexto

### Q5: Como testar código que usa Facade? Devo mockar a Facade ou o subsistema?
**R**: **Mockar a Facade** é mais simples e mantém testes desacoplados:

```java
// ✅ Bom - Mockar facade
@Test
public void testOrderProcessing() {
    PaymentFacade paymentMock = mock(PaymentFacade.class);
    when(paymentMock.processPayment(any())).thenReturn(true);
    
    OrderService service = new OrderService(paymentMock);
    assertTrue(service.placeOrder(order));
}

// ❌ Evitar - Mockar todo subsistema
@Test
public void testOrderProcessing() {
    PayPal paypal = mock(PayPal.class);
    StripeAPI stripe = mock(StripeAPI.class);
    TransactionLog log = mock(TransactionLog.class);
    // ... muitos mocks ...
}
```

Para testar a Facade em si, teste de integração com subsistema real ou mocks do subsistema.

### Q6: Facade pode chamar outra Facade?
**R**: **Sim, mas com moderação**. Facades podem compor:

```java
class EcommerceFacade {
    private OrderFacade orderFacade;
    private PaymentFacade paymentFacade;
    private ShippingFacade shippingFacade;
    
    public void completePurchase(Cart cart) {
        Order order = orderFacade.createOrder(cart);
        paymentFacade.processPayment(order);
        shippingFacade.scheduleShipment(order);
    }
}
```

**Cuidado**: Não criar hierarquia profunda de Facades (max 2-3 níveis) para evitar complexidade.

### Q7: Qual a diferença entre Facade e Service Layer (arquitetura)?
**R**: Conceitos relacionados mas distintos:

- **Facade**: Padrão de design, simplifica interface de subsistema técnico
- **Service Layer**: Padrão arquitetural, encapsula lógica de negócio

```java
// Service Layer - lógica de negócio
@Service
public class OrderService {
    public void placeOrder(Order order) {
        validateOrder(order);        // Regra de negócio
        calculateDiscounts(order);   // Regra de negócio
        applyTaxes(order);          // Regra de negócio
        saveOrder(order);
    }
}

// Facade - simplifica subsistema técnico
public class DatabaseFacade {
    public void save(Order order) {
        connection.open();
        transaction.begin();
        repository.save(order);
        transaction.commit();
        connection.close();
    }
}

// Service pode usar Facade
@Service
public class OrderService {
    private DatabaseFacade dbFacade;
    
    public void placeOrder(Order order) {
        // lógica de negócio...
        dbFacade.save(order);  // Usa facade para persistência
    }
}
```

### Q8: Como evoluir Facade sem quebrar clientes existentes?
**R**: Estratégias de evolução:

```java
// 1. Adicionar novos métodos (não quebra código existente)
class EmailFacade {
    public void send(String to, String msg) { ... }           // V1
    public void sendWithAttachment(String to, String msg, File f) { ... }  // V2
}

// 2. Versionar Facade
interface EmailFacade { ... }
class EmailFacadeV1 implements EmailFacade { ... }
class EmailFacadeV2 implements EmailFacade { ... }

// 3. Usar @Deprecated e fornecer alternativa
class PaymentFacade {
    @Deprecated
    public void process(Payment p) { 
        processPayment(p);  // Delega para novo método
    }
    
    public void processPayment(Payment p) { ... }  // Novo método
}

// 4. Configuração via Builder
class EmailFacade {
    public static Builder builder() { return new Builder(); }
    
    public static class Builder {
        public Builder withAuth(String user, String pwd) { ... }
        public Builder withSSL(boolean ssl) { ... }
        public EmailFacade build() { ... }
    }
}
```

### Q9: Facade pode lançar suas próprias exceções ou deve propagar exceções do subsistema?
**R**: **Facade deve abstrair exceções do subsistema**:

```java
// ❌ Ruim - vaza exceções do subsistema
class DatabaseFacade {
    public User findUser(int id) throws SQLException {  // Expõe detalhe de implementação
        // ...
    }
}

// ✅ Bom - exceções próprias
class DatabaseFacade {
    public User findUser(int id) throws DataAccessException {  // Abstrato
        try {
            // ... JDBC code ...
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao buscar usuário", e);
        }
    }
}

// ✅ Também bom - usar unchecked exceptions
class DatabaseFacade {
    public User findUser(int id) {
        try {
            // ... JDBC code ...
        } catch (SQLException e) {
            throw new DataAccessRuntimeException("Erro ao buscar usuário", e);
        }
    }
}
```

Benefícios: Cliente não acopla a implementação, facilita trocar subsistema.

### Q10: Quando criar Facade: no início do projeto ou depois (refactoring)?
**R**: **Ambos são válidos**:

**Criar no início** quando:
- Sabe que subsistema será complexo
- Definindo arquitetura de camadas
- Integrando biblioteca/framework conhecido por ser complexo

**Criar depois (refactoring)** quando:
- Code smell: cliente duplicando código de coordenação
- Cliente acoplado a muitas classes do subsistema
- Testes difíceis por muitas dependências

```java
// Refactoring típico: extrair Facade
// Antes - cliente faz muitas chamadas
public void processPayment(Payment p) {
    paypal.init();
    paypal.setCredentials(user, pwd);
    paypal.connect();
    Transaction tx = paypal.startTransaction(p.getAmount());
    tx.setDescription(p.getDescription());
    tx.execute();
    logger.log(tx.getId());
}

// Depois - Facade encapsula
public void processPayment(Payment p) {
    paymentFacade.process(p);
}
```

### Q11: Facade pode ter lógica de negócio ou apenas coordenação?
**R**: **Ideal é apenas coordenação**, mas pode ter lógica simples:

```java
// ✅ Bom - apenas coordenação
class OrderFacade {
    public void placeOrder(Order order) {
        inventoryService.reserve(order.getItems());
        paymentService.charge(order.getTotal());
        shippingService.schedule(order);
        notificationService.sendConfirmation(order);
    }
}

// ⚠️ Aceitável - lógica simples de validação/transformação
class OrderFacade {
    public void placeOrder(Order order) {
        if (order.getTotal() <= 0) {  // Validação simples OK
            throw new IllegalArgumentException("Total inválido");
        }
        
        // Coordenação
        inventoryService.reserve(order.getItems());
        // ...
    }
}

// ❌ Evitar - lógica de negócio complexa
class OrderFacade {
    public void placeOrder(Order order) {
        // Cálculos complexos de desconto, impostos, frete...
        // Regras de negócio complexas...
        // Isso deveria estar em Service Layer, não Facade!
    }
}
```

**Regra**: Se lógica é complexa ou específica do domínio, vai para Service, não Facade.

## 📝 Exercícios Práticos

### Exercício 1: Facade para Sistema de Pagamentos (Iniciante)
Crie facade para processar pagamentos com diferentes gateways.

**Requisitos**:
- Suporte a PayPal, Stripe, PagSeguro
- Cada gateway tem API diferente (simule com classes)
- Facade unifica interface: `processarPagamento(valor, cartao)`
- Tratamento de erros unificado
- Log de transações

**Template**:
```java
// Subsistema - APIs diferentes
class PayPalAPI {
    void authenticate(String key) { ... }
    String createPayment(double amount) { ... }
    boolean execute(String paymentId) { ... }
}

class StripeAPI {
    void setApiKey(String key) { ... }
    boolean charge(String cardToken, int amountCents) { ... }
}

// Facade
class PaymentFacade {
    private PayPalAPI paypal;
    private StripeAPI stripe;
    
    public boolean processarPagamento(double valor, String metodoPagamento) {
        // Implemente: escolhe gateway, faz autenticação, processa, loga
    }
}

// Cliente
PaymentFacade payment = new PaymentFacade();
boolean sucesso = payment.processarPagamento(150.00, "paypal");
```

**Desafio extra**: Adicionar retry automático e fallback para gateway alternativo.

### Exercício 2: Facade para Conversão de Documentos (Intermediário)
Sistema de conversão entre formatos de documentos.

**Requisitos**:
- Conversão: PDF↔Word, PDF↔Image, Word↔HTML
- Cada conversão usa biblioteca diferente (simule)
- Facade: `converter(File origem, String formatoDestino)`
- Detecção automática de formato origem
- Compressão opcional
- Progress callback para conversões longas

**Template**:
```java
interface ConversorDocumento {
    File converter(File origem, String formatoDestino);
}

class PDFConverter {
    byte[] toImage(File pdf, String imageFormat) { ... }
    byte[] toWord(File pdf) { ... }
}

class WordConverter {
    byte[] toPDF(File docx) { ... }
    String toHTML(File docx) { ... }
}

class DocumentFacade {
    private PDFConverter pdfConverter;
    private WordConverter wordConverter;
    private ImageProcessor imageProcessor;
    
    public File converter(File origem, String formatoDestino, 
                         ConversionOptions options) {
        // Detectar formato origem
        // Escolher conversor apropriado
        // Aplicar opções (compressão, qualidade)
        // Retornar resultado
    }
}
```

**Teste**:
```java
DocumentFacade facade = new DocumentFacade();
File resultado = facade.converter(
    new File("document.pdf"), 
    "docx",
    ConversionOptions.builder()
        .withCompression(true)
        .withQuality("high")
        .build()
);
```

### Exercício 3: Facade para Sistema de Notificações Multi-Canal (Intermediário)
Sistema que envia notificações por email, SMS, push e Slack.

**Requisitos**:
- Canais: Email (SMTP), SMS (Twilio), Push (Firebase), Slack (Webhook)
- Cada canal tem configuração e API próprias
- Facade: `enviarNotificacao(Usuario, Mensagem, List<Canal>)`
- Envio paralelo para múltiplos canais
- Retry e fallback por canal
- Templates de mensagem por canal

**Template**:
```java
class EmailSender {
    void configure(String smtp, int port, String user, String pwd) { ... }
    void send(String to, String subject, String body) { ... }
}

class SMSSender {
    void setCredentials(String accountSid, String authToken) { ... }
    boolean sendSMS(String phoneNumber, String message) { ... }
}

// Implemente FirebasePush e SlackNotifier

class NotificationFacade {
    private EmailSender email;
    private SMSSender sms;
    private FirebasePush push;
    private SlackNotifier slack;
    
    public Map<Canal, Boolean> enviarNotificacao(
        Usuario usuario,
        Mensagem mensagem,
        List<Canal> canais
    ) {
        // Para cada canal:
        // - Adaptar mensagem para formato do canal
        // - Enviar (com retry se falhar)
        // - Retornar status por canal
    }
}

enum Canal { EMAIL, SMS, PUSH, SLACK }
```

### Exercício 4: Facade para Sistema de Relatórios (Avançado)
Sistema de geração de relatórios complexos.

**Requisitos**:
- Fontes de dados: Database, REST API, arquivos CSV
- Processamento: agregação, filtros, joins
- Formatos de saída: PDF, Excel, HTML, JSON
- Features: gráficos, tabelas, totalizações
- Cache de relatórios recentes
- Geração assíncrona para relatórios grandes

**Template**:
```java
class ReportFacade {
    private DataAggregator aggregator;
    private ChartGenerator chartGen;
    private PDFExporter pdfExporter;
    private ExcelExporter excelExporter;
    private ReportCache cache;
    
    // Simples - relatório padrão
    public Report gerarRelatorio(String tipo, LocalDate inicio, LocalDate fim) {
        // ...
    }
    
    // Avançado - customização completa
    public Report gerarRelatorio(ReportConfig config) {
        // 1. Verificar cache
        // 2. Buscar dados de múltiplas fontes
        // 3. Processar e agregar dados
        // 4. Gerar visualizações
        // 5. Exportar no formato escolhido
        // 6. Cachear resultado
    }
    
    // Assíncrono para relatórios grandes
    public CompletableFuture<Report> gerarRelatorioAsync(ReportConfig config) {
        return CompletableFuture.supplyAsync(() -> gerarRelatorio(config));
    }
}

class ReportConfig {
    private List<DataSource> dataSources;
    private List<Filter> filters;
    private List<Aggregation> aggregations;
    private ExportFormat format;
    private boolean includeCharts;
    // Builder pattern
}
```

### Exercício 5: Facade para API de Machine Learning (Avançado)
Facade simplificando uso de biblioteca ML complexa.

**Requisitos**:
- Operações: treinar modelo, fazer predições, avaliar performance
- Suporte a diferentes algoritmos (classificação, regressão, clustering)
- Pré-processamento: normalização, encoding, feature selection
- Validação: cross-validation, train/test split
- Serialização de modelos treinados
- Métricas de avaliação

**Template**:
```java
class MLFacade {
    private DataPreprocessor preprocessor;
    private ModelTrainer trainer;
    private ModelEvaluator evaluator;
    private ModelSerializer serializer;
    
    // API simples - usa defaults inteligentes
    public Model treinarClassificador(Dataset data, String targetColumn) {
        // Preprocessamento automático
        // Escolhe melhor algoritmo
        // Treina com parâmetros padrão
        // Retorna modelo treinado
    }
    
    // API intermediária
    public Model treinar(Dataset data, String targetColumn, 
                        Algorithm algorithm) {
        // Mais controle sobre algoritmo
    }
    
    // API avançada
    public TrainingResult treinar(TrainingConfig config) {
        // Controle completo:
        // - Algoritmo e hiperparâmetros
        // - Estratégia de validação
        // - Métricas de interesse
        // - Early stopping
        // - Feature engineering
    }
    
    public Prediction predizer(Model model, DataRow input) {
        // Preprocessa input
        // Faz predição
        // Retorna resultado com confidence
    }
}

// Cliente - muito simples!
MLFacade ml = new MLFacade();
Dataset data = Dataset.fromCSV("data.csv");
Model model = ml.treinarClassificador(data, "churn");
Prediction pred = ml.predizer(model, novoCliente);
```

## 🎓 Análise Acadêmica

### Princípios SOLID Aplicados

#### 1. Single Responsibility Principle (SRP)
- Facade tem uma responsabilidade: **simplificar acesso ao subsistema**
- Não deve conter lógica de negócio complexa
- Foca em coordenação, não em computação

**Violação comum**:
```java
// ❌ Facade com múltiplas responsabilidades
class SystemFacade {
    void processOrder() { ... }        // Processamento
    void validateInput() { ... }       // Validação
    void calculateDiscount() { ... }   // Cálculo
    void sendNotification() { ... }    // Comunicação
}
```

#### 2. Open/Closed Principle (OCP)
- Subsistema pode evoluir sem afetar Facade (se mudanças são internas)
- Facade pode adicionar novos métodos sem modificar existentes
- Novos subsistemas podem ser adicionados

```java
// Aberto para extensão
class PaymentFacade {
    void processPayment(Payment p) { ... }              // Existente
    void processRecurringPayment(Payment p) { ... }     // Novo - não quebra código
}
```

#### 3. Dependency Inversion Principle (DIP)
- Cliente depende de Facade (abstração), não de subsistema concreto
- Facade pode depender de interfaces do subsistema

```java
interface PaymentGateway {
    boolean process(Payment p);
}

class PaymentFacade {
    private PaymentGateway gateway;  // Depende de abstração
    
    public PaymentFacade(PaymentGateway gateway) {
        this.gateway = gateway;
    }
}
```

### Análise de Complexidade

**Sem Facade**:
- Cliente acopla a N classes do subsistema
- Complexidade de uso: O(N) onde N = classes do subsistema
- Duplicação de código de coordenação

**Com Facade**:
- Cliente acopla a 1 classe (Facade)
- Complexidade de uso: O(1)
- Código de coordenação centralizado

**Trade-off**: Adiciona camada de indireção (+1 chamada de método), mas simplifica enormemente o cliente.

### Comparação Arquitetural

| Sem Facade | Com Facade |
|------------|------------|
| Cliente conhece subsistema | Cliente conhece apenas Facade |
| Alta complexidade cliente | Baixa complexidade cliente |
| Acoplamento alto | Acoplamento baixo |
| Difícil testar | Fácil testar (mock facade) |
| Código duplicado | Código reutilizado |
| Mudanças custosas | Mudanças isoladas |

### Padrões Arquiteturais Relacionados

#### Facade em Arquitetura em Camadas
```
┌─────────────────────────┐
│   Presentation Layer    │
└─────────────────────────┘
            │
            ▼
┌─────────────────────────┐
│    Facade Layer        │  ← Facade simplifica acesso
└─────────────────────────┘
            │
            ▼
┌─────────────────────────┐
│    Business Layer       │
└─────────────────────────┘
            │
            ▼
┌─────────────────────────┐
│   Data Access Layer     │
└─────────────────────────┘
```

#### Facade vs Service Layer
- **Service**: Lógica de negócio do domínio
- **Facade**: Simplificação de subsistema técnico
- **Podem coexistir**: Service chama Facade

```java
@Service
class OrderService {           // Service Layer (negócio)
    private DatabaseFacade db;  // Usa Facade (técnico)
    
    public void placeOrder(Order order) {
        validateBusinessRules(order);  // Lógica de negócio
        calculatePricing(order);       // Lógica de negócio
        db.save(order);                // Usa facade técnica
    }
}
```

## 🔍 Detecção de Code Smells

**Você precisa de Facade quando vê**:

### 1. Duplicação de Código de Inicialização
```java
// Smell: Código repetido em vários lugares
// Cliente A
connection.open();
connection.setAutoCommit(false);
statement = connection.prepareStatement(sql);
// ...

// Cliente B
connection.open();  // Repetido!
connection.setAutoCommit(false);  // Repetido!
statement = connection.prepareStatement(sql);
// ...

// Solução: Facade
databaseFacade.executeQuery(sql);  // Encapsula inicialização
```

### 2. Acoplamento Alto do Cliente
```java
// Smell: Cliente depende de muitas classes
class OrderProcessor {
    private PayPalAPI paypal;
    private StripeAPI stripe;
    private TransactionLog log;
    private EmailService email;
    private InventorySystem inventory;
    // ... 10+ dependências
}

// Solução: Facade reduz dependências
class OrderProcessor {
    private OrderFacade facade;  // Uma dependência!
}
```

### 3. Sequências Complexas Repetidas
```java
// Smell: Mesma sequência em vários lugares
amplifier.on();
amplifier.setVolume(5);
dvd.on();
dvd.play(movie);
projector.on();
projector.wideScreen();
lights.dim(10);

// Solução: Facade encapsula sequência
homeTheater.watchMovie(movie);
```

## 📚 Referências e Leitura Adicional

### Livros
1. **"Design Patterns: Elements of Reusable Object-Oriented Software"** - Gang of Four
   - Capítulo Facade (páginas 185-193)
   
2. **"Head First Design Patterns"** - Freeman & Freeman
   - Capítulo 7: Facade e Adapter

3. **"Patterns of Enterprise Application Architecture"** - Martin Fowler
   - Service Layer e sua relação com Facade

### Frameworks que Usam Facade
- **Spring Framework**: `JdbcTemplate`, `RestTemplate`, `@SpringBootApplication`
- **Hibernate/JPA**: `EntityManager`
- **Apache Commons**: `Email`, `FileUtils`, `IOUtils`
- **SLF4J**: Facade para diferentes frameworks de logging

### Artigos
- [Refactoring Guru - Facade](https://refactoring.guru/design-patterns/facade)
- [SourceMaking - Facade](https://sourcemaking.com/design_patterns/facade)
- [Martin Fowler - Gateway](https://martinfowler.com/eaaCatalog/gateway.html)
