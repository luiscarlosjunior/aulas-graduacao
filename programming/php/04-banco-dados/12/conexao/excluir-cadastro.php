<?php

// Importa a classe php
require_once "conexao.php";

$codigo = $_GET['codigo'];

$sql_delete = "
    DELETE FROM registro where codigo = $codigo;
";

// Fazemos um delete
if(mysqli_query($con, $sql_delete)) {
    echo "O usuário foi excluido com sucesso";
} 
else {
    echo "Erro ao exlcuir usuário ".mysqli_error($con);
}

?>
