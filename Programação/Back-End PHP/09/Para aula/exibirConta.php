<?php

require_once 'src/Conta.php';
require_once 'src/Titular.php';
require_once 'src/CPF.php';

$nome = $_POST['nome'];
$cpf = $_POST['cpf'];
$deposita = $_POST['deposita'];

$camila = new Titular(new CPF($cpf), $nome);
$primeiraConta = new Conta($camila);
$primeiraConta->deposita($deposita);

/*
$patricia = new Titular(new CPF('698.549.548-10'), 'Patricia');
$segundaConta = new Conta($patricia);
var_dump($segundaConta);

$outra = new Conta(new Titular(new CPF('123.654.789-01'), 'Abcdefg'));
unset($segundaConta);
echo Conta::recuperaNumeroDeContas();*/
?>
<html>  
    <body>
        <h1> <?php echo htmlspecialchars($_POST['nome']); ?></h1>
        <h2> 
        <?php
            echo $primeiraConta->recuperaNomeTitular() . "<br>";
            echo $primeiraConta->recuperaCpfTitular() . "<br>";
            echo $primeiraConta->recuperaSaldo() . "<br>"; 
        ?>
        </h2>
    </body>
</html>