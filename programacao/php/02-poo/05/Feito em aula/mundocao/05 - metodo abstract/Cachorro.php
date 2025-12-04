<?php

    class Cachorro extends Canideo{
        private string $nome;
        private string $raca;
        private string $idade;
        private float $peso;

        public function setNome(string $nome) {
            $this->nome = $nome;
        } 
        
        public function getNome() : string {
            return $this->nome;
        } 
        
        public function setRaca(string $raca) {
            $this->raca = $raca;
        }

        public function getRaca() : string {
            return $this->raca;
        }

        public function setIdade(string $idade) {
            $this->idade = $idade;
        }

        public function getIdade() : string {
            return $this->idade;
        }

        public function setPeso(float $peso) {
            $this->peso = $peso;
        }

        public function getPeso() {
            return $this->peso;
        }

        public function Ladrar() {
            print("Au au!" . PHP_EOL);
        }

        public function Sociabilidade() {
            echo "São sociaveis com os humanos" . PHP_EOL;
        }

    }

?>