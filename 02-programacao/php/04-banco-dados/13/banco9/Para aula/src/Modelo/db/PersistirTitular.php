<?php
    namespace Modelo\db;

    class PersistirTitular extends Conexao {
        
        // Pego os valores que foram enviados
        private string $nome; 
        private string $cpf;
        
        public function __construct(string $nome, string $cpf) {
            $this->nome = $nome;
            $this->cpf = $cpf;
        }

        public function inserirTitular() {
            // Faço o meu insert
            $sql_insert = " 
                INSERT INTO CLIENTE (nome, sobrenome, cpf) 
                    VALUES ('$this->nome', 'Santos', '$this->cpf');
                ";

            if(mysqli_query($this->abrirConexao(), $sql_insert)) {
                // TODO
                echo "Cadastrado com sucesso";
            } 
        }

    }


?>