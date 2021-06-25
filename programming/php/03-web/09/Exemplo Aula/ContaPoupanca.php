<?php

    require_once '/../Correntista/Titular.php';

    class ContaPoupanca extends Conta 
    {

        public function __construct(Titular $titular)
        {
            
        }

        public function resgate(float $saque) : float
        {
            return 1.5;
        }
    }

?>