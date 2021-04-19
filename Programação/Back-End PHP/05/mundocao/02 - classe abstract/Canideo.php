<?php

     abstract class Canideo {
        private float $tempoVida;
        private string $tamanho;
        private string $coloracao;
        private float $pelagem;
        private float $velocidade;

        public function setTempoVida(float $tempoVida) {
            $this->tempoVida = $tempoVida;
        } 
        
        public function getTempoVida() : float {
            return $this->tempoVida;
        } 

        public function setTamanho(string $tamanho) {
            $this->tamanho = $tamanho;
        }

        public function getTamanho() : string {
            return $this->tamanho;
        }

        public function setColoracao(string $coloracao) {
            $this->coloracao = $coloracao;
        }

        public function getColoracao() {
            return $this->coloracao;
        }

        public function setPelagem(float $pelagem) {
            $this->pelagem = $pelagem;
        }

        public function getPelagem() : float {
            return $this->pelagem;
        }

        public function setVelocidade(float $velocidade) {
            $this->velocidade = $velocidade;
        }

        public function getVelocidade() : float {
            return $this->velocidade;
        }

        public function Correr() {
            print("Estou correndo!" . PHP_EOL);
        }
        public function Dormir() {
            print("Estou dormindo!" . PHP_EOL);
        }

        public function Cacar() {
            print("Estou caçando!" . PHP_EOL);
        }

        public function Acordar() {
            print("Estou acordando!" . PHP_EOL);
        }
        
        public function Sociabilidade() {
            print("Ando em alcateia!" . PHP_EOL);
        }
    }
?>