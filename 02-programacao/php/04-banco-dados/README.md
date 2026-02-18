# PHP com Banco de Dados

Esta seção aborda a integração do PHP com bancos de dados, focando principalmente em MySQL/MariaDB, incluindo conexões, operações CRUD e boas práticas de segurança.

## 📋 Pré-requisitos

- Conhecimento dos [conceitos básicos do PHP](../01-conceitos-php/)
- Familiaridade com [POO em PHP](../02-poo/)
- Conhecimentos básicos de SQL
- Banco de dados MySQL/MariaDB instalado (XAMPP/MAMP/Docker)

## 🎯 Objetivos de Aprendizado

Ao final desta seção, você será capaz de:

- Conectar PHP com bancos de dados MySQL
- Executar operações CRUD (Create, Read, Update, Delete)
- Implementar prepared statements para segurança
- Criar classes para acesso a dados (DAO/Repository)
- Tratar exceções e erros de banco de dados
- Aplicar boas práticas de segurança

## 🗄️ Configurando o Ambiente

### Opção 1: XAMPP/MAMP (Recomendado para iniciantes)

1. **Inicie os serviços**:
   - Apache (servidor web)
   - MySQL (banco de dados)

2. **Acesse phpMyAdmin**:
   - URL: `http://localhost/phpmyadmin`
   - Crie um banco de dados para seus testes

### Opção 2: MySQL Standalone

```bash
# Instalar MySQL (Ubuntu/Debian)
sudo apt update
sudo apt install mysql-server php-mysql

# Iniciar serviço
sudo systemctl start mysql

# Configurar senha root
sudo mysql_secure_installation
```

### Opção 3: Docker

```bash
# MySQL com Docker
docker run --name mysql-dev -e MYSQL_ROOT_PASSWORD=root123 -p 3306:3306 -d mysql:8.0

# Conectar ao container
docker exec -it mysql-dev mysql -u root -p
```

## 📚 Estrutura do Conteúdo

### [12 - Fundamentos](12/)

#### [Conexão](12/conexao/)
- Configuração da conexão com MySQL
- Diferentes métodos de conexão (MySQLi, PDO)
- Tratamento de erros de conexão

#### [Feito em Aula](12/feito%20em%20aula/)
- Exemplos práticos desenvolvidos em aula
- Operações básicas com banco de dados
- Integração com formulários web

### [13 - Operações Avançadas](13/)
- Transações
- Procedures e Functions
- Triggers
- Índices e otimização

## 🔗 Métodos de Conexão

### 1. **PDO (Recomendado)**

```php
<?php
class Database {
    private $host = 'localhost';
    private $dbname = 'meu_banco';
    private $username = 'root';
    private $password = '';
    private $pdo;
    
    public function __construct() {
        try {
            $dsn = "mysql:host={$this->host};dbname={$this->dbname};charset=utf8mb4";
            $this->pdo = new PDO($dsn, $this->username, $this->password, [
                PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
                PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
                PDO::ATTR_EMULATE_PREPARES => false,
            ]);
        } catch (PDOException $e) {
            die("Erro na conexão: " . $e->getMessage());
        }
    }
    
    public function getConnection() {
        return $this->pdo;
    }
}
?>
```

### 2. **MySQLi Orientado a Objetos**

```php
<?php
class DatabaseMySQLi {
    private $host = 'localhost';
    private $username = 'root';
    private $password = '';
    private $database = 'meu_banco';
    private $mysqli;
    
    public function __construct() {
        $this->mysqli = new mysqli($this->host, $this->username, $this->password, $this->database);
        
        if ($this->mysqli->connect_error) {
            die("Erro na conexão: " . $this->mysqli->connect_error);
        }
        
        $this->mysqli->set_charset("utf8mb4");
    }
    
    public function getConnection() {
        return $this->mysqli;
    }
}
?>
```

## 🛠️ Operações CRUD

### 1. **Create (Inserir)**

```php
<?php
class UsuarioDAO {
    private $pdo;
    
    public function __construct($pdo) {
        $this->pdo = $pdo;
    }
    
    public function inserir($nome, $email, $senha) {
        try {
            $sql = "INSERT INTO usuarios (nome, email, senha, data_criacao) VALUES (?, ?, ?, NOW())";
            $stmt = $this->pdo->prepare($sql);
            
            $senha_hash = password_hash($senha, PASSWORD_DEFAULT);
            $stmt->execute([$nome, $email, $senha_hash]);
            
            return $this->pdo->lastInsertId();
        } catch (PDOException $e) {
            throw new Exception("Erro ao inserir usuário: " . $e->getMessage());
        }
    }
}
?>
```

### 2. **Read (Consultar)**

```php
<?php
public function buscarPorId($id) {
    try {
        $sql = "SELECT id, nome, email, data_criacao FROM usuarios WHERE id = ?";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([$id]);
        
        return $stmt->fetch();
    } catch (PDOException $e) {
        throw new Exception("Erro ao buscar usuário: " . $e->getMessage());
    }
}

public function listarTodos() {
    try {
        $sql = "SELECT id, nome, email, data_criacao FROM usuarios ORDER BY nome";
        $stmt = $this->pdo->query($sql);
        
        return $stmt->fetchAll();
    } catch (PDOException $e) {
        throw new Exception("Erro ao listar usuários: " . $e->getMessage());
    }
}
?>
```

### 3. **Update (Atualizar)**

```php
<?php
public function atualizar($id, $nome, $email) {
    try {
        $sql = "UPDATE usuarios SET nome = ?, email = ?, data_atualizacao = NOW() WHERE id = ?";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([$nome, $email, $id]);
        
        return $stmt->rowCount();
    } catch (PDOException $e) {
        throw new Exception("Erro ao atualizar usuário: " . $e->getMessage());
    }
}
?>
```

### 4. **Delete (Excluir)**

```php
<?php
public function excluir($id) {
    try {
        $sql = "DELETE FROM usuarios WHERE id = ?";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([$id]);
        
        return $stmt->rowCount();
    } catch (PDOException $e) {
        throw new Exception("Erro ao excluir usuário: " . $e->getMessage());
    }
}
?>
```

## 🛡️ Segurança

### 1. **Prepared Statements (Obrigatório)**

```php
// ❌ NUNCA faça isso (vulnerável a SQL Injection)
$sql = "SELECT * FROM usuarios WHERE email = '$email'";

// ✅ Use sempre prepared statements
$sql = "SELECT * FROM usuarios WHERE email = ?";
$stmt = $pdo->prepare($sql);
$stmt->execute([$email]);
```

### 2. **Validação de Dados**

```php
<?php
public function validarEmail($email) {
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        throw new InvalidArgumentException("Email inválido");
    }
    return true;
}

public function validarSenha($senha) {
    if (strlen($senha) < 8) {
        throw new InvalidArgumentException("Senha deve ter pelo menos 8 caracteres");
    }
    return true;
}
?>
```

### 3. **Hash de Senhas**

```php
<?php
// Gerar hash
$senha_hash = password_hash($senha, PASSWORD_DEFAULT);

// Verificar senha
if (password_verify($senha_digitada, $senha_hash_do_banco)) {
    echo "Senha correta!";
} else {
    echo "Senha incorreta!";
}
?>
```

## 🏗️ Padrão DAO (Data Access Object)

```php
<?php
// Usuario.php - Modelo
class Usuario {
    private $id;
    private $nome;
    private $email;
    private $dataCriacao;
    
    // Getters e Setters
    public function getId() { return $this->id; }
    public function setId($id) { $this->id = $id; }
    
    public function getNome() { return $this->nome; }
    public function setNome($nome) { $this->nome = $nome; }
    
    public function getEmail() { return $this->email; }
    public function setEmail($email) { $this->email = $email; }
    
    public function getDataCriacao() { return $this->dataCriacao; }
    public function setDataCriacao($data) { $this->dataCriacao = $data; }
}

// UsuarioDAO.php - Acesso a dados
class UsuarioDAO {
    private $pdo;
    
    public function __construct($pdo) {
        $this->pdo = $pdo;
    }
    
    public function salvar(Usuario $usuario) {
        if ($usuario->getId()) {
            return $this->atualizar($usuario);
        } else {
            return $this->inserir($usuario);
        }
    }
    
    private function inserir(Usuario $usuario) {
        $sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([$usuario->getNome(), $usuario->getEmail()]);
        
        $usuario->setId($this->pdo->lastInsertId());
        return $usuario;
    }
    
    public function buscarPorId($id) {
        $sql = "SELECT * FROM usuarios WHERE id = ?";
        $stmt = $this->pdo->prepare($sql);
        $stmt->execute([$id]);
        
        $dados = $stmt->fetch();
        if ($dados) {
            $usuario = new Usuario();
            $usuario->setId($dados['id']);
            $usuario->setNome($dados['nome']);
            $usuario->setEmail($dados['email']);
            $usuario->setDataCriacao($dados['data_criacao']);
            return $usuario;
        }
        
        return null;
    }
}
?>
```

## 🔄 Transações

```php
<?php
class TransacaoExample {
    private $pdo;
    
    public function transferirDinheiro($contaOrigem, $contaDestino, $valor) {
        try {
            $this->pdo->beginTransaction();
            
            // Debitar da conta origem
            $sql = "UPDATE contas SET saldo = saldo - ? WHERE id = ? AND saldo >= ?";
            $stmt = $this->pdo->prepare($sql);
            $stmt->execute([$valor, $contaOrigem, $valor]);
            
            if ($stmt->rowCount() === 0) {
                throw new Exception("Saldo insuficiente");
            }
            
            // Creditar na conta destino
            $sql = "UPDATE contas SET saldo = saldo + ? WHERE id = ?";
            $stmt = $this->pdo->prepare($sql);
            $stmt->execute([$valor, $contaDestino]);
            
            // Registrar histórico
            $sql = "INSERT INTO historico (conta_origem, conta_destino, valor, data_operacao) VALUES (?, ?, ?, NOW())";
            $stmt = $this->pdo->prepare($sql);
            $stmt->execute([$contaOrigem, $contaDestino, $valor]);
            
            $this->pdo->commit();
            return true;
            
        } catch (Exception $e) {
            $this->pdo->rollback();
            throw $e;
        }
    }
}
?>
```

## 📊 Exemplo Prático: Sistema de Blog

### 1. **Estrutura do Banco**

```sql
-- Criar banco de dados
CREATE DATABASE blog_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE blog_db;

-- Tabela de usuários
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de posts
CREATE TABLE posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    conteudo TEXT NOT NULL,
    usuario_id INT NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabela de comentários
CREATE TABLE comentarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT NOT NULL,
    usuario_id INT NOT NULL,
    comentario TEXT NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);
```

### 2. **Classe de Conexão**

```php
<?php
// config/database.php
class Database {
    private static $instance = null;
    private $pdo;
    
    private function __construct() {
        $config = [
            'host' => 'localhost',
            'dbname' => 'blog_db',
            'username' => 'root',
            'password' => ''
        ];
        
        try {
            $dsn = "mysql:host={$config['host']};dbname={$config['dbname']};charset=utf8mb4";
            $this->pdo = new PDO($dsn, $config['username'], $config['password'], [
                PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
                PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
                PDO::ATTR_EMULATE_PREPARES => false,
            ]);
        } catch (PDOException $e) {
            die("Erro na conexão: " . $e->getMessage());
        }
    }
    
    public static function getInstance() {
        if (self::$instance === null) {
            self::$instance = new self();
        }
        return self::$instance;
    }
    
    public function getConnection() {
        return $this->pdo;
    }
}
?>
```

## 🔧 Ferramentas de Desenvolvimento

### 1. **Depuração de Consultas**

```php
<?php
// Mostrar consulta SQL preparada
$stmt = $pdo->prepare("SELECT * FROM usuarios WHERE id = ?");
$stmt->debugDumpParams(); // Mostra a consulta

// Log de consultas
$pdo->setAttribute(PDO::ATTR_STATEMENT_CLASS, array('LoggedPDOStatement'));
?>
```

### 2. **phpMyAdmin**
- Interface web para gerenciar MySQL
- URL: `http://localhost/phpmyadmin`
- Ideal para visualizar dados e testar consultas

### 3. **Adminer**
- Alternativa mais leve ao phpMyAdmin
- Download: https://www.adminer.org/

## 🐛 Problemas Comuns

### 1. **Erro de conexão**
```
SQLSTATE[HY000] [2002] Connection refused
```
- Verifique se o MySQL está rodando
- Confirme host, porta, usuário e senha

### 2. **Erro de charset**
```
Incorrect string value
```
- Configure charset para utf8mb4
- Use `SET NAMES utf8mb4` após conexão

### 3. **SQL Injection**
```
You have an error in your SQL syntax
```
- SEMPRE use prepared statements
- Nunca concatene dados do usuário no SQL

## 🌟 Boas Práticas

### 1. **Configuração**
- Use variáveis de ambiente para credenciais
- Nunca commite senhas no código
- Configure diferentes ambientes (dev/prod)

### 2. **Performance**
- Use índices nas colunas de busca
- Implemente cache quando necessário
- Evite SELECT * em produção

### 3. **Segurança**
- Sempre use prepared statements
- Valide TODOS os dados de entrada
- Configure privilégios mínimos para o usuário do banco

## 📖 Próximos Passos

1. **[PDF Generation](../05-pdf/)** - Geração de relatórios
2. **ORM (Object-Relational Mapping)** - Eloquent, Doctrine
3. **Cache** - Redis, Memcached
4. **Database Migrations** - Versionamento de schema
5. **NoSQL** - MongoDB, Redis

## 📚 Recursos Adicionais

- [Manual PHP - PDO](https://www.php.net/manual/pt_BR/book.pdo.php)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [OWASP SQL Injection Prevention](https://cheatsheetseries.owasp.org/cheatsheets/SQL_Injection_Prevention_Cheat_Sheet.html)

---

💡 **Dica**: Segurança em banco de dados é fundamental. Sempre use prepared statements e nunca confie em dados vindos do usuário!