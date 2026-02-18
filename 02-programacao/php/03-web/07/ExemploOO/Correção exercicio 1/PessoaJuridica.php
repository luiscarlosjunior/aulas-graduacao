<?php
class PessoaJuridica extends Pessoa {
    // Atributo
    private string $cnpj;
    
    // Comportamentos
    public function getCnpj() : string {
        return $this->cnpj;
    }

    public function setCnpj(string $cnpj) {
        $this->cnpj = $cnpj;
    }
}

?>