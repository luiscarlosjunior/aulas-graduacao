# PHP para Desenvolvimento Web

Esta seção aborda o uso do PHP para desenvolvimento de aplicações web, incluindo formulários, sessões, cookies e integração com HTML.

## 📋 Pré-requisitos

- Conhecimento dos [conceitos básicos do PHP](../01-conceitos-php/)
- Familiaridade com [POO em PHP](../02-poo/)
- Conhecimentos básicos de HTML e CSS
- Servidor web (Apache/Nginx) ou servidor built-in do PHP

## 🎯 Objetivos de Aprendizado

Ao final desta seção, você será capaz de:

- Integrar PHP com HTML para criar páginas dinâmicas
- Processar formulários web com PHP
- Gerenciar sessões e cookies
- Trabalhar com uploads de arquivos
- Implementar autenticação básica
- Criar aplicações web completas

## 🌐 Configurando o Ambiente Web

### Opção 1: Servidor Built-in do PHP (Recomendado para desenvolvimento)

```bash
# Na pasta do seu projeto
cd programming/php/03-web/
php -S localhost:8000
```

Acesse: `http://localhost:8000`

### Opção 2: XAMPP/MAMP

1. Coloque os arquivos na pasta `htdocs`
2. Inicie Apache no painel de controle
3. Acesse: `http://localhost/nome-do-arquivo.php`

### Opção 3: Servidor Web Configurado

- Apache + PHP
- Nginx + PHP-FPM
- Docker com imagem PHP/Apache

## 📚 Estrutura do Conteúdo

### [06 - Fundamentos Web](06/)

Exemplos básicos de integração PHP + HTML:

#### [HTML Estático](06/html/)
- Páginas HTML básicas
- Estrutura de documentos web
- Formulários HTML

#### [HTML + PHP](06/html+php/)
- Embedding PHP em HTML
- Processamento de dados do servidor
- Geração dinâmica de conteúdo

**Exemplo de execução:**
```bash
cd programming/php/03-web/06/html+php/
php -S localhost:8000
# Acesse: http://localhost:8000/index.php
```

### [07 - Formulários e Processamento](07/)
- Métodos GET e POST
- Validação de dados
- Sanitização de entrada
- Redirecionamentos

### [08 - Sessões e Cookies](08/)
- Gerenciamento de estado
- Login/logout de usuários
- Carrinho de compras
- Preferências do usuário

### [09 - Upload de Arquivos](09/)
- Formulários multipart
- Validação de arquivos
- Armazenamento seguro
- Tipos de arquivo permitidos

### [10 - Autenticação e Segurança](10/)
- Sistema de login
- Hash de senhas
- Proteção contra ataques
- Controle de acesso

### [11 - Aplicações Completas](11/)
- Projetos integrados
- Arquitetura MVC básica
- Organização de código
- Boas práticas

## 🚀 Conceitos Essenciais

### 1. **Embedding PHP em HTML**

```php
<!DOCTYPE html>
<html>
<head>
    <title><?php echo "Página Dinâmica"; ?></title>
</head>
<body>
    <h1><?php echo "Olá, " . $nome; ?>!</h1>
    <p>Hoje é <?php echo date('d/m/Y'); ?></p>
</body>
</html>
```

### 2. **Processamento de Formulários**

```php
// HTML do formulário
<form method="POST" action="processar.php">
    <input type="text" name="nome" required>
    <input type="email" name="email" required>
    <button type="submit">Enviar</button>
</form>

// PHP para processar (processar.php)
<?php
if ($_POST) {
    $nome = filter_var($_POST['nome'], FILTER_SANITIZE_STRING);
    $email = filter_var($_POST['email'], FILTER_SANITIZE_EMAIL);
    
    if (filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo "Dados válidos!";
    } else {
        echo "Email inválido!";
    }
}
?>
```

### 3. **Gerenciamento de Sessões**

```php
<?php
session_start();

// Armazenar dados na sessão
$_SESSION['usuario'] = 'João';
$_SESSION['logado'] = true;

// Verificar se está logado
if (isset($_SESSION['logado']) && $_SESSION['logado']) {
    echo "Bem-vindo, " . $_SESSION['usuario'];
} else {
    header('Location: login.php');
    exit;
}

// Destruir sessão (logout)
session_destroy();
?>
```

### 4. **Cookies**

```php
<?php
// Definir cookie (válido por 30 dias)
setcookie('preferencia', 'tema_escuro', time() + (30 * 24 * 60 * 60));

// Ler cookie
if (isset($_COOKIE['preferencia'])) {
    $tema = $_COOKIE['preferencia'];
    echo "Tema selecionado: " . $tema;
}

// Remover cookie
setcookie('preferencia', '', time() - 3600);
?>
```

## 🛡️ Segurança Web

### 1. **Validação e Sanitização**

```php
// Validar entrada
$email = filter_var($_POST['email'], FILTER_VALIDATE_EMAIL);
$idade = filter_var($_POST['idade'], FILTER_VALIDATE_INT);

// Sanitizar saída
$nome = htmlspecialchars($_POST['nome'], ENT_QUOTES, 'UTF-8');
echo $nome; // Seguro contra XSS
```

### 2. **Proteção CSRF**

```php
// Gerar token CSRF
session_start();
$_SESSION['csrf_token'] = bin2hex(random_bytes(32));

// No formulário
echo '<input type="hidden" name="csrf_token" value="' . $_SESSION['csrf_token'] . '">';

// Verificar token
if ($_POST['csrf_token'] !== $_SESSION['csrf_token']) {
    die('Token CSRF inválido');
}
```

### 3. **Hash de Senhas**

```php
// Criar hash da senha
$senha_hash = password_hash($_POST['senha'], PASSWORD_DEFAULT);

// Verificar senha
if (password_verify($_POST['senha'], $senha_hash_do_banco)) {
    echo "Senha correta!";
}
```

## 🎯 Exemplos Práticos

### Sistema de Login Básico

```php
// login.php
<?php
session_start();

if ($_POST) {
    $usuario = $_POST['usuario'];
    $senha = $_POST['senha'];
    
    // Verificar credenciais (em produção, use banco de dados)
    if ($usuario === 'admin' && $senha === '123456') {
        $_SESSION['logado'] = true;
        $_SESSION['usuario'] = $usuario;
        header('Location: dashboard.php');
        exit;
    } else {
        $erro = "Credenciais inválidas!";
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <?php if (isset($erro)): ?>
        <div style="color: red;"><?php echo $erro; ?></div>
    <?php endif; ?>
    
    <form method="POST">
        <input type="text" name="usuario" placeholder="Usuário" required>
        <input type="password" name="senha" placeholder="Senha" required>
        <button type="submit">Entrar</button>
    </form>
</body>
</html>
```

### Formulário com Upload

```php
// upload.php
<?php
if ($_FILES) {
    $arquivo = $_FILES['arquivo'];
    
    // Verificar se houve erro
    if ($arquivo['error'] === UPLOAD_ERR_OK) {
        $nome_temp = $arquivo['tmp_name'];
        $nome_original = $arquivo['name'];
        $tamanho = $arquivo['size'];
        
        // Validações
        $tipos_permitidos = ['image/jpeg', 'image/png', 'image/gif'];
        $tamanho_max = 2 * 1024 * 1024; // 2MB
        
        if (in_array($arquivo['type'], $tipos_permitidos) && $tamanho <= $tamanho_max) {
            $destino = 'uploads/' . $nome_original;
            
            if (move_uploaded_file($nome_temp, $destino)) {
                echo "Arquivo enviado com sucesso!";
            } else {
                echo "Erro ao mover arquivo.";
            }
        } else {
            echo "Tipo de arquivo não permitido ou muito grande.";
        }
    }
}
?>

<form method="POST" enctype="multipart/form-data">
    <input type="file" name="arquivo" accept="image/*" required>
    <button type="submit">Enviar Arquivo</button>
</form>
```

## 🔧 Ferramentas de Desenvolvimento

### 1. **Depuração**

```php
// Mostrar todos os dados POST
var_dump($_POST);

// Mostrar sessão atual
var_dump($_SESSION);

// Mostrar cookies
var_dump($_COOKIE);

// Log de erros
error_log("Debug: " . print_r($dados, true));
```

### 2. **Headers Úteis**

```php
// Redirecionamento
header('Location: outra_pagina.php');
exit;

// Tipo de conteúdo
header('Content-Type: application/json');

// Cache
header('Cache-Control: no-cache, must-revalidate');

// CORS
header('Access-Control-Allow-Origin: *');
```

## 🐛 Problemas Comuns

### 1. **Headers already sent**
```
Warning: Cannot modify header information - headers already sent...
```
- Não envie output (echo, HTML) antes de headers
- Use `ob_start()` se necessário

### 2. **Sessão não funciona**
- Chame `session_start()` antes de qualquer output
- Verifique se cookies estão habilitados

### 3. **Upload não funciona**
- Verifique `upload_max_filesize` no php.ini
- Use `enctype="multipart/form-data"` no form
- Crie pasta `uploads/` com permissões adequadas

## 🌟 Boas Práticas

### 1. **Estrutura de Projeto**
```
projeto-web/
├── index.php
├── config/
│   └── database.php
├── includes/
│   ├── header.php
│   └── footer.php
├── css/
├── js/
├── uploads/
└── classes/
```

### 2. **Separação de Responsabilidades**
- HTML/CSS para apresentação
- PHP para lógica de negócio
- JavaScript para interatividade
- Banco de dados para persistência

### 3. **Segurança**
- Sempre validar/sanitizar entrada
- Usar prepared statements para SQL
- Implementar CSRF protection
- Hash senhas adequadamente
- Configurar HTTPS em produção

## 📖 Próximos Passos

1. **[Banco de Dados](../04-banco-dados/)** - Integração com MySQL/PostgreSQL
2. **Frameworks PHP** - Laravel, Symfony, CodeIgniter
3. **APIs REST** - Criação de webservices
4. **JavaScript/AJAX** - Interatividade avançada
5. **CSS Frameworks** - Bootstrap, Tailwind

## 📚 Recursos Adicionais

- [Manual PHP - Tratamento de Formulários](https://www.php.net/manual/pt_BR/tutorial.forms.php)
- [OWASP PHP Security Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/PHP_Configuration_Cheat_Sheet.html)
- [MDN Web Docs - HTML Forms](https://developer.mozilla.org/pt-BR/docs/Web/Guide/HTML/Forms)

---

💡 **Dica**: Desenvolvimento web requer atenção especial à segurança. Sempre valide dados de entrada e mantenha-se atualizado sobre vulnerabilidades comuns!