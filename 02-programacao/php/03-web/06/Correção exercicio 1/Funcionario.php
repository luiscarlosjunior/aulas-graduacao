<?php
class Funcionario extends PessoaFisica {
    // Atributo
    private string $cartao;
    
    // Comportamentos
    public function getCartao() : string {
        return $this->cartao;
    }

    public function setCartao(string $cartao) {
        $this->cartao = $cartao;
    }
}
?>