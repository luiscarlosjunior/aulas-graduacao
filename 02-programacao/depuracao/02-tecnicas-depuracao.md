# 🔍 Técnicas e Metodologias de Depuração

## Técnicas Clássicas de Depuração

### 1. Print Debugging (Printf Debugging)

A técnica mais antiga e ainda amplamente utilizada.

#### Vantagens
- ✅ Simples e rápida de implementar
- ✅ Não requer ferramentas especiais
- ✅ Funciona em qualquer ambiente
- ✅ Útil para entender fluxo de execução

#### Quando usar
- Debug rápido de problemas simples
- Ambientes onde debuggers não estão disponíveis
- Código legado sem símbolos de debug
- Sistemas em produção (com cautela)

#### Exemplo em Java
```java
public class CalculadoraJuros {
    public double calcularJurosCompostos(double principal, double taxa, int periodo) {
        System.out.println("DEBUG: Entrada - principal=" + principal + 
                          ", taxa=" + taxa + ", periodo=" + periodo);
        
        double montante = principal * Math.pow(1 + taxa, periodo);
        
        System.out.println("DEBUG: Montante calculado=" + montante);
        System.out.println("DEBUG: Juros=" + (montante - principal));
        
        return montante;
    }
}
```

#### Exemplo em PHP
```php
<?php
function processar_pedido($pedido) {
    error_log("DEBUG: Processando pedido ID: " . $pedido['id']);
    error_log("DEBUG: Valor total: " . $pedido['total']);
    
    $resultado = validar_pedido($pedido);
    error_log("DEBUG: Resultado validação: " . json_encode($resultado));
    
    return $resultado;
}
?>
```

#### Exemplo em Python
```python
def calcular_fatorial(n):
    print(f"DEBUG: Calculando fatorial de {n}")
    
    if n == 0 or n == 1:
        print(f"DEBUG: Caso base, retornando 1")
        return 1
    
    resultado = n * calcular_fatorial(n - 1)
    print(f"DEBUG: Fatorial de {n} = {resultado}")
    
    return resultado
```

#### Boas Práticas
1. **Use prefixo consistente** (DEBUG:, LOG:, TRACE:)
2. **Inclua contexto relevante** (nome da função, valores de variáveis)
3. **Remova prints antes de commit** (ou use logging framework)
4. **Formate output de forma legível**

### 2. Logging Estruturado

Evolução do print debugging com níveis e formatação profissional.

#### Níveis de Log

```
TRACE   → Informação muito detalhada (desenvolvimento)
DEBUG   → Informação detalhada (debug)
INFO    → Eventos importantes do sistema
WARN    → Avisos de problemas potenciais
ERROR   → Erros que não param a aplicação
FATAL   → Erros críticos que param a aplicação
```

#### Exemplo em Java com SLF4J
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicoUsuario {
    private static final Logger logger = LoggerFactory.getLogger(ServicoUsuario.class);
    
    public Usuario buscarPorId(Long id) {
        logger.debug("Buscando usuário com ID: {}", id);
        
        try {
            Usuario usuario = repositorio.findById(id);
            
            if (usuario == null) {
                logger.warn("Usuário não encontrado: {}", id);
                return null;
            }
            
            logger.info("Usuário {} carregado com sucesso", usuario.getNome());
            return usuario;
            
        } catch (DatabaseException e) {
            logger.error("Erro ao buscar usuário {}: {}", id, e.getMessage(), e);
            throw new ServicoException("Falha ao buscar usuário", e);
        }
    }
}
```

#### Exemplo em Python com logging
```python
import logging

# Configuração do logging
logging.basicConfig(
    level=logging.DEBUG,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
    filename='app.log'
)

logger = logging.getLogger(__name__)

def processar_arquivo(caminho):
    logger.debug(f"Iniciando processamento de {caminho}")
    
    try:
        with open(caminho, 'r') as arquivo:
            conteudo = arquivo.read()
            logger.info(f"Arquivo {caminho} lido com sucesso ({len(conteudo)} bytes)")
            
            resultado = analisar_conteudo(conteudo)
            logger.debug(f"Análise concluída: {resultado}")
            
            return resultado
            
    except FileNotFoundError:
        logger.error(f"Arquivo não encontrado: {caminho}")
        raise
    except Exception as e:
        logger.exception(f"Erro inesperado ao processar {caminho}")
        raise
```

#### Exemplo em PHP com Monolog
```php
<?php
use Monolog\Logger;
use Monolog\Handler\StreamHandler;

class PedidoService {
    private $logger;
    
    public function __construct() {
        $this->logger = new Logger('pedidos');
        $this->logger->pushHandler(new StreamHandler('logs/pedidos.log', Logger::DEBUG));
    }
    
    public function criarPedido($dados) {
        $this->logger->info('Criando novo pedido', ['cliente_id' => $dados['cliente_id']]);
        
        try {
            $pedido = $this->validarDados($dados);
            $this->logger->debug('Pedido validado', ['pedido_id' => $pedido->id]);
            
            $this->salvar($pedido);
            $this->logger->info('Pedido criado com sucesso', ['pedido_id' => $pedido->id]);
            
            return $pedido;
            
        } catch (ValidationException $e) {
            $this->logger->warning('Falha na validação', [
                'erros' => $e->getErrors(),
                'dados' => $dados
            ]);
            throw $e;
        } catch (Exception $e) {
            $this->logger->error('Erro ao criar pedido', [
                'mensagem' => $e->getMessage(),
                'trace' => $e->getTraceAsString()
            ]);
            throw $e;
        }
    }
}
?>
```

### 3. Breakpoint Debugging

Uso de debuggers interativos para pausar e inspecionar o programa.

#### Tipos de Breakpoints

1. **Line Breakpoint**: Para em uma linha específica
2. **Conditional Breakpoint**: Para apenas se condição for verdadeira
3. **Exception Breakpoint**: Para quando exceção é lançada
4. **Method Breakpoint**: Para ao entrar/sair de método
5. **Field Watchpoint**: Para quando campo é acessado/modificado

#### Exemplo: Depuração em Java (IntelliJ IDEA)

```java
public class ProcessadorPedidos {
    public void processar(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos) {
            // Breakpoint condicional: i > 100 && pedido.getValor() > 1000
            
            double desconto = calcularDesconto(pedido);
            // Inspecionar: desconto, pedido.getValor(), pedido.getCliente()
            
            pedido.aplicarDesconto(desconto);
            // Watchpoint: pedido.valorFinal (monitora mudanças)
            
            salvarPedido(pedido);
        }
    }
}
```

**Comandos comuns no debugger:**
- **Step Over (F8)**: Executa linha atual, não entra em métodos
- **Step Into (F7)**: Entra no método sendo chamado
- **Step Out (Shift+F8)**: Sai do método atual
- **Resume (F9)**: Continua até próximo breakpoint
- **Evaluate Expression**: Avalia expressão no contexto atual

### 4. Stack Trace Analysis

Análise de pilha de chamadas para identificar origem de erros.

#### Exemplo de Stack Trace em Java
```
Exception in thread "main" java.lang.NullPointerException: 
    Não é possível invocar "Usuario.getNome()" porque "usuario" é nulo
    at com.exemplo.ServicoPedido.processar(ServicoPedido.java:45)
    at com.exemplo.ControladorPedido.criar(ControladorPedido.java:28)
    at com.exemplo.Main.main(Main.java:12)
```

**Como ler:**
1. **Tipo de exceção**: NullPointerException
2. **Mensagem**: "usuario" é nulo
3. **Linha do erro**: ServicoPedido.java:45
4. **Caminho de chamadas**: Main → ControladorPedido → ServicoPedido

#### Exemplo em Python
```python
Traceback (most recent call last):
  File "main.py", line 12, in <module>
    processar_dados(dados)
  File "processador.py", line 28, in processar_dados
    resultado = calcular(item)
  File "calculadora.py", line 45, in calcular
    return total / quantidade
ZeroDivisionError: division by zero
```

**Análise:**
- Erro ocorreu em `calculadora.py`, linha 45
- Causado por divisão por zero
- Chamado a partir de `processar_dados` em `processador.py`

### 5. Rubber Duck Debugging

Explicar o código linha por linha para um objeto (ou pessoa).

#### Como Praticar

1. **Prepare o "pato"**
   - Pode ser objeto físico, colega, ou até você mesmo

2. **Explique o problema**
   - "Este código deveria fazer X, mas está fazendo Y"

3. **Percorra o código**
   ```java
   // Explique: "Aqui eu recebo o usuário do banco..."
   Usuario usuario = buscarUsuario(id);
   
   // "Então eu verifico se ele é administrador..."
   if (usuario.getTipo() == TipoUsuario.ADMIN) {
       // "Espera... e se usuário for null? 🤔"
       // BUG ENCONTRADO!
   }
   ```

4. **Frequentemente você encontrará o erro ao verbalizar**

### 6. Binary Search Debugging (Dividir e Conquistar)

Isola o problema dividindo o código em seções menores.

#### Processo

```
[Código todo] → Funciona ou não?
    ↓ não funciona
[Primeira metade] → Funciona ou não?
    ↓ funciona
[Segunda metade] → Funciona ou não?
    ↓ não funciona
[Dividir segunda metade...] → Continue até encontrar a linha exata
```

#### Exemplo Prático em Python
```python
def processar_lista(items):
    # Teste: Tudo funciona até aqui?
    print("Checkpoint 1: Início")
    
    items_filtrados = filtrar(items)
    print("Checkpoint 2: Filtrados")  # Funciona
    
    items_transformados = transformar(items_filtrados)
    print("Checkpoint 3: Transformados")  # NÃO funciona - BUG está em transformar()
    
    items_ordenados = ordenar(items_transformados)
    print("Checkpoint 4: Ordenados")
    
    return items_ordenados
```

### 7. Delta Debugging

Identificar a mudança mínima que causa o bug.

#### Cenário
- Versão 1.0 funciona ✅
- Versão 2.0 tem bug ❌
- Houve 50 commits entre as versões

#### Processo com Git Bisect

```bash
# Inicia bisect
git bisect start

# Marca versão atual como ruim
git bisect bad

# Marca última versão boa
git bisect good v1.0

# Git automaticamente faz busca binária
# Teste cada commit que Git apresenta
git bisect good  # se funcionar
git bisect bad   # se tiver bug

# Git identifica commit exato que introduziu o bug
```

### 8. Debugging por Hipóteses

Método científico aplicado à depuração.

#### Processo

**1. Observação**
```
Sintoma: Aplicação trava após 1 hora de uso
```

**2. Formulação de Hipóteses**
```
H1: Memory leak (vazamento de memória)
H2: Deadlock em threads
H3: Timeout de conexão com banco
H4: Cache crescendo indefinidamente
```

**3. Previsões para cada hipótese**
```
Se H1 (memory leak):
  - Uso de memória aumentará continuamente
  - Profiler mostrará objetos não coletados
  - GC será invocado cada vez mais frequentemente

Se H2 (deadlock):
  - Thread dump mostrará threads bloqueadas
  - CPU permanecerá baixa
  - Aplicação não responderá imediatamente
```

**4. Experimentos**
```java
// Teste H1: Monitorar memória
Runtime runtime = Runtime.getRuntime();
long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
// ... executar operação
long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
System.out.println("Memory delta: " + (memoryAfter - memoryBefore));
```

**5. Análise dos Resultados**
- Confirmar ou refutar cada hipótese
- Refinar hipóteses baseado em dados
- Iterar até encontrar a causa raiz

### 9. Time Travel Debugging

Debuggers que permitem voltar no tempo (execução reversa).

#### Conceito
- Gravar toda a execução do programa
- Permitir navegação para frente e para trás
- Inspecionar estado em qualquer ponto

#### Ferramentas
- **rr** (Linux): Record and Replay
- **UDB** (Undo): Time-travel debugging comercial
- **WinDbg Time Travel** (Windows)

#### Casos de Uso
- Bugs não-determinísticos
- Condições de corrida
- Heisenbug (bugs que somem ao depurar)

### 10. Postmortem Debugging

Análise após falha do programa (crash).

#### Core Dump Analysis

**Linux:**
```bash
# Gerar core dump ao travar
ulimit -c unlimited

# Analisar com gdb
gdb programa core.dump

# Comandos úteis no gdb
(gdb) backtrace     # Ver stack trace
(gdb) info locals   # Ver variáveis locais
(gdb) print var     # Imprimir variável
```

**Java:**
```bash
# Gerar heap dump em OutOfMemoryError
java -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=/tmp/heap.hprof \
     MinhaAplicacao

# Analisar com Eclipse MAT ou VisualVM
```

## Metodologias de Depuração

### 1. The Scientific Method

**Baseado em "Why Programs Fail" - Andreas Zeller**

1. **Observar** o fracasso
2. **Formular hipótese** sobre a causa
3. **Prever** consequências da hipótese
4. **Testar** a hipótese através de experimentos
5. **Refinar** hipótese ou concluir

### 2. The 9 Rules of Debugging

**Baseado em "Debugging" - David Agans**

1. **Understand the system**: Conheça o funcionamento
2. **Make it fail**: Reproduza consistentemente
3. **Quit thinking and look**: Observe dados reais
4. **Divide and conquer**: Isole o problema
5. **Change one thing at a time**: Testes controlados
6. **Keep an audit trail**: Documente tudo
7. **Check the plug**: Verifique o óbvio
8. **Get a fresh view**: Segunda opinião
9. **If you didn't fix it, it ain't fixed**: Valide a correção

### 3. The TRAFFIC Process

**Baseado em "Why Programs Fail" - Andreas Zeller**

- **T**rack: Rastreie o problema
- **R**eproduce: Reproduza a falha
- **A**utomate: Automatize a reprodução
- **F**ind: Encontre possíveis origens
- **F**ocus: Foque na origem mais provável
- **I**solate: Isole a cadeia de infecção
- **C**orrect: Corrija o defeito

## Técnicas Avançadas

### 1. Mutation Testing

Introduzir mudanças no código para verificar se testes detectam.

```java
// Original
if (idade >= 18) {
    return true;
}

// Mutante 1: Troca >= por >
if (idade > 18) {  // Testes detectam?
    return true;
}

// Mutante 2: Troca 18 por 17
if (idade >= 17) {  // Testes detectam?
    return true;
}
```

### 2. Fuzzing

Testes com entradas aleatórias ou malformadas.

```python
import random
import string

def gerar_entrada_aleatoria():
    tamanho = random.randint(0, 10000)
    return ''.join(random.choices(string.printable, k=tamanho))

# Teste a função com entradas aleatórias
for _ in range(10000):
    try:
        processar(gerar_entrada_aleatoria())
    except Exception as e:
        print(f"Bug encontrado com entrada: {entrada}")
        print(f"Exceção: {e}")
```

### 3. Slicing

Extrair apenas o código relevante para um comportamento específico.

```java
// Código completo
public double calcular(int a, int b, int c) {
    int x = a + b;          // Relevante
    int y = b * 2;          // Irrelevante para resultado
    int z = x * c;          // Relevante
    System.out.println(y);  // Irrelevante
    return z / 2.0;         // Relevante
}

// Slice para a variável de retorno
public double calcular_slice(int a, int b, int c) {
    int x = a + b;
    int z = x * c;
    return z / 2.0;
}
```

## Checklist de Depuração

Antes de começar a depurar:
- [ ] Consigo reproduzir o problema consistentemente?
- [ ] Entendo o comportamento esperado?
- [ ] Li a mensagem de erro completamente?
- [ ] Verifiquei o óbvio (entradas, configurações)?
- [ ] Busquei por erros similares (Google, Stack Overflow)?

Durante a depuração:
- [ ] Estou documentando minhas descobertas?
- [ ] Testei apenas uma mudança por vez?
- [ ] Verifiquei minhas suposições com dados reais?
- [ ] Considerei pedir ajuda a um colega?

Após corrigir:
- [ ] Entendo por que o bug ocorreu?
- [ ] Criei teste para prevenir regressão?
- [ ] Verifiquei se a correção não introduziu novos bugs?
- [ ] Documentei a correção adequadamente?

## Próximos Passos

Continue aprendendo sobre depuração:

- 🛠️ [Ferramentas de Depuração](03-ferramentas-depuracao.md)
- 💻 [Depuração em Java](04-depuracao-java.md)
- 🐘 [Depuração em PHP](05-depuracao-php.md)
- 🐍 [Depuração em Python](06-depuracao-python.md)
- 🎯 [Boas Práticas e Prevenção](07-boas-praticas.md)

---

**📚 Referências:**
- Zeller, A. (2009). "Why Programs Fail: A Guide to Systematic Debugging"
- Agans, D. (2006). "Debugging: The 9 Indispensable Rules"
- Spinellis, D. (2016). "Effective Debugging: 66 Specific Ways"
