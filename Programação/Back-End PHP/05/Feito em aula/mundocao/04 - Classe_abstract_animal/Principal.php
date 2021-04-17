<?php

    require_once 'Animal.php';
    require_once 'Cachorro.php';
    require_once 'Lobo.php';
    require_once 'Gato.php';
    require_once 'Zebra.php';
    
    function Zebra()
    {
        echo PHP_EOL;
        /*
        echo PHP_EOL . "*******Cachorro********" . PHP_EOL;

        $dog = new Cachorro();

        $dog->nome = "Rex";
        $dog->darEspecie("Cão");
        
        echo $dog->nome . PHP_EOL;
        echo $dog->retornarEspecie() . PHP_EOL;
*/

        $dog = new Zebra();

        $dog->especie = "zebra";
        $dog->numeroPatas = 4;
        
        echo $dog->especie . PHP_EOL;
        echo $dog->numeroPatas . PHP_EOL;
        echo $dog->Som() . PHP_EOL;
    }
    
    Zebra();
      
?>