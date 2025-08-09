# Classes e Objetos Básicos em PHP

Este é seu primeiro contato com **Programação Orientada a Objetos** em PHP. Aqui você aprenderá os conceitos fundamentais de classes e objetos de forma simples e prática.

## 🎯 O que você vai aprender

- O que são classes e objetos
- Como criar e usar classes básicas
- Propriedades e métodos
- Instanciação de objetos
- Acesso a propriedades e métodos

## 📝 Conceitos Fundamentais

### O que é uma Classe?
Uma **classe** é um modelo ou molde que define as características (propriedades) e comportamentos (métodos) que um objeto pode ter.

### O que é um Objeto?
Um **objeto** é uma instância de uma classe - é a "coisa real" criada a partir do molde.

### Analogia do Mundo Real
- **Classe**: Planta de uma casa (o projeto)
- **Objeto**: A casa construída (a instância real)

## 🔍 Exemplo: Classe Cachorro

### [Cachorro.php](Cachorro.php) - Definindo a Classe
```php
<?php
class Cachorro {
    // Propriedades (características)
    public string $nome;
    public string $raca;
    public string $corPelo;

    // Método (comportamento)
    public function Latir() {
        print("Au au!" . PHP_EOL);
    }
}
?>
```

**Explicação:**
- `class Cachorro` - Define a classe
- `public string $nome` - Propriedade pública do tipo string
- `public function Latir()` - Método que define um comportamento
- `PHP_EOL` - Quebra de linha compatível com qualquer sistema operacional

### [Principal.php](Principal.php) - Usando a Classe
```php
<?php
require_once 'Cachorro.php';

// Criando um objeto (instanciando)
$cachorro = new Cachorro();

// Definindo propriedades
$cachorro->nome = "Fex";
$cachorro->raca = "Poddle";
$cachorro->idate = 2; // Note: tem um pequeno erro no nome

// Chamando método
$cachorro->Latir();

// Exibindo informações
echo "Sou um cão da raça " . $cachorro->raca . PHP_EOL;
echo "Meu nome é " . $cachorro->nome . " e tenho " . $cachorro->idate . " de idade." . PHP_EOL;
?>
```

## 🚀 Executando o Exemplo

```bash
cd programming/php/02-poo/04-mundocao/simples/
php Principal.php
```

**Saída esperada:**
```
Au au!
Sou um cão da raça Poddle
Meu nome é Fex e tenho 2 de idade.
```

## 💡 Anatomia de uma Classe

```php
class MinhaClasse {
    // 1. PROPRIEDADES (dados/características)
    public $propriedadePublica;      // Acessível de qualquer lugar
    private $propriedadePrivada;     // Apenas dentro da classe
    protected $propriedadeProtegida; // Na classe e filhas
    
    // 2. MÉTODOS (ações/comportamentos)
    public function meuMetodo() {
        // Código do método
        return "Valor de retorno";
    }
    
    // 3. CONSTRUTOR (opcional)
    public function __construct() {
        // Executado automaticamente ao criar objeto
    }
}
```

## 🎯 Exemplo Passo a Passo

### Passo 1: Criar a Classe
```php
class Pessoa {
    public string $nome;
    public int $idade;
    
    public function falar() {
        echo "Olá, meu nome é " . $this->nome . PHP_EOL;
    }
    
    public function aniversario() {
        $this->idade++;
        echo "Agora tenho " . $this->idade . " anos!" . PHP_EOL;
    }
}
```

### Passo 2: Usar a Classe
```php
// Instanciar (criar objeto)
$pessoa1 = new Pessoa();
$pessoa2 = new Pessoa();

// Configurar propriedades
$pessoa1->nome = "João";
$pessoa1->idade = 25;

$pessoa2->nome = "Maria";
$pessoa2->idade = 30;

// Chamar métodos
$pessoa1->falar();        // Olá, meu nome é João
$pessoa1->aniversario();  // Agora tenho 26 anos!

$pessoa2->falar();        // Olá, meu nome é Maria
```

## 🔧 Palavras-chave Importantes

### `$this`
Refere-se ao objeto atual dentro da classe:
```php
class Exemplo {
    public $valor = 10;
    
    public function mostrarValor() {
        echo $this->valor; // Acessa a propriedade do objeto atual
    }
}
```

### `new`
Cria uma nova instância da classe:
```php
$objeto = new MinhaClasse(); // Cria novo objeto
```

### `->`
Operador para acessar propriedades e métodos:
```php
$objeto->propriedade = "valor";    // Define propriedade
$resultado = $objeto->metodo();    // Chama método
```

## 🎯 Exercícios Práticos

### Exercício 1: Classe Carro
Crie uma classe `Carro` com:
- Propriedades: marca, modelo, cor, velocidade
- Métodos: acelerar(), frear(), buzinar()

```php
class Carro {
    public string $marca;
    public string $modelo;
    public string $cor;
    public int $velocidade = 0;
    
    public function acelerar() {
        $this->velocidade += 10;
        echo "Acelerando! Velocidade: " . $this->velocidade . " km/h" . PHP_EOL;
    }
    
    public function frear() {
        if ($this->velocidade > 0) {
            $this->velocidade -= 10;
            echo "Freando! Velocidade: " . $this->velocidade . " km/h" . PHP_EOL;
        }
    }
    
    public function buzinar() {
        echo "BEEP BEEP!" . PHP_EOL;
    }
}

// Teste
$meuCarro = new Carro();
$meuCarro->marca = "Toyota";
$meuCarro->modelo = "Corolla";
$meuCarro->acelerar();
$meuCarro->buzinar();
```

### Exercício 2: Classe ContaBancaria
```php
class ContaBancaria {
    public string $titular;
    public float $saldo = 0.0;
    
    public function depositar(float $valor) {
        $this->saldo += $valor;
        echo "Depósito de R$ " . $valor . " realizado. Saldo: R$ " . $this->saldo . PHP_EOL;
    }
    
    public function sacar(float $valor) {
        if ($valor <= $this->saldo) {
            $this->saldo -= $valor;
            echo "Saque de R$ " . $valor . " realizado. Saldo: R$ " . $this->saldo . PHP_EOL;
        } else {
            echo "Saldo insuficiente!" . PHP_EOL;
        }
    }
    
    public function consultarSaldo() {
        echo "Saldo atual: R$ " . $this->saldo . PHP_EOL;
    }
}
```

### Exercício 3: Classe Produto
```php
class Produto {
    public string $nome;
    public float $preco;
    public int $quantidade = 0;
    
    public function adicionarEstoque(int $qtd) {
        // Implementar
    }
    
    public function vender(int $qtd) {
        // Implementar com verificação de estoque
    }
    
    public function calcularValorTotal() {
        // Retornar preço * quantidade
    }
}
```

## 🐛 Erros Comuns

### 1. **Esquecer o `$this`**
```php
class Exemplo {
    public $nome = "João";
    
    public function mostrarNome() {
        echo $nome;        // ❌ Erro: variável não definida
        echo $this->nome;  // ✅ Correto
    }
}
```

### 2. **Usar `::` em vez de `->`**
```php
$objeto = new MinhaClasse();
$objeto::metodo();    // ❌ Erro (para métodos estáticos)
$objeto->metodo();    // ✅ Correto
```

### 3. **Não incluir arquivo da classe**
```php
$objeto = new MinhaClasse(); // ❌ Erro: classe não encontrada
// Solução:
require_once 'MinhaClasse.php';
$objeto = new MinhaClasse(); // ✅ Correto
```

## 📚 Próximos Passos

Após dominar classes básicas, avance para:

1. **[Construtores](../construtor/)** - Inicialização automática
2. **[Encapsulamento](../encapsulamento/)** - Controle de acesso
3. **[Herança](../heranca/)** - Reutilização de código
4. **[Polimorfismo](../polimorfismo/)** - Comportamentos dinâmicos

## 💡 Dicas Importantes

1. **Nomenclatura**: Use PascalCase para classes (`MinhaClasse`) e camelCase para métodos/propriedades (`meuMetodo`)
2. **Um arquivo por classe**: Mantenha cada classe em seu próprio arquivo
3. **Use `require_once`**: Para incluir arquivos de classe
4. **Documente**: Adicione comentários explicando o propósito da classe

## ❓ Perguntas Frequentes

**Q: Posso criar vários objetos da mesma classe?**
A: Sim! Cada objeto é independente, como ter várias casas construídas da mesma planta.

**Q: Propriedades precisam ser inicializadas?**
A: Não obrigatoriamente, mas é boa prática. PHP atribui valores padrão (null, 0, "", etc.).

**Q: Quantos métodos posso ter em uma classe?**
A: Quantos precisar, mas mantenha classes focadas em uma responsabilidade.

---

💡 **Dica**: POO não é apenas sobre sintaxe, é sobre organizar código de forma que faça sentido no mundo real!