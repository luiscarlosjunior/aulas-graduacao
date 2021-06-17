<?php
    require_once 'Pessoa.php';
    require_once 'PessoaFisica.php';
    require_once 'Funcionario.php';

    $nome = $rg = $cartao = "";

    $nome = $_POST["nome"]; 
    $rg = $_POST["rg"]; 
    $cartao = $_POST["cartao"]; 

    $func1 = new Funcionario();

    $func1->setNome($nome);
    $func1->setRg($rg);
    $func1->setCartao($cartao);
/*
    echo $func1->getNome() . PHP_EOL;
    echo $func1->getRg() . PHP_EOL;
    echo $func1->getCartao() . PHP_EOL;*/

?>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Pagina do Prof. Luis</title>
  </head>
  <body>
    <h2 >
      Seja bem-vindo, <?php echo $func1->getNome(); ?> <br>
    </h2>
    <h3> 
      Seu RG é <?php echo $func1->getRg(); ?> <br>
    </h3>
    <h3> 
      Seu Cartao é <?php echo $func1->getCartao(); ?> <br>
    </h3>
  </body>
</html>