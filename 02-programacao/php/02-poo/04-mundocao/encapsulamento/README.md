# Encapsulamento em PHP

Este exemplo demonstra o **encapsulamento**, um dos pilares fundamentais da Programação Orientada a Objetos que controla o acesso aos dados e métodos de uma classe.

## 🎯 O que você vai aprender

- Modificadores de acesso (public, private, protected)
- Métodos getters e setters
- Proteção de dados e validação
- Boas práticas de encapsulamento

## 📝 Conceito: Encapsulamento

**Encapsulamento** é o princípio que permite ocultar os detalhes internos de uma classe e controlar como seus dados são acessados e modificados.

### Vantagens do Encapsulamento:
- 🔒 **Proteção de dados** - Evita modificações indevidas
- ✅ **Validação** - Controla que dados são aceitos
- 🛡️ **Integridade** - Mantém o objeto em estado consistente
- 🔧 **Manutenção** - Facilita mudanças internas sem afetar código externo

## 🔑 Modificadores de Acesso

### `public` - Público
- Acessível de **qualquer lugar**
- Tanto dentro quanto fora da classe

### `private` - Privado  
- Acessível **apenas dentro da própria classe**
- Não é herdado por classes filhas

### `protected` - Protegido
- Acessível **na classe e em suas filhas**
- Não acessível externamente

## 🔍 Exemplo Prático

```php
class Cachorro {
    // ❌ Evite propriedades públicas
    public $nome;
    
    // ✅ Use propriedades privadas
    private string $raca;
    private float $peso;
    private int $idade;
    
    // ✅ Controle o acesso com métodos
    public function setRaca(string $raca): void {
        if (empty($raca)) {
            throw new InvalidArgumentException("Raça não pode ser vazia");
        }
        $this->raca = $raca;
    }
    
    public function getRaca(): string {
        return $this->raca;
    }
    
    public function setPeso(float $peso): void {
        if ($peso <= 0) {
            throw new InvalidArgumentException("Peso deve ser positivo");
        }
        $this->peso = $peso;
    }
    
    public function getPeso(): float {
        return $this->peso;
    }
}
```

## 🚀 Executando o Exemplo

```bash
cd programming/php/02-poo/04-mundocao/encapsulamento/
php Principal.php
```

## 💡 Comparação: Com e Sem Encapsulamento

### ❌ Sem Encapsulamento
```php
class Cachorro {
    public $peso;
}

$dog = new Cachorro();
$dog->peso = -50; // ❌ Peso negativo!? Sem validação!
$dog->peso = "abc"; // ❌ Tipo errado!
```

### ✅ Com Encapsulamento
```php
class Cachorro {
    private float $peso;
    
    public function setPeso(float $peso): void {
        if ($peso <= 0) {
            throw new InvalidArgumentException("Peso deve ser positivo");
        }
        $this->peso = $peso;
    }
}

$dog = new Cachorro();
$dog->setPeso(-50); // ✅ Erro capturado e tratado!
```

## 🎯 Padrões de Getters e Setters

### Setter com Validação
```php
public function setIdade(int $idade): void {
    if ($idade < 0 || $idade > 30) {
        throw new InvalidArgumentException("Idade deve estar entre 0 e 30 anos");
    }
    $this->idade = $idade;
}
```

### Getter com Formatação
```php
public function getIdadeFormatada(): string {
    return $this->idade . " anos";
}
```

### Propriedade Calculada
```php
public function getIdadeEmMeses(): int {
    return $this->idade * 12;
}
```

### Propriedade Somente Leitura
```php
private DateTime $dataNascimento;

public function getDataNascimento(): DateTime {
    return $this->dataNascimento; // Apenas getter, sem setter
}
```

## 🔧 Técnicas Avançadas

### 1. **Lazy Loading**
```php
private ?array $historico = null;

public function getHistorico(): array {
    if ($this->historico === null) {
        $this->historico = $this->carregarHistoricoDoBanco();
    }
    return $this->historico;
}
```

### 2. **Fluent Interface**
```php
public function setNome(string $nome): self {
    $this->nome = $nome;
    return $this; // Permite encadeamento
}

// Uso:
$dog->setNome("Rex")->setRaca("Poddle")->setPeso(25.5);
```

### 3. **Propriedades Imutáveis**
```php
class Cachorro {
    private readonly string $chip; // PHP 8.1+
    
    public function __construct(string $chip) {
        $this->chip = $chip; // Só pode ser definido uma vez
    }
    
    public function getChip(): string {
        return $this->chip;
    }
    // Sem setter - propriedade imutável
}
```

## 🎯 Exercícios Práticos

### Exercício 1: Conta Bancária
```php
class ContaBancaria {
    private float $saldo;
    private string $titular;
    
    // Implementar:
    // - setSaldo() com validação
    // - depositar($valor) 
    // - sacar($valor) com verificação de saldo
    // - getSaldo() apenas para o titular
}
```

### Exercício 2: Produto
```php
class Produto {
    private string $nome;
    private float $preco;
    private int $estoque;
    
    // Implementar:
    // - setPreco() sem valores negativos
    // - adicionarEstoque($quantidade)
    // - removerEstoque($quantidade) com verificação
    // - estaDisponivel(): bool
}
```

### Exercício 3: Usuario
```php
class Usuario {
    private string $email;
    private string $senha;
    private DateTime $ultimoLogin;
    
    // Implementar:
    // - setEmail() com validação de formato
    // - setSenha() com hash automático
    // - login($senha) com verificação
    // - Propriedade ultimoLogin somente leitura
}
```

## 🛡️ Validações Comuns

### Email
```php
public function setEmail(string $email): void {
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        throw new InvalidArgumentException("Email inválido");
    }
    $this->email = $email;
}
```

### String não vazia
```php
public function setNome(string $nome): void {
    $nome = trim($nome);
    if (empty($nome)) {
        throw new InvalidArgumentException("Nome não pode ser vazio");
    }
    $this->nome = $nome;
}
```

### Intervalo numérico
```php
public function setNota(float $nota): void {
    if ($nota < 0 || $nota > 10) {
        throw new InvalidArgumentException("Nota deve estar entre 0 e 10");
    }
    $this->nota = $nota;
}
```

## 📚 Próximos Conceitos

Após dominar encapsulamento:

1. **[Herança](../heranca/)** - Reutilização de código
2. **[Polimorfismo](../polimorfismo/)** - Comportamentos dinâmicos
3. **[Abstração](../abstratas/)** - Classes e métodos abstratos

## ❓ Perguntas Frequentes

**Q: Sempre usar getters/setters para todas as propriedades?**
A: Não sempre. Use apenas quando precisar de validação ou controle de acesso.

**Q: Propriedades protected vs private?**
A: Use `private` quando a propriedade é específica da classe. Use `protected` quando classes filhas podem precisar acessar.

**Q: Como escolher entre public, private, protected?**
A: Comece com `private` e torne `protected` ou `public` apenas quando necessário.

---

💡 **Dica**: Encapsulamento é sobre controle, não complicação. Use-o para proteger a integridade dos seus objetos!