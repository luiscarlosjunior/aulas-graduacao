# Herança em PHP

Este exemplo demonstra a **herança**, um dos pilares fundamentais da POO que permite criar novas classes baseadas em classes existentes, promovendo a reutilização de código.

## 🎯 O que você vai aprender

- Como criar classes filhas usando `extends`
- Reutilização de código através de herança
- Sobrescrita de métodos (method overriding)
- Uso da palavra-chave `parent::`
- Hierarquia de classes

## 📝 Conceito: Herança

**Herança** é o mecanismo que permite que uma classe (classe filha) herde características (propriedades e métodos) de outra classe (classe pai), podendo especializar ou modificar comportamentos.

### Vantagens da Herança:
- ♻️ **Reutilização** - Evita duplicação de código
- 🏗️ **Extensibilidade** - Facilita adicionar novas funcionalidades
- 🎯 **Especialização** - Permite criar versões específicas
- 🧱 **Hierarquia** - Organiza código de forma lógica

## 🔍 Estrutura do Exemplo

### Hierarquia de Classes
```
Cachorro (classe pai)
    ├── Poddle (classe filha)
    └── Pinscher (classe filha)
```

### [Cachorro.php](Cachorro.php) - Classe Pai
```php
class Cachorro {
    public string $nome;
    public string $raca;
    public string $corPelo;

    public function Latir() {
        print("Au au!" . PHP_EOL);
    }

    public function Raca() {
        echo "Cachorro da raça " . $this->raca . PHP_EOL;
    }
}
```

### [poddle.php](poddle.php) - Classe Filha
```php
class Poddle extends Cachorro {
    // Herda todas as propriedades e métodos de Cachorro
    
    // Pode adicionar novos métodos
    public function brincar() {
        echo "Estou brincando..." . PHP_EOL;
    }
    
    // Pode sobrescrever métodos existentes
    public function Latir() {
        echo "Au au au au au ! Au!" . PHP_EOL;
    }
}
```

### [Pinscher.php](Pinscher.php) - Outra Classe Filha
```php
class Pinscher extends Cachorro {
    // Métodos específicos do Pinscher
    public function tremer() {
        echo "Estou tremendo..." . PHP_EOL;
    }
    
    // Sobrescreve o método Latir
    public function Latir() {
        echo "Hau! grgrgrgrgrgr Hau!" . PHP_EOL;
    }
}
```

## 🚀 Executando o Exemplo

```bash
cd programming/php/02-poo/04-mundocao/heranca/
php Principal.php
```

**Saída esperada:**
```
Au au!
Sou um cão da raça Poddle
Meu nome é Fex e tenho 2 de idade.
Estou tremendo...
Sou um cão da raça Pinscher pequeno
Meu nome é Fex e tenho 4 de idade.
```

## 💡 Conceitos Importantes

### 1. **Palavra-chave `extends`**
```php
class ClasseFilha extends ClassePai {
    // A classe filha herda tudo da classe pai
}
```

### 2. **Sobrescrita de Métodos**
```php
class Animal {
    public function som() {
        echo "Algum som";
    }
}

class Cachorro extends Animal {
    public function som() {
        echo "Au au!"; // Sobrescreve o método da classe pai
    }
}
```

### 3. **Chamando Método da Classe Pai**
```php
class Cachorro extends Animal {
    public function som() {
        parent::som(); // Chama o método da classe pai primeiro
        echo " Au au!"; // Adiciona comportamento específico
    }
}
```

### 4. **Construtor e Herança**
```php
class Animal {
    protected string $nome;
    
    public function __construct(string $nome) {
        $this->nome = $nome;
    }
}

class Cachorro extends Animal {
    private string $raca;
    
    public function __construct(string $nome, string $raca) {
        parent::__construct($nome); // Chama construtor pai
        $this->raca = $raca;
    }
}
```

## 🎯 Exemplo Prático Completo

```php
// Classe pai
class Veiculo {
    protected string $marca;
    protected string $modelo;
    protected int $ano;
    
    public function __construct(string $marca, string $modelo, int $ano) {
        $this->marca = $marca;
        $this->modelo = $modelo;
        $this->ano = $ano;
    }
    
    public function acelerar(): string {
        return "Veículo acelerando...";
    }
    
    public function getInfo(): string {
        return "{$this->marca} {$this->modelo} ({$this->ano})";
    }
}

// Classe filha
class Carro extends Veiculo {
    private int $portas;
    
    public function __construct(string $marca, string $modelo, int $ano, int $portas) {
        parent::__construct($marca, $modelo, $ano);
        $this->portas = $portas;
    }
    
    // Sobrescreve método pai
    public function acelerar(): string {
        return "Carro acelerando com motor...";
    }
    
    // Novo método específico
    public function abrirPortas(): string {
        return "Abrindo {$this->portas} portas";
    }
}

// Uso
$carro = new Carro("Toyota", "Corolla", 2024, 4);
echo $carro->getInfo(); // Método herdado
echo $carro->acelerar(); // Método sobrescrito
echo $carro->abrirPortas(); // Método específico
```

## 🔧 Tipos de Herança

### 1. **Herança Simples**
```php
class A extends B {
    // B é pai de A
}
```

### 2. **Herança Múltipla (via Traits)**
```php
trait TraitA {
    public function metodoA() { }
}

trait TraitB {
    public function metodoB() { }
}

class MinhaClasse {
    use TraitA, TraitB; // "Herda" de múltiplas traits
}
```

### 3. **Herança Multinível**
```php
class Animal { }
class Mamifero extends Animal { }
class Cachorro extends Mamifero { }
// Cachorro herda de Mamifero que herda de Animal
```

## 🎯 Exercícios Práticos

### Exercício 1: Funcionários
```php
class Funcionario {
    protected string $nome;
    protected float $salarioBase;
    
    // Implementar construtor e método calcularSalario()
}

class Gerente extends Funcionario {
    private float $bonus;
    
    // Sobrescrever calcularSalario() incluindo bonus
}

class Vendedor extends Funcionario {
    private float $comissao;
    
    // Sobrescrever calcularSalario() incluindo comissao
}
```

### Exercício 2: Formas Geométricas
```php
abstract class Forma {
    protected string $cor;
    
    abstract public function calcularArea(): float;
    abstract public function calcularPerimetro(): float;
}

class Retangulo extends Forma {
    // Implementar métodos abstratos
}

class Circulo extends Forma {
    // Implementar métodos abstratos
}
```

### Exercício 3: Conta Bancária
```php
class ContaBancaria {
    protected float $saldo;
    protected string $titular;
    
    public function depositar(float $valor): void { }
    public function sacar(float $valor): bool { }
}

class ContaCorrente extends ContaBancaria {
    private float $limite;
    
    // Sobrescrever sacar() para considerar limite
}

class ContaPoupanca extends ContaBancaria {
    private float $taxaRendimento;
    
    // Adicionar método aplicarRendimento()
}
```

## 🛡️ Boas Práticas

### 1. **Use protected para propriedades que classes filhas podem precisar**
```php
class Pai {
    protected $propriedadeParaFilhas; // ✅
    private $propriedadePrivada;      // ✅
    public $propriedadePublica;       // ❌ Evite
}
```

### 2. **Chame o construtor pai quando necessário**
```php
class Filha extends Pai {
    public function __construct($param1, $param2) {
        parent::__construct($param1); // ✅
        $this->propriedadeEspecifica = $param2;
    }
}
```

### 3. **Use final para impedir herança/sobrescrita**
```php
final class ClasseFinal {
    // Não pode ser herdada
}

class Pai {
    final public function metodoFinal() {
        // Não pode ser sobrescrito
    }
}
```

## 📚 Próximos Conceitos

Após dominar herança:

1. **[Polimorfismo](../polimorfismo/)** - Comportamentos dinâmicos
2. **[Classes Abstratas](../abstratas/)** - Templates para herança
3. **[Interfaces](../interfaces/)** - Contratos de implementação

## ❓ Perguntas Frequentes

**Q: PHP suporta herança múltipla?**
A: Não diretamente, mas você pode usar Traits para simular herança múltipla.

**Q: Posso acessar propriedades private da classe pai?**
A: Não, use protected se a classe filha precisar acessar.

**Q: Como sei se devo usar herança ou composição?**
A: Use herança para relação "é um" (Cachorro é um Animal). Use composição para "tem um" (Carro tem um Motor).

---

💡 **Dica**: Herança é poderosa, mas use com moderação. Prefira composição quando a relação não for claramente "é um tipo de"!