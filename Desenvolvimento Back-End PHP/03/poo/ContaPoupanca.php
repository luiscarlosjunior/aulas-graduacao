<?php
    class ContaPoupanca extends Conta {
        
        private int $_diaDeposito;

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