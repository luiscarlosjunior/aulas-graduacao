<?php

    class Lobo extends Canideo{
        private string $raca;
        private float $peso;

        public function setRaca(string $raca) {
            $this->raca = $raca;
        }

        public function getRaca() : string {
            return $this->raca;
        }

        public function setPeso(float $peso) {
            $this->peso = $peso;
        }

        public function getPeso() {
            return $this->peso;
        }

        public function Uivar() {
            print("Auuuuuuuuu!" . PHP_EOL);
        }

    }

?>