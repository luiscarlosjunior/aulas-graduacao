<?php

    require_once 'Cachorro.php';
    require_once 'Poddle.php';
    require_once 'Pinscher.php';

    /*
    $dog = new Cachorro();

    $dog->setNome("Rex");
    $dog->setRaca("Poddle");
    $dog->setCorPelo("Preto");
    $dog->setPeso(56.0);

    echo $dog->getNome() . PHP_EOL;
    echo $dog->getRaca() . PHP_EOL;
    echo $dog->getCorPelo() . PHP_EOL;
    echo $dog->getPeso() . PHP_EOL;
    */
/*
    $dog = new Poddle();

    $dog->setNome("Rex");
    $dog->setRaca("Poddle");
    $dog->setCorPelo("Preto");
    $dog->setPeso(56.0);
    
    echo $dog->getNome() . PHP_EOL;
    echo $dog->getRaca() . PHP_EOL;
    echo $dog->getCorPelo() . PHP_EOL;
    echo $dog->getPeso() . PHP_EOL;
    $dog->Brincar();
*/
    $dog = new Pinscher();

    $dog->setNome("Rex");
    $dog->setRaca("Poddle");
    $dog->setCorPelo("Preto");
    $dog->setPeso(56.0);
    
    echo $dog->getNome() . PHP_EOL;
    echo $dog->getRaca() . PHP_EOL;
    echo $dog->getCorPelo() . PHP_EOL;
    echo $dog->getPeso() . PHP_EOL;
    $dog->tremer();

    /*
    $cachorro = new Cachorro();
    $cachorro->idate = 2;
    $cachorro->raca = "Poddle";
    $cachorro->nome = "Fex";
    $cachorro->Latir();

    echo "Sou um cão da raça " . $cachorro->raca . PHP_EOL;
    echo "Meu nome é " . $cachorro->nome . " e tenho " . $cachorro->idate . " de idade." . PHP_EOL;
    */
?>