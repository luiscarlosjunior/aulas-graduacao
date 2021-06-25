# Estrutura de repetição

Uma estrutura de repetição realiza e repete diferentes computações ou ações dependendo se uma condição é verdadeira ou falsa, condição essa que é um expressão processada e transformada em um valor booleano. Está associado a ela além da condição (também chamada "expressão de controle" ou "condição de parada") o bloco de código: verifica-se a condição, e caso seja verdadeira, o bloco é executado. Após o final da execução do bloco, a condição é verificada novamente, e caso ela ainda seja verdadeira, o código é executado novamente.

Deve-se observar que, caso o bloco de código nunca modificar o estado da condição, a estrutura será executada para sempre, uma situação chamada laço infinito. Da mesma forma, é possível especificar uma estrutura em que o bloco de código modifica o estado da condição, mas esta é sempre verdadeira.

Algumas linguagens de programação especificam ainda uma palavra reservada para sair da estrutura de repetição de dentro do bloco de código, "quebrando" a estrutura. Também é oferecido por algumas linguagens uma palavra reservada para terminar uma iteração específica do bloco de código, forçando nova verificação da condição.

Fonte: [Wikipédia](https://pt.wikipedia.org/wiki/Estrutura_de_controle)

---

## Estrutura de repetição em PHP

O PHP possui quatro estruturas de repetição: **for, foreach, while e do-while**.

---

## Estrutura While

> (PHP 4, PHP 5, PHP 7, PHP 8)

Laços while são os mais simples tipos de laços do PHP. Possui comportamento semelhante ao C. O formato básico de uma declaração while é:

```php
while (expressão)
    declaração;
```

O propósito da declaração while é simples. Ele dirá ao PHP para executar as declarações aninhadas repetidamente, enquanto a expressão do while forem avaliadas como true. O valor da expressão é checado a cada vez que o laço é iniciado, então, mesmo seu valor mude durante a a execução das declarações aninhadas, a execução não será interrompida até o final da iteração ( cada vez que o PHP executa as declarações dentro do laço é uma iteração). Entretanto, se a expressão do while for avaliada como false desde o início, as declarações aninhadas não serão executadas nenhuma vez.

Similar a declaração if, pode-se agrupar múltiplas declarações no mesmo laço while delimitando o grupo de declarações por chaves, ou utilizando a sintaxe alternativa:

```php
while (expr):
    statement
    ...
endwhile;
```

Os exemplos a seguir são idênticos, e ambos imprimem os números de 1 a 10.

```php
<?php
/* Exemplo 1 */

$i = 1;
while ($i <= 10) {
    echo $i++;  /* é exibido o valor de $i     antes do incremento */
}

/* Exemplo 2 */

$i = 1;
while ($i <= 10):
    echo $i;
    $i++;
endwhile;
?>
```

---

## Estrutura Do-While

> (PHP 4, PHP 5, PHP 7, PHP 8)

Os laços do-while é muito similar aos laços while, com exceção que a expressão de avaliação é verificada ao final de cada iteração em vez de no começo. A maior diferença para o laço while é que a primeira iteração do laço do-while sempre é executada (a expressão de avaliação é executada somente no final da iteração), considerando que no laço while não é necessariamente executada (a expressão de avaliação é executada no começo de cada iteração, se avaliada como false logo no começo, a execução do laço será abortada imediatamente).

Só há uma sintaxe para o laço do-while:

```php
<?php
$i = 0;
do {
    echo $i;
} while ($i > 0);
?>
```

O laço acima será executado somente uma vez, pois após a primeira iteração, quando a expressão de avaliação for executada, resultará em false ($i não é maior que 0) e a execução do laço será encerrada.

Usuário avançados de C devem estar familiarizados com um uso diferente do laço do-while, que permite parar a execução no meio do bloco de códigos, encapsulando-os em um do-while (0), e usando o break . O código a seguir demonstra isso:

```php
<?php
do {
    if ($i < 5) {
        echo "i is not big enough";
        break;
    }
    $i *= $factor;
    if ($i < $minimum_limit) {
        break;
    }
   echo "i is ok";

    /* process i */

} while (0);
?>
```

Não se preocupe se não entender isso neste momento. Pode-se criar scripts simples ou mesmo poderosos sem usar esse 'recurso'. Desde o PHP 5.3.0, é possível usar o operador goto ao invés desse hack.

---

## Estrutura for

>(PHP 4, PHP 5, PHP 7, PHP 8)

Os laços for são os mais complexo no PHP. Possui comportamento semelhante ao C. A sintaxe do laço for é:

```php
for (expr1; expr2; expr3)
    declaracao;
```

A primeira expressão (expr1) é avaliada (executada), uma vez, incondicionalmente, no início do laço.

No começo de cada iteração a expr2 é avaliada. Se a avaliada como true, o laço continuará e as instruções aninhada serão executadas. Se avaliada como false, a execução do laço terminará.

No final de cada iteração, a expr3 é avaliada (executada).

Cada uma das expressões podem ser vazias ou conter múltiplas expressões separadas por vírgulas. Na expr2, todas as expressões separadas por vírgula são avaliadas mas o resultado é obtido da última parte. Se a expr2 estiver vazia significa que o laço deve ser executado indefinidamente (O PHP considera implicitamente como true, igual ao C). Isto não é inútil como se imagina, pois muitas vezes deseja-se interromper o laço utilizando a instrução break ao invés de usar a expressão verdade do for.

Analise os seguintes exemplos. Todos exibem números de 1 até 10:

```php
<?php
/* exemplo 1 */

for ($i = 1; $i <= 10; $i++) {
    echo $i;
}

/* exemplo 2 2 */

for ($i = 1; ; $i++) {
    if ($i > 10) {
        break;
    }
    echo $i;
}

/* exemplo 3 */

$i = 1;
for (; ; ) {
    if ($i > 10) {
        break;
    }
    echo $i;
    $i++;
}

/* exemplo 4 */

for ($i = 1, $j = 0; $i <= 10; $j += $i, print $i, $i++);
?>

```
É claro que o primeiro exemplo aparenta ser o mais simpático (ou talvez o quarto), mas pode-se achar que o uso de expressões vazias no laço for, seja vantajoso em algumas ocasiões.

O PHP também suporta a sintaxe alternativa "dois pontos" para o laço for.

```php
for (expr1; expr2; expr3):
    statement
    ...
endfor;

```

É comum, para muitos usuários, iterar em arrays como no exemplo abaixo.

```php

<?php
/*
 * Esta é uma array com alguns dados que devem ser modificadoswant to modify
 * durante a execuçao do loop for.
 */
$people = array(
    array('name' => 'Kalle', 'salt' => 856412),
    array('name' => 'Pierre', 'salt' => 215863)
);

for($i = 0; $i < count($people); ++$i) {
    $people[$i]['salt'] = mt_rand(000000, 999999);
}
?>
```

O código acima pode se tornar lento, pois o tamanho do array será calculado a cada iteração. Desde que o tamanho nunca mude, o laço pode ser facilmente otimizado usando uma variável intermediária para armazenar o tamanho ao invés de executar repetidamente o count():

```php
<?php
$people = array(
    array('name' => 'Kalle', 'salt' => 856412),
    array('name' => 'Pierre', 'salt' => 215863)
);

for($i = 0, $size = count($people); $i < $size; ++$i) {
    $people[$i]['salt'] = mt_rand(000000, 999999);
}
?>

```

---

## Estrutura foreach

>(PHP 4, PHP 5, PHP 7, PHP 8)

O construtor foreach fornece uma maneira fácil de iterar sobre arrays. O foreach funciona somente em arrays e objetos, e emitirá um erro ao tentar usá-lo em uma variável com um tipo de dado diferente ou em uma variável não inicializada. Possui duas sintaxes:

```php
foreach (array_expression as $value)
    declaração;
foreach (array_expression as $key => $value)
    declaração;

```
A primeira forma, itera sobre arrays informados na array_expression. A cada iteração, o valor do elemento atual é atribuído a $value e o ponteiro interno do array avança uma posição (então, na próxima iteração, se estará olhando para o próximo elemento).

A segunda forma var, adicionalmente, atribuir a chave do elemento corrente a variável $key a cada iteração.

É possível customizar a iteração em objetos.

>Nota:
>
>>No PHP 5, quando o foreach inicia sua primeira execução, o ponteiro interno do array é automaticamente redefinido para o primeiro elemento. Isso indica que não é necessário chamar a função reset() antes de um laço foreach.

Como o foreach depende do ponteiro interno do array no PHP 5, modificá-lo dentro de um laço pode levar a comportamentos inesperados.

>No PHP 7, o foreach não utiliza o ponteiro interno do array.

Para modificar diretamente elementos de um array dentro de um laço, preceda $value com &. Neste caso, o valor será atribuído por referência.

```php
<?php
$arr = array(1, 2, 3, 4);
foreach ($arr as &$value) {
    $value = $value * 2;
}
// $arr is now array(2, 4, 6, 8)
unset($value); // break the reference with the last element
?>
```

>Aviso
>>A referência ao $value e o último elemento do array permanecerá inalterado mesmo após a iteração do foreach. É recomendado destruí-lo utilizando a função unset(). Caso contrário você experienciará o seguinte comportamento:

```php
<?php
$arr = array(1, 2, 3, 4);
foreach ($arr as &$value) {
    $value = $value * 2;
}
// $arr is now array(2, 4, 6, 8)

// sem um unset($value), $value continuará como referência ao último item: $arr[3]

foreach ($arr as $key => $value) {
    // $arr[3] será atualizado com cada valor de $arr...
    echo "{$key} => {$value} ";
    print_r($arr);
}
// ...até que, o segundo e último valor é copiado para o último valor

// saída:
// 0 => 2 Array ( [0] => 2, [1] => 4, [2] => 6, [3] => 2 )
// 1 => 4 Array ( [0] => 2, [1] => 4, [2] => 6, [3] => 4 )
// 2 => 6 Array ( [0] => 2, [1] => 4, [2] => 6, [3] => 6 )
// 3 => 6 Array ( [0] => 2, [1] => 4, [2] => 6, [3] => 6 )
?>
```

Em versões anteriores ao PHP 5.5.0, a referência a $value só é possível se o array iterado puder ser referenciado (isso é, se ele for uma variável). O código a seguir só funcionará em versões do PHP superiores a 5.5.0:

```php
<?php
foreach (array(1, 2, 3, 4) as &$value) {
    $value = $value * 2;
}
?>
```

>Nota:

>>O foreach não possui suporte a habilidade de suprimir mensagens de erro utilizando o '@'.

### Mais alguns exemplos para demonstrar o uso:

```php
<?php
/* foreach example 1: value only */

$a = array(1, 2, 3, 17);

foreach ($a as $v) {
    echo "Current value of \$a: $v.\n";
}

/* foreach example 2: value (with its manual access notation printed for illustration) */

$a = array(1, 2, 3, 17);

$i = 0; /* for illustrative purposes only */

foreach ($a as $v) {
    echo "\$a[$i] => $v.\n";
    $i++;
}

/* foreach example 3: key and value */

$a = array(
    "one" => 1,
    "two" => 2,
    "three" => 3,
    "seventeen" => 17
);

foreach ($a as $k => $v) {
    echo "\$a[$k] => $v.\n";
}

/* foreach example 4: multi-dimensional arrays */
$a = array();
$a[0][0] = "a";
$a[0][1] = "b";
$a[1][0] = "y";
$a[1][1] = "z";

foreach ($a as $v1) {
    foreach ($v1 as $v2) {
        echo "$v2\n";
    }
}

/* foreach example 5: dynamic arrays */

foreach (array(1, 2, 3, 4, 5) as $v) {
    echo "$v\n";
}
?>
```

Desempacotando arrays aninhados com o construtor list() ¶
(PHP 5 >= 5.5.0, PHP 7, PHP 8)

O PHP 5.5 adicionou a habilidade de iterar sobre array de arrays e desempacotar arrays aninhados em variáveis do laço fornecendo o construtor list() como valor.

Por exemplo:


```php
<?php
$array = [
    [1, 2],
    [3, 4],
];

foreach ($array as list($a, $b)) {
    // $a contains the first element of the nested array,
    // and $b contains the second element.
    echo "A: $a; B: $b\n";
}
?>

```
O exemplo acima irá imprimir:

A: 1; B: 2
A: 3; B: 4
Pode-se se fornecer menos elementos ao construtor list() aos que existem no array aninhado, neste caso, os valores do array que sobrarem serão ignorados.


```php
<?php
$array = [
    [1, 2],
    [3, 4],
];

foreach ($array as list($a)) {
    // Note that there is no $b here.
    echo "$a\n";
}
?>

```

O exemplo acima irá imprimir:

1
3
Um aviso será gerado se não houver elementos suficientes no array para o preenchimento do construtor list():



```php
<?php
$array = [
    [1, 2],
    [3, 4],
];

foreach ($array as list($a, $b, $c)) {
    echo "A: $a; B: $b; C: $c\n";
}
?>

```

O exemplo acima irá imprimir:


Notice: Undefined offset: 2 in example.php on line 7
A: 1; B: 2; C:

Notice: Undefined offset: 2 in example.php on line 7
A: 3; B: 4; C: