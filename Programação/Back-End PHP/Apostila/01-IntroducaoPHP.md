# Introdução
PHP é uma poderosa linguagem e um interpretador, seja incluído em um servidor web ou executado separadamente como binário. Com ele é possível acessar arquivos, executar comandos e abrir conexões de rede com o servidor. Essas propriedades fazem qualquer coisa executando em um servidor web inseguras por padrão.

## O que é PHP?
O PHP (**um acrônimo recursivo para PHP: Hypertext Preprocessor**) é uma linguagem de script _open source_ de uso geral, muito utilizada, e especialmente adequada para o desenvolvimento web e que pode ser embutida dentro do HTML. 

PHP é desenhado especificamente para ser uma linguagem mais segura para escrever programas CGI que Perl ou C, e com a escolha correta de opções de configuração em tempo compilação ou de execução, e práticas corretas de programação, ela pode dar a combinação exata de liberdade e segurança que você precisa.

Um exemplo simples em PHP:

```php
<?php
  echo "Olá, mundo. Eu sou um script PHP!";
?>
```

## O que o PHP pode fazer?

Qualquer coisa. O PHP é focado principalmente nos scripts do lado do servidor, portanto, você pode fazer qualquer coisa que outro programa CGI pode fazer, como coletar dados de formulários, gerar páginas com conteúdo dinâmico ou enviar e receber cookies. Mas o PHP pode fazer muito mais.

### Existem três áreas principais onde os scripts PHP são usados:

* **Scripts no lado do servidor** (server-side): Este é o mais tradicional e principal campo de atuação do PHP. Você precisa de três coisas para isto funcionar: o interpretador do PHP (CGI ou módulo do servidor), um servidor web e um navegador web. É preciso rodar um servidor web conectado a o PHP. 
* **Scripts de linha de comando**: Você pode fazer um script PHP para executá-lo sem um servidor ou navegador. A única coisa necessária é o interpretador PHP. Esse tipo de uso é ideal para script executados usando o cron (Unix, Linux) ou o Agendador de Tarefas (no Windows). 
* **Escrever aplicações desktop**: O PHP provavelmente não é a melhor linguagem para criação de aplicações desktop com interfaces gráficas, mas se você conhece bem o PHP, e gostaria de usar alguns dos seus recursos avançados nas suas aplicações do lado do cliente, você pode usar o PHP-GTK para escrever programas assim. Você também tem a possibilidade de escrever aplicações multi-plataformas desse jeito.

Referencia
* [O que é PHP](https://www.php.net/manual/pt_BR/intro-whatis.php)
* [O que o PHP pode fazer](https://www.php.net/manual/pt_BR/intro-whatcando.php)
