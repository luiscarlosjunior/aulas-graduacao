<?php
    class Cliente {
        
        private string $_cpf;
        private string $_nome;
        private string $_telefone;
        private string $_endereco;
        
        function __construct($cpf, $nome, $telefone, $endereco)
        {
            $this->_cpf = $cpf;
            $this->_nome = $nome;
            $this->_telefone = $telefone;
            $this->_endereco = $endereco;
        }

        public ContaPoupanca $contaPoupanca;

        function VisualizarRelatorio() {
            $relatorio = PHP_EOL . 
                "*********************************" . PHP_EOL . 
                "A conta de numero";
        }

    }
?>