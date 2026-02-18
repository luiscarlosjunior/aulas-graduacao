<?php

    require_once 'Canideo.php';
    require_once 'Lobo.php';
    require_once 'Cachorro.php';
    require_once 'Poddle.php';
    require_once 'Pinscher.php';


    function cachorro()
    {
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
        echo $dog->getPeso() . PHP_EOL;
        echo $dog->Sociabilidade() . PHP_EOL;
    }
    
    function poddle()
    {
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
    }

    function pinscher()
    {
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
    
    }

    function lobo()
    {
        echo PHP_EOL;
        echo PHP_EOL . "*******Lobo********" . PHP_EOL;

        $dog = new Lobo();

        $dog->setRaca("Lobo Guará");
        $dog->setColoracao("Avermelhado");
        $dog->setPeso(56.0);

        echo $dog->getRaca() . PHP_EOL;
        echo $dog->getColoracao() . PHP_EOL;
        echo $dog->getPeso() . PHP_EOL;

        echo PHP_EOL;
        echo PHP_EOL . "*******Lobo********" . PHP_EOL;

        $dog = new Lobo();

        $dog->setRaca("Lobo Cinzento");
        $dog->setColoracao("Acinzentado");
        $dog->setPeso(56.0);

        echo $dog->getRaca() . PHP_EOL;
        echo $dog->getColoracao() . PHP_EOL;
        echo $dog->getPeso() . PHP_EOL;
  
    }

    Cachorro();
      
?>