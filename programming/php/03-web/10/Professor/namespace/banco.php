<?php

require_once 'src/Modelo/Conta/Conta.php';
require_once 'src/Modelo/Pessoa.php';
require_once 'src/Modelo/Endereco.php';
require_once 'src/Modelo/Conta/Titular.php';
require_once 'src/Modelo/CPF.php';

$endereco = new Endereco('Sao Paulo', 'Bela Vista', 'Rua dos bobos', '0');
$cliente = new Titular(new CPF('123.456.789-10'), 'Carlos Eduardo', $endereco);
$primeiraConta = new Conta($cliente);
$primeiraConta->deposita(500);
$primeiraConta->saca(300); // isso é ok

echo $primeiraConta->recuperaNomeTitular() . PHP_EOL;
echo $primeiraConta->recuperaCpfTitular() . PHP_EOL;
echo $primeiraConta->recuperaSaldo() . PHP_EOL;

$cliente2 = new Titular(new CPF('222.555.548-10'), 'Joaquina', $endereco);
$segundaConta = new Conta($cliente2);
var_dump($segundaConta);
