# Programação PHP

Este repositório faz parte do monorepositório de aulas de graduação dado para os alunos de informática da Universidade Nove de Julho.

Aqui você encontrará conteúdo completo sobre programação PHP, desde conceitos básicos até desenvolvimento web avançado, organizado de forma progressiva para facilitar o aprendizado.

## 🚀 Começando

### 📥 Instalação do PHP

**IMPORTANTE**: Antes de executar qualquer exemplo, você precisa ter o PHP instalado em sua máquina.

👉 **[Guia Completo de Instalação do PHP](INSTALACAO.md)** 

Este guia contém instruções detalhadas para:
- ✅ Windows (XAMPP, PHP Standalone)
- ✅ macOS (Homebrew, MAMP) 
- ✅ Linux (Ubuntu, CentOS, Arch)
- ✅ Configuração de ambiente de desenvolvimento
- ✅ Solução de problemas comuns

### ⚡ Teste Rápido

Após a instalação, teste se o PHP está funcionando:

```bash
php --version
```

Execute um exemplo básico:
```bash
cd programming/php/01-conceitos-php/01/
php 01-ola-mundo.php
```

## 📚 Estrutura do Conteúdo

O repositório está organizado em 6 seções progressivas:

### 1. 🎯 [Conceitos Básicos](01-conceitos-php/)
**Fundamentos da linguagem PHP**
- Sintaxe básica e variáveis
- Tipos de dados e operadores  
- Estruturas de controle (if, while, for)
- Arrays e manipulação de strings
- Exercícios práticos e desafios

**Ideal para**: Iniciantes em PHP

### 2. 🏗️ [Programação Orientada a Objetos](02-poo/)
**POO completa com exemplos práticos**
- Classes, objetos e métodos
- Encapsulamento, herança e polimorfismo
- Construtores e destrutores
- Integração com banco de dados
- Sistema completo "MundoCão" com exemplos

**Ideal para**: Após dominar conceitos básicos

### 3. 🌐 [PHP para Web](03-web/)
**Desenvolvimento de aplicações web**
- Integração PHP + HTML
- Formulários e processamento de dados
- Sessões e cookies
- Upload de arquivos
- Autenticação e segurança

**Ideal para**: Criação de sites dinâmicos

### 4. 🗄️ [Banco de Dados](04-banco-dados/)
**Integração com MySQL/MariaDB**
- Conexões PDO e MySQLi
- Operações CRUD completas
- Prepared statements e segurança
- Padrão DAO e classes de acesso a dados
- Transações e otimização

**Ideal para**: Aplicações que armazenam dados

### 5. 📄 [Geração de PDF](05-pdf/)
**Criação de documentos e relatórios**
- Biblioteca mPDF
- Relatórios com dados do banco
- Faturas e certificados
- Imagens e formatação avançada
- Proteção e marca d'água

**Ideal para**: Sistemas que geram documentos

### 6. 📖 [Apostila de Referência](Apostila/)
**Material de consulta rápida**
- Resumos dos principais conceitos
- Referência rápida de funções
- Exemplos práticos concentrados

## 🎓 Trilha de Aprendizado Recomendada

```
1. Instalação do PHP     → Configurar ambiente
2. Conceitos Básicos     → Fundamentos da linguagem  
3. POO                   → Organização do código
4. PHP para Web          → Aplicações dinâmicas
5. Banco de Dados        → Persistência de dados
6. Geração de PDF        → Relatórios e documentos
```

## 🛠️ Pré-requisitos por Seção

| Seção | Pré-requisitos |
|-------|----------------|
| Conceitos Básicos | PHP instalado |
| POO | Conceitos básicos |
| PHP Web | Conceitos básicos + POO |
| Banco de Dados | POO + Servidor MySQL |
| PDF | POO + Composer |

## 🚀 Executando os Exemplos

### Via Terminal/Prompt

```bash
# Navegue até a pasta do exemplo
cd programming/php/01-conceitos-php/01/

# Execute o arquivo
php 01-ola-mundo.php
```

### Via Servidor Web

Para exemplos web, use o servidor built-in do PHP:

```bash
# Na pasta do projeto
php -S localhost:8000

# Acesse: http://localhost:8000/arquivo.php
```

Ou use XAMPP/MAMP e coloque os arquivos em `htdocs`.

## 🎯 Características dos Exemplos

- ✅ **Testados e funcionais** - Todos os exemplos foram validados
- ✅ **Progressivos** - Complexidade crescente
- ✅ **Comentados** - Código explicado
- ✅ **Cross-platform** - Funcionam em Windows, macOS e Linux
- ✅ **Práticos** - Aplicações do mundo real

## 💡 Dicas de Estudo

1. **Siga a ordem recomendada** - Cada seção prepara para a próxima
2. **Execute todos os exemplos** - Não apenas leia, pratique
3. **Modifique os códigos** - Experimente e personalize
4. **Faça os desafios** - Eles consolidam o aprendizado
5. **Use a apostila** - Como referência rápida

## 🔧 Ferramentas Recomendadas

### Editores/IDEs
- **Visual Studio Code** (Gratuito) + Extensão PHP Intelephense
- **PhpStorm** (Pago/Gratuito para estudantes)
- **Sublime Text** (Freemium)

### Servidores Locais
- **XAMPP** (Windows/Linux/macOS)
- **MAMP** (macOS/Windows)  
- **Servidor built-in do PHP** (Qualquer SO)

### Banco de Dados
- **MySQL/MariaDB** 
- **phpMyAdmin** (Interface web)
- **Adminer** (Alternativa leve)

## 🐛 Suporte e Problemas

### Problemas Comuns

1. **PHP não reconhecido**: Verifique se está no PATH do sistema
2. **Erro de sintaxe**: Confira `;` no final das linhas
3. **Arquivo não encontrado**: Verifique o caminho e nome do arquivo
4. **Erro de conexão com banco**: Confirme se MySQL está rodando

### Onde Buscar Ajuda

- 📖 [Documentação Oficial do PHP](https://www.php.net/manual/pt_BR/)
- 🔍 [Stack Overflow em Português](https://pt.stackoverflow.com/questions/tagged/php)
- 💬 [PHP Brasil - Telegram](https://t.me/phpbrasil)

## 🤝 Contribuições

Contribuições são muito bem-vindas! Você pode:

- 🐛 Reportar bugs ou problemas
- 💡 Sugerir melhorias nos exemplos
- 📝 Corrigir documentação
- ✨ Adicionar novos exemplos

### Como Contribuir

1. Faça um fork do repositório
2. Crie uma branch para sua feature
3. Implemente suas alterações
4. Teste os exemplos
5. Envie um Pull Request

**Obs**: Verifique o padrão utilizado neste projeto na página inicial do repositório.

## 📄 Licença

Este material é disponibilizado para fins educacionais como parte do curso de graduação da Universidade Nove de Julho.

---

💡 **Dica**: Este material foi criado para ser seu guia completo de PHP. Bookmark esta página e volte sempre que precisar!
