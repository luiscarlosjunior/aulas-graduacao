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



## Referência

[Documentação](https://www.php.net/manual/pt_BR/language.types.intro.php)
