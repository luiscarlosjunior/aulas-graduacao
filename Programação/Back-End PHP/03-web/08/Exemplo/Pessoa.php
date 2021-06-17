<?php
    class Pessoa {
        // Atributo
        private string $nome;
        
        // Comportamentos
        public function getNome() : string {
            return $this->nome;
        }

        public function setNome(string $nome) {
            $this->nome = $nome;
        }
    }
?>