<?php

namespace Uninove\Banco\Modelo\Conta;

use Uninove\Banco\Modelo\Pessoa;
use Uninove\Banco\Modelo\CPF;
use Uninove\Banco\Modelo\Endereco;

class Titular extends Pessoa
{
    private $endereco;

    public function __construct(CPF $cpf, string $nome, Endereco $endereco)
    {
        parent::__construct($nome, $cpf);
        $this->endereco = $endereco;
    }

    public function recuperaEndereco(): Endereco
    {
        return $this->endereco;
    }
}
