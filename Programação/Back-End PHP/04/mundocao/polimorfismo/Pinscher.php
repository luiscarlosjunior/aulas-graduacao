<?php

    class Pinscher extends Cachorro {

        private string $raca = "Pinscher";

        public function __construct($nome) 
        {
            parent::__construct($nome, $this->raca);
        }

        public function tremer() {
            print("Estou tremendo..." . PHP_EOL);
        }

        public function Latir() {
            print("Hau! grgrgrgrgrgr Hau!" . PHP_EOL);
        }

    }

?>