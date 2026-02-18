# 🎯 Boas Práticas e Prevenção de Bugs

## Escrever Código que Não Precisa de Debug

**Robert C. Martin** em *"Clean Code"* (2008) afirma:

> "The proper use of comments is to compensate for our failure to express ourselves in code. Note that I used the word failure. I meant it. Comments are always failures."

Estenda isso para debugging:

> **"A melhor depuração é aquela que você não precisa fazer."**

## Princípios de Prevenção

### 1. KISS - Keep It Simple, Stupid

**Complexidade é inimiga da correção.**

**Ruim:**
```java
public double calcular(double a, double b, double c, int tipo, boolean flag) {
    return tipo == 1 ? (flag ? a * b + c : a * b - c) 
                    : (flag ? a / b + c : a / b - c);
}
```

**Bom:**
```java
public double multiplicarComAjuste(double a, double b, double ajuste) {
    return (a * b) + ajuste;
}

public double dividirComAjuste(double a, double b, double ajuste) {
    if (b == 0) {
        throw new IllegalArgumentException("Divisor não pode ser zero");
    }
    return (a / b) + ajuste;
}
```

### 2. DRY - Don't Repeat Yourself

**Código duplicado = bugs duplicados.**

**Ruim:**
```python
# Lógica repetida em 3 lugares
def processar_pedido_online(pedido):
    if pedido.valor < 0:
        raise ValueError("Valor inválido")
    if pedido.quantidade < 1:
        raise ValueError("Quantidade inválida")
    # ... processar

def processar_pedido_loja(pedido):
    if pedido.valor < 0:
        raise ValueError("Valor inválido")
    if pedido.quantidade < 1:
        raise ValueError("Quantidade inválida")
    # ... processar
```

**Bom:**
```python
def validar_pedido(pedido):
    """Validação centralizada"""
    if pedido.valor < 0:
        raise ValueError("Valor inválido")
    if pedido.quantidade < 1:
        raise ValueError("Quantidade inválida")

def processar_pedido_online(pedido):
    validar_pedido(pedido)
    # ... processar

def processar_pedido_loja(pedido):
    validar_pedido(pedido)
    # ... processar
```

### 3. YAGNI - You Aren't Gonna Need It

**Não adicione funcionalidade que "pode ser útil no futuro".**

**Ruim:**
```php
<?php
class Usuario {
    private $nome;
    private $email;
    private $telefone;
    private $endereco;
    private $cpf;
    private $rg;
    // ... 50 campos que "podem ser úteis"
    
    // 200 getters e setters
    // Impossível de testar e debugar
}
?>
```

**Bom:**
```php
<?php
// Comece simples
class Usuario {
    private $nome;
    private $email;
    
    public function __construct(string $nome, string $email) {
        $this->nome = $nome;
        $this->email = $email;
    }
    
    // Adicione campos conforme necessário
}
?>
```

### 4. Fail Fast

**Detecte erros o mais cedo possível.**

**Ruim:**
```java
public class Pedido {
    private Cliente cliente;
    private List<Item> itens;
    
    public double calcularTotal() {
        // NPE aqui se cliente ou itens forem null
        return itens.stream()
            .mapToDouble(Item::getPreco)
            .sum();
    }
}
```

**Bom:**
```java
public class Pedido {
    private final Cliente cliente;
    private final List<Item> itens;
    
    public Pedido(Cliente cliente, List<Item> itens) {
        // Fail fast: valida no construtor
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Pedido deve ter pelo menos um item");
        }
        
        this.cliente = cliente;
        this.itens = new ArrayList<>(itens);  // Cópia defensiva
    }
    
    public double calcularTotal() {
        // Seguro: itens nunca é null ou vazia
        return itens.stream()
            .mapToDouble(Item::getPreco)
            .sum();
    }
}
```

## Defensive Programming

### Validação de Entrada

**Sempre valide dados externos:**

```python
def dividir(numerador: float, denominador: float) -> float:
    """
    Divide dois números.
    
    Args:
        numerador: Número a ser dividido
        denominador: Divisor (não pode ser zero)
    
    Returns:
        Resultado da divisão
    
    Raises:
        ValueError: Se denominador for zero
        TypeError: Se argumentos não forem números
    """
    # Validação de tipo
    if not isinstance(numerador, (int, float)):
        raise TypeError(f"Numerador deve ser número, recebido {type(numerador)}")
    
    if not isinstance(denominador, (int, float)):
        raise TypeError(f"Denominador deve ser número, recebido {type(denominador)}")
    
    # Validação de valor
    if denominador == 0:
        raise ValueError("Denominador não pode ser zero")
    
    return numerador / denominador
```

### Assertions para Invariantes

**Use assertions para condições que "nunca deveriam acontecer":**

```java
public class ContaBancaria {
    private double saldo;
    
    public void debitar(double valor) {
        assert valor > 0 : "Valor de débito deve ser positivo";
        assert saldo >= valor : "Saldo insuficiente";
        
        saldo -= valor;
        
        assert saldo >= 0 : "Saldo nunca pode ser negativo";
    }
}

// Executar com: java -ea MinhaClasse (enable assertions)
```

**Em Python:**
```python
def calcular_media(lista):
    assert len(lista) > 0, "Lista não pode estar vazia"
    
    soma = sum(lista)
    media = soma / len(lista)
    
    assert media == sum(lista) / len(lista), "Invariante violada"
    
    return media
```

### Cópias Defensivas

**Proteja objetos internos:**

```java
public class Agenda {
    private List<Evento> eventos;
    
    // Ruim: expõe referência interna
    public List<Evento> getEventos() {
        return eventos;
    }
    
    // Bom: retorna cópia
    public List<Evento> getEventos() {
        return new ArrayList<>(eventos);
    }
    
    // Ou imutável
    public List<Evento> getEventos() {
        return Collections.unmodifiableList(eventos);
    }
}
```

## Test-Driven Development (TDD)

**Kent Beck**: *"Test-Driven Development: By Example"* (2002)

### Ciclo Red-Green-Refactor

```
1. Red:   Escreva teste que falha
2. Green: Implemente código mínimo para passar
3. Refactor: Melhore o código mantendo testes passando
```

**Exemplo:**

```python
import unittest

# 1. RED: Teste falha (função nem existe)
class TestCalculadora(unittest.TestCase):
    def test_adicao_numeros_positivos(self):
        calc = Calculadora()
        resultado = calc.adicionar(2, 3)
        self.assertEqual(5, resultado)

# 2. GREEN: Implementação mínima
class Calculadora:
    def adicionar(self, a, b):
        return a + b

# 3. REFACTOR: Adicionar validação
class Calculadora:
    def adicionar(self, a, b):
        if not isinstance(a, (int, float)) or not isinstance(b, (int, float)):
            raise TypeError("Argumentos devem ser números")
        return a + b

# Adicionar mais testes
class TestCalculadora(unittest.TestCase):
    def test_adicao_com_zero(self):
        calc = Calculadora()
        self.assertEqual(5, calc.adicionar(5, 0))
    
    def test_adicao_com_negativos(self):
        calc = Calculadora()
        self.assertEqual(-1, calc.adicionar(2, -3))
    
    def test_adicao_tipo_invalido(self):
        calc = Calculadora()
        with self.assertRaises(TypeError):
            calc.adicionar("2", 3)
```

### Cobertura de Código

```bash
# Python: coverage
pip install coverage
coverage run -m pytest
coverage report
coverage html  # Relatório visual

# Java: JaCoCo
# PHP: PHPUnit com --coverage-html
phpunit --coverage-html coverage/
```

**Meta recomendada:** 80-90% de cobertura (não 100%)

## Code Review

### Checklist de Review

**Funcionalidade:**
- [ ] O código faz o que deveria?
- [ ] Todos os casos de uso estão cobertos?
- [ ] Edge cases estão tratados?
- [ ] Erros são tratados apropriadamente?

**Legibilidade:**
- [ ] Nomes de variáveis são descritivos?
- [ ] Funções têm responsabilidade única?
- [ ] Código está bem organizado?
- [ ] Há comentários onde necessário?

**Qualidade:**
- [ ] Não há código duplicado?
- [ ] Segue padrões do projeto?
- [ ] Testes adequados foram adicionados?
- [ ] Performance é aceitável?

**Segurança:**
- [ ] Entrada do usuário é validada?
- [ ] Não há SQL injection?
- [ ] Senhas/tokens não estão hardcoded?
- [ ] Dados sensíveis estão protegidos?

### Pair Programming

**Benefícios para prevenção de bugs:**
1. Revisão em tempo real
2. Conhecimento compartilhado
3. Menos distrações
4. Soluções mais criativas

## Ferramentas de Análise Estática

### Java: SpotBugs, PMD, Checkstyle

```xml
<!-- Maven pom.xml -->
<plugin>
    <groupId>com.github.spotbugs</groupId>
    <artifactId>spotbugs-maven-plugin</artifactId>
    <version>4.7.3.0</version>
    <configuration>
        <effort>Max</effort>
        <threshold>Low</threshold>
    </configuration>
</plugin>
```

### Python: pylint, flake8, mypy

```bash
# Instalar
pip install pylint flake8 mypy

# Executar
pylint meu_modulo.py
flake8 meu_modulo.py
mypy meu_modulo.py

# .pylintrc para configurar
[MESSAGES CONTROL]
disable=C0111  # missing-docstring
max-line-length=100
```

### PHP: PHPStan, Psalm

```bash
# Instalar
composer require --dev phpstan/phpstan

# Executar (níveis 0-9)
./vendor/bin/phpstan analyse src --level 7
```

## Design Patterns para Código Robusto

### 1. Null Object Pattern

**Evita NPE/AttributeError:**

```java
// Ruim: Verificações null por todo código
if (cliente != null) {
    if (cliente.getEndereco() != null) {
        String cidade = cliente.getEndereco().getCidade();
        if (cidade != null) {
            // usar cidade
        }
    }
}

// Bom: Null Object
public class EnderecoNulo extends Endereco {
    @Override
    public String getCidade() {
        return "Não informado";
    }
}

public class Cliente {
    private Endereco endereco = new EnderecoNulo();  // Nunca null
    
    public Endereco getEndereco() {
        return endereco;  // Sempre retorna objeto válido
    }
}

// Uso simples
String cidade = cliente.getEndereco().getCidade();  // Sem verificações
```

### 2. Builder Pattern

**Evita construtores complexos e estados inválidos:**

```python
# Ruim: Construtor telescópico
class Usuario:
    def __init__(self, nome, email, telefone=None, endereco=None, 
                 cpf=None, rg=None, data_nascimento=None):
        # Qual ordem dos parâmetros? Fácil errar!
        pass

# Bom: Builder
class UsuarioBuilder:
    def __init__(self):
        self._usuario = Usuario()
    
    def com_nome(self, nome):
        self._usuario.nome = nome
        return self
    
    def com_email(self, email):
        self._usuario.email = email
        return self
    
    def com_telefone(self, telefone):
        self._usuario.telefone = telefone
        return self
    
    def construir(self):
        # Validação centralizada
        if not self._usuario.nome:
            raise ValueError("Nome é obrigatório")
        if not self._usuario.email:
            raise ValueError("Email é obrigatório")
        return self._usuario

# Uso claro e sem erros
usuario = (UsuarioBuilder()
    .com_nome("João")
    .com_email("joao@example.com")
    .com_telefone("11999999999")
    .construir())
```

### 3. Strategy Pattern

**Evita if/else complexos:**

```php
<?php
// Ruim: if/else nightmare
function calcularDesconto($tipo, $valor) {
    if ($tipo == 'black_friday') {
        return $valor * 0.5;
    } elseif ($tipo == 'natal') {
        return $valor * 0.3;
    } elseif ($tipo == 'cliente_vip') {
        return $valor * 0.2;
    } else {
        return $valor * 0.1;
    }
}

// Bom: Strategy Pattern
interface EstrategiaDesconto {
    public function calcular(float $valor): float;
}

class DescontoBlackFriday implements EstrategiaDesconto {
    public function calcular(float $valor): float {
        return $valor * 0.5;
    }
}

class DescontoNatal implements EstrategiaDesconto {
    public function calcular(float $valor): float {
        return $valor * 0.3;
    }
}

class CalculadoraDesconto {
    private $estrategia;
    
    public function __construct(EstrategiaDesconto $estrategia) {
        $this->estrategia = $estrategia;
    }
    
    public function calcular(float $valor): float {
        return $this->estrategia->calcular($valor);
    }
}

// Uso
$calc = new CalculadoraDesconto(new DescontoBlackFriday());
$desconto = $calc->calcular(100);  // 50
?>
```

## Logging Estratégico

### O que Logar

**✅ Logar:**
- Início e fim de operações importantes
- Parâmetros de entrada (sem dados sensíveis)
- Decisões tomadas (qual branch de if)
- Erros e exceções (com contexto)
- Métricas de performance
- Eventos de negócio

**❌ Não Logar:**
- Senhas ou tokens
- Dados pessoais sensíveis (CPF, cartão de crédito)
- Dentro de loops frequentes (poluição)
- Informação redundante

### Níveis Apropriados

```python
# TRACE/DEBUG: Desenvolvimento
logger.debug(f"Processando item {item.id}, tentativa {tentativa}")

# INFO: Eventos importantes
logger.info(f"Usuário {user.id} logado com sucesso")

# WARNING: Problemas potenciais
logger.warning(f"Cache miss para chave {key}, buscando do banco")

# ERROR: Erros recuperáveis
logger.error(f"Falha ao enviar email para {email}: {e}", exc_info=True)

# CRITICAL: Sistema em estado crítico
logger.critical("Banco de dados inacessível, sistema em modo degradado")
```

## Monitoramento e Alertas

### Métricas Importantes

```python
# Instrumentação com Prometheus
from prometheus_client import Counter, Histogram, Gauge

# Contador: Requisições processadas
requisicoes = Counter('app_requisicoes_total', 'Total de requisições')

# Histograma: Latência
latencia = Histogram('app_latencia_segundos', 'Latência das requisições')

# Gauge: Recursos atuais
memoria_uso = Gauge('app_memoria_mb', 'Uso de memória em MB')

@latencia.time()
def processar_requisicao():
    requisicoes.inc()
    # ... processamento
    memoria_uso.set(get_memory_usage())
```

### Alertas

```yaml
# Exemplo com Prometheus Alertmanager
groups:
- name: aplicacao
  rules:
  - alert: TaxaErroAlta
    expr: rate(app_erros_total[5m]) > 0.05
    for: 5m
    annotations:
      summary: "Taxa de erro acima de 5%"
      
  - alert: LatenciaAlta
    expr: app_latencia_segundos{quantile="0.95"} > 2
    for: 10m
    annotations:
      summary: "Latência P95 acima de 2s"
```

## Documentação Como Prevenção

### Docstrings Úteis

```python
def processar_pagamento(
    valor: float,
    metodo: str,
    parcelas: int = 1
) -> Dict[str, Any]:
    """
    Processa pagamento de um pedido.
    
    Args:
        valor: Valor total do pagamento em reais. Deve ser positivo.
        metodo: Método de pagamento ('credito', 'debito', 'pix').
        parcelas: Número de parcelas (padrão: 1). Apenas para crédito.
    
    Returns:
        Dicionário com:
        - 'id': ID da transação
        - 'status': Status ('aprovado', 'recusado', 'pendente')
        - 'mensagem': Mensagem descritiva
    
    Raises:
        ValueError: Se valor <= 0 ou método inválido
        PaymentError: Se gateway de pagamento falhar
    
    Examples:
        >>> processar_pagamento(100.50, 'pix')
        {'id': '123', 'status': 'aprovado', 'mensagem': 'Pagamento confirmado'}
        
        >>> processar_pagamento(500.00, 'credito', parcelas=3)
        {'id': '124', 'status': 'aprovado', 'mensagem': 'Pagamento parcelado'}
    
    Note:
        Pagamentos em PIX são processados instantaneamente.
        Crédito pode levar até 2 dias úteis para confirmação.
    """
    # Implementação...
```

## Cultura de Qualidade

### Definition of Done

**Antes de considerar uma tarefa "pronta":**
- [ ] Código implementado e funcional
- [ ] Testes unitários escritos e passando
- [ ] Testes de integração (se aplicável)
- [ ] Code review aprovado
- [ ] Documentação atualizada
- [ ] Sem warnings de linter
- [ ] Análise estática sem erros críticos
- [ ] Testado manualmente
- [ ] Deploy em ambiente de staging

### Cerimônias Úteis

**1. Bug Triage** (semanal)
- Revisar bugs abertos
- Priorizar correções
- Identificar padrões

**2. Postmortem** (após incidentes)
- O que aconteceu?
- Por que aconteceu?
- Como prevenir?
- Ações concretas

**3. Tech Debt Review** (mensal)
- Identificar débito técnico
- Planejar refatorações
- Equilibrar features vs qualidade

## Resumo: Pirâmide de Prevenção

```
                   /\
                  /  \
                 / CI \
                /------\
               / Static \
              / Analysis \
             /------------\
            /  Code Review \
           /----------------\
          /   Unit Tests     \
         /--------------------\
        /   Good Practices     \
       /------------------------\
      /   Clean Code Principles  \
     /----------------------------\
```

**Base:** Código limpo e simples
**Camadas superiores:** Ferramentas e processos

## Conclusão

**Gerald Weinberg** resumiu perfeitamente:

> "If builders built buildings the way programmers wrote programs, then the first woodpecker that came along would destroy civilization."

Prevenir bugs é mais eficaz e barato que corrigi-los. Invista em:
1. **Educação**: Aprenda boas práticas
2. **Ferramentas**: Use linters, testes, CI/CD
3. **Cultura**: Code review, pair programming
4. **Processo**: TDD, Definition of Done
5. **Monitoramento**: Detecte problemas cedo

---

**📚 Leituras Recomendadas:**
- Martin, R. C. (2008). "Clean Code"
- Fowler, M. (2018). "Refactoring"
- Beck, K. (2002). "Test-Driven Development"
- Thomas, D. & Hunt, A. (1999). "The Pragmatic Programmer"
