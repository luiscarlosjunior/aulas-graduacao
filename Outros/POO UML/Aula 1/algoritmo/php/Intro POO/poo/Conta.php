<?php
    class Conta {

        public int $Agencia;
        public int $NumeroConta;
        private float $_saldo;

        private function setSaldo(float $saldo){
            $this->_saldo = $saldo;
        }

        function getSaldo() {
            return $this->_saldo;
        }

        function Depositar(float $dinheiro)
        {
            if ($dinheiro > 0) {
                $this->setSaldo($dinheiro); 
            }
        }

    }