<?php
    require_once 'Pessoa.php';
    require_once 'PessoaFisica.php';
    require_once 'Funcionario.php';
    $func1 = new Funcionario();
    $func1->setNome("Luis Caparroz");
    $func1->setRg("123456789");
    $func1->setCartao("123654");
    echo 'Nome do funcionário ' . $func1->getNome() . PHP_EOL;
    echo 'RG ' . $func1->getRG() . PHP_EOL;
    echo 'Cartão ' . $func1->getCartao() . PHP_EOL;
?>