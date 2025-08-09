# Construtores em PHP

Este exemplo demonstra o uso de **construtores** em PHP, um método especial que é executado automaticamente quando um objeto é criado.

## 🎯 O que você vai aprender

- Como criar e usar construtores
- Inicialização automática de propriedades
- Diferentes formas de usar construtores
- Boas práticas na definição de construtores

## 📝 Conceito: Construtor

O **construtor** é um método especial chamado `__construct()` que é executado automaticamente quando você cria um novo objeto da classe.

### Vantagens dos Construtores:
- ✅ Inicialização automática de propriedades
- ✅ Garantia de que o objeto será criado em estado válido
- ✅ Redução de código repetitivo
- ✅ Validação de dados na criação

## 🔍 Arquivos do Exemplo

### [Cachorro.php](Cachorro.php) - Classe Base
```php
class Cachorro {
    private string $nome;
    private string $raca;
    private string $corPelo = "Amarelo"; // Valor padrão
    private float $peso;

    // Construtor - executado automaticamente
    public function __construct(string $nome, string $raca) {
        $this->nome = $nome;
        $this->raca = $raca;
    }
    
    // Métodos getters e setters...
}
```

### [Poddle.php](Poddle.php) - Herança com Construtor
Classe que herda de `Cachorro` e redefine o construtor.

### [Pinscher.php](Pinscher.php) - Especialização
Outra classe filha com comportamentos específicos.

### [Principal.php](Principal.php) - Execução
Demonstra como usar as classes com construtores.

## 🚀 Executando o Exemplo

```bash
cd programming/php/02-poo/04-mundocao/construtor/
php Principal.php
```

**Saída esperada:**
```
Hau! grgrgrgrgrgr Hau!
Au au au au au ! Au!
Au au!
```

## 💡 Comparação: Com e Sem Construtor

### ❌ Sem Construtor (mais código)
```php
$cachorro = new Cachorro();
$cachorro->setNome("Rex");
$cachorro->setRaca("Poddle");
// Esqueceu de definir nome/raça? Objeto em estado inválido!
```

### ✅ Com Construtor (mais seguro)
```php
$cachorro = new Cachorro("Rex", "Poddle");
// Objeto sempre criado com dados essenciais!
```

## 🎯 Exercícios Práticos

### Exercício 1: Pessoa
Crie uma classe `Pessoa` com construtor que receba nome e idade:
```php
class Pessoa {
    private string $nome;
    private int $idade;
    
    public function __construct(string $nome, int $idade) {
        // Implementar validação
        if ($idade < 0) {
            throw new InvalidArgumentException("Idade não pode ser negativa");
        }
        $this->nome = $nome;
        $this->idade = $idade;
    }
}
```

### Exercício 2: Conta Bancária
Implemente uma classe `ContaBancaria` com:
- Construtor que recebe titular e saldo inicial
- Validação para saldo não negativo
- Métodos para depositar e sacar

### Exercício 3: Produto
Crie uma classe `Produto` com:
- Construtor obrigatório: nome, preço
- Construtor opcional: descrição (valor padrão)
- Validação de preço positivo

## 🔧 Construtor com Parâmetros Opcionais

```php
class Produto {
    private string $nome;
    private float $preco;
    private string $descricao;
    
    public function __construct(
        string $nome, 
        float $preco, 
        string $descricao = "Sem descrição"
    ) {
        $this->nome = $nome;
        $this->preco = $preco;
        $this->descricao = $descricao;
    }
}

// Uso:
$produto1 = new Produto("Notebook", 2500.00);
$produto2 = new Produto("Mouse", 25.00, "Mouse óptico sem fio");
```

## 🛡️ Validação no Construtor

```php
public function __construct(string $nome, float $peso) {
    if (empty($nome)) {
        throw new InvalidArgumentException("Nome não pode ser vazio");
    }
    
    if ($peso <= 0) {
        throw new InvalidArgumentException("Peso deve ser positivo");
    }
    
    $this->nome = $nome;
    $this->peso = $peso;
}
```

## 🔄 Destrutor

Além do construtor, PHP também tem **destrutor** (`__destruct()`):

```php
class MinhaClasse {
    public function __construct() {
        echo "Objeto criado!" . PHP_EOL;
    }
    
    public function __destruct() {
        echo "Objeto destruído!" . PHP_EOL;
        // Cleanup: fechar arquivos, conexões, etc.
    }
}
```

## 📚 Próximos Conceitos

Após dominar construtores, você pode avançar para:

1. **[Encapsulamento](../encapsulamento/)** - Controle de acesso
2. **[Herança](../heranca/)** - Reutilização de código
3. **[Polimorfismo](../polimorfismo/)** - Comportamentos dinâmicos

## ❓ Perguntas Frequentes

**Q: Posso ter múltiplos construtores?**
A: PHP não suporta sobrecarga de construtores nativamente, mas você pode usar parâmetros opcionais ou métodos estáticos factory.

**Q: O construtor é obrigatório?**
A: Não, se você não definir um construtor, PHP cria um vazio automaticamente.

**Q: Posso chamar o construtor manualmente?**
A: Não é recomendado. O construtor deve ser chamado apenas na criação do objeto.

---

💡 **Dica**: Construtores são fundamentais para criar objetos em estado válido desde o início. Use-os sempre que possível!