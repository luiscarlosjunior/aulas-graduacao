<?php

    class Cachorro {

        private string $nome;
        private int $idade;
        public string $raca;
        public string $corOlhos;
        public string $corPelo;

        public function getNome() {
            return $this->nome;
        }

        public function setNome(string $nome) {
            $this->nome = $nome;
        }

        public function getIdade() {
            return $this->idade;
        }

        public function setIdade(int $idade) {
            $this->idade = $idade;
        }

        // Comportamentos / Ações
        public function latir(string $latido){
            echo $latido . PHP_EOL;
        }

        public function comer() {
            echo "Estou comendo..!" . PHP_EOL;
        }

        public function dormir(){
            echo "Estou dormindo com olhos fechados.." . PHP_EOL;
        }

        public function correr(){
            echo "Estou correndo.." . PHP_EOL;
        }

        public function acordar(){
            echo "Estou acordando.." . PHP_EOL;
        }

    }

?>