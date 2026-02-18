<?php

    require_once 'Cachorro.php';
    require_once 'Pinscher.php';
    require_once 'Poddle.php';


    $dog1 = new Pinscher();
    $dog2 = new Poddle();
    $dog3 = new Cachorro();

    echo "Pinscher " . $dog1->dormir();
    echo "Poddle " . $dog2->dormir();
    echo "Cachorro " . $dog3->dormir();

    /*$dog1->setNome("Café");
    $dog1->setIdade(2);
    $dog1->correr();
    $dog1->tremer();
    
    $dog2 = new Poddle();
    
    $dog2->setNome("Peludinho");
    $dog2->setIdade(5);
    $dog2->correr();
    $dog2->brincar();
    $dog2->comer();*/

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