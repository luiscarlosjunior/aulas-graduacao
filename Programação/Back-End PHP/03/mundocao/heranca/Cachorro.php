<?php

    class Cachorro {
     public string $nome;
     public string $raca;
     public string $corPelo;

     public function Latir() {
        print("Au au!" . PHP_EOL);
     }

     public function Raca() {
         echo "Cachorro da raça " . $this->raca . PHP_EOL;
     }

    }

?>