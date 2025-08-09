# Guia de Instalação do PHP

Este guia irá orientá-lo na instalação do PHP em diferentes sistemas operacionais (Windows, macOS e Linux) para que você possa executar os exemplos e exercícios deste repositório.

## Sumário

- [O que é PHP?](#o-que-é-php)
- [Requisitos Mínimos](#requisitos-mínimos)
- [Instalação no Windows](#instalação-no-windows)
- [Instalação no macOS](#instalação-no-macos)
- [Instalação no Linux](#instalação-no-linux)
- [Verificando a Instalação](#verificando-a-instalação)
- [Configurando um Ambiente de Desenvolvimento](#configurando-um-ambiente-de-desenvolvimento)
- [Executando os Exemplos](#executando-os-exemplos)
- [Solução de Problemas Comuns](#solução-de-problemas-comuns)

## O que é PHP?

PHP (PHP: Hypertext Preprocessor) é uma linguagem de script de código aberto especialmente adequada para desenvolvimento web e pode ser incorporada em HTML. É uma das linguagens mais populares para desenvolvimento de aplicações web.

## Requisitos Mínimos

- Sistema operacional: Windows 10+, macOS 10.14+, ou distribuição Linux moderna
- Espaço em disco: ~50MB para instalação básica
- RAM: 512MB (recomendado 1GB+)
- **Versão recomendada**: PHP 8.1 ou superior

## Instalação no Windows

### Opção 1: XAMPP (Recomendado para iniciantes)

1. **Baixe o XAMPP**:
   - Acesse [https://www.apachefriends.org/](https://www.apachefriends.org/)
   - Baixe a versão mais recente para Windows
   - Execute o instalador como administrador

2. **Execute a instalação**:
   - Siga o assistente de instalação
   - Marque ao menos: Apache, MySQL, PHP, phpMyAdmin
   - Instale em `C:\xampp` (padrão)

3. **Inicie os serviços**:
   - Abra o XAMPP Control Panel
   - Clique em "Start" para Apache (e MySQL se necessário)

4. **Teste a instalação**:
   - Abra o navegador e vá para `http://localhost`
   - Você deve ver a página de boas-vindas do XAMPP

### Opção 2: PHP Standalone

1. **Baixe o PHP**:
   - Acesse [https://windows.php.net/download](https://windows.php.net/download)
   - Baixe a versão "Thread Safe" mais recente
   - Extraia para `C:\php`

2. **Configure as variáveis de ambiente**:
   - Abra "Variáveis de Ambiente" do Windows
   - Adicione `C:\php` à variável PATH

3. **Configure o php.ini**:
   - Copie `php.ini-development` para `php.ini`
   - Descomente as extensões necessárias

## Instalação no macOS

### Opção 1: Homebrew (Recomendado)

1. **Instale o Homebrew** (se não tiver):
   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
   ```

2. **Instale o PHP**:
   ```bash
   brew install php
   ```

3. **Adicione ao PATH** (adicione ao ~/.zshrc ou ~/.bash_profile):
   ```bash
   export PATH="/opt/homebrew/bin:$PATH"
   ```

4. **Recarregue o terminal**:
   ```bash
   source ~/.zshrc  # ou ~/.bash_profile
   ```

### Opção 2: MAMP

1. **Baixe o MAMP**:
   - Acesse [https://www.mamp.info/](https://www.mamp.info/)
   - Baixe a versão gratuita
   - Execute o instalador

2. **Configure e execute**:
   - Abra o MAMP
   - Clique em "Start Servers"
   - Teste em `http://localhost:8888`

## Instalação no Linux

### Ubuntu/Debian

```bash
# Atualize os repositórios
sudo apt update

# Instale o PHP e extensões básicas
sudo apt install php php-cli php-mbstring php-xml php-mysql php-curl

# Para desenvolvimento web, instale também o Apache
sudo apt install apache2 libapache2-mod-php
```

### CentOS/RHEL/Fedora

```bash
# CentOS/RHEL
sudo yum install php php-cli php-mbstring php-xml php-mysql

# Fedora
sudo dnf install php php-cli php-mbstring php-xml php-mysql
```

### Arch Linux

```bash
sudo pacman -S php php-apache php-gd
```

## Verificando a Instalação

Abra o terminal/prompt de comando e execute:

```bash
php --version
```

Você deve ver algo como:
```
PHP 8.3.6 (cli) (built: Jul 14 2025 18:30:55) (NTS)
Copyright (c) The PHP Group
Zend Engine v4.3.6, Copyright (c) Zend Technologies
```

### Teste básico

Crie um arquivo `teste.php`:

```php
<?php
echo "PHP está funcionando!" . PHP_EOL;
echo "Versão: " . phpversion() . PHP_EOL;
?>
```

Execute:
```bash
php teste.php
```

## Configurando um Ambiente de Desenvolvimento

### Editores Recomendados

1. **Visual Studio Code** (Gratuito)
   - Extensões: PHP Intelephense, PHP Debug
   - Download: [https://code.visualstudio.com/](https://code.visualstudio.com/)

2. **PhpStorm** (Pago/Gratuito para estudantes)
   - IDE completa para PHP
   - Download: [https://www.jetbrains.com/phpstorm/](https://www.jetbrains.com/phpstorm/)

3. **Sublime Text** (Gratuito/Pago)
   - Leve e rápido
   - Pacotes: PHP Companion, SublimeCodeIntel

### Configurações Úteis

Edite o arquivo `php.ini` para desenvolvimento:

```ini
; Mostrar todos os erros
display_errors = On
error_reporting = E_ALL

; Aumentar limite de memória
memory_limit = 256M

; Permitir tags curtas (opcional)
short_open_tag = On

; Habilitar extensões úteis
extension=curl
extension=gd
extension=mbstring
extension=pdo_mysql
extension=zip
```

## Executando os Exemplos

### Exemplos Básicos (CLI)

```bash
# Navegue até a pasta do exemplo
cd programming/php/01-conceitos-php/01/

# Execute um exemplo
php 01-ola-mundo.php
```

### Exemplos Web

Para exemplos web, você precisará de um servidor:

#### Servidor Built-in do PHP
```bash
# Na pasta do projeto
php -S localhost:8000

# Acesse: http://localhost:8000
```

#### XAMPP/MAMP
- Coloque os arquivos em `htdocs` (XAMPP) ou `htdocs` (MAMP)
- Acesse: `http://localhost/nome-do-arquivo.php`

### Estrutura Recomendada para Projetos

```
meu-projeto-php/
├── index.php          # Arquivo principal
├── classes/           # Classes PHP
├── includes/          # Arquivos incluídos
├── css/              # Estilos
├── js/               # JavaScript
└── images/           # Imagens
```

## Solução de Problemas Comuns

### PHP não é reconhecido como comando

**Windows**: Verifique se o PHP está no PATH do sistema.
**macOS/Linux**: Use o caminho completo ou configure o PATH.

### Erro "Class not found"

- Verifique se o arquivo da classe está sendo incluído corretamente
- Use `require_once` ou `include_once`
- Verifique os nomes de arquivos (case-sensitive no Linux/macOS)

### Erro de permissão (Linux/macOS)

```bash
# Dar permissão de execução
chmod +x arquivo.php

# Ou executar com sudo se necessário
sudo php arquivo.php
```

### Extensões PHP não encontradas

- Verifique se as extensões estão instaladas
- Descomente as linhas no php.ini
- Reinicie o servidor web

### Erro de memória

Aumente o `memory_limit` no php.ini:
```ini
memory_limit = 512M
```

### Problemas de encoding

Configure o encoding no início dos seus arquivos:
```php
<?php
header('Content-Type: text/html; charset=utf-8');
?>
```

## Recursos Adicionais

- [Documentação Oficial do PHP](https://www.php.net/manual/pt_BR/)
- [W3Schools PHP Tutorial](https://www.w3schools.com/php/)
- [PHP The Right Way](https://phptherightway.com/)
- [Composer (Gerenciador de Dependências)](https://getcomposer.org/)

## Próximos Passos

Após instalar o PHP, você pode:

1. Começar com os [Conceitos Básicos](01-conceitos-php/)
2. Aprender [Programação Orientada a Objetos](02-poo/)
3. Explorar [PHP para Web](03-web/)
4. Trabalhar com [Banco de Dados](04-banco-dados/)

---

**Importante**: Este guia foi testado com PHP 8.1+ em sistemas atualizados. Para versões antigas ou sistemas específicos, consulte a documentação oficial.