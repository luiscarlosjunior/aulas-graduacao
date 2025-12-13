# 🐘 Depuração em PHP - Guia Prático

## Exemplos Práticos do Dia a Dia

### Cenário 1: Undefined Variable/Index

**Problema comum:**
```php
<?php
function processar_pedido($dados) {
    // Notice: Undefined index: cliente
    $cliente_id = $dados['cliente'];
    
    // Notice: Undefined variable: total
    return $total * 1.1;
}
?>
```

**Depuração básica com var_dump:**

```php
<?php
function processar_pedido($dados) {
    // Debug: O que tem em $dados?
    var_dump($dados);
    die();  // Para execução para análise
    
    // Saída:
    // array(2) {
    //   ["produto"]=> string(10) "Notebook"
    //   ["quantidade"]=> int(2)
    // }
    // Falta a chave 'cliente'!
}
?>
```

**Solução preventiva:**

```php
<?php
function processar_pedido(array $dados): float {
    // Método 1: isset()
    if (!isset($dados['cliente'])) {
        throw new InvalidArgumentException('Cliente é obrigatório');
    }
    
    // Método 2: array_key_exists()
    if (!array_key_exists('total', $dados)) {
        $total = calcular_total($dados);
    } else {
        $total = $dados['total'];
    }
    
    // Método 3: Null coalescing operator (PHP 7+)
    $desconto = $dados['desconto'] ?? 0;
    
    return $total - $desconto;
}
?>
```

### Cenário 2: SQL Injection e Erros de Query

**Problema:**
```php
<?php
// PERIGO: SQL Injection!
$usuario = $_GET['usuario'];
$sql = "SELECT * FROM usuarios WHERE nome = '$usuario'";
$resultado = mysqli_query($conn, $sql);

if (!$resultado) {
    // Erro, mas qual?
    die("Erro na query");
}
?>
```

**Depuração adequada:**

```php
<?php
// Debug 1: Ver a query construída
$usuario = $_GET['usuario'];
$sql = "SELECT * FROM usuarios WHERE nome = '$usuario'";

error_log("DEBUG SQL: " . $sql);
// Se $usuario = "'; DROP TABLE usuarios; --"
// SQL: SELECT * FROM usuarios WHERE nome = ''; DROP TABLE usuarios; --'

$resultado = mysqli_query($conn, $sql);

if (!$resultado) {
    // Debug 2: Ver erro específico
    error_log("Erro MySQL: " . mysqli_error($conn));
    error_log("Erro número: " . mysqli_errno($conn));
    die("Erro ao executar query");
}
?>
```

**Solução correta (Prepared Statements):**

```php
<?php
function buscar_usuario($conn, $nome) {
    // Proteção contra SQL Injection
    $stmt = $conn->prepare("SELECT * FROM usuarios WHERE nome = ?");
    
    if (!$stmt) {
        error_log("Erro ao preparar statement: " . $conn->error);
        throw new Exception("Erro no banco de dados");
    }
    
    $stmt->bind_param("s", $nome);
    
    if (!$stmt->execute()) {
        error_log("Erro ao executar: " . $stmt->error);
        throw new Exception("Erro ao buscar usuário");
    }
    
    $resultado = $stmt->get_result();
    return $resultado->fetch_assoc();
}

// Com PDO (ainda melhor)
function buscar_usuario_pdo($pdo, $nome) {
    try {
        $stmt = $pdo->prepare("SELECT * FROM usuarios WHERE nome = :nome");
        $stmt->execute(['nome' => $nome]);
        return $stmt->fetch(PDO::FETCH_ASSOC);
    } catch (PDOException $e) {
        error_log("Erro PDO: " . $e->getMessage());
        throw new Exception("Erro ao buscar usuário");
    }
}
?>
```

### Cenário 3: Erro de Lógica em Cálculo

**Problema:**
```php
<?php
class CalculadoraDesconto {
    public function calcular($valor, $percentual) {
        // Bug: desconto errado
        return $valor - $percentual;
    }
}

$calc = new CalculadoraDesconto();
$preco_final = $calc->calcular(100, 10);  // Esperado: 90, Resultado: 90 ✓

$preco_final = $calc->calcular(100, 15);  // Esperado: 85, Resultado: 85 ✓
$preco_final = $calc->calcular(100, 50);  // Esperado: 50, Resultado: 50 ✗
// Bug só aparece quando percebemos que 50 deveria ser 50% (resultado = 50)
?>
```

**Depuração com Xdebug e PHPUnit:**

```php
<?php
// Teste revela o problema
class CalculadoraDescontoTest extends PHPUnit\Framework\TestCase {
    public function testCalcularDesconto10Porcento() {
        $calc = new CalculadoraDesconto();
        $resultado = $calc->calcular(100, 10);
        
        // Debug: O que realmente acontece?
        var_dump($resultado);  // int(90) - Mas por acidente!
        
        $this->assertEquals(90, $resultado);  // Passa mas está errado!
    }
    
    public function testCalcularDesconto50Porcento() {
        $calc = new CalculadoraDesconto();
        $resultado = $calc->calcular(100, 50);
        
        // Esperado: 50 (50% de desconto)
        // Real: 50 (100 - 50)
        // Funciona por acidente!
        $this->assertEquals(50, $resultado);
    }
    
    public function testCalcularDesconto25Porcento() {
        $calc = new CalculadoraDesconto();
        $resultado = $calc->calcular(200, 25);
        
        // Esperado: 150 (25% de 200 = 50, 200 - 50 = 150)
        // Real: 175 (200 - 25)
        $this->assertEquals(150, $resultado);  // FALHA! Bug encontrado!
    }
}
?>
```

**Solução correta:**

```php
<?php
class CalculadoraDesconto {
    public function calcular(float $valor, float $percentual): float {
        // Log para debug
        error_log(sprintf(
            "Calculando: valor=%.2f, percentual=%.2f%%",
            $valor,
            $percentual
        ));
        
        // Cálculo correto
        $desconto = $valor * ($percentual / 100);
        $valor_final = $valor - $desconto;
        
        error_log(sprintf(
            "Resultado: desconto=%.2f, valor_final=%.2f",
            $desconto,
            $valor_final
        ));
        
        return $valor_final;
    }
}
?>
```

### Cenário 4: Memory Limit Exceeded

**Problema:**
```php
<?php
// Fatal error: Allowed memory size exhausted
function processar_arquivo_grande($arquivo) {
    $conteudo = file_get_contents($arquivo);  // Carrega tudo na memória!
    $linhas = explode("\n", $conteudo);
    
    foreach ($linhas as $linha) {
        processar_linha($linha);
    }
}
?>
```

**Depuração:**

```php
<?php
function processar_arquivo_debug($arquivo) {
    // Verificar tamanho do arquivo
    $tamanho = filesize($arquivo);
    error_log("Tamanho do arquivo: " . number_format($tamanho / 1024 / 1024, 2) . " MB");
    
    // Verificar limite de memória
    $limite = ini_get('memory_limit');
    error_log("Limite de memória: " . $limite);
    
    // Monitorar uso de memória
    $inicio = memory_get_usage(true);
    error_log("Memória inicial: " . number_format($inicio / 1024 / 1024, 2) . " MB");
    
    $conteudo = file_get_contents($arquivo);
    
    $depois = memory_get_usage(true);
    error_log("Memória após ler: " . number_format($depois / 1024 / 1024, 2) . " MB");
    error_log("Delta: " . number_format(($depois - $inicio) / 1024 / 1024, 2) . " MB");
}
?>
```

**Solução:**

```php
<?php
function processar_arquivo_eficiente($arquivo) {
    // Processar linha por linha, sem carregar tudo
    $handle = fopen($arquivo, 'r');
    
    if (!$handle) {
        throw new RuntimeException("Não foi possível abrir o arquivo");
    }
    
    $linha_numero = 0;
    while (($linha = fgets($handle)) !== false) {
        $linha_numero++;
        
        try {
            processar_linha($linha);
        } catch (Exception $e) {
            error_log("Erro na linha $linha_numero: " . $e->getMessage());
        }
        
        // Debug a cada 1000 linhas
        if ($linha_numero % 1000 == 0) {
            $memoria = memory_get_usage(true) / 1024 / 1024;
            error_log("Linha $linha_numero, Memória: " . number_format($memoria, 2) . " MB");
        }
    }
    
    fclose($handle);
}
?>
```

### Cenário 5: Sessões Não Funcionando

**Problema:**
```php
<?php
// pagina1.php
session_start();
$_SESSION['usuario'] = 'João';
header('Location: pagina2.php');

// pagina2.php
session_start();
echo $_SESSION['usuario'];  // Notice: Undefined index
?>
```

**Depuração:**

```php
<?php
// Debug de sessões
function debug_sessao() {
    echo "<pre>";
    echo "Session ID: " . session_id() . "\n";
    echo "Session Status: " . session_status() . "\n";
    echo "Session Save Path: " . session_save_path() . "\n";
    echo "Session Name: " . session_name() . "\n";
    echo "\nConteúdo da Sessão:\n";
    print_r($_SESSION);
    echo "</pre>";
}

// pagina1.php
session_start();
$_SESSION['usuario'] = 'João';
debug_sessao();

// Verificar se sessão foi gravada
echo "Cookie enviado: " . (isset($_COOKIE[session_name()]) ? 'Sim' : 'Não');
?>
```

**Problemas comuns e soluções:**

```php
<?php
// Problema 1: Output antes de session_start()
// ERRADO:
echo "Olá";
session_start();  // Warning: Cannot send session cookie

// CERTO:
session_start();
echo "Olá";

// Problema 2: Permissões no diretório de sessões
$save_path = session_save_path();
if (!is_writable($save_path)) {
    error_log("Diretório de sessões não é gravável: $save_path");
    // Definir caminho alternativo
    session_save_path('/tmp/sessions');
}

// Problema 3: Cookies bloqueados
// Verificar se cookies funcionam
if (!isset($_COOKIE[session_name()]) && isset($_GET['teste'])) {
    die("Cookies estão bloqueados ou não estão funcionando");
}
?>
```

## Depuração com Xdebug

### Instalação e Configuração

```bash
# Instalar Xdebug
pecl install xdebug

# Ou no Ubuntu/Debian
apt-get install php-xdebug
```

**Configuração php.ini:**
```ini
[Xdebug]
zend_extension=xdebug.so
xdebug.mode=debug,develop
xdebug.start_with_request=trigger
xdebug.client_host=127.0.0.1
xdebug.client_port=9003

; Logging para debug
xdebug.log=/tmp/xdebug.log
xdebug.log_level=7

; Pretty var_dump
xdebug.var_display_max_depth=5
xdebug.var_display_max_children=128
xdebug.var_display_max_data=512
```

### Uso com VSCode

**launch.json:**
```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "name": "Listen for Xdebug",
            "type": "php",
            "request": "launch",
            "port": 9003,
            "pathMappings": {
                "/var/www/html": "${workspaceFolder}"
            }
        }
    ]
}
```

**Workflow:**
1. Coloque breakpoints no VSCode
2. Inicie "Listen for Xdebug" (F5)
3. Acesse URL com trigger: `http://localhost/script.php?XDEBUG_TRIGGER=1`
4. VSCode para nos breakpoints

### Uso com PHPStorm

1. **Configurar interpretador PHP**
   - Settings → PHP → CLI Interpreter
   
2. **Configurar Xdebug**
   - Settings → PHP → Debug → Xdebug
   - Port: 9003

3. **Iniciar debug**
   - Ativar "Start Listening for PHP Debug Connections"
   - Instalar extensão browser (Xdebug Helper)
   - Ativar debug no browser
   - Colocar breakpoints e acessar página

### Debug de CLI

```bash
# Exportar variável de ambiente
export XDEBUG_TRIGGER=1

# Executar script
php script.php

# Ou diretamente
php -dxdebug.mode=debug -dxdebug.start_with_request=yes script.php
```

## Logging Profissional em PHP

### Usando Monolog

```php
<?php
use Monolog\Logger;
use Monolog\Handler\StreamHandler;
use Monolog\Handler\FirePHPHandler;
use Monolog\Formatter\LineFormatter;
use Monolog\Processor\IntrospectionProcessor;
use Monolog\Processor\WebProcessor;

class LoggerFactory {
    public static function create($name = 'app') {
        $logger = new Logger($name);
        
        // Handler para arquivo
        $stream = new StreamHandler('logs/app.log', Logger::DEBUG);
        $formatter = new LineFormatter(
            "[%datetime%] %channel%.%level_name%: %message% %context%\n",
            "Y-m-d H:i:s"
        );
        $stream->setFormatter($formatter);
        
        // Adiciona informações de arquivo/linha
        $logger->pushProcessor(new IntrospectionProcessor());
        
        // Adiciona informações de request HTTP
        $logger->pushProcessor(new WebProcessor());
        
        $logger->pushHandler($stream);
        
        // Handler para erros críticos (email)
        if (php_sapi_name() !== 'cli') {
            $mailer = new \Monolog\Handler\NativeMailerHandler(
                'admin@example.com',
                'Erro crítico na aplicação',
                'noreply@example.com',
                Logger::ERROR
            );
            $logger->pushHandler($mailer);
        }
        
        return $logger;
    }
}

// Uso
$logger = LoggerFactory::create();

$logger->debug('Valor da variável', ['valor' => $x]);
$logger->info('Usuário logado', ['user_id' => 123]);
$logger->warning('Memória alta', ['usage' => memory_get_usage()]);
$logger->error('Falha ao conectar', ['error' => $e->getMessage()]);
?>
```

## Técnicas Avançadas

### 1. Error Handler Customizado

```php
<?php
set_error_handler(function($errno, $errstr, $errfile, $errline) {
    $mensagem = sprintf(
        "[%s] %s in %s on line %d",
        error_type_to_string($errno),
        $errstr,
        $errfile,
        $errline
    );
    
    error_log($mensagem);
    
    // Em desenvolvimento, mostrar
    if (getenv('APP_ENV') === 'development') {
        echo "<pre>$mensagem</pre>";
    }
    
    // Não executar handler padrão do PHP
    return true;
});

function error_type_to_string($type) {
    switch($type) {
        case E_ERROR: return 'ERROR';
        case E_WARNING: return 'WARNING';
        case E_PARSE: return 'PARSE';
        case E_NOTICE: return 'NOTICE';
        default: return 'UNKNOWN';
    }
}
?>
```

### 2. Exception Handler Global

```php
<?php
set_exception_handler(function($exception) {
    $mensagem = sprintf(
        "Uncaught %s: %s in %s:%d\nStack trace:\n%s",
        get_class($exception),
        $exception->getMessage(),
        $exception->getFile(),
        $exception->getLine(),
        $exception->getTraceAsString()
    );
    
    error_log($mensagem);
    
    // Resposta HTTP apropriada
    http_response_code(500);
    
    if (getenv('APP_ENV') === 'development') {
        echo "<pre>$mensagem</pre>";
    } else {
        echo "Erro interno do servidor";
    }
});
?>
```

### 3. Profiling com Xdebug

```ini
; php.ini
xdebug.mode=profile
xdebug.output_dir=/tmp/xdebug
xdebug.profiler_output_name=cachegrind.out.%p
```

```bash
# Analisar com KCacheGrind
kcachegrind /tmp/xdebug/cachegrind.out.12345

# Ou com webgrind (browser-based)
```

## Checklist de Debug PHP

- [ ] Ativei error_reporting(E_ALL) em desenvolvimento?
- [ ] Configurei display_errors apropriadamente?
- [ ] Tenho logs configurados e acessíveis?
- [ ] Xdebug está instalado e configurado?
- [ ] Verifico valores com var_dump/print_r?
- [ ] Uso prepared statements em queries SQL?
- [ ] Monitoro uso de memória em operações grandes?
- [ ] Testo com diferentes configurações de php.ini?

## Próximos Passos

- 🐍 [Depuração em Python](06-depuracao-python.md)
- 🎯 [Boas Práticas](07-boas-praticas.md)

---

**📚 Recursos:**
- [Xdebug Documentation](https://xdebug.org/docs/)
- [PHP Debugging Guide](https://www.php.net/manual/en/debugger.php)
- [Monolog Documentation](https://github.com/Seldaek/monolog)
