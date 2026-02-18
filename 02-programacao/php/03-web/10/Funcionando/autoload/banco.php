<?php
require_once 'autoload.php';

use Uninove\Banco\Modelo\{Endereco, CPF};
use Uninove\Banco\Modelo\Conta\{Conta, Titular};

$endereco = new Endereco('Sao Paulo', 'Bela Vista', 'Rua dos bobos', '0');
$cliente = new Titular(new CPF('123.456.789-10'), 'Luis Carlos', $endereco);
$primeiraConta = new Conta($cliente);
$primeiraConta->deposita(500);
$primeiraConta->saca(300); 

echo $primeiraConta->recuperaNomeTitular() . PHP_EOL;
echo $primeiraConta->recuperaCpfTitular() . PHP_EOL;
echo $primeiraConta->recuperaSaldo() . PHP_EOL;

$cliente2 = new Titular(new CPF('222.555.548-10'), 'Joaquina', $endereco);
$segundaConta = new Conta($cliente2);
var_dump($segundaConta);
