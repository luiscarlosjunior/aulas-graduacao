<?php

require_once 'src/Conta/ContaCorrente.php';
require_once 'src/Correntista/Titular.php';
require_once 'src/Correntista/CPF.php';

$nome = $_POST['nome'];
$cpf = $_POST['cpf'];
$deposita = $_POST['deposita'];

$cliente = new Titular(new CPF($cpf), $nome);
$contaCorrente = new ContaCorrente($cliente);

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