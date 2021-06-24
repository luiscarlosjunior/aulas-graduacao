
<?php

// Importa a classe php
require_once "conexao.php";

$codigo = $_GET['codigo'];
$usuario = $_GET['usuario'];

$sql_update = "
    UPDATE registro SET USUARIO = '$usuario' where codigo = $codigo;
";

// Fazemos um update e pegamos se houve retorna de linhas
if(mysqli_query($con, $sql_update)) {
    echo "O usuário foi alterador com sucesso";
} 
else {
    echo "Erro ao atualizar os dados ".mysqli_error($con);
}

?>
