<?php

abstract class Conta
{
    private $agencia;
    private $titular;
    private $saldo;



    protected function saca(float $valorASacar): void
    {
        if ($valorASacar > $this->saldo) {
            echo "Saldo indisponível";
            return;
        }

        $this->saldo -= $valorASacar;
    }

    protected function depositar(float $valorADepositar): void
    {
        if ($valorADepositar < 0) {
            echo "Valor precisa ser positivo";
            return;
        }

        $this->saldo += $valorADepositar;
    }

    protected function setSaldo(float $saldo)
    {
        $this->saldo = $saldo;
    }

    protected function getSaldo(): float
    {
        return $this->agencia;
    }

    protected function setAgencia(float $agencia)
    {
        $this->agencia = $agencia;
    }

    protected function getAgencia(): float
    {
        return $this->saldo;
    }


    protected function getNomeTitular(): string
    {
        return $this->titular->recuperaNome();
    }

    protected function getCpfTitular(): string
    {
        return $this->titular->recuperaCpf();
    }

}
