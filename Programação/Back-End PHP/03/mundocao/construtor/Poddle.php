<?php

    class Poddle extends Cachorro {

        private string $raca = "Poddle";

        public function __construct($nome) 
        {
            parent::__construct($nome, $this->raca);
        }

        public function brincar() {
            print("Estou brincando..." . PHP_EOL);
        }

        public function Latir() {
            print("Au au au au au ! Au!" . PHP_EOL);
        }
    }

?>