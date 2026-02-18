<?php
namespace Modelo\db;

class Conexao {
    // Declarando as variáveis de acesso
    private string $nome_servidor = "localhost";
    private string $nome_bd = "usuario";
    private string $usuario_bd = "root";
    private string $senha_bd = "";
    
    function abrirConexao() {
        return mysqli_connect($this->nome_servidor, $this->usuario_bd, $this->senha_bd, $this->nome_bd, 3303);
    }
}

?>