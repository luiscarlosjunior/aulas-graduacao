<?php
    require_once '/../Correntista/Titular.php';

    class ContaCorrente extends Conta 
    {

        public function __construct(Titular $titular)
        {
            
        }

        public function sacar(float $saque) : float
        {
            return 1.5;
        }

        public function transfere(float $quantidade)
        {

        }

    }

?>