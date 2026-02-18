<?php

    require_once 'Cachorro.php';

    // Criando uma instância
    $cafe = new Cachorro();

    $cafe->nome = "Café";
    $cafe->idade = 2;
    $cafe->corPelo = "Preto";

    echo "Meu nome é " . $cafe->nome . PHP_EOL; 
    echo "Minha idade é " . $cafe->idade . PHP_EOL; 
    echo "Meu pelo é da cor " . $cafe->corPelo . PHP_EOL; 

    $cafe->latir("Hof hof hof");
    $cafe->correr();

?>