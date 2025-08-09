# Programação Orientada a Objetos em PHP

Esta seção aborda os conceitos fundamentais da Programação Orientada a Objetos (POO) em PHP, desde conceitos básicos até tópicos avançados.

## 📋 Pré-requisitos

- Conhecimento dos [conceitos básicos do PHP](../01-conceitos-php/)
- PHP instalado (veja o [Guia de Instalação](../INSTALACAO.md))
- Familiaridade com variáveis, funções e estruturas de controle

## 🎯 Objetivos de Aprendizado

Ao final desta seção, você será capaz de:

- Entender os pilares da POO: Encapsulamento, Herança, Polimorfismo e Abstração
- Criar e utilizar classes e objetos
- Implementar construtores e destrutores
- Aplicar encapsulamento com modificadores de acesso
- Implementar herança e polimorfismo
- Trabalhar com métodos e propriedades estáticas
- Integrar POO com banco de dados

## 🏗️ Pilares da POO

### 1. **Encapsulamento**
Controla o acesso aos dados através de modificadores de visibilidade:
- `public`: Acessível de qualquer lugar
- `private`: Acessível apenas dentro da própria classe
- `protected`: Acessível na classe e em suas filhas

### 2. **Herança**
Permite que uma classe filha herde características da classe pai usando `extends`.

### 3. **Polimorfismo**
Capacidade de objetos de diferentes classes responderem de forma diferente ao mesmo método.

### 4. **Abstração**
Oculta detalhes de implementação, mostrando apenas funcionalidades essenciais.

## 📚 Estrutura do Conteúdo

### [03 - Conceitos Fundamentais](03/)
- Introdução às classes e objetos básicos

### [04 - MundoCão - Exemplos Práticos](04-mundocao/)

Um sistema completo de exemplos usando o tema "Mundo dos Cães" para demonstrar todos os conceitos de POO:

#### [Exemplos Simples](04-mundocao/simples/)
- **[Cachorro.php](04-mundocao/simples/Cachorro.php)**: Classe básica com propriedades e métodos
- **[Principal.php](04-mundocao/simples/Principal.php)**: Como instanciar e usar objetos

**Executar exemplo:**
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

#### [Construtores](04-mundocao/construtor/)
Aprenda a inicializar objetos automaticamente:
- Métodos `__construct()` e `__destruct()`
- Inicialização de propriedades na criação do objeto
- Validação de dados no construtor

#### [Encapsulamento](04-mundocao/encapsulamento/)
Controle de acesso aos dados:
- Propriedades privadas e protegidas
- Métodos getters e setters
- Validação de dados
- Proteção da integridade dos objetos

#### [Herança](04-mundocao/heranca/)
Reutilização e extensão de código:
- Classe pai (Animal) e classe filha (Cachorro)
- Sobrescrita de métodos com `parent::`
- Modificador `protected` vs `private`
- Hierarquia de classes

#### [Polimorfismo](04-mundocao/polimorfismo/)
Comportamentos diferentes para o mesmo método:
- Interface comum para diferentes implementações
- Método abstrato e classes filhas
- Uso de `instanceof` para verificação de tipo

#### [Banco de Dados](04-mundocao/banco/)
Integração de POO com banco de dados:
- Classe de conexão com banco
- Operações CRUD (Create, Read, Update, Delete)
- Padrão DAO (Data Access Object)
- Tratamento de exceções

#### [Feitos em Aula](04-mundocao/feitos%20em%20aula/)
Exemplos desenvolvidos durante as aulas com explicações passo a passo.

### [05 - Tópicos Avançados](05/)
- Interfaces e classes abstratas
- Traits e namespaces
- Padrões de design (Singleton, Factory, Observer)
- Autoloading de classes

## 🚀 Como Estudar Esta Seção

### 1. **Ordem Recomendada**

```
1. Simples          → Classes básicas e objetos
2. Construtor       → Inicialização automática  
3. Encapsulamento   → Controle de acesso
4. Herança          → Reutilização de código
5. Polimorfismo     → Comportamentos dinâmicos
6. Banco de Dados   → Aplicação prática
```

### 2. **Metodologia de Estudo**

Para cada conceito:
1. **Leia** a teoria e exemplos
2. **Execute** o código fornecido
3. **Modifique** os exemplos
4. **Crie** seus próprios exemplos
5. **Pratique** com exercícios

### 3. **Comandos para Execução**

```bash
# Navegue até a pasta do exemplo
cd programming/php/02-poo/04-mundocao/[pasta-do-conceito]/

# Execute o arquivo principal
php Principal.php
# ou
php index.php
# ou
php nome-do-arquivo.php
```

## 💡 Conceitos Essenciais

### Classe vs Objeto

```php
// Classe - molde/template
class Cachorro {
    public $nome;
    public function latir() {
        return "Au au!";
    }
}

// Objeto - instância da classe
$meuCao = new Cachorro();
$meuCao->nome = "Rex";
echo $meuCao->latir(); // Au au!
```

### Modificadores de Acesso

```php
class MinhaClasse {
    public $publico;      // Acessível de qualquer lugar
    private $privado;     // Apenas dentro desta classe
    protected $protegido; // Nesta classe e filhas
}
```

### Herança

```php
class Animal {
    protected $nome;
    public function comer() { /* ... */ }
}

class Cachorro extends Animal {
    public function latir() { 
        return $this->nome . " faz: Au au!"; 
    }
}
```

## 🎯 Exercícios Práticos

### Exercício 1: Classe Básica
Crie uma classe `Pessoa` com propriedades nome, idade e métodos para apresentar-se.

### Exercício 2: Encapsulamento
Implemente uma classe `ContaBancaria` com saldo privado e métodos para depositar/sacar.

### Exercício 3: Herança
Crie uma hierarquia: `Veiculo` → `Carro` → `CarroEsportivo`.

### Exercício 4: Polimorfismo
Implemente diferentes tipos de `Funcionario` (Gerente, Vendedor) com cálculos de salário distintos.

## 🔧 Ferramentas Úteis

### Depuração
```php
// Ver estrutura do objeto
var_dump($objeto);

// Verificar tipo
if ($objeto instanceof MinhaClasse) {
    // ...
}

// Listar métodos da classe
print_r(get_class_methods('MinhaClasse'));
```

### Autoloading (PHP moderno)
```php
// Carregar classes automaticamente
spl_autoload_register(function ($classe) {
    include 'classes/' . $classe . '.php';
});
```

## 🐛 Erros Comuns

### 1. **Propriedade não acessível**
```
Fatal error: Cannot access private property...
```
- Use getters/setters para propriedades privadas
- Verifique os modificadores de acesso

### 2. **Método não encontrado**
```
Fatal error: Call to undefined method...
```
- Verifique o nome do método
- Certifique-se que o método existe na classe

### 3. **Classe não encontrada**
```
Fatal error: Class 'MinhaClasse' not found
```
- Verifique se incluiu o arquivo da classe
- Use `require_once` ou `include_once`

## 🌟 Boas Práticas

### 1. **Nomenclatura**
- Classes: PascalCase (`MinhaClasse`)
- Métodos/Propriedades: camelCase (`meuMetodo`)
- Constantes: UPPER_CASE (`MINHA_CONSTANTE`)

### 2. **Organização**
- Uma classe por arquivo
- Nome do arquivo = nome da classe
- Use namespaces para organizar

### 3. **Segurança**
- Propriedades privadas/protegidas
- Validação nos setters
- Tratamento de exceções

## 📖 Próximos Passos

Após dominar POO, você pode avançar para:

1. **[PHP para Web](../03-web/)** - Aplicar POO em aplicações web
2. **[Banco de Dados](../04-banco-dados/)** - Integração avançada com BD
3. **Frameworks PHP** - Laravel, Symfony, CodeIgniter
4. **Padrões de Design** - MVC, Repository, Service Layer

## 📚 Recursos Adicionais

- [Manual PHP - Classes e Objetos](https://www.php.net/manual/pt_BR/language.oop5.php)
- [PHP-FIG PSR Standards](https://www.php-fig.org/psr/)
- [Design Patterns em PHP](https://refactoring.guru/design-patterns/php)
- [Clean Code PHP](https://github.com/jupeter/clean-code-php)

## 🎓 Certificação de Conhecimento

Você dominou POO em PHP quando conseguir:

- [ ] Criar classes com propriedades e métodos
- [ ] Implementar construtores e destrutores
- [ ] Aplicar encapsulamento corretamente
- [ ] Usar herança para reutilizar código
- [ ] Implementar polimorfismo
- [ ] Integrar objetos com banco de dados
- [ ] Aplicar boas práticas de POO

---

💡 **Dica Final**: POO é um paradigma poderoso que organiza o código de forma mais intuitiva e reutilizável. Pratique criando seus próprios projetos aplicando estes conceitos!