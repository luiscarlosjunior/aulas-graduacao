<?php

    class Cachorro {

        public string $nome;
        public string $raca;
        public int $idade;
        public string $corOlhos;
        public string $corPelo;

        // Comportamentos / Ações
        public function latir(string $latido){
            echo $latido . PHP_EOL;
        }

        public function comer() {
            echo "Estou comendo..!" . PHP_EOL;
        }

        public function dormir(){
            echo "Estou dormindo.." . PHP_EOL;
        }

        public function correr(){
            echo "Estou correndo.." . PHP_EOL;
        }

        public function acordar(){
            echo "Estou acordando.." . PHP_EOL;
        }

    }

?>