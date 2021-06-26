<?php

    class Pinscher extends Cachorro {

        public function __construct(string $nome)
        {
            // Mandando para a classe mãe
            parent::__construct($nome, "Pinscher");
        }

        public function tremer() {
            echo "Estou tremendo..." . PHP_EOL;
        }

        public function dormir(){
            echo "Eu durmo e estou tremendo.." . PHP_EOL;
        }

    }

?>