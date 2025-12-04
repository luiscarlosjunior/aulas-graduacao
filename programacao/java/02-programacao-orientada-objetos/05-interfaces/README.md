# Interfaces - Contratos e Múltipla Implementação

## 🎯 O que são Interfaces?

Uma **interface** em Java é um contrato que define:

- **Quais métodos** uma classe deve implementar (sem definir como)
- **Constantes** que podem ser compartilhadas
- **Comportamentos comuns** para classes não relacionadas hierarquicamente
- **Múltipla herança de comportamento** (uma classe pode implementar várias interfaces)

**Analogia**: Como um contrato de trabalho - define o que deve ser feito (responsabilidades), mas não como fazer. Diferentes pessoas podem cumprir o mesmo contrato de maneiras diferentes.

## Por que estudar interfaces? (motivação / importância)

**Contrato explícito:** interfaces definem o que um tipo faz sem impor como faz — ideal para desenhar APIs e separar implementação de uso.   
**Polimorfismo e desacoplamento:** clientes programam contra interfaces e não contra implementações concretas → facilita substituição, testes e evolução.   
**Composição de comportamentos:** permitem que uma classe implemente múltiplos papéis/capacidades (herança múltipla de tipo).   
**Compatibilidade e evolução de API:** com recursos modernos (default methods) é possível adicionar comportamento sem quebrar implementações antigas (com limitações).  
**Suporte a programação funcional:** interfaces SAM (Single Abstract Method) permitem uso de lambdas e referências de método.  

## 📋 Sintaxe e Características

### Definindo uma Interface

```java
public interface Voavel {
    // Constantes (implicitamente public static final)
    int VELOCIDADE_MAXIMA = 1000;
    String TIPO_MOVIMENTO = "Aéreo";
    
    // Métodos abstratos (implicitamente public abstract)
    void decolar();
    void voar();
    void aterrissar();
    double calcularTempoVoo(double distancia);
    
    // Método default (Java 8+) - implementação padrão
    default void planar() {
        System.out.println("Planando suavemente...");
    }
    
    // Método estático (Java 8+)
    static void informacoesVoo() {
        System.out.println("Informações sobre voo disponíveis.");
    }
}
```

### Implementando uma Interface

```java
public class Aviao implements Voavel {
    private String modelo;
    private boolean voando;
    
    @Override
    public void decolar() {
        System.out.println("Avião " + modelo + " decolando da pista...");
        voando = true;
    }
    
    @Override
    public void voar() {
        if (voando) {
            System.out.println("Avião voando a " + VELOCIDADE_MAXIMA + " km/h");
        }
    }
    
    @Override
    public void aterrissar() {
        System.out.println("Avião " + modelo + " aterrissando...");
        voando = false;
    }
    
    @Override
    public double calcularTempoVoo(double distancia) {
        return distancia / VELOCIDADE_MAXIMA;
    }
    
    // Pode usar método default da interface ou sobrescrever
    // planar() já está disponível sem implementar
}
```

### Múltipla Implementação

```java
// Uma classe pode implementar várias interfaces
public class Passaro implements Voavel, Animal {
    private String especie;
    
    // Implementa métodos de Voavel
    @Override
    public void decolar() {
        System.out.println("Pássaro " + especie + " batendo as asas...");
    }
    
    @Override
    public void voar() {
        System.out.println("Pássaro voando graciosamente");
    }
    
    @Override
    public void aterrissar() {
        System.out.println("Pássaro pousando no galho");
    }
    
    @Override
    public double calcularTempoVoo(double distancia) {
        return distancia / 50; // Velocidade menor que avião
    }
    
    // Implementa métodos de Animal
    @Override
    public void comer() {
        System.out.println("Pássaro se alimentando");
    }
    
    @Override
    public void dormir() {
        System.out.println("Pássaro dormindo no ninho");
    }
}
```

## 🔧 Tipos de Métodos em Interfaces

### 1. **Métodos Abstratos** (Padrão)
```java
public interface Exemplo {
    void metodoObrigatorio();  // Deve ser implementado
}
```

### 2. **Métodos Default** (Java 8+)
```java
public interface Exemplo {
    default void metodoComImplementacao() {
        System.out.println("Implementação padrão");
    }
}
```

### 3. **Métodos Estáticos** (Java 8+)
```java
public interface Exemplo {
    static void metodoUtilitario() {
        System.out.println("Método estático da interface");
    }
}
```

### 4. **Métodos Privados** (Java 9+)
```java
public interface Exemplo {
    default void metodoPublico() {
        metodoPrivado(); // Reutilização interna
    }
    
    private void metodoPrivado() {
        System.out.println("Usado apenas internamente");
    }
}
```

## 🏗️ Exemplo Prático: Sistema de Pagamentos

```java
// Interface principal
public interface ProcessadorPagamento {
    double TAXA_MAXIMA = 0.10; // 10%
    
    boolean processar(double valor);
    double calcularTaxa(double valor);
    String obterTipoPagamento();
    
    // Método default para validação comum
    default boolean validarValor(double valor) {
        return valor > 0 && valor <= 10000;
    }
    
    // Método estático utilitário
    static void exibirInformacoesSistema() {
        System.out.println("Sistema de Pagamentos v2.0");
        System.out.println("Taxa máxima permitida: " + TAXA_MAXIMA);
    }
}

// Interface adicional para operações online
public interface PagamentoOnline {
    void autenticar(String token);
    boolean verificarConexao();
}

// Implementação: Cartão de Crédito
public class CartaoCredito implements ProcessadorPagamento, PagamentoOnline {
    private String numero;
    private boolean autenticado;
    
    @Override
    public boolean processar(double valor) {
        if (!validarValor(valor)) {
            System.out.println("Valor inválido para cartão");
            return false;
        }
        
        if (!autenticado) {
            System.out.println("Cartão não autenticado");
            return false;
        }
        
        System.out.println("Processando R$ " + valor + " no cartão " + numero);
        return true;
    }
    
    @Override
    public double calcularTaxa(double valor) {
        return valor * 0.03; // 3% para cartão
    }
    
    @Override
    public String obterTipoPagamento() {
        return "Cartão de Crédito";
    }
    
    @Override
    public void autenticar(String token) {
        // Simula autenticação
        this.autenticado = token.length() > 10;
        System.out.println("Cartão " + (autenticado ? "autenticado" : "não autenticado"));
    }
    
    @Override
    public boolean verificarConexao() {
        // Simula verificação de conexão
        return true;
    }
}

// Implementação: PIX
public class PIX implements ProcessadorPagamento, PagamentoOnline {
    private String chave;
    
    @Override
    public boolean processar(double valor) {
        if (!validarValor(valor)) return false;
        
        System.out.println("Transferindo R$ " + valor + " via PIX");
        return true;
    }
    
    @Override
    public double calcularTaxa(double valor) {
        return 0; // PIX sem taxa
    }
    
    @Override
    public String obterTipoPagamento() {
        return "PIX";
    }
    
    @Override
    public void autenticar(String token) {
        System.out.println("PIX autenticado via token bancário");
    }
    
    @Override
    public boolean verificarConexao() {
        return true;
    }
}

// Implementação: Boleto (apenas ProcessadorPagamento)
public class Boleto implements ProcessadorPagamento {
    private String codigoBarras;
    
    @Override
    public boolean processar(double valor) {
        if (!validarValor(valor)) return false;
        
        System.out.println("Gerando boleto de R$ " + valor);
        return true;
    }
    
    @Override
    public double calcularTaxa(double valor) {
        return 2.50; // Taxa fixa
    }
    
    @Override
    public String obterTipoPagamento() {
        return "Boleto Bancário";
    }
}
```

### Sistema de Uso Polimórfico

```java
public class SistemaPagamento {
    public static void main(String[] args) {
        // Array polimórfico de interfaces
        ProcessadorPagamento[] processadores = {
            new CartaoCredito(),
            new PIX(),
            new Boleto()
        };
        
        double valorCompra = 150.00;
        
        System.out.println("=== PROCESSAMENTO DE PAGAMENTOS ===");
        ProcessadorPagamento.exibirInformacoesSistema();
        
        for (ProcessadorPagamento proc : processadores) {
            System.out.println("\n--- " + proc.obterTipoPagamento() + " ---");
            
            // Autenticação para pagamentos online
            if (proc instanceof PagamentoOnline) {
                PagamentoOnline online = (PagamentoOnline) proc;
                online.autenticar("token_seguro_123456");
            }
            
            // Processamento
            boolean sucesso = proc.processar(valorCompra);
            if (sucesso) {
                double taxa = proc.calcularTaxa(valorCompra);
                double total = valorCompra + taxa;
                System.out.println("Taxa: R$ " + taxa);
                System.out.println("Total: R$ " + total);
            }
        }
    }
}
```

## 💡 Vantagens das Interfaces

### 1. **Múltipla Herança de Comportamento**
- Java não permite herança múltipla de classes
- Mas permite implementar múltiplas interfaces

### 2. **Desacoplamento**
- Código depende de interfaces, não implementações
- Facilita testes e manutenção

### 3. **Flexibilidade**
- Diferentes classes podem implementar a mesma interface
- Permite polimorfismo sem herança

### 4. **Contratos Claros**
- Define exatamente o que uma classe deve fazer
- Documentação viva do comportamento esperado

## 🔄 Interface vs Classe Abstrata

| Aspecto | Interface | Classe Abstrata |
|---------|-----------|-----------------|
| **Herança** | Múltipla implementação | Herança simples |
| **Métodos** | Abstratos, default, static | Abstratos + concretos |
| **Atributos** | Apenas constantes | Qualquer tipo |
| **Construtor** | Não tem | Pode ter |
| **Quando usar** | Contratos, múltipla herança | Código comum + abstração |

## ⚠️ Boas Práticas

### ✅ Nomeação Clara
```java
interface Corrivel {    // Comportamento
interface Comparavel {  // Capacidade
interface Processador { // Função
```

### ✅ Interfaces Pequenas e Focadas
```java
// ✅ Interface focada
interface Desenhavel {
    void desenhar();
}

// ❌ Interface muito grande
interface SuperInterface {
    void desenhar();
    void calcular();
    void processar();
    void validar();
    // ... muitos métodos não relacionados
}
```

### ✅ Use Composição de Interfaces
```java
interface Leitor {
    String ler();
}

interface Escritor {
    void escrever(String dados);
}

interface ProcessadorArquivo extends Leitor, Escritor {
    // Combina comportamentos
}
```

## 📚 Exemplos

A seguir, apresentamos **5 exemplos práticos com diagramas de classes** que demonstram o uso de interfaces em diferentes contextos do mundo real. Cada exemplo ilustra como interfaces permitem criar código flexível, extensível e desacoplado.

---

### **Exemplo 1: Sistema de Autenticação Multi-Fator**

**Contexto:** Uma aplicação precisa suportar diferentes métodos de autenticação (senha, biometria, token) onde cada método tem sua própria implementação, mas todos seguem o mesmo contrato de autenticação.

**Diagrama de Classes:**

```
┌─────────────────────────────────┐
│     <<interface>>               │
│     Autenticavel                │
├─────────────────────────────────┤
│ + autenticar(credencial): bool  │
│ + validarCredencial(): bool     │
│ + logout(): void                │
└─────────────────────────────────┘
           △
           │ implements
    ┌──────┼──────┐
    │      │      │
┌───────┐ ┌────────┐ ┌──────────────┐
│ Senha │ │Biometria│ │TokenOAuth    │
├───────┤ ├────────┤ ├──────────────┤
│-hash  │ │-digital│ │-token: String│
└───────┘ └────────┘ └──────────────┘
```

**Aplicação:** Sistemas bancários, aplicativos corporativos, e-commerce com login social.

---

### **Exemplo 2: Sistema de Armazenamento em Nuvem**

**Contexto:** Uma aplicação de backup precisa suportar múltiplos provedores de armazenamento (AWS, Google Drive, Dropbox) de forma intercambiável, permitindo que o usuário escolha onde seus dados serão armazenados sem alterar a lógica da aplicação.

**Diagrama de Classes:**

```
┌──────────────────────────────────────────┐
│         <<interface>>                    │
│         ArmazenamentoNuvem               │
├──────────────────────────────────────────┤
│ + upload(arquivo): boolean               │
│ + download(nomeArquivo): File            │
│ + deletar(nomeArquivo): boolean          │
│ + listarArquivos(): List<String>         │
│ + obterEspacoDisponivel(): double        │
└──────────────────────────────────────────┘
                    △
                    │ implements
         ┌──────────┼──────────┐
         │          │          │
   ┌──────────┐ ┌─────────┐ ┌──────────┐
   │ AmazonS3 │ │GoogleDrive│ │Dropbox  │
   ├──────────┤ ├─────────┤ ├──────────┤
   │-bucketName│ │-folderId│ │-appKey   │
   │-region   │ │-oauth   │ │-appSecret│
   └──────────┘ └─────────┘ └──────────┘
```

**Aplicação:** Sistemas de backup corporativo, aplicações de sincronização de arquivos, gerenciadores de documentos.

---

### **Exemplo 3: Sistema de Transporte Urbano**

**Contexto:** Um aplicativo de mobilidade urbana integra diferentes tipos de transporte (bicicleta, patinete, carro compartilhado) onde cada veículo tem suas particularidades, mas todos precisam ser rastreados, alugados e devolvidos seguindo um padrão comum.

**Diagrama de Classes:**

```
┌────────────────────────────────────┐        ┌─────────────────────────┐
│       <<interface>>                │        │    <<interface>>        │
│       Alugavel                     │        │    Rastreavel           │
├────────────────────────────────────┤        ├─────────────────────────┤
│ + alugar(usuario): boolean         │        │ + obterLocalizacao(): GPS│
│ + devolver(localizacao): void      │        │ + atualizarPosicao(): void│
│ + calcularTarifa(tempo): double    │        │ + habilitarRastreio(): void│
└────────────────────────────────────┘        └─────────────────────────┘
              △                                         △
              │ implements                              │ implements
       ┌──────┴──────┬──────────────┐         ┌────────┴────────┐
       │             │              │         │                 │
┌─────────────┐ ┌──────────┐ ┌────────────┐  │                 │
│ Bicicleta   │ │ Patinete │ │CarroCompart│──┘                 │
├─────────────┤ ├──────────┤ ├────────────┤                    │
│-marcha: int │ │-bateria% │ │-placa:String│                   │
│-aro: int    │ │-velMax:int│ │-modelo:String│                 │
└─────────────┘ └──────────┘ └────────────┘                    │
                                                                │
                         CarroCompartilhado implements ambas ──┘
```

**Aplicação:** Apps de mobilidade urbana (tipo Uber, Lime, Tembici), sistemas de gestão de frotas compartilhadas.

---

### **Exemplo 4: Sistema de Reprodução Multimídia**

**Contexto:** Um player de mídia universal precisa reproduzir diferentes formatos (áudio, vídeo, streaming) onde cada formato tem seu codec e processamento específico, mas todos devem responder aos mesmos comandos de controle (play, pause, stop).

**Diagrama de Classes:**

```
┌───────────────────────────────┐        ┌──────────────────────────────┐
│      <<interface>>            │        │       <<interface>>          │
│      Reproduzivel             │        │       Streamable             │
├───────────────────────────────┤        ├──────────────────────────────┤
│ + play(): void                │        │ + conectar(url): boolean     │
│ + pause(): void               │        │ + bufferizar(): void         │
│ + stop(): void                │        │ + ajustarQualidade(nivel): void│
│ + ajustarVolume(nivel): void  │        └──────────────────────────────┘
│ + obterDuracao(): int         │                     △
└───────────────────────────────┘                     │ implements
           △                                          │
           │ implements                               │
    ┌──────┼────────┬──────────┐                    │
    │      │        │          │                     │
┌─────────┐│ ┌────────────┐ ┌──────────────┐        │
│AudioMP3 ││ │ VideoMP4   │ │StreamingYouTube│───────┘
├─────────┤│ ├────────────┤ ├──────────────┤
│-bitrate ││ │-resolucao  │ │-apiKey       │
│-codec   ││ │-fps        │ │-qualidade    │
└─────────┘│ └────────────┘ └──────────────┘
           │
      ┌────────────┐
      │ AudioWAV   │
      ├────────────┤
      │-sampleRate │
      └────────────┘
```

**Aplicação:** Media players, apps de streaming (Spotify, Netflix), editores de vídeo/áudio.

---

### **Exemplo 5: Sistema de Notificações Multi-Canal Empresarial**

**Contexto:** Uma empresa precisa enviar notificações críticas por diferentes canais (email, SMS, push notification, Slack, Teams) com suporte a priorização, agendamento e confirmação de entrega. O sistema deve permitir adicionar novos canais sem modificar o código existente.

**Diagrama de Classes:**

```
┌────────────────────────────────────────────┐
│           <<interface>>                    │
│           Notificador                      │
├────────────────────────────────────────────┤
│ + enviar(mensagem, destinatario): boolean  │
│ + validarDestinatario(destinatario): bool  │
│ + obterStatus(): StatusEntrega             │
└────────────────────────────────────────────┘
                    △
                    │ implements
                    │
    ┌───────────────┼───────────────┬─────────────────┐
    │               │               │                 │
┌─────────┐  ┌──────────┐  ┌──────────────┐  ┌─────────────┐
│NotifEmail│  │NotifSMS  │  │NotifPush     │  │NotifSlack   │
├─────────┤  ├──────────┤  ├──────────────┤  ├─────────────┤
│-smtp    │  │-gateway  │  │-deviceToken  │  │-webhookURL  │
│-porta   │  │-apiKey   │  │-appId        │  │-canal       │
└─────────┘  └──────────┘  └──────────────┘  └─────────────┘
                    │
                    │
                    ▼
┌────────────────────────────────────────────┐
│           <<interface>>                    │
│           Agendavel                        │
├────────────────────────────────────────────┤
│ + agendar(dataHora): void                  │
│ + cancelarAgendamento(): void              │
│ + verificarAgendamentos(): List<Notif>     │
└────────────────────────────────────────────┘
                    △
                    │ implements
                    │
            ┌───────────────┐
            │  NotifSMS     │ (implementa ambas interfaces)
            │  (Extended)   │
            └───────────────┘
```

**Aplicação:** Sistemas empresariais de alertas críticos, plataformas de comunicação interna, sistemas de monitoramento e alertas operacionais.

**Destaque:** Este exemplo mostra como uma classe pode implementar múltiplas interfaces (`NotifSMS` implementa tanto `Notificador` quanto `Agendavel`), permitindo composição de comportamentos.

---

## 🚀 Exercícios Práticos

1. **Sistema de Dispositivos Eletrônicos**
   - Interface: `DispositivoEletronico` (ligar, desligar, status)
   - Implementações: `Smartphone`, `Tablet`, `Laptop`

2. **Sistema de Formas Geométricas**
   - Interface: `CalculavelArea` (calcularArea)
   - Interface: `Desenhavel` (desenhar)
   - Classes que implementam ambas

3. **Sistema de Notificações**
   - Interface: `Notificador` (enviarNotificacao)
   - Implementações: `Email`, `SMS`, `PushNotification`

## 🔗 Navegação

[← 04 - Polimorfismo](../04-polimorfismo/) | [06 - Classes Abstratas →](../06-classes-abstratas/)

---

**💡 Lembre-se**: Interfaces definem O QUE fazer, não COMO fazer. São contratos que garantem que certas funcionalidades estarão disponíveis!
