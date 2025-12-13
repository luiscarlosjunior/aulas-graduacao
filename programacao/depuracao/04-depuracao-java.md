# ☕ Depuração em Java - Guia Prático

## Exemplos Práticos do Dia a Dia

### Cenário 1: NullPointerException

**Problema comum:**
```java
public class ProcessadorPedido {
    public void processar(Pedido pedido) {
        // NPE aqui se pedido.getCliente() retornar null
        String nomeCliente = pedido.getCliente().getNome();
        System.out.println("Processando pedido de: " + nomeCliente);
    }
}
```

**Depuração com IntelliJ IDEA:**

1. **Coloque breakpoint na linha do erro**
   - Clique na margem esquerda ao lado da linha
   
2. **Execute em modo Debug (Shift+F9)**

3. **Inspecione as variáveis:**
   ```
   pedido: Pedido@4e25154f
   pedido.getCliente(): null  ← Encontrado o problema!
   ```

4. **Avalie expressões (Alt+F8):**
   ```java
   pedido != null           // true
   pedido.getCliente()      // null ← Aqui está o problema
   ```

**Solução preventiva:**
```java
public void processar(Pedido pedido) {
    // Validação defensiva
    if (pedido == null) {
        throw new IllegalArgumentException("Pedido não pode ser nulo");
    }
    
    Cliente cliente = pedido.getCliente();
    if (cliente == null) {
        throw new IllegalStateException("Pedido sem cliente associado");
    }
    
    String nomeCliente = cliente.getNome();
    System.out.println("Processando pedido de: " + nomeCliente);
}
```

**Usando Optional (Java 8+):**
```java
public void processar(Pedido pedido) {
    String nomeCliente = Optional.ofNullable(pedido)
        .map(Pedido::getCliente)
        .map(Cliente::getNome)
        .orElse("Cliente Desconhecido");
    
    System.out.println("Processando pedido de: " + nomeCliente);
}
```

### Cenário 2: ConcurrentModificationException

**Problema:**
```java
public void removerInativos(List<Usuario> usuarios) {
    for (Usuario usuario : usuarios) {
        if (!usuario.isAtivo()) {
            usuarios.remove(usuario);  // ConcurrentModificationException!
        }
    }
}
```

**Depuração:**

1. **Stack trace indica o problema:**
```
Exception in thread "main" java.util.ConcurrentModificationException
    at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
    at java.util.ArrayList$Itr.next(ArrayList.java:859)
    at MinhaClasse.removerInativos(MinhaClasse.java:15)
```

2. **Entenda o problema:**
   - Modificando a lista durante iteração
   - Enhanced for-loop usa Iterator internamente
   - Iterator detecta modificação estrutural

**Soluções:**

```java
// Solução 1: Iterator explícito
public void removerInativos(List<Usuario> usuarios) {
    Iterator<Usuario> iterator = usuarios.iterator();
    while (iterator.hasNext()) {
        Usuario usuario = iterator.next();
        if (!usuario.isAtivo()) {
            iterator.remove();  // Método correto!
        }
    }
}

// Solução 2: removeIf (Java 8+)
public void removerInativos(List<Usuario> usuarios) {
    usuarios.removeIf(usuario -> !usuario.isAtivo());
}

// Solução 3: Stream (Java 8+)
public List<Usuario> filtrarAtivos(List<Usuario> usuarios) {
    return usuarios.stream()
        .filter(Usuario::isAtivo)
        .collect(Collectors.toList());
}
```

### Cenário 3: Memory Leak em Aplicação Web

**Sintomas:**
- Aplicação fica lenta com o tempo
- OutOfMemoryError após algumas horas
- GC executando constantemente

**Exemplo problemático:**
```java
public class CacheUsuarios {
    // Memory leak: nunca limpa o cache!
    private static Map<Long, Usuario> cache = new HashMap<>();
    
    public Usuario buscar(Long id) {
        if (!cache.containsKey(id)) {
            Usuario usuario = bancoDados.buscar(id);
            cache.put(id, usuario);  // Cresce indefinidamente
        }
        return cache.get(id);
    }
}
```

**Depuração com VisualVM:**

1. **Conecte ao processo Java**
   ```bash
   jvisualvm
   # Selecione a aplicação na lista
   ```

2. **Monitor tab:**
   - Observe "Heap size" crescendo continuamente
   - GC não consegue liberar memória

3. **Capture Heap Dump:**
   ```
   Monitor → Heap Dump
   ```

4. **Analise classes com mais instâncias:**
   ```
   Classes → Sort by "Instances"
   
   HashMap$Node: 1,234,567 instances
   Usuario: 456,789 instances  ← Suspeito!
   ```

5. **Encontre referências:**
   ```
   Clique em Usuario → References → GC Root
   CacheUsuarios.cache ← Origem do leak!
   ```

**Soluções:**

```java
// Solução 1: Cache com limite (LRU)
public class CacheUsuarios {
    private static final int MAX_ENTRIES = 1000;
    
    private static Map<Long, Usuario> cache = new LinkedHashMap<Long, Usuario>(
        MAX_ENTRIES + 1, 0.75f, true) {
        
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > MAX_ENTRIES;
        }
    };
}

// Solução 2: Guava Cache com expiração
public class CacheUsuarios {
    private static Cache<Long, Usuario> cache = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build();
    
    public Usuario buscar(Long id) {
        return cache.get(id, () -> bancoDados.buscar(id));
    }
}

// Solução 3: WeakHashMap (GC pode remover)
public class CacheUsuarios {
    private static Map<Long, Usuario> cache = new WeakHashMap<>();
}
```

### Cenário 4: Deadlock em Aplicação Multi-thread

**Problema:**
```java
public class ContaBancaria {
    private final Object lock = new Object();
    private double saldo;
    
    // Deadlock potencial!
    public void transferir(ContaBancaria destino, double valor) {
        synchronized(this.lock) {
            synchronized(destino.lock) {  // Ordem de locks pode causar deadlock
                this.saldo -= valor;
                destino.saldo += valor;
            }
        }
    }
}

// Thread 1: conta1.transferir(conta2, 100)
// Thread 2: conta2.transferir(conta1, 50)
// Deadlock!
```

**Detecção de Deadlock:**

```bash
# 1. Encontre o PID do processo
jps

# 2. Thread dump
jstack <PID>

# Output mostra deadlock:
Found one Java-level deadlock:
=============================
"Thread-1":
  waiting to lock monitor 0x00007f8b4c004e80 (object 0x00000007d5f3e2d8)
  which is held by "Thread-0"
"Thread-0":
  waiting to lock monitor 0x00007f8b4c007390 (object 0x00000007d5f3e2e8)
  which is held by "Thread-1"
```

**Depuração no IntelliJ:**

```
Durante debug, pause todas as threads (Ctrl+F2)
View → Tool Windows → Debug → Threads

Veja:
Thread-1: BLOCKED on monitor@12345
Thread-2: BLOCKED on monitor@67890

Analise o código em cada thread para identificar locks
```

**Solução:**

```java
public class ContaBancaria {
    private final Object lock = new Object();
    private double saldo;
    private final long id;  // Identificador único
    
    // Sempre trava locks em ordem determinística
    public void transferir(ContaBancaria destino, double valor) {
        ContaBancaria primeira = this.id < destino.id ? this : destino;
        ContaBancaria segunda = this.id < destino.id ? destino : this;
        
        synchronized(primeira.lock) {
            synchronized(segunda.lock) {
                if (this == primeira) {
                    this.saldo -= valor;
                    destino.saldo += valor;
                } else {
                    destino.saldo += valor;
                    this.saldo -= valor;
                }
            }
        }
    }
}

// Ou usar ReentrantLock com tryLock:
public boolean transferir(ContaBancaria destino, double valor, long timeout) 
    throws InterruptedException {
    
    if (lock.tryLock(timeout, TimeUnit.MILLISECONDS)) {
        try {
            if (destino.lock.tryLock(timeout, TimeUnit.MILLISECONDS)) {
                try {
                    this.saldo -= valor;
                    destino.saldo += valor;
                    return true;
                } finally {
                    destino.lock.unlock();
                }
            }
        } finally {
            lock.unlock();
        }
    }
    return false;  // Não conseguiu fazer a transferência
}
```

### Cenário 5: Bug Intermitente em Testes

**Problema:**
```java
@Test
public void testProcessamentoParalelo() {
    Processador processador = new Processador();
    List<Integer> resultados = processador.processarEmParalelo(dados);
    
    // Às vezes passa, às vezes falha!
    assertEquals(100, resultados.size());
}
```

**Causa:** Condição de corrida (race condition)

**Depuração:**

```java
// Adicione logging para entender o timing
@Test
public void testProcessamentoParalelo() {
    logger.debug("Iniciando teste às {}", System.currentTimeMillis());
    
    Processador processador = new Processador();
    List<Integer> resultados = processador.processarEmParalelo(dados);
    
    logger.debug("Tamanho dos resultados: {}", resultados.size());
    logger.debug("Tempo decorrido: {}", System.currentTimeMillis());
    
    assertEquals(100, resultados.size());
}

// Adicione sincronização apropriada
public class Processador {
    public List<Integer> processarEmParalelo(List<Integer> dados) {
        // Problema: ArrayList não é thread-safe!
        List<Integer> resultados = new ArrayList<>();
        
        dados.parallelStream().forEach(item -> {
            int resultado = processar(item);
            resultados.add(resultado);  // Race condition!
        });
        
        return resultados;
    }
}
```

**Solução:**

```java
public class Processador {
    public List<Integer> processarEmParalelo(List<Integer> dados) {
        // Solução 1: Coletor thread-safe
        return dados.parallelStream()
            .map(this::processar)
            .collect(Collectors.toList());
    }
    
    // Ou Solução 2: Lista sincronizada
    public List<Integer> processarEmParaleloV2(List<Integer> dados) {
        List<Integer> resultados = Collections.synchronizedList(new ArrayList<>());
        
        dados.parallelStream().forEach(item -> {
            int resultado = processar(item);
            resultados.add(resultado);
        });
        
        return resultados;
    }
}
```

## Técnicas Avançadas de Debug em Java

### 1. Conditional Breakpoints

```java
public void processarPedidos(List<Pedido> pedidos) {
    for (int i = 0; i < pedidos.size(); i++) {
        Pedido pedido = pedidos.get(i);
        
        // Breakpoint com condição:
        // i > 100 && pedido.getValor() > 10000
        
        processar(pedido);
    }
}
```

### 2. Exception Breakpoints

```
IntelliJ: Run → View Breakpoints → + → Java Exception Breakpoints
Adicione: NullPointerException

Agora debugger para em QUALQUER NPE, mesmo sem breakpoint explícito
```

### 3. Method Breakpoints

```java
public class ServicoUsuario {
    // Breakpoint no método (não na linha)
    // Para tanto na entrada quanto na saída
    public Usuario buscar(Long id) {
        // ... código
    }
}

// Configure para parar apenas na saída com valor específico
// Condição: $result != null && $result.isAdmin()
```

### 4. Field Watchpoints

```java
public class Configuracao {
    private String ambiente;  // Coloque watchpoint aqui
    
    // Debugger para quando 'ambiente' for:
    // - Acessado (read)
    // - Modificado (write)
}
```

### 5. Remote Debugging

```bash
# Inicie aplicação com parâmetros de debug
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 \
     -jar aplicacao.jar

# No IntelliJ: Run → Edit Configurations → + → Remote JVM Debug
# Host: localhost
# Port: 5005
```

### 6. Hot Swap (Reload de código)

```java
// Durante debug, modifique o código
public double calcular(double valor) {
    return valor * 1.1;  // Mude para 1.2
}

// Recompile: Ctrl+Shift+F9
// JVM recarrega a classe sem reiniciar!
// Limitação: Só funciona para mudanças em métodos existentes
```

## Ferramentas Complementares

### 1. JProfiler

```bash
# Iniciar aplicação com JProfiler
jprofiler --port=8849 aplicacao.jar

# Análises disponíveis:
# - CPU profiling
# - Memory profiling
# - Thread profiling
# - Database profiling
```

### 2. Java Flight Recorder (JFR)

```bash
# Iniciar gravação
jcmd <PID> JFR.start duration=60s filename=recording.jfr

# Analisar com JMC (Java Mission Control)
jmc recording.jfr
```

### 3. Arthas (Alibaba)

```bash
# Instalar e iniciar
curl -O https://arthas.aliyun.com/arthas-boot.jar
java -jar arthas-boot.jar

# Comandos úteis:
dashboard          # Visão geral do sistema
thread             # Estado das threads
jvm                # Informações da JVM
watch com.Classe metodo "{params,returnObj}"  # Watch de método
trace com.Classe metodo  # Trace de execução
```

## Checklist de Debug Java

- [ ] Verifiquei o stack trace completo?
- [ ] Reproduzo o erro consistentemente?
- [ ] Coloquei breakpoints nos locais estratégicos?
- [ ] Inspecionei valores de variáveis relevantes?
- [ ] Verifiquei se não é um problema de thread/concorrência?
- [ ] Analisei o heap dump para memory leaks?
- [ ] Revisei logs da aplicação?
- [ ] Testei em ambiente isolado?
- [ ] Criei teste unitário para reproduzir o bug?

## Próximos Passos

- 🐘 [Depuração em PHP](05-depuracao-php.md)
- 🐍 [Depuração em Python](06-depuracao-python.md)
- 🎯 [Boas Práticas](07-boas-praticas.md)

---

**📚 Recursos:**
- [IntelliJ IDEA Debugging](https://www.jetbrains.com/help/idea/debugging-code.html)
- [Java Debugging with Eclipse](https://www.eclipse.org/community/eclipse_newsletter/2017/june/article1.php)
- [JVM Troubleshooting Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/)
