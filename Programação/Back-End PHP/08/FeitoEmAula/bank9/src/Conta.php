<?php
    class Conta {
        private Titular $titular;
        private float $saldo;

        public function sacar(float $sacar)
        {
            echo $sacar;
        }

        public function depositar(float $depositar)
        {

        }
        public function transfere(float $valor)
        {

        }
        public function getSaldo(): float
        {
            return $this->saldo;
        }
        public function getNomeTitular(): string
        {
            return $this->titular->getNome();
        }

        public function getCpfTitular()
        {
            return $this->titular->getCpf();
        }

    }
?>