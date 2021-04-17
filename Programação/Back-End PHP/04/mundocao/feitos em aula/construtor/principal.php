<?php

    require_once 'Cachorro.php';
    require_once 'Pinscher.php';
    require_once 'Poddle.php';


    echo PHP_EOL . "**********Caramelo********" . PHP_EOL;
    $dog3 =new  Cachorro("Rex", "Caramelo");
    echo "Minha raça " . $dog3->raca;

    echo PHP_EOL . "**********PINSCHER********" . PHP_EOL;
    $dog1 = new Pinscher("Tufinho");
    $dog1->tremer();
    
    echo PHP_EOL . "**********PODDLE********" . PHP_EOL;
    $dog2 = new Poddle("Peludinho");
    
    $dog2->brincar();
    $dog2->comer();

    // Exibindo dados
    /*echo "Meu nome é " . $dog1->getNome() . PHP_EOL; 
    echo "Minha idade é " . $dog1->getIdade() . PHP_EOL; 
    echo "Meu nome é " . $dog2->getNome() . PHP_EOL; 
    echo "Minha idade é " . $dog2->getIdade() . PHP_EOL; */

    // Criando uma instância
    /*
    $cafe = new Cachorro();

    $cafe->nome = "Café";
    $cafe->idade = 2;
    $cafe->corPelo = "Preto";

    echo "Meu nome é " . $cafe->nome . PHP_EOL; 
    echo "Minha idade é " . $cafe->idade . PHP_EOL; 
    echo "Meu pelo é da cor " . $cafe->corPelo . PHP_EOL; 

    $cafe->latir("Hof hof hof");
    $cafe->correr();
    */
?>