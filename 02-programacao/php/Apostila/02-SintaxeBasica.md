# Sintaxe básica do PHP
Sintaxe em linguagem de programação é o conjunto de normas que regulam e coordenam as diferentes variáveis e sua associação. Em programação, existem três definições relevantes: **a sintaxe, a semântica e a hierarquia**. A primeira tem a ver com uma linguagem de programação de um software ou sua aplicação pode ser entendida como uma série de caracteres em particular á combinação. A sintaxe está composta de regras que determinam se a combinação ou "string" é válida ou não, e, por conseguinte, operacional.

## Tags PHP

Quando o PHP interpreta um arquivo ele procura pelas tags de abertura e fechamento, **\<?php e ?>\**, que dizem ao PHP para iniciar ou parar a interpretação do código entre elas.

PHP inclui uma tag curta echo <?= que é uma forma abreviada mais verbosa para<?php echo.

### Exemplo 01 - Abrindo e fechando tags PHP

```php
<?php echo 'Se quiser rodar mais linhas de código, 
              utilize dessa forma'; ?>

Você pode utilizar a tag curta <?= 'Exibir isso' ?>.
    É o equivalente de <?php echo 'Exibir isso' ?>.

<? echo 'Este código está entre tags curtas, mas somente funcionará '.
            'se short_open_tag estiver ativo'; ?>
```
> Nota:
>
>Como as tags curtas podem ser desabilitadas, é recomendável usar apenas as tags normais ( \<?php ?> e \<?= ?>\) para maximizar a compatibilidade.
>

Caso nã haja código HTML, é preferível omitir a tag de fechamento no final do arquivo. Prevenindo a existência de espaços ou linhas em branco após a tag, que podem causar efeitos indesejáveis, por que o PHP iniciará o buffer de saída quando não existir intenção do programador de enviar alguma saída neste ponto do script.

```php
<?php
echo "Oá, mundo";

// ... mais código

echo "última instrução";

// o script termina aqui, sem tag de fechamento PHP
```

## Comentários
O comentários em PHP são declarações que não são executadas pelo compilador ou interpretador. Os comentários podem ser usados para prover informações ou explanações sobre as variáveis, métodos, classes ou alguma alguma declaração.

Em PHP os comentários são no estilo do 'C', 'C++'. Por exemplo:

```php
<?php
    echo 'Isto é um aviso'; // Estilo de comentário de uma linha
    /* 
      Este é um comentário de múltiplas linhas
       ainda outra linha de comentário 
     */
    echo 'Isto é um outro texto';
    echo 'Um texto final'; # Este é um comentário de uma linha no estilo shell
?>
```
### Fique atento
Comentários de estilo "uma linha" apenas comentam até o final da linha ou do bloco PHP de código corrente, o que ocorrer primeiro. Isto significa que o código HTML após \// \... \?> ou \# \... \?> SERÁ impresso: \?> interrompe o modo PHP e retorna para o modo HTML, e // ou # não podem influenciar isto.

```php
<h1>Isto é um <?php # echo 'simples';?> exemplo.</h1>
<p>O cabeçalho acima irá dizer 'Isto é um exemplo'.</p>
```
Comentários no estilo 'C' terminam ao primeiro */ encontrado. Tenha certeza de não aninhar comentários no estilo 'C'. É fácil fazer este equívoco se estiver tentando comentar grandes blocos de código.

```php
<?php
 /*
    echo 'Isto é um teste'; /* Este comentário irá causar um problema */
 */
?>
```

### Exemplo comentários PHP

```php
<?php

  //======================================================================
  // CATEGORY LARGE FONT
  //======================================================================

  //-----------------------------------------------------
  // Sub-Category Smaller Font
  //-----------------------------------------------------

  /* Title Here Notice the First Letters are Capitalized */

  # Option 1
  # Option 2
  # Option 3

  /*
  * This is a detailed explanation
  * of something that should require
  * several paragraphs of information.
  */

// This is a single line quote.
?>
```
Os exemplos acima foi retirado da documentação do PHP [neste link](https://www.php.net/manual/pt_BR/language.basic-syntax.comments.php)
