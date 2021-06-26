<?php

    require_once 'Animal.php';
    require_once 'Cachorro.php';
    require_once 'Lobo.php';
    require_once 'Gato.php';
    
    function cachorro()
    {
        echo PHP_EOL;
        echo PHP_EOL . "*******Cachorro********" . PHP_EOL;

        $dog = new Cachorro();

        $dog->nome = "Rex";
        $dog->darEspecie("Cão");
        
        echo $dog->nome . PHP_EOL;
        echo $dog->retornarEspecie() . PHP_EOL;

    }
    
    Cachorro();
      
?>