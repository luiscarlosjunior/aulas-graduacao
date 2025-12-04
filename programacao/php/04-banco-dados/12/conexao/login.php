<?php

    // Importa a classe php
    require_once "conexao.php";

    $usuario = $_GET['usuario'];
    $senha = $_GET['senha'];

    // Faço o meu select
    $sql_query = " 
        SELECT NOME FROM REGISTRO 
	        WHERE usuario = '$usuario' AND senha = '$senha';
        ";

    // Fazemos a consulta e pegamos se houve retorna de linhas
    $resultado = mysqli_query($con, $sql_query);

    if(mysqli_num_rows($resultado) > 0) {

        $linha = mysqli_fetch_assoc($resultado);
        
        // Pego a coluna da consulta realizada
        $nome = $linha['NOME'];
        
        // Exibo o nome
        echo $nome;

    } else if (mysqli_num_rows($resultado) == 0)
    {
        echo "Usuario ou senha incorretos";
    } 
    else {
        echo "Erro ao registrar dados ".mysqli_error($con);
    }

?>