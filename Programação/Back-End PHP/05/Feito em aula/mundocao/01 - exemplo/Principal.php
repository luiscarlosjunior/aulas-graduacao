<?php

    require_once 'Canideo.php';
    require_once 'Cachorro.php';
    require_once 'Lobo.php';
    require_once 'Poddle.php';
    require_once 'Pinscher.php';
    
    
    echo PHP_EOL;
    echo PHP_EOL . "*******Cachorro********" . PHP_EOL;
    
    
    $dog = new Cachorro();
    $dog->setNome("Triton");
    $dog->setRaca("Husky");
    $dog->setColoracao("Branco e Preto");
    $dog->setPeso(56.0);

    echo $dog->getNome() . PHP_EOL;
    echo $dog->getRaca() . PHP_EOL;
    echo $dog->getColoracao() . PHP_EOL;
    
    echo PHP_EOL;
    echo PHP_EOL . "***************" . PHP_EOL;

    $dog = new Poddle();

    $dog->setNome("Rex");
    $dog->setRaca("Poddle");
    $dog->setColoracao("Branco");
    $dog->setPeso(10.0);
    
    echo $dog->getNome() . PHP_EOL;
    echo $dog->getRaca() . PHP_EOL;
    echo $dog->getColoracao() . PHP_EOL;
    echo $dog->getPeso() . PHP_EOL;
    $dog->Brincar();

    echo PHP_EOL . "***************" . PHP_EOL;

    $dog = new Pinscher();

    $dog->setNome("Rex");
    $dog->setRaca("Poddle");
    $dog->setColoracao("Preto");
    $dog->setPeso(56.0);
    
    echo $dog->getNome() . PHP_EOL;
    echo $dog->getRaca() . PHP_EOL;
    echo $dog->getColoracao() . PHP_EOL;
    echo $dog->getPeso() . PHP_EOL;
    $dog->tremer();


    echo PHP_EOL;
    echo PHP_EOL . "*******Lobo********" . PHP_EOL;

    $lobo = new Lobo();

    $lobo->setRaca("Lobo Guará");
    $lobo->setColoracao("Avermelhado");
    $lobo->setPeso(56.0);

    echo $lobo->getRaca() . PHP_EOL;
    echo $lobo->getColoracao() . PHP_EOL;
    echo $lobo->getPeso() . PHP_EOL;

    echo PHP_EOL;
    echo PHP_EOL . "*******Lobo********" . PHP_EOL;

    $lobo = new Lobo();

    $lobo->setRaca("Lobo Cinzento");
    $lobo->setColoracao("Acinzentado");
    $lobo->setPeso(56.0);

    echo $lobo->getRaca() . PHP_EOL;
    echo $lobo->getColoracao() . PHP_EOL;
    echo $lobo->getPeso() . PHP_EOL;


/*
    // Problema
    echo PHP_EOL;
    echo PHP_EOL;
    echo PHP_EOL . "*******Canideo********" . PHP_EOL;
    $canideo = new Canideo();

    $canideo->setTempoVida(10.0);
    $canideo->setTamanho("Médio");
    $canideo->setColoracao("Amarelado");
    $canideo->setPelagem(1.0);
    $canideo->setVelocidade(40.0);

    echo $canideo->getTempoVida() . PHP_EOL;
    echo $canideo->getTamanho() . PHP_EOL;
    echo $canideo->getColoracao() . PHP_EOL;
    echo $canideo->getPelagem() . PHP_EOL;
    echo $canideo->getVelocidade() . PHP_EOL;
*/
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