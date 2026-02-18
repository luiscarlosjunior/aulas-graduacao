<?php
    namespace Modelo\Conta;

    use Modelo\Conta\Conta;

    class ContaCorrente extends Conta 
    {

        public function sacar(float $saque) : float
        {
            return 1.5;
        }

        public function transfere(float $quantidade)
        {

        }

    }

?>