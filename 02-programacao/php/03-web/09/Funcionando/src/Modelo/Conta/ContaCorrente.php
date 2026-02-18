<?php
    namespace Modelo\Conta;

    use Modelo\Correntista\Titular;

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