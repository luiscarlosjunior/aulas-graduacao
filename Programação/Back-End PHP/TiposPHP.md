# Tipos de dados em PHP
Tipos de dados são formas de dizer ao sistema, compilador ou interpretador o quando de memória em bytes ele precisará alocar para uma devida variável. Existem diversos tipos de dados, mas aqui iremos abordar os dados existentes em PHP.

### O PHP suporta os seguintes tipos primitivos.

* Quatro tipos escalares:

```
bool
int
float (número de ponto flutuante)
double
string
boolean
```
* Quatro tipos compostos:
```
array
object
callable
iterable
```

* E finalmente dois tipos especiais:
```
resource
NULL
```

Este manual também introduz alguns pseudo-tipos por razões de legibilidade:

mixed
void

> **Nota**: O tipo de uma variável geralmente não é definido pelo programador: isto é decidido em tempo de execução pelo PHP, dependendo do contexto no qual a variável é usada.

## Descrição dos tipos

* **Valores numéricos**
  PHP reconhece dois tipos de dados numéricos, inteiros(int) e valores de ponto flutuante(float ou também conhecido como double).

  Tipo Int ( inteiro / integer )

  Um número inteiro é constituído de números naturais {0, 1, 2, 3, …} e de seus simétricos {-1, -2, -3, …}, ou seja, _signed_, podendo conter valores positivos ou negativos.

### Exemplo simples de tipos de dados

```php
<?php

int $idade = 21;
float $salario = 1000.301s;
double $divisao = 10 / 3;

float $divisao = 3;

string $texto = "Olá mundo";

boolean $verdadeiro = true;
boolean $falso = false;

//echo gettype($idade);
echo var_dump($idade);
```

O mesmo exemplo acima pode ser descrito sem os tipos de dados passado anteriomente, vejo o exemplo:

```php
<?php

$idade = 21;
$salario = 1000.301s;
$divisao = 10 / 3;

$divisao = 3;

$texto = "Olá mundo";

$verdadeiro = true;
$falso = false;

//echo gettype($idade);
echo var_dump($idade);
```
Isso acontece porque PHP é uma linguagem de tipagem dinâmica, logo, não é preciso passar de forma obrigatória o próprio compilador irá tratar disso.

# Tipos

## Booleanos

  Este é o tipo mais simples. Um booleano expressa um valor de verdade. Ele pode ser true ou false.

### Sintaxe
Para especificar um booleano literal, use as palavras-chave true ou false. Ambas são case-insensitive.

```php
  <?php
  $foo = True; // atribui o valor True para $foo
  ?>
```

Tipicamente, o resultado de um operador que retorne um valor booleano, é passado para uma estrutura de controle.

```php
<?php
// == É um operador que testa
// igualdade e retorna um booleano.
if ($action == "mostrar_versao") {
    echo "A versão é 1.23";
}

// isto não é necessário ...
if ($exibir_separadores == TRUE) {
    echo "<hr>\n";
}

// ... porque você pode simplesmente escrever isso:
if ($exibir_separadores) {
    echo "<hr>\n";
}
?>
```

### Convertendo para booleano
  Para converter explicitamente um valor para booleano, utilize os modificadores (bool) ou (boolean). Entretanto, na maioria dos casos, o modificador não será necessário, já que qualquer valor será convertido automaticamente se um operador, função ou estrutura de controle requerer um argumento booleano.

### Veja também Manipulação de tipos.

Ao converter para booleano, os seguintes valores são considerados false:

* O próprio booleano false
* Os inteiros 0 e -0 (zero)
* Os pontos flutuantes 0.0 e -0.0 (zero)
* Uma string vazia e a string "0"
* Um array sem elementos
* O tipo especial NULL (incluindo variáveis não definidas)
* Objetos SimpleXML criados a partir de tags vazias


Qualquer outro valor é considerado true (incluindo qualquer recurso e NAN).

> **Aviso** -1 é considerado **true**, como qualquer número que não seja zero (negativos ou positivos)!

```php
<?php
  var_dump((bool) "");        // bool(false)
  var_dump((bool) 1);         // bool(true)
  var_dump((bool) -2);        // bool(true)
  var_dump((bool) "foo");     // bool(true)
  var_dump((bool) 2.3e5);     // bool(true)
  var_dump((bool) array(12)); // bool(true)
  var_dump((bool) array());   // bool(false)
  var_dump((bool) "false");   // bool(true)
?>
```

Fonte: [Documentação Boolean](https://www.php.net/manual/pt_BR/language.types.boolean.php)

----

## Inteiros

Um inteiro é um número do conjunto ℤ = {..., -2, -1, 0, 1, 2, ...}.

Inteiros podem ser especificados em notação decimal (base 10), hexadecimal (base 16), octal (base 8) ou binária (base 2). O operador de negação pode ser usado para indicar um inteiro negativo. Inteiros binários literais estão disponíveis a partir do PHP 5.4.0.

Para usar a notação octal, preceda o número com um 0 (zero). Para utilizar a notação hexadecimal, preceda o número com 0x. Para utilizar a notação binária, preceda o número com 0b. A partir do PHP 7.4.0, inteiros literais podem conter sublinhados (\_) entre os dígitos, para melhorar a sua leitura. Estes sublinhados serão removidos pelo scanner do PHP.

### Exemplo #1 Literais inteiras

```php
<?php
  $a = 1234; // número decimal
  $a = 0123; // número octal (equivalente a 83 em decimal)
  $a = 0x1A; // número hexadecimal (equivalente a 26 em decimal)
  $a = 0b11111111; // número binário (equivalente ao 255 decimal)
  $a = 1_234_567; // decimal number (as of PHP 7.4.0)
?>
```

  O tamanho de um inteiro depende da plataforma, sendo um número aproximado a 2 bilhões o valor mais comum (número de 32 bits com sinal). Plataformas 64-bit possuem comumente o valor máximo de aproximadamente 9E18, exceto no Windows em versões anteriores ao PHP 7, onde são sempre 32-bit. O PHP não suporta inteiros sem sinal. 
  O tamanho do inteiro pode ser determinado pela constante **PHP_INT_SIZE**, e seu o valor máximo com a constante **PHP_INT_MAX** a partir do **PHP 5.0.5**, e o valor mínimo utilizando a constante **PHP_INT_MIN** a partir do **PHP 7.0.0**.

## Overflow de inteiros
Se o PHP encontrar um número além dos limites do tipo inteiro, ele será interpretado como um ponto flutuante. Assim, uma operação que resulte em um número além dos limites do tipo inteiro, retornará um ponto flutuante.

### Exemplo - Overflow de inteiros em sistemas 32-bit

```php
<?php
$large_number = 2147483647;
var_dump($large_number);                     // int(2147483647)

$large_number = 2147483648;
var_dump($large_number);                     // float(2147483648)

$million = 1000000;
$large_number =  50000 * $million;
var_dump($large_number);                     // float(50000000000)
?>
```

### Exemplo - Overflow de inteiros em sistemas 64-bit

```php
<?php
$large_number = 9223372036854775807;
var_dump($large_number);                     // int(9223372036854775807)

$large_number = 9223372036854775808;
var_dump($large_number);                     // float(9.2233720368548E+18)

$million = 1000000;
$large_number =  50000000000000 * $million;
var_dump($large_number);                     // float(5.0E+19)
?>
```

Não há um operador de divisão que resulta em um inteiro no PHP. 1/2 retorna o ponto flutuante 0.5. O valor pode ser convertido para inteiro para sempre truncar o número, ou usar a função round() que provê um fino controle sobre o arredondamento.

> **Nota**: A partir do PHP 7.0.0, a função **intdiv()** está disponível para uma divisão inteira.

```php
<?php
  var_dump(25/7);         // float(3.5714285714286)
  var_dump((int) (25/7)); // int(3)
  var_dump(round(25/7));  // float(4)
?>
```

## Referência

[Documentação](https://www.php.net/manual/pt_BR/language.types.intro.php)
