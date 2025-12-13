# 🛠️ Ferramentas de Depuração

## Debuggers de IDE

### 1. IntelliJ IDEA (Java, Kotlin, Scala)

**Características:**
- Debugger integrado de alto nível
- Breakpoints condicionais avançados
- Evaluate expressions em tempo real
- Hot swapping de código

**Recursos Principais:**

#### Breakpoints
```
Line Breakpoint: Clique na margem esquerda
Conditional: Botão direito no breakpoint → Add condition
    Exemplo: user.getId() > 1000 && user.isActive()
Exception Breakpoint: Run → View Breakpoints → + → Java Exception Breakpoints
```

#### Evaluate Expression (Alt+F8)
Durante debug, avalie qualquer expressão Java:
```java
// Durante breakpoint, avaliar:
lista.stream().filter(x -> x > 10).count()
usuario.getPedidos().size()
new SimpleDateFormat("dd/MM/yyyy").format(data)
```

#### Watches
Monitore variáveis automaticamente:
```
+ (Add watch)
Digite: pedido.getValorTotal()
Atualiza automaticamente a cada step
```

### 2. Visual Studio Code (Multi-linguagem)

**Extensões necessárias:**
- **Java**: Debugger for Java (Microsoft)
- **Python**: Python Extension Pack
- **PHP**: PHP Debug (Xdebug)
- **JavaScript/TypeScript**: Built-in

**Configuração launch.json:**

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Debug Java",
            "request": "launch",
            "mainClass": "com.exemplo.Main",
            "args": ["arg1", "arg2"]
        },
        {
            "type": "python",
            "name": "Debug Python",
            "request": "launch",
            "program": "${file}",
            "console": "integratedTerminal"
        }
    ]
}
```

### 3. Eclipse (Java)

**Recursos de Debug:**

```
Perspective → Debug (Ctrl+Alt+D)

Breakpoints:
- Linha: Duplo clique na margem
- Method: Botão direito no método → Toggle Method Breakpoint
- Watchpoint: Botão direito no campo → Toggle Watchpoint

Display View:
- View → Display (mostra resultado de expressões)
- Útil para testar código durante debug
```

### 4. PhpStorm (PHP)

**Integração com Xdebug:**

```ini
; php.ini
[Xdebug]
zend_extension=xdebug.so
xdebug.mode=debug
xdebug.start_with_request=yes
xdebug.client_host=127.0.0.1
xdebug.client_port=9003
```

**Uso:**
1. Configurar PHP Interpreter
2. Ativar "Start Listening for PHP Debug Connections"
3. Colocar breakpoints
4. Acessar aplicação no navegador

### 5. PyCharm (Python)

**Recursos Python-específicos:**

```python
# Breakpoint condicional
# Condição: len(lista) > 100 and valor < 0

# Avaliar expressão durante debug
import pdb; pdb.set_trace()  # Breakpoint manual

# Ver variáveis especiais
__file__    # Arquivo atual
__name__    # Nome do módulo
locals()    # Todas variáveis locais
globals()   # Todas variáveis globais
```

## Debuggers de Linha de Comando

### 1. GDB (GNU Debugger)

**Para C, C++, e outras linguagens compiladas**

```bash
# Compilar com símbolos de debug
gcc -g programa.c -o programa

# Iniciar GDB
gdb programa

# Comandos básicos
(gdb) break main          # Breakpoint na função main
(gdb) break arquivo.c:42  # Breakpoint na linha 42
(gdb) run arg1 arg2       # Executar programa

# Navegação
(gdb) step       # Executar próxima linha (entra em funções)
(gdb) next       # Executar próxima linha (não entra em funções)
(gdb) continue   # Continuar até próximo breakpoint
(gdb) finish     # Sair da função atual

# Inspeção
(gdb) print variavel      # Imprimir valor de variável
(gdb) print *ponteiro     # Derreferenciar ponteiro
(gdb) info locals         # Ver todas variáveis locais
(gdb) backtrace           # Ver stack trace completo
(gdb) frame 2             # Ir para frame 2 do stack

# Watchpoints
(gdb) watch variavel      # Para quando variável mudar
(gdb) rwatch variavel     # Para quando variável for lida
```

### 2. JDB (Java Debugger)

**Debugger CLI para Java**

```bash
# Compilar com informação de debug
javac -g MinhaClasse.java

# Iniciar jdb
jdb MinhaClasse

# Comandos
> stop at MinhaClasse:42        # Breakpoint na linha 42
> stop in MinhaClasse.metodo    # Breakpoint no método
> run                           # Executar

# Navegação
> step        # Step into
> next        # Step over
> cont        # Continue

# Inspeção
> print objeto.campo
> dump objeto              # Dump de todos os campos
> locals                   # Variáveis locais
> where                    # Stack trace
```

### 3. PDB (Python Debugger)

**Debugger padrão do Python**

```python
import pdb

def funcao_problematica(x, y):
    pdb.set_trace()  # Breakpoint aqui
    resultado = x / y
    return resultado
```

**Comandos PDB:**
```python
# Navegação
(Pdb) n      # Next (step over)
(Pdb) s      # Step (step into)
(Pdb) c      # Continue
(Pdb) r      # Return (até retorno da função)

# Inspeção
(Pdb) p variavel          # Print variável
(Pdb) pp objeto           # Pretty print
(Pdb) a                   # Arguments da função atual
(Pdb) l                   # List (mostrar código)
(Pdb) ll                  # Long list (mostrar função toda)

# Breakpoints
(Pdb) b 42               # Breakpoint na linha 42
(Pdb) b funcao           # Breakpoint na função
(Pdb) b arquivo.py:42    # Breakpoint em arquivo específico
(Pdb) condition 1 x > 10 # Adicionar condição ao breakpoint 1

# Stack
(Pdb) w      # Where (stack trace)
(Pdb) u      # Up (subir no stack)
(Pdb) d      # Down (descer no stack)

# Avançado
(Pdb) !import numpy      # Executar código Python
(Pdb) !x = 42            # Modificar variável
```

### 4. LLDB (LLVM Debugger)

**Alternativa moderna ao GDB**

```bash
# Iniciar
lldb programa

# Comandos similares ao GDB mas com sintaxe diferente
(lldb) breakpoint set --name main
(lldb) breakpoint set --file arquivo.c --line 42
(lldb) run

# Navegação
(lldb) step
(lldb) next
(lldb) continue

# Inspeção
(lldb) frame variable          # Todas variáveis locais
(lldb) print variavel
(lldb) po objeto              # Print object (para objetos complexos)
```

## Ferramentas de Análise

### 1. Valgrind (Memory Debugging)

**Detecta memory leaks e erros de memória**

```bash
# Executar com Valgrind
valgrind --leak-check=full --show-leak-kinds=all ./programa

# Output mostra:
# - Memory leaks
# - Invalid reads/writes
# - Uso de memória não inicializada
```

**Exemplo de output:**
```
==12345== LEAK SUMMARY:
==12345==    definitely lost: 1,024 bytes in 1 blocks
==12345==    indirectly lost: 0 bytes in 0 blocks
==12345==      possibly lost: 0 bytes in 0 blocks
==12345==    still reachable: 4,096 bytes in 2 blocks
```

### 2. VisualVM (Java Profiling)

**Monitora aplicações Java**

```bash
# Iniciar VisualVM
jvisualvm

# Recursos:
- Monitor: CPU, Memory, Classes, Threads
- Sampler: CPU e Memory sampling
- Profiler: Profiling detalhado
- Heap Dump: Análise de memória
- Thread Dump: Estado das threads
```

**Análise de Memory Leak:**
1. Capture Heap Dump
2. Analise "Objects" → Classes por tamanho
3. Procure classes com muitas instâncias
4. Analise referências (GC Roots)

### 3. Eclipse MAT (Memory Analyzer Tool)

**Análise avançada de heap dumps Java**

```bash
# Gerar heap dump
jmap -dump:format=b,file=heap.hprof <pid>

# Ou programaticamente
jconsole  # Manual GC e heap dump

# Abrir no MAT
File → Open Heap Dump → heap.hprof
```

**Análises principais:**
- **Leak Suspects Report**: Identifica suspeitos de memory leak
- **Dominator Tree**: Mostra quais objetos retêm mais memória
- **Top Consumers**: Maiores consumidores de memória
- **Duplicate Classes**: Classes carregadas múltiplas vezes

### 4. Xdebug (PHP)

**Extensão de debug para PHP**

**Instalação:**
```bash
# Ubuntu/Debian
sudo apt-get install php-xdebug

# Via PECL
pecl install xdebug
```

**Configuração php.ini:**
```ini
[Xdebug]
zend_extension=xdebug.so
xdebug.mode=debug
xdebug.start_with_request=trigger
xdebug.client_host=localhost
xdebug.client_port=9003

; Profiling
xdebug.mode=profile
xdebug.output_dir=/tmp/xdebug
xdebug.profiler_output_name=cachegrind.out.%p
```

**Uso com navegador:**
```
# Instalar extensão browser:
- Xdebug Helper (Chrome)
- Xdebug Helper (Firefox)

# Ativar debug no browser
# Acessar página PHP
# IDE intercepta e para em breakpoints
```

### 5. Chrome DevTools (JavaScript/Web)

**Debugger built-in do navegador**

**Console:**
```javascript
// Log levels
console.log("Info")
console.warn("Aviso")
console.error("Erro")
console.table([{a:1, b:2}, {a:3, b:4}])  // Tabela formatada

// Timing
console.time("operacao")
// ... código
console.timeEnd("operacao")  // Mostra tempo decorrido

// Stack trace
console.trace("Rastreamento")
```

**Sources (Debugger):**
```
F8: Resume/Pause
F10: Step over
F11: Step into
Shift+F11: Step out

Conditional Breakpoint:
  Botão direito → Add conditional breakpoint
  Condição: user.id > 100 && user.active
```

**Performance:**
```
Record → Executar ação → Stop
Analisa:
- CPU usage
- Rendering
- JavaScript execution
- Network requests
```

## Ferramentas de Logging

### 1. Log4j (Java)

```xml
<!-- log4j2.xml -->
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
        <File name="File" fileName="logs/app.log">
            <PatternLayout pattern="%d %p %c{1.} [%t] %m%n"/>
        </File>
    </Appenders>
    <Loggers>
        <Root level="debug">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </Root>
    </Loggers>
</Configuration>
```

```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinhaClasse {
    private static final Logger logger = LogManager.getLogger(MinhaClasse.class);
    
    public void processar() {
        logger.debug("Iniciando processamento");
        logger.info("Processando item {}", item.getId());
        logger.warn("Valor suspeito: {}", valor);
        logger.error("Erro ao processar", exception);
    }
}
```

### 2. Monolog (PHP)

```php
<?php
use Monolog\Logger;
use Monolog\Handler\StreamHandler;
use Monolog\Handler\FirePHPHandler;
use Monolog\Formatter\LineFormatter;

$logger = new Logger('app');

// Stream para arquivo
$stream = new StreamHandler('logs/app.log', Logger::DEBUG);
$formatter = new LineFormatter("[%datetime%] %channel%.%level_name%: %message% %context%\n");
$stream->setFormatter($formatter);
$logger->pushHandler($stream);

// FirePHP para browser console
$logger->pushHandler(new FirePHPHandler());

// Uso
$logger->debug('Debug message', ['user_id' => 123]);
$logger->info('User logged in', ['username' => 'joao']);
$logger->warning('High memory usage', ['usage' => '85%']);
$logger->error('Database connection failed', ['error' => $e->getMessage()]);
?>
```

### 3. Python logging

```python
import logging
from logging.handlers import RotatingFileHandler

# Configuração avançada
logger = logging.getLogger(__name__)
logger.setLevel(logging.DEBUG)

# Handler para arquivo com rotação
file_handler = RotatingFileHandler(
    'logs/app.log',
    maxBytes=10485760,  # 10MB
    backupCount=5
)
file_handler.setLevel(logging.DEBUG)

# Handler para console
console_handler = logging.StreamHandler()
console_handler.setLevel(logging.INFO)

# Formatação
formatter = logging.Formatter(
    '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
file_handler.setFormatter(formatter)
console_handler.setFormatter(formatter)

logger.addHandler(file_handler)
logger.addHandler(console_handler)

# Uso
logger.debug('Variável x = %d', x)
logger.info('Processamento iniciado')
logger.warning('Memória acima de 80%%')
logger.error('Erro ao conectar', exc_info=True)  # Inclui stack trace
```

## Ferramentas de Monitoramento

### 1. Application Performance Monitoring (APM)

**New Relic:**
```java
// Adicionar agente Java
java -javaagent:/path/to/newrelic.jar \
     -Dnewrelic.config.file=/path/to/newrelic.yml \
     -jar aplicacao.jar
```

**Datadog:**
```python
from ddtrace import tracer

@tracer.wrap()
def funcao_monitorada():
    # Código será automaticamente instrumentado
    pass
```

### 2. ELK Stack (Elasticsearch, Logstash, Kibana)

**Logstash config:**
```ruby
input {
  file {
    path => "/var/log/app/*.log"
    start_position => "beginning"
  }
}

filter {
  grok {
    match => { "message" => "%{TIMESTAMP_ISO8601:timestamp} %{LOGLEVEL:level} %{GREEDYDATA:message}" }
  }
}

output {
  elasticsearch {
    hosts => ["localhost:9200"]
    index => "app-logs-%{+YYYY.MM.dd}"
  }
}
```

### 3. Prometheus + Grafana

**Métricas em aplicação:**
```java
import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;

public class MetricasApp {
    static final Counter requisicoes = Counter.build()
        .name("app_requisicoes_total")
        .help("Total de requisições")
        .register();
    
    static final Histogram latencia = Histogram.build()
        .name("app_latencia_segundos")
        .help("Latência das requisições")
        .register();
    
    public void processar() {
        Histogram.Timer timer = latencia.startTimer();
        try {
            // Processamento
            requisicoes.inc();
        } finally {
            timer.observeDuration();
        }
    }
}
```

## Ferramentas Específicas

### Java
- **JConsole**: Monitoramento básico de JVM
- **JProfiler**: Profiler comercial avançado
- **YourKit**: Profiler com análise de memory leaks
- **Arthas**: Debug de aplicações Java em produção

### Python
- **py-spy**: Profiler sem overhead
- **memory_profiler**: Análise de uso de memória
- **line_profiler**: Profiling linha por linha
- **pdb++**: PDB melhorado com syntax highlighting

### PHP
- **PHPStorm + Xdebug**: Melhor experiência de debug
- **Blackfire**: Profiler de performance
- **Tideways**: APM para PHP
- **Whoops**: Pretty error pages para desenvolvimento

### JavaScript/Node.js
- **Chrome DevTools**: Debug frontend e Node.js
- **node --inspect**: Debug de aplicações Node
- **clinic.js**: Diagnostics para Node.js
- **pm2**: Process manager com logs

## Próximos Passos

- 💻 [Depuração em Java](04-depuracao-java.md)
- 🐘 [Depuração em PHP](05-depuracao-php.md)
- 🐍 [Depuração em Python](06-depuracao-python.md)
- 🎯 [Boas Práticas](07-boas-praticas.md)

---

**📚 Recursos adicionais:**
- [Visual Studio Code Debugging](https://code.visualstudio.com/docs/editor/debugging)
- [IntelliJ IDEA Debug Guide](https://www.jetbrains.com/help/idea/debugging-code.html)
- [GDB Documentation](https://sourceware.org/gdb/documentation/)
- [Python pdb Documentation](https://docs.python.org/3/library/pdb.html)
