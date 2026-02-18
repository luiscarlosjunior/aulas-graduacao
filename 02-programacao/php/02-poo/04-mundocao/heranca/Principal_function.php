<?php

    require_once 'Cachorro.php';
    require_once 'Pinscher.php';
    require_once 'Poddle.php';

    $cachorro = new Cachorro();
    $cachorro->idate = 2;
    $cachorro->raca = "Poddle";
    $cachorro->nome = "Fex";
    $cachorro->Latir();
    $cachorro->Raca();
    
    /*echo "Sou um cão da raça " . $cachorro->raca . PHP_EOL;
    echo "Meu nome é " . $cachorro->nome . " e tenho " . $cachorro->idate . " de idade." . PHP_EOL;

    $cachorro1 = new Pinscher();
    $cachorro1->idate = 4;
    $cachorro1->raca = "Pinscher pequeno";
    $cachorro1->nome = "Villa Lobus";
    $cachorro1->Latir();

    echo "Sou um cão da raça " . $cachorro1->raca . PHP_EOL;
    echo "Meu nome é " . $cachorro1->nome . " e tenho " . $cachorro1->idate . " de idade." . PHP_EOL;

    $cachorro2 = new Poddle();
    $cachorro2->idate = 4;
    $cachorro2->raca = "Poddle zero";
    $cachorro2->nome = "Joaquim";
    $cachorro2->Latir();
    $cachorro2->Raca();

    echo "Sou um cão da raça " . $cachorro1->raca . PHP_EOL;
    echo "Meu nome é " . $cachorro1->nome . " e tenho " . $cachorro1->idate . " de idade." . PHP_EOL;*/

?>