<?php

    class Titular {

        private Cpf $cpf;
        private string $nome;
        
        public function getCpf() : Cpf
        {
            return $this->cpf;
        }

        public function getNome() : string
        {
            return $this->nome;
        }

    }

?>