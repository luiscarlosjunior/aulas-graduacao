<?php

    class Poddle extends Cachorro {

        public function __construct(string $nome)
        {
            // Mandando para a classe mãe
            parent::__construct($nome, "Poddle");
        }

        public function brincar() {
            echo "Gosto de brincar..." . PHP_EOL;
         }

         public function dormir(){
            echo "Eu durmo e fico sonhando.." . PHP_EOL;
        }

    }

?>