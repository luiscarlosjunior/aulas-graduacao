<?php
    require 'Cliente.php';

    class ContaPoupanca extends Conta {
        
        private int $_diaDeposito;

        function __construct($agencia, $numeroConta, $diaDeposito)
        {
            $this->Agencia = $agencia;
            $this->NumeroConta = $numeroConta;
            $this->_diaDeposito = $diaDeposito;
        }

        function setDiaDeposito(int $diaDeposito)
        {
            $multiploCinco = $diaDeposito % 5; 

            if ($diaDeposito < 30 && 
                ($multiploCinco == 0)) {
                $this->_diaDeposito = $diaDeposito;
            }
        }

        function VerLucro()
        {
        }

    }
?>