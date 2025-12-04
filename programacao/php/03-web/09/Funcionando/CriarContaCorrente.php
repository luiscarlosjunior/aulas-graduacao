<?php

require_once __DIR__ . '/src/Modelo/Correntista/Titular.php';
require_once __DIR__ . '/src/Modelo/Correntista/CPF.php';
require_once __DIR__ . '/src/Modelo/Conta/ContaCorrente.php';

use Modelo\Correntista\Titular;
use Modelo\Correntista\CPF;
use Modelo\Conta\ContaCorrente;

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
            
        ?>
        </h2>
    </body>
</html>