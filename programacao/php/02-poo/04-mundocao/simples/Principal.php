<?php

    require_once 'Cachorro.php';

    $cachorro = new Cachorro();
    $cachorro->idate = 2;
    $cachorro->raca = "Poddle";
    $cachorro->nome = "Fex";
    $cachorro->Latir();

    echo "Sou um cão da raça " . $cachorro->raca . PHP_EOL;
    echo "Meu nome é " . $cachorro->nome . " e tenho " . $cachorro->idate . " de idade." . PHP_EOL;

?>