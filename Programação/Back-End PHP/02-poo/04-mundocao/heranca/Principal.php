<?php

    require_once 'Cachorro.php';
    require_once 'Pinscher.php';

    $cachorro = new Cachorro();
    $cachorro->idate = 2;
    $cachorro->raca = "Poddle";
    $cachorro->nome = "Fex";
    $cachorro->Latir();

    echo "Sou um cão da raça " . $cachorro->raca . PHP_EOL;
    echo "Meu nome é " . $cachorro->nome . " e tenho " . $cachorro->idate . " de idade." . PHP_EOL;

    $cachorro1 = new Pinscher();
    $cachorro1->idate = 4;
    $cachorro1->raca = "Pinscher pequeno";
    $cachorro1->nome = "Fex";
    $cachorro1->Latir();

    echo "Sou um cão da raça " . $cachorro1->raca . PHP_EOL;
    echo "Meu nome é " . $cachorro1->nome . " e tenho " . $cachorro1->idate . " de idade." . PHP_EOL;

    $cachorro1 = new Pinscher();
    $cachorro1->idate = 4;
    $cachorro1->raca = "Pinscher pequeno";
    $cachorro1->nome = "Fex";
    $cachorro1->Latir();

    echo "Sou um cão da raça " . $cachorro1->raca . PHP_EOL;
    echo "Meu nome é " . $cachorro1->nome . " e tenho " . $cachorro1->idate . " de idade." . PHP_EOL;

?>