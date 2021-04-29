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

## Referência

[Documentação](https://www.php.net/manual/pt_BR/language.types.intro.php)
