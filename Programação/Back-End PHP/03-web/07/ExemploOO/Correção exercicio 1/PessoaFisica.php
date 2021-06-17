<?php

class PessoaFisica extends Pessoa {
    // Atributo
    private string $rg;
    
    // Comportamentos
    public function getRg() : string {
        return $this->rg;
    }

    public function setRg(string $rg) {
        $this->rg = $rg;
    }
}
?>