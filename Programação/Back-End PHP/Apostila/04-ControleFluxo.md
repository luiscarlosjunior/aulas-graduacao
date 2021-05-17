# Estrutura de controle

Estruturas de controle (ou fluxo de controle) refere-se à ordem em que instruções, expressões e chamadas de função são executadas ou avaliadas em programas de computador sob programação imperativa ou funcional. Os tipos de estruturas de controle disponíveis diferem de linguagem para linguagem, mas podem ser cruamente caracterizados por seus efeitos. O primeiro é a continuação da execução em uma outra instrução, como na estrutura sequencial ou em uma instrução jump. O segundo é a execução de um bloco de código somente se uma condição é verdadeira, uma estrutura de seleção. O terceiro é a execução de um bloco de código enquanto uma condição é verdadeira, ou de forma a iterar uma coleção de dados, uma estrutura de repetição. O quarto é a execução de instruções distantes entre si, em que o controle de fluxo possivelmente volte para a posição original posteriormente, como chamadas de subrotinas e corotinas. O quinto é a parada do programa de computador. Interrupções e sinais são mecanismos de baixo nível que podem alterar o fluxo de controle de forma similar a uma sub-rotina, mas geralmente em resposta a algum estímulo externo ou um evento ao invés de uma estrutura de controle em uma linguagem. Em nível de linguagem de máquina, as instruções de estruturas de controle geralmente funcionam ao alterar o contador de programa. Para algumas CPUs, as únicas instruções de estruturas de controle disponíveis são os diversos tipos de jump condicional.

[Referência Estrutura de controle](https://pt.wikipedia.org/wiki/Estrutura_de_controle)

Há diversos tipos de estrutura de controle, porém aqui iremos focar somente nas estruturas de seleção (if, else, elseif)

## Estrurura de controle PHP
O texto abaixo foi tirado da documentação oficial da linguagem PHP [Link](https://www.php.net/manual/pt_BR/language.control-structures.php)

Qualquer script PHP é construído por uma série de instruções. Uma instrução pode ser uma atribuição, uma chamada de função, um laço de repetição, uma instrução condicional, ou mesmo uma instrução que não faz nada (um comando vazio). Instruções geralmente terminam com um ponto e vírgula. Além disso, as instruções podem ser agrupados em um grupo de comandos através do encapsulamento de um grupo de comandos com chaves. Um grupo de comandos é uma instrução também. Os vários tipos de instruções são descritos neste capítulo.

---

## Estrutura If

>(PHP 4, PHP 5, PHP 7, PHP 8)

O construtor **if** é um dos recursos mais importantes em muitas linguagens, inclusive no PHP. Permite a execução condicional de fragmentos de código. O PHP apresenta uma estrutura if semelhante a linguagem C

```php
if(expressao)
{
    //declaração lógica
}
```

Expressões são avaliadas por seus valores **Booleanos**. Se uma expressão for avaliada como **_true_**, o PHP executará a declaração, e se avaliá-la como **_false_** - ignorá-la.

O exemplo a seguir exibirá a is bigger than b se $a for maior que $b:

```php
<?php

$a = 50;
$b = 30;

if ($a > $b)
  echo "A é maior que B";
?>
```

Na maioria das vezes, é utilizado mais de uma declaraçao seja condicionalmente executada. É claro que não é necessário envolver cada declaração em uma cláusula if. Em vez disso, pode-se agrupar várias declarações em grupos. Por exemplo, este código exibirá A é maior que B se $a for maior que $b, e atribuirá o valor de $a em $b:

```php
<?php
if ($a > $b) {
  echo "A é maior que B";
  $b = $a;
}
?>
```
A declaração **If** pode ser aninhada indefinidamente dentro de outras declarações **If**, fornecendo completa flexibilidade para execução condicional de várias partes do programa.

---

## Estrutura else

>(PHP 4, PHP 5, PHP 7, PHP 8)

Muitas vezes deseja-se executar uma instrução se uma certa condição for válida, e uma instrução diferente se a mesma condição não for válida. Para isso que o else serve. O else estende a instrução if para executar outras caso a expressão no if retornar false. Por exemplo, o código a seguir exibirá a is greater than b se $a for maior que $b, e a is NOT greater than b caso contrário:

```php
<?php
if ($a > $b) {
  echo "a is greater than b";
} else {
  echo "a is NOT greater than b";
}
?>
```

---

## Estrutura elseif

A instrução else só é executada se a expressão de avaliação do if for avaliada como false, e se tiver qualquer expressão elseif - somente se também retornarem false.


>(PHP 4, PHP 5, PHP 7, PHP 8)

elseif, como o nome sugere, é uma combinação do if e else. Como o else, estende um if para executar instruções diferentes no caso da expressão if original ser avaliada como false. Entretanto, diferentemente do else, executará uma expressão alternativa somente se a expressão condicional do elseif for avaliada como true. Por exemplo, o código a seguir exibirá a is bigger than b, a equal to b ou a is smaller than b:

```php
<?php
if ($a > $b) {
    echo "a is bigger than b";
} elseif ($a == $b) {
    echo "a is equal to b";
} else {
    echo "a is smaller than b";
}
?>
```

Pode haver vários **elseifs** dentro da mesma declaração **if**. A primeira expressão elseif (se houver) que retornar true será executada. No PHP, pode-se escrever 'else if' (em duas palavras), e o comportamento será idêntico ao do 'elseif' (em uma única palavra). O significado sintático é um pouco diferente (se você está familiarizado com C, o comportamento é o mesmo), mas, no fundo, ambos terão exatamente o mesmo comportamento.

O elseif só é executado se o if precedente ou qualquer elseif for avaliado como false, e o elseif corrente for avaliado como true.

Nota: Note que o elseif e else if só serão considerados exatamente iguais se usados com chaves como no exemplo abaixo. Ao utilizar os dois pontos (:) para definir as condições de if/elseif, não deve-se separar else if em duas palavras, ou o PHP falhará com um erro de interpretação.

```php
<?php

/* Maneira incorreta*/
if ($a > $b):
    echo $a." is greater than ".$b;
else if ($a == $b): // Will not compile.
    echo "The above line causes a parse error.";
endif;


/* Maneira correta */
if ($a > $b):
    echo $a." é maior do que ".$b;
elseif ($a == $b): // Note na combinação das palavras.
    echo $a." igual ".$b;
else:
    echo $a." não é nem menor nem maior ou igual à b".$b;
endif;

?>
```

---

## Estrutura Switch

> (PHP 4, PHP 5, PHP 7, PHP 8)

A declaração **switch** é bem parecido a uma série de declarações IFs na mesma expressão. Em muitos casos, se for comparar as mesmas variáveis (ou expressões), com valores diferentes, e executar pedaços diferentes de código dependendo de qual valor ela é igual. Esta é exatamente a serventia da declaração **switch**.

Os exemplos a seguir demonstram duas formas diferentes de escrever a mesma coisa, uma usando uma série de declarações if e elseif, e a outra usando a declaração switch:

### Exemplo #1 Estrutura do switch

```php
<?php
if ($i == 0) {
    echo "i igual 0";
} elseif ($i == 1) {
    echo "i igual 1";
} elseif ($i == 2) {
    echo "i igual 2";
}

switch ($i) {
    case 0:
        echo "i igual 0";
        break;
    case 1:
        echo "i igual 1";
        break;
    case 2:
        echo "i igual 2";
        break;
}
?>
```

### Exemplo #2 A estrutura do switch permite o uso de strings (sequência de texto)

```php
<?php
switch ($i) {
    case "maça":
        echo "i é uma maça";
        break;
    case "bar":
        echo "i é bar";
        break;
    case "bolo":
        echo "i é bolo";
        break;
}
?>
```

É importante entender como a declaração **switch** é executada a fim de evitar enganos. A declação **switch** executa linha por linha (na verdade, declaração por declaração). No início nenhum código é executado. Somente quando uma declaração case é encontrada, cuja expressão avalia um valor que corresponde ao valor do **switch**, o PHP começará a executar a declaração. O PHP continuará a executar a declaração até o fim do bloco **switch**, ou até a primeira declaração **break** encontrada. Se não for escrita uma declaração **break** ao final da lista de declarações do case, o PHP continuará executando as declarações dos próximos cases. Por exemplo:

```php
<?php
switch ($i) {
    case 0:
        echo "i igual 0";
    case 1:
        echo "i igual 1";
    case 2:
        echo "i igual 2";
}
?>
```

Neste exemplo, se a variável $i for igual a 0, o PHP executará todos as declarações echo! Se a variável $i for igual a 1, o PHP executará as duas últimas declarações echo. Somente se obterá o comportamento esperado ('i igual 2' será exibido), se $i for igual a 2. Sendo assim, é importante não esquecer as declarações break (mesmo quando se quer evitar, de propósito, sua omissão em certas circunstâncias).

Em uma declaração **switch**, a condição é avaliada somente uma vez, e o resultado é comparado a cada declaração **case**. Em uma declaração **elseif** a condição é avaliada outra vez. Se a condição for mais complicada que uma simples comparação e/ou está em um laço pequeno, a declaração **switch** pode ser mais rápida.

A lista de declarações de um case também podem estar vazia, passando o controle da lista de declarações para o próximo case.

```php
<?php
switch ($i) {
    case 0:
    case 1:
    case 2:
        echo "i é menor do que 3, mas não é negativo";
        break;
    case 3:
        echo "i is 3";
}
?>
```

Um case especial é o **default**. Este **case** corresponde a tudo que não foi correspondido pelos outros cases. Por exemplo:

```php

<?php
switch ($i) {
    case 0:
        echo "i igual 0";
        break;
    case 1:
        echo "i igual 1";
        break;
    case 2:
        echo "i igual 2";
        break;
    default:
       echo "i is not equal to 0, 1 or 2";
}
?>

```

A sintaxe alternativa das estruturas de controle é suportada por switches. Para mais informações, veja Sintaxe alternativa para estruturas de controle.

```php

<?php
switch ($i):
    case 0:
        echo "i igual 0";
        break;
    case 1:
        echo "i igual 1";
        break;
    case 2:
        echo "i igual 2";
        break;
    default:
        echo "i não é igual a 0, 1 ou 2";
endswitch;
?>
```

É possível utilizar o ponto e vírgula ao invés dos dois pontos em um **case**, como:

```php
<?php
switch($cerveja)
{
    case 'tuborg';
    case 'amsten';
    case 'heineken';
        echo 'Boa escolha';
    break;
    default;
        echo 'Por favor, faça uma nova seleção...';
    break;
}
?>
```

---

Referência

Os exemplos e o texto acima foi retirado da documentação oficial em portugues do PHP [neste link](https://www.php.net/manual/pt_BR/language.control-structures.php)
