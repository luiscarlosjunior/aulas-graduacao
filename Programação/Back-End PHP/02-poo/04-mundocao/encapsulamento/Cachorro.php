<?php

    class Cachorro {
        private string $nome;
        private string $raca;
        private string $corPelo;
        private float $peso;

        public function setNome(string $nome) {
            $this->nome = $nome;
        } 
        
        public function getNome() : string {
            return $this->nome;
        } 

        public function setCorPelo(string $corPelo) {
            $this->corPelo = $corPelo;
        }

        public function getCorPelo() : string {
            return $this->corPelo;
        }

        public function setPeso(float $peso) {
            $this->peso = $peso;
        }

        public function getPeso() {
            return $this->peso;
        }

        public function setRaca(string $raca) {
            $this->raca = $raca;
        }

        public function getRaca() : string {
            return $this->raca;
        }

        public function Latir() {
            print("Au au!" . PHP_EOL);
        }

    }

?>